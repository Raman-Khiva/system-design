# Thread Operations

## What

Thread operations are methods that let you **control** how threads execute — pause them, wait for them, signal them to stop, and influence scheduling.

| Operation | Method | What It Does |
|-----------|--------|-------------|
| **sleep** | `Thread.sleep(ms)` | Pauses current thread for a time period |
| **join** | `t.join()` | Waits for thread `t` to finish |
| **interrupt** | `t.interrupt()` | Signals thread `t` to stop what it's doing |
| **yield** | `Thread.yield()` | Hints the scheduler to switch to another thread |
| **priority** | `t.setPriority(1-10)` | Influences which threads get CPU time first |

---

## sleep()

Pauses the **current** thread for a specified time. The thread enters `TIMED_WAITING` state.

```java
Thread t = new Thread(() -> {
    for (int i = 1; i <= 5; i++) {
        System.out.println("Count: " + i);
        try {
            Thread.sleep(1000);  // pause 1 second
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted!");
            return;
        }
    }
});
t.start();
```

**Key points:**
- `sleep()` is **static** — always affects the current thread
- The thread **does not release locks** while sleeping
- It throws `InterruptedException` — you must handle it
- After sleeping, the thread goes back to `RUNNABLE` (not necessarily `RUNNING` immediately)

---

## join()

Waits for another thread to finish. The current thread blocks until the target thread terminates.

```java
Thread t = new Thread(() -> {
    System.out.println("Worker thread running");
    try { Thread.sleep(3000); } catch (InterruptedException e) {}
    System.out.println("Worker thread done");
});

t.start();
System.out.println("Main waiting for worker...");
t.join();  // main thread blocks here until t finishes
System.out.println("Main continues after worker done");
```

**Output:**
```
Main waiting for worker...
Worker thread running
Worker thread done
Main continues after worker done
```

**Key points:**
- `join()` is an **instance** method — called on the thread you want to wait for
- The calling thread enters `WAITING` state
- Also throws `InterruptedException`
- You can specify a timeout: `t.join(2000)` — waits max 2 seconds

---

## interrupt()

Signals a thread to stop what it's doing. It does **not** force the thread to stop — it sets a flag that the thread can check.

```java
Thread t = new Thread(() -> {
    while (!Thread.currentThread().isInterrupted()) {
        System.out.println("Working...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Interrupted! Cleaning up...");
            return;  // exit the thread
        }
    }
});

t.start();
Thread.sleep(2000);
t.interrupt();  // signal the thread to stop
```

**Key points:**
- `interrupt()` sets the thread's **interrupt flag** to `true`
- If the thread is sleeping/waiting, it throws `InterruptedException`
- The thread decides how to respond — it's a cooperative mechanism
- `Thread.interrupted()` checks **and clears** the flag
- `Thread.currentThread().isInterrupted()` checks **without clearing**

---

## yield()

Hints to the scheduler that the current thread is willing to give up its CPU time. The scheduler may ignore this hint.

```java
Thread t = new Thread(() -> {
    for (int i = 0; i < 100; i++) {
        System.out.println("Count: " + i);
        Thread.yield();  // hint: let other threads run
    }
});
t.start();
```

**Key points:**
- `yield()` is **static** — affects the current thread
- It's a **hint**, not a guarantee — the scheduler may ignore it
- The thread goes from `RUNNING` to `RUNNABLE` (still eligible to run)
- Rarely used in practice — the OS scheduler is usually better at making this decision

---

## Priority

Threads have a priority from 1 (lowest) to 10 (highest). Default is 5.

```java
Thread t1 = new Thread(() -> {
    for (int i = 0; i < 5; i++) {
        System.out.println("Low priority: " + i);
    }
});

Thread t2 = new Thread(() -> {
    for (int i = 0; i < 5; i++) {
        System.out.println("High priority: " + i);
    }
});

t1.setPriority(Thread.MIN_PRIORITY);  // 1
t2.setPriority(Thread.MAX_PRIORITY);  // 10

t1.start();
t2.start();
```

**Key points:**
- Priority is a **hint** to the scheduler — not a guarantee
- Behavior varies across operating systems
- Some OSes ignore thread priorities entirely
- Default priority is 5 (`Thread.NORM_PRIORITY`)
- Never rely on priority for correctness — use it only for optimization

---

## Summary Table

| Operation | Type | Effect | Throws |
|-----------|------|--------|--------|
| `sleep(ms)` | Static | Pauses current thread for ms | InterruptedException |
| `join()` | Instance | Waits for target thread to finish | InterruptedException |
| `interrupt()` | Instance | Sets interrupt flag on target thread | — |
| `yield()` | Static | Hints scheduler to switch threads | — |
| `setPriority(int)` | Instance | Sets thread priority (1-10) | IllegalArgumentException |

---

## Key Takeaways

- `sleep()` pauses the current thread, does not release locks
- `join()` makes the calling thread wait for the target thread
- `interrupt()` sets a flag — it does not force a thread to stop
- `yield()` is a hint that the scheduler may ignore
- Priority is a hint — never rely on it for correctness
- `Thread.interrupted()` checks and clears; `isInterrupted()` checks only

---

## Questions You Must Be Able to Answer

1. What does `sleep()` do? What state does the thread enter?
2. Does `sleep()` release locks? (No)
3. What is the difference between `join()` and `sleep()`?
4. Does `interrupt()` force a thread to stop? (No — it sets a flag)
5. What is the difference between `Thread.interrupted()` and `isInterrupted()`?
6. What does `yield()` do? Is it guaranteed to switch threads?
7. What is the default thread priority? What is the range?
8. Can you rely on thread priority for program correctness? (No)
