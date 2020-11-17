package mx.gob.stps.portal.web.company.form;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.address.form.DomicilioForm;
import mx.gob.stps.portal.web.infra.utils.Utils;
import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static mx.gob.stps.portal.web.infra.utils.Constantes.LST_TELEFONOS_ADICIONALES;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

//TODO ELIMINAR CLASE YA NO SE USA
public class ComRegPartnerCompanyFormv2 extends DomicilioForm implements Serializable{

	private static Logger logger = Logger.getLogger(ComRegPartnerCompanyFormv2.class);
	//inicializar propiedades
	private long idTerceraEmpresa;
	private long idEmpresa;
	private String rfc;	
	private long idTipoPersona;	
	//datos exclusivos del tipo de persona fisica
	private String nombre;
	private String apellido1;
	private String apellido2;
	//datos exclusivos del tipo de persona moral
	private String razonSocial;
	//datos para cualquier tipo de persona
	private String descripcion;	
	private String contactoEmpresa;
	private long idTipoEmpresa;
	private long idActividadEconomica;
	private int numeroEmpleados;
	private int estatus;
	private Date fechaUltimaActualizacion;
	private String correoElectronico;
	private String confirmarCorreoElectronico;
	private List<TelefonoVO> lstTelefonos = new ArrayList<TelefonoVO>();
	//Datos para el telefono principal
	private int idTipoTelefono;
	private String acceso;
	private String clave;
	private String telefono;
	private String extension;
	//Domicilio
	private DomicilioVO domicilio = new DomicilioVO();
	private long idEntidad;
	private long idMunicipio;
	private long idColonia;
	private String calle;
	private String numeroInterior;
	private String numeroExterior;
	private String entreCalle;
	private String yCalle;
	private String codigoPostal;	
	//Terceras Empresas Registradas
	//private List<TerceraEmpresaVO> lstTercerasEmpresas = new ArrayList<TerceraEmpresaVO>();
	private long selectPartnerCompany = -1;
	//DATOS DE EMPRESA PADRE
	private String padreIdPortalEmpleo;
	private long padreIdTipoPersona;
	private String padreNombre;
	private String padreApellido1;
	private String padreApellido2;
	private String padreRazonSocial;
	private String padreContactoEmpresa;
	private String padreLeyendaNombre;
	//Hidden Cbos
	private String hiddenDesTipoEmpresa;
	private String hiddenDesActividadEconomica;
	private String nombreComercial;
	private String preCorreoElectronico;
	private long idColoniaSelected;
	private String idColoniaSelectedText;	
	
	public void reset() {
		rfc = null;	
		idTipoPersona = -1;	
		nombre = null;
		apellido1 = null;
		apellido2 = null;
		razonSocial = null;
		descripcion = null;	
		nombreComercial = null;	
		contactoEmpresa = null;
		idTipoEmpresa = -1;
		idActividadEconomica = -1;
		numeroEmpleados = 0;
		correoElectronico = null;
		confirmarCorreoElectronico = null;
		idTipoTelefono = 0;
		acceso = null;
		clave = null;
		telefono = null;
		extension = null;
		codigoPostal = null;	
		calle = null;
		numeroInterior = null;
		numeroExterior = null;
		entreCalle = null;
		yCalle = null;
		idTerceraEmpresa = 0;		
	}	
	
