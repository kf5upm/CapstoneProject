package servlets;

import dao.TeacherDao;
import dao.UserDao;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DraftServlet", urlPatterns = {"/DOOOO"})
public class DraftServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter w = response.getWriter();
        
        String action = request.getServletPath();
        
        w.println(action);
        



    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter w = response.getWriter();

        String pathInfo = request.getPathInfo();

        String data = request.getParameter("data");

        w.println(pathInfo);
        w.println(data);

    }
}
