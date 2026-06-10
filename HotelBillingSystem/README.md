# Hotel Guest Billing System

## System Description
A hotel requires a subsystem to manage a guest's account balance. Guests can incur different types of charges during their stay, such as room rates and spa services. These items are completely unrelated structurally, but they all share the behavior of being "chargeable" to an account.

## Class Specification

### 1. Chargeable (Interface)
* **Package:** `za.co.wethinkcode.service`
* **Methods:**
    * `double calculateCharge()`
    * `String getChargeDescription()`

### 2. RoomBooking (Model Class)
* **Implements:** `Chargeable`
* **Fields:**
    * `roomNumber` (String)
    * `nights` (int)
    * `baseRate` (double)
* **Constructor:**
    * `RoomBooking(String roomNumber, int nights, double baseRate)`
    * Throws `IllegalArgumentException` if `roomNumber` is null or empty.
    * Throws `IllegalArgumentException` if `nights` is less than 1.
    * Throws `IllegalArgumentException` if `baseRate` is less than 0.0.
* **Methods:**
    * Standard getters for all fields.
    * `calculateCharge()`: Returns the total cost (`nights` multiplied by `baseRate`).
    * `getChargeDescription()`: Returns a strictly formatted string: `"Room [roomNumber] for [nights] nights"`.

### 3. SpaService (Model Class)
* **Implements:** `Chargeable`
* **Fields:**
    * `serviceName` (String)
    * `isPremium` (boolean)
* **Constructor:**
    * `SpaService(String serviceName, boolean isPremium)`
    * Throws `IllegalArgumentException` if `serviceName` is null or empty.
* **Methods:**
    * Standard getters for all fields.
    * `calculateCharge()`: Returns `150.0` if it is a premium service, and `75.0` if it is a standard service.
    * `getChargeDescription()`: Returns a strictly formatted string: `"[Premium/Standard] Spa Service: [serviceName]"`. (Use "Premium" if true, "Standard" if false).

### 4. GuestAccount (Service Class)
* **Fields:**
    * `accountId` (String)
    * `charges` (List of Chargeable objects)
* **Constructor:**
    * `GuestAccount(String accountId)`
    * Throws `IllegalArgumentException` if `accountId` is null or empty.
    * Initializes the internal list of charges.
* **Methods:**
    * `getAccountId()`: Returns the account ID.
    * `addCharge(Chargeable charge)`:
        * Throws `IllegalArgumentException` if `charge` is null.
        * Adds the charge to the account.
    * `getCharges()`: Returns a defensive copy of the charges list.
    * `getTotalBalance()`: Iterates through all added charges, sums their calculated costs, and returns the total as a double.

## Project Structure
```text
hospitality-app/
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
    │                   │   ├── RoomBooking.java
    │                   │   └── SpaService.java
    │                   └── service/
    │                       ├── Chargeable.java
    │                       └── GuestAccount.java
    └── test/
        └── java/
            └── za/
                └── co/
                    └── wethinkcode/
                        └── TestBillingSystem.java