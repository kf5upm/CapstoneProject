package servlets;

import dao.AbstractDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entities.Course;
import dao.CourseDao;
import dao.TeacherDao;
import entities.User;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CourseAdminServlet", urlPatterns = {"/Manage/Courses/*"})
public class CourseAdminServlet extends AbstractAdminServlet<Course> {
    private CourseDao cdao;

    public CourseAdminServlet() {
        viewPath = "/views/admin/courses/";
        returnUri = "/Manage/Courses";
    }

    @Override
    protected CourseDao getDao() {
        if (cdao == null) {
            cdao = new CourseDao();
        }

        return cdao;
    }
    
    @Override
    protected void Create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          Course course = new Course();
          
          course.setName(request.getParameter("name"));
          course.setCredits(Long.parseLong(request.getParameter("credits")));
          course.setTeacher(new TeacherDao().find(Long.parseLong(request.getParameter("teacher"))));

          new CourseDao().save(course);
    }
    
    @Override
    protected void List(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        AbstractDAO dao = getDao();
        Collection<Course> collection = dao.findAll();
        Collection<User> teachers = new TeacherDao().findAll();
        
        session.setAttribute("payload", collection);
        session.setAttribute("teachers", teachers);
       session.setAttribute("selected", null);
        
        RequestDispatcher disp = request.getRequestDispatcher(viewPath);
        disp.forward(request, response);
    }
 
    @Override
    protected void Update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Long courseId = Long.parseLong(request.getParameter("id"));

        cdao = new CourseDao();
        Course course = cdao.find(courseId);

        course.setName(request.getParameter("name"));
        course.setCredits(Long.parseLong(request.getParameter("credits")));
        course.setTeacher(new TeacherDao().find(Long.parseLong(request.getParameter("teacher"))));
        
        cdao.update(course);
    }
}
