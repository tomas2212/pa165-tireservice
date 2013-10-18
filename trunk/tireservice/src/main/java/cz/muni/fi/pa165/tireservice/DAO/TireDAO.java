package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entities.Tire;
import java.util.List;

/**
 *
 * @author Ivan Novak
 */
public interface TireDAO {

    /**
     * Gets tire with active status by id from the database
     *
     * @param id the ID of tire
     * @return Tire with active status by id from the database
     */
    public Tire getTireById(Long id);

    /**
     * Gets all tire with active status
     *
     * @return All tire with active status
     */
    public List<Tire> getAllTires();

    /**
     * Inserts new tire into database
     *
     * @param tire The tire that is to be inserted into DB
     */
    public void insertTire(Tire tire);

    /**
     * Updates the tire and it's attributes
     *
     * @param tire The tire to be updated
     */
    public void updateTire(Tire tire);

    /**
     * Removes the tire from the list of available tires
     *
     * @param tire The tire to be removed
     */
    public void removeTire(Tire tire);
}
