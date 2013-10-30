/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.dto.OrderDTO;
import java.util.List;

/**
 *
 * @author Atares
 */
public interface OrderServices {
    /**
     * Gets order with active status by id from the db
     * 
     * @param id the ID of order
     * @return Order with active status by id from the db
     */  
    public OrderDTO getOrderById(Long id);
    
    /**
     * Gets all orders (active and inactive)
     * 
     * @return all active and inactive orders
     */
    public List<OrderDTO> getAllOrders();
    
    /**
     * Gets all orders with active status
     * 
     * @return All orders with active status
     */
    public List<OrderDTO> getAllEnabledOrders();
    
    /**
     * Creates new order into database
     * 
     * @param order Order that will be inserted into DB  
     */
    public void createOrder(OrderDTO order);
    
    /**
     * Updates the order and it's attributes
     * 
     * @param orderDTO The order that will be updated
     */
    public void updateOrder(OrderDTO orderDTO);
    
    /**
     * Removes the order from the list of available orders
     * (sets the active attribute to false)
     * 
     * @param orderDTO The order which will be removed
     */
    public void removeOrder(OrderDTO orderDTO);
    
}
