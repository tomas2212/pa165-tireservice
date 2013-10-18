package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entities.TireType;
import java.util.List;

/**
 * The DAO layer for the managing operations with the data in Tire Types table
 *
 * @author Jakub Papcun(359 474)
 */
public interface TireTypeDAO {

    /**
     * Gets tire type with active status by id from the database
     *
     * @param id the ID of tire type
     * @return Tire type with active status by id from the database
     */
    public TireType getTireTypeById(Long id);

    /**
     * Gets all tire types with active status
     *
     * @return All tire types with active status
     */
    public List<TireType> getAllTireTypes();

    /**
     * Inserts new tire type into database
     *
     * @param tireType The tire type that is to be inserted into DB
     */
    public void insertTireType(TireType tireType);

    /**
     * Updates the tire type and it's attributes
     *
     * @param tireType The tire type to be updated
     */
    public void updateTireType(TireType tireType);

    /**
     * Removes the tire type from the list of available tire types (sets the
     * active attribute to false)
     *
     * @param tireType The tire type to be removed
     */
    public void removeTireType(TireType tireType);
}
