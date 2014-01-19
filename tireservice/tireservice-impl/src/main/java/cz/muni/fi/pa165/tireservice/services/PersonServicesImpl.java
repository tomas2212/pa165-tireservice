package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.DAO.PersonDAO;
import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.entities.Person;
import cz.muni.fi.pa165.tireservice.utils.PersonUtils;
import cz.muni.fi.pa165.tireservice.utils.ValidationHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Makarsky 359978
 */
@Service
public class PersonServicesImpl implements PersonServices{

    @Autowired
    private PersonDAO personDAO;
    

    @Transactional
    public PersonDTO getPersonById(Long id) {
        ValidationHelper.IdIsZero(id);
        Person person = personDAO.getPersonById(id);
        PersonDTO personDTO = PersonUtils.getPersonDTOFromEntity(person);
        return personDTO;
    }

  
    @Transactional
    public List<PersonDTO> getAllPersons() {
        List<PersonDTO> toReturn = new ArrayList<PersonDTO>();
        List<Person> persons = personDAO.getAllPersons();
        for(Person person : persons) {
            PersonDTO personDTO = PersonUtils.getPersonDTOFromEntity(person);
            toReturn.add(personDTO);
        }
        return toReturn;
    }

    @Transactional
    public void insertPerson(PersonDTO personDTO) {
        ValidationHelper.ArgumentNull(personDTO);
        ValidationHelper.ArgumentIsNull(personDTO.getId());
        ValidationHelper.IdIsZero(personDTO.getId());
        
        Person person = PersonUtils.personDTOToEntity(personDTO);
        personDAO.insertPerson(person);
    }


    @Transactional
    public void updatePerson(PersonDTO personDTO) {
        ValidationHelper.ArgumentNull(personDTO);
        ValidationHelper.ArgumentNull(personDTO.getId());
        ValidationHelper.IdIsZero(personDTO.getId());
        
        Person person = PersonUtils.personDTOToEntity(personDTO);
        personDAO.updatePerson(person);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void removePerson(PersonDTO personDTO) {
        ValidationHelper.ArgumentNull(personDTO);
        ValidationHelper.ArgumentNull(personDTO.getId());
        ValidationHelper.IdIsZero(personDTO.getId());
        
        Person person = PersonUtils.personDTOToEntity(personDTO);
        personDAO.removePerson(person);
    }


    public PersonDTO getPersonByEmail(String email) {
        Person person = personDAO.getPersonByEmail(email);
        PersonDTO personDTO = PersonUtils.getPersonDTOFromEntity(person);
        return personDTO;
    }
    
}
