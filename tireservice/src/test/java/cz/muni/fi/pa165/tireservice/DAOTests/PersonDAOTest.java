package cz.muni.fi.pa165.tireservice.DAOTests;

import cz.muni.fi.pa165.tireservice.DAO.PersonDAO;
import cz.muni.fi.pa165.tireservice.DAO.PersonDAOImpl;
import cz.muni.fi.pa165.tireservice.entities.Person;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.jboss.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Atares //
 */
public class PersonDAOTest extends AbstractDAOTest {
    
    @Test
    public void testGetAllPersons() {
        Logger log = Logger.getLogger(PersonDAOTest.class);
        log.error("fds"); //preco fds?
        
        PersonDAO pd = new PersonDAOImpl(em);
        
        //Person1
        Person person1 = new Person();
        person1.setFirstName("Joe");
        person1.setLastName("Black");
        person1.setAddress("Elysian Fields, New York, NY");
        person1.setPhoneNumber("+555 586 358");
        person1.setPassword("nbusr123");
        person1.setActive(Boolean.TRUE);
        person1.setIsServiceman(Boolean.TRUE);
        
        pd.insertPerson(person1);
        int countOfPeople1 = pd.getAllPersons().size();
        
        //Person2
        Person person2 = new Person();
        person2.setFirstName("William");
        person2.setLastName("Parrish");
        person2.setAddress("Elysian Fields, New York, NY");
        person2.setPhoneNumber("+555 586 358");
        person2.setPassword("pass");
        person2.setActive(Boolean.TRUE);
        person2.setIsServiceman(Boolean.FALSE);
        
        pd.insertPerson(person2);
        int countOfPeople2 = pd.getAllPersons().size();
        
        //Person3
        Person person3 = new Person();
        person3.setFirstName("Eddie");
        person3.setLastName("Parrish");
        person3.setAddress("Elysian Fields, San Francisco, CA");
        person3.setPhoneNumber("+555 222 358");
        person3.setPassword("password");
        person3.setActive(Boolean.TRUE);
        person3.setIsServiceman(Boolean.FALSE);
        
        pd.insertPerson(person3);
        int countOfPeople3 = pd.getAllPersons().size();
        
        assertEquals(countOfPeople1 + 2, countOfPeople3);   
    }
    
    @Test
    public void testInsertPerson() {
        Logger log = Logger.getLogger(PersonDAOTest.class);
        log.error("fds"); //preco fds?
        
        PersonDAO pd = new PersonDAOImpl(em);
        
        Person person1 = new Person();
        person1.setFirstName("Joe");
        person1.setLastName("Black");
        person1.setAddress("Elysian Fields, New York City, NY");
        person1.setPhoneNumber("+555 586 358");
        person1.setPassword("nbusr123");
        person1.setActive(Boolean.TRUE);
        person1.setIsServiceman(Boolean.TRUE);
        
        pd.insertPerson(person1);
        
        Person person2 = pd.getPersonById(person1.getId());
        assertEquals(person1, person2);        
    }
    
    @Test
    public void testUpdatePerson() {
        Logger log = Logger.getLogger(PersonDAOTest.class);
        log.error("fds"); //preco fds?
        
        PersonDAO pd = new PersonDAOImpl(em);
        
        Person person1 = new Person();
        person1.setFirstName("Joe");
        person1.setLastName("Black");
        person1.setAddress("Elysian Fields, New York City, NY");
        person1.setPhoneNumber("+555 586 358");
        person1.setPassword("nbusr123");
        person1.setActive(Boolean.TRUE);
        person1.setIsServiceman(Boolean.TRUE);
        
        pd.insertPerson(person1);
        
        Person person2 = pd.getPersonById(person1.getId());
        person2.setActive(Boolean.FALSE);
        
        Person person3 = pd.getPersonById(person2.getId());
        assertEquals(person2.isActive(), person3.isActive());
    }
    
    @Test
    public void testGetPersonByID() {
        Logger log = Logger.getLogger(PersonDAOTest.class);
        log.error("fds"); //preco fds?
        
        PersonDAO pd = new PersonDAOImpl(em);
        
        Person person1 = new Person();
        person1.setFirstName("Joe");
        person1.setLastName("White");
        person1.setAddress("Elysian Fields, New York City, NY");
        person1.setPhoneNumber("+555 586 358");
        person1.setPassword("nbusr123");
        person1.setActive(Boolean.TRUE);
        person1.setIsServiceman(Boolean.FALSE);
        
        pd.insertPerson(person1);
        
        Person person2 = pd.getPersonById(person1.getId());
        
        assertEquals(person1, person2);
    }
    
   //TODO dorobit test na disablovanie
}
