# Strategy Pattern — Progress

## Formal Definition

**Strategy** is a behavioral design pattern that defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

- **Intent:** Encapsulate interchangeable behaviors and use delegation to decide which one to run.
- **Also known as:** Policy
- **Applicability:**
  - Many related classes differ only in their behavior.
  - You need different variants of an algorithm.
  - An algorithm uses data that clients shouldn't know about.
- **Consequences:**
  - Families of related algorithms are defined as separate classes.
  - Eliminates conditional statements for selecting behaviors.
  - Strategies provide different implementations of the same behavior.
  - Clients must be aware of different strategies.

---

## Completed: Strategy Pattern (Assignment #1)

### Original naive code (`Order.java`)
```java
public class Order {
    static void checkout(String paymentType, double amount) {
        switch (paymentType) {
            case "credit_card":
                System.out.println("Processing $" + amount + " via Credit Card");
                break;
            case "paypal":
                System.out.println("Processing $" + amount + " via Paypal");
                break;
            case "crypto":
                System.out.println("Processing $" + amount + " via crypto");
                break;
            default:
                System.out.println("Payment failed! No valid payment method selected.");
        }
    }
}
```

### Pain identified
1. **Open/Closed violation:** Adding Apple Pay/UPI requires editing `Order.java`.
2. **Single Responsibility violation:** `Order` knows internals of every payment method.
3. **Fragile:** One `switch` holds all payment logic; a typo breaks everything.

### Pattern born from pain
**Strategy (Behavioral):** Extract each algorithm into its own class behind a common interface. The context (`Order`) delegates instead of deciding.

### Refactored structure
- `PaymentStrategy` interface → `pay(double amount)`
- `CreditCardPayment`, `PayPalPayment`, `CryptoPayment` implement it
- `Order` holds a `PaymentStrategy` reference, calls `pay()` — no `switch`
- Client (`Main`) chooses and injects the strategy

---

## Progress Log

| Date | Pattern | Assignment | Notes |
|------|---------|------------|-------|
| — | Strategy | Payment methods in `Order` | ✅ Naive written → pain identified → Strategy refactor understood |
