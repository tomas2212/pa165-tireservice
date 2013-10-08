package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Service;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Martin(359978)
 */
public class ServiceDAOImpl implements ServiceDAO {
    protected EntityManager entityManager;

    public ServiceDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Service getServiceById(Long id) {
        Service s = entityManager.find(Service.class, id);
        return s.isActive() ? s : null;
    }

    public List<Service> getAllServices() {
        TypedQuery<Service> s = entityManager.createQuery("SELECT s FROM Service s WHERE s.active = :activity", Service.class);
        s.setParameter("activity", Boolean.TRUE);
        return s.getResultList();
    }

    public void insertService(Service service) {
        if (service == null) {
            throw new IllegalArgumentException("You have to set service");
        }
        entityManager.getTransaction().begin();
        entityManager.persist(service);
        entityManager.getTransaction().commit();
    }

    public void updateService(Service service) {
        if (service == null) {
            throw new IllegalArgumentException("You have to set service");
        }
        if (service.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity service does not contain ID");
        }
        entityManager.getTransaction().begin();
        entityManager.merge(service);
        entityManager.getTransaction().commit();
    }

    public void removeService(Service service) {
        if (service == null) {
            throw new IllegalArgumentException("You have to set service");
        }
        if (service.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity service does not contain ID");
        }
        service.setActive(Boolean.FALSE);
        entityManager.getTransaction().begin();
        entityManager.merge(service);
        entityManager.getTransaction().commit();
    }

   
}
