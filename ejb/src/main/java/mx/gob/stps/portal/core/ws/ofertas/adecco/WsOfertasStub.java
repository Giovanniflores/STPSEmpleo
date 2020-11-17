
/**
 * WsOfertasStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */
        package mx.gob.stps.portal.core.ws.ofertas.adecco;

        

        /*
        *  WsOfertasStub java implementation
        */

        
        public class WsOfertasStub extends org.apache.axis2.client.Stub
        {
        protected org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();

        private static int counter = 0;

        private static synchronized java.lang.String getUniqueSuffix(){
            // reset the counter if it is greater than 99999
            if (counter > 99999){
                counter = 0;
            }
            counter = counter + 1; 
            return java.lang.Long.toString(java.lang.System.currentTimeMillis()) + "_" + counter;
        }

    
    private void populateAxisService() throws org.apache.axis2.AxisFault {

     //creating the Service with a unique name
     _service = new org.apache.axis2.description.AxisService("WsOfertas" + getUniqueSuffix());
     addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[1];
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://200.57.181.35/", "obtenVacantes"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[0]=__operation;
            
        
        }

    //populates the faults
    private void populateFaults(){
         


    }

    /**
      *Constructor that takes in a configContext
      */

    public WsOfertasStub(org.apache.axis2.context.ConfigurationContext configurationContext,
       java.lang.String targetEndpoint)
       throws org.apache.axis2.AxisFault {
         this(configurationContext,targetEndpoint,false);
   }


   /**
     * Constructor that takes in a configContext  and useseperate listner
     */
   public WsOfertasStub(org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
         //To populate AxisService
         populateAxisService();
         populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);
        
	
        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
        
            //Set the soap version
            _serviceClient.getOptions().setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
        
    
    }

    /**
     * Default Constructor
     */
    public WsOfertasStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        
                    this(configurationContext,"http://back.keyworks.com.mx:7000/wsOfertas.asmx" );
                
    }

    /**
     * Default Constructor
     */
    public WsOfertasStub() throws org.apache.axis2.AxisFault {
        
//                    this("http://back.keyworks.com.mx:7000/wsOfertas.asmx" );
                    this("http://vacantes.keyworks.com.mx/wsofertas.asmx" );

    }

    /**
     * Constructor taking the target endpoint
     */
    public WsOfertasStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }



        
                    /**
                     * Auto generated method signature
                     * 
                     * @see mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertas#obtenVacantes
                     * @param obtenVacantes
                    
                     */

                    

                            public  mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantesResponse obtenVacantes(

                            mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantes obtenVacantes)
                        

                    throws java.rmi.RemoteException
                    
                    {
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
              _operationClient.getOptions().setAction("http://200.57.181.35/ObtenVacantes");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    obtenVacantes,
                                                    optimizeContent(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                    "obtenVacantes")));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantesResponse.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantesResponse)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex=
                                (java.lang.Exception) exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
            


       /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
       private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
       return returnMap;
    }

    
    
    private javax.xml.namespace.QName[] opNameArray = null;
    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        

        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;   
            }
        }
        return false;
    }
     //http://back.keyworks.com.mx:7000/wsOfertas.asmx
        public static class ObtenVacantes
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://200.57.181.35/",
                "ObtenVacantes",
                "ns1");

            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://200.57.181.35/")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for OEntrada
                        */

                        
                                    protected ParametroEntrada localOEntrada ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOEntradaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return ParametroEntrada
                           */
                           public  ParametroEntrada getOEntrada(){
                               return localOEntrada;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OEntrada
                               */
                               public void setOEntrada(ParametroEntrada param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localOEntradaTracker = true;
                                       } else {
                                          localOEntradaTracker = false;
                                              
                                       }
                                   
                                            this.localOEntrada=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
                org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       ObtenVacantes.this.serialize(MY_QNAME,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               MY_QNAME,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://200.57.181.35/");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":ObtenVacantes",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "ObtenVacantes",
                           xmlWriter);
                   }

               
                   }
                if (localOEntradaTracker){
                                            if (localOEntrada==null){
                                                 throw new org.apache.axis2.databinding.ADBException("oEntrada cannot be null!!");
                                            }
                                           localOEntrada.serialize(new javax.xml.namespace.QName("http://200.57.181.35/","oEntrada"),
                                               factory,xmlWriter);
                                        }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localOEntradaTracker){
                            elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "oEntrada"));
                            
                            
                                    if (localOEntrada==null){
                                         throw new org.apache.axis2.databinding.ADBException("oEntrada cannot be null!!");
                                    }
                                    elementList.add(localOEntrada);
                                }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static ObtenVacantes parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ObtenVacantes object =
                new ObtenVacantes();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"ObtenVacantes".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ObtenVacantes)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","oEntrada").equals(reader.getName())){
                                
                                                object.setOEntrada(ParametroEntrada.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          
        public static class Vacante
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Vacante
                Namespace URI = http://200.57.181.35/
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://200.57.181.35/")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for VacanteId
                        */

                        
                                    protected java.lang.String localVacanteId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localVacanteIdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getVacanteId(){
                               return localVacanteId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VacanteId
                               */
                               public void setVacanteId(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localVacanteIdTracker = true;
                                       } else {
                                          localVacanteIdTracker = false;
                                              
                                       }
                                   
                                            this.localVacanteId=param;
                                    

                               }
                            

                        /**
                        * field for PuestoOfrecido
                        */

                        
                                    protected java.lang.String localPuestoOfrecido ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPuestoOfrecidoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPuestoOfrecido(){
                               return localPuestoOfrecido;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PuestoOfrecido
                               */
                               public void setPuestoOfrecido(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPuestoOfrecidoTracker = true;
                                       } else {
                                          localPuestoOfrecidoTracker = false;
                                              
                                       }
                                   
                                            this.localPuestoOfrecido=param;
                                    

                               }
                            

                        /**
                        * field for PuestoCliente
                        */

                        
                                    protected java.lang.String localPuestoCliente ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPuestoClienteTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPuestoCliente(){
                               return localPuestoCliente;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PuestoCliente
                               */
                               public void setPuestoCliente(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPuestoClienteTracker = true;
                                       } else {
                                          localPuestoClienteTracker = false;
                                              
                                       }
                                   
                                            this.localPuestoCliente=param;
                                    

                               }
                            

                        /**
                        * field for Area
                        */

                        
                                    protected java.lang.String localArea ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAreaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getArea(){
                               return localArea;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Area
                               */
                               public void setArea(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localAreaTracker = true;
                                       } else {
                                          localAreaTracker = false;
                                              
                                       }
                                   
                                            this.localArea=param;
                                    

                               }
                            

                        /**
                        * field for Escolaridad
                        */

                        
                                    protected java.lang.String localEscolaridad ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEscolaridadTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEscolaridad(){
                               return localEscolaridad;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Escolaridad
                               */
                               public void setEscolaridad(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localEscolaridadTracker = true;
                                       } else {
                                          localEscolaridadTracker = false;
                                              
                                       }
                                   
                                            this.localEscolaridad=param;
                                    

                               }
                            

                        /**
                        * field for Licenciatura
                        */

                        
                                    protected java.lang.String localLicenciatura ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLicenciaturaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLicenciatura(){
                               return localLicenciatura;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Licenciatura
                               */
                               public void setLicenciatura(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localLicenciaturaTracker = true;
                                       } else {
                                          localLicenciaturaTracker = false;
                                              
                                       }
                                   
                                            this.localLicenciatura=param;
                                    

                               }
                            

                        /**
                        * field for Estado
                        */

                        
                                    protected java.lang.String localEstado ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEstadoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEstado(){
                               return localEstado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Estado
                               */
                               public void setEstado(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localEstadoTracker = true;
                                       } else {
                                          localEstadoTracker = false;
                                              
                                       }
                                   
                                            this.localEstado=param;
                                    

                               }
                            

                        /**
                        * field for Ciudad
                        */

                        
                                    protected java.lang.String localCiudad ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCiudadTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCiudad(){
                               return localCiudad;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Ciudad
                               */
                               public void setCiudad(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCiudadTracker = true;
                                       } else {
                                          localCiudadTracker = false;
                                              
                                       }
                                   
                                            this.localCiudad=param;
                                    

                               }
                            

                        /**
                        * field for Zona
                        */

                        
                                    protected java.lang.String localZona ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localZonaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getZona(){
                               return localZona;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Zona
                               */
                               public void setZona(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localZonaTracker = true;
                                       } else {
                                          localZonaTracker = false;
                                              
                                       }
                                   
                                            this.localZona=param;
                                    

                               }
                            

                        /**
                        * field for OtrosEstudios
                        */

                        
                                    protected java.lang.String localOtrosEstudios ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOtrosEstudiosTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getOtrosEstudios(){
                               return localOtrosEstudios;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OtrosEstudios
                               */
                               public void setOtrosEstudios(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localOtrosEstudiosTracker = true;
                                       } else {
                                          localOtrosEstudiosTracker = false;
                                              
                                       }
                                   
                                            this.localOtrosEstudios=param;
                                    

                               }
                            

                        /**
                        * field for TiempoExperiencia
                        */

                        
                                    protected java.lang.String localTiempoExperiencia ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTiempoExperienciaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTiempoExperiencia(){
                               return localTiempoExperiencia;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TiempoExperiencia
                               */
                               public void setTiempoExperiencia(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTiempoExperienciaTracker = true;
                                       } else {
                                          localTiempoExperienciaTracker = false;
                                              
                                       }
                                   
                                            this.localTiempoExperiencia=param;
                                    

                               }
                            

                        /**
                        * field for ExperienciaReq
                        */

                        
                                    protected java.lang.String localExperienciaReq ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localExperienciaReqTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getExperienciaReq(){
                               return localExperienciaReq;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ExperienciaReq
                               */
                               public void setExperienciaReq(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localExperienciaReqTracker = true;
                                       } else {
                                          localExperienciaReqTracker = false;
                                              
                                       }
                                   
                                            this.localExperienciaReq=param;
                                    

                               }
                            

                        /**
                        * field for PrincipalesTareas
                        */

                        
                                    protected java.lang.String localPrincipalesTareas ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPrincipalesTareasTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPrincipalesTareas(){
                               return localPrincipalesTareas;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PrincipalesTareas
                               */
                               public void setPrincipalesTareas(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPrincipalesTareasTracker = true;
                                       } else {
                                          localPrincipalesTareasTracker = false;
                                              
                                       }
                                   
                                            this.localPrincipalesTareas=param;
                                    

                               }
                            

                        /**
                        * field for Habilidaes
                        */

                        
                                    protected java.lang.String localHabilidaes ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHabilidaesTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHabilidaes(){
                               return localHabilidaes;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Habilidaes
                               */
                               public void setHabilidaes(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHabilidaesTracker = true;
                                       } else {
                                          localHabilidaesTracker = false;
                                              
                                       }
                                   
                                            this.localHabilidaes=param;
                                    

                               }
                            

                        /**
                        * field for ConocimientoComp
                        */

                        
                                    protected java.lang.String localConocimientoComp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localConocimientoCompTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getConocimientoComp(){
                               return localConocimientoComp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ConocimientoComp
                               */
                               public void setConocimientoComp(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localConocimientoCompTracker = true;
                                       } else {
                                          localConocimientoCompTracker = false;
                                              
                                       }
                                   
                                            this.localConocimientoComp=param;
                                    

                               }
                            

                        /**
                        * field for Idiomas
                        */

                        
                                    protected java.lang.String localIdiomas ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIdiomasTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getIdiomas(){
                               return localIdiomas;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Idiomas
                               */
                               public void setIdiomas(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localIdiomasTracker = true;
                                       } else {
                                          localIdiomasTracker = false;
                                              
                                       }
                                   
                                            this.localIdiomas=param;
                                    

                               }
                            

                        /**
                        * field for InfoAdicional
                        */

                        
                                    protected java.lang.String localInfoAdicional ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localInfoAdicionalTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getInfoAdicional(){
                               return localInfoAdicional;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param InfoAdicional
                               */
                               public void setInfoAdicional(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localInfoAdicionalTracker = true;
                                       } else {
                                          localInfoAdicionalTracker = false;
                                              
                                       }
                                   
                                            this.localInfoAdicional=param;
                                    

                               }
                            

                        /**
                        * field for Idsucursal
                        */

                        
                                    protected java.lang.String localIdsucursal ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIdsucursalTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getIdsucursal(){
                               return localIdsucursal;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Idsucursal
                               */
                               public void setIdsucursal(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localIdsucursalTracker = true;
                                       } else {
                                          localIdsucursalTracker = false;
                                              
                                       }
                                   
                                            this.localIdsucursal=param;
                                    

                               }
                            

                        /**
                        * field for Direccion
                        */

                        
                                    protected java.lang.String localDireccion ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDireccionTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDireccion(){
                               return localDireccion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Direccion
                               */
                               public void setDireccion(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDireccionTracker = true;
                                       } else {
                                          localDireccionTracker = false;
                                              
                                       }
                                   
                                            this.localDireccion=param;
                                    

                               }
                            

                        /**
                        * field for Telefono
                        */

                        
                                    protected java.lang.String localTelefono ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTelefonoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTelefono(){
                               return localTelefono;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Telefono
                               */
                               public void setTelefono(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTelefonoTracker = true;
                                       } else {
                                          localTelefonoTracker = false;
                                              
                                       }
                                   
                                            this.localTelefono=param;
                                    

                               }
                            

                        /**
                        * field for URL
                        */

                        
                                    protected java.lang.String localURL ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localURLTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getURL(){
                               return localURL;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param URL
                               */
                               public void setURL(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localURLTracker = true;
                                       } else {
                                          localURLTracker = false;
                                              
                                       }
                                   
                                            this.localURL=param;
                                    

                               }
                            

                        /**
                        * field for Reclutador
                        */

                        
                                    protected java.lang.String localReclutador ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReclutadorTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getReclutador(){
                               return localReclutador;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Reclutador
                               */
                               public void setReclutador(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReclutadorTracker = true;
                                       } else {
                                          localReclutadorTracker = false;
                                              
                                       }
                                   
                                            this.localReclutador=param;
                                    

                               }
                            

                        /**
                        * field for Sueldo
                        */

                        
                                    protected java.lang.String localSueldo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSueldoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSueldo(){
                               return localSueldo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Sueldo
                               */
                               public void setSueldo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSueldoTracker = true;
                                       } else {
                                          localSueldoTracker = false;
                                              
                                       }
                                   
                                            this.localSueldo=param;
                                    

                               }
                            

                        /**
                        * field for SPeriodo
                        */

                        
                                    protected java.lang.String localSPeriodo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSPeriodoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSPeriodo(){
                               return localSPeriodo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SPeriodo
                               */
                               public void setSPeriodo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSPeriodoTracker = true;
                                       } else {
                                          localSPeriodoTracker = false;
                                              
                                       }
                                   
                                            this.localSPeriodo=param;
                                    

                               }
                            

                        /**
                        * field for Frecuencia
                        */

                        
                                    protected java.lang.String localFrecuencia ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFrecuenciaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFrecuencia(){
                               return localFrecuencia;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Frecuencia
                               */
                               public void setFrecuencia(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFrecuenciaTracker = true;
                                       } else {
                                          localFrecuenciaTracker = false;
                                              
                                       }
                                   
                                            this.localFrecuencia=param;
                                    

                               }
                            

                        /**
                        * field for Comedor
                        */

                        
                                    protected java.lang.String localComedor ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localComedorTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getComedor(){
                               return localComedor;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Comedor
                               */
                               public void setComedor(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localComedorTracker = true;
                                       } else {
                                          localComedorTracker = false;
                                              
                                       }
                                   
                                            this.localComedor=param;
                                    

                               }
                            

                        /**
                        * field for Ropa
                        */

                        
                                    protected java.lang.String localRopa ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRopaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getRopa(){
                               return localRopa;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Ropa
                               */
                               public void setRopa(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localRopaTracker = true;
                                       } else {
                                          localRopaTracker = false;
                                              
                                       }
                                   
                                            this.localRopa=param;
                                    

                               }
                            

                        /**
                        * field for Horario
                        */

                        
                                    protected java.lang.String localHorario ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHorarioTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHorario(){
                               return localHorario;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Horario
                               */
                               public void setHorario(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHorarioTracker = true;
                                       } else {
                                          localHorarioTracker = false;
                                              
                                       }
                                   
                                            this.localHorario=param;
                                    

                               }
                            

                        /**
                        * field for Dia
                        */

                        
                                    protected java.lang.String localDia ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDiaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDia(){
                               return localDia;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Dia
                               */
                               public void setDia(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDiaTracker = true;
                                       } else {
                                          localDiaTracker = false;
                                              
                                       }
                                   
                                            this.localDia=param;
                                    

                               }
                            

                        /**
                        * field for Campo0
                        */

                        
                                    protected java.lang.String localCampo0 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCampo0Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCampo0(){
                               return localCampo0;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Campo0
                               */
                               public void setCampo0(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCampo0Tracker = true;
                                       } else {
                                          localCampo0Tracker = false;
                                              
                                       }
                                   
                                            this.localCampo0=param;
                                    

                               }
                            

                        /**
                        * field for Campo1
                        */

                        
                                    protected java.lang.String localCampo1 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCampo1Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCampo1(){
                               return localCampo1;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Campo1
                               */
                               public void setCampo1(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCampo1Tracker = true;
                                       } else {
                                          localCampo1Tracker = false;
                                              
                                       }
                                   
                                            this.localCampo1=param;
                                    

                               }
                            

                        /**
                        * field for Campo2
                        */

                        
                                    protected java.lang.String localCampo2 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCampo2Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCampo2(){
                               return localCampo2;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Campo2
                               */
                               public void setCampo2(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCampo2Tracker = true;
                                       } else {
                                          localCampo2Tracker = false;
                                              
                                       }
                                   
                                            this.localCampo2=param;
                                    

                               }
                            

                        /**
                        * field for Campo3
                        */

                        
                                    protected java.lang.String localCampo3 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCampo3Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCampo3(){
                               return localCampo3;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Campo3
                               */
                               public void setCampo3(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCampo3Tracker = true;
                                       } else {
                                          localCampo3Tracker = false;
                                              
                                       }
                                   
                                            this.localCampo3=param;
                                    

                               }
                            

                        /**
                        * field for Campo4
                        */

                        
                                    protected java.lang.String localCampo4 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCampo4Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCampo4(){
                               return localCampo4;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Campo4
                               */
                               public void setCampo4(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCampo4Tracker = true;
                                       } else {
                                          localCampo4Tracker = false;
                                              
                                       }
                                   
                                            this.localCampo4=param;
                                    

                               }
                            

                        /**
                        * field for Campo5
                        */

                        
                                    protected java.lang.String localCampo5 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCampo5Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCampo5(){
                               return localCampo5;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Campo5
                               */
                               public void setCampo5(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCampo5Tracker = true;
                                       } else {
                                          localCampo5Tracker = false;
                                              
                                       }
                                   
                                            this.localCampo5=param;
                                    

                               }
                            

                        /**
                        * field for Campo6
                        */

                        
                                    protected java.lang.String localCampo6 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCampo6Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCampo6(){
                               return localCampo6;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Campo6
                               */
                               public void setCampo6(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCampo6Tracker = true;
                                       } else {
                                          localCampo6Tracker = false;
                                              
                                       }
                                   
                                            this.localCampo6=param;
                                    

                               }
                            

                        /**
                        * field for Campo7
                        */

                        
                                    protected java.lang.String localCampo7 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCampo7Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCampo7(){
                               return localCampo7;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Campo7
                               */
                               public void setCampo7(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCampo7Tracker = true;
                                       } else {
                                          localCampo7Tracker = false;
                                              
                                       }
                                   
                                            this.localCampo7=param;
                                    

                               }
                            

                        /**
                        * field for Campo8
                        */

                        
                                    protected java.lang.String localCampo8 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCampo8Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCampo8(){
                               return localCampo8;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Campo8
                               */
                               public void setCampo8(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCampo8Tracker = true;
                                       } else {
                                          localCampo8Tracker = false;
                                              
                                       }
                                   
                                            this.localCampo8=param;
                                    

                               }
                            

                        /**
                        * field for Campo9
                        */

                        
                                    protected java.lang.String localCampo9 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCampo9Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCampo9(){
                               return localCampo9;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Campo9
                               */
                               public void setCampo9(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCampo9Tracker = true;
                                       } else {
                                          localCampo9Tracker = false;
                                              
                                       }
                                   
                                            this.localCampo9=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       Vacante.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://200.57.181.35/");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Vacante",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Vacante",
                           xmlWriter);
                   }

               
                   }
                if (localVacanteIdTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"VacanteId", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"VacanteId");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("VacanteId");
                                    }
                                

                                          if (localVacanteId==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("VacanteId cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localVacanteId);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPuestoOfrecidoTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"PuestoOfrecido", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"PuestoOfrecido");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("PuestoOfrecido");
                                    }
                                

                                          if (localPuestoOfrecido==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("PuestoOfrecido cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPuestoOfrecido);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPuestoClienteTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"PuestoCliente", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"PuestoCliente");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("PuestoCliente");
                                    }
                                

                                          if (localPuestoCliente==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("PuestoCliente cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPuestoCliente);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAreaTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Area", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Area");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Area");
                                    }
                                

                                          if (localArea==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Area cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localArea);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEscolaridadTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Escolaridad", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Escolaridad");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Escolaridad");
                                    }
                                

                                          if (localEscolaridad==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Escolaridad cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEscolaridad);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLicenciaturaTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Licenciatura", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Licenciatura");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Licenciatura");
                                    }
                                

                                          if (localLicenciatura==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Licenciatura cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLicenciatura);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEstadoTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Estado", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Estado");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Estado");
                                    }
                                

                                          if (localEstado==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Estado cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEstado);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCiudadTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Ciudad", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Ciudad");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Ciudad");
                                    }
                                

                                          if (localCiudad==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Ciudad cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCiudad);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localZonaTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Zona", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Zona");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Zona");
                                    }
                                

                                          if (localZona==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Zona cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localZona);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localOtrosEstudiosTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"OtrosEstudios", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"OtrosEstudios");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("OtrosEstudios");
                                    }
                                

                                          if (localOtrosEstudios==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("OtrosEstudios cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localOtrosEstudios);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTiempoExperienciaTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TiempoExperiencia", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TiempoExperiencia");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TiempoExperiencia");
                                    }
                                

                                          if (localTiempoExperiencia==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TiempoExperiencia cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTiempoExperiencia);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localExperienciaReqTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ExperienciaReq", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ExperienciaReq");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ExperienciaReq");
                                    }
                                

                                          if (localExperienciaReq==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ExperienciaReq cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localExperienciaReq);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPrincipalesTareasTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"PrincipalesTareas", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"PrincipalesTareas");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("PrincipalesTareas");
                                    }
                                

                                          if (localPrincipalesTareas==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("PrincipalesTareas cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPrincipalesTareas);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHabilidaesTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Habilidaes", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Habilidaes");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Habilidaes");
                                    }
                                

                                          if (localHabilidaes==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Habilidaes cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHabilidaes);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localConocimientoCompTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ConocimientoComp", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ConocimientoComp");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ConocimientoComp");
                                    }
                                

                                          if (localConocimientoComp==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ConocimientoComp cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localConocimientoComp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localIdiomasTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Idiomas", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Idiomas");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Idiomas");
                                    }
                                

                                          if (localIdiomas==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Idiomas cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localIdiomas);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localInfoAdicionalTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"InfoAdicional", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"InfoAdicional");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("InfoAdicional");
                                    }
                                

                                          if (localInfoAdicional==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("InfoAdicional cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localInfoAdicional);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localIdsucursalTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Idsucursal", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Idsucursal");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Idsucursal");
                                    }
                                

                                          if (localIdsucursal==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Idsucursal cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localIdsucursal);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDireccionTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Direccion", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Direccion");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Direccion");
                                    }
                                

                                          if (localDireccion==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Direccion cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDireccion);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTelefonoTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Telefono", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Telefono");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Telefono");
                                    }
                                

                                          if (localTelefono==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Telefono cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTelefono);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localURLTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"URL", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"URL");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("URL");
                                    }
                                

                                          if (localURL==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("URL cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localURL);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localReclutadorTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Reclutador", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Reclutador");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Reclutador");
                                    }
                                

                                          if (localReclutador==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Reclutador cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localReclutador);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSueldoTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Sueldo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Sueldo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Sueldo");
                                    }
                                

                                          if (localSueldo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Sueldo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSueldo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSPeriodoTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"SPeriodo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"SPeriodo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("SPeriodo");
                                    }
                                

                                          if (localSPeriodo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("SPeriodo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSPeriodo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFrecuenciaTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Frecuencia", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Frecuencia");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Frecuencia");
                                    }
                                

                                          if (localFrecuencia==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Frecuencia cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFrecuencia);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localComedorTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Comedor", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Comedor");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Comedor");
                                    }
                                

                                          if (localComedor==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Comedor cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localComedor);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localRopaTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Ropa", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Ropa");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Ropa");
                                    }
                                

                                          if (localRopa==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Ropa cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localRopa);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHorarioTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Horario", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Horario");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Horario");
                                    }
                                

                                          if (localHorario==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Horario cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHorario);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDiaTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Dia", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Dia");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Dia");
                                    }
                                

                                          if (localDia==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Dia cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDia);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCampo0Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Campo0", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Campo0");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Campo0");
                                    }
                                

                                          if (localCampo0==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Campo0 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCampo0);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCampo1Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Campo1", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Campo1");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Campo1");
                                    }
                                

                                          if (localCampo1==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Campo1 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCampo1);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCampo2Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Campo2", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Campo2");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Campo2");
                                    }
                                

                                          if (localCampo2==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Campo2 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCampo2);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCampo3Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Campo3", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Campo3");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Campo3");
                                    }
                                

                                          if (localCampo3==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Campo3 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCampo3);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCampo4Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Campo4", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Campo4");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Campo4");
                                    }
                                

                                          if (localCampo4==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Campo4 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCampo4);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCampo5Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Campo5", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Campo5");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Campo5");
                                    }
                                

                                          if (localCampo5==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Campo5 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCampo5);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCampo6Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Campo6", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Campo6");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Campo6");
                                    }
                                

                                          if (localCampo6==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Campo6 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCampo6);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCampo7Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Campo7", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Campo7");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Campo7");
                                    }
                                

                                          if (localCampo7==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Campo7 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCampo7);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCampo8Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Campo8", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Campo8");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Campo8");
                                    }
                                

                                          if (localCampo8==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Campo8 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCampo8);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCampo9Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Campo9", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Campo9");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Campo9");
                                    }
                                

                                          if (localCampo9==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Campo9 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCampo9);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localVacanteIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "VacanteId"));
                                 
                                        if (localVacanteId != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localVacanteId));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("VacanteId cannot be null!!");
                                        }
                                    } if (localPuestoOfrecidoTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "PuestoOfrecido"));
                                 
                                        if (localPuestoOfrecido != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPuestoOfrecido));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("PuestoOfrecido cannot be null!!");
                                        }
                                    } if (localPuestoClienteTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "PuestoCliente"));
                                 
                                        if (localPuestoCliente != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPuestoCliente));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("PuestoCliente cannot be null!!");
                                        }
                                    } if (localAreaTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Area"));
                                 
                                        if (localArea != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localArea));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Area cannot be null!!");
                                        }
                                    } if (localEscolaridadTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Escolaridad"));
                                 
                                        if (localEscolaridad != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEscolaridad));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Escolaridad cannot be null!!");
                                        }
                                    } if (localLicenciaturaTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Licenciatura"));
                                 
                                        if (localLicenciatura != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLicenciatura));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Licenciatura cannot be null!!");
                                        }
                                    } if (localEstadoTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Estado"));
                                 
                                        if (localEstado != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEstado));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Estado cannot be null!!");
                                        }
                                    } if (localCiudadTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Ciudad"));
                                 
                                        if (localCiudad != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCiudad));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Ciudad cannot be null!!");
                                        }
                                    } if (localZonaTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Zona"));
                                 
                                        if (localZona != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localZona));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Zona cannot be null!!");
                                        }
                                    } if (localOtrosEstudiosTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "OtrosEstudios"));
                                 
                                        if (localOtrosEstudios != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOtrosEstudios));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("OtrosEstudios cannot be null!!");
                                        }
                                    } if (localTiempoExperienciaTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "TiempoExperiencia"));
                                 
                                        if (localTiempoExperiencia != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTiempoExperiencia));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TiempoExperiencia cannot be null!!");
                                        }
                                    } if (localExperienciaReqTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "ExperienciaReq"));
                                 
                                        if (localExperienciaReq != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localExperienciaReq));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ExperienciaReq cannot be null!!");
                                        }
                                    } if (localPrincipalesTareasTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "PrincipalesTareas"));
                                 
                                        if (localPrincipalesTareas != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPrincipalesTareas));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("PrincipalesTareas cannot be null!!");
                                        }
                                    } if (localHabilidaesTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Habilidaes"));
                                 
                                        if (localHabilidaes != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHabilidaes));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Habilidaes cannot be null!!");
                                        }
                                    } if (localConocimientoCompTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "ConocimientoComp"));
                                 
                                        if (localConocimientoComp != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localConocimientoComp));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ConocimientoComp cannot be null!!");
                                        }
                                    } if (localIdiomasTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Idiomas"));
                                 
                                        if (localIdiomas != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdiomas));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Idiomas cannot be null!!");
                                        }
                                    } if (localInfoAdicionalTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "InfoAdicional"));
                                 
                                        if (localInfoAdicional != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localInfoAdicional));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("InfoAdicional cannot be null!!");
                                        }
                                    } if (localIdsucursalTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Idsucursal"));
                                 
                                        if (localIdsucursal != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdsucursal));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Idsucursal cannot be null!!");
                                        }
                                    } if (localDireccionTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Direccion"));
                                 
                                        if (localDireccion != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDireccion));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Direccion cannot be null!!");
                                        }
                                    } if (localTelefonoTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Telefono"));
                                 
                                        if (localTelefono != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTelefono));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Telefono cannot be null!!");
                                        }
                                    } if (localURLTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "URL"));
                                 
                                        if (localURL != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localURL));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("URL cannot be null!!");
                                        }
                                    } if (localReclutadorTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Reclutador"));
                                 
                                        if (localReclutador != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReclutador));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Reclutador cannot be null!!");
                                        }
                                    } if (localSueldoTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Sueldo"));
                                 
                                        if (localSueldo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSueldo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Sueldo cannot be null!!");
                                        }
                                    } if (localSPeriodoTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "SPeriodo"));
                                 
                                        if (localSPeriodo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSPeriodo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("SPeriodo cannot be null!!");
                                        }
                                    } if (localFrecuenciaTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Frecuencia"));
                                 
                                        if (localFrecuencia != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFrecuencia));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Frecuencia cannot be null!!");
                                        }
                                    } if (localComedorTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Comedor"));
                                 
                                        if (localComedor != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localComedor));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Comedor cannot be null!!");
                                        }
                                    } if (localRopaTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Ropa"));
                                 
                                        if (localRopa != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRopa));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Ropa cannot be null!!");
                                        }
                                    } if (localHorarioTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Horario"));
                                 
                                        if (localHorario != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHorario));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Horario cannot be null!!");
                                        }
                                    } if (localDiaTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Dia"));
                                 
                                        if (localDia != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDia));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Dia cannot be null!!");
                                        }
                                    } if (localCampo0Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Campo0"));
                                 
                                        if (localCampo0 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCampo0));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Campo0 cannot be null!!");
                                        }
                                    } if (localCampo1Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Campo1"));
                                 
                                        if (localCampo1 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCampo1));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Campo1 cannot be null!!");
                                        }
                                    } if (localCampo2Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Campo2"));
                                 
                                        if (localCampo2 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCampo2));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Campo2 cannot be null!!");
                                        }
                                    } if (localCampo3Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Campo3"));
                                 
                                        if (localCampo3 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCampo3));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Campo3 cannot be null!!");
                                        }
                                    } if (localCampo4Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Campo4"));
                                 
                                        if (localCampo4 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCampo4));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Campo4 cannot be null!!");
                                        }
                                    } if (localCampo5Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Campo5"));
                                 
                                        if (localCampo5 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCampo5));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Campo5 cannot be null!!");
                                        }
                                    } if (localCampo6Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Campo6"));
                                 
                                        if (localCampo6 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCampo6));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Campo6 cannot be null!!");
                                        }
                                    } if (localCampo7Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Campo7"));
                                 
                                        if (localCampo7 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCampo7));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Campo7 cannot be null!!");
                                        }
                                    } if (localCampo8Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Campo8"));
                                 
                                        if (localCampo8 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCampo8));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Campo8 cannot be null!!");
                                        }
                                    } if (localCampo9Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Campo9"));
                                 
                                        if (localCampo9 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCampo9));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Campo9 cannot be null!!");
                                        }
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Vacante parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Vacante object =
                new Vacante();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Vacante".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Vacante)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","VacanteId").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setVacanteId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","PuestoOfrecido").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPuestoOfrecido(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","PuestoCliente").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPuestoCliente(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Area").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setArea(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Escolaridad").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEscolaridad(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Licenciatura").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLicenciatura(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Estado").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEstado(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Ciudad").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCiudad(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Zona").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setZona(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","OtrosEstudios").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setOtrosEstudios(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","TiempoExperiencia").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTiempoExperiencia(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","ExperienciaReq").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setExperienciaReq(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","PrincipalesTareas").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPrincipalesTareas(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Habilidaes").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHabilidaes(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","ConocimientoComp").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setConocimientoComp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Idiomas").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setIdiomas(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","InfoAdicional").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setInfoAdicional(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Idsucursal").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setIdsucursal(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Direccion").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDireccion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Telefono").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTelefono(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","URL").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setURL(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Reclutador").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setReclutador(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Sueldo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSueldo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","SPeriodo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSPeriodo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Frecuencia").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFrecuencia(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Comedor").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setComedor(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Ropa").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setRopa(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Horario").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHorario(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Dia").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDia(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Campo0").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCampo0(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Campo1").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCampo1(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Campo2").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCampo2(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Campo3").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCampo3(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Campo4").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCampo4(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Campo5").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCampo5(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Campo6").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCampo6(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Campo7").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCampo7(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Campo8").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCampo8(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Campo9").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCampo9(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          
        public static class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://200.57.181.35/".equals(namespaceURI) &&
                  "Vacante".equals(typeName)){
                   
                            return  Vacante.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://200.57.181.35/".equals(namespaceURI) &&
                  "ParametroEntrada".equals(typeName)){
                   
                            return  ParametroEntrada.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://200.57.181.35/".equals(namespaceURI) &&
                  "ArrayOfVacante".equals(typeName)){
                   
                            return  ArrayOfVacante.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    
        public static class ParametroEntrada
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = ParametroEntrada
                Namespace URI = http://200.57.181.35/
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://200.57.181.35/")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for Puesto
                        */

                        
                                    protected java.lang.String localPuesto ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPuestoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPuesto(){
                               return localPuesto;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Puesto
                               */
                               public void setPuesto(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPuestoTracker = true;
                                       } else {
                                          localPuestoTracker = false;
                                              
                                       }
                                   
                                            this.localPuesto=param;
                                    

                               }
                            

                        /**
                        * field for PuestoID
                        */

                        
                                    protected int localPuestoID ;
                                

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getPuestoID(){
                               return localPuestoID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PuestoID
                               */
                               public void setPuestoID(int param){
                            
                                            this.localPuestoID=param;
                                    

                               }
                            

                        /**
                        * field for EstadoID
                        */

                        
                                    protected int localEstadoID ;
                                

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getEstadoID(){
                               return localEstadoID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EstadoID
                               */
                               public void setEstadoID(int param){
                            
                                            this.localEstadoID=param;
                                    

                               }
                            

                        /**
                        * field for AreaID
                        */

                        
                                    protected int localAreaID ;
                                

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getAreaID(){
                               return localAreaID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AreaID
                               */
                               public void setAreaID(int param){
                            
                                            this.localAreaID=param;
                                    

                               }
                            

                        /**
                        * field for SMinimo
                        */

                        
                                    protected int localSMinimo ;
                                

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getSMinimo(){
                               return localSMinimo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SMinimo
                               */
                               public void setSMinimo(int param){
                            
                                            this.localSMinimo=param;
                                    

                               }
                            

                        /**
                        * field for SMaximo
                        */

                        
                                    protected int localSMaximo ;
                                

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getSMaximo(){
                               return localSMaximo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SMaximo
                               */
                               public void setSMaximo(int param){
                            
                                            this.localSMaximo=param;
                                    

                               }
                            

                        /**
                        * field for VacanteID
                        */

                        
                                    protected int localVacanteID ;
                                

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getVacanteID(){
                               return localVacanteID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VacanteID
                               */
                               public void setVacanteID(int param){
                            
                                            this.localVacanteID=param;
                                    

                               }
                            

                        /**
                        * field for Filtro1
                        */

                        
                                    protected java.lang.String localFiltro1 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFiltro1Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFiltro1(){
                               return localFiltro1;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Filtro1
                               */
                               public void setFiltro1(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFiltro1Tracker = true;
                                       } else {
                                          localFiltro1Tracker = false;
                                              
                                       }
                                   
                                            this.localFiltro1=param;
                                    

                               }
                            

                        /**
                        * field for Filtro2
                        */

                        
                                    protected java.lang.String localFiltro2 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFiltro2Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFiltro2(){
                               return localFiltro2;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Filtro2
                               */
                               public void setFiltro2(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFiltro2Tracker = true;
                                       } else {
                                          localFiltro2Tracker = false;
                                              
                                       }
                                   
                                            this.localFiltro2=param;
                                    

                               }
                            

                        /**
                        * field for Filtro3
                        */

                        
                                    protected java.lang.String localFiltro3 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFiltro3Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFiltro3(){
                               return localFiltro3;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Filtro3
                               */
                               public void setFiltro3(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFiltro3Tracker = true;
                                       } else {
                                          localFiltro3Tracker = false;
                                              
                                       }
                                   
                                            this.localFiltro3=param;
                                    

                               }
                            

                        /**
                        * field for Filtro4
                        */

                        
                                    protected java.lang.String localFiltro4 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFiltro4Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFiltro4(){
                               return localFiltro4;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Filtro4
                               */
                               public void setFiltro4(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFiltro4Tracker = true;
                                       } else {
                                          localFiltro4Tracker = false;
                                              
                                       }
                                   
                                            this.localFiltro4=param;
                                    

                               }
                            

                        /**
                        * field for Filtro5
                        */

                        
                                    protected java.lang.String localFiltro5 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFiltro5Tracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFiltro5(){
                               return localFiltro5;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Filtro5
                               */
                               public void setFiltro5(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFiltro5Tracker = true;
                                       } else {
                                          localFiltro5Tracker = false;
                                              
                                       }
                                   
                                            this.localFiltro5=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       ParametroEntrada.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://200.57.181.35/");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":ParametroEntrada",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "ParametroEntrada",
                           xmlWriter);
                   }

               
                   }
                if (localPuestoTracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Puesto", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Puesto");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Puesto");
                                    }
                                

                                          if (localPuesto==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Puesto cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPuesto);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"PuestoID", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"PuestoID");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("PuestoID");
                                    }
                                
                                               if (localPuestoID==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("PuestoID cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPuestoID));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"EstadoID", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"EstadoID");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("EstadoID");
                                    }
                                
                                               if (localEstadoID==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("EstadoID cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEstadoID));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"AreaID", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"AreaID");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("AreaID");
                                    }
                                
                                               if (localAreaID==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("AreaID cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAreaID));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"SMinimo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"SMinimo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("SMinimo");
                                    }
                                
                                               if (localSMinimo==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("SMinimo cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSMinimo));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"SMaximo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"SMaximo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("SMaximo");
                                    }
                                
                                               if (localSMaximo==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("SMaximo cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSMaximo));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"VacanteID", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"VacanteID");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("VacanteID");
                                    }
                                
                                               if (localVacanteID==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("VacanteID cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localVacanteID));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                              if (localFiltro1Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Filtro1", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Filtro1");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Filtro1");
                                    }
                                

                                          if (localFiltro1==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Filtro1 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFiltro1);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFiltro2Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Filtro2", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Filtro2");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Filtro2");
                                    }
                                

                                          if (localFiltro2==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Filtro2 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFiltro2);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFiltro3Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Filtro3", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Filtro3");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Filtro3");
                                    }
                                

                                          if (localFiltro3==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Filtro3 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFiltro3);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFiltro4Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Filtro4", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Filtro4");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Filtro4");
                                    }
                                

                                          if (localFiltro4==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Filtro4 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFiltro4);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFiltro5Tracker){
                                    namespace = "http://200.57.181.35/";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Filtro5", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Filtro5");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Filtro5");
                                    }
                                

                                          if (localFiltro5==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Filtro5 cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFiltro5);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localPuestoTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Puesto"));
                                 
                                        if (localPuesto != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPuesto));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Puesto cannot be null!!");
                                        }
                                    }
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "PuestoID"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPuestoID));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "EstadoID"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEstadoID));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "AreaID"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAreaID));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "SMinimo"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSMinimo));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "SMaximo"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSMaximo));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "VacanteID"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localVacanteID));
                             if (localFiltro1Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Filtro1"));
                                 
                                        if (localFiltro1 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFiltro1));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Filtro1 cannot be null!!");
                                        }
                                    } if (localFiltro2Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Filtro2"));
                                 
                                        if (localFiltro2 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFiltro2));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Filtro2 cannot be null!!");
                                        }
                                    } if (localFiltro3Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Filtro3"));
                                 
                                        if (localFiltro3 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFiltro3));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Filtro3 cannot be null!!");
                                        }
                                    } if (localFiltro4Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Filtro4"));
                                 
                                        if (localFiltro4 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFiltro4));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Filtro4 cannot be null!!");
                                        }
                                    } if (localFiltro5Tracker){
                                      elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "Filtro5"));
                                 
                                        if (localFiltro5 != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFiltro5));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Filtro5 cannot be null!!");
                                        }
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static ParametroEntrada parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ParametroEntrada object =
                new ParametroEntrada();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"ParametroEntrada".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ParametroEntrada)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Puesto").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPuesto(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","PuestoID").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPuestoID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","EstadoID").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEstadoID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","AreaID").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAreaID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","SMinimo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSMinimo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","SMaximo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSMaximo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","VacanteID").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setVacanteID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Filtro1").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFiltro1(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Filtro2").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFiltro2(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Filtro3").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFiltro3(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Filtro4").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFiltro4(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Filtro5").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFiltro5(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          
        public static class ObtenVacantesResponse
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://200.57.181.35/",
                "ObtenVacantesResponse",
                "ns1");

            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://200.57.181.35/")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for ObtenVacantesResult
                        */

                        
                                    protected ArrayOfVacante localObtenVacantesResult ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localObtenVacantesResultTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return ArrayOfVacante
                           */
                           public  ArrayOfVacante getObtenVacantesResult(){
                               return localObtenVacantesResult;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ObtenVacantesResult
                               */
                               public void setObtenVacantesResult(ArrayOfVacante param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localObtenVacantesResultTracker = true;
                                       } else {
                                          localObtenVacantesResultTracker = false;
                                              
                                       }
                                   
                                            this.localObtenVacantesResult=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
                org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       ObtenVacantesResponse.this.serialize(MY_QNAME,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               MY_QNAME,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://200.57.181.35/");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":ObtenVacantesResponse",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "ObtenVacantesResponse",
                           xmlWriter);
                   }

               
                   }
                if (localObtenVacantesResultTracker){
                                            if (localObtenVacantesResult==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ObtenVacantesResult cannot be null!!");
                                            }
                                           localObtenVacantesResult.serialize(new javax.xml.namespace.QName("http://200.57.181.35/","ObtenVacantesResult"),
                                               factory,xmlWriter);
                                        }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localObtenVacantesResultTracker){
                            elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                      "ObtenVacantesResult"));
                            
                            
                                    if (localObtenVacantesResult==null){
                                         throw new org.apache.axis2.databinding.ADBException("ObtenVacantesResult cannot be null!!");
                                    }
                                    elementList.add(localObtenVacantesResult);
                                }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static ObtenVacantesResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ObtenVacantesResponse object =
                new ObtenVacantesResponse();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"ObtenVacantesResponse".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ObtenVacantesResponse)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","ObtenVacantesResult").equals(reader.getName())){
                                
                                                object.setObtenVacantesResult(ArrayOfVacante.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          
        public static class ArrayOfVacante
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = ArrayOfVacante
                Namespace URI = http://200.57.181.35/
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://200.57.181.35/")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for Vacante
                        * This was an Array!
                        */

                        
                                    protected Vacante[] localVacante ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localVacanteTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return Vacante[]
                           */
                           public  Vacante[] getVacante(){
                               return localVacante;
                           }

                           
                        


                               
                              /**
                               * validate the array for Vacante
                               */
                              protected void validateVacante(Vacante[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Vacante
                              */
                              public void setVacante(Vacante[] param){
                              
                                   validateVacante(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localVacanteTracker = true;
                                          } else {
                                             localVacanteTracker = true;
                                                 
                                          }
                                      
                                      this.localVacante=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param Vacante
                             */
                             public void addVacante(Vacante param){
                                   if (localVacante == null){
                                   localVacante = new Vacante[]{};
                                   }

                            
                                 //update the setting tracker
                                localVacanteTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localVacante);
                               list.add(param);
                               this.localVacante =
                             (Vacante[])list.toArray(
                            new Vacante[list.size()]);

                             }
                             

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       ArrayOfVacante.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://200.57.181.35/");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":ArrayOfVacante",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "ArrayOfVacante",
                           xmlWriter);
                   }

               
                   }
                if (localVacanteTracker){
                                       if (localVacante!=null){
                                            for (int i = 0;i < localVacante.length;i++){
                                                if (localVacante[i] != null){
                                                 localVacante[i].serialize(new javax.xml.namespace.QName("http://200.57.181.35/","Vacante"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                            // write null attribute
                                                            java.lang.String namespace2 = "http://200.57.181.35/";
                                                            if (! namespace2.equals("")) {
                                                                java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                                if (prefix2 == null) {
                                                                    prefix2 = generatePrefix(namespace2);

                                                                    xmlWriter.writeStartElement(prefix2,"Vacante", namespace2);
                                                                    xmlWriter.writeNamespace(prefix2, namespace2);
                                                                    xmlWriter.setPrefix(prefix2, namespace2);

                                                                } else {
                                                                    xmlWriter.writeStartElement(namespace2,"Vacante");
                                                                }

                                                            } else {
                                                                xmlWriter.writeStartElement("Vacante");
                                                            }

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                // write null attribute
                                                java.lang.String namespace2 = "http://200.57.181.35/";
                                                if (! namespace2.equals("")) {
                                                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                    if (prefix2 == null) {
                                                        prefix2 = generatePrefix(namespace2);

                                                        xmlWriter.writeStartElement(prefix2,"Vacante", namespace2);
                                                        xmlWriter.writeNamespace(prefix2, namespace2);
                                                        xmlWriter.setPrefix(prefix2, namespace2);

                                                    } else {
                                                        xmlWriter.writeStartElement(namespace2,"Vacante");
                                                    }

                                                } else {
                                                    xmlWriter.writeStartElement("Vacante");
                                                }

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localVacanteTracker){
                             if (localVacante!=null) {
                                 for (int i = 0;i < localVacante.length;i++){

                                    if (localVacante[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                          "Vacante"));
                                         elementList.add(localVacante[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                          "Vacante"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("http://200.57.181.35/",
                                                                          "Vacante"));
                                        elementList.add(localVacante);
                                    
                             }

                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static ArrayOfVacante parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ArrayOfVacante object =
                new ArrayOfVacante();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"ArrayOfVacante".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ArrayOfVacante)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://200.57.181.35/","Vacante").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list1.add(null);
                                                              reader.next();
                                                          } else {
                                                        list1.add(Vacante.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone1 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://200.57.181.35/","Vacante").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list1.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list1.add(Vacante.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone1 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setVacante((Vacante[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                Vacante.class,
                                                                list1));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          
            private  org.apache.axiom.om.OMElement  toOM(mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantes param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantes.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantesResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantesResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantes param, boolean optimizeContent)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantes.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantes.class.equals(type)){
                
                           return mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantes.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantesResponse.class.equals(type)){
                
                           return mx.gob.stps.portal.core.ws.ofertas.adecco.WsOfertasStub.ObtenVacantesResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    
   }
   