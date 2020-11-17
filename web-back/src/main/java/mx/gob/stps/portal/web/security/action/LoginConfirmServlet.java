package mx.gob.stps.portal.web.security.action;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LoginConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 2720914707481384252L;

	private static final Logger logger = Logger.getLogger(LoginConfirmServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		confirm(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		confirm(request, response);
	}

	private void confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ServletOutputStream output = response.getOutputStream();
		response.setContentType("text/html");
		String xml = null;
		
		String paramEncoded = request.getParameter("nuance");
		
		//logger.info(" ********************** Se confirma usuario :"+ paramEncoded +" Session ("+ (session!=null?session.getId():"Sin Session") +")********************** ");
		//logger.info(" Thread:"+ Thread.currentThread().getId() +" "+ Thread.currentThread().getName());

		if (paramEncoded!=null && !paramEncoded.isEmpty()){

			String userName = Utils.decodifica(paramEncoded);
			//logger.info(" ********************** Verificando identidad de :"+ userName +" ********************** ");
			
			if (userName!=null && !userName.trim().isEmpty()){

				SecutityDelegate services = SecutityDelegateImpl.getInstance();
				try {
					UsuarioVO usuario = services.consultaUsuarioPorLogin(userName);
					
					if (usuario!=null){
						long idPropietario = services.consultaPropietario(usuario.getIdUsuario());

						String login    = usuario.getUsuario();
						String correo    = (usuario.getCorreoElectronico()!=null?usuario.getCorreoElectronico():"");
						String nombre    = (usuario.getNombre()!=null?usuario.getNombre():"");
						String apellido1 = (usuario.getApellido1()!=null?usuario.getApellido1():"");
						String apellido2 = (usuario.getApellido2()!=null?usuario.getApellido2():"");
						
						if (usuario.getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()){
							EmpresaVO empresa = services.consultaEmpresa(idPropietario);
							
							if (empresa!=null){
								if (empresa.getIdTipoPersona() == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
									nombre = empresa.getNombre();
									apellido1 = empresa.getApellido1();
									apellido2 = empresa.getApellido2();
								}else if (empresa.getIdTipoPersona() == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
									nombre = empresa.getRazonSocial();
									apellido1 = "";
									apellido2 = "";
								}
							}

						} else if (usuario.getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()){
							CandidatoVo candidato = services.consultaCandidato(idPropietario);

							if (candidato!=null){
								nombre = candidato.getNombre();
								apellido1 = candidato.getApellido1();
								apellido2 = candidato.getApellido2();
							}
						}

						// Se acortan los datos para que se puedan mostrar en el encabezado del Portal
						/*nombre = shortName(nombre, apellido1, apellido2);
						apellido1 = "";
						apellido2 = "";*/
						
						xml = getUserResponse(login, correo, nombre, apellido1, apellido2);
						
					}else{
						xml = getErrorResponse();
					}

				} catch (Exception e) {
					logger.error(e);
					xml = getErrorResponse();
				}
			}else{
				xml = getErrorResponse();
			}
		} else {
			xml = getErrorResponse();
		}

		//logger.info(" ********************** Respuesta de confirmacion de usuario ********************** ");
		//logger.info(xml);
		output.print(xml);
	} 

	/*private String getUserResponse(String correo, String nombre, String apellido1, String apellido2){
		if (nombre==null) nombre = "";
		if (apellido1==null) apellido1 = "";
		if (apellido2==null) apellido2 = "";
		
		StringBuilder out = new StringBuilder();
        out.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
        out.append("<user logedin=\"true\">");
        out.append("<login>"+ correo +"</login>"); //Identificador único en este sistema, puede ser el correo electrónico
        out.append("<firstName>"+ nombre +"</firstName>");
        out.append("<lastName>"+ apellido1 +"</lastName>");
        out.append("<middleName>"+ apellido2 +"</middleName>");
        out.append("<email>"+ correo +"</email>");
        out.append("</user>");
		return out.toString();
	}

	private String getErrorResponse(){
        return "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"+
               "<error>ERROR !!!</error>";
	}*/
	
	private String getUserResponse(String login, String correo, String nombre, String apellido1, String apellido2){
    	String xml = null;
    	
    	if (nombre==null) nombre = "";
    	if (apellido1==null) apellido1 = "";
    	if (apellido2==null) apellido2 = "";
    	
    	try {
            Document doc= new DocumentImpl();

            Element root = doc.createElement("user");
            root.setAttribute("logedin", "true");
            
            Element item = doc.createElement("login");
            item.appendChild(doc.createTextNode(login));
            root.appendChild(item);
            
            item = doc.createElement("firstName");
            item.appendChild(doc.createTextNode(nombre));
            root.appendChild(item);
            
            item = doc.createElement("lastName");
            item.appendChild(doc.createTextNode(apellido1) );
            root.appendChild(item);

            item = doc.createElement("middleName");            
            item.appendChild(doc.createTextNode(apellido2));
            root.appendChild(item);

            item = doc.createElement("email");            
            item.appendChild(doc.createTextNode(correo));
            root.appendChild(item);

            doc.appendChild(root);

            xml = serializaXML(doc);

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    	
    	return xml;
    }
    
	private String getErrorResponse(){
    	String xml = null;
    	try {
            Document doc= new DocumentImpl();

            Element root = doc.createElement("error");
            root.appendChild(doc.createTextNode("ERROR !!!"));

            doc.appendChild(root);

            xml = serializaXML(doc);

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    	
    	return xml;
    }
    
    private String serializaXML(Document doc) throws IOException{
        OutputFormat    format  = new OutputFormat(doc, "ISO-8859-1", false);   //Serialize DOM
        StringWriter  stringOut = new StringWriter();        //Writer will be a String
        XMLSerializer    serial = new XMLSerializer(stringOut, format);
        serial.asDOMSerializer();                            // As a DOM Serializer

        serial.serialize(doc.getDocumentElement());

        String xml = stringOut.toString();
    	return xml;
    }

    /*private String shortName(String nombre, String apellido1, String apellido2){
    	if (nombre==null) return "";
    
    	nombre += (apellido1!=null?" "+ apellido1: "") + (apellido2!=null?" "+ apellido2:"");
    	nombre = nombre.trim();
    	
    	if (nombre.length()>25){
    		nombre = nombre.substring(0, 25) +"...";
    	}

    	return nombre;
    }*/

}