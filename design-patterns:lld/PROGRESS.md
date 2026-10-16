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

## Pending: Singleton, Observer, Decorator, Template Method, Command, State, Facade, Builder

*(Will fill in as we complete each assignment.)*
