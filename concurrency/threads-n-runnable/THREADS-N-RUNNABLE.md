# Threads & Runnable

## What

A **thread** is the smallest unit of execution inside a process. It is an independent path of execution with its own call stack, but shares the process's memory (heap) with other threads.

Java provides two ways to create a thread:

**Way 1 — extends Thread:**
```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

MyThread t = new MyThread();
t.start();  // starts the thread
```

**Way 2 — implements Runnable (preferred):**
```java
class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

Thread t = new Thread(new MyTask());
t.start();
```

**Why Way 2 is preferred:** Java doesn't support multiple inheritance. If you extend `Thread`, you can't extend anything else. `Runnable` is an interface — you can extend a class and implement `Runnable` at the same time. It also separates the task (what to run) from the thread (how to run it).

---

## Why

Without threads, your program is a single flow — one statement after another. If one part waits (I/O, network), everything stops.

Threads let you:
- Run multiple tasks in the same process
- Share memory directly (no IPC overhead)
- Keep the CPU busy while some tasks wait

---

## How — Thread Lifecycle

A thread moves through 6 states:

```
NEW → RUNNABLE → RUNNING → BLOCKED/WAITING/TIMED_WAITING → TERMINATED
```

| State | What It Means |
|-------|---------------|
| **NEW** | Thread created, `start()` not called yet |
| **RUNNABLE** | Thread is ready to run, waiting for CPU time |
| **RUNNING** | Thread is currently executing on CPU |
| **BLOCKED** | Thread is waiting for a monitor lock (synchronized) |
| **WAITING** | Thread is waiting indefinitely for another thread |
| **TIMED_WAITING** | Thread is waiting for a specific time period |
| **TERMINATED** | Thread has finished execution |

```java
Thread t = new Thread(() -> {
    System.out.println("State while running: " + Thread.currentThread().getState());
});

System.out.println("Before start: " + t.getState());  // NEW
t.start();
// After start: RUNNABLE (or RUNNING, depending on OS scheduling)
// After run() completes: TERMINATED
```

---

## start() vs run()

```java
MyThread t = new MyThread();

t.run();   // WRONG — calls run() as a normal method, executes in main thread
t.start(); // CORRECT — creates a new thread, JVM calls run() in that thread
```

**`run()`** is just a method. Calling it directly executes it in the current thread — no new thread is created.

**`start()`** tells the JVM to create a new thread, which then calls `run()` in that new thread.

---

## Thread.currentThread()

```java
System.out.println(Thread.currentThread().getName());  // "main"

Thread t = new Thread(() -> {
    System.out.println(Thread.currentThread().getName());  // "Thread-0"
});
t.start();
```

Every thread has a name. The main thread is called `"main"`. Others are `"Thread-0"`, `"Thread-1"`, etc. You can set custom names:

```java
Thread t = new Thread(() -> {}, "DatabaseThread");
t.start();
System.out.println(t.getName());  // "DatabaseThread"
```

---

## Key Takeaways

- A thread is an independent path of execution inside a process
- Two ways to create: `extends Thread` or `implements Runnable` (preferred)
- `Runnable` is preferred because Java has no multiple inheritance
- Thread lifecycle: NEW → RUNNABLE → RUNNING → BLOCKED/WAITING → TERMINATED
- `start()` creates a new thread; `run()` is just a method call
- `Thread.currentThread()` gives you the currently executing thread
- Custom thread names help with debugging

---

## Questions You Must Be Able to Answer

1. What is a thread?
2. What are the two ways to create a thread in Java? Which is preferred and why?
3. What are the 6 thread states?
4. What is the difference between `start()` and `run()`?
5. What happens if you call `run()` directly instead of `start()`?
6. What is `Thread.currentThread()`?
7. Can a thread go back to RUNNABLE after being TERMINATED?
8. Why can't you call `currentThread()` on a thread instance?
