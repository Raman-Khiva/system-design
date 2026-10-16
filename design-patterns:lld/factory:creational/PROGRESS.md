# Factory Method Pattern — Detailed Progress & Revision Notes

**Directory:** `factory:creational/`  
**Language:** Java  
**Pattern Type:** Creational  
**Status:** ✅ Complete

---

## 1. Assignment Brief (The Starting Point)

**Scenario:** Building on the Strategy pattern, `Order` can now process payments via interchangeable `PaymentStrategy` objects. However, the client (`Main`) still directly instantiates concrete payment classes:

```java
strategy = new CreditCardPayment();
strategy = new PayPalPayment();
strategy = new CryptoPayment();
```

**Problem:** Every client that needs a payment strategy must know every concrete payment class. Adding Apple Pay means finding every `new ...Payment()` across the app and updating it. If a payment constructor changes, every client breaks.

**Goal:** Centralize object creation so clients never call `new` on payment classes directly.

---

## 2. Naive Implementation (Before Pattern)

### `naive/Main.java`
```java
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
                strategy = new CreditCardPayment();  // client knows concrete class
                break;
            case "paypal":
                strategy = new PayPalPayment();      // client knows concrete class
                break;
            case "crypto":
                strategy = new CryptoPayment();      // client knows concrete class
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

### Pain identified
| # | Pain | Explanation |
|---|------|-------------|
| 1 | **Client coupled to concrete classes** | `Main` directly calls `new CreditCardPayment()`, etc. |
| 2 | **Fragile on change** | Adding Apple Pay requires editing every client that instantiates payments. |
| 3 | **Constructor changes break clients** | If `CreditCardPayment` needs new constructor args, every `new` call site breaks. |

---

## 3. How the Factory Method Pattern Was Born

Developers kept facing this pain: *"I don't want my client code to know which concrete class to instantiate. I want to delegate that decision."*

**The cure:** Define an interface for creating an object, but let subclasses/factories decide which class to instantiate. The client depends on the factory interface, not concrete classes.

**Pattern:** Factory Method (Creational)  
**Definition:** Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.

---

## 4. Refactored Implementation (After Pattern)

### `pattern/PaymentFactory.java`
```java
public interface PaymentFactory {
    PaymentStrategy createPayment();
}
```

### `pattern/CreditCardFactory.java`
```java
public class CreditCardFactory implements PaymentFactory {
    @Override
    public PaymentStrategy createPayment() {
        return new CreditCardPayment();
    }
}
```

### `pattern/PayPalFactory.java`
```java
public class PayPalFactory implements PaymentFactory {
    @Override
    public PaymentStrategy createPayment() {
        return new PayPalPayment();
    }
}
```

### `pattern/CryptoFactory.java`
```java
public class CryptoFactory implements PaymentFactory {
    @Override
    public PaymentStrategy createPayment() {
        return new CryptoPayment();
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

        PaymentFactory factory;
        switch (method) {
            case "credit_card":
                factory = new CreditCardFactory();  // client knows factory, not payment
                break;
            case "paypal":
                factory = new PayPalFactory();
                break;
            case "crypto":
                factory = new CryptoFactory();
                break;
            default:
                System.out.println("Invalid payment method.");
                sc.close();
                return;
        }

        PaymentStrategy strategy = factory.createPayment();  // creation delegated
        Order order = new Order(strategy);
        order.checkout(amount);

        sc.close();
    }
}
```

### `pattern/Order.java` (unchanged from Strategy)
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

---

## 5. What Changed (Before vs After)

| Aspect | Naive (Before) | Factory Method (After) |
|--------|---------------|------------------------|
| `Main` knows concrete payment classes? | Yes (`new CreditCardPayment()`) | No (knows `CreditCardFactory`) |
| Where `new` is called | Inside `Main` (client) | Inside factory classes |
| Add 4th payment method | Edit every client's `switch` | Add new factory class; clients may add one factory case |
| Constructor change impact | Every `new` call site breaks | Only the matching factory breaks |
| Client depends on | Concrete classes | `PaymentFactory` interface |

**Where did the `new` go?**  
It moved from `Main` into each concrete factory. `Main` now switches on factories, not products.

---

## 6. Key Takeaways (For Revision)

- **Factory Method encapsulates object creation.** Clients ask for a product through a factory interface; they don't instantiate concrete classes directly.
- **Single Responsibility for creation:** Each factory knows how to create exactly one product type.
- **Open/Closed Principle:** To add a new payment type, add a new factory class. Existing factories don't change.
- **Dependency inversion:** `Main` depends on `PaymentFactory` (abstraction), not `CreditCardPayment` (concrete).
- **Relationship to Strategy:** Factory Method is often used *with* Strategy. Strategy encapsulates the algorithm; Factory Method encapsulates the creation of the strategy object.

---

## 7. Chronological Summary

1. ✅ Strategy pattern complete: `Order` delegates to `PaymentStrategy`, no `switch` in `Order`
2. ✅ Identified new pain: `Main` still calls `new` on concrete payment classes directly
3. ✅ Factory Method introduced: create `PaymentFactory` interface + concrete factories
4. ✅ `Main` refactored: chooses a factory, calls `factory.createPayment()`, never calls `new ...Payment()` directly
5. ✅ Factory Method marked complete

---

## 8. Next Step (Pending Your Flag)

**Singleton Pattern** — born from this pain: You may end up with multiple factory instances or multiple payment gateway config objects floating around, causing inconsistent state. Singleton ensures only one instance exists and provides global access.
