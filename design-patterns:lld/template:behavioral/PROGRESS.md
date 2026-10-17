# Template Method Pattern — Detailed Progress & Revision Notes

**Directory:** `template:behavioral/`  
**Language:** Java  
**Pattern Type:** Behavioral  
**Status:** ✅ Complete

---

## 1. Assignment Brief (The Starting Point)

**Scenario:** Your checkout system handles different order types (Standard, Express, International). Each follows roughly the same steps but with small variations:

| Step | Standard Order | Express Order |
|------|---------------|---------------|
| 1. Validate order | Check items in stock | Check items + priority fee |
| 2. Calculate tax | Local tax rate | Local tax + surcharge |
| 3. Notify customer | Email | Email + SMS |
| 4. Update inventory | Local warehouse | Express stock |
| 5. Send bill | Standard bill | Standard bill |

**Goal:** Each order type should run the same step sequence, but with different implementations per step.

---

## 2. Naive Implementation (Part 1 — Completed)

### `naive/Order.java`
```java
public interface Order{
    public void process();
}
```

### `naive/StandardOrder.java`
```java
public class StandardOrder implements Order{
    @Override public void process(){
        System.out.println("Validating order by checking items.....");
        System.out.println("Calculating tax at local rate......");
        System.out.println("Sending notification email....");
        System.out.println("Updating inventory by deducting items from local warehouse");
    }
}
```

### `naive/ExpressOrder.java`
```java
public class ExpressOrder implements Order{
    @Override public void process(){
        System.out.println("Validating order by checking order and if priority fees is  paid...");
        System.out.println("Calculating tax at local rate + subcharges...");
        System.out.println("Sending notification email and sms......");
        System.out.println("Updating inventory by deducting items from express store");
    }
}
```

### Pain identified
| # | Pain | Explanation |
|---|------|-------------|
| 1 | **Duplicated algorithm skeleton** | Both classes contain the identical step sequence. Adding a step requires editing every class. |
| 2 | **Hard to reorder steps** | Change step order? Edit every order class. |
| 3 | **No way to override just one step** | Each class implements entire `process()` from scratch. |
| 4 | **Interface is too thin** | `Order` exposes only `process()`, no contract for individual steps. |

---

## 3. How the Template Method Pattern Was Born

Developers kept encountering: **"Multiple classes share the same algorithm structure, but individual steps vary. We keep copying the skeleton and it's a maintenance nightmare."**

Common examples:
- Data processing pipelines — read → parse → validate → transform → save
- Build systems — compile → test → package → deploy
- Frameworks — authenticate → authorize → execute → respond

**Pattern:** Template Method (Behavioral)  
**Definition:** Define the skeleton of an algorithm in a base class method, deferring some steps to subclasses.

---

## 4. Refactored Implementation (Part 2 — Completed by You)

### `pattern/Order.java`
```java
public abstract class Order{
    protected void validate(){ System.out.println("Validating Order by checking items "); }
    protected void calculateTax(){ System.out.println("Calcualting tax with local rate "); }
    protected void updateInventory(){ System.out.println("Updating inventory by"); }
    protected void notifyCustomer(){ System.out.println("Notifying customer by sending email"); }
    protected void sendBill(){ System.out.println("Sending bill"); }

    public final void process(){
        validate();
        calculateTax();
        notifyCustomer();
        updateInventory();
        sendBill();
    }
}
```

### `pattern/StandardOrder.java`
```java
public class StandardOrder extends Order{
    @Override protected void updateInventory(){
        System.out.println("Updating inventory by deducting items from local warehouse");
    }
}
```

### `pattern/ExpressOrder.java`
```java
public class ExpressOrder extends Order{
    @Override protected void validate(){ ... }
    @Override protected void calculateTax(){ ... }
    @Override protected void updateInventory(){ ... }
    @Override protected void notifyCustomer(){ ... }
}
```

### `pattern/Main.java`
```java
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Please select order type [e=express/s=standard]");
            String type = sc.next();
            Order order;
            if(type.equals("s")){ order = new StandardOrder(); order.process(); }
            else if(type.equals("e")){ order = new ExpressOrder(); order.process(); }
            else{ System.out.println("Please select a valid type"); }
        }
    }
}
```

---

## 5. What Changed (Before vs After)

| Aspect | Naive (Before) | Template Method (After) |
|--------|---------------|-------------------------|
| Algorithm skeleton | Copied in every class | Defined once in `Order.process()` |
| `process()` overridable? | Yes (each class rewrites it) | No (`final` — step order protected) |
| Add 5th step | Edit every order class | Edit one class (base) |
| Override one step only | Impossible | Possible (e.g., `StandardOrder` overrides only `updateInventory()`) |
| Contract for steps | None | Base class declares all step methods |

---

## 6. Chronological Summary

1. ✅ Created naive `Order` interface with `process()`
2. ✅ Created `StandardOrder` and `ExpressOrder` with duplicated 4-step skeleton
3. ✅ Created `Main` driver
4. ✅ Pain identified: duplicated skeleton, hard to reorder, all-or-nothing override, thin interface
5. ✅ Refactored into Template Method (abstract base with `final` process skeleton + hook methods)
6. ✅ Verified `StandardOrder` overrides only one step (`updateInventory`)
7. ✅ Verified `ExpressOrder` overrides multiple steps, inherits rest

---

## 7. Key Takeaways (For Revision)

- **Template Method defines the algorithm skeleton once** in a base class.
- **`final` keyword** on the template method prevents subclasses from changing the step order.
- **Hook methods** (concrete methods with defaults) let subclasses override selectively.
- **Hollywood Principle:** "Don't call us, we'll call you" — base class calls subclass methods.
- **Use case:** When multiple algorithms share the same structure but vary in details.

---

## 8. Next Pattern

**Command** — born from this pain: You need to place, cancel, and retry orders with undo support. Business logic is tangled with invocation/request handling; there's no way to undo or retry cleanly.
