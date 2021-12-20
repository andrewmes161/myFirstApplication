package com.example.module3assignment.myfiles;

import java.util.ArrayList;

public class itemList {

    ArrayList<item> myItemList = new ArrayList<item>();
    // myListLooksLike = [ item01, item02, etc...]

    public void addItem(String itemName, int num) {
        // Check if Item Exists in the list
        for (int i = 0; i < myItemList.size(); ++i) {
            if (myItemList.get(i).getItemName() == itemName) {
                throw new IllegalArgumentException("ITEM ALREADY EXISTS");
            }
            else {
            }
        }
        myItemList.add(new item(itemName, num));
    }

    public void deleteItem(String itemName) {
        for (int i = 0; i < myItemList.size(); ++i) {
            if (myItemList.get(i).getItemName() == itemName) {
                myItemList.remove(i);
            }
            else {
            }
        }
    }

    public void updateItemAmount(String itemName, int num) {
        for (int i = 0; i < myItemList.size(); ++i) {
            if (myItemList.get(i).getItemName() == itemName) {
                if (myItemList.get(i).getQuantity() + num == 0) {
                    throw new IllegalArgumentException("CAN'T HAVE ZERO QUANTITY");
                    // Delete item if amount hits zero???
                }
                else {
                    myItemList.get(i).setQuantity(myItemList.get(i).getQuantity() + num);
                }
            }
        }

    }


}
