<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
	String context = request.getContextPath() +"/";
	pageContext.setAttribute("context", context);
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript" src="js/misDatos/messagesValidate.js"></script>
<c:set var="CV_COMPLETO" value="4"/>

<style type="text/css">
	#busquedas{
        padding:0;
		margin:0;
	}
	#busquedas li.horizontal {
		display:inline;
	}
	#utlAct {
		font-size: 10px;
	    position: relative;
    	left: 120px;
    	top: -30px;
    	display: inherit; !important;
    	height: 20px;
    	}
    	
 .tooltip {
    position: relative;
    display: inline-block;
    border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 450px;
    background-color: #555;
    color: #fff;
    text-align:justify ;
    font-size: 12px;
    border-radius: 6px;
    padding: 5px 5px;
    position: absolute;
    z-index: 1;
    bottom: 80%;
    left: 80%;
    margin-left: -60px;
    opacity: 0;
    transition: opacity 0.3s;
}

.tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 10%;
    left: 10%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
    opacity: 1;
}
</style>

<script type="text/javascript" src="js/helper/messageHelper.js"></script>

<script type="text/javascript">
dojo.require("dijit.dijit"); // loads the optimized dijit layer
dojo.require("dijit.form.Form");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.TextBox");

var dialogTestimonio;
var dialogAviso;
var dialogActualiza;

function showWindow(){
	if (!dialogTestimonio){
		dialogTestimonio = new dijit.Dialog({
	        title: 'Comparte tu testimonio',
	        href: '${context}testimonio.do?method=init',
	        style: "width:500px; height:350px;",
	        showTitle: false,
	        draggable : false
	    });
	}

	dialogTestimonio.show();
}

function closeWindow(){
	dialogTestimonio.hide();
}

function doSubmitTestimonio(method){
	if(document.testimonioForm.testimonio.value==''){
		dojo.byId('msg').innerHTML = 'Debe escribir su testimonio para poder guardarlo';
		dojo.byId('msg').style.color ='#FF0000';
	}else {
		document.testimonioForm.method.value=method;
		dojo.xhrPost(
				 {url: 'testimonio.do', form:'testimonioForm', timeout:180000, // Tiempo de espera 3 min
				  load: function(data){
					  closeWindow();	
					  dojo.byId('mensaje').innerHTML = 'Tu testimonio se guardo correctamente';
					  dijit.byId('MensajeAlert').show();
				  }
				 } );
	}
}

function maxlength(field, size) {
    if (field.value.length > size) {
        field.value = field.value.substring(0, size);
    }
}

function openLogoWindow(){	
 	var newLogoWin = window.open("<c:url value="/cargaFoto.do?method=init"/>", "CambiarImagen","height=500,width=400,resizable=yes,scrollbars=yes"); 	
}
function openCvWindow(){	
 	var newCvWin = window.open("<c:url value="/cargaCurriculum.do?method=init"/>", "CambiarImagen","height=500,width=900,resizable=yes,scrollbars=yes"); 	
} 	

function openCvStyleWindow(){	
 	var newCvWin = window.open("<c:url value="/eligeEstiloCurriculum.do?method=init"/>", "CambiarEstiloCurriculum","height=500,width=900,resizable=yes,scrollbars=yes"); 	
} 	

function showAvisoConocer(){	
	volverPreguntarConocerRequest = "${volverPreguntarConocer}";
	volverPreguntarConocerSession = "${sessionScope.volverPreguntarConocer}";	
	volverPreguntarConocerSI = "${volverPreguntarConocerSI}";
	volverPreguntarConocerNO = "${volverPreguntarConocerNO}";
	
	// si no se ha mostrado el aviso anteriormente
	if (volverPreguntarConocerSession == "" || volverPreguntarConocerSession != volverPreguntarConocerNO) {
		 //si el usuario no ha expresado su preferencia por que no le muestren nuevamente el aviso
		 if(volverPreguntarConocerRequest == volverPreguntarConocerSI) {
			dialogAviso = new dijit.Dialog({
		        title: 'Mensaje',
		        href: '${context}conocerconfig.do?method=init&idPropietario=${idCandidato}',
		        style: 'width:500px; height:450px; background: #FFF',
		        showTitle: false, draggable : false, closable : true
		    });
	
			dialogAviso.show();
			}
	}
}

