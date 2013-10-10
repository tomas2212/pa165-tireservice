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
import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import junit.framework.Assert;
import sun.nio.cs.ext.Big5;

/**
 *
 * @author Jakub Papcun
 */
public class OrderDAOTest extends AbstractDAOTest {
    
    @Test
    public void testGetPriceExpectedEleven() {
        //arrange
        BigDecimal expectedPrice = new BigDecimal(11);
        Order order = createOrder();
        
        Service mockService = mock(Service.class);
        when(mockService.getPrice()).thenReturn(BigDecimal.TEN);
        order.getServices().add(mockService);
        
        Tire tire = createTire();
        when(tire.getTireType().getPrice()).thenReturn(BigDecimal.ONE);
        order.getTires().add(tire);
        
        //act
        BigDecimal actualPrice =  order.getOrderPrice();
        
        //assert
        assertEquals(expectedPrice, actualPrice);        
    }
    
    @Test
    public void testGetPriceExpectedZero() {
        //arrange
        BigDecimal expectedPrice = BigDecimal.ZERO;
        Order order = createOrder();
        
        //act
        BigDecimal actualPrice =  order.getOrderPrice();
        
        //assert
        assertEquals(expectedPrice, actualPrice);        
    }
    
    @Test
    public void testGetAllOrdersExpectedTwo() {
        //arrange
        OrderDAO orderManager = new OrderDAOImpl(em);
        Order order1 = createOrder();
        order1.setActive(true);
        
        Order order2 = createOrder();
        order2.setActive(false);
        
        orderManager.insertOrder(order1);
        orderManager.insertOrder(order2);
        
        //act
        int size = orderManager.getAllOrders().size();
        
        //assert
        assertEquals(2, size);
    }
    
    @Test
    public void testGetAllActiveOrdersExpectedOne() {
        //arrange
        OrderDAO orderManager = new OrderDAOImpl(em);
        Order order1 = createOrder();
        order1.setActive(true);
        
        Order order2 = createOrder();
        order2.setActive(false);
        
        orderManager.insertOrder(order1);
        orderManager.insertOrder(order2);
        
        //act
        int size = orderManager.getAllActiveOrders().size();
        
        //assert
        assertEquals(1, size);
    }
    
    @Test
    public void testRemoveOrder() {
        //arrange
        OrderDAO orderManager = new OrderDAOImpl(em);
        Order order1 = createOrder();
        order1.setActive(true);
        
        orderManager.removeOrder(order1);
        
        //act
        Order inActiveOrder = orderManager.getOrderById(order1.getId());
        
        //assert
        assertFalse(inActiveOrder.isActive());
    }
    
    @Test
    public void testUpdateOrder() {
        //arrange
        OrderDAO orderManager = new OrderDAOImpl(em);
        Order order = createOrder();
        orderManager.insertOrder(order);
        Order changedOrder = orderManager.getOrderById(order.getId());
        changedOrder.setCarType("testCarType");
        
        //act
        orderManager.updateOrder(changedOrder);
        
        //assert
        Order actualOrder = orderManager.getOrderById(changedOrder.getId());
        assertEquals(changedOrder, actualOrder);
    }
    
    @Test
    public void testGetOrderByID() {
        //arrange
        OrderDAO orderManager = new OrderDAOImpl(em);
        Order order = createOrder();
        
        //act
        orderManager.insertOrder(order);
        
        //assert
        Order actualOrder = orderManager.getOrderById(order.getId());
        assertEquals(order, actualOrder);        
    }
    
    @Test
    public void testInsertOrder() {
        //arrange
        OrderDAO orderManager = new OrderDAOImpl(em);
        ServiceDAO serviceManager = new ServiceDAOImpl(em);
        TireDAO tireManager = new TireDAOImpl(em);
                
        Order order = createOrder();
        Service service = createService();
        Tire tire = createTire();
        
        order.getTires().add(tire);
        order.getServices().add(service);
        service.setOrder(order);
        tire.setOrder(order);
        
        //act
        orderManager.insertOrder(order);
        
        //assert
        Order actualOrder = orderManager.getOrderById(order.getId());
        assertEquals(order, actualOrder);
        assertTrue(actualOrder.getServices().contains(service));
        assertTrue(actualOrder.getTires().contains(tire));
        
        //check that Tire and Service has been created
        assertEquals(service, serviceManager.getServiceById(service.getId()));
        assertEquals(tire, tireManager.getTireById(tire.getId()));        
    }
    
    private Order createOrder(){
        Order order = new Order();
        order.setActive(true);
        order.setCarType("sedan");
        order.setDate(new Date(373892));
        order.setTires(new ArrayList<Tire>());
        order.setServices(new ArrayList<Service>());
        
        return order;
    }
    
    private Tire createTire(){
        TireType tireType = mock(TireType.class);
        when(tireType.getId()).thenReturn(Long.MIN_VALUE);
        Tire tire = new Tire(tireType);
        
        return tire;
    }
    
    private Service createService(){
        Service s = new Service();
        s.setName("testService");
        
        return s;
    }
    
    
}
