# Library Asset Management

## System Description
A university library holds various assets. Every asset shares basic properties, but their physical availability rules differ. Books can be physically borrowed and taken home, altering their status. Reference Journals, however, must be read inside the library and can never be checked out. You must use interface segregation to ensure that only the correct assets have borrowing capabilities.

## Class Specification

### 1. ItemStatus (Enum)
* **Package:** `za.co.wethinkcode.model`
* **Values:** `AVAILABLE`, `CHECKED_OUT`

### 2. Borrowable (Interface)
* **Package:** `za.co.wethinkcode.service`
* **Methods:**
    * `void checkout()`
    * `void returnItem()`

### 3. LibraryItem (Abstract Class)
* **Package:** `za.co.wethinkcode.model`
* **Fields:**
    * `itemId` (String) - private
    * `title` (String) - private
    * `status` (ItemStatus) - protected (so subclasses can modify it directly)
* **Constructor:**
    * `LibraryItem(String itemId, String title)`
    * Throws `IllegalArgumentException` if `itemId` or `title` is null or empty.
    * Initializes the `status` strictly to `ItemStatus.AVAILABLE`.
* **Methods:**
    * Standard getters for `itemId`, `title`, and `status`.

### 4. Book (Concrete Class)
* **Package:** `za.co.wethinkcode.model`
* **Extends:** `LibraryItem`
* **Implements:** `Borrowable`
* **Constructor:**
    * `Book(String itemId, String title)`
* **Methods:**
    * `checkout()`:
        * Throws `IllegalStateException` if the current status is NOT `AVAILABLE`.
        * Otherwise, changes the status to `CHECKED_OUT`.
    * `returnItem()`:
        * Throws `IllegalStateException` if the current status is NOT `CHECKED_OUT`.
        * Otherwise, changes the status to `AVAILABLE`.

### 5. ReferenceJournal (Concrete Class)
* **Package:** `za.co.wethinkcode.model`
* **Extends:** `LibraryItem`
* *(Does NOT implement Borrowable)*
* **Fields:**
    * `floorNumber` (int) - private
* **Constructor:**
    * `ReferenceJournal(String itemId, String title, int floorNumber)`
    * Throws `IllegalArgumentException` if `floorNumber` is less than 1.
* **Methods:**
    * `getFloorNumber()`: Returns the floor number.

## Project Structure
```text
library-app/
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
    │                   │   ├── ItemStatus.java
    │                   │   ├── LibraryItem.java
    │                   │   ├── Book.java
    │                   │   └── ReferenceJournal.java
    │                   └── service/
    │                       └── Borrowable.java
    └── test/
        └── java/
            └── za/
                └── co/
                    └── wethinkcode/
                        └── TestLibraryAssets.java