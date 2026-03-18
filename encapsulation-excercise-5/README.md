# Encapsulation Practice 05

## Goal

Practice using constructors to initialize objects.

## Task

Implement a `Person` class with the following private fields:

- name
- age

---

## Constructors

Implement the following constructors:

### Default Constructor

Person()


Creates a person with:

name = "Unknown"  
age = 0

---

### Parameterized Constructor
Person(String name, int age)


Creates a person with the provided name and age.

Age must still follow the validation rule:

- age must be between **0 and 130**

If the provided age is invalid, store **0 instead**.

---

## Required Methods

Creates a person with the provided name and age.

Age must still follow the validation rule:

- age must be between **0 and 130**

If the provided age is invalid, store **0 instead**.

---

## Required Methods
getName()
setName(String name)

getAge()
setAge(int age)

boolean isAdult()


### `isAdult()`

Returns `true` if age ≥ 18.

---

## Important

Do not modify the tests.

Your implementation should pass all tests.