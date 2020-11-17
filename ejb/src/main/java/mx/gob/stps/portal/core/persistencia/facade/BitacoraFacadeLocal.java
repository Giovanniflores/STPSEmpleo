package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;

/**
 * Define los métodos para la persistencia de Bitácora
 * 
 *
 */
@Local
public interface BitacoraFacadeLocal {

	/**
	 * Salva un registro de Bitácora
	 * @param idEvento	long	Identificador del tipo de evento que se registra
	 * @param idUsuario	long 	Identificador del usuario que genera el registro
	 * @param descripcion	String	Descripción del evento que se registra
	 * @param fechaEvento	Calendar	Fecha en que se registra el evento en la bitácora
	 * @param detalle	String	Cadena con los parametros más importantes que se modificaron durante el evento
	 */	
	public void save(long idEvento, long idUsuario, String descripcion, Calendar fechaEvento, String detalle, long idRegistro, int idTipoPropietario);
	
	public void save(EVENTO evento, long idUsuario, String descripcion,String detalle, long idRegistro, TIPO_PROPIETARIO tipoPropietario);

	public List<BitacoraVO> consultaBitacora(long idRegistro, TIPO_PROPIETARIO tipoPropietario, int numregistros);

	public int registraMovimiento(EVENTO evento, Date fechaEvento, long idUsuario, long idPerfil);

	public void registraHistoricoPPC(long idCandidato, long idMovimiento,List<OfertaPorPerfilVO> ofertas, boolean postulado);
	
	public boolean consultaHistoricoPPC(long idMovimiento);
	
	public void actualizaHistoricoPPC(long idMovimiento);

}