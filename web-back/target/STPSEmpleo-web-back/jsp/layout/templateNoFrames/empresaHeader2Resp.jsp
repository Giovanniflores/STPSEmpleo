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
			document.location.href = '/busquedaEspecificaCandidatos.do?method=init';
		</c:if>
	}
//     function employ() {
//     	if (document.ocp.searchPlace.value == '')
//     		document.ocp.searchQ.value = document.ocp.searchTopic.value;
//     	else document.ocp.searchQ.value = document.ocp.searchTopic.value + ' ' + document.ocp.searchPlace.value;
//         document.ocp.submit();
//     }
    function employ() {
    	document.ocp.searchQ.value = document.ocp.searchTopic.value;
    	document.ocp.idEntidad.value = document.ocp.searchPlace.value;
        document.ocp.submit();
    }

</script>


<!-- div nav-black -->
<div class="row">
	<div class="col-sm-12">
		<div class="btn-group btn-group-justified nav-black">
			<a href="<%=contextSWB%>/es_mx/empleo/candidatos" class="btn btn-candidato">Candidato</a> 
			<a href="<%=contextSWB%>/es_mx/empleo/empresas" class="btn btn-empresa">Empresas</a>
			<a href="<%= context%>/es_mx/empleo/servicio_nacional_de_empleo_" class="btn btn-SNE">Servicio Nacional de Empleo</a>
		</div>
	</div>
</div>
<!-- /div nav-black -->



<div class="row"><!--Buscador Interno -->
	<div class="col-sm-12"><!-- div buscador -->
	<div class="panel panel-buscador empresa">
    	<div class="panel-body"><!-- /div panel-body -->
    	<c:set var="rolEmpresa" value="${usuarioWebVO.isRolEmpresa()}"/>
        	<c:if test="${rolEmpresa}">
        	<form name="ocp" id="ocp" action="<%=contextApp%>/buscarcandidatos.do" method="get">
        		<input type="hidden" name="method" value="search" />
                <input type="hidden" name="searchQ" value="" />
	            <div class="row">
	            	<div class="col-sm-12">
		                <div class="col-sm-5">
		                  <div class="form-group">
		                    <label for="searchTopic">¿Qué candidato buscas?</label>                
		                    <input name="searchTopic" id="searchTopic" value="" type="text" class="form-control">
		                  </div>
		                  <span class="help-block">Puedes indicar un puesto, carrera u oficio</span> 
		                </div>
		                <div class="col-sm-5">
		                  	<div class="form-group">
		                      <label for="searchPlace" class="t_buscador">¿Dónde?</label>
		                      <select name="searchPlace" id="searchPlace" class="form-control">
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
                                </select>
		                    </div>
		                     <!-- <span class="help-block text-right"><a href="https://qa.empleo.gob.mx/swb/empleo/Uso_del_Buscador">&iquest;C&oacute;mo utilizar el buscador?</a></span>  --> 
		                </div>
		                <div class="col-sm-2">
		                  <span class="hidden-xs blockBtn"></span>
		                  <input type="button" name="bt_buscador" id="bt_buscador" onclick="validaOfertasActivas();" value="Buscar" class="btn btn-buscador form-control" />
		                </div>
	                  </div>
	           	</div>
           	</form>
           	</c:if>
       	</div><!-- /div panel-body -->
       	<div class="panel-footer text-right">
            <div class="busquedaEspecificaLink">
            	También puedes realizar una <a href="#" onclick="validaOfertasActivasEspecifica();">búsqueda específica</a>
            </div>
          </div>
       	</div>
   	</div><!-- /div col-12 -->
</div><!-- /Buscador Interno -->

<!-- Contacto -->
<div class="row">
	<!-- div class="col-sm-4">
		<div class="panel panel-contactoSWB">
			<div class="ayuda">
				<a href="<%=contextSWB%>/swb/empleo/Necesitas_ayuda">
				¿Necesitas ayuda? Inicia una asesoría
				</a>
			</div>       
		</div>
	</div -->
	<div class="col-sm-4">
		<div class="panel panel-contactoSWB">
			<div class="atencion">
				<a href="<%=contextSWB%>/swb/empleo/contacto">Atención telefónica 01 800 841 2020</a>
			</div>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="panel panel-contactoSWB">
			<div class="quejas">
				<a href="<c:url value="/suggestion.do?method=init"/>" target="popUp" onclick="window.open(this.href, this.target, 'toolbar=no,directories=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=yes,width=830,height=700'); return false;">Quejas y sugerencias</a>
			</div>       
		</div>
	</div>
</div-->
<!-- Contacto -->