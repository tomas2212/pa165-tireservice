/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.web;

import cz.muni.fi.pa165.tireservice.dto.TireTypeDTO;
import cz.muni.fi.pa165.tireservice.services.ServiceTireType;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;


/**
 *
 * @author Atares
 */

@UrlBinding("/tiretype/{$event}/")
public class TireTypeActionBean implements ActionBean, ValidationErrorHandler{
    
    private static final String LIST = "/tiretype/list.jsp";
    private static final String EDIT = "/tiretype/edit.jsp";
    
    private ActionBeanContext actionBeanContext;
    private List<TireTypeDTO> tireType;
    private TireTypeDTO tireTypeDTO;
    
    @SpringBean
    protected ServiceTireType tireTypeServices;

    @ValidateNestedProperties(value = {
        @Validate(on = {"delete"}, field = "id", required = true),
        @Validate(on = {"save", "add"}, field = "type", required = true, maxlength=5),
        @Validate(on = {"save", "add"}, field = "manufactuer", required = true, maxlength=5),
        @Validate(on = {"save", "add"}, field = "description", required = true, maxlength=5),
        @Validate(on = {"save", "add"}, field = "tireRimSize", required = true, maxvalue=50),
        @Validate(on = {"save", "add"}, field = "ammountOnStore", required = true, minvalue=0),
        @Validate(on = {"save", "add"}, field = "price", required = true, minvalue=0)
    })
    public ActionBeanContext getActionBeanContext() {
        return actionBeanContext;
    }

    public void setActionBeanContext(ActionBeanContext actionBeanContext) {
        this.actionBeanContext = actionBeanContext;
    }

    public List<TireTypeDTO> getTireType() {
        return tireTypeServices.getAllTireTypes();
    }

    public void setTireType(List<TireTypeDTO> tireType) {
        this.tireType = tireType;
    }

    public TireTypeDTO getTireTypeDTO() {
        return tireTypeDTO;
    }

    public void setTireTypeDTO(TireTypeDTO tireTypeDTO) {
        this.tireTypeDTO = tireTypeDTO;
    }

    @DefaultHandler
    public Resolution list() {
        tireType = getTireType();
        return new ForwardResolution("/tiretype/list.jsp");
    }
    
    public Resolution edit(){
        return new ForwardResolution(EDIT);
    }
    
    
    
     public Resolution add(){
      try{
          
          tireTypeServices.createTireType(tireTypeDTO);        
         getContext().getMessages().add(new SimpleMessage("Tire Type added successfully"));                                                                      
        }
        catch(Exception ex){
           getContext().getMessages().add(new SimpleMessage("error: "+ex.getLocalizedMessage()));                                                                      
        }            
        
        return new RedirectResolution(this.getClass(), "list");
    }
     
       public Resolution delete(){
        try{
            tireTypeServices.removeTireType(tireTypeDTO);
            getContext().getMessages().add(new SimpleMessage("Tire Type disabled"));
        }catch(Exception ex){
            getContext().getMessages().add(new SimpleMessage("error: " + ex.getLocalizedMessage()));
        }
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public List<TireTypeDTO> getTireTypes(){
        return tireTypeServices.getAllTireTypes();
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.actionBeanContext = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return this.actionBeanContext;
    }
    
    

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "delete"})
    public void loadTireTypeFromDatabase() {
        String ids = getContext().getRequest().getParameter("tireTypeDTO.id");
        if (ids == null) return;
        tireTypeDTO = tireTypeServices.getTireTypeById(Long.parseLong(ids));
    }

    public Resolution save() {
        tireTypeServices.updateTireType(tireTypeDTO);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        tireType = tireTypeServices.getAllTireTypes();
        return null;
    }
    
}

    
   
