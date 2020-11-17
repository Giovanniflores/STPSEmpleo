/**
 * PortalEmpleoLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.gob.stps.portal.core.ws.quejas;

public class PortalEmpleoLocator extends org.apache.axis.client.Service implements mx.gob.stps.portal.core.ws.quejas.PortalEmpleo {

    public PortalEmpleoLocator() {
    }


    public PortalEmpleoLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PortalEmpleoLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PortalEmpleoSoap
    private java.lang.String PortalEmpleoSoap_address = "http://172.16.20.113:204/PortalEmpleo.asmx";

    public java.lang.String getPortalEmpleoSoapAddress() {
        return PortalEmpleoSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PortalEmpleoSoapWSDDServiceName = "PortalEmpleoSoap";

    public java.lang.String getPortalEmpleoSoapWSDDServiceName() {
        return PortalEmpleoSoapWSDDServiceName;
    }

    public void setPortalEmpleoSoapWSDDServiceName(java.lang.String name) {
        PortalEmpleoSoapWSDDServiceName = name;
    }

    public mx.gob.stps.portal.core.ws.quejas.PortalEmpleoSoap getPortalEmpleoSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PortalEmpleoSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPortalEmpleoSoap(endpoint);
    }

    public mx.gob.stps.portal.core.ws.quejas.PortalEmpleoSoap getPortalEmpleoSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mx.gob.stps.portal.core.ws.quejas.PortalEmpleoSoapStub _stub = new mx.gob.stps.portal.core.ws.quejas.PortalEmpleoSoapStub(portAddress, this);
            _stub.setPortName(getPortalEmpleoSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPortalEmpleoSoapEndpointAddress(java.lang.String address) {
        PortalEmpleoSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (mx.gob.stps.portal.core.ws.quejas.PortalEmpleoSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                mx.gob.stps.portal.core.ws.quejas.PortalEmpleoSoapStub _stub = new mx.gob.stps.portal.core.ws.quejas.PortalEmpleoSoapStub(new java.net.URL(PortalEmpleoSoap_address), this);
                _stub.setPortName(getPortalEmpleoSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PortalEmpleoSoap".equals(inputPortName)) {
            return getPortalEmpleoSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "PortalEmpleo");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "PortalEmpleoSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PortalEmpleoSoap".equals(portName)) {
            setPortalEmpleoSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
