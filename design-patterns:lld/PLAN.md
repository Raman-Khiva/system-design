# Design Patterns — Learning Plan & Tracker

**Method:** Build naive → feel the pain → pattern emerges → refactor → repeat  
**Language:** Java  
**Purpose:** Roadmap + progress tracking

---

## Method — Step by Step

| # | Step | What You Do | What Happens |
|---|------|-------------|--------------|
| 1 | **Scenario / Case** | Read the assignment brief — a concrete problem to solve. | You get a clear goal: payment methods, notifications, order creation, etc. |
| 2 | **Build naive** | Write code that just works — `switch`, `new`, `if/else`, telescoping constructors. No patterns. | You have working code, but it's fragile and tightly coupled. |
| 3 | **Feel the pain** | Try to extend it — add a new payment method, a new service, a new optional field. | You notice: editing existing code, tight coupling, code duplication, unreadable call sites. |
| 4 | **See the pattern emerge** | Look at the pain and ask: "What cures *this specific* problem?" | The pattern is the cure. It's not arbitrary — it's the natural solution to the pain you just felt. |
| 5 | **Refactor** | Apply the pattern. Extract interfaces, introduce indirection, separate concerns. | Code becomes extensible, readable, and maintainable. Trade-offs are discussed. |
| 6 | **Repeat** | Next assignment builds on the previous insight. | Each pattern compounds your understanding. |

**Key principle:** Patterns are not memorized — they are *discovered* from pain. If you feel the pain first, the pattern makes intuitive sense.

---

## How to use this file

1. Read the current assignment scenario.
2. Write the naive Java code (no patterns yet).
3. Paste it here for review → I'll point out the pain and introduce the pattern.
4. Refactor together, mark the pattern ✅ Done, move to the next.
5. Use the **Quick Revision** table before interviews or when you forget a pattern's purpose.

---

## Learning Order & Assignments

| # | Pattern | Assignment Scenario | Simple Definition | Why / Pain it Cures | Status |
|---|---------|---------------------|-------------------|---------------------|--------|
| 1 | **Strategy** | `Order.checkout()` supports Credit Card, PayPal, Crypto | Encapsulate each payment algorithm in its own class and swap them at runtime | `if/else` or `switch` on payment type inside `Order`; adding a 4th method forces editing `Order` | ✅ Done |
| 2 | **Factory Method** | Create the right payment strategy object without exposing `new` calls | Define an interface for creating an object, but let subclasses/factories decide which class to instantiate | `new CreditCardPayment()`, `new PayPalPayment()` scattered across the codebase; hard to add/rename types | ✅ Done |
| 3 | **Singleton** | Shared payment gateway config / order processor | Ensure only one instance of a class exists and provide global access | Multiple instances of config/connection causing inconsistent state or wasted resources | ✅ Done |
| 4 | **Observer** | Notify email, inventory, shipping when an order is placed | Let objects subscribe to events and get notified automatically without the publisher knowing who they are | `Order` directly calling 5 different services; tight coupling, hard to add a new notification target | ✅ Done |
| 5 | **Decorator** | Add gift wrap, insurance, express shipping to an order | Dynamically attach responsibilities to an object by wrapping it | Subclass explosion: `OrderWithGiftWrap`, `OrderWithInsurance`, `OrderWithBoth`, etc. | ⬜ Next |
| 6 | **Template Method** | Checkout flow: validate → tax → payment → confirmation | Define the skeleton of an algorithm in a base class, let subclasses override specific steps | Duplicated checkout skeleton across different order types; steps hard to reuse | ✅ Done |
| 7 | **Command** | Place, cancel, retry orders with undo support | Turn a request into an object so you can parameterize, queue, log, or undo operations | Business logic tangled with invocation/request handling; no way to undo or retry cleanly | ✅ Done |
| 8 | **State** | Order lifecycle: `Pending → Paid → Shipped → Delivered → Cancelled` | Let an object change behavior when its internal state changes | Huge `if/else` on `order.status` inside every method; adding a new status breaks everything | ✅ Done |
| 9 | **Facade** | Simplify a complex checkout subsystem (payment + inventory + shipping + tax) | Provide a unified, simple interface to a complex set of classes/subsystems | Client code talking to 10 different services directly; changes in one service ripple to clients | ⏭️ Skipped |
| 10 | **Builder** | Create complex orders with many optional fields | Construct a complex object step by step, separating construction from representation | Telescoping constructors / huge parameter lists; unclear what each argument means | ✅ Done |

---

## Overall Progress

| Status | Count | Patterns |
|--------|-------|----------|
| ✅ Done | 8 | Strategy, Factory, Singleton, Observer, Template Method, Command, State, Builder |
| ⏭️ Skipped | 1 | Facade |
| ⬜ Pending | 1 | Decorator |

---

## Next Immediate Step

**Assignment #5 — Decorator Pattern:**

Pain: You need to add gift wrap, insurance, express shipping to an order. Subclassing creates `OrderWithGiftWrap`, `OrderWithInsurance`, `OrderWithBoth`, etc. — a subclass explosion.

Task: Use Decorator to wrap an `Order` with additional responsibilities dynamically, instead of creating a subclass for every combination.

Tell me when you're ready, and I'll give the full scenario.
