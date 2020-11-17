<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO"%>
<%@page import="mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO"%>
<%@page import="mx.gob.stps.portal.core.oferta.registro.vo.RequisitoVO"%>
<%@page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO"%>
<%@page import="java.util.List"%>
<%@page import="mx.gob.stps.portal.web.infra.utils.Utils"%>

<%
boolean flag=false;
boolean flag2=false;
int ultima=request.getSession().getAttribute("ultimaOferta")!=null?Integer.parseInt(request.getSession().getAttribute("ultimaOferta").toString()):0;
int ofertaMostradas=0;
%>
<%	
	String invoke = (null != request.getSession().getAttribute("_urlpageinvokeCand") ? (String)request.getSession().getAttribute("_urlpageinvokeCand") : "javascript:history.go(-1)");
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">

dojo.require("dijit.dijit"); // loads the optimized dijit layer
dojo.require("dijit.form.Form");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.Button");
dojo.require("dijit.form.ValidationTextBox");

function doSubmit(accion){
	document.navegacionFormBean.action=accion;
	document.navegacionFormBean.submit();
}



function doSiguiente(accion){
	document.navegacionFormBean.siguienteP.value='true';
	document.navegacionFormBean.action=accion;
	document.navegacionFormBean.submit();
}
function doAtras(accion){
	document.navegacionFormBean.atrasP.value='true';
	document.navegacionFormBean.action=accion;
	document.navegacionFormBean.submit();
}
function inhabilita(method){
	document.registroContactoFormBean.siguiente.disabled=true;
}
	var uri;
	function cancelaOferta(accion) {
		var input;
		uri = accion;
		input = '<input id="btnCnl" class="boton_naranja" type="button" name="btnCnl" onClick="gotoDeactivateOffer();" value="Aceptar"/>';
		messageInput('¿Está seguro que desea cancelar la oferta de empleo?',input);
	}
	function activaOferta(accion) {
		var input;
		uri = accion;
		input = '<input id="btnAcp" class="boton_naranja" type="button" name="btnAcp" onClick="gotoActivateOffer();" value="Aceptar"/>';
		messageInput('¿Desea ACTIVAR la oferta?',input);
	}
	function gotoActivateOffer() {
		document.navegacionFormBean.action=uri;
		document.navegacionFormBean.submit();
	}
	function gotoDeactivateOffer() {
		document.navegacionFormBean.action=uri;
		document.navegacionFormBean.submit();
	}
	function gotoDeleteOffer() {
		document.navegacionFormBean.action=uri;
		document.navegacionFormBean.submit();
	}
	function eliminaOferta(accion) {
		var input;
		uri = accion;
		input = '<input id="btnDel" class="boton_naranja" type="button" name="btnDel" onClick="gotoDeleteOffer();" value="Aceptar"/>';
		messageInput('¿Está seguro que desea eliminar la oferta de empleo?',input);
	}
	function messageInput(msg,input, action) {
		var html =
			'<div id="messageDialgop2" name="messageDialgop2">' +
				'<p style="text-align: center;">'+ msg +'</p><br>'+
				'<p class="form_nav">' + input + cerrarMsg() + 
			'</div>';

		dialogMsg = new dijit.Dialog({
	        title: 'Mensaje',
	        content: html,
	        style: "width:300px;",
	        showTitle: false, draggable : false, closable : false,
	        hide: function() {
	        	clearWidgetsHtml('messageDialgop2');
	        	dialogMsg.destroy();
	        }
	    });			
		dialogMsg.show();
	}

function doSubmitPaginaInicial(method) {

	vurl = 'OfertaNavegacion.do?method='+method;
	if (dojo.byId('idCandidato').value != null && dojo.byId('idCandidato').value > 0)
		vurl = vurl + '&idCandidato='+dojo.byId('idCandidato').value;
	
	dojo.xhrPost({url: vurl, timeout:180000,
                  load: function(data) {
                        dojo.byId('reporte').innerHTML = data;
                  }});
}

function doSubmitPagina(pagina, dif){
	var vurl = 'OfertaNavegacion.do?method=goToPage&goToPageNumber='+pagina;
	if (dojo.byId('idCandidato').value != null && dojo.byId('idCandidato').value > 0)
		vurl = vurl + '&idCandidato='+dojo.byId('idCandidato').value;		

   	dojo.xhrPost({url: vurl, timeout:180000, 
				  load: function(dataCand){
				      dojo.byId('reporte').innerHTML=dataCand;
				  }});
}

