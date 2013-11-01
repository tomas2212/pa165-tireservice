package cz.muni.fi.pa165.tireservice.servicestests;

import cz.muni.fi.pa165.tireservice.dao.PersonDAO;
import cz.muni.fi.pa165.tireservice.dao.PersonDAOImpl;
import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.entities.Person;
import cz.muni.fi.pa165.tireservice.services.PersonServices;
import cz.muni.fi.pa165.tireservice.services.PersonServicesImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import org.mockito.Spy;

/**
 *
 * @author Jakub Papcun(359 474)
 */
@RunWith(MockitoJUnitRunner.class)
public class ServicePersonImplTests {

    @Spy
    private PersonDAO personDAO = new PersonDAOImpl();
    
    @InjectMocks
    private PersonServices personService = new PersonServicesImpl();

    @Test
    public void getPersonIdIsRight() {
        Person p = new Person();
        p.setActive(true);
        p.setFirstName("Janko");

        PersonDTO expected = new PersonDTO();
        expected.setActive(true);
        expected.setFirstName("Janko");

        doReturn(p).when(personDAO).getPersonById(anyLong());

        PersonDTO actual = personService.getPersonById(1l);

        assertEquals(expected, actual);
    }

    @Test
    public void getPersonIdIsWrong() {
        doReturn(null).when(personDAO).getPersonById(anyLong());

        PersonDTO actual = personService.getPersonById(1l);

        assertNull(actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullPerson() {
        personService.updatePerson(null);
        fail("IllegalArgument expected as the person for updating was NULL");
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePersonWithNullId() {
        PersonDTO p = new PersonDTO();
        personService.updatePerson(p);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullPerson() {
        personService.removePerson(null);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void removePersonWithNullId() {
        PersonDTO p = new PersonDTO();
        personService.removePerson(p);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertNullPerson() {
        personService.insertPerson(null);
        fail("IllegalArgument expected");
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertPersonDTOWithId() {
        PersonDTO p = new PersonDTO();
        p.setId(1L);
        personService.insertPerson(p);
        fail("Creating person with ID which is set is forbidden");
    }

    @Test
    public void getAllPersons() {

        Person p1 = new Person();
        p1.setFirstName("janko");
        p1.setActive(true);

        Person p2 = new Person();
        p2.setFirstName("misko");
        p2.setActive(true);

        List<Person> persons = new ArrayList<Person>();
        persons.add(p1);
        persons.add(p2);

        PersonDTO pDto1 = new PersonDTO();
        pDto1.setFirstName("janko");
        pDto1.setActive(true);

        PersonDTO pDto2 = new PersonDTO();
        pDto2.setFirstName("misko");
        pDto2.setActive(true);

        List<PersonDTO> expectedPersons = new ArrayList<PersonDTO>();
        expectedPersons.add(pDto1);
        expectedPersons.add(pDto2);

        doReturn(persons).when(personDAO).getAllPersons();

        List<PersonDTO> actualPersons = personService.getAllPersons();

        assertEquals(expectedPersons, actualPersons);

    }
}
