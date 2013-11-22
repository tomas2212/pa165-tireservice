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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Handle;
import javax.interceptor.Interceptor;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
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
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.springframework.http.HttpRequest;
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
    
    private OrderDTO order;
    
    @Validate(on = {"save", "add"}, required = true)
    private Long personId;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    
    @Validate(on = {"save", "add"}, required = true)
    private String carType;

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
    
    @Validate(on = {"save", "add"}, required = true)
    private Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
    
    private int tireAmount;

    public int getTireAmount() {
        return tireAmount;
    }

    public void setTireAmount(int tireAmount) {
        this.tireAmount = tireAmount;
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
        getAllServices();
        getPeople();
        
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
    
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"addTire", "removeTire", "addService", "removeService", "add", "save"})
    public void loadOrderFromSession() {
        order = getContext().getOrder();
    }
   
    private boolean enoughTiresOnStore(TireDTO tire){
        int numberOfOrderedTires = 0;
        for(TireDTO t :order.getTires()){
            if(t.getTireType() == tire.getTireType()){
                numberOfOrderedTires += t.getAmountOnStore();
            }   
        }
        
        if(tire.getAmountOnStore() + numberOfOrderedTires > tire.getTireType().getAmountOnStore()){
            return false;
        }
        
        return true;
    }
    
    public Resolution addTire(){
        String ids = getContext().getRequest().getParameter("tireType.id");
        TireDTO tire = new TireDTO();
        tire.setTireType(tireTypeServices.getTireTypeById(Long.parseLong(ids)));
        
        if(tireAmount == 0){
            getContext().getMessages().add(new SimpleError("You can not order zero tires.")); 
            return ReturnCorrectForm();
        }
        
        tire.setAmountOnStore(tireAmount);
        
        SetOrderParams();
        
        if(!enoughTiresOnStore(tire)){
            getContext().getMessages().add(new SimpleError("You can not order more of theese tires, because we do not have them on store.")); 
            return ReturnCorrectForm();
        }
        
        order.getTires().add(tire);
        
        return ReturnCorrectForm();
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
        SetOrderParams();
        return ReturnCorrectForm();
    }
    
    public Resolution addService(){
        String ids = getContext().getRequest().getParameter("service.id");
        
        order.getServices().add(serviceServices.getServiceById(Long.parseLong(ids)));
        SetOrderParams();
        return ReturnCorrectForm();
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
        SetOrderParams();
        return ReturnCorrectForm();
    }
    
    @After(on = {"addTire", "removeTire", "addService", "removeService"})
    public void setOrderToSession() {
        getContext().setOrder(order);
    }
    
    
    public Resolution add(){
        try{
          order.setCarType(carType);
          order.setDate(date);
          orderServices.createOrder(order);
          getContext().getRequest().getSession().removeAttribute("order");
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
            getContext().getRequest().removeAttribute("order");
            getContext().getMessages().add(new SimpleMessage("Order was shipped"));
        }catch(Exception ex){
            getContext().getMessages().add(new SimpleMessage("error: " + ex.getLocalizedMessage()));
        }
        return new RedirectResolution((Class<? extends ActionBean>) this.getClass(), "list");
    }
    
    public List<OrderDTO> getOrders(){
        return orderServices.getAllEnabledOrders();
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
        getContext().setOrder(order);
    }

    public Resolution save() {
        order.setCarType(carType);
        order.setDate(date);
        orderServices.updateOrder(order);
        
        getContext().getRequest().removeAttribute("order");
        return new RedirectResolution((Class<? extends ActionBean>) this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        //people = personServices.getAllPersons();
        return null;
    }

    private void SetOrderParams() {
        order.setCarType(carType);
        order.setDate(date);
        if(personId != null && personId > 0){
            order.setPerson(personServices.getPersonById(personId));
        }
    }

    private Resolution ReturnCorrectForm() {
        String formType = getContext().getRequest().getParameter("formType");
        if("edit".equals(formType))
        {
            return new RedirectResolution(EDIT);
        }
        return new RedirectResolution(REGISTER);
    }
    
    public String convertDateToString(){
        if(order.getDate() == null){
            return "";
        }else{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(order.getDate());
        }
    }
}
