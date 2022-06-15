package dao;

import static dao.AbstractDAO.em;
import entities.Role;
import java.util.Collection;
import javax.persistence.TypedQuery;

public class RoleDao extends AbstractDAO<Role> {
    public RoleDao() {
        super(Role.class);
    }
    
    @Override
    public Collection<Role> findAll() {
        TypedQuery<Role> query = em.createNamedQuery("Role.findByRole", Role.class);
        return (Collection<Role>) query.getResultList();
    }

    public Role findByTitle(String title) {
        TypedQuery<Role> query = em.createNamedQuery("Role.findByTitle", Role.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }
}
