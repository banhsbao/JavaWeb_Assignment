/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baohc.login;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class CusUpdateErrors implements Serializable{
    private String passwordLengthError;
    private String lastnameLengthError;

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getLastnameLengthError() {
        return lastnameLengthError;
    }

    public void setLastnameLengthError(String lastnameLengthError) {
        this.lastnameLengthError = lastnameLengthError;
    }

}
