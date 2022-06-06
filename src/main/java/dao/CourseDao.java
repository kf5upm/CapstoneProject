package dao;

import entities.Course;
import java.util.Collection;
import javax.persistence.TypedQuery;

public class CourseDao extends AbstractDAO<Course> {
    public CourseDao() {
        super(Course.class);
    }
    
    public Collection<Course> findAll() {
        TypedQuery<Course> query = em.createNamedQuery("Course.findAll", Course.class);
        return query.getResultList();
    }
}
