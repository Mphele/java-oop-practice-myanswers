# Sports Team Roster Management

## System Description
A sports club requires a system to manage their team rosters. Each team has a strict maximum squad size limit. The system must enforce squad capacity rules, prevent duplicate jersey numbers on the same team, and provide filtered views of the players (such as identifying players who are currently match-fit and not injured).

## Class Specification

### 1. Player (Model Class)
* **Fields:**
    * `jerseyNumber` (int)
    * `name` (String)
    * `isInjured` (boolean)
* **Constructor:**
    * `Player(int jerseyNumber, String name, boolean isInjured)`
    * Throws `IllegalArgumentException` if `name` is null or empty.
    * Throws `IllegalArgumentException` if `jerseyNumber` is less than 1.
* **Methods:**
    * Standard getters for all fields: `getJerseyNumber()`, `getName()`, `isInjured()`.
    * `equals(Object obj)`: Two players are considered equal if they have the exact same `jerseyNumber`.
    * `hashCode()`: Override to match `equals` logic.

### 2. Team (Service Class)
* **Fields:**
    * `teamName` (String)
    * `maxSquadSize` (int)
    * `roster` (List of Player objects)
* **Constructor:**
    * `Team(String teamName, int maxSquadSize)`
    * Throws `IllegalArgumentException` if `teamName` is null or empty.
    * Throws `IllegalArgumentException` if `maxSquadSize` is less than 1.
    * Initializes the internal player collection.
* **Methods:**
    * `getTeamName()`: Returns the team name.
    * `getMaxSquadSize()`: Returns the total capacity.
    * `getCurrentSquadSize()`: Returns the current number of signed players.
    * `signPlayer(Player player)`:
        * Throws `IllegalArgumentException` if the `player` is null.
        * Throws `IllegalArgumentException` if a player with the same `jerseyNumber` is already on the team.
        * Throws `IllegalStateException` if the team is already at maximum squad size.
        * Otherwise, adds the player to the roster.
    * `getAllPlayers()`: Returns a defensive copy of the roster list.
    * `getAvailablePlayers()`: Returns a new List containing only the players where `isInjured()` is false.

## Project Structure
```text
sports-app/
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
    │                   │   └── Player.java
    │                   └── service/
    │                       └── Team.java
    └── test/
        └── java/
            └── za/
                └── co/
                    └── wethinkcode/
                        └── TestTeam.java