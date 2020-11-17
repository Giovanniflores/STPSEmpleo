<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<script type="text/javascript">
function doSubmit(){
	if (!document.loginForm.username.value && !document.loginForm.password.value){
		message('Datos inv�lidos, favor de verificar.');
	}else if (!document.loginForm.username.value){
		message('Favor de capturar el usuario.');
	}else if (!document.loginForm.password.value){
		message('Favor de indicar la contrase�a.');
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

function configForm(){
	var frm = document.getElementById('loginForm');
	
	if(frm){
		frm.autocomplete='off';
	}
}

</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<form id="loginForm" name="loginForm" action="${context}login.do?method=login" method="post">
<input type="hidden" id="login_page" name="login_page" value="cand" />
<div id="overPanel">
	<div id="cerrar" class="seiscientospx"></div>
	<div id="cuerpo_pop" class="busquedaCandidato">
		<!-- <h3><span>Entra al sitio</span><br />para ver m�s informaci�n</h3>  -->

		<div class="introLog" >
		<p style="text-align: justify;">El <span style="color:#F60;">Portal del Empleo</span> te ofrece la oportunidad de contar con un espacio personalizado para administrar tu informaci�n, recibir ofertas laborales acordes con tu perfil y disfrutar de beneficios exclusivos para usuarios registrados.</p><br/>
		<p style="text-align: justify;">Tu registro es gratuito, te permite postularte a las ofertas laborales de tu inter�s y utilizar herramientas de apoyo a tu b�squeda de empleo.</p><br/>
		<p style="text-align: justify;">Ingresa a tu espacio y con�ctate con las mejores empresas para trabajar.</p><br/>
		</div>

        <div class="login_vignett">
        	<div class="campo_pop derecha2">
				<p class="campo_2_3"><label for="username">Correo electr�nico</label><br/>
                	<input type="text" size="40" id="username" name="username"/>
                </p>
                <p class="campo_2_3"><label for="password">Contrase�a</label><br/>
          			<input type="password" size="40" id="password" name="password" onkeypress="keySubmit(event)" />
                </p>
            </div>

				<p class="btnEnvia" >
					<input type="button" id="btnEntrar" value="Entrar" class="boton" onclick="doSubmit();" />
				</p>	

			<div> 			
				<span>
				<br>
				&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
					<a href="${context}recuperarpsw.do?method=init&tipo=Candidato">Olvid� mi contrase�a</a>
				</span>

			</div>

        </div>

		<div class="registro_call">
        	<h3>�Haz que las empresas <br /> te encuentren!</h3>
        	<ul>
             <li>Sube tu curr�culum</li>
             <li>Recibe ofertas laborales en tu correo</li>
             <li>Post�late por las ofertas de tu Inter�s</li>
            </ul>
            <p style="text-align: left">
            	<a href="${context}registro.do?method=redirectRegistro">Reg�strate</a>
            </p>
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
