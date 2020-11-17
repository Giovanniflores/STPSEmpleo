package mx.gob.stps.portal.core.persistencia.facade;


import java.util.Date;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.CurriculumVo;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;


/**
 * Define el medio para la persistencia de PerfilLaboral
 * 
 * @author jose.jimenez
 *
 */
@Local
public interface PerfilLaboralFacadeLocal {

	/**
	 * Almacena un registro de PerfilLaboral
	 * @param vo instancia de {@code mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVO}
	 * @return identificador del candidato asociado al perfil laboral
	 * @throws PersistenceException lanzada en caso de error durante el acceso a la capa de persistencia
	 */
	public long save(PerfilLaboralVo vo) throws PersistenceException;
	
	public boolean tienePerfilLaboral(long idCandidato);
	
	public void actualizaRegistroPerfilLaboral(PerfilLaboralVo vo) throws PersistenceException;
	
	/**
	 * Consulta los datos del perfil laboral asociado a un candidato
	 * @param idCandidato identificador del candidato
	 * @return instancia de {@code mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVO}
	 * @throws PersistenceException lanzada en caso de error durante el acceso a la capa de persistencia
	 */
	public PerfilLaboralVo find(long idCandidato) throws PersistenceException;

	/**
	 * Almacena la foto de un candidato
	 * @param idCandidato identificador del candidato a actualizar
	 * @param fotografia la imagen a almacenar
	 */
	public void guardarFoto(long idCandidato, byte[] fotografia);
	
	/**
	 * Actualiza los datos del video curriculum de un candidato
	 * @param idCandidato identificador del candidato a actualizar
	 * @param vo contiene los datos del video curriculum a actualizar
	 */
	public void actualizarCurriculum(long idCandidato, CurriculumVo vo);

	public void actualizarEstatus(long idCandidato, int estatusVideoc) throws PersistenceException;

	/**
	 * Actualiza el medio por el cual recibe ofertas el candidato
	 * @param idCandidato identificador del candidato a actualizar
	 * @param idRecibeOferta identificador del medio por el cual recibira ofertas el candidato
	 */
	public void actualizarIdRecibeOferta(long idCandidato, long idRecibeOferta);

	public long registraOtroMedio(long idCandidato, long idMedioBusqueda, Date fechaAlta);

	public void eliminaOtrosMedios(long idCandidato);

	public byte[] consultaFotografia(int idPropietario);
}
