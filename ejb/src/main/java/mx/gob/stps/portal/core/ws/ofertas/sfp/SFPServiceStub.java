package mx.gob.stps.portal.core.ws.ofertas.sfp;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoSFPVO;

import org.apache.axis2.AxisFault;

public class SFPServiceStub extends org.apache.axis2.client.Stub {

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
        return java.lang.Long.toString(System.currentTimeMillis()) + "_" + counter;
    }    
    
    /* 
	public static void main(String [] args){
		try {
			List<OfertaEmpleoSFPVO> listaOfertas = null;
			OfertaEmpleoSFPVO voOut = null;			
			mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub reqWS = new mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub();
			ListaVacantes listaVacantes = new ListaVacantes();			
			
			SimpleTimeZone tz = new SimpleTimeZone(-21600000, "America/Mexico_City", 3, 1, -1, 7200000, 9, -1, 1, 7200000, 3600000);
			GregorianCalendar cal = new GregorianCalendar(tz);
			cal.add(Calendar.DATE, -1); // Se cargan las ofertas del dia anterior	
			String fechaAlta = Utils.getFechaFormato(cal.getTime());			
			//listaVacantes.setFecha("01/01/2012");
			listaVacantes.setFecha(fechaAlta);
			ListaVacantesResponse respuesta = reqWS.listaVacantes(listaVacantes);			
			
			Vacante[] vacante = respuesta.get_return();
			if (vacante!=null){
				listaOfertas = new ArrayList<OfertaEmpleoSFPVO>();		
				for(SFPServiceStub.Vacante v : vacante){										
					voOut = new OfertaEmpleoSFPVO();
					DomicilioVO domicilio = new DomicilioVO();
					domicilio.setCalle(v.getCalle());
					System.out.println("------(v.getCodigoPostal():" + v.getCodigoPostal());
					domicilio.setCodigoPostal(v.getCodigoPostal());
					System.out.println("------(v.getIdMunicipio():" + v.getIdMunicipio());
					domicilio.setIdMunicipio(v.getIdMunicipio());
					System.out.println("------(v.getIdEntidad():" + v.getIdEntidad());
					domicilio.setIdEntidad(v.getIdEntidad());					
					domicilio.setNumeroExterior(v.getNumeroExt());
					voOut.setDomicilio(domicilio);
					voOut.setFechaAlta(v.getFechaAlta());
					System.out.println("------voOut.getFechaAlta:" + voOut.getFechaAlta());
					voOut.setFechaFin(v.getFechaVencimiento());
					voOut.setFunciones(v.getFunciones());
					voOut.setHabilidadGeneral(v.getHabilidad());
					voOut.setIdAreaLaboral(v.getIdAreaLaboral());
					voOut.setIdEmpresa(v.getIdEmpresa());
					voOut.setIdOcupacion(v.getIdOcupacion());
					voOut.setIdSituacionAcademica(v.getIdSituacion());					
					voOut.setObservaciones(v.getObservaciones());
					voOut.setTituloOferta(v.getPuesto());
					System.out.println("------voOut.getPuesto:" + voOut.getTituloOferta());					
					voOut.setSalario(v.getSalario());		
					System.out.println("------voOut.getIdVacante:" + v.getIdVacante());
					voOut.setIdOfertaEmpleo((long)v.getIdVacante());
					//idEspecialidad???
					List<OfertaCarreraEspecialidadVO> carreras = new ArrayList<OfertaCarreraEspecialidadVO>();
					OfertaCarreraEspecialidadVO carrera = new OfertaCarreraEspecialidadVO();
					int[] intCarreras = v.getIdEspecialidad();
					if (intCarreras != null) {
						for(int i=0; i<intCarreras.length; i++){
							carrera.setId(intCarreras[i]);			
							System.out.println("------intCarreras[i]:" + intCarreras[i]);
							carreras.add(carrera);							
						}												
					}
					voOut.setCarreras(carreras);					
					//idExperiencia		
					
					//voOut.setEscolaridad(v.getIdEscolaridad());	
					//voOut.setExperienciaAnios(experienciaAnios)
					//voOut.setExperienciaAniosDescrip(experienciaAniosDescrip)
					//voOut.setCarreraDescripcion(carreraDescripcion)
					//voOut.setCarreras(carreras)					
					//voOut.setAreaLaboralDescripcion(areaLaboralDescripcion)
					//voOut.setDispRadicarDescripcion(dispRadicarDescripcion)
					//voOut.setDispViajarDescripcion(dispViajarDescripcion)
					//voOut.setEdadPreferente(edadPreferente)
					//voOut.setEmpresaDescripcion(empresaDescripcion)
					//voOut.setHorario(horario)
					//voOut.setIdNivelEstudio(idNivelEstudio)
					//voOut.setIdOfertaBolsaSFP(idOfertaBolsaSFP)
					//voOut.setNumeroPlazas(numeroPlazas)
					//voOut.setOcupacionDescripcion(ocupacionDescripcion)
					//voOut.setSituacionAcademicaDescrip(situacionAcademicaDescrip)
					//voOut.setTipoEmpleoDescripcion(tipoEmpleoDescripcion)					
					listaOfertas.add(voOut);					
				}
				
			} else{
				//voOut = new OfertaEmpleoSFPVO();
				//voOut.setFechaAlta(v.getFechaAlta());
				//System.out.println("------voOut.getFecha:" + voOut.getFechaAlta());				
				//voOut.setTituloOferta("No se encontraron datos");
				//System.out.println("------voOut.getTituloOferta:" + voOut.getTituloOferta());
				listaOfertas.add(voOut);											
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	*/
    
    
    private void populateAxisService() throws org.apache.axis2.AxisFault {
    	
        //creating the Service with a unique name
        _service = new org.apache.axis2.description.AxisService("VacantesTrabajaEnService" + getUniqueSuffix());
        addAnonymousOperations();

           //creating the operations
           org.apache.axis2.description.AxisOperation __operation;

           _operations = new org.apache.axis2.description.AxisOperation[2];
           
                       __operation = new org.apache.axis2.description.OutOnlyAxisOperation();
                   
               __operation.setName(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx", "preparaStageVacantes"));
   	    _service.addOperation(__operation);
   	    
               _operations[0]=__operation;
           
                      __operation = new org.apache.axis2.description.OutInAxisOperation();

               __operation.setName(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx", "listaVacantes"));
   	    _service.addOperation(__operation);
   	    
               _operations[1]=__operation;    	
    }
    
    //populates the faults
    private void populateFaults(){
    }    
    
    /**
     *Constructor that takes in a configContext
     */
    public SFPServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
	      java.lang.String targetEndpoint)
	      throws org.apache.axis2.AxisFault {
    	this(configurationContext,targetEndpoint,false);
	}    
    
   
   /**
    * Constructor that takes in a configContext  and useseperate listner
    */
    public SFPServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
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
    public SFPServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {    
        this(configurationContext,"http://200.77.236.103:8080/axis2/services/VacantesTrabajaEnService.VacantesTrabajaEnServiceHttpSoap12Endpoint/" );              
    }
  
    /**
     * Default Constructor
     */
    public SFPServiceStub() throws org.apache.axis2.AxisFault {      
        this("http://200.77.236.103:8080/axis2/services/VacantesTrabajaEnService.VacantesTrabajaEnServiceHttpSoap12Endpoint/" );             
    }
  
    /**
     * Constructor taking the target endpoint
     */
    public SFPServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }  
  

    /**
     * Auto generated method signature
     * 
     */
    public void  preparaStageVacantes(
    		mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.PreparaStageVacantes preparaStageVacantes) throws java.rmi.RemoteException
    	{
    	org.apache.axis2.context.MessageContext _messageContext = null;

    	org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
    	_operationClient.getOptions().setAction("urn:preparaStageVacantes");
    	_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

    	addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");

    	org.apache.axiom.soap.SOAPEnvelope env = null;
    	_messageContext = new org.apache.axis2.context.MessageContext();

    	//Style is Doc.
    	env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
    			preparaStageVacantes,
    			optimizeContent(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx",
    					"preparaStageVacantes")));
    	//adding SOAP soap_headers
    	_serviceClient.addHeadersToEnvelope(env);
    	// create message context with that soap envelope
    	_messageContext.setEnvelope(env);
    	// add the message contxt to the operation client
    	_operationClient.addMessageContext(_messageContext);
    	_operationClient.execute(true);
    	_messageContext.getTransportOut().getSender().cleanup(_messageContext); 
    	return;
    }

     /**
      * Auto generated method signature
      * 
      * @see mx.gob.stps.portal.core.ws.ofertas.sfp.SFPService#listaVacantes
      * @param listaVacantes
     
      */
    public  mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantesResponse listaVacantes(

             mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantes listaVacantes)
         

     throws java.rmi.RemoteException
     
     {
org.apache.axis2.context.MessageContext _messageContext = null;
try{
org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
_operationClient.getOptions().setAction("urn:listaVacantes");
_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



   addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


// create a message context
_messageContext = new org.apache.axis2.context.MessageContext();



// create SOAP envelope with that payload
org.apache.axiom.soap.SOAPEnvelope env = null;
     
                                     
                                     env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                     listaVacantes,
                                     optimizeContent(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx",
                                     "listaVacantes")));
                                 
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
                              mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantesResponse.class,
                               getEnvelopeNamespaces(_returnEnv));

                
                         return (mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantesResponse)object;
                    
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
//http://200.77.236.103:8080/axis2/services/VacantesTrabajaEnService.VacantesTrabajaEnServiceHttpSoap12Endpoint/
public static class Vacante
implements org.apache.axis2.databinding.ADBBean{
/* This type was generated from the piece of schema that had
 name = Vacante
 Namespace URI = http://stps.ws.sfp.gob.mx/xsd
 Namespace Prefix = ns1
 */


private static java.lang.String generatePrefix(java.lang.String namespace) {
if(namespace.equals("http://stps.ws.sfp.gob.mx/xsd")){
 return "ns1";
}
return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
}



         /**
         * field for Calle
         */

         
                     protected java.lang.String localCalle ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localCalleTracker = false ;
            

            /**
            * Auto generated getter method
            * @return java.lang.String
            */
            public  java.lang.String getCalle(){
                return localCalle;
            }

            
         
             /**
                * Auto generated setter method
                * @param param Calle
                */
                public void setCalle(java.lang.String param){
             
                        if (param != null){
                           //update the setting tracker
                           localCalleTracker = true;
                        } else {
                           localCalleTracker = true;
                               
                        }
                    
                             this.localCalle=param;
                     

                }
             

         /**
         * field for CodigoPostal
         */

         
                     protected java.lang.String localCodigoPostal ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localCodigoPostalTracker = false ;
            

            /**
            * Auto generated getter method
            * @return java.lang.String
            */
            public  java.lang.String getCodigoPostal(){
                return localCodigoPostal;
            }

            
         
             /**
                * Auto generated setter method
                * @param param CodigoPostal
                */
                public void setCodigoPostal(java.lang.String param){
             
                        if (param != null){
                           //update the setting tracker
                           localCodigoPostalTracker = true;
                        } else {
                           localCodigoPostalTracker = true;
                               
                        }
                    
                             this.localCodigoPostal=param;
                     

                }
             

         /**
         * field for FechaAlta
         */

         
                     protected java.util.Date localFechaAlta ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localFechaAltaTracker = false ;
            

            /**
            * Auto generated getter method
            * @return java.util.Date
            */
            public  java.util.Date getFechaAlta(){
                return localFechaAlta;
            }

            
         
             /**
                * Auto generated setter method
                * @param param FechaAlta
                */
                public void setFechaAlta(java.util.Date param){
             
                        if (param != null){
                           //update the setting tracker
                           localFechaAltaTracker = true;
                        } else {
                           localFechaAltaTracker = true;
                               
                        }
                    
                             this.localFechaAlta=param;
                     

                }
             

         /**
         * field for FechaVencimiento
         */

         
                     protected java.util.Date localFechaVencimiento ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localFechaVencimientoTracker = false ;
            

            /**
            * Auto generated getter method
            * @return java.util.Date
            */
            public  java.util.Date getFechaVencimiento(){
                return localFechaVencimiento;
            }

            
         
             /**
                * Auto generated setter method
                * @param param FechaVencimiento
                */
                public void setFechaVencimiento(java.util.Date param){
             
                        if (param != null){
                           //update the setting tracker
                           localFechaVencimientoTracker = true;
                        } else {
                           localFechaVencimientoTracker = true;
                               
                        }
                    
                             this.localFechaVencimiento=param;
                     

                }
             

         /**
         * field for Funciones
         */

         
                     protected java.lang.String localFunciones ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localFuncionesTracker = false ;
            

            /**
            * Auto generated getter method
            * @return java.lang.String
            */
            public  java.lang.String getFunciones(){
                return localFunciones;
            }

            
         
             /**
                * Auto generated setter method
                * @param param Funciones
                */
                public void setFunciones(java.lang.String param){
             
                        if (param != null){
                           //update the setting tracker
                           localFuncionesTracker = true;
                        } else {
                           localFuncionesTracker = true;
                               
                        }
                    
                             this.localFunciones=param;
                     

                }
             

         /**
         * field for Habilidad
         */

         
                     protected java.lang.String localHabilidad ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localHabilidadTracker = false ;
            

            /**
            * Auto generated getter method
            * @return java.lang.String
            */
            public  java.lang.String getHabilidad(){
                return localHabilidad;
            }

            
         
             /**
                * Auto generated setter method
                * @param param Habilidad
                */
                public void setHabilidad(java.lang.String param){
             
                        if (param != null){
                           //update the setting tracker
                           localHabilidadTracker = true;
                        } else {
                           localHabilidadTracker = true;
                               
                        }
                    
                             this.localHabilidad=param;
                     

                }
             

         /**
         * field for IdAreaLaboral
         */

         
                     protected int localIdAreaLaboral ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localIdAreaLaboralTracker = false ;
            

            /**
            * Auto generated getter method
            * @return int
            */
            public  int getIdAreaLaboral(){
                return localIdAreaLaboral;
            }

            
         
             /**
                * Auto generated setter method
                * @param param IdAreaLaboral
                */
                public void setIdAreaLaboral(int param){
             
                        // setting primitive attribute tracker to true
                        
                                if (param==java.lang.Integer.MIN_VALUE) {
                            localIdAreaLaboralTracker = true;
                               
                        } else {
                           localIdAreaLaboralTracker = true;
                        }
                    
                             this.localIdAreaLaboral=param;
                     

                }
             

         /**
         * field for IdEmpresa
         */

         
                     protected int localIdEmpresa ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localIdEmpresaTracker = false ;
            

            /**
            * Auto generated getter method
            * @return int
            */
            public  int getIdEmpresa(){
                return localIdEmpresa;
            }

            
         
             /**
                * Auto generated setter method
                * @param param IdEmpresa
                */
                public void setIdEmpresa(int param){
             
                        // setting primitive attribute tracker to true
                        
                                if (param==java.lang.Integer.MIN_VALUE) {
                            localIdEmpresaTracker = true;
                               
                        } else {
                           localIdEmpresaTracker = true;
                        }
                    
                             this.localIdEmpresa=param;
                     

                }
             

         /**
         * field for IdEntidad
         */

         
                     protected int localIdEntidad ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localIdEntidadTracker = false ;
            

            /**
            * Auto generated getter method
            * @return int
            */
            public  int getIdEntidad(){
                return localIdEntidad;
            }

            
         
             /**
                * Auto generated setter method
                * @param param IdEntidad
                */
                public void setIdEntidad(int param){
             
                        // setting primitive attribute tracker to true
                        
                                if (param==java.lang.Integer.MIN_VALUE) {
                            localIdEntidadTracker = true;
                               
                        } else {
                           localIdEntidadTracker = true;
                        }
                    
                             this.localIdEntidad=param;
                     

                }
             

         /**
         * field for IdEscolaridad
         */

         
                     protected int localIdEscolaridad ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localIdEscolaridadTracker = false ;
            

            /**
            * Auto generated getter method
            * @return int
            */
            public  int getIdEscolaridad(){
                return localIdEscolaridad;
            }

            
         
             /**
                * Auto generated setter method
                * @param param IdEscolaridad
                */
                public void setIdEscolaridad(int param){
             
                        // setting primitive attribute tracker to true
                        
                                if (param==java.lang.Integer.MIN_VALUE) {
                            localIdEscolaridadTracker = true;
                               
                        } else {
                           localIdEscolaridadTracker = true;
                        }
                    
                             this.localIdEscolaridad=param;
                     

                }
             

         /**
         * field for IdEspecialidad
         * This was an Array!
         */

         
                     protected int[] localIdEspecialidad ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localIdEspecialidadTracker = false ;
            

            /**
            * Auto generated getter method
            * @return int[]
            */
            public  int[] getIdEspecialidad(){
                return localIdEspecialidad;
            }

            
         


                
               /**
                * validate the array for IdEspecialidad
                */
               protected void validateIdEspecialidad(int[] param){
              
               }


              /**
               * Auto generated setter method
               * @param param IdEspecialidad
               */
               public void setIdEspecialidad(int[] param){
               
                    validateIdEspecialidad(param);

                
                           if (param != null){
                              //update the setting tracker
                              localIdEspecialidadTracker = true;
                           } else {
                              localIdEspecialidadTracker = true;
                                  
                           }
                       
                       this.localIdEspecialidad=param;
               }

                
              

         /**
         * field for IdExperiencia
         */

         
                     protected int localIdExperiencia ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localIdExperienciaTracker = false ;
            

            /**
            * Auto generated getter method
            * @return int
            */
            public  int getIdExperiencia(){
                return localIdExperiencia;
            }

            
         
             /**
                * Auto generated setter method
                * @param param IdExperiencia
                */
                public void setIdExperiencia(int param){
             
                        // setting primitive attribute tracker to true
                        
                                if (param==java.lang.Integer.MIN_VALUE) {
                            localIdExperienciaTracker = true;
                               
                        } else {
                           localIdExperienciaTracker = true;
                        }
                    
                             this.localIdExperiencia=param;
                     

                }
             

         /**
         * field for IdMunicipio
         */

         
                     protected int localIdMunicipio ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localIdMunicipioTracker = false ;
            

            /**
            * Auto generated getter method
            * @return int
            */
            public  int getIdMunicipio(){
                return localIdMunicipio;
            }

            
         
             /**
                * Auto generated setter method
                * @param param IdMunicipio
                */
                public void setIdMunicipio(int param){
             
                        // setting primitive attribute tracker to true
                        
                                if (param==java.lang.Integer.MIN_VALUE) {
                            localIdMunicipioTracker = true;
                               
                        } else {
                           localIdMunicipioTracker = true;
                        }
                    
                             this.localIdMunicipio=param;
                     

                }
             

         /**
         * field for IdOcupacion
         */

         
                     protected int localIdOcupacion ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localIdOcupacionTracker = false ;
            

            /**
            * Auto generated getter method
            * @return int
            */
            public  int getIdOcupacion(){
                return localIdOcupacion;
            }

            
         
             /**
                * Auto generated setter method
                * @param param IdOcupacion
                */
                public void setIdOcupacion(int param){
             
                        // setting primitive attribute tracker to true
                        
                                if (param==java.lang.Integer.MIN_VALUE) {
                            localIdOcupacionTracker = true;
                               
                        } else {
                           localIdOcupacionTracker = true;
                        }
                    
                             this.localIdOcupacion=param;
                     

                }
             

         /**
         * field for IdSituacion
         */

         
                     protected int localIdSituacion ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localIdSituacionTracker = false ;
            

            /**
            * Auto generated getter method
            * @return int
            */
            public  int getIdSituacion(){
                return localIdSituacion;
            }

            
         
             /**
                * Auto generated setter method
                * @param param IdSituacion
                */
                public void setIdSituacion(int param){
             
                        // setting primitive attribute tracker to true
                        
                                if (param==java.lang.Integer.MIN_VALUE) {
                            localIdSituacionTracker = true;
                               
                        } else {
                           localIdSituacionTracker = true;
                        }
                    
                             this.localIdSituacion=param;
                     

                }
             

         /**
         * field for IdVacante
         */

         
                     protected int localIdVacante ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localIdVacanteTracker = false ;
            

            /**
            * Auto generated getter method
            * @return int
            */
            public  int getIdVacante(){
                return localIdVacante;
            }

            
         
             /**
                * Auto generated setter method
                * @param param IdVacante
                */
                public void setIdVacante(int param){
             
                        // setting primitive attribute tracker to true
                        
                                if (param==java.lang.Integer.MIN_VALUE) {
                            localIdVacanteTracker = true;
                               
                        } else {
                           localIdVacanteTracker = true;
                        }
                    
                             this.localIdVacante=param;
                     

                }
             

         /**
         * field for NumeroExt
         */

         
                     protected java.lang.String localNumeroExt ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localNumeroExtTracker = false ;
            

            /**
            * Auto generated getter method
            * @return java.lang.String
            */
            public  java.lang.String getNumeroExt(){
                return localNumeroExt;
            }

            
         
             /**
                * Auto generated setter method
                * @param param NumeroExt
                */
                public void setNumeroExt(java.lang.String param){
             
                        if (param != null){
                           //update the setting tracker
                           localNumeroExtTracker = true;
                        } else {
                           localNumeroExtTracker = true;
                               
                        }
                    
                             this.localNumeroExt=param;
                     

                }
             

         /**
         * field for Observaciones
         */

         
                     protected java.lang.String localObservaciones ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localObservacionesTracker = false ;
            

            /**
            * Auto generated getter method
            * @return java.lang.String
            */
            public  java.lang.String getObservaciones(){
                return localObservaciones;
            }

            
         
             /**
                * Auto generated setter method
                * @param param Observaciones
                */
                public void setObservaciones(java.lang.String param){
             
                        if (param != null){
                           //update the setting tracker
                           localObservacionesTracker = true;
                        } else {
                           localObservacionesTracker = true;
                               
                        }
                    
                             this.localObservaciones=param;
                     

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
                           localPuestoTracker = true;
                               
                        }
                    
                             this.localPuesto=param;
                     

                }
             

         /**
         * field for Salario
         */

         
                     protected int localSalario ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localSalarioTracker = false ;
            

            /**
            * Auto generated getter method
            * @return int
            */
            public  int getSalario(){
                return localSalario;
            }

            
         
             /**
                * Auto generated setter method
                * @param param Salario
                */
                public void setSalario(int param){
             
                        // setting primitive attribute tracker to true
                        
                                if (param==java.lang.Integer.MIN_VALUE) {
                            localSalarioTracker = true;
                               
                        } else {
                           localSalarioTracker = true;
                        }
                    
                             this.localSalario=param;
                     

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


    java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://stps.ws.sfp.gob.mx/xsd");
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
 if (localCalleTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"calle", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"calle");
                         }

                     } else {
                         xmlWriter.writeStartElement("calle");
                     }
                 

                           if (localCalle==null){
                               // write the nil attribute
                               
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                   
                           }else{

                         
                                    xmlWriter.writeCharacters(localCalle);
                             
                           }
                     
                    xmlWriter.writeEndElement();
              } if (localCodigoPostalTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"codigoPostal", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"codigoPostal");
                         }

                     } else {
                         xmlWriter.writeStartElement("codigoPostal");
                     }
                 

                           if (localCodigoPostal==null){
                               // write the nil attribute
                               
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                   
                           }else{

                         
                                    xmlWriter.writeCharacters(localCodigoPostal);
                             
                           }
                     
                    xmlWriter.writeEndElement();
              } if (localFechaAltaTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"fechaAlta", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"fechaAlta");
                         }

                     } else {
                         xmlWriter.writeStartElement("fechaAlta");
                     }
                 

                           if (localFechaAlta==null){
                               // write the nil attribute
                               
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                   
                           }else{

                         
                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaAlta));
                             
                           }
                     
                    xmlWriter.writeEndElement();
              } if (localFechaVencimientoTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"fechaVencimiento", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"fechaVencimiento");
                         }

                     } else {
                         xmlWriter.writeStartElement("fechaVencimiento");
                     }
                 

                           if (localFechaVencimiento==null){
                               // write the nil attribute
                               
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                   
                           }else{

                         
                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaVencimiento));
                             
                           }
                     
                    xmlWriter.writeEndElement();
              } if (localFuncionesTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"funciones", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"funciones");
                         }

                     } else {
                         xmlWriter.writeStartElement("funciones");
                     }
                 

                           if (localFunciones==null){
                               // write the nil attribute
                               
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                   
                           }else{

                         
                                    xmlWriter.writeCharacters(localFunciones);
                             
                           }
                     
                    xmlWriter.writeEndElement();
              } if (localHabilidadTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"habilidad", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"habilidad");
                         }

                     } else {
                         xmlWriter.writeStartElement("habilidad");
                     }
                 

                           if (localHabilidad==null){
                               // write the nil attribute
                               
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                   
                           }else{

                         
                                    xmlWriter.writeCharacters(localHabilidad);
                             
                           }
                     
                    xmlWriter.writeEndElement();
              } if (localIdAreaLaboralTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"idAreaLaboral", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"idAreaLaboral");
                         }

                     } else {
                         xmlWriter.writeStartElement("idAreaLaboral");
                     }
                 
                                if (localIdAreaLaboral==java.lang.Integer.MIN_VALUE) {
                            
                                          writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                       
                                } else {
                                     xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdAreaLaboral));
                                }
                     
                    xmlWriter.writeEndElement();
              } if (localIdEmpresaTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"idEmpresa", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"idEmpresa");
                         }

                     } else {
                         xmlWriter.writeStartElement("idEmpresa");
                     }
                 
                                if (localIdEmpresa==java.lang.Integer.MIN_VALUE) {
                            
                                          writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                       
                                } else {
                                     xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdEmpresa));
                                }
                     
                    xmlWriter.writeEndElement();
              } if (localIdEntidadTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"idEntidad", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"idEntidad");
                         }

                     } else {
                         xmlWriter.writeStartElement("idEntidad");
                     }
                 
                                if (localIdEntidad==java.lang.Integer.MIN_VALUE) {
                            
                                          writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                       
                                } else {
                                     xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdEntidad));
                                }
                     
                    xmlWriter.writeEndElement();
              } if (localIdEscolaridadTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"idEscolaridad", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"idEscolaridad");
                         }

                     } else {
                         xmlWriter.writeStartElement("idEscolaridad");
                     }
                 
                                if (localIdEscolaridad==java.lang.Integer.MIN_VALUE) {
                            
                                          writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                       
                                } else {
                                     xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdEscolaridad));
                                }
                     
                    xmlWriter.writeEndElement();
              } if (localIdEspecialidadTracker){
              if (localIdEspecialidad!=null) {
                    namespace = "http://stps.ws.sfp.gob.mx/xsd";
                    boolean emptyNamespace = namespace == null || namespace.length() == 0;
                    prefix =  emptyNamespace ? null : xmlWriter.getPrefix(namespace);
                    for (int i = 0;i < localIdEspecialidad.length;i++){
                         
                                    if (localIdEspecialidad[i]!=java.lang.Integer.MIN_VALUE) {
                                
                                 if (!emptyNamespace) {
                                     if (prefix == null) {
                                         java.lang.String prefix2 = generatePrefix(namespace);

                                         xmlWriter.writeStartElement(prefix2,"idEspecialidad", namespace);
                                         xmlWriter.writeNamespace(prefix2, namespace);
                                         xmlWriter.setPrefix(prefix2, namespace);

                                     } else {
                                         xmlWriter.writeStartElement(namespace,"idEspecialidad");
                                     }

                                 } else {
                                     xmlWriter.writeStartElement("idEspecialidad");
                                 }

                             
                                 xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdEspecialidad[i]));
                                 xmlWriter.writeEndElement();
                             
                                 } else {
                                    
                                            // write null attribute
                                             namespace = "http://stps.ws.sfp.gob.mx/xsd";
                                             if (! namespace.equals("")) {
                                                 prefix = xmlWriter.getPrefix(namespace);

                                                 if (prefix == null) {
                                                     prefix = generatePrefix(namespace);

                                                     xmlWriter.writeStartElement(prefix,"idEspecialidad", namespace);
                                                     xmlWriter.writeNamespace(prefix, namespace);
                                                     xmlWriter.setPrefix(prefix, namespace);

                                                 } else {
                                                     xmlWriter.writeStartElement(namespace,"idEspecialidad");
                                                 }

                                             } else {
                                                 xmlWriter.writeStartElement("idEspecialidad");
                                             }
                                             writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                             xmlWriter.writeEndElement();
                                        
                                 }

                    }
              } else {
                  
                          // write the null attribute
                         // write null attribute
                             java.lang.String namespace2 = "http://stps.ws.sfp.gob.mx/xsd";
                             if (! namespace2.equals("")) {
                                 java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                 if (prefix2 == null) {
                                     prefix2 = generatePrefix(namespace2);

                                     xmlWriter.writeStartElement(prefix2,"idEspecialidad", namespace2);
                                     xmlWriter.writeNamespace(prefix2, namespace2);
                                     xmlWriter.setPrefix(prefix2, namespace2);

                                 } else {
                                     xmlWriter.writeStartElement(namespace2,"idEspecialidad");
                                 }

                             } else {
                                 xmlWriter.writeStartElement("idEspecialidad");
                             }

                            // write the nil attribute
                            writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                            xmlWriter.writeEndElement();
                     
              }

         } if (localIdExperienciaTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"idExperiencia", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"idExperiencia");
                         }

                     } else {
                         xmlWriter.writeStartElement("idExperiencia");
                     }
                 
                                if (localIdExperiencia==java.lang.Integer.MIN_VALUE) {
                            
                                          writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                       
                                } else {
                                     xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdExperiencia));
                                }
                     
                    xmlWriter.writeEndElement();
              } if (localIdMunicipioTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"idMunicipio", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"idMunicipio");
                         }

                     } else {
                         xmlWriter.writeStartElement("idMunicipio");
                     }
                 
                                if (localIdMunicipio==java.lang.Integer.MIN_VALUE) {
                            
                                          writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                       
                                } else {
                                     xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdMunicipio));
                                }
                     
                    xmlWriter.writeEndElement();
              } if (localIdOcupacionTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"idOcupacion", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"idOcupacion");
                         }

                     } else {
                         xmlWriter.writeStartElement("idOcupacion");
                     }
                 
                                if (localIdOcupacion==java.lang.Integer.MIN_VALUE) {
                            
                                          writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                       
                                } else {
                                     xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdOcupacion));
                                }
                     
                    xmlWriter.writeEndElement();
              } if (localIdSituacionTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"idSituacion", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"idSituacion");
                         }

                     } else {
                         xmlWriter.writeStartElement("idSituacion");
                     }
                 
                                if (localIdSituacion==java.lang.Integer.MIN_VALUE) {
                            
                                          writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                       
                                } else {
                                     xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdSituacion));
                                }
                     
                    xmlWriter.writeEndElement();
              } if (localIdVacanteTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"idVacante", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"idVacante");
                         }

                     } else {
                         xmlWriter.writeStartElement("idVacante");
                     }
                 
                                if (localIdVacante==java.lang.Integer.MIN_VALUE) {
                            
                                          writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                       
                                } else {
                                     xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdVacante));
                                }
                     
                    xmlWriter.writeEndElement();
              } if (localNumeroExtTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"numeroExt", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"numeroExt");
                         }

                     } else {
                         xmlWriter.writeStartElement("numeroExt");
                     }
                 

                           if (localNumeroExt==null){
                               // write the nil attribute
                               
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                   
                           }else{

                         
                                    xmlWriter.writeCharacters(localNumeroExt);
                             
                           }
                     
                    xmlWriter.writeEndElement();
              } if (localObservacionesTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"observaciones", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"observaciones");
                         }

                     } else {
                         xmlWriter.writeStartElement("observaciones");
                     }
                 

                           if (localObservaciones==null){
                               // write the nil attribute
                               
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                   
                           }else{

                         
                                    xmlWriter.writeCharacters(localObservaciones);
                             
                           }
                     
                    xmlWriter.writeEndElement();
              } if (localPuestoTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"puesto", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"puesto");
                         }

                     } else {
                         xmlWriter.writeStartElement("puesto");
                     }
                 

                           if (localPuesto==null){
                               // write the nil attribute
                               
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                   
                           }else{

                         
                                    xmlWriter.writeCharacters(localPuesto);
                             
                           }
                     
                    xmlWriter.writeEndElement();
              } if (localSalarioTracker){
                     namespace = "http://stps.ws.sfp.gob.mx/xsd";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"salario", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"salario");
                         }

                     } else {
                         xmlWriter.writeStartElement("salario");
                     }
                 
                                if (localSalario==java.lang.Integer.MIN_VALUE) {
                            
                                          writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                       
                                } else {
                                     xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSalario));
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

  if (localCalleTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "calle"));
                  
                          elementList.add(localCalle==null?null:
                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCalle));
                     } if (localCodigoPostalTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "codigoPostal"));
                  
                          elementList.add(localCodigoPostal==null?null:
                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodigoPostal));
                     } if (localFechaAltaTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "fechaAlta"));
                  
                          elementList.add(localFechaAlta==null?null:
                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaAlta));
                     } if (localFechaVencimientoTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "fechaVencimiento"));
                  
                          elementList.add(localFechaVencimiento==null?null:
                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFechaVencimiento));
                     } if (localFuncionesTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "funciones"));
                  
                          elementList.add(localFunciones==null?null:
                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFunciones));
                     } if (localHabilidadTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "habilidad"));
                  
                          elementList.add(localHabilidad==null?null:
                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHabilidad));
                     } if (localIdAreaLaboralTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "idAreaLaboral"));
                  
                 elementList.add(
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdAreaLaboral));
             } if (localIdEmpresaTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "idEmpresa"));
                  
                 elementList.add(
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdEmpresa));
             } if (localIdEntidadTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "idEntidad"));
                  
                 elementList.add(
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdEntidad));
             } if (localIdEscolaridadTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "idEscolaridad"));
                  
                 elementList.add(
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdEscolaridad));
             } if (localIdEspecialidadTracker){
             if (localIdEspecialidad!=null){
                   for (int i = 0;i < localIdEspecialidad.length;i++){
                       
                           elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                                                                        "idEspecialidad"));
                           elementList.add(
                           org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdEspecialidad[i]));

                       

                   }
             } else {
               
                     elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                               "idEspecialidad"));
                     elementList.add(null);
                 
             }

         } if (localIdExperienciaTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "idExperiencia"));
                  
                 elementList.add(
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdExperiencia));
             } if (localIdMunicipioTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "idMunicipio"));
                  
                 elementList.add(
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdMunicipio));
             } if (localIdOcupacionTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "idOcupacion"));
                  
                 elementList.add(
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdOcupacion));
             } if (localIdSituacionTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "idSituacion"));
                  
                 elementList.add(
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdSituacion));
             } if (localIdVacanteTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "idVacante"));
                  
                 elementList.add(
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdVacante));
             } if (localNumeroExtTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "numeroExt"));
                  
                          elementList.add(localNumeroExt==null?null:
                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNumeroExt));
                     } if (localObservacionesTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "observaciones"));
                  
                          elementList.add(localObservaciones==null?null:
                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localObservaciones));
                     } if (localPuestoTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "puesto"));
                  
                          elementList.add(localPuesto==null?null:
                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPuesto));
                     } if (localSalarioTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd",
                                                       "salario"));
                  
                 elementList.add(
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSalario));
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
 
         java.util.ArrayList list11 = new java.util.ArrayList();
     
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","calle").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setCalle(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                             
                        } else {
                            
                            
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","codigoPostal").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setCodigoPostal(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                             
                        } else {
                            
                            
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","fechaAlta").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setFechaAlta(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToDate(content));
                             
                        } else {
                            
                            
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","fechaVencimiento").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setFechaVencimiento(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToDate(content));
                             
                        } else {
                            
                            
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","funciones").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setFunciones(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                             
                        } else {
                            
                            
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","habilidad").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setHabilidad(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                             
                        } else {
                            
                            
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","idAreaLaboral").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setIdAreaLaboral(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                             
                        } else {
                            
                            
                                    object.setIdAreaLaboral(java.lang.Integer.MIN_VALUE);
                                
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                                object.setIdAreaLaboral(java.lang.Integer.MIN_VALUE);
                            
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","idEmpresa").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setIdEmpresa(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                             
                        } else {
                            
                            
                                    object.setIdEmpresa(java.lang.Integer.MIN_VALUE);
                                
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                                object.setIdEmpresa(java.lang.Integer.MIN_VALUE);
                            
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","idEntidad").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setIdEntidad(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                             
                        } else {
                            
                            
                                    object.setIdEntidad(java.lang.Integer.MIN_VALUE);
                                
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                                object.setIdEntidad(java.lang.Integer.MIN_VALUE);
                            
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","idEscolaridad").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setIdEscolaridad(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                             
                        } else {
                            
                            
                                    object.setIdEscolaridad(java.lang.Integer.MIN_VALUE);
                                
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                                object.setIdEscolaridad(java.lang.Integer.MIN_VALUE);
                            
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","idEspecialidad").equals(reader.getName())){
                 
                     
                     
                     // Process the array and step past its final element's end.
                     
                               nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                               if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                   list11.add(String.valueOf(java.lang.Integer.MIN_VALUE));
                                        
                                   reader.next();
                               } else {
                             list11.add(reader.getElementText());
                             }
                             //loop until we find a start element that is not part of this array
                             boolean loopDone11 = false;
                             while(!loopDone11){
                                 // Ensure we are at the EndElement
                                 while (!reader.isEndElement()){
                                     reader.next();
                                 }
                                 // Step out of this element
                                 reader.next();
                                 // Step to next element event.
                                 while (!reader.isStartElement() && !reader.isEndElement())
                                     reader.next();
                                 if (reader.isEndElement()){
                                     //two continuous end elements means we are exiting the xml structure
                                     loopDone11 = true;
                                 } else {
                                     if (new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","idEspecialidad").equals(reader.getName())){
                                          
                                           nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                           if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                               list11.add(String.valueOf(java.lang.Integer.MIN_VALUE));
                                                    
                                               reader.next();
                                           } else {
                                         list11.add(reader.getElementText());
                                         }
                                     }else{
                                         loopDone11 = true;
                                     }
                                 }
                             }
                             // call the converter utility  to convert and set the array
                             
                             object.setIdEspecialidad((int[])
                                 org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                             int.class,list11));
                                 
               }  // End of if for expected property start element
                 
                     else {
                         
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","idExperiencia").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setIdExperiencia(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                             
                        } else {
                            
                            
                                    object.setIdExperiencia(java.lang.Integer.MIN_VALUE);
                                
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                                object.setIdExperiencia(java.lang.Integer.MIN_VALUE);
                            
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","idMunicipio").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setIdMunicipio(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                             
                        } else {
                            
                            
                                    object.setIdMunicipio(java.lang.Integer.MIN_VALUE);
                                
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                                object.setIdMunicipio(java.lang.Integer.MIN_VALUE);
                            
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","idOcupacion").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setIdOcupacion(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                             
                        } else {
                            
                            
                                    object.setIdOcupacion(java.lang.Integer.MIN_VALUE);
                                
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                                object.setIdOcupacion(java.lang.Integer.MIN_VALUE);
                            
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","idSituacion").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setIdSituacion(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                             
                        } else {
                            
                            
                                    object.setIdSituacion(java.lang.Integer.MIN_VALUE);
                                
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                                object.setIdSituacion(java.lang.Integer.MIN_VALUE);
                            
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","idVacante").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setIdVacante(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                             
                        } else {
                            
                            
                                    object.setIdVacante(java.lang.Integer.MIN_VALUE);
                                
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                                object.setIdVacante(java.lang.Integer.MIN_VALUE);
                            
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","numeroExt").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setNumeroExt(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                             
                        } else {
                            
                            
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","observaciones").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setObservaciones(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                             
                        } else {
                            
                            
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","puesto").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setPuesto(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                             
                        } else {
                            
                            
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                     }
                 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx/xsd","salario").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setSalario(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                             
                        } else {
                            
                            
                                    object.setSalario(java.lang.Integer.MIN_VALUE);
                                
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
                         reader.next();
                     
               }  // End of if for expected property start element
                 
                     else {
                         
                                object.setSalario(java.lang.Integer.MIN_VALUE);
                            
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
   "http://stps.ws.sfp.gob.mx/xsd".equals(namespaceURI) &&
   "Vacante".equals(typeName)){
    
             return  Vacante.Factory.parse(reader);
         

   }


throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
}

}

public static class PreparaStageVacantes
implements org.apache.axis2.databinding.ADBBean{

 public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
 "http://stps.ws.sfp.gob.mx",
 "preparaStageVacantes",
 "ns2");



private static java.lang.String generatePrefix(java.lang.String namespace) {
if(namespace.equals("http://stps.ws.sfp.gob.mx")){
 return "ns2";
}
return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
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
        PreparaStageVacantes.this.serialize(MY_QNAME,factory,xmlWriter);
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


    java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://stps.ws.sfp.gob.mx");
    if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
        writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
            namespacePrefix+":preparaStageVacantes",
            xmlWriter);
    } else {
        writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
            "preparaStageVacantes",
            xmlWriter);
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
public static PreparaStageVacantes parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
PreparaStageVacantes object =
 new PreparaStageVacantes();

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
     
             if (!"preparaStageVacantes".equals(type)){
                 //find namespace for the prefix
                 java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                 return (PreparaStageVacantes)ExtensionMapper.getTypeObject(
                      nsUri,type,reader);
               }
         

   }
 

 }

 

 
 // Note all attributes that were handled. Used to differ normal attributes
 // from anyAttributes.
 java.util.Vector handledAttributes = new java.util.Vector();
 

  
     
     reader.next();
   
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


