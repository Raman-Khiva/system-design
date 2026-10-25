# CURRICULUM — Design Patterns Roadmap & Progress

> **To the AI tutor:** This file defines *what* to teach and *in what order*. *How* to teach is in `TUTOR_METHOD.md`. Work through the table top to bottom and start at the first pattern not marked ✅. The "Practical Scenario" column is for your assignment design; do not reveal the pattern name or "Core Design Problem" to the learner before Phase 5.

**Language:** Java
**Domain thread:** An order checkout system that grows across assignments, so later patterns can build on earlier ones.

---

## 1. Roadmap & Status

Status values: ⬜ Not started · 🔄 In progress · ✅ Completed

| #  | Pattern             | Practical Scenario                                                        | Core Design Problem Addressed                                     | Status |
| -- | ------------------- | ------------------------------------------------------------------------- | ----------------------------------------------------------------- | ------ |
| 1  | **Strategy**        | Multiple payment methods in an order checkout system                      | Interchangeable behaviors entangled with conditional logic        | ⬜     |
| 2  | **Factory Method**  | Creating the appropriate payment strategy                                 | Object creation decisions coupled to client code                  | ⬜     |
| 3  | **Singleton**       | Shared payment gateway configuration or order processor                   | Controlling instance creation and providing a shared access point | ⬜     |
| 4  | **Observer**        | Notifying email, inventory, and shipping services when an order is placed | Publishers tightly coupled to their notification recipients       | ⬜     |
| 5  | **Decorator**       | Adding gift wrap, insurance, and express shipping dynamically             | Subclass proliferation when combining optional responsibilities   | ⬜     |
| 6  | **Template Method** | Checkout workflows with common steps and varying implementations          | Duplicated algorithm structure across related workflows           | ⬜     |
| 7  | **Command**         | Placing, cancelling, retrying, and undoing operations                     | Requests tightly coupled to their execution and invocation        | ⬜     |
| 8  | **State**           | Managing an order's lifecycle and permitted transitions                   | State-dependent behavior scattered across conditional logic       | ⬜     |
| 9  | **Facade**          | Coordinating payment, inventory, shipping, and tax services               | Client code exposed to the complexity of multiple subsystems      | ⬜     |
| 10 | **Builder**         | Constructing orders with multiple optional fields                         | Telescoping constructors and unclear object-construction calls    | ⬜     |

When all rows are ✅, tell the learner the roadmap is finished and offer to add further patterns as new rows using the same columns.

---

## 2. Quick Revision

Use after a pattern is completed, or when the learner asks to revise. Ask the question and have the learner explain the problem, the solution, the trade-offs, and when *not* to use it. These prompts are for recall, not a substitute for understanding the underlying problem.

| Pattern             | Remember It By Asking                                                                                                |
| ------------------- | -------------------------------------------------------------------------------------------------------------------- |
| **Strategy**        | Can I isolate a behavior that needs interchangeable implementations?                                                 |
| **Factory Method**  | Can I delegate object-creation decisions instead of coupling clients to concrete classes?                            |
| **Singleton**       | Does this design genuinely require a single shared instance with controlled access?                                  |
| **Observer**        | How can interested components react to an event without the publisher knowing every recipient?                       |
| **Decorator**       | Can I add responsibilities dynamically by wrapping an object rather than creating subclasses for every combination?  |
| **Template Method** | Can I preserve a common algorithm structure while allowing selected steps to vary?                                   |
| **Command**         | Can I represent an operation as an object so it can be passed, queued, logged, retried, or undone where appropriate? |
| **State**           | Can I organize behavior around the current state instead of scattering state-dependent conditionals?                 |
| **Facade**          | Can I give clients a simpler interface to a complex subsystem?                                                       |
| **Builder**         | Can I make the construction of a complex object clearer and separate it from the resulting object?                   |

---

## 3. Updating This File

- Set a pattern to 🔄 when its assignment starts and ✅ only after Phase 7 (the learner has explained the design in their own words).
- Each pattern's full reasoning lives in its `progress.md`. This file only tracks status.
- Never mark a pattern complete just because source files exist; confirm with the learner.

---

## Guiding Principle

