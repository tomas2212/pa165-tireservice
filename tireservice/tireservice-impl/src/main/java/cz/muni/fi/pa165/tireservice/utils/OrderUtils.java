package cz.muni.fi.pa165.tireservice.utils;

import cz.muni.fi.pa165.tireservice.dto.OrderDTO;
import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.dto.TireDTO;
import cz.muni.fi.pa165.tireservice.entities.Order;
import cz.muni.fi.pa165.tireservice.entities.Service;
import cz.muni.fi.pa165.tireservice.entities.Tire;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jakub Papcun(359 474)
 */
public class OrderUtils {

    private OrderUtils() {
    }

    public static OrderDTO getOrderDTOFromEntity(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setActive(order.isActive());
        orderDTO.setCarType(order.getCarType());
        orderDTO.setDate(order.getDate());
        orderDTO.setId(order.getId());

        if (order.getPerson() != null) {
            orderDTO.setPerson(PersonUtils.getPersonDTOFromEntity(order.getPerson()));
        }

        List<Tire> listTires = order.getTires();
        if (listTires != null && !listTires.isEmpty()) {
            List<TireDTO> listTiresDTO = new ArrayList<TireDTO>();
            for (Tire tire : listTires) {
                TireDTO tireDTO = TireUtils.getTireDTOFromEntity(tire);
                listTiresDTO.add(tireDTO);
            }
            orderDTO.setTires(listTiresDTO);
        }

        List<Service> listServices = order.getServices();
        if (listServices != null && !listServices.isEmpty()) {
            List<ServiceDTO> listServiceDTO = new ArrayList<ServiceDTO>();
            for (Service service : listServices) {
                ServiceDTO serviceDTO = ServicesUtils.getServiceDTOFromEntity(service);
                listServiceDTO.add(serviceDTO);
            }
            orderDTO.setServices(listServiceDTO);
        }

        return orderDTO;

    }

    public static Order orderDTOToEntity(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();
        order.setActive(orderDTO.isActive());
        order.setCarType(orderDTO.getCarType());
        order.setDate(orderDTO.getDate());
        order.setId(orderDTO.getId());

        if (orderDTO.getPerson() != null) {
            order.setPerson(PersonUtils.personDTOToEntity(orderDTO.getPerson()));
        }

        List<TireDTO> listTiresDTO = orderDTO.getTires();
        if (listTiresDTO != null && !listTiresDTO.isEmpty()) {
            List<Tire> listTires = new ArrayList<Tire>();
            for (TireDTO tireDTO : listTiresDTO) {
                Tire tire = TireUtils.tireDTOToEntity(tireDTO);
                listTires.add(tire);
            }
            order.setTires(listTires);
        }

        List<ServiceDTO> listServiceDTO = orderDTO.getServices();
        if (listServiceDTO != null && !listServiceDTO.isEmpty()) {
            List<Service> listServices = new ArrayList<Service>();
            for (ServiceDTO serviceDTO : listServiceDTO) {
                Service service = ServicesUtils.serviceDTOToEntity(serviceDTO);
                listServices.add(service);
            }
            order.setServices(listServices);
        }

        return order;
    }
}
