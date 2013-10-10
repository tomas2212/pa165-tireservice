package cz.muni.fi.pa165.tireservice.DAOTests;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 *
 * @author Jakub Papcun(359 474)
 */
public class AbstractDAOTest extends TestCase {
    private EntityManagerFactory emFactory;

    protected EntityManager em;

    private Connection connection;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:unit-testing-jpa", "sa", "");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during HSQL database startup.");
        }
        try {
            emFactory = Persistence.createEntityManagerFactory("TireService");
//            emFactory = Persistence.createEntityManagerFactory("TestPU");
            em = emFactory.createEntityManager();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during JPA EntityManager instanciation.");
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (em != null) {
            em.close();
        }
        if (emFactory != null) {
            emFactory.close();
        }
        try {
            connection.createStatement().execute("SHUTDOWN");
        } catch (Exception ex) {}
    }
    
}
