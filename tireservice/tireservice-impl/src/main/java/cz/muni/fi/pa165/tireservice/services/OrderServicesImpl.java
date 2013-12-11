package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.DAO.OrderDAO;
import cz.muni.fi.pa165.tireservice.dto.OrderDTO;
import cz.muni.fi.pa165.tireservice.entities.Order;
import cz.muni.fi.pa165.tireservice.utils.OrderUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Stefan Sakala (359772)
 */
@Service
@Transactional
public class OrderServicesImpl implements OrderServices {

    @Autowired
    private OrderDAO orderDAO;

    @Transactional
    public OrderDTO getOrderById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID of order cannot be null");
        }
        Order order = orderDAO.getOrderById(id);
        OrderDTO orderDTO = OrderUtils.getOrderDTOFromEntity(order);
        return orderDTO;
    }

    @Transactional
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> toReturn = new ArrayList<OrderDTO>();
        List<Order> orders = orderDAO.getAllOrders();
        for (Order order : orders) {
            OrderDTO orderDTO = OrderUtils.getOrderDTOFromEntity(order);
            toReturn.add(orderDTO);
        }
        return toReturn;
    }

    @Transactional
    public List<OrderDTO> getAllEnabledOrders() {
        List<OrderDTO> toReturn = new ArrayList<OrderDTO>();
        List<Order> orders = orderDAO.getAllActiveOrders();
        for (Order order : orders) {
            OrderDTO orderDTO = OrderUtils.getOrderDTOFromEntity(order);
            toReturn.add(orderDTO);
        }
        return toReturn;
    }

    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        Order order = OrderUtils.orderDTOToEntity(orderDTO);
        orderDAO.insertOrder(order);
        orderDTO.setId(order.getId());
    }

    @Transactional
    public void updateOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("Order for updating cannot be null");
        }
        Order order = OrderUtils.orderDTOToEntity(orderDTO);
        orderDAO.updateOrder(order);
    }

    @Transactional
    public void removeOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("Order for removing cannot be null");
        }
        Order order = OrderUtils.orderDTOToEntity(orderDTO);
        orderDAO.removeOrder(order);
    }
}
