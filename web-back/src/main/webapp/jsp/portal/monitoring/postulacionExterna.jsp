<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>


<fmt:setBundle basename="MessageResources" var="portalbundle"/>
<fmt:setBundle basename="portal-web" var="messages"/>

<c:set var="regexpnombrecontacto">^[A-Za-z\s\d\áéíóúÁÉÍÓÚñÑ']{1,150}$</c:set>


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
	

	dojo.addOnLoad(function(){
		var post = ${POSTULACION.estatus};
		if(post == 3){
			document.getElementById("enEspera").disabled = true;
		}else{
			document.getElementById("enEspera").disabled = false;
		};			
	});
	
	
	
	function  guarda(){
		doRegistraSeguimiento();
	}
	
	
	
	function doRegistraSeguimiento(){
	
		if(validaRadios()){
				//alert('todo bien');
				dojo.xhrPost( {
					url: 'seguimientoPostulacionExterna.do?method=registrar', 
			  		form:'registroPostulacionForm',
			  	 	sync: true,
			  		timeout:180000
		  		});
		  		window.location.href = '${context}seguimientoPostulacionExterna.do?method=cargaDetallePostulacion&idPostulacionExterna='+${POSTULACION.idPostulacionExterna};
		}
	}

	function alertMsg(msg){
		alert(msg);
	}
	
	function validaRadios(){
		if(!dojo.byId('no').checked && !dojo.byId('si').checked && !dojo.byId('enEspera').checked){
			alertMsg('El campo ¿Fuiste Contratado? es requerido.');
			return false;
		}else{
			if(dojo.byId('si').checked){
				var valida = validaFecha();
				return valida;
			}else if(dojo.byId('no').checked){
				var validaM = validaMotivo();
				return validaM;
			}else if(dojo.byId('enEspera').checked){
				return true;
			}
		
		}
	}
	
	function validaFecha(){
		if(parseInt(dijit.byId('diaSelect').get('value')) > 0 && dijit.byId('diaSelect').isValid() && dijit.byId('mesSelect').isValid() && parseInt(dijit.byId('mesSelect').get('value'))>0 && parseInt(dijit.byId('anioSelect').get('value'))>0 && dijit.byId('anioSelect').isValid()){
			return true;
		}else{
				if(!dijit.byId('diaSelect').isValid()){
					alertMsg('Día de fecha de contrato invalido, no se permiten caracteres especiales');
					dijit.byId('diaSelect').focus();
					return false;
				}else if(dijit.byId('diaSelect').get('value') == '0'){
					alertMsg('El Día en el campo ¿A partir de qué fecha quedaste contratado? es requerido');
					dijit.byId('diaSelect').focus();
					return false;
				}
				if(!dijit.byId('mesSelect').isValid()){
					alertMsg('Mes de fecha de contrato invalido, no se permiten caracteres especiales');
					dijit.byId('mesSelect').focus();
					return false;
				}else if(dijit.byId('mesSelect').get('value') == '0'){
					alertMsg('El Mes en el campo ¿A partir de qué fecha quedaste contratado? es requerido.');
					dijit.byId('mesSelect').focus();
					return false;
				}
				if(!dijit.byId('anioSelect').isValid()){
					alertMsg('Año de fecha de contrato invalido, no se permiten caracteres especiales');
					dijit.byId('anioSelect').focus();
					return false;
				}else if(dijit.byId('anioSelect').get('value') == '0'){
					alertMsg('El Año en el campo ¿A partir de qué fecha quedaste contratado? es requerido.');
					dijit.byId('anioSelect').focus();
					return false;
				}
		}
	}
	
	
	function validaMotivo(){
	//alert(dijit.byId('motivoSelect').get('value'));
		if(dijit.byId('motivoSelect').isValid() && dijit.byId('motivoSelect')!= 9 && parseInt(dijit.byId('motivoSelect').get('value')) > 0){
			return true;
		}
		if(dijit.byId('motivoSelect').isValid() && dijit.byId('motivoSelect') == 9 && parseInt(dijit.byId('motivoSelect').get('value')) > 0){
			if(dojo.byId('otroMotivo').value == ''){
				alertMsg('El campo Especifica el motivo es requerido.');
				return false;
			}else if(!dijit.byId('otroMotivo').isValid()){
				alertMsg('Datos en el campo especifica el motivo inválidos, no se permiten caracteres especiales.');
				return false;			
			}else{
				return true;
			}
		}
		
		if(!dijit.byId('motivoSelect').isValid()){
				alertMsg('Datos inválidos en el campo motivo por el que no te contrataron, no se permiten caracteres especiales.');
				dijit.byId('motivoSelect').focus();
				return false;
		}	
		if(dijit.byId('motivoSelect') == 0){
				alertMsg('El campo ¿Cuál fue el motivo por el que no te contrataron? es requerido.');
				dijit.byId('motivoSelect').focus();
				return false;
		}
	}
	
    function muestraComponentes(){
    	if(dojo.byId('si').checked){
    		dijit.byId('diaSelect').set('required',true);
    		dijit.byId('mesSelect').set('required',true);
    		dijit.byId('anioSelect').set('required',true);
    		dijit.byId('diaSelect').set('value','0');
    		dijit.byId('mesSelect').set('value','0');
    		dijit.byId('anioSelect').set('value','0');
    		document.getElementById("leyDate").style.display = 'block';
    		document.getElementById("fechas").style.display = 'table-row';
    		document.getElementById("motivos").style.display = 'none';
			dijit.byId('otroMotivo').set('value',null);
			dijit.byId('motivoSelect').set('value','0');
			dijit.byId('motivoSelect').set('required',false);
			document.getElementById('leyendaPPC').innerHTML = 'En el momento de guardar tus datos de contratación, tu inscripción en el PPC será dada de baja.';
    	}else if(dojo.byId('no').checked){
    		dijit.byId('motivoSelect').set('required',true);
    		dijit.byId('motivoSelect').set('value','0');
    		dijit.byId('diaSelect').set('required',false);
    		dijit.byId('mesSelect').set('required',false);
    		dijit.byId('anioSelect').set('required',false);
    		document.getElementById("fechas").style.display = 'none';
    		document.getElementById("leyDate").style.display = 'none';
    		document.getElementById("motivos").style.display = 'block';
    		dijit.byId('anioSelect').set('value',null);
    		dijit.byId('mesSelect').set('value',null);
    		dijit.byId('diaSelect').set('value',null);
    		document.getElementById('leyendaPPC').innerHTML = '';
    	}else{
    		dijit.byId('diaSelect').set('required',false);
    		dijit.byId('mesSelect').set('required',false);
    		dijit.byId('anioSelect').set('required',false);
    		document.getElementById("fechas").style.display = 'none';
    		document.getElementById("leyDate").style.display = 'none';
       		document.getElementById("motivos").style.display = 'none';
			document.getElementById("otros").style.display = 'none';
			dijit.byId('otroMotivo').set('value',null);
			dijit.byId('motivoSelect').set('value','0');
			dijit.byId('anioSelect').set('value',null);
    		dijit.byId('mesSelect').set('value',null);
    		dijit.byId('diaSelect').set('value',null);
    		dijit.byId('motivoSelect').set('required',false);
    		document.getElementById('leyendaPPC').innerHTML = '';
    	}
    }
    
    function hideMsj(){
    	dijit.byId('msjFecha').hide();
    	dojo.xhrPost( {
					url: 'seguimientoPostulacionExterna.do?method=cerrarMsjs', 
			  		form:'registroPostulacionForm',
			  	 	sync: true,
			  		timeout:180000
		  		});
    }
    
    function cierraMsjCancelar(){
    	dijit.byId('MensajeAlerConfirt').hide();
    }
    
    function muestraOtroMotivo(){
     	if(dijit.byId('motivoSelect') == 9){
     		document.getElementById("otros").style.display = 'block';
     	}else{
     		document.getElementById("otros").style.display = 'none';
     		dijit.byId('otroMotivo').set('value',null);
     	}
    }
    
    function enviarAMiEspacioCandidato(){
    	dijit.byId('MensajeAlerConfirt').hide();
    	window.location.href = '<c:url value="/miEspacioCandidato.do"/>'
    
    }
    
    function cerrarMsjExito(){
    	dijit.byId('msjExito').hide();
    	dijit.byId('msjNo').hide();
    	dijit.byId('msjProceso').hide();
    	window.location.href = '${context}seguimientoPostulacionExterna.do?method=init';
    	
    }
    
    function cancelar(){
		
		dijit.byId('MensajeAlerConfirt').show();		
    }
    
    function closeDialog(){
        dijit.byId('MensajeAlerConfirt').hide();
        window.location.href = '${context}seguimientoPostulacionExterna.do?method=init';
	}    
</script>

<c:if test="${showDateMsj==1}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			dijit.byId('msjFecha').show();
		});	
		
	</script>
