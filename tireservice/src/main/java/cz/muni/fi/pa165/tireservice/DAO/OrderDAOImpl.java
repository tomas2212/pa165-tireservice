/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Order;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ivan Novak
 */
public class OrderDAOImpl implements OrderDAO {
    
    @PersistenceContext
    protected EntityManager entityManager;
    
    @Transactional
    public Order getOrderById(Long id) {
        Order o = entityManager.find(Order.class, id);
        return o.isActive() ? o : null;
    }

    @Transactional
    public List<Order> getAllOrders() {
        TypedQuery<Order> oList = entityManager.createQuery("SELECT o FROM Order o", Order.class);
        return oList.getResultList();
    }
    
    @Transactional
    public List<Order> getAllActiveOrders() {
        List<Order> activeOrders = new ArrayList<Order>();
        
        for(Order o : getAllOrders()){
            if(o.isActive()){
                activeOrders.add(o);
            }
        }
        
        return activeOrders;
    }

    @Transactional
    public void insertOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("You have to set order");
        }
        
        entityManager.persist(order);
    }

    @Transactional
    public void updateOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("You have to set order");
        }
        if (order.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity does not contain ID");
        }
        
        entityManager.merge(order);
    }

    @Transactional
    public void removeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("You have to set order");
        }
        if (order.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity does not contain ID");
        }
        
        order.setActive(Boolean.FALSE);
        entityManager.merge(order);
    }
    
}
