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
public class CusCreateNewErrors implements Serializable {

    private String usernameLengthError;
    private String passwordLengthError;
    private String confirmNoMatchedError;
    private String lastnameLengthError;
    private String usernameIsExisted;

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getConfirmNoMatchedError() {
        return confirmNoMatchedError;
    }

    public void setConfirmNoMatchedError(String confirmNoMatchedError) {
        this.confirmNoMatchedError = confirmNoMatchedError;
    }

    public String getLastnameLengthError() {
        return lastnameLengthError;
    }

    public void setLastnameLengthError(String lastnameLengthError) {
        this.lastnameLengthError = lastnameLengthError;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }


}
