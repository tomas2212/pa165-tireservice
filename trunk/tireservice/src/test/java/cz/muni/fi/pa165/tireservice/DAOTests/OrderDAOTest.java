/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DAOTests;

import cz.muni.fi.pa165.tireservice.DAO.OrderDAO;
import cz.muni.fi.pa165.tireservice.DAO.PersonDAO;
import cz.muni.fi.pa165.tireservice.DAO.ServiceDAO;
import cz.muni.fi.pa165.tireservice.entities.Order;
import cz.muni.fi.pa165.tireservice.entities.Service;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.junit.Test;
import cz.muni.fi.pa165.tireservice.entities.Person;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jakub Papcun
 */
public class OrderDAOTest extends AbstractDAOTest {

    @Autowired
    OrderDAO orderDAO;
    @Autowired
    PersonDAO personDAO;
    @Autowired
    ServiceDAO serviceDAO;

    @Test
    public void testInsertOrder() {
        //arrange
        Order order1 = new Order();
        order1.setActive(Boolean.TRUE);
        order1.setCarType("Trabant");
        order1.setDate(new GregorianCalendar(2013, 9, 30).getTime());
        orderDAO.insertOrder(order1);

        Order order = orderDAO.getOrderById(order1.getId());

        assertEquals(order, order1);
    }

    @Test
    public void testGetPriceExpectedEleven() {
        //arrange
        BigDecimal expectedPrice = new BigDecimal(11);
        expectedPrice = expectedPrice.setScale(2, RoundingMode.CEILING);

        Order order1 = new Order();
        order1.setActive(Boolean.TRUE);
        order1.setCarType("Porsche");
        order1.setDate(new GregorianCalendar(2013, 9, 15).getTime());

        Service s = new Service();
        s.setActive(Boolean.TRUE);
        s.setName("some service");
        s.setPrice(new BigDecimal(11));
        serviceDAO.insertService(s);

        List<Service> services = new ArrayList<Service>();
        services.add(s);

        order1.setServices(services);
        orderDAO.insertOrder(order1);

        Order order = orderDAO.getOrderById(order1.getId());
        //act
        BigDecimal actualPrice = order.getOrderPrice();

        //assert
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testGetAllOrders() {
        Person person1 = new Person();
        person1.setFirstName("Joe");
        person1.setLastName("Black");
        person1.setAddress("Elysian Fields, New York, NY");
        person1.setPhoneNumber("+555 586 358");
        person1.setPassword("nbusr123");
        person1.setActive(Boolean.TRUE);
        person1.setIsServiceman(Boolean.TRUE);


        Order order1 = new Order();
        order1.setActive(Boolean.TRUE);
        order1.setCarType("Porsche");
        order1.setDate(new GregorianCalendar(2013, 9, 15).getTime());

        Order order2 = new Order();
        order2.setActive(Boolean.TRUE);
        order2.setCarType("Audi");
        order2.setDate(new GregorianCalendar(2013, 9, 15).getTime());
        order1.setPerson(person1);
        order2.setPerson(person1);

        List<Order> orders = new ArrayList<Order>();
        orders.add(order1);
        orders.add(order2);
        person1.setOrders(orders);

        personDAO.insertPerson(person1);

        List<Order> ordersList = orderDAO.getAllActiveOrders();

        assertEquals(2, ordersList.size());
    }

    @Test
    public void testGetAllActiveOrdersExpectedOne() {
        //arrange
        Order order1 = new Order();
        order1.setActive(Boolean.TRUE);
        order1.setCarType("Porsche");
        order1.setDate(new GregorianCalendar(2013, 9, 15).getTime());
        orderDAO.insertOrder(order1);

        Order order2 = new Order();
        order2.setActive(Boolean.FALSE);
        order2.setCarType("Audi");
        order2.setDate(new GregorianCalendar(2013, 9, 15).getTime());
        orderDAO.insertOrder(order2);

        List<Order> activeOrders = orderDAO.getAllActiveOrders();

        //assert
        assertEquals(1, activeOrders.size());
    }

    @Test
    public void testRemoveOrder() {
        //arrange
        Order order1 = new Order();
        order1.setActive(Boolean.TRUE);
        order1.setCarType("Porsche");
        order1.setDate(new GregorianCalendar(2013, 9, 15).getTime());
        orderDAO.insertOrder(order1);

        orderDAO.removeOrder(order1);

        //act
        Order inActiveOrder = orderDAO.getOrderById(order1.getId());

        //assert
        assertNull(inActiveOrder);
    }

    @Test
    public void testUpdateOrder() {
        //arrange
        Order order1 = new Order();
        order1.setActive(Boolean.TRUE);
        order1.setCarType("Porsche");
        order1.setDate(new GregorianCalendar(2013, 9, 15).getTime());
        orderDAO.insertOrder(order1);

        Order changedOrder = orderDAO.getOrderById(order1.getId());
        changedOrder.setCarType("Trabant");

        //act
        orderDAO.updateOrder(changedOrder);

        //assert
        Order actualOrder = orderDAO.getOrderById(changedOrder.getId());
        assertEquals(changedOrder, actualOrder);
    }

    @Test
    public void testGetOrderByID() {
        //arrange
        Order order1 = new Order();
        order1.setActive(Boolean.TRUE);
        order1.setCarType("Porsche");
        order1.setDate(new GregorianCalendar(2013, 9, 15).getTime());
        orderDAO.insertOrder(order1);

        Order order = orderDAO.getOrderById(order1.getId());

        assertEquals(order, order1);
    }
}
