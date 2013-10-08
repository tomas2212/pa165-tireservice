/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.util.List;

/**
 *
 * @author Jakub Papcun(359 474)
 */
public interface TireTypeDAO {
    
    public TireType getTireTypeById(Long id);
    
    public List<TireType> getAllTireTypes();
    
    public void insertTireType(TireType tire);
    
    public void updateTireType(TireType tire);
    
    public void removeTireType(TireType tire);
    
}
