package mx.gob.stps.portal.core.ws.ofertas.riviera;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 *
 * @author cvvmac
 */
@WebServiceClient(name = "OfertasRivieraMayaWSImpl",
        targetNamespace = "http://www.empleosenrivieramaya.com/WS/SNE/",
        wsdlLocation = "http://empleosenrivieramaya.com/WS/SNE/ws-ofertas.php?wsdl")
public class OfertasRivieraMayaWSImpl extends Service {
    private final static URL RIVIERAMAYASERVICE_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("http://empleosenrivieramaya.com/WS/SNE/ws-ofertas.php?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        RIVIERAMAYASERVICE_WSDL_LOCATION = url;
    }

    public OfertasRivieraMayaWSImpl(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OfertasRivieraMayaWSImpl() {
        super(RIVIERAMAYASERVICE_WSDL_LOCATION,
                new QName("http://www.empleosenrivieramaya.com/WS/SNE/", "ws_sne_ofertas"));
    }

    /**
     *
     * @return returns String
     */
    @WebEndpoint(name = "ws_sne_ofertasPort")
    public OfertasRivieraMayaWS getOfertasRivieraMayaWSImplPort() {
        return (OfertasRivieraMayaWS) super.getPort(
                new QName("http://www.empleosenrivieramaya.com/WS/SNE/", "ws_sne_ofertasPort"),
                OfertasRivieraMayaWS.class);
    }

    /**
     *
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to
     * configure on the proxy. Supported features not in
     * the <code>features</code> parameter will have their default values.
     * @return returns String
     */
    @WebEndpoint(name = "ws_sne_ofertasPort")
    public OfertasRivieraMayaWS getOfertasRivieraMayaWSImplPort(WebServiceFeature... features) {
        return (OfertasRivieraMayaWS) super.getPort(
                new QName("http://www.empleosenrivieramaya.com/WS/SNE/", "ws_sne_ofertasPort"),
                OfertasRivieraMayaWS.class,
                features);
    }
}
