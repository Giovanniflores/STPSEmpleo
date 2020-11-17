<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">
function keySubmit(e) {
    if (e.keyCode == 13) {
    	document.loginForm.submit();
    }
}

var userclean = true;
var pswclean = true;

function clean(field, numfield) {

	if (numfield==1){
		if (userclean){
			field.value='';
			userclean = false;
		}		
	}

	if (numfield==2){
		if (pswclean){
			field.value='';
			pswclean = false;
		}		
	}
}
</script>

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" > 
  <tr> 
    <td align="center" valign="middle">
    	<img src="images/cabecera-email.jpg" alt="Bienvenido al Portal del Empleo" border="0" />
    </td> 
  </tr>
  <tr>
  	<td align="left" valign="middle">

	<c:if test="${empty USUARIO_APP}">
	    <div id="login">
	
	        <form id="loginForm" name="loginForm" action="login.do?method=loginresp" method="post">
	
	            <input type="text" name="username" id="username" value="Escribe tu nombre de usuario" onfocus="clean(this, 1)"/>
	
	            <input type="password" name="password" id="password" value="contrasena" onfocus="clean(this, 2)" onkeypress="keySubmit(event)" />
				
				<input type="button" value="Ir a mi carpeta" onclick="javascript:document.loginForm.submit();"/>
	        </form>
	
	        <div id="message_error">
	        	<c:out value="${errmsg}"/>
	        </div>
	
		</div>
	
	</c:if>

  	</td>
  </tr>   
</table>