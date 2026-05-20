# 🏥 Hospital Ward Management System — Java OOP Assessment

## Overview

In this assessment you will design and implement a hospital ward management system in Java. The system manages patient admissions, maintains medical records, and processes patient appointments through a queue-based scheduling system — all modelled using core OOP design principles.

The skeleton project is provided. Your task is to implement the classes described in the steps below, following the principles of **Encapsulation**, **Inheritance**, and **Interfaces** as taught in the Java-OOP Modules.

### Learning Outcomes

- OOP (Encapsulation, Inheritance, Interfaces, Abstract Classes)
- Greenfield Development
- Unit testing
- System Design

---

## Time Limit

**3 hours**

---

## Assessment Structure

This assessment has three components. You may complete them in any order.

| Component | Weight | Recommended Time |
|---|---|---|
| Implementation | **60%** | 2 hours |
| UML Class Diagram | **25%** | 45 minutes |
| Long Question | **15%** | 15 minutes |

### Scoring

```
Coding Score  = (tests passed / total tests) × 60%
UML Score     = (marks earned / total UML marks) × 25%
Long Q Score  = (marks earned / 10) × 15%

Final Score   = Coding Score + UML Score + Long Q Score
```

To pass, your Final Score must be **60% or higher**.

---

## Project Setup

Before you begin coding, create the following files in the project root:

- `.gitignore` — add at minimum `target/` and `.idea/`
- `answer.txt` — this is where you will write your long question answers; use the format shown in the Long Question section and do not remove the comment markers

---

## Project Structure

The four test files are already provided. You must create **all** `src/main/` class files yourself in the correct packages.

```
hospital-ward-system/
├── pom.xml
├── README.md
├── answer.txt                               ← you will create this
├── .gitignore                               ← you will create this
└── src/
    ├── main/
    │   └── java/
    │       └── za/
    │           └── co/
    │               └── wethinkcode/
    │                   ├── Main.java                        ← you will create this
    │                   ├── model/
    │                   │   ├── Patient.java                 ← you will create this
    │                   │   ├── MedicalRecord.java           ← you will create this
    │                   │   └── Appointment.java             ← you will create this
    │                   └── service/
    │                       ├── Billable.java                ← you will create this
    │                       ├── Ward.java                    ← you will create this
    │                       ├── GeneralWard.java             ← you will create this
    │                       └── ICU.java                     ← you will create this
    └── test/
        └── java/
            └── za/
                └── co/
                    └── wethinkcode/
                        ├── TestPatient.java                 ✓ provided
                        ├── TestMedicalRecord.java           ✓ provided
                        ├── TestAppointment.java             ✓ provided
                        └── TestWard.java                    ✓ provided
```

---

As you implement each step, run the full test suite:

```bash
mvn clean compile test
```

Your goal is to reach **100% of tests passing** before submission.

---

## Implementation Steps

Work through the steps **in order** — later classes depend on earlier ones.

---

### Step 1 — Implement `Patient`

**Create file:** `src/main/java/za/co/wethinkcode/model/Patient.java`

The `Patient` class represents an individual admitted to a hospital ward.

#### Fields

| Field | Type | Details |
|---|---|---|
| `patientId` | `String` | A unique identifier for the patient (e.g. `"P001"`). Must be **private**. |
| `fullName` | `String` | The patient's full name. Must be **private**. |
| `age` | `int` | The patient's age in years. Must be **private**. |

#### Constructor

Accepts `patientId` (String), `fullName` (String), and `age` (int). Assigns all fields. Throws `IllegalArgumentException` if `age` is **negative** or **greater than 150**.

#### Methods

| Method | Return Type | Details |
|---|---|---|
| `patientId()` | `String` | Returns the patient's ID. |
| `fullName()` | `String` | Returns the patient's full name. |
| `age()` | `int` | Returns the patient's age. |
| `updateAge(int)` | `void` | Updates the age. Throws `IllegalArgumentException` if the new value is negative or greater than 150. |
| `toString()` | `String` | Returns a readable summary that includes `patientId`, `fullName`, and `age`. |

---

### Step 2 — Implement `MedicalRecord`

**Create file:** `src/main/java/za/co/wethinkcode/model/MedicalRecord.java`

A `MedicalRecord` is linked to a patient by their ID and holds two separate lists — diagnoses and prescriptions. It must protect both internal lists from outside mutation.

#### Fields

| Field | Type | Details |
|---|---|---|
| `patientId` | `String` | The ID of the patient this record belongs to. Must be **private**. |
| `diagnoses` | `List<String>` | A list of diagnosis descriptions. Must be **private**. |
| `prescriptions` | `List<String>` | A list of prescription descriptions. Starts **empty**. Must be **private**. |

#### Constructor

Accepts `patientId` (String) and `diagnoses` (List\<String\>). Store a **mutable copy** of the provided diagnoses list — do not store the reference directly. Initialise `prescriptions` as a new, empty `ArrayList`.

#### Methods

