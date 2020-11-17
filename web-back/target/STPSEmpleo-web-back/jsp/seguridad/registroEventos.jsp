<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="portal-web" var="messages"/>
<script type="text/javascript">

function goAhead(){
		 dojo.xhrPost({url: '${pageContext.request.contextPath}/registroseventos.do?method=proximosEventos&goAhead=true', timeout:180000, 
                    load: function(data) {
			dojo.byId('registroEventos').innerHTML = data;
                    }});
	}
	
function goBack(){
			 dojo.xhrPost({url: '${pageContext.request.contextPath}/registroseventos.do?method=proximosEventos&goBack=true', timeout:180000, 
	                    load: function(data) {
				dojo.byId('registroEventos').innerHTML = data;
	                    }});
}
</script>

<div class="panel panel-default panel-calendar">
	         <div class="panel-heading text-center">
				<c:if  test="${sessionScope.eventosRecientesList.activaAnterior}">
					<a href="javascript:goBack()" class="mes_anterior pull-left calendar-control" alt="mes anterior"></a>
				</c:if>	
				<h3><c:out value="${sessionScope.eventosRecientesList.mesElementosPaginaActual} " /></h3>
			<c:if test="${sessionScope.eventosRecientesList.activaSiguiente}">
				<a href="javascript:goAhead()" class="mes_siguiente pull-right calendar-control" alt="mes siguiente"></a>
			</c:if>	
	          </div>
	          <div class="panel-body">
	          <c:forEach var="eventos" items="${sessionScope.eventosRecientesList.elementosPaginaActual}" >
	             <div class="col-xs-3">
	                <span class="dia_calendario"><c:out value="${eventos.diaMesFechaInicio}"/></span>
	             </div>
	             <div class="col-xs-9">
	              <p><a href="<fmt:message key='app.domain.ferias.virtual' bundle='${messages}'/>/content/oferta/OfertasEvento.jsf?faces-redirect=true&idEvento=${eventos.idEvento}&idEntidad=${eventos.idEntidad}" target="_blank"><c:out value="${eventos.evento}"/></a></p>
	              <p><c:out value="${eventos.calle} ${eventos.numeroExterior} C.P. ${eventos.codigoPostal} ${eventos.localidad}"/></p>
	             </div>
	             <hr></hr>
			    </c:forEach>
	          </div>
</div>