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
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class CartDetailDAO implements Serializable{

    public boolean insertCartDetailToDB(String id, Map<String, Integer> items) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        int row = 0;

        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "insert CartDetail(id, productName, quantity) "
                        + "values (?,?,?)";
                pst = con.prepareStatement(sql);

                con.setAutoCommit(false);

                for (String key : items.keySet()) {
                    pst.setString(1, id);
                    pst.setString(2, key);
                    pst.setInt(3, items.get(key));

                    row += pst.executeUpdate();
                }

                if (row == items.size()) {
                    con.commit();
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
