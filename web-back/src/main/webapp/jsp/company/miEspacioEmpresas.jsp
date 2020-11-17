<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@ page import="mx.gob.stps.portal.core.empresa.vo.EmpresaVO"%>
<%@ page import="mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate"%>
<%@ page import="mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl"%>
<%@ page import="mx.gob.stps.portal.web.security.vo.UsuarioWebVO"%>
<%@ page import="java.util.Calendar"%>

<c:set var="regexpcurp">[a-zA-Z]{4}\d{6}[hmHM][a-zA-Z]{2}[B-DF-HJ-NP-TV-Z|b-df-hj-np-tv-z]{3}[a-zA-Z0-9][0-9]</c:set>

<%String context = request.getContextPath() +"/";%> 

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


var abrevEntidades = ["AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG",
                      "GT", "GR", "HG", "JC", "MC", "MN", "MS", "NT", "NL", "OC",
                      "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TL", "TS", "VZ",
                      "YN", "ZS"];


var dialogTestimonio;


function showWindow(){
	if (!dialogTestimonio){
		dialogTestimonio = new dijit.Dialog({
	        title: 'Comparte tu testimonio',
	        href: '${context}testimonio.do?method=init',
	        style: "width:400px; height:350px;",
	        showTitle: false,
	        draggable : false
	    });
	}

	dialogTestimonio.show();	
}


function closeWindow(){
	dialogTestimonio.hide();
}


function maxlength(field, size) {
    if (field.value.length > size) {
        field.value = field.value.substring(0, size);
    }
}


var dialogActualizaCurp;

function showAvisoActualizaCurp(){	
	
	dialogActualizaCurp = new dijit.Dialog({
        title: 'Notificación',
        href: '${context}actualizaCurp.do?method=init',
        style: "width:400px; height:450px;",
        showTitle: false,
        draggable : false
    });
	
	dialogActualizaCurp.show();	
}


function closeNotificacion(){
	
	if(dialogActualizaCurp){
		dialogActualizaCurp.hide();
	}
}


function validarDatosSolicitados(){
	
	if (!validaCampoSelect('curp')) return false;
	if (!validaCampoSelect('entidadNacimientoSelect')) return false;		
			
	dojo.byId('method').value = "validaCurp";
	dojo.byId('idGenero').value = getRadioValue('genero');
	
	var curpValue = dojo.byId('curp').value;
	
	if(curpValue){
		
		var curpGenero = curpValue.substring(10, 11);
		var curpEntidad = curpValue.substring(11, 13);
		
		var idEntidadSeleccionada = dijit.byId('entidadNacimientoSelect').get('value'); 
		var inicialGenero = "";
		
		if(getRadioValue('genero') == "1"){
			inicialGenero = "H";
		}
		
		if(getRadioValue('genero') == "2"){
			inicialGenero = "M";
		}		
		
		if(inicialGenero != curpGenero){
			alert('Existe error en el campo género');
			return false;
			
		} else {
			
			var iniciales = abrevEntidades[idEntidadSeleccionada-1];
			
			if(iniciales != curpEntidad){
				alert('Existe error en el campo entidad');
				return false;
				
			} else {
				
				var url = 'actualizaCurp.do?method=validaCurp&curp=' + curpValue + '&idGenero=' +  getRadioValue('genero') + '&entidadNacimientoSelect=' + idEntidadSeleccionada;
			   	dojo.xhrGet({url: url, sync: true, timeout:180000,
					  load: function(data){
						  
						var res = dojo.fromJson(data);	
						
						if (res.type == 'ext') {
							alert(res.message);

							dojo.byId('curp').disabled=true;
							dijit.byId('entidadNacimientoSelect').attr('disabled', true);
							document.getElementById("idGeneroMasculino").disabled = true;
							document.getElementById("idGeneroFemenino").disabled = true;
							
														
							document.getElementById("btnValidar").style.display = 'none';							
							document.getElementById("btnActualizar").style.display = 'block';			
							document.getElementById("btnCerrar").style.display = 'block';
							
							return false;
							
						} else  {
							alertMsg(res.message);
							return false;
						}
				}});						
			}								
		}		
	}
}	


function actualizarDatosCurp(){
	
	var curpValue = dojo.byId('curp').value;
	
	var idEntidadSeleccionada = dijit.byId('entidadNacimientoSelect').get('value'); 
	
	var url = 'actualizaCurp.do?method=actualizarDatosCurp&curp=' + curpValue + '&idGenero=' +  getRadioValue('genero') + '&entidadNacimientoSelect=' + idEntidadSeleccionada;
   	dojo.xhrGet({url: url, sync: true, timeout:180000,
		  load: function(data){
			  
			var res = dojo.fromJson(data);	
			
			if (res.type == 'ext') {
				
				alert(res.message);
				closeNotificacion();
				location.replace('<%=context%>miEspacioEmpresas.do?method=init');
				
			} else  {
				alertMsg(res.message);
				return false;
			}
	}});								
}


