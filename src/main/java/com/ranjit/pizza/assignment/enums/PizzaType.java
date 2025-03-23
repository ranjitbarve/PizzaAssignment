package com.ranjit.pizza.assignment;

public enum PizzaType {
    DELUXE_VEGGIE(150, 200, 325, true),
    CHEESE_AND_CORN(175, 375, 475, true),
    PANEER_TIKKA(160, 290, 340, true),
    NON_VEG_SUPREME(190, 325, 425, false),
    CHICKEN_TIKKA(210, 370, 500, false),
    PEPPER_BARBECUE_CHICKEN(220, 380, 525, false);

    private final double regularPrice;
    private final double mediumPrice;
    private final double largePrice;
    private final boolean isVegetarian;

    PizzaType(double regularPrice, double mediumPrice, double largePrice, boolean isVegetarian) {
        this.regularPrice = regularPrice;
        this.mediumPrice = mediumPrice;
        this.largePrice = largePrice;
        this.isVegetarian = isVegetarian;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public double getMediumPrice() {
        return mediumPrice;
    }

    public double getLargePrice() {
        return largePrice;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }
}