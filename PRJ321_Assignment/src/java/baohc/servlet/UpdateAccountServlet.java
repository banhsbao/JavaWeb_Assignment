/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baohc.servlet;

import baohc.login.CusDAO;
import baohc.login.CusDTO;
import baohc.login.CusUpdateErrors;
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
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "errorPage";
    private final String SEARCH_CONTROLER = "search";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String lastname = request.getParameter("txtLastname");
        String role = request.getParameter("chkAdmin");
        String lasSearchVale = request.getParameter("lastSearchValue");
        ServletContext context = request.getServletContext();
        Map<String, String> listMapping = (Map<String, String>) context.getAttribute("MAPPING");
        String url = listMapping.get(ERROR_PAGE);
        boolean errorFound = false;
        boolean isAdmin = false;
        if (role != null) {
            isAdmin = true;
        }
        try {
            CusUpdateErrors error = new CusUpdateErrors();
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                errorFound = true;
                error.setPasswordLengthError("Update password of " + username + " Fail." + " Password must be contained 6-30 characters");
            }
            if (lastname.trim().length() < 2 || lastname.trim().length() > 50) {
                errorFound = true;
                error.setLastnameLengthError("Update lastname of " + lastname + " Fail." + " Lastname must be contained 2-50 characters");
            }
            if (errorFound) {
                request.setAttribute("ERROR", error);
            } else {
                CusDAO dao = new CusDAO();
                if (dao.updateAccount(username, password, lastname, isAdmin)) {
                }
            }
            url = listMapping.get(SEARCH_CONTROLER) + "?txtSearchValue=" + lasSearchVale;
        } catch (SQLException sq) {
            log("UpdateAccountServler_SQLException " + sq.getMessage());
        } catch (NamingException ne) {
            log("UpdateAccountServlet_NamingException " + ne.getMessage());
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
