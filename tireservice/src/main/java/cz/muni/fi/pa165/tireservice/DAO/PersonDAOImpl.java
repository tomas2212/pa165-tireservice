package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Stefan Sakala (359772)
 */
@Repository
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    public Person getPersonById(Long id) {
        Person p = entityManager.find(Person.class, id);
        if(p != null && p.isActive()){
            Hibernate.initialize(p.getOrders());
        }
        return p != null && p.isActive() ? p : null;
    }

    public List<Person> getAllPersons() {
        TypedQuery<Person> p = entityManager.createQuery("SELECT p FROM Person p WHERE p.active = :activity", Person.class);
        p.setParameter("activity", Boolean.TRUE);
        List<Person> people = p.getResultList();
        for(Person person : people){
            Hibernate.initialize(person.getOrders());
        }
        return p.getResultList();
    }

    public void insertPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("You have to set person");
        }

        entityManager.persist(person);
    }

    public void updatePerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("You have to set person.");
        }
        if (person.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity does not contain ID.");
        }

        entityManager.merge(person);
    }

    public void removePerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("You have to set person.");
        }
        if (person.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity does not contain ID.");
        }
        person.setActive(Boolean.FALSE);
        entityManager.merge(person);
    }
}
