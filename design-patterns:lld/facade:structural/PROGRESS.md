# Facade Pattern — Detailed Progress & Revision Notes

**Directory:** `facade:structural/`  
**Language:** Java  
**Pattern Type:** Structural  
**Status:** 🔄 Part 1 Complete (Naive), Part 2 Pending (Facade Refactor)

---

## 1. Assignment Brief (The Starting Point)

**Scenario:** After checkout completes, multiple subsystems need to be coordinated:

- PaymentService — process payment
- InventoryService — deduct stock
- ShippingService — schedule delivery
- TaxService — calculate tax
- EmailService — send confirmation
- AnalyticsService — track sale

**Goal:** Clients should not need to know about every subsystem or the correct order of operations.

---

## 2. Naive Implementation (Part 1 — Completed by You)

### `naive/CheckoutController.java`
```java
public class CheckoutController{
    public void checkout(){
        EmailService email = new EmailService();
        ShippingService shipping = new ShippingService();
        InventoryService inventory = new InventoryService();

        email.sendEmail();
        shipping.scheduleDelivery();
        inventory.deductItems();
    }
}
```

### `naive/Main.java`
```java
public class Main{
    public static void main(String [] args){
        CheckoutController controller = new CheckoutController();
        controller.checkout();
    }
}
```

### Service classes
- `EmailService.sendEmail()` — static
- `ShippingService.scheduleDelivery()` — static
- `InventoryService.deductItems()` — static

**Key characteristic:** `CheckoutController` directly instantiates and calls concrete service classes. Services use static methods.

---

## 3. Pain Points Identified (Tutor Review)

| # | Pain | Explanation |
|---|------|-------------|
| 1 | **Controller coupled to concrete classes** | `CheckoutController` instantiates `EmailService`, `ShippingService`, `InventoryService` directly. Can't use mocks or alternatives. |
| 2 | **Adding a subsystem requires editing controller** | New service = new `new XxxService()` + method call inside `checkout()`. |
| 3 | **Static methods** | All service methods are `static` — can't mock or stub for testing. |
| 4 | **Order of operations hardcoded** | Sequence `email → shipping → inventory` is baked in. Changing order requires editing controller. |
| 5 | **Client knows subsystem details** | Although `Main` only knows `CheckoutController`, the controller itself exposes subsystem complexity. |

**The core problem:** The client (or controller) must know about every subsystem and the correct order to call them.

---

## 4. How the Facade Pattern Was Born

Developers kept encountering: **"A client needs to use a complex subsystem with many classes, but the client shouldn't know about all of them or the correct order of operations."**

Common real-world examples:
- **Compilers** — lexer, parser, optimizer, code generator hidden behind `compile()`
- **Home theater** — TV, DVD player, sound system, lights hidden behind `watchMovie()`
- **Order processing** — payment, inventory, shipping, tax hidden behind `placeOrder()`

**Pattern:** Facade (Structural)  
**Definition:** Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.

---

## 5. The Cure: What Facade Does (Conceptual — Not Yet Implemented by You)

Instead of the client knowing about every service:

1. **Facade class** (`CheckoutFacade`) wraps all subsystems
2. **Facade provides simple methods** like `completeOrder()` that coordinate everything internally
3. **Client calls only the facade** — doesn't know about individual services
4. **Subsystems can be added/removed/changed** inside the facade without touching clients

Result:
```java
CheckoutFacade facade = new CheckoutFacade();
facade.completeOrder(order, amount);  // client doesn't know about payment, inventory, shipping, etc.
```

---

## 6. Your Next Task (Part 2 — Pending)

**Folder:** `facade:structural/pattern/`

### Create:

1. **Keep the service classes** (copy from `naive/`)
2. **`CheckoutFacade.java`** — facade class that:
   - Holds references to all services
   - Has a simple method like `completeOrder()` that calls all services in the correct order
3. **`Main.java`** — calls only `facade.completeOrder(...)`, not individual services

### Key question:
> If you add `PaymentService` and `TaxService`, do you still have to touch `Main`?

(With Facade: **No** — add them inside `CheckoutFacade`, `Main` stays untouched.)

---

## 7. Chronological Summary

1. ✅ Created naive `CheckoutController` with direct service instantiation
2. ✅ Created service classes (`EmailService`, `ShippingService`, `InventoryService`)
3. ✅ Created `Main` driver
4. ✅ Pain identified: tight coupling, static methods, hardcoded order, fragile on change
5. ⏳ Part 2: Refactor into Facade (facade class wraps all services, simple method for client)
6. ⏳ Verify adding a new service doesn't require touching `Main`

---

## 8. Key Takeaways (For Revision)

- **Facade provides a simple interface** to a complex subsystem
- **Client depends on the facade, not individual subsystems**
- **Subsystem changes are contained** inside the facade
- **Use case:** Simplifying complex APIs, hiding subsystem complexity from clients

---

## 9. Next Pattern

**Builder** — born from this pain: Creating complex orders with many optional fields leads to telescoping constructors or huge parameter lists where it's unclear what each argument means.
