package mx.gob.stps.portal.web.phone.form;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public class PhoneForm extends ValidatorForm implements Serializable{
	private static final long serialVersionUID = 3182013066132638993L;

	private static Logger logger = Logger.getLogger(PhoneForm.class);

	//inicializar propiedades
	private int idTelefono;
	private int idTipoTelefono = 1;
	private String acceso;
	private String clave;
	private String telefono;
	private String extension;	
	private List<TelefonoVO> lstTelefonos = new ArrayList<TelefonoVO>();
	
	/**
	 * Reinicia los valores de la forma
	 * Method 'reset'
	 * 
	 */		
	public void reset() {
		this.idTelefono = 0;
		this.idTipoTelefono = 1;
		this.acceso = null;
		this.clave = null;
		this.telefono = null;
		this.extension = null;
		this.lstTelefonos = new ArrayList<TelefonoVO>();		
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

		
		String strDataTableRows = request.getParameter("dataTableRows");
		int intDataTableRows = Integer.parseInt(strDataTableRows);
		Hashtable htTelephoneParams = new Hashtable();
		String strTableId = "dataTable";
		int tableCols = 6;	//5
		htTelephoneParams = getTableParameters(request, strTableId);	
		lstTelefonos = clearAdditionalPhones(lstTelefonos);
		lstTelefonos.addAll(getListFromHashtable(htTelephoneParams, strTableId, tableCols, intDataTableRows, Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion()));
		
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
	
	public List<TelefonoVO> clearAdditionalPhones(List<TelefonoVO> lstPhones){
		List<TelefonoVO> lstMain = new ArrayList<TelefonoVO>();
		Iterator itPhones = lstPhones.iterator();
		while(itPhones.hasNext()){
			TelefonoVO telVo = (TelefonoVO)itPhones.next();
			if(telVo.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
				lstMain.add(telVo);
			}
		}
		return lstMain;
	}

	
	/**
	 * Obtiene los valores contenidos en un Hashtable y los parsea en objetos 
	 * de tipo TelefonoVO y los agrega a un ArrayList.
	 * 
	 * @param ht
	 * @param tableId
	 * @param tableCols
	 * @return List<TelefonoVO>
	 */    	 
	public List<TelefonoVO> getListFromHashtable(Hashtable ht, String tableId, int tableCols, int tableRows, int intPrincipal){
		List<TelefonoVO> lst = new ArrayList<TelefonoVO>();		
		for(int i=1; i<=tableRows; i++){
			TelefonoVO telVo = new TelefonoVO();
			//idTipoTelefonoSelect
			String strKeyName = tableId + "Cell1Row" + i;
			String strValue = String.valueOf(ht.get(strKeyName));			
			if(null!=strValue && !strValue.equalsIgnoreCase("null")){
//				telVo.setIdTipoTelefono(Long.parseLong(strValue.trim()));
				telVo.setIdTipoTelefono(Integer.parseInt(strValue.trim()));
				//acceso
				strKeyName = tableId + "Cell3Row" + i;
				strValue = String.valueOf(ht.get(strKeyName));
				telVo.setAcceso(strValue);			
				//claveSelect
				strKeyName = tableId + "Cell5Row" + i;
				strValue = String.valueOf(ht.get(strKeyName));
				telVo.setClave(strValue);
				//telefono
				strKeyName = tableId + "Cell7Row" + i;
				strValue = String.valueOf(ht.get(strKeyName));
				telVo.setTelefono(strValue);
				//extension
				strKeyName = tableId + "Cell9Row" + i;
				strValue = String.valueOf(ht.get(strKeyName));
				telVo.setExtension(strValue);		
				//idTelefono
				strKeyName = tableId + "Cell11Row" + i;
				strValue = String.valueOf(ht.get(strKeyName));
				if(null!=strValue && !strValue.equalsIgnoreCase("null") && !strValue.equalsIgnoreCase("0")){
					try{
						long id = Long.parseLong(strValue);
						telVo.setIdTelefono(id);						
				    } catch (Exception e) {
				        logger.error(e);
				    }   
				}
				if(intPrincipal==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
					telVo.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				} else {
					telVo.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
				}			
				
				lst.add(telVo);				
			}
		}
		return lst;
	}

	
	/**
	 * Obtiene los valores contenidos en la tabla dinamica (telefonos) y 
	 * genera un hashtable. 
	 * 
	 * @param request
	 * @param tableId
	 * @return Hashtable
	 */    	
	public Hashtable getTableParameters(HttpServletRequest request, String tableId){
		Hashtable htTableParams = new Hashtable();
		try{
			Enumeration eParamNames = request.getParameterNames();
			while(eParamNames.hasMoreElements()){
				String strParamName = String.valueOf(eParamNames.nextElement());				
				if(strParamName.contains(tableId)){
					String strParamValue = request.getParameter(strParamName); 
					htTableParams.put(strParamName, strParamValue);					
				}
			}			
		} catch (Exception e) {
			logger.error(e);
        }
		return htTableParams;
	}		
	
	
	/**
	 * Method 'getTelefono'
	 * 
	 * @return TelefonoVO
	 */		
	public TelefonoVO getTelefono(){
		TelefonoVO telVo = new TelefonoVO();
		telVo.setAcceso(acceso);
		telVo.setClave(clave);
		telVo.setExtension(extension);
		telVo.setIdTipoTelefono(idTipoTelefono);
		telVo.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
		telVo.setTelefono(telefono);
		telVo.setIdTelefono(idTelefono);
		return telVo;
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
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the idTelefono
	 */
	public int getIdTelefono() {
		return idTelefono;
	}


	/**
	 * @param idTelefono the idTelefono to set
	 */
	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}
	
}
