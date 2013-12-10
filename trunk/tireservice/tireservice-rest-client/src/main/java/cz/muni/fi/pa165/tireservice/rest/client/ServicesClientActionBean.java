package cz.muni.fi.pa165.tireservice.rest.client;

import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.rest.util.PropertyHelper;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */

@Component
@UrlBinding("/services/{$event}/")
public class ServicesClientActionBean implements ActionBean {
    
    @SpringBean
    private PropertyHelper ph;
    
    private final static Logger logger = LoggerFactory.getLogger(ServicesClientActionBean.class);
    
    private ActionBeanContext context;
    
    @SpringBean
    private RestTemplate restTemplate;
//    @ValidateNestedProperties(value = {
//        @Validate(on = {"create", "save"}, field = "name", required = true),
//        @Validate(on = {"create", "save"}, field = "price", required = true, minvalue = 1)
//    })
    private ServiceDTO service;

    @DefaultHandler
    public Resolution list() {
        logger.info("listing");        
        return new ForwardResolution("/services/list.jsp");
    }

    public Resolution edit() {
        logger.debug("edit() {}", service);
        return new ForwardResolution("/services/edit.jsp");
    }

    public Resolution save() {
        logger.debug("save() {}", service);
        restTemplate.put(getURL() + "/{id}", service, service.getId());
        System.out.println(service.toString());
        return new RedirectResolution(this.getClass(), "list.jsp");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadServiceFromDatabase() {
        String id = context.getRequest().getParameter("service.id");
        if (id != null) {
            service = restTemplate.getForObject(getURL() + "/{id}", ServiceDTO.class, id);
        }
    }

    public Resolution delete() {
        logger.debug("delete({})", service);
        restTemplate.delete(getURL() + "/{id}", service.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution newService() {
        logger.info("newService()");
        return new ForwardResolution("/services/create.jsp");
    }

    public Resolution create() {
        logger.debug("create() {}", service);
        System.out.println(service);
        restTemplate.postForObject(getURL() + "/", service, ServiceDTO.class);
        return new RedirectResolution(this.getClass(), "list");
    }

    public ServiceDTO[] getAllServices() {
        logger.debug("getAllServices()");
        ServiceDTO[] services = restTemplate.getForObject(getURL() + "/", ServiceDTO[].class);
        return services;
    }
    
   
    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
    
    private String getURL() {
        return ph.getServiceURL();
    }
}
