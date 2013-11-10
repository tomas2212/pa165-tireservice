/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.dto.TireTypeDTO;
import java.util.List;

/**
 *
 * @author Ivan
 */
public interface ServiceTireType {
    /**
     * Gets tire type with active status by id from the database
     *
     * @param id the ID of tire type
     * @return TireTypeDTO with active status by id from the database
     */
    public TireTypeDTO getTireTypeById(Long id);

    /**
     * Gets all tire types with active status
     *
     * @return All tire types with active status
     */
    public List<TireTypeDTO> getAllTireTypes();

    /**
     * Creates new tireTypeDTO
     *
     * @param tireTypeDTO The tire type that is to be inserted into DB
     */
    public void createTireType(TireTypeDTO tireTypeDTO);

    /**
     * Updates the tire type and it's attributes
     *
     * @param tireTypeDTO The tire type to be updated
     */
    public void updateTireType(TireTypeDTO tireTypeDTO);

    /**
     * Removes the tire type from the list of available tire types (sets the
     * active attribute to false)
     *
     * @param tireTypeDTO The tire type to be removed
     */
    public void removeTireType(TireTypeDTO tireTypeDTO);
}
