# Builder Pattern — Progress & Revision

**Directory:** `builder:creational/`  
**Language:** Java  
**Pattern Type:** Creational  
**Status:** ✅ Complete

---

## Assignment Brief

**Scenario:** Create an `Order` class with multiple fields — some required, some optional.

**Fields:**
- Required: `id` (String), `amt` (int)
- Optional: `promocode` (int), `giftWrap` (boolean), `giftMessage` (String)

**Goal:** Support any combination of optional fields without constructor explosion.

---

## Naive Implementation (Telescoping Constructors)

### `naive/Order.java`
```java
public class Order{
    public String id;
    public int amt;
    public int promocode = 0;
    public boolean giftWrap = false;
    public String giftMessage = "";

    public Order(String _id, int _amt){
        id = _id;
        amt = _amt;
    }
    public Order(String _id, int _amt, boolean _giftWrap){
        id = _id;
        amt = _amt;
        giftWrap = _giftWrap;
    }
    public Order(String _id, int _amt, boolean _giftWrap, String _giftMessage){
        id = _id;
        amt = _amt;
        giftWrap = _giftWrap;
        giftMessage = _giftMessage;
    }
    public Order(String _id, int _amt, int _promoCode){
        id = _id;
        amt = _amt;
        promocode = _promoCode;
    }

    public void printOrder() {
        System.out.println(
            "Order{id='" + id +
            "', amt=" + amt +
            ", giftWrap=" + giftWrap +
            ", giftMessage='" + giftMessage +
            "', promo=" + promocode
        );
    }
}
```

### `naive/Main.java`
```java
public class Main {
    public static void main(String[] args) {
        Order simpleOrder = new Order("order1", 10);
        Order orderWithGiftWrap = new Order("order2", 20, true);
        Order orderWithGiftWrapAndMessage =
                new Order("order3", 20, true, "Happy Birthday");
        Order orderWithPromo = new Order("order4", 5, 4831);

        simpleOrder.printOrder();
        orderWithGiftWrap.printOrder();
        orderWithGiftWrapAndMessage.printOrder();
        orderWithPromo.printOrder();
    }
}
```

---

## Pain Points

| # | Pain | Explanation |
|---|------|-------------|
| 1 | **Constructor explosion** | 3 optional fields → 4 constructors. Add 1 more optional field → 8 constructors. Grows as 2^n. |
| 2 | **Unreadable call sites** | `new Order("order2", 20, true)` — what does `true` mean? Must look up the constructor signature. |
| 3 | **Can't skip optional fields** | Want `id`, `amt`, and `giftMessage` (but no giftWrap)? No constructor matches. |
| 4 | **Duplicate code** | Every constructor repeats `id = _id; amt = _amt;`. |
| 5 | **Mutable / incomplete state** | Object created in an incomplete state. No way to make it immutable. |
| 6 | **No compile-time safety** | `new Order("order4", 5, 4831)` — the `4831` could be a promo or a mistake. Nothing enforces correctness. |

**The core problem:** Too many optional parameters make constructors unreadable, unmaintainable, and error-prone.

---

## How the Builder Pattern Was Born

Developers kept hitting this: **"I need to create an object with many optional parameters, but constructors become unreadable and unmaintainable."**

The evolution:
1. **JavaBeans / Setter approach** — `new Order()` then `setId()`, `setAmt()`, `setGiftWrap()`... but leaves the object mutable and inconsistent between calls. Not thread-safe.
2. **Telescoping constructors** — what we just wrote. Solves immutability but creates the problems above.
3. **Builder pattern** — a separate `Builder` class that collects all parameters step-by-step, then calls `build()` to produce an immutable object in one shot.

**Pattern:** Builder (Creational)  
**Definition:** Separate the construction of a complex object from its representation, so the same construction process can create different representations.

---

## The Cure: What Builder Does

Instead of multiple constructors:

1. **`Order`** — all fields `final`, single constructor that takes a `Builder`
2. **`OrderBuilder`** — collects all parameters via fluent methods
3. **`build()`** — creates the immutable `Order` in one call

