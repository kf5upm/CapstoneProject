package servlets;

import dao.RoleDao;
import dao.StudentDao;
import dao.UserDao;
import entities.Role;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "StudentAdminServlet", urlPatterns = {"/Manage/Students/*"})
public class StudentAdminServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();
    private final String viewPath = "/views/admin/students/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String pathParts[] = (request.getPathInfo() != null) ? request.getPathInfo().split("/") : null;
        
        String action = (request.getPathInfo() != null) ? request.getPathInfo().split("/")[1] : "List";
        
        switch(action) {
            case "Edit":
                Edit(request, response);
                break;
                
            case "Delete":
                Delete(request, response);
                break;
                
            default:
                List(request, response);
                break;
        }
    }
    
    private void List(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Collection<User> students = new StudentDao().findAll();
        session.setAttribute("payload", students);
        session.setAttribute("student", null);
        
        RequestDispatcher disp = request.getRequestDispatcher(viewPath);
        disp.forward(request, response);
    }

    private void Edit(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Long id = Long.parseLong(request.getPathInfo().split("/")[2]);
        User student = new StudentDao().find(id);
        
        HttpSession session = request.getSession();
        session.setAttribute("student", student);

        RequestDispatcher disp = request.getRequestDispatcher(viewPath);
        disp.forward(request, response);
    }

    private void Delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher disp = request.getRequestDispatcher(viewPath);
        disp.forward(request, response);
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        
        if ("create".equals(action))
            Create(request, response);
        else
            Update(request, response);

        request.getSession().removeAttribute("student");
        response.sendRedirect(request.getContextPath() + "/Manage/Students");
    }
    
    private void Create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Role role = new RoleDao().find(2L);
        
        User student = new User();
        student.setId(Long.parseLong(request.getParameter("id")));
        student.setFirstName(request.getParameter("firstname"));
        student.setLastName(request.getParameter("lastname"));
        student.setGender(request.getParameter("gender"));
        student.setRole(role);
        
        new UserDao().save(student);
    }
    
    private void Update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long studentId = Long.parseLong(request.getParameter("id"));

        StudentDao tdao = new StudentDao();
        User student = tdao.find(studentId);

        student.setFirstName(request.getParameter("firstname"));
        student.setLastName(request.getParameter("lastname"));
        student.setGender(request.getParameter("gender"));
        
        tdao.update(student);
    }
}
