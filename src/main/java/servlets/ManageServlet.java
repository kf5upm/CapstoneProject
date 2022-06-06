package servlets;

import dao.CourseDao;
import entities.Course;
import entities.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ManageServlet", urlPatterns = {"/Manage/Courses"})
public class ManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (!user.getRole().getTitle().equals("Admin")) {
            response.sendRedirect("Home");
        }

        String target = request.getPathInfo().replace("/","").toLowerCase();
        
        switch(target) {
            case "courses":
                break;
            
        }

//        RequestDispatcher rd = request.getRequestDispatcher("admin/" + target);
//        rd.forward(request, response);
    }
}
