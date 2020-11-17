<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<script type="text/javascript">
function doSubmit(){
	if (!document.loginForm.username.value && !document.loginForm.password.value){
		message('Datos inválidos, favor de verificar.');
	}else if (!document.loginForm.username.value){
		message('Favor de capturar el usuario.');
	}else if (!document.loginForm.password.value){
		message('Favor de indicar la contraseña.');
	}else{
		document.loginForm.submit();
	}
}

function doSubmitRegistro(){
	document.loginForm.action='comregcompany.do';
	document.loginForm.submit();
}

function message(msg){
	alert(msg);
}

function keySubmit(e) {
    if (e.keyCode == 13) {
    	doSubmit();
    }
}

function configForm(){
	var frm = document.getElementById('loginForm');
	
	if(frm){
		frm.autocomplete='off';
	}
}

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<form id="loginForm" name="loginForm" action="${context}login.do?method=login" method="post">
	<input type="hidden" id="login_page" name="login_page" value="emp" />
	<input type="hidden" id="method" name="method" value="registrar" />

<div id="overPanel">
	<div id="cerrar" class="seiscientospx"></div>
	<div id="cuerpo_pop" class="loginEmpresas">
		<!-- <h3><span>Entra al sitio</span></h3> -->
		<div class="introLog" >				
			<p style="text-align: justify;">El <span style="color:#F60;">Portal del Empleo</span> le ofrece la oportunidad de contar con un espacio personalizado para publicar y administrar sus ofertas laborales, buscar y seleccionar candidatos, y acceder a nuevas funcionalidades para empresas registradas.</p><br/> 
			<p style="text-align: justify;">Registrar su empresa es totalmente gratis y le permitirá utilizar herramientas que apoyen sus procesos de reclutamiento y selección de personal.</p><br/>
			<p style="text-align: justify;">Ingrese a su espacio y encuentre a los mejores candidatos para sus ofertas laborales.</p><br/>		
		</div>
		
		<p class="login"><span>¿Ya está registrado?</span><span>¿Aún no está registrado?</span></p>
		
		<div>
        	<div class="campo_pop derecha2">
				<p class="campo_2_3"><label>Correo electrónico</label><br/>
					<input type="text" size="40" id="username" name="username" />
                </p>
                <p class="campo_2_3"><label>Contrase&ntilde;a</label><br/>
          			<input type="password" size="40" id="password" name="password" onkeypress="keySubmit(event)" />
				</p>
			</div>
			<p class="btnEnvia">
				<input type="button" id="btnEntrar" value="Entrar" class="boton" onclick="doSubmit();" />
			</p>
		</div>
	
		<div class="logoLightBox"> <span><img src="images/busqueda_candidatos_div.jpg" /></span>
   			<ul>
   				<li>Ingrese los datos de su empresa</li><br/>
   				<li>Registre sus ofertas de empleo</li><br/>
   				<li>Cree su carpeta empresarial</li><br/>
   				<li>Busque y contacte a los candidatos adecuados para cubrir sus ofertas de empleo</li>
   			</ul>
   			<p class="btnEnvia">
   				<input type="button" onclick="javascript:doSubmitRegistro()" value="Regístrese aquí" class="boton">
   			</p>
            <p class="registro"><span><a href="${context}recuperarpsw.do?method=init&tipo=Empresa">Olvidé mi contraseña</a></span><span>¡Es un servicio gratuito!</span></p>
            <img class="logo" src="images/logo_bottomLightBox.gif" alt="Portal del Empleo" />
			<p class="divLine"><span></span></p>
        </div>
	</div>
	<div class="bottom_lightBox2"></div>
</div>
</form>

<script>
	document.loginForm.username.focus();
	configForm();
</script>

<c:if test="${not empty errmsg}">
	<script>
		message('${errmsg}');
		<%session.removeAttribute("errmsg");%>
	</script>
</c:if>