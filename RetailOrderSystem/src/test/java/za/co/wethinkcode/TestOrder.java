package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.OrderStatus;
import za.co.wethinkcode.service.Order;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrder {

    @Test
    public void constructor_throwsWhenOrderIdInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Order(null));
        assertThrows(IllegalArgumentException.class, () -> new Order(""));
    }

    @Test
    public void constructor_initializesToNew() {
        Order order = new Order("ORD-001");
        assertEquals(OrderStatus.NEW, order.getStatus());
    }

    @Test
    public void fullLifecycle_transitionsCorrectly() {
        Order order = new Order("ORD-002");

        order.processOrder();
        assertEquals(OrderStatus.PROCESSING, order.getStatus());

        order.shipOrder();
        assertEquals(OrderStatus.SHIPPED, order.getStatus());

        order.deliverOrder();
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    public void processOrder_throwsWhenNotInNewState() {
        Order order = new Order("ORD-003");
        order.processOrder(); // Now PROCESSING

        assertThrows(IllegalStateException.class, order::processOrder);
    }

    @Test
    public void shipOrder_throwsWhenNotInProcessingState() {
        Order order = new Order("ORD-004");
        // Still NEW, trying to skip PROCESSING
        assertThrows(IllegalStateException.class, order::shipOrder);
    }

    @Test
    public void deliverOrder_throwsWhenNotInShippedState() {
        Order order = new Order("ORD-005");
        order.processOrder(); // Now PROCESSING, trying to skip SHIPPED

        assertThrows(IllegalStateException.class, order::deliverOrder);
    }
}