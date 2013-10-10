
package cz.muni.fi.pa165.tireservice.DAOTests;

import cz.muni.fi.pa165.tireservice.DAO.ServiceDAO;
import cz.muni.fi.pa165.tireservice.DAO.ServiceDAOImpl;
import cz.muni.fi.pa165.tireservice.entities.Service;
import java.math.BigDecimal;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Stefan Sakala (359772)
 */
public class ServiceDAOTest extends AbstractDAOTest {
   

        @Test
        public void testInsertService() {
        
        ServiceDAO sd = new ServiceDAOImpl(em);
        
        Service serviceA = new Service();
        serviceA.setPrice(BigDecimal.valueOf(999));
        serviceA.setDescription("4 tires on standard vehicle.");
        serviceA.setActive(Boolean.TRUE);
        serviceA.setName("Change of 4 tires.");
       
        
        sd.insertService(serviceA);

        Service serviceB = sd.getServiceById(serviceA.getId());
        assertEquals(serviceA, serviceB);
    }
    
    @Test
    public void testUpdateService() {

        ServiceDAO sd = new ServiceDAOImpl(em);
        
        Service serviceA = new Service();
        serviceA.setPrice(BigDecimal.valueOf(999));
        serviceA.setDescription("4 tires on standard vehicle.");
        serviceA.setActive(Boolean.TRUE);
        serviceA.setName("Change of 4 tires.");
        
        sd.insertService(serviceA);
        
        Service serviceB = sd.getServiceById(serviceA.getId());
        serviceB.setPrice(BigDecimal.valueOf(888));

        Service serviceC = sd.getServiceById(serviceB.getId());
        assertEquals(serviceB.getPrice(), serviceC.getPrice());
    }
    
    @Test
    public void testDeleteService(){
  
        ServiceDAO sd = new ServiceDAOImpl(em);
        
        Service serviceA = new Service();
        serviceA.setPrice(BigDecimal.valueOf(999));
        serviceA.setDescription("4 tires on standard vehicle.");
        serviceA.setActive(Boolean.TRUE);
        serviceA.setName("Change of 4 tires.");
        
        sd.insertService(serviceA);
        
        Service serviceB = sd.getServiceById(serviceA.getId());
        sd.removeService(serviceB);
        
        Service serviceC = sd.getServiceById(serviceB.getId());
        assertNull(serviceC);
        
    }
    
    @Test
    public void testGetServiceById(){

        ServiceDAO sd = new ServiceDAOImpl(em);
        
        Service serviceA = new Service();
        serviceA.setPrice(BigDecimal.valueOf(999));
        serviceA.setDescription("4 tires on standard vehicle.");
        serviceA.setActive(Boolean.TRUE);
        serviceA.setName("Change of 4 tires.");
        
        sd.insertService(serviceA);
        
        Service serviceB = sd.getServiceById(serviceA.getId());
        
        assertEquals(serviceA, serviceB);
        
    }
    
    @Test
    public void testGetAllServices() {
        
        ServiceDAO sd = new ServiceDAOImpl(em);
        
        Service serviceA = new Service();
        serviceA.setPrice(BigDecimal.valueOf(999));
        serviceA.setDescription("4 tires on standard vehicle.");
        serviceA.setActive(Boolean.TRUE);
        serviceA.setName("Change of 4 tires.");
        
        sd.insertService(serviceA);
        int services = sd.getAllServices().size();
        
        Service serviceB = new Service();
        serviceB.setPrice(BigDecimal.valueOf(9999));
        serviceB.setDescription("McDonalds PREMIUM.");
        serviceB.setActive(Boolean.TRUE);
        serviceB.setName("Change of oil.");
        
        sd.insertService(serviceB);
        int services2 = sd.getAllServices().size();

        assertEquals(services + 1, services2);
    }
    
  }

