package dao;

import entities.Course;
import java.util.Collection;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CourseDao extends AbstractDAO<Course> {
    
    private static Logger logger = LogManager.getLogger();
    
    public CourseDao() {
        super(Course.class);
    }
    
    public Collection<Course> findAll() {
        TypedQuery<Course> query = em.createNamedQuery("Course.findAll", Course.class);
        return (Collection<Course>) query.getResultList();
    }
}
