/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baohc.cart;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CartDetailUpdateError implements Serializable{
    private ArrayList<String> quantityFormatError;

    public ArrayList<String> getQuantityFormatError() {
	return quantityFormatError;
    }
    public void setQuantityFormatError(ArrayList<String> quantityFormatError) {
	this.quantityFormatError = quantityFormatError;
    }
}
