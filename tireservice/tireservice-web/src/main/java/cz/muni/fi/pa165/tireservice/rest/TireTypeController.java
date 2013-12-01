/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.rest;

import cz.muni.fi.pa165.tireservice.dto.TireTypeDTO;
import cz.muni.fi.pa165.tireservice.services.ServiceTireType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Atares
 */
@Controller
@RequestMapping("tiretypes")
public class TireTypeController {

    @Autowired
    ServiceTireType tireTypeServices;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TireTypeDTO getTire(@PathVariable("id") Long id) {
        return tireTypeServices.getTireTypeById(id);
    }

    @RequestMapping
    @ResponseBody
    public List<TireTypeDTO> allTireTypes() {
        List<TireTypeDTO> tireTypes = tireTypeServices.getAllTireTypes();
        return tireTypes;
    }
    
     @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public TireTypeDTO createTireType(@RequestBody TireTypeDTO tireTypeDTO) {
        tireTypeServices.createTireType(tireTypeDTO);
        return tireTypeDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public TireTypeDTO deleteTireType(@PathVariable("id") Long id) {
        TireTypeDTO tireTypeDTO = tireTypeServices.getTireTypeById(id);
        if (tireTypeDTO != null) {
            tireTypeServices.removeTireType(tireTypeDTO);
        } else {
            throw new IllegalArgumentException("Tire type with this id was not found");
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public TireTypeDTO updateTireType(@PathVariable("id") Long id, @RequestBody TireTypeDTO tireTypeDTO) {
        tireTypeDTO.setId(id);
        tireTypeServices.updateTireType(tireTypeDTO);

        return tireTypeDTO;
    }
}