/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.servicestests;

import cz.muni.fi.pa165.tireservice.dao.OrderDAO;
import cz.muni.fi.pa165.tireservice.dao.OrderDAOImpl;
import cz.muni.fi.pa165.tireservice.dto.OrderDTO;
import cz.muni.fi.pa165.tireservice.entities.Order;
import cz.muni.fi.pa165.tireservice.services.OrderServices;
import cz.muni.fi.pa165.tireservice.services.OrderServicesImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doReturn;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Ivan(373892)
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceOrderImplTests {
    @Spy
    private OrderDAO orderDAO = new OrderDAOImpl();
    
    @InjectMocks
    private OrderServices serviceOrder = new OrderServicesImpl();
    
    @Test
    public void getOrderIdIsRight(){
        //arrange 
       Order o = new Order();
       o.setActive(true);
       o.setCarType("someType");
       
       OrderDTO expected = new OrderDTO();
       expected.setActive(true);
       expected.setCarType("someType");
       
       doReturn(o).when(orderDAO).getOrderById((Long) any());
       
       //act
       OrderDTO actual = serviceOrder.getOrderById(1l);
       
       //assert
       assertEquals(expected, actual);
    }
    
    @Test
    public void getOrderIdIsWrong(){
       //arrange 
       doReturn(null).when(orderDAO).getOrderById(anyLong());
       
       //act
       OrderDTO actual = serviceOrder.getOrderById(1l);
       
       //assert
       assertNull(actual);
    }
    
    @Test
    public void getAllOrders(){
       
       //arrange
       Order o = new Order();
       o.setActive(true);
       o.setCarType("test");
       
       Order o2 = new Order();
       o2.setActive(true);
       o2.setCarType("test2");
       
       List<Order> orders = new ArrayList<Order>();
       orders.add(o);
       orders.add(o2);
       
       OrderDTO oDTO = new OrderDTO();
       oDTO.setActive(true);
       oDTO.setCarType("test");
       
       OrderDTO o2DTO = new OrderDTO();
       o2DTO.setActive(true);
       o2DTO.setCarType("test2");
       
       List<OrderDTO> ordersDTOExpected = new ArrayList<OrderDTO>();
       ordersDTOExpected.add(oDTO);
       ordersDTOExpected.add(o2DTO);
       
       doReturn(orders).when(orderDAO).getAllOrders();
       
       //act
       List<OrderDTO> actual = serviceOrder.getAllOrders();
       
       //assert
       assertEquals(ordersDTOExpected, actual);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void updateOrderThrowsExceptionBecauseObjectIsNull(){
        serviceOrder.updateOrder(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void updateOrderThrowsException(){
       OrderDTO oDTO = new OrderDTO();
       oDTO.setCarType("test");
       oDTO.setId(null);
        
       serviceOrder.updateOrder(oDTO);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void removeOrderThrowsExceptionBecauseObjectIsNull(){
        serviceOrder.removeOrder(null);
        
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void removeOrderThrowsExceptionIdIsNull(){
       OrderDTO oDTO = new OrderDTO();
       oDTO.setId(0l);
        
       serviceOrder.removeOrder(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void createORderThrowsExceptionObjectIsNull(){
       serviceOrder.createOrder(null);
    }
}
