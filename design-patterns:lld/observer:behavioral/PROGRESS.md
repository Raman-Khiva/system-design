# Observer Pattern — Detailed Progress & Revision Notes

**Directory:** `observer:behavioral/`  
**Language:** Java  
**Pattern Type:** Behavioral  
**Status:** ✅ Complete

---

## 1. Assignment Brief (The Starting Point)

**Scenario:** After `Order.checkout()` completes, multiple services need to be notified:

- EmailService — send order confirmation
- InventoryService — deduct stock
- ShippingService — schedule delivery

**Goal:** `Order` should notify interested services without knowing who they are.

---

## 2. Naive Implementation (Part 1 — Completed)

### `naive/Order.java`
```java
public class Order{
    EmailService emailService;
    InventoryService inventoryService;
    ShippingService shippingService;

    public Order(){
        emailService = new EmailService();
        inventoryService = new InventoryService();
        shippingService = new ShippingService();
    }

    public void checkout(){
        emailService.send();
        inventoryService.update();
        shippingService.schedule();
    }
}
```

**Key characteristic:** `Order` directly instantiates and calls concrete service classes with different method names (`send()`, `update()`, `schedule()`).

---

## 3. Pain Points Identified

| # | Pain | Explanation |
|---|------|-------------|
| 1 | **Tight coupling to concrete classes** | `Order` knows `EmailService`, `InventoryService`, `ShippingService` by name and their method signatures. |
| 2 | **Order creates its own dependencies** | Constructor does `new EmailService()`, etc. Can't inject mocks for testing. |
| 3 | **Adding a service requires editing `Order.java`** | New service = new field + constructor init + method call in `checkout()`. Violates Open/Closed Principle. |
| 4 | **No selective notification** | `checkout()` fires all services unconditionally. |
| 5 | **Inconsistent method names** | Each service has a different method name. `Order` must remember which to call. |

**The core problem:** `Order` is responsible for both processing payment *and* deciding who gets notified.

---

## 4. How the Observer Pattern Was Born

Developers kept encountering this scenario: **"One object (the subject) needs to notify many other objects (observers) when something happens, but the subject shouldn't know who they are."**

Common real-world examples:
- UI frameworks — button click notifies many listeners
- Newsletter subscriptions — publisher notifies subscribers without knowing names
- Stock tickers — price changes notify all subscribed traders
- Event systems — file saved → auto-save, backup, indexer, logger all react

**Pattern:** Observer (Behavioral)  
**Definition:** Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

---

## 5. Refactored Implementation (Part 2 — Completed by You)

### `pattern/Observer.java`
```java
public interface Observer{
    public void update(Order order);
}
```

### `pattern/Order.java`
```java
import java.util.List;
import java.util.ArrayList;

public class Order{
    String id;
    private List<Observer> observers = new ArrayList<>();

    public Order(String _id){
        id = _id;
    }

    public void attach(Observer o) {observers.add(o);}
    public void detach(Observer o) {observers.remove(o);}

    public void checkout(){
        notifyObservers();
    }

    private void notifyObservers(){
        for(Observer o : observers){
            o.update(this);
        }
    }
}
```

### `pattern/EmailService.java`
```java
public class EmailService implements Observer{
    @Override public void update(Order order){
        this.send(order);
    }
    public void send(Order order){
        System.out.println("Sending Email for Order #" + order.id);
    }
}
```

### `pattern/InventoryService.java`
```java
public class InventoryService implements Observer{
    @Override public void update(Order order){
        this.updateInv(order);
    }
    public void updateInv(Order order){
        System.out.println("Updating inventory for Order #" + order.id);
    }
}
```

### `pattern/ShippingService.java`
```java
public class ShippingService implements Observer{
    @Override public void update(Order order){
        this.schedule(order);
    }
    public void schedule(Order order){
        System.out.println("Scheduling Shipping for Order #" + order.id);
    }
}
```

### `pattern/Main.java`
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter order id..");
            String id = sc.next().toLowerCase();

            Order order = new Order(id);
            EmailService o1 = new EmailService();
            InventoryService o2 = new InventoryService();
            ShippingService o3 = new ShippingService();
            order.attach(o1);
            order.attach(o2);
            order.attach(o3);

            System.out.println("Do you want email confirmation...(y/n)");
            String preference = sc.next();
            if(preference.equals("n")){
                order.detach(o1);
            }

            order.checkout();
        }
    }
}
```

---

## 6. What Changed (Before vs After)

| Aspect | Naive (Before) | Observer (After) |
|--------|---------------|------------------|
| `Order` knows concrete services? | Yes (`EmailService`, etc.) | No (only `Observer` interface) |
| Service creation | Inside `Order` constructor | Outside, attached via `attach()` |
| Notification calls | `emailService.send()`, `inventoryService.update()` | `o.update(this)` uniformly |
| Add SMS service | Edit `Order.java` (field + init + call) | Create `SMSService implements Observer`, `order.attach()` |
| Runtime toggle | Impossible | `order.detach(emailService)` |

---

## 7. Chronological Summary

1. ✅ Created naive `Order` with direct service calls
2. ✅ Created `EmailService`, `InventoryService`, `ShippingService` with inconsistent method names
3. ✅ Created `Main` with order confirmation loop
4. ✅ Pain identified: tight coupling, constructor instantiation, fragile on change
5. ✅ Refactored into Observer (interface, subject, attach/detach/notify)
6. ✅ Verified adding/removing observers at runtime without touching `Order.java`

---

## 8. Key Takeaways (For Revision)

- **Observer decouples subject from observers** — subject knows only the interface, not concrete classes.
- **One-to-many dependency** — one state change triggers updates across many objects.
- **Runtime subscription** — observers can attach/detach dynamically.
- **Uniform notification** — all observers implement the same `update()` method.
- **Use case:** Event systems, UI listeners, publish-subscribe, notification services.

---

## 9. Next Pattern

**Decorator** — born from this pain: You need to add gift wrap, insurance, express shipping to an order. Subclassing creates `OrderWithGiftWrap`, `OrderWithInsurance`, `OrderWithBoth`, etc. — a subclass explosion. Decorator wraps objects dynamically instead.
