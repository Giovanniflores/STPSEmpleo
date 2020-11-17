<%@ page import="mx.gob.stps.portal.web.entrevista.helper.EntrevistaHelper"%>

<%

	String entrevistaCon	= "";
	String contexto = EntrevistaHelper.getContextoChat();

	String idEntrevista 	= (String)request.getParameter("idEntrevista");	
	String nombreUsuario	= (String)request.getParameter("nombreUsuario");
	String nombreCandidato	= (String)request.getParameter("nombreCandidato");
	String idCandidato		= (String)request.getParameter("idCandidato");
	String tipo				= (String)request.getParameter("tipo");
	String IdEmpresa		= (String)request.getParameter("idEmpresa");
	String razonSocial		= (String)request.getParameter("razonSocial");
	String personaContacto	= (String)request.getParameter("personaContacto");
	
	Boolean esValidaLaEntrevista = 	EntrevistaHelper.validarEntrevistaEnLinea(idEntrevista);
	
	if("Empresa".equals(tipo)){
		entrevistaCon = EntrevistaHelper.cadenaRecortada(nombreCandidato) + "," + EntrevistaHelper.cadenaRecortada(razonSocial);
	}else {
		entrevistaCon = EntrevistaHelper.cadenaRecortada(personaContacto) + "," + EntrevistaHelper.cadenaRecortada(razonSocial);
	}	
	 
%>


<div>  
<%if(esValidaLaEntrevista){ %>
<%-- QUITAR LOS CARACTERES ESPECIALES ACENTOS Y Ñ DE DESCRIPCIONES --%>
	<div class="ms-chat">
	   <applet code="class_AppletChat.class"  codebase="<%=contexto%>jsp/entrevistas/" archive="appletChat.jar" width="614" height="350" id="chat1">
	    <param name="anchoApp" value="614"/> <%-- VERIFICAR TAMAÑOS E IGUALAR --%>
	    <param name="altoApp" value="350"/>

		<%-- <param name="host" value="entrevista.empleo.gob.mx"/> --%>
		<%--  Descomentar en produccion  --%> 
		<param name="host" value="172.18.28.3"/>
	    <param name="puerto" value="9494"/>

	    <%-- Nombre de entidad (candidado, empresa (Nombre de Contacto de empresa)) --%>
	    <%-- Debe ser menor o igual de 16 caracteres en caso de ser mayor se agregan (...) --%>
	    <param name="idUsuario" value="<%=nombreUsuario%>"/>
	    <%if(!"Candidato".equals(tipo)){%>	    	
	    	    <param name="idUsuario" value="<%=EntrevistaHelper.cadenaRecortada(personaContacto)%>"/>
	  	<% } else { %>
	    	    <param name="idUsuario" value="<%=EntrevistaHelper.cadenaRecortada(nombreCandidato)%>"/>
	 	<%}%>

	    <%-- Identificador de Cita --%>
	    <param name="idComunidad" value="<%=idEntrevista%>"/>

	    <%-- Titulo Cruzado VERIFICAR TAMAÑO Maximo 
	         (Posible Maximo 63 A PROBAR Y VERIFICAR CUAL TIENE MAS RELEVANCIA POSIBLE CORTAR EL QUE EXCEDA (...)) --%>
	    <param name="header" value="Tienes una entrevista con <%=entrevistaCon%>"/>
	    
	    <param name="formato" value="0-Dialog-ORANGE-12"/>
	    
	    <param name="bg" value="<%=contexto%>images/chat_fondo_interfaz_small.jpg"/>
	    
	    <%-- Imagen Cruzada --%>
	  
	  <%if(!"Candidato".equals(tipo)){%>
	    	<param name="photo" value="<%=contexto%>imageAction.do?method=getImagen&ID_CANDIDATO=<%=idCandidato%>"/>
	  <% } else { %>	  
	    	<param name="photo" value="<%=contexto%>imageAction.do?method=getImagen&ID_EMPRESA=<%=IdEmpresa%>"/>	    	
	  <%}%>
	  
	  </applet>
		</div>
<%} else { %>
	Esta entrev&iacute;sta ya no es v&aacute;lida. Favor de programar una nueva.
<%} %>
</div>