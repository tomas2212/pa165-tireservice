/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOTests;

import cz.muni.fi.pa165.tireservice.dao.TireTypeDAO;
import cz.muni.fi.pa165.tireservice.services.PersonServices;
import cz.muni.fi.pa165.tireservice.services.PersonServicesImpl;
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
 * @author Jakub Papcun(359 474)
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonDTOImplTests {
    
    @Mock
    private TireTypeDAO tireTypeDAO;
    
    @InjectMocks
    private PersonServices person = new PersonServicesImpl();
    
    @Test
    public void getAllPersons(){
       
       //arrange
//       TireType tt = new TireType();
//       tt.setActive(true);
//       tt.setManufacturer("SomeType");
//       
//       TireType tt2 = new TireType();
//       tt2.setDescription("desc");
//       tt2.setActive(true);
//       
//       List<TireType> types = new ArrayList<TireType>();
//       types.add(tt);
//       types.add(tt2);
//       
//       TireTypeDTO ttDTO = new TireTypeDTO();
//       ttDTO.setActive(true);
//       ttDTO.setManufacturer("SomeType");
//       
//       TireTypeDTO tt2DTO = new TireTypeDTO();
//       tt2DTO.setActive(true);
//       tt2DTO.setDescription("desc");
//       
//       List<TireTypeDTO> typesDTOExpected = new ArrayList<TireTypeDTO>();
//       typesDTOExpected.add(ttDTO);
//       typesDTOExpected.add(tt2DTO);
//       
//       when(tireTypeDAO.getAllTireTypes()).thenReturn(types);
//       
//       //act
//       List<TireTypeDTO> actual = person.getAllTireTypes();
//       
//       //assert
//       assertEquals(typesDTOExpected, actual);
              
    }
    
}
