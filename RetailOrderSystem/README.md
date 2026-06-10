# Retail Order Lifecycle System

## System Description
An online retailer needs to track the lifecycle of customer orders. An order moves through a strict sequence of states. It cannot skip states, and it cannot move backwards. You must model this lifecycle using an Enum and enforce the state transitions strictly.

## Class Specification

### 1. OrderStatus (Enum)
* **Values:** `NEW`, `PROCESSING`, `SHIPPED`, `DELIVERED`

### 2. Order (Service Class)
* **Fields:**
    * `orderId` (String)
    * `status` (OrderStatus)
* **Constructor:**
    * `Order(String orderId)`
    * Throws `IllegalArgumentException` if `orderId` is null or empty.
    * Initializes the `status` strictly to `OrderStatus.NEW`.
* **Methods:**
    * `getOrderId()`: Returns the order ID.
    * `getStatus()`: Returns the current status.
    * `processOrder()`:
        * Throws `IllegalStateException` if the current status is not `NEW`.
        * Otherwise, changes the status to `PROCESSING`.
    * `shipOrder()`:
        * Throws `IllegalStateException` if the current status is not `PROCESSING`.
        * Otherwise, changes the status to `SHIPPED`.
    * `deliverOrder()`:
        * Throws `IllegalStateException` if the current status is not `SHIPPED`.
        * Otherwise, changes the status to `DELIVERED`.

## Project Structure
```text
retail-app/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/
    │   └── java/
    │       └── za/
    │           └── co/
    │               └── wethinkcode/
    │                   ├── Main.java
    │                   ├── model/
    │                   │   └── OrderStatus.java
    │                   └── service/
    │                       └── Order.java
    └── test/
        └── java/
            └── za/
                └── co/
                    └── wethinkcode/
                        └── TestOrder.java