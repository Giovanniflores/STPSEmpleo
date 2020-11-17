package mx.gob.stps.portal.web.company.form;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.RegistroContactoVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.address.form.DomicilioForm;
import mx.gob.stps.portal.web.infra.utils.Utils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static mx.gob.stps.portal.web.infra.utils.Constantes.LST_TELEFONOS_ADICIONALES;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

//TODO ELIMINAR CLASE YA NO SE USA
public class RegContForm extends DomicilioForm implements Serializable{
	private static final long serialVersionUID = -1700242611456974824L;

	//private static Logger logger = Logger.getLogger(RegContForm.class);
	
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
	//Datos para el telefono principal
	private int idTipoTelefono = 1;
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
	//Listado de contatactos
	private List<RegistroContactoVO> lstContacto = new ArrayList<RegistroContactoVO>();
	private long selectPartnerCompany = -1;
	

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
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Nombre"));
			}
			if (cargo==null || cargo.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Cargo"));
			}			
			if(getCodigoPostal()==null || getCodigoPostal().length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El código postal del domicilio"));
			} else {
			    if(!Utils.esNumero(getCodigoPostal())){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El código postal del domicilio"));
			    }											
			}
			if(getIdEntidad()<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La entidad del domicilio"));
			}
			if(getIdMunicipio()<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El municipio o delegación del domicilio"));
			}
			if(getIdColonia()<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La colonia del domicilio"));
			}
			if (getNumeroExterior()==null || getNumeroExterior().length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El número exterior del domicilio"));
			} else {
			    if(!Utils.esAlfanumerico(getNumeroExterior())){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El número exterior del domicilio"));
			    }							
			}			
			if (getNumeroInterior()!=null && getNumeroInterior().length()>0 && !Utils.esAlfanumerico(getNumeroInterior())){
		    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El número interior del domicilio"));
			}			
			if (getCalle()==null || getCalle().length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La primer calle de referencia del domicilio"));
			} else {
			    if(!Utils.esAlfanumerico(getCalle())){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La primer calle de referencia del domicilio"));
			    }							
			}				
			if (getEntreCalle()==null || getEntreCalle().length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La primer calle de referencia del domicilio"));
			} else {
			    if(!Utils.esAlfanumerico(getEntreCalle())){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La primer calle de referencia del domicilio"));
			    }							
			}	
			if (getyCalle()==null || getyCalle().length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La primer calle de referencia del domicilio"));
			} else {
			    if(!Utils.esAlfanumerico(getyCalle())){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La primer calle de referencia del domicilio"));
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
			
			
			if (correoElectronico==null || correoElectronico.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La dirección de correo electrónico"));
			}
			if (correoElectronico!=null && correoElectronico.length()>0 && (confirmarCorreoElectronico==null || confirmarCorreoElectronico.length()==0)){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La confirmación de la contraseña"));
			}
			if (correoElectronico!=null && correoElectronico.length()>0 && confirmarCorreoElectronico!=null && confirmarCorreoElectronico.length()>0){
				if (!correoElectronico.equals(confirmarCorreoElectronico)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.pass.confirm", "La dirección de correo electrónico y la confirmacion deben ser iguales"));
				} else {
					if(!Utils.validaMail(correoElectronico)){
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La dirección de correo electrónico"));
				    }
				}
			}			

			//TELEFONO PRINCIPAL
			if(idTipoTelefono<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Debe indicar el tipo de teléfono"));
			}
			if (acceso==null || acceso.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Debe indicar el acceso del teléfono"));
			} else {
				if(!Utils.esNumero(acceso)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El acceso del teléfono"));
			    }			
			}
			if (clave==null || clave.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Debe indicar la clave LADA del teléfono"));
			} else {
				if(!Utils.esNumero(clave)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La clave LADA del teléfono"));
			    }						
			}
			if (telefono==null || telefono.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Debe indicar el número telefónico"));
			} else {
				if(!Utils.esNumero(telefono)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El número telefónico"));
			    }									
			}

			if (errors.isEmpty()){
				//AGREGAR TELEFONO PRINCIPAL AL LISTADO
				TelefonoVO telVo = new TelefonoVO();
				telVo.setAcceso(acceso);
				telVo.setClave(clave);
				telVo.setExtension(extension);
				telVo.setIdTipoTelefono(idTipoTelefono);
				telVo.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				telVo.setTelefono(telefono);
				lstTelefonos.add(telVo);
			}
			if (errors.isEmpty()){
				List<TelefonoVO> lstTelefonosAdicionales = (List<TelefonoVO>) request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES);
				if(null!=lstTelefonosAdicionales && lstTelefonosAdicionales.size()>1){
					lstTelefonos.addAll(lstTelefonosAdicionales);	
				}				
			}			
			
		} else {
			System.out.println("Cambio de estatus");
			if(selectPartnerCompany<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La tercera empresa"));
			}
			
		}
		
		
		if (errors.isEmpty()){
			errors = null;
			System.out.println("******-----errorForm:null");
		}else{
			request.setAttribute(Globals.ERROR_KEY, errors);
			//DEBUG
			Iterator itMessages = errors.get();
			while(itMessages.hasNext()){				
				Object actionMsg = itMessages.next(); 
				System.out.println("******-----errorForm:" + actionMsg.toString());
			}
			//END DEBUG
		}		
		return errors;
	}
	
	
	/**
	 * Method 'getTerceraEmpresa'
	 * 
	 * @return RegistroContactoVO
	 */		
	/*public RegistroContactoVO getTerceraEmpresa(){
		RegistroContactoVO contacto = new RegistroContactoVO();
		contacto.setIdContacto(idContacto);
		contacto.setIdEmpresa(idEmpresa);
		contacto.setIdTerceraEmpresa(idTerceraEmpresa);
		contacto.setNombreContacto(nombreContacto);
		contacto.setCargo(cargo);
		contacto.setFechaAlta(fechaAlta);
		contacto.setEstatus(estatus);
		contacto.setFechaModificacion(fechaModificacion);
		contacto.setCorreoElectronico(correoElectronico);
		return contacto;
	}*/


	/**
	 * @return the idEmpresa
	 */
	public long getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(long idContacto) {
		this.idContacto = idContacto;
	}

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public long getIdTerceraEmpresa() {
		return idTerceraEmpresa;
	}

	public void setIdTerceraEmpresa(long idTerceraEmpresa) {
		this.idTerceraEmpresa = idTerceraEmpresa;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

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
	 * @return the lstTerceras
	 */
	public List<RegistroContactoVO> getlstContacto() {
		return lstContacto;
	}


	/**
	 * @param lstTerceras the lstTerceras to set
	 */
	public void setLstContacto(List<RegistroContactoVO> lstContacto) {
		this.lstContacto = lstContacto;
	}	
	
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
}
