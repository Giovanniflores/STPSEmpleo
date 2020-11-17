<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">&iquest;Necesitas ayuda?</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
    <jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Este apartado constituye un mecanismo de comunicación para asesoría en línea sobre la navegación (operación) del Portal  del Empleo.
	</jsp:attribute>
    <jsp:attribute name="js">
    	<script type="text/javascript" src="../../../js/helper/messageHelper.js"></script>
    	<script type="text/javascript">
    		function valida() {
			     var patt1 = new RegExp("^[a-z0-9,!#\$%&'\*\+/=\?\^_`\{\|}~-]+(\.[a-z0-9,!#\$%&'\*\+/=\?\^_`\{\|}~-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*\.([a-z]{2,})$", "i");
			     if (document.ayuda.firstName.value.match(/^$|^\s+$/) || document.ayuda.firstName.value == "") {
			         message("Por favor, capture su nombre.");
			         document.getElementById("firstName").focus();
			         return false;
			     }
			     if (document.ayuda.lastName.value.match(/^$|^\s+$/) || document.ayuda.lastName.value == "") {
			         message("Por favor, capture su apellido paterno.");
			         document.getElementById("lastName").focus();
			         return false;
			     }
			     if (checkFieldChars(document.ayuda.firstName.value)) {
			         message("El nombre no puede contener carácteres especiales");
			         document.getElementById("firstName").focus();
			         return false;
			     }
			     if (checkFieldChars(document.ayuda.lastName.value)) {
			         message("El apellido paterno no puede contener los carácteres especiales");
			         document.getElementById("lastName").focus();
			         return false;
			     }
			     if (checkFieldChars(document.ayuda.materno.value)) {
			         message("El apellido materno no puede contener los carácteres especiales");
			         document.getElementById("materno").focus();
			         return false;
			     }
			     if (document.ayuda.tipoUsuario.selectedIndex < 1) {
			         message("Por favor, seleccione una opción para la pregunta ¿Usted Es?");
			         document.getElementById("tipoUsuario").focus();
			         return false;
			     }
			     if (document.ayuda.canal.selectedIndex < 1) {
			         message("Por favor, seleccione un canal");
			         document.getElementById("canal").focus();
			         return false;
			     }
			     if (checkFieldChars(document.ayuda.asunto.value)) {
			         message("El asunto no puede contener los carácteres especiales");
			         document.getElementById("asunto").focus();
			         return false;
			     }
			     if (document.ayuda.asunto.value == "") {
			         message("Por favor escriba su mensaje.");
			         document.ayuda.asunto.focus();
			         return;
			    }
			    if (document.ayuda.asunto.value.length > 100) {
			        message("Escriba no más de 100 carácteres.");
			        document.ayuda.asunto.focus();
			        return;
			    }
			    if (document.ayuda.conCopia[0].checked) {
			    	if (document.ayuda.email.value == "") {
			    		message("Por favor escriba su cuenta de correo.");
			        	document.ayuda.email.focus();
			        	return false;
			    	}else if (!patt1.test(document.ayuda.email.value)) {
			    		message("Por favor revise el formato de la cuenta de correo.");
			         	document.ayuda.email.focus();
			         	return false;
			    	}
			    }
			    if (document.ayuda.asunto.value.length > 100) {
			        document.ayuda.asunto.value = document.ayuda.asunto.value.substr(0, 99);
			    }
			    var nombre = document.ayuda.firstName.value;
			    var apellidos = document.ayuda.lastName.value;
			    if (document.ayuda.materno.value.length > 0) {
					apellidos + '%20' + document.ayuda.materno.value;
			    }
			    var mail = document.ayuda.email.value;
			    var tipoUsuario = document.ayuda.tipoUsuario.options[document.ayuda.tipoUsuario.selectedIndex].value;
			    var canal = document.ayuda.canal.options[document.ayuda.canal.selectedIndex].value;
			    var comentarios = 'Tipo%20de%20usuario:%20'+tipoUsuario+',%20Canal:'+canal+'%20'+document.ayuda.asunto.value;
				var url = 'http://pentafon.com/Chat/SNE/index.html?proyecto=sne&p=sne&b=LOGIND&%7Bnombre%7D='+nombre+'%20&%7Bapellidos%7D='+apellidos+'%20&%7Bmail%7D='+mail+'&comentarios='+comentarios+'&%7Bsendchat%7D=1';
			    //var url = 'http://189.202.196.50:3003/Chat/SNE/index.html?proyecto=sne&p=sne&b=LOGIND&%7Bnombre%7D='+nombre+'%20&%7Bapellidos%7D='+apellidos+'%20&%7Bmail%7D='+mail+'&comentarios='+comentarios+'&%7Bsendchat%7D=1';
			    popup(url);
			}
			function popup(url) {
				newwindow=window.open(url,'Chat con un agente','height=500,width=400');
				if (window.focus) {newwindow.focus()}
				return false;
			}
			function checkFieldChars(sFieldValue) {
				return (sFieldValue.indexOf("&") != -1  ||
					sFieldValue.indexOf("+") != -1  ||
			        sFieldValue.indexOf("%") != -1  ||
		            sFieldValue.indexOf("#") != -1  ||
	                sFieldValue.indexOf("/") != -1 ||
	                sFieldValue.indexOf("'") != -1)
			}
			function hideEmail() { document.getElementById('email').disabled = true; }
		  	function showEmail() { document.getElementById('email').disabled = false; }
		</script>
    </jsp:attribute>

	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li>
          	  <li>¿Necesitas ayuda?</li>
	        </ol>
	      </div>
	    </div>
	    <!-- /div ruta_navegacion -->
	    
	    <div class="row">
		  <!-- div menu -->
		  <jsp:include page="menu.jsp"/>
		  
		  <!-- div contenido -->
	      <div class="col-sm-8 col-sm-pull-4">
	      
	       <jsp:include page="/WEB-INF/template/redes.jsp"/>
	
	        <div class="panel panel-contacto">
	          <div class="panel-heading">
	            <h2 class="titulosh2">¿Necesitas ayuda?</h2>
	          </div>
	          <div class="panel-body">
	          
	          	<p>Este apartado constituye un mecanismo de  comunicación para asesoría en línea sobre la navegación (operación) del Portal  del Empleo. 
	          		La asesoría la recibirá directamente de un ejecutivo capacitado  para ayudarle a resolver sus problemas de operación y navegación del sitio. 
	          		El  horario de servicio es de las 8:00 a 20:00 hrs. de lunes a viernes si tiene  alguna duda en un horario distinto déjenos un mensaje a través 
	          		de la opción de contacto y nosotros le daremos  respuesta a sus dudas.
	          	</p>
	          	<br />
	          	<p>Es importante que tenga en cuenta que la asesoría que se le  brindará es sólo sobre la navegación del Portal del Empleo.</p>
	          	<br />
	          	<p>No se admitirá contenido Obsceno o que falten el respeto a las  personas, instituciones u organismos.</p>
	          	<br />
	          	<p>Por favor llene los campos siguientes lo cual nos permitirá tener  la oportunidad de atenderle mejor.</p>
	          	<br />
	          	<form name="ayuda" method="post" action="" >
	          	
		          	<div class="row">
		          	
			          	<div class="col-sm-4">
			          		<div class="form-group">
			          			<label>Nombre:</label>
			          			<input name="firstName" id="firstName" type="text" maxlength="30" class="form-control" />
			          		</div>
			          	</div>
			          	
			          	<div class="col-sm-4">
			          		<div class="form-group">
			          			<label>Apellido Paterno:</label>
			          			<input name="lastName" id="lastName" type="text" maxlength="35" class="form-control" />
			          		</div>
			          	</div>
			          	
			          	<div class="col-sm-4">
			          		<div class="form-group">
			          			<label>Apellido Materno:</label>
			          			<input name="materno" id="materno" type="text" maxlength="35" class="form-control" />
			          		</div>
			          	</div>
			          	
			          	<div class="col-sm-6">
			          		<div class="form-group">
			          			<label>¿Usted Es?</label>
			          			<select name="tipoUsuario" id="tipoUsuario" size="1" class="form-control">
					                <option value="0">SELECCIONE UNA OPCIÓN</option>
					                <option value="1">BUSCADOR DE EMPLEO</option>
					                <option value="2">EMPRESARIO</option>
					                <option value="3">ESTUDIANTE</option>
					                <option value="4">TRABAJADOR EN ACTIVO</option>
					                <option value="5">OTRO</option>
					              </select>
			          		</div>
			          	</div>
			          	
			          	<div class="col-sm-6">
			          		<div class="form-group">
			          			<label>Indique la sección sobre el que tiene Dudas:</label>
			          			<select name="canal" id="canal" size="1" class="form-control">
					                <option value="0">SELECCIONE UNA OPCIÓN</option>
					                <option value="1">Busco empleo</option>
					                <option value="2">Ofrezco empleo</option>
					                <option value="3">Opciones de capacitación</option>
					                <option value="4">¿Qué me conviene estudiar?</option>
					                <option value="5">Asesoría para el trabajo</option>
					                <option value="6">Estadísticas del mercado laboral</option>
					            </select>
			          		</div>
			          	</div>
			          	
			          	<div class="col-sm-12">
			          		<div class="form-group">
			          			<label>¿En que podemos ayudarle?</label>
			          			 <textarea name="asunto" id="asunto" maxlength="100" cols="40" rows="3" class="form-control"></textarea>
	              				 <p class="help-block">Cuenta con 100 caracteres como máximo para su pregunta inicial</p>
			          		</div>
			          	</div>
			          	
			          	<div class="col-sm-6">
			          		<div class="form-group">
			          			<label>¿Desea copia de la conversación?</label>
			          			<div class="row">
						          	<div class="col-sm-12">
						          		<div class="form-group">
						          			<label class="radio-inline">
											  <input type="radio" name="conCopia" id="conCopia" value="1" onclick="showEmail();" /> Si
											</label>
											<label class="radio-inline">
											  <input type="radio" name="conCopia" id="sinCopia" value="0" checked="checked" onclick="hideEmail()" /> No
											</label>
										</div>
									</div>
								</div>
			          		</div>
			          	</div>
			          	
			          	<div class="col-sm-6">
			          		<div class="form-group">
			          			<label>Correo Electrónico:</label>
			          			<input name="email" id="email" type="text" maxlength="50" class="form-control" disabled="disabled"/>
			          		</div>
			          	</div>
			          	
			          	<div class="col-sm-12">
			          		<div class="form-group">
					          	<input type="button" class="btnPE pull-right" onclick="javascript:valida();" value="INICIAR ASESORÍA EN LÍNEA" />
			          		</div>
			          	</div>
			          	
		          	</div>
		          	
		         </form>
	            

	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
