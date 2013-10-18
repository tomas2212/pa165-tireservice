package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entities.Person;
import java.util.List;

/**
 *
 *  The DAO layer for operations with the data in the Person table.
 * 
 * @author Stefan Sakala (359772)
 */
public interface PersonDAO {
    
    /**
     * Returns person with active status by id from the database
     * 
     * @param id the ID of person
     * @return Person with active status by id from the database
     */    
    public Person getPersonById(Long id);
    
    /**
     * Returns all persons with active status
     * 
     * @return All persons with active status
     */
    public List<Person> getAllPersons();
    
     /**
     * Adds new person into database
     * 
     * @param person The person that will be inserted into DB  
     */
    public void insertPerson (Person person);
    
    /**
     * Updates the person's attributes
     * 
     * @param person The person which will be updated
     */
    public void updatePerson (Person person);
    
    /**
     * Removes person from the list of active persons 
     * (sets the active attribute to false)
     * 
     * @param person The person which will be removed(set inactive).
     */
    public void removePerson (Person person);
    
}