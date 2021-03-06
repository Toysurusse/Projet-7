
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
@WebServiceClient(name = "SOABookSearchEndpointService", targetNamespace = "http://javainuse.com", wsdlLocation = "http://localhost:8085/javainuse/ws/helloworld.wsdl")
public class SOABookSearchEndpointService
    extends Service
{

    private final static URL SOABOOKSEARCHENDPOINTSERVICE_WSDL_LOCATION;
    private final static WebServiceException SOABOOKSEARCHENDPOINTSERVICE_EXCEPTION;
    private final static QName SOABOOKSEARCHENDPOINTSERVICE_QNAME = new QName("http://javainuse.com", "SOABookSearchEndpointService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8085/javainuse/ws/helloworld.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SOABOOKSEARCHENDPOINTSERVICE_WSDL_LOCATION = url;
        SOABOOKSEARCHENDPOINTSERVICE_EXCEPTION = e;
    }

    public SOABookSearchEndpointService() {
        super(__getWsdlLocation(), SOABOOKSEARCHENDPOINTSERVICE_QNAME);
    }

    public SOABookSearchEndpointService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SOABOOKSEARCHENDPOINTSERVICE_QNAME, features);
    }

    public SOABookSearchEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SOABOOKSEARCHENDPOINTSERVICE_QNAME);
    }

    public SOABookSearchEndpointService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SOABOOKSEARCHENDPOINTSERVICE_QNAME, features);
    }

    public SOABookSearchEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SOABookSearchEndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SOABookSearchEndpoint
     */
    @WebEndpoint(name = "SOABookSearchEndpoint")
    public SOABookSearchEndpoint getSOABookSearchEndpoint() {
        return super.getPort(new QName("http://javainuse.com", "SOABookSearchEndpoint"), SOABookSearchEndpoint.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SOABookSearchEndpoint
     */
    @WebEndpoint(name = "SOABookSearchEndpoint")
    public SOABookSearchEndpoint getSOABookSearchEndpoint(WebServiceFeature... features) {
        return super.getPort(new QName("http://javainuse.com", "SOABookSearchEndpoint"), SOABookSearchEndpoint.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SOABOOKSEARCHENDPOINTSERVICE_EXCEPTION!= null) {
            throw SOABOOKSEARCHENDPOINTSERVICE_EXCEPTION;
        }
        return SOABOOKSEARCHENDPOINTSERVICE_WSDL_LOCATION;
    }

}
