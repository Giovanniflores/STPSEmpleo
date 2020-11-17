<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="${pageContext.request.requestURL}" />
<%
  String context = request.getContextPath() +"/";
%>
<title>Ofertas de empleo creadas | Portal del Empleo</title>
	<meta property="og:title" content="Portal del Empleo: Ofertas de empleo creadas">	
	<meta name="twitter:title" content="Portal del Empleo: Ofertas de empleo creadas">
	<meta property="og:description" content="El Portal del Empleo ofrece información y servicios que te apoyarán a mejorar tu búsqueda de empleo de la manera más fácil y novedosa."/>
	<meta name="twitter:description" content="El Portal del Empleo ofrece información y servicios que te apoyarán a mejorar tu búsqueda de empleo de la manera más fácil y novedosa."/>
	<meta name="description" content="El Portal del Empleo ofrece información y servicios que te apoyarán a mejorar tu búsqueda de empleo de la manera más fácil y novedosa."/>
	<meta property="og:url" content="${url}">
	<meta property="og:image" content="http://qa.empleo.gob.mx/css/images/compartir-contenido.jpg">
	<meta name="twitter:image:src" content="http://qa.empleo.gob.mx/css/images/contenido-compartir-tweetA.jpg">

<script type="text/javascript">
	function doSubmit(method){
		document.carrerasFormBean.method.value=method;
		document.carrerasFormBean.submit();
	}
	function cerrarse(){ 
		window.close();
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<body>
	<form name="spaceCompany" action="${context}spaceCompany.do?method=init" method="post">
		<input type="hidden" name="method" value="experienciaInit" />
		<input type="hidden" name="carrera" value="inicio" />
		<div class="candidato">
			<h2>
				Ofertas de empleo creadas
			</h2>
			<c:forEach var="oferta" items="${spacecompanyform.listOfertasCreadas}" varStatus="rowCounter">
				<div class="oferta_creada">
					<h3>
					${oferta.tituloOferta}
					<c:if test="${empty oferta.tituloOferta}">
						<strong>
							Título no especificado
						</strong>
					</c:if>
					</h3>
					<a href="#">Usar como plantilla</a>
					<a href="#">Búsqueda filtrada</a>
					<a class="resaltado" href="#">Desactivar</a>/
					<a class="resaltado" href="#">Eliminar</a>
					<a class="resaltado" href="#">Editar</a>
<!-- 					<br/> -->
					<div class="criterios">
						${oferta.ocupacionDescripcion}
						<c:if test="${empty oferta.ocupacionDescripcion}">
							<strong>
								Ocupación no especificada
							</strong>
						</c:if> |
						${oferta.nivelEstudiosDescripcion}
						<c:if test="${empty oferta.nivelEstudiosDescripcion}">
							<strong>
								Estudios no especificados
							</strong>
						</c:if> |
						${oferta.carreraDescripcion}
						<c:if test="${empty oferta.carreraDescripcion}">
							<strong>
								Carrera no especificada
							</strong>
						</c:if> |
							${oferta.entidadDescripcion} ${oferta.municipioDescripcion}
						<c:if test="${empty oferta.entidadDescripcion}">
							<c:if test="${empty oferta.municipioDescripcion}">
								<strong>
									Ubicación no especificada
								</strong>
							</c:if>
						</c:if> |
						<c:if test="${oferta.estatus == 1}">
							<a class="lupa" href="${context}detalleOfertaCreada.do?method=init&index=${rowCounter.count}">
								<img src="images/bottom_oferta.gif">
							</a>
						</c:if>
					</div>
				<img src="images/bottom_oferta.gif">
				</div>
			</c:forEach>
		</div>
	</form>
</body>