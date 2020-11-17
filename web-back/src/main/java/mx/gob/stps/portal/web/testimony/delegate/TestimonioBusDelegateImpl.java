/**
 * 
 */
package mx.gob.stps.portal.web.testimony.delegate;

import java.sql.SQLException;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.testimonio.service.TestimonioAppServiceRemote;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;

/**
 * @author concepcion.aguilar
 * Impletacion de la Intefaz TestimonioBusDelegate en la cual esta la documentacion de cada metodo heredado
 */
public class TestimonioBusDelegateImpl implements TestimonioBusDelegate {
	/**
	 * Objeto  que sirve para acceder a una sola instancia de esta clase
	 */
	private static TestimonioBusDelegate instance = new TestimonioBusDelegateImpl();
	
	private TestimonioBusDelegateImpl(){}	
	/**
	 * Metodo publico que genera una instancia de esta misma clase
	 * @return
	 */
	public static TestimonioBusDelegate getInstance(){
		return instance;
	}
	
	/**
	 * Service locator que genera un objeto remoto EJB
	 * @return
	 * @throws ServiceLocatorException
	 */
	private TestimonioAppServiceRemote getTestimonioAppService() throws PersistenceException, ServiceLocatorException {
		TestimonioAppServiceRemote ejb = (TestimonioAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_TESTIMONIO);
		return ejb;
	}	
	
	public long registraTestimonio(TestimonioVO vo) throws BusinessException, ServiceLocatorException {
		return getTestimonioAppService().registraDatos(vo);
	}
	
	public TestimonioVO recuperaDatos(TestimonioVO vo) throws SQLException, ServiceLocatorException {	
		return getTestimonioAppService().recuperaDatos(vo);
	}
	
	public TestimonioVO recuperaDatosIndex() throws SQLException, ServiceLocatorException {
		return getTestimonioAppService().recuperaDatosIndex();
	}		
	
	public long saveSuggestion(long idCategoria, String asunto, String mensaje, String email, String nombre, String apellido1, String apellido2, String telefono, int tipoTelefono) throws ServiceLocatorException {
		long result = 0;
		if (idCategoria > 0 && null != mensaje && null != email)
			result = getTestimonioAppService().saveSuggestion(idCategoria, asunto, mensaje, email, nombre, apellido1, apellido2, telefono, tipoTelefono);
		return result;
	}
	
	public long savePollComment(int item1, int item2, int item3, int item4, int item5, int item6, int item7, int item8, int item9, 
			String description1, String description2, String description3, String description4, String description5) throws ServiceLocatorException {
		long result = 0;
		result = getTestimonioAppService().savePollComment(item1, item2, item3, item4, item5, item6, item7, item8, item9, 
				description1, description2, description3, description4, description5);
		return result;
	}
}
