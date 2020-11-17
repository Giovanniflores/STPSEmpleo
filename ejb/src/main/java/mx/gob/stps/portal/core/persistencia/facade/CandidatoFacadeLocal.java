package mx.gob.stps.portal.core.persistencia.facade;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.BusquedaCandidatosVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ComputacionAvanzadaVO;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.candidate.vo.EscolaridadVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLugarVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaVO;
import mx.gob.stps.portal.core.candidate.vo.ExperienciaVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.candidate.vo.ResultadoBusquedaCandidatosVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.vo.CandidatoVerDetalleVO;
import mx.gob.stps.portal.core.search.Candidate;
import mx.gob.stps.portal.persistencia.entity.Candidato;

@Local
public interface CandidatoFacadeLocal {

	/** Persistiendo al candidato
	 * @param candidatoVo
	 * @return
	 * @throws PersistenceException
	 */
	//public CandidatoVo save(CandidatoVo candidatoVo) throws PersistenceException;

	public long save(Candidato candidato) throws PersistenceException;
	
	public void actualizaRegistroCandidato(CandidatoVo vo) throws PersistenceException;
	
	/**
	 * Actualiza o crea el perfil de un candidato, 
	 * afecta la tablas: CANDIDATO
	 * PERFIL_LABORAL 
	 * DOMICILIO 
	 * TELEFONO
	 * OTRO_MEDIO
	 * @author Felipe Juárez Ramírez
	 * @since 28/02/2011
	 * @param perfil
	 * @throws PersistenceException
	 * @return PerfilVO
	 **/
	public PerfilVO registrarPerfil(PerfilVO perfil) throws PersistenceException;

	/**
	 * Actualiza o crea los datos de escolaridad de un candidato. Afecta las
	 * tablas: CANDIDATO_GRADO_ACADEMICO CANDIDATO_IDIOMA
	 * CANDIDATO_CONOCIM_HABILIDAD PERFIL_LABORAL CANDIDATO_COMPU_AVANZADA
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 08/03/2011
	 * @param EscolaridadVO
	 * @throws PersistenceException
	 * @return void
	 **/
	public EscolaridadVO registrarEscolaridad(EscolaridadVO escolaridad)
			throws PersistenceException;

	/**
	 * Actualiza o crea un grado academico de un candidato. Afecta las tablas:
	 * CANDIDATO_GRADO_ACADEMICO
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 22/03/2011
	 * @param long
	 * @param GradoAcademicoVO
	 * @throws PersistenceException
	 * @return void
	 **/
	public void agregarGrado(long idCandidato, GradoAcademicoVO gradoVO)
			throws PersistenceException;

	/**
	 * Borra un grado academico de un candidato. Afecta las tablas:
	 * CANDIDATO_GRADO_ACADEMICO
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 22/03/2011
	 * @param long
	 * @throws PersistenceException
	 * @return void
	 **/
	public void borrarGrado(long idCandidatoGradoAcademico) throws PersistenceException;

	public void borrarGradosAcademicos(long idCandidato);
	
	/**
	 * Actualiza o crea un idioma de un candidato. Afecta las tablas:
	 * CANDIDATO_IDIOMA
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @param IdiomaVO
	 * @throws PersistenceException
	 * @return void
	 **/
	public void agregarIdioma(long idCandidato, IdiomaVO idiomaVO)
			throws PersistenceException;

	/**
	 * Borra un idioma de un candidato. Afecta las tablas: CANDIDATO_IDIOMA
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @throws PersistenceException
	 * @return void
	 **/
	public void borrarIdioma(long idCandidatoIdioma) throws PersistenceException;

	public void borrarIdiomas(long idCandidato);
	
	/**
	 * Actualiza o crea un conocimiento o habilidad de un candidato. 
	 * Afecta las tablas:
	 * CANDIDATO_CONOCIM_HABILIDAD
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @param ConocimientoHabilidadVO
	 * @throws PersistenceException
	 * @return void
	 **/
	public void agregarConocHab(long idCandidato,
			ConocimientoHabilidadVO conocHabVO) throws PersistenceException;

	/**
	 * Actualiza conocimiento o habilidad de un candidato. 
	 * Afecta las tablas:
	 * CANDIDATO_CONOCIM_HABILIDAD
	 * @author Sergio Téllez Vázquez
	 * @since 26/06/2012
	 * @param ConocimientoHabilidadVO
	 * @throws PersistenceException
	 * @return int
	 **/
	public int actualizarConocHab(ConocimientoHabilidadVO conocHabVO) throws PersistenceException;	
	
	
	public int guardarCandidatoHabilidades(long idCandidato, long[] habilidades);
	
	/**
	 * Borra un conocimiento o habilidad de un candidato. 
	 * Afecta las tablas:
	 * CANDIDATO_CONOCIM_HABILIDAD
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @throws PersistenceException
	 * @return void
	 **/
	public void borrarConocHab(long idCandidatoConocimHabilidad)
			throws PersistenceException;
	
	public void borrarCandidatoConocimHabilidad(long idCandidato);
	
