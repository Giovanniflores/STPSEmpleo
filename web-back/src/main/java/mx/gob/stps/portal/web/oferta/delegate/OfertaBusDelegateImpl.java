package mx.gob.stps.portal.web.oferta.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import mx.gob.stps.portal.core.candidate.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.registro.service.OfertaAppServiceRemote;
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
import mx.gob.stps.portal.utils.Catalogos.ESTATUS;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;
  
public final  class OfertaBusDelegateImpl implements OfertaBusDelegate {
 
	private static OfertaBusDelegate instance = new OfertaBusDelegateImpl();
	
	private OfertaBusDelegateImpl(){}	

	public static OfertaBusDelegate getInstance(){
		return instance;
	} 
	
	public OfertaEmpleoVO consultaOfertaEmpleo(long idOfertaEmpleo) throws ServiceLocatorException {
		return getOfertaAppService().consultaOfertaEmpleo(idOfertaEmpleo);
	}
	
	public long registraOfertaEmpleo(OfertaEmpleoVO ofertaEmpleo) throws BusinessException, ServiceLocatorException{
		return getOfertaAppService().registraOfertaEmpleo(ofertaEmpleo);
	}

	public long editaOfertaEmpleo(OfertaEmpleoVO ofertaEmpleo, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, TechnicalException, IndexerException, ServiceLocatorException {
		return getOfertaAppService().editaOfertaEmpleo(ofertaEmpleo, idTipoUsuario, idUsuario, idRegValidar);
	}

	public void actualizaHabilidades(long idOfertaEmpleo, long[] idsHabilidades) throws ServiceLocatorException {
		getOfertaAppService().actualizaHabilidades(idOfertaEmpleo, idsHabilidades);
	}
	
	public long guardarUbicacion(RegistroUbicacionVO vo) throws BusinessException, ServiceLocatorException {
		long idOferta= getOfertaAppService().guardarUbicacion(vo);
		return idOferta;
	}
	
	public long editarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario) throws BusinessException, IndexerException, TechnicalException, ServiceLocatorException {
		long idOferta= getOfertaAppService().editarUbicacionTotal(vo1,vo2,vo3,idTipoUsuario);
		return idOferta;
	}
	
	public long editarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, IndexerException, TechnicalException, ServiceLocatorException {
		long idOferta= getOfertaAppService().editarUbicacionTotal(vo1,vo2,vo3,idTipoUsuario, idUsuario, idRegValidar);
		return idOferta;
	}	
	
