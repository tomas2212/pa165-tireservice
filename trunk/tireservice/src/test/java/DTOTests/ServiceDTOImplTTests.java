/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOTests;

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
 * @author Martin
 */

//je to dojebane, nefunguje to asi ot mam napicu
@RunWith(MockitoJUnitRunner.class)
public class ServiceDTOImplTTests {
    
    @Mock
    private ServiceDAO serviceDAO;
    
    @InjectMocks
    private ServiceServices service = new ServiceServicesImpl();
    
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
        List<Service> actual = serviceDAO.getAllServices();
        
        //assert
        assertEquals(servicesDTOExpected, actual);
    }
}
