package CourierService;

import CourierService.DTO.Address;
import CourierService.DTO.Courier;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebService(endpointInterface = "CourierService.CourierServiceImpl",
        portName = "CourierServicePort",
        serviceName = "CourierService")
public class CourierServiceImpl implements CourierService {
    public static Logger logger = Logger.getLogger("KullerTeenusLogger");
    private List<Courier> couriers;
    public CourierServiceImpl() {
        Address address = new Address();
        address.setAddressId(1);
        address.setCountry("Estonia");
        address.setCounty("Harjumaa");
        address.setStreet("Pärnu mnt");
        address.setHouseNumber("1");
        address.setTown("Tallinn");
        address.setDepartmentNumber("2");
        Address address2 = new Address();
        address2.setAddressId(1);
        address2.setCountry("Estonia");
        address2.setCounty("Jõgeva");
        address2.setStreet("Pärnu mnt");
        address2.setHouseNumber("1");
        address2.setTown("Jõgeva");
        address2.setDepartmentNumber("2");
        Address address3 = new Address();
        address3.setAddressId(1);
        address3.setCountry("Estonia");
        address3.setCounty("Võrumaa");
        address3.setStreet("Pärnu mnt");
        address3.setHouseNumber("1");
        address3.setTown("Võrumaa");
        address3.setDepartmentNumber("2");
        Address address4 = new Address();
        address4.setAddressId(1);
        address4.setCountry("Estonia");
        address4.setCounty("Läänemaa");
        address4.setStreet("Pärnu mnt");
        address4.setHouseNumber("1");
        address4.setTown("Haapsalu");
        address4.setDepartmentNumber("2");
        List<Address> addresses1 = new ArrayList<Address>();
        addresses1.add(address);
        addresses1.add(address3);
        List<Address> addresses2 = new ArrayList<Address>();
        addresses2.add(address2);
        addresses2.add(address4);
        List<Address> addresses3 = new ArrayList<Address>();
        addresses3.add(address4);
        List<Address> addresses4 = new ArrayList<Address>();
        addresses4.add(address);
        addresses4.add(address2);
        couriers = new ArrayList<Courier>();
        Courier courier1 = new Courier();
        courier1.setId(1);
        courier1.setName("Kuller 1");
        courier1.setPercentFromOrder(10);
        courier1.setAddresses(addresses1);
        couriers.add(courier1);
        Courier courier2 = new Courier();
        courier2.setId(2);
        courier2.setName("Kuller 2");
        courier2.setPercentFromOrder(23);
        courier2.setAddresses(addresses2);
        couriers.add(courier2);
        Courier courier3 = new Courier();
        courier3.setId(3);
        courier3.setName("Kuller 3");
        courier3.setAddresses(addresses3);
        courier3.setPercentFromOrder(5);
        couriers.add(courier3);
        Courier courier4 = new Courier();
        courier4.setId(4);
        courier4.setAddresses(addresses4);
        courier4.setPercentFromOrder(13);
        courier4.setName("Kuller 4");
        couriers.add(courier4);
    }

    public Courier getById(int id) {
        logger.info("Kullerteenus.Leia kuller id jargi id: " +id);
        for(Courier courier : this.couriers){
            if(courier.getId() == id){
                return courier;
            }
        }
        return null;
    }

    public List<Courier> getAllCouriers() {
        logger.info("KullerTeenus. Leia kõik kullerid");
        return this.couriers;
    }
}
