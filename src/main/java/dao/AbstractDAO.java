package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import database.EMF;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EntityManager;

public abstract class AbstractDAO<T> implements Serializable {
    private static final Logger logger = LogManager.getLogger();
    private final Class<T> type;
    protected final static EntityManager em = EMF.createEntityManager();
    
    public AbstractDAO(Class<T> type) {
        this.type = type;
    }
    
    public T find(Long id) {
        return em.find(type, id);
    }
    
    public abstract Collection<T> findAll();
    
    public void save(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }
    
    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }
    
    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }
}
