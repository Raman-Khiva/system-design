# synchronized

## What

`synchronized` is Java's built-in mechanism for **mutual exclusion**. It ensures that only one thread can execute a critical section of code at a time.

It provides two things:
1. **Mutual exclusion** — only one thread in the critical section
2. **Visibility** — changes made inside are visible to other threads

---

## How It Works — The Monitor Lock

Every Java object has an **internal lock** (called a **monitor** or **intrinsic lock**).

```
┌─────────────────────────────────────┐
│           Object (heap)             │
│  ┌─────────────────────────────┐   │
│  │       Monitor (lock)        │   │  ← One per object
│  │   - lock count              │   │
│  │   - owning thread           │   │
│  │   - waiting threads queue   │   │
│  └─────────────────────────────┘   │
└─────────────────────────────────────┘
```

When a thread enters a `synchronized` method/block:
1. It tries to acquire the object's lock
2. If free → acquires, executes
3. If taken → blocks, goes to `BLOCKED` state, waits in queue
4. On exit → releases lock, next waiting thread acquires

---

## synchronized Method

```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;  // only one thread at a time
    }

    public synchronized int getCount() {
        return count;  // also synchronized for visibility
    }
}
```

**Which lock?** The instance (`this`) — the object on which the method is called.

```java
Counter c1 = new Counter();
Counter c2 = new Counter();

// t1 calls c1.increment() → locks c1
// t2 calls c2.increment() → locks c2
// These run IN PARALLEL — different objects, different locks
```

---

## synchronized Block (More Granular)

```java
public class Counter {
    private int count = 0;
    private final Object lock = new Object();  // dedicated lock object

    public void increment() {
        synchronized (lock) {
            count++;  // critical section
        }
        // non-critical code here runs without lock
    }
}
```

**Why use a block?**
- Reduce lock scope → better performance
- Use a dedicated lock object → separate from the object's state
- Lock on a different object than `this`

**Best practice:** Use a private final lock object, not `this`.

```java
// Good
private final Object lock = new Object();
synchronized (lock) { ... }

// Bad — external code could lock on your object
synchronized (this) { ... }
```

---

## Static synchronized (Class-Level Lock)

```java
public class Counter {
    private static int count = 0;

    public static synchronized void increment() {
        count++;  // locks the Class object, not an instance
    }
}
```

**Which lock?** The `Class` object (`Counter.class`).

```java
Counter c1 = new Counter();
Counter c2 = new Counter();

// t1 calls c1.increment() → locks Counter.class
// t2 calls c2.increment() → ALSO locks Counter.class
// These run SEQUENTIALLY — same class lock
```

**Rule:** Static synchronized locks the **class**. Instance synchronized locks the **instance**. They are completely independent locks.

---

## Fixed Counter (Topic 3 Solution)

```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

Now 2 threads × 2000 increments = exactly 4000, every time.

---

## Implementation (What We Ran)

```java
// Non-synchronized — shows race condition
class NSCounter {
    private int count = 0;
    public void increment() { count++; }
    public int getCount() { return count; }
}

// Synchronized method — fixes race condition
public class Counter {
    private int count = 0;
    public synchronized void increment() { count++; }
    public synchronized int getCount() { return count; }
}

// Synchronized block with private lock — more granular
class SBCounter {
    private int count = 0;
    private final Object lock = new Object();
    public void increment() {
        synchronized (lock) { count++; }
    }
}

// Static synchronized — class-level lock
class SSCounter {
    private static int count = 0;
    public static synchronized void increment() { count++; }
}
```

**Test results (10 runs each):**

```
Non-synchronized counter (race condition visible):
Final count (expected 4000) : 3165
Final count (expected 4000) : 4000
Final count (expected 4000) : 4000
Final count (expected 4000) : 4000
...

Synchronized counter (always correct):
Final count (expected 4000) : 4000
Final count (expected 4000) : 4000
...
```

---

## Key Takeaways

- `synchronized` provides mutual exclusion + visibility
- Every object has a monitor lock
- Instance method → locks `this`
- Static method → locks the Class object
- Different objects = different locks = can run in parallel
- `synchronized` block with private lock = better encapsulation
- `synchronized` prevents instruction reordering (happens-before)

---

## Questions You Must Be Able to Answer

1. What does `synchronized` provide? (mutual exclusion + visibility)
2. What is a monitor lock? Does every object have one? (yes)
3. What lock does an instance synchronized method use? (`this`)
4. What lock does a static synchronized method use? (the Class object)
5. Can two threads run synchronized instance methods on **different** objects simultaneously? (yes)
6. Can a thread run an instance synchronized method and a static synchronized method simultaneously? (yes — different locks)
7. What is the difference between `synchronized(this)` and a private lock object?
8. Does `synchronized` prevent reordering? (yes — it provides happens-before)