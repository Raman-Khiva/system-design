# Progress Tracker

**Language:** Java  
**Method:** Build naive → feel pain → pattern emerges → refactor → repeat

---

## Completed: Strategy Pattern (Assignment #1)

### Location
Detailed chronological progress with code: `strategy:behavioral/PROGRESS.md`

### What you built (naive → refactored)
- **Naive:** `strategy:behavioral/naive/Order.java` — `switch` on payment type inside `checkout()`
- **Refactored:** `strategy:behavioral/pattern/` — `PaymentStrategy` interface + 3 concrete strategies; `Order` delegates, no `switch`

### Pain identified
1. **Open/Closed violation:** Adding a 4th payment method forces editing `Order.java`.
2. **Single Responsibility violation:** `Order` knows internals of every payment method.
3. **Fragile:** One `switch` holds all payment logic.

### Pattern born from pain
**Strategy (Behavioral):** Extract each payment algorithm into its own class behind a common interface. `Order` delegates to a `PaymentStrategy` instead of deciding which payment to run.

### Key result
- `Order` knows nothing about `credit_card`, `paypal`, or `crypto`
- Adding Apple Pay requires only a new class; `Order` stays untouched
- Client (`Main`) chooses and injects the strategy

**Status:** ✅ Complete. Review `strategy:behavioral/PROGRESS.md` for full revision notes.

---

## In Progress: Factory Pattern (Assignment #2)

### Current pain
Even with Strategy, your client code still does:
```java
order.setPaymentStrategy(new CreditCardPayment());
order.setPaymentStrategy(new PayPalPayment());
```
Every time you add/rename a payment class, you must hunt down every `new ...Payment()` across the app and update it.

### Your task
Create a `PaymentFactory` so clients never call `new` on payment classes directly.

**Requirements:**
1. Keep your Strategy classes (`PaymentStrategy`, `CreditCardPayment`, `PayPalPayment`, `CryptoPayment`).
2. Create `PaymentFactory` with a static method like:
   ```java
   public static PaymentStrategy createPayment(String type)
   ```
3. Update your client (`Main`) to use the factory instead of `new`.
4. Support at least `credit_card`, `paypal`, `crypto`.

**Question to keep in mind while coding:**
> If you add Apple Pay, do you still have to touch `Main`? Or only `PaymentFactory`?

---

## Completed: Factory Method (Assignment #2)

### Location
Detailed chronological progress with code: `factory:creational/PROGRESS.md`

### What you built
- **Naive:** `factory:creational/naive/Main.java` — `switch` calling `new CreditCardPayment()` directly
- **Pattern:** `factory:creational/pattern/` — `PaymentFactory` interface + 3 concrete factories; `Main` calls `factory.createPayment()`, no direct `new` on payment classes

### Pain identified
1. **Client coupled to concrete classes:** `Main` directly instantiates payment classes.
2. **Fragile on change:** Adding Apple Pay requires editing every client's `new` calls.
3. **Constructor changes break clients:** Every `new` call site must be updated.

### Pattern born from pain
**Factory Method (Creational):** Define an interface for creating an object, but let factories decide which class to instantiate. Clients depend on the factory interface, not concrete classes.

### Key result
- `new CreditCardPayment()` etc. moved from `Main` into factory classes
- `Main` depends on `PaymentFactory` abstraction, not concrete payment classes
- Adding a new payment type requires only a new factory class

**Status:** ✅ Complete. Review `factory:creational/PROGRESS.md` for full revision notes.

---

## Completed: Singleton (Assignment #3)

### Location
Detailed chronological progress with code: `singleton:creational/PROGRESS.md`

### What you built
- **Naive:** `singleton:creational/naive/PaymentGatewayConfig.java` + `Main.java` — public constructor, two instances with inconsistent `isSandboxMode` state
- **Pattern:** `singleton:creational/pattern/` — private constructor, private static `instance`, static `getInstance()` with lazy initialization; `Main` proves `config1` and `config2` are the same object

