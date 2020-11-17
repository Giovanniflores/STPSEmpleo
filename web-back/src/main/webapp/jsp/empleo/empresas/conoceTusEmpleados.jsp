<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Conoce a tus empleados</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Empresa</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Para aumentar la productividad en el trabajo, es importante que conozcas los puntos fuertes de cada trabajador para aprovechar al m&aacute;ximo su potencial, ya que de lo contrario las habilidades de los trabajadores se quedar&iacute;an en el olvido. Si conoces a fondo a cada trabajador la productividad de la empresa aumentar&aacute;, exponencialmente.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/empresas/empresas.jsp"/>">Empresas</a></li>
          <li><a href="<c:url value="/jsp/empleo/empresas/mejoraDesempeñoEmpresa.jsp"/>">Mejora el desempeño de tu empresa</a></li>
          <li class="active">Conoce a tus empleados</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp"/>
      
      <div class="col-sm-8 col-sm-pull-4">
      	 
      	 <jsp:include page="/WEB-INF/template/redes.jsp"/>
      
        <div class="clearfix"></div>
		
		 <div class="panel">
          <div class="panel-body">
			<h2 class="titulosh2">Conoce a tus empleados</h2>
		
	        <p>Para aumentar la productividad en el trabajo, es importante que conozcas los puntos fuertes de cada trabajador para aprovechar al m&aacute;ximo su potencial, ya que de lo contrario las habilidades de los trabajadores se quedar&iacute;an en el olvido. Si conoces a fondo a cada trabajador la productividad de la empresa aumentar&aacute;, exponencialmente.</p>
<p>El talento de los trabajadores es, sin duda, uno de los aspectos medulares de las compa&ntilde;&iacute;as. Si eres capaz de diferenciar el talento de cada uno de los empleados, ser&aacute;s capaz de capitalizar a la empresa con la mezcla exacta de habilidades y conocimientos.</p>
<p>Asimismo, el empleado pondr&aacute; su energ&iacute;a en las tareas claves y no en actividades banales que no traer&aacute;n resultados satisfactorios.</p>
<p>El an&aacute;lisis FODA (Fortalezas, Oportunidades, Debilidades, Amenazas), utilizado para evaluar a las empresas o proyectos, podr&iacute;as utilizarlo tambi&eacute;n para evaluar a tu personal, de esta manera, ser&iacute;a m&aacute;s f&aacute;cil identificar los puntos fuertes y d&eacute;biles de cada trabajador.</p>
<ul class="list-group-contenidos">
    <li><strong>Fortalezas:</strong><br />
    Destrezas del empleado, an&aacute;lisis de habilidades, experiencia y personalidad para conocer cu&aacute;l es la diferencia que marca entre los dem&aacute;s compa&ntilde;eros.</li>
    <li><strong>Oportunidades:</strong><br />
    &Aacute;reas en las que el trabajador pueda destacar y aprovechar su potencial y conocimientos.</li>
    <li><strong>Debilidades:</strong><br />
    Aspectos desfavorables en los empleados, que puede mejorarse. Mientras m&aacute;s detallado sea el an&aacute;lisis, sabr&aacute;s en qu&eacute; &aacute;reas fallan los trabajadores y podr&aacute;s ubicarlos en las &aacute;reas estrat&eacute;gicas de la empresa.</li>
    <li><strong>Amenazas:</strong><br />
    Aspectos externos que pudieran incidir de manera negativa en el rendimiento de la persona, a partir de ah&iacute;, dise&ntilde;a estrategias para contrarrestar los resultados.</li>
</ul>
<p>Cuando tengas identificadas las fortalezas y debilidades de cada empleado, es recomendable echar un vistazo a las &aacute;reas en las que pueden incursionar de manera positiva. Por ejemplo, un trabajador podr&iacute;a ser muy bueno para las ventas pero se encuentra en el &aacute;rea administrativa, tal vez, ser&iacute;a bueno cambiarlo de &aacute;rea.</p>
<p>Este an&aacute;lisis servir&aacute;, tambi&eacute;n, cuando existe alg&uacute;n proyecto y se necesite del trabajo en equipo. Entonces, tendr&aacute;s bien identificadas las habilidades de los empleados y al momento de asignar tareas, estar&aacute;n encaminadas en lo que mejor saben hacer.</p>
<p>Sin duda, conocer a tus empleados te traer&aacute; mejores resultados y podr&aacute;s aprovechar su potencial de la mejor manera y en beneficio de la empresa.</p>
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
