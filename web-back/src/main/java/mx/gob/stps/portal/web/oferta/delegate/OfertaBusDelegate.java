package mx.gob.stps.portal.web.oferta.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadOfertaVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoBecateVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
 
public interface OfertaBusDelegate {

	public OfertaEmpleoVO consultaOfertaEmpleo(long idOfertaEmpleo) throws ServiceLocatorException;
	
	public long registraOfertaEmpleo(OfertaEmpleoVO ofertaEmpleo) throws BusinessException, ServiceLocatorException;
	
	public long editaOfertaEmpleo(OfertaEmpleoVO ofertaEmpleo, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, TechnicalException, IndexerException, ServiceLocatorException;

	public long guardarUbicacion(RegistroUbicacionVO vo) throws BusinessException, ServiceLocatorException;
	public long editarUbicacion(RegistroUbicacionVO vo) throws BusinessException, ServiceLocatorException;
	public long editarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario) throws BusinessException, IndexerException, TechnicalException, ServiceLocatorException;
	public long editarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, IndexerException, TechnicalException, ServiceLocatorException;
	public long guardarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario) throws BusinessException, IndexerException, TechnicalException, ServiceLocatorException;
	public long guardarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, IndexerException, TechnicalException, ServiceLocatorException;
	public void guardarRequisitos(RegistroRequisitosVO vo) throws BusinessException, ServiceLocatorException; 
	public void guardarContacto(RegistroContactoVO vo, long idTipoUsuario) throws BusinessException, IndexerException, TechnicalException, ServiceLocatorException; 
	public List<CatalogoOpcionVO> obtenerCatalogo(Long idCatalogo)throws BusinessException, ServiceLocatorException;
	public List<CatalogoOpcionVO> obtenerCatalogoAlf(Long idCatalogo)throws ServiceLocatorException;
	public List<CatalogoOpcionVO> obtenerCatalogoExcluyente(Long idCatalogo,long[] arrIds)throws ServiceLocatorException;
	public OfertaEmpleoVO obtenerOferta(long id)throws BusinessException, ServiceLocatorException;
	public ArrayList<String> obtenerSectoresOferta(long idOferta) throws BusinessException, ServiceLocatorException;
	public ArrayList<String> obtenerPrestacionesOferta(long idOferta) throws BusinessException, ServiceLocatorException;
	public ArrayList<RegistroEntidadesVO> obtenerEntidadesOferta(long idOferta) throws BusinessException, ServiceLocatorException;
	public ArrayList<OfertaCarreraEspecialidadVO> obtenerCarrerasEspecialidades(long idOferta) throws BusinessException, ServiceLocatorException;
	public ArrayList<RequisitoVO> obetenerRequisitos(long idOferta) throws BusinessException, ServiceLocatorException;
	public ArrayList<OfertaIdiomaVO> obtenerIdiomas(long idOferta) throws BusinessException, ServiceLocatorException;
	//public ArrayList<TerceraEmpresaVO> obtenerTercerasEmpresas(long idEmpresa) throws BusinessException, ServiceLocatorException;
	//public ArrayList<ContactoVO> obtenerContactos(long idEmpresa) throws BusinessException, ServiceLocatorException;
	//public ArrayList<TerceraEmpresaVO> obtenerEmpresas(long idEmpresa) throws BusinessException, ServiceLocatorException;
	public List<MunicipioVO> obtenerMunicipio(long idEntidad) throws BusinessException, ServiceLocatorException;
	public List<OfertaEmpleoVO> obtenerOfertasEmpresa(long idEmpresa) throws BusinessException, ServiceLocatorException;
	//public void updateStatusOferta(long idOferta, int estatus)	throws BusinessException, ServiceLocatorException;
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFolio(long idEmpresa,int estatus1,int estatus2, long folio)throws BusinessException, ServiceLocatorException;
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFecha(long idEmpresa,int estatus1,int estatus2, Date fecha1, Date fecha2)throws BusinessException, ServiceLocatorException;
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasTitulo(long idEmpresa,int estatus1,int estatus2, String titulo) throws BusinessException, ServiceLocatorException;
	public void insertarEventoBitacora(EventoVO evento) throws BusinessException, ServiceLocatorException;
	public DomicilioVO obtenerDomicilio( long idOferta) throws  BusinessException, ServiceLocatorException;
	public CodigoPostalVO obtenerCodigoPostal( long idColonia) throws  BusinessException, ServiceLocatorException;
	public List<MunicipioVO> obtenerMunicipios() throws  BusinessException, ServiceLocatorException;
	public List<CatalogoOpcionVO> getOpcionByInterval(long idCatalogo,long op1, long op2)throws  BusinessException, ServiceLocatorException;
	public void updateFechaOfertaCanceladaActivada(long idOferta) throws BusinessException,ServiceLocatorException;
	
	/**
	 *  Método que obtiene las ofertas creadas por la empresa
	 * @param idPropietario
	 * @return List<OfertaEmpleoVO>
	 * @throws ServiceLocatorException 
	 * @throws BusinessException 
	 */
	public List<OfertaEmpleoVO> obtenerOfertasCreadas(long idPropietario)throws BusinessException, ServiceLocatorException;

	public List<OfertaPorCanalVO> consultaOfertasXCanal(long idEmpresa) throws SQLException, ServiceLocatorException;

	public void activaOfertaEmpleo(long idOfertaEmpleo, long idUsuario) throws ServiceLocatorException;
	
	public void cancelaOfertaEmpleo(long idOfertaEmpleo, long idUsuario) throws ServiceLocatorException;

	public void eliminaOfertaPorEmpresa(long idOfertaEmpleo, long idUsuario) throws ServiceLocatorException;
	
	public void eliminaOfertaPorAdministrador(long idOfertaEmpleo, long idUsuario) throws ServiceLocatorException;
	
	public List<OfertaPostulacionVO> obtienePostulantesDeEmpresa(long idEmpresa) throws BusinessException, ServiceLocatorException;

	public List<OfertaEmpleoVO> consultaTotalPostuladosPorOferta(long idEmpresa) throws ServiceLocatorException;
	
	public List<OfertaEmpleoVO> consultaMisOfertas(long idEmpresa) throws ServiceLocatorException;
	
	public List<ReporteOfertasEmpresaVO> getOfertasEmpresaReporte(long idEmpresa, int selectedStatus, 
			String selectedInitial, String selectedFinal) throws ServiceLocatorException;
	
	public List<OfertaEmpleoVO> busquedaOfertaActivaCancelada(long idEmpresa, Long folioOferta, String tituloOferta) throws ServiceLocatorException, SQLException;

	public void insertarEventoBitacora(EVENTO evento, long idUsuario,
			TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior) throws BusinessException, ServiceLocatorException;
	
	public void actualizaHabilidades(long idOfertaEmpleo, long[] idsHabilidades) throws ServiceLocatorException;

	public List<ResultadoBusquedaOfertasVO> buscarOfertasEspecificas(BusquedaOfertasVO vo) throws ServiceLocatorException, BusinessException, SQLException;
	
	public int update(OfertaEmpleoVO ofertaEmpleo) throws ServiceLocatorException, BusinessException, SQLException;
	
	public int actualizaEstatus(long idOfertaEmpleo, int estatus) throws ServiceLocatorException, BusinessException;
	
	public List<OfertaEmpleoBecateVO> obtenerOfertasBecate() throws ServiceLocatorException;
	public OfertaEmpleoBecateVO obtenerOfertaBecateById(long idOferta)throws BusinessException, ServiceLocatorException;
	
	public List<ModalidadVO> consultarModalidad(long idSubprograma) throws ServiceLocatorException;
	public ModalidadOfertaVO obtenerModalidadOfertaByIdOferta(long idOferta) throws BusinessException, ServiceLocatorException;

	public Boolean esOfertaBecate(long idOfertaEmpleo) throws ServiceLocatorException, BusinessException;

}