# Encapsulation Practice 06

## Goal

Understand static fields and how they are shared across all objects.

## Task

Implement a `Person` class with:

### Instance fields (private)
- name
- age

### Static field (private)
- totalPeople

---

## Requirements

### Constructors

Implement:
Person()
Person(String name, int age)


Each time a `Person` object is created, the static variable `totalPeople` must increase by 1.

---

## Methods

Each time a `Person` object is created, the static variable `totalPeople` must increase by 1.

---

## Methods

---

## Rules

Age must still:
- be between 0 and 130
- invalid values should not change the value

---

## Important

Do not modify the tests.