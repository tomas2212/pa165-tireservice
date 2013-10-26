package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import java.util.List;

/**
 *
 * @author Jakub Papcun(359 474)
 */
public interface ServiceServices {
    
    /**
     * Gets service with active status by id from the database
     * 
     * @param id the ID of service
     * @return Service with active status by id from the database
     */  
    public ServiceDTO getServiceById(Long id);
    
    /**
     * Gets services active and inactive services as well
     * 
     * @return all active and inactive services
     */
    public List<ServiceDTO> getAllServices();
    
    /**
     * Gets all services with active status
     * 
     * @return All services with active status
     */
    public List<ServiceDTO> getAllEnabledServices();
    
    /**
     * Creates new service into database
     * 
     * @param service Service type that is to be inserted into DB  
     */
    public void createService(ServiceDTO service);
    
    /**
     * Updates the service and it's attributes
     * 
     * @param service The service to be updated
     */
    public void updateService(ServiceDTO serviceDTO);
    
    /**
     * Removes the service from the list of available services
     * (sets the active attribute to false)
     * 
     * @param service The service to be removed
     */
    public void removeService(ServiceDTO serviceDTO);
    
}
