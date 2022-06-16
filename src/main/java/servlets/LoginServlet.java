package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static Logger logger = LogManager.getLogger();    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processLogin(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processLogin(request, response);
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response)
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
            logger.error("An error occured.", ex);
        }
        
        if (errorMessage.length() > 0) {
            session.setAttribute("error", errorMessage);
            response.sendRedirect(request.getContextPath());
        } else {
            session.removeAttribute("error");
            response.sendRedirect(request.getContextPath() + "/Home");
        }
    }
}
