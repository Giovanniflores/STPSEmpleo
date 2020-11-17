package mx.gob.stps.portal.core.persistencia.facade;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;
import mx.gob.stps.portal.persistencia.vo.OfertasRecientesVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaDetalleCortoVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
import mx.gob.stps.portal.core.ws.vo.OfertaTotalesVO;

@Local
public interface OfertaEmpleoFacadeLocal {

	public mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO findOfertaEmpleoById(long idOfertaEmpleo);
	
	public OfertaEmpleoVO findById(long idOfertaEmpleo) throws PersistenceException;
	
	public void update(OfertaEmpleoVO vo) throws PersistenceException;
	
	public void remove(long idOfertaEmpleo) throws PersistenceException;

	public List<OfertaEmpleoVO> consultaOfertasEmpleo(long idEmpresa, int estatus) throws PersistenceException;

	public List<OfertaEmpleoVO> consultaOfertasEmpleo(long idEmpresa, int estatus1, int estatus2, int estatus3, int estatus4);
	
	public List<OfertaEmpleoVO> consultaOfertasEmpleoTerceraEmpresa(long idTerceraEmpresa, int estatus) throws PersistenceException;
	
	public List<OfertaEmpleoVO> findAllOfertasEmpleoTerceraEmpresa(long idTerceraEmpresa) throws PersistenceException;
	
	public void actualizaEstatus(long idOfertaEmpleo, int estatus) throws PersistenceException;

	public List<OfertaEmpleoVO> ofertaEmpleosList(long idEmpresa) throws PersistenceException;
	
	public Long save(OfertaEmpleoVO vo) throws PersistenceException;

	public Long saveAndFlush(OfertaEmpleoVO vo) throws PersistenceException;	

	public long consultaTotalPlazasOfertasActivas() throws PersistenceException;
	
	//public OfertaTotalesVO consultaOfertasEmpleoEspecificaPorEntidad(List<Long> idsOfertaEmpleo, int idEntidad); 

	public OfertaTotalesVO consultaOfertasEmpleoPorEntidad(int idEntidad);
		
	public OfertaTotalesVO consultaOfertasEmpleoPorOcupacion(List<Integer> idsOcupacion);
	
	public OfertaTotalesVO consultaOfertasEmpleoPorOcupacion(List<Integer> idsOcupacion, int idOcupacionPortal);
	
	public OfertaTotalesVO consultaOfertasEmpleoPorEntidadOcupacion(int idEntidad, List<Integer> idsOcupacion, int idOcupacionPortal);
	
	public OfertaTotalesVO consultaOfertasEmpleoPorEntidadOcupacion(int idEntidad, List<Integer> idsOcupacion);
	
	public List<OfertaEmpleoVO> obtenerOfertasVigentesEliminadasFecha(long idEmpresa,int estatus1,Date fecha1, Date fecha2)throws PersistenceException;
	
	@SuppressWarnings("rawtypes")
	public List consultaTotalOfertasActivasPortalPorEntidad();

	public List<OfertasRecientesVO> obtieneOfertasRecientes(int tipoConsulta);

	
	@SuppressWarnings("rawtypes")
	public List consultaTotalOfertasActivasPortalPorEntidadEscolaridad();
	
	@SuppressWarnings("rawtypes")
	public List consultaTotalOfertasActivasPortalPorEntidadExperiencia();		
	
	public int consultaEstatus(long idOfertaEmpleo) throws PersistenceException;	

	public OfertaPorPerfilVO consultaOferta(long idOfertaEmpleo)throws SQLException;

	public List<ConocimientoHabilidadVO> buscarConocimientosHabilidades(long idCandidato, long idTipoConocimHabilidad) throws SQLException; 

	public List<ExpectativaLaboralVO> buscarExpecLaboral(long idCandidato) throws SQLException;	

	@SuppressWarnings("rawtypes")
	public List consultaOfertasVigentesActividadEconomica();
	
	@SuppressWarnings("rawtypes")
	public List consultaOfertasVigentesAreaOcupacion();
	
	public List<OfertaEmpleoVO> obtenerOfertas(int idEntidad, int numVacantes);

	public List<OfertaDetalleCortoVO> consultaOfertasDescripcionCorta(List<Long> idsOfertas);
	
	public void autorizaOfertaEmpleo(long idOfertaEmpleo, int idOpcion, Date fechaFin);

	public Long consultaNumeroOfertasActivas(long idEmpresa);

	public List<OfertaEmpleoJB> consultaOfertasEmpleoAsignadas(long idCandidato, Catalogos.ESTATUS estatus01, Catalogos.ESTATUS estatus02);

    public List<OfertaEmpleoJB> consultaOfertasEmpleoAsignadas(long idCandidato, Catalogos.ESTATUS estatus);

    public List<OfertaEmpleoJB> misPostulaciones(long idCandidato, List<Catalogos.ESTATUS> estatusOfertaCandidatoList, List<Catalogos.ESTATUS> estatusOfertaEmpleoList, int daysAfterExpires);

    public List<OfertaEmpleoJB> miContratacionPpc(long idOfertaCandidato);

    public int consultarOfertasContratadoPpc(long idCandidato);

	public List<Long> consultaOfertasFueraVigencia();

	public List<OfertaEmpleoOutVO> consultaOfertasEmpleoDyE(String palabra,String idDiscapacidad,String idEntidad,String genero,String idNivelEst);

	public int offersToIndex();

	public List<Long> offers(int lowerLimit, int upperLimit);
	
	public Integer contarNumeroPlazasResultados(List<Long> indicesOfertas);
	
	public List<OfertaFuncionVO> getOfertaFuncionList(long idOfertaEmpleo);
	
	
	//Inicio cambio movil
	public List<OfertaEmpleoJB> consultaOfertasEmpleoAsignadas(long idCandidato, List<Catalogos.ESTATUS> listEstatus,int diasDifferencia);
	
	public List<OfertaEmpleoJB> consultaOfertasEmpleosAsignadas(long idCandidato, List<Catalogos.ESTATUS> listEstatus,int diasDifferencia);
	
	//Fin cambio movil
}
