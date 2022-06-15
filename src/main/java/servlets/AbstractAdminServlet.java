package servlets;

import dao.AbstractDAO;
import dao.CourseDao;
import entities.Course;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractAdminServlet<T> extends HttpServlet {
    
    protected static final Logger logger = LogManager.getLogger();
    protected String viewPath;
    protected String returnUri;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        String pathParts[] = (request.getPathInfo() != null) ? request.getPathInfo().split("/") : null;
        String action = (request.getPathInfo() != null) ? request.getPathInfo().split("/")[1] : "List";
        switch (action) {
            case "Edit":
                Load(request, response);
                break;
            case "Delete":
                Delete(request, response);
                break;
            default:
                List(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            Create(request, response);
        } else {
            Update(request, response);
        }
        request.getSession().removeAttribute("selected");
        response.sendRedirect(request.getContextPath() + returnUri);
    }
    
    protected abstract <T> T getDao();

    protected abstract void Create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected abstract void Update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    protected void List(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Collection<Course> offered = new CourseDao().findAll();
        session.setAttribute("offered", offered);
        
        AbstractDAO dao = getDao();
        Collection<T> collection = dao.findAll();
        
        session.setAttribute("payload", collection);
        session.setAttribute("selected", null);
        
        RequestDispatcher disp = request.getRequestDispatcher(viewPath);
        disp.forward(request, response);
    }
    
    protected void Load(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Long id = Long.parseLong(request.getPathInfo().split("/")[2]);
        
        AbstractDAO dao = getDao();
        
        T entity = (T) dao.find(id);
        
        HttpSession session = request.getSession();
        session.setAttribute("selected", entity);

        RequestDispatcher disp = request.getRequestDispatcher(viewPath);
        disp.forward(request, response);
    }
    
    protected void Delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getPathInfo().split("/")[2]);

        AbstractDAO dao = getDao();
        T entity = (T) dao.find(id);

        dao.delete(entity);

        request.getSession().removeAttribute("selected");
        response.sendRedirect(request.getContextPath() + returnUri);
    }
}
