/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baohc.cart;

import baohc.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class CartInfoDAO implements Serializable{
    
    public boolean insertCartInfoToDB(String id, String username, String address, String phone) throws SQLException, NamingException{
	Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "insert CartInfo(id, username, address, phone) "
                        + "values (?,?,?,?)";
                pst = con.prepareStatement(sql);
		
		pst.setString(1, id);
		pst.setString(2, username);
		pst.setString(3, address);
		pst.setString(4, phone);
		int row = pst.executeUpdate();

		if(row > 0){
		    return true;
		}
            }
        } finally {
            con.setAutoCommit(true);
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
