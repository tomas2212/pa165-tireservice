package cz.muni.fi.pa165.tireservice.utils;

import cz.muni.fi.pa165.tireservice.dto.OrderDTO;
import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.entities.Order;
import cz.muni.fi.pa165.tireservice.entities.Service;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jakub Papcun(359 474)
 */
public class ServicesUtils {
    
    private ServicesUtils(){};
    
    public static Service serviceDTOToEntity(ServiceDTO serviceDTO) {
        if (serviceDTO == null) {
            return null;
        }
        Service service = new Service();
        
        service.setId(serviceDTO.getId());
        service.setActive(serviceDTO.isActive());
        service.setPrice(serviceDTO.getPrice());
        service.setDescription(serviceDTO.getDescription());
        service.setName(serviceDTO.getName());
        
        // setting order probably not necessary as the relationship between 
        // orders and services is saved through cascade when order is saved
        // new services have no orders
//        List<OrderDTO> listOrdersDTO = serviceDTO.getOrders();
//        if(listOrdersDTO != null && !listOrdersDTO.isEmpty()){
//            List<Order> listOrders = new ArrayList<Order>();
//            for(OrderDTO orderDTO : listOrdersDTO){
//                Order order = OrderUtils.orderDTOToEntity(orderDTO);
//                listOrders.add(order);
//            }
//            service.setOrder(listOrders);
//        }
        
        return service;
    }

    public static ServiceDTO getServiceDTOFromEntity(Service service) {
        if (service == null) {
            return null;
        }
        ServiceDTO serviceDTO = new ServiceDTO();
        
        serviceDTO.setId(service.getId());
        serviceDTO.setActive(service.isActive());
        serviceDTO.setPrice(service.getPrice());
        serviceDTO.setDescription(service.getDescription());
        serviceDTO.setName(service.getName());
        
        List<Order> listOrders = service.getOrders();
        if(listOrders != null && !listOrders.isEmpty()){
            List<OrderDTO> listOrdersDTO = new ArrayList<OrderDTO>();
            for(Order order : listOrders){
                OrderDTO orderDTO = OrderUtils.getOrderDTOFromEntity(order);
                listOrdersDTO.add(orderDTO);
            }
            serviceDTO.setOrders(listOrdersDTO);
        }
        
        return serviceDTO;
        
    }
    
}