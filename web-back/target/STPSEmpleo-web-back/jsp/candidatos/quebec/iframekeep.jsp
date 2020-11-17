<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.PropertiesLoader;"%>
<%
	String context = request.getContextPath() +"/";
	PropertiesLoader properties = PropertiesLoader.getInstance();
	String domain = properties.getProperty("app.domain.portal") + context;
	String uri = domain + "jsp/candidatos/quebec/resultat.html";
%>
    <div class="sidebar">
        <!-- ********************************************************************* -->
        <!-- Iframe à intégrer sur le site du client. Valeur de "lang" possibles : fr, en -->
        <!-- ********************************************************************* -->
        <iframe style="width: 100%; height: 500px; border: 0px; display: block; " src="<%=context %>jsp/candidatos/quebec/application.html?lang=fr&domain=<%=domain %>&uri=<%=uri %>&name=${registroCandidatoForm.nombre}&lastname=${registroCandidatoForm.apellido1}&email=${registroCandidatoForm.correoElectronico}&sexe=${registroCandidatoForm.genero}&user=${registroCandidatoForm.usuario}"></iframe>
    </div>