# Design Patterns — Quick Revision Guide

**Method:** Build naive → feel the pain → pattern emerges → refactor  
**Language:** Java  
**Purpose:** Interview & revision reference

---

## 1. Strategy (Behavioral) ✅

**Scenario:** `Order.checkout()` needs to support multiple payment methods (Credit Card, PayPal, Crypto).

**Naive Approach:** `switch` on payment type inside `Order.checkout()`.

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
                System.out.println("Payment failed!");
        }
    }
}
```

**Pain:** Adding a new payment method forces editing `Order`. Violates Open/Closed and Single Responsibility.

**Cure:** Extract each algorithm into its own class behind a common interface.

**Formal Definition:** Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

**Intuitive Definition:** "Swap algorithms like plugins."

**Refactored Structure:**
- `PaymentStrategy` interface → `pay(double amount)`
- `CreditCardPayment`, `PayPalPayment`, `CryptoPayment` implement it
- `Order` holds a `PaymentStrategy` reference, calls `pay()` — no `switch`

---

## 2. Factory (Behavioral) ✅

**Scenario:** Even with Strategy, client code still does `new CreditCardPayment()`, `new PayPalPayment()` directly.

**Naive Approach:** Scattered `new` calls across the codebase.

```java
PaymentStrategy strategy;
if (type.equals("credit_card")) {
    strategy = new CreditCardPayment();
} else if (type.equals("paypal")) {
    strategy = new PayPalPayment();
} else {
    strategy = new CryptoPayment();
}
```

**Pain:** If class names change or construction gets complex, every client breaks. `new` is scattered everywhere.

**Cure:** Centralize object creation in one class/method so clients don't instantiate concrete classes directly.

**Formal Definition:** Define an interface for creating an object, but let subclasses/factories decide which class to instantiate.

**Intuitive Definition:** "Don't call `new` everywhere; let a factory decide."

**Refactored Structure:**
- `PaymentFactory` with `getStrategy(type)` method
- Returns the right `PaymentStrategy` based on a type string
- Clients call `PaymentFactory.getStrategy("paypal")` — no direct `new`

---

## 3. Singleton (Behavioral) ✅

**Scenario:** Shared payment gateway config / order processor — need exactly one instance.

**Naive Approach:** Multiple instances cause inconsistent state or wasted resources.

```java
PaymentConfig config1 = new PaymentConfig();
PaymentConfig config2 = new PaymentConfig();  // different state!
```

**Pain:** Multiple instances of config/connection cause inconsistent state. No global access point.

**Cure:** Ensure only one instance exists and provide global access to it.

**Formal Definition:** Ensure a class has only one instance and provide a global point of access to it.

**Intuitive Definition:** "One instance to rule them all."

**Refactored Structure:**
- `private` constructor
- `private static` instance field
- `public static getInstance()` method with lazy initialization

---

## 4. Observer (Behavioral) ✅

**Scenario:** After `Order.checkout()`, notify email, inventory, shipping services.

**Naive Approach:** `Order` directly calls concrete services with inconsistent method names.

```java
public class Order {
    EmailService emailService;
    InventoryService inventoryService;
    ShippingService shippingService;

    public Order() {
        emailService = new EmailService();
        inventoryService = new InventoryService();
        shippingService = new ShippingService();
    }

