package mx.gob.stps.portal.web.company.form;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

@Deprecated
public class ComRegCompanyForm extends DomicilioForm implements Serializable{
	private static final long serialVersionUID = 1222217509045108609L;

	private static Logger logger = Logger.getLogger(ComRegCompanyForm.class);

	//inicializar propiedades
	private String idPortalEmpleo;	
	private String rfc;	
	private int idTipoPersona;		
	//datos exclusivos del tipo de persona fisica
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String fechaNacimiento;
	//datos exclusivos del tipo de persona moral
	private String razonSocial;
	private String fechaActa;
	//datos para cualquier tipo de persona
	private String descripcion;	
	private String contactoEmpresa;
	private int idTipoEmpresa;
	private int idActividadEconomica;
	private int numeroEmpleados;
	private int idMedio;
	private int confidencial;
	private String paginaWeb;
	private int aceptacionTerminos;
	//private String fechaAlta;
	//private int idEntidad = -1;
	private int estatus=Constantes.ESTATUS.REGISTRADA.getIdOpcion();
	private String fechaUltimaActualizacion;
	private String correoElectronico;
	private String confirmarCorreoElectronico;
	private List<TelefonoVO> lstTelefonos = new ArrayList<TelefonoVO>();
	private int aseguraDatos;
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

