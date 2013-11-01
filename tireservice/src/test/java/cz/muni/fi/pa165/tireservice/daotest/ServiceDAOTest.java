package cz.muni.fi.pa165.tireservice.daotest;

import cz.muni.fi.pa165.tireservice.dao.ServiceDAO;
import cz.muni.fi.pa165.tireservice.entities.Service;
import java.math.BigDecimal;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Stefan Sakala (359772)
 */
public class ServiceDAOTest extends AbstractDAOTest {

    @Autowired
    ServiceDAO serviceDAO;

    @Test
    public void testInsertService() {

        Service serviceA = new Service();
        serviceA.setPrice(BigDecimal.valueOf(999));
        serviceA.setDescription("4 tires on standard vehicle.");
        serviceA.setActive(Boolean.TRUE);
        serviceA.setName("Change of 4 tires.");

        serviceDAO.insertService(serviceA);

        Service serviceB = serviceDAO.getServiceById(serviceA.getId());

        assertEquals(serviceA, serviceB);
    }

    @Test
    public void testUpdateService() {

        Service serviceA = new Service();
        serviceA.setPrice(BigDecimal.valueOf(999));
        serviceA.setDescription("4 tires on standard vehicle.");
        serviceA.setActive(Boolean.TRUE);
        serviceA.setName("Change of 4 tires.");

        serviceDAO.insertService(serviceA);

        Service serviceB = serviceDAO.getServiceById(serviceA.getId());
        serviceB.setPrice(BigDecimal.valueOf(888l));

        serviceDAO.updateService(serviceB);

        Service serviceC = serviceDAO.getServiceById(serviceB.getId());
        assertEquals(serviceB.getPrice(), serviceC.getPrice());
    }

    @Test
    public void testDisableService() {

        Service serviceA = new Service();
        serviceA.setPrice(BigDecimal.valueOf(999l));
        serviceA.setDescription("4 tires on standard vehicle.");
        serviceA.setActive(Boolean.TRUE);
        serviceA.setName("Change of 4 tires.");

        serviceDAO.insertService(serviceA);

        Service serviceB = serviceDAO.getServiceById(serviceA.getId());
        serviceDAO.removeService(serviceB);

        Service serviceC = serviceDAO.getServiceById(serviceB.getId());
        assertNull(serviceC);

    }

    @Test
    public void testGetServiceById() {

        Service serviceA = new Service();
        serviceA.setPrice(BigDecimal.valueOf(999l));
        serviceA.setDescription("4 tires on standard vehicle.");
        serviceA.setActive(Boolean.TRUE);
        serviceA.setName("Change of 4 tires.");

        serviceDAO.insertService(serviceA);

        Service serviceB = serviceDAO.getServiceById(serviceA.getId());

        assertEquals(serviceA, serviceB);

    }

    @Test
    public void testGetAllServices() {

        Service serviceA = new Service();
        serviceA.setPrice(BigDecimal.valueOf(999));
        serviceA.setDescription("4 tires on standard vehicle.");
        serviceA.setActive(Boolean.TRUE);
        serviceA.setName("Change of 4 tires.");

        serviceDAO.insertService(serviceA);
        int services = serviceDAO.getAllActiveServices().size();

        Service serviceB = new Service();
        serviceB.setPrice(BigDecimal.valueOf(9999l));
        serviceB.setDescription("McDonalds PREMIUM.");
        serviceB.setActive(Boolean.TRUE);
        serviceB.setName("Change of oil.");

        serviceDAO.insertService(serviceB);
        int services2 = serviceDAO.getAllActiveServices().size();

        assertEquals(services + 1, services2);
    }
}
