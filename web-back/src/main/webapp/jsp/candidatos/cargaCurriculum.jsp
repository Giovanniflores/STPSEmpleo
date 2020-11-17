<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>	

<% 
	String context = request.getContextPath() +"/";
	String vProxy = context + "SpellCheck.do?method=gogiespell&lang="; 
%>

	<script type="text/javascript" src="googiespell/AJS.js"></script>
	<script type="text/javascript" src="googiespell/googiespell.js"></script>
	<script type="text/javascript" src="googiespell/cookiesupport.js"></script>
	
	<link href="googiespell/googiespell.css" rel="stylesheet" type="text/css"/>

	<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>

    <script type="text/javascript">
    
      dojo.require("dijit.dijit"); // loads the optimized dijit layer
      dojo.require("dijit.form.Form");
      dojo.require("dijit.form.ValidationTextBox");
      dojo.require("dijit.form.SimpleTextarea");
      dojo.require("dijit.form.CheckBox");
      dojo.require("dijit.form.Button");
    
      function valida(forma) {
    	  var formaDijit = dijit.byId(forma.id);
    	  if (formaDijit.isValid()) {
    		  if (dijit.byId("termsConditionsAccepted").get("checked") != false) {
    			  forma.submit();
    		  } else {
    			  alert("Debe aceptar los Términos y Condiciones.");
    			  return false;
    		  }
    	  } 
//     	  else {
//     		  alert("Debe capturar los datos obligatorios (marcados con *)");
//     		  return false;
//     	  }
      }
      
      function preview(forma) {     
      
    	  if (dijit.byId('videoURL').isValid()) {
    		  var x = window.open(forma.videoURL.value, "nueva", "width=800,height=600,resize=yes,scrollbars=true");
    	  } else {
    		  alert("Debe capturar la ruta de la página en que aparece su vídeo currículo");
    	  }
    	  return false;
      }
      
      function cancela(forma) {
          forma.method.value = "cancelar";
          forma.submit();
      }
      
      function caracteresValidos(e){
    	var tecla_codigo = (document.all) ? e.keyCode : e.which;
    	if(tecla_codigo==8 || tecla_codigo==0) return true;
    	if (tecla_codigo==225 || tecla_codigo==233 || tecla_codigo==237 || tecla_codigo==243 || tecla_codigo==250) return true; //vocales minusculas con acento
    	if (tecla_codigo==193 || tecla_codigo==201 || tecla_codigo==205 || tecla_codigo==211 || tecla_codigo==218) return true; //vocales mayuzculas con acento
    	if (tecla_codigo==209 || tecla_codigo==241 ) return true; //Ñ y ñ
   		
    	var patron =/[0-9\-a-zA-Z_ .,:;#]/;
    	tecla_valor = String.fromCharCode(tecla_codigo);
    	return patron.test(tecla_valor);

    }
    </script>

	<c:if test="${not empty msg_registro}">
	<script type="text/javascript">
		alert('${msg_registro}');
	</script>
	</c:if>
    
	<html:messages id="errors">
	    <span class="redFont Font80"><bean:write name="errors"/></span><br/>
	</html:messages>
	
	<html:messages id="messages" message="true">
	    <span class="Font80"><bean:write name="messages"/></span><br/>
	</html:messages>

<form name="videocurriculumForm" id="videocurriculumForm" method="post" action="cargaCurriculum.do" dojoType="dijit.form.Form">
    <input type="hidden" name="method" id="method" value="registrar"/>



<div class="formApp miEspacio">
		<h2>Mi espacio</h2>
		<div class="tab_block">
			<div align="left" style="display:block;" id="returnME">
				<a class="expand" href="javascript:location.replace('<c:url value="/miEspacioCandidato.do"/>');">
				<strong>Ir al inicio de Mi espacio</strong></a>
			</div>
			<div class="Tab_espacio">
				<h3>Crear mi currículum</h3>
					<div class="subTab">
						<ul>
							<%--li><a href="<c:url value="/eligeEstiloCurriculum.do?method=init"/>">Elegir estilo de CV</a></li --%>
							<li><a href="/miscandidatos.do?method=genera&idCandidato=${sessionScope.idCanEstiloCv != null ? sessionScope.idCanEstiloCv : 0}">Ver currículum</a></li>
<%-- 							<li><a href="<c:url value="/miscandidatos.do?method=genera&idCandidato=${requestScope.idCandidato}"/>">Ver currículum</a></li> --%>
							<li class="curSubTab"><span>Subir video-currículum</span></li>
						</ul>
		                <div class="clearfix"></div>
		            </div>
			</div>	      
		</div>
			<fieldset class="videoCurriculum">
				<legend>Vídeo currículum</legend>
				    <div class="division">
					    <div class="grid1_3 margin_top_20">
					      <label for="videoURL"><span>*</span> Insertar URL</label>
					      <input type="text" id="videoURL" name="videoURL" value="${curriculumCargaForm.videoURL}" 
					             dojoType="dijit.form.ValidationTextBox" required="true"  trim="true"
					             size="80" maxlength="255"
					             promptMessage="Capture la ruta completa de la p&aacute;gina en que aparece su v&iacute;deo"
					             invalidMessage="El dato capturado no es v&aacute;lido, la ruta debe capturarse desde - http://"
					             regExp="^(http|https)\://[a-zA-Z0-9\-\.]+\.[a-zA-Z]{2,3}(:[0-9]{3,4})?(/\S*)?$"
					             missingMessage="Falta por capturar la direcci&oacute;n del v&iacute;deo">
					    </div>
					    <div class="campoTexto margin_top_30">
					      <label for="videoDescription">Descripci&oacute;n del v&iacute;deo:</label>
					      
					       <textarea name="videoDescription" id="videoDescription" 
					        	onkeypress="return caracteresValidos(event);"
					        	maxlength="2000"
					        	required="false">${curriculumCargaForm.videoDescription}</textarea>
					        	
					      <script type="text/javascript">
					         		var vSpell = new GoogieSpell("googiespell/", '<%=vProxy%>'); 
				   					vSpell.setLanguages({'es': 'Español'});
				   					vSpell.hideLangWindow();
				  					vSpell.decorateTextarea("videoDescription");
							</script>		
								
					    </div>
					    <!-- El despliegue de los terminos y condiciones se realiza en otra ventana de navegador -->
				
					    <div class="entero">    
						    <span class="un_tercio">
						      <label for="termsConditionsAccepted"><span>*</span> Aceptaci&oacute;n de t&eacute;rminos y condiciones</label>
						      <input type="checkbox" id="termsConditionsAccepted" name="termsConditionsAccepted" value="true"
						             dojoType="dijit.form.CheckBox">
						    </span>	              
				            <span class="un_cuarto">
				                    <a href="/<%=Constantes.TERMINOS_CONDICIONES_CARGA_VIDEO_CV%>" target="_new">Términos y condiciones</a>
							 </span>  
				         </div>

				    </div>

			</fieldset>

			<div class="form_nav">
				<input type="button" id="btnEnviar" value="Guardar" class="boton" onclick="javascript:valida(this.form);"/>
				<input type="button" id="btnVistaPrevia" value="Vista previa" class="boton" onclick="javascript:preview(this.form);"/>  
				<input type="button" id="btnCancelar" value="${labelBoton}" class="boton" onclick="javascript:window.location.href = '<c:url value="/miEspacioCandidato.do"/>';"/> 
			</div>
</div>

</form>