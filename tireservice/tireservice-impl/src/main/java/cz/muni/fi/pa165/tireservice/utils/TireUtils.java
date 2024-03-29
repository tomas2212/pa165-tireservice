package cz.muni.fi.pa165.tireservice.utils;

import cz.muni.fi.pa165.tireservice.dto.TireDTO;
import cz.muni.fi.pa165.tireservice.entities.Tire;

/**
 *
 * @author Stefan Sakala (359772)
 */
public class TireUtils {
    
    
     private TireUtils(){};
    
    public static Tire tireDTOToEntity(TireDTO tireDTO) {
        if (tireDTO == null) {
            return null;
        }
        Tire tire = new Tire();

        tire.setId(tireDTO.getId());
        tire.setAmountOnStore(tireDTO.getAmountOnStore());
        tire.setTireType(TireTypeUtils.tireTypeDTOToEntity(tireDTO.getTireType()));

//        if (tire.getOrder() != null) {
//            tire.setOrder(OrderUtils.orderDTOToEntity(tireDTO.getOrder()));
//        }

        return tire;
    }

    public static TireDTO getTireDTOFromEntity(Tire tire) {
        if (tire == null) {
            return null;
        }
        TireDTO tireDTO = new TireDTO();

        tireDTO.setId(tire.getId());
        tireDTO.setAmountOnStore(tire.getAmountOnStore());
        tireDTO.setTireType(TireTypeUtils.getTireTypeDTOFromEntity(tire.getTireType()));

//        if (tire.getOrder() != null) {
//            tireDTO.setOrder(OrderUtils.getOrderDTOFromEntity(tire.getOrder()));
//        }

        return tireDTO;

    }
}
