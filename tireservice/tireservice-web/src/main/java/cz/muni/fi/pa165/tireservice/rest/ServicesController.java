package cz.muni.fi.pa165.tireservice.rest;

import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.services.ServiceServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("services")
public class ServicesController {
    
        @Autowired
	ServiceServices serviceServices;

	@RequestMapping
	@ResponseBody
	public List<ServiceDTO> allServices() {
            List<ServiceDTO> services = serviceServices.getAllEnabledServices();
            return services;
	}
        
}