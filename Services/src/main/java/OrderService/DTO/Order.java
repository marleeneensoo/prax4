package OrderService.DTO;

public class Order {

    private int OrderId;
    private Double OrderCost;
    private Address DeliveryAddress;
    private Seller Seller;

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public Double getOrderCost() {
        return OrderCost;
    }

    public void setOrderCost(Double orderCost) {
        OrderCost = orderCost;
    }

    public Address getDeliveryAddress() {
        return DeliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        DeliveryAddress = deliveryAddress;
    }

    public OrderService.DTO.Seller getSeller() {
        return Seller;
    }

    public void setSeller(OrderService.DTO.Seller seller) {
        Seller = seller;
    }
}
