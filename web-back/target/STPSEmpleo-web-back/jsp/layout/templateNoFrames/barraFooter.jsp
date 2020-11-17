<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
String context = (String)application.getAttribute("DOMAIN_PORTAL");
String contextApp=request.getContextPath();

if (null != request.getSession().getAttribute("FROM_CIL"))
	context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");

%>

<style type="text/css">
	.oculto {
	   position: absolute !important;
	   top: -9999px !important;
	   left: -9999px !important;
	}
</style>
<div id="bar">
<div id="barHome">
<div id="barCenter" style="display: block;">			    			
<form id="loginbarForm" name="loginbarForm" action="<%=contextApp %>/loginhome.do?method=login" method="post" autocomplete="off">

<input type="hidden" id="isHome" name="isHome" value="home" />
<input type="hidden" id="login_page" name="login_page" value="cand"/>

<p class="barLogin">
<label class="oculto" for="username">Usuario candidato</label>
<input type="text" onfocus="clean(this, 6)" name="username" id="login" value="Usuario candidato" style="font-size: 12px;">
</p>

<p class="barLogin">
<label class="oculto" for="password">Contraseña</label>
<input type="password" onkeypress="keybarSubmit(event)" onfocus="clean(this, 7)" value="Contraseña" name="password" id="pass">
</p>

<p><a href="#" class="barSesion" onclick="javascript:document.loginbarForm.submit();">Iniciar sesión</a>
</p>

</form>
		<div id="captchaFrm" class="oculto">
			<jsp:include page="/jsp/seguridad/modalCaptchaGoogle.jsp" />
		</div>
<div class="bannerCanal_princ">
            <ul>
            <li><a class="textAumentar" href="#">Aumentar tamaño de texto</a></li>
            <li><a href="#" class="textNormal">Reestablecer tamaño de texto</a></li>
            <li><a href="#"  class="textReducir">Disminuir tamaño de texto</a></li>
            <li><a class="iconPrint" href="javascript:print()">Imprimir</a></li>
             <li><a class="iconRecomend" onclick="openRecommendationModal('recommendi1_55','#000000', 80);void(0);" href="#">Recomendar</a></li>  
            </ul>
</div>
   		<p><a href="<%=context %>/swb/empleo/Necesitas_ayuda" class="barFaq">¿Necesitas ayuda?</a>
        </p>
<form action="<%=context %>/swb/empleo/buscador" method="get" id="formaq" name="formaq">
    <p class="barLogin">
   <label class="oculto" for="password">¿Qué buscas?</label>
   <input type="text" onfocus="this.value = '';" id="buscar" value="¿Qué buscas?" name="q">
</p>
   <p class="icoSearch">
<input type="submit" id="bt_buscar" value="" name="q2">
   </p>
</form>
    </div>

</div></div>