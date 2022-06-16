package servlets;

import dao.CourseDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.RoleDao;
import dao.StudentDao;
import dao.UserDao;
import entities.Course;
import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StudentAdminServlet", urlPatterns = {"/Manage/Students/*"})
public class StudentAdminServlet extends AbstractAdminServlet<User> {
    private StudentDao sdao;

    public StudentAdminServlet() {
        viewPath = "/views/admin/students/";
        returnUri = "/Manage/Students";
    }

    @Override
    protected StudentDao getDao() {
        if (sdao == null) {
            sdao = new StudentDao();
        }

        return sdao;
    }
    
    @Override
    protected void Create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User student = new User();
        student.setId(Long.parseLong(request.getParameter("id")));
        student.setFirstName(request.getParameter("firstname"));
        student.setLastName(request.getParameter("lastname"));
        student.setGender(request.getParameter("gender"));
        student.setGpa(Float.parseFloat(request.getParameter("gpa")));
        student.setRole(new RoleDao().findByTitle("Student"));
        
        String[] courses = request.getParameterValues("courses");
        Collection<Course> coursesSelected = new ArrayList<>();
        
        CourseDao courseDao = new CourseDao();
        
        if (courses != null) {
            for(String courseId : courses) {
                Course tmp = courseDao.find(Long.parseLong(courseId));
                coursesSelected.add(tmp);
            }
        }
        
        student.setCoursesTaken(coursesSelected);
        
        new UserDao().save(student);
    }
    
    @Override
    protected void Update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long studentId = Long.parseLong(request.getParameter("id"));

        StudentDao sdao = new StudentDao();
        User student = sdao.find(studentId);

        student.setFirstName(request.getParameter("firstname"));
        student.setLastName(request.getParameter("lastname"));
        student.setGender(request.getParameter("gender"));
        student.setGpa(Float.parseFloat(request.getParameter("gpa")));
        
        String[] courses = request.getParameterValues("courses");
        Collection<Course> coursesSelected = new ArrayList<>();
        
        CourseDao courseDao = new CourseDao();
        
        if (courses != null) {
            for(String courseId : courses) {
                Course tmp = courseDao.find(Long.parseLong(courseId));
                coursesSelected.add(tmp);
            }
        }
        
        student.setCoursesTaken(coursesSelected);
        sdao.update(student);
    }
}