	private String hiddenDesTipoEmpresa;
	private String hiddenDesActividadEconomica;
	private String hiddenDesMedio;
	
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
		SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
		
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
				if (apellido1==null || apellido1.length()<2){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Primer Apellido es un dato que debe tener al menos 2 caracteres y "));
				} else{
				    if(!Utils.esAlfabetico(apellido1)){
				    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Primer Apellido no es un dato válido."));
				    }								
				}
				if (fechaNacimiento==null){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Fecha de nacimiento es un dato que"));
				} else {				
				    if(!Utils.validaFecha(fechaNacimiento)){
				    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La Fecha de nacimiento no es un dato válido."));
				    } else{
		    			try {
							java.util.Date dtFecha = sdfSource.parse(fechaNacimiento);		
							Calendar now = Calendar.getInstance();
							now.add(Calendar.DATE,-1);
							if(!dtFecha.before(now.getTime())){								
								errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "La Fecha de nacimiento debe ser previa a la actual."));
							}
						} catch (ParseException e) {
							logger.error(e); 
						}    						    	
				    }				
				}	
			} else {
				//		tipo de persona moral
				if (razonSocial==null || razonSocial.length()<3){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Razón social es un dato que debe tener al menos 3 caracteres y "));
				} else {
					//DESCOMENTAR EN QA String strPatron = "^[A-Za-z\\s\\d\\-.&,áéíóúÁÉÍÓÚñÑ/']{3,50}$";
					//DESCOMENTAR EN Prod String strPatron = "^[A-Za-z\\s\\-.&,áéíóúÁÉÍÓÚñÑ/']{3,50}$";
					String strPatron = "^[A-Za-z\\s\\d\\-.&,áéíóúÁÉÍÓÚñÑ/']{3,50}$";
					if(!Utils.validaPatron(strPatron,razonSocial)){				    	
				    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La Razón social no es un dato válido."));
				    }								
				}	
				if (fechaActa==null){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Fecha de acta constitutiva es un dato que"));
				} else {
				    if(!Utils.validaFecha(fechaActa)){
				    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La Fecha de acta constitutiva no es un dato válido."));
				    } else{
		    			try {
							java.util.Date dtFecha = sdfSource.parse(fechaActa);	
							Calendar now = Calendar.getInstance();
							now.add(Calendar.DATE,-1);
							if(!dtFecha.before(now.getTime())){								
								errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "La Fecha de acta constitutiva debe ser previa a la actual"));
							}
						} catch (ParseException e) {
							logger.error(e); 					
						}    						    				    	
				    }				
				}			
			}
			
			// Validando Nombre Comercial no importa el tipo de  persona
			if (nombreComercial.length()>0){	
				 if(!Utils.esAlfanumerico(nombreComercial)){
				    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Nombre Comercial no es un dato válido."));
				    }	
			}		
			
			
			if(getCodigoPostal()==null || getCodigoPostal().length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El código postal es un dato que"));
			} else {
				int iCodigoPostal_len = getCodigoPostal().length();
			    if(iCodigoPostal_len<5){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "El código postal debe tener 5 dígitos."));
			    } 															
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
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La colonia es es un dato que"));
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
			
			if (descripcion==null || descripcion.length()<1 || descripcion.length()>2000){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Descripción de la empresa es un dato que "));
			} else {
				String strPatron = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9.,;:#\\s]+$";
				if(!Utils.validaPatron(strPatron,descripcion)){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La Descripción de la empresa no es un dato válido."));
			    }							
			}
			if (contactoEmpresa==null || contactoEmpresa.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Nombre de la persona de contacto con la empresa es un dato que "));
			} else {				
				String strPatron = "^[A-Za-z\\s\\áéíóúÁÉÍÓÚñÑ0-9/']{1,150}$";				
				if(!Utils.validaPatron(strPatron,contactoEmpresa)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "El Nombre de la persona de contacto con la empresa no es un dato válido."));
			    }	
			}
						
			if (idTipoEmpresa<=0) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Tipo de empresa es un dato que "));
			} else {
				List<CatalogoOpcionVO> catTipoEmpresa = (List<CatalogoOpcionVO>)request.getSession().getAttribute(CAT_TIPO_EMPRESA);
				boolean bCheckOption = isValidOption(catTipoEmpresa,idTipoEmpresa);
				if(bCheckOption== false){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Debe seleccionar una opción del listado. Por favor no teclee información en el listado."));
				}				
			}
			if (idActividadEconomica<=0) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Actividad económica principal es un dato que "));
			} else{
				List<CatalogoOpcionVO> catActEco = (List<CatalogoOpcionVO>)request.getSession().getAttribute(CAT_ACTIVIDAD_ECONOMICA);
				boolean bCheckOption = isValidOption(catActEco,idActividadEconomica);
				if(bCheckOption== false){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Debe seleccionar una opción del listado. Por favor no teclee información en el listado."));
				}								
			}
			if (numeroEmpleados<=0){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Número de empleados  es un dato que "));
			} else {
			    if(!Utils.esEntero(String.valueOf(numeroEmpleados))){
			    	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El Número de empleados no es un dato válido."));
			    }			
			}
			if (idMedio<=0) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "¿Cómo se enteró del portal del empleo? es un dato que "));
			} else {
				List<CatalogoOpcionVO> catMedioEnt = (List<CatalogoOpcionVO>)request.getSession().getAttribute(CAT_MEDIO_ENTERADO);													
				boolean bCheckOption = isValidOption(catMedioEnt,idMedio);
				if(bCheckOption== false){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Debe seleccionar una opción del listado. Por favor no teclee información en el listado."));
				}					
			}
				
			
			if (correoElectronico==null || correoElectronico.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Correo electrónico es un dato que "));
			}
			if (correoElectronico!=null && correoElectronico.length()>0 && (confirmarCorreoElectronico==null || confirmarCorreoElectronico.length()==0)){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Confirmar correo electrónico es un dato que "));
			}
			if (correoElectronico!=null && correoElectronico.length()>0 && confirmarCorreoElectronico!=null && confirmarCorreoElectronico.length()>0){
				if (!correoElectronico.equals(confirmarCorreoElectronico)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Correo electrónico y Confirmar correo electrónico deben ser iguales"));
				} else {
					if(!Utils.validaMail(correoElectronico)){
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "El Correo electrónico no es un dato válido."));
				    } else {
						UsuarioDAO usrDAO = UsuarioDAO.getInstance();
						try {
							if(!usrDAO.esCorreoUnico(correoElectronico)){
								errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "El Correo electrónico proporcionado ya existe."));								
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} 			    					    	
				    }
				}
			}			
			
			if(aseguraDatos!=1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "Debe asegurar que sus datos son correctos"));
			}
			//TELEFONO PRINCIPAL
			if(idTipoTelefono<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El tipo de teléfono es un dato que "));
			}
			if (acceso==null || acceso.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El acceso del teléfono  es un dato que "));
			} else {
				if(!Utils.esNumero(acceso)){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "El acceso del teléfono no es un dato válido."));
			    }			
			}
			if (clave==null || clave.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "La Clave Lada del teléfono  es un dato que "));
			} else {
				if(!Utils.esNumero(clave) || clave.length()>3){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La Clave Lada del teléfono no es un dato válido."));
			    }						
			}
			if (telefono==null || telefono.length()<1){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "El Teléfono es un dato que "));
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
			if (extension!=null && extension.length()>6){
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.range", "La extensión no es un dato válido."));
			}			
			
			/*COMENTAR EN PRODUCCION*/
			if (errors.isEmpty()){
				//NO AGREGAR TELEFONO PRINCIPAL
				List<TelefonoVO> lstTelefonosAdicionales = (List<TelefonoVO>) request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES);
				if(null!=lstTelefonosAdicionales && lstTelefonosAdicionales.size()>1){
					lstTelefonos.addAll(lstTelefonosAdicionales);	
				}
				System.out.println("---lstTelefonos con adicionales:" + lstTelefonos.size());
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
	
	public boolean isValidOption(List<CatalogoOpcionVO> lst, long selectedValue){
		boolean isValid= false;
		//System.out.println("--------selectedValue:" + selectedValue);
		Iterator itList = lst.listIterator();
		while(itList.hasNext()){
			CatalogoOpcionVO option = (CatalogoOpcionVO)itList.next();
			//System.out.println("--------option:" + option.getIdCatalogoOpcion());
			if(option.getIdCatalogoOpcion()==selectedValue){
				isValid = true;
				break;
			}
		}
		return isValid;
	}
	
	/**
	 * Reinicia los valores de la forma
	 * Method 'reset'
	 * 
	 */		
	public void reset() {
		//idUsuario = -1;
		//idRol = -1;
		//idEmpresa = -1;
		idPortalEmpleo = null;	
		idTipoPersona = 0;
		rfc = null;			
			//tipo de persona fisica
		nombre = null;
		apellido1 = null;
		apellido2 = null;
		fechaNacimiento = null;
			//tipo de persona moral
		razonSocial = null;
		fechaActa = null;
		descripcion = null;	
		contactoEmpresa = null;
		idTipoEmpresa = 0;
		idActividadEconomica = 0;
		numeroEmpleados = 0;
		idMedio = 0;
		confidencial = 0;
		paginaWeb = null;
		aceptacionTerminos = 0;
		//fechaAlta = null;
		estatus = Constantes.ESTATUS.REGISTRADA.getIdOpcion();
		//idEntidad = 0;
		fechaUltimaActualizacion = null;
		correoElectronico = null;
		confirmarCorreoElectronico = null;		
		lstTelefonos = new ArrayList<TelefonoVO>();
		aseguraDatos = 0;
		//Datos para el telefono principal
		idTipoTelefono = 0;
		acceso=null;
		clave=null;
		telefono=null;
		extension=null;			
		//Domicilio
		domicilio = new DomicilioVO();
		idEntidad = 0;
		idMunicipio = 0;
		idColonia = 0;
		calle=null;
		numeroInterior=null;
		numeroExterior=null;
		entreCalle=null;
		yCalle=null;
		codigoPostal=null;
		nombreComercial=null;
	}
	

	/**
	 * Method 'getEmpresaPorAutorizar'
	 * 
	 * @return EmpresaPorAutorizarVO
	 */			
	public EmpresaPorAutorizarVO getEmpresaPorAutorizar(){
		EmpresaPorAutorizarVO empresa = new EmpresaPorAutorizarVO();
		
		empresa.setAceptacionTerminos(aceptacionTerminos);
		empresa.setApellido1(apellido1);
		empresa.setApellido2(apellido2);
		empresa.setConfidencial(confidencial);
		empresa.setContactoEmpresa(contactoEmpresa);
		empresa.setCorreoElectronico(correoElectronico);
		empresa.setDescripcion(descripcion);	
		empresa.setEstatus(estatus);
		empresa.setIdActividadEconomica(idActividadEconomica);
		empresa.setIdMedio(idMedio);
		empresa.setIdPortalEmpleo(idPortalEmpleo);
		empresa.setIdTipoEmpresa(idTipoEmpresa);
		empresa.setIdTipoPersona(idTipoPersona);
		empresa.setNombre(nombre);
		empresa.setNumeroEmpleados(numeroEmpleados);
		empresa.setPaginaWeb(paginaWeb);
		empresa.setRazonSocial(razonSocial);
		empresa.setRfc(rfc);
		empresa.setTelefonos(lstTelefonos);
		empresa.setAseguraDatos(aseguraDatos);
		empresa.setDomicilio(domicilio);
		empresa.setNombreComercial(nombreComercial);
		
		return empresa;
	}


	public String getIdPortalEmpleo() {
		return idPortalEmpleo;
	}

	public void setIdPortalEmpleo(String idPortalEmpleo) {
		this.idPortalEmpleo = idPortalEmpleo;
	}

	public int getIdTipoPersona() {
		return idTipoPersona;
	}

	public void setIdTipoPersona(int idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getFechaActa() {
		return fechaActa;
	}

	public void setFechaActa(String fechaActa) {
		this.fechaActa = fechaActa;
	}

	public int getEstatus() {
		return estatus;
	}


	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}


	public String getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}


	public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	
	public String getConfirmarCorreoElectronico() {
		return confirmarCorreoElectronico;
	}


	public void setConfirmarCorreoElectronico(String confirmarCorreoElectronico) {
		this.confirmarCorreoElectronico = confirmarCorreoElectronico;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContactoEmpresa() {
		return contactoEmpresa;
	}

	public void setContactoEmpresa(String contactoEmpresa) {
		this.contactoEmpresa = contactoEmpresa;
	}

	public int getIdTipoEmpresa() {
		return idTipoEmpresa;
	}

	public void setIdTipoEmpresa(int idTipoEmpresa) {
		this.idTipoEmpresa = idTipoEmpresa;
	}

	public int getIdActividadEconomica() {
		return idActividadEconomica;
	}

	public void setIdActividadEconomica(int idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}

	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}

	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}

	public int getIdMedio() {
		return idMedio;
	}

	public void setIdMedio(int idMedio) {
		this.idMedio = idMedio;
	}

	public int getConfidencial() {
		return confidencial;
	}

	public void setConfidencial(int confidencial) {
		this.confidencial = confidencial;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public int getAceptacionTerminos() {
		return aceptacionTerminos;
	}

	public void setAceptacionTerminos(int aceptacionTerminos) {
		this.aceptacionTerminos = aceptacionTerminos;
	}

	/*
	public String getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}		
	*/
	
	public List<TelefonoVO> getTelefonos(){
		return lstTelefonos;
	}
	
	public void setTelefonos(List<TelefonoVO> lstTelefonos){
		this.lstTelefonos = lstTelefonos;
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
	 * @return the hiddenDesMedio
	 */
	public String getHiddenDesMedio() {
		return hiddenDesMedio;
	}


	/**
	 * @param hiddenDesMedio the hiddenDesMedio to set
	 */
	public void setHiddenDesMedio(String hiddenDesMedio) {
		this.hiddenDesMedio = hiddenDesMedio;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	
}
