package cz.muni.fi.pa165.tireservice.rest.client;


import cz.muni.fi.pa165.tireservice.dto.TireTypeDTO;
import cz.muni.fi.pa165.tireservice.rest.util.PropertyHelper;
import java.util.List;
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
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Martin
 */
@UrlBinding("/tiretypes/{$event}/")
public class TireTypeClientActionBean implements ActionBean {

    public TireTypeClientActionBean(){ }
    
    private static final Logger logger = LoggerFactory.getLogger(TireTypeClientActionBean.class);

    private ActionBeanContext context;

    private List<TireTypeDTO> allTyreTypes;

    public List<TireTypeDTO> getAllTyreTypes() {
        return allTyreTypes;
    }

    public void setAllTyreTypes(List<TireTypeDTO> allTyreTypes) {
        this.allTyreTypes = allTyreTypes;
    }
    
    @SpringBean
    public RestTemplate rt;

    public RestTemplate getRt() {
        return rt;
    }

    public void setRt(RestTemplate rt) {
        this.rt = rt;
    }

    @SpringBean
    public PropertyHelper ph;

    public PropertyHelper getPh() {
        return ph;
    }

    public void setPh(PropertyHelper ph) {
        this.ph = ph;
    }

//    @ValidateNestedProperties(value = {
//        @Validate(on = {"create", "save"}, field = "name", required = true),
//        @Validate(on = {"create", "save"}, field = "price", required = true, minvalue = 1)
//    })
    private TireTypeDTO tireType;

    @DefaultHandler
    public Resolution list() {
        logger.info("listing");
        return new ForwardResolution("/tiretypes/list.jsp");
    }

    public Resolution edit() {
        logger.debug("edit() {}", tireType);
        return new ForwardResolution("/tiretypes/edit.jsp");
    }

    public Resolution save() {
        logger.debug("save() {}", tireType);
        rt.put(getURL() + "/{id}", tireType, tireType.getId());
        System.out.println(tireType.toString());
        return new RedirectResolution(this.getClass(), "list.jsp");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadServiceFromDatabase() {
        String id = context.getRequest().getParameter("tireType.id");
        if (id != null) {
            tireType = rt.getForObject(getURL() + "/{id}", TireTypeDTO.class, id);
        }
    }

    public Resolution delete() {
        logger.debug("delete({})", tireType);
        rt.delete(getURL() + "/{id}", tireType.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution newTireType() {
        logger.info("newTireType()");
        return new ForwardResolution("/tiretypes/create.jsp");
    }

    public Resolution create() {
        logger.debug("create() {}", tireType);
        rt.postForObject(getURL() + "/", tireType, TireTypeDTO.class);
        return new RedirectResolution(this.getClass(), "list");
    }

    public TireTypeDTO[] getAllTipes() {
        logger.debug("getAllTireTypes()");
        TireTypeDTO[] ess = rt.getForObject(getURL() + "/", TireTypeDTO[].class);
        
        for(TireTypeDTO t : ess){
            allTyreTypes.add(t);
        }
        
        return ess;
    }

    public TireTypeDTO getTire() {
        return tireType;
    }

    public void setTire(TireTypeDTO tireType) {
        this.tireType = tireType;
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
        return ph.getTireTypeURL();
    }
}
