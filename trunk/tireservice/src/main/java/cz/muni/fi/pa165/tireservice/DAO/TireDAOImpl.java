/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Tire;
import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ivan
 */
public class TireDAOImpl implements TireDAO {
    protected EntityManager entityManager;

    public TireDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public TireType getTireById(Long id) {
        return entityManager.find(TireType.class, id);
    }

    public List<Tire> getAllTires() {
        return entityManager.createQuery("SELECT t FROM Tire t").getResultList();
    }

    public void insertTire(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("You have to set the tire");
        }
        entityManager.getTransaction().begin();
        entityManager.persist(tire);
        entityManager.getTransaction().commit();
    }

    public void updateTire(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("You have to set tire");
        }
        if (tire.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity does not contain ID");
        }
        entityManager.getTransaction().begin();
        entityManager.merge(tire);
        entityManager.getTransaction().commit();
    }

    public void removeTire(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("You have to set tire");
        }
        if (tire.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity does not contain ID");
        }
        
        entityManager.getTransaction().begin();
        entityManager.remove(tire);
        entityManager.getTransaction().commit();
    }
}
