/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.web;

import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.services.PersonServices;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 *
 * @author yrael
 */
@UrlBinding("/p/{$event}/{person.id}")
public class PersonActionBean implements ActionBean{
    private static final String LIST = "/p/list.jsp";
    private static final String EDIT = "/p/edit.jsp";
    
    private ActionBeanContext actionBeanContext;
    private List<PersonDTO> people;
    
    
    @SpringBean
    protected PersonServices personServices;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"save", "add"}, field = "name", required = true),
        @Validate(on = {"save", "add"}, field = "password", required = true),
        @Validate(on = {"save", "add"}, field = "admin", required = true)
    })
    private PersonDTO person;

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }
    
    @DefaultHandler
    public Resolution list() {
        people = getPeople();
        return new ForwardResolution(LIST);
    }
    
    public Resolution edit(){
        return new ForwardResolution(EDIT);
    }
    
    public Resolution add(){
        try{
          personServices.insertPerson(person);        
          getContext().getMessages().add(new SimpleMessage("User added"));                                                                      
        }
        catch(Exception ex){
           getContext().getMessages().add(new SimpleMessage("error: "+ex.getLocalizedMessage()));                                                                      
        }            
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete(){
        try{
            personServices.removePerson(person);
            getContext().getMessages().add(new SimpleMessage("User Removed"));
        }catch(Exception ex){
            getContext().getMessages().add(new SimpleMessage("error: " + ex.getLocalizedMessage()));
        }
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public List<PersonDTO> getPeople(){
        return personServices.getAllPersons();
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.actionBeanContext = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return this.actionBeanContext;
    }
    
}