function getRadioValue(idOrName) {
	
	var value = null;
	var element = document.getElementById(idOrName);
	var radioGroupName = null;  
	
	if (element == null) {
		radioGroupName = idOrName;
	} else {
		radioGroupName = element.name;     
	}
	
	if (radioGroupName == null) {
		return null;
	}
	
	var radios = document.getElementsByTagName('input');
	
	for (var i=0; i<radios.length; i++) {
		
		var input = radios[ i ];    
		
		if (input.type == 'radio' && input.name == radioGroupName && input.checked) {    
			
			value = input.value;
			break;
		}
	}
	return value;
}



function validaCampoSelect(campo){

	var control = dijit.byId(campo);
	
	if (control && control.get('value')==''){
		
		alertMsg(control.get("missingMessage"));
		
		dijit.byId(campo).focus();
		dojo.byId(campo).blur();
		
		return false;
	}

	return true;
}	


function alertMsg(msg){
	alert(msg);
}	



function openCapacitacionMixta(){	
 	var newCapMixta = window.open("<%=context%>capacitacionMixta.do?method=init", "CapacitacionMixta","height=650,width=850,resizable=yes,scrollbars=yes"); 	
}

</script>
<noscript>Funciones genéricas de javascript desactivadas por el navegador</noscript>


<c:if test="${not empty PERSONA_FISICA_SIN_CURP_VALIDADO && PERSONA_FISICA_SIN_CURP_VALIDADO == 'PERSONA_FISICA_SIN_CURP_VALIDADO'}">

	<script type="text/javascript">
	
		dojo.addOnLoad(function() { 
			
			showAvisoActualizaCurp();	
			
		}); 
	</script>
	
