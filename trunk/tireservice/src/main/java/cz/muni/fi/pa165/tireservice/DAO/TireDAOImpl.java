/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Tire;
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
        if (tire == null) {
            throw new IllegalArgumentException("You have to set the tire");
        }

        entityManager.persist(tire);
    }

    @Transactional
    public void updateTire(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("You have to set tire");
        }
        if (tire.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity does not contain ID");
        }

        entityManager.merge(tire);
    }

    @Transactional
    public void removeTire(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("You have to set tire");
        }
        if (tire.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity does not contain ID");
        }

        entityManager.remove(getTireById(tire.getId()));
    }
}
