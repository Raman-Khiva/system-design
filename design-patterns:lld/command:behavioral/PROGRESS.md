# Command Pattern — Detailed Progress & Revision Notes

**Directory:** `command:behavioral/`  
**Language:** Java  
**Pattern Type:** Behavioral  
**Status:** 🔄 Part 2 Partially Done — CommandInvoker implemented but has bugs; undo not demonstrated

---

## 1. Assignment Brief (The Starting Point)

**Scenario:** Your order processing logic is mixed with UI/controller code. You need to place and cancel orders.

**Goal:** Turn order actions into objects so they can be undone, retried, queued, or logged.

---

## 2. Naive Implementation (Part 1 — Completed)

### `naive/OrderProcessor.java`
```java
public class OrderProcessor{
    public void placeOrder(){
        System.out.println("Validating order...");
        System.out.println("Calculating tax");
        System.out.println("Sending notification");
        System.out.println("Updating inventory..");
    }

    public void cancelOrder(){
        System.out.println("Informing shipping team...");
        System.out.println("Initializing refunt..");
        System.out.println("Sending notification....");
    }
}
```

### `naive/Main.java`
```java
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Choose your action [p=place order / c = cancel order]");
            String action = sc.next();
            OrderProcessor processor = new OrderProcessor();
            if(action.equals("p")){ processor.placeOrder(); }
            else if(action.equals("c")){ processor.cancelOrder(); }
            else{ System.out.println("Please choose a valid option"); }
        }
    }
}
```

**Pain identified:** Business logic tangled with invocation, no undo, no queue, no audit trail, fragile on change.

---

## 3. Refactored Implementation (Part 2 — Partially Done)

### `pattern/Command.java` ✅
```java
public interface Command{
    public void execute();
    public void undo();
}
```

### `pattern/Order.java` (Receiver) ✅
```java
public class Order{
    public void place(){ ... }
    public void cancel(){ ... }
}
```

### `pattern/PlaceOrderCommand.java` ✅
```java
public class PlaceOrderCommand implements Command{
    Order order;
    @Override public void execute(){ order.place(); }
    @Override public void undo(){ order.cancel(); }
}
```

### `pattern/CancelOrderCommand.java` ✅
```java
public class CancelOrderCommand implements Command{
    Order order;
    @Override public void execute(){ order.cancel(); }
    @Override public void undo(){ order.place(); }
}
```

### `pattern/CommandInvoker.java` ❌ Has bugs
```java
import java.util.Stack   // ❌ Missing semicolon

public class CommandInvoker{
    Stack<Command> commands;  // ❌ Not initialized (null)

    public void exectueCmd(Command command){  // ❌ Typo: exectueCmd
        command.execute();
        commands.push(command);  // ❌ NullPointerException
    }
    public void undoCmd(){  // Typo: undoCmd
        if(commands.empty()) return;
        Command command = commands.pop();
        command.undo();
    }
}
```

**Issues:**
1. Missing semicolon on import → won't compile
2. `commands` field never initialized → NullPointerException at runtime
3. Method name typos (`exectueCmd`, `undoCmd`)

### `pattern/Main.java` ⚠️ Logic issues
```java
CommandInvoker invoker = new CommandInvoker();
while(true){
    Order order = new Order();  // ❌ New Order every loop — undo operates on wrong object
    Command placeOrder = new PlaceOrderCommand(order);
    Command cancelOrder = new CancelOrderCommand(order);

    if(action.equals("p")){
        invoker.executeCmd(placeOrder);
    }
    else if(action.equals("c")){
        invoker.executeCmd(cancelOrder);
    }
    // ❌ No undo option demonstrated
}
```

**Issues:**
1. New `Order` created every iteration — undo would reverse a different order than placed
2. No undo option in the menu
3. `CommandInvoker` not used for undo

---

## 4. What You Need to Fix

1. **`CommandInvoker.java`:**
   - Add semicolon: `import java.util.Stack;`
   - Initialize: `Stack<Command> commands = new Stack<>();`
   - Fix method name typos (optional)

2. **`Main.java`:**
   - Move `Order order = new Order()` outside the while loop
   - Add undo option (e.g., input `u`)
   - Call `invoker.undoCmd()` when undo is selected

3. **Demonstrate:** Place an order → Undo it → See the reversal

---

## 5. Chronological Summary

1. ✅ Created naive `OrderProcessor` with `placeOrder()` and `cancelOrder()`
2. ✅ Created naive `Main` that invokes methods directly
3. ✅ Pain identified: tangled logic, no undo, no queue, fragile on change, no audit
4. ✅ Created `Command` interface, `Order` receiver, `PlaceOrderCommand`, `CancelOrderCommand`
5. ⚠️ Created `CommandInvoker.java` — has compilation/runtime bugs
6. ⚠️ `Main.java` — bug with new Order each loop, no undo demonstrated

---

## 6. Next Step

Fix the bugs above, demonstrate undo, then we move to **State** (#8).
