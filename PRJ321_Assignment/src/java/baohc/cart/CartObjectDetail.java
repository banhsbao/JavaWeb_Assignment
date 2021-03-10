/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baohc.cart;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartObjectDetail implements Serializable {

    private Map<String, Integer> items;
    private String id;
    public Map<String, Integer> getItems() {
        return items;
    }
    public void addItemToCart(String itemName) {
        // 1. Check items is existed or not
        if (this.items == null) {
            this.items = new HashMap<>();
        }// end if items is not existed

        // 2. Check item is existed in cart or not
        int quantity = 1;
        if (this.items.containsKey(itemName)) {
            quantity = this.items.get(itemName) + 1;
        }// end if contain item

        // 3. Update item
        this.items.put(itemName, quantity);
    }

    public void deleteItemFromCart(String itemName) {
        if (this.items != null) {
            if (this.items.containsKey(itemName)) {
                this.items.remove(itemName);
            }
        }
    }

    public void updateQuantityOfItem(String item, int quantity) {
        if (this.items.containsKey(item)) {
            this.items.replace(item, quantity);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String generateID() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy-HHmmss");
        Date date = new Date();
        return formatter.format(date);
    }

}
