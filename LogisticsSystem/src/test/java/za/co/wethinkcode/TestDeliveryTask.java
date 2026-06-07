package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.service.DeliveryTask;
import za.co.wethinkcode.service.ExpressTask;
import za.co.wethinkcode.service.StandardTask;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDeliveryTask {

    @Test
    public void standardTask_calculatesCorrectCost() {
        DeliveryTask task = new StandardTask("TRK123", 10.0);
        assertEquals(150.0, task.calculateCost(), 0.01);
    }

    @Test
    public void expressTask_calculatesCorrectCost_noRefrigeration() {
        DeliveryTask task = new ExpressTask("EXP456", 10.0, false);
        assertEquals(250.0, task.calculateCost(), 0.01);
    }

    @Test
    public void expressTask_calculatesCorrectCost_withRefrigeration() {
        DeliveryTask task = new ExpressTask("EXP789", 10.0, true);
        assertEquals(350.0, task.calculateCost(), 0.01);
    }

    @Test
    public void constructor_throwsWhenTrackingIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StandardTask(null, 10.0);
        });
    }

    @Test
    public void constructor_throwsWhenTrackingIdEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ExpressTask("", 10.0, true);
        });
    }

    @Test
    public void constructor_throwsWhenDistanceZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StandardTask("TRK123", 0.0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new StandardTask("TRK123", -5.0);
        });
    }

    @Test
    public void addCheckpoint_throwsWhenCheckpointNullOrEmpty() {
        DeliveryTask task = new StandardTask("TRK123", 10.0);

        assertThrows(IllegalArgumentException.class, () -> {
            task.addCheckpoint(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            task.addCheckpoint("");
        });
    }

    @Test
    public void addCheckpoint_addsSuccessfully() {
        DeliveryTask task = new StandardTask("TRK123", 10.0);
        task.addCheckpoint("Warehouse A");
        task.addCheckpoint("Depot B");

        assertEquals(2, task.getCheckpoints().size());
        assertEquals("Warehouse A", task.getCheckpoints().get(0));
    }

    @Test
    public void getCheckpoints_returnsDefensiveCopy() {
        DeliveryTask task = new StandardTask("TRK123", 10.0);
        task.addCheckpoint("Warehouse A");

        List<String> returnedCheckpoints = task.getCheckpoints();
        returnedCheckpoints.add("HACKED_CHECKPOINT");

        assertEquals(1, task.getCheckpoints().size(), "Original list must not be modified by altering the returned copy");
        assertFalse(task.getCheckpoints().contains("HACKED_CHECKPOINT"));
    }
}