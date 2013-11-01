/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.servicestests;

import cz.muni.fi.pa165.tireservice.dao.TireDAO;
import cz.muni.fi.pa165.tireservice.dao.TireDAOImpl;
import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.dto.TireDTO;
import cz.muni.fi.pa165.tireservice.entities.Tire;
import cz.muni.fi.pa165.tireservice.services.TireServices;
import cz.muni.fi.pa165.tireservice.services.TireServicesImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import org.mockito.Spy;

/**
 *
 * @author Stefan Sakala (359772)
 */
@RunWith(MockitoJUnitRunner.class)
public class TireDTOImplTests {

    @Spy
    private TireDAOImpl tireDAO = new TireDAOImpl();
    @InjectMocks
    private TireServices tireService = new TireServicesImpl();

    @Test
    public void getTireIdIsRight() {
        Tire t = new Tire();

        TireDTO expected = new TireDTO();

        doReturn(t).when(tireDAO).getTireById((Long) any());


        TireDTO actual = tireService.getTireById(1l);

        assertEquals(expected, actual);
    }

    @Test
    public void getTireIdIsWrong() {

        doReturn(null).when(tireDAO).getTireById(anyLong());

        TireDTO actual = tireService.getTireById(1l);

        assertNull(actual);
    }

    @Test
    public void getAllTires() {


        Tire t = new Tire();

        Tire t2 = new Tire();

        List<Tire> tires = new ArrayList<Tire>();
        tires.add(t);
        tires.add(t2);

        TireDTO tireDTO = new TireDTO();

        TireDTO tireDTO2 = new TireDTO();

        List<TireDTO> tiresDTOexp = new ArrayList<TireDTO>();
        tiresDTOexp.add(tireDTO);
        tiresDTOexp.add(tireDTO2);

        doReturn(tires).when(tireDAO).getAllTires();

        List<TireDTO> actual = tireService.getAllTires();

        assertEquals(tiresDTOexp, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTireThrowsExceptionBecauseObjectIsNull() {
        tireService.updateTire(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTireThrowsException() {
        TireDTO tireDTO = new TireDTO();

        tireService.updateTire(tireDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeTireThrowsExceptionBecauseObjectIsNull() {
        tireService.removeTire(null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void removeTireThrowsExceptionIdIsNull() {
        TireDTO tireDTO = new TireDTO();
        tireDTO.setId(0l);

        tireService.removeTire(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTireThrowsExceptionObjectIsNull() {
        tireService.createTire(null);
    }
}
