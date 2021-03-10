/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baohc.product;

import baohc.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class ProductDAO {

    private List<ProductDTO> listProduct;

    public List<ProductDTO> getListProduct() {
        return listProduct;
    }

    public void loadProduct() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT productName "
                    + "FROM Product";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (listProduct == null) {
                    listProduct = new ArrayList<>();
                }
                String productName = rs.getString("productName");

                ProductDTO dto = new ProductDTO(productName);

                listProduct.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
