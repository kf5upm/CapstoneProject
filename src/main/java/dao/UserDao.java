package dao;

import static dao.AbstractDAO.em;
import entities.User;
import java.util.Collection;
import javax.persistence.TypedQuery;

public class UserDao extends AbstractDAO<User> {
    
    public UserDao() {
        super(User.class);
    }
    
    public Collection<User> findAll() {
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        return (Collection<User>) query.getResultList();
    }
    
}
