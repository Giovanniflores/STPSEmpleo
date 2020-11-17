package mx.gob.stps.portal.core.seguridad.service;

import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;

@Remote
public interface SeguimientoAtencionAppServiceRemote {

	public void iniciaSesion(long idUsuario);
	
	public void iniciaSesion(long idUsuario, long idPerfil);
	
	public void finalizaSesion(long idUsuario);
	
	public void finalizaSesion(long idUsuario, long idPerfil);
	
	public void actualizaDatosPersonales(long idUsuario);
	
	public void actualizaDatosPersonales(long idUsuario, long idPerfil);
	
	public void registroCuentaPersonal(long idUsuario);
	
	public void registroCuentaPersonal(long idUsuario, long idPerfil);
	
	public void busquedaEspecifica(long idUsuario);
	
	public void busquedaEspecifica(long idUsuario, long idPerfil);
	
	public void busquedaOcupate(long idUsuario);
	
	public void busquedaOcupate(long idUsuario, long idPerfil);
	
	public void busquedaPorPerfil(long idUsuario);
	
	public int busquedaPorPerfil(long idUsuario, long idPerfil);
	
	public void busquedaOtrasBolsasTrabajo(long idUsuario);
	
	public void busquedaOtrasBolsasTrabajo(long idUsuario, long idPerfil);

	public void consultaCurriculo(long idUsuario);
	
	public void consultaCurriculo(long idUsuario, long idPerfil);

	public void busquedaPorPerfilPPC(long idCandidato, long idMovimiento,
			List<OfertaPorPerfilVO> ofertas, boolean postulado);
	
	public void consultaHistoricoBusquedaPPC(long idMovimiento);

}