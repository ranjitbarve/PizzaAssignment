package com.ranjit.pizza.assignment;

public class PizzaOrderService {
    private Inventory inventory;

    public PizzaOrderService() {
        inventory = new Inventory();
        inventory.initializeInventory(); // Set initial stock
    }

    public Order createOrder() {
        return new Order(); // Create a new order
    }

    public void addPizzaToOrder(Order order, PizzaType type, Size size, CrustType crust) {
        if (inventory.isPizzaAvailable(type, size)) {
            Pizza pizza = new Pizza(type, crust, size);
            order.addPizza(pizza);
            inventory.decreaseInventory(type, size);
        } else {
            throw new IllegalArgumentException("Pizza type is out of stock.");
        }
    }

    public void addToppingToPizza(Order order, int pizzaIndex, Topping topping) {
        Pizza pizza = order.getPizzas().get(pizzaIndex);
        pizza.addTopping(topping);
    }

    public void addSideToOrder(Order order, Side side) {
        order.addSide(side);
    }

    public boolean isOrderValid(Order order) {
        for (Pizza pizza : order.getPizzas()) {
            for (Topping topping : pizza.getToppings()) {
                if (!pizza.canAddTopping(topping)) {
                    return false; // If any topping is invalid, the order is not valid
                }
            }
        }
        return true; // Order is valid
    }

    public void placeOrder(Order order) {
        if (isOrderValid(order)) {
            order.calculateTotalAmount(); // Update total amount
            System.out.println("Order placed successfully! Total amount: " + order.getTotalAmount());
        } else {
            throw new IllegalArgumentException("Order is not valid due to business rules.");
        }
    }

    public void restockInventory(PizzaType type, Size size, int quantity) {
        inventory.restockInventory(type, size, quantity);
    }

    public int getInventoryCount(PizzaType type, Size size) {
        return inventory.getInventoryCount(type, size);
    }
}