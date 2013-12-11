package cz.muni.fi.pa165.tireservice.dto;

/**
 *
 * @author Stefan Sakala (359772)
 */
public class TireDTO {

    private Long id;
    private TireTypeDTO tireType;
    private OrderDTO order;
    private int amountOnStore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TireTypeDTO getTireType() {
        return tireType;
    }

    public void setTireType(TireTypeDTO tireType) {
        this.tireType = tireType;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public int getAmountOnStore() {
        return amountOnStore;
    }

    public void setAmountOnStore(int amountOnStore) {
        this.amountOnStore = amountOnStore;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.tireType != null ? this.tireType.hashCode() : 0);
        hash = 29 * hash + (this.order != null ? this.order.hashCode() : 0);
        hash = 29 * hash + this.amountOnStore;
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
        final TireDTO other = (TireDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.tireType != other.tireType && (this.tireType == null || !this.tireType.equals(other.tireType))) {
            return false;
        }
        if (this.order != other.order && (this.order == null || !this.order.equals(other.order))) {
            return false;
        }
        if (this.amountOnStore != other.amountOnStore) {
            return false;
        }
        return true;
    }
}
