package cz.muni.fi.pa165.tireservice.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Stefan Sakala
 */
    public class OrderDTO {
    
    private long id;
    private PersonDTO person;
    private List<TireDTO> tires;
    private List<ServiceDTO> services;
    private Date date;
    private boolean active;
    private String carType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public List<TireDTO> getTires() {
        return tires;
    }

    public void setTires(List<TireDTO> tires) {
        this.tires = tires;
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

    public void setServices(List<ServiceDTO> services) {
        this.services = services;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + (this.person != null ? this.person.hashCode() : 0);
        hash = 97 * hash + (this.tires != null ? this.tires.hashCode() : 0);
        hash = 97 * hash + (this.services != null ? this.services.hashCode() : 0);
        hash = 97 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 97 * hash + (this.active ? 1 : 0);
        hash = 97 * hash + (this.carType != null ? this.carType.hashCode() : 0);
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
        final OrderDTO other = (OrderDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.person != other.person && (this.person == null || !this.person.equals(other.person))) {
            return false;
        }
        if (this.tires != other.tires && (this.tires == null || !this.tires.equals(other.tires))) {
            return false;
        }
        if (this.services != other.services && (this.services == null || !this.services.equals(other.services))) {
            return false;
        }
        if (this.date != other.date && (this.date == null || !this.date.equals(other.date))) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if ((this.carType == null) ? (other.carType != null) : !this.carType.equals(other.carType)) {
            return false;
        }
        return true;
    }
    
    
    
}
