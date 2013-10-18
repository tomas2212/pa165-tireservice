package cz.muni.fi.pa165.tireservice.DAOTests;

import cz.muni.fi.pa165.tireservice.dao.TireTypeDAO;
import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.math.BigDecimal;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ivan Novak
 */
public class TireTypeDAOTest extends AbstractDAOTest {

    @Autowired
    TireTypeDAO tireTypeDAO;

    @Test
    public void testInsertTireType() {

        //arrange
        TireType tireTypeToInsert = new TireType();
        tireTypeToInsert.setManufacturer("Man");
        tireTypeToInsert.setTireRimSize(1d);
        tireTypeToInsert.setType("Zima");
        tireTypeToInsert.setActive(Boolean.TRUE);
        tireTypeToInsert.setAmountOnStore(10);
        tireTypeToInsert.setPrice(BigDecimal.valueOf(1000l));

        //act
        tireTypeDAO.insertTireType(tireTypeToInsert);

        //assert
        TireType actualTireType = tireTypeDAO.getTireTypeById(tireTypeToInsert.getId());
        assertEquals(tireTypeToInsert, actualTireType);
    }

    @Test
    public void testGetAllTireTypes() {

        //arrange
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
        //act
        int actualNumberOfTires = tireTypeDAO.getAllTireTypes().size();

        //assert
        assertEquals(2, actualNumberOfTires);
    }

    @Test
    public void testUpdateTireTypes() {
        //act
        TireType originalTireType = new TireType();
        originalTireType.setManufacturer("Man");
        originalTireType.setTireRimSize(1d);
        originalTireType.setType("Zima");
        originalTireType.setActive(true);
        originalTireType.setAmountOnStore(10);
        originalTireType.setPrice(BigDecimal.valueOf(1000l));
        tireTypeDAO.insertTireType(originalTireType);

        TireType tireTypeForUpdate = tireTypeDAO.getTireTypeById(originalTireType.getId());
        tireTypeForUpdate.setPrice(BigDecimal.valueOf(500l));
        tireTypeDAO.updateTireType(tireTypeForUpdate);

        //assert
        TireType updatedTireType = tireTypeDAO.getTireTypeById(tireTypeForUpdate.getId());
        assertEquals(tireTypeForUpdate.getPrice(), updatedTireType.getPrice());
    }

    @Test
    public void testDeleteTireType() {
        //arrange
        TireType tireTypeToDelete = new TireType();
        tireTypeToDelete.setManufacturer("Man");
        tireTypeToDelete.setTireRimSize(1d);
        tireTypeToDelete.setType("Zima");
        tireTypeToDelete.setActive(true);
        tireTypeToDelete.setAmountOnStore(10);
        tireTypeToDelete.setPrice(BigDecimal.valueOf(1000l));

        tireTypeDAO.insertTireType(tireTypeToDelete);

        //act
        tireTypeDAO.removeTireType(tireTypeToDelete);

        //asset
        assertNull(tireTypeDAO.getTireTypeById(tireTypeToDelete.getId()));
    }

    @Test
    public void testGetTireTypeById() {
        //arrange
        TireType tireType = new TireType();
        tireType.setManufacturer("Man");
        tireType.setTireRimSize(1d);
        tireType.setType("Zima");
        tireType.setActive(true);
        tireType.setAmountOnStore(10);
        tireType.setPrice(BigDecimal.valueOf(1000l));

        tireTypeDAO.insertTireType(tireType);

        //act
        TireType actualTireType = tireTypeDAO.getTireTypeById(tireType.getId());

        //assert
        assertEquals(tireType, actualTireType);
    }
}