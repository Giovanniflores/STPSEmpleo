package mx.gob.stps.portal.core.ws.quejas;

public class PortalEmpleoSoapProxy implements mx.gob.stps.portal.core.ws.quejas.PortalEmpleoSoap {
  private String _endpoint = null;
  private mx.gob.stps.portal.core.ws.quejas.PortalEmpleoSoap portalEmpleoSoap = null;
  
  public PortalEmpleoSoapProxy() {
    _initPortalEmpleoSoapProxy();
  }
  
  public PortalEmpleoSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initPortalEmpleoSoapProxy();
  }
  
  private void _initPortalEmpleoSoapProxy() {
    try {
      portalEmpleoSoap = (new mx.gob.stps.portal.core.ws.quejas.PortalEmpleoLocator()).getPortalEmpleoSoap();
      if (portalEmpleoSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)portalEmpleoSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)portalEmpleoSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (portalEmpleoSoap != null)
      ((javax.xml.rpc.Stub)portalEmpleoSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public mx.gob.stps.portal.core.ws.quejas.PortalEmpleoSoap getPortalEmpleoSoap() {
    if (portalEmpleoSoap == null)
      _initPortalEmpleoSoapProxy();
    return portalEmpleoSoap;
  }
  
  public java.lang.String enviodeQuejas(java.lang.String JSON) throws java.rmi.RemoteException{
    if (portalEmpleoSoap == null)
      _initPortalEmpleoSoapProxy();
    return portalEmpleoSoap.enviodeQuejas(JSON);
  }
  
  
}