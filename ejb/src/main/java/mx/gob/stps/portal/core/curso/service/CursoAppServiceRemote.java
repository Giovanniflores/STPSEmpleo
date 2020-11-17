package mx.gob.stps.portal.core.curso.service;
import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.curso.vo.CursoVO;

@Remote
public interface CursoAppServiceRemote {

	List<CursoVO> getCursos();

	List<CursoVO> busquedaCurso(String curso, String plantel, int idEntidad);

}
