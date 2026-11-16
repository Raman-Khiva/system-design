# Parking Lot — Requirements

## Overview
Design and implement an object-oriented **Parking Lot Management System**. The system manages parking for multiple vehicle types across multiple levels and spots, handles ticket issuance, parking, unparking, and fee calculation.

---

## Functional Requirements

### 1. Vehicle Management
- Support multiple vehicle types: **Two-Wheeler**, **Four-Wheeler (Car)**, **Truck** (and any future vehicle type without modifying existing code).
- Each vehicle has a unique **license plate number** and a vehicle type.
- The system must validate that a vehicle cannot be parked twice simultaneously (same license plate cannot have an active ticket in two spots).

### 2. Parking Spot Management
- Spots are organized by **Level** (floor), each level has a fixed number of spots.
- Each spot has a **Spot Number**, a **Level**, and a **Spot Size** (e.g., Compact, Large, Truck).
- Spot sizes have a compatibility rule:
  - A Compact spot fits only Two-Wheelers (and possibly Compact cars if specified).
  - A Large spot fits Two-Wheelers, Cars, and Compact cars.
  - A Truck spot fits everything (including Trucks).
  *(You may define your own size matrix, but document it clearly.)*
- Spot availability must be tracked in real time.

### 3. Ticketing
- When a vehicle enters, the system issues a **Ticket** containing:
  - Ticket ID (unique)
  - License plate
  - Vehicle type
  - Assigned spot (level + spot number)
  - Entry timestamp
- A ticket is **active** until the vehicle unparks.
- Only one active ticket per license plate at any time.

### 4. Parking / Unparking
- **Park**: Given a vehicle, find the nearest available compatible spot (prefer closest to entrance/exit), assign it, mark it occupied, issue a ticket.
- **Unpark**: Given a ticket ID (or license plate), release the spot, mark it available, calculate and return the parking fee, close the ticket.

### 5. Pricing / Fee Calculation
- Fee is based on **vehicle type** and **duration parked**.
- Support at least two pricing models (e.g., hourly rate and daily maximum).
  - Example: Car = ₹30/hour (max ₹300/day), Truck = ₹50/hour (max ₹500/day), Two-Wheeler = ₹15/hour (max ₹100/day).
- Rounding rule: any partial hour is charged as a full hour (ceiling).
- The system should allow pricing rules to be changed without modifying core parking logic (Open/Closed Principle).

### 6. Queries / Reporting (at least these)
- Total number of free spots per level.
- Total number of occupied spots per level.
- Total number of free/occupied spots by vehicle type across the lot.
- Is a specific spot available?

### 7. Multiple Lots (Optional but recommended)
- The design should support multiple independent parking lots (each with its own levels, spots, pricing), identifiable by a Lot ID.

---

## Non-Functional Requirements

- **Extensibility**: Adding a new vehicle type or spot size should not require changing existing parking logic (Open/Closed Principle).
- **Maintainability**: Clear separation between entities (Vehicle, Spot, Ticket, Level, Lot, PricingService) and services.
- **Concurrency awareness**: In a real scenario, two cars could try to park the same last spot simultaneously. Your code should be thread-safe or at least discuss how you'd handle it (synchronization, locks, atomic spot assignment).
- **No external dependencies**: Implement using core language features only (no databases, no frameworks). In-memory storage is fine.
- **Input validation**: Reject invalid operations (parking an already-parked vehicle, unparking an invalid/expired ticket, parking a truck in a compact spot).

---

## Constraints / Edge Cases to Handle

1. **No compatible spot available**: Parking should fail gracefully with a clear message (e.g., "No large spot available for Truck").
2. **Vehicle already parked**: Reject with "Vehicle with license plate XYZ is already parked."
3. **Invalid ticket**: Unparking with a non-existent or already-closed ticket should fail.
4. **License plate uniqueness**: Treat license plate as the unique identifier for an active vehicle.
5. **Spot released properly**: After unpark, the spot must become available for the next vehicle.
6. **Zero-duration parking**: If entry and exit are the same timestamp, charge minimum fee (e.g., one hour or the defined minimum).

---

## Expected Deliverables (from you)

1. **Code** in your preferred language (Java, C++, Python, C#, etc.) with classes/interfaces.
2. A brief **design note** explaining:
   - Class diagram / key classes and their responsibilities.
   - Which design patterns you used (Strategy for pricing, Factory for spot/vehicle creation, Singleton for the lot, etc.) and why.
   - How you handled spot assignment (nearest available compatible spot).
3. A **main() / driver** demonstrating:
   - Creating a lot with levels and spots.
   - Parking multiple vehicles of different types.
   - Attempting an invalid park (wrong spot size / already parked).
   - Unparking and seeing the fee.
   - Querying free/occupied spots.

---

## Out of Scope (for now)

- Payment gateway integration.
- Persistence (database/files).
- Camera/LPR (license plate recognition) — license plate is provided as input.
- Multi-lot centralized management API (though code structure should allow it).

---

Good luck! Write your solution, then we'll review it together and improve it.