package cz.muni.fi.pa165.tireservice.servicestests;

import cz.muni.fi.pa165.tireservice.DAO.ServiceDAO;
import cz.muni.fi.pa165.tireservice.DAO.ServiceDAOImpl;
import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.entities.Service;
import cz.muni.fi.pa165.tireservice.services.ServiceServices;
import cz.muni.fi.pa165.tireservice.services.ServiceServicesImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Martin Makarsky 359978
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceServiceImplTests {
    
    @Spy
    private ServiceDAO serviceDAO = new ServiceDAOImpl();
    
    @InjectMocks
    private ServiceServices serviceServices = new ServiceServicesImpl();
    
    @Test
    public void getServiceIdIsRight(){
        //arrange
        Service s = new Service();
        s.setActive(true);
        s.setName("Change tires");
        
        ServiceDTO expected = new ServiceDTO();
        expected.setActive(true);
        expected.setName("Change tires");
        
        doReturn(s).when(serviceDAO).getServiceById(anyLong());
        
        //acr
        ServiceDTO actual = serviceServices.getServiceById(1l);
        
        //assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void getServiceIdIsWrong() {
        //arrange
        doReturn(null).when(serviceDAO).getServiceById(anyLong());
        
        //act
        ServiceDTO actual = serviceServices.getServiceById(1l);
        
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
        
        doReturn(services).when(serviceDAO).getAllServices();
        
        //act
        List<ServiceDTO> actual = serviceServices.getAllServices();
        
        //assert
        assertEquals(servicesDTOExpected, actual);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateServiceThrowsExceptionBecauseObjectISNull() {
        serviceServices.updateService(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateServiceThrowsException() {
        ServiceDTO s = new ServiceDTO();
        s.setPrice(BigDecimal.ZERO);
        s.setId(null);
        
        serviceServices.updateService(s);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void removeServiceThrowsExceptionBecauseObjectIsNull(){
        serviceServices.removeService(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeServiceThrowsExceptionIdIsNull(){
       ServiceDTO s = new ServiceDTO();
       s.setId(0l);
        
       serviceServices.removeService(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void createServiceThrowsExceptionObjectIsNull(){
       serviceServices.createService(null);
    }
}