public static class ListaVacantesResponse
implements org.apache.axis2.databinding.ADBBean{

 public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
 "http://stps.ws.sfp.gob.mx",
 "listaVacantesResponse",
 "ns2");



private static java.lang.String generatePrefix(java.lang.String namespace) {
if(namespace.equals("http://stps.ws.sfp.gob.mx")){
 return "ns2";
}
return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
}



         /**
         * field for _return
         * This was an Array!
         */

         
                     protected Vacante[] local_return ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean local_returnTracker = false ;
            

            /**
            * Auto generated getter method
            * @return Vacante[]
            */
            public  Vacante[] get_return(){
                return local_return;
            }

            
         


                
               /**
                * validate the array for _return
                */
               protected void validate_return(Vacante[] param){
              
               }


              /**
               * Auto generated setter method
               * @param param _return
               */
               public void set_return(Vacante[] param){
               
                    validate_return(param);

                
                           if (param != null){
                              //update the setting tracker
                              local_returnTracker = true;
                           } else {
                              local_returnTracker = true;
                                  
                           }
                       
                       this.local_return=param;
               }

                
              
              /**
              * Auto generated add method for the array for convenience
              * @param param Vacante
              */
              public void add_return(Vacante param){
                    if (local_return == null){
                    local_return = new Vacante[]{};
                    }

             
                  //update the setting tracker
                 local_returnTracker = true;
             

                java.util.List list =
             org.apache.axis2.databinding.utils.ConverterUtil.toList(local_return);
                list.add(param);
                this.local_return =
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
        new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME){

  public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
        ListaVacantesResponse.this.serialize(MY_QNAME,factory,xmlWriter);
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


    java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://stps.ws.sfp.gob.mx");
    if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
        writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
            namespacePrefix+":listaVacantesResponse",
            xmlWriter);
    } else {
        writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
            "listaVacantesResponse",
            xmlWriter);
    }


    }
 if (local_returnTracker){
                        if (local_return!=null){
                             for (int i = 0;i < local_return.length;i++){
                                 if (local_return[i] != null){
                                  local_return[i].serialize(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx","return"),
                                            factory,xmlWriter);
                                 } else {
                                    
                                             // write null attribute
                                             java.lang.String namespace2 = "http://stps.ws.sfp.gob.mx";
                                             if (! namespace2.equals("")) {
                                                 java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                 if (prefix2 == null) {
                                                     prefix2 = generatePrefix(namespace2);

                                                     xmlWriter.writeStartElement(prefix2,"return", namespace2);
                                                     xmlWriter.writeNamespace(prefix2, namespace2);
                                                     xmlWriter.setPrefix(prefix2, namespace2);

                                                 } else {
                                                     xmlWriter.writeStartElement(namespace2,"return");
                                                 }

                                             } else {
                                                 xmlWriter.writeStartElement("return");
                                             }

                                            // write the nil attribute
                                            writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                            xmlWriter.writeEndElement();
                                     
                                 }

                             }
                      } else {
                         
                                 // write null attribute
                                 java.lang.String namespace2 = "http://stps.ws.sfp.gob.mx";
                                 if (! namespace2.equals("")) {
                                     java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                     if (prefix2 == null) {
                                         prefix2 = generatePrefix(namespace2);

                                         xmlWriter.writeStartElement(prefix2,"return", namespace2);
                                         xmlWriter.writeNamespace(prefix2, namespace2);
                                         xmlWriter.setPrefix(prefix2, namespace2);

                                     } else {
                                         xmlWriter.writeStartElement(namespace2,"return");
                                     }

                                 } else {
                                     xmlWriter.writeStartElement("return");
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

  if (local_returnTracker){
              if (local_return!=null) {
                  for (int i = 0;i < local_return.length;i++){

                     if (local_return[i] != null){
                          elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx",
                                                           "return"));
                          elementList.add(local_return[i]);
                     } else {
                         
                                 elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx",
                                                           "return"));
                                 elementList.add(null);
                             
                     }

                  }
              } else {
                  
                         elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx",
                                                           "return"));
                         elementList.add(local_return);
                     
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
public static ListaVacantesResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
ListaVacantesResponse object =
 new ListaVacantesResponse();

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
     
             if (!"listaVacantesResponse".equals(type)){
                 //find namespace for the prefix
                 java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                 return (ListaVacantesResponse)ExtensionMapper.getTypeObject(
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
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx","return").equals(reader.getName())){
                 
                     
                     
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
                                                 if (new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx","return").equals(reader.getName())){
                                                     
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
                                         
                                         object.set_return((Vacante[])
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


public static class ListaVacantes
implements org.apache.axis2.databinding.ADBBean{

 public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
 "http://stps.ws.sfp.gob.mx",
 "listaVacantes",
 "ns2");



private static java.lang.String generatePrefix(java.lang.String namespace) {
if(namespace.equals("http://stps.ws.sfp.gob.mx")){
 return "ns2";
}
return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
}



         /**
         * field for Fecha
         */

         
                     protected java.lang.String localFecha ;
                 
            /*  This tracker boolean wil be used to detect whether the user called the set method
           *   for this attribute. It will be used to determine whether to include this field
            *   in the serialized XML
            */
            protected boolean localFechaTracker = false ;
            

            /**
            * Auto generated getter method
            * @return java.lang.String
            */
            public  java.lang.String getFecha(){
                return localFecha;
            }

            
         
             /**
                * Auto generated setter method
                * @param param Fecha
                */
                public void setFecha(java.lang.String param){
             
                        if (param != null){
                           //update the setting tracker
                           localFechaTracker = true;
                        } else {
                           localFechaTracker = true;
                               
                        }
                    
                             this.localFecha=param;
                     

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
        ListaVacantes.this.serialize(MY_QNAME,factory,xmlWriter);
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


    java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://stps.ws.sfp.gob.mx");
    if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
        writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
            namespacePrefix+":listaVacantes",
            xmlWriter);
    } else {
        writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
            "listaVacantes",
            xmlWriter);
    }


    }
 if (localFechaTracker){
                     namespace = "http://stps.ws.sfp.gob.mx";
                     if (! namespace.equals("")) {
                         prefix = xmlWriter.getPrefix(namespace);

                         if (prefix == null) {
                             prefix = generatePrefix(namespace);

                             xmlWriter.writeStartElement(prefix,"fecha", namespace);
                             xmlWriter.writeNamespace(prefix, namespace);
                             xmlWriter.setPrefix(prefix, namespace);

                         } else {
                             xmlWriter.writeStartElement(namespace,"fecha");
                         }

                     } else {
                         xmlWriter.writeStartElement("fecha");
                     }
                 

                           if (localFecha==null){
                               // write the nil attribute
                               
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                   
                           }else{

                         
                                    xmlWriter.writeCharacters(localFecha);
                             
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

  if (localFechaTracker){
                       elementList.add(new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx",
                                                       "fecha"));
                  
                          elementList.add(localFecha==null?null:
                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFecha));
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
public static ListaVacantes parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
ListaVacantes object =
 new ListaVacantes();

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
     
             if (!"listaVacantes".equals(type)){
                 //find namespace for the prefix
                 java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                 return (ListaVacantes)ExtensionMapper.getTypeObject(
                      nsUri,type,reader);
               }
         

   }
 

 }

 

 
 // Note all attributes that were handled. Used to differ normal attributes
 // from anyAttributes.
 java.util.Vector handledAttributes = new java.util.Vector();
 

  
     
     reader.next();
 
                     
                     while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                 
                     if (reader.isStartElement() && new javax.xml.namespace.QName("http://stps.ws.sfp.gob.mx","fecha").equals(reader.getName())){
                 
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                     
                     java.lang.String content = reader.getElementText();
                     
                               object.setFecha(
                                     org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                             
                        } else {
                            
                            
                            reader.getElementText(); // throw away text nodes if any.
                        }
                       
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


private  org.apache.axiom.om.OMElement  toOM(mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.PreparaStageVacantes param, boolean optimizeContent)
throws org.apache.axis2.AxisFault {


         try{
              return param.getOMElement(mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.PreparaStageVacantes.MY_QNAME,
                           org.apache.axiom.om.OMAbstractFactory.getOMFactory());
         } catch(org.apache.axis2.databinding.ADBException e){
             throw org.apache.axis2.AxisFault.makeFault(e);
         }
     

}

private  org.apache.axiom.om.OMElement  toOM(mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantes param, boolean optimizeContent)
throws org.apache.axis2.AxisFault {


         try{
              return param.getOMElement(mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantes.MY_QNAME,
                           org.apache.axiom.om.OMAbstractFactory.getOMFactory());
         } catch(org.apache.axis2.databinding.ADBException e){
             throw org.apache.axis2.AxisFault.makeFault(e);
         }
     

}

	private  org.apache.axiom.om.OMElement  toOM(mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantesResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {
         try{
              return param.getOMElement(mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantesResponse.MY_QNAME,
                           org.apache.axiom.om.OMAbstractFactory.getOMFactory());
         } catch(org.apache.axis2.databinding.ADBException e){
             throw org.apache.axis2.AxisFault.makeFault(e);
         }
	}

                     
     private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.PreparaStageVacantes param, boolean optimizeContent)
    		 throws org.apache.axis2.AxisFault{
         try{
             org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
             emptyEnvelope.getBody().addChild(param.getOMElement(mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.PreparaStageVacantes.MY_QNAME,factory));
             return emptyEnvelope;
         } catch(org.apache.axis2.databinding.ADBException e){
             throw org.apache.axis2.AxisFault.makeFault(e);
         }
     }
                 
              
	/* methods to provide back word compatibility */
    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantes param, boolean optimizeContent)
    		throws org.apache.axis2.AxisFault{
	     try{
             org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
             emptyEnvelope.getBody().addChild(param.getOMElement(mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantes.MY_QNAME,factory));
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
		
		 if (mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.PreparaStageVacantes.class.equals(type)){		 
		            return mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.PreparaStageVacantes.Factory.parse(param.getXMLStreamReaderWithoutCaching());
		 }
		
		 if (mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantes.class.equals(type)){		 
		            return mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantes.Factory.parse(param.getXMLStreamReaderWithoutCaching());
		 }
		
		 if (mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantesResponse.class.equals(type)){		 
		            return mx.gob.stps.portal.core.ws.ofertas.sfp.SFPServiceStub.ListaVacantesResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
		 }
		
		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}  
  
	

	
}
