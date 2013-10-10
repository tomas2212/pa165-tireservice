/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Order;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ivan Novak
 */
public class OrderDAOImpl implements OrderDAO {

    protected EntityManager entityManager;

    public OrderDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Order getOrderById(Long id) {
        Order o = entityManager.find(Order.class, id);
        return o.isActive() ? o : null;
    }

    public List<Order> getAllOrders() {
        TypedQuery<Order> oList = entityManager.createQuery("SELECT o FROM Order o", Order.class);
        return oList.getResultList();
    }
    
    public List<Order> getAllActiveOrders() {
        List<Order> activeOrders = new ArrayList<Order>();
        
        for(Order o : getAllOrders()){
            if(o.isActive()){
                activeOrders.add(o);
            }
        }
        
        return activeOrders;
    }

    public void insertOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("You have to set order");
        }
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    public void updateOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("You have to set order");
        }
        if (order.getId() == null) {
            throw new IllegalArgumentException("Can't update, because entity does not contain ID");
        }
        entityManager.getTransaction().begin();
        entityManager.merge(order);
        entityManager.getTransaction().commit();
    }

    public void removeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("You have to set order");
        }
        if (order.getId() == null) {
            throw new IllegalArgumentException("Can't remove, because entity does not contain ID");
        }
        
        order.setActive(Boolean.FALSE);
        entityManager.getTransaction().begin();
        entityManager.merge(order);
        entityManager.getTransaction().commit();
    }
    
}