### Pain identified
1. **Uncontrolled instantiation:** Anyone can call `new PaymentGatewayConfig()` and create independent copies.
2. **Inconsistent state:** `config1` has `isSandboxMode = true`, `config2` has `false`. Different objects, diverging state.
3. **No single source of truth:** Changing one instance doesn't affect others; easy to miss updates.
4. **Memory waste:** Duplicate config objects waste resources.

### Pattern born from pain
**Singleton (Creational):** Ensure a class has only one instance and provide a global point of access to it.

### Key result
- `getInstance()` always returns the same object
- State changes are visible across the whole app
- `config1 == config2` → `true`

**Status:** ✅ Complete. Review `singleton:creational/PROGRESS.md` for full revision notes.

---

## Completed: Observer (Assignment #4)

### Location
Detailed chronological progress with code: `observer:behavioral/PROGRESS.md`

### What you built
- **Naive:** `observer:behavioral/naive/Order.java` — directly instantiates `EmailService`, `InventoryService`, `ShippingService` and calls `send()`, `update()`, `schedule()` directly
- **Pattern:** `observer:behavioral/pattern/` — `Observer` interface + 3 concrete observers; `Order` holds `List<Observer>` with `attach()`/`detach()`/`notifyObservers()`

### Pain identified
1. **Tight coupling to concrete classes:** `Order` knows service classes by name and method signatures.
2. **Order creates its own dependencies:** Constructor uses `new`, preventing mock injection.
3. **Fragile on change:** Adding SMS requires editing `Order.java` (field + init + method call).
4. **No selective notification:** All services fire unconditionally.
5. **Inconsistent method names:** `send()`, `update()`, `schedule()` — `Order` must remember each.

### Pattern born from pain
**Observer (Behavioral):** Define a one-to-many dependency so that when one object changes state, all dependents are notified automatically. Subject knows only the interface, not concrete observers.

### Key result
- `Order` maintains `List<Observer>` — knows nothing about Email/Inventory/Shipping
- `attach()` / `detach()` for runtime subscription
- `notifyObservers()` loops and calls `update()`
- Adding SMSService requires zero changes to `Order.java`

**Status:** ✅ Complete. Review `observer:behavioral/PROGRESS.md` for full revision notes.

---

## Skipped: Decorator (Assignment #5)

**Status:** ⏭️ Skipped by mutual agreement — not a prerequisite for Template Method.

---

## Completed: Template Method (Assignment #6)

### Location
Naive code: `template:behavioral/naive/`

### What you built (naive)
- **`Order.java`** — interface with only `process()`
- **`StandardOrder.java`** — implements `process()` with 4 hardcoded steps (validate → tax → notify → inventory)
- **`ExpressOrder.java`** — implements `process()` with same 4-step sequence, different step implementations
- **`Main.java`** — selects order type, calls `process()`

### Pain identified (Tutor Review)

| # | Pain | Explanation |
|---|------|-------------|
| 1 | **Duplicated algorithm skeleton** | Both `StandardOrder` and `ExpressOrder` contain the identical 4-step sequence. Adding a 5th step requires editing every order class. |
| 2 | **Hard to reorder steps** | If business changes step order (e.g., notify before tax), every order class must be edited. |
| 3 | **No way to override just one step** | Each class implements the entire `process()` from scratch. If only tax differs, you still rewrite validation, notification, inventory. |
| 4. | **Interface is too thin** | `Order` exposes only `process()`, no contract declaring the individual steps that all orders must follow. |

**The core problem:** Multiple classes share the same algorithm structure but individual steps vary. The skeleton is copied, not centralized.

### Pattern born from pain
**Template Method (Behavioral):** Define the skeleton of an algorithm in a base class method, deferring some steps to subclasses. Subclasses redefine certain steps without changing the algorithm's structure.

### Your Next Task (Part 2 — Pending)

**Folder:** `template:behavioral/pattern/`

### Create:

1. **`Order.java`** — abstract class with:
   - `final void process()` containing the step skeleton
   - Abstract methods for steps that vary
   - Optional hook methods with default implementations