	public long guardarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario) throws BusinessException, IndexerException, TechnicalException, ServiceLocatorException {
		long idOferta= getOfertaAppService().guardarUbicacionTotal(vo1,vo2,vo3,idTipoUsuario);
		return idOferta;
	}
	
	public long guardarUbicacionTotal(RegistroUbicacionVO vo1,RegistroRequisitosVO vo2,RegistroContactoVO vo3, long idTipoUsuario, long idUsuario, long idRegValidar) throws BusinessException, IndexerException, TechnicalException, ServiceLocatorException {
		long idOferta= getOfertaAppService().guardarUbicacionTotal(vo1,vo2,vo3,idTipoUsuario, idUsuario, idRegValidar);
		return idOferta;
	}
	
	public long editarUbicacion(RegistroUbicacionVO vo) throws BusinessException, ServiceLocatorException {
		long idOferta= getOfertaAppService().editarUbicacion(vo);
		return idOferta;
	}
	
	public void guardarRequisitos(RegistroRequisitosVO vo) throws BusinessException, ServiceLocatorException {
		getOfertaAppService().guardarRequisitos(vo);
	}
	
	public void guardarContacto(RegistroContactoVO vo, long idTipoUsuario) throws BusinessException, IndexerException, TechnicalException, ServiceLocatorException {
		getOfertaAppService().guardarContacto(vo, idTipoUsuario, 0,0);
	}

	/*public void guardarContacto(RegistroContactoVO vo, long idTipoUsuario, long idRegValidar) throws BusinessException, IndexerException, TechnicalException, ServiceLocatorException {
		getOfertaAppService().guardarContacto(vo, idTipoUsuario, idRegValidar);
	}*/
	
	private OfertaAppServiceRemote getOfertaAppService() throws ServiceLocatorException {
		OfertaAppServiceRemote ejb = (OfertaAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_OFERTA);
		return ejb;
	}	

	public List<CatalogoOpcionVO> obtenerCatalogo(Long idCatalogo)throws ServiceLocatorException{
		return CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(idCatalogo,true);
	} 
	 
	public List<CatalogoOpcionVO> obtenerCatalogoAlf(Long idCatalogo)throws ServiceLocatorException{
		return CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(idCatalogo);
	}
	
	public List<CatalogoOpcionVO> obtenerCatalogoExcluyente(Long idCatalogo,long[] arrIds)throws ServiceLocatorException{
		return CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(idCatalogo,arrIds);
	}
	
	public OfertaEmpleoVO obtenerOferta(long id)throws BusinessException, ServiceLocatorException{
		OfertaEmpleoVO oferta = getOfertaAppService().obtenerOferta(id);
		return oferta;
	}
	public ArrayList<String> obtenerSectoresOferta(long idOferta) throws BusinessException, ServiceLocatorException{
		ArrayList<String> sectores =  getOfertaAppService().getSectoresOferta(idOferta);
		return sectores;
	}
	public ArrayList<String> obtenerPrestacionesOferta(long idOferta) throws BusinessException, ServiceLocatorException{
		ArrayList<String> prestaciones =  getOfertaAppService().getPrestacionesOferta(idOferta);
		return prestaciones;
	}
	
	public ArrayList<RegistroEntidadesVO> obtenerEntidadesOferta(long idOferta) throws BusinessException, ServiceLocatorException{
		ArrayList<RegistroEntidadesVO> entidades =  getOfertaAppService().getEntidadesOferta(idOferta);
		return entidades;
	}
	
	public ArrayList<OfertaCarreraEspecialidadVO> obtenerCarrerasEspecialidades(long idOferta) throws BusinessException, ServiceLocatorException{
		ArrayList<OfertaCarreraEspecialidadVO> carreras =  getOfertaAppService().getCarrerasEspecialidades(idOferta);
		return carreras;
	}
	
	public ArrayList<RequisitoVO> obetenerRequisitos(long idOferta) throws BusinessException, ServiceLocatorException{
		ArrayList<RequisitoVO> requisitos =  getOfertaAppService().getRequisitos(idOferta);
		return requisitos;	
		
	}
	
	public ArrayList<OfertaIdiomaVO> obtenerIdiomas(long idOferta) throws BusinessException, ServiceLocatorException{
		ArrayList<OfertaIdiomaVO> idiomas =  getOfertaAppService().getIdiomas(idOferta);
		return idiomas;	
	}
	
	/*public ArrayList<TerceraEmpresaVO> obtenerTercerasEmpresas(long idEmpresa) throws BusinessException, ServiceLocatorException{
		ArrayList<TerceraEmpresaVO> terceras = getOfertaAppService().getTercerasEmpresas(idEmpresa);
		return terceras;
	}*/

	/*public ArrayList<ContactoVO> obtenerContactos(long idEmpresa) throws BusinessException, ServiceLocatorException{
		 ArrayList<ContactoVO> contactos=( ArrayList<ContactoVO>)getOfertaAppService().getContactos(idEmpresa);
		 return contactos;
	}*/

	/*public ArrayList<TerceraEmpresaVO> obtenerEmpresas(long idEmpresa) throws BusinessException, ServiceLocatorException{
		ArrayList<TerceraEmpresaVO> empresas=(ArrayList<TerceraEmpresaVO>)getOfertaAppService().getEmpresas(idEmpresa);
		return empresas;
	}*/
	
	public List<MunicipioVO> obtenerMunicipio(long idEntidad) throws BusinessException, ServiceLocatorException{
		ArrayList<MunicipioVO> empresas=(ArrayList<MunicipioVO>)getOfertaAppService().obtenerMunicipio(idEntidad);
		return empresas;
	}
	
	public List<OfertaEmpleoVO> obtenerOfertasEmpresa(long idEmpresa) throws BusinessException, ServiceLocatorException{
		return getOfertaAppService().obtenerOfertasEmpresa(idEmpresa);
	}
	
	public List<OfertaEmpleoVO> consultaMisOfertas(long idEmpresa) throws ServiceLocatorException{
		return getOfertaAppService().consultaMisOfertas(idEmpresa);
	}
	
	public void activaOfertaEmpleo(long idOfertaEmpleo, long idUsuario) throws ServiceLocatorException {
		getOfertaAppService().activaOfertaEmpleo(idOfertaEmpleo, idUsuario);
	}
	
	public void cancelaOfertaEmpleo(long idOfertaEmpleo, long idUsuario) throws ServiceLocatorException {
		getOfertaAppService().cancelaOfertaEmpleo(idOfertaEmpleo, idUsuario);
		try {
			updateStatusOferta(idOfertaEmpleo, ESTATUS.CANCELADA.getIdOpcion());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminaOfertaPorEmpresa(long idOfertaEmpleo, long idUsuario) throws ServiceLocatorException {
		getOfertaAppService().eliminaOfertaPorEmpresa(idOfertaEmpleo, idUsuario);
		try {
			updateStatusOferta(idOfertaEmpleo, ESTATUS.ELIMINADA_EMP.getIdOpcion());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminaOfertaPorAdministrador(long idOfertaEmpleo, long idUsuario) throws ServiceLocatorException {
		getOfertaAppService().eliminaOfertaPorAdministrador(idOfertaEmpleo, idUsuario);
		try {
			updateStatusOferta(idOfertaEmpleo, ESTATUS.ELIMINADA_ADMIN.getIdOpcion());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStatusOferta(long idOferta, int estatus) throws BusinessException, ServiceLocatorException{
		getOfertaAppService().actualizaEstatus(idOferta, estatus);
	}
	
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFolio(long idEmpresa,int estatus1,int estatus2, long folio)throws BusinessException, ServiceLocatorException{
		List<OfertaEmpleoVO> ofertas= (List<OfertaEmpleoVO>)getOfertaAppService().obtenerOfertasEliminadasFolio(idEmpresa, estatus1, estatus2, folio);
		return ofertas;
	}
	
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFecha(long idEmpresa,int estatus1,int estatus2, Date fecha1, Date fecha2)throws BusinessException, ServiceLocatorException{
	
		List<OfertaEmpleoVO> ofertas= (List<OfertaEmpleoVO>)getOfertaAppService().obtenerOfertasEliminadasFecha(idEmpresa, estatus1, estatus2, fecha1, fecha2);
		return ofertas;
	}
	
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasTitulo(long idEmpresa,int estatus1,int estatus2, String titulo) throws BusinessException, ServiceLocatorException{
	
		List<OfertaEmpleoVO> ofertas= (List<OfertaEmpleoVO>)getOfertaAppService().obtenerOfertasEliminadasTitulo(idEmpresa, estatus1, estatus2, titulo);
		return ofertas;
	}
	
	public void insertarEventoBitacora(EventoVO evento) throws BusinessException, ServiceLocatorException{
		getOfertaAppService().insertarEventoBitacora(evento);
		
	}
	
	public DomicilioVO obtenerDomicilio(long idOferta) throws  BusinessException, ServiceLocatorException{
		DomicilioVO domicilio = getOfertaAppService().obtenerDomicilio( idOferta);
		return domicilio;
		
	}
	
	public CodigoPostalVO obtenerCodigoPostal( long idColonia) throws  BusinessException, ServiceLocatorException{
		CodigoPostalVO cp =  getOfertaAppService().obtenerCodigoPostal(idColonia);
		return cp;
	}
	public List<MunicipioVO> obtenerMunicipios() throws  BusinessException, ServiceLocatorException{
		List<MunicipioVO> municipios=getOfertaAppService().obtenerMunicipios();
		return municipios;
		
	}
	
	/**
	 *  Método que obtiene las ofertas creadas por la empresa
	 * @param idPropietario
	 * @return List<OfertaEmpleoVO>
	 * @throws ServiceLocatorException 
	 * @throws BusinessException 
	 */
	public List<OfertaEmpleoVO> obtenerOfertasCreadas(long idPropietario) throws BusinessException, ServiceLocatorException{
		List<OfertaEmpleoVO> list = (List<OfertaEmpleoVO>) getOfertaAppService().obtenerOfertasEmpresa(idPropietario);
		return list;
	}
	
	
	public List<CatalogoOpcionVO> getOpcionByInterval(long idCatalogo,long op1, long op2) throws  BusinessException, ServiceLocatorException{
		
		List<CatalogoOpcionVO> list = (List<CatalogoOpcionVO>) getOfertaAppService().getOpcionByInterval(idCatalogo,op1, op2);
		return list;
	}
	
	public void updateFechaOfertaCanceladaActivada(long idOferta) throws BusinessException,ServiceLocatorException{
		
		getOfertaAppService().updateFechaOfertaCanceladaActivada(idOferta);
		
	}

	public List<OfertaPorCanalVO> consultaOfertasXCanal(long idEmpresa) throws SQLException, ServiceLocatorException{
		return getOfertaAppService().consultaOfertasXCanal(idEmpresa);
	}
	
	public List<OfertaPostulacionVO> obtienePostulantesDeEmpresa(long idEmpresa) throws BusinessException, ServiceLocatorException{
		return getOfertaAppService().obtienePostulantesDeEmpresa(idEmpresa);		
	}

	public List<OfertaEmpleoVO> consultaTotalPostuladosPorOferta(long idEmpresa) throws ServiceLocatorException {
		return getOfertaAppService().consultaTotalPostuladosPorOferta(idEmpresa);
	}
	
	public List<ReporteOfertasEmpresaVO> getOfertasEmpresaReporte(long idEmpresa, int selectedStatus, 
			String selectedInitial, String selectedFinal) throws ServiceLocatorException{
		return getOfertaAppService().getOfertasEmpresaReporte(idEmpresa, selectedStatus, selectedInitial, selectedFinal);
	}

	@Override
	public List<OfertaEmpleoVO> busquedaOfertaActivaCancelada(long idEmpresa, Long folioOferta, String tituloOferta) throws ServiceLocatorException, SQLException {
		return getOfertaAppService().busquedaOfertaActivaCancelada(idEmpresa, folioOferta, tituloOferta);
	}

	@Override
	public void insertarEventoBitacora(EVENTO evento, long idUsuario,
			TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior) throws BusinessException, ServiceLocatorException {
		getOfertaAppService().insertarEventoBitacora(evento, idUsuario, tipoRegistro, idRegistro, estatusAnterior);
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ResultadoBusquedaOfertasVO> buscarOfertasEspecificas(BusquedaOfertasVO vo) throws ServiceLocatorException, BusinessException, SQLException {
		return getOfertaAppService().buscarOfertasEspecificas(vo);
	}

	@Override
	public int update(OfertaEmpleoVO ofertaEmpleo) throws ServiceLocatorException, BusinessException, SQLException {
		return getOfertaAppService().update(ofertaEmpleo);
	}

	@Override
	public int actualizaEstatus(long idOfertaEmpleo, int estatus) throws ServiceLocatorException, BusinessException {
		return getOfertaAppService().actualizaEstatus(idOfertaEmpleo, estatus);
	}

	@Override
	public List<OfertaEmpleoBecateVO> obtenerOfertasBecate() throws ServiceLocatorException{
		List<OfertaEmpleoBecateVO> ofertaBecateList = getOfertaAppService().obtenerOfertaBecate();
		return ofertaBecateList;
	}

	@Override
	public List<ModalidadVO> consultarModalidad(long idSubprograma) throws ServiceLocatorException {
		List<ModalidadVO> modalidadList = getOfertaAppService().consultarModalidad(idSubprograma);
		return modalidadList;		
	}

	@Override
	public OfertaEmpleoBecateVO obtenerOfertaBecateById(long idOferta) throws BusinessException, ServiceLocatorException {
		OfertaEmpleoBecateVO oferta = getOfertaAppService().obtenerOfertaBecateById(idOferta);
		return oferta;
	}

	@Override
	public ModalidadOfertaVO obtenerModalidadOfertaByIdOferta(long idOferta) throws BusinessException, ServiceLocatorException  {
		ModalidadOfertaVO modalidadOferta = getOfertaAppService().obtenerModalidadOfertaByIdOferta(idOferta);
		return modalidadOferta;
	}

	@Override
	public Boolean esOfertaBecate(long idOfertaEmpleo) throws ServiceLocatorException, BusinessException {
		return getOfertaAppService().esOfertaBecate(idOfertaEmpleo);
	}
	
}