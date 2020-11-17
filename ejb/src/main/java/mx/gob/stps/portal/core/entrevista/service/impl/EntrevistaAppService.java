/**
 * 
 */
package mx.gob.stps.portal.core.entrevista.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.entrevista.dao.EntrevistaDao;
import mx.gob.stps.portal.core.entrevista.service.EntrevistaAppServiceRemote;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EntrevistaFacadeLocal;

import org.apache.log4j.Logger;

/**
 * @author jose.hernandez
 *
 */
@Stateless(name = "EntrevistaAppService", mappedName = "EntrevistaAppService")
public class EntrevistaAppService implements EntrevistaAppServiceRemote {

	private static Logger logger = Logger.getLogger(EntrevistaAppService.class);

	@EJB
	private EntrevistaFacadeLocal entrevistaFacade;
	
	@EJB
	private BitacoraFacadeLocal bitacoraFacade;

	@Override
	public List<EntrevistaVO> getEntrevistaProgramadaCandidato(EntrevistaVO entrevista) throws PersistenceException {
		List<EntrevistaVO> entrevistas = new ArrayList<EntrevistaVO>();
		
		try {
			EntrevistaDao entrevistaDao = new EntrevistaDao();
			entrevistas = entrevistaDao.buscaEntrevistaProgramada(PERFIL.CANDIDATO, entrevista.getIdCandidato());
		} catch (SQLException e) {			
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException("Error al consultar la base de datos favor de contactar al Administrador de sistemas");
		}	
		
		return entrevistas;
	}

	@Override
	public List<EntrevistaVO> getEntrevistaProgramadaEmpresa(EntrevistaVO entrevista) throws PersistenceException {
		List<EntrevistaVO> entrevistas = new ArrayList<EntrevistaVO>();

		try {
			EntrevistaDao entrevistaDao = new EntrevistaDao();
			entrevistas = entrevistaDao.buscaEntrevistaProgramada(PERFIL.EMPRESA, entrevista.getIdEmpresa());
		} catch (SQLException e) {			
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException("Error al consultar la base de datos favor de contactar al Administrador de sistemas");
		}

		return entrevistas;
	}
	
	@Override
	public List<EntrevistaVO> getEntrevistaProgramadaCandidatoEnLinea(EntrevistaVO entrevista) throws PersistenceException {
		List<EntrevistaVO> entrevistas = new ArrayList<EntrevistaVO>();
		
		try {
			EntrevistaDao entrevistaDao = new EntrevistaDao();
			entrevistas = entrevistaDao.buscaEntrevistaProgramadaEnLinea(PERFIL.CANDIDATO, entrevista.getIdCandidato());
		} catch (SQLException e) {			
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException("Error al consultar la base de datos favor de contactar al Administrador de sistemas");
		}

		return entrevistas;
	}

	@Override
	public List<EntrevistaVO> getEntrevistaProgramadaEmpresaEnLinea(EntrevistaVO entrevista) throws PersistenceException {
		List<EntrevistaVO> entrevistas = new ArrayList<EntrevistaVO>();

		try {
			EntrevistaDao entrevistaDao = new EntrevistaDao();
			entrevistas = entrevistaDao.buscaEntrevistaProgramadaEnLinea(PERFIL.EMPRESA, entrevista.getIdEmpresa());
		} catch (SQLException e) {			
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException("Error al consultar la base de datos favor de contactar al Administrador de sistemas");
		}

		return entrevistas;
	}

	@Override
	public void rechazar(EntrevistaVO entrevista) throws PersistenceException {
		entrevistaFacade.update(entrevista);
		registroBitacora(EVENTO.ENTREVISTA_RECHAZADA, 
						entrevista.getIdUsuario(), 
						EVENTO.ENTREVISTA_RECHAZADA.getEvento(),
						entrevista.getMensajeBitacora(),
						entrevista.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO);		
	}

	@Override
	public void aceptar(EntrevistaVO entrevista) throws PersistenceException {		
		entrevistaFacade.update(entrevista);
		registroBitacora(EVENTO.ENTREVISTA_ACEPTADA, 
				entrevista.getIdUsuario(), 
				EVENTO.ENTREVISTA_ACEPTADA.getEvento(),
				entrevista.getMensajeBitacora(),
				entrevista.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO);
		
	}

	@Override
	public void cancelar(EntrevistaVO entrevista) throws PersistenceException {	
		entrevistaFacade.update(entrevista);
		registroBitacora(EVENTO.ENTREVISTA_CANCELADA, 
				entrevista.getIdUsuario(), 
				EVENTO.ENTREVISTA_CANCELADA.getEvento(),
				entrevista.getMensajeBitacora(),
				entrevista.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO);
		
	}

	@Override
	public void reprogramar(EntrevistaVO entrevista) throws PersistenceException {
			entrevistaFacade.update(entrevista);
			registroBitacora(EVENTO.ENTREVISTA_REPROGRAMAR, 
					entrevista.getIdUsuario(), 
					EVENTO.ENTREVISTA_REPROGRAMAR.getEvento(),
					entrevista.getMensajeBitacora(),
					entrevista.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO);
	}

	public void reprogramarEntrevista(EntrevistaVO entrevista) throws PersistenceException {
		entrevistaFacade.reprograma(entrevista);
		registroBitacora(EVENTO.ENTREVISTA_REPROGRAMAR,  entrevista.getIdUsuario(), EVENTO.ENTREVISTA_REPROGRAMAR.getEvento(),
				         entrevista.getMensajeBitacora(), entrevista.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO);
	}

	@Override
	public Boolean validarEntrevistaEnLinea(long idEntrevista){
		try {
			EntrevistaDao entrevistaDao = new EntrevistaDao();
			return entrevistaDao.validarEntrevistaEnTiempo(idEntrevista);
		} catch (SQLException e) {
			e.printStackTrace(); logger.error(e);
		}

		return false;
	}

	@Override
	public void save(EntrevistaVO entrevista) throws PersistenceException {
		entrevistaFacade.save(entrevista);
	}
	
	public void registroBitacora(EVENTO evento, long idUsuario, String descripcion,String detalle, long idRegistro, TIPO_PROPIETARIO tipoPropietario){
		bitacoraFacade.save(evento.getIdEvento(), idUsuario, descripcion, Calendar.getInstance(), detalle, idRegistro, tipoPropietario.getIdTipoPropietario());
	}
	
	@Override
	public EntrevistaVO buscaEntrevistaOfertaCandidatoActiva(long idCandidato, long idOfertaEmpleo) throws PersistenceException {
		EntrevistaVO entrevistaVO = null;
		
		try {
			EntrevistaDao entrevistaDao = new EntrevistaDao();
			entrevistaVO = entrevistaDao.buscaEntrevistaOfertaCandidatoActiva(idCandidato, idOfertaEmpleo);
		} catch (SQLException e) {
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException("Error al consultar la base de datos favor de contactar al Administrador de sistemas");
		}

		return entrevistaVO;
	}
	

}
