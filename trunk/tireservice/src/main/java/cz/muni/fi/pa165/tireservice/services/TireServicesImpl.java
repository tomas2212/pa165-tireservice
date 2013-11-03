/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.DAO.TireDAO;
import cz.muni.fi.pa165.tireservice.dto.TireDTO;
import cz.muni.fi.pa165.tireservice.entities.Tire;
import cz.muni.fi.pa165.tireservice.utils.TireUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Stefan Sakala (359772)
 */
@Service
@Transactional
public class TireServicesImpl implements TireServices {

    @Autowired
    private TireDAO tireDAO;

    @Transactional
    public TireDTO getTireById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID of tire cannot be null");
        }
        Tire tire = tireDAO.getTireById(id);
        TireDTO tireDTO = TireUtils.getTireDTOFromEntity(tire);
        return tireDTO;
    }

    @Transactional
    public List<TireDTO> getAllTires() {
        List<TireDTO> toReturn = new ArrayList<TireDTO>();
        List<Tire> tires = tireDAO.getAllTires();
        for (Tire tire : tires) {
            TireDTO tireDTO = TireUtils.getTireDTOFromEntity(tire);
            toReturn.add(tireDTO);
        }
        return toReturn;
    }

    @Transactional
    public List<TireDTO> getAllEnabledTires() {
        List<TireDTO> toReturn = new ArrayList<TireDTO>();
        List<Tire> tires = tireDAO.getAllTires();
        for (Tire tire : tires) {
            TireDTO tireDTO = TireUtils.getTireDTOFromEntity(tire);
            toReturn.add(tireDTO);
        }
        return toReturn;
    }

    @Transactional
    public void createTire(TireDTO tireDTO) {
        if (tireDTO == null) {
            throw new IllegalArgumentException("Tire cannot be null");
        }
        if (tireDTO.getId() != null) {
            throw new IllegalArgumentException("New Tire ID must be null");
        }
        Tire tire = TireUtils.tireDTOToEntity(tireDTO);
        tireDAO.insertTire(tire);
        tireDTO.setId(tire.getId());
    }

    @Transactional
    public void updateTire(TireDTO tireDTO) {
        if (tireDTO == null) {
            throw new IllegalArgumentException("Tire for updating cannot be null");
        }
        if (tireDTO.getId() == null) {
            throw new IllegalArgumentException("ID of Tire for updating cannot be null");
        }
        Tire tire = TireUtils.tireDTOToEntity(tireDTO);
        tireDAO.updateTire(tire);
    }

    @Transactional
    public void removeTire(TireDTO tireDTO) {
        if (tireDTO == null) {
            throw new IllegalArgumentException("Tire for removing cannot be null");
        }
        if (tireDTO.getId() == null) {
            throw new IllegalArgumentException("ID of Tire for removing cannot be null");
        }
        Tire tire = TireUtils.tireDTOToEntity(tireDTO);
        tireDAO.removeTire(tire);
    }
}
