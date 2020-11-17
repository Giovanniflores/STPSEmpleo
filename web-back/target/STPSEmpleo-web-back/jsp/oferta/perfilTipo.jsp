<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String context = request.getContextPath() +"/";
	String vProxy = "http://orangoo.com/newnox?lang=";
	pageContext.setAttribute("vProxy",vProxy);
%>
		<script type="text/javascript">
			dojo.require("dijit.dijit"); // loads the optimized dijit layer
			dojo.require("dijit.form.Form");
			dojo.require("dijit.Dialog");
			dojo.require("dijit.form.Button");
			dojo.require("dijit.form.ComboBox");
			dojo.require("dojo.data.ItemFileReadStore");
			dojo.require("dijit.form.Textarea");
			dojo.require("dijit.form.TextBox");
			dojo.require("dijit.form.ValidationTextBox");
			dojo.require("dijit.form.DateTextBox");
			dojo.require("dijit.form.FilteringSelect");
			dojo.require("dijit.form.MultiSelect");
			dojo.require("dijit.form.CheckBox");
			dojo.require('dojo.parser');
			function doSubmit() {
				var id = dojo.byId('id').value;
				var idOcupacion = dojo.byId('idOcupacion').value;
				var tituloOferta = dojo.byId('tituloOferta').value;
				var ocupacionDeseada = dojo.byId('ocupacionDeseada').value;
		    	var params = '&idTypeProfile='+id+'&idOcupacion='+dojo.byId('idOcupacion').value+'&tituloOferta='+dojo.byId('tituloOferta').value+'&ocupacionDeseada='+dojo.byId('ocupacionDeseada').value;
		    	window.opener.location.replace('<%=context%>ofertaEmpleo.do?method=init'+params);
		  		self.close ();
		  	}
			function doSubmitCancelar() {
				window.opener.location.replace('<%=context%>ofertaEmpleo.do?method=init');
		  		self.close ();
			}
		</script>
		<style type="text/css">
			@import "dojotoolkit/dojo/resources/dojo.css";
			@import "dojotoolkit/dijit/themes/claro/claro.css";
			@import "css/estilos_formularios.css";
			@import "css/estilos_plantilla.css";
			body {background-color: #ffffff;}
			#wrapper {padding: 10px;}
			#cuerpo_int #contenido_principal {width: auto !important;}
		</style>
		<div dojoType="dojo.data.ItemFileReadStore" jsId="experienciaTotalStore"     url="<%=context%>ofertaEmpleo.do?method=experiencia" urlPreventCache="true"></div>
		<div dojoType="dojo.data.ItemFileReadStore" jsId="experienciaTotalStore1"    url="<%=context%>ofertaEmpleo.do?method=experiencia" urlPreventCache="true"></div>
		<form name="perfilTipo" id="perfilTipo" action="ofertaEmpleo.do" method="post" dojoType="dijit.form.Form">
			<input type="hidden" name="id" id="id" value="${ofertaEmpleoForm.idperfilTipo}" dojoType="dijit.form.TextBox" />
			<input type="hidden" name="ocupacionDeseada" id="ocupacionDeseada" value="${ofertaEmpleoForm.ocupacion}" dojoType="dijit.form.TextBox" />
			<input type="hidden" name="tituloOferta" id="tituloOferta" value="${ofertaEmpleoForm.tituloOferta}" dojoType="dijit.form.TextBox" />
			<input type="hidden" name="idOcupacion" id="idOcupacion" value="${ofertaEmpleoForm.idOcupacion}" dojoType="dijit.form.TextBox" />
			<img src="css/images/m_empleoGob.png" style="width:80%" alt="Portal del empleo. Llama 01 800 841 2020 - www.empleo.gob.mx">
			<div style="border-top: 1px solid #cccccc; margin-top: 20px;"></div>
			<p class="instruc_01">
				<strong>Detalle Perfil Tipo</strong>
			</p>
			<div>
				<p><strong>Puesto u ocupación </strong>${ofertaEmpleoForm.ocupacion}</p>
				<div class="labeled">Funciones y actividades a realizar</div>
				<div>
					<textarea disabled cols="70" rows="4" name="funciones" id="funciones" trim="true"
						onchange="return maximaLongitud(this,2000)" onKeyUp="return maximaLongitud(this,2000)"
						onblur="return maximaLongitud(this,2000)" class="textGoogie">${fn:trim(fn:replace(ofertaEmpleoForm.funciones, '\\n|\\r|\\t', ''))}</textarea>
				</div>
				<div class="labeled">Equipo y tecnología a manejar</div>
				<div>
					<textarea disabled cols="70" rows="4" name="equipoTecnologia" id="equipoTecnologia" onchange="return maximaLongitud(this,2000)"
						onKeyUp="return maximaLongitud(this,2000)" trim="true" onblur="return maximaLongitud(this,2000)"
						class="textGoogie">${fn:trim(fn:replace(ofertaEmpleoForm.equipoTecnologia, '\\n|\\r|\\t', ''))}</textarea>
				</div>
				<div class="labeled"><strong>Habilidades y actitudes</strong></div>
				<ul>
					<c:forEach var="habilidadOpc" items="${opcHabilidades}" varStatus="index">
						<c:forEach var="habilidadSaved" items="${ofertaEmpleoForm.idHabilidad}">					
							<c:if test="${habilidadOpc.idCatalogoOpcion == habilidadSaved}">
								<li>${habilidadOpc.opcion}</li>
							</c:if>
						</c:forEach>
					</c:forEach>	
				</ul>
				<p>&nbsp;</p>
				<p>
					<strong>Conocimientos:</strong>&nbsp;${ofertaEmpleoForm.conocimiento}
				</p>
				<p>&nbsp;</p>
				<p>
					<strong>Experiencia:</strong>
					<select id="idExperienciaConocimientoPric"
							name="idExperienciaConocimientoPric" required="false" disabled
						missingMessage="Es necesario indicar los años de experiencia del conocimiento."
						store="experienciaTotalStore" dojotype="dijit.form.FilteringSelect"
						value="${ofertaEmpleoForm.idExperienciaConocimiento}">
					</select>
				</p>
				<c:forEach var="conocimiento" items="${ofertaEmpleoForm.conocimientos}" varStatus="loop">
					<p>
						<strong>Conocimiento:</strong>${conocimiento.descripcion}
						<strong>Experiencia:</strong>
						<select id="idExperienciaConocimientoPric${loop.index}" name="idExperienciaConocimientoPric${loop.index}"
							required="false" disabled
							missingMessage="Es necesario indicar los años de experiencia del conocimiento."
							store="experienciaTotalStore" dojotype="dijit.form.FilteringSelect"
							value="${conocimiento.idExperiencia}">
						</select>
					</p>
				</c:forEach>
				<div class="clearfix"></div>
				<div class="form_nav">
					<input id="btnAceptar" type="button" value="Aceptar" onclick="doSubmit();"/>&nbsp;&nbsp;<input id="btnCancelar" type="button" value="Cancelar" class="boton_naranja" onclick="doSubmitCancelar();"/>
				</div>
			</div>
		</form>