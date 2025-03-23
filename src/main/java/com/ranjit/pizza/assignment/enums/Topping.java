package com.ranjit.pizza.assignment;

public enum Topping {
    BLACK_OLIVE(20, false),
    CAPSICUM(25, false),
    PANEER(35, false),
    MUSHROOM(30, false),
    FRESH_TOMATO(10, false),
    CHICKEN_TIKKA(35, true),
    BARBECUE_CHICKEN(45, true),
    GRILLED_CHICKEN(40, true),
    EXTRA_CHEESE(35, false);

    private final double price;
    private final boolean isNonVeg;

    Topping(double price, boolean isNonVeg) {
        this.price = price;
        this.isNonVeg = isNonVeg;
    }

    public double getPrice() {
        return price;
    }

    public boolean isNonVeg() {
        return isNonVeg;
    }
}
