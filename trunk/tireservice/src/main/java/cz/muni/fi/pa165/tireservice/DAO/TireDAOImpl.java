package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Tire;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jakub Papcun(359 474)
 */
public class TireDAOImpl implements TireDAO {
    @PersistenceContext
    protected EntityManager entityManager;
    
    public Tire getTireById(Long id) {
        return entityManager.find(Tire.class, id);
    }

    public List<Tire> getAllTires() {
        TypedQuery<Tire> q = entityManager.createQuery("SELECT e FROM Tire e", Tire.class);
        return q.getResultList();
    }

    public void insertTire(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("You have to set tire");
        }
        entityManager.persist(tire);
    }

    public void updateTire(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("You have to set tire");
        }
        if (tire.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity does not contain ID");
        }
        entityManager.merge(tire);
    }

    public void removeTire(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("You have to set tire");
        }
        if (tire.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity does not contain ID");
        }
        entityManager.remove(entityManager.merge(tire));
    }
    
}
