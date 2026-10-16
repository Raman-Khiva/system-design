# Strategy Pattern — Detailed Progress & Revision Notes

**Directory:** `strategy:behavioral/`  
**Language:** Java  
**Pattern Type:** Behavioral  
**Status:** ✅ Complete

---

## 1. Assignment Brief (The Starting Point)

**Scenario:** An `Order` needs to process payments using different methods: Credit Card, PayPal, Crypto.

**Requirements:**
- Support at least 3 payment methods (`credit_card`, `paypal`, `crypto`)
- Each method prints something like: `Processing $100.0 via Credit Card`
- Callable as: `order.checkout("paypal", 100)`
- No design patterns yet — just make it functional

---

## 2. Naive Implementation (Before Pattern)

### `naive/Order.java`
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

### `naive/Main.java`
```java
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter the amount:");
            int amount = sc.nextInt();
            System.out.println("Please enter the payment method (credit_card, paypal, crypto):");
            String method = sc.next();
            Order.checkout(method, amount);
        }
    }
}
```

**Key characteristic:** `Order` contained a `switch` statement on payment type strings. It knew the internals of every payment method.

---

## 3. Pain Points Identified (Tutor Review)

| # | Pain | Explanation |
|---|------|-------------|
| 1 | **Open/Closed Principle violation** | Adding a 4th payment method (e.g., Apple Pay) requires editing `Order.java`. A class should be open for extension but closed for modification. |
| 2 | **Single Responsibility violation** | `Order` is supposed to manage an order, not know how Credit Card, PayPal, and Crypto each work internally. |
| 3 | **Fragile code** | All payment logic lives in one giant `switch`. One typo can break payment processing for all methods. |

**The core problem:** `Order` decides *which* payment algorithm to run. That decision should not live inside `Order`.

---

## 4. How the Strategy Pattern Was Born

Developers kept facing this exact pain: *"I have an algorithm that varies (payment processing), and I don’t want my main class to know all the variations."*

**The cure:** Extract each variation into its own class, hide them behind a common interface, and let the main class (`Order`) delegate to whichever strategy it is given.

**Pattern:** Strategy (Behavioral)  
**Definition:** Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

---

## 5. Refactored Implementation (After Pattern)

### `pattern/PaymentStrategy.java`
```java
public interface PaymentStrategy {
    void pay(double amount);
}
```

### `pattern/CreditCardPayment.java`
```java
public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Processing $" + amount + " using Credit Card");
    }
}
```

### `pattern/PayPalPayment.java`
```java
public class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Processing $" + amount + " using Paypal");
    }
}
```

### `pattern/CryptoPayment.java`
```java
public class CryptoPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Processing $" + amount + " using Crypto");
    }
}
```

### `pattern/Order.java`
```java
public class Order {
    PaymentStrategy paymentStrategy;

    public Order(PaymentStrategy strategy) {
        paymentStrategy = strategy;
    }

    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }
}
```

### `pattern/Main.java`
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        System.out.print("Choose payment method (credit_card, paypal, crypto): ");
        String method = sc.next();

        PaymentStrategy strategy;
        switch (method) {
            case "credit_card":
                strategy = new CreditCardPayment();
                break;
            case "paypal":
                strategy = new PayPalPayment();
                break;
            case "crypto":
                strategy = new CryptoPayment();
                break;
            default:
                System.out.println("Invalid payment method.");
                sc.close();
                return;
        }

        Order order = new Order(strategy);
        order.checkout(amount);

        sc.close();
    }
}
```

---

## 6. What Changed (Before vs After)

| Aspect | Naive (Before) | Strategy (After) |
|--------|---------------|------------------|
| `Order` knows payment types? | Yes (`switch` on strings) | No (delegates to `PaymentStrategy`) |
| `Order.checkout()` logic | Contains `switch` | One line: `paymentStrategy.pay(amount)` |
| Add 4th payment method | Edit `Order.java` | Create new class; `Order` untouched |
| Payment logic location | Inside `Order` | Inside individual strategy classes |
| Client responsibility | Calls `Order.checkout(type, amount)` | Creates strategy, injects into `Order` |

**Where did the `switch` go?**  
It moved from `Order` to `Main` (the client). This is expected and correct. In Strategy, the *client* chooses which strategy to configure. The key win is that `Order` is now closed for modification.

---

## 7. Key Takeaways (For Revision)

- **Strategy lets behavior vary independently from the object that uses it.**
- **Composition over inheritance:** `Order` is composed with a `PaymentStrategy` rather than subclassing for each payment type.
- **Open/Closed Principle:** `Order` is open for extension (new strategies) but closed for modification.
- **The context (`Order`) should not know concrete strategy classes.** It only knows the interface.
- **The client (`Main`) is responsible for instantiating and injecting the correct strategy.**

---

## 8. Minor Notes / Polish (Optional)

- `CryptoPayment.java` prints `"using CryptoPayment"` — can be changed to `"using Crypto"` for consistency.
- `Order.checkout()` could guard against `null` strategy to avoid `NullPointerException`.
- Classes are in the default package; in a real project use a package like `strategy.behavioral.pattern`.

---

## 9. Chronological Summary

1. ✅ Wrote naive `Order` with `switch` on payment type (`naive/`)
2. ✅ Tutor identified pain: `Order` violates Open/Closed and SRP
3. ✅ Strategy pattern introduced: extract algorithms behind interface
4. ✅ Refactored into `PaymentStrategy` interface + 3 concrete strategies (`pattern/`)
5. ✅ `Order` now holds a `PaymentStrategy` and delegates — no `switch`
6. ✅ `Main` injects strategy into `Order`
7. ✅ Strategy Pattern marked complete

---

## 10. Next Step (Pending Your Flag)

**Factory Pattern** — born from the remaining pain: `Main` still does `new CreditCardPayment()` directly. If you add many payment methods, `Main` becomes a giant `switch` of `new` calls. Factory centralizes that object creation.
