package cz.muni.fi.pa165.tireservice.utils;

import cz.muni.fi.pa165.tireservice.dto.OrderDTO;
import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.entities.Order;
import cz.muni.fi.pa165.tireservice.entities.Person;
import java.util.ArrayList;
import java.util.List;

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
        
        // when we insert a person he has no orders created
        //person.setOrders(personDTO.getOrders());  
        
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
        
        
        List<Order> listOrders = person.getOrders();
        if(listOrders != null && !listOrders.isEmpty()){
            List<OrderDTO> listOrdersDTO = new ArrayList<OrderDTO>();
            for(Order order : listOrders){
                OrderDTO orderDTO = OrderUtils.getOrderDTOFromEntity(order);
                listOrdersDTO.add(orderDTO);
            }
            personDTO.setOrders(listOrdersDTO);
        }
        
        return personDTO;
    }
}
