/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.web;

import cz.muni.fi.pa165.tireservice.dto.OrderDTO;
import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.dto.ServiceDTO;
import cz.muni.fi.pa165.tireservice.dto.TireDTO;
import cz.muni.fi.pa165.tireservice.dto.TireTypeDTO;
import cz.muni.fi.pa165.tireservice.services.OrderServices;
import cz.muni.fi.pa165.tireservice.services.PersonServices;
import cz.muni.fi.pa165.tireservice.services.ServiceServices;
import cz.muni.fi.pa165.tireservice.services.ServiceTireType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Handle;
import javax.interceptor.Interceptor;
import javax.mail.Session;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.After;
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
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Ivan
 */

@UrlBinding("/order/{$event}/")
public class OrderActionBean implements ActionBean, ValidationErrorHandler{
    private static final String LIST = "/order/list.jsp";
    private static final String EDIT = "/order/edit.jsp";
    private static final String REGISTER = "/order/register.jsp";
    
    private OrderDTO order  = new OrderDTO();
    private int personId;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    
    private SessionActionBeanContext actionBeanContext;
    private List<OrderDTO> orders;
    private List<PersonDTO> people;
    
    @SpringBean
    protected OrderServices orderServices;
    
    @SpringBean
    protected PersonServices personServices;
    
    @SpringBean
    protected ServiceTireType tireTypeServices;
    
    @SpringBean
    protected ServiceServices serviceServices;
    
  /*  @ValidateNestedProperties(value = {
        @Validate(on = {"delete"}, field = "id", required = true),
        @Validate(on = {"save", "add"}, field = "email", required = true),
        @Validate(on = {"save", "add"}, field = "firstName", required = true),
        @Validate(on = {"save", "add"}, field = "lastName", required = true),
        @Validate(on = {"save", "add"}, field = "address", required = true),
        @Validate(on = {"save", "add"}, field = "phoneNumber", required = true),
        
    })*/
    
    
    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }
    
    @DefaultHandler
    public Resolution list() {
        orders = getOrders();
        return new ForwardResolution(LIST);
    }
    
  
    public Resolution register() {
        getAllTireTypes();
        order = new OrderDTO();
        order.setTires(new ArrayList<TireDTO>());
        order.setServices(new ArrayList<ServiceDTO>());
        order.setActive(true);
        getContext().setOrder(order);
        
        return new ForwardResolution(REGISTER);
    }
    
    public Resolution edit(){
        return new ForwardResolution(EDIT);
    }
    
    private List<TireTypeDTO> allTireTypes = new ArrayList<TireTypeDTO>();

    public List<TireTypeDTO> getAllTireTypes() {
        allTireTypes = tireTypeServices.getAllTireTypes();
        return allTireTypes;
    }

    public void setAllTireTypes(List<TireTypeDTO> allTireTypes) {
        this.allTireTypes = allTireTypes;
    }
    
    private List<ServiceDTO> allServices = new ArrayList<ServiceDTO>();

    public List<ServiceDTO> getAllServices() {
        allServices= serviceServices.getAllEnabledServices();
        return allServices;
    }

    public void setAllServices(List<ServiceDTO> allServices) {
        this.allServices = allServices;
    }
    
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"addTire", "removeTire", "addService", "removeService", "add"})
    public void loadOrderFromSession() {
        OrderDTO submitedOrder = order;
        order = getContext().getOrder();
        order.setCarType(submitedOrder.getCarType());
        order.setDate(submitedOrder.getDate());
    }
    
    public Resolution addTire(){
        String ids = getContext().getRequest().getParameter("tireType.id");
        TireDTO tire = new TireDTO();
        tire.setTireType(tireTypeServices.getTireTypeById(Long.parseLong(ids)));
        
        order.getTires().add(tire);
        
        return new RedirectResolution(REGISTER);
    }
    
    
    public Resolution removeTire(){
        String ids = getContext().getRequest().getParameter("tireType.id");
        List<TireDTO> list = order.getTires();
        for(TireDTO t : list){
            if(t.getTireType().getId() == Long.parseLong(ids)){
                list.remove(t);
                break;
            }
        }
        order.setTires((list));
        
        return new RedirectResolution(REGISTER);
    }
    
    public Resolution addService(){
        String ids = getContext().getRequest().getParameter("service.id");
        
        order.getServices().add(serviceServices.getServiceById(Long.parseLong(ids)));
        
        return new RedirectResolution(REGISTER);
    }
    
    public Resolution removeService(){
        String ids = getContext().getRequest().getParameter("service.id");
        List<ServiceDTO> list = order.getServices();
        for(ServiceDTO s : list){
            if(s.getId() == Long.parseLong(ids)){
                list.remove(s);
                break;
            }
        }
        order.setServices((list));
        
        return new RedirectResolution(REGISTER);
    }
    
    @After(on = {"addTire", "removeTire", "addService", "removeService"})
    public void setOrderToSession() {
        getContext().setOrder(order);
    }
    
    
    public Resolution add(){
        try{
          orderServices.createOrder(order);        
          getContext().getMessages().add(new SimpleMessage("The order was inserted."));                                                                      
        }
        catch(Exception ex){
           getContext().getMessages().add(new SimpleMessage("error: "+ex.getLocalizedMessage()));                                                                      
        }            
        return new RedirectResolution((Class<? extends ActionBean>) this.getClass(), "register");
    }
    
    public Resolution delete(){
        try{
            orderServices.removeOrder(order);
            getContext().getMessages().add(new SimpleMessage("Order was shipped"));
        }catch(Exception ex){
            getContext().getMessages().add(new SimpleMessage("error: " + ex.getLocalizedMessage()));
        }
        return new RedirectResolution((Class<? extends ActionBean>) this.getClass(), "list");
    }
    
    public List<OrderDTO> getOrders(){
        return orderServices.getAllOrders();
    }
    
    public List<PersonDTO> getPeople(){
        people = personServices.getAllPersons();
        return people;
    }
    
    public void setPeople(List<PersonDTO> people){
        this.people = people;
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.actionBeanContext = (SessionActionBeanContext)abc;
    }

    @Override
    public SessionActionBeanContext getContext() {
        return this.actionBeanContext;
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "delete"})
    public void loadOrderFromDatabase() {
        String ids = getContext().getRequest().getParameter("order.id");
        if (ids == null) return;
        order = orderServices.getOrderById(Long.parseLong(ids));
    }

    public Resolution save() {
        orderServices.updateOrder(order);
        return new RedirectResolution((Class<? extends ActionBean>) this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        //people = personServices.getAllPersons();
        return null;
    }
}
