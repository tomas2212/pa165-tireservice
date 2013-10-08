/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Service;
import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.util.List;

/**
 *
 * @author Martin(359978)
 */
public interface ServiceDAO {
    
    public Service getServiceById(Long id);
    
    public List<Service> getAllServices();
    
    public void insertService(Service service);
    
    public void updateService(Service service);
    
    public void removeService(Service service);
}
