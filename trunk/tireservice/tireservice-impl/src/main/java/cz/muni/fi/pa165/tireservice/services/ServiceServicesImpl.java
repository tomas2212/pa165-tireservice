package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.DAO.ServiceDAO;
import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.entities.Service;
import cz.muni.fi.pa165.tireservice.utils.ServicesUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jakub Papcun(359 474)
 */
@org.springframework.stereotype.Service
@Transactional
public class ServiceServicesImpl implements ServiceServices {

    @Autowired
    private ServiceDAO serviceDAO;

    @Transactional
    public ServiceDTO getServiceById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID of service cannot be null");
        }
        Service service = serviceDAO.getServiceById(id);
        ServiceDTO serviceDTO = ServicesUtils.getServiceDTOFromEntity(service);
        return serviceDTO;
    }

    @Transactional
    public List<ServiceDTO> getAllServices() {
        List<ServiceDTO> toReturn = new ArrayList<ServiceDTO>();
        List<Service> services = serviceDAO.getAllServices();
        for (Service service : services) {
            ServiceDTO serviceDTO = ServicesUtils.getServiceDTOFromEntity(service);
            toReturn.add(serviceDTO);
        }
        return toReturn;
    }

    @Transactional
    public List<ServiceDTO> getAllEnabledServices() {
        List<ServiceDTO> toReturn = new ArrayList<ServiceDTO>();
        List<Service> services = serviceDAO.getAllActiveServices();
        for (Service service : services) {
            ServiceDTO serviceDTO = ServicesUtils.getServiceDTOFromEntity(service);
            toReturn.add(serviceDTO);
        }
        return toReturn;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void createService(ServiceDTO serviceDTO) {
        if (serviceDTO == null) {
            throw new IllegalArgumentException("Service cannot be null");
        }
        if (serviceDTO.getId() != null) {
            throw new IllegalArgumentException("New Service ID must be null");
        }
        Service service = ServicesUtils.serviceDTOToEntity(serviceDTO);
        serviceDAO.insertService(service);
        serviceDTO.setId(service.getId());
    }

    @Transactional
    public void updateService(ServiceDTO serviceDTO) {
        if (serviceDTO == null) {
            throw new IllegalArgumentException("Service for updating cannot be null");
        }
        if (serviceDTO.getId() == null) {
            throw new IllegalArgumentException("ID of Service for updating cannot be null");
        }
        Service service = ServicesUtils.serviceDTOToEntity(serviceDTO);
        serviceDAO.updateService(service);
    }

    @Transactional
    public void removeService(ServiceDTO serviceDTO) {
        if (serviceDTO == null) {
            throw new IllegalArgumentException("Service for removing cannot be null");
        }
        if (serviceDTO.getId() == null) {
            throw new IllegalArgumentException("ID of Service for removing cannot be null");
        }
        Service service = ServicesUtils.serviceDTOToEntity(serviceDTO);
        serviceDAO.removeService(service);
    }
}
