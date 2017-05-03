package OrderService;

import OrderService.DTO.Address;
import OrderService.DTO.Order;
import OrderService.DTO.Seller;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "OrderService.OrderServiceImpl",
portName = "OrderServicePort",
serviceName = "OrderService")
public class OrderServiceImpl implements OrderService {

    public Order getOrderById(int id) {
        Address address = new Address();
        address.setAddressId(1);
        address.setCountry("Estonia");
        address.setCounty("Harjumaa");
        address.setStreet("Pärnu mnt");
        address.setHouseNumber("1");
        address.setTown("Tallinn");
        address.setDepartmentNumber("2");

        Address address2 = new Address();
        address2.setAddressId(2);
        address2.setCountry("Estonia");
        address2.setCounty("Harjumaa");
        address2.setStreet("Paldiski mnt");
        address2.setHouseNumber("221");
        address2.setTown("Tallinn");
        address2.setDepartmentNumber("2");

        List<Address> addresses = new ArrayList<Address>();
        addresses.add(address);
        addresses.add(address2);
        Seller seller = new Seller();
        seller.setAddresses(addresses);
        Order order = new Order();
        if(id == 1){
            order.setOrderId(id);
            order.setOrderCost(29.00);
            order.setDeliveryAddress(address);
            order.setSeller(seller);
            return order;
        }else if(id == 2){
            order.setOrderId(id);
            order.setOrderCost(577.00);
            order.setDeliveryAddress(address);
            order.setSeller(seller);
            return order;
        }
        order.setOrderId(id);
        order.setOrderCost(126.00);
        order.setDeliveryAddress(address);
        order.setSeller(seller);
        return order;
    }
}