<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String context = request.getContextPath() +"/";%>
<script type="text/javascript">
	dojo.require("dijit.dijit"); // loads the optimized dijit layer
	dojo.require("dijit.Dialog");
</script>
<script type="text/javascript" src="js/helper/messageHelper.js"></script>
<script type="text/javascript">
	function doSubmit() {
		if (!document.sesion.username.value && !document.sesion.password.value && !document.sesion.code.value){
			message('Datos no validos, favor de verificar.');
		}else if (!document.sesion.username.value){
			message('Favor de ingresar usuario.');
		}else if (!document.sesion.password.value){
			message('Favor de indicar la contraseña.');
		}else if (!document.sesion.code.value){
			message('Favor de indicar el código de acceso.');
		}else{
			document.sesion.submit();
		}
	}
	
	function doSubmitRegistro() {
		if (!document.registro.code.value){
			message('Favor de indicar el código de acceso.');
		}else {
			document.registro.submit();
		}
	}
	
	function keySubmit(e) {
	    if (e.keyCode == 13) {
	    	doSubmit();
	    }
	}
	
	function keySubmitRegistro(e) {
	    if (e.keyCode == 13) {
	    	doSubmitRegistro();
	    }
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<link href="${PATH_CSS_SWB_APP}sesion-cil.css" rel="stylesheet" type="text/css" />

<div id="contenidko" class="col-md-6 col-sm-push-3">
    <div id="bienvenido">
    <img style="margin: 0" src="images/bienvenido-cil.png" alt="Bienvenido a los centros de intermediación laboral" />
    
    <p  class="bullet">Introduce los siguientes datos para <strong>iniciar tú sesión.</strong></p>
    
    <form id="sesion" name="sesion" action="${context}login.do?method=login" method="post" autocomplete="off">
	<input type="hidden" id="login_page" name="login_page" value="cil" />
    
        <div class="form-group"><label>Correo electrónico</label><input class="txt-box form-control" type="email" id="username" name="username" size="40"/></div>
        
        <div class="form-group"><label>Contraseña</label><input class="txt-box form-control" type="password" id="password" name="password" size="40"/></div>
        
        <div class="form-group"><label>Código de acceso</label><input class="txt-box form-control" type="password" id="code" name="code"  size="40" onkeypress="keySubmit(event)"/></div>
        
        <p><input class="boton_naranja" type="button" value="Ingresar" onclick="doSubmit();"/></p>
        
        <p><a id="recuperar-password" href="${context}recupera_contrasena.do?method=init">¿Olvidaste tu usuario o contraseña?</a></p>
    
    </form>
    
    <p class="bullet">Si aún no estás registrado, ingresa el número de acceso para <strong>crear tú cuenta.</strong></p>
    
    <form id="registro" name="registro" action="${context}logincil.do?method=access" method="post" autocomplete="off">
    
    	<div class="form-group">
    	<label>Código de acceso</label>
    	<input class="txt-box form-control" type="text" size="40" id="code" name="code" onkeypress="keySubmitRegistro(event)"/>
    	</div>
    	<input class="boton_naranja" type="button" value="Registrar" onclick="doSubmitRegistro();"/>
    
    </form>
    
    <p class="bullet">Consulte las <a href="<c:url value="/jsp/empleo/herramientasDelSitio/politicasYCondicionesDeUso.jsp"/>">Políticas y condiciones de uso </a></p>
    <div class="row">&nbsp;</div><div class="row">&nbsp;</div>
    </div>
</div>

<script>
	document.sesion.username.focus();	
</script>

<c:if test="${not empty errmsg}">
	<script>
		message('${errmsg}');
		<%session.removeAttribute("errmsg");%>
	</script>
</c:if>