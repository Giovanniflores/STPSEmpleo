/**
 * 
 */
package mx.gob.stps.portal.core.testimonio.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceLocal;
import mx.gob.stps.portal.core.infra.data.ConexionFactory;
import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.exception.MailServiceImpl;
import mx.gob.stps.portal.core.infra.mail.vo.DestinatarioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.oferta.detalle.helper.Utils;
import mx.gob.stps.portal.core.persistencia.facade.PollCommentFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.SugerenciaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.TestimonioFacadeLocal;
import mx.gob.stps.portal.core.testimonio.dao.TestimonioDAO;
import mx.gob.stps.portal.core.testimonio.service.TestimonioAppServiceRemote;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;

/**
 * @author concepcion.aguilar
 * Implementacion de la interfaz TestimonioAppServiceRemote en la cual esta la documentacion 
 * de los metodos heredados
 */
@Stateless(name = "TestimonioAppService", mappedName = "TestimonioAppService")
public class TestimonioAppService implements TestimonioAppServiceRemote {
	private final static int VALIDAR_REGISTRO = 1;
	private final static int VALIDAR_CONSULTA = 2;
	private final PropertiesLoader properties = PropertiesLoader.getInstance();
	
	@EJB private TestimonioFacadeLocal testimonioFacadeLocal;
	@EJB private AutorizacionAppServiceLocal autorizacionAppService;
	@EJB private SugerenciaFacadeLocal sugerenciaFacadeLocal;
	@EJB private PollCommentFacadeLocal pollcommentFacadeLocal;

	@Override
	public long registraDatos(TestimonioVO vo) throws PersistenceException {
		
		vo.setEstatus(ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion()); // Se coloca como pendiente hasta que sea autorizado por un publicador
		vo.setFechaAlta(Calendar.getInstance().getTime());
		
		if (!validaVO(vo, VALIDAR_REGISTRO))throw new IllegalArgumentException("Identificador de registro invalido");

		long idTestimonio = testimonioFacadeLocal.save(vo);
		
		if (vo.getIdTipoPropietario() == TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario()) {
			autorizacionAppService.registraTestimonioCandidatoPorValidar(idTestimonio, vo.getIdPropietario());
		
		}else if (vo.getIdTipoPropietario() == TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario()) {
			autorizacionAppService.registraTestimonioEmpresaPorValidar(idTestimonio, vo.getIdPropietario());
		}

		return idTestimonio;
	}

	@Override
	public TestimonioVO recuperaDatos(TestimonioVO vo) throws SQLException{
		if (!validaVO(vo, VALIDAR_CONSULTA))throw new IllegalArgumentException("Faltan parametros para realizar la operacion");
		TestimonioDAO testimonioDAO = new TestimonioDAO();
		return testimonioDAO.datosTestimonio(vo);
	}
	
	@Override
	public TestimonioVO recuperaDatosIndex() throws SQLException{
		TestimonioVO vo = null;
		Connection conn = null;
		Context context = null;
		try {
    		ConnectionWraper wraper = ConexionFactory.getInstance().getConnection();
    		conn = wraper.getConnection();
    		context = wraper.getContext();

			TestimonioDAO testimonioDAO = new TestimonioDAO(conn);
			
			vo = testimonioDAO.getTestimonioAleatorio();
			
			if (vo.getDescripcion() != null) {
				vo = testimonioDAO.datosTestimonioIndex(vo);
			} else {
				vo.setNombre(properties.getProperty("testimonio.nombre"));
				vo.setDescripcion(properties.getProperty("testimonio.descripcion"));
			}

		} catch (SQLException e1) {
			e1.printStackTrace(); throw e1;
		} catch (Exception e1) {
			e1.printStackTrace(); throw new SQLException(e1);
		} finally {
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
			
			if (context!=null){
				System.out.println("Cerrando context");
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}

		return vo; 
	}

	/**
	 * Realiza las validaciones de los parametros de entrada para los metodos de testimonio
	 * @param vo parametros de entrada dependen del metodo
	 * @param opcion tipo de validacion
	 * @return true si los parametros son correctos de lo contrario false
	 */
	private boolean validaVO(TestimonioVO vo, int opcion){
		boolean result = true;
		switch (opcion) {
		case  1:
			if(vo.getIdTipoPropietario() <= 0 || vo.getIdPropietario() <= 0 
					|| vo.getDescripcion()== null || vo.getFechaAlta() == null
					|| vo.getEstatus() <= 0){
				result = false;
			}
			break;
		case 2:
			if(vo.getIdTipoPropietario() <= 0 || vo.getIdPropietario() <= 0)
				result = false;
			break;
		}
		return result;
	}

	public TestimonioVO consultaTestimonio(long idTestimonio) throws PersistenceException {

		if (idTestimonio<=0) throw new IllegalArgumentException("Identificador del testimonio requerido");

		TestimonioVO testimonio = testimonioFacadeLocal.findById(idTestimonio);
		return testimonio;
	}
	
	public long saveSuggestion(long idCategoria, String asunto, String mensaje, String email, String nombre, String apellido1, String apellido2, String telefono, int tipoTelefono) throws PersistenceException {
		ArrayList<String> mails = new ArrayList<String>();
		ArrayList<String> mailsCc = new ArrayList<String>();
		DestinatarioVO destinatarioVO = new DestinatarioVO();
		String sender = properties.getProperty("email.remitente");
		String refers = properties.getProperty("mailing.list.suggestions.refers");
		mensaje = Utils.normalizeDetail(mensaje,1000);
		long result = sugerenciaFacadeLocal.create(idCategoria, asunto, mensaje, email, nombre, apellido1, apellido2, telefono, tipoTelefono);
		if (result > 0 || result	==	-1) {
			try {
				String[] mailing = refers.split(",");
				for (int i = 0; i < mailing.length; i++)
					mails.add(mailing[i]);
				destinatarioVO.setMails(mails);
				destinatarioVO.setMailsCc(mailsCc);
				destinatarioVO.setArea(Utils.getLblCategoriaSugerencia((int)idCategoria));
				destinatarioVO.setLogin(email);
				destinatarioVO.setNombre((nombre + " " + apellido1 + " " + apellido2).trim());
				destinatarioVO.setSistema(telefono);
				String msg = destinatarioVO.getArea() + " de " + destinatarioVO.getNombre() + " con correo electrónico " + destinatarioVO.getLogin() + " y teléfono " + destinatarioVO.getSistema() + " es: " + mensaje; 
				MailServiceImpl.getInstance().enviarCorreo(sender, destinatarioVO.getMails(), destinatarioVO.getMailsCc(), destinatarioVO.getMailsCco(), asunto, msg);
			} catch (MailException e) { e.printStackTrace(); }
		}
		return result;
	}	
	
	public long savePollComment(int item1, int item2, int item3, int item4, int item5, int item6, int item7, int item8, int item9, 
			String description1, String description2, String description3, String description4, String description5) throws PersistenceException{
		long result = pollcommentFacadeLocal.create(item1, item2, item3, item4, item5, item6, item7, item8, item9, 
				description1, description2, description3, description4, description5);
		return result;		
	}

}
