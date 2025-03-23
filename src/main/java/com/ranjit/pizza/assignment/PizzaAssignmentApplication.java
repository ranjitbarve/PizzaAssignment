package com.ranjit.pizza.assignment;

import com.ranjit.pizza.assignment.enums.*;
import com.ranjit.pizza.assignment.data.Order;
import com.ranjit.pizza.assignment.services.PizzaOrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaAssignmentApplication.class, args);
        System.out.println("Pizza Assignment Application Started");

        PizzaOrderService orderService = new PizzaOrderService();

        // Create valid orders
        validOrder(orderService);
        validVegetarianOrder(orderService);
        validNonVegetarianOrder(orderService);

        // Create invalid orders
        invalidOrderNonVegWithPaneer(orderService);
        invalidOrderVegWithNonVegTopping(orderService);

    }

    private static void validOrder(PizzaOrderService orderService) {
        Order order = orderService.createOrder();
        orderService.addPizzaToOrder(order, PizzaType.DELUXE_VEGGIE, Size.MEDIUM, CrustType.HAND_TOSSED);
        orderService.addToppingToPizza(order, 0, Topping.BLACK_OLIVE);
        orderService.addSideToOrder(order, Side.COLD_DRINK);
        orderService.placeOrder(order);
        System.out.println("Order placed successfully!");
    }

    private static void validVegetarianOrder(PizzaOrderService orderService) {
        Order order = orderService.createOrder();
        orderService.addPizzaToOrder(order, PizzaType.DELUXE_VEGGIE, Size.MEDIUM, CrustType.HAND_TOSSED);
        orderService.addToppingToPizza(order, 0, Topping.BLACK_OLIVE);
        orderService.addToppingToPizza(order, 0, Topping.MUSHROOM);
        orderService.addSideToOrder(order, Side.COLD_DRINK);
        orderService.placeOrder(order);
        System.out.println("Valid vegetarian order placed successfully!");
    }

    private static void validNonVegetarianOrder(PizzaOrderService orderService) {
        Order order = orderService.createOrder();
        orderService.addPizzaToOrder(order, PizzaType.NON_VEG_SUPREME, Size.LARGE, CrustType.CHEESE_BURST);
        orderService.addToppingToPizza(order, 0, Topping.CHICKEN_TIKKA);
        orderService.addSideToOrder(order, Side.MOUSSE_CAKE);
        orderService.placeOrder(order);
        System.out.println("Valid non-vegetarian order placed successfully!");
    }

    private static void invalidOrderNonVegWithPaneer(PizzaOrderService orderService) {
        Order order = orderService.createOrder();
        try {
            orderService.addPizzaToOrder(order, PizzaType.NON_VEG_SUPREME, Size.MEDIUM, CrustType.HAND_TOSSED);
            orderService.addToppingToPizza(order, 0, Topping.PANEER);
            orderService.placeOrder(order);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to place order: " + e.getMessage());
        }
    }

    private static void invalidOrderVegWithNonVegTopping(PizzaOrderService orderService) {
        Order order = orderService.createOrder();
        try {
            orderService.addPizzaToOrder(order, PizzaType.DELUXE_VEGGIE, Size.MEDIUM, CrustType.HAND_TOSSED);
            orderService.addToppingToPizza(order, 0, Topping.CHICKEN_TIKKA);
            orderService.placeOrder(order);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to place order: " + e.getMessage());
        }
    }

}
