<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<script type="text/javascript">
	require(["dojo/cookie","dijit/Dialog", "dijit/form/TextBox", "dijit/form/Button", "dojo/domReady!"]);
	
	
	
   function doSubmitPager(method) {
	  	  dojo.xhrPost({url: 'detalleEvento.do?method='+method+'&tablaPager=_EMPRESAS&goToPageNumber='+pagina, form:'pagForm', timeout:180000, 
			  load: function(data){
			    dojo.byId('tabla').innerHTML=data;
			    
		  }});
	    }
	    
	
	function regresar(){
		window.location.href='<c:url value="/registrarCandidatoEvento.do?method=init"/>';
	}
	
	function getAnyElementValueById(elementId) {
		var vElement = dojo.byId(elementId).value;
		if(vElement==undefined || vElement==''){
			vElement = document.getElementById(elementId).value;
		}
		return vElement;
	}


   	function doRegistraEvento(){
   			dojo.xhrGet( {
				url: 'detalleEvento.do?method=registraAEvento',
		  		form:'registerForm',
		  	 	sync: true,
		  		timeout:180000});
		  		window.location.href='${context}detalleEvento.do?method=init&idEvento='+${registraCandidatoEventoForm.evento.idEvento};
	}
   	
    	
   	function doImprimeComprobante(){
   			dijit.byId('msjExito').hide();
   			window.location.href='registrarCandidatoEvento.do?method=init&imprimir=true';
   		}
   	
   	 	 function hideMsj(){
   			dijit.byId('msgErrores').hide();
   			window.location.href = '${context}detalleEvento.do?method=reset';
   	
   	}
   	
	function doSubmitPagina(pagina, dif) {
    		dojo.xhrPost({url: 'detalleEvento.do?method=goToPage&tablaPager=_EMPRESAS&goToPageNumber='+pagina, timeout:180000, 
				load: function(dataCand){
    	 			dojo.byId('tabla').innerHTML=dataCand;
    	 			
			}});
    }
    
     function cerrarMsjExito(){
    	dijit.byId('msjExito').hide();
    	window.location.href = '<c:url value="/registrarCandidatoEvento.do?method=init"/>';
    	
    }
    
   
</script>
<c:if test="${showExito == 1}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			dijit.byId('msjExito').show();
		});	
		
	</script>
</c:if>

<c:if test="${showError == 1}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			dijit.byId('msgErrores').show();
		});	
		
	</script>
</c:if>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<div class="formApp miEspacio">
 <h2>Mi espacio</h2>
	<div class="tab_block">
		<div align="left" style="display:block;" id="returnME">
            <a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
			<strong>Ir al inicio de Mi espacio</strong></a>
        </div>
	        <div class="Tab_espacio">
	            <h3>Programas y servicios</h3>
	            <div class="subTab">
					<ul>
						<li>
							<a href="<c:url value="/entrevistaProgramada.do?method=entrevistaProgramadaEnlineaCandidato"/>">Consultar mis entrevistas en línea</a>
						</li>
						<li>
							<a href="#">Solicitar una cita</a>
						</li>
						<li class="curSubTab">
							<span>Registrarme a un evento de Ferias de Empleo</span>
						</li>
						<!--INI_JGLC_PPC-->
<%-- 						<c:if test="${candidatoheader.isActiveToPpc() || candidatoheader.isInactiveToPpc()}"> --%>
<!-- 							<li class="width_f"> -->
<!-- 								<a target="_blank" href="/miespacionav.do?method=terminosCondicionesPpcEnPdf" id="imprimir_terminos_condiciones_ppc_sd">Imprimir los términos y condiciones del Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD)</a> -->
<!-- 							</li> -->
<%-- 						</c:if> --%>
						<!--FIN_JGLC_PPC-->
					</ul>
	                <div class="clearfix"></div>
	            </div>
	            <div class="clearfix"></div>
	        </div>
	</div>

<h3 style="padding-top: 30px
">Detalle del evento</h3>
<div class="postulacione_e">				
	<form name="registraCandidatoEventoForm" id="registraCandidatoEventoForm" action="detalleEvento.do" method="post" >
	<fieldset>
		<legend>Datos generales</legend>
		<div class="margin_top_20">
			<div class="grid1_3 fl" style="width: 600px;">
				<label>Nombre del evento</label>
				<c:out value="${registraCandidatoEventoForm.evento.evento}"></c:out>
			</div>
			<div class="grid1_3 fl">
				<label>Tipo de evento</label>
				<c:out value="${registraCandidatoEventoForm.evento.ambiente}"></c:out>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="margin_top_20">
			<div class="grid1_3 fl">
				<label>Fecha</label>
				<c:out value="${registraCandidatoEventoForm.evento.fechaE}"></c:out>
			</div>
			<div class="grid1_3 fl">
				<label>Horario</label>
				<c:out value="${registraCandidatoEventoForm.evento.horario}"></c:out>
			</div>
			<div class="grid1_3 fl">
				<label>Lugar</label>
				<c:if test="${registraCandidatoEventoForm.evento.idAmbiente == 1}">
					<c:out value="${registraCandidatoEventoForm.evento.sede}"></c:out>
				</c:if>
				<c:if test="${registraCandidatoEventoForm.evento.idAmbiente == 2}">
					<a  href="<%=request.getContextPath()%>/detalleEvento.do?method=redirectToVirtualEvent" target="_blank">Ir al evento</a>
				</c:if>
			</div>
			<div class="clearfix"></div>
		</div>
	</fieldset>
	<fieldset>
		<legend>Lugar donde se llevará a cabo</legend>
		<div class="margin_top_20" style="line-height: 140%; width: 95%">
			<c:out value="${registraCandidatoEventoForm.evento.lugar}"></c:out>
		</div>
		<div class="margin_top_20">
			<label>Referencia para llegar al evento</label>
			<c:out value="${registraCandidatoEventoForm.evento.referenciaDomicilio}"></c:out>
		</div>
	</fieldset>
	</form>
