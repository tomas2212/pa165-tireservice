package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jakub Papcun(359 474)
 */
public class TireTypeDAOImpl implements TireTypeDAO {
    protected EntityManager entityManager;

    public TireTypeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public TireType getTireTypeById(Long id) {
        TireType t = entityManager.find(TireType.class, id);
        return t.isActive() ? t : null;
    }

    public List<TireType> getAllTireTypes() {
        TypedQuery<TireType> t = entityManager.createQuery("SELECT t FROM TireType t WHERE t.active = :activity", TireType.class);
        t.setParameter("activity", Boolean.TRUE);
        return t.getResultList();
    }

    public void insertTireType(TireType tireType) {
        if (tireType == null) {
            throw new IllegalArgumentException("You have to set tire");
        }
        entityManager.getTransaction().begin();
        entityManager.persist(tireType);
        entityManager.getTransaction().commit();
    }

    public void updateTireType(TireType tireType) {
        if (tireType == null) {
            throw new IllegalArgumentException("You have to set tire type");
        }
        if (tireType.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity does not contain ID");
        }
        entityManager.getTransaction().begin();
        entityManager.merge(tireType);
        entityManager.getTransaction().commit();
    }

    public void removeTireType(TireType tireType) {
        if (tireType == null) {
            throw new IllegalArgumentException("You have to set tire");
        }
        if (tireType.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity does not contain ID");
        }
        tireType.setActive(Boolean.FALSE);
        entityManager.getTransaction().begin();
        entityManager.merge(tireType);
        entityManager.getTransaction().commit();
    }
    
}
