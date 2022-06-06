package dao;

import com.learning.EMF;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class AbstractDAO<T> implements Serializable {
    private final Class<T> type;
    private final static EntityManager em = EMF.createEntityManager();
    
    public AbstractDAO(Class<T> type) {
        this.type = type;
    }
    
    public T find(Long id) {
        return em.find(type, id);
    }

    public List<T> findAll() {
        Query query = em.createNamedQuery("DAO.findAll");
        
        List<T> list = query.getResultList();
        
        return list;
    }
}
