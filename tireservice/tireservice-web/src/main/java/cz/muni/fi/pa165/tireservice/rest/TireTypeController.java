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
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping
    @ResponseBody
    public List<TireTypeDTO> allTireTypes() {
        List<TireTypeDTO> tireTypes = tireTypeServices.getAllTireTypes();
        return tireTypes;
    }
}