/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.tireservice.rest.client;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin
 */
@UrlBinding("/${event}")
public class ErrorHandlerActionBean implements ActionBean{
    
    private String exception;
    private ActionBeanContext context;
    private String rootCause;
    private final Logger log = LoggerFactory.getLogger(ErrorHandlerActionBean.class);

    @DefaultHandler
    public Resolution error() {
        log.debug("error(){}" + exception + rootCause);
        return new ForwardResolution("/error.jsp");
    }

    public String getException() {
        return this.exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getRootCause() {
        return rootCause;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
}
