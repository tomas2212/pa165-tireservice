/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DAOTests;

import cz.muni.fi.pa165.tireservice.DAO.OrderDAO;
import cz.muni.fi.pa165.tireservice.DAO.OrderDAOImpl;
import cz.muni.fi.pa165.tireservice.DAO.ServiceDAO;
import cz.muni.fi.pa165.tireservice.DAO.ServiceDAOImpl;
import cz.muni.fi.pa165.tireservice.DAO.TireDAOImpl;
import cz.muni.fi.pa165.tireservice.entities.Order;
import cz.muni.fi.pa165.tireservice.entities.Service;
import cz.muni.fi.pa165.tireservice.entities.Tire;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import static org.mockito.Mockito.*;
import cz.muni.fi.pa165.tireservice.DAO.TireDAO;
import cz.muni.fi.pa165.tireservice.entities.Person;
import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Jakub Papcun
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"} )
public class OrderDAOTest extends AbstractDAOTest {
    @Autowired
    OrderDAO orderDAO;
    
    @Test
    public void testGetPriceExpectedEleven() {
        //arrange
//        BigDecimal expectedPrice = new BigDecimal(11);
//        Order order = createOrder();
//        
//        Service mockService = mock(Service.class);
//        when(mockService.getPrice()).thenReturn(BigDecimal.TEN);
//        order.getServices().add(mockService);
//        
//        Tire tire = createTire();
//        when(tire.getTireType().getPrice()).thenReturn(BigDecimal.ONE);
//        order.getTires().add(tire);
//        
//        //act
//        BigDecimal actualPrice =  order.getOrderPrice();
//        
//        //assert
//        assertEquals(expectedPrice, actualPrice);        
    }
    
    @Test
    public void testGetPriceExpectedZero() {
//        //arrange
//        BigDecimal expectedPrice = BigDecimal.ZERO;
//        Order order = createOrder();
//        
//        //act
//        BigDecimal actualPrice =  order.getOrderPrice();
//        
//        //assert
//        assertEquals(expectedPrice, actualPrice);        
    }
    
    @Test
    public void testGetAllOrdersExpectedTwo() {
        Person person = new Person();
        person.setFirstName("Janko");
        person.setLastName("Majko");
        person.setActive(Boolean.TRUE);
        person.setIsServiceman(Boolean.TRUE);
        person.setPassword("pass");
        
        Order order1 = new Order();
        order1.setActive(Boolean.TRUE);
        order1.setCarType("Porsche");
        order1.setDate(new GregorianCalendar(2013, 9, 15).getTime());
        order1.setPerson(person);
        
        Order order2 = new Order();
        order2.setActive(Boolean.TRUE);
        order2.setCarType("Audi");
        order2.setDate(new GregorianCalendar(2013, 9, 15).getTime());
        order2.setPerson(person);
        
        orderDAO.insertOrder(order1);
        orderDAO.insertOrder(order2);
        
        //act
        int size = orderDAO.getAllOrders().size();
        
        //assert
        assertEquals(2, size);
    }
    
    @Test
    public void testGetAllActiveOrdersExpectedOne() {
        //arrange
//        OrderDAO orderManager = new OrderDAOImpl(em);
//        Order order1 = createOrder();
//        order1.setActive(true);
//        
//        Order order2 = createOrder();
//        order2.setActive(false);
//        
//        orderManager.insertOrder(order1);
//        orderManager.insertOrder(order2);
//        
//        //act
//        int size = orderManager.getAllActiveOrders().size();
//        
//        //assert
//        assertEquals(1, size);
    }
    
    @Test
    public void testRemoveOrder() {
        //arrange
//        OrderDAO orderManager = new OrderDAOImpl(em);
//        Order order1 = createOrder();
//        order1.setActive(true);
//        
//        orderManager.removeOrder(order1);
//        
//        //act
//        Order inActiveOrder = orderManager.getOrderById(order1.getId());
//        
//        //assert
//        assertFalse(inActiveOrder.isActive());
    }
    
    @Test
    public void testUpdateOrder() {
//        //arrange
//        OrderDAO orderManager = new OrderDAOImpl(em);
//        Order order = createOrder();
//        orderManager.insertOrder(order);
//        Order changedOrder = orderManager.getOrderById(order.getId());
//        changedOrder.setCarType("testCarType");
//        
//        //act
//        orderManager.updateOrder(changedOrder);
//        
//        //assert
//        Order actualOrder = orderManager.getOrderById(changedOrder.getId());
//        assertEquals(changedOrder, actualOrder);
    }
    
    @Test
    public void testGetOrderByID() {
        //arrange
//        OrderDAO orderManager = new OrderDAOImpl(em);
//        Order order = createOrder();
//        
//        //act
//        orderManager.insertOrder(order);
//        
//        //assert
//        Order actualOrder = orderManager.getOrderById(order.getId());
//        assertEquals(order, actualOrder);        
    }
    
    @Test
    public void testInsertOrder() {
        //arrange
//        OrderDAO orderManager = new OrderDAOImpl(em);
//        ServiceDAO serviceManager = new ServiceDAOImpl(em);
//        TireDAO tireManager = new TireDAOImpl(em);
//                
//        Order order = createOrder();
//        Service service = createService();
//        Tire tire = createTire();
//        
//        order.getTires().add(tire);
//        order.getServices().add(service);
//        service.setOrder(order);
//        tire.setOrder(order);
//        
//        //act
//        orderManager.insertOrder(order);
//        
//        //assert
//        Order actualOrder = orderManager.getOrderById(order.getId());
//        assertEquals(order, actualOrder);
//        assertTrue(actualOrder.getServices().contains(service));
//        assertTrue(actualOrder.getTires().contains(tire));
//        
//        //check that Tire and Service has been created
//        assertEquals(service, serviceManager.getServiceById(service.getId()));
//        assertEquals(tire, tireManager.getTireById(tire.getId()));        
//    }
//    
//    private Order createOrder(){
//        Order order = new Order();
//        order.setActive(true);
//        order.setCarType("sedan");
//        order.setDate(new Date(373892));
//        order.setTires(new ArrayList<Tire>());
//        order.setServices(new ArrayList<Service>());
//        
//        return order;
    }
    
    private Tire createTire(TireType tireType, int amount){
        Tire tire = new Tire();
        tire.setAmountOnStore(amount);
        tire.setTireType(tireType);
        
        return tire;
    }
    
    private Service createService(){
        Service s = new Service();
        s.setName("testService");
        
        return s;
    }
    
    
}
