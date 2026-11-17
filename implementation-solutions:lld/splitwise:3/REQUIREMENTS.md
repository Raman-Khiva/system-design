# Splitwise — Requirements

## Overview
Design and implement an object-oriented **expense-sharing system** (Splitwise-style). Users add expenses, split them among participants, and the system tracks **balances** (who owes whom). Keep it in-memory LLD — no app UI, no real payments.

Difficulty sits above LRU Cache (multiple entities + rules) and below movie booking (no seat locks / time windows).

---

## Functional Requirements

### 1. Users
- Each **User** has a unique user ID and a name (email optional).
- The system can register users and look them up by ID.
- Reject duplicate user IDs.

### 2. Expenses
- An **Expense** records money spent and how it is split.
- Each expense has:
  - Expense ID (unique)
  - Description (e.g. `"Dinner"`)
  - Total amount (positive)
  - **Paid by** — one user who paid the full amount (v1: single payer)
  - **Participants** — users involved in the split (must include or allow the payer; document your rule)
  - **Split type** (see below)
  - Created timestamp (optional but useful)
- Adding an expense must update balances immediately.

### 3. Split types (minimum three)
Support these strategies so split logic is not a giant `if-else` inside core code:

| Type | Behavior |
| --- | --- |
| **EQUAL** | Split total equally among participants. Define rounding (e.g. remainder goes to first participant, or use cents carefully). |
| **EXACT** | Each participant is assigned an exact share; shares must sum to total. |
| **PERCENT** | Each participant gets a percent; percents must sum to 100; shares derived from total. |

- Invalid splits (sum mismatch, empty participants, non-positive total) must fail with a clear error — expense not created, balances unchanged.

### 4. Balances
- Maintain a **balance sheet**: for each pair of users, net amount owed.
  - Example: if A paid ₹100 split equally with B, then **B owes A ₹50**.
- Prefer storing **net** balances (if B owes A 50 and later A owes B 20 → B owes A 30), not an ever-growing list of raw edges only.
- Queries:
  - Show balances for a **single user** (whom they owe / who owes them).
  - Show **all** non-zero balances in the system.
- Printing format example (any clear format is fine):
  - `B owes A: 50`
  - `C owes A: 25`

### 5. Groups (v1 — simple)
- A **Group** has an ID, name, and a set of member users.
- Expenses can optionally be tagged with a group (members should be the participants, or participants ⊆ members — pick one rule and enforce it).
- Query: list expenses in a group (if you store them), or at least ensure group membership is validated when adding a group expense.
- If this threatens flow, implement **non-group expenses first**, then add groups as a thin layer.

### 6. Settle up (simple)
- **Settle**: user A pays user B an amount toward reducing debt (or you support `settle(A, B)` that clears the net balance between A and B).
- After settle, balances update accordingly.
- Full “simplify debts across the whole graph” (minimize transactions) is **optional stretch**, not required for done.

---

## Non-Functional Requirements

- **Extensibility:** Adding a new split type (e.g. SHARES) should not rewrite expense creation or balance updates (Strategy / polymorphism is a natural fit — use it if it earns its place).
- **Maintainability:** Separate entities (User, Expense, Group, BalanceSheet) from services (ExpenseService, BalanceService, Split strategies).
- **Correctness over cleverness:** Rounding and sum checks must be explicit and tested in the driver.
- **No external dependencies:** Core Java only; in-memory storage.
- **Validation:** Unknown user IDs, bad split sums, negative amounts, empty participant lists.

---

## Constraints / Edge Cases to Handle

1. **EXACT shares ≠ total** → reject.
2. **PERCENT sum ≠ 100** → reject.
3. **EQUAL with 3 people, amount not divisible** → define rounding; balances must still sum correctly to total.
4. **Participant not a registered user** → reject.
5. **Payer not in participants** — either allow (payer paid for others) or reject; document and stick to it. Recommended: **allow** (common Splitwise case).
6. **Self-only expense** (single participant = payer) → all shares 0 owed; balance unchanged (or reject — document).
7. **Settle more than owed** → reject or cap; document.

---

## Expected Deliverables (from you)

1. **Java** code with clear classes/interfaces.
2. Short **design note**: key classes, how balances are updated on add expense, how split types are modeled.
3. A **`main()` / driver** that:
   - Creates users (e.g. A, B, C, D).
   - Adds an EQUAL expense (A paid 1000 for A,B,C,D).
   - Adds an EXACT expense with valid shares.
   - Adds a PERCENT expense with valid percents.
   - Prints balances after each (or at the end).
   - Shows at least one **rejected** expense (bad EXACT or PERCENT sum).
   - Demonstrates a simple **settle** between two users and prints balances again.

---

## Out of Scope (for now)

- Real payments, UPI, reminders, notifications.
- Persistence, auth, multi-currency.
- Recurring expenses, itemized bills, comments.
- Global debt simplification algorithm (stretch only).
- Mobile/web UI.

---

## Suggested start order (keep flow)

1. User registry + balance sheet (`A owes B` net amounts).
2. EQUAL expenses only + print balances.
3. EXACT and PERCENT via separate split strategies + validation.
4. Settle between two users.
5. Groups if energy remains; otherwise leave a clean hook.

---

Movie booking and parking lot stay parked. LRU was the warm-up; this is the next progressive step.

Good luck — implement, then we’ll review.
