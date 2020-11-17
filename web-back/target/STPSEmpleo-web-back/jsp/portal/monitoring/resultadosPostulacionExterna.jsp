<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>


<fmt:setBundle basename="MessageResources" var="portalbundle"/>
<fmt:setBundle basename="portal-web" var="messages"/>


<%
String context = request.getContextPath() +"/";  
%>

<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.form.Form");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ComboBox");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.FilteringSelect");
	

	function  guarda(){
		doRegistraSeguimiento();
	}
	
	
	
	

	function alertMsg(msg){
		alert(msg);
	}
	
	
	
    function muestraComponentes(){
    	if(dojo.byId('si').checked){
    		
    		dijit.byId('diaSelect').set('disabled',false);
    		dijit.byId('mesSelect').set('disabled',false);
    		dijit.byId('anioSelect').set('disabled',false);
    		dijit.byId('motivoSelect').set('disabled',true);
			dijit.byId('otroMotivo').set('disabled',true);
			dijit.byId('otroMotivo').set('value',null);
			dijit.byId('motivoSelect').set('value',null);
			document.getElementById('leyendaPPC').innerHTML = 'En el momento de guardar tus datos de contratación, tu inscripción en el PPC será dada de baja.';
    	}else if(dojo.byId('no').checked){
    		dijit.byId('motivoSelect').set('disabled',false);
    		dijit.byId('diaSelect').set('disabled',true);
    		dijit.byId('mesSelect').set('disabled',true);
    		dijit.byId('anioSelect').set('disabled',true);
    		dijit.byId('anioSelect').set('value',null);
    		dijit.byId('mesSelect').set('value',null);
    		dijit.byId('diaSelect').set('value',null);
    		document.getElementById('leyendaPPC').innerHTML = '';
    	}else{
    		dijit.byId('diaSelect').set('disabled',true);
    		dijit.byId('mesSelect').set('disabled',true);
    		dijit.byId('anioSelect').set('disabled',true);
    		dijit.byId('motivoSelect').set('disabled',true);
			dijit.byId('otroMotivo').set('disabled',true);
			dijit.byId('otroMotivo').set('value',null);
			dijit.byId('motivoSelect').set('value',null);
			dijit.byId('anioSelect').set('value',null);
    		dijit.byId('mesSelect').set('value',null);
    		dijit.byId('diaSelect').set('value',null);
    		document.getElementById('leyendaPPC').innerHTML = '';
    	}
    }
    
    function hideMsj(){
    	dijit.byId('msjFecha').hide();
    }
    
    function muestraOtroMotivo(){
     	if(dijit.byId('motivoSelect') == 9){
     		dijit.byId('otroMotivo').set('disabled',false);
     	}else{
     		dijit.byId('otroMotivo').set('disabled',true);
     		dijit.byId('otroMotivo').set('value',null);
     	}
    }
    
    function enviarAMiEspacioCandidato(){
    	dijit.byId('MensajeAlerConfirt').hide();
    	window.location.href = '<c:url value="/miEspacioCandidato.do"/>'
    
    }
    
    function cancelar(){
		dojo.byId('mensajeConfir').innerHTML = 'Los datos no guardados se perderán ¿Continuar?';
		dijit.byId('MensajeAlerConfirt').show();		
    }
    
    function closeDialog(){
        dijit.byId('MensajeAlerConfirt').hide();
        setTimeout("window.location.reload();",10);
	}    
</script>

<c:if test="${showDateMsj==1}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			dijit.byId('msjFecha').show();
		});	
		
	</script>
</c:if>

