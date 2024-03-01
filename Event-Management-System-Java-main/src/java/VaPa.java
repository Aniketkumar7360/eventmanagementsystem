/*
 *

/**
 /*
 * @author aniket
 * Participant Login Validation Servlet
 * for registering new participants.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aniket
 */
public class VaPa extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            String userName = request.getParameter("Pausername");
            String userPassword = request.getParameter("Papassword");
            
            if(userName.isEmpty() && userPassword.isEmpty()){
                response.setContentType("text/html");  
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('Please Enter Your Login Details!!!');");  
                out.println("</script>");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Plogin.html");
                requestDispatcher.include(request, response);
            }else{
                try {
                    if(LoginDao.validate(userName, userPassword)){
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ParticipantEvent.html");
                        requestDispatcher.forward(request, response);
                    }else{
                        response.setContentType("text/html");  
                        out.println("<script type=\"text/javascript\">");  
                        out.println("alert('Sorry! ... User Name and Password Incorrect!!!');");  
                        out.println("</script>");
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Plogin.html");
                        requestDispatcher.include(request, response);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(VaPa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            
        }
    }

}
