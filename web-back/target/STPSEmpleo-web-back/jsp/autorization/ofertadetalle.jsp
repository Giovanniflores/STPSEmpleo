<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.web.autorization.form.AutorizationForm"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_REQUISITO"%>
<%@ page import="mx.gob.stps.portal.web.autorization.vo.OfertaDetalleVO"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="url" value="${pageContext.request.requestURL}" />
<%
	String context = request.getContextPath() +"/";
	AutorizationForm autorizationForm = (AutorizationForm)session.getAttribute("autorizationForm");
	OfertaDetalleVO employ = autorizationForm.getOfertaDetalle();
	
%>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.Textarea");
	
function cancelarValidacion(idRegValidar){
	dojo.byId('method').value='init';
	  dojo.byId('idRegValidar').value=idRegValidar;
	  dojo.byId('autorizationForm').submit();	  
	  
	  
}

function cancelarValidacionAdmin(idRegValidar,adminPublisher){
	 
	 if(adminPublisher==0) document.location = '${context}filteroffer.do?method=filter&idoffer='+idRegValidar;
	 else  document.location = '${context}filteroffer.do?method=filterAdminPublisher&idoffer='+idRegValidar;
}
function showWindowMotivoRechazo(idRegValidar){
	  
		try{
			if (dialogRechazo){
				  dijit.byId('idMotivoRechazoSelect').attr('value', null);
				  dojo.style(dialogRechazo.closeButtonNode,"display","none");
				  dialogRechazo._relativePosition = { x: 338, y:315 };				  
				  dialogRechazo.show();
			}
		} catch(err){
			  dialogRechazo = new dijit.Dialog({
			        title: 'Motivo de Rechazo',
			        href: '${context}autorization.do?method=motivoRechazoDetalle',
			        style: 'width:280px; height:330px; top:500px !important;',
			        showTitle: false, draggable : true, closable : false
			  });
			  
			  dojo.style(dialogRechazo.closeButtonNode,"display","none");
			  dialogRechazo._relativePosition = { x: 338, y:315 };
			  dialogRechazo.show();
		}
}

function closeWindowRechazo(){
	  dialogRechazo.hide();
}

function autorizarRegistro(idRegValidar){
	var esEmpresaFraudulenta = dojo.byId('esEmpresaFraudulenta').value;
	
	if(esEmpresaFraudulenta=='true'){
		 var objDetalle = document.getElementById('detalleOferta');
		 var objFraud = document.getElementById('listaEmpresasFraudulentas');
		 var objEmpresa = document.getElementById('detalleEmpresaFraudulentas');
		 objDetalle.style.display = 'none';
		 objFraud.style.display = 'block';
		 objEmpresa.style.display = 'none';
		 dojo.byId('esEmpresaFraudulenta').value = false;
	}else{
		dojo.byId('idRegValidar').value=idRegValidar;	  
		dojo.byId('method').value='autorizarRegistro';
		dojo.byId('autorizationForm').submit();	
	}
	  
	    
}

function autorizarRegistroAdmin(idRegValidar,adminPublisher){
var esEmpresaFraudulenta = dojo.byId('esEmpresaFraudulenta').value;
	
	if(esEmpresaFraudulenta=='true'){
		 var objDetalle = document.getElementById('detalleOferta');
		 var objFraud = document.getElementById('listaEmpresasFraudulentas');
		 var objEmpresa = document.getElementById('detalleEmpresaFraudulentas');
		 objDetalle.style.display = 'none';
		 objFraud.style.display = 'block';
		 objEmpresa.style.display = 'none';
		 
		 dojo.byId('esEmpresaFraudulenta').value = false;
	}else{
	  document.autorizationForm.action='filteroffer.do';
	  dojo.byId('idRegValidar').value=idRegValidar;	  
	  dojo.byId('method').value='autorizarRegistro';
	  dojo.byId('adminPublisher').value=adminPublisher;
	  dojo.byId('autorizationForm').submit();	  
	}
}

