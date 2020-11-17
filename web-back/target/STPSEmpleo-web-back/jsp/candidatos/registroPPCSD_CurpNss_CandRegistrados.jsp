<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<c:set var="regexpcurp">[a-zA-Z]{4}\d{6}[hmHM][a-zA-Z]{2}[B-DF-HJ-NP-TV-Z|b-df-hj-np-tv-z]{3}[a-zA-Z0-9][0-9]</c:set>
<c:set var="regexpnss">[a-zA-Z0-9]{11}</c:set>

<script type="text/javascript">

	dojo.require("dijit.dijit");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.Tooltip");
	
	function doSubmit(method){

		dojo.byId('method').value=method;
		dojo.byId('registroPPCSD_CandidatoRegistrado').submit();
	}

	function imprimirTerminosCondicionesPPC(){
		
		dojo.xhrPost({url: 'miespacionav.do?method=terminosCondicionesPpcEnPdf',
			  load: function(data){
				  alert("callback imprimirTerminosCondicionesPPC");
				  
			  }
		});

	}
	
	function verificarCurpNss(){
		
		// Validamos que estén correctamente informados los campos
		
		if (dijit.byId('iNss').value == ''){
			displayErrorMsg(dijit.byId('iNss'), dijit.byId('iNss').get("missingMessage"));
			return;
		}
		
		if (!dijit.byId('iNss').isValid()){
			displayErrorMsg(dijit.byId('iNss'), dijit.byId('iNss').get("invalidMessage"));
			return;			
		}

		if (dijit.byId('iRespuestaCaptcha').value == ''){
			displayErrorMsg(dijit.byId('iRespuestaCaptcha'), dijit.byId('iRespuestaCaptcha').get("missingMessage"));
			return;
		}		

		if (!dijit.byId('iRespuestaCaptcha').isValid()){			
			displayErrorMsg(dijit.byId('iRespuestaCaptcha'), dijit.byId('iRespuestaCaptcha').get("invalidMessage"));
			return;
		}

		dojo.xhrPost({url: 'registroCandidatoPPCSD.do?method=checkRespuesta', form: 'registroPPCSD_CandidatoRegistrado',
			  load: function(data){				  
				  var res = dojo.fromJson(data);

				  if (res.result == "false"){
					  displayErrorMsg(dijit.byId('iRespuestaCaptcha'), "La respuesta no es correcta: int&eacute;ntelo de nuevo o cambie de pregunta");					  
					  return;				  
					  
				  } else{
					  
						dojo.xhrPost({url: 'registroCandidatoPPCSD.do?method=verificaCurpNss', form: 'registroPPCSD_CandidatoRegistrado',
							  load: function(data){
								  var res = dojo.fromJson(data);

								  if (res.result == 1){
									  // continua a terminos y condiciones							  
									  document.getElementById("idCandidato").value = res.idCandidato;
									  doSubmit("showTerminosCondiciones");
									  
								  } else if (res.result == 2) { 
									  showMsgInscritoPPC();
									  
								  } else if (res.result == 3) { 
									  showMsgFueraPPC();
								  
								  } else if (res.result == 4) {
									  showMsgImmsNoDisponible();				  
									  
								  } else if (res.result == 5) {
									  showMsgNoRegistradoImms();
									  
								  } else {
									  showMsgError();							  
								  }
								  
							  	}
							 });					  
					  
					  
				  } 
			  }
				
		 });		
		
	}

	function showMsgInscritoPPC(){
		dijit.byId("msgInscritoPPC").show();
	}

	function showMsgFueraPPC(){
		dijit.byId("msgFueraPPC").show();
	}
	
	function showMsgNoRegistradoImms(){
		dijit.byId("msgNoRegistradoImms").show();		
	}
	
	function showMsgImmsNoDisponible(){		
		
		var idTag = "msgImmsNoDisponible";
		
		if (dijit.byId(idTag) == null){
				
			var html = "<p>No se puede validar el NSS porque el servicio del IMMS no está disponible: por favor inténtelo de nuevo más tarde.</p>"+
					   "<p><button onclick=\"dijit.byId('"+idTag+"').hide();\">Cerrar</button></p>"
					   ;

			dialogMensaje = new dijit.Dialog({
								 title: 'Mensaje',
								 id: idTag,
								 content: html,
								 style: "width:350px;",
								 showTitle: true, draggable : true, closable : true			
			
							});

			dojo.style(dialogMensaje.closeButtonNode,"display","none");
		}
	
		dijit.byId(idTag).show();
	}
	
	function showMsgError(){		
		dijit.byId("msgImmsNoDisponible").show();
	}

	function cambiarPregunta(){		
		dojo.xhrPost({url: 'registroCandidatoPPCSD.do?method=cambiaPregunta',
			  load: function(data) {
				  document.getElementById('captchaPregunta').innerHTML = data;
			  }
				  		
			});
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
	
</script>

<form name="registroPPCSD_CandidatoRegistrado" id="registroPPCSD_CandidatoRegistrado" method="post" action="registroCandidatoPPCSD.do" dojoType="dijit.form.Form">

	<input type="hidden" name="method" id="method" value="init" dojoType="dijit.form.TextBox"/>

	<div class="formApp">
	
		<div class="flow_1">
			<h2>Inscripci&oacute;n al Programa de Promoci&oacute;n y Colocaci&oacute;n del Seguro de Desempleo (PPC-SD)</h2>
		</div>
	    
	    <div class="form_wrap">
			
			<div class="form_edge">
	    	
	    	<div class="stepApp">
	    	
		        <p>Los datos marcados con <span>*</span> son obligatorios</p>					
	
				<div class="user_dt form_nom">
					<h3>Datos de la Clave &Uacute;nica de Registro de Poblaci&oacute;n y N&uacute;mero de Seguridad Social</h3>
				</div>
		
			    <fieldset class="field_curp">
	
	                <legend><acronym title="Clave &uacute;nica de Registro de Poblaci&oacute;n">CURP</acronym></legend>		 				
					
					<p class="curp_active">
						<strong>
							<input id="iCurp" name="curp" value="${registroCandidatoPPCSDForm.curp}" style="border: none;" dojoType="dijit.form.ValidationTextBox"
								required="true" missingMessage="Debe de indicar la CURP." 
								regExp="${regexpcurp}" maxlength="18" uppercase="true" clearOnClose="true" trim="true" invalidMessage="CURP invalida, favor de verificar su captura."/>
						</strong>
					</p>
					
				</fieldset>
									
            	<fieldset class="nss_1">									
					<legend>NSS</legend>

                	<div class="grid1_3">
						<label><span>*</span> N&uacute;mero de Seguridad Social</label>
						
						<input type="text" id="iNss" name="nss" dojoType="dijit.form.ValidationTextBox" required="true"
							maxlength="11" regExp="${regexpnss}"
							invalidMessage="N&uacute;mero de seguridad social inv&aacute;lido, favor de verificar su captura."
							missingMessage="El n&uacute;mero de seguridad social es requerido." trim="true" />

                	</div>
				</fieldset>

				<input type="hidden" id="idCandidato" name="idCandidato" value="${registroCandidatoPPCSDForm.idCandidato}"/>
				
			    <fieldset class="field_pregunta">
					
					<legend>Responde la siguiente pregunta</legend>
					
			        <div class="ctrl_07 margin_top_30" id="divCaptcha">
						<label id="captchaPregunta" for="iRespuestaCaptcha">
							<span>*</span>${registroCandidatoPPCSDForm.pregunta}
						</label>
						
						<input type="text" id="iRespuestaCaptcha" name="respuestaUsuario" required="true" dojoType="dijit.form.ValidationTextBox" missingMessage="La respuesta es requerida."/>

						<div>
							<a href="javascript:cambiarPregunta()" ><img src="${PATH_CSS_SWB}css_registro_candidato/images/icono-cambiar_pregunta.png" width="20" height="20" />Cambiar pregunta</a>
						</div>						
					</div>
					
				</fieldset>
				
			</div>			
		</div>
    </div>	
	
	<div class="form_nav">
		<input id="btnEnviar" name="btnEnviar" class="boton_azul" type="button" value="Continuar" onclick="verificarCurpNss();"/>
		<input id="btnCancelar" name="btnCancelar" class="boton_azul" type="button" value="Cancelar" onclick="dijit.byId('msgCancelar').show();"/>

	</div>
	</div>
	
</form>

<div dojoType="dijit.Dialog" id="msgImmsNoDisponible" title="Aviso" draggable ="false" style="display: none;">
	<div class="msg_contain">
		<p>No se puede validar el NSS porque el servicio del IMMS no está disponible: por favor inténtelo de nuevo más tarde.</p>
		
		<p class="form_nav">
			<button onclick="dijit.byId('msgImmsNoDisponible').hide();\">Cerrar</button>
		</p>
	</div>
</div>

<div dojoType="dijit.Dialog" id="msgNoRegistradoImms" title="Aviso" draggable ="false" style="display: none;">
	<div class="msg_contain">
		<p>No se ha podido validar el NSS; por favor verifique si situaci&oacute;n en el IMMS.</p>
		
		<p class="form_nav">
			<button onclick="dijit.byId('msgNoRegistradoImms').hide();">Cerrar</button>
		</p>
	</div>
</div>

<div dojoType="dijit.Dialog" id="msgFueraPPC" title="Aviso" draggable ="false" style="display: none;">
	<div class="msg_contain">
		<p>
			Tenemos registrado que ya participaste en el programa del Programa de Promoci&oacuten y Colocaci&oacuten del Seguro de Desempleo (PPC-SD) por lo tanto no se puede llevar a cabo tu inscripc&oacuten. Consulta las <a href="#">pol&iacuteticas de inscripci&oacuten</a> del PPC-SD  o acude a alguna de nuestras <a href="${DOMAIN_PORTAL}/es/empleo/donde_puedes_encontrarnos">oficinas del SNE</a>.
		</p>
		  
		<p class="form_nav">
			<button onclick="dijit.byId('msgFueraPPC').hide();">Cerrar</button>
		</p>		
	</div>
</div>

<div dojoType="dijit.Dialog" id="msgInscritoPPC" title="Aviso" draggable ="false" style="display: none;">
	<div class="msg_contain">
		<p>Ya te encuentras inscrito al  Programa de Promoci&oacute;n y Colocaci&oacute;n del Seguro de Desempleo (PPC-SD), te recomendamos realizar la b&uacute;squeda de ofertas y postularte a ellas para que cumplas con el programa o si lo deseas imprime los t&eacute;rminos y condiciones del PPC.</p>

		<p class="form_nav">
			<button onclick="window.open('<c:url value="/ofertasPerfiles.do?method=init&tipoConsulta=1"/>', '_self');">Buscar ofertas</button>
			<button onclick="window.open('${pageContext.request.contextPath}/miespacionav.do?method=terminosCondicionesPpcEnPdf', '_self');">Imprimir</button>
			<button onclick="window.open('<c:url value="/miEspacioCandidato.do"/>', '_self');">Ir a mi espacio</button>
		</p>
	</div>
</div>

<div dojoType="dijit.Dialog" id="msgCancelar" title="Aviso" style="display:none" draggable="false">
	<div class="msg_contain">
		<p>Los cambios no se guardar&aacute;n.</p>		
	</div>
	<p class="form_nav">	
		<button class="button" onclick="window.open('<c:url value="/miEspacioCandidato.do"/>', '_self');">Aceptar</button>
	</p>
</div>
