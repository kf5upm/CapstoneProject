package dao;

import static dao.AbstractDAO.em;
import entities.User;
import java.util.Collection;
import javax.persistence.TypedQuery;

public class TeacherDao extends AbstractDAO<User> {
    public TeacherDao() {
        super(User.class);
    }

    public Collection<User> findAll() {
        TypedQuery<User> query = em.createNamedQuery("User.findByRole", User.class);
        query.setParameter("role", "Teacher");
        return (Collection<User>) query.getResultList();
    }
}
