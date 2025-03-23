package com.ranjit.pizza.assignment;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<PizzaType, Map<Size, Integer>> stock;

    public void initializeInventory() {
        stock = new HashMap<>();
        for (PizzaType type : PizzaType.values()) {
            stock.put(type, new HashMap<>());
            for (Size size : Size.values()) {
                stock.get(type).put(size, 12); // Set initial stock to 12 for each size
            }
        }
    }

    public boolean isPizzaAvailable(PizzaType type, Size size) {
        return stock.get(type).get(size) > 0;
    }

    public void decreaseInventory(PizzaType type, Size size) {
        stock.get(type).put(size, stock.get(type).get(size) - 1);
    }

    public void restockInventory(PizzaType type, Size size, int quantity) {
        stock.get(type).put(size, stock.get(type).get(size) + quantity);
    }

    public int getInventoryCount(PizzaType type, Size size) {
        return stock.get(type).get(size);
    }
}
