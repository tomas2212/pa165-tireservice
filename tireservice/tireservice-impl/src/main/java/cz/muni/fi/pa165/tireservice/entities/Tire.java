package cz.muni.fi.pa165.tireservice.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ivan Novak
 */
@Entity
public class Tire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private TireType tireType;
    
//    @ManyToOne
//    private Order order;
    
    private int amountOnStore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public TireType getTireType() {
        return tireType;
    }

    public void setTireType(TireType tireType) {
        this.tireType = tireType;
    }

//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }

    public int getAmountOnStore() {
        return amountOnStore;
    }

    public void setAmountOnStore(int amountOnStore) {
        this.amountOnStore = amountOnStore;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.tireType != null ? this.tireType.hashCode() : 0);
//        hash = 37 * hash + (this.order != null ? this.order.hashCode() : 0);
        hash = 37 * hash + this.amountOnStore;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tire other = (Tire) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.tireType != other.tireType && (this.tireType == null || !this.tireType.equals(other.tireType))) {
            return false;
        }
//        if (this.order != other.order && (this.order == null || !this.order.equals(other.order))) {
//            return false;
//        }
        if (this.amountOnStore != other.amountOnStore) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "Tire{" + "id=" + id + ", tireType=" + tireType + ", order=" + order + ", amountOnStore=" + amountOnStore + '}';
//    }
}
