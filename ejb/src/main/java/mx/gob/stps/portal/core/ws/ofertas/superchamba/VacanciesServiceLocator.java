/**
 * VacanciesServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.gob.stps.portal.core.ws.ofertas.superchamba;

public class VacanciesServiceLocator extends org.apache.axis.client.Service implements mx.gob.stps.portal.core.ws.ofertas.superchamba.VacanciesService {

    public VacanciesServiceLocator() {
    }


    public VacanciesServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public VacanciesServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for vacanciesServicePort
    private String vacanciesServicePort_address = "https://reports.superchamba.com:443/api/vacancies/wsdl/server.php";

    public String getvacanciesServicePortAddress() {
        return vacanciesServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private String vacanciesServicePortWSDDServiceName = "vacanciesServicePort";

    public String getvacanciesServicePortWSDDServiceName() {
        return vacanciesServicePortWSDDServiceName;
    }

    public void setvacanciesServicePortWSDDServiceName(String name) {
        vacanciesServicePortWSDDServiceName = name;
    }

    public mx.gob.stps.portal.core.ws.ofertas.superchamba.VacanciesServicePortType getvacanciesServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(vacanciesServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getvacanciesServicePort(endpoint);
    }

    public mx.gob.stps.portal.core.ws.ofertas.superchamba.VacanciesServicePortType getvacanciesServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mx.gob.stps.portal.core.ws.ofertas.superchamba.VacanciesServiceBindingStub _stub = new mx.gob.stps.portal.core.ws.ofertas.superchamba.VacanciesServiceBindingStub(portAddress, this);
            _stub.setPortName(getvacanciesServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setvacanciesServicePortEndpointAddress(String address) {
        vacanciesServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (mx.gob.stps.portal.core.ws.ofertas.superchamba.VacanciesServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                mx.gob.stps.portal.core.ws.ofertas.superchamba.VacanciesServiceBindingStub _stub = new mx.gob.stps.portal.core.ws.ofertas.superchamba.VacanciesServiceBindingStub(new java.net.URL(vacanciesServicePort_address), this);
                _stub.setPortName(getvacanciesServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
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
        String inputPortName = portName.getLocalPart();
        if ("vacanciesServicePort".equals(inputPortName)) {
            return getvacanciesServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://127.0.0.1/queue/api/vacancies/wsdl/server.php", "vacanciesService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://127.0.0.1/queue/api/vacancies/wsdl/server.php", "vacanciesServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

        if ("vacanciesServicePort".equals(portName)) {
            setvacanciesServicePortEndpointAddress(address);
        }
        else
        { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
