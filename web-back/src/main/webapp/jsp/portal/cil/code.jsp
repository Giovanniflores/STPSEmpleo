<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String context = request.getContextPath() +"/";%>
<script type="text/javascript">
	function doSubmit() {
		if (!document.loginForm.code.value){
			message('Favor de indicar el código de acceso.');
		}else{
			document.loginForm.submit();
		}
	}
	
	function message(msg){
		alert(msg);
	}
	
	function keySubmit(e) {
	    if (e.keyCode == 13) {
	    	doSubmit();
	    }
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<form id="loginForm" name="loginForm" action="${context}logincil.do?method=access" method="post" autocomplete="off">
	<div id="overPanel">
		<div id="cerrar" class="seiscientospx"></div>
		<div id="cuerpo_pop" class="busquedaCandidato">
			<div class="introLog" >
				<p style="text-align: justify;">El <span style="color:#F60;">Portal del Empleo</span> te ofrece la oportunidad de contar con un espacio personalizado para administrar tu información, recibir ofertas laborales acordes con tu perfil y disfrutar de beneficios exclusivos para usuarios registrados.</p><br/>
				<p style="text-align: justify;">Tu registro es gratuito, te permite postularte a las ofertas laborales de tu interés y utilizar herramientas de apoyo a tu búsqueda de empleo.</p><br/>
				<p style="text-align: justify;">Ingresa a tu espacio y conéctate con las mejores empresas para trabajar.</p><br/>
			</div>
	        <div>
	        	<div class="campo_pop derecha2">
	                <p class="campo_2_3">Código de acceso<br/>
	          			<input type="text" size="40" id="code" name="code" onkeypress="keySubmit(event)" />
	                </p>
	            </div>
				<p class="btnEnvia">
					<input type="button" id="btnEntrar" value="Ingresar" class="boton" onclick="doSubmit();" />
				</p>	
				<div> 			
					<span>
					<br>
					&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						<a href="${context}recuperarpsw.do?method=init&tipo=Candidato">Olvidé mi contraseña</a>
					</span>
	
				</div>
	
	        </div>
	        <div class="logoLightBox"> <!-- <span><img src="images/busqueda_candidatos_div.jpg" /></span>  -->
	        	<p class="user">
	        		<span><a href="${context}logincil.do?method=init">Regresar</a></span>
	            	<img src="images/logo_bottomLightBox.gif" alt="Portal del Empleo" /></p>
			</div>
	    </div>
	    <div class="bottom_lightBox2"></div>
	</div>
</form>

<script>
	document.loginForm.code.focus();	
</script>

<c:if test="${not empty errmsg}">
	<script>
		message('${errmsg}');
		<%session.removeAttribute("errmsg");%>
	</script>
</c:if>