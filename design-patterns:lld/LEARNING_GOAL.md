# Design Patterns — Learn by Doing

## Goal

Learn design patterns inductively, not by memorization.

The method:
1. **Build something naive** — just make it work.
2. **Feel the pain** — tight coupling, fragile code, hard to extend, duplicated conditionals, etc.
3. **See the pattern emerge** — the pattern is the cure for that specific pain.
4. **Refactor** — apply the pattern, then discuss trade-offs.
5. **Repeat** — next assignment builds on the previous insight.

---

## Language

**Java**

---

## Current Assignment: Payment Processing (Assignment #1)

**Scenario:** An `Order` needs to process payments using different methods (Credit Card, PayPal, Crypto).

**Requirements:**
- Support at least 3 payment methods (`credit_card`, `paypal`, `crypto`)
- Each method should print/log something like `Processing $X via Credit Card`
- Should be callable like `order.checkout("paypal", 100)`

**Expected deliverable:** A Java class (or classes) that makes this work. No patterns yet — just make it functional.

---

## Patterns Covered (to be filled in as we go)

| # | Pattern | Pain it solves | Status |
|---|---------|---------------|--------|
| 1 | Strategy | `if/else` or `switch` on payment type inside `Order` | ⬜ Pending |
| 2 | | | ⬜ |
| 3 | | | ⬜ |

---

## Notes for Java

- Prefer interfaces over concrete classes where behavior varies.
- Keep classes small and focused (Single Responsibility).
- Use `System.out.println` for logging unless you want to wire up a real logger.
- Package structure suggestion: `com.learning.patterns.strategy` (or whatever fits the current pattern).

---

## How to proceed

1. Write your Java solution for Assignment #1.
2. Paste it here.
3. I'll review it, tell you what's wrong, and introduce the pattern born from that pain.
4. We refactor together, update this file, and move to Assignment #2.
