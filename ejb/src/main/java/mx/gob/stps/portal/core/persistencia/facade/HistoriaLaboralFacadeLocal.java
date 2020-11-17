package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;

import mx.gob.stps.portal.persistencia.entity.HistoriaLaboral;
import mx.gob.stps.portal.persistencia.facade.TemplateFacade;
import mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.utils.converter.exceptions.NotFoundAnnotationException;


@Local
public interface HistoriaLaboralFacadeLocal extends TemplateFacade<HistoriaLaboral> {
	
	public List<HistoriaLaboralVO> getByIdCandidato(long idCandidato);

	public int borrar(Long idCandidato);

	public long create(HistoriaLaboralVO historiaLaboralVO) throws InstantiationException, NoSuchFieldException, IllegalAccessException, NotFoundAnnotationException;

}
