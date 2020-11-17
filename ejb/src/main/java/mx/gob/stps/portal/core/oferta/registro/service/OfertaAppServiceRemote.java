package mx.gob.stps.portal.core.oferta.registro.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.candidate.vo.NotificacionCandidatoVO;
import mx.gob.stps.portal.core.candidate.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.registro.vo.EventoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroContactoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroRequisitosVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RequisitoVO;
import mx.gob.stps.portal.core.oferta.reporte.vo.ReporteOfertasEmpresaVO;
import mx.gob.stps.portal.core.oferta.vo.BusquedaOfertasVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.vo.ResultadoBusquedaOfertasVO;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.persistencia.entity.NotificacionCandidato;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadOfertaVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoBecateVO;

@Remote
public interface OfertaAppServiceRemote {
	
	
	public OfertaEmpleoVO consultaOfertaEmpleo(long idOfertaEmpleo);
	
	public OfertaEmpleoVO consultaOfertaEmpleoActiva(long idOfertaEmpleo);
	
	public long registraOfertaEmpleo(OfertaEmpleoVO ofertaEmpleo) throws BusinessException;

	public long editaOfertaEmpleo(OfertaEmpleoVO ofertaEmpleo, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, TechnicalException, IndexerException;
	
	public long guardarUbicacion(RegistroUbicacionVO form) throws BusinessException;

	public long editarUbicacion(RegistroUbicacionVO form) throws BusinessException;
	
	public long editarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario)throws BusinessException, IndexerException, TechnicalException;
	
	public long editarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario, long idUsuario, long idRegValidar)throws BusinessException, IndexerException, TechnicalException;
	
	public long guardarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario) throws BusinessException, IndexerException, TechnicalException;

	public long guardarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, IndexerException, TechnicalException;	
	
	public void guardarRequisitos(RegistroRequisitosVO form) throws BusinessException;

	public void guardarContacto(RegistroContactoVO vo, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, IndexerException, TechnicalException;

	public OfertaEmpleoVO obtenerOferta(long id) throws BusinessException;
	
	public ArrayList<String> getSectoresOferta(long idOferta) throws BusinessException;

	public ArrayList<String> getPrestacionesOferta(long idOferta) throws BusinessException;

	public ArrayList<RegistroEntidadesVO> getEntidadesOferta(long idOferta) throws BusinessException;
	
	public ArrayList<OfertaCarreraEspecialidadVO> getCarrerasEspecialidades(long idOferta) throws BusinessException;

	public ArrayList<RequisitoVO> getRequisitos(long idOferta) throws BusinessException;
	
	public ArrayList<OfertaIdiomaVO> getIdiomas(long idOferta) throws BusinessException;
	
	//public ArrayList<TerceraEmpresaVO> getTercerasEmpresas(long idEmpresa) throws BusinessException;
	
	//public ArrayList<ContactoVO> getContactos(long idEmpresa) throws BusinessException;
	
	//public ArrayList<TerceraEmpresaVO> getEmpresas(long idEmpresa) throws BusinessException;
	
	public List<MunicipioVO> obtenerMunicipio(long idEntidad) throws BusinessException;
	
	public List<OfertaEmpleoVO> obtenerOfertasEmpresa(long idEmpresa) throws BusinessException;
	
	//public void updateStatusOferta(long idOferta, int estatus)	throws BusinessException;
	
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFolio(long idEmpresa,int estatus1,int estatus2, long folio)throws BusinessException;
	
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFecha(long idEmpresa,int estatus1,int estatus2, Date fecha1, Date fecha2)throws BusinessException;
	
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasTitulo(long idEmpresa,int estatus1,int estatus2, String titulo) throws BusinessException;
	
	public void insertarEventoBitacora(EventoVO evento) throws  BusinessException;
	
	public void insertarEventoBitacora(EVENTO evento, long idUsuario, TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior) throws  BusinessException;
		
	public DomicilioVO obtenerDomicilio( long idOferta) throws  BusinessException;
	
	public CodigoPostalVO obtenerCodigoPostal( long idColonia) throws  BusinessException;
	
	public List<MunicipioVO> obtenerMunicipios() throws  BusinessException;
	
	public List<CatalogoOpcionVO> getOpcionByInterval(long idCatalogo,long op1, long op2)throws  BusinessException;
	
	public void updateFechaOfertaCanceladaActivada(long idOferta) throws BusinessException;
	
	public void insertarNotificacionCandidato(NotificacionCandidato candidato) throws BusinessException;
	
	public List<OfertaPorCanalVO> consultaOfertasXCanal(long idEmpresa) throws SQLException;

	public void activaOfertaEmpleo(long idOfertaEmpleo, long idUsuario);
	
	public void cancelaOfertaEmpleo(long idOfertaEmpleo, long idUsuario);
	
	public void eliminaOfertaPorEmpresa(long idOfertaEmpleo, long idUsuario);
	
	public void eliminaOfertaPorAdministrador(long idOfertaEmpleo, long idUsuario);
	
	public  List<OfertaPostulacionVO> obtienePostulantesDeEmpresa(long idEmpresa) throws BusinessException;
	
	public List<OfertaEmpleoVO> consultaTotalPostuladosPorOferta(long idEmpresa);

	public List<OfertaEmpleoVO> consultaMisOfertas(long idEmpresa);
	
	public List<ReporteOfertasEmpresaVO> getOfertasEmpresaReporte(long idEmpresa, int selectedStatus, 
			String selectedInitial, String selectedFinal);
	
	public List<OfertaEmpleoVO> busquedaOfertaActivaCancelada(long idEmpresa, Long folioOferta, String tituloOferta) throws SQLException;

	public void actualizaHabilidades(long idOfertaEmpleo, long[] idsHabilidades);
	
	public List<ResultadoBusquedaOfertasVO> buscarOfertasEspecificas(BusquedaOfertasVO vo) throws BusinessException, SQLException;
	
	public int update(OfertaEmpleoVO vo) throws BusinessException;
	
	public int actualizaEstatus(long idOfertaEmpleo, int estatus) throws BusinessException;

	public List<OfertaEmpleoBecateVO> obtenerOfertaBecate();
	public OfertaEmpleoBecateVO obtenerOfertaBecateById(long idOferta) throws BusinessException;
	
	public List<ModalidadVO> consultarModalidad(long idSubprograma);
	
	public ModalidadOfertaVO obtenerModalidadOfertaByIdOferta(long idOferta) throws BusinessException;
	
	public Boolean esOfertaBecate(long idOfertaEmpleo) throws BusinessException;

	public void updateCandidatoNotificacion2(NotificacionCandidato candidato) throws BusinessException;
	
	public void updateEstatusNotificacion(NotificacionCandidato candidato) throws BusinessException;

	public long findCandidatoNotificacion(Long idCantidato);

	public OfertaEmpleoVO consultaOfertaPush(long idOfertaEmpleo);
	

	
	
}
