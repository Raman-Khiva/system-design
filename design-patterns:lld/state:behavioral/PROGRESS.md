# State Pattern — Detailed Progress & Revision Notes

**Directory:** `state:behavioral/`  
**Language:** Java  
**Pattern Type:** Behavioral  
**Status:** ✅ Complete

---

## 1. Assignment Brief (The Starting Point)

**Scenario:** An `Order` goes through a lifecycle:

```
Pending → Paid → Shipped → Delivered → Cancelled
```

Each status changes what actions are allowed:

| Status | Can place? | Can pay? | Can ship? | Can deliver? | Can cancel? |
|--------|-----------|----------|-----------|--------------|-------------|
| Pending | ✅ | ✅ | ❌ | ❌ | ✅ |
| Paid | ❌ | ❌ | ✅ | ❌ | ✅ |
| Shipped | ❌ | ❌ | ❌ | ✅ | ❌ |
| Delivered | ❌ | ❌ | ❌ | ❌ | ❌ |
| Canceled | ❌ | ❌ | ❌ | ❌ | ❌ |

**Goal:** Each status should define its own behavior without `if/else` on status inside every method.

---

## 2. Naive Implementation (Part 1 — Completed)

### `naive/Order.java`
```java
enum Status {
    PENDING, PAID, SHIPPED, DELIVERED, CANCELED
}

public class Order {
    private Status status = Status.PENDING;

    public void place() {
        if (status == Status.PENDING) {
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Cannot place order in " + status + " state.");
        }
    }

    public void pay() {
        if (status == Status.PENDING) {
            System.out.println("Payment successful!");
            status = Status.PAID;
        } else {
            System.out.println("Cannot pay in " + status + " state.");
        }
    }

    // ... ship(), deliver(), cancel() each with if/else on status
}
```

**Pain identified:** `if/else` on status inside every method; adding a new status requires editing every method; adding a new action requires status checks for all statuses.

---

## 3. Refactored Implementation (Part 2 — Completed by You)

### `pattern/State.java`
```java
public interface State {
    void place(Order order);
    void pay(Order order);
    void ship(Order order);
    void deliver(Order order);
    void cancel(Order order);
}
```

### `pattern/Order.java` (Context)
```java
public class Order {
    private State currentState = new PendingState();

    public void place() { currentState.place(this); }
    public void pay() { currentState.pay(this); }
    public void ship() { currentState.ship(this); }
    public void deliver() { currentState.deliver(this); }
    public void cancel() { currentState.cancel(this); }

    public void setState(State state) {
        this.currentState = state;
    }
}
```

### Concrete States (each implements State)
- **`PendingState`**: `pay()` → PaidState, `cancel()` → CanceledState, others invalid
- **`PaidState`**: `ship()` → ShippedState, `cancel()` → CanceledState, others invalid
- **`ShippedState`**: `deliver()` → DeliveredState, others invalid
- **`DeliveredState`**: Terminal — all actions invalid
- **`CanceledState`**: Terminal — all actions invalid

### `pattern/Main.java`
Demonstrates transitions through PENDING → PAID → SHIPPED → DELIVERED and a separate CANCELED scenario.

---

## 4. What Changed (Before vs After)

| Aspect | Naive (Before) | State (After) |
|--------|---------------|---------------|
| Status check | `if/else` in every method | Each state class decides its own behavior |
| Add new status | Edit every method | Create one new class |
| Add new action | Write status checks for all statuses | Add to `State` interface, implement per state |
| Transition logic | Scattered across methods | Centralized in each state class |
| `Order` knows all statuses | Yes | No — only knows `State` interface |

---

## 5. Chronological Summary

1. ✅ Created naive `Order` with `if/else` on `status` enum
2. ✅ Created `Main` demonstrating valid/invalid transitions
3. ✅ Pain identified: duplicated conditionals, fragile on change
4. ✅ Refactored into State (interface + 5 concrete states + context delegation)
5. ✅ Verified transitions work correctly through all states

---

## 6. Key Takeaways (For Revision)

- **State encapsulates behavior per state** — each state class decides what actions are valid
- **Context delegates to current state** — `Order` doesn't know about specific statuses
- **Transitions happen inside state classes** — `order.setState(new PaidState())` etc.
- **Open/Closed Principle** — add new states without modifying existing code
- **Use case:** Objects with lifecycle-driven behavior (orders, connections, documents, game characters)

---

## 7. Next Pattern

**Facade** — born from this pain: Client code talks to 10 different services directly (payment, inventory, shipping, tax, email, etc.). Changes in one service ripple to all clients. Facade provides one simple interface to a complex subsystem.
