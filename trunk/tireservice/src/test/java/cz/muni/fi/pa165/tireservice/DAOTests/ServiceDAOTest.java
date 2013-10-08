
package cz.muni.fi.pa165.tireservice.DAOTests;

import cz.muni.fi.pa165.tireservice.DAO.ServiceDAO;
import cz.muni.fi.pa165.tireservice.DAO.ServiceDAOImpl;
import cz.muni.fi.pa165.tireservice.entities.Service;
import java.math.BigDecimal;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.jboss.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Martin Makarsky(359978)
 */
public class ServiceDAOTest extends AbstractDAOTest {
    
    @Test
    public void testInsertService(){
        Logger log = Logger.getLogger(ServiceDAOTest.class);
        //log.error("fds"); preco fds? je to nejaky java slang, best practice...?
        
        ServiceDAO sd = new ServiceDAOImpl(em);
        
        Service service1 = new Service();
        service1.setName("Super supa vymena oleja za facku");
        service1.setActive(Boolean.TRUE);
        service1.setDescription("Cerstvy fritezovy olej z KFC");
        service1.setPrice(BigDecimal.valueOf(1999L));
        
        //TODO dokoncit
    }
}