| Method | Return Type | Details |
|---|---|---|
| `patientId()` | `String` | Returns the patient ID. |
| `diagnoses()` | `List<String>` | Returns a **defensive copy** of the diagnoses list. |
| `prescriptions()` | `List<String>` | Returns a **defensive copy** of the prescriptions list. |
| `addDiagnosis(String)` | `void` | Appends the given diagnosis to the internal list. |
| `addPrescription(String)` | `void` | Appends the given prescription to the internal list. |
| `toString()` | `String` | Returns a readable summary containing `patientId`, all diagnoses, and all prescriptions. |

> **Design Tip — Mutable Copy in Constructor:** If the constructor stored the caller's list directly, the caller could still modify the list after construction and silently alter your record. Always copy it: `this.diagnoses = new ArrayList<>(diagnoses)`.

> **Design Tip — Defensive Copy in Getters:** `diagnoses()` and `prescriptions()` should each return `new ArrayList<>(...)`. If you return the internal list directly, any caller can call `.add()` or `.clear()` on it and corrupt your object's state without you knowing.

---

### Step 3 — Implement `Appointment`

**Create file:** `src/main/java/za/co/wethinkcode/model/Appointment.java`

An `Appointment` links a `Patient` to an attending doctor and tracks the current state of the consultation through a lifecycle using an enum.

#### Enum — `AppointmentStatus`

Define the following `public` enum **inside** the `Appointment` class:

```java
public enum AppointmentStatus {
    SCHEDULED,
    IN_CONSULTATION,
    COMPLETED,
    CANCELLED
}
```

#### Fields

| Field | Type | Details |
|---|---|---|
| `appointmentId` | `int` | Unique numeric ID for this appointment. Must be **private**. |
| `patient` | `Patient` | The patient for this appointment. Must be **private**. |
| `doctorName` | `String` | The name of the attending doctor. Must be **private**. |
| `status` | `AppointmentStatus` | Current lifecycle status. Defaults to `SCHEDULED`. Must be **private**. |

#### Constructor

Accepts `appointmentId` (int), `patient` (Patient), and `doctorName` (String). Sets `status` to `AppointmentStatus.SCHEDULED`.

#### Methods

| Method | Return Type | Details |
|---|---|---|
| `appointmentId()` | `int` | Returns `appointmentId`. |
| `patient()` | `Patient` | Returns the `Patient`. |
| `doctorName()` | `String` | Returns `doctorName`. |
| `status()` | `AppointmentStatus` | Returns the current `AppointmentStatus`. |
| `updateStatus(AppointmentStatus)` | `void` | Replaces the current status with the given one. |
| `toString()` | `String` | Returns a summary including `appointmentId`, the patient's full name, `doctorName`, and `status`. |

---

### Step 4 — Implement the `Billable` Interface

**Create file:** `src/main/java/za/co/wethinkcode/service/Billable.java`

`Billable` is a **Java interface** that any ward which charges for appointments must implement. It defines the contract for calculating an appointment cost.

#### Method

| Method | Return Type | Details |
|---|---|---|
| `calculateBill(int durationMinutes)` | `double` | Returns the total cost in Rands for an appointment of the given duration. |

> **Design Note:** `Billable` is kept separate from `Ward` deliberately. Not every ward type necessarily needs to be billable — a research ward or a trial ward may operate differently. By extracting this into an interface, only the wards that charge patients need to implement it, and the billing contract remains independent of the ward hierarchy. This is the **Interface Segregation** principle in practice.

---

### Step 5 — Implement `Ward` (Abstract Class)

**Create file:** `src/main/java/za/co/wethinkcode/service/Ward.java`

`Ward` is an **abstract base class** that manages patient admissions and appointment scheduling. It delegates the ward-specific treatment step to its concrete subclasses through an abstract method.

#### Fields

| Field | Type | Details |
|---|---|---|
| `wardName` | `String` | The ward's display name. Must be **private**. |
| `capacity` | `int` | Maximum number of patients the ward can hold at any one time. Must be **private**. |
| `patients` | `Map<String, Patient>` | Maps patient IDs to `Patient` objects for the currently admitted patients. Must be **private**. |
| `appointmentQueue` | `List<Appointment>` | An ordered list of all scheduled appointments. Must be **private**. |
| `appointmentCounter` | `int` | Starts at `0`; incremented each time a new appointment is scheduled. Must be **private**. |

#### Constructor

Accepts `wardName` (String) and `capacity` (int). Initialises `patients` as a new `HashMap` and `appointmentQueue` as a new `ArrayList`.

#### Concrete Methods (fully implement these)

| Method | Return Type | Details |
|---|---|---|
| `admitPatient(Patient)` | `void` | Adds the patient to the map using their `patientId` as the key. Throws `IllegalStateException` if the ward is already at capacity. Throws `IllegalArgumentException` if the patient is already admitted. |
| `dischargePatient(String patientId)` | `void` | Removes the patient with the given ID from the map. Throws `IllegalArgumentException` if no patient with that ID is currently admitted. |
| `getPatient(String patientId)` | `Patient` | Returns the `Patient` for the given ID, or `null` if not found. |
| `getAllPatients()` | `Map<String, Patient>` | Returns an **unmodifiable** view of the patients map. |
| `isFull()` | `boolean` | Returns `true` if the number of admitted patients is greater than or equal to `capacity`. |
| `scheduleAppointment(String patientId, String doctorName)` | `Appointment` | Creates a new `Appointment` with `appointmentId = ++appointmentCounter`, adds it to the queue, and returns it. Throws `IllegalArgumentException` if the patient is not currently admitted. |
| `processNextAppointment()` | `Appointment` | Finds the first `SCHEDULED` appointment in the queue. Sets its status to `IN_CONSULTATION`, calls `treat(appointment)`, then sets status to `COMPLETED`, and returns it. Returns `null` if there are no `SCHEDULED` appointments in the queue. |
| `appointmentQueue()` | `List<Appointment>` | Returns an **unmodifiable** view of the appointment queue. |
| `wardName()` | `String` | Returns `wardName`. |
| `capacity()` | `int` | Returns `capacity`. |

#### Abstract Method

```java
protected abstract void treat(Appointment appointment);
```

Subclasses must override `treat()` to provide ward-specific treatment output. This method is called automatically inside `processNextAppointment()` — you do not call it directly.

---

### Step 6 — Implement `GeneralWard` & `ICU`

**Create files:** `src/main/java/za/co/wethinkcode/service/GeneralWard.java`
and `src/main/java/za/co/wethinkcode/service/ICU.java`

These two concrete classes each extend `Ward` **and** implement `Billable`. Each provides ward-specific treatment output and its own billing rate.

#### GeneralWard

| | Details |
|---|---|
| Extends | `Ward` |
| Implements | `Billable` |
| Constructor | Accepts `wardName` (String) and `capacity` (int); passes both to `super()`. |
| `treat(Appointment)` | Prints: `"[wardName] treating [patientFullName] with Dr [doctorName] using standard general care protocols."` |
| `calculateBill(int durationMinutes)` | Charges at **R500 per hour**. Formula: `500.0 * durationMinutes / 60.0` |

#### ICU

| | Details |
|---|---|
| Extends | `Ward` |
| Implements | `Billable` |
| Constructor | Accepts `wardName` (String) and `capacity` (int); passes both to `super()`. |
| `treat(Appointment)` | Prints: `"[wardName] treating [patientFullName] with Dr [doctorName] using intensive monitoring and life support."` |
| `calculateBill(int durationMinutes)` | Charges at **R2500 per hour**. Formula: `2500.0 * durationMinutes / 60.0` |

---

### Step 7 — Create `Main.java`

**Create file:** `src/main/java/za/co/wethinkcode/Main.java`

`Main` is the entry point of the application. It does not need to be complex — its purpose is to demonstrate that your classes work together. Create a `main` method in the `za.co.wethinkcode` package that:

1. Creates a `GeneralWard` and an `ICU`
2. Admits at least one patient to each
3. Schedules at least one appointment per ward
4. Processes those appointments and prints the result

`Main` is not tested, but the project must compile. Running `mvn clean compile` must succeed before you submit.

---

## UML Class Diagram

Produce a UML class diagram for this project using [draw.io](https://draw.io). No other tool is allowed.

Your diagram must include all **seven** types (`Patient`, `MedicalRecord`, `Appointment`, `Billable`, `Ward`, `GeneralWard`, `ICU`) and show the following for each:

- Class or interface name — mark interfaces with the `«interface»` stereotype
- All fields with their types and access modifiers (`+` public, `-` private, `#` protected)
- All methods with their return types and parameters
- Relationships between types:
  - **Inheritance** (solid arrow with hollow head) where one class extends another
  - **Realisation** (dashed arrow with hollow head) where a class implements an interface
  - **Association** (solid arrow) where one class holds a reference to another — label it with the field name and multiplicity where relevant

Export your diagram as a **PNG or PDF** and place it in the root of your project named `uml.pdf`.

---

## Long Question

Answer both parts of this question in `answer.txt`. Use the format below exactly — do not remove the comment markers or change the structure.

```
# Long Question — Design Justification
# Do not remove these comments or change the format.
# -------------------------------------------------------

(a) [your answer here]

(b) [your answer here]

# -------------------------------------------------------
```

### Question — Design Justification (10 marks)

This system uses both an abstract class (`Ward`) and an interface (`Billable`) to model different aspects of behaviour.

**(a)** Explain why `Ward` is implemented as an **abstract class** rather than an interface. What does the abstract class provide that an interface alone could not? Reference at least two specific aspects of the `Ward` implementation in your answer. *(5 marks)*

**(b)** Explain why billing behaviour is modelled as a **separate interface** (`Billable`) rather than as a concrete or abstract method placed directly inside `Ward`. Under what future scenario could you imagine a new ward type where keeping `calculateBill` inside `Ward` would cause a design problem? *(5 marks)*

---

**NOTE:** Before pushing your solution for grading, make sure it compiles. You can check this by running:

```bash
mvn clean compile
```

*Good luck — may your queues stay short and your wards stay bug-free. 🏥*
