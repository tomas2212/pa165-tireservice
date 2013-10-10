package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Service;
import java.util.List;

/**
 * The DAO layer for managing operations with the data in Service table
 * 
 * @author Martin Makarsky(359978)
 */
public interface ServiceDAO {
    
    /**
     * Gets service with active status by id from the database
     * 
     * @param id the ID of service
     * @return Service with active status by id from the database
     */  
    public Service getServiceById(Long id);
    
    /**
     * Gets all services with active status
     * 
     * @return All services with active status
     */
    public List<Service> getAllServices();
    
    /**
     * Inserts new service into database
     * 
     * @param service Service type that is to be inserted into DB  
     */
    public void insertService(Service service);
    
    /**
     * Updates the service and it's attributes
     * 
     * @param service The service to be updated
     */
    public void updateService(Service service);
    
    /**
     * Removes the service from the list of available services
     * (sets the active attribute to false)
     * 
     * @param service The service to be removed
     */
    public void removeService(Service service);
}
