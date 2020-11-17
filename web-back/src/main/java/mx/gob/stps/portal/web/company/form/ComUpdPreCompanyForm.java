package mx.gob.stps.portal.web.company.form;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.seguridad.dao.UsuarioDAO;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static mx.gob.stps.portal.web.infra.utils.Constantes.LST_TELEFONOS_ADICIONALES;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;


public class ComUpdPreCompanyForm extends DomicilioForm implements Serializable{
	private static final long serialVersionUID = -3306632976758933736L;

	private static Logger logger = Logger.getLogger(ComUpdPreCompanyForm.class);
	
	//inicializar propiedades
	private long idEmpresa;
	private String idPortalEmpleo;	
	private String rfc;
	private long idTipoPersona;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date fechaNacimiento;
	private String razonSocial;
	private Date fechaActa;
	private String descripcion;
	private String contactoEmpresa;
	private long idTipoEmpresa;
	private long idActividadEconomica;
	private int numeroEmpleados;
	private long idMedio;
	private int confidencial;
	private String paginaWeb;
	private int aceptacionTerminos;
	private Date fechaAlta;
	private int estatus;
	private Date fechaUltimaActualizacion;
	private String correoElectronico;	
	private String confirmarCorreoElectronico;
	private int aseguraDatos;
	private List<TelefonoVO> lstTelefonos = new ArrayList<TelefonoVO>();
	//Datos para el telefono principal
	private Integer idTipoTelefono;
	private String acceso;
	private String clave;
	private String telefono;
	private String extension;
	//Domicilio
	private DomicilioVO domicilio;	
	//Hidden Cbos
	private String hiddenDesTipoEmpresa;
	private String hiddenDesActividadEconomica;
	private String hiddenDesEntidad;
	private String hiddenDesMunicipio;
	private String hiddenDesColonia;
	private String hiddenCodigoPostal;
	//Datos para tipo de cambio
	private String preDescripcion;
	private String preCorreoElectronico;
	private String preContactoEmpresa;
	private long preIdTipoTelefono;
	private String preAcceso;
	private String preClave;
	private String preTelefono;
	private String preExtension;		
	private long idTipoUsuario;
	private String nombreComercial;
	
	
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
		
