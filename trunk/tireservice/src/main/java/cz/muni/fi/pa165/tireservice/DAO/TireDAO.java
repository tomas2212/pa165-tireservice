/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DAO;

import cz.muni.fi.pa165.tireservice.entities.Tire;
import java.util.List;

/**
 *
 * @author Jakub Papcun(359 474)
 */
public interface TireDAO {
    
    public Tire getTireById(Long id);
    
    public List<Tire> getAllTires();
    
    public void insertTire(Tire tire);
    
    public void updateTire(Tire tire);
    
    public void removeTire(Tire tire);
    
}
