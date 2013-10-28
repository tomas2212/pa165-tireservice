package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entities.Tire;
import cz.muni.fi.pa165.tireservice.utils.ValidationHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ivan
 */
public class TireDAOImpl implements TireDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    public Tire getTireById(Long id) {
        return entityManager.find(Tire.class, id);
    }

    @Transactional
    public List<Tire> getAllTires() {
        return entityManager.createQuery("SELECT t FROM Tire t").getResultList();
    }

    @Transactional
    public void insertTire(Tire tire) {
        ValidationHelper.ArgumentNull(tire);

        entityManager.persist(tire);
    }

    @Transactional
    public void updateTire(Tire tire) {
        ValidationHelper.ArgumentNull(tire);
        ValidationHelper.IdIsZero(tire.getId());

        entityManager.merge(tire);
    }

    @Transactional
    public void removeTire(Tire tire) {
        ValidationHelper.ArgumentNull(tire);
        ValidationHelper.IdIsZero(tire.getId());

        entityManager.remove(getTireById(tire.getId()));
    }
}
