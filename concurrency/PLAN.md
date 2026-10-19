# Concurrency — Learning Plan & Tracker

**Method:** Understand → Implement → Feel the pain → Fix → Repeat  
**Language:** Java  
**Purpose:** Deep system understanding of concurrency — how it works, why it breaks, how to control it

---

## How to use this file

1. Follow the topic order — each topic builds on the previous one.
2. For each topic: read the concept, write the code, answer what/why/how.
3. Mark ✅ only when you can implement it without looking.
4. Use the **Quick Revision** table before interviews.

---

## Topic Order (Chained — Do Not Skip)

| # | Topic | What You'll Learn | Status |
|---|-------|-------------------|--------|
| 0 | **Concurrency Fundamentals** | What is concurrency, why it exists, parallelism vs concurrency, processes vs threads | ⬜ |
| 1 | **Threads & Runnable** | Creating threads, thread lifecycle, states | ⬜ |
| 2 | **Thread Operations** | sleep, join, interrupt, yield, priority | ⬜ |
| 3 | **Shared Data & Race Condition** | What breaks when threads share data | ⬜ |
| 4 | **synchronized** | Method-level, block-level, static | ⬜ |
| 5 | **volatile** | Visibility guarantee, happens-before | ⬜ |
| 6 | **Atomic Classes** | AtomicInteger, AtomicReference, CAS | ⬜ |
| 7 | **Locks (ReentrantLock)** | lock, unlock, tryLock, lockInterruptibly | ⬜ |
| 8 | **Condition Variables** | await, signal, signalAll | ⬜ |
| 9 | **Deadlock** | What, why, how to prevent, how to detect | ⬜ |
| 10 | **Thread-Safe Collections** | ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue | ⬜ |
| 11 | **ExecutorService & Thread Pools** | Fixed, cached, scheduled pools, Future | ⬜ |
| 12 | **CountDownLatch & CyclicBarrier** | Synchronization utilities | ⬜ |
| 13 | **Semaphore** | Permits, acquire, release | ⬜ |
| 14 | **CompletableFuture** | Async programming, chaining, combining | ⬜ |
| 15 | **Classic Problems** | Odd-even, producer-consumer, dining philosophers, sleeping barber | ⬜ |
| 16 | **Concurrency in LLD** | Applying concurrency to design problems | ⬜ |

---

## Topic Dependency Graph

```
1. Threads & Runnable
   └── 2. Thread Operations
        └── 3. Shared Data & Race Condition
             ├── 4. synchronized
             │    ├── 5. volatile
             │    ├── 6. Atomic Classes
             │    └── 7. Locks (ReentrantLock)
             │         └── 8. Condition Variables
             │              └── 9. Deadlock
             ├── 10. Thread-Safe Collections
             │    └── 11. ExecutorService & Thread Pools
             │         ├── 12. CountDownLatch & CyclicBarrier
             │         ├── 13. Semaphore
             │         └── 14. CompletableFuture
             └── 15. Classic Problems
                  └── 16. Concurrency in LLD
```

---

## Overall Progress

| Status | Count |
|--------|-------|
| ✅ Done | 0 |
| ⬜ Pending | 16 |

---

## Next Immediate Step

**Topic 1 — Threads & Runnable**

Goal: Create a thread, understand its lifecycle, know the 6 thread states.

Ready? I'll give you the concept, you implement, we discuss.
