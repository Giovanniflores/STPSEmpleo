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
	function validaOfertasActivas() {
		<c:if test="${existenOfertasActivas == 'No'}">
			alert('Debe existir por lo menos una oferta de empleo activa, para ingresar a esta opción.');
		</c:if>
		<c:if test="${existenOfertasActivas == 'Si'}">
			employ();
		</c:if>
	}
	function validaOfertasActivasEspecifica() {
		<c:if test="${existenOfertasActivas == 'No'}">
			alert('Debe existir por lo menos una oferta de empleo activa, para ingresar a esta opción.');
		</c:if>
		<c:if test="${existenOfertasActivas == 'Si'}">
			document.location.href = '<%=request.getContextPath()%>/busquedaEspecificaCandidatos.do?method=init';
		</c:if>
	}
    function employ() {
    	document.ocp.searchQ.value = document.ocp.searchTopic.value;
    	document.ocp.idEntidad.value = document.ocp.searchPlace.value;
        document.ocp.submit();
    }
</script>

<!-- Navegación principal -->

<div id="navegacion">
    <ul>
        <li>
        <!-- 
        <a class="swb-menumap-act" href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a> -->
        </li>
        <li>
        <!--  
        <a class="swb-menumap-act" href="<c:url value="/jsp/empleo/empresas/empresas.jsp"/>">Empresas</a>-->
        </li>
        <li>
        <!-- 
        <a style="width: 319px" class="swb-menumap-act" href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a> -->
        </li>
    </ul>
</div>

<!--Buscador Interno -->
<div id="espacio_buscador_interno">
    <div id="buscador_interno" class="ac_2014Interno">

        <c:set var="rolEmpresa" value="${usuarioWebVO.isRolEmpresa()}"/>
        <c:if test="${rolEmpresa}">
            <!--Buscador de Ofertas-->
            <h2>Buscador de candidatos de empleo</h2>
            <form name="ocp" id="ocp" action="<%=contextApp%>/buscarcandidatos.do" method="post">
                <div>
                    <input type="hidden" name="method" value="search" />
                    <input type="hidden" name="searchQ" value="" />
                    <input type="hidden" name="idEntidad" value="" />                    
                  <!--  <div class="que_empleo">
                          <p class="buscador_1">
                            <label for="searchTopic" class="t_buscador b2">¿Qué candidato buscas?
                                <input name="searchTopic" id="searchTopic" value="" type="text" /></label></p>
                        <p class="ejemplo">Puedes indicar un puesto, carrera u oficio </p>
                    </div>-->
                  <!--   <div class="donde" id="spryselect1">
                         <p id="combo">
                            <label for="searchPlace" class="t_buscador">¿Dónde?
                                <select name="searchPlace" id="searchPlace">
                                    <option value="" selected="selected"></option>
                                    <option value="1">Aguascalientes</option>
                                    <option value="2">Baja California</option>
                                    <option value="3">Baja California Sur</option>
                                    <option value="4">Campeche</option>
                                    <option value="5">Coahuila</option>
                                    <option value="6">Colima</option>
                                    <option value="7">Chiapas</option>
                                    <option value="8">Chihuahua</option>
                                    <option value="9">Ciudad de México</option>
                                    <option value="10">Durango</option>
                                    <option value="11">Guanajuato</option>
                                    <option value="12">Guerrero</option>
                                    <option value="13">Hidalgo</option>
                                    <option value="14">Jalisco</option>
                                    <option value="15">México</option>
                                    <option value="16">Michoacán</option>
                                    <option value="17">Morelos</option>
                                    <option value="18">Nayarit</option>
                                    <option value="19">Nuevo León</option>
                                    <option value="20">Oaxaca</option>
                                    <option value="21">Puebla</option>
                                    <option value="22">Querétaro</option>
                                    <option value="23">Quintana Roo</option>
                                    <option value="24">San Luis Potosí</option>
                                    <option value="25">Sinaloa</option>
                                    <option value="26">Sonora</option>
                                    <option value="27">Tabasco</option>
                                    <option value="28">Tamaulipas</option>
                                    <option value="29">Tlaxcala</option>
                                    <option value="30">Veracruz</option>
                                    <option value="31">Yucatán</option>
                                    <option value="32">Zacatecas</option>
                                </select></label>
                        </p>
                        <%--<p class="ejemplo"><a href="<%=contextSWB%>/swb/empleo/Uso_del_Buscador">¿Cómo utilizar el buscador?</a></p>--%>
                    </div>-->
                   <!--  <input type="button" name="bt_buscador" id="bt_buscador" onclick="validaOfertasActivas();" value="Buscar" /> -->
                </div>
            </form>
            <!--div class="busqueda_especifica">También puedes realizar una  <a href="<%=contextApp%>/busquedaEspecificaCandidatos.do?method=init">Búsqueda específica</a></div -->
           <div class="busqueda_especifica"> También puedes realizar una  <a href="#" onclick="validaOfertasActivasEspecifica();">búsqueda específica</a> </div>
        </c:if>

    </div>
</div>
<!--Termina buscador interno-->

<div class="contacto_op">
   <!-- <ul>
        <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/necesitasAyuda.jsp"/>" class="ayuda_int">¿Necesitas ayuda? Inicia una asesoría</a></li>
        <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/contacto.jsp"/>" class="atencion_int">Atención telefónica 01 800 841 2020</a></li>
        <li style="margin-right: 0"><a href="<c:url value="/suggestion.do?method=init"/>" target="popUp" onclick="window.open(this.href, this.target, 'toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700'); return false;" class="quejas_int">Quejas y sugerencias</a></li>
    </ul> -->
    <div class="clearfix"></div>
</div>