/* Ordena tabla por columna */
function orderBy(orden,tipocolumna){
	var vurl = 'OfertaNavegacion.do?method=orderByColumn&tipoOrden=' + orden + '&tipoColumna=' + tipocolumna;
	if (dojo.byId('idCandidato').value != null && dojo.byId('idCandidato').value > 0)
		vurl = vurl + '&idCandidato='+dojo.byId('idCandidato').value;
	
	dojo.xhrPost({url: vurl, timeout:180000, 
		  load: function(dataCand){
		      dojo.byId('reporte').innerHTML=dataCand;
		      if(tipocolumna == '1' && orden == 'asc'){
		  		 document.getElementById("id_orden_asc").style.display = 'none';
		  		 document.getElementById("id_orden_desc").style.display = 'inline';
		  	}else if(tipocolumna == '1' && orden == 'desc'){
		  		document.getElementById("id_orden_desc").style.display = 'none';
		  		document.getElementById("id_orden_asc").style.display = 'inline';
		  	}else if(tipocolumna == '2' && orden == 'asc'){
		  		document.getElementById("titulo_orden_asc").style.display = 'none';
		  		 document.getElementById("titulo_orden_desc").style.display = 'inline';
		  	}else if(tipocolumna == '2' && orden == 'desc'){
		  		document.getElementById("titulo_orden_desc").style.display = 'none';
		  		document.getElementById("titulo_orden_asc").style.display = 'inline';
		  	}else if(tipocolumna == '3' && orden == 'asc'){
		  		document.getElementById("ocupacion_orden_asc").style.display = 'none';
		  		 document.getElementById("ocupacion_orden_desc").style.display = 'inline';
		  	}else if(tipocolumna == '3' && orden == 'desc'){
		  		document.getElementById("ocupacion_orden_asc").style.display = 'inline';
		  		 document.getElementById("ocupacion_orden_desc").style.display = 'none';
		  	}else if(tipocolumna == '4' && orden == 'asc'){
		  		document.getElementById("nivel_orden_asc").style.display = 'none';
		  		 document.getElementById("nivel_orden_desc").style.display = 'inline';
		  	}else if(tipocolumna == '4' && orden == 'desc'){
		  		document.getElementById("nivel_orden_asc").style.display = 'inline';
		  		 document.getElementById("nivel_orden_desc").style.display = 'none';
		  	}else if(tipocolumna == '5' && orden == 'asc'){
		  		document.getElementById("carrera_orden_asc").style.display = 'none';
		  		 document.getElementById("carrera_orden_desc").style.display = 'inline';
		  	}else if(tipocolumna == '5' && orden == 'desc'){
		  		document.getElementById("carrera_orden_asc").style.display = 'inline';
		  		 document.getElementById("carrera_orden_desc").style.display = 'none';
		  	}else if(tipocolumna == '6' && orden == 'asc'){
		  		document.getElementById("ubicacion_orden_asc").style.display = 'none';
		  		 document.getElementById("ubicacion_orden_desc").style.display = 'inline';
		  	}else if(tipocolumna == '6' && orden == 'desc'){
		  		document.getElementById("ubicacion_orden_asc").style.display = 'inline';
		  		 document.getElementById("ubicacion_orden_desc").style.display = 'none';
		  	}else if(tipocolumna == '7' && orden == 'asc'){
		  		document.getElementById("fecha_orden_asc").style.display = 'none';
		  		 document.getElementById("fecha_orden_desc").style.display = 'inline';
		  	}else if(tipocolumna == '7' && orden == 'desc'){
		  		document.getElementById("fecha_orden_asc").style.display = 'inline';
		  		 document.getElementById("fecha_orden_desc").style.display = 'none';
		  	}else if(tipocolumna == '8' && orden == 'asc'){
		  		document.getElementById("estatus_orden_asc").style.display = 'none';
		  		 document.getElementById("estatus_orden_desc").style.display = 'inline';
		  	}else if(tipocolumna == '8' && orden == 'desc'){
		  		document.getElementById("estatus_orden_asc").style.display = 'inline';
		  		 document.getElementById("estatus_orden_desc").style.display = 'none';
		  	}else if(tipocolumna == '10' && orden == 'asc'){
		  		document.getElementById("discapacidades_orden_asc").style.display = 'none';
		  		document.getElementById("discapacidades_orden_desc").style.display = 'inline';
		  	}else if(tipocolumna == '10' && orden == 'desc'){
		  		document.getElementById("discapacidades_orden_asc").style.display = 'inline';
		  		document.getElementById("discapacidades_orden_desc").style.display = 'none';
		  	}else if(tipocolumna == '11' && orden == 'asc'){
		  		document.getElementById("tipo_oferta_orden_asc").style.display = 'none';
		  		document.getElementById("tipo_oferta_orden_desc").style.display = 'inline';
		  	}else if(tipocolumna == '11' && orden == 'desc'){
		  		document.getElementById("tipo_oferta_orden_asc").style.display = 'inline';
		  		document.getElementById("tipo_oferta_orden_desc").style.display = 'none';
		  	}
		  }});
}
/* Busca ofertas con estatus Activas ó Canceladas */
function ofertasActivasCanceladas(method) {
	var folio = dijit.byId('folio').value;
	var titulo = dijit.byId('titulo').value;
	if (folio.length > 0) {
		if (!dijit.byId('folio').isValid()) {
			dijit.byId('folio').focus();
			message("El siguiente campo muestra datos invalidos: Folio ");								
			return false;
		}
	}else if(titulo.length > 0) {
		if(!dijit.byId('titulo').isValid()) {
			dijit.byId('titulo').focus();
			message("El siguiente campo muestra datos invalidos: Título ");								
			return false;
		}
	}else {
		message("Ingrese al menos uno de los campos.");
		return false;
	}
	document.navegacionFormBean.method.value=method;
	document.navegacionFormBean.submit();
	return true;
}

