package cz.muni.fi.pa165.tireservice.daotest;

import cz.muni.fi.pa165.tireservice.dao.TireDAO;
import cz.muni.fi.pa165.tireservice.entities.Tire;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ivan Novak
 */
public class TireDAOTests extends AbstractDAOTest {

    @Autowired
    TireDAO tireDAO;

    @Test
    public void testGetAllTires() {

        //arrange
        Tire tire = new Tire();

        tireDAO.insertTire(tire);

        Tire anotherTire = new Tire();

        tireDAO.insertTire(anotherTire);
        //act
        int actualNumberOfTires = tireDAO.getAllTires().size();

        //assert
        assertEquals(2, actualNumberOfTires);
    }

    @Test
    public void testUpdateTire() {
        //act
        Tire originalTire = new Tire();
        originalTire.setAmountOnStore(3);
        tireDAO.insertTire(originalTire);

        Tire tireForUpdate = tireDAO.getTireById(originalTire.getId());
        tireForUpdate.setAmountOnStore(5);
        tireDAO.updateTire(tireForUpdate);

        //assert
        Tire updatedTire = tireDAO.getTireById(tireForUpdate.getId());
        assertEquals(tireForUpdate.getAmountOnStore(), updatedTire.getAmountOnStore());
    }

    @Test
    public void testInsertTire() {

        //arrange
        Tire tireToInsert = new Tire();

        //act
        tireDAO.insertTire(tireToInsert);

        //assert
        Tire actualTire = tireDAO.getTireById(tireToInsert.getId());
        assertEquals(tireToInsert, actualTire);
    }

    @Test
    public void testDeleteTire() {
        //arrange
        Tire tireToDelete = new Tire();
        tireToDelete.setAmountOnStore(10);

        tireDAO.insertTire(tireToDelete);
        Tire gettedTire = tireDAO.getTireById(tireToDelete.getId());

        //act
        tireDAO.removeTire(gettedTire);

        //asset
        assertNull(tireDAO.getTireById(gettedTire.getId()));
    }

    @Test
    public void testGetTireId() {
        //arrange
        Tire tire = new Tire();

        tireDAO.insertTire(tire);

        //act
        Tire actualTire = tireDAO.getTireById(tire.getId());

        //assert
        assertEquals(tire, actualTire);
    }
}