function detalleEmpresaFraudulenta(idEmpresaFraudulenta){
	
	dojo.byId('idEmpresaFraudulenta').value=idEmpresaFraudulenta;	  
	dojo.byId('method').value='detalleEmpresaFraudulenta';
	dojo.byId('autorizationForm').submit();	
}

function showWindowEmpFraudulenta(idEmpresaFraudulenta){

	dojo.xhrPost({
		url: '${context}autorization.do?method=detalleEmpresaFraudulenta&idEmpresaFraudulenta='+ idEmpresaFraudulenta,
	  	form:'autorizationForm',
	  	timeout:180000,
	  	load: function(data) {
	  	     var objDetalle = document.getElementById('detalleOferta');
			 var objFraud = document.getElementById('listaEmpresasFraudulentas');
			 var objEmpresa = document.getElementById('detalleEmpresaFraudulentas');
			 objDetalle.style.display = 'none';
			 objFraud.style.display = 'none';
			 objEmpresa.style.display = 'block';
			 dojo.byId('esEmpresaFraudulenta').value = true;
			document.getElementById("detalleEmpresaFraudulentas").innerHTML = data;
			
	    }
	});
	
	 
}

function closeWindowEmpFraudulenta(){
	
	 var objDetalle = document.getElementById('detalleOferta');
	 var objFraud = document.getElementById('listaEmpresasFraudulentas');
	 var objEmpresa = document.getElementById('detalleEmpresaFraudulentas');
	 objDetalle.style.display = 'none';
	 objFraud.style.display = 'block';
	 objEmpresa.style.display = 'none';
	
}



function enviarEmpresaFraudulenta(idEmpresaFraudulenta){
	
	dojo.byId('idEmpresaFraudulenta').value=idEmpresaFraudulenta;	  
	dojo.byId('method').value='registraEmpresaFraudulenta';
	dojo.byId('autorizationForm').submit();	
	
	
}

function regresarDetalleOferta(){
	
	 var objDetalle = document.getElementById('detalleOferta');
	 var objFraud = document.getElementById('listaEmpresasFraudulentas');
	 var objEmpresa = document.getElementById('detalleEmpresaFraudulentas');
	 objDetalle.style.display = 'block';
	 objFraud.style.display = 'none';
	 objEmpresa.style.display = 'none';
	 dojo.byId('esEmpresaFraudulenta').value = true;
	
}

function editarOfertaEmpleo(idRegValidar, idEmpresa, idOfertaEmpleo){
	  dojo.byId('method').value='editarOfertaEmpleo';
	  dojo.byId('idRegValidar').value = idRegValidar;
	  dojo.byId('idEmpresa').value = idEmpresa;
	  dojo.byId('idOfertaEmpleo').value = idOfertaEmpleo;
	  dojo.byId('autorizationForm').submit();
}

function rechazarRegistro(idRegValidar){

	  if(!dijit.byId('idMotivoRechazoSelect').isValid()){
			dijit.byId('idMotivoRechazoSelect').focus();
			alert("El siguiente campo muestra datos inválidos: Motivo. ");								
		return false;
		}			
	  
	if (dijit.byId('idMotivoRechazoSelect').get('item') && dijit.byId('idMotivoRechazoSelect').get('item').label){
		dojo.byId('idMotivoRechazo').value = dijit.byId('idMotivoRechazoSelect').get('item').label[0];
	}
	
	if(dijit.byId('motivoRechazoText').value.length==0){			
		alert("El siguiente campo muestra datos inválidos: Detalle. ");								
		return false;
	}		
	
	if('${!ES_ADMINISTRADOR}'){
	document.autorizationForm.idMotivoRechazo.value = dojo.byId('idMotivoRechazo').value;	
	document.autorizationForm.motivoRechazo.value   = dojo.byId('motivoRechazoText').value;
	closeWindowRechazo();
    dojo.byId('idRegValidar').value=idRegValidar;	  
	dojo.byId('method').value='rechazarRegistro';
	dojo.byId('autorizationForm').submit();		
	}
	
	if('${ES_ADMINISTRADOR}'){
		
		document.location = '${context}autorization.do?method=rechazarRegistro&idMotivoRechazo='+dojo.byId('idMotivoRechazo').value+'&motivoRechazo='+dojo.byId('motivoRechazoText').value+'&idRegValidar='+idRegValidar+'&desdeAdminPendientesPublicar=${desdeAdminPendientesPublicar}';
		}
}

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<style type="text/css">
table.offer td {
	padding: 0;
}
</style>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>


