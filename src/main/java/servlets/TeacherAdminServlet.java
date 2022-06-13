package servlets;

import dao.RoleDao;
import entities.User;
import dao.TeacherDao;
import dao.UserDao;
import entities.Role;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TeacherAdminServlet", urlPatterns = {"/Manage/Teachers/*"})
public class TeacherAdminServlet extends AbstractAdminServlet<User> {
    
    private TeacherDao tdao;

    public TeacherAdminServlet() {
        viewPath = "/views/admin/teachers/";
        returnUri = "/Manage/Teachers";
    }

    @Override
    protected TeacherDao getDao() {
        if (tdao == null) {
            tdao = new TeacherDao();
        }

        return tdao;
    }
    
    @Override
    protected void Create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User teacher = new User();
        teacher.setId(Long.parseLong(request.getParameter("id")));
        teacher.setFirstName(request.getParameter("firstname"));
        teacher.setLastName(request.getParameter("lastname"));
        teacher.setGender(request.getParameter("gender"));
        teacher.setRole(new RoleDao().findByTitle("Teacher"));
        new UserDao().save(teacher);
    }

    @Override
    protected void Update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Long teacherId = Long.parseLong(request.getParameter("id"));

        tdao = new TeacherDao();
        User teacher = tdao.find(teacherId);

        teacher.setFirstName(request.getParameter("firstname"));
        teacher.setLastName(request.getParameter("lastname"));
        teacher.setGender(request.getParameter("gender"));
        
        tdao.update(teacher);
    }
}
