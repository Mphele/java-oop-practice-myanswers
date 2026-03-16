# Encapsulation Practice 04

## Goal

Practice writing behavior methods that use class fields.

## Task

Implement a `Person` class with the following fields:

- name
- age
- height (in meters)
- weight (in kilograms)

All fields must be **private**.

Implement getters and setters for each field.

---

## New Methods

Implement the following methods:

### `boolean isAdult()`

Returns `true` if the person is **18 or older**, otherwise `false`.

---

### `double calculateBMI()`

BMI formula:

BMI = weight / (height * height)

Example:

weight = 70kg  
height = 1.75m

BMI = 70 / (1.75 * 1.75)

---

## Validation Rules

Age:
- must be between 0 and 130

Height:
- must be greater than 0

Weight:
- must be greater than 0

Invalid values should **not change the stored value**.

---

## Important

Do not modify the tests.