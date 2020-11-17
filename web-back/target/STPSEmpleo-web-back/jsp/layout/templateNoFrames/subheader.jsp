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

	<!-- Navegación principal -->
	
	<div id="navegacion">
        <ul>
           <li class="swb-menumap-cur" href="<%=contextSWB%>/">Servicio Nacional de Empleo</li>
           <li><a class="swb-menumap-act" href="<%=contextSWB%>/es_mx/empleo/busco_capacitacion">Capacitación y becas</a></li>
           <li><a style="width: 319px" class="swb-menumap-act" href="<%=contextSWB%>/es_mx/empleo/busco_asesoria_laboral">Asesoría laboral</a></li>
       </ul>     
   </div>

    <!--Buscador Interno -->
	 <div id="espacio_buscador_interno">
        <div id="buscador_interno" class="ac_2014Interno">

            <c:set var="rolCandidato" value="${usuarioWebVO.getCandidato()}"/>
            <c:if test="${rolCandidato}">
                <!--Buscador de Ofertas-->
                <h2>Buscador de ofertas de empleo</h2>
                <form name="ocp" id="ocp" action="<c:url value="/ocupate.do"/>" method="get">
                    <div>
                        <input type="hidden" name="method" value="init" />
                        <input type="hidden" name="searchQ" value="" />
                        <div class="que_empleo">
                            <p class="buscador_1">
                                <label for="searchTopic" class="t_buscador b2">¿Qué empleo buscas?
                                    <input name="searchTopic" id="searchTopic" value="" type="text" /></label></p>
                            <p class="ejemplo">Puedes indicar un puesto, carrera u oficio </p>
                        </div>
                        <div class="donde" id="spryselect1">
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
                            <p class="ejemplo">¿Cómo utilizar el buscador?</p>
                        </div>
                        <input type="button" name="bt_buscador" id="bt_buscador" onclick="employ()" value="Buscar" />
                    </div>
                </form>
                <a href="<%=contextSWB%>/swb/empleo/Uso_del_Buscador" class="ayuda1" title="Ayuda para uso del buscador">Ayuda para uso del buscador</a>
                <div class="busqueda_especifica">También puedes realizar una  <a href="<c:url value="/busquedaEspecificaOfertas.do?method=buscar"/>">búsqueda específica</a></div>
            </c:if>

        </div>
    </div>
    <!--Termina buscador interno-->

    <div class="contacto_op">
   <ul>
        <!-- li><a href="#" class="ayuda_int">¿Necesitas ayuda? Inicia una asesoría</a></li -->
        <li><a href="#" class="atencion_int">Atención telefónica 01 800 841 2020</a></li>
        <li style="margin-right: 0"><a href="<c:url value="/suggestion.do?method=init"/>" target="popUp" onclick="window.open(this.href, this.target, 'toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700'); return false;" class="quejas_int">Quejas y sugerencias</a></li>
    </ul>
    <div class="clearfix"></div>
</div>
