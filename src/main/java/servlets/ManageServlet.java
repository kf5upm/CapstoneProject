package servlets;

import dao.CourseDao;
import entities.Course;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet(name = "ManageServlet", urlPatterns = {"/ManageAAAA/*"})
public class ManageServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (!user.getRole().getTitle().equals("Admin")) {
            response.sendRedirect("Home");
        }

        String target = request.getPathInfo().replace("/", "").toLowerCase();


        PrintWriter writer = response.getWriter();
       
        switch(target) {
            case "courses":
                Collection<Course> courses = new CourseDao().findAll();
                session.setAttribute("payload", courses);
                break;
            
            case "teachers":
                
                break;
                
            case "students":
                
                break;
        }


        RequestDispatcher rd = request.getRequestDispatcher("/admin/" + target + ".jsp");
        rd.forward(request, response);
    }
}
