/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.DTOTests;

import cz.muni.fi.pa165.tireservice.dao.TireDAO;
import cz.muni.fi.pa165.tireservice.dao.TireDAOImpl;
import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.dto.TireDTO;
import cz.muni.fi.pa165.tireservice.entities.Tire;
import cz.muni.fi.pa165.tireservice.services.TireServices;
import cz.muni.fi.pa165.tireservice.services.TireServicesImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

/**
 *
 * @author Stefan Sakala (359772)
 */
@RunWith(MockitoJUnitRunner.class)
public class TireDTOImplTests {
    
    @Mock
    private TireDAO tireDAO;
    
    @InjectMocks
    private TireServices tireService = new TireServicesImpl();
    
    @Test
    public void getTireIdIsRight(){
       Tire t = new Tire();
       
       TireDTO expected = new TireDTO();
       
       when(tireDAO.getTireById(1l)).thenReturn(t);
       
       TireDTO actual = tireService.getTireById(1l);
       
       assertEquals(expected, actual);
    }
    
    @Test
    public void getTireIdIsWrong(){
       Tire t = new Tire();
        
       when(tireDAO.getTireById(2l)).thenReturn(t);
       
       TireDTO actual = tireService.getTireById(1l);
       
       assertNull(actual);
    }
     
    
    @Test(expected=IllegalArgumentException.class)
    public void updateNullTire(){
        tireService.updateTire(null);
        fail("IllegalArgument expected as the tire for updating was NULL");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void updateTireWithId(){
        TireDTO t = new TireDTO();
        t.setId(1L);
        tireService.updateTire(t);
        
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void removeNullTire(){
        tireService.removeTire(null);
        
        fail("err");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void removeTireWithId(){
        TireDTO t = new TireDTO();
        t.setId(1L);
        tireService.removeTire(t);
       fail("err");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void insertNullTire(){
        tireService.createTire(null);
        fail("IllegalArgument expected");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void insertTireDTOWithId(){
        TireDTO t = new TireDTO();
        t.setId(1L);
        tireService.createTire(t);
        fail("Creating tire with ID which is set is not allowed");
    }
    
    
}

