package com.ranjit.pizza.assignment;

import com.ranjit.pizza.assignment.enums.*;
import com.ranjit.pizza.assignment.data.Order;
import com.ranjit.pizza.assignment.services.PizzaOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PizzaOrderServiceTest {
    private PizzaOrderService orderService; // Service to manage pizza orders
    private Order order; // Order object to hold the current order

    @BeforeEach
    public void setUp() {
        orderService = new PizzaOrderService(); // Initialize the service
        order = orderService.createOrder(); // Create a new order
    }

    @Test
    public void testAddPizzaToOrder() {
        // Test adding a pizza to the order
        orderService.addPizzaToOrder(order, PizzaType.DELUXE_VEGGIE, Size.MEDIUM, CrustType.HAND_TOSSED);
        assertEquals(1, order.getPizzas().size(), "One pizza should be added to the order."); // Verify that the pizza was added
    }

    @Test
    public void testAddToppingToPizza() {
        // Add a pizza first
        orderService.addPizzaToOrder(order, PizzaType.DELUXE_VEGGIE, Size.MEDIUM, CrustType.HAND_TOSSED);
        // Add a valid topping
        orderService.addToppingToPizza(order, 0, Topping.BLACK_OLIVE);
        assertEquals(1, order.getPizzas().get(0).getToppings().size(), "Topping should be added to the pizza."); // Verify that the topping was added
    }

    @Test
    public void testAddSideToOrder() {
        // Test adding a side to the order
        orderService.addSideToOrder(order, Side.COLD_DRINK);
        assertEquals(1, order.getSides().size(), "One side should be added to the order."); // Verify that the side was added
    }

    @Test
    public void testInventoryCheck() {
        // Test adding a pizza when inventory is low
        orderService.addPizzaToOrder(order, PizzaType.NON_VEG_SUPREME, Size.MEDIUM, CrustType.HAND_TOSSED);
        orderService.addPizzaToOrder(order, PizzaType.NON_VEG_SUPREME, Size.MEDIUM, CrustType.HAND_TOSSED); // Add another to check inventory
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.addPizzaToOrder(order, PizzaType.NON_VEG_SUPREME, Size.MEDIUM, CrustType.HAND_TOSSED); // Should throw exception
        }, "Should throw an exception when trying to add out of stock pizza."); // Verify exception is thrown
    }

    @Test
    public void testRestockInventory() {
        // Test restocking inventory
        int initialStock = orderService.getInventoryCount(PizzaType.DELUXE_VEGGIE, Size.MEDIUM);
        orderService.restockInventory(PizzaType.DELUXE_VEGGIE, Size.MEDIUM, 5); // Restock 5 units
        assertEquals(initialStock + 5, orderService.getInventoryCount(PizzaType.DELUXE_VEGGIE, Size.MEDIUM), "Inventory should be updated after restocking."); // Verify inventory count
    }

    @Test
    public void testOrderValidation() {
        // Test order validation based on business rules
        orderService.addPizzaToOrder(order, PizzaType.NON_VEG_SUPREME, Size.MEDIUM, CrustType.HAND_TOSSED);
        assertTrue(orderService.isOrderValid(order), "Order should be valid if business rules are satisfied."); // Verify that the order is valid
    }

    @Test
    public void testPlaceOrder() {
        // Add a pizza and a side
        orderService.addPizzaToOrder(order, PizzaType.DELUXE_VEGGIE, Size.MEDIUM, CrustType.HAND_TOSSED);
        orderService.addSideToOrder(order, Side.COLD_DRINK);
        // Place the order
        assertDoesNotThrow(() -> orderService.placeOrder(order), "Order should be placed successfully if business rules are satisfied."); // Verify that the order is placed
    }
}