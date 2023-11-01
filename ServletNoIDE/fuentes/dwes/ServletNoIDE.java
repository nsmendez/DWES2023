package dwes;

import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class ServletNoIDE extends HttpServlet {
    private String message;
    @Override
    public void init() {
        message = "Bienvenido/a a Jakarta EE!";
    }

    /**
    * Processes requests for both HTTP
    * <code>GET</code> and
    * <code>POST</code> methods.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.printf("""
                <html>
                    <head>
                        <title>Servlet Sin IDE</title>
                    </head>
                    <body>
                        <h2>Simple Servlet ejecutándose en %s</h2>
                        <br/>%s
                    </body>
                </html>
             """, request.getContextPath(), message);
        }
    }
    /**
    * Handles the HTTP GET 
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
    * Handles the HTTP POST 
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
}