</c:if>

<c:if test="${showMsjExito == 1}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			dijit.byId('msjExito').show();
		});	
		
	</script>
</c:if>

<c:if test="${showMsjNo == 1}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			dijit.byId('msjNo').show();
		});	
		
	</script>
</c:if>


<c:if test="${showMsjProcess == 1}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			dijit.byId('msjProceso').show();
		});	
		
	</script>
</c:if>

<c:if test="${not empty ERROR_MSG}">
	<script type="text/javascript">
		dojo.addOnLoad(function(){
			dijit.byId('msgErrores').show();
		});	
		
	</script>
</c:if>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div id="mis_ofertas" class="formApp miEspacio">


        <h2>Mi espacio</h2>

        <div class="tab_block">
            <div align="left" style="display:block;" id="returnME">
                <a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
                    <strong>Ir al inicio de Mi espacio</strong>
                </a>
            </div>
            <div class="Tab_espacio">
                <h3>Mis ofertas de empleo</h3>

                <div class="subTab">
                    <ul>
                        <li>
                            <a href="<c:url value="/misofertas.do?method=misPostulaciones"/>">Mis postulaciones</a>
                        </li>
                        <li>
                            <a href="<c:url value="/misofertas.do?method=misOfertasGuardadas"/>">Mis ofertas guardadas</a>
                        </li>
                        <li>
                            <a href="<c:url value="/misofertas.do?method=empresasQueMeBuscan"/>">Empresas que me buscan</a>
                        </li>
                        <!--INI_JGLC_PPC-->
