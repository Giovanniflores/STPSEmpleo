<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.web.security.vo.UsuarioWebVO"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String context = (String)application.getAttribute("DOMAIN_PORTAL");
    String contextApp = request.getContextPath();
    String contextSWB = (String)application.getAttribute("DOMAIN_PORTAL");
    UsuarioWebVO usuarioWebVO = (UsuarioWebVO) session.getAttribute(Constantes.USUARIO_APP);
    if (null != request.getSession().getAttribute("FROM_CIL")){
        contextSWB = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
        context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
    }
    pageContext.setAttribute("usuarioWebVO", usuarioWebVO);
%>

<script type="text/javascript">
    var djConfig = {
        parseOnLoad : true,
        isDebug : false
    };

</script>
<script type="text/javascript">
    function employ() {
        document.ocp.searchPlace.value = document.ocp.searchPlace.value;
        document.ocp.searchQ.value = document.ocp.searchTopic.value;
        document.ocp.submit();
    }

</script>

<!--Buscador Interno -->
<div id="espacio_buscador_interno">
    <div id="buscador_interno" class="ac_2014Interno">
        <!--Buscador de Ofertas-->
        <h2>Buscador de ofertas de empleo</h2>
        <form name="ocp" id="ocp" action="<c:url value="/ocupate.do"/>" method="get">
			<div class="busqueda_especifica">También puedes realizar una  <a href="<c:url value="/busquedaEspecificaOfertas.do?method=buscar"/>">búsqueda específica</a></div>
        </form>
    </div>
</div>
<!-- Termina buscador interno -->