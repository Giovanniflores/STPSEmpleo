package mx.gob.stps.portal.core.persistencia.facade;
import java.util.List;

import javax.ejb.Local;

import mx.gob.stps.portal.core.curso.vo.CursoVO;

@Local
public interface CursoFacadeLocal {

	List<CursoVO> getCursos();

	List<CursoVO> busquedaCurso(String curso, String plantel, int idEntidad);

}
