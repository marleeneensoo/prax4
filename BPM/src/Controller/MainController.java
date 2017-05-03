package Controller;

import DTO.FullOffer;
import MyCourierServiceClient.Courier;
import MyCourierServiceClient.MalformedURLException_Exception;
import MyCourierServiceClient.MyCourierService;
import OfferServiceClient.OfferService;
import OfferServiceClient.Seller;
import OrderServiceClient.Address;
import OrderServiceClient.Order;
import OrderServiceClient.OrderService;
import OrderShipmentServiceClient.OrderShipment;
import OrderShipmentServiceClient.OrderShipmentService;
import TransportOrderServiceClient.TransportOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainController {

    public static Logger logger = Logger.getLogger("MainControllerLogger");
    public static void main(String[] args) throws MalformedURLException_Exception {
        int orderId = 2;
        logger.info("-----------Protsessi algus ----------");
        Order order = getOrder(orderId);
        logger.info("tellimus laetud id: "+order.getOrderId());
        printOrder(order);
        List<Courier> couriers = getAllCouriers();
        logger.info("Kulleri laetud");
        printCouriers(couriers);
        List<FullOffer> offers = getAllOffers(order,couriers);
        logger.info("Pakkumised laetud ning analüüs lõpetetud");
        FullOffer bestOffer = getBestFullOffer(offers);
        logger.info("Parim pakkumine leitud  pakkumiseId: " + bestOffer.getOffer().getOfferId());
        bestOffer = orderTransport(bestOffer);
        logger.info("Transport tellitud");
        checkOutOrder(bestOffer,orderId);
        logger.info("Tellimus salvestatud");
        logger.info("-----------Protsessi lõpp ----------");

    }

    private static List<Courier> getAllCouriers() throws MalformedURLException_Exception {
        MyCourierService service = new MyCourierService();
        return service.getMyCourierServicePort().getCouriers();
    }

    private static Order getOrder(int orderId){
        OrderService orderService = new OrderService();
        return orderService.getOrderServicePort().getOrderById(orderId);
    }
    private static void checkOutOrder(FullOffer offer,int orderId){
        OrderShipmentService service = new OrderShipmentService();
        OrderShipment shipment = new OrderShipment();
        shipment.setRyhmaName("IDU0080");
        shipment.setApproximateDeliveryTime(offer.getOffer().getApproximateDeliveryTime());
        shipment.setCourierName(offer.getCourier().getName());
        shipment.setOrderId(orderId);
        shipment.setTrackingNumber(offer.getTrackingCode());
        shipment.setTransportPrice(offer.getOffer().getPrice());
        service.getOrderShipmentServicePort().insertOrderShipment(shipment);

        System.out.println("\nTellitud : "
                +"\n Rühma nimi: " + shipment.getRyhmaName()
                +"\n Oletatavd saabumis päevade arv: "+shipment.getApproximateDeliveryTime()
                +"\n Kulleri nimi: " + shipment.getCourierName()
                +"\n Tellimuse number: " + shipment.getOrderId()
                +"\n Tracking number: " + shipment.getTrackingNumber()
                +"\n Transpordi hind: " + shipment.getTransportPrice()
        );
    }

    private static List<FullOffer> getAllOffers(Order order,List<Courier> couriers){
        List<FullOffer> fullOffers = new ArrayList<FullOffer>();
        System.out.println("\n\n\nPakkumised : ");
        OfferService offerService = new OfferService();
        for(Courier courier : couriers){
            OfferServiceClient.TransportServiceOffer offer = offerService.getOfferServicePort().getOffer(courier.getId(),mapOrderToOfferOrder(order));
            double rate = evaluateOffer(offer.getPrice(),offer.getApproximateDeliveryTime());
            System.out.println(courier.getName()+"\n pakkumise id: "+offer.getOfferId()+ " hind: "+offer.getPrice()+ " oodatav aeg: "+offer.getApproximateDeliveryTime()+"\n Headus: "+rate);
            FullOffer fullOffer = new FullOffer();
            fullOffer.setCourier(courier);
            fullOffer.setOffer(offer);
            fullOffer.setRate(rate);
            fullOffers.add(fullOffer);
        }
        return fullOffers;
    }

    private static FullOffer orderTransport(FullOffer bestOffer){
        TransportOrderService transportService = new TransportOrderService();
        String trackingCode = transportService.getTransportOrderServicePort().orderTransport(bestOffer.getOffer().getOfferId());
        System.out.println("Tracking number: "+ trackingCode);
        bestOffer.setTrackingCode(trackingCode);
        return bestOffer;
    }
    private static double evaluateOffer(double shippingPrice, int dayAmmount) {
        return shippingPrice * 0.01 * dayAmmount;
    }

    private static FullOffer getBestFullOffer(List<FullOffer> offers){
        FullOffer bestOffer = null;
        for(FullOffer offer : offers){
            if(bestOffer == null){
                bestOffer = offer;
            }
            if(bestOffer.getRate() >= offer.getRate()){
                bestOffer = offer;
            }
        }
        if(bestOffer.getOffer() != null){
            System.out.println("\n\n parim pakkumine : "+ bestOffer.getOffer().getOfferId());
        }
        return bestOffer;
    }

    private static OfferServiceClient.Order mapOrderToOfferOrder(Order order){
        OfferServiceClient.Order offerOrder = new OfferServiceClient.Order();
        offerOrder.setOrderId(order.getOrderId());

        OfferServiceClient.Address address = new OfferServiceClient.Address();
        address.setAddressId(order.getDeliveryAddress().getAddressId());
        address.setCountry(order.getDeliveryAddress().getCountry());
        address.setCounty(order.getDeliveryAddress().getCounty());
        address.setDepartmentNumber(order.getDeliveryAddress().getDepartmentNumber());
        address.setHouseNumber(order.getDeliveryAddress().getHouseNumber());
        address.setStreet(order.getDeliveryAddress().getStreet());
        offerOrder.setDeliveryAddress(address);

        offerOrder.setOrderCost(order.getOrderCost());


        Seller seller  = new Seller();
        List<OfferServiceClient.Address> selAddresses = new ArrayList<OfferServiceClient.Address>();
        for(Address selAddress : order.getSeller().getAddresses()){
            OfferServiceClient.Address ad = new OfferServiceClient.Address();
            ad.setAddressId(selAddress.getAddressId());
            ad.setCountry(selAddress.getCountry());
            ad.setCounty(selAddress.getCounty());
            ad.setDepartmentNumber(selAddress.getDepartmentNumber());
            ad.setHouseNumber(selAddress.getHouseNumber());
            ad.setStreet(selAddress.getStreet());
            selAddresses.add(ad);
        }
        seller.getAddresses().addAll(selAddresses);
        offerOrder.setSeller(seller);
        return offerOrder;
    }

    private static void printOrder(Order order){
        Address deAddress = order.getDeliveryAddress();
        System.out.println("Tellimus :  "+ order.getOrderId());
        System.out.println("Kohale toimetamise aadress - " + deAddress.getCounty()+" "+deAddress.getCountry()
                +" "+deAddress.getTown()+" "+ deAddress.getStreet()+" "+deAddress.getHouseNumber()+" "+deAddress.getDepartmentNumber() );
        for(Address address : order.getSeller().getAddresses()){
            System.out.println("Müüja aadressid - "+ address.getCounty()+" "+address.getCountry()
                    +" "+address.getTown()+" "+ address.getStreet()+" "+address.getHouseNumber()+" "+address.getDepartmentNumber() );
        }
        System.out.println("Tellimuse maksumus -  " + order.getOrderCost());
        System.out.println("\n\n\n");
    }
    private static void printCouriers(List<Courier> couriers){
        System.out.println("Kõik kullerid: ");
        for(Courier courier : couriers){
            System.out.println(courier.getId()+ " " + courier.getName());
        }
    }
}
