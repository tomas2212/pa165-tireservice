package cz.muni.fi.pa165.tireservice.utils;

import cz.muni.fi.pa165.tireservice.dto.OrderDTO;
import cz.muni.fi.pa165.tireservice.entities.Order;

/**
 *
 * @author Jakub Papcun(359 474)
 */
public class OrderUtils {

    public static OrderDTO getOrderDTOFromEntity(Order order) {
        if(order == null){
            return null;
        }
        
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setActive(order.isActive());
        orderDTO.setCarType(order.getCarType());
        orderDTO.setDate(order.getDate());
        orderDTO.setId(order.getId());
                
        //orderDTO.setPerson(order.getPerson());
        //orderDTO.setTires(order.getTires());
        //orderDTO.setServices(order.getServices());
        return orderDTO;
        
    }

    public static Order orderDTOToEntity(OrderDTO orderDTO) {
        if(orderDTO == null){
            return null;
        }
        
        Order order = new Order();
        order.setActive(orderDTO.isActive());
        order.setCarType(orderDTO.getCarType());
        order.setDate(orderDTO.getDate());
        //order.setId(orderDTO.getId());
                
        //orderDTO.setPerson(orderDTO.getPerson());
        //orderDTO.setTires(orderDTO.getTires());
        //orderDTO.setServices(orderDTO.getServices());
        return order;
    }
    
}