	/**
	 * Actualiza o crea una computacion avanzada de un candidato. 
	 * Afecta las tablas:
	 * CANDIDATO_COMPU_AVANZADA
	 * @author Felipe Juárez Ramírez
	 * @since 24/03/2011
	 * @param long
	 * @param ComputacionAvanzadaVO
	 * @throws PersistenceException
	 * @return void
	 **/
	public void agregarCompuAvanzada(long idCandidato,
			ComputacionAvanzadaVO compAvanVO) throws PersistenceException;

	/**
	 * Borra una computacion avanzada de un candidato. 
	 * Afecta las tablas:
	 * CANDIDATO_COMPU_AVANZADA
	 * @author Felipe Juárez Ramírez
	 * @since 24/03/2011
	 * @param long
	 * @throws PersistenceException
	 * @return void
	 **/
	public void borrarCompuAvanzada(long idCandidatoCompuAvanzada)
			throws PersistenceException;

	/**
	 * Activando Candidato
	 * 
	 * @param idCandidato
	 * @return Operacion Exitoza
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public long activarCandidato(long idCandidato)throws PersistenceException;

	/**
	 * Actualiza o crea los datos de experiencia laboral de un candidato.
	 * Afecta las tablas:
	 * PERFIL_LABORAL
	 * HISTORIA_LABORAL
	 * @author Felipe Juárez Ramírez
	 * @since 10/03/2011
	 * @param ExperienciaVO
	 * @throws PersistenceException
	 * @return void
	 **/
	public ExperienciaVO registrarExperiencia(ExperienciaVO experiencia)
			throws PersistenceException;
	
	/**
	 * Actualiza o crea una historia laboral de un candidato.
	 * Afecta las tablas:
	 * HISTORIA_LABORAL
	 * @author Felipe Juárez Ramírez
	 * @since 25/03/2011
	 * @param long
	 * @param HistoriaLaboralVO
	 * @throws PersistenceException
	 * @return void
	 **/
	public void agregarHistLaboral(long idCandidato,
			HistoriaLaboralVO histLaboralVO) throws PersistenceException;

	/**
	 * Borra un historial laboral de un candidato. 
	 * Afecta las tablas:
	 * HISTORIA_LABORAL
	 * @author Felipe Juárez Ramírez
	 * @since 25/03/2011
	 * @param long
	 * @throws PersistenceException
	 * @return void
	 **/
	public void borrarHistLaboral(long idHistorialLaboral)
			throws PersistenceException;

	public void borrarHistoriaLaboral(long idCandidato);
	
	/**
	 * Actualiza o crea los datos de expectavas laborales y de ubicación de un 
	 * candidato.
	 * Afecta las tablas:
	 * PERFIL_LABORAL
	 * EXPECTATIVA_LABORAL
	 * EXPECTATIVA_LUGAR
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 10/03/2011
	 * @param ExpectativaVO
	 * @throws PersistenceException
	 * @return void
	 **/
	public ExpectativaVO registrarExpectativa(ExpectativaVO expectativa)
			throws PersistenceException;
	
	/**
	 * Actualiza o crea una expectativa laboral de un candidato.
	 * Afecta las tablas:
	 * EXPECTATIVA_LABORAL
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @param ExpectativaLaboralVO
	 * @throws PersistenceException
	 * @return void
	 **/
	public void agregarExpecLaboral(long idCandidato,
			ExpectativaLaboralVO expecLaboralVO) throws PersistenceException;
	
	/**
	 * Borra una expectativa laboral de un candidato. 
	 * Afecta las tablas:
	 * EXPECTATIVA_LABORAL
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @throws PersistenceException
	 * @return void
	 **/
	public void borrarExpecLaboral(long idExpectativaLaboral)
			throws PersistenceException;
	
	/**
	 * Actualiza o crea una expectativa laboral de un candidato.
	 * Afecta las tablas:
	 * EXPECTATIVA_LUGAR
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @param ExpectativaLugarVO
	 * @throws PersistenceException
	 * @return void
	 **/
	public void agregarExpecLugar(long idCandidato,
			ExpectativaLugarVO expecLugarVO) throws PersistenceException;
	
	/**
	 * Borra una expectativa laboral de un candidato. 
	 * Afecta las tablas:
	 * EXPECTATIVA_LABORAL
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @throws PersistenceException
	 * @return void
	 **/
	public void borrarExpecLugar(long idExpectativaLugar)
			throws PersistenceException;
	
	/**
	 * Validarmos si la curp ya fue registrada
	 * @param candidatoVo
	 * @return Boolean
	 */
	public Boolean repetidaCurp(String curp);
	
	/** Validamos Si el usuario se encuentra en EstatusInactivo
	 * @param candidatoVo
	 * @return
	 * @throws PersistenceException
	 */
	//public Boolean usuarioInactivo(ConfirmacionRegistroVo confirmacionRegistroVo)throws PersistenceException,BusinessException;
	
	
	/**
	 * Actualiza idUsuario en candidato
	 * @param idCandidato
	 * @param idUsuario
	 * @throws PersistenceException
	 */	
	public void updateIdUsuario(CandidatoVo candidatoVo, int idUsuario) throws PersistenceException;

