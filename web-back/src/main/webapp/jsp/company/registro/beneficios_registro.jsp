<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar, mx.gob.stps.portal.web.infra.utils.PropertiesLoader"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="MessageResources" var="portalbundle"/>

<%String context = request.getContextPath() +"/";
pageContext.setAttribute("context", context);
PropertiesLoader properties = PropertiesLoader.getInstance();
String urlRedirect = properties.getProperty("app.swb.redirect.home");
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
	dojo.require("dijit.form.FilteringSelect");
	
	function iniciarRegistroEmpresa(){
		//dojo.byId('method').value='redirectRegisterEmpresaRU';
		//dojo.byId('registroEmpresaForm').submit();	
		window.open('registro-unico.do?method=redirectRegisterEmpresaRU','_blank');

	}
	
	function salirRegistro(){
		document.location.href = '<%=response.encodeUrl(request.getContextPath()+"/inicio.do")%>';
	}
	
</script> 	
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<form name="registroEmpresaForm" id="registroEmpresaForm" method="post" action="registro-unico.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/>

	<div class="formApp">
	    <div class="flow_1">Registro de Empresa</div>
	    <div class="form_wrap round_corn">
	        <div class="instruc_01"><strong>Regístrate y obtén los beneficios que tenemos para ti.</strong></div>
	        <div class="beneficios">
	            <p><strong>Con tu registro, podrás:</strong></p>
	            <ul>
	                <li class="li_sne"><div class="img_hold"></div><div class="li_text">Acceder al Portal del Empleo y a todos los servicios y programas que ofrece el Servicio Nacional del Empleo (SNE).</div></li>
	                <li class="li_ferias"><div class="img_hold"></div><div class="li_text">Participar en nuestras Ferias de Empleo y encontrar a los candidatos idóneos para tus ofertas de empleo.  </div></li>
	                <li class="li_dom"><div class="img_hold"></div><div class="li_text">Agendar una cita para ser atendido, personalmente, en la oficina del SNE más cercana a tu domicilio.</div></li>
	                <li class="li_abrEsp"><div class="img_hold"></div><div class="li_text">Crear ofertas de empleo para personas con discapacidad y adultos mayores en el programa Abriendo Espacios. </div></li>
	                <li class="li_compat"><div class="img_hold"></div><div class="li_text">Consultar la compatibilidad entre tus ofertas de empleo y los candidatos postulados.</div></li>
	                <li class="li_buscar"><div class="img_hold"></div><div class="li_text">Acceder a nuestra base de datos para buscar a candidatos por ocupación, área o carrera.</div></li>
	                <li class="li_entrev"><div class="img_hold"></div><div class="li_text">Agendar entrevistas en línea con candidatos postulados a tus ofertas de empleo. </div></li>
	                <li class="li_elect"><div class="img_hold"></div><div class="li_text">Administrar la información de tus ofertas de empleo y elegir a los candidatos potenciales.</div></li>
	            </ul>
	            <div class="clearfix"></div>
	            <p class="ta_center fz_12"><strong>Para hacer uso de estos beneficios, es importante crear una oferta de empleo al finalizar tu registro.</strong></p>
	            <p class="aviso_1">Este programa es público, ajeno a cualquier partido político. Queda prohibido el uso para fines distintos a los establecidos en el programa.</p>
	        </div>
	        <div class="form_nav">
	            <input type="button" value="Iniciar Registro" name="iniciarRegistro" class="boton" onclick="javascript:iniciarRegistroEmpresa()" />
	            <input type="button" value="Salir" name="salir" class="boton"  onclick="javascript:salirRegistro()" />
	        </div>
	    </div>
	</div>

</form>