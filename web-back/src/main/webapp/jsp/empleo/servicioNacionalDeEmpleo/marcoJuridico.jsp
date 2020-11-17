<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Marco jur&iacute;dico del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		La creaci&oacute;n del Servicio Nacional de Empleo se encuentra dentro de un marco legal establecido por las siguientes leyes y reglamentos:
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/acercaSne.jsp"/>">Acerca del SNE </a></li>
          <li class="active">Marco jurídico</li>
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
			<h2 class="titulosh2">Marco jurídico</h2>
		
	        <p>La creaci&oacute;n del Servicio Nacional de Empleo se encuentra dentro de un marco legal establecido por las siguientes leyes y reglamentos:</p>
<ul class="list-group-contenidos">
    <li>Constituci&oacute;n Pol&iacute;tica de los Estados Unidos Mexicanos: Prev&eacute; la estructura del estado mexicano como una rep&uacute;blica representativa, democr&aacute;tica y federal, compuesta de estados libres y soberanos, pero unidos en una federaci&oacute;n (Art&iacute;culo 40). Estipula que toda persona tiene derecho al trabajo digno y socialmente &uacute;til (Art&iacute;culo 123).</li>
    <li>Ley Org&aacute;nica de la Administraci&oacute;n P&uacute;blica Federal: Establece las facultades de las secretar&iacute;as de Gobernaci&oacute;n, Hacienda y Cr&eacute;dito P&uacute;blico, Desarrollo Social y de la Funci&oacute;n P&uacute;blica en lo correspondiente a la coordinaci&oacute;n con las entidades federativas (Art&iacute;culos 27, fracci&oacute;n XIV, 31, 32 y 37). El Art&iacute;culo 40 determina los pormenores en materia de capacitaci&oacute;n, adiestramiento y funcionamiento del Servicio Nacional de Empleo.</li>
    <li>Ley de Planeaci&oacute;n: Establece y regula la planeaci&oacute;n nacional de desarrollo y las bases de coordinaci&oacute;n del Ejecutivo Federal con las entidades federativas, as&iacute; como los instrumentos jur&iacute;dicos a trav&eacute;s de los cuales se dar&aacute; &eacute;sta &uacute;ltima.</li>
    <li>Ley Federal de Presupuesto y Responsabilidad Hacendaria: Regula la programaci&oacute;n, presupuesto, aprobaci&oacute;n, ejercicio, control y evaluaci&oacute;n de los egresos p&uacute;blicos federales, en donde se incluye la elaboraci&oacute;n del proyecto de Presupuesto de Egresos y la manera en que se ejecutar&aacute;n los programas a cargo de la dependencias del Ejecutivo Federal.</li>
    <li>Ley Federal del Trabajo: Prev&eacute; y establece las actividades que corresponden al Servicio Nacional de Empleo, Capacitaci&oacute;n y Adiestramiento (Art&iacute;culos 537, 538 Y 539).</li>
    <li>Ley Federal de Transparencia y Acceso a la Informaci&oacute;n P&uacute;blica Gubernamental: Regula la publicidad y transparencia de la aplicaci&oacute;n de recursos en la ejecuci&oacute;n de los programas a cargo de las dependencias del Ejecutivo Federal.</li>
    <li>Plan Nacional de Desarrollo: Determina la promoci&oacute;n de las pol&iacute;ticas de Estado y genera las condiciones en el mercado laboral que incentiven la creaci&oacute;n de empleos de alta calidad en el sector formal. (Eje tem&aacute;tico 2. Econom&iacute;a competitiva y generadora de empleos. Objetivo 2. Estrategias 4.1 y 4.2.)</li>
    <li>Presupuesto de Egresos de la Federaci&oacute;n. Prev&eacute; el ejercicio, control y evaluaci&oacute;n del gasto p&uacute;blico para cada ejercicio fiscal.</li>
    <li>Reglamento de Agencias de Colocaci&oacute;n de Trabajadores: Determina las acciones que en dicha materia prev&eacute;n los Anexos de Ejecuci&oacute;n del PAE, espec&iacute;ficamente en las facultades de los gobiernos de los Estados sobre agencias de colocaci&oacute;n de trabajadores, as&iacute; como el fortalecimiento de la coordinaci&oacute;n entre las citadas agencias y las bolsas de trabajo p&uacute;blicas y privadas.&nbsp;</li>
    <li>Convenio de Coordinaci&oacute;n para el Desarrollo Social y Humano: Este fue celebrado por el Poder Ejecutivo Federal con la participaci&oacute;n de las secretar&iacute;as de Gobernaci&oacute;n, de Hacienda y Cr&eacute;dito P&uacute;blico, de Desarrollo Social y de la Funci&oacute;n P&uacute;blica, con el titular del Poder Ejecutivo de las entidades federativas.<br />
    <br />
    Su objetivo es coordinar a los poderes ejecutivos tanto federal como estatales para ejecutar programas, acciones y recursos con el fin de trabajar de manera conjunta en la tarea de superar la pobreza y marginaci&oacute;n; vincular las acciones de los programas sectoriales, regionales, institucionales y especiales que lleve a cabo el Ejecutivo Federal a trav&eacute;s de sus dependencias y entidades y conjuntar esfuerzos en materia de planeaci&oacute;n, dise&ntilde;o, implementaci&oacute;n y evaluaci&oacute;n de programas y pol&iacute;ticas sociales.</li>
    <li>Acuerdo para establecer las Reglas de Operaci&oacute;n e Indicadores de Evaluaci&oacute;n y de Gesti&oacute;n del Programa de Apoyo al Empleo (PAE). Este tuvo como objetivos instaurar los subprogramas y dem&aacute;s acciones que ofrece el PAE, as&iacute; como los lineamientos, la poblaci&oacute;n objetivo, los beneficiarios, requisitos y procedimientos de selecci&oacute;n, auditoria, control y seguimiento.</li>
</ul>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