</div>
<div id="cont-resultados">
	<div id="tabla">
		<c:choose>
			<c:when test="${total == 0}">
				<div id="msjTam" class="gris" style="line-height: 160%; margin: 20px auto 0; padding-bottom: 10px; text-align: center; width: 599px">
				No se encontraron empresas con ofertas para este evento.
				</div>
			</c:when>
			<c:otherwise>	
				<table class="offer" style="width: 620px; margin: 40px auto 0">
					<caption style="text-align: center; font-size: 14px">Algunas empresas que participan</caption>
					<tr class="temas">
						<th>Empresas participantes al día de hoy</th>
						<th style="width: 115px">Número de plazas</th>
					</tr>
					<logic:iterate id="empresas" collection='<%= request.getSession(false).getAttribute("PAGE_LIST_EMPRESAS") %>' indexId="empresasIndexId">
						<bean:define id="indexMod3" value='<%= String.format("%d",empresasIndexId % 2) %>'/>
						<logic:equal name="indexMod3" value="0">
							<bean:define id="trClase" value=""/>
						</logic:equal>
						<logic:notEqual name="indexMod3" value="0">
							<bean:define id="trClase" value="odd"/>
						</logic:notEqual>
						<tr class="${trClase}">
							<td>
								<bean:write name="empresas" property="nombreEmpresa"/>
							</td>
							<td style="text-align: center">
								<bean:write name="empresas" property="noPlazasEvento"/>
							</td>
						</tr>
					</logic:iterate>
				</table>
				<bean:define id="sufijo" value="_EMPRESAS"/>
				<c:if test="${total > 0}">
					<jsp:include page="../layout/pager.jsp" flush="true">
						<jsp:param name="SUFIJO" value="${sufijo}"/>
						<jsp:param name="tipoRegistros" value="empresas"/>		 
					</jsp:include>
				</c:if>
			</c:otherwise>
		</c:choose>
		<br/>
		<div class="gris" style="line-height: 160%; margin: auto; padding-bottom: 10px; text-align: center; width: 599px;">
			Una vez registrado al evento podrás consultar el detalle de todas las ofertas de empleo<br> ingresando con tu usuario y contraseña del Portal del Empleo a 
			<a href="http://ferias.empleo.gob.mx">http://ferias.empleo.gob.mx</a>
		</div>
		<form name="registerForm" id="registerForm">
			<div class="form_nav" id="div_form_nav">
				<c:if test="${registered == 0}">
					<input type="button" value="Registrarme" onclick="javascript:doRegistraEvento()" id="btnReg"/>
				</c:if>
				<c:if test="${registered == 1}">
					<a href="<%=request.getContextPath()%>/detalleEvento.do?method=imprimirComprobante" id="nueva-busqueda" class="boton_naranja nueva-busqueda">Imprimir Comprobante</a>
				</c:if>
			</div>
			<div dojoType="dijit.Dialog" id="msjExito" title="Aviso" draggable ="false">
				<div class="msg_contain">
					<br/>
					<p>Te has registrado exitosamente al evento.<br>
					<strong>No olvides imprimir tu comprobante de participación.</strong></p>		
					<p class="form_nav 2" >
						<button id="btnAceptar" class="button" onclick="javascript:doImprimeComprobante();" >Imprimir comprobante</button>
						<button id="btnAceptar" class="button" onclick="javascript:cerrarMsjExito();">Regresar</button>										
					</p>
				</div>
			</div>	
			<div dojoType="dijit.Dialog" id="msgErrores" title="Error" draggable="false" style="display:none">
				<div class="msg_contain">
					<p>Se ha presentado un problema con el registro.</p>
					<p>Por favor contacta con el administrador.</p>
				</div>
				<p class="form_nav">	
					<button class="button" onclick="javascript:hideMsj();">Aceptar</button>
				</p>
			</div>
		</form>
	</div>
	<div class="form_nav" id="div_form_nav" style="position: relative">
		<!-- <a class="boton_naranja nueva-busqueda" id="nueva-busqueda" href="<%=request.getContextPath()%>/registrarCandidatoEvento.do?method=init" >Regresar</a> -->
		<button class="button" onclick="javascript:regresar();">Regresar</button>
	</div>
	<form name="pagForm" id="pagForm" method="post" action="detalleEvento.do" dojoType="dijit.form.Form">
		<input type="hidden" name="goToPageNumber" id="goToPageNumber" value="1" />
	</form>	
	<script>
		doSubmitPager('page');
	</script>	
</div>
</div>