<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<%
String context = request.getContextPath() +"/";
String urlAbriendoEspacios = (String)request.getAttribute("URL_ABRIENDO_ESPACIOS");
%>

<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div class="tab_block">
	<div align="left" style="display:block;" id="returnME">
		<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');">
			<strong>Ir al inicio de Mi espacio</strong>
		</a>
	</div>
	<div class="Tab_espacio">
		<h3>Administrar mis ofertas de empleo</h3>
		<div class="subTab">
			<ul>
				<li><a href="<c:url value="/entrevistaProgramada.do?method=entrevistaProgramadaEnlineaEmpresa"/>">Entrevistas en línea</a></li>
				<li class="curSubTab"><span>Acerca del portal Abriendo Espacios</span></li>
			</ul>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<div class="capacitacionMixta margin_top_40">
	<form id="acercaAbriendoEspaciosForm" name ="acercaAbriendoEspaciosForm" action="acercaAbriendoEspacios.do?method=registrarOferta" method="post" dojoType="dijit.form.Form">
		<div class="acerca_AEspacios">
			<div class="logo_AEspacios"></div>
			<h3>Acerca del programa Abriendo espacios</h3>
			<p><strong>Abriendo Espacios</strong> es un programa que apoya la contratación de personas con discapacidad y adultos mayores, para aumentar sus oportunidades de empleo y que, al mismo tiempo, las empresas se beneficien de sus talentos.</p>
			<p>En algunos casos, se evalúan las habilidades y competencias de las personas para dar certeza al empleador de que pueden cubrir, adecuadamente, su vacante.</p>
			<p>Por medio del programa <strong>Abriendo Espacios</strong>, se ofrece apoyo y asesoría para la contratación de personas con discapacidad y adultos mayores; así como en la elaboración de las descripciones de puestos que puedan ser adaptados para cada perfil.</p>
			<p>Por medio del Portal del Empleo, puedes crear ofertas laborales dirigidas a personas con discapacidad o a adultos mayores. Si deseas consultar las ofertas publicadas, visita el Portal <em>Abriendo Espacios</em>.</p>
			<div class="form_nav">        			    				                                          
				<input type="button" onclick="javascript:submit();" class="boton" value="Crear una oferta de empleo" id="btnCrearOferta">
				<input type="button" onclick="javascript:window.location = '<%=urlAbriendoEspacios%>';" class="boton" value="Ir al portal Abriendo Espacios" id="btnIrAbriendoEspacios">  
			</div>			
		</div>
	</form>	
</div>
