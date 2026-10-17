# Template Method Pattern — Detailed Progress & Revision Notes

**Directory:** `template:behavioral/`  
**Language:** Java  
**Pattern Type:** Behavioral  
**Status:** 🔄 Part 1 Complete (Naive), Part 2 Pending (Template Method Refactor)

---

## 1. Assignment Brief (The Starting Point)

**Scenario:** Your checkout system handles different order types (Standard, Express, International). Each follows roughly the same steps but with small variations:

| Step | Standard Order | Express Order | International Order |
|------|---------------|---------------|---------------------|
| 1. Validate order | Check items in stock | Check items + priority fee | Check items + customs docs |
| 2. Calculate tax | Local tax rate | Local tax + surcharge | Import duty + VAT |
| 3. Process payment | Standard gateway | Express gateway | International + currency conversion |
| 4. Send confirmation | Email | Email + SMS | Email + customs notification |
| 5. Update inventory | Local warehouse | Express stock | Deduct + declare for export |

**Goal:** Each order type should run the same 5-step sequence, but with different implementations per step.

---

## 2. Naive Implementation (Part 1 — Completed by You)

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

### `naive/Main.java`
```java
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Please select order type [e=express/s=standard]");
            String type = sc.next();

            Order order;
            if(type.equals("s")){
                 order = new StandardOrder();
                 order.process();
            }
            else if(type.equals("e")){
                 order = new ExpressOrder();
                 order.process();
            }else{
                System.out.println("Please select a valid type");
            }
        }
    }
}
```

**Key characteristic:** Each order class implements the entire `process()` from scratch. The 4-step sequence is duplicated across classes.

---

## 3. Pain Points Identified (Tutor Review)

| # | Pain | Explanation |
|---|------|-------------|
| 1 | **Duplicated algorithm skeleton** | Both `StandardOrder` and `ExpressOrder` contain the identical step sequence. Adding a 5th step requires editing every order class. |
| 2 | **Hard to reorder steps** | If business changes step order (e.g., notify before tax), every order class must be edited. |
| 3 | **No way to override just one step** | Each class implements the entire `process()` from scratch. If only tax differs, you still rewrite validation, notification, inventory. |
| 4 | **Interface is too thin** | `Order` exposes only `process()`, no contract declaring the individual steps that all orders must follow. |

**The core problem:** Multiple classes share the same algorithm structure but individual steps vary. The skeleton is copied, not centralized.

---

## 4. How the Template Method Pattern Was Born

Developers kept encountering this scenario: **"Multiple classes share the same algorithm structure, but individual steps vary. We keep copying the skeleton and it's a maintenance nightmare."**

Common real-world examples:
- **Data processing pipelines** — read → parse → validate → transform → save (each source varies parse/transform steps)
- **Build systems** — compile → test → package → deploy (each project type varies test/package)
- **Frameworks** — HTTP request handling → authenticate → authorize → execute → respond (each endpoint varies execute)

**Pattern:** Template Method (Behavioral)  
**Definition:** Define the skeleton of an algorithm in a base class method, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing its structure.

---

## 5. The Cure: What Template Method Does (Conceptual — Not Yet Implemented by You)

Instead of each subclass implementing `process()` entirely:

1. **Abstract base class** defines `process()` as a **final** method containing the step skeleton:
   ```java
   public final void process() {
       validate();
       calculateTax();
       notifyCustomer();
       updateInventory();
   }
   ```

2. **Individual steps** are declared as abstract or hook methods that subclasses override:
   ```java
   protected abstract void validate();
   protected abstract void calculateTax();
   ```

3. **Subclasses** only override the steps that differ. The skeleton stays in one place.

Result:
- Add a 5th step → edit **one** class (the abstract base)
- Reorder steps → edit **one** class
- Override one step → only that step in the subclass

---

## 6. Your Next Task (Part 2 — Pending)

**Folder:** `template:behavioral/pattern/`

### Create `Order.java` as abstract class:
1. `final void process()` — contains the step skeleton (cannot be overridden)
2. Abstract methods for steps that vary (e.g., `validate()`, `calculateTax()`, etc.)
3. Optional hook methods with default empty implementations

### Create concrete subclasses:
4. **`StandardOrder.java`** — extends `Order`, overrides only steps that differ
5. **`ExpressOrder.java`** — extends `Order`, overrides only steps that differ

### Update `Main.java`:
6. Same driver — order types now share the skeleton

---

## 7. Chronological Summary

1. ✅ Created naive `Order` interface with `process()`
2. ✅ Created `StandardOrder` and `ExpressOrder` with duplicated 4-step skeleton
3. ✅ Created `Main` driver
4. ✅ Pain identified: duplicated skeleton, hard to reorder, all-or-nothing override, thin interface
5. ⏳ Part 2: Refactor into Template Method (abstract base with `final` process skeleton)
6. ⏳ Verify adding a 5th step requires editing only the base class

---

## 8. Key Takeaways (For Revision)

- **Template Method defines the algorithm skeleton once** in a base class.
- **`final` keyword** on the template method prevents subclasses from changing the step order.
- **Abstract/hook methods** let subclasses customize individual steps.
- **Hollywood Principle:** "Don't call us, we'll call you" — the base class calls subclass methods, not vice versa.
- **Use case:** When multiple algorithms share the same structure but vary in details.

---

## 9. Questions to Consider Before Part 2

- Why should `process()` be `final`? What happens if a subclass overrides it?
- What is a "hook method" and when would you use one instead of an abstract method?
- Can Template Method work with an interface instead of an abstract class? (Not really — you need method bodies for the skeleton.)

---

## 10. Next Pattern

**Command** — born from this pain: You need to place, cancel, and retry orders with undo support. Business logic is tangled with invocation/request handling; there's no way to undo or retry cleanly.
