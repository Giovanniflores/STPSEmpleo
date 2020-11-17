/**
 * VacanciesServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.gob.stps.portal.core.ws.ofertas.superchamba;

public interface VacanciesServicePortType extends java.rmi.Remote {

    /**
     * Get vacancies.
     */
    public String vacanciesFindVacancies(String keyWord, java.math.BigInteger federalEntityId, java.math.BigInteger publishedId) throws java.rmi.RemoteException;
}