	/**
	 * Method 'validate'
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */		
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		String method = request.getParameter("method");
		if(method.equalsIgnoreCase("salvar")){			
			if (rfc.length()>0){
				String strPatron = "^[A-Za-z\\s\\ñÑ0-9]{0,13}$";				
				if(!Utils.validaPatron(strPatron,rfc)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "El RFC no es un dato válido."));
			    }	
			}									
			if (idTipoPersona<=0) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Tipo de persona es un dato que"));
			}				
			if (idTipoPersona==1) {
				//		tipo de persona fisica
				if (nombre==null || nombre.length()<1){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Nombre es un dato que"));
				} else{
				    if(!Utils.esAlfabetico(nombre)){
				    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Nombre no es un dato válido."));
				    }				
				}
				if (apellido1==null || apellido1.length()<1){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Primer Apellido es un dato que"));
				} else{
				    if(!Utils.esAlfabetico(apellido1)){
				    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Primer Apellido no es un dato válido."));
				    }								
				}
			} else {
				//		tipo de persona moral
				if (razonSocial==null || razonSocial.length()<1){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Razón social es un dato que"));
				} else {
				    if(!Utils.esAlfanumericoAmpersand(razonSocial)){
				    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La Razón social no es un dato válido."));
				    }								
				}	
			}			
			if(getCodigoPostal()==null || getCodigoPostal().length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El código postal es un dato que"));
			} else {
			    if(!Utils.esNumero(getCodigoPostal())){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El código postal no es un dato válido."));
			    }											
			}
			if(getIdEntidad()<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La entidad federativa es un dato que"));
			}
			if(getIdMunicipio()<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La delegación o municipio es un dato que"));
			}
			if(getIdColonia()<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La colonia es un dato que"));
			}
			if (getNumeroExterior()==null || getNumeroExterior().length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El número exterior es un dato que"));
			} else {
			    if(!Utils.esAlfanumerico(getNumeroExterior())){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El número exterior no es un dato válido."));
			    }							
			}			
			if (getNumeroInterior()!=null && getNumeroInterior().length()>0 && !Utils.esAlfanumerico(getNumeroInterior())){
		    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El número interior no es un dato válido."));
			}			
			if (getCalle()==null || getCalle().length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La calle es un dato que"));
			} else {
			    if(!Utils.esAlfanumerico(getCalle())){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La calle no es un dato válido."));
			    }							
			}				
			if (getEntreCalle()==null || getEntreCalle().length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Entre calle es un dato que"));
			} else {
			    if(!Utils.esAlfanumerico(getEntreCalle())){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "Entre calle no es un dato válido."));
			    }							
			}	
			if (getyCalle()==null || getyCalle().length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Y calle es un dato que"));
			} else {
			    if(!Utils.esAlfanumerico(getyCalle())){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "Y calle no es un dato válido."));
			    }							
			}					
			System.out.println("----FORMA DOMICILIO  calle:" + getCalle() + " CP:" + getCodigoPostal() +
					" entre calle:" +  getEntreCalle() +  " idColonia:" +  getIdColonia() + 
					" idEntidad:" + getIdEntidad() + " idMunicipio:" +  getIdMunicipio() +
					" numeroExterior:" +  getNumeroExterior() + " numeroInterior:" +  getNumeroInterior() +
					" yCalle:" +  getyCalle());			
			if (errors.isEmpty()){
				domicilio.setCalle(getCalle());
				domicilio.setCodigoPostal(getCodigoPostal());
				domicilio.setEntreCalle(getEntreCalle());
				domicilio.setIdColonia(getIdColonia());
				domicilio.setIdEntidad(getIdEntidad());
				domicilio.setIdMunicipio(getIdMunicipio());
				domicilio.setNumeroExterior(getNumeroExterior());
				domicilio.setNumeroInterior(getNumeroInterior());
				domicilio.setyCalle(getyCalle());		
				System.out.println("----DOMICILIO  calle:" + domicilio.getCalle() + " CP:" + domicilio.getCodigoPostal() +
						" entre calle:" +  domicilio.getEntreCalle() +  " idColonia:" +  domicilio.getIdColonia() + 
						" idEntidad:" + domicilio.getIdEntidad() + " idMunicipio:" +  domicilio.getIdMunicipio() +
						" numeroExterior:" +  domicilio.getNumeroExterior() + " numeroInterior:" +  domicilio.getNumeroInterior() +
						" yCalle:" +  domicilio.getyCalle());
			}			
			if (descripcion==null || descripcion.length()<1  || descripcion.length()>2000){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Descripción de la empresa es un dato que"));
			} else {
				String strPatron = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9.,;:#\\s]+$";
				if(!Utils.validaPatron(strPatron,descripcion)){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La Descripción de la empresa no es un dato válido."));
			    }							
			}
			if (contactoEmpresa==null || contactoEmpresa.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Nombre de la persona de contacto con la empresa es un dato que"));
			} else {
			    if(!Utils.esAlfabetico(contactoEmpresa)){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Nombre de la persona de contacto con la empresa no es un dato válido."));
			    }							
			}			
			if (idTipoEmpresa<=0) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Tipo de empresa es un dato que"));
			}
			if (idActividadEconomica<=0) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Actividad económica principal es un dato que"));
			}
			if (numeroEmpleados<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Número de empleados es un dato que"));
			} else {
			    if(!Utils.esEntero(String.valueOf(numeroEmpleados))){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Número de empleados no es un dato válido."));
			    }			
			}
			if (correoElectronico==null || correoElectronico.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Correo electrónico es un dato que"));
			}
			if (correoElectronico!=null && correoElectronico.length()>0 && (confirmarCorreoElectronico==null || confirmarCorreoElectronico.length()==0)){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Confirmar correo electrónico es un dato que"));
			}
			if (correoElectronico!=null && correoElectronico.length()>0 && confirmarCorreoElectronico!=null && confirmarCorreoElectronico.length()>0){
				if (!correoElectronico.equals(confirmarCorreoElectronico)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Correo electrónico y Confirmar correo electrónico deben ser iguales"));
				} else {
					if(!Utils.validaMail(correoElectronico)){
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "El Correo electrónico no es un dato válido."));
				    }
				}
			}			

			//TELEFONO PRINCIPAL
			if(idTipoTelefono<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Tipo de teléfono principal es un dato que"));
			}
			if (acceso==null || acceso.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Acceso del teléfono principal es un dato que"));
			} else {
				if(!Utils.esNumero(acceso)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Acceso del teléfono principal  no es un dato válido."));
			    }			
			}
			if (clave==null || clave.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Clave Lada del teléfono principal es un dato que"));
			} else {
				if(!Utils.esNumero(clave) || clave.length()>3){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La Clave Lada del teléfono no es un dato válido."));
			    }						
			}
			if (telefono==null || telefono.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Teléfono principal es un dato que"));
			} else {
				if(!Utils.esNumero(telefono)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Teléfono no es un dato válido."));
			    } else{
			    	if(clave.length()==2 && telefono.length()!=8){
			    		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "El teléfono debe tener 8 dígitos si la clave Lada tiene 2 dígitos."));								
			    	}else if(clave.length()==3 && telefono.length()!=7){
			    		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "El teléfono debe tener 7 dígitos si la clave Lada tiene 3 dígitos."));								
			    	}					
			    }									
			}
			if (errors.isEmpty()){
				//NO AGREGAR TELEFONO PRINCIPAL
				List<TelefonoVO> lstTelefonosAdicionales = (List<TelefonoVO>) request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES);
				if(null!=lstTelefonosAdicionales && lstTelefonosAdicionales.size()>1){
					lstTelefonos.addAll(lstTelefonosAdicionales);	
				}				
				System.out.println("---lstTelefonos con adicionales:" + lstTelefonos.size());
			}			
			
		} else if(method.equalsIgnoreCase("cambiarEstatus")) {
			System.out.println("Cambio de estatus");
			if(selectPartnerCompany<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Debe seleccionar una tercera empresa del listado."));
			}			
		}		
		
		if (errors.isEmpty()){
			errors = null;
			System.out.println("******-----errorForm:null");
		}else{
			request.setAttribute(Globals.ERROR_KEY, errors);
			//DEBUG
			/* 
			Iterator itMessages = errors.get();
			while(itMessages.hasNext()){				
				Object actionMsg = itMessages.next(); 
				System.out.println("******-----errorForm:" + actionMsg.toString());
			}
			*/
			//END DEBUG
		}		
		return errors;
	}
	
	
	/**
	 * Method 'getTerceraEmpresa'
	 * 
	 * @return TerceraEmpresaVO
	 */		
	/*public TerceraEmpresaVO getTerceraEmpresa(){
		TerceraEmpresaVO empresa = new TerceraEmpresaVO();
		empresa.setApellido1(apellido1);
		empresa.setApellido2(apellido2);
		empresa.setContactoEmpresa(contactoEmpresa);
		empresa.setCorreoElectronico(correoElectronico);
		empresa.setDescripcion(descripcion);	
		empresa.setEstatus(estatus);
		empresa.setIdActividadEconomica(idActividadEconomica);
		empresa.setIdEmpresa(idEmpresa);
		empresa.setIdTipoEmpresa(idTipoEmpresa);
		empresa.setIdTipoPersona(idTipoPersona);
		empresa.setNombre(nombre);
		empresa.setNumeroEmpleados(numeroEmpleados);
		empresa.setRazonSocial(razonSocial);
		empresa.setRfc(rfc);
		empresa.setNombreComercial(nombreComercial);
		return empresa;
	}*/
	
	/**
	 * @return the idTerceraEmpresa
	 */
	public long getIdTerceraEmpresa() {
		return idTerceraEmpresa;
	}
	/**
	 * @param idTerceraEmpresa the idTerceraEmpresa to set
	 */
	public void setIdTerceraEmpresa(long idTerceraEmpresa) {
		this.idTerceraEmpresa = idTerceraEmpresa;
	}
	/**
	 * @return the idEmpresa
	 */
	public long getIdEmpresa() {
		return idEmpresa;
	}
	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}
	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	/**
	 * @return the idTipoPersona
	 */
	public long getIdTipoPersona() {
		return idTipoPersona;
	}
	/**
	 * @param idTipoPersona the idTipoPersona to set
	 */
	public void setIdTipoPersona(long idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}
	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}
	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the contactoEmpresa
	 */
	public String getContactoEmpresa() {
		return contactoEmpresa;
	}
	/**
	 * @param contactoEmpresa the contactoEmpresa to set
	 */
	public void setContactoEmpresa(String contactoEmpresa) {
		this.contactoEmpresa = contactoEmpresa;
	}
	/**
	 * @return the idTipoEmpresa
	 */
	public long getIdTipoEmpresa() {
		return idTipoEmpresa;
	}
	/**
	 * @param idTipoEmpresa the idTipoEmpresa to set
	 */
	public void setIdTipoEmpresa(long idTipoEmpresa) {
		this.idTipoEmpresa = idTipoEmpresa;
	}
	/**
	 * @return the idActividadEconomica
	 */
	public long getIdActividadEconomica() {
		return idActividadEconomica;
	}
	/**
	 * @param idActividadEconomica the idActividadEconomica to set
	 */
	public void setIdActividadEconomica(long idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}
	/**
	 * @return the numeroEmpleados
	 */
	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}
	/**
	 * @param numeroEmpleados the numeroEmpleados to set
	 */
	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}
	/**
	 * @return the estatus
	 */
	public int getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the fechaUltimaActualizacion
	 */
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	/**
	 * @param fechaUltimaActualizacion the fechaUltimaActualizacion to set
	 */
	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}
	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	/**
	 * @return the confirmarCorreoElectronico
	 */
	public String getConfirmarCorreoElectronico() {
		return confirmarCorreoElectronico;
	}
	/**
	 * @param confirmarCorreoElectronico the confirmarCorreoElectronico to set
	 */
	public void setConfirmarCorreoElectronico(String confirmarCorreoElectronico) {
		this.confirmarCorreoElectronico = confirmarCorreoElectronico;
	}
	/**
	 * @return the lstTelefonos
	 */
	public List<TelefonoVO> getLstTelefonos() {
		return lstTelefonos;
	}
	/**
	 * @param lstTelefonos the lstTelefonos to set
	 */
	public void setLstTelefonos(List<TelefonoVO> lstTelefonos) {
		this.lstTelefonos = lstTelefonos;
	}
	/**
	 * @return the idTipoTelefono
	 */
	public int getIdTipoTelefono() {
		return idTipoTelefono;
	}
	/**
	 * @param idTipoTelefono the idTipoTelefono to set
	 */
	public void setIdTipoTelefono(int idTipoTelefono) {
		this.idTipoTelefono = idTipoTelefono;
	}
	/**
	 * @return the acceso
	 */
	public String getAcceso() {
		return acceso;
	}
	/**
	 * @param acceso the acceso to set
	 */
	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}
	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	/**
	 * @return the domicilio
	 */
	public DomicilioVO getDomicilio() {
		return domicilio;
	}
	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(DomicilioVO domicilio) {
		this.domicilio = domicilio;
	}
	/**
	 * @return the lstTercerasEmpresas
	 */
	/*public List<TerceraEmpresaVO> getLstTercerasEmpresas() {
		return lstTercerasEmpresas;
	}*/
	/**
	 * @param lstTercerasEmpresas the lstTercerasEmpresas to set
	 */
	/*public void setLstTercerasEmpresas(List<TerceraEmpresaVO> lstTercerasEmpresas) {
		this.lstTercerasEmpresas = lstTercerasEmpresas;
	}*/
	/**
	 * @return the selectPartnerCompany
	 */
	public long getSelectPartnerCompany() {
		return selectPartnerCompany;
	}
	/**
	 * @param selectPartnerCompany the selectPartnerCompany to set
	 */
	public void setSelectPartnerCompany(long selectPartnerCompany) {
		this.selectPartnerCompany = selectPartnerCompany;
	}
	/**
	 * @return the padreIdPortalEmpleo
	 */
	public String getPadreIdPortalEmpleo() {
		return padreIdPortalEmpleo;
	}
	/**
	 * @param padreIdPortalEmpleo the padreIdPortalEmpleo to set
	 */
	public void setPadreIdPortalEmpleo(String padreIdPortalEmpleo) {
		this.padreIdPortalEmpleo = padreIdPortalEmpleo;
	}
	/**
	 * @return the padreIdTipoPersona
	 */
	public long getPadreIdTipoPersona() {
		return padreIdTipoPersona;
	}
	/**
	 * @param padreIdTipoPersona the padreIdTipoPersona to set
	 */
	public void setPadreIdTipoPersona(long padreIdTipoPersona) {
		this.padreIdTipoPersona = padreIdTipoPersona;
	}
	/**
	 * @return the padreNombre
	 */
	public String getPadreNombre() {
		return padreNombre;
	}
	/**
	 * @param padreNombre the padreNombre to set
	 */
	public void setPadreNombre(String padreNombre) {
		this.padreNombre = padreNombre;
	}
	/**
	 * @return the padreApellido1
	 */
	public String getPadreApellido1() {
		return padreApellido1;
	}
	/**
	 * @param padreApellido1 the padreApellido1 to set
	 */
	public void setPadreApellido1(String padreApellido1) {
		this.padreApellido1 = padreApellido1;
	}
	/**
	 * @return the padreApellido2
	 */
	public String getPadreApellido2() {
		return padreApellido2;
	}
	/**
	 * @param padreApellido2 the padreApellido2 to set
	 */
	public void setPadreApellido2(String padreApellido2) {
		this.padreApellido2 = padreApellido2;
	}
	/**
	 * @return the padreRazonSocial
	 */
	public String getPadreRazonSocial() {
		return padreRazonSocial;
	}
	/**
	 * @param padreRazonSocial the padreRazonSocial to set
	 */
	public void setPadreRazonSocial(String padreRazonSocial) {
		this.padreRazonSocial = padreRazonSocial;
	}
	/**
	 * @return the padreContactoEmpresa
	 */
	public String getPadreContactoEmpresa() {
		return padreContactoEmpresa;
	}
	/**
	 * @param padreContactoEmpresa the padreContactoEmpresa to set
	 */
	public void setPadreContactoEmpresa(String padreContactoEmpresa) {
		this.padreContactoEmpresa = padreContactoEmpresa;
	}
	/**
	 * @return the padreLeyendaNombre
	 */
	public String getPadreLeyendaNombre() {
		return padreLeyendaNombre;
	}
	/**
	 * @param padreLeyendaNombre the padreLeyendaNombre to set
	 */
	public void setPadreLeyendaNombre(String padreLeyendaNombre) {
		this.padreLeyendaNombre = padreLeyendaNombre;
	}
	/**
	 * @return the hiddenDesTipoEmpresa
	 */
	public String getHiddenDesTipoEmpresa() {
		return hiddenDesTipoEmpresa;
	}
	/**
	 * @param hiddenDesTipoEmpresa the hiddenDesTipoEmpresa to set
	 */
	public void setHiddenDesTipoEmpresa(String hiddenDesTipoEmpresa) {
		this.hiddenDesTipoEmpresa = hiddenDesTipoEmpresa;
	}
	/**
	 * @return the hiddenDesActividadEconomica
	 */
	public String getHiddenDesActividadEconomica() {
		return hiddenDesActividadEconomica;
	}
	/**
	 * @param hiddenDesActividadEconomica the hiddenDesActividadEconomica to set
	 */
	public void setHiddenDesActividadEconomica(String hiddenDesActividadEconomica) {
		this.hiddenDesActividadEconomica = hiddenDesActividadEconomica;
	}
	/**
	 * @return the nombreComercial
	 */
	public String getNombreComercial() {
		return nombreComercial;
	}
	/**
	 * @param nombreComercial the nombreComercial to set
	 */
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	/**
	 * @return the preCorreoElectronico
	 */
	public String getPreCorreoElectronico() {
		return preCorreoElectronico;
	}

	/**
	 * @param preCorreoElectronico the preCorreoElectronico to set
	 */
	public void setPreCorreoElectronico(String preCorreoElectronico) {
		this.preCorreoElectronico = preCorreoElectronico;
	}

	/**
	 * @return the idColoniaSelected
	 */
	public long getIdColoniaSelected() {
		return idColoniaSelected;
	}

	/**
	 * @param idColoniaSelected the idColoniaSelected to set
	 */
	public void setIdColoniaSelected(long idColoniaSelected) {
		this.idColoniaSelected = idColoniaSelected;
	}

	/**
	 * @return the idColoniaSelectedText
	 */
	public String getIdColoniaSelectedText() {
		return idColoniaSelectedText;
	}

	/**
	 * @param idColoniaSelectedText the idColoniaSelectedText to set
	 */
	public void setIdColoniaSelectedText(String idColoniaSelectedText) {
		this.idColoniaSelectedText = idColoniaSelectedText;
	}

}
