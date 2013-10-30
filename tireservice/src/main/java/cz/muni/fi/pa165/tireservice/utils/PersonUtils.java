/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.utils;

import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.entities.Person;

/**
 *
 * @author Martin
 */
public class PersonUtils {
    
    private PersonUtils(){};
    

    public static Person personDTOToEntity(PersonDTO personDTO) {
        ValidationHelper.ArgumentNull(personDTO);
        ValidationHelper.IdIsZero(personDTO.getId());
        
        Person person = new Person();
        
        person.setId(personDTO.getId());
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setAddress(personDTO.getAddress());
        person.setPhoneNumber(personDTO.getPhoneNumber());
        person.setPassword(personDTO.getPassword());
        person.setActive(personDTO.isActive());
        person.setIsServiceman(personDTO.isIsServiceman());
        
        person.setOrders(personDTO.getOrders());  
        
        return person;
    }
    // TODO dorobit (setnut)zoznam orderov kazdemu personovi
    public static PersonDTO getPersonDTOFromEntity(Person person){
//        ValidationHelper.ArgumentNull(person);
        if(person == null){
            return null;
        }
        ValidationHelper.IdIsZero(person.getId());
        
        PersonDTO personDTO = new PersonDTO();
        
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setAddress(person.getAddress());
        personDTO.setPhoneNumber(person.getPhoneNumber());
        personDTO.setPassword(person.getPassword());
        personDTO.setActive(person.isActive());
        personDTO.setIsServiceman(person.isIsServiceman());
        
        personDTO.setOrders(person.getOrders());
        
        return personDTO;
    }
}
