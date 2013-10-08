/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DAOTests;

import cz.muni.fi.pa165.tireservice.DAO.TireDAO;
import cz.muni.fi.pa165.tireservice.DAO.TireDAOImpl;
import cz.muni.fi.pa165.tireservice.entities.Tire;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author yrael
 */
public class TireDAOTest extends AbstractDAOTest{
    
    @Test
    public void testPersistence() {
        
        TireDAO td = new TireDAOImpl(em);
        
        Tire tire1 = new Tire();
        tire1.setAmount(1);
        tire1.setChangeTire(false);
        tire1.setManufacturer("Man");
        tire1.setPriceForChange(BigDecimal.valueOf(2000L));
        tire1.setSeason("zima");
        tire1.setTireAspect(1d);
        tire1.setTireRimSize(1d);
        tire1.setTireWidth(1d);
        tire1.setType("auto");

        td.insertTire(tire1);

        Tire tire2 = td.getTireById(tire1.getId());
        assertEquals(tire1, tire2);
    }
    
//    @Test
//    public void insertTest() {
//        Car car1 = new Car();
//        car1.setSpz("BR975AM");
//
//        carDao.insert(car1);
//
//        Car car2 = carDao.getCarById(car1.getId());
//        assertEquals(car1, car2);
//
//        try {
//            carDao.insert(null);
//            fail("Inserted null entity");
//        } catch (DataAccessException e) {
//        }
//    }
}
