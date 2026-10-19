# Concurrency Fundamentals

## What Is Concurrency

Concurrency is the ability of a system to handle multiple tasks **at the same time**. Not necessarily simultaneously, but in overlapping time periods.

Your CPU has multiple cores. Without concurrency, only one task runs at a time — the rest wait. With concurrency, the system switches between tasks so fast that they appear to progress together.

---

## Why Concurrency Exists

**The problem:** A single task often waits — for disk I/O, network response, user input. While it waits, the CPU sits idle.

**The solution:** When one task waits, switch to another task. The CPU is always doing useful work.

**Real scenario in your code:**

```java
// Without concurrency — sequential, slow
String data = readFromDatabase();   // waits 200ms
String result = callExternalAPI(data);  // waits 500ms
// Total: 700ms, CPU idle 700ms

// With concurrency — overlapping, fast
Thread t1 = new Thread(() -> readFromDatabase());
Thread t2 = new Thread(() -> callExternalAPI());
// Both waiting periods overlap, CPU switches between them
```

---

## Parallelism vs Concurrency

These are different concepts:

| | Concurrency | Parallelism |
|---|-------------|-------------|
| **Definition** | Multiple tasks in overlapping time periods | Multiple tasks at the exact same time |
| **CPU cores** | Can work on 1 core (task switching) | Requires multiple cores |
| **Focus** | Structure — how you organize tasks | Execution — how tasks actually run |
| **Example** | Single core switching between tasks | 4 cores running 4 tasks simultaneously |

**Key insight:** Concurrency is about **dealing with** many things. Parallelism is about **doing** many things at once.

---

## Process vs Thread

| | Process | Thread |
|---|---------|--------|
| **What** | A running program | A unit of execution inside a process |
| **Memory** | Own memory space (isolated) | Shares memory with other threads in same process |
| **Communication** | IPC (pipes, sockets, files) | Direct shared memory |
| **Creation cost** | Heavy (new memory space) | Light (shares existing memory) |
| **Crash** | One process crash doesn't affect others | One thread crash can kill the process |

**Why threads for concurrency:** Threads share memory, so they can work on the same data without expensive IPC. This is also what makes concurrency hard — shared memory means race conditions.

---

## The Core Problem Concurrency Creates

When threads share memory, they can interfere with each other:

```java
// Thread A reads balance = 100
// Thread B reads balance = 100
// Thread A writes balance = 150 (adds 50)
// Thread B writes balance = 120 (adds 20)
// Result: 120 (should be 170)
```

This is why concurrency requires **synchronization** — controlling how threads access shared data.

---

## Key Takeaways

- Concurrency = handling multiple tasks in overlapping time periods
- It exists so the CPU is never idle while tasks wait for I/O
- Concurrency is about structure; parallelism is about execution
- Threads share memory — fast communication but risk of interference
- Synchronization is the tool to control thread access to shared data

---

## Questions You Must Be Able to Answer

1. What is concurrency?
2. Why does concurrency exist? What problem does it solve?
3. What is the difference between concurrency and parallelism?
4. What is the difference between a process and a thread?
5. Why does concurrency make programming harder?
6. Can you have concurrency on a single-core CPU? Can you have parallelism?
7. Why do threads share memory? What is the benefit and the risk?
