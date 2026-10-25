# Design Patterns: Learn by Doing

Learn design patterns in Java by hitting the problem first, then discovering the pattern that solves it.

This repo holds my work on 10 patterns, the reasoning behind each one, and two files that let you learn the same way with any LLM.

---

## Why this exists

Most people learn design patterns like this:

> read the definition → memorize the UML → see an example → hope to recognize it later

That gives you pattern names, but not the judgment to know when to use one, or when not to.

I learned them the opposite way:

> get a problem → build a simple solution → extend it until it hurts → find the cause → discover a better design → learn the pattern's name

By the time a pattern got its name, I already understood why it was needed. The pattern felt like the answer to a problem I had lived through.

---

## How it works: the approach

**In one line:** Scenario → Build naive → Feel the pain → Diagnose → Discover the design → Refactor → Consolidate

Every pattern follows these seven stages.

| Step | Stage | What happens |
| ---- | ----- | ------------ |
| 1 | **Understand the scenario** | You get a concrete problem with clear requirements. The pattern is not named. |
| 2 | **Build the naive solution** | You write the simplest reasonable solution in Java, with no patterns. This is your working baseline. |
| 3 | **Experience the pain** | New requirements arrive. You see which classes change, where conditionals pile up, and what becomes hard. |
| 4 | **Diagnose the problem** | You explain why the design is hard to maintain and what should be separated. |
| 5 | **Discover the design** | You reason toward a better structure. Only then does it get its pattern name. |
| 6 | **Refactor and compare** | You rebuild it and compare with the naive version, including trade-offs. |
| 7 | **Consolidate understanding** | You explain the reasoning in your own words and write it down. |

Two rules matter most:

- **You write the code.** The tutor guides with questions and hints. It does not hand over solutions.
- **A pattern must earn its place.** If plain code is enough, the tutor says so.

---

## What I covered

All scenarios build on one growing order checkout system.

| # | Pattern | Scenario | Problem it solves |
| - | ------- | -------- | ----------------- |
| 1 | Strategy | Multiple payment methods | Interchangeable behavior tangled in conditionals |
| 2 | Factory Method | Creating the right payment strategy | Creation decisions coupled to client code |
| 3 | Singleton | Shared gateway configuration | Controlling instances and shared access |
| 4 | Observer | Notify email, inventory, shipping on order | Publisher coupled to its recipients |
| 5 | Decorator | Gift wrap, insurance, express shipping | Subclass explosion from optional features |
| 6 | Template Method | Checkout workflows with shared steps | Duplicated algorithm structure |
| 7 | Command | Place, cancel, retry, undo operations | Requests coupled to their execution |
| 8 | State | Order lifecycle and allowed transitions | State logic scattered across conditionals |
| 9 | Facade | Coordinate payment, inventory, shipping, tax | Clients exposed to subsystem complexity |
| 10 | Builder | Orders with many optional fields | Telescoping constructors |

---

## Repository layout

```
designpattern/
├── TUTOR_METHOD.md      # how the tutor teaches (give this to your LLM)
├── CURRICULUM.md        # what to learn, in order, with progress status
└── <pattern>/
    ├── naive/           # first implementation, before the pattern
    ├── pattern/         # refactored implementation
    └── progress.md      # the full story: scenario, pain, diagnosis, solution
```

The code shows the final result. Each `progress.md` shows how I got there, which is the part that actually teaches.

To see how a design evolved, read `naive/` first, then `pattern/`, then `progress.md`.

---

## Learn this way yourself

You do not need to write a long prompt.

1. Start a new chat with an LLM that can read attachments.
2. Attach `TUTOR_METHOD.md` and `CURRICULUM.md`.
3. Send them. Add no other instructions.

The tutor reads both files, starts at the first unfinished pattern, and gives you your first scenario. From there, build in Java, share your code, ask questions, and answer its questions.

Tips:

- Keep each pattern in one conversation when you can. If you start a new chat, attach both files again plus your latest `progress.md`.
- Save the `progress.md` the tutor produces after each pattern, and update the status in `CURRICULUM.md`.
- If the tutor gives you a full solution too early or names the pattern first, tell it to follow `TUTOR_METHOD.md`.
- Prefer another language? Change the language line in `CURRICULUM.md`.

---

## Quick revision

After finishing a pattern, test yourself with the questions in `CURRICULUM.md`, for example: *Can I add responsibilities by wrapping an object instead of creating a subclass for every combination?* Explain the problem, the solution, the trade-offs, and when you would not use it.

---

## Guiding principle

Don't just memorize what a design pattern does. Understand the problem that makes it useful, the structure that solves it, and the trade-offs that come with it.
