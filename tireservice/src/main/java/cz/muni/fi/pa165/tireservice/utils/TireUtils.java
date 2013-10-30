/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.utils;

import cz.muni.fi.pa165.tireservice.dto.TireDTO;
import cz.muni.fi.pa165.tireservice.entities.Tire;
import cz.muni.fi.pa165.tireservice.entities.TireType;

/**
 *
 * @author Atares
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
        tire.setTireType(tire.getTireType());
        tire.setOrder(tire.getOrder());

        return tire;
    }
    
     public static TireDTO getTireDTOFromEntity(Tire tire) {
        if (tire == null) {
            return null;
        }
        TireDTO tireDTO = new TireDTO();
        
        tireDTO.setId(tire.getId());
        tireDTO.setAmountOnStore(tire.getAmountOnStore());
   //     tireDTO.setTireType(tire.getTireType());
   //     tireDTO.setOrder(tire.getOrder());      
        //problem s typom - spytat sa Kuba.
        
        return tireDTO;
        
    }

  /*  public static TireDTO getTireDTOFromEntity(Tire tire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Tire tireDTOToEntity(TireDTO tireDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
}