Don't just memorize what a design pattern does. Understand the design problem that makes it useful, the structure that solves it, and the trade-offs that come with it.
# CURRICULUM — Design Patterns Roadmap & Progress

> **To the AI tutor:** This file defines *what* to teach and *in what order*. *How* to teach is in `TUTOR_METHOD.md`. Work through the table top to bottom and start at the first pattern not marked ✅. The "Practical Scenario" column is for your assignment design; do not reveal the pattern name or "Core Design Problem" to the learner before Phase 5.

**Language:** Java
**Domain thread:** An order checkout system that grows across assignments, so later patterns can build on earlier ones.

---

## 1. Roadmap & Status

Status values: ⬜ Not started · 🔄 In progress · ✅ Completed

| #  | Pattern             | Practical Scenario                                                        | Core Design Problem Addressed                                     | Status |
| -- | ------------------- | ------------------------------------------------------------------------- | ----------------------------------------------------------------- | ------ |
| 1  | **Strategy**        | Multiple payment methods in an order checkout system                      | Interchangeable behaviors entangled with conditional logic        | ⬜     |
| 2  | **Factory Method**  | Creating the appropriate payment strategy                                 | Object creation decisions coupled to client code                  | ⬜     |
| 3  | **Singleton**       | Shared payment gateway configuration or order processor                   | Controlling instance creation and providing a shared access point | ⬜     |
| 4  | **Observer**        | Notifying email, inventory, and shipping services when an order is placed | Publishers tightly coupled to their notification recipients       | ⬜     |
| 5  | **Decorator**       | Adding gift wrap, insurance, and express shipping dynamically             | Subclass proliferation when combining optional responsibilities   | ⬜     |
| 6  | **Template Method** | Checkout workflows with common steps and varying implementations          | Duplicated algorithm structure across related workflows           | ⬜     |
| 7  | **Command**         | Placing, cancelling, retrying, and undoing operations                     | Requests tightly coupled to their execution and invocation        | ⬜     |
| 8  | **State**           | Managing an order's lifecycle and permitted transitions                   | State-dependent behavior scattered across conditional logic       | ⬜     |
| 9  | **Facade**          | Coordinating payment, inventory, shipping, and tax services               | Client code exposed to the complexity of multiple subsystems      | ⬜     |
| 10 | **Builder**         | Constructing orders with multiple optional fields                         | Telescoping constructors and unclear object-construction calls    | ⬜     |

When all rows are ✅, tell the learner the roadmap is finished and offer to add further patterns as new rows using the same columns.

---

## 2. Quick Revision

Use after a pattern is completed, or when the learner asks to revise. Ask the question and have the learner explain the problem, the solution, the trade-offs, and when *not* to use it. These prompts are for recall, not a substitute for understanding the underlying problem.

| Pattern             | Remember It By Asking                                                                                                |
| ------------------- | -------------------------------------------------------------------------------------------------------------------- |
| **Strategy**        | Can I isolate a behavior that needs interchangeable implementations?                                                 |
| **Factory Method**  | Can I delegate object-creation decisions instead of coupling clients to concrete classes?                            |
| **Singleton**       | Does this design genuinely require a single shared instance with controlled access?                                  |
| **Observer**        | How can interested components react to an event without the publisher knowing every recipient?                       |
| **Decorator**       | Can I add responsibilities dynamically by wrapping an object rather than creating subclasses for every combination?  |
| **Template Method** | Can I preserve a common algorithm structure while allowing selected steps to vary?                                   |
| **Command**         | Can I represent an operation as an object so it can be passed, queued, logged, retried, or undone where appropriate? |
| **State**           | Can I organize behavior around the current state instead of scattering state-dependent conditionals?                 |
| **Facade**          | Can I give clients a simpler interface to a complex subsystem?                                                       |
| **Builder**         | Can I make the construction of a complex object clearer and separate it from the resulting object?                   |

---

## 3. Updating This File

- Set a pattern to 🔄 when its assignment starts and ✅ only after Phase 7 (the learner has explained the design in their own words).
- Each pattern's full reasoning lives in its `progress.md`. This file only tracks status.
- Never mark a pattern complete just because source files exist; confirm with the learner.

---

## Guiding Principle

Don't just memorize what a design pattern does. Understand the design problem that makes it useful, the structure that solves it, and the trade-offs that come with it.
