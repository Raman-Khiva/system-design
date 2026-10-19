# Concurrency — Learning Method

**Goal:** Build a deep, practical understanding of how concurrency works under the hood — not just how to use it, but why it behaves the way it does.

**For each topic, follow this exact process:**

---

## The 5-Step Process

| # | Step | What You Do | Output |
|---|------|-------------|--------|
| 1 | **What** | Read the concept. What is it? What problem does it solve? | You can explain it in 2-3 sentences. |
| 2 | **Why** | Why does this exist? What breaks without it? | You can describe the pain. |
| 3 | **How** | How does it work internally? | You can explain the mechanism. |
| 4 | **Implement** | Write code. Make it work. | Working Java code. |
| 5 | **Break & Fix** | Intentionally break it. Then fix it. | You understand edge cases. |

---

## Rules

1. **No analogies** — explain in technical terms only.
2. **Always implement** — reading is not enough.
3. **Answer what/why/how** — before moving to next topic.
4. **Chain topics** — each topic builds on the previous. Don't skip.
5. **Mark ✅ only when** — you can write the code without looking.

---

## Example: How to Answer Each Topic

### Topic: synchronized

**What:**  
`synchronized` is a keyword that ensures only one thread can execute a method or block at a time. It provides mutual exclusion.

**Why:**  
Without it, multiple threads reading and writing shared data simultaneously causes race conditions. The result is unpredictable and incorrect.

**How:**  
Every Java object has an internal lock (monitor). When a thread enters a synchronized method, it acquires the object's lock. Other threads block until the lock is released. When the thread exits, the lock is released.

**Implement:**  
```java
public class Counter {
    private int count = 0;
    public synchronized void increment() {
        count++;
    }
}
```

**Break & Fix:**  
- Break: Remove synchronized → run with 1000 threads → count < 1000 (race condition).  
- Fix: Add synchronized → count = 1000 always.

---

## Quick Revision Table

| Topic | Remember it as... |
|-------|-------------------|
| Threads & Runnable | "A thread is an independent path of execution." |
| Thread Operations | "Control threads: sleep, join, interrupt." |
| Race Condition | "Two threads, same data, wrong result." |
| synchronized | "One thread at a time — mutual exclusion." |
| volatile | "Always read from main memory — visibility." |
| Atomic Classes | "Lock-free thread safety using CAS." |
| ReentrantLock | "Manual lock with more control than synchronized." |
| Condition Variables | "Wait for a condition, get signaled when ready." |
| Deadlock | "Two threads waiting on each other forever." |
| Thread-Safe Collections | "Collections that handle concurrency internally." |
| ExecutorService | "Thread pool — reuse threads, don't create new ones." |
| CountDownLatch | "Wait for N threads to finish." |
| CyclicBarrier | "N threads wait for each other at a barrier." |
| Semaphore | "N permits — only N threads can access." |
| CompletableFuture | "Async programming with chaining." |
| Classic Problems | "Odd-even, producer-consumer, dining philosophers." |
| Concurrency in LLD | "Apply all of the above to design problems." |