		if (rfc.length()>0){
			String strPatron = "^[A-Za-z\\s\\ñÑ0-9]{0,13}$";
			
			if(!Utils.validaPatron(strPatron,rfc)){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "El RFC no es un dato válido."));
		    }	
		}						
		
		if(getCodigoPostal()==null || getCodigoPostal().length()<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El código postal es un dato que"));
		} else {
		    if(!Utils.esNumero(getCodigoPostal())){
		    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El código postal es un dato inválido."));
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
		    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El número exterior es un dato inválido."));
		    }							
		}			
		if (getNumeroInterior()!=null && getNumeroInterior().length()>0 && !Utils.esAlfanumerico(getNumeroInterior())){
	    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El número interior es un dato inválido."));
		}			
		if (getCalle()==null || getCalle().length()<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La calle es un dato que"));
		} else {
		    if(!Utils.esAlfanumerico(getCalle())){
		    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La calle es un dato inválido."));
		    }							
		}				
		if (getEntreCalle()==null || getEntreCalle().length()<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Entre calle es un dato que"));
		} else {
		    if(!Utils.esAlfanumerico(getEntreCalle())){
		    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "Entre calle es un dato inválido."));
		    }							
		}	
		if (getyCalle()==null || getyCalle().length()<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Y calle es un dato que"));
		} else {
		    if(!Utils.esAlfanumerico(getyCalle())){
		    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "Y calle es un dato inválido."));
		    }							
		}			
		
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
			/*
			System.out.println("----DOMICILIO  calle:" + domicilio.getCalle() + " CP:" + domicilio.getCodigoPostal() +
					" entre calle:" +  domicilio.getEntreCalle() +  " idColonia:" +  domicilio.getIdColonia() + 
					" idEntidad:" + domicilio.getIdEntidad() + " idMunicipio:" +  domicilio.getIdMunicipio() +
					" numeroExterior:" +  domicilio.getNumeroExterior() + " numeroInterior:" +  domicilio.getNumeroInterior() +
					" yCalle:" +  domicilio.getyCalle());
					*/
		}
						
		if (descripcion==null || descripcion.length()<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Descripción de la empresa es un dato requerido."));
		} else {
			String strPatron = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9.,;:#\\s]+$";
			if(!Utils.validaPatron(strPatron,descripcion)){
		    //if(!Utils.esAlfanumerico(descripcion)){
		    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La Descripción de la empresa es un dato inválido."));
		    }							
		}
		if (contactoEmpresa==null || contactoEmpresa.length()<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Nombre de la persona de contacto con la empresa es un dato requerido."));
		} else {
			String strPatron = "^[A-Za-z\\s\\áéíóúÁÉÍÓÚñÑ0-9/']{1,150}$";				
			if(!Utils.validaPatron(strPatron,contactoEmpresa)){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "El Nombre de la persona de contacto con la empresa no es un dato válido."));
		    }							
		}
		if (idTipoEmpresa<=0) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Tipo de empresa es un dato requerido."));
		}
		if (idActividadEconomica<=0) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Actividad económica principal es un dato requerido."));
		}						

		if (numeroEmpleados<=0){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Número de empleados es un dato requerido."));
		} else {
		    if(!Utils.esEntero(String.valueOf(numeroEmpleados))){
		    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Número de empleados es un dato inválido."));
		    }			
		}
		
		if (correoElectronico==null || correoElectronico.length()<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Correo electrónico es un dato que "));
		}
		if (correoElectronico!=null && correoElectronico.length()>0 && (confirmarCorreoElectronico==null || confirmarCorreoElectronico.length()==0)){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Confirmar correo electrónico  es un dato que "));
		}
		if (correoElectronico!=null && correoElectronico.length()>0 && confirmarCorreoElectronico!=null && confirmarCorreoElectronico.length()>0){
			if (!correoElectronico.equals(confirmarCorreoElectronico)){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Correo electrónico y Confirmar correo electrónico deben ser iguales"));
			} else {
				if(!Utils.validaMail(correoElectronico)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Correo electrónico no es un dato válido."));
			    } else {
			    	if(!correoElectronico.equalsIgnoreCase(preCorreoElectronico)){
						UsuarioDAO usrDAO = UsuarioDAO.getInstance();
						try {
							if(!usrDAO.esCorreoUnico(correoElectronico)){
								errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "El Correo electrónico proporcionado ya existe."));								
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 				    		
			    	}						
			    }
			}
		}			
		
		//TELEFONO PRINCIPAL
		if(idTipoTelefono<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El tipo de teléfono principal es un dato que"));
		}
		if (acceso==null || acceso.length()<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El acceso del teléfono principal es un dato que"));
		} else {
			if(!Utils.esNumero(acceso)){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El acceso del teléfono principal es un dato inválido."));
		    }			
		}
		if (clave==null || clave.length()<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Clave Lada del teléfono principal es un dato que"));
		} else {
			if(!Utils.esNumero(clave)){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La Clave Lada del teléfono principal es un dato que"));
		    }						
		}
		if (telefono==null || telefono.length()<1){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Teléfono principal es un dato que"));
		} else {
			if(!Utils.esNumero(telefono)){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Teléfono es un dato inválido."));
		    }									
		}
		/*COMENTAR EN PROD*/
		if (errors.isEmpty()){
			//NO AGREGAR TELEFONO PRINCIPAL
			List<TelefonoVO> lstTelefonosAdicionales = (List<TelefonoVO>) request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES);
			if(null!=lstTelefonosAdicionales && lstTelefonosAdicionales.size()>1){
				lstTelefonos.addAll(lstTelefonosAdicionales);	
			}
			System.out.println("---lstTelefonos con adicionales:" + lstTelefonos.size());
		}				
		/*	DESCOMENTAR EN PROD
		if (errors.isEmpty()){			
			//AGREGAR TELEFONO PRINCIPAL AL LISTADO
			TelefonoVO telVo = getTelefonoPrincipal(lstTelefonos);
			
			telVo.setAcceso(acceso);
			telVo.setClave(clave);
			telVo.setExtension(extension);
			telVo.setIdTipoTelefono(idTipoTelefono);
			telVo.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			telVo.setTelefono(telefono);
			//lstTelefonos = setTelefonoPrincipal(lstTelefonos, telVo);
			lstTelefonos = new ArrayList<TelefonoVO>();
			lstTelefonos.add(telVo);
		}
		
		//TELEFONOS ADICIONALES		
		if (errors.isEmpty()){
			List<TelefonoVO> lstTelefonosAdicionales = (List<TelefonoVO>) request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES);
			lstTelefonosAdicionales.removeAll(lstTelefonos);
			if(null!=lstTelefonosAdicionales && lstTelefonosAdicionales.size()>1){
				lstTelefonos.addAll(lstTelefonosAdicionales);	
			}
		}			
		*/
		
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
	 * @return the idPortalEmpleo
	 */
	public String getIdPortalEmpleo() {
		return idPortalEmpleo;
	}
	/**
	 * @param idPortalEmpleo the idPortalEmpleo to set
	 */
	public void setIdPortalEmpleo(String idPortalEmpleo) {
		this.idPortalEmpleo = idPortalEmpleo;
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
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	 * @return the fechaActa
	 */
	public Date getFechaActa() {
		return fechaActa;
	}
	/**
	 * @param fechaActa the fechaActa to set
	 */
	public void setFechaActa(Date fechaActa) {
		this.fechaActa = fechaActa;
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
	 * @return the idMedio
	 */
	public long getIdMedio() {
		return idMedio;
	}
	/**
	 * @param idMedio the idMedio to set
	 */
	public void setIdMedio(long idMedio) {
		this.idMedio = idMedio;
	}
	/**
	 * @return the confidencial
	 */
	public int getConfidencial() {
		return confidencial;
	}
	/**
	 * @param confidencial the confidencial to set
	 */
	public void setConfidencial(int confidencial) {
		this.confidencial = confidencial;
	}
	/**
	 * @return the paginaWeb
	 */
	public String getPaginaWeb() {
		return paginaWeb;
	}
	/**
	 * @param paginaWeb the paginaWeb to set
	 */
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
	/**
	 * @return the aceptacionTerminos
	 */
	public int getAceptacionTerminos() {
		return aceptacionTerminos;
	}
	/**
	 * @param aceptacionTerminos the aceptacionTerminos to set
	 */
	public void setAceptacionTerminos(int aceptacionTerminos) {
		this.aceptacionTerminos = aceptacionTerminos;
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
	 * 
	 */
	public int getEstatus() {
		return estatus;
	}	

	/**
	 * @param estatus the estatus to set
	 * 
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
	 * @return the aseguraDatos
	 */
	public int getAseguraDatos() {
		return aseguraDatos;
	}
	/**
	 * @param aseguraDatos the aseguraDatos to set
	 */
	public void setAseguraDatos(int aseguraDatos) {
		this.aseguraDatos = aseguraDatos;
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
	public Integer getIdTipoTelefono() {
		return idTipoTelefono;
	}
	/**
	 * @param idTipoTelefono the idTipoTelefono to set
	 */
	public void setIdTipoTelefono(Integer idTipoTelefono) {
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
	 * @return the hiddenDesEntidad
	 */
	public String getHiddenDesEntidad() {
		return hiddenDesEntidad;
	}
	/**
	 * @param hiddenDesEntidad the hiddenDesEntidad to set
	 */
	public void setHiddenDesEntidad(String hiddenDesEntidad) {
		this.hiddenDesEntidad = hiddenDesEntidad;
	}
	/**
	 * @return the hiddenDesMunicipio
	 */
	public String getHiddenDesMunicipio() {
		return hiddenDesMunicipio;
	}
	/**
	 * @param hiddenDesMunicipio the hiddenDesMunicipio to set
	 */
	public void setHiddenDesMunicipio(String hiddenDesMunicipio) {
		this.hiddenDesMunicipio = hiddenDesMunicipio;
	}
	/**
	 * @return the hiddenDesColonia
	 */
	public String getHiddenDesColonia() {
		return hiddenDesColonia;
	}
	/**
	 * @param hiddenDesColonia the hiddenDesColonia to set
	 */
	public void setHiddenDesColonia(String hiddenDesColonia) {
		this.hiddenDesColonia = hiddenDesColonia;
	}
	/**
	 * @return the preDescripcion
	 */
	public String getPreDescripcion() {
		return preDescripcion;
	}
	/**
	 * @param preDescripcion the preDescripcion to set
	 */
	public void setPreDescripcion(String preDescripcion) {
		this.preDescripcion = preDescripcion;
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
	 * @return the preContactoEmpresa
	 */
	public String getPreContactoEmpresa() {
		return preContactoEmpresa;
	}
	/**
	 * @param preContactoEmpresa the preContactoEmpresa to set
	 */
	public void setPreContactoEmpresa(String preContactoEmpresa) {
		this.preContactoEmpresa = preContactoEmpresa;
	}
	/**
	 * @return the preIdTipoTelefono
	 */
	public long getPreIdTipoTelefono() {
		return preIdTipoTelefono;
	}
	/**
	 * @param preIdTipoTelefono the preIdTipoTelefono to set
	 */
	public void setPreIdTipoTelefono(long preIdTipoTelefono) {
		this.preIdTipoTelefono = preIdTipoTelefono;
	}
	/**
	 * @return the preAcceso
	 */
	public String getPreAcceso() {
		return preAcceso;
	}
	/**
	 * @param preAcceso the preAcceso to set
	 */
	public void setPreAcceso(String preAcceso) {
		this.preAcceso = preAcceso;
	}
	/**
	 * @return the preClave
	 */
	public String getPreClave() {
		return preClave;
	}
	/**
	 * @param preClave the preClave to set
	 */
	public void setPreClave(String preClave) {
		this.preClave = preClave;
	}
	/**
	 * @return the preTelefono
	 */
	public String getPreTelefono() {
		return preTelefono;
	}
	/**
	 * @param preTelefono the preTelefono to set
	 */
	public void setPreTelefono(String preTelefono) {
		this.preTelefono = preTelefono;
	}
	/**
	 * @return the preExtension
	 */
	public String getPreExtension() {
		return preExtension;
	}
	/**
	 * @param preExtension the preExtension to set
	 */
	public void setPreExtension(String preExtension) {
		this.preExtension = preExtension;
	}
	/**
	 * @return the idTipoUsuario
	 */
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}
	/**
	 * @param idTipoUsuario the idTipoUsuario to set
	 */
	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	/**
	 * @return the hiddenCodigoPostal
	 */
	public String getHiddenCodigoPostal() {
		return hiddenCodigoPostal;
	}

	/**
	 * @param hiddenCodigoPostal the hiddenCodigoPostal to set
	 */
	public void setHiddenCodigoPostal(String hiddenCodigoPostal) {
		this.hiddenCodigoPostal = hiddenCodigoPostal;
	}		
	
	/**
	 * Method 'getEmpresaPorAutorizar'
	 * 
	 * @return EmpresaPorAutorizarVO
	 */		
	public EmpresaPorAutorizarVO getEmpresaPorAutorizar(){
		EmpresaPorAutorizarVO empVo = new EmpresaPorAutorizarVO();
			empVo.setAceptacionTerminos(aceptacionTerminos);
			empVo.setApellido1(apellido1);
			empVo.setApellido2(apellido2);
			empVo.setAseguraDatos(aseguraDatos);
			empVo.setConfidencial(confidencial);
			empVo.setContactoEmpresa(contactoEmpresa);
			empVo.setCorreoElectronico(confirmarCorreoElectronico);
			empVo.setDescripcion(descripcion);
			empVo.setEstatus(estatus);
			empVo.setIdActividadEconomica(idActividadEconomica);
			empVo.setIdEmpresa(idEmpresa);
			empVo.setIdMedio(idMedio);
			empVo.setIdPortalEmpleo(idPortalEmpleo);
			empVo.setIdTipoEmpresa(idTipoEmpresa);
			empVo.setIdTipoPersona(idTipoPersona);
			empVo.setNombre(nombre);
			empVo.setNumeroEmpleados(numeroEmpleados);
			empVo.setPaginaWeb(paginaWeb);
			empVo.setRazonSocial(razonSocial);
			empVo.setRfc(rfc);		
			empVo.setDomicilio(domicilio);
			empVo.setTelefonos(lstTelefonos);
			empVo.setNombreComercial(nombreComercial);
		return empVo;
	}
	
	
	public TelefonoVO getTelefonoPrincipal(List<TelefonoVO> lstTelefonos){
		TelefonoVO telVo = null;
		Iterator itList = lstTelefonos.iterator();
		while(itList.hasNext()){
			TelefonoVO vo = (TelefonoVO) itList.next();
			if(vo.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
				telVo = vo;
				break;
			}
		}
		return telVo;	
	}
	
	public List<TelefonoVO> setTelefonoPrincipal(List<TelefonoVO> lstTelefonos, TelefonoVO telVo){
		Iterator itList = lstTelefonos.iterator();
		while(itList.hasNext()){
			TelefonoVO vo = (TelefonoVO) itList.next();
			if(vo.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
				telVo.setIdTelefono(vo.getIdTelefono());
				telVo.setIdPropietario(vo.getIdPropietario());
				telVo.setIdTipoPropietario(vo.getIdTipoPropietario());
				telVo.setFechaAlta(vo.getFechaAlta());
				itList.remove();
				lstTelefonos.add(telVo);
				break;
			}
		}	
		return lstTelefonos;
	}



	/**
	 * @param nombreComercial the nombreComercial to set
	 */
	/* COMENTAR EN PROD */
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}



	/**
	 * @return the nombreComercial
	 */
	/* COMENTAR EN PROD */
	public String getNombreComercial() {
		return nombreComercial;
	}
	
	
}
