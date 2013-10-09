package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Person;
import java.util.List;

/**
 *
 * @author Stefan Sakala (359772)
 */
public interface PersonDAO {
    
    public Person getPersonById(Long id);
    
    public List<Person> getAllPersons();
    
    public void insertPerson (Person person);
    
    public void updatePerson (Person person);
    
    public void removePerson (Person person);
    
}