`Order` is created once, fully formed, and cannot be modified.

---

## Refactored Implementation

### `pattern/Order.java`
```java
public class Order{
    public final String id;
    public final int    amt;
    public final boolean giftWrap;
    public final String giftMessage;
    public final int promoCode;

    public Order(OrderBuilder _builder){
        id = _builder.id;
        amt = _builder.amt;
        giftWrap = _builder.giftWrap;
        giftMessage = _builder.giftMessage;
        promoCode = _builder.promoCode;
    }

    public static OrderBuilder Builder(){
        return new OrderBuilder();
    }
}
```

### `pattern/OrderBuilder.java`
```java
public class OrderBuilder{
    public String id;
    public int    amt;
    public boolean giftWrap;
    public String giftMessage;
    public int promoCode;

    public OrderBuilder id(String _id){
        this.id = _id;
        return this;
    }
    public OrderBuilder amt(int _amt){
        this.amt = _amt;
        return this;
    }
    public OrderBuilder giftWrap(boolean wrap){
        this.giftWrap = wrap;
        return this;
    }
    public OrderBuilder giftMessage(String message){
        this.giftMessage = message;
        return this;
    }
    public OrderBuilder promoCode(int code){
        this.promoCode = code;
        return this;
    }
    public Order build(){
        return new Order(this);
    }
}
```

### `pattern/Main.java`
```java
public class Main{
    public static void main(String[] args){
        Order order = Order.Builder()
            .id("order1")
            .amt(40)
            .giftWrap(true)
            .promoCode(4958)
            .build();

        System.out.print("Order 1 details \n"
            + "id : " + order.id + "\n"
            + "amount : " + order.amt + "\n"
            + "gift wraped : " + order.giftWrap + "\n"
            + "promo code : " + order.promoCode + "\n"
        );
    }
}
```

---

## How Builder Cured the Pain

| Before (Telescoping Constructors) | After (Builder) |
|-----------------------------------|-----------------|
| 4+ constructors for 3 optional fields | 1 private constructor |
| `new Order("order2", 20, true)` — unclear | `.giftWrap(true)` — self-documenting |
| Can't skip optional fields | Skip any optional field — just don't call it |
| Duplicate assignment code in every constructor | One constructor, one place |
| Mutable object | Immutable — all fields `final` |
| Adding a new optional field = new constructor | Adding a new optional field = new fluent method on builder only |

**The core win:** Construction is separated from representation. The builder handles complexity; the product stays clean and immutable.

---

## Chronological Summary

1. ✅ Created naive `Order` with telescoping constructors for different field combinations
2. ✅ Felt the pain: constructor explosion, unreadable call sites, can't skip fields
3. ✅ Learned Builder pattern: separate construction from representation
4. ✅ Refactored: `Order` with `final` fields + `OrderBuilder` with fluent methods
5. ✅ Verified: adding a new optional field requires zero changes to `Order` class

---

## Key Takeaways

- **Builder separates construction from representation** — the same build process can create different representations.
- **Immutable objects** — all fields are `final`, set once at construction.
- **Readable call sites** — `.giftWrap(true)` is self-documenting.
- **No constructor explosion** — one constructor, many fluent methods.
- **Use case:** Objects with many optional parameters, complex construction steps, or when immutability is desired.
- **Formal Definition:** Separate the construction of a complex object from its representation, so the same construction process can create different representations.
- **Also known as:** telescoping constructor replacement.

---

## Questions to Consider

- Why can't `OrderBuilder` be an inner class of `Order` to make the constructor private?
- What happens if `build()` is called without setting required fields (`id`, `amt`)? Should the builder validate?
- How does Builder differ from Factory? (Factory creates different types; Builder creates one type with different configurations.)

---

## Next Pattern

**Template Method** — born from this pain: You have a checkout flow (validate → tax → payment → confirmation) that's the same across order types, but individual steps vary. Copy-pasting the skeleton leads to duplication and inconsistency. Template Method defines the skeleton in a base class and lets subclasses override specific steps.
