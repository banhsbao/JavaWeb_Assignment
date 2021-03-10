/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baohc.login;

import baohc.utils.DBHelper;
import java.io.Serializable;
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
public class CusDAO implements Serializable {

    private List<CusDTO> listAccounts;

    public List<CusDTO> getListAccounts() {
        return listAccounts;
    }

    public boolean checkLogin(String username, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            if (con != null) {
                con = DBHelper.makeConnection();
                String sql = "Select username, password, lastname, isAdmin"
                        + "from UserAccount "
                        + "Where username = ? and password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public CusDTO getUser(String username, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select username, password, lastname, isAdmin "
                        + "from UserAccount "
                        + "where username = ? and password = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userNameCus = rs.getString("username");
                    String passwordCus = rs.getString("password");
                    String lastNameCus = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    CusDTO obj = new CusDTO(userNameCus, passwordCus, lastNameCus, role);
                    return obj;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public void searchLastName(String searchName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select username, password, lastname, isAdmin "
                        + "from UserAccount "
                        + " where lastname like ?";
                pst = con.prepareCall(sql);
                pst.setString(1, "%" + searchName + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastnameCus = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    CusDTO obj = new CusDTO(username, password, lastnameCus, role);
                    if (listAccounts == null) {
                        listAccounts = new ArrayList<>();
                    }
                    listAccounts.add(obj);
                }
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

    public boolean deleteAccount(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "delete from UserAccount"
                        + " where username = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                int status = pst.executeUpdate();
                if (status > 0) {
                    return true;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateAccount(String username, String password, String lastname, boolean isAdmin) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update UserAccount "
                        + "set password = ?, lastname= ?, isAdmin = ? "
                        + "where username = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, password);
                pst.setString(2, lastname);
                pst.setBoolean(3, isAdmin);
                pst.setString(4, username);
                int status = pst.executeUpdate();
                if (status > 0) {
                    return true;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean insertAccount(String username, String password, String lastname, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into UserAccount(username, password, lastname, isAdmin) "
                        + "values(?, ?, ?, ?)";
                pst = con.prepareCall(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.setString(3, lastname);
                pst.setBoolean(4, isAdmin);
                int status = pst.executeUpdate();
                if (status > 0) {
                    return true;
                }
            }
        } finally {
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