function enviarCandidato(){
	var idOfertaEmpleo = 0;
	var list = dojo.query("#mis-ofertas input[type='radio']:checked");

	if (list != null && list.length > 0){
		for (i = 0; i < list.length; i++){
			idOfertaEmpleo = list[i].value;
			break;
		}
	}

	if ( idOfertaEmpleo == 0){
		message("Es preciso seleccionar un oferta de empleo");
		return;
	}

	var idCandidato = dojo.byId('idCandidato').value;	
	var url = 'OfertaNavegacion.do?method=enviaCandidato&idOfertaEmpleo='+idOfertaEmpleo+'&idCandidato='+idCandidato;
	dojo.xhrPost(
		{url: url, timeout:180000, sync: true, 
		 load: function(data) {
		          var res = dojo.fromJson(data);
		          if (res != null && res.message != null && res.type=='ext') {
		        	  messagePath(res.message,'<%=invoke%>');		        	  		        	  		        	  
		          } else {
		        	  message(res.message);
		          }		        	  
		 }
	});
	//submitWindow();
}

function messagePath(msg,ruta){
	var html =
		'<div id="messageDialgop2" name="messageDialgop2">' +
	'<p style="text-align: center;">'+ msg +'</p>'+
	'<p class="form_nav">'
	+ '<input id="btnCerrar" class="boton_naranja" type="button" name="btnCerrar" onClick="cerrarRuta(\''+ruta+'\');" value="Cerrar"/>'
    + '</p>'
    +'</div>';

	dialogMsg = new dijit.Dialog({
        title: 'Mensaje',
        content: html,
        style: "width:300px;",
        showTitle: false, draggable : false, closable : false,
    });		
	dialogMsg.show();
}


function submitWindow() {
	document.navegacionFormBean.action = '<%=invoke%>';
	document.navegacionFormBean.submit();
}

</script> 
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			alert('${ERROR_MSG}');
		});
	</script>
</c:if>


<div class="lista_ofertas">
	
	<div class="tab_block">
	<div align="left" style="display:block;" id="returnME">
		<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioEmpresas.do"/>');">
		<strong>Ir al inicio de Mi espacio</strong></a>
	</div>
	<div class="Tab_espacio">
		<h3>Administrar mis ofertas de empleo</h3>
			<div class="subTab">
				<ul>
					<li><a href="javascript:doSubmit('<c:url value="/dondePublicar.do?method=init"/>');">Crear oferta de empleo</a></li>
					<li class="curSubTab"><span>Ver mis ofertas de empleos</span></li>
				</ul>
                <div class="clearfix"></div>
            </div>
	</div>	      
