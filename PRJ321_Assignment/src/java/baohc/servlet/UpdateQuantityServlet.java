/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baohc.servlet;

import baohc.cart.CartDetailUpdateError;
import baohc.cart.CartObjectDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
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
@WebServlet(name = "UpdateQuantityServlet", urlPatterns = {"/UpdateQuantityServlet"})
public class UpdateQuantityServlet extends HttpServlet {

    private final String VIEW_CART_CONTROLLER = "viewCart";

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

	CartDetailUpdateError error = new CartDetailUpdateError();

	ServletContext context = request.getServletContext();
	Map<String, String> siteMap = (Map<String, String>) context.getAttribute("MAPPING");
	String url = siteMap.get(VIEW_CART_CONTROLLER);

	try {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
		CartObjectDetail cart = (CartObjectDetail) session.getAttribute("CART");
		if (cart != null) {
		    String[] itemUpdate = request.getParameterValues("txtItemName");
                    for (String string : itemUpdate) {
                        System.out.println(string);
                    }
		    String[] quantity = request.getParameterValues("txtQuantity");

		    if (itemUpdate.length == quantity.length) {
			for (int i = 0; i < itemUpdate.length; i++) {
			    try {
				int quantityUpdate = Integer.parseInt(quantity[i]);
				if (quantityUpdate < 1) {
				    cart.deleteItemFromCart(itemUpdate[i]);
				} else {
				    cart.updateQuantityOfItem(itemUpdate[i], quantityUpdate);
				}
			    } catch (NumberFormatException ex) {
				ArrayList<String> listErrorFormat = error.getQuantityFormatError();
				if (listErrorFormat == null) {
				    listErrorFormat = new ArrayList<>();
				}

				listErrorFormat.add("Update quantity at " + itemUpdate[i] + " fail. Quantity must be a number.");
				error.setQuantityFormatError(listErrorFormat);
				request.setAttribute("ERROR_UPDATE_CART", error);
				log("UpdateQuantityServlet NumberFormatException " + ex.getMessage());
			    }
			}
			session.setAttribute("CART", cart);
		    }
		}
	    }
	} finally {
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
