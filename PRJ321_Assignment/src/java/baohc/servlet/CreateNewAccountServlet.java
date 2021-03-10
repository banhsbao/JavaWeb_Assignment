/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baohc.servlet;

import baohc.login.CusCreateNewErrors;
import baohc.login.CusDAO;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    private final String LOGIN_PAGE = "index";
    private final String CREATE_ERROR_PAGE = "createErrorPage";

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
        String confirm = request.getParameter("txtConfirm");
        String lastname = request.getParameter("txtLastname");
        boolean foundError = false;
        
        CusCreateNewErrors errors = new CusCreateNewErrors();
        ServletContext context = request.getServletContext();
        Map<String, String> Mapping = (Map<String, String>) context.getAttribute("MAPPING");
        String url = Mapping.get(CREATE_ERROR_PAGE);
        try {
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundError = true;
                errors.setUsernameLengthError("Username must be contained 6-20 charcters");
            }

            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Password must be contained 6-30 charcters");
            } else if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNoMatchedError("Confirm must be matched with password");
            }

            if ( lastname.trim().length() < 2 || lastname.trim().length() > 50) {
                foundError = true;
                errors.setLastnameLengthError("lastname must be contained 2-50 charcters");
            }
            if (foundError) {
                request.setAttribute("ERROR_CREATE_NEW_ACCOUT", errors);
            } else {
                CusDAO dao = new CusDAO();
                boolean result = dao.insertAccount(username, password, lastname, false);
                if (result) {
                    url = Mapping.get(LOGIN_PAGE);
                }
            }
        } catch (NamingException ne) {
            log("CreateNewAccountServlet_NamingException " + ne.getMessage());
        } catch (SQLException se) {
            String msg = se.getMessage();
            log("CreateNewAccountServlet SQLException " + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed.");
                request.setAttribute("ERROR_CREATE_NEW_ACCOUT", errors);
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
