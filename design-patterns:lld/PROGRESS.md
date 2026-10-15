# Progress Tracker

**Language:** Java  
**Method:** Build naive → feel pain → pattern emerges → refactor → repeat

---

## In Progress: Strategy Pattern (Assignment #1 — Refactor)

### What you built (naive)
`strategy:behavioral/Order.java` — an `Order` class with a `switch` on `paymentType` (`credit_card`, `paypal`, `crypto`) inside `checkout()`.

### Pain identified
1. **Open/Closed Principle violation:** Adding a 4th payment method forces editing `Order.java`.
2. **Single Responsibility violation:** `Order` knows internals of every payment method.
3. **Fragile:** One giant `switch` holds all payment logic.

### Pattern born from the pain
**Strategy (Behavioral):** Extract each payment algorithm into its own class behind a common interface. `Order` delegates to a `PaymentStrategy` instead of deciding which payment to run.

### Your current task
Refactor the naive code into Strategy:
- `PaymentStrategy` interface → `pay(double amount)`
- `CreditCardPayment`, `PayPalPayment`, `CryptoPayment` implement it
- `Order` holds a `PaymentStrategy` reference, no `switch`
- Client injects the strategy into `Order`

**Not marked complete until you paste the refactored code and I review it.**

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

## Next (Pending): Singleton, Observer, Decorator, Template Method, Command, State, Facade, Builder

*(Will fill in as we complete each assignment.)*
