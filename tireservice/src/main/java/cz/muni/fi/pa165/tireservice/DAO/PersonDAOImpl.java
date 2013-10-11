package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Stefan Sakala(359772)
 */
@Repository
public class PersonDAOImpl implements PersonDAO {
    
    @PersistenceContext
    protected EntityManager entityManager;
    
    @Transactional
    public Person getPersonById(Long id) {
        Person p = entityManager.find(Person.class, id);
        return p.isActive() ? p : null;
    }

    @Transactional
    public List<Person> getAllPersons() {
        TypedQuery<Person> p = entityManager.createQuery("SELECT p FROM Person p WHERE p.active = :activity", Person.class);
        p.setParameter("activity", Boolean.TRUE);
        return p.getResultList();
    }

    @Transactional
    public void insertPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("You have to set person");
        }
        
        entityManager.persist(person);
    }

    @Transactional
    public void updatePerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("You have to set person.");
        }
        if (person.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity does not contain ID.");
        }
        
        entityManager.merge(person);
    }

    @Transactional
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
