package cz.muni.fi.pa165.tireservice.utils;

import cz.muni.fi.pa165.tireservice.dto.TireTypeDTO;
import cz.muni.fi.pa165.tireservice.entities.TireType;

/**
 *
 * @author Ivan
 */
public class TireTypeUtils {
    private TireTypeUtils(){};
    
    public static TireType tireTypeDTOToEntity(TireTypeDTO tireTypeDTO) {
        if (tireTypeDTO == null) {
            return null;
        }
        TireType tireType = new TireType();
        
        tireType.setId(tireTypeDTO.getId());
        tireType.setActive(tireTypeDTO.isActive());
        tireType.setPrice(tireTypeDTO.getPrice());
        tireType.setDescription(tireTypeDTO.getDescription());
        tireType.setManufacturer(tireTypeDTO.getManufacturer());
        tireType.setAmountOnStore(tireTypeDTO.getAmountOnStore());
        tireType.setTireRimSize(tireTypeDTO.getTireRimSize());
        tireType.setType(tireTypeDTO.getType());
        
        return tireType;
    }

    public static TireTypeDTO getTireTypeDTOFromEntity(TireType tireType) {
        if (tireType == null) {
            return null;
        }
        TireTypeDTO tireTypeDTO = new TireTypeDTO();
        
        tireTypeDTO.setId(tireType.getId());
        tireTypeDTO.setActive(tireType.isActive());
        tireTypeDTO.setPrice(tireType.getPrice());
        tireTypeDTO.setDescription(tireType.getDescription());
        tireTypeDTO.setManufacturer(tireType.getManufacturer());
        tireTypeDTO.setAmountOnStore(tireType.getAmountOnStore());
        tireTypeDTO.setTireRimSize(tireType.getTireRimSize());
        tireTypeDTO.setType(tireType.getType());
        
        return tireTypeDTO;
    }
}
