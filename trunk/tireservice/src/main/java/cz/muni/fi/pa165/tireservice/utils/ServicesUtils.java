package cz.muni.fi.pa165.tireservice.utils;

import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.entities.Service;

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
        
        //TODO setting orders
        
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
        
        //TODO setting orders
        return serviceDTO;
        
    }
    
}