function showAvisoActualizaPerfil(){
	var html = '<p>Tu perfil está incompleto.<br/>Actualiza tu información, y agrega tus conocimientos y habilidades.</p>'+
	           '<div class="form_nav 2">'+
	           '<button id="btnActualizar" name="btnActualizar" class="boton_naranja" onclick="actualizarPerfil();">Ir a mi perfil</button>'+
	           '<button id="btnCerrar"     name="btnCerrar"     class="boton_naranja" onclick="closeNotificacion();">Cerrar mensaje</button>'+
	           '</div>';

	dialogActualiza = new dijit.Dialog({
        title: 'Notificación',
        //href: '${context}conocerconfig.do?method=init&idPropietario=${idCandidato}',
        content: html,
        style: 'width:500px; height:150px; background: #FFF',
        showTitle: false, draggable : false, closable : true
    });

	dialogActualiza.show();
}

function closeNotificacion(){
	dialogActualiza.hide();
}

function actualizarPerfil(){
	dojo.byId('OfertasPorPerfilForm').action='<%=response.encodeURL(request.getContextPath() + "/registro-unico.do?method=redirectEditaCandidatoRU")%>';
	dojo.byId('OfertasPorPerfilForm').submit();
}

function test(txt){
	alert(txt);
}

function mensaje(mensaje) {	
	message(mensaje);
}

function registrarConocerConfig(respuesta) {
	 
	 volverPreguntar = 'Si';
	 if (dojo.byId('checkVolverPreguntar').checked)
		 volverPreguntar = 'No';
	 
	 dojo.xhrPost({url: 'conocerconfig.do?method=registrarConocerConfig'+
			 	   '&idCandidato=${idCandidato}' +
			       '&respuesta='+respuesta+
			       '&checkVolverPreguntar='+volverPreguntar, 
			       timeout:180000});

	 dialogAviso.hide();
}

function redireccionarLogin(){
	window.location='logout.do';
}

function doSubmitDesactivarCandidato(estado){
		if(estado==true){	
			if (!confirm('¿Desea realizar la desactivación de su cuenta personal?')) return;
   		dojo.xhrGet( {
			url: 'perfil.do?method=desactivar',
	  		form:'perfilForm',
	  	 	sync: true,
	  		timeout:180000,
	  		load: function(data) {
	  			
				var res = dojo.fromJson(data);
				
				mensaje(res.msg.message);
				
				if(res.msg.type=="ext"){
					redireccionarLogin();
				}
	    	}
		}); 
		}
	}

</script>

<c:if test="${not empty OfertasPorPerfilForm.estandares}">

	<script type="text/javascript">
	dojo.addOnLoad(function() {  
		showAvisoConocer();	
	}); 
	</script>
</c:if>

<c:if test="${not empty CANDIDATO_NOTIFICACION && CANDIDATO_NOTIFICACION == 'PERFIL_SIN_HABILIDADES'}">
	<script type="text/javascript">
	dojo.addOnLoad(function() {  
		showAvisoActualizaPerfil();	
	}); 
	</script>
</c:if>

<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false" >
		  <table class="alertDialog" >
			 <tr align="center">
				 <td><div id ="mensaje"></div>&nbsp;</td>				 
			 </tr>
		 </table>	
</div> 

<!-- Formulario utilizado para el direccionamiento hacia la edicion del Perfil del Candidato -->
<form name="OfertasPorPerfilForm" id="OfertasPorPerfilForm" action="perfil.do?method=init" method="post">
</form>


<form name="perfilForm" id="perfilForm" action="perfil.do?method=init" method="post" >
	<input type="hidden" name="method" id="method" value="init" />
</form>


