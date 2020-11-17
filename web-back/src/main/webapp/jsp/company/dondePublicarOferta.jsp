<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="fecha" scope="page" class="java.util.Date"/>
<fmt:setBundle basename="MessageResources" var="portalbundle"/>

<%String context = request.getContextPath() +"/";
pageContext.setAttribute("context", context);%>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.Button");
	
	function alertMsg(msg){
		alert(msg);
	}
	
  	function displayErrorMsg(textBox, errmsg){
  		var originalValidator = textBox.validator;
  		textBox.validator = function() {return false;};
  		textBox.validate();
  		textBox.validator = originalValidator;
  		
  		dijit.showTooltip(
  			    errmsg,
  			    textBox.domNode, 
  			    textBox.get("tooltipPosition"),
  			    !textBox.isLeftToRight()
  		);
  	}	
	
	function crearOfertaEnAbriendoEspacios(){
		document.registroEmpresaForm.action='dondePublicar.do?method=registrarOfertaAbriendoEspacios';
		document.registroEmpresaForm.submit();
	}
	
	function crearOfertaEnPortalEmpleo(){
		//document.registroEmpresaForm.action='registro-unico.do?method=redirectRegistraOfertaRU';
		//document.registroEmpresaForm.submit();
		window.open('registro-unico.do?method=redirectRegistraOfertaRU','_blank');
	}	
	
	function crearOfertaEnBecate(){
		document.registroEmpresaForm.action='dondePublicar.do?method=registrarOfertaBecate';
		document.registroEmpresaForm.submit();
	}	
		
</script> 
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>


<form name="registroEmpresaForm" id="registroEmpresaForm" method="post" action="dondePublicar.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/>
	
<div class="tab_block">
	<div align="left" id="returnME" style="display:block;">
		<a href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');" class="expand">
			<strong>Ir al inicio de Mi espacio</strong>
		</a>
	</div>
	<div class="Tab_espacio">
		<h3>Administrar mis ofertas de empleo</h3>
		<div class="subTab">
			<ul>
				<li class="curSubTab">
					<span>Crear oferta de empleo</span>
				</li>
				<li>
					<a href="<c:url value="/OfertaNavegacion.do?method=init"/>">Mis ofertas de empleo</a>
				</li>
				<li>
					<a href="<c:url value="/RecuperaOfertas.do?method=init"/>">Utiliza oferta como plantilla</a>
				</li>
				<li>
					<a href="<c:url value="/reporteOfertasEmpresa.do?method=init"/>">Reporte de ofertas de empleo</a>
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
	
		<div class="generalesOferta bloque" style="width: 793px">
			<h3><span>¿Dónde deseas publicar la oferta de empleo?</span></h3>
			<div class="un_medio certificacion margin-r_20 fl">
				<a id="portalEmpleo" href="javascript:crearOfertaEnPortalEmpleo()"><img src="images/logo-portal-empleo.jpg" alt="Portal del empleo" /></a>
				<p><strong>Portal del empleo</strong>
				Crear oferta de empleo para candidatos<br> en general<br><br><br>
				<input type="button" value="Crear oferta" name="crearOfertaPortalEmpleo" class="boton" onclick="javascript:crearOfertaEnPortalEmpleo()"/>
				</p>					
			</div>

						
			<!-- COMENTAR PARA PROD -->	
			<div class="un_medio certificacion fl">
				<a id="abriendoEspacios" href="javascript:crearOfertaEnAbriendoEspacios()"><img src="images/logo-ae.jpg" alt="Abriendo Espacios" /></a>
				<p><strong>Programa Abriendo Espacios</strong>
				Crear oferta de empleo para personas con<br> discapacidad y adultos mayores<br><br><br>
				<input type="button" value="Crear oferta" name="crearOfertaAbriendoEspacios" class="boton" onclick="javascript:crearOfertaEnAbriendoEspacios()"/>
				</p>		
			</div>		
			
			<!-- BECATE -->	
			<!-- div class="un_medio certificacion fl">
				<p><strong>Programa Bécate</strong>
				Crear oferta de empleo para<br> programa Bécate<br><br><br>
				<input type="button" value="Crear oferta" name="crearOfertaBecate" class="boton" onclick="javascript:crearOfertaEnBecate()"/>
				</p>		
			</div -->					
			<div class="clearfix"></div>
		</div>			
</form>	