	public CandidatoVo find(long idCandidato) throws PersistenceException;	
	
	/** Otorga el valor de IdCandidato
	 * @param idUsuario
	 * @return
	 */
	public long idCandidatoPorUsuario(long idUsuario);
	
	/**
	 * Método que obtiene el contador del candidato
	 * @param idCandidato
	 * @return CandidatoVerDetalleVO
	 * @throws SQLException 
	 */
	public CandidatoVerDetalleVO obtenerCandidatoVerDetalle(long idCandidato, int anio, int mes) throws SQLException;

	/**
	 * Método para actualizar el contador del candidato
	 * @param vo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException
	 */
	public int actualizarCandidatoVerDetalle(CandidatoVerDetalleVO vo) throws SQLException;
	
	/**
	 * Método que crea un contador para el candidato
	 * @param vo
	 * @return 
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException
	 */
	public int crearCandidatoVerDetalle(CandidatoVerDetalleVO vo) throws SQLException;
	
	/** Otorga el valor de Evalua CV
	 * @param idUsuario
	 * @return
	 */
	public void updateCV(long idCandidato, int estatusCV) throws PersistenceException;
	
	/** Establece el estilo para desplegar el CV del candidato
	 * @param idCandidato long
	 * @param estiloCv int
	 * @return
	 */
	public void updateCVStyle(long idCandidato, int estiloCv) throws PersistenceException;
	
	
	public CandidatoVo consultaPorCURP(String curp) throws PersistenceException;

	public boolean esNSSUnico(String nss) throws PersistenceException;


	public void actualizaCorreoElectronico(long idCandidato, String correoElectronico) throws PersistenceException;

	/** Inactivar al candidato
	 * @param idCandidato
	 * @return
	 * @throws PersistenceException
	 */
	public long inactivarCandidato(long idCandidato) throws PersistenceException;	
	
	/** Inactivar al candidato
	 * @param idCandidato
	 * @param idMotivoDesactivacion
	 * @param detalleDesactivacion
	 * @return
	 * @throws PersistenceException
	 */
	public long inactivarCandidato(long idCandidato, int idMotivoDesactivacion, String detalleDesactivacion) throws PersistenceException;
	
	public void actualizaEstatusCandidato(long idCandidato, int estatus);
	
	public void eliminarDetalleDesactivacion(long idCandidato);
	
	/** El id corresponde al correo electronico
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	public Boolean idCorreo(long idCandidato,String correo)throws PersistenceException;

	public CandidatoVo consultaCandidatoPorCorreo(String correoElectronico) throws PersistenceException;

	public CandidatoVo consultaCandidato(long idUsuario);
	
	/**
	 * Regresa la lista de Candidatos que tiene un Número de Seguro Social ó
	 * Número de Crédito INFONAVIT registrado.
	 * 
	 * @return List<CandidatoVo>
	 * @throws PersistenceException
	 */
	public List<CandidatoVo> consultaCandidatoRegByInfonavit() throws PersistenceException;
	
	public CandidatoVo findById(long idCandidato) throws PersistenceException;
	
	public long registrarExpectativaLaboral(long idCandidato, ExpectativaLaboralVO expLaboralVO);
	
	public void borrarExpectativaLaboral(long idCandidato);

	public long registraCandidatoPublicidad(String curp, String cookie, String parametro, int idTipoIngreso);
	
	public void actualizaFechaConfirma(long idCandidato, Date fechaConfirma) throws PersistenceException;

	public List<Long> consultaHabilidades(long idCandidato);

	public List<Candidate> consultaCandidatoAIndexar(long idCandidato) throws SQLException;
	
	public List<Candidate> consultaCandidatoAIndexar(List<Long> idsCandidato) throws SQLException;

	public int candidatesToIndex();

	public List<Long> candidates(int lowerLimit, int upperLimit);
	
	public void actualizaFechaUltimoAcceso(long idCandidato) throws PersistenceException;
		
	public List<ResultadoBusquedaCandidatosVO> busquedaEspecificaCandidatos(BusquedaCandidatosVO vo) throws SQLException;

	public List<CandidatoVo> buscarCandidatosQuery(List<Long> indices);

	public CandidatoVo findPpcSdTermsAndConditionsData(final long idUsuario);	
	
	public void actualizaRegistroPPC(long idCandidato, Integer ppcEstatus, Date ppcFechaInscripcion, Integer ppcAceptacionTerminos, Integer ppcEstatusIMSS, Date ppcFechaBajaIMSS, String ppcTipoContratoIMSS, String nss) throws PersistenceException;	
	
	public int actualizaEstatusPPC(long idCandidato, Integer ppcEstatus, Integer ppcIdMotivoFuera) throws PersistenceException;

	public boolean consultarPermisoGeolocalizacion(long idCandidato)throws BusinessException;

	public void actualizaEstatusGeoreferencia(long idCandidato, boolean estatus) throws PersistenceException;
	
	public int consultaEstatusCandidato(long idCandidato);

	public List<ExpectativaLaboralVO> expectativaLaboralList(long idCandidato, int principal);
}
