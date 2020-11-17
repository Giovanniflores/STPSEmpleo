package mx.gob.stps.portal.core.ws.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;

@WebService
@Stateless(name="PortalEmpleoWSDisyEmp",mappedName="PortalEmpleoWSDisyEmp")
@SOAPBinding(style = Style.DOCUMENT)
public class PortalEmpleoWSDisyEmp{

	@EJB
	private OfertaEmpleoFacadeLocal ofertaEmpleoFacade;
	
	@TransactionAttribute (TransactionAttributeType.NOT_SUPPORTED)
    public List<OfertaEmpleoOutVO> consultaOfertasEmpleoDyE(String palabra,String idDiscapacidad,String idEntidad,String genero,String idNivelEst){
    	
		List<OfertaEmpleoOutVO> listaOfertasEmpleo = new ArrayList<OfertaEmpleoOutVO>(); 
		listaOfertasEmpleo = ofertaEmpleoFacade.consultaOfertasEmpleoDyE(palabra, idDiscapacidad, idEntidad, genero, idNivelEst);
    	return listaOfertasEmpleo;
    }
}
