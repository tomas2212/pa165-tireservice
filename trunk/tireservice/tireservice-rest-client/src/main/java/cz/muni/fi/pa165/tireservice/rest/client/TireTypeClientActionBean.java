package cz.muni.fi.pa165.tireservice.rest.client;


import cz.muni.fi.pa165.tireservice.dto.TireTypeDTO;
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
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Martin
 */
@UrlBinding("/tiretypes/{$event}/")
public class TireTypeClientActionBean implements ActionBean {

    private static final Logger logger = LoggerFactory.getLogger(TireTypeClientActionBean.class);

    private ActionBeanContext context;

    @SpringBean
    private RestTemplate rt;

    @SpringBean
    private PropertyHelper ph;

//    @ValidateNestedProperties(value = {
//        @Validate(on = {"create", "save"}, field = "name", required = true),
//        @Validate(on = {"create", "save"}, field = "price", required = true, minvalue = 1)
//    })
    private TireTypeDTO tireType;

    @DefaultHandler
    public Resolution list() {
        logger.info("listing");
        return new ForwardResolution("/tiretype/list.jsp");
    }

    public Resolution edit() {
        logger.debug("edit() {}", tireType);
        return new ForwardResolution("/tiretype/edit.jsp");
    }

    public Resolution save() {
        logger.debug("save() {}", tireType);
        rt.put(getURL() + "/{id}", tireType, tireType.getId());
        System.out.println(tireType.toString());
        return new RedirectResolution(this.getClass(), "list.jsp");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadESFromDatabase() {
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
        return new ForwardResolution("/tiretype/create.jsp");
    }

    public Resolution create() {
        logger.debug("create() {}", tireType);
        rt.postForObject(getURL() + "/", tireType, TireTypeDTO.class);
        return new RedirectResolution(this.getClass(), "list");
    }

    public TireTypeDTO[] getAllTyres() {
        logger.debug("getAllTyres()");
        TireTypeDTO[] ess = rt.getForObject(getURL() + "/", TireTypeDTO[].class);
        return ess;
    }

    public TireTypeDTO getTyre() {
        return tireType;
    }

    public void setTyre(TireTypeDTO tireType) {
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