</div>
	
	<form name="navegacionFormBean" action="OfertaNavegacion.do" method="post" >
	<fieldset>
		<legend>Mis ofertas de empleo</legend>
		<input type="hidden" name="method" value="init" />
		<input type="hidden" name="atrasP" value="false" />
		<input type="hidden" name="siguienteP" value="false" />
		<input type="hidden" name="idCandidato" id="idCandidato" value="${param.idCandidato}" />
		<input type="hidden" name="exitoRedirect" id="exitoRedirect"  value="false"/>
		<div>
			<div>
			<div class="grid1_3 margin_top_10">
				<label for="folio">Número de folio</label>
			 	<input id="folio" name="folio" maxlength="8" size="10"
			 	       lang="es"
					   dojoType="dijit.form.ValidationTextBox" regExp="^[0-9]{1,10}$"
					   invalidMessage="Dato Invalido" missingMessage="Dato requerido" trim="true"  required="true"/>
			</div>
			<div class="grid1_3 margin_top_10">	   
				<label for="titulo">Título de la oferta</label>
				<input id="titulo" name="titulo" maxlength="150" size="25" dojoType="dijit.form.ValidationTextBox" regExp="^[\w\D\s\.áéíóúÁÉÍÓÚñÑ]{5,150}$" required="true" 
		        	   invalidMessage="Dato Invalido"  missingMessage="Dato requerido" trim="true"/><br /><br />
		        <input type="button" value="Buscar" name="buscatOfertas" class="boton" onclick="javascript:ofertasActivasCanceladas('ofertasActivasCanceladas')"/>
		    </div>
		        
			</div> 
		</div>
		
	    <div id="reporte"></div>
	</fieldset>
	</form>

	<div class="form_nav">
	
	
	<c:if test="${empty param.idCandidato || param.idCandidato == 0}">
		<!-- <input type="button" value="Crear oferta de empleo" class="boton"
			   onclick="javascript:this.disabled=true;doSubmit('ofertaEmpleo.do?method=init');"/>  -->
		<input type="button" value="Crear oferta de empleo" class="boton"
			   onclick="javascript:this.disabled=true;doSubmit('<c:url value="/dondePublicar.do?method=init"/>');"/> 			   
		&nbsp;&nbsp;
		<input type="button" value="Recuperar ofertas de empleo" class="boton"
			   onclick="javascript:this.disabled=true;doSubmit('<c:url value="/RecuperaOfertas.do?method=init"/>');"/>
		&nbsp;&nbsp;	
		<input type="button" value="Reporte ofertas de empleo" class="boton"
		   onclick="javascript:this.disabled=true;doSubmit('<c:url value="/reporteOfertasEmpresa.do?method=init"/>');"/>
	</c:if>
	
	<c:if test="${param.idCandidato > 0}">
		<input type="button" value="Enviar" class="boton"
			   onclick="enviarCandidato();"/>
		&nbsp;&nbsp;	
	</c:if>	
			
	</div>    
</div>
<c:if test="${vacanteInactiva == true}">
	<script type="text/javascript">
		alert("La vacante que busca, no se encuentra activa.");
	</script>
</c:if>

<%
	String mensaje = "";
	if (request.getSession().getAttribute("nuevaOfertaGuardada")!=null) {
		if (request.getSession().getAttribute("nuevaOfertaGuardada")!="0") {
			mensaje = "Se ha creado la oferta de empleo con el número de folio: "+request.getSession().getAttribute("nuevaOfertaGuardada");
			if(request.getSession().getAttribute("claveOferta")!=null){
				mensaje = mensaje + ", clave de la oferta: "+request.getSession().getAttribute("claveOferta");
			}
			mensaje = mensaje + ". La oferta será visible una vez que sea autorizada.";
		}else {
			mensaje = "Ha ocurrido un error durante la generacion de la nueva oferta, favor de notificar al administrador.";		
		}
%>
		<script type="text/javascript">
			message('<%=mensaje%>');
		</script>
<%
		request.getSession().removeAttribute("nuevaOfertaGuardada");
	}
	if (request.getSession().getAttribute("cancelada")!=null) {
		mensaje = "La oferta ha sido cancelada exitosamente";
%>
		<script type="text/javascript">
			message('<%=mensaje%>');
		</script> 
<%
		request.getSession().removeAttribute("cancelada");
	}
	if (request.getSession().getAttribute("idOfertaEditada")!=null) {
		mensaje="Los datos han sido guardados exitosamente, la oferta será visible una vez que sea autorizada.";
%>
		<script type="text/javascript">
			message('<%=mensaje%>');
		</script> 
<%
		request.getSession().removeAttribute("idOfertaEditada");
	}
	if (request.getSession().getAttribute("idOfertaEliminada")!=null) {
		mensaje="Los datos han sido eliminados exitosamente";
%>
		<script type="text/javascript">
		message('<%=mensaje%>');
		</script> 
<%
		request.getSession().removeAttribute("idOfertaEliminada");
	}
	if (request.getSession().getAttribute("noContactos")!=null) {
		request.getSession().setAttribute("noContactosDisplayed","done");
		mensaje="Es necesario que existan contactos activos antes de crear ofertas de empleo";
%>
		<script type="text/javascript">
			message('<%=mensaje%>');
			document.navegacionFormBean.submit();
		</script> 
<%
		request.getSession().removeAttribute("noContactos");
	}
%>
<script type="text/javascript">
    doSubmitPaginaInicial('page');
</script>