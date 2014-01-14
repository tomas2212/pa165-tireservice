package cz.muni.fi.pa165.tireservice.services;

import cz.muni.fi.pa165.tireservice.DAO.TireTypeDAO;
import cz.muni.fi.pa165.tireservice.dto.TireTypeDTO;
import cz.muni.fi.pa165.tireservice.entities.TireType;
import cz.muni.fi.pa165.tireservice.utils.TireTypeUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ivan
 */
@Service
@Transactional
public class ServiceTireTypeImpl implements ServiceTireType {

    @Autowired
    TireTypeDAO tireTypeDAO;
    
    @Transactional
    public TireTypeDTO getTireTypeById(Long id) {
        TireType tireType = tireTypeDAO.getTireTypeById(id);
        TireTypeDTO tireTypeDTO = TireTypeUtils.getTireTypeDTOFromEntity(tireType);
        return tireTypeDTO;
    }

    @Transactional
    public List<TireTypeDTO> getAllTireTypes() 
    {
        List<TireType> tireTypes = tireTypeDAO.getAllTireTypes();
        List<TireTypeDTO> tireTypesDTO = new ArrayList<TireTypeDTO>();
        for(TireType t : tireTypes){
            tireTypesDTO.add(TireTypeUtils.getTireTypeDTOFromEntity(t));
        }
            
        return tireTypesDTO;
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void updateTireType(TireTypeDTO tireTypeDTO) {
        TireType tireType = TireTypeUtils.tireTypeDTOToEntity(tireTypeDTO);
        tireTypeDAO.updateTireType(tireType);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void removeTireType(TireTypeDTO tireTypeDTO) {
        TireType tireType = TireTypeUtils.tireTypeDTOToEntity(tireTypeDTO);
        tireTypeDAO.removeTireType(tireType);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void createTireType(TireTypeDTO tireTypeDTO) {
        TireType tireType = TireTypeUtils.tireTypeDTOToEntity(tireTypeDTO);
        tireTypeDAO.insertTireType(tireType);
        tireTypeDTO.setId(tireType.getId());
    }
    
}
