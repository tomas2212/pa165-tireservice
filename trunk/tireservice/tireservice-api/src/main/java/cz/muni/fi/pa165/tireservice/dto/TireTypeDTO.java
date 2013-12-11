package cz.muni.fi.pa165.tireservice.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Ivan
 */
public class TireTypeDTO 
{
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
        if(price != null){
            this.price = price.setScale(2, RoundingMode.CEILING);
        }
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
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 53 * hash + this.amountOnStore;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.tireRimSize) ^ (Double.doubleToLongBits(this.tireRimSize) >>> 32));
        hash = 53 * hash + (this.price != null ? this.price.hashCode() : 0);
        hash = 53 * hash + (this.active ? 1 : 0);
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
        final TireTypeDTO other = (TireTypeDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if ((this.manufacturer == null) ? (other.manufacturer != null) : !this.manufacturer.equals(other.manufacturer)) {
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
}
