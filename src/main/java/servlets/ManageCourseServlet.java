package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManageCourseServlet", urlPatterns = {"/Manage/*"})
public class ManageCourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathParts[] = request.getPathInfo().split("/");
        
        
        
        
//        String operation = pathParts[1].toLowerCase();
//        String target = pathParts[2];
//        
//        switch(operation) {
//            case "create":
//                
//                break;
//            case "view":
//                
//                break;
//            case "edit":
//                
//                break;
//            case "delete":
//                
//                break;
//            default:
//                
//                break;
//        }
        
        
        
        
        
        PrintWriter writer = response.getWriter();

        for (String part : pathParts) {
            writer.println(part);
        }
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
