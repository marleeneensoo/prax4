
package OfferServiceClient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "OfferService", targetNamespace = "http://OfferService/", wsdlLocation = "http://localhost:8080/OfferService?wsdl")
public class OfferService
    extends Service
{

    private final static URL OFFERSERVICE_WSDL_LOCATION;
    private final static WebServiceException OFFERSERVICE_EXCEPTION;
    private final static QName OFFERSERVICE_QNAME = new QName("http://OfferService/", "OfferService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/OfferService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        OFFERSERVICE_WSDL_LOCATION = url;
        OFFERSERVICE_EXCEPTION = e;
    }

    public OfferService() {
        super(__getWsdlLocation(), OFFERSERVICE_QNAME);
    }

    public OfferService(WebServiceFeature... features) {
        super(__getWsdlLocation(), OFFERSERVICE_QNAME, features);
    }

    public OfferService(URL wsdlLocation) {
        super(wsdlLocation, OFFERSERVICE_QNAME);
    }

    public OfferService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, OFFERSERVICE_QNAME, features);
    }

    public OfferService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OfferService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns OfferServiceImpl
     */
    @WebEndpoint(name = "OfferServicePort")
    public OfferServiceImpl getOfferServicePort() {
        return super.getPort(new QName("http://OfferService/", "OfferServicePort"), OfferServiceImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OfferServiceImpl
     */
    @WebEndpoint(name = "OfferServicePort")
    public OfferServiceImpl getOfferServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://OfferService/", "OfferServicePort"), OfferServiceImpl.class, features);
    }

    private static URL __getWsdlLocation() {
        if (OFFERSERVICE_EXCEPTION!= null) {
            throw OFFERSERVICE_EXCEPTION;
        }
        return OFFERSERVICE_WSDL_LOCATION;
    }

}
