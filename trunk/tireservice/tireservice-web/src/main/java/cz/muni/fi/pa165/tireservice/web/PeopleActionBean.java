package cz.muni.fi.pa165.tireservice.web;

import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.services.PersonServices;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.springframework.security.access.AccessDeniedException;

/**
 *
 * @author Jakub Papcun (359 474)
 */
@UrlBinding("/people/{$event}/")
public class PeopleActionBean implements ActionBean, ValidationErrorHandler{
    private static final String LIST = "/people/list.jsp";
    private static final String EDIT = "/people/edit.jsp";
    private static final String REGISTER = "/people/register.jsp";
    
    private ActionBeanContext actionBeanContext;
    private List<PersonDTO> people;
    
    @SpringBean
    protected PersonServices personServices;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"delete, edit"}, field = "id", required = true),
        @Validate(on = {"save", "add"}, field = "email", required = true, maxlength = 50, converter = EmailTypeConverter.class),
        @Validate(on = {"save", "add"}, field = "firstName", required = true, maxlength = 25),
        @Validate(on = {"save", "add"}, field = "lastName", required = true, maxlength = 25),
        @Validate(on = {"save", "add"}, field = "address", required = true, maxlength = 100),
        @Validate(on = {"save", "add"}, field = "phoneNumber", required = true, maxlength = 12),
        @Validate(on = {"save", "add"}, field = "password", required = true, minlength = 6)
    })
    private PersonDTO person;
    
    @Validate(on = {"add"}, required = true, expression = "confirmPassword eq person.password")
    private String confirmPassword;

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
    
    public Resolution register() {
        return new ForwardResolution(REGISTER);
    }
    
    public Resolution edit(){
        return new ForwardResolution(EDIT);
    }
    
    
    public Resolution add(){
        try{
          personServices.insertPerson(person);        
          getContext().getMessages().add(new LocalizableMessage("user.registered"));                                                                      
        } catch(AccessDeniedException ex){
            addValidationError("error.notallowed", null);
            return getContext().getSourcePageResolution();
        } catch(Exception ex){
            if(ex.getMessage().contains("ConstraintViolationException")){
                addValidationError("error.userexists", null);
            }else{
                addValidationError("error", ex.getLocalizedMessage());
            }
            return getContext().getSourcePageResolution();
        } 
        return new RedirectResolution(this.getClass(), "register");
    }
    
    public Resolution delete(){
        try{
            personServices.removePerson(person);
            getContext().getMessages().add(new LocalizableMessage("user.removed"));
        } catch(AccessDeniedException ex){
            addValidationError("error.notallowed", null);
            return getContext().getSourcePageResolution();
        } catch(Exception ex){
            addValidationError("error", ex.getLocalizedMessage());
            return getContext().getSourcePageResolution();
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "delete"})
    public void loadPersonFromDatabase() {
        String ids = getContext().getRequest().getParameter("person.id");
        if (ids == null) return;
        person = personServices.getPersonById(Long.parseLong(ids));
    }

    public Resolution save() {
        personServices.updatePerson(person);
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        people = personServices.getAllPersons();
        return null;
    }
    
    private void addValidationError(String key, String cause){
        ValidationErrors errors = new ValidationErrors();
        if(cause != null){
            errors.addGlobalError(new LocalizableError(key, cause) );
        }else{
            errors.addGlobalError(new LocalizableError(key) );
        }
        getContext().setValidationErrors(errors);
    }
    
}
