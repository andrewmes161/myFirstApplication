package com.example.module3assignment.myfiles;

// This class holds all info about an item

public class item {

    private String itemName;
    private int quantity;

    // Default Constructors //
    item() {
        this.itemName = "NO NAME";
        this.quantity = -1;
    }

    item(String name, int num) {
        this.itemName = name;
        this.quantity = num;
    }

    // Getters & Setters //

    public String getItemName() {
        return itemName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setItemName(String text) {
        this.itemName = text;
    }

    public void setQuantity(int num) {
        this.quantity = num;
    }
}
