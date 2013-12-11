package cz.muni.fi.pa165.tireservice.web;

import cz.muni.fi.pa165.tireservice.dto.OrderDTO;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 *
 * @author Ivan
 */
public class SessionActionBeanContext extends ActionBeanContext {
    public void setOrder(OrderDTO order) {
        getRequest().getSession().setAttribute("order", order);
    }

    public OrderDTO getOrder() {
        return (OrderDTO) getRequest().getSession().getAttribute("order");
    }
}
