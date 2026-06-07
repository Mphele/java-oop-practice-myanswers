# Logistics Task Processing System

## System Description
A logistics company requires a system to process delivery tasks. Every delivery task has shared properties such as a tracking ID, distance in kilometres, and a list of checkpoints it has passed through. However, the cost calculation differs depending on the priority of the task.

This drill requires the implementation of a Tier 3 inheritance hierarchy to model these delivery tasks.

## Class Specification

### 1. DeliveryTask (Abstract Class)
* **Fields:**
    * `trackingId` (String)
    * `distanceKm` (double)
    * `checkpoints` (List of Strings)
* **Constructor:**
    * `DeliveryTask(String trackingId, double distanceKm)`
    * Throws `IllegalArgumentException` if `trackingId` is null or empty.
    * Throws `IllegalArgumentException` if `distanceKm` is less than or equal to 0.
    * Initializes `checkpoints` as an empty collection.
* **Methods:**
    * `getTrackingId()`: Returns the tracking ID.
    * `getDistanceKm()`: Returns the distance.
    * `addCheckpoint(String checkpoint)`: Throws `IllegalArgumentException` if the checkpoint is null or empty. Otherwise, adds it to the list.
    * `getCheckpoints()`: Returns a defensive copy of the checkpoints list.
    * `calculateCost()`: Abstract method returning a double.

### 2. StandardTask (Concrete Class)
* **Extends:** `DeliveryTask`
* **Constructor:**
    * `StandardTask(String trackingId, double distanceKm)`
* **Methods:**
    * `calculateCost()`: Returns the cost calculated as `distanceKm` multiplied by a flat rate of `15.0`.

### 3. ExpressTask (Concrete Class)
* **Extends:** `DeliveryTask`
* **Fields:**
    * `requiresRefrigeration` (boolean)
* **Constructor:**
    * `ExpressTask(String trackingId, double distanceKm, boolean requiresRefrigeration)`
* **Methods:**
    * `calculateCost()`: Returns the cost calculated as `distanceKm` multiplied by an express rate of `25.0`. If `requiresRefrigeration` is true, an additional flat fee of `100.0` is added to the final cost.

## Project Structure
```text
logistics-app/
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
    │                   └── service/
    │                       ├── DeliveryTask.java
    │                       ├── StandardTask.java
    │                       └── ExpressTask.java
    └── test/
        └── java/
            └── za/
                └── co/
                    └── wethinkcode/
                        └── TestDeliveryTask.java