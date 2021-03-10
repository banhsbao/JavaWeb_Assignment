/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baohc.cart;

/**
 *
 * @author Admin
 */
public class CartInfoCreateNewError {
    private String usernameLengthError;
    private String phoneFormatError;
    private String phoneLengthError;
    private String addressLengthError;

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPhoneFormatError() {
        return phoneFormatError;
    }

    public void setPhoneFormatError(String phoneFormatError) {
        this.phoneFormatError = phoneFormatError;
    }

    public String getPhoneLengthError() {
        return phoneLengthError;
    }

    public void setPhoneLengthError(String phoneLengthError) {
        this.phoneLengthError = phoneLengthError;
    }

    public String getAddressLengthError() {
        return addressLengthError;
    }

    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }
    
}
