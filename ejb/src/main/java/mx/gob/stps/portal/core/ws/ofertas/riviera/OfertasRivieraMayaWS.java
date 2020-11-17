package mx.gob.stps.portal.core.ws.ofertas.riviera;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author cvvmac
 */
@WebService(name = "ws_sne_ofertasPortType",targetNamespace = "http://www.empleosenrivieramaya.com/WS/SNE/",portName = "ws_sne_ofertasPort")//(name="ws_sne_ofertasPort",targetNamespace = "http://www.empleosenrivieramaya.com/WS/SNE/",portName = "ws_sne_ofertasPort")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface OfertasRivieraMayaWS {
   
    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
   @WebMethod
   @WebResult(partName = "return")
    public String getOfertas(@WebParam(name = "fecha_publicacion", partName = "fecha_publicacion") String arg0);
    
}
