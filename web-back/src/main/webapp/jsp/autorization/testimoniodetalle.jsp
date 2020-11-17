<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="${pageContext.request.requestURL}" />
<%String context = request.getContextPath() +"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
 <title>Detalle Testimonio | Portal del Empleo</title> 
<meta property="og:title" content="Portal del Empleo: Detalle Testimonio">
<meta name="twitter:title" content="Portal del Empleo: Detalle Testimonio">
<meta property="og:description" content="Portal del Empleo: ${autorizationForm.testimonio.descripcion}">
<meta name="twitter:description" content="Portal del Empleo: ${autorizationForm.testimonio.descripcion}">
<meta name="description" content="Portal del Empleo: ${autorizationForm.testimonio.descripcion}">
<meta property="og:url" content="${url}">
<meta property="og:site_name" content="Portal del Empleo">
<meta property="og:image" content="${context}css/images/contenido-compartir.jpg">
<meta property="og:image:type" content="image/jpeg">
<meta property="og:image:width" content="1200">
<meta property="og:image:height" content="627">
<meta property="fb:app_id" content="308925772806125" />
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@empleogob_mx">
<meta name="twitter:creator" content="@InfotecMexico">
<meta name="twitter:image:src:src" content="https://qa.empleo.gob.mx/css/images/compartir-contenido-tweetA.jpg">
<meta name="author" content="infotec">

<link href="${PATH_CSS_SWB_APP}estilos_lightBox.css" rel="stylesheet" type="text/css" />


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
		  dojo.byId('autorizationForm').submit();	  
	}
	
	function showWindowMotivoRechazo(idRegValidar){

		try{
			if (dialogRechazo){
				  dijit.byId('idMotivoRechazoSelect').attr('value', null);
				  dojo.style(dialogRechazo.closeButtonNode,"display","none");
				  dialogRechazo._relativePosition = { x: 338, y:150 };				  
				  dialogRechazo.show();
			}
		} catch(err){
			  dialogRechazo = new dijit.Dialog({
			        title: 'Motivo de Rechazo',
			        href: '${context}autorization.do?method=motivoRechazoDetalle',
			        style: "width:280px; height:330px;",
			        showTitle: false, draggable : true, closable : false
			  });
			  
			  dojo.style(dialogRechazo.closeButtonNode,"display","none");
			  dialogRechazo._relativePosition = { x: 338, y:150 };			  
			  dialogRechazo.show();
		}
	
	}

	function closeWindowRechazo(){
		  dialogRechazo.hide();
	}
	
	function autorizarRegistro(idRegValidar){
		  dojo.byId('idRegValidar').value=idRegValidar;	  
		  dojo.byId('method').value='autorizarRegistro';
		  dojo.byId('autorizationForm').submit();	  
	}	

	  function rechazarRegistro(idRegValidar){


		if(!dijit.byId('idMotivoRechazoSelect').isValid()){
			dijit.byId('idMotivoRechazoSelect').focus();
			alert("El siguiente campo muestra datos inv�lidos: Motivo. ");								
			return false;
		}			
		  
		if (dijit.byId('idMotivoRechazoSelect').get('item') && dijit.byId('idMotivoRechazoSelect').get('item').label){
			dojo.byId('idMotivoRechazo').value = dijit.byId('idMotivoRechazoSelect').get('item').label[0];
		}

		if(dijit.byId('motivoRechazoText').value.length==0){		
			alert("El siguiente campo muestra datos inv�lidos: Detalle. ");								
			return false;
		}		

		document.autorizationForm.idMotivoRechazo.value = dojo.byId('idMotivoRechazo').value;
		document.autorizationForm.motivoRechazo.value   = dojo.byId('motivoRechazoText').value;
		
		closeWindowRechazo();

        dojo.byId('idRegValidar').value=idRegValidar;	  
		dojo.byId('method').value='rechazarRegistro';
		dojo.byId('autorizationForm').submit();			
	  }

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

</head>
<body>

<div id="overPanel">
	<div id="cuerpo_pop" class="compartirSolicitante">
    	<h3><span>&nbsp;&nbsp;&nbsp;&nbsp;Testimonio</span></h3>
		<div>
                             
        	<div class="campo_pop derecha2">
            	<p>
                	<br/>
                    <textarea name="comentario" id="comentario" cols="45" rows="3" readonly="readonly" style="text-align:left">${autorizationForm.testimonio.descripcion}</textarea>
                </p>
            </div>
			<br/>
            <br/>
            <p style="text-align:center">
            	<input type="button" value="Autorizar" class="boton" onclick="javascript:autorizarRegistro(${autorizationForm.idRegValidar});"/>
            	<input type="button" value="Eliminar" class="boton" onclick="javascript:showWindowMotivoRechazo(${autorizationForm.idRegValidar});"/>
            	<input type="button" value="Cancelar" class="boton" onclick="javascript:cancelarValidacion(${autorizationForm.idRegValidar});"/>
            </p>
            <br/>
            <br/>
        </div>
        <div class="logoLightBox">
        	<img src="images/logo_bottomLightBox.gif" alt="Portal del Empleo" />
        </div>
	</div>
</div>
          
<form name="autorizationForm" id="autorizationForm" method="post" action="autorization.do">
	<input type="hidden" name="method" id="method" value="init"/>
	<input type="hidden" name="idRegValidar" id="idRegValidar" value="0"/>
	<input type="hidden" name="idEmpresa" id="idEmpresa" value="0"/>
	<input type="hidden" name="idOfertaEmpleo" id="idOfertaEmpleo" value="0"/>
	<input type="hidden" name="idMotivoRechazo" id="idMotivoRechazo" value="0"/>
	<input type="hidden" name="motivoRechazo" id="motivoRechazo" value=""/>	
</form>
          
</body>
</html>
