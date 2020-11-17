package mx.gob.stps.portal.core.seguridad.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal;
import mx.gob.stps.portal.core.seguridad.service.SeguimientoAtencionAppServiceLocal;
import mx.gob.stps.portal.core.seguridad.service.SeguimientoAtencionAppServiceRemote;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;

@Stateless(name = "SeguimientoAtencionAppService", mappedName = "SeguimientoAtencionAppService")
public class SeguimientoAtencionAppService implements SeguimientoAtencionAppServiceRemote, SeguimientoAtencionAppServiceLocal {

	@EJB
	private BitacoraFacadeLocal bitacoraFacade;

	@EJB
	private UsuarioFacadeLocal  usuarioFacade;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void iniciaSesion(long idUsuario){
		registraEvento(idUsuario, EVENTO.INICIA_SESION);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void iniciaSesion(long idUsuario, long idPerfil){
		registraEvento(idUsuario, idPerfil, EVENTO.INICIA_SESION);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void finalizaSesion(long idUsuario){
		registraEvento(idUsuario, EVENTO.FINALIZA_SESION);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void finalizaSesion(long idUsuario, long idPerfil){
		registraEvento(idUsuario, idPerfil, EVENTO.FINALIZA_SESION);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void actualizaDatosPersonales(long idUsuario){
		registraEvento(idUsuario, EVENTO.ACTUALIZA_DATOS_PERSONALES);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void actualizaDatosPersonales(long idUsuario, long idPerfil){
		registraEvento(idUsuario, idPerfil, EVENTO.ACTUALIZA_DATOS_PERSONALES);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void registroCuentaPersonal(long idUsuario){
		registraEvento(idUsuario, EVENTO.REGISTRA_CUENTA_PERSONAL);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void registroCuentaPersonal(long idUsuario, long idPerfil){
		registraEvento(idUsuario, idPerfil, EVENTO.REGISTRA_CUENTA_PERSONAL);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void busquedaEspecifica(long idUsuario){
		registraEvento(idUsuario, EVENTO.BUSQUEDA_ESPECIFICA);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void busquedaEspecifica(long idUsuario, long idPerfil){
		registraEvento(idUsuario, idPerfil, EVENTO.BUSQUEDA_ESPECIFICA);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void busquedaOcupate(long idUsuario){
		registraEvento(idUsuario, EVENTO.BUSQUEDA_OCUPATE);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void busquedaOcupate(long idUsuario, long idPerfil){
		registraEvento(idUsuario, idPerfil, EVENTO.BUSQUEDA_OCUPATE);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void busquedaPorPerfil(long idUsuario){
		registraEvento(idUsuario, EVENTO.BUSQUEDA_POR_PERFIL);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public int busquedaPorPerfil(long idUsuario, long idPerfil){
		return registraEvento(idUsuario, idPerfil, EVENTO.BUSQUEDA_POR_PERFIL);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void busquedaPorPerfilPPC(long idCandidato, long idMovimiento, List<OfertaPorPerfilVO> ofertas, boolean postulado){
		registraEventoPPC(idCandidato, idMovimiento, ofertas, postulado);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void consultaHistoricoBusquedaPPC(long idMovimiento){
		boolean motivo = consultaHistoricoPPC(idMovimiento);
		if (motivo)
			actualizaHistoricoPPC(idMovimiento);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void busquedaOtrasBolsasTrabajo(long idUsuario){
		registraEvento(idUsuario, EVENTO.BUSQUEDA_OTRAS_BOLSAS);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void busquedaOtrasBolsasTrabajo(long idUsuario, long idPerfil){
		registraEvento(idUsuario, idPerfil, EVENTO.BUSQUEDA_OTRAS_BOLSAS);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void consultaCurriculo(long idUsuario){
		registraEvento(idUsuario, EVENTO.CONSULTA_CURRICULO);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void consultaCurriculo(long idUsuario, long idPerfil){
		registraEvento(idUsuario, idPerfil, EVENTO.CONSULTA_CURRICULO);
	}

	private void registraEvento(long idUsuario, EVENTO evento){
		long idPerfil = consultaPerfil(idUsuario);
		registraEvento(idUsuario, idPerfil, evento);
	}
	
	private int registraEvento(long idUsuario, long idPerfil, EVENTO evento){
		Date fechaEvento = Calendar.getInstance().getTime();
		return bitacoraFacade.registraMovimiento(evento, fechaEvento, idUsuario, idPerfil);
	}
	
	private void registraEventoPPC(long idCandidato, long idMovimiento, List<OfertaPorPerfilVO> ofertas, boolean postulado){
		bitacoraFacade.registraHistoricoPPC(idCandidato, idMovimiento, ofertas, postulado);
	}
		
	private long consultaPerfil(long idUsuario){
		long idPerfil = 0;

		try{
			UsuarioVO usuario = usuarioFacade.find(idUsuario);
			idPerfil = usuario.getIdPerfil();
		} catch(Exception e){
			// No se imprime el error
		}

		return idPerfil;
	}
	
	private boolean consultaHistoricoPPC(long idMovimiento){
		return bitacoraFacade.consultaHistoricoPPC(idMovimiento);
	}
	
	private void actualizaHistoricoPPC(long idMovimiento){
		bitacoraFacade.actualizaHistoricoPPC(idMovimiento);
	}
}