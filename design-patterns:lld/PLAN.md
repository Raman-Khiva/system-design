# Design Patterns — Learning Plan & Tracker

**Method:** Build naive → feel the pain → pattern emerges → refactor → repeat  
**Language:** Java  
**Purpose:** Revision overview + progress tracking

---

## How to use this file

1. Read the current assignment scenario.
2. Write the naive Java code (no patterns yet).
3. Paste it here for review → I’ll point out the pain and introduce the pattern.
4. Refactor together, mark the pattern ✅ Done, move to the next.
5. Use the **Quick Revision** table before interviews or when you forget a pattern’s purpose.

---

## Learning Order & Assignments

| # | Pattern | Assignment Scenario | Simple Definition | Why / Pain it Cures | Status |
|---|---------|---------------------|-------------------|---------------------|--------|
| 1 | **Strategy** | `Order.checkout()` supports Credit Card, PayPal, Crypto | Encapsulate each payment algorithm in its own class and swap them at runtime | `if/else` or `switch` on payment type inside `Order`; adding a 4th method forces editing `Order` | ✅ Done |
| 2 | **Factory** | Create the right payment strategy object without exposing `new` calls | Centralize object creation in one class/method so clients don’t instantiate concrete classes directly | `new CreditCardPayment()`, `new PayPalPayment()` scattered across the codebase; hard to add/rename types | ⬜ Next |
| 3 | **Singleton** | Shared payment gateway config / order processor | Ensure only one instance of a class exists and provide global access | Multiple instances of config/connection causing inconsistent state or wasted resources | ⬜ Pending |
| 4 | **Observer** | Notify email, inventory, shipping when an order is placed | Let objects subscribe to events and get notified automatically without the publisher knowing who they are | `Order` directly calling 5 different services; tight coupling, hard to add a new notification target | ⬜ Pending |
| 5 | **Decorator** | Add gift wrap, insurance, express shipping to an order | Dynamically attach responsibilities to an object by wrapping it | Subclass explosion: `OrderWithGiftWrap`, `OrderWithInsurance`, `OrderWithBoth`, etc. | ⬜ Pending |
| 6 | **Template Method** | Checkout flow: validate → tax → payment → confirmation | Define the skeleton of an algorithm in a base class, let subclasses override specific steps | Duplicated checkout skeleton across different order types; steps hard to reuse | ⬜ Pending |
| 7 | **Command** | Place, cancel, retry orders with undo support | Turn a request into an object so you can parameterize, queue, log, or undo operations | Business logic tangled with invocation/request handling; no way to undo or retry cleanly | ⬜ Pending |
| 8 | **State** | Order lifecycle: `Pending → Paid → Shipped → Delivered → Cancelled` | Let an object change behavior when its internal state changes | Huge `if/else` on `order.status` inside every method; adding a new status breaks everything | ⬜ Pending |
| 9 | **Facade** | Simplify a complex checkout subsystem (payment + inventory + shipping + tax) | Provide a unified, simple interface to a complex set of classes/subsystems | Client code talking to 10 different services directly; changes in one service ripple to clients | ⬜ Pending |
| 10 | **Builder** | Create complex orders with many optional fields | Construct a complex object step by step, separating construction from representation | Telescoping constructors / huge parameter lists; unclear what each argument means | ⬜ Pending |

---

## Next Immediate Step

**Assignment #2 — Factory Pattern:**

Pain: Even with Strategy, `Main` (or other clients) still does `new CreditCardPayment()`, `new PayPalPayment()` directly. If payment class names change or construction logic gets complex, every client breaks.

Task: Create a `PaymentFactory` that returns the right `PaymentStrategy` based on a type string, so clients don't call `new` directly.

Tell me when you're ready, and I'll give the full scenario.
