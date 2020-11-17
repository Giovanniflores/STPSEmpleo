package mx.gob.stps.portal.core.curso.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import mx.gob.stps.portal.core.curso.service.CursoAppServiceRemote;
import mx.gob.stps.portal.core.curso.vo.CursoVO;
import mx.gob.stps.portal.core.persistencia.facade.CursoFacadeLocal;

/**
 * Session Bean implementation class CursoAppService
 */
@Stateless(mappedName = "CursoAppService")
public class CursoAppService implements CursoAppServiceRemote {

	@EJB CursoFacadeLocal cursoFacade;
    
	
	/**
     * Default constructor. 
     */
	
	
    public CursoAppService() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public List<CursoVO> getCursos() {
		
		return cursoFacade.getCursos();
	}


	@Override
	public List<CursoVO> busquedaCurso(String curso, String plantel,int idEntidad) {
		return cursoFacade.busquedaCurso(curso,plantel,idEntidad);
	}
    
   
    
    

}
