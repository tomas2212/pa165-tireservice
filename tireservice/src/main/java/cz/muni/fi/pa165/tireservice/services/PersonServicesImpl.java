/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.dao.PersonDAO;
import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.entities.Person;
import cz.muni.fi.pa165.tireservice.utils.PersonUtils;
import cz.muni.fi.pa165.tireservice.utils.ValidationHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Martin
 */
public class PersonServicesImpl implements PersonServices{

    @Autowired
    private PersonDAO personDAO;
    
    public PersonDTO getPersonById(Long id) {
        ValidationHelper.IdIsZero(id);
        Person person = personDAO.getPersonById(id);
        PersonDTO personDTO = PersonUtils.getPersonDTOFromEntity(person);
        return personDTO;
    }

    public List<PersonDTO> getAllPersons() {
        List<PersonDTO> toReturn = new ArrayList<PersonDTO>();
        List<Person> persons = personDAO.getAllPersons();
        for(Person person : persons) {
            PersonDTO personDTO = PersonUtils.getPersonDTOFromEntity(person);
            toReturn.add(personDTO);
        }
        return toReturn;
    }

    public void insertPerson(PersonDTO personDTO) {
        ValidationHelper.ArgumentNull(personDTO);
        ValidationHelper.ArgumentIsNull(personDTO.getId());
        ValidationHelper.IdIsZero(personDTO.getId());
        
        Person person = PersonUtils.personDTOToEntity(personDTO);
        personDAO.updatePerson(person);
    }

    public void updatePerson(PersonDTO personDTO) {
        ValidationHelper.ArgumentNull(personDTO);
        ValidationHelper.ArgumentIsNull(personDTO.getId());
        ValidationHelper.IdIsZero(personDTO.getId());
        
        Person person = PersonUtils.personDTOToEntity(personDTO);
        personDAO.updatePerson(person);
    }

    public void removePerson(PersonDTO personDTO) {
        ValidationHelper.ArgumentNull(personDTO);
        ValidationHelper.ArgumentIsNull(personDTO.getId());
        ValidationHelper.IdIsZero(personDTO.getId());
        
        Person person = PersonUtils.personDTOToEntity(personDTO);
        personDAO.removePerson(person);
    }
    
}
