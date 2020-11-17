package mx.gob.stps.portal.core.persistencia.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.persistencia.entity.NotificacionCandidato;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
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
import mx.gob.stps.portal.core.ws.vo.OfertaTotalesVO;

@Local
public interface OfertaFacadeLocal {

	public long save(OfertaEmpleoVO oferta);
	
	public void validaOferta(long idOfertaEmpleo);
	
	public void notificacionCandidato(NotificacionCandidato notificacion);
	
	public void eliminaRequisitos(long idOfertaEmpleo);
	
	public void eliminaSectores(long idOfertaEmpleo);

	public void eliminaIdiomas(long idOfertaEmpleo);
	
	public void eliminaCarreras(long idOfertaEmpleo);
	
	public void eliminaUbicaciones(long idOfertaEmpleo);
	
	public void eliminaPrestaciones(long idOfertaEmpleo);
	
	public long registraOfertaRequisito(long idOfertaEmpleo, long idTipoRequisito,  String descripcion,
									    long idExperiencia, long idDominio, int principal, Date fechaAlta);

	public void registraHabilidad(long idOfertaEmpleo, long idHabilidad);
	
	public void eliminaHabilidades(long idOfertaEmpleo);
	
	public List<Long> consultaHabilidades(long idOfertaEmpleo);
	
	public List<CatalogoOpcionVO> consultaHabilidades(long idOfertaEmpleo, long idCatalogo);
	
	public long registraOfertaIdioma(long idOfertaEmpleo, long idIdioma, long idCertificacion, long idDominio, Date fechaAlta, int principal);
	
	public long registraOfertaCarrera(long idOfertaEmpleo, long idCarreraEspecialidad, int principal, Date fechaAlta);
	
	public long registraOfertaUbicacion(long idOfertaEmpleo, long idEntidad, long idMunicipio, Date fechaAlta);
	
	public long registraOfertaPrestacion(long idOfertaEmpleo, long idPrestacion, Date fechaAlta);
	
	//----------------------------------------------------------------------------------------------------
	
	public long save(RegistroUbicacionVO form) throws PersistenceException;

	public long update(RegistroUbicacionVO vo) throws PersistenceException;
	
	public long saveRequisitosOferta(RegistroRequisitosVO form)
			throws PersistenceException;

	//public long saveContactoOferta(RegistroContactoVO form) throws PersistenceException;

	public long updateContactoOferta(RegistroContactoVO vo)throws PersistenceException;
	
	public OfertaEmpleoVO obtenerOferta(long id) throws BusinessException;
	
	public ArrayList<String> getSectoresOferta(long idOferta) throws PersistenceException;
	
	public ArrayList<String> getPrestacionesOferta(long idOferta) throws PersistenceException;
	
	public ArrayList<RegistroEntidadesVO> getEntidadesOferta(long idOferta) throws PersistenceException;
	
	public ArrayList<OfertaCarreraEspecialidadVO> getCarrerasEspecialidades(long idOferta) throws PersistenceException;
	
	public ArrayList<RequisitoVO> getRequisitos(long idOferta) throws PersistenceException;
	
	public ArrayList<OfertaIdiomaVO> getIdiomas(long idOferta) throws PersistenceException;
	
	//public ArrayList<TerceraEmpresaVO> getTercerasEmpresas(long idEmpresa) throws PersistenceException;
	
	//public ArrayList<ContactoVO> getContactos(long idEmpresa) throws PersistenceException;
	
	//public ArrayList<TerceraEmpresaVO> getEmpresas(long idEmpresa) throws PersistenceException;
	
	public List<MunicipioVO> obtenerMunicipio(long idEntidad) throws PersistenceException;
			
	public List<OfertaEmpleoVO> obtenerOfertasEmpresa(long idEmpresa) throws PersistenceException;
	// public void updateParametro(long idParametro, String descripcion, String
	// valor);
	//public void updateStatusOferta(long idOferta, int estatus) throws PersistenceException;
	
	public void updateFechaOfertaCanceladaActivada(long idOferta) throws PersistenceException;
	
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFolio(long idEmpresa,int estatus1,int estatus2, long folio)throws PersistenceException;
	
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasFecha(long idEmpresa,int estatus1,int estatus2, Date fecha1, Date fecha2)throws PersistenceException;
	
	public List<OfertaEmpleoVO> obtenerOfertasEliminadasTitulo(long idEmpresa,int estatus1,int estatus2, String titulo) throws PersistenceException;
	
	public void insertarEventoBitacora(EventoVO evento) throws  PersistenceException;
	
	public DomicilioVO obtenerDomicilio(long idOferta) throws  PersistenceException;
	
	public CodigoPostalVO obtenerCodigoPostal( long idColonia) throws  PersistenceException;
	
	public List<MunicipioVO> obtenerMunicipios() throws  PersistenceException;
	
	public long registraSector(long idOfertaEmpleo, long idSector);
	
	public List<OfertaPorCanalVO> getOffersPerFilter(long idoffer, int district, String dateInitAdd, String dateFinalAdd, String dateInitUpd,
			String dateFinalUpd, String idPortal, String email, int status, int deleteRazon,
			String idEmpresa, String contacto, String telefono, String idEntidadSelect, String idMunicipio, String salarioRango, String salario, String titulo) throws  PersistenceException;	

	public List<Long> busquedaEspecificaMultiple(int entidad, int area, int escolaridad, int salario, int idMunicipio, List<Integer> idsOcupaciones,
			List<Integer> idsCarreras, int edad, int region) throws PersistenceException; 	
	
	public List<Long> busquedaEspecifica(int entidad, int area, int escolaridad, int salario, int idMunicipio, int ocupacion,
			int carrera, int edad, int region, String tipoOrden, int columna, int fuente) throws PersistenceException;

	public List<OfertaEmpleoVO> consultaMisOfertas(long idEmpresa);
	
	public List<ReporteOfertasEmpresaVO> getOfertasEmpresaReporte(long idEmpresa, int selectedStatus, 
			String selectedInitial, String selectedFinal);

	public void update(OfertaEmpleoVO oferta);
	
	public String obtenerOcupacion(long idOferta) throws PersistenceException;

	public List<OfertaRequisitoVO> getRequisitosOferta(long idOfertaEmpleo,long idTipoRequisito);

	public OfertaEmpleoVO find(long idOfertaEmpleo);
	
	public List<OfertaEmpleoVO> consultaOfertaByFolioTitulo(long idEmpresa, Long folioOferta, String tituloOferta) throws SQLException;
	
	public OfertaTotalesVO obtenerEstadisticasDeOfertasEncontradasPorCarrera(List<Integer> idsCarrerasUOcupaciones, int idEscolaridad, int idEntidad) throws SQLException;
	
	public OfertaTotalesVO obtenerEstadisticasDeOfertasEncontradasPorOcupacion(List<Integer> idsOcupaciones, int idEntidad) throws SQLException;

	public List<OfertaPorCanalVO> getOffersPerFilterAdminPublisher(
			long idoffer, String idPortal, String nombreEmpresa);

	public DomicilioVO getLocation(long idOfertaEmpleo);

	public List<ResultadoBusquedaOfertasVO> buscarOfertasEspecificas(BusquedaOfertasVO vo) throws SQLException;

	void updateCandidatoNotificacion2(NotificacionCandidato notificacion)
			throws PersistenceException;

	
	public long findCandidatoNotificacion(Long idCantidato);
	

	void updateEstatusNotificacion(NotificacionCandidato notificacion)
			throws PersistenceException;

	
	
}