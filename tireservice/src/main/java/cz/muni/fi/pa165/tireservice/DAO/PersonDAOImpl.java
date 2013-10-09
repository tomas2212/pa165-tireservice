package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Stefan Sakala (359772)
 */
public class PersonDAOImpl implements PersonDAO {
    protected EntityManager entityManager;

    public PersonDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Person getPersonById(Long id) {
        Person p = entityManager.find(Person.class, id);
        return p.isActive() ? p : null;
    }

    public List<Person> getAllPersons() {
        TypedQuery<Person> p = entityManager.createQuery("SELECT p FROM Person p WHERE p.active = :activity", Person.class);
        p.setParameter("activity", Boolean.TRUE);
        return p.getResultList();
    }

    public void insertPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("You have to set person");
        }
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
    }

    public void updatePerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("You have to set person.");
        }
        if (person.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity does not contain ID.");
        }
        entityManager.getTransaction().begin();
        entityManager.merge(person);
        entityManager.getTransaction().commit();
    }

    public void removePerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("You have to set person.");
        }
        if (person.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity does not contain ID.");
        }
        person.setActive(Boolean.FALSE);
        entityManager.getTransaction().begin();
        entityManager.merge(person);
        entityManager.getTransaction().commit();
    }
    
}
