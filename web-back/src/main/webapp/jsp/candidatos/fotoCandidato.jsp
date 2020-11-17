<%@ page import="java.util.Calendar"%>
<%String context = request.getContextPath() +"/";%>
<div id="datos_usuario">
	<div id="identidad">
		  <img alt="foto del usuario" src="<%=context%>imageAction.do?method=getImagen&<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>" 
                      	   			width="90" height="90" >
		<h2>Jos&eacute; Manuel Arechiga Villase&ntilde;or</h2>
		<span id="profesion">Ingeniero en Sistemas</span>
    </div>
    <div id="status_wrapper">
    	<div id="status_perfil">
	    	<h3>Situaci&oacute;n actual</h3>
	        <input type="radio" name="radio" id="radio" value="radio" />
	        <label for="radio"></label>En busca de empleo<br />
	        <input type="radio" name="radio2" id="radio2" value="radio2" />
	        <label for="radio2"></label>Laborando actualmente<br />
	        <input type="radio" name="radio3" id="radio3" value="radio3" />
	        <label for="radio3"></label>Temporalmente desocupado<br />
	      	<span><input class="boton" name="" type="button" value="Guardar" /></span>
      	</div>
      	<span class="bottom_status"></span>
    </div>
    <a href="#" id="cerrar_sesion">Cerrar sesi&oacute;n</a>
</div>