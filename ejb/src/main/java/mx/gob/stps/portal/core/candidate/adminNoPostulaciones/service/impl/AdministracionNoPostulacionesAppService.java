package mx.gob.stps.portal.core.candidate.adminNoPostulaciones.service.impl;


import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.Transactional;

import mx.gob.stps.portal.core.candidate.adminNoPostulaciones.service.AdministracionNoPostulacionesAppServiceRemote;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.persistencia.facade.AdministracionNoPostulacionesFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;
import mx.gob.stps.portal.utils.Catalogos.PERFIL;


/**********************************************************
//
// Nombre: OPR  Fecha:	8.10.14
// Servicios para el Modulo Administracion de No Postulaciones 
// para el seguro de desempleo
//
/************************************************************/
@Stateless(name = "AdministracionNoPostulacionesAppService", mappedName = "AdministracionNoPostulacionesAppService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AdministracionNoPostulacionesAppService implements AdministracionNoPostulacionesAppServiceRemote{

	@EJB private AdministracionNoPostulacionesFacadeLocal administracionNoPostulacionesFacade;
	@EJB private BitacoraFacadeLocal  					  bitacoraFacade;
	
	@Override
	public List<HistoricoBusquedaPPCVO> getListadoOfertasNoPostuladas(long idCandidato) throws SQLException {
		List<HistoricoBusquedaPPCVO> listadoOfertasNoPostuladas = administracionNoPostulacionesFacade.getListadoOfertasNoPostuladas(idCandidato);
		OfertaPorPerfilVO            ofertaPorPerfilVO 			= null;
		
		if(listadoOfertasNoPostuladas != null && !listadoOfertasNoPostuladas.isEmpty())
			for(int ofertaEmpleo = 0; ofertaEmpleo < listadoOfertasNoPostuladas.size(); ofertaEmpleo++){
				/** Obtiene informacion del detalle de la oferta **/
				ofertaPorPerfilVO = administracionNoPostulacionesFacade.obtenerOfertaPorID(listadoOfertasNoPostuladas.get(ofertaEmpleo).getIdOfertaEmpleo(), idCandidato);
				
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setIdEmpresa(ofertaPorPerfilVO.getIdEmpresa());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setIdOfertaEmpleo(ofertaPorPerfilVO.getIdOfertaEmpleo());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setTituloOferta(ofertaPorPerfilVO.getTituloOferta());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setUbicacion(ofertaPorPerfilVO.getUbicacion());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setNombreEmpresa(ofertaPorPerfilVO.getEmpresa());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setSalario(ofertaPorPerfilVO.getSalario());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setFuente(ofertaPorPerfilVO.getFuente());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setFunciones(ofertaPorPerfilVO.getFunciones());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setNumeroPlazas(ofertaPorPerfilVO.getNumeroPlazas());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setFormaContacto(String.valueOf(ofertaPorPerfilVO.getMedioContacto()));
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setNivelEstudios(ofertaPorPerfilVO.getGradoEstudio());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setCarrera(ofertaPorPerfilVO.getGradoEstudio());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setOcupacion(ofertaPorPerfilVO.getOcupacion());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setIdioma(ofertaPorPerfilVO.getIdiomas());
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setDominioIdioma(ofertaPorPerfilVO.getIdiomasCert());

				/*listadoOfertasNoPostuladas.get(ofertaEmpleo).setDetalleOferta(ofertaPorPerfilVO.getTituloOferta(), 
						ofertaPorPerfilVO.getGradoEstudio(), ofertaPorPerfilVO.getCarrera(), ofertaPorPerfilVO.getFunciones(), 
						ofertaPorPerfilVO.getIdiomas(), String.valueOf(ofertaPorPerfilVO.getNumeroPlazas()), ofertaPorPerfilVO.getMedioContacto());*/
				
				listadoOfertasNoPostuladas.get(ofertaEmpleo).setDetalleOferta(ofertaPorPerfilVO.getTituloOferta(), ofertaPorPerfilVO.getGradoEstudio(), 
						ofertaPorPerfilVO.getCarrera(),  
						ofertaPorPerfilVO.getFunciones(), ofertaPorPerfilVO.getIdiomas(), ofertaPorPerfilVO.getIdiomasCert(), String.valueOf(ofertaPorPerfilVO.getNumeroPlazas()), ofertaPorPerfilVO.getMedioContacto());
			}
		
		return listadoOfertasNoPostuladas;
	}
	
	@Override
	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean actualizarMotivoNoPostulacion(long idCandidato, long idUsuario, long idOfertaEmpleo, long idMotivo, String motivoDescripcion, int fuente) {
			boolean nuevoMotivo = false;
			try {
				Calendar fecha = Calendar.getInstance();
				administracionNoPostulacionesFacade.actualizarMotivoNoPostulacion(idCandidato, idOfertaEmpleo, idMotivo, motivoDescripcion, fuente);
				bitacoraFacade.registraMovimiento(Constantes.EVENTO.BUSQUEDA_POR_PERFIL, fecha.getTime(), idUsuario, PERFIL.CANDIDATO.getIdOpcion());
				nuevoMotivo = true;
			} catch (SQLException e) {
				e.printStackTrace();
				e.getMessage();
			}
			
			return nuevoMotivo;
	}

}