<div dojoType="dojo.data.ItemFileReadStore" jsId="diasContratoStore"	url="${context}seguimientoPostulacionExterna.do?method=dias"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="mesesContratoStore"   url="${context}seguimientoPostulacionExterna.do?method=meses"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosContratoStore"   url="${context}seguimientoPostulacionExterna.do?method=anios"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="motivoContratoStore"   url="${context}seguimientoPostulacionExterna.do?method=motivos"></div>

			<fieldset>
			    	<legend>Datos de la empresa</legend>
			        <div class="grid1_3 margin_top_20">
			            <label>Nombre de la empresa que ofrece la oferta</label>
			            <c:out value="${POSTULACION.nombreEmpresa}"></c:out>    
			        </div>
			        <div class="margin_top_20">
			            <div class="grid1_3 fl">
				            <label>Persona de contacto</label>
				            <c:out value="${POSTULACION.contactoEmpresa}"></c:out>
				        </div>
			            <div class="grid1_3 fl">
				            <label>Cargo</label>
				            <c:out value="${POSTULACION.contactoCargo}"></c:out>
				        </div>
			            <div class="clearfix"></div>
			        </div>
			    </fieldset>	
			<fieldset>
			    	<legend>Datos de contacto</legend>
			       <div class="margin_top_20">
			            <div class="grid1_3 fl">
				            <label>Teléfono</label>
				            <c:out value="${POSTULACION.acceso}-${POSTULACION.clave}-${POSTULACION.telefono}   Extensión: ${POSTULACION.extension}"></c:out>
				        </div>
			            <div class="grid1_3 fl">
				            <label>Correo electrónico</label>
				            <c:out value="${POSTULACION.contactoCorreoElectronico}"></c:out>
				        </div>
			            <div class="clearfix"></div>
			        </div>
			    </fieldset>	
			   <fieldset>
			    	<legend>Datos de la oferta</legend>
			       <div class="margin_top_20">
			            <div class="grid1_3 fl">
				            <label>Título de la oferta</label>
				            <c:out value="${POSTULACION.tituloOferta}"></c:out>
				        </div>
			            <div class="grid1_3 fl">
				            <label>Salario</label>
				            <fmt:formatNumber type="currency" value="${POSTULACION.salario}" var="sueldo"></fmt:formatNumber>
				            <c:out value="${sueldo}"></c:out>
				        </div>
			            <div class="clearfix"></div>
			        </div>
			        <div class="grid1_3 margin_top_20">
			            <label>¿Cómo te enteraste de la oferta de empleo?</label>
			            <c:out value="${POSTULACION.medioPortal}"></c:out>    
			        </div>
			    </fieldset>	
			  <fieldset>
			    	<legend>Fechas</legend>
			       <div class="margin_top_20">
			            <div class="grid1_3 fl">
				            <label>Fecha de contacto</label>
				            <fmt:formatDate value="${POSTULACION.fechaContacto}" pattern="dd-MM-yyyy" var="fechaCont"/>
				            <c:out value="${fechaCont}"></c:out>
				        </div>
			            <div class="grid1_3 fl">
				            <label>Fecha de entrevista</label>
				            <fmt:formatDate value="${POSTULACION.fechaEntrevista}" pattern="dd-MM-yyyy" var="fechaEnt"/>
				            <c:out value="${fechaEnt}"></c:out>
				        </div>
			            <div class="clearfix"></div>
			        </div>
			    </fieldset>	
			    <fieldset>
			    <legend>Resultado de la entrevista</legend>
			    <c:out value="Llena los siguientes campos para ingresar el resultado de tu entrevista"></c:out>
			    <div class="margin_top_20" style="vertical-align: top;">
			    <div class="labeled margin_no_t"><span>*</span> ¿Fuiste contratado?</div>
			    	<div class="grid1_3 fl" style="padding-right: 20px;">   
								<div  >
									<input type="radio" name="contratado" id="si"
								       value="1" onclick="muestraComponentes()" >
								       Sí
								</div><br/>
								<div >
									<input type="radio" name="contratado" id="no"
							    	   value="2" onclick="muestraComponentes()">
							    	   No
								</div><br/>
								<div style="width:385px">
									<input type="radio" name="contratado" id="enEspera"
							    	   value="3" onclick="muestraComponentes()">
							    	   Estoy en espera del resultado del proceso de selección
								</div>
								
			                </div>
			                
			                	<div class="labeled"> ¿A partir de qué fecha quedaste contratado?</div>
			                	 <div class="grid1_3 fl" style="width: 600px;">
			               		<select id="diaSelect" name="diaSelect" 
									required="false" missingMessage="día de fecha de contacto."
									invalidMessage="Día de fecha de contacto inválido, favor de verificar." 
									promptMessage="Día de fecha de contacto" maxHeight="250" 
									regExp="${regexpdia}" disabled="true"
						        	value="${registroPostulacionForm.diaContrato}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="diasContratoStore">
						    </select>

			                </select>
							<select id="mesSelect" name="mesSelect"
								    required="false" missingMessage="mes de fecha de contacto."
								    invalidMessage="Mes de fecha de contacto inválido, favor de verificar."
							    	promptMessage="Mes de fecha de contacto" maxHeight="250" disabled="true"
						        	value="${registroPostulacionForm.mesContrato}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="mesesContratoStore">
						    </select>

							<select id="anioSelect" name="anioSelect" disabled="true"
									required="false" missingMessage="año de fecha de contacto."
									invalidMessage="Año de fecha de contacto inválido, favor de verificar." 
									promptMessage="Año de fecha de contacto" maxHeight="250"
						        	value="${registroPostulacionForm.anioContrato}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="aniosContratoStore">
						    </select>
			            </div>
			               
			                 <div class="clearfix"></div>
			    </div>
			    <div class="margin_top_20">
			    	<div class="grid1_3 fl" style="width: 400px;">
				            <label>¿Cuál fue el motivo por el que no te contrataron?</label>
				           <select id="motivoSelect" name="motivoSelect" disabled="true"
									required="false" missingMessage="motivo por el que no te contrataron." 
									invalidMessage="Motivo de contratación inválido, favor de verificar." 
									promptMessage="Motivo de no contratación" maxHeight="250"
						        	value="${registroPostulacionForm.idMotivoNoContratacion}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="motivoContratoStore" onchange="javascript:muestraOtroMotivo()">
						    </select>
						   
				        </div>
				       <div class="grid1_3 fl">
				       <label>Especifica el motivo</label> 
			
				       <input type="text" name="otroMotivo" id="otroMotivo"
				               required="false" missingMessage="otro motivo"
				               regExp="${regexpnombrecontacto}" 
				               invalidMessage="motivo inválido favor de verificar."
				               value="${registroPostulacionForm.otroMotivo}" 
				        	   maxlength="150" dojoType="dijit.form.ValidationTextBox"
				        	  disabled="true" />	    
				       </div>
				        <div class="clearfix"></div>
			    </div>
			    </fieldset>
			    <div  id="leyendaPPC" style="font-weight: bold; color: olive;"></div>
			    <div class="form_nav" id="div_form_nav">
						<input id="btnGuardar" name="btnGuardar" type="button" value="Guardar" 		onclick="guarda();"/>
						<input id="btnCancel"  name="btnCancel"  type="button" value="Cancelar" 	onclick="cancelar();"/>
				</div>	
	    







