package cz.muni.fi.pa165.tireservice.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ivan Novák
 */
@Entity
@Table(name = "OrderTable")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Person person;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<Tire> tires;
    @ManyToMany
    private List<Service> services;
    @Temporal(TemporalType.DATE)
    @Column(name = "executionDate")
    private Date date;
    private boolean active;
    private String carType;

    public Order() {
    }

    public Order(Person person, List<Tire> tires, List<Service> services, Date date, boolean active) {
        this.person = person;
        this.tires = tires;
        this.services = services;
        this.date = date;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Tire> getTires() {
        return tires;
    }

    public void setTires(List<Tire> tires) {
        this.tires = tires;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
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

    public BigDecimal getOrderPrice() {
        BigDecimal price = BigDecimal.ZERO;

        if (services == null && tires == null) {
            return price;
        }
        if (tires != null) {
            for (Tire t : tires) {
                price = price.add(t.getTireType().getPrice());
            }
        }

        if (services != null) {
            for (Service s : services) {
                price = price.add(s.getPrice());
            }
        }

        return price;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 79 * hash + (this.person != null ? this.person.hashCode() : 0);
        hash = 79 * hash + (this.active ? 1 : 0);
        hash = 79 * hash + (this.carType != null ? this.carType.hashCode() : 0);
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
        final Order other = (Order) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.person != other.person && (this.person == null || !this.person.equals(other.person))) {
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

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", person=" + person + ", tires=" + tires + ", services=" + services + ", date=" + date + ", active=" + active + ", carType=" + carType + '}';
    }
}
