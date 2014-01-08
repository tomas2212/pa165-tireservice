package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author Stefan Sakala (359772)
 */
@Repository
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

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
        String encPassword = passwordEncoder.encodePassword(person.getPassword(), person.getEmail());
        person.setPassword(encPassword);
        entityManager.persist(person);
    }

    public void updatePerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("You have to set person.");
        }
        if (person.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity does not contain ID.");
        }
        String encPassword = passwordEncoder.encodePassword(person.getPassword(), person.getEmail());
        person.setPassword(encPassword);
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

    public Person getPersonByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("You have to set email.");
        }
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Cannot search for empty email.");
        }
        TypedQuery<Person> p = entityManager.createQuery("SELECT p FROM Person p WHERE p.email = :email", Person.class);
        p.setParameter("email", email);
        List<Person> people = p.getResultList();
        if(people != null && !people.isEmpty()){
            Hibernate.initialize(people.get(0));
            return people.get(0);
        }
        
        return null;
    }
}
