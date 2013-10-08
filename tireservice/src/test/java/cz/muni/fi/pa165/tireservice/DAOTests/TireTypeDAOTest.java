package cz.muni.fi.pa165.tireservice.DAOTests;

import cz.muni.fi.pa165.tireservice.DAO.TireTypeDAO;
import cz.muni.fi.pa165.tireservice.DAO.TireTypeDAOImpl;
import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.math.BigDecimal;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.jboss.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Ivan Novak
 */
public class TireTypeDAOTest extends AbstractDAOTest{
    
    @Test
    public void testInsertTireType() {
        Logger log = Logger.getLogger(TireTypeDAOTest.class);
        log.error("fds");
        
        TireTypeDAO td = new TireTypeDAOImpl(em);
        
        TireType tireType1 = new TireType();
        tireType1.setManufacturer("Man");
        tireType1.setTireRimSize(1d);
        tireType1.setType("Zima");
        tireType1.setActive(Boolean.TRUE);
        tireType1.setAmountOnStore(10);
        tireType1.setPrice(BigDecimal.valueOf(1000l));
        
        td.insertTireType(tireType1);

        TireType tireType2 = td.getTireTypeById(tireType1.getId());
        assertEquals(tireType1, tireType2);
    }
    
    @Test
    public void testGetAllTireTypes() {
        Logger log = Logger.getLogger(TireTypeDAOTest.class);
        log.error("fds");
        
        TireTypeDAO td = new TireTypeDAOImpl(em);
        
        TireType tireType1 = new TireType();
        tireType1.setManufacturer("Man");
        tireType1.setTireRimSize(1d);
        tireType1.setType("Zima");
        tireType1.setActive(Boolean.TRUE);
        tireType1.setAmountOnStore(10);
        tireType1.setPrice(BigDecimal.valueOf(1000l));
        
        td.insertTireType(tireType1);
        int tires1 = td.getAllTireTypes().size();
        
        TireType tireType2 = new TireType();
        tireType2.setManufacturer("Man");
        tireType2.setTireRimSize(1d);
        tireType2.setType("Leto");
        tireType2.setActive(Boolean.TRUE);
        tireType2.setAmountOnStore(100);
        tireType2.setPrice(BigDecimal.valueOf(2000l));
        
        td.insertTireType(tireType2);
        int tires2 = td.getAllTireTypes().size();

        assertEquals(tires1 + 1, tires2);
    }
    
    @Test
    public void testUpdateTireTypes() {
        Logger log = Logger.getLogger(TireTypeDAOTest.class);
        log.error("fds");
        
        TireTypeDAO td = new TireTypeDAOImpl(em);
        
        TireType tireType1 = new TireType();
        tireType1.setManufacturer("Man");
        tireType1.setTireRimSize(1d);
        tireType1.setType("Zima");
        tireType1.setActive(true);
        tireType1.setAmountOnStore(10);
        tireType1.setPrice(BigDecimal.valueOf(1000l));
        
        td.insertTireType(tireType1);
        
        TireType tireType2 = td.getTireTypeById(tireType1.getId());
        tireType2.setPrice(BigDecimal.valueOf(500l));

        TireType tireType3 = td.getTireTypeById(tireType2.getId());
        assertEquals(tireType2.getPrice(), tireType3.getPrice());
    }
    
    @Test
    public void testDeleteTireType(){
        
        TireTypeDAO td = new TireTypeDAOImpl(em);
        
        TireType tireType1 = new TireType();
        tireType1.setManufacturer("Man");
        tireType1.setTireRimSize(1d);
        tireType1.setType("Zima");
        tireType1.setActive(true);
        tireType1.setAmountOnStore(10);
        tireType1.setPrice(BigDecimal.valueOf(1000l));
        
        td.insertTireType(tireType1);
        
        TireType tireType2 = td.getTireTypeById(tireType1.getId());
        td.removeTireType(tireType2);
        
        TireType tireType3 = td.getTireTypeById(tireType2.getId());
        assertNull(tireType3);
        
    }
    
    @Test
    public void testGetTireTypeById(){
        
        TireTypeDAO td = new TireTypeDAOImpl(em);
        
        TireType tireType1 = new TireType();
        tireType1.setManufacturer("Man");
        tireType1.setTireRimSize(1d);
        tireType1.setType("Zima");
        tireType1.setActive(true);
        tireType1.setAmountOnStore(10);
        tireType1.setPrice(BigDecimal.valueOf(1000l));
        
        td.insertTireType(tireType1);
        
        TireType tireType2 = td.getTireTypeById(tireType1.getId());
        
        assertEquals(tireType1, tireType2);
        
    }
}