<!--                         <li > -->
<%--                             <a href="${context}registroPostulacionExterna.do?method=init">Registrar postulaciones externas</a> --%>
<!--                         </li> -->
<!--                         <li class="curSubTab"> -->
<!--                         	<span >Seguimiento a postulaciones externas</span> -->
                           
<!--                         </li> -->
<!--                         <li> -->
<!--                             <a href="/adminNoPostulacionesCandidato.do?method=init" id="registrar_motivo_no_postulacion">Registrar motivo de no postulaci&oacute;n</a> -->
<!--                         </li> -->
                        <!--FIN_JGLC_PPC-->
                    </ul>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>


<div dojoType="dojo.data.ItemFileReadStore" jsId="diasContratoStore"	url="${context}seguimientoPostulacionExterna.do?method=dias"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="mesesContratoStore"   url="${context}seguimientoPostulacionExterna.do?method=meses"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="aniosContratoStore"   url="${context}seguimientoPostulacionExterna.do?method=anios"></div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="motivoContratoStore"   url="${context}seguimientoPostulacionExterna.do?method=motivos"></div>

<p style="font-size:16px; font-weight: bold; margin-left:25px;">Detalle de la postulación externa</p>
<form name="registroPostulacionForm" id="registroPostulacionForm" method="post" action="seguimientoPostulacionExterna.do" dojoType="dijit.form.Form">

