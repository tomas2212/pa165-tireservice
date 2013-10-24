package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.dao.ServiceDAO;
import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.entities.Service;
import cz.muni.fi.pa165.tireservice.utils.ServicesUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Jakub Papcun(359 474)
 */
public class ServiceServicesImpl implements ServiceServices {
    
    @Autowired
    private ServiceDAO serviceDAO;

    public ServiceDTO getServiceById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("ID of service cannot be null");
        }
        Service service = serviceDAO.getServiceById(id);
        ServiceDTO serviceDTO = ServicesUtils.getServiceDTOFromEntity(service);
        return serviceDTO;
    }

    public ServiceDTO getServiceByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ServiceDTO> getAllServices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ServiceDTO> getAllEnabledServices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void createService(ServiceDTO serviceDTO) {
        if(serviceDTO == null){
            throw new IllegalArgumentException("Service cannot be null");
        }
        if(serviceDTO.getId() != null){
            throw new IllegalArgumentException("New Service ID must be null");
        }
        // TODO some validation maybe
        Service service = ServicesUtils.serviceDTOToEntity(serviceDTO);
        serviceDAO.insertService(service);
        serviceDTO.setId(service.getId());
    }

    public void updateService(ServiceDTO serviceDTO) {
        if(serviceDTO == null){
            throw new IllegalArgumentException("Service for updating cannot be null");
        }
        if(serviceDTO.getId() == null){
            throw new IllegalArgumentException("ID of Service for updating cannot be null");
        }
        // validation of service?
        Service service = ServicesUtils.serviceDTOToEntity(serviceDTO);
        serviceDAO.updateService(service);
    }

    public void removeService(ServiceDTO serviceDTO) {
        if(serviceDTO == null){
            throw new IllegalArgumentException("Service for removing cannot be null");
        }
        if(serviceDTO.getId() == null){
            throw new IllegalArgumentException("ID of Service for removing cannot be null");
        }
        // validation of service?
        Service service = ServicesUtils.serviceDTOToEntity(serviceDTO);
        serviceDAO.removeService(service);
    }
    
}
