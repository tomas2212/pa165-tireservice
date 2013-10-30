/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DTOTests;

import cz.muni.fi.pa165.tireservice.dao.PersonDAO;
import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.entities.Person;
import cz.muni.fi.pa165.tireservice.services.PersonServices;
import cz.muni.fi.pa165.tireservice.services.PersonServicesImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

/**
 *
 * @author Jakub Papcun(359 474)
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonDTOImplTests {
    
    @Mock
    private PersonDAO personDAO;
    
    @InjectMocks
    private PersonServices personService = new PersonServicesImpl();
    
    @Test
    public void getPersonIdIsRight(){
       Person p = new Person();
       p.setActive(true);
       p.setFirstName("Janko");
       
       PersonDTO expected = new PersonDTO();
       expected.setActive(true);
       expected.setFirstName("Janko");
       
       when(personDAO.getPersonById(1l)).thenReturn(p);
       
       PersonDTO actual = personService.getPersonById(1l);
       
       assertEquals(expected, actual);
    }
    
    @Test
    public void getPersonIdIsWrong(){
       Person p = new Person();
       p.setActive(true);
       p.setFirstName("Janko");
       
       when(personDAO.getPersonById(2l)).thenReturn(p);
       
       PersonDTO actual = personService.getPersonById(1l);
       
       assertNull(actual);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void updateNullPerson(){
        personService.updatePerson(null);
        fail("IllegalArgument expected as the person for updating was NULL");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void updatePersonWithId(){
        PersonDTO p = new PersonDTO();
        p.setId(1L);
        personService.updatePerson(p);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void removeNullPerson(){
        personService.removePerson(null);
        
        fail();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void removePersonWithId(){
        PersonDTO p = new PersonDTO();
        p.setId(1L);
        personService.removePerson(p);
       fail();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void insertNullPerson(){
        personService.insertPerson(null);
        fail("IllegalArgument expected");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void insertPersonDTOWithId(){
        PersonDTO p = new PersonDTO();
        p.setId(1L);
        personService.insertPerson(p);
        fail("Creating person with ID which is set is forbidden");
    }
    
    @Test
    public void getAllPersons(){
        
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
       
        when(personDAO.getAllPersons()).thenReturn(persons);
        
        List<PersonDTO> actualPersons = personService.getAllPersons();
        
        assertEquals(expectedPersons, actualPersons);
              
    }
    
//    @Test
//    public void updatePerson(){
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setFirstName("John");
//        personDTO.setLastName("Makovicka");
//        personDTO.setAddress("Brnenska ulica");
//        personDTO.setPhoneNumber("+420 111 111");
//        personDTO.setActive(true);
//        
//        
//    }
//    
//    @Test
//    public void removePerson(){
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setFirstName("John");
//        personDTO.setLastName("Makovicka");
//        personDTO.setAddress("Brnenska ulica");
//        personDTO.setPhoneNumber("+420 111 111");
//        personDTO.setActive(true);
//        
//        
//        
//    }
//    
//    @Test
//    public void getPersonById(){
//    
//    }
//    
//    private PersonDTO makePersonDTO() {
//        PersonDTO person = new PersonDTO();
//        person.setFirstName("John");
//        person.setLastName("Makovicka");
//        person.setAddress("Brnenska ulica");
//        person.setPhoneNumber("+420 111 111");
//        person.setActive(true);
//        return person;
//    }
//    
//    private Person makePersonEntity() {
//        Person person = new Person();
//        person.setFirstName("John");
//        person.setLastName("Makovicka");
//        person.setAddress("Brnenska ulica");
//        person.setPhoneNumber("+420 111 111");
//        person.setActive(true);
//        return person;
//    }
    
}
