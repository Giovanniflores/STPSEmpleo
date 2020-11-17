<?xml version="1.0" encoding="ISO-8859-1"?>
<definitions xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/" xmlns:tns="http://127.0.0.1/queue/api/vacancies/wsdl/server.php" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns="http://schemas.xmlsoap.org/wsdl/" targetNamespace="http://127.0.0.1/queue/api/vacancies/wsdl/server.php">
<types>
<xsd:schema targetNamespace="http://127.0.0.1/queue/api/vacancies/wsdl/server.php"
>
 <xsd:import namespace="http://schemas.xmlsoap.org/soap/encoding/" />
 <xsd:import namespace="http://schemas.xmlsoap.org/wsdl/" />
</xsd:schema>
</types>
<message name="vacancies.findVacanciesRequest">
  <part name="keyWord" type="xsd:string" />
  <part name="federalEntityId" type="xsd:integer" />
  <part name="publishedId" type="xsd:integer" /></message>
<message name="vacancies.findVacanciesResponse">
  <part name="return" type="xsd:string" /></message>
<portType name="vacanciesServicePortType">
  <operation name="vacancies.findVacancies">
    <documentation>Get vacancies.</documentation>
    <input message="tns:vacancies.findVacanciesRequest"/>
    <output message="tns:vacancies.findVacanciesResponse"/>
  </operation>
</portType>
<binding name="vacanciesServiceBinding" type="tns:vacanciesServicePortType">
  <soap:binding style="rpc" transport="http://schemas.xmlsoap.org/soap/http"/>
  <operation name="vacancies.findVacancies">
    <soap:operation soapAction="http://127.0.0.1/queue/api/vacancies/wsdl/server.php#findVacancies" style="rpc"/>
    <input><soap:body use="encoded" namespace="http://127.0.0.1/queue/api/vacancies/wsdl/server.php" encodingStyle="http://schemas.xmlsoap.org/soap/encoding/"/></input>
    <output><soap:body use="encoded" namespace="http://127.0.0.1/queue/api/vacancies/wsdl/server.php" encodingStyle="http://schemas.xmlsoap.org/soap/encoding/"/></output>
  </operation>
</binding>
<service name="vacanciesService">
  <port name="vacanciesServicePort" binding="tns:vacanciesServiceBinding">
    <soap:address location="https://reports.superchamba.com:443/api/vacancies/wsdl/server.php"/>
  </port>
</service>
</definitions>