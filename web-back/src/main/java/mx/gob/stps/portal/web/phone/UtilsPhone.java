package mx.gob.stps.portal.web.phone;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import java.util.Iterator;
import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public final class UtilsPhone {

	
	
	/**
	 * Method <b>getTelefonoPrincipal</b>
	 * Regresa un objeto de tipo TelefonoVO con los datos del telefono principal
	 * encontrado en una lista lst
	 * 
	 * @param lst
	 * @return TelefonoVO
	 */		
	public static TelefonoVO getTelefonoPrincipal(List<TelefonoVO> lst){
		TelefonoVO telvo = new TelefonoVO();
    	if(lst!=null && lst.size()>0){
    		Iterator<TelefonoVO> it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			System.out.println("--UtilsPhone.getTelefonoPrincipal:" + temp.toString());
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    				telvo = temp;
    				break;
    			}    			
    		}
    	}		
		return telvo;
	}  	
	
	/**
	 * Method <b>existsInList</b>
	 * Indica si el listado contiene al elemento de tipo TelefonoVO
	 * 
	 * @param lst
	 * @param t1
	 * @return boolean
	 */		
	public static boolean existsInList(List<TelefonoVO> lst, TelefonoVO t1){
		boolean exists = false;
    	if(lst!=null && lst.size()>0){
    		Iterator<TelefonoVO> it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			String ext1 = null==temp.getExtension() ? "" : temp.getExtension();
    			String ext2 = null==t1.getExtension() ? "" : t1.getExtension();
    			if(temp.getIdTipoTelefono().equals(t1.getIdTipoTelefono()) &&
    				temp.getClave().equalsIgnoreCase(t1.getClave()) &&
    				temp.getPrincipal().equals(t1.getPrincipal()) &&
    				temp.getTelefono().equalsIgnoreCase(t1.getTelefono()) &&
    				ext1.equalsIgnoreCase(ext2)){
    				exists = true;
    				break;
    			}
    		}
    	}			
		return exists;
	}	
	
	/**
	 * Method <b>hasPrincipalPhone</b>
	 * Indica si el listado contiene un telefono de tipo principal
	 * 
	 * @param lst
	 * @return boolean
	 */		
	public static boolean hasPrincipalPhone(List<TelefonoVO> lst){
    	boolean principal = false;
    	if(lst!=null && lst.size()>0){
    		Iterator<TelefonoVO> it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			//System.out.println("--UtilsPhone.hasPrincipalPhone:" + temp.toString());
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    				principal =  true;
    				break;
    			}    			
    		}
    	}
    	return principal;
    }	
	
	/**
	 * Method <b>hasAdditionalPhone</b>
	 * Indica si el listado contiene al menos un telefono de tipo adicional
	 * 
	 * @param lst
	 * @return boolean
	 */	
    public static boolean hasAdditionalPhone(List<TelefonoVO> lst){
    	boolean additional = false;
    	if(lst!=null && lst.size()>0){
    		Iterator<TelefonoVO> it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			//System.out.println("--UtilsPhone.hasAdditionalPhone:" + temp.toString());
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion()){
    				additional =  true;
    				break;
    			}    			
    		}
    	}
    	return additional;
    }	
	
}
