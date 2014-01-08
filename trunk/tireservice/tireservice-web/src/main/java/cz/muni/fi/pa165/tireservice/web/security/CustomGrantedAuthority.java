package cz.muni.fi.pa165.tireservice.web.security;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Jakub Papcun (359 474)
 */
public class CustomGrantedAuthority implements GrantedAuthority {
    
    private String authority;
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
    
}