    public void checkout() {
        emailService.send();
        inventoryService.update();
        shippingService.schedule();
    }
}
```

**Pain:** Adding a new service = edit `Order.java`. Tight coupling to concrete classes. No runtime subscription.

**Cure:** Define a one-to-many dependency so when one object changes state, all dependents are notified automatically.

**Formal Definition:** Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

**Intuitive Definition:** "Subscribe and get notified."

**Refactored Structure:**
- `Observer` interface → `update(Order order)`
- `Order` maintains `List<Observer>`, not concrete services
- `attach(Observer)` / `detach(Observer)` for runtime subscription
- `notifyObservers()` loops and calls `o.update(this)` uniformly

---

## 5. Template Method (Behavioral) ✅

**Scenario:** Checkout flow: validate → tax → payment → confirmation — same skeleton, different steps.

**Naive Approach:** Copy-paste the skeleton across order types, override inconsistently.

**Pain:** Duplicated checkout skeleton across different order types. Steps hard to reuse or skip.

**Cure:** Define the skeleton of an algorithm in a base class, let subclasses override specific steps.

**Formal Definition:** Define the skeleton of an algorithm in an operation, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps without changing the algorithm's structure.

**Intuitive Definition:** "Fixed skeleton, customizable steps."

---

## 6. Command (Behavioral) ✅

**Scenario:** Place, cancel, retry orders with undo support.

**Naive Approach:** Business logic tangled with invocation/request handling.

**Pain:** No way to undo or retry cleanly. Logic mixed with how requests are handled.

**Cure:** Turn a request into an object so you can parameterize, queue, log, or undo operations.

**Formal Definition:** Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

**Intuitive Definition:** "Turn actions into objects."

---

## 7. State (Behavioral) ✅

**Scenario:** Order lifecycle: `Pending → Paid → Shipped → Delivered → Cancelled`.

**Naive Approach:** Huge `if/else` on `order.status` inside every method.

```java
public void ship() {
    if (status.equals("Pending")) { ... }
    else if (status.equals("Paid")) { ... }
    else if (status.equals("Shipped")) { ... }
}
```

**Pain:** Adding a new status breaks every method. Behavior scattered across conditionals.

**Cure:** Let an object change behavior when its internal state changes.

**Formal Definition:** Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.

**Intuitive Definition:** "Behavior changes with mood (state)."

---

## 8. Facade (Behavioral) ⏭️ Skipped

**Scenario:** Simplify a complex checkout subsystem (payment + inventory + shipping + tax).

**Pain:** Client code talking to 10 different services directly. Changes ripple everywhere.

**Formal Definition:** Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.

**Intuitive Definition:** "One simple door to a messy room."

---

## 9. Builder (Creational) ✅

**Scenario:** Create complex orders with many optional fields (id, amt, promocode, giftWrap, giftMessage).

**Naive Approach:** Telescoping constructors for every combination.

```java
public Order(String _id, int _amt) { ... }
public Order(String _id, int _amt, boolean _giftWrap) { ... }
public Order(String _id, int _amt, boolean _giftWrap, String _giftMessage) { ... }
public Order(String _id, int _amt, int _promoCode) { ... }
```

**Pain:** Constructor explosion (2^n), unreadable call sites, can't skip optional fields, mutable objects.

**Cure:** Separate construction from representation. Build step by step, produce immutable object.

**Formal Definition:** Separate the construction of a complex object from its representation, so the same construction process can create different representations.

**Intuitive Definition:** "Build step by step, not one giant constructor."

**Refactored Structure:**
- `Order` — all fields `final`, single constructor taking `Builder`
- `OrderBuilder` — fluent methods `.id()`, `.amt()`, `.giftWrap()`, `.build()`
- `build()` creates immutable `Order` in one shot

---

## 10. Decorator (Behavioral) ⬜ Pending

**Scenario:** Add gift wrap, insurance, express shipping to an order dynamically.

**Naive Approach:** Subclass explosion — `OrderWithGiftWrap`, `OrderWithInsurance`, `OrderWithBoth`, etc.

**Pain:** 2^n subclasses for n options. Adding one option doubles the class count.

**Formal Definition:** Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.

**Intuitive Definition:** "Wrap it, don't subclass it."

---

## Quick Revision Table

| # | Pattern | Remember it as... |
|---|---------|-------------------|
| 1 | Strategy | "Swap algorithms like plugins." |
| 2 | Factory | "Don't call `new` everywhere; let a factory decide." |
| 3 | Singleton | "One instance to rule them all." |
| 4 | Observer | "Subscribe and get notified." |
| 5 | Template Method | "Fixed skeleton, customizable steps." |
| 6 | Command | "Turn actions into objects." |
| 7 | State | "Behavior changes with mood (state)." |
| 8 | Facade | "One simple door to a messy room." |
| 9 | Builder | "Build step by step, not one giant constructor." |
| 10 | Decorator | "Wrap it, don't subclass it." |

---

## Pattern Categories

| Category | Patterns |
|----------|----------|
| **Creational** | Singleton, Factory, Builder |
| **Behavioral** | Strategy, Observer, Template Method, Command, State, Decorator |
| **Structural** | Facade |