<%-- N U E V A   E S T R U C T U R A --%>
<div class="miEspacio">
	<h2>Mi espacio</h2>
	<div class="user_profile">
		<div class="user_pic">
       	  <img src="${context}imageAction.do?method=getImagen&<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>" alt="foto del usuario" >
          <div class="shadow"></div>
		</div>
		<ul>
		    <li class="user_name_01">${candidatoheader.nombre}</li>
		    <li><a href="<c:url value="/registro-unico.do?method=redirectEditaCandidatoRU"/>">Editar mi perfil</a></li>
			<!-- li><a href="${context}perfil.do?method=init">Editar mi perfil</a></li -->
			<!-- li><a href="<c:url value="/cargaFoto.do?method=init"/>">Cambiar fotografía</a></li -->
			<!-- li><a href="${context}perfilComplementario.do?method=strengthenProfile">Fortalecer mi perfil</a></li -->
		</ul>
        <div class="clearfix"></div>
	</div>
	
	<!-- div class="Quebec">
	  <c:if test="${empty _quebec}">
	  	<a href="${context}registro_candidato_quebec.do">Completar datos</a>
	  </c:if>
	  <a href="http://www.quebecentete.com/fr/travailler-a-quebec/offres-demploi/" target="_blank">Ver ofertas en Quebec</a>
	</div -->
	
	<div class="user_options">
		<div class="optionsBlock">
        	<div class="c_curriculum">
			<h3>Crear mi currículum</h3>
			<ul>
				<li><a href="<c:url value="/miscandidatos.do?method=genera&idCandidato=${idCandidato }"/>">Ver currículum</a></li>
				<li><a href="<c:url value="/cargaCurriculum.do?method=init"/>">Subir video-currículum</a></li>
			</ul>
			<div class="videoCurriculum">
            	    <c:if test="${not empty decoratedVideo and decoratedVideo != 'No disponible'}">
                    <div class="videoCV"> 
        				<div align="center">
                			<iframe width="300" height="200" src="${decoratedVideo}" frameborder="0" allowfullscreen></iframe>
                		</div>
    				</div>
                	</c:if>
			</div>
            </div>

        	<div class="c_ofertas">
			<h3>Encontrar ofertas de empleo</h3>
			<ul>
				<li><a href="<c:url value="/ofertasPerfiles.do?method=init&tipoConsulta=1"/>">Ofertas de acuerdo a mi perfil</a></li>
				<!-- li><a href="${context}ofertasPorParametros.do?method=init">Búsqueda parametrizable</a></li -->
				<li><a href="<c:url value="/bolsasTrabajo.do?method=init"/>">Buscar en otras bolsas de trabajo</a></li>
			</ul>
            </div>
		</div>
		<div class="optionsBlock">
            <div class="c_misOfertas">
			<h3>Mis ofertas de empleo</h3>
			<ul>
				<li><a href="<c:url value="/misofertas.do?method=misPostulaciones"/>">Mis postulaciones</a></li>
				<li><a href="<c:url value="/misofertas.do?method=misOfertasGuardadas"/>">Mis ofertas guardadas</a></li>
				<li><a href="<c:url value="/misofertas.do?method=empresasQueMeBuscan"/>">Empresas que me buscan</a></li>
                <!-- New links to PPC-SD -->
                <!--INI_JGLC_PPC-->
