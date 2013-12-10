package cz.muni.fi.pa165.tireservice.rest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 *
 * @author Martin
 */
@Component
public class PropertyHelper {
    
    @Value("${rest.tiretype}")
    private String tireType;
    
    @Value("${rest.service}")
    private String service;
    
    @Value("${rest.host}")
    private String host;
    
    @Value("${rest.port}")
    private String port;
    
    @Value("${rest.webapp}")
    private String webapp;
    
    public String getServiceURL() {
        return "http://" + host + ":" + port + "/" + webapp + "/" + service;
    }
    
    public String getTireTypeURL() {
        return "http://" + host + ":" + port + "/" + webapp + "/" + tireType;
    }
}
