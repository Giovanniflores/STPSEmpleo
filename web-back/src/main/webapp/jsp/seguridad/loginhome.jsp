<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%String context = request.getContextPath() +"/";%>

<script type="text/javascript">
function keySubmit(e) {
    if (e.keyCode == 13) {
    	document.loginForm.submit();
    }
}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<c:if test="${empty USUARIO_APP}">
    <div id="login">
        <form id="loginForm" name="loginForm" action="${context}login.do?method=login" method="post">
        	  <input type="hidden" id="isHome" name="isHome" value="true" />
            <c:choose>
	            <c:when test="${!empty bFromAbriendoEspacios && bFromAbriendoEspacios==true}">
		            <label for="login_page">Candidato</label>
		            <input id="login_page" name="login_page" type="radio" value="cand" />
		            <label for="login_page">Empresa</label>
		            <input id="login_page" name="login_page" type="radio" value="emp"  checked="checked" />	            
	            </c:when>
				<c:otherwise>
		            <label for="login_page">Candidato</label>
		            <input id="login_page" name="login_page" type="radio" value="cand" checked="checked" />
		            <label for="login_page">Empresa</label>
		            <input id="login_page" name="login_page" type="radio" value="emp" />				
				</c:otherwise>            
            </c:choose>
        	  
            <input type="text" name="username" id="username" value="Escribe tu nombre de usuario" onfocus="this.value='';"/>
            <input type="password" name="password" id="password" value="contrasena" onfocus="this.value='';" onkeypress="keySubmit(event)" />
        	  <a class="bt_mi_carpeta" href="#" onclick="javascript:document.loginForm.submit();" >Ir a mi carpeta</a>        	
        </form>
        <div id="message_error">
        	<c:out value="${errmsg}"/>
        	<%session.removeAttribute("errmsg");%>
        </div>
	</div>
</c:if>
