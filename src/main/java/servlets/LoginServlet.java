package servlets;

import dao.UserDao;
import entities.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String errorMessage = "";
        HttpSession session = request.getSession();
        
        try {
            Long userId = Long.parseLong(request.getParameter("userid"));
            User user = new UserDao().find(userId);
            
            if (user == null) {
                errorMessage = "Unknown user or bad password.";
            } else {
                session.setAttribute("user", user);

                String viewsPath = "/views/" + user.getRole().getTitle().toLowerCase() + "/";
                session.setAttribute("viewsPath", viewsPath);
            }
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
        }
        
        if (errorMessage.length() > 0) {
            session.setAttribute("error", errorMessage);
            response.sendRedirect(request.getContextPath());
        } else {
            response.sendRedirect(request.getContextPath() + "/Home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    }

}
