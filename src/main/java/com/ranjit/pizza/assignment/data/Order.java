package com.ranjit.pizza.assignment;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Pizza> pizzas;
    private List<Side> sides;
    private double totalAmount;

    public Order() {
        this.pizzas = new ArrayList<>();
        this.sides = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        totalAmount += pizza.getPrice();
    }

    public void addSide(Side side) {
        sides.add(side);
        totalAmount += side.getPrice();
    }

    public void calculateTotalAmount() {
        totalAmount = 0.0;
        for (Pizza pizza : pizzas) {
            totalAmount += pizza.getPrice();
        }
        for (Side side : sides) {
            totalAmount += side.getPrice();
        }
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Side> getSides() {
        return sides;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}