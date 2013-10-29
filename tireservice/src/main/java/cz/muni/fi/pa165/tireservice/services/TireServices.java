/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.dto.TireDTO;
import java.util.List;

/**
 *
 * @author Stefan Sakala
 */
public interface TireServices {
    
    
    /**
     * Gets Tire with active status by id from the db
     * 
     * @param id the ID of tire
     * @return Tire with active status by id from the db
     */  
    public TireDTO getTireById(Long id);
    
    /**
     * Gets all tires (active and inactive)
     * 
     * @return all active and inactive tires
     */
    public List<TireDTO> getAllTires();
    
    /**
     * Gets all tires with active status
     * 
     * @return All tires with active status
     */
    public List<TireDTO> getAllEnabledTires();
    
    /**
     * Creates new tire into db
     * 
     * @param tire Tire that will be inserted into DB  
     */
    public void createTire(TireDTO tire);
    
    /**
     * Updates the tire and it's attributes
     * 
     * @param tire The tire which will be updated
     */
    public void updateTire(TireDTO tire);
    
    /**
     * Removes the tire from the list of available tires
     * (sets the active attribute to false)
     * 
     * @param tire The tire which will be removed
     */
    public void removeTire(TireDTO tire);
    
}

