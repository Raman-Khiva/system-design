# Shared Data & Race Condition

## What

A **race condition** happens when two or more threads access the same shared data at the same time, and the final result depends on the order in which the threads execute.

The key phrase: **"depends on timing."** If the result is correct only by luck, you have a race condition.

---

## Why

Threads share the same memory (heap). When one thread reads a value and another thread modifies it before the first thread writes back, the data is lost.

This is not a bug in Java — it's a fundamental problem of concurrent access to shared state.

---

## How — A Concrete Example

```java
public class Counter {
    private int count = 0;

    public void increment() {
        count++;  // looks like one operation
    }
}
```

`count++` is **not** a single operation. The JVM does three things:
1. **Read** `count` from memory into a register
2. **Add** 1 to the register
3. **Write** the register value back to memory

**What happens with 2 threads:**

```
Time ──────────────────────────────────────────────►

Thread A:  READ count=100 ──── ADD 1 ──── WRITE count=101
Thread B:        READ count=100 ──── ADD 1 ──── WRITE count=101

Final result: 101  (two increments, but count only went up by 1)
```

Both threads read 100 before either wrote back. Both write 101. One increment is **lost**.

**The window of vulnerability** is the gap between read and write. If another thread modifies the value in that window, the first thread's write overwrites the change.

---

## The Problem Is Invisible

```java
Counter counter = new Counter();

// Start 2 threads, each increments 1000 times
Thread t1 = new Thread(() -> { for (int i = 0; i < 1000; i++) counter.increment(); });
Thread t2 = new Thread(() -> { for (int i = 0; i < 1000; i++) counter.increment(); });

t1.start();
t2.start();
t1.join();
t2.join();

System.out.println("Count: " + counter.count);  // Expect 2000, often get less
```

With 2000 increments, you might get `1987`, `1999`, `2000` — it's unpredictable. The more threads and the longer the critical section, the worse it gets.

---

## Why `count++` Alone Is Not Safe

Even though `count++` looks atomic, the JVM compiles it to multiple bytecode instructions. There is no guarantee that a thread won't be interrupted between them.

**Non-atomic operations** (read-modify-write, check-then-act) are where race conditions live.

---

## What Makes It Worse

| Factor | Why It Matters |
|--------|----------------|
| **More threads** | More threads = more interleaving = more lost updates |
| **Longer critical section** | More instructions between read and write = larger window |
| **Shared mutable state** | The more shared data, the more places for races |
| **No synchronization** | Without locks, threads have no coordination |

---

## The Fix (Preview)

You need **mutual exclusion** — only one thread can execute the critical section at a time. That's what `synchronized` does (Topic 4).

```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;  // now only one thread at a time
    }
}
```

---

## Implementation (What We Ran)

```java
// Counter.java
public class Counter {
    private int count = 0;
    public void increment() { count++; }
    public int getCount() { return count; }
}

// Main.java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 2000; i++) counter.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 2000; i++) counter.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count (expected 4000) : " + counter.getCount());
    }
}
```

**Sample output (5 runs):**
```
Final count (expected 4000) : 3350
Final count (expected 4000) : 3405
Final count (expected 4000) : 3463
Final count (expected 4000) : 4000
Final count (expected 4000) : 3548
Final count (expected 4000) : 3512
```

The count is **almost always less than 4000** — increments are lost due to the race condition.

---

## Key Takeaways

- Race condition = result depends on thread timing
- `count++` is 3 operations: read, add, write
- The "window of vulnerability" is between read and write
- Shared mutable state + no synchronization = data corruption
- Fix: mutual exclusion (`synchronized`, locks, atomics)

---

## Questions You Must Be Able to Answer

1. What is a race condition?
2. Why is `count++` not atomic? What three steps does it do?
3. What is the "window of vulnerability"?
4. What happens when two threads read the same value and both write back?
5. Why is the result unpredictable?
6. What factors make race conditions worse?
7. What is the fix? (mutual exclusion — `synchronized`)