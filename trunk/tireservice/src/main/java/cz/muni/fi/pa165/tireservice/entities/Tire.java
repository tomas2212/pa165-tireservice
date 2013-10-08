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
public class Tire implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String type;
    private String manufacturer;
    private String season;
    
    private int amount;
    
    private double tireWidth;
    private double tireAspect;
    private double tireRimSize;
    
    private BigDecimal priceForChange;
    
    private boolean changeTire;

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

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTireWidth() {
        return tireWidth;
    }

    public void setTireWidth(double tireWidth) {
        this.tireWidth = tireWidth;
    }

    public double getTireAspect() {
        return tireAspect;
    }

    public void setTireAspect(double tireAspect) {
        this.tireAspect = tireAspect;
    }

    public double getTireRimSize() {
        return tireRimSize;
    }

    public void setTireRimSize(double tireRimSize) {
        this.tireRimSize = tireRimSize;
    }

    public BigDecimal getPriceForChange() {
        return priceForChange;
    }

    public void setPriceForChange(BigDecimal priceForChange) {
        this.priceForChange = priceForChange;
    }

    public boolean isChangeTire() {
        return changeTire;
    }

    public void setChangeTire(boolean changeTire) {
        this.changeTire = changeTire;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 17 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 17 * hash + (this.manufacturer != null ? this.manufacturer.hashCode() : 0);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.tireWidth) ^ (Double.doubleToLongBits(this.tireWidth) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.tireAspect) ^ (Double.doubleToLongBits(this.tireAspect) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.tireRimSize) ^ (Double.doubleToLongBits(this.tireRimSize) >>> 32));
        hash = 17 * hash + (this.priceForChange != null ? this.priceForChange.hashCode() : 0);
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
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if ((this.manufacturer == null) ? (other.manufacturer != null) : !this.manufacturer.equals(other.manufacturer)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tireWidth) != Double.doubleToLongBits(other.tireWidth)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tireAspect) != Double.doubleToLongBits(other.tireAspect)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tireRimSize) != Double.doubleToLongBits(other.tireRimSize)) {
            return false;
        }
        if (this.priceForChange != other.priceForChange && (this.priceForChange == null || !this.priceForChange.equals(other.priceForChange))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tire{" + "id=" + id + ", type=" + type + ", manufacturer=" + manufacturer + ", usage=" + season + ", amount=" + amount + ", tireWidth=" + tireWidth + ", tireAspect=" + tireAspect + ", tireRimSize=" + tireRimSize + ", priceForChange=" + priceForChange + ", changeTire=" + changeTire + '}';
    }    
}
