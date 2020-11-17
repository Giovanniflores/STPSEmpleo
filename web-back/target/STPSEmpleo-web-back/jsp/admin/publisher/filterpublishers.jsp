<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.web.autorization.form.ParametrizarForm"%>
<%
	String context = request.getContextPath() +"/";
	ParametrizarForm paramform = (ParametrizarForm)session.getAttribute("ParametrizarForm");
%>

<link href="<%=request.getContextPath()%>/css/bootstrap/table-responsive.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.NumberTextBox");
	
	function doSubmitPager(method){
	  dojo.byId('method').value = method;	 
	  dojo.xhrPost({url: 'publishertree.do', form:'FilterPublisherForm', timeout:180000, 
	  	load: function(data) {
	  		dojo.byId('table').innerHTML=data;
		}});  
	}
	function doSubmitPagina(pagina) {
		dojo.byId('method').value = "goToPage";
		dojo.byId('goToPageNumber').value = pagina;
		dojo.xhrPost({url: 'publishertree.do', form:'FilterPublisherForm', timeout:180000, 
			load: function(data) {
				dojo.byId('table').innerHTML=data;
		}});
	}
	function mostrarResumen(idResumen) {
	    var thisRow = dojo.style(idResumen, "display");
	    if (thisRow == 'block') {
	        dojo.style(idResumen, "display", "none");
	        dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "none");
	        dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "block");
	    }
	    if (thisRow == 'none') {
	        dojo.style(idResumen, "display", "block");
	        dojo.style(eval("\"" + "hide" + idResumen + "\""), "display", "block");
	        dojo.style(eval("\"" + "show" + idResumen + "\""), "display", "none");
	    }
	 }
	function mostrarTodas(tipo, totReg) {
	    if (tipo == 1) {
	        for (var i = 1; i <= totReg; i++) {
	            dojo.style("hideAll", "display", "block");
	            dojo.style("showAll", "display", "none");
	            dojo.style(eval("\"" + i + "\""), "display", "block");
	            dojo.style(eval("\"" + "hide" + i + "\""), "display", "block");
	            dojo.style(eval("\"" + "show" + i + "\""), "display", "none");
	        }
	    }
	    if (tipo == 2) {
	        for (var i = 1; i <= totReg; i++) {
	            dojo.style("hideAll", "display", "none");
	            dojo.style("showAll", "display", "block");
	            dojo.style(eval("\"" + i + "\""), "display", "none");
	            dojo.style(eval("\"" + "hide" + i + "\""), "display", "none");
	            dojo.style(eval("\"" + "show" + i + "\""), "display", "block");
	        }
	    }
	}
	function doSubmitAjax(method) {
		if(!dijit.byId('valor').isValid()) {
			alert('El tamaño del bloque es requerido.');
			return;
		}
		dojo.byId('method').value=method;
		dojo.byId('btnEnviar').disabled=true;
		dojo.xhrPost(
			 {
			  url: 'publishertree.do',
			  form:'FilterPublisherForm',
			  timeout:180000, // Tiempo de espera 3 min
			  load: function(data) {
				    var res = dojo.fromJson(data); // Datos del servidor
				    dojo.byId('message').innerHTML=res.message;
					if (res.type!='err'){// EXITO
				    	dojo.byId('message').style.color='#000066';
				        dijit.byId('valor').disabled=true;
				    }else{ // ERROR
				    	dojo.byId('message').style.color='#FF0000';
				        dijit.byId('valor').disabled=false;
				    	dojo.byId('btnEnviar').disabled=false;
				    }
			    }
			} );
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>


<form name="FilterPublisherForm" id="FilterPublisherForm" method="post" action="publishertree.do" dojoType="dijit.form.Form">
	<input type="hidden" name="method" id="method" value=""/>
	<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />
	<h3>Publicadores</h3>
	<c:if test="${USUARIO_APP.administrador}">
		<strong><label for="valor">Tamaño del bloque de registros por asignar</label>: </strong>
		<input type="text" name="valor" id="valor" style="width:100px" dojoType="dijit.form.NumberTextBox" required="true" missingMessage="Dato requerido" value="<% out.print(paramform.getValor()); %>"/>
			
		<div id="divRegis">
			<input type="button" id="btnEnviar" value="Modificar" class="boton" onclick="doSubmitAjax('balance');" />
		</div>
	</c:if>	
	<div id="message"></div>
	<div id="table"></div>
</form>

<script>
	doSubmitPager('page');
</script>