2. **`StandardOrder.java`** — extends `Order`, overrides only steps that differ
3. **`ExpressOrder.java`** — extends `Order`, overrides only steps that differ
4. **`Main.java`** — same driver, order types share the skeleton

### Key question:
> If you add a 5th step (e.g., `sendInvoice()`), do you edit one class (the abstract base) or every order subclass?

---

## Completed: Command (Assignment #7)

### Location
Detailed chronological progress with code: `command:behavioral/PROGRESS.md`

### What you built
- **Naive:** `command:behavioral/naive/OrderProcessor.java` — `placeOrder()` and `cancelOrder()` with business logic inline; `Main.java` calls methods directly
- **Pattern:** `command:behavioral/pattern/` — `Command` interface (`execute()`/`undo()`), `Order` receiver, `PlaceOrderCommand`, `CancelOrderCommand`, `CommandInvoker` (history stack), `Main` demonstrates execute + undo

### Pain identified
1. **Business logic tangled with invocation:** Caller knows internal steps of `placeOrder()`.
2. **No undo capability:** Once executed, no true reversal.
3. **No retry or queue support:** Can't queue commands or retry failed steps.
4. **Fragile on change:** New action requires editing both `OrderProcessor` and `Main`.
5. **No logging or audit trail:** Actions execute immediately, not captured as objects.

### Pattern born from pain
**Command (Behavioral):** Encapsulate a request as an object, letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

### Key result
- `CommandInvoker` holds a history stack and calls `execute()`/`undo()` without knowing command internals
- `Order` object created once outside loop — undo operates on the same order
- Undo demonstrated via menu option (`u`)

**Status:** ✅ Complete. Review `command:behavioral/PROGRESS.md` for full revision notes.

---

## Completed: State (Assignment #8)

### Location
Detailed chronological progress with code: `state:behavioral/PROGRESS.md`

### What you built
- **Naive:** `state:behavioral/naive/Order.java` — `if/else` on `Status` enum inside every method
- **Pattern:** `state:behavioral/pattern/` — `State` interface + 5 concrete states + `Order` context that delegates

### Pain identified
1. **`if/else` on status inside every method** — logic duplicated across all methods
2. **Adding a new status requires editing every method**
3. **Adding a new action requires writing status checks for all statuses**
4. **`Order` knows about every status and transition** — does too much
5. **Invalid transitions just print messages** — no clean error handling

### Pattern born from pain
**State (Behavioral):** Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.

### Key result
- No `if/else` on status anywhere in `Order` or state classes
- Each state encapsulates its own behavior and transitions
- Adding `REFUNDED` = one new class, no existing class changes
- Terminal states (`DeliveredState`, `CanceledState`) correctly reject all actions

**Status:** ✅ Complete. Review `state:behavioral/PROGRESS.md` for full revision notes.

---

## In Progress: Facade (Assignment #9)

### Current pain
After checkout completes, multiple subsystems need to be coordinated:

- PaymentService — process payment
- InventoryService — deduct stock
- ShippingService — schedule delivery
- TaxService — calculate tax
- EmailService — send confirmation
- AnalyticsService — track sale

If `Main` (or a controller) calls all these directly:

```java
paymentService.process(payment);
inventoryService.deduct(items);
shippingService.schedule(address);
taxService.calculate(order);
emailService.send(customer);
analyticsService.track(order);
```

**Problems:**
- Client knows about every subsystem (tight coupling)
- Adding/removing a subsystem requires editing every client
- Client must know the correct order of operations
- Testing requires mocking all 6 services
- Changes in one subsystem ripple to all clients

### Your task
Implement Facade so clients talk to one simple interface:

1. Create subsystem classes (already have some from Observer: `EmailService`, `InventoryService`, `ShippingService`)
2. Create `CheckoutFacade` with a simple method like `completeOrder(order, amount)` that coordinates all subsystems internally
3. `Main` should call only `facade.completeOrder(...)`, not individual services

**Question to keep in mind:**
> Can you add a new subsystem (e.g., `NotificationService`) without changing `Main`?

---

## Pending: Builder

*(Will fill in as we complete each assignment.)*
