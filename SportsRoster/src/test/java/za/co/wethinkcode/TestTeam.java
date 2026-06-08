package za.co.wethinkcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.Player;
import za.co.wethinkcode.service.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTeam {

    private Team mainTeam;
    private Player p1;
    private Player p2;
    private Player p3;

    @BeforeEach
    public void setUp() {
        mainTeam = new Team("United FC", 2);
        p1 = new Player(10, "Alice", false);
        p2 = new Player(7, "Bob", true);
        p3 = new Player(9, "Charlie", false);
    }

    @Test
    public void player_constructorThrowsOnInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new Player(10, null, false));
        assertThrows(IllegalArgumentException.class, () -> new Player(10, "", false));
        assertThrows(IllegalArgumentException.class, () -> new Player(0, "Alice", false));
        assertThrows(IllegalArgumentException.class, () -> new Player(-5, "Alice", false));
    }

    @Test
    public void team_constructorThrowsOnInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new Team("", 25));
        assertThrows(IllegalArgumentException.class, () -> new Team("City FC", 0));
        assertThrows(IllegalArgumentException.class, () -> new Team("City FC", -1));
    }

    @Test
    public void signPlayer_successfulSigningIncreasesSquadSize() {
        mainTeam.signPlayer(p1);
        assertEquals(1, mainTeam.getCurrentSquadSize());
        assertTrue(mainTeam.getAllPlayers().contains(p1));
    }

    @Test
    public void signPlayer_throwsWhenPlayerIsNull() {
        assertThrows(IllegalArgumentException.class, () -> mainTeam.signPlayer(null));
    }

    @Test
    public void signPlayer_throwsWhenDuplicateJerseyNumber() {
        mainTeam.signPlayer(p1);
        Player duplicateJerseyPlayer = new Player(10, "Clone Alice", false);

        assertThrows(IllegalArgumentException.class, () -> mainTeam.signPlayer(duplicateJerseyPlayer));
    }

    @Test
    public void signPlayer_throwsIllegalStateWhenAtCapacity() {
        mainTeam.signPlayer(p1);
        mainTeam.signPlayer(p2);

        assertThrows(IllegalStateException.class, () -> mainTeam.signPlayer(p3));
    }

    @Test
    public void getAllPlayers_returnsDefensiveCopy() {
        mainTeam.signPlayer(p1);
        List<Player> returnedList = mainTeam.getAllPlayers();
        returnedList.add(p2);

        assertEquals(1, mainTeam.getCurrentSquadSize(), "Original team roster must not be modified by altering returned copy");
        assertFalse(mainTeam.getAllPlayers().contains(p2));
    }

    @Test
    public void getAvailablePlayers_returnsOnlyUninjured() {
        mainTeam = new Team("LARGE SQUAD", 10);
        mainTeam.signPlayer(p1); // not injured
        mainTeam.signPlayer(p2); // injured
        mainTeam.signPlayer(p3); // not injured

        List<Player> availablePlayers = mainTeam.getAvailablePlayers();

        assertEquals(2, availablePlayers.size());
        assertTrue(availablePlayers.contains(p1));
        assertTrue(availablePlayers.contains(p3));
        assertFalse(availablePlayers.contains(p2));
    }
}