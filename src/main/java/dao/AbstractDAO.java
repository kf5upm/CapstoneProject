package dao;

import database.EMF;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractDAO<T> implements Serializable {
    private final Class<T> type;
    protected final static EntityManager em = EMF.createEntityManager();
    
    public AbstractDAO(Class<T> type) {
        this.type = type;
    }
    
    public T find(Long id) {
        return em.find(type, id);
    }
}
