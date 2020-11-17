<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Accidentes en el trabajo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Empresas</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		M&aacute;s all&aacute; de un sueldo digno, un beneficio inmediato, es necesaria la implementaci&oacute;n de reglas y procedimientos que resguarden el bienestar de los trabajadores y les permita gozar de condiciones &oacute;ptimas de salud para su desarrollo laboral.
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
          <li class="active">Accidentes en el trabajo</li>
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
			<h2 class="titulosh2">Accidentes en el trabajo</h2>
		
	        <p>La <a href="<c:url value="/jsp/empleo/candidatos/leyFederalTrabajo.jsp"/>">Ley Federal del Trabajo</a> ha identificado m&aacute;s de 400 lesiones y enfermedades que son consecuencia directa de la vida laboral. En la <em>Tabla de Enfermedades de Trabajo</em> hay padecimientos que quiz&aacute; suenen desconocidos, pero que suelen   presentarse por una preocupante raz&oacute;n: la falta de prevenci&oacute;n de riesgos   en el entorno laboral.</p>
<p>M&aacute;s all&aacute; de un sueldo digno, un beneficio inmediato, es necesaria la implementaci&oacute;n de reglas y procedimientos que resguarden el bienestar de los trabajadores y les permita gozar de condiciones &oacute;ptimas de salud para su desarrollo laboral.</p>
<p>Las cifras sobre accidentes, lesiones y enfermedades provocadas en el trabajo o como consecuencia de &eacute;l, deber&iacute;an ser suficientes para que todas las empresas adopten medidas preventivas. De acuerdo con los datos recopilados por la Organizaci&oacute;n Internacional del Trabajo (OIT), en un a&ntilde;o ocurren 2.3 millones de muertes a nivel mundial debido a percances laborales; adem&aacute;s, la cifra de accidentados asciende a 337 millones. Estos indicadores, no reflejan la importancia que deben brindar las empresas a la seguridad del trabajador.</p>
<p><strong>La prevenci&oacute;n del riesgo</strong></p>
<p>A la par con sus obligaciones, el trabajador tambi&eacute;n adquiere ciertos derechos, entre los que se encuentra el de la seguridad en el lugar donde desempe&ntilde;a su labor y cuya implementaci&oacute;n depende de la empresa que lo contrata.</p>
<p>Todos los centros de trabajo, independientemente de su giro, deben analizar las situaciones o condiciones que ponen en riesgo el bienestar del trabajador, no s&oacute;lo respecto a accidentes, sino tambi&eacute;n en lo que concierne a la prevenci&oacute;n de las enfermedades profesionales.</p>
<p>El empleador no debe esperar a que los accidentes sucedan o los padecimientos comiencen para detectar los focos rojos en su empresa, por lo que resulta necesario establecer un plan de prevenci&oacute;n que estipule un escenario libre de siniestros.</p>
<p>Para lograrlo, se deben cumplir dos requisitos: que el patr&oacute;n provea el equipo, material o mobiliario necesario para el desempe&ntilde;o &oacute;ptimo y seguro de cada actividad, as&iacute; como la capacitaci&oacute;n del trabajador para el uso correcto de sus herramientas.</p>
<p>Estad&iacute;sticas del Instituto Mexicano del Seguro Social (IMSS) indican que los j&oacute;venes de entre 25 y 34 a&ntilde;os son los que sufren la mayor parte de los accidentes laborales, dato alarmante, pues se trata del grupo de edad en el que se concentra una gran parte de la poblaci&oacute;n econ&oacute;micamente activa del pa&iacute;s.</p>
<p>En materia de seguridad laboral, es com&uacute;n que las empresas enfoquen su atenci&oacute;n en las actividades que representan un mayor riesgo para sus empleados, como la operaci&oacute;n de maquinaria pesada o la ejecuci&oacute;n de cualquier trabajo f&iacute;sico. Sin embargo, al intentar ofrecer protecci&oacute;n y seguridad para los trabajadores, es importante considerar tambi&eacute;n elementos como la adecuaci&oacute;n de las instalaciones, la ergonom&iacute;a de las herramientas, el grado de ventilaci&oacute;n de los espacios, el nivel de iluminaci&oacute;n de las oficinas y la duraci&oacute;n de la jornada laboral. &Eacute;stas son s&oacute;lo algunas de las condiciones necesarias para garantizar un entorno de trabajo &oacute;ptimo.</p>
<p>Con la implementaci&oacute;n de un plan de prevenci&oacute;n de riesgos que provea las condiciones mencionadas, ser&aacute; posible disminuir molestias f&iacute;sicas e intelectuales como la fatiga muscular, la fatiga visual, la sobrecarga mental o el s&iacute;ndrome del t&uacute;nel carpiano ocasionado por movimientos repetitivos en la mu&ntilde;eca. &Eacute;stas, identificadas como consecuencias directas de condiciones atenuantes para la integridad del trabajador no suelen ser atendidas con la misma seriedad por todas las empresas.</p>
<p>Ante esta informaci&oacute;n, resulta de vital importancia que toda organizaci&oacute;n, sin importar la orientaci&oacute;n de su giro, establezca planes de prevenci&oacute;n de riesgos en atenci&oacute;n a los trabajadores, los cuales constituyen la principal fuerza productiva del pa&iacute;s.</p>
<p>Adem&aacute;s de los efectos nocivos en la salud, la baja productividad puede ser consecuencia directa de la falta de comodidad o de elementos perturbadores de la atenci&oacute;n, por lo que el cuidado de estos factores ser&aacute; ben&eacute;fico no s&oacute;lo para el trabajador sino para la compa&ntilde;&iacute;a en general.</p>
<p>Una silla c&oacute;moda, un ambiente libre de ruido y una iluminaci&oacute;n adecuada son igualmente importantes que un casco o un par de botas reforzadas. Por lo que una empresa formal que desea cumplir con los requisitos de seguridad laboral exigidos en la <a href="<c:url value="/jsp/empleo/candidatos/leyFederalTrabajo.jsp"/>">Ley Federal del Trabajo</a>, deber&aacute; siempre tomar en cuenta las necesidades en el entorno de sus trabajadores.</p>
<p><strong>La empresa y la seguridad social</strong></p>
<p>Es necesario que la empresa tenga una relaci&oacute;n directa con el instituto de seguridad social al que se encuentre inscrita, as&iacute; como que conozca sus lineamientos y le haga saber a sus empleados todo lo concerniente a su situaci&oacute;n en lo que a salud y seguridad se refiere.</p>
<p>El espectro de lo que la <a href="<c:url value="/jsp/empleo/candidatos/leyFederalTrabajo.jsp"/>">Ley Federal del Trabajo</a> identifica como riesgos en el trabajo es muy amplio. Sin embargo, en el contexto laboral puede definirse una &quot;situaci&oacute;n de riesgo&quot; como el accidente o enfermedad a que est&aacute;n expuestos los trabajadores en ejercicio o con motivo del trabajo.</p>
<p>A partir de este concepto se derivan una serie de situaciones avaladas por la ley en la que el patr&oacute;n debe hacerse cargo del trabajador, por lo que es necesario que ambos est&eacute;n al tanto de cu&aacute;ndo y c&oacute;mo un accidente o enfermedad se considera producto del trabajo.</p>
<p>Con base en lo anterior, es necesario conocer los procedimientos que cada instituci&oacute;n de salud estipula para proveer sus servicios, as&iacute; como los mecanismos para que el trabajador pueda ser atendido eficazmente. Por esta raz&oacute;n, resulta de gran importancia que exista una comunicaci&oacute;n estrecha entre empleador y empleado la cual garantice un entorno &oacute;ptimo, asegurando el bienestar y la productividad de toda la organizaci&oacute;n.</p>
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
