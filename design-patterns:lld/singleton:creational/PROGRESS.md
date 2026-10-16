# Singleton Pattern — Detailed Progress & Revision Notes

**Directory:** `singleton:creational/`  
**Language:** Java  
**Pattern Type:** Creational  
**Status:** 🔄 Part 1 Complete (Naive), Part 2 Pending (Singleton Refactor)

---

## 1. Assignment Brief (The Starting Point)

**Scenario:** Your checkout app needs shared payment gateway settings used across the whole application:

- `apiEndpoints` (e.g., `/some/api/endpoint`)
- `defaultCurrency` (e.g., `"USD"`)
- `timeoutSeconds` (e.g., `120`)
- `isSandboxMode` (e.g., `false`)

**Goal:** Different parts of the app (PaymentService, OrderService, RefundService, etc.) should read and update the **same** configuration object.

---

## 2. Naive Implementation (Part 1 — Completed by You)

### `naive/PaymentGatewayConfig.java`
```java
public class PaymentGatewayConfig {
    private String apiEndpoints;
    private String defaultCurrency;
    private int timeoutSeconds;
    private boolean isSandboxMode;

    public PaymentGatewayConfig() {
        apiEndpoints = "/some/api/endpoint";
        defaultCurrency = "USD";
        timeoutSeconds = 120;
        isSandboxMode = false;
    }

    public void setApiEndpoints(String endpoints) {
        apiEndpoints = endpoints;
    }

    public void setDefaultCurrency(String currency) {
        defaultCurrency = currency;
    }

    public void setTimeoutSeconds(int seconds) {
        timeoutSeconds = seconds;
    }

    public void setIsSandboxMode(boolean mode) {
        isSandboxMode = mode;
    }

    public String getApiEndpoints() {
        return apiEndpoints;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public boolean isSandboxMode() {
        return isSandboxMode;
    }
}
```

### `naive/Main.java`
```java
public class Main {
    public static void main(String[] args) {
        PaymentGatewayConfig config1 = new PaymentGatewayConfig();
        config1.setIsSandboxMode(true);

        PaymentGatewayConfig config2 = new PaymentGatewayConfig();

        PaymentGatewayConfig[] configs = {config1, config2};

        for (int i = 0; i < configs.length; i++) {
            PaymentGatewayConfig config = configs[i];
            System.out.println("====== CONFIG " + i + " ======");
            System.out.println("apiEndpoints: " + config.getApiEndpoints());
            System.out.println("defaultCurrency: " + config.getDefaultCurrency());
            System.out.println("timeoutSeconds: " + config.getTimeoutSeconds());
            System.out.println("isSandboxMode: " + config.isSandboxMode());
            System.out.println();
        }
    }
}
```

**Key characteristic:** The constructor is `public`, so anyone can call `new PaymentGatewayConfig()` and create independent copies.

---

## 3. Pain Points Identified (Tutor Review)

| # | Pain | Explanation |
|---|------|-------------|
| 1 | **Uncontrolled instantiation** | Because the constructor is public, any class can create its own `PaymentGatewayConfig`. There is no limit on how many copies exist. |
| 2 | **Inconsistent state across the app** | `config1` has `isSandboxMode = true`, `config2` has `isSandboxMode = false`. They are different objects in memory. In a real system, PaymentService might send transactions to sandbox while OrderService sends them to production. |
| 3 | **No single source of truth** | If `defaultCurrency` changes from `"USD"` to `"EUR"`, you must find and update every instance individually. Miss one, and your app has mixed currencies. |
| 4 | **Memory waste** | If the config held heavy objects (connection pools, API clients, caches), creating 50 instances means 50 copies of the same data. |

**The core problem:** Payment gateway configuration should represent **one global setting**, but the naive code allows multiple independent copies with diverging state.

---

## 4. How the Singleton Pattern Was Born

Developers kept encountering this exact scenario: **"We need exactly one object of this class to coordinate actions across the system, but we keep accidentally creating multiple copies."**

Common real-world examples:
- **Application configuration** (your case)
- **Database connection pools**
- **Logger instances**
- **Print spoolers**
- **Hardware interface controllers** (a system has one printer, one serial port)

**Pattern:** Singleton (Creational)  
**Definition:** Ensure a class has only one instance and provide a global point of access to it.

---

## 5. The Cure: What Singleton Does (Conceptual — Not Yet Implemented by You)

Singleton restricts instantiation to exactly one object through:

1. **Private constructor** — prevents `new PaymentGatewayConfig()` from outside the class
2. **Private static field** — holds the single instance inside the class itself
3. **Public static `getInstance()` method** — returns the single instance, creates it lazily on first call if null
4. **Global access** — every part of the app calls `PaymentGatewayConfig.getInstance()` and gets the same object

Result:
```java
PaymentGatewayConfig config1 = PaymentGatewayConfig.getInstance();
PaymentGatewayConfig config2 = PaymentGatewayConfig.getInstance();
// config1 == config2  →  true (same object reference)
```

Change state on `config1`, and `config2` sees it — because they are identical.

---

## 6. Your Next Task (Part 2 — Pending)

**Folder:** `singleton:creational/pattern/`

### Create `PaymentGatewayConfig.java` as a Singleton:
1. **Private constructor** — `private PaymentGatewayConfig() { ... }`
2. **Private static instance field** — `private static PaymentGatewayConfig instance;`
3. **Public static `getInstance()` method** — returns the single instance, creates it on first call if null
4. Keep all existing fields and getters/setters

### Update `Main.java`:
1. Get the instance twice via `PaymentGatewayConfig.getInstance()`
2. Set `isSandboxMode = true` on the first reference
3. Print both references — both should show `true`
4. Print `config1 == config2` — should output `true`

---

## 7. Chronological Summary

1. ✅ Created naive `PaymentGatewayConfig` with public constructor
2. ✅ Created naive `Main` showing two instances with different `isSandboxMode` states
3. ✅ Pain identified: uncontrolled instantiation, inconsistent state, no single source of truth
4. ⏳ Part 2: Refactor into Singleton (private constructor, static `getInstance()`, shared instance)
5. ⏳ Verify `config1 == config2` is `true` and state is consistent

---

## 8. Key Takeaways (For Revision)

- **Singleton guarantees exactly one instance** of a class per JVM (per classloader).
- **Private constructor** is the gatekeeper — without it, anyone can bypass the singleton.
- **Static `getInstance()`** is the global access point.
- **Lazy initialization** — object is created only when first requested.
- **Use case:** When exactly one object is needed to coordinate actions or hold shared state (config, loggers, connection pools).

---

## 9. Questions to Consider Before Part 2

- Why must the constructor be `private`? What happens if it stays `public`?
- What does `config1 == config2` compare — object identity or object equality?
- Is this implementation thread-safe? (Consider this, but don't implement thread safety yet.)

---

## 10. Next Pattern (Pending Your Flag)

**Observer** — born from this pain: After checkout completes, you need to notify email, inventory, shipping, and analytics services. Having `Order` call all of them directly creates tight coupling and makes adding/removing notification targets painful.
