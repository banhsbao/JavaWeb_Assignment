/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baohc.servlet;

import baohc.cart.CartDetailDAO;
import baohc.cart.CartObjectDetail;
import baohc.cart.CartInfoCreateNewError;
import baohc.cart.CartInfoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

    private final String LOAD_PRODUCT_CONTROLLER = "loadProduct";
    private final String CONFIRM_ERROR_CONTROLLER = "confirmCart";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ServletContext context = request.getServletContext();
        Map<String, String> siteMap = (Map<String, String>) context.getAttribute("MAPPING");
        String url = siteMap.get(CONFIRM_ERROR_CONTROLLER);

        String username = request.getParameter("txtUsername");
        String address = request.getParameter("txtAddress");
        String phone = request.getParameter("txtPhone");

        boolean foundError = false;
        CartInfoCreateNewError error = new CartInfoCreateNewError();

        try {
            HttpSession session = request.getSession(false);

            if (session != null) {
                CartObjectDetail cart = (CartObjectDetail) session.getAttribute("CART");
                if (cart != null) {
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        if (username.trim().length() < 2 || username.trim().length() > 50) {
                            foundError = true;
                            error.setUsernameLengthError("Username must be contained 2-50 characters");
                        }
                        if (phone.trim().length() < 10 || phone.trim().length() > 20) {
                            foundError = true;
                            error.setPhoneLengthError("Phone must be contained 10-20 numbers");
                        } else if (!phone.trim().matches("^[0-9]{10,}$")) {
                            foundError = true;
                            error.setPhoneFormatError("Phone only contain number.");
                        }
                        if (address.trim().length() < 2 || address.trim().length() > 100) {
                            foundError = true;
                            error.setAddressLengthError("Address must be contained 2-100 characters");
                        }
                        if (!foundError) {
                            cart.generateID();
                            String cartID = cart.getId();

                            CartInfoDAO daoInfo = new CartInfoDAO();
                            boolean result = daoInfo.insertCartInfoToDB(cartID, username, address, phone);

                            if (result) {
                                CartDetailDAO daoDetail = new CartDetailDAO();
                                result = daoDetail.insertCartDetailToDB(cartID, items);
                            }

                            if (result) {
                                session.removeAttribute("CART");
                                url = siteMap.get(LOAD_PRODUCT_CONTROLLER);

                                request.setAttribute("CHECK_OUT_SUCESS", "Check out sucessfully!");
                            }
                        } else {
                            request.setAttribute("ERROR_CHECKOUT", error);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            log("CheckoutServlet SQLException " + ex.getMessage());
//            ex.printStackTrace();
        } catch (NamingException ex) {
            log("CheckoutServlet NamingException " + ex.getMessage());
//            ex.printStackTrace();
        } finally {
//	    response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
