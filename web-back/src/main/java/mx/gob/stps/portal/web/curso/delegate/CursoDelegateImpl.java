package mx.gob.stps.portal.web.curso.delegate;

import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_CURSO;

import java.util.List;

import mx.gob.stps.portal.core.curso.service.CursoAppServiceRemote;
import mx.gob.stps.portal.core.curso.vo.CursoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;

public class CursoDelegateImpl implements CursoDelegate {
	
	private static CursoDelegate instance = new CursoDelegateImpl();

	public static CursoDelegate getInstance() {
		return instance;
	}

	private CursoAppServiceRemote getCursoAppService() throws ServiceLocatorException {
		CursoAppServiceRemote ejb = (CursoAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_CURSO);
		return ejb;
	}
	
	@Override
	public List<CursoVO> getCursos() throws ServiceLocatorException {
		return getCursoAppService().getCursos();
	}

	@Override
	public List<CursoVO> busquedaCurso(String curso, String plantel,int idEntidad) throws ServiceLocatorException {
		return getCursoAppService().busquedaCurso(curso,plantel,idEntidad);
	}

	

	

}
