package servlets;

import dao.RoleDao;
import dao.StudentDao;
import dao.UserDao;
import entities.Role;
import entities.User;
import java.io.IOException;
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
        student.setRole(new RoleDao().findByTitle("Student"));
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
        
        sdao.update(student);
    }
}
