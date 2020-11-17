<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Decisi&oacute;n final</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Conoce qu&eacute; debes saber del empleo para tomar una mejor decisi&oacute;n.  Los buscadores de trabajo deben tomar decisiones trascendentes al momento de iniciar el proceso de selecci&oacute;n de personal, puesto que este requiere tiempo, dinero y esfuerzo, mismos que deben emplearse en contratar a quien mayores beneficios traiga a la empresa.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasServiciosEmpleo.jsp"/>">Programas y servicios de empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/vinculacionLaboral.jsp"/>">Vinculación laboral</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/talleresBuscadoresEmpleo.jsp"/>">Talleres para buscadores de empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/talleresLinea.jsp"/>">Talleres en línea</a></li>
          <li class="active">Decisión final</li>
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
			<h2 class="titulosh2">Decisión final</h2>
		
	        <p>Conoce qu&eacute; debes saber del empleo para tomar una mejor decisi&oacute;n</p>
<p>Los buscadores de trabajo deben tomar decisiones trascendentes al momento de iniciar el proceso de selecci&oacute;n de personal, puesto que este requiere tiempo, dinero y esfuerzo, mismos que deben emplearse en contratar a quien mayores beneficios traiga a la empresa.</p>
<p>Los representantes de las empresas y las personas que buscan trabajo toman decisiones a lo largo del proceso de reclutamiento y selecci&oacute;n.</p>
<p>A la empresa le interesa contar con personal capaz y dispuesto, aspecto que vuelca su inter&eacute;s en evaluar las capacidades, conocimientos, actitudes y dem&aacute;s aspectos relevantes del desempe&ntilde;o de un trabajador.</p>
<p>Quien solicita empleo debe decidir tomando en cuenta sus intereses y necesidades econ&oacute;micas, de seguridad, de desarrollo profesional, familiares y personales.</p>
<p>En este m&oacute;dulo, se enlistan una serie de preguntas que el solicitante de empleo debe formularse para asegurarse que tanto el puesto como la empresa son acordes a sus intereses y objetivos personales y profesionales, y que pueden ayudarlo a tomar la mejor decisi&oacute;n.</p>
<p>Antes de decidir, preg&uacute;ntate lo siguiente:</p>
<ul class="list-group-contenidos">
    <li>&iquest;El salario alcanza a cubrir los gastos de alimentaci&oacute;n, transporte y otros necesarios para mantenerte en el empleo?</li>
    <li>&iquest;La paga podr&aacute; cubrir tus gastos personales y del hogar?</li>
    <li>&iquest;El trabajo te asegura un ingreso regular o depende de las propinas, de la cantidad de productos adquiridos por clientes, etc.?</li>
    <li>&iquest;El trabajo supone pagar de tu sueldo gastos derivados de la actividad laboral como llamadas telef&oacute;nicas, consumo de gasolina, compra de papeler&iacute;a, etc.?</li>
    <li>&iquest;El domicilio del establecimiento est&aacute; en un &aacute;rea segura?</li>
    <li>&iquest;Debes arribar a la empresa muy temprano o salir muy tarde de ella?</li>
    <li>&iquest;Debes manejar dinero u otros valores sin la protecci&oacute;n adecuada?</li>
    <li>&iquest;Debes asumir responsabilidades muy elevadas para tu nivel de preparaci&oacute;n y experiencia?</li>
    <li>&iquest;La empresa suministra equipo de seguridad a sus trabajadores?</li>
    <li>&iquest;Recibir&aacute;s inducci&oacute;n acerca de la filosof&iacute;a de la empresa poco despu&eacute;s de tu contrataci&oacute;n?</li>
    <li>&iquest;La empresa te capacitar&aacute; para mejorar tus habilidades y conocimientos?</li>
    <li>&iquest;La empresa tiene establecida una pol&iacute;tica de incentivos para los mejores trabajadores?</li>
    <li>&iquest;Existe la posibilidad de ascenso?</li>
    <li>&iquest;La empresa te dar&aacute; oportunidad de continuar con tus estudios?</li>
    <li>&iquest;La din&aacute;mica familiar puede impedir cumplir con los compromisos laborales?</li>
    <li>&iquest;El horario y los compromisos de trabajo reducir&aacute;n significativamente el tiempo que acostumbras pasar con tu familia y amigos?</li>
    <li>&iquest;El trabajo requerir&aacute; que viajes constantemente fuera de tu localidad y que, incluso, cambies tu domicilio de manera temporal o definitiva?</li>
    <li>&iquest;La empresa da un trato digno a sus empleados, ya sean hombres, mujeres o personas con alguna discapacidad?</li>
    <li>&iquest;La empresa incurre en pr&aacute;cticas desleales en las que no aceptar&iacute;as participar?</li>
    <li>&iquest;El empleo puede ayudarte a solventar tu crisis econ&oacute;mica mientras identificas mejores opciones de trabajo?</li>
    <li>&iquest;El empleo te dar&aacute; la experiencia que requieres para mejorar profesionalmente?</li>
    <li>&iquest;El contacto con la infraestructura y procesos de trabajo de la empresa te puede ayudar a actualizar los conocimientos que el desempleo no te ha permitido poner al d&iacute;a?</li>
    <li>&iquest;Es un empleo que te ayudar&aacute; a orientar tus estudios, si a&uacute;n vas a la escuela?</li>
</ul>
<p>Aqu&iacute; termina el m&oacute;dulo.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
