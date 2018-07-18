
package com.javainuse;

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
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SOABookEndpointService", targetNamespace = "http://javainuse.com", wsdlLocation = "http://localhost:8085/javainuse/ws/helloworld.wsdl")
public class SOABookEndpointService
    extends Service
{

    private final static URL SOABOOKENDPOINTSERVICE_WSDL_LOCATION;
    private final static WebServiceException SOABOOKENDPOINTSERVICE_EXCEPTION;
    private final static QName SOABOOKENDPOINTSERVICE_QNAME = new QName("http://javainuse.com", "SOABookEndpointService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8085/javainuse/ws/helloworld.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SOABOOKENDPOINTSERVICE_WSDL_LOCATION = url;
        SOABOOKENDPOINTSERVICE_EXCEPTION = e;
    }

    public SOABookEndpointService() {
        super(__getWsdlLocation(), SOABOOKENDPOINTSERVICE_QNAME);
    }

    public SOABookEndpointService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SOABOOKENDPOINTSERVICE_QNAME, features);
    }

    public SOABookEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SOABOOKENDPOINTSERVICE_QNAME);
    }

    public SOABookEndpointService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SOABOOKENDPOINTSERVICE_QNAME, features);
    }

    public SOABookEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SOABookEndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SOABookEndpoint
     */
    @WebEndpoint(name = "SOABookEndpoint")
    public SOABookEndpoint getSOABookEndpoint() {
        return super.getPort(new QName("http://javainuse.com", "SOABookEndpoint"), SOABookEndpoint.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SOABookEndpoint
     */
    @WebEndpoint(name = "SOABookEndpoint")
    public SOABookEndpoint getSOABookEndpoint(WebServiceFeature... features) {
        return super.getPort(new QName("http://javainuse.com", "SOABookEndpoint"), SOABookEndpoint.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SOABOOKENDPOINTSERVICE_EXCEPTION!= null) {
            throw SOABOOKENDPOINTSERVICE_EXCEPTION;
        }
        return SOABOOKENDPOINTSERVICE_WSDL_LOCATION;
    }

}
