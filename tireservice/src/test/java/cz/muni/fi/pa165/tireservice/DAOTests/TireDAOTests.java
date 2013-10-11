/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DAOTests;

import cz.muni.fi.pa165.tireservice.DAO.TireDAO;
import cz.muni.fi.pa165.tireservice.entities.Tire;
import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.math.BigDecimal;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Ivan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TireDAOTests {

    @Autowired
    TireDAO tireDAO;

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
        tireDAO.insertTire(originalTire);

        Tire tireForUpdate = tireDAO.getTireById(originalTire.getId());
        tireForUpdate.setTireType(new TireType());
        tireDAO.updateTire(tireForUpdate);

        //assert
        Tire updatedTire = tireDAO.getTireById(tireForUpdate.getId());
        assertEquals(tireForUpdate, updatedTire);
    }

    @Test
    public void testDeleteTire() {
        //arrange
        Tire tireToDelete = new Tire();

        tireDAO.insertTire(tireToDelete);

        //act
        tireDAO.removeTire(tireToDelete);

        //asset
        assertNull(tireDAO.getTireById(tireToDelete.getId()));
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
