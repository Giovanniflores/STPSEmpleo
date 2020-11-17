<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<fmt:setBundle basename="portal-web" var="messages"/>

<% String contextSWB = "https://www.empleo.gob.mx/";
if (null != request.getSession().getAttribute("FROM_CIL"))contextSWB = "http://cil.empleo.gob.mx/";
%>

<c:if test="${not empty USUARIO_APP}">

<script type="text/javascript">
function submitTo(idArticulo){
	document.articulosform.idArticulo.value=idArticulo;
	document.articulosform.submit();
}
</script>

<form name="articulosform" id="articulosform" action="articulos.do" method="post">
	<input type="hidden" name="idArticulo" id="idArticulo" value="0"/>
</form>
			<c:if test="${USUARIO_APP.empresa}">
			<div>
				<a class="link_conocer" href="conocerconfig.do?method=toSWBConocer">
				<span>Certificaci&oacute;n<br/> laboral</span> para<br/>empresarios y<br/> trabajadores
				</a>
			</div>
			<div class="tips">
				<a href="<%=contextSWB%>es_mx/empleo/ferias_de_empleo" ><span>Ferias</span> de empleo</a>
			</div>
			<div  class="foro">
				<a href="<fmt:message key='app.context.url' bundle='${messages}'/><c:url value="/ofertasRivieraMaya.do?method=init"/>" ><span>Ofertas</span> en la Riviera Maya</a>
			</div>
			<div class="relacionados">
					<h4>Recomendaciones</h4>
					<ul>
					   <%--  <li><a href="${USUARIO_APP.urlOcupacion}" target="_blank">${USUARIO_APP.tituloOcupacion}</a></li>
					   <li><a href="${USUARIO_APP.urlCarrera}" target="_blank">${USUARIO_APP.tituloCarrera}</a></li>--%>
					   <li><a href="<%=contextSWB%>en_mx/empleo/Proceso_de_Reclutamiento" target="_blank">El proceso de reclutamiento</a></li>
					   <li><a href="<%=contextSWB%>en_mx/empleo/La_evaluacion_de_candidatos" target="_blank">La evaluación de candidatos</a></li>
					</ul>
            </div>
            <div class="relacionados">
					<h4>Conoce más sobre</h4>
					<ul>
					   <%-- <li><a href="ofertasSFP.do?method=init">Ofertas en la Administración Pública</a></li>  --%>
					   <%-- <li><a href="javascript:submitTo(7);">Capacitación para el autoempleo</a></li>
					   <li><a href="javascript:submitTo(8);">Crédito FONACOT para la capacitación</a></li> --%>
					   <li><a href="<%=contextSWB%>en_mx/empleo/La_seleccion_de_personal" target="_blank">Selección de personal</a></li>
					   <li><a href="<%=contextSWB%>en_mx/empleo/Preparacion_de_la_entrevista" target="_blank">Preparación de la entrevista</a></li> 
					</ul> 
            </div>
			</c:if>
			<c:if test="${USUARIO_APP.candidato}">
			<div>
				<a class="link_conocer" href="conocerconfig.do?method=toSWBConocer">
				<span>Certifica tus <br> competencias</span> <br>y consigue un <br>mejor empleo
				</a>
			</div>
			
						
			<div class="tips">
				<a href="javascript:submitTo(1);" ><span>Tips</span> para conseguir empleo</a>
			</div>
			<div  class="foro">
				<a href="<fmt:message key='app.context.url' bundle='${messages}'/><c:url value="/ofertasRivieraMaya.do?method=init"/>" ><span>Ofertas</span> en la Riviera Maya</a>
			</div>

            <div class="relacionados">
					<h4>Recomendaciones</h4>
					<ul>
					   <%--  <li><a href="${USUARIO_APP.urlOcupacion}" target="_blank">${USUARIO_APP.tituloOcupacion}</a></li>
					   <li><a href="${USUARIO_APP.urlCarrera}" target="_blank">${USUARIO_APP.tituloCarrera}</a></li>--%>
					   <li><a href="<%=contextSWB%>en_mx/empleo/Descubre_tus_habilidades" target="_blank">Descubre tus habilidades</a></li>
					   <li><a href="<%=contextSWB%>en_mx/empleo/Consejos_para_el_trabajo" target="_blank">Consejos para el trabajo</a></li>
					</ul>
            </div>
            <div class="relacionados">
					<h4>Conoce más sobre</h4>
					<ul>
					   <%-- <li><a href="ofertasSFP.do?method=init">Ofertas en la Administración Pública</a></li>  --%>
					   <%-- <li><a href="javascript:submitTo(7);">Capacitación para el autoempleo</a></li>
					   <li><a href="javascript:submitTo(8);">Crédito FONACOT para la capacitación</a></li> --%>
					   <li><a href="<%=contextSWB%>en_mx/empleo/becas_cap" target="_blank">Becas a la Capacitación del SNE</a></li>
					   <li><a href="<%=contextSWB%>en_mx/empleo/CONOCER_general" target="_blank">CONOCER: Certificación de Competencias</a></li> 
					</ul> 
            </div>
            </c:if>
			<%--
			<div class="container_calendario">
				<c:import url="https://www.empleo.gob.mx/swb/empleo/ArticuloEventos"/>
			</div>
			--%>
			<%--
            <div class="relacionados">
                  <div id="twitter_widget">
					<script src="http://widgets.twimg.com/j/2/widget.js" ></script>
					<script>
					new TWTR.Widget({
					  version: 2,
					  type: 'profile',
					  rpp: 3,
					  interval: 6000,
					  width: 265,
					  height: 300,
					  theme: {
					    shell: {
					      background: '#D7D7D7',
					      color: '#000'
					    },
					    tweets: {
					      background: '#fff',
					      color: '#000',
					      links: '#FF6600'
					    }
					  },
					  features: {
					    scrollbar: false,
					    loop: false,
					    live: false,
					    hashtags: true,
					    timestamp: true,
					    avatars: false,
					    behavior: 'all'
					  }
					}).render().setUser('empleogob_mx').start();
					</script>
                  </div>
             </div>
             --%>

</c:if>
