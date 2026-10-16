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

## In Progress: Observer (Assignment #4)

### Current pain
After `Order.checkout()` completes, you need to notify email, inventory, shipping, and analytics services. If `Order` calls them directly:

```java
public void checkout(double amount) {
    paymentStrategy.pay(amount);
    emailService.send(...);
    inventoryService.update(...);
    shippingService.schedule(...);
    analyticsService.track(...);
}
```

**Problems:**
- `Order` knows about every service (tight coupling)
- Adding a new notification (e.g., SMS) requires editing `Order.java`
- Testing `Order` requires mocking all 5 services
- You can't easily enable/disable notifications per environment

### Your task
Implement Observer so `Order` doesn't know who its observers are. Services subscribe to order events and get notified automatically.

**Requirements:**
1. Create an `Observer` interface with an `update(Order order)` method
2. Create concrete observers: `EmailService`, `InventoryService`, `ShippingService`, `AnalyticsService`
3. Refactor `Order` to:
   - Maintain a list of observers (`List<Observer>`)
   - Provide `attach(Observer)` and `detach(Observer)` methods
   - Call `notifyObservers()` after checkout completes
4. `Main` should attach observers to the order, then call `checkout()`

**Question to keep in mind:**
> If you add a 6th service (e.g., SMS notification), do you still have to touch `Order.java`? Or just create a new observer class and attach it?

---

## Pending: Decorator, Template Method, Command, State, Facade, Builder

*(Will fill in as we complete each assignment.)*
