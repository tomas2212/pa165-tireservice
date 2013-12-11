package cz.muni.fi.pa165.tireservice.web;

import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.services.ServiceServices;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.BigDecimalTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 *
 * @author Martin
 */
@UrlBinding("/service/{$event}/")
public class ServiceActionBean implements ActionBean, ValidationErrorHandler{
    private static final String LIST = "/services/list.jsp";
    
    private ActionBeanContext actionBeanContext;
    private List<ServiceDTO> services;
    private ServiceDTO serviceDTO;
    
    @SpringBean
    protected ServiceServices serviceServices;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"delete"}, field = "id", required = true),
        @Validate(on = {"save", "add"}, field = "name", required = true, maxlength=50),
        @Validate(on = {"save", "add"}, field = "description", required = true, maxlength=200),
        @Validate(on = {"save", "add"}, field = "price", required = true, minvalue=1, converter = BigDecimalTypeConverter.class)
    })
    
    public ServiceDTO getService() {
        return serviceDTO;
    }

    public void setService(ServiceDTO service) {
        this.serviceDTO = service;
    }
    
    
    @DefaultHandler
    public Resolution list() {
        services = getServices();
        return new ForwardResolution(LIST);
    }
    
    public Resolution edit() {
        return new ForwardResolution(LIST);
    }
    
    public Resolution add() {
        try{
            serviceServices.createService(serviceDTO);
            getContext().getMessages().add(new LocalizableMessage("service.created"));
        }
        catch(Exception ex){
            getContext().getMessages().add(new SimpleMessage("error: " + ex.getLocalizedMessage()));
        }
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        try{
            serviceServices.removeService(serviceDTO);
            getContext().getMessages().add(new LocalizableMessage("service.removed"));
        }
        catch(Exception ex){
            getContext().getMessages().add(new SimpleMessage("error: " + ex.getLocalizedMessage()));
        }
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public List<ServiceDTO> getServices(){
        return serviceServices.getAllServices();
    }
    
    @Override
    public void setContext(ActionBeanContext abc) {
        this.actionBeanContext = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return this.actionBeanContext;
    }
    
    @Before (stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "delete"})
    public void loadServiceFromDatabase() {
        String ids = getContext().getRequest().getParameter("service.id");
        if (ids == null) return;
        serviceDTO = serviceServices.getServiceById(Long.parseLong(ids));
    }
    
    public Resolution save() {
        serviceServices.updateService(serviceDTO);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        services = serviceServices.getAllServices();
        return null;
    }
    
}