<div id="formulario" class="postulacione_e">
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
				            <fmt:setLocale value="es_MX"/>
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
			                
			                	<div class="labeled" style="display: none;" id="leyDate"> ¿A partir de qué fecha quedaste contratado?</div>
			                	 <div class="grid1_3 fl" style="width: 600px;display: none;" id="fechas">
			               		<select id="diaSelect" name="diaSelect" 
									required="false" missingMessage="El Día en el campo ¿A partir de qué fecha quedaste contratado? es requerido."
									invalidMessage="Día de fecha de contrato inválido, no se permiten caracteres especiales." 
									maxHeight="250" 
									regExp="${regexpdia}"  
						        	value="${registroPostulacionForm.diaContrato}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="diasContratoStore">
						    </select>

			                </select>
							<select id="mesSelect" name="mesSelect"
								    required="false" missingMessage="El Mes en el campo ¿A partir de qué fecha quedaste contratado? es requerido."
								    invalidMessage="Mes de fecha de contrato inválido, no se permiten caracteres especiales."
								    maxHeight="250" 
						        	value="${registroPostulacionForm.mesContrato}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="mesesContratoStore">
						    </select>

							<select id="anioSelect" name="anioSelect" 
									required="false" missingMessage="El Año en el campo ¿A partir de qué fecha quedaste contratado? es requerido"
									invalidMessage="Año de fecha de contrato inválido, no se permiten caracteres especiales."
									maxHeight="250"
						        	value="${registroPostulacionForm.anioContrato}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="aniosContratoStore">
						    </select>
			            </div>
			               
			                 <div class="clearfix"></div>
			    </div>
			    <div class="margin_top_20" >
			    	<div class="grid1_3 fl" style="width: 400px;display: none;" id="motivos" >
				            <label>¿Cuál fue el motivo por el que no te contrataron?</label>
				           <select id="motivoSelect" name="motivoSelect" 
									required="false" missingMessage="El campo ¿Cuál fue el motivo por el que no te contrataron? es requerido." 
									invalidMessage="Datos inválidos en ¿Cuál fue el motivo por el que no te contrataron?,no se permiten caracteres especiales." 
									maxHeight="250"
						        	value="${registroPostulacionForm.idMotivoNoContratacion}" autocomplete="true"
						        	dojotype="dijit.form.FilteringSelect" store="motivoContratoStore" onchange="javascript:muestraOtroMotivo()">
						    </select>
						   
				        </div>
				       <div class="grid1_3 fl" id="otros" style="display: none;">
				       <label>Especifica el motivo</label> 
			
				       <input type="text" name="otroMotivo" id="otroMotivo"
				               required="false" missingMessage="El campo Especifica el motivo es requerido."
				               regExp="${regexpnombrecontacto}"
				               invalidMessage="Datos inválidos en Especifica el Motivo, no se permiten caracteres especiales."
				               value="${registroPostulacionForm.otroMotivo}" 
				        	   maxlength="150" dojoType="dijit.form.ValidationTextBox"
				        	   />	    
				       </div>
				        <div class="clearfix"></div>
			    </div>
			    </fieldset>
			    <div  id="leyendaPPC" style="font-weight: bold; color: olive;"></div>
			    <div class="form_nav" id="div_form_nav">
						<input id="btnGuardar" name="btnGuardar" type="button" value="Guardar" 		onclick="guarda();"/>
						<input id="btnCancel"  name="btnCancel"  type="button" value="Cancelar" 	onclick="cancelar();"/>
				</div>	
	    
</div>

<div dojoType="dijit.Dialog" id="msjExito" title="Aviso" draggable ="false" class="suelto">
	<div class="msg_contain">
		<p>Te agradecemos el tiempo dedicado para notificarnos la respuesta a tu proceso de selección.</p>
		<br/>
		
		<p class="form_nav 2" >		
			<br/>
			<button class="button" onclick="javascript:cerrarMsjExito();">Aceptar</button>
		</p>
	</div>
</div>


<div dojoType="dijit.Dialog" id="msjNo" title="Aviso" draggable ="false" class="suelto">
	<div class="msg_contain">
		<p>Para evitar la inactividad de tu registro en el Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD), debes continuar con la búsqueda de ofertas y postularte a las de tu interés</p>
		<br/>
		
		<p class="form_nav 2" >		
			<br/>
			<button class="button" onclick="javascript:cerrarMsjExito();">Aceptar</button>
		</p>
	</div>
</div>

<div dojoType="dijit.Dialog" id="msjProceso" title="Aviso" draggable ="false" class="suelto">
	<div class="msg_contain">
		<p>Para continuar en el Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD), debes registrar el resultado de tu entrevista.</p>
		<br/>
		
		<p class="form_nav 2" >		
			<br/>
			<button class="button" onclick="javascript:cerrarMsjExito();">Aceptar</button>
		</p>
	</div>
</div>
<div dojoType="dijit.Dialog" id="msjFecha" title="Fecha inválida" draggable ="false" class="suelto">
			<div class="msg_contain">
				<br/>
				<p>	
				La fecha de contratación debe ser igual o mayor a la fecha de postulación.
				</p>
				<br/><br/>				
				<p class="form_nav 2" >
					<button id="btnAceptar" class="button" onclick="javascript:hideMsj();">Aceptar</button>										
						
				</p>
			</div>
		</div>
		
<div dojoType="dijit.Dialog" id="msgErrores" title="Error" draggable="false" style="display:none">
			<div class="msg_contain">
				<p>${ERROR_MSG}</p>
			</div>
			<p class="form_nav">	
				<button class="button" onclick="javascript:hideMsj();">Aceptar</button>
			</p>
		</div>

		
<div dojoType="dijit.Dialog" id="MensajeAlerConfirt" title="Aviso" draggable="false" style="display:none">
			<div class="msg_contain">
				<p>Los datos no guardados se perderán ¿Continuar?</p>
			</div>
			<p class="form_nav">	
				<button class="button" onclick="javascript:closeDialog();">Aceptar</button>
				<button class="button" onclick="javascript:cierraMsjCancelar();">Cancelar</button>
			</p>
			
		</div>

</form>












