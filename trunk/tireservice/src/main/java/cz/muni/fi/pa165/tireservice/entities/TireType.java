package cz.muni.fi.pa165.tireservice.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Jakub Papcun(359 474)
 */
@Entity
public class TireType implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String type;
    private String manufacturer;
    private String description;
    
    private int amountOnStore;
    
    private double tireRimSize;
    
    private BigDecimal price;
    
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountOnStore() {
        return amountOnStore;
    }

    public void setAmountOnStore(int amountOnStore) {
        this.amountOnStore = amountOnStore;
    }

    public double getTireRimSize() {
        return tireRimSize;
    }

    public void setTireRimSize(double tireRimSize) {
        this.tireRimSize = tireRimSize;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 67 * hash + (this.manufacturer != null ? this.manufacturer.hashCode() : 0);
        hash = 67 * hash + this.amountOnStore;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.tireRimSize) ^ (Double.doubleToLongBits(this.tireRimSize) >>> 32));
        hash = 67 * hash + (this.price != null ? this.price.hashCode() : 0);
        hash = 67 * hash + (this.active ? 1 : 0);
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
        final TireType other = (TireType) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if ((this.manufacturer == null) ? (other.manufacturer != null) : !this.manufacturer.equals(other.manufacturer)) {
            return false;
        }
        if (this.amountOnStore != other.amountOnStore) {
            return false;
        }
        if (Double.doubleToLongBits(this.tireRimSize) != Double.doubleToLongBits(other.tireRimSize)) {
            return false;
        }
        if (this.price != other.price && (this.price == null || !this.price.equals(other.price))) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tire{" + "id=" + id + ", type=" + type + ", manufacturer=" + manufacturer + ", description=" + description + ", amountOnStore=" + amountOnStore + ", tireRimSize=" + tireRimSize + ", price=" + price + ", active=" + active + '}';
    }
    
}