<%--                 <c:if test="${candidatoheader.isActiveToPpc() || candidatoheader.isInactiveToPpc()}"> --%>
<%--                     <li><a id="registro_postulaciones_externas" href="${context}registroPostulacionExterna.do?method=init">Registrar postulaciones externas</a></li> --%>
<%--                     <li><a id="seguimiento_postulaciones_externas" href="${context}seguimientoPostulacionExterna.do?method=init">Seguimiento a postulaciones externas</a></li> --%>
<%--                     <li><a id="registrar_motivo_no_postulacion" href="${context}adminNoPostulacionesCandidato.do?method=init">Registrar motivo de no postulaci&oacute;n</a></li> --%>
<%--                 </c:if> --%>
                <!--FIN_JGLC_PPC-->
			</ul>
            </div>
            
        	<div class="c_programas c_servicios">
			<h3>Servicios</h3>
			<ul>
				<li><a href="<c:url value="/entrevistaProgramada.do?method=entrevistaProgramadaEnlineaCandidato"/>">Consultar mis entrevistas en línea</a></li>
				<li><a href="<c:url value="/miespacionav.do?method=agendaCita"/>" target="_self">Solicitar una cita</a></li>
				<div class="tooltip">
				<li>
					<a href="javascript:doSubmitDesactivarCandidato(true);">Desactivar Candidato</a>
				 	<span class="tooltiptext">Al elegir "Desactivar Candidato", ocultarás temporalmente tu información.</br>
											Considera que las empresas no podrán consultar tus datos, ni comunicarse contigo.</span>
				</li>
				</div>
			</ul>
            </div>
		</div>
		<div class="optionsBlock rightLast">
        	<div class="c_programas">
			<h3>Programas</h3>
			<ul>
				<li><a href="<c:url value="/registrarCandidatoEvento.do?method=init"/>">Registrarme a un evento de Ferias de Empleo</a></li>
                <!-- New links to PPC-SD -->
					<!--INI_JGLC_PPC-->
					<%--                 <c:if test="${candidatoheader.isActiveToPpc() || candidatoheader.isInactiveToPpc()}"> --%>
					<%--                     <li><a target="_blank" id="imprimir_terminos_condiciones_ppc_sd" href="${context}miespacionav.do?method=terminosCondicionesPpcEnPdf">Imprimir los t&eacute;rminos y condiciones del Programa de Promoci&oacute;n y Colocaci&oacute;n del Seguro de Desempleo (PPC-SD)</a></li> --%>
					<%--                 </c:if> --%>
					<%--                 <c:if test="${candidatoheader.hasNeverBeenInTouchWithPpc() || candidatoheader.hasDecidedNotToEnrollToPpc()}"> --%>
					<%--                     <li><a id="inscribir_al_ppc_sd" href="${context}registroCandidatoPPCSD.do?method=showContenido">Inscribirme al Programa de Promoci&oacute;n y Colocaci&oacute;n del Seguro de Desempleo (PPC-SD)</a></li> --%>
					<%--                 </c:if> --%>
					<!--FIN_JGLC_PPC-->
					<!--li><a href="${context}informacionPrograma.do?method=init&program=mml">Mecanismo de Movilidad Laboral</a></li>
					<li><a href="${context}informacionPrograma.do?method=init&program=ptat">Programa de Trabajadores Agrícolas Temporales México-Canadá</a></li>
					<li><a href="${context}informacionPrograma.do?method=init&program=misa">Movilidad Laboral Interna (Sector Agrícola)</a></li>
					<li><a href="${context}informacionPrograma.do?method=init&program=misi">Movilidad Laboral Interna (Sector Industrial de Servicios)</a></li>
					<li><a href="${context}informacionPrograma.do?method=init&program=beca">Bécate</a></li>
					<li><a href="${context}informacionPrograma.do?method=init&program=fa">Fomento al Autoempleo</a></li-->
					<c:if test="${requestScope.esCandidatoConalep eq true}">
						<li><a href="<c:url value="/conalep.do?method=init"/>">CONALEP</a></li>
					</c:if>
				</ul>
            </div>
		</div>
        <div class="clearfix"></div>
	</div>
</div>
<%-- F I N   D E   N U E V A   E S T R U C T U R A --%>

<%-- Repatriados - Geolocalizacion --%>
<% String httpAcceptLanguage = (String)session.getAttribute("ACCEPT_LANGUAGE"); %>
<c:set var="acceptLanguage"><%= httpAcceptLanguage %></c:set>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            console.log(position);
            $.getJSON('http://ws.geonames.org/countryCode', {
                lat: position.coords.latitude,
                lng: position.coords.longitude,
                username: 'omaroman',
                type: 'JSON',
                lang: 'es'
            }, function(result) {
                //alert(result.countryName);
                if (result.countryName == "Estados Unidos") {
                    message('Como trabajador repatriado puedes ponerte en contacto con nosotros acudiendo a la oficina más cercana del Servicio Nacional del Empleo');
                }
            });

        });
    } else {
        if ("${acceptLanguage}".toLowerCase().includes("en-us")) {
            message('Como trabajador repatriado puedes ponerte en contacto con nosotros acudiendo a la oficina más cercana del Servicio Nacional del Empleo');
        }
    }
</script>