</c:if>


            <div class="miEspacio">           
            <div class="user_profile">
				<div class="user_pic">
					<img alt="logo ${miEspacioEmpForm.nombreEmpresa}" src="${context}imageAction.do?method=getImagen&<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>">
					<div class="shadow"></div>
				</div>
				<ul>
					<li class="user_name_01">${miEspacioEmpForm.nombreEmpresa}</li>
					<li class="hidden-xs"><a  target="_blank" href="<c:url  value="/registro-unico.do?method=redirectEditaEmpresaRU"/>">Editar mis datos</a></li>
					<!-- li class="hidden-xs"><a href="<c:url value="/uploadcompanylogo.do?method=init"/>">Cambiar logotipo</a></li -->
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="user_options">
				<div class="optionsBlock col-sm-4">
					<div class="e_ofertas">
						<h3>Administrar mis ofertas de empleo</h3>
						<ul>
							<li><a href="<c:url value="/dondePublicar.do?method=init"/>">Crear oferta de empleo</a></li>
							<li><a href="<c:url value="/OfertaNavegacion.do?method=init"/>">Mis ofertas de empleo</a></li>
							<li><a href="<c:url value="/RecuperaOfertas.do?method=init"/>">Recuperar ofertas de empleo</a></li>
							<li><a href="<c:url value="/reporteOfertasEmpresa.do?method=init"/>">Reporte de ofertas de empleo</a></li>
						</ul>
						<div class="videoCurriculum"></div>
					</div>
				</div>
				<div class="optionsBlock col-sm-4">
					<div class="e_postulantes">
						<h3>Postulantes a mis ofertas de empleo</h3>
						<ul>
							<li><a href="<c:url value="/admonCandidatos.do?method=postulates&idEmpresa=${miEspacioEmpForm.idEmpresa}"/>">Mis postulantes</a></li>
						</ul>
					</div>
				</div>
				<div class="optionsBlock col-sm-4">
					<div class="e_herramientas">
						<h3>Otras herramientas</h3>
						<ul>
							<li><a href="<c:url value="/entrevistaProgramada.do?method=entrevistaProgramadaEnlineaEmpresa"/>">Entrevistas en línea</a></li>
							<li><a href="<c:url value="/acercaAbriendoEspacios.do?method=init"/>">Acerca del portal Abriendo Espacios</a></li>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
            
            
            
            
        <%--     
            
            <div style="float: left; width: 310px; height: auto; margin: 0 0 0 20px">
            
                  <div id="aviso_carta">
                    <h3>Modificar perfil</h3>
                    <div id="modificar_perfil">
                    <div id="image_wrapper">
                    <img alt="logo ${miEspacioEmpForm.nombreEmpresa}" src="${context}imageAction.do?method=getLogotipoEmpresa&<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>" ><br />
                    <a href="${context}uploadcompanylogo.do?method=init">Cambiar la imagen</a>
                    </div>
                      <ul class="avisosGroup">
                      		<li class="aviso"><a href="${context}edicionEmpresa.do?method=init">Actualizar datos de la empresa</a></li>
                      </ul>
                    </div>
                  </div>
                  <div id="mi_portafolios">
                  	<h3>Mis ofertas de empleo recientes</h3>
                   <div id="acciones_portafolios">
                    <c:forEach var="oferta" items="${miEspacioEmpForm.ofertasRecientes}">
                      <p class="aviso"><a href="${context}detalleoferta.do?method=init&id_oferta_empleo=${oferta.idOferta}">${oferta.tituloOferta}</a></p>
                    </c:forEach>
                    <c:if test="${empty miEspacioEmpForm.ofertasRecientes}">
                      <p>Actualmente no hay ofertas que mostrar</p>
                    </c:if>
                    </div>                    
                  </div>
                  
                  <div class="postulaciones">
                  <h3>Postulaciones recientes</h3>
                    <ul>
                        <c:forEach var="row" items="${listaPostulacionesRecientes}">
                        <li>
                        	<a href="${pageContext.request.contextPath}/viewCandidateInfo.do?method=init&idc=${row.idCandidato}&idoc=${row.idOfertaCandidato}">                        	
	                        	<c:out value="${row.nombreCompletoCandidato}"/>&nbsp;
	                        	se postul&oacute; a la oferta&nbsp;
	                        	&ldquo;<c:out value="${row.tituloOferta}"/>&ldquo;&nbsp;
	                        	&shy;&nbsp;Hace <c:out value="${row.antiguedadDias}"/> d&iacute;as
                        	</a>
                        </li>                        
                        </c:forEach>
                        <c:if test="${empty listaPostulacionesRecientes}">
                      		<li>Actualmente no hay postulaciones que mostrar</li>
                        </c:if>
                    </ul>
                  </div>                  
                  
                  <span class="bottom_portafolios"></span>
                  </div>
                    <div id="mis_herramientas">
                    	<h3>Mis herramientas para encontrar candidatos</h3>
                    	 <a id="buscadorPorPalabra" href="javascript:validaOfertasActivas()">Buscador por palabra clave</a>
                    	 <a id="buscadorEspecifico" href="javascript:validaOfertasActivasEspecifica()">Buscador específico</a>
                   </div>
                	<div id="mis_herramientas">
	                  <h3>Mis herramientas</h3>
					  <a id="entrevista" href="entrevistaProgramada.do?method=entrevistaProgramadaEnlineaEmpresa">Entrevista en línea</a>
                  	  <a id="testimonio" href="${context}testimonio.do?method=init">Compartir mi testimonio</a>                  	  
                  </div>
                  <div id="mis_herramientas">
                  	  <a id="acercaAbriendoEspacios" href="${context}acercaAbriendoEspacios.do?method=init">Acerca del portal Abriendo Espacios</a>                  	                                                        
                  </div>

				<div style="float: left;">
                  <div id="ofertas_recomendadas">
                  	<h3>Postulantes a mis ofertas (<c:out value="${totalUltimosPostulantes}"/>)</h3>              
                    <table>
                        <c:forEach var="row" items="${ultimasOfertas}" varStatus="rowCount">
                        <tr>                        
                            <td><a href="${pageContext.request.contextPath}/admonCandidatos.do?method=init&ido=${row.idOfertaEmpleo}">
                            		<c:out value="${row.tituloOferta}"/>
                            	</a>
                            </td>
                            <td>
                            	<c:out value="${row.totalPostulados}"/>
                            </td>
                        </tr>                        
                        </c:forEach>
                        <c:if test="${empty ultimasOfertas}">
                        	<p>Actualmente no hay postulantes que mostrar</p>
                        </c:if>
                    </table>
                  <span class="bottom_ofertas"><a href="OfertaNavegacion.do?method=init">Ver ofertas y postulantes</a></span></br>       
                  </div>
                </div>--%> 
            </div>
	
	<div dojoType="dijit.Dialog" id="MensajeAlert" title="Mensaje" style="display:none" draggable ="false" >
		<table class="alertDialog" >
			 <tr align="center">
				 <td><div id ="mensaje"></div>&nbsp;</td>				 
			 </tr>
		 </table>	
	</div> 
	


<!-- Formulario para recargar la pagina despues de actualizar el Logo de la Empresa -->
<form action="<c:url value="/miEspacioEmpresas.do"/>" id="formEmpresa" name="formEmpresa">
</form>
	
	
	