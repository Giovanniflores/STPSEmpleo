package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.persistencia.entity.CalculadoraCandidato;

@Local
public interface CalculadoraCandidatoFacadeLocal {

	/**
	 * Guarda un regisro en la tabla Calculadora_candidato
	 * @param idCandidato el id del candidato
	 * @param suma el monto mensual al que ascienden los gastos del candidato
	 * @throws PersistenceException si hay un error al guardar el registro
	 */
	public void save(long idCandidato, double suma) throws PersistenceException;
	
	/**
	 * Busca un registro en la tabla calculadora_candidato 
	 * @param idPropietario el id del candidato o empresa
	 * @return el objeto con los datos de la entidad calculadora candidato si no encuentra
	 * un registro con el id que recibe como parametro regresa null
	 */
	public CalculadoraCandidato findCalculadoraCandidato(long idPropietario);
	
	/**
	 * Actualiza un regisro en la tabla Calculadora_candidato
	 * @param idCandidato el id del candidato
	 * @param suma el monto mensual al que ascienden los gastos del candidato
	 * @throws PersistenceException si hay un error al actualizar el registro
	 */
	public void update(long idCandidato, double suma) throws PersistenceException;
}
