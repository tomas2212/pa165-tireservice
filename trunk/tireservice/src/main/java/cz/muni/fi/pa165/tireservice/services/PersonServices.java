package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import java.util.List;

/**
 *
 * @author Martin Makarsky 359978
 */
public interface PersonServices {
     /**
     * Gets person with active status by id from the database
     * 
     * @param id the ID of person
     * @return Person with active status by id from the database
     */    
    public PersonDTO getPersonById(Long id);
    
    /**
     * Gets all persons with active status
     * 
     * @return All persons with active status
     */
    public List<PersonDTO> getAllPersons();
    
     /**
     * Adds new person into database
     * 
     * @param personDTO The person that will be inserted into DB  
     */
    public void insertPerson (PersonDTO personDTO);
    
    /**
     * Updates the person's attributes
     * 
     * @param personDTO The person which will be updated
     */
    public void updatePerson (PersonDTO personDTO);
    
    /**
     * Removes person from the list of active persons 
     * (sets the active attribute to false)
     * 
     * @param personDTO The person which will be removed(set inactive).
     */
    public void removePerson (PersonDTO personDTO);
}
