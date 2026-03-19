encapsulation-practice-07
│
├── pom.xml
├── README.md
│
└── src
├── main
│   └── java
│       └── com
│           └── practice
│               └── Student.java
│
└── test
└── java
└── com
└── practice
└── StudentTest.java

# Encapsulation Practice 07

## Goal

Learn how to use collections (ArrayList) inside a class.

## Task

Implement a `Student` class.

---

## Fields

- name (String)
- scores (ArrayList<Double>)

Both must be **private**.

---

## Requirements

### Methods

setName(String name)
getName()

void addScore(double score)
ArrayList<Double> getScores()

double getAverageScore()


---

## Rules

- Scores must be between 0 and 100
- Invalid scores should NOT be added

---

## Behavior

### addScore

Adds a score to the list (if valid)

---

### getAverageScore

Returns the average of all scores

If no scores exist → return 0

---

## Important

Do not modify the tests.
