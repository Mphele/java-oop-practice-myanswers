# ⚙️ Legacy Fleet Manager — Java Theory & Practical Assessment

## Overview

FleetCo has just handed your team a **Vehicle Rental System** codebase that was written two years ago by a developer who has since left the company. Your job in this assessment is not to build it from scratch — it's to demonstrate that you understand the theory needed to **inherit, test, extend, and reason about** an existing Java OOP system as part of an Agile team.

This is a **practice assessment** and is predominantly **theory-based** (written/short-answer), with one small applied code-reading section. All code shown is Java.

### Learning Outcomes

- Testing
- Brownfields Development
- Agile Methodology
- OOP (Encapsulation, Inheritance, Polymorphism, Abstraction)
- Systems Design

---

## Time Limit

**2 hours 15 minutes**

---

## Assessment Structure

You may complete sections in any order.

| Component                                                        | Weight  | Recommended Time |
|-------------------------------------------------------------------|---------|-------------------|
| [Section A — OOP Theory](#section-a--oop-theory-20)               | **20%** | 25 minutes        |
| [Section B — Testing Theory](#section-b--testing-theory-20)       | **20%** | 25 minutes        |
| [Section C — Brownfields Development](#section-c--brownfields-development-20) | **20%** | 25 minutes |
| [Section D — Agile Methodology](#section-d--agile-methodology-15) | **15%** | 15 minutes        |
| [Section E — Systems Design](#section-e--systems-design-15)       | **15%** | 15 minutes        |
| [Section F — Applied Code Reading](#section-f--applied-code-reading-10) | **10%** | 20 minutes  |

### Scoring

```
Section Score = (marks earned / marks available) × section weight
Final Score   = Sum of all Section Scores
```

To pass, your Final Score must be **60% or higher**.

---

## Reference System

Sections C and F refer back to the `Vehicle`/`Booking`/`RentalAgency` classes from the Vehicle Rental System. Assume you have just inherited this codebase — you did **not** write it — and it currently has:

- No test suite.
- One long-lived `main` branch (no feature branches).
- A `RentalAgency` class that has grown to over 400 lines and now also sends confirmation emails, calculates loyalty points, and generates PDF invoices.

Keep this scenario in mind as you answer Sections C and F.

---

## Section A — OOP Theory (20%)

Answer in your own words. Java code examples are encouraged where useful.

1. **(4 marks)** Explain the difference between **encapsulation** and **abstraction**, using the `Vehicle` class as an example of each.
2. **(4 marks)** `Vehicle` declares `calculateRentalCost(int days)` as abstract. Explain why this method could not simply be a normal method with a default implementation, and what would break if `Vehicle` were not abstract at all.
3. **(4 marks)** Explain the difference between **method overloading** and **method overriding** in Java. Give one example of each that could plausibly appear in the `Car` or `Van` classes.
4. **(4 marks)** `Bakkie.isDoubleCab()` has no setter and the field is immutable. Explain, in terms of OOP design principles, why a developer might deliberately choose not to provide a setter for a field.
5. **(4 marks)** Compare **composition** and **inheritance** as relationships. Is the relationship between `RentalAgency` and `Vehicle` (via `fleet`) composition or inheritance? Justify your answer.
6. **(4 marks — practical)** Write the Java code for a new `Motorbike` class that extends `Vehicle`. It should have a private, immutable `engineCC` (`int`) field, a constructor accepting all required parameters, a getter `engineCC()`, and a `calculateRentalCost(int days)` implementation using a daily rate of `R300.00`, throwing `IllegalArgumentException` if `days < 1`.

---

## Section B — Testing Theory (20%)

1. **(3 marks)** Explain the **test pyramid** and where unit tests, integration tests, and system/end-to-end tests each sit within it.
2. **(4 marks)** `RentalAgencyTest.java` needs to test `book(String, String, int)`. Explain, using JUnit 5 terminology, what the **Arrange–Act–Assert (AAA)** pattern would look like for a test that confirms booking an unavailable vehicle throws `IllegalArgumentException`.
3. **(3 marks)** Explain the difference between a **mock** and a **stub**, and give one Java scenario from the Vehicle Rental System where a mock would be appropriate (hint: think about the email-sending behaviour mentioned in the Reference System above).
4. **(4 marks)** Explain what **code coverage** measures, and why 100% code coverage does **not** guarantee an absence of bugs.
5. **(3 marks)** Describe the **Red–Green–Refactor** cycle in Test-Driven Development (TDD).
6. **(3 marks)** `Vehicle.defineYear(int)` throws an exception for invalid years. Name and briefly describe the testing technique you would use to decide *which* invalid year values are worth testing (e.g. 1899, 1900, current year, current year + 1).
7. **(4 marks — practical)** Write an actual JUnit 5 test method, complete with `@Test` annotation and `assertThrows`, that verifies calling `agency.book("REG123", "John", 3)` throws `IllegalArgumentException` when the vehicle with that registration is already unavailable.

---

## Section C — Brownfields Development (20%)

1. **(4 marks)** Define **technical debt** and give two examples of technical debt that could plausibly exist in the inherited `RentalAgency` class described in the Reference System.
2. **(4 marks)** You must add a test suite to a codebase that currently has none. Explain what a **characterization test** is and why it is a recommended first step before refactoring legacy code.
3. **(4 marks)** Explain the difference between **trunk-based development** and **feature-branch development**. Give one advantage and one risk of trunk-based development for a team of five inheriting this codebase.
4. **(4 marks)** Identify and briefly explain **two code smells** that could apply to a `RentalAgency` class that has grown to 400+ lines and handles booking, email sending, loyalty points, and PDF generation.
5. **(4 marks)** You are asked to refactor `RentalAgency` without changing its external behaviour. Name this refactoring practice and explain, in 2–3 sentences, why maintaining **backward compatibility** matters when working on an inherited system that other teams depend on.
6. **(4 marks — practical)** Write the Java code to decouple email sending from `RentalAgency` using dependency injection: define a small `NotificationService` interface with a `send(String customer)` method, and show how `RentalAgency`'s constructor and `book()` method would change to use it instead of calling `sendConfirmationEmail()` directly.

---

## Section D — Agile Methodology (15%)

1. **(3 marks)** List the four Scrum ceremonies (events) and state the purpose of each in one sentence.
2. **(3 marks)** Write a well-formed **user story** (using the "As a ___, I want ___, so that ___" format) for a new feature: allowing a customer to extend an active booking's rental days.
3. **(3 marks)** Explain what a **Definition of Done (DoD)** is, and suggest two criteria that should be in the DoD for the user story you wrote above.
4. **(3 marks)** Compare **Scrum** and **Kanban** in terms of how work is scheduled and delivered.
5. **(3 marks)** Explain the purpose of **story point estimation**, and why teams often use a Fibonacci-like sequence (1, 2, 3, 5, 8, 13...) rather than linear numbers.
6. **(3 marks — practical)** Take the user story you wrote in Question 2 and break it into **three concrete acceptance criteria** written in Given/When/Then (Gherkin) format.

---

## Section E — Systems Design (15%)

1. **(3 marks)** In UML class diagrams, explain the difference between an **association** arrow and an **inheritance** (generalisation) arrow, and state which one connects `RentalAgency` to `Vehicle`.
2. **(4 marks)** Explain the **Single Responsibility Principle (SRP)** and how it applies to splitting the bloated `RentalAgency` class from the Reference System into smaller, focused classes. Propose a rough split into at least two classes.
3. **(4 marks)** Briefly describe the **Strategy design pattern** and explain how it could replace the current approach of using subclasses (`Car`, `Van`, `Truck`, `Bakkie`) with different `calculateRentalCost()` implementations. What would be the tradeoff of making this change?
4. **(4 marks)** Explain the difference between **cohesion** and **coupling**, and state whether a well-designed system should aim for high or low values of each.
5. **(3 marks — practical)** Write the Java code for the **Strategy pattern** replacement described in Question 3: define a `RentalPricingStrategy` interface with a `calculate(int days)` method, and show one implementing class (e.g. `CarPricingStrategy`) with the `R450.00` daily rate.

---

## Section F — Applied Code Reading (10%)

Below is a **simplified, deliberately flawed** snippet inspired by the inherited codebase. It is not production code — it is here for you to critique.

```java
public class RentalAgency {
    public List<Vehicle> fleet = new ArrayList<>();
    public List<Booking> bookings = new ArrayList<>();
    public int bookingCounter = 0;

    public Booking book(String reg, String customer, int days) {
        for (Vehicle v : fleet) {
            if (v.registration().equalsIgnoreCase(reg)) {
                Booking b = new Booking(bookingCounter, customer, v, days);
                bookingCounter = bookingCounter + 1;
                bookings.add(b);
                sendConfirmationEmail(customer);
                return b;
            }
        }
        return null;
    }

    public void sendConfirmationEmail(String customer) {
        // connects directly to SMTP server here
    }
}
```

1. **(2 marks)** Identify **one encapsulation violation** in this snippet and explain why it is a problem.
2. **(2 marks)** The method returns `null` if no vehicle is found, instead of throwing an exception (compare this to the original `RentalAgency.book()` spec). Explain why this is considered poor practice, and what a caller would have to do to use this method safely.
3. **(2 marks)** This method never checks `v.available()` before booking. Describe a **unit test** you would write to catch this bug, in plain English (no code required).
4. **(2 marks)** `sendConfirmationEmail()` connects directly to an SMTP server from inside `book()`. Explain why this makes the class **hard to unit test**, and what technique (from Section B) would let you test `book()` without actually sending an email.
5. **(2 marks)** Suggest one small, low-risk refactor you could make to this class today, consistent with brownfields best practice of making incremental, safe changes rather than a full rewrite.

---

## Submission

Answer all sections in a seperate file called `answers.md`, copying over the section headings. Where a question asks for Java code, paste your code blocks.

---
**NOTE:** This is a practice paper. There is no code to compile or push — focus on the quality and precision of your written answers.

*Good luck — and remember: legacy code is just code you don't have tests for yet. 🚗*
