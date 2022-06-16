package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.CourseDao;
import dao.RoleDao;
import dao.StudentDao;
import dao.UserDao;
import entities.Course;
import entities.CourseRecord;
import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
        
        String[] courses = request.getParameterValues("offerings");
        Collection<CourseRecord> coursesSelected = new ArrayList<>();
        
        CourseDao courseDao = new CourseDao();
        
        if (courses != null) {
            for(String courseId : courses) {
                Course courseSelected = courseDao.find(Long.parseLong(courseId));
                CourseRecord courseRecord = new CourseRecord();
                
                courseRecord.setStudent(student);
                courseRecord.setCourse(courseSelected);
                coursesSelected.add(courseRecord);
            }
        }
        
        student.setCourseRecords(coursesSelected);
        new UserDao().save(student);
    }
    
    @Override
    protected void Update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long studentId = Long.parseLong(request.getParameter("id"));

        User student = sdao.find(studentId);

        student.setFirstName(request.getParameter("firstname"));
        student.setLastName(request.getParameter("lastname"));
        student.setGender(request.getParameter("gender"));
        student.setGpa(Float.parseFloat(request.getParameter("gpa")));
        
        String[] courses = request.getParameterValues("offerings");
        Collection<CourseRecord> coursesSelected = new ArrayList<>();
        
        CourseDao courseDao = new CourseDao();
        
        if (courses != null) {
            for(String courseId : courses) {
                Course courseSelected = courseDao.find(Long.parseLong(courseId));
                CourseRecord courseRecord = new CourseRecord();
                
                courseRecord.setStudent(student);
                courseRecord.setCourse(courseSelected);
                coursesSelected.add(courseRecord);
            }
        }
        
        student.setCourseRecords(coursesSelected);
        sdao.update(student);
    }
}
