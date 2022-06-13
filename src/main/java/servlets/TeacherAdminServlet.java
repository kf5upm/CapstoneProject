package servlets;

import dao.RoleDao;
import entities.User;
import dao.TeacherDao;
import dao.UserDao;
import entities.Role;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@WebServlet(name = "TeacherAdminServlet", urlPatterns = {"/Manage/Teachers/*"})
public class TeacherAdminServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private final String viewPath = "/views/admin/teachers/";

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
        
        Collection<User> teachers = new TeacherDao().findAll();
        session.setAttribute("payload", teachers);
        session.setAttribute("teacher", null);
        
        RequestDispatcher disp = request.getRequestDispatcher(viewPath);
        disp.forward(request, response);
    }

    private void Edit(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Long id = Long.parseLong(request.getPathInfo().split("/")[2]);
        User teacher = new TeacherDao().find(id);
        
        HttpSession session = request.getSession();
        session.setAttribute("teacher", teacher);

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

        request.getSession().removeAttribute("teacher");
        response.sendRedirect(request.getContextPath() + "/Manage/Teachers");
    }
    
    private void Create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Role role = new RoleDao().find(2L);
        
        User teacher = new User();
        teacher.setId(Long.parseLong(request.getParameter("id")));
        teacher.setFirstName(request.getParameter("firstname"));
        teacher.setLastName(request.getParameter("lastname"));
        teacher.setGender(request.getParameter("gender"));
        teacher.setRole(role);
        
        new UserDao().save(teacher);
    }
    
    private void Update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long teacherId = Long.parseLong(request.getParameter("id"));

        TeacherDao tdao = new TeacherDao();
        User teacher = tdao.find(teacherId);

        teacher.setFirstName(request.getParameter("firstname"));
        teacher.setLastName(request.getParameter("lastname"));
        teacher.setGender(request.getParameter("gender"));
        
        tdao.update(teacher);
    }
    
    
}
