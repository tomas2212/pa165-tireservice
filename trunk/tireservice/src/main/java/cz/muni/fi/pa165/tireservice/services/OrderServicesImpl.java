/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.DAO.OrderDAO;
import cz.muni.fi.pa165.tireservice.dto.OrderDTO;
import cz.muni.fi.pa165.tireservice.entities.Order;
import cz.muni.fi.pa165.tireservice.utils.OrderUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Stefan Sakala (359772)
 */
public class OrderServicesImpl implements OrderServices {

    @Autowired
    private OrderDAO orderDAO;

    public OrderDTO getOrderById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID of order cannot be null");
        }
        Order order = orderDAO.getOrderById(id);
        OrderDTO orderDTO = OrderUtils.getOrderDTOFromEntity(order);
        return orderDTO;
    }

    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> toReturn = new ArrayList<OrderDTO>();
        List<Order> orders = orderDAO.getAllOrders();
        for (Order order : orders) {
            OrderDTO orderDTO = OrderUtils.getOrderDTOFromEntity(order);
            toReturn.add(orderDTO);
        }
        return toReturn;
    }

    public List<OrderDTO> getAllEnabledOrders() {
        List<OrderDTO> toReturn = new ArrayList<OrderDTO>();
        List<Order> orders = orderDAO.getAllActiveOrders();
        for (Order order : orders) {
            OrderDTO orderDTO = OrderUtils.getOrderDTOFromEntity(order);
            toReturn.add(orderDTO);
        }
        return toReturn;
    }

    public void createOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        Order order = OrderUtils.orderDTOToEntity(orderDTO);
        orderDAO.insertOrder(order);
        orderDTO.setId(order.getId());
    }

    public void updateOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("Order for updating cannot be null");
        }
        Order order = OrderUtils.orderDTOToEntity(orderDTO);
        orderDAO.updateOrder(order);
    }

    public void removeOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("Order for removing cannot be null");
        }
        Order order = OrderUtils.orderDTOToEntity(orderDTO);
        orderDAO.removeOrder(order);
    }
}
