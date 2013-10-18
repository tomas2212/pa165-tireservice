package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entities.Order;
import java.util.List;

/**
 *
 * @author Ivan Novak
 */
public interface OrderDAO {
    /**
     * Gets active Order from database
     * 
     * @param id the ID of order
     * @return Order or null if id does not exists or is inactive
     */ 
    public Order getOrderById(Long id);
    
    /**
     * Gets all orders even inactive ones
     * 
     * @return List of all orders in database or empty list
     */
    public List<Order> getAllOrders();
    
    /**
     * Gets all active orders
     * 
     * @return List of all active orders in database or empty list
     */
    public List<Order> getAllActiveOrders();
    
    /**
     * Inserts new order into database
     * 
     * @param order The order which will be inserted  
     */
    public void insertOrder (Order order);
    
    /**
     * Updates order in database
     * 
     * @param order The order which will be updated 
     */
    public void updateOrder (Order order);
    
    /**
     * Sets status to inactive
     * 
     * @param order The order which becomes inactive
     */
    public void removeOrder (Order order);
}
