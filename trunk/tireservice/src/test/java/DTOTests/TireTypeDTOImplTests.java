/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOTests;

import cz.muni.fi.pa165.tireservice.dao.TireTypeDAO;
import cz.muni.fi.pa165.tireservice.dto.TireTypeDTO;
import cz.muni.fi.pa165.tireservice.entities.TireType;
import cz.muni.fi.pa165.tireservice.services.ServiceTireType;
import cz.muni.fi.pa165.tireservice.services.ServiceTireTypeImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

/**
 *
 * @author Ivan
 */
@RunWith(MockitoJUnitRunner.class)
public class TireTypeDTOImplTests {
    
    @Mock
    private TireTypeDAO tireTypeDAO;
    
    @InjectMocks
    private ServiceTireType serviceTireType = new ServiceTireTypeImpl();
    
    @Test
    public void GetAllTireTypes(){
       
       //arrange
       TireType tt = new TireType();
       tt.setActive(true);
       tt.setManufacturer("SomeType");
       
       TireType tt2 = new TireType();
       tt2.setDescription("desc");
       tt2.setActive(true);
       
       List<TireType> types = new ArrayList<TireType>();
       types.add(tt);
       types.add(tt2);
       
       TireTypeDTO ttDTO = new TireTypeDTO();
       ttDTO.setActive(true);
       ttDTO.setManufacturer("SomeType");
       
       TireTypeDTO tt2DTO = new TireTypeDTO();
       tt2DTO.setActive(true);
       tt2DTO.setDescription("desc");
       
       List<TireTypeDTO> typesDTOExpected = new ArrayList<TireTypeDTO>();
       typesDTOExpected.add(ttDTO);
       typesDTOExpected.add(tt2DTO);
       
       when(tireTypeDAO.getAllTireTypes()).thenReturn(types);
       
       //act
       List<TireTypeDTO> actual = serviceTireType.getAllTireTypes();
       
       //assert
       assertEquals(typesDTOExpected, actual);
              
    }
    
}