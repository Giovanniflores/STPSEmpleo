package mx.gob.stps.portal.web.curso.delegate;

import java.util.List;

import mx.gob.stps.portal.core.curso.vo.CursoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;


public interface CursoDelegate {

	List<CursoVO> getCursos() throws ServiceLocatorException;

	List<CursoVO> busquedaCurso(String curso, String plantel, int idEntidad) throws ServiceLocatorException;



}
