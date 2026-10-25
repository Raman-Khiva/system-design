<div align="center">

# Design Patterns: Learn by Doing

**Hit the problem first. Then discover the pattern that solves it.**

![Java](https://img.shields.io/badge/Java-ED8B00?logo=openjdk&logoColor=white)
![Patterns](https://img.shields.io/badge/patterns-10%2F10-2ea44f)
![Method](https://img.shields.io/badge/method-learn%20by%20doing-0969da)

[Why](#-why-this-exists) · [Approach](#%EF%B8%8F-how-it-works-the-approach) · [Example](#-see-it-in-code) · [Patterns](#-what-i-covered) · [How to use](#-how-to-use-this)

</div>

This folder holds my work on 10 design patterns in Java, the reasoning behind each one, and two files that let you learn the same way with any LLM.

<br>

| 💡 **Want to start right now?** |
| :-: |
| Attach two files to any LLM and it becomes your design patterns tutor. No prompt needed.<br><br>**[👉 Go to: How to use this](#-how-to-use-this)** |

<br>

## 📖 Why this exists

Most people learn design patterns by memorizing them. That gives you pattern names, but not the judgment to know when to use one, or when not to.

| | Typical way | This way |
| --- | --- | --- |
| **Start** | Read the definition | Get a real problem |
| **Middle** | Memorize the UML, see an example | Build a simple solution, extend it until it hurts |
| **End** | Hope to recognize it later | Find the cause, discover the design, then learn the name |
| **Result** | ❌ You know the name | ✅ You know why it exists |

By the time a pattern got its name, I already understood why it was needed. It felt like the answer to a problem I had lived through.

> [!NOTE]
> **Who this is for:** anyone comfortable with basic Java (classes, interfaces, inheritance) who wants design judgment, not just pattern names.

## ⚙️ How it works: the approach

**In one line:** Scenario → Build naive → Feel the pain → Diagnose → Discover the design → Refactor → Consolidate

```mermaid
flowchart LR
    A([Scenario]) --> B([Build naive]) --> C([Feel the pain])
    C --> D([Diagnose]) --> E([Discover the design])
    E --> F([Refactor]) --> G([Consolidate])

    classDef problem fill:#ffebe9,stroke:#cf222e,color:#1f2328
    classDef insight fill:#ddf4ff,stroke:#0969da,color:#1f2328
    classDef solution fill:#dafbe1,stroke:#1a7f37,color:#1f2328
    class A,B,C problem
    class D,E insight
    class F,G solution
```

🔴 **Live the problem** (1–3) · 🔵 **Find the design** (4–5) · 🟢 **Lock it in** (6–7)

| Step | Stage | What happens |
| :-: | --- | --- |
| 1 | **Understand the scenario** | You get a concrete problem with clear requirements. The pattern is not named. |
| 2 | **Build the naive solution** | You write the simplest reasonable solution in Java, with no patterns. This is your working baseline. |
| 3 | **Experience the pain** | New requirements arrive. You see which classes change, where conditionals pile up, and what becomes hard. |
| 4 | **Diagnose the problem** | You explain why the design is hard to maintain and what should be separated. |
| 5 | **Discover the design** | You reason toward a better structure. Only then does it get its pattern name. |
| 6 | **Refactor and compare** | You rebuild it and compare with the naive version, including trade-offs. |
| 7 | **Consolidate understanding** | You explain the reasoning in your own words and write it down. |

### Your role and the tutor's role

| You | The tutor (LLM) |
| --- | --- |
| Write the code | Presents the scenario without naming the pattern |
| Extend it and notice what hurts | Reviews your actual code and asks guiding questions |
| Diagnose the cause and propose fixes | Gives hints in small steps, never the full answer |
| Explain the result in your own words | Names the pattern only after you have found the design |

> [!IMPORTANT]
> **A pattern must earn its place.** If plain code is enough, the tutor says so.

## 🔍 See it in code

A small taste of the method, using the first pattern. The exact examples live in each pattern's folder.

<details>
<summary><b>Step 2: the naive solution works</b></summary>

```java
double checkout(String method, double amount) {
    if (method.equals("card")) {
        return chargeCard(amount);
    } else if (method.equals("upi")) {
        return chargeUpi(amount);
    } else if (method.equals("wallet")) {
        return chargeWallet(amount);
    }
    throw new IllegalArgumentException("Unknown method: " + method);
}
```

It is correct, simple, and passes every test.
</details>

<details>
<summary><b>Step 3: the pain appears</b></summary>

A new requirement arrives: *add net banking, and give wallet payments a discount.*

- You must edit `checkout`, which already works, and retest every branch.
- The method now knows about every payment type.
- Every new method makes the `if` chain longer.

The question to ask: *what changes often, and what should stay stable?*
</details>

<details>
<summary><b>Step 5 and 6: the design emerges</b></summary>

```java
interface PaymentStrategy {
    double pay(double amount);
}

class CardPayment implements PaymentStrategy { /* ... */ }
class UpiPayment  implements PaymentStrategy { /* ... */ }

double checkout(PaymentStrategy strategy, double amount) {
    return strategy.pay(amount);
}
```

Adding a payment method is now a new class. `checkout` never changes. This is the **Strategy** pattern, and you arrived at it by fixing a problem you felt.
</details>

## 🧩 What I covered

All scenarios build on one growing order checkout system.

| # | Pattern | Scenario | Problem it solves |
| :-: | --- | --- | --- |
| 1 | [**Strategy**](strategy/) | Multiple payment methods | Interchangeable behavior tangled in conditionals |
| 2 | [**Factory Method**](factory-method/) | Creating the right payment strategy | Creation decisions coupled to client code |
| 3 | [**Singleton**](singleton/) | Shared gateway configuration | Controlling instances and shared access |
| 4 | [**Observer**](observer/) | Notify email, inventory, shipping on order | Publisher coupled to its recipients |
| 5 | [**Decorator**](decorator/) | Gift wrap, insurance, express shipping | Subclass explosion from optional features |
| 6 | [**Template Method**](template-method/) | Checkout workflows with shared steps | Duplicated algorithm structure |
| 7 | [**Command**](command/) | Place, cancel, retry, undo operations | Requests coupled to their execution |
| 8 | [**State**](state/) | Order lifecycle and allowed transitions | State logic scattered across conditionals |
| 9 | [**Facade**](facade/) | Coordinate payment, inventory, shipping, tax | Clients exposed to subsystem complexity |
| 10 | [**Builder**](builder/) | Orders with many optional fields | Telescoping constructors |

## 📁 Repository layout

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

> [!TIP]
> To see how a design evolved, read `naive/` first, then `pattern/`, then `progress.md`.

## 🚀 How to use this

You do not need to write a long prompt.

1. Start a new chat with an LLM that can read attachments.
2. Attach [`TUTOR_METHOD.md`](TUTOR_METHOD.md) and [`CURRICULUM.md`](CURRICULUM.md).
3. Send them with no other instructions.

The tutor reads both files, starts at the first unfinished pattern, and gives you your first scenario. From there, build in Java, share your code, ask questions, and answer its questions.

> [!NOTE]
> - Keep each pattern in one conversation when you can. In a new chat, attach both files again plus your latest `progress.md`.
> - Save the `progress.md` the tutor produces after each pattern, and update the status in `CURRICULUM.md`.
> - If the tutor gives a full solution too early or names the pattern first, tell it to follow `TUTOR_METHOD.md`.
> - Prefer another language? Change the language line in `CURRICULUM.md`.

## 🔁 Quick revision

After finishing a pattern, test yourself with the questions in [`CURRICULUM.md`](CURRICULUM.md), for example: *Can I add responsibilities by wrapping an object instead of creating a subclass for every combination?* Explain the problem, the solution, the trade-offs, and when you would not use it.

---

<div align="center">

**Don't memorize what a pattern does. Understand the problem that makes it useful, the structure that solves it, and the trade-offs that come with it.**

</div>