<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet"
	type="text/css" />
</head>
<body>

	<div id="detalleOferta" style="display: block;">
		<%--
    <div class="ficha_oferta">
		<div class="logo_empresa">
			< %--<img src="${context}detalleoferta.do?method=paintLogo" alt="logo Bimbo" />--% >
        </div>
    </div>
    --%>
		<table width="600px" class="offer">
			<tr>
				<td colspan="4"><h2>Oferta de empleo</h2></td>
			</tr>
			<tr>
				<td colspan="4"><strong>${autorizationForm.ofertaDetalle.empresaNombre}</strong></td>
			</tr>
			<tr>
				<td>Oferta de empleo</td>
				<td><strong>${autorizationForm.ofertaDetalle.tituloOferta}</strong></td>
				<td>Tipo de contrato</td>
				<td><strong>${autorizationForm.ofertaDetalle.tipoContrato}</strong></td>
			</tr>
			<tr>
				<td>Horario</td>
				<td><strong>${autorizationForm.ofertaDetalle.horarioLaboral}</strong></td>
				<td>Ubicación</td>
				<td><strong>${autorizationForm.ofertaDetalle.ubicacion}</strong></td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4"><h2>Requisitos</h2></td>
			</tr>
			<tr>
				<td>Escolaridad:</td>
				<td><strong>${autorizationForm.ofertaDetalle.gradoEstudios}</strong></td>
				<td>Carrera o especialidad:</td>
				<td><strong>${autorizationForm.ofertaDetalle.especialidades}</strong></td>
			</tr>
			<tr>
				<td>Situación académica:</td>
				<td><strong>${autorizationForm.ofertaDetalle.situacionAcademica}</strong></td>
				<td>Habilidades y actitudes:</td>
				<%--
				<td><strong>${autorizationForm.ofertaDetalle.habilidadGeneral}</strong></td>
				 --%>				 
			<td><strong>
				<c:if test="${empty autorizationForm.ofertaDetalle.habilidades}">
					<c:out value="${autorizationForm.ofertaDetalle.habilidadGeneral}"/>
				</c:if>

				<c:if test="${not empty autorizationForm.ofertaDetalle.habilidades}">
					<br/>
					<c:forEach var="habilidad" items="${autorizationForm.ofertaDetalle.habilidades}" varStatus="indexHab" >
					${habilidad.opcion}<c:if test="${indexHab.count < fn:length(autorizationForm.ofertaDetalle.habilidades)}">,</c:if>
					</c:forEach>
				</c:if>
				</strong></td> 
			</tr>
			<tr>
				<td>Experiencia:</td>
				<td><strong>${autorizationForm.ofertaDetalle.experienciaAnios}
						años</strong></td>
				<td>Competencias:</td>
				<td><strong>${autorizationForm.ofertaDetalle.requisitos}</strong></td>
			</tr>
			<tr>
				<td>Idiomas:</td>
				<td colspan="3"><strong>${autorizationForm.ofertaDetalle.idiomasCert}</strong></td>
			</tr>
			<tr>
				<td>Edad:</td>
				<td colspan="3"><strong> <%
			if (EDAD_REQUISITO.SI.getIdOpcion() == employ.getEdadRequisito()) {%>
						${autorizationForm.ofertaDetalle.edadMinima} a
						${autorizationForm.ofertaDetalle.edadMaxima} años <%} else {
				out.print("No es requisito.");				
			}%>
				</strong></td>
			</tr>
			<tr>
				<td>Disponibilidad para viajar:</td>
				<td><strong>${autorizationForm.ofertaDetalle.disponibilidadViajar}</strong></td>
				<td>Disponibilidad para radicar:</td>
				<td><strong>${autorizationForm.ofertaDetalle.disponibilidadRadicar}</strong></td>
			</tr>
			<tr>
				<td colspan="4">Observaciones</td>
			</tr>
			<tr>
				<td colspan="4">${autorizationForm.ofertaDetalle.observaciones}</td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4"><h2>La empresa ofrece</h2></td>
			</tr>
			<tr><!-- RBM1 TK1000 TK1001  se homologa con RU -->
				<td>&Aacute;rea:</td>
				<td><strong>${autorizationForm.ofertaDetalle.areaLaboral}</strong></td>
				<td>Sub&aacute;rea:</td>
				<td><strong>${autorizationForm.ofertaDetalle.ocupacion}</strong></td>
			</tr>
			<tr>
				<td>Nivel de puesto:</td>

				<td><strong>${autorizationForm.ofertaDetalle.sector}</strong></td>
				<td>Funciones:</td>
				<td><strong>${autorizationForm.ofertaDetalle.funciones}</strong></td>
			</tr>
			<tr>
				<td>Horario:</td>
				<td><strong>${autorizationForm.ofertaDetalle.horarioLaboral}</strong></td>
				<td>Tipo de contrato:</td>
				<td><strong>
						${autorizationForm.ofertaDetalle.tipoContrato}</strong></td>
			</tr>
			<tr>
				<td>Prestaciones de ley:</td>
				<td><strong>${autorizationForm.ofertaDetalle.prestaciones}</strong></td>
				<td>Sueldo:</td>

				<td><strong>$
						${autorizationForm.ofertaDetalle.salario}</strong></td>
			</tr>
			
			<tr>
				<c:if test="${not empty autorizationForm.ofertaDetalle.descripcionesDiscapacidades and autorizationForm.ofertaDetalle.descripcionesDiscapacidades!=''}">
					<td>Tipo de discapacidad aceptada:</td><td><strong>${autorizationForm.ofertaDetalle.descripcionesDiscapacidades}</strong></td>
				</c:if>		
			
				<c:if test="${ empty autorizationForm.ofertaDetalle.descripcionesDiscapacidades or autorizationForm.ofertaDetalle.descripcionesDiscapacidades==''}">
					<td></td><td></td>
				</c:if>	
			
				<td>No. de plazas:</td>
				<td><strong>${autorizationForm.ofertaDetalle.numeroPlazas}</strong></td>
				
			</tr>
			
		</table>

		<div class="formulario">

			<div>
				<br /> <br />
				<p style="text-align: center">
					<!-- edición -->
					<c:choose>
						<c:when test="${desdeBusquedaOfertas}">
							<c:if
								test="${estatusOferta == activa    or 
			    		              estatusOferta == inactiva    or 
			    		              estatusOferta == modificada  or
			    		              estatusOferta == registrada  or
			    		              estatusOferta == prevalidada or
			    		              estatusOferta == activaAbriendoEspacios or       
			    		              estatusOferta == pendientePublicar}">
								<input type="button" value="Editar" class="boton" 
									onclick="javascript:editarOfertaEmpleo(${autorizationForm.idRegValidar},${autorizationForm.ofertaDetalle.idEmpresa}, ${autorizationForm.ofertaDetalle.idOfertaEmpleo});" />
							</c:if>
						</c:when>
						<c:otherwise>
							<input type="button" value="Modificar oferta de empleo"
								class="boton"
								onclick="javascript:editarOfertaEmpleo(${autorizationForm.idRegValidar},${autorizationForm.ofertaDetalle.idEmpresa}, ${autorizationForm.ofertaDetalle.idOfertaEmpleo});" />
						</c:otherwise>
					</c:choose>

					<!-- autorizacion -->
					<c:choose>
						<c:when test="${desdeBusquedaOfertas}">
							<c:if
								test="${estatusOferta == pendientePublicar and autorizationForm.idRegValidar gt 0}">
								<c:if test="${!ES_ADMINISTRADOR}">
									<input type="button" value="Publicar" class="boton"
										onclick="javascript:autorizarRegistro(${autorizationForm.idRegValidar});" />
								</c:if>
								<c:if test="${ES_ADMINISTRADOR}">
									<c:if test="${desdeAdminPendientesPublicar}">
										<input type="button" value="Publicar" class="boton"
											onclick="javascript:autorizarRegistroAdmin(${autorizationForm.idRegValidar},1);" />
									</c:if>
									<c:if test="${!desdeAdminPendientesPublicar}">
										<input type="button" value="Publicar" class="boton"
											onclick="javascript:autorizarRegistroAdmin(${autorizationForm.idRegValidar},0);" />
									</c:if>
								</c:if>

							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${!ES_ADMINISTRADOR}">
								<input type="button" value="Autorizar registro" class="boton"
									onclick="javascript:autorizarRegistro(${autorizationForm.idRegValidar});" />
							</c:if>
							<c:if test="${ES_ADMINISTRADOR}">
								<input type="button" value="Autorizar registro" class="boton"
									onclick="javascript:autorizarRegistroAdmin(${autorizationForm.idRegValidar},0);" />
							</c:if>
						</c:otherwise>
					</c:choose>

					<!-- eliminar -->
					<c:choose>
						<c:when test="${desdeBusquedaOfertas}">
							<c:if test="${autorizationForm.idRegValidar gt 0}">
								<c:if
									test="${estatusOferta == activa      or 
				    		              estatusOferta == inactiva    or 
				    		              estatusOferta == modificada  or
				    		              estatusOferta == registrada  or
				    		              estatusOferta == prevalidada or
				    		              estatusOferta == activaAbriendoEspacios or				    		              
				    		              estatusOferta == pendientePublicar}">
									<input type="button" value="Eliminar" class="boton"
										onclick="javascript:showWindowMotivoRechazo(${autorizationForm.idRegValidar});" />
								</c:if>
							</c:if>
						</c:when>
						<c:otherwise>
							<input type="button" value="Eliminar registro" class="boton"
								onclick="javascript:showWindowMotivoRechazo(${autorizationForm.idRegValidar});" />
						</c:otherwise>
					</c:choose>
					<c:if test="${!ES_ADMINISTRADOR}">
						<input type="button" value="Cancelar validación" class="boton"
							onclick="javascript:cancelarValidacion(${autorizationForm.idRegValidar});" />
					</c:if>
					<c:if test="${ES_ADMINISTRADOR}">
						<c:if test="${desdeAdminPendientesPublicar}">
							<input type="button" value="Cancelar validación" class="boton"
								onclick="javascript:cancelarValidacionAdmin(${autorizationForm.ofertaDetalle.idOfertaEmpleo},1);" />
						</c:if>
						<c:if test="${!desdeAdminPendientesPublicar}">
							<input type="button" value="Cancelar validación" class="boton"
								onclick="javascript:cancelarValidacionAdmin(${autorizationForm.ofertaDetalle.idOfertaEmpleo},0);" />
						</c:if>
					</c:if>
				</p>
				<br /> <br />
			</div>
			<div class="logoLightBox">
				<img src="images/logo_bottomLightBox.gif" alt="Portal del Empleo" />
			</div>
		</div>
	</div>

	<div id="listaEmpresasFraudulentas" style="display: none;">
		<strong>Se detectó que la empresa
			${autorizationForm.ofertaDetalle.empresaNombre} dueña de la oferta
			${autorizationForm.ofertaDetalle.tituloOferta} es coincidente con
			${fn:length(autorizationForm.empresasFraudulentas)} registros de
			empresas catalogadas como fraude.</strong>
		<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tbody>
				<tr class="temas">
					<th>Empresa</th>
					<th>Dato coincidente</th>
					<th>Fuente</th>
					<th>Nombre usuario</th>
				</tr>
				<c:forEach var="empresa"
					items="${autorizationForm.empresasFraudulentas}"
					varStatus="rowCounter">
					<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
						<td><a class="btn_portal" href="javascript:showWindowEmpFraudulenta(${empresa.idEmpresa})">${empresa.nombre}</a></td>
						<td>${empresa.coincidencias}</td>
						<td>Portal del empleo</td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="formulario">
			<div>
				<br /> <br />
				<p style="text-align: center">
					<input type="button" value="Enviar a empresa fraudulenta" class="boton"
									onclick="javascript:enviarEmpresaFraudulenta(${autorizationForm.ofertaDetalle.idEmpresa});" />
					<c:choose>
						<c:when test="${desdeBusquedaOfertas}">
							<c:if
								test="${estatusOferta == pendientePublicar and autorizationForm.idRegValidar gt 0}">
								<c:if test="${!ES_ADMINISTRADOR}">
									<input type="button" value="Publicar" class="boton"
										onclick="javascript:autorizarRegistro(${autorizationForm.idRegValidar});" />
								</c:if>
								<c:if test="${ES_ADMINISTRADOR}">
									<c:if test="${desdeAdminPendientesPublicar}">
										<input type="button" value="Publicar" class="boton"
											onclick="javascript:autorizarRegistroAdmin(${autorizationForm.idRegValidar},1);" />
									</c:if>
									<c:if test="${!desdeAdminPendientesPublicar}">
										<input type="button" value="Publicar" class="boton"
											onclick="javascript:autorizarRegistroAdmin(${autorizationForm.idRegValidar},0);" />
									</c:if>
								</c:if>

							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${!ES_ADMINISTRADOR}">
								<input type="button" value="Autorizar registro" class="boton"
									onclick="javascript:autorizarRegistro(${autorizationForm.idRegValidar});" />
							</c:if>
							<c:if test="${ES_ADMINISTRADOR}">
								<input type="button" value="Autorizar registro" class="boton"
									onclick="javascript:autorizarRegistroAdmin(${autorizationForm.idRegValidar},0);" />
							</c:if>
						</c:otherwise>
					</c:choose>
					<input type="button" value="Regresar al detalle de la oferta" class="boton"
									onclick="javascript:regresarDetalleOferta();" />
				</p>
			</div>
		</div>

		<div class="logoLightBox">
			<img src="images/logo_bottomLightBox.gif" alt="Portal del Empleo" />
		</div>
	</div>
	
	
	<div id="detalleEmpresaFraudulentas" style="display: none;">
		

		<div class="logoLightBox">
			<img src="images/logo_bottomLightBox.gif" alt="Portal del Empleo" />
		</div>
	</div>

	<form name="autorizationForm" id="autorizationForm" method="post"
		action="autorization.do">
		<input type="hidden" name="method" id="method" value="init" />
		<input type="hidden" name="idRegValidar" id="idRegValidar" value="0" />
		<input type="hidden" name="idEmpresa" id="idEmpresa" value="0" />
		<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo"
			value="0" />
		<input type="hidden" name="idMotivoRechazo" id="idMotivoRechazo"
			value="0" />
		<input type="hidden" name="motivoRechazo" id="motivoRechazo" value="" />
		<input type="hidden" name="adminPublisher" id="adminPublisher"
			value="0" />
		<input type="hidden" name="desdeAdminPendientesPublicar"
			id="desdeAdminPendientesPublicar"
			value="${desdeAdminPendientesPublicar}" />
		<input type="hidden" name="esEmpresaFraudulenta"
			id="esEmpresaFraudulenta"
			value="${not empty autorizationForm.empresasFraudulentas}" />
			<input type="hidden" name="idEmpresaFraudulenta" id="idEmpresaFraudulenta" value="0" />
		<%
			if (request.getSession().getAttribute("alert4Admin") != null && request.getSession().getAttribute("alert4Admin") != "") {
				String msg = String.valueOf(request.getSession().getAttribute("alert4Admin"));
		%>
				<script>message('<%=msg%>');</script>
		<%
				request.getSession().removeAttribute("alert4Admin");
			}
		%>
	</form>
</body>
</html>
