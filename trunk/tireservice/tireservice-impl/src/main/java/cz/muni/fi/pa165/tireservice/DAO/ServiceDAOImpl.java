package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Service;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin(359978)
 */
@Repository
public class ServiceDAOImpl implements ServiceDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    public Service getServiceById(Long id) {
        Service s = entityManager.find(Service.class, id);
        if (s != null && s.isActive()) {
            Hibernate.initialize(s.getOrders());
        }
        return s != null && s.isActive() ? s : null;
    }

    public List<Service> getAllServices() {
        TypedQuery<Service> s = entityManager.createQuery("SELECT s FROM Service s WHERE s.active = :activity", Service.class);
        s.setParameter("activity", Boolean.TRUE);
        List<Service> services = s.getResultList();
        for (Service service : services) {
            Hibernate.initialize(service.getOrders());
        }
        return s.getResultList();
    }

    public List<Service> getAllActiveServices() {
        TypedQuery<Service> s = entityManager.createQuery("SELECT s FROM Service s WHERE s.active = :activity", Service.class);
        s.setParameter("activity", Boolean.TRUE);
        List<Service> services = s.getResultList();
        for (Service service : services) {
            Hibernate.initialize(service.getOrders());
        }
        return s.getResultList();
    }

    public void insertService(Service service) {
        if (service == null) {
            throw new IllegalArgumentException("You have to set service");
        }
        entityManager.persist(service);
    }

    public void updateService(Service service) {
        if (service == null) {
            throw new IllegalArgumentException("You have to set service");
        }
        if (service.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity service does not contain ID");
        }
        entityManager.merge(service);
    }

    public void removeService(Service service) {
        if (service == null) {
            throw new IllegalArgumentException("You have to set service");
        }
        if (service.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity service does not contain ID");
        }
        service.setActive(Boolean.FALSE);
        entityManager.merge(service);
    }
}
