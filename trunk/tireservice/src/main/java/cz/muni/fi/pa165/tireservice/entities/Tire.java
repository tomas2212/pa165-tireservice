package cz.muni.fi.pa165.tireservice.entities;

import java.io.Serializable;
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
    private String brand;
    private String type;
    private int tireSize;
    private int price;
    private boolean changeTire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTireSize() {
        return tireSize;
    }

    public void setTireSize(int tireSize) {
        this.tireSize = tireSize;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isChangeTire() {
        return changeTire;
    }

    public void setChangeTire(boolean changeTire) {
        this.changeTire = changeTire;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 89 * hash + (this.brand != null ? this.brand.hashCode() : 0);
        hash = 89 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 89 * hash + this.tireSize;
        hash = 89 * hash + this.price;
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
        if ((this.brand == null) ? (other.brand != null) : !this.brand.equals(other.brand)) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if (this.tireSize != other.tireSize) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tire{" + "id=" + id + ", brand=" + brand + ", type=" + type + ", tireSize=" + tireSize + ", price=" + price + '}';
    }
    
}
