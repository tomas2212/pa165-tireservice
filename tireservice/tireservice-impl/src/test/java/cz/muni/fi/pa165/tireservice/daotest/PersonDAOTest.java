package cz.muni.fi.pa165.tireservice.daotest;

import cz.muni.fi.pa165.tireservice.DAO.PersonDAO;
import cz.muni.fi.pa165.tireservice.entities.Person;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Martin Makarsky
 */
public class PersonDAOTest extends AbstractDAOTest {

    @Autowired
    PersonDAO personDAO;

    @Test
    public void testGetAllPersons() {
        Person person1 = new Person();
        person1.setEmail("joe.black@yesman.com");
        person1.setFirstName("Joe");
        person1.setLastName("Black");
        person1.setAddress("Elysian Fields, New York, NY");
        person1.setPhoneNumber("+555 586 358");
        person1.setPassword("nbusr123");
        person1.setActive(Boolean.TRUE);
        person1.setIsServiceman(Boolean.TRUE);

        personDAO.insertPerson(person1);
        int countOfPeople1 = personDAO.getAllPersons().size();


        Person person2 = new Person();
        person2.setEmail("william.parrish@yesman.com");
        person2.setFirstName("William");
        person2.setLastName("Parrish");
        person2.setAddress("Elysian Fields, New York, NY");
        person2.setPhoneNumber("+555 586 358");
        person2.setPassword("pass");
        person2.setActive(Boolean.TRUE);
        person2.setIsServiceman(Boolean.FALSE);

        personDAO.insertPerson(person2);
        int countOfPeople2 = personDAO.getAllPersons().size();


        Person person3 = new Person();
        person3.setEmail("eddie.parrish@yesman.com");
        person3.setFirstName("Eddie");
        person3.setLastName("Parrish");
        person3.setAddress("Elysian Fields, San Francisco, CA");
        person3.setPhoneNumber("+555 222 358");
        person3.setPassword("password");
        person3.setActive(Boolean.TRUE);
        person3.setIsServiceman(Boolean.FALSE);

        personDAO.insertPerson(person3);
        int countOfPeople3 = personDAO.getAllPersons().size();

        assertEquals(countOfPeople1 + 2, countOfPeople3);
    }

    @Test
    public void testInsertPerson() {

        Person person1 = new Person();
        person1.setEmail("joe.black@yesman.com");
        person1.setFirstName("Joe");
        person1.setLastName("Black");
        person1.setAddress("Elysian Fields, New York City, NY");
        person1.setPhoneNumber("+555 586 358");
        person1.setPassword("nbusr123");
        person1.setActive(Boolean.TRUE);
        person1.setIsServiceman(Boolean.TRUE);

        personDAO.insertPerson(person1);
        Person person2 = personDAO.getPersonById(person1.getId());
        System.err.println(person1.equals(person2));
        assertEquals(person1, person2);
    }

    @Test
    public void testUpdatePerson() {
        Person person1 = new Person();
        person1.setEmail("joe.black@yesman.com");
        person1.setFirstName("Joe");
        person1.setLastName("Black");
        person1.setAddress("Elysian Fields, New York City, NY");
        person1.setPhoneNumber("+555 586 358");
        person1.setPassword("nbusr123");
        person1.setActive(Boolean.TRUE);
        person1.setIsServiceman(Boolean.TRUE);

        personDAO.insertPerson(person1);

        Person person2 = personDAO.getPersonById(person1.getId());
        person2.setLastName("White");
        personDAO.updatePerson(person2);
        Person person3 = personDAO.getPersonById(person2.getId());
        assertEquals(person2.getLastName(), person3.getLastName());
    }

    @Test
    public void testGetPersonByID() {
        Person person1 = new Person();
        person1.setEmail("joe.black@yesman.com");
        person1.setFirstName("Joe");
        person1.setLastName("White");
        person1.setAddress("Elysian Fields, New York City, NY");
        person1.setPhoneNumber("+555 586 358");
        person1.setPassword("nbusr123");
        person1.setActive(Boolean.TRUE);
        person1.setIsServiceman(Boolean.FALSE);

        personDAO.insertPerson(person1);

        Person person2 = personDAO.getPersonById(person1.getId());

        assertEquals(person1, person2);
    }

    @Test
    public void testRemovePerson() {
        Person person1 = new Person();
        person1.setEmail("joe.black@yesman.com");
        person1.setFirstName("Joe");
        person1.setLastName("White");
        person1.setAddress("Elysian Fields, New York City, NY");
        person1.setPhoneNumber("+555 586 358");
        person1.setPassword("nbusr123");
        person1.setActive(Boolean.TRUE);
        person1.setIsServiceman(Boolean.FALSE);

        personDAO.insertPerson(person1);

        Person person2 = personDAO.getPersonById(person1.getId());
        personDAO.removePerson(person2);

        Person person3 = personDAO.getPersonById(person2.getId());
        assertNull(person3);

    }
}
