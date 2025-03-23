package com.ranjit.pizza.assignment;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private PizzaType type;
    private CrustType crust;
    private List<Topping> toppings;
    private double price;
    private Size size;

    public Pizza(PizzaType type, CrustType crust, Size size) {
        this.type = type;
        this.crust = crust;
        this.size = size;
        this.toppings = new ArrayList<>();

        // Set initial price based on pizza type and size
        switch (size) {
            case REGULAR:
                this.price = type.getRegularPrice();
                break;
            case MEDIUM:
                this.price = type.getMediumPrice();
                break;
            case LARGE:
                this.price = type.getLargePrice();
                break;
            default:
                throw new IllegalArgumentException("Invalid size: " + size);
        }
    }

    public void addTopping(Topping topping) {
        if (canAddTopping(topping)) {
            toppings.add(topping);
            price += topping.getPrice();
        } else {
            throw new IllegalArgumentException("Invalid topping: " + topping + " cannot be added to this pizza.");
        }
    }

    public boolean canAddTopping(Topping topping) {
        if (type.isVegetarian() && topping.isNonVeg()) {
            return false; // Vegetarian pizza cannot have non-vegetarian toppings
        }
        if (!type.isVegetarian() && topping == Topping.PANEER) {
            return false; // Non-vegetarian pizza cannot have paneer topping
        }

        // Check if the pizza is non-vegetarian and already has a non-veg topping
        if (!type.isVegetarian()) {
            if (toppings.stream().filter(Topping::isNonVeg).count() > 1) {
                return false; // Only one non-veg topping allowed
            }
        }

        // Check for large pizzas
        if (size == Size.LARGE) {
            if (toppings.size() > 2) {
                return false; // Large pizzas can have only 2 toppings
            }
        } else {
            // For small and medium pizzas, allow only one non-veg topping
            if (toppings.size() > 1 && topping.isNonVeg()) {
                return false; // Only one non-veg topping allowed for small and medium pizzas
            }
        }

        return true; // Topping can be added
    }

    public double getPrice() {
        return price;
    }

    public PizzaType getType() {
        return type;
    }

    public CrustType getCrust() {
        return crust;
    }

    public List<Topping> getToppings() {
        return toppings;
    }
}