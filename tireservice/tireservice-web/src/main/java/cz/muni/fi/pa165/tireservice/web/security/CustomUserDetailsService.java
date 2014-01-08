package cz.muni.fi.pa165.tireservice.web.security;

import cz.muni.fi.pa165.tireservice.dto.PersonDTO;
import cz.muni.fi.pa165.tireservice.services.PersonServices;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Jakub Papcun (359 474)
 */
public class CustomUserDetailsService implements UserDetailsService {
    
//    @Value("${admin.username}")
//    private String adminUsername;
//    @Value("${admin.password}")
//    private String adminPassword;
//    @Value("${rest.username}")
//    private String restUsername;
//    @Value("${rest.password}")
//    private String restPassword;
    
    private String adminUsername = "admin";
    private String adminPassword = "a40546cc4fd6a12572828bb803380888ad1bfdab";
    private String restUsername = "rest";
    private String restPassword = "4411ff2bbcce78e1067742c302f82c46539e96df";
    
    @Autowired
    private PersonServices personServices;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {        
        CustomUserDetails d = new CustomUserDetails();
        if (string.equals(adminUsername)) {
            d.setIsAdmin(Boolean.TRUE);
            d.setAuthorities(Arrays.asList(createRole("ROLE_ADMIN")));
            d.setUsername(adminUsername);
            d.setPassword(adminPassword);
            
            return d;
        }
        if (string.equals(restUsername)) {
            d.setIsAdmin(Boolean.TRUE);
            d.setAuthorities(Arrays.asList(createRole("ROLE_ADMIN")));
            d.setUsername(restUsername);
            d.setPassword(restPassword);
            
            return d;
        }
//        CustomerUserTO cUser = cuFacade.getByUsername(string);
        PersonDTO person = null;
        for(PersonDTO p : personServices.getAllPersons()){
            if(p.getEmail().equals(string)){
                person = p;
                break;
            }
        }
        if (person == null) {
            throw new UsernameNotFoundException(string + " not found");
        }
        d.setIsAdmin(person.isIsServiceman());
        if (d.getIsAdmin()) {
            d.setAuthorities(Arrays.asList(createRole("ROLE_ADMIN")));
        } else {
            d.setAuthorities(Arrays.asList(createRole("ROLE_USER")));
        }
        d.setUsername(person.getEmail());
        d.setPassword(person.getPassword());
        d.setPerson(person);

        return d;
    }
    
    private CustomGrantedAuthority createRole(String role) {
        CustomGrantedAuthority cga = new CustomGrantedAuthority();
        cga.setAuthority(role);
        
        return cga;
    }
    
}