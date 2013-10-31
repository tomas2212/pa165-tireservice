/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DTOTests;

import cz.muni.fi.pa165.tireservice.dao.ServiceDAO;
import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.entities.Service;
import cz.muni.fi.pa165.tireservice.services.ServiceServices;
import cz.muni.fi.pa165.tireservice.services.ServiceServicesImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
/**
 *
 * @author Martin Makarsky 359978
 */

@RunWith(MockitoJUnitRunner.class)
public class ServiceDTOImplTTests {
    
    @Mock
    private ServiceDAO serviceDAO;
    
    @InjectMocks
    private ServiceServices service = new ServiceServicesImpl();
    
    @Test
    public void getServiceIdIsRight(){
        //arrange
        Service s = new Service();
        s.setActive(true);
        s.setName("Change tires");
        
        ServiceDTO expected = new ServiceDTO();
        expected.setActive(true);
        expected.setName("Change tires");
        
        when(serviceDAO.getServiceById(1l)).thenReturn(s);
        
        //acr
        ServiceDTO actual = service.getServiceById(1l);
        
        //assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void getServiceIdIsWrong() {
        //arrange
        Service s = new Service();
        s.setActive(true);
        s.setName("Change tires");
        
        when(serviceDAO.getServiceById(2l)).thenReturn(s);
        
        //act
        ServiceDTO actual = service.getServiceById(1l);
        
        //assert
        assertNull(actual);
    }
     
    @Test
    public void getAllServices() {
        
        //arrange
        Service s1 = new Service();
        s1.setActive(true);
        s1.setName("Service 1");

        Service s2 = new Service();
        s2.setActive(true);
        s2.setName("Service 2");
        
        List<Service> services = new ArrayList<Service>();
        services.add(s1);
        services.add(s2);
        
        ServiceDTO s1DTO = new ServiceDTO();
        s1DTO.setActive(true);
        s1DTO.setName("Service 1");
        
        ServiceDTO s2DTO = new ServiceDTO();
        s2DTO.setActive(true);
        s2DTO.setName("Service 2");
        
        List<ServiceDTO> servicesDTOExpected = new ArrayList<ServiceDTO>();
        servicesDTOExpected.add(s1DTO);
        servicesDTOExpected.add(s2DTO);
        
        when(serviceDAO.getAllServices()).thenReturn(services);
        
        //act
        List<ServiceDTO> actual = service.getAllServices();
        
        //assert
        assertEquals(servicesDTOExpected, actual);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateServiceThrowsExceptionBecauseObjectISNull() {
        service.updateService(null);
        fail();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateServiceThrowsException() {
        ServiceDTO s = new ServiceDTO();
        
        service.updateService(s);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void removeServiceThrowsExceptionBecauseObjectIsNull(){
        service.removeService(null);
        
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ServiceThrowsExceptionIdIsNull(){
       ServiceDTO s = new ServiceDTO();
       s.setId(0l);
        
       service.removeService(null);
       fail();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void createServiceThrowsExceptionObjectIsNull(){
       service.createService(null);
       fail();
    }
}
