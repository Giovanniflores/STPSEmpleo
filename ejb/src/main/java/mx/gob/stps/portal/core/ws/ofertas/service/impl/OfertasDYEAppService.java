package mx.gob.stps.portal.core.ws.ofertas.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import mx.gob.stps.portal.core.infra.utils.Constantes.ETIQUETAS;
import mx.gob.stps.portal.core.ws.ofertas.discapacidadyEmpleo.VdyeStub;
import mx.gob.stps.portal.core.ws.ofertas.discapacidadyEmpleo.VdyeStub.GetVacantesWSEmpleo;
import mx.gob.stps.portal.core.ws.ofertas.discapacidadyEmpleo.VdyeStub.GetVacantesWSEmpleoResponse;
import mx.gob.stps.portal.core.ws.ofertas.parser.OfertasEmpleoParser;
import mx.gob.stps.portal.core.ws.ofertas.service.OfertasDYEAppServiceRemote;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;


@Stateless(name="OfertasDYEAppService",mappedName = "OfertasDYEAppService")
public class OfertasDYEAppService implements OfertasDYEAppServiceRemote   {
	
	
	List<OfertaEmpleoOutVO> listaOfertas = null;
	OfertaEmpleoOutVO voOut = null;
	
	
	@Override
	@TransactionAttribute (TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaEmpleoOutVO> buscarVacantesDyE(){
		List<OfertaEmpleoOutVO> listaVacantes = new ArrayList<OfertaEmpleoOutVO>();

		try {
			VdyeStub dyeStub = new VdyeStub();
			
			GetVacantesWSEmpleo getVacantesWSEmpleo = new GetVacantesWSEmpleo();			
			GetVacantesWSEmpleoResponse vacantes;
			dyeStub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED,Boolean.FALSE);
			vacantes = dyeStub.getVacantesWSEmpleo(getVacantesWSEmpleo);
			String vacantesXML = vacantes.get_return().toString();

			if (vacantesXML!=null)
				vacantesXML = vacantesXML.replace("&", "&amp;");
						
			OfertasEmpleoParser parser = new OfertasEmpleoParser(vacantesXML.getBytes(),ETIQUETAS.NODOSDISCACIDADEMPLEO);
			listaVacantes = parser.cargaVacantes();
	
			} catch (final Exception e) {
				e.printStackTrace();
			}		
		return listaVacantes;
	}
	
	
	
}
