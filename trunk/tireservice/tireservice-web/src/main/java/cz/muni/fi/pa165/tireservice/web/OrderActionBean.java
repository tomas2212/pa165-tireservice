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
import cz.muni.fi.pa165.tireservice.web.security.CustomUserDetails;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.After;
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
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

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
    
    @Validate(on = {"filter"}, required = true)
    private Long personFilter;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getPersonFilter() {
        return personFilter;
    }

    public void setPersonFilter(Long personFilter) {
        this.personFilter = personFilter;
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
        orders = getOrdersAccordingToAuthentication();
        return new ForwardResolution(LIST);
    }
    
    public Resolution filter() {
        if(getPersonFilter() != null){
            PersonDTO p = personServices.getPersonById(getPersonFilter());
            orders = orderServices.getAllUsersEnabledOrders(p.getEmail());
        }else{
            orders = orderServices.getAllEnabledOrders();
        }
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
        OrderDTO o = getOrder();
        if(o != null){
            List<TireDTO> tires = o.getTires();
            for(TireDTO t : tires){
                for(TireTypeDTO tt : allTireTypes){
                    if(tt.getId().equals(t.getTireType().getId())){
                        tt.setAmountOnStore(tt.getAmountOnStore()-t.getAmountOnStore());
                    }
                }
            }
        }
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
        if(order.getTires() != null){
            for(TireDTO t :order.getTires()){
                if(t.getTireType().equals(tire.getTireType())){
                    numberOfOrderedTires = numberOfOrderedTires + t.getAmountOnStore();
                }   
            }

            int orderedAndToBeOrderedTires = tire.getAmountOnStore() + numberOfOrderedTires;
            int tiresOnStore = tire.getTireType().getAmountOnStore();

            if(orderedAndToBeOrderedTires > tiresOnStore){
                return false;
            }
        }

        return true;
    }
    
    public Resolution addTire() throws ParseException{
        String ids = getContext().getRequest().getParameter("tireType.id");
        TireDTO tire = new TireDTO();
        TireTypeDTO tireType = tireTypeServices.getTireTypeById(Long.parseLong(ids));
        tire.setTireType(tireType);
        SetOrderParams();
        
        if(tireAmount == 0){
            addValidationError("tireAmount.errorMessage", null);
            return getContext().getSourcePageResolution();
        }
        
        tire.setAmountOnStore(tireAmount);
        
        if(!enoughTiresOnStore(tire)){
            addValidationError("order.noOtherTires", null);
            return getContext().getSourcePageResolution();
        }
        
        if(order.getTires() == null){
            order.setTires(new ArrayList<TireDTO>());
        }
        order.getTires().add(tire);
        
        return ReturnCorrectForm();
    }
    
    
    public Resolution removeTire() throws ParseException{
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
    
    public Resolution addService() throws ParseException{
        String ids = getContext().getRequest().getParameter("service.id");
        if(order.getServices() == null){
            order.setServices(new ArrayList<ServiceDTO>());
        }
        order.getServices().add(serviceServices.getServiceById(Long.parseLong(ids)));
        SetOrderParams();
        return ReturnCorrectForm();
    }
    
    public Resolution removeService() throws ParseException{
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
            if(personId != null && personId > 0){
              order.setPerson(personServices.getPersonById(personId));
            }
          
            orderServices.createOrder(order);
            for(TireDTO t : order.getTires()){
                TireTypeDTO tt = tireTypeServices.getTireTypeById(t.getTireType().getId());
                tt.setAmountOnStore(tt.getAmountOnStore()-t.getAmountOnStore());
                tireTypeServices.updateTireType(tt);
            }
            getContext().getRequest().getSession().removeAttribute("order");
            getContext().getMessages().add(new LocalizableMessage("order.inserted"));                                                                      
        } catch(AccessDeniedException ex){
            addValidationError("error.notallowed", null);
            return getContext().getSourcePageResolution();
        } catch(Exception ex){
            addValidationError("error", ex.getLocalizedMessage());
            return getContext().getSourcePageResolution();
        }             
        return new RedirectResolution((Class<? extends ActionBean>) this.getClass(), "register");
    }
    
    public Resolution delete(){
        try{
            Date orderDate = order.getDate();
            orderServices.removeOrder(order);
            if(orderDate.getTime() > System.currentTimeMillis()){
                for(TireDTO t : order.getTires()){
                    TireTypeDTO tt = tireTypeServices.getTireTypeById(t.getTireType().getId());
                    tt.setAmountOnStore(tt.getAmountOnStore()+t.getAmountOnStore());
                    tireTypeServices.updateTireType(tt);
                }
            }
            getContext().getRequest().removeAttribute("order");
            getContext().getMessages().add(new LocalizableMessage("order.removed"));
        } catch(AccessDeniedException ex){
            addValidationError("error.notallowed", null);
            return getContext().getSourcePageResolution();
        } catch(Exception ex){
            addValidationError("error", ex.getLocalizedMessage());
            return getContext().getSourcePageResolution();
        }   
        return new RedirectResolution((Class<? extends ActionBean>) this.getClass(), "list");
    }
    
    public List<OrderDTO> getOrders(){
        return orders;
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
        if(personId != null && personId > 0){
            order.setPerson(personServices.getPersonById(personId));
        }
        orderServices.updateOrder(order);
        
        getContext().getRequest().removeAttribute("order");
        return new RedirectResolution((Class<? extends ActionBean>) this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        people = personServices.getAllPersons();
        orders = getOrdersAccordingToAuthentication();
        return null;
    }

    @SuppressWarnings("empty-statement")
    private void SetOrderParams() throws ParseException {
        
        if(date == null){
            String dateTemp = null;
            try{
                dateTemp = getContext().getRequest().getParameter("editOrder.date");
                date = new SimpleDateFormat("DD.MM.YYYY", Locale.ENGLISH).parse(dateTemp);
            }
                catch(NullPointerException n)
            {
                date = null;
            }
                catch(ParseException pe)
                {
                    //if the value was passed by javascript
                    if(dateTemp != null && !"".equals(dateTemp)){
                        date = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH).parse(dateTemp);
                    }
                }
        }
        
        order.setDate(date);
        
        if(carType == null || carType.isEmpty()){
            carType = getContext().getRequest().getParameter("editOrder.carType");
        }
        order.setCarType(carType);
        
        if(personId == null || personId < 1){
            try{
                personId = Long.parseLong(getContext().getRequest().getParameter("editOrder.personId"));
            }
            catch(NumberFormatException nfe)
            {
                //do nothing, when no person passed as param or there is none to choose from, just do nothing
            }
        }
        
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
    
    private List<OrderDTO> getOrdersAccordingToAuthentication(){
        List<OrderDTO> localOrders;
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        for(GrantedAuthority ga : auth.getAuthorities()){
            if("ROLE_ADMIN".equals(ga.getAuthority())){
                isAdmin = true;
                break;
            }
        }
        
        if(!isAdmin){
            String username = ((CustomUserDetails) auth.getPrincipal()).getUsername();
            localOrders = orderServices.getAllUsersEnabledOrders(username);
        }else{
            localOrders = orderServices.getAllEnabledOrders();
        }
        return localOrders;
    }
    
     public PersonDTO getUserLoginCredetials()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PersonDTO person = ((CustomUserDetails) auth.getPrincipal()).getPerson();
        return person;
    }
    
    private PersonDTO loggedUser = null;

    public PersonDTO getLoggedUser() {
        if(loggedUser == null){
            setLoggedUser(getUserLoginCredetials());
        }
        
        return loggedUser;
    }

    public void setLoggedUser(PersonDTO loggedUser) {
        this.loggedUser = loggedUser;
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
