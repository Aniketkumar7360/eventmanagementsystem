/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aniket
 */
public class Register extends HttpServlet {

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
       PrintWriter out = response.getWriter();
        
       String a1 = request.getParameter("ename");
       String a2 = request.getParameter("enum");
       String a3 = request.getParameter("cardno");
       String a4 = request.getParameter("edate");
       String a5 = request.getParameter("cvv");
       String a6 = request.getParameter("cname");
       
       if(a1.isEmpty() && a2.isEmpty() && a3.isEmpty() && a4.isEmpty() && a5.isEmpty() && a6.isEmpty()){
            response.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Please Enter Event Details!!!');");  
            out.println("</script>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Registration.html");
            requestDispatcher.include(request, response);
       }else{
           try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String conURL = "jdbc:mysql://localhost:3307/EventlyDB";
                String dbusername = "root";
                String dbuserpassword = "";
                Connection con;
                con = DriverManager.getConnection(conURL , dbusername, dbuserpassword);
                con.setAutoCommit(false);
                Statement statement = con.createStatement();
                String mysqlQuery = "insert into card values('"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"','"+a6+"') ";
                statement.executeUpdate(mysqlQuery);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Payment.html");
                requestDispatcher.forward(request, response);
                con.commit();
                con.close();
            }catch(ServletException | IOException | ClassNotFoundException | SQLException e){
                System.out.println("Exception Caught: " + e);
            }
       }
       
    }

}
