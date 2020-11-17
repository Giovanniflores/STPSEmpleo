package mx.gob.stps.portal.web.company.form;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.RegistroContactoVO;
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
public class ComRegContactFormv2 extends DomicilioForm implements Serializable{
	//private static final long serialVersionUID = -4428593644111641135L;

	private static Logger logger = Logger.getLogger(ComRegContactFormv2.class);
	
	//inicializar propiedades
	private long idContacto;
	private long idEmpresa;
	private String nombreContacto;
	private String cargo;
	private Date fechaAlta;
	private int estatus;
	private Date fechaModificacion;
	private long idTerceraEmpresa;
	private String correoElectronico;
	private String confirmarCorreoElectronico;
	private List<TelefonoVO> lstTelefonos = new ArrayList<TelefonoVO>();
	//private List<TerceraEmpresaVO> lstTercerasEmpresas = new ArrayList<TerceraEmpresaVO>();
	private List<RegistroContactoVO> lstContactos = new ArrayList<RegistroContactoVO>();
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
	private long selectContact = -1;
	private long selectTerceraEmpresa = -1;
	//DATOS DE EMPRESA PADRE
	private String padreIdPortalEmpleo;
	private long padreIdTipoPersona;
	private String padreNombre;
	private String padreApellido1;
	private String padreApellido2;
	private String padreRazonSocial;
	private String padreContactoEmpresa;
	private String padreLeyendaNombre;
	
	private String preCorreoElectronico;
	private long idColoniaSelected;
	private String idColoniaSelectedText;
	
	/**
	 * Reinicia los valores de la forma
	 * Method 'reset'
	 * 
	 */		
	public void reset() {
		nombreContacto = null;
		cargo = null;
		correoElectronico = null;
		confirmarCorreoElectronico = null;
		idTipoTelefono=0;
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
		//COMENTAR EN PRODUCCION TODO:domicilio = new DomicilioVO();		
		//COMENTAR EN PRODUCCION TODO:idContacto = 0;		
		//COMENTAR EN PRODUCCION TODO:lstTelefonos = new ArrayList<TelefonoVO>();		
		preCorreoElectronico = null;
		selectTerceraEmpresa = -1;
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

		/*errors = super.validate(mapping, request);
		if (errors == null){
			errors = new ActionErrors();
		}*/
		String method = request.getParameter("method");
		
		if(method.equalsIgnoreCase("salvar")){
			if (nombreContacto==null || nombreContacto.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Nombre es un dato que"));
			} else {
			    if(!Utils.esAlfabetico(nombreContacto)){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Nombre no es un dato válido."));
			    }							
			}
			if (cargo==null || cargo.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Cargo es un dato que"));
			} else {
			    if(!Utils.esAlfabetico(cargo)){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Cargo no es un dato válido."));
			    }							
			}		
			
			if(selectTerceraEmpresa<0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Debe seleccionar un elemento del listado de Terceras Empresas, si no desea seleccionar una tercera empresa, seleccione la opción Ninguna del listado."));
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
				System.out.println("----FORMA DOMICILIO errors.isEmpty:");				
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
				if(!Utils.esNumero(clave)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La Clave Lada del teléfono no es un dato válido."));
			    }						
			}
			if (telefono==null || telefono.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Teléfono principal es un dato que"));
			} else {
				if(!Utils.esNumero(telefono)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Teléfono principal no es un dato válido."));
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
			if(selectContact<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Debe seleccionar un contacto del listado."));
			}
			
		}
				
		if (errors.isEmpty()){
			errors = null;
			System.out.println("******---2--errorForm:null");
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
	 * Method 'getContacto'
	 * 
	 * @return ContactoVO
	 */		
	/*public RegistroContactoVO getContacto(){
		RegistroContactoVO contactoVo = new RegistroContactoVO();
		contactoVo.setCargo(cargo);
		contactoVo.setCorreoElectronico(correoElectronico);
		contactoVo.setEstatus(estatus);
		contactoVo.setIdEmpresa(idEmpresa);
		contactoVo.setIdTerceraEmpresa(idTerceraEmpresa);
		contactoVo.setNombreContacto(nombreContacto);
		//COMENTAR EN PRODUCCION TODO:contactoVo.setDomicilio(domicilio);
		//COMENTAR EN PRODUCCION TODO:contactoVo.setTelefonos(lstTelefonos);
		return contactoVo;
	}*/
	
	
	/**
	 * @return the idContacto
	 */
	public long getIdContacto() {
		return idContacto;
	}


	/**
	 * @param idContacto the idContacto to set
	 */
	public void setIdContacto(long idContacto) {
		this.idContacto = idContacto;
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
	 * @return the nombreContacto
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}


	/**
	 * @param nombreContacto the nombreContacto to set
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}


	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}


	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}


	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
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
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}


	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}


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
	 * @return the idEntidad
	 */
	public long getIdEntidad() {
		return idEntidad;
	}


	/**
	 * @param idEntidad the idEntidad to set
	 */
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}


	/**
	 * @return the idMunicipio
	 */
	public long getIdMunicipio() {
		return idMunicipio;
	}


	/**
	 * @param idMunicipio the idMunicipio to set
	 */
	public void setIdMunicipio(long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}


	/**
	 * @return the idColonia
	 */
	public long getIdColonia() {
		return idColonia;
	}


	/**
	 * @param idColonia the idColonia to set
	 */
	public void setIdColonia(long idColonia) {
		this.idColonia = idColonia;
	}


	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}


	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}


	/**
	 * @return the numeroInterior
	 */
	public String getNumeroInterior() {
		return numeroInterior;
	}


	/**
	 * @param numeroInterior the numeroInterior to set
	 */
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}


	/**
	 * @return the numeroExterior
	 */
	public String getNumeroExterior() {
		return numeroExterior;
	}


	/**
	 * @param numeroExterior the numeroExterior to set
	 */
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}


	/**
	 * @return the entreCalle
	 */
	public String getEntreCalle() {
		return entreCalle;
	}


	/**
	 * @param entreCalle the entreCalle to set
	 */
	public void setEntreCalle(String entreCalle) {
		this.entreCalle = entreCalle;
	}


	/**
	 * @return the yCalle
	 */
	public String getyCalle() {
		return yCalle;
	}


	/**
	 * @param yCalle the yCalle to set
	 */
	public void setyCalle(String yCalle) {
		this.yCalle = yCalle;
	}


	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}


	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	/**
	 * @return the selectContact
	 */
	public long getSelectContact() {
		return selectContact;
	}


	/**
	 * @param selectContact the selectContact to set
	 */
	public void setSelectContact(long selectContact) {
		this.selectContact = selectContact;
	}

	/**
	 * @return the selectTerceraEmpresa
	 */
	public long getSelectTerceraEmpresa() {
		return selectTerceraEmpresa;
	}


	/**
	 * @param selectTerceraEmpresa the selectTerceraEmpresa to set
	 */
	public void setSelectTerceraEmpresa(long selectTerceraEmpresa) {
		this.selectTerceraEmpresa = selectTerceraEmpresa;
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
	 * @return the lstContactos
	 */
	public List<RegistroContactoVO> getLstContactos() {
		return lstContactos;
	}

	/**
	 * @param lstContactos the lstContactos to set
	 */
	public void setLstContactos(List<RegistroContactoVO> lstContactos) {
		this.lstContactos = lstContactos;
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
