package cz.muni.fi.pa165.tireservice.rest;

import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.services.ServiceServices;
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
 * @author Stefan Sakala
 */
@Controller
@RequestMapping("services")
public class ServicesController {

    @Autowired
    ServiceServices serviceServices;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ServiceDTO get(@PathVariable("id") Long id) {
        return serviceServices.getServiceById(id);
    }

    @RequestMapping
    @ResponseBody
    public List<ServiceDTO> allServices() {
        List<ServiceDTO> services = serviceServices.getAllEnabledServices();
        return services;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ServiceDTO createService(@RequestBody ServiceDTO serviceDTO) {
        serviceServices.createService(serviceDTO);
        return serviceDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ServiceDTO deleteService(@PathVariable("id") Long id) {
        ServiceDTO serviceDTO = serviceServices.getServiceById(id);
        if (serviceDTO != null) {
            serviceServices.removeService(serviceDTO);
        } else {
            throw new IllegalArgumentException("Service with this id was not found");
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ServiceDTO updateService(@PathVariable("id") Long id, @RequestBody ServiceDTO serviceDTO) {
        serviceDTO.setId(id);
        serviceServices.updateService(serviceDTO);

        return serviceDTO;
    }
}
