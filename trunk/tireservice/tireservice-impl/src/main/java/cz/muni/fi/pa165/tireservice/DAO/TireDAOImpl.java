package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Tire;
import cz.muni.fi.pa165.tireservice.utils.ValidationHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ivan
 */
@Repository
public class TireDAOImpl implements TireDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    public Tire getTireById(Long id) {
        return entityManager.find(Tire.class, id);
    }

    public List<Tire> getAllTires() {
        return entityManager.createQuery("SELECT t FROM Tire t").getResultList();
    }

    public void insertTire(Tire tire) {
        ValidationHelper.ArgumentNull(tire);

        entityManager.persist(tire);
    }

    public void updateTire(Tire tire) {
        ValidationHelper.ArgumentNull(tire);
        ValidationHelper.IdIsZero(tire.getId());

        entityManager.merge(tire);
    }

    public void removeTire(Tire tire) {
        ValidationHelper.ArgumentNull(tire);
        ValidationHelper.IdIsZero(tire.getId());

        entityManager.remove(getTireById(tire.getId()));
    }
}
