package cz.muni.fi.pa165.tireservice.DAOTests;

import cz.muni.fi.pa165.tireservice.DAO.TireTypeDAO;
import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.math.BigDecimal;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Ivan Novak
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TireTypeDAOTest {

    @Autowired
    TireTypeDAO tireTypeDAO;

    @Test
    public void testInsertTireType() {

        TireType tireType1 = new TireType();
        tireType1.setManufacturer("Man");
        tireType1.setTireRimSize(1d);
        tireType1.setType("Zima");
        tireType1.setActive(Boolean.TRUE);
        tireType1.setAmountOnStore(10);
        tireType1.setPrice(BigDecimal.valueOf(1000l));

        tireTypeDAO.insertTireType(tireType1);
        TireType tireType2 = tireTypeDAO.getTireTypeById(tireType1.getId());
        System.err.println(tireType1.equals(tireType2));
        assertEquals(tireType1, tireType2);
    }

    @Test
    public void testGetAllTireTypes() {
        TireType tireType1 = new TireType();
        tireType1.setManufacturer("Man");
        tireType1.setTireRimSize(1d);
        tireType1.setType("Zima");
        tireType1.setActive(Boolean.TRUE);
        tireType1.setAmountOnStore(10);
        tireType1.setPrice(BigDecimal.valueOf(1000l));

        tireTypeDAO.insertTireType(tireType1);
        int tires1 = tireTypeDAO.getAllTireTypes().size();

        TireType tireType2 = new TireType();
        tireType2.setManufacturer("Man");
        tireType2.setTireRimSize(1d);
        tireType2.setType("Leto");
        tireType2.setActive(Boolean.TRUE);
        tireType2.setAmountOnStore(100);
        tireType2.setPrice(BigDecimal.valueOf(2000l));

        tireTypeDAO.insertTireType(tireType2);
        int tires2 = tireTypeDAO.getAllTireTypes().size();
        assertEquals(tires1 + 1, tires2);
    }

    @Test
    public void testUpdateTireTypes() {
        TireType tireType1 = new TireType();
        tireType1.setManufacturer("Man");
        tireType1.setTireRimSize(1d);
        tireType1.setType("Zima");
        tireType1.setActive(true);
        tireType1.setAmountOnStore(10);
        tireType1.setPrice(BigDecimal.valueOf(1000l));
        tireTypeDAO.insertTireType(tireType1);

        TireType tireType2 = tireTypeDAO.getTireTypeById(tireType1.getId());
        tireType2.setPrice(BigDecimal.valueOf(500l));
        tireTypeDAO.updateTireType(tireType2);
        TireType tireType3 = tireTypeDAO.getTireTypeById(tireType2.getId());
        assertEquals(tireType3.getPrice(), tireType2.getPrice());
    }

    @Test
    public void testDeleteTireType() {
        TireType tireType1 = new TireType();
        tireType1.setManufacturer("Man");
        tireType1.setTireRimSize(1d);
        tireType1.setType("Zima");
        tireType1.setActive(true);
        tireType1.setAmountOnStore(10);
        tireType1.setPrice(BigDecimal.valueOf(1000l));

        tireTypeDAO.insertTireType(tireType1);

        TireType tireType2 = tireTypeDAO.getTireTypeById(tireType1.getId());
        tireTypeDAO.removeTireType(tireType2);

        TireType tireType3 = tireTypeDAO.getTireTypeById(tireType2.getId());
        assertNull(tireType3);
    }

    @Test
    public void testGetTireTypeById() {
        TireType tireType1 = new TireType();
        tireType1.setManufacturer("Man");
        tireType1.setTireRimSize(1d);
        tireType1.setType("Zima");
        tireType1.setActive(true);
        tireType1.setAmountOnStore(10);
        tireType1.setPrice(BigDecimal.valueOf(1000l));

        tireTypeDAO.insertTireType(tireType1);

        TireType tireType2 = tireTypeDAO.getTireTypeById(tireType1.getId());

        assertEquals(tireType1, tireType2);
    }
}