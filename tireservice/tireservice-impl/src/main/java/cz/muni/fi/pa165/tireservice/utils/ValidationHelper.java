package cz.muni.fi.pa165.tireservice.utils;

/**
 *
 * @author Ivan
 */
public class ValidationHelper {
    private ValidationHelper() {}
    
    /**
     * Throws exception when argument is null.
     *
     * @param obj parameter set into a method
     * @return false if argument is not null
     */
    public static boolean ArgumentNull(Object obj){
        if(obj == null){
            throw new  IllegalArgumentException("The parameter cannot be null");
        }
        
        return false;
    }
    
    /**
     * Throws exception when argument is not null.
     *
     * @param obj parameter set into a method
     * @return false if argument is not null
     */
    public static boolean ArgumentIsNull(Object obj){
        if(obj != null){
            throw new  IllegalArgumentException("This parameter be null " + obj.toString());
        }
        
        return true;
    }
    
    /**
     * Throws exception when id is zero.
     *
     * @param id is equal to zero
     * @return false if argument is not zero
     */
    public static boolean IdIsZero(Long id){
        if(id != null && id == 0){
            throw new  IllegalArgumentException("The id cannot be zero");
        }
        
        return false;
    }
    
}
