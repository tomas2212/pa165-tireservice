package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * @author Jakub Papcun(359 474)
 */
@Repository
public class TireTypeDAOImpl implements TireTypeDAO {

    @PersistenceContext
    protected EntityManager entityManager;

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
            throw new IllegalArgumentException("You have to set tire type");
        }
        entityManager.persist(tireType);
    }

    public void updateTireType(TireType tireType) {
        if (tireType == null) {
            throw new IllegalArgumentException("You have to set tire type");
        }
        if (tireType.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity does not contain ID");
        }
        entityManager.merge(tireType);
    }

    public void removeTireType(TireType tireType) {
        if (tireType == null) {
            throw new IllegalArgumentException("You have to set tire type");
        }
        if (tireType.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity does not contain ID");
        }
        tireType.setActive(Boolean.FALSE);
        entityManager.merge(tireType);
    }
}
