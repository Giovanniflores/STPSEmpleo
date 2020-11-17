<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">El an&aacute;lisis de puestos</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Empresas</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Para que la selecci&oacute;n de personal resulte eficaz, es fundamental conocer a profundidad las caracter&iacute;sticas del puesto y determinar las cualidades de la persona a desempe&ntilde;arlo. De esta manera, el empleado podr&aacute; cumplir con las responsabilidades que se le exijan.
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
          <li class="active">El análisis de puestos</li>
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
			<h2 class="titulosh2">El análisis de puestos</h2>
		
	        <p>Para que la <a href="<c:url value="/jsp/empleo/empresas/seleccionDePersonal.jsp"/>">selecci&oacute;n de personal</a> resulte eficaz, es fundamental conocer a profundidad las caracter&iacute;sticas del puesto y determinar las cualidades de la persona a desempe&ntilde;arlo. De esta manera, el empleado podr&aacute; cumplir con las responsabilidades que se le exijan.</p>
<p>Una vez detectada la necesidad de personal, el an&aacute;lisis del puestos es una etapa obligada en el proceso de reclutamiento y selecci&oacute;n de candidatos. Su objetivo es determinar las caracter&iacute;sticas de la persona que ocupar&aacute; el cargo, con base en una descripci&oacute;n y conocimiento previo de las funciones a desarrollar.</p>
<p>Para determinar con exactitud los requisitos que deben cumplir los interesados en la oferta laboral, el empleador debe conocer las necesidades del &aacute;rea o departamento que la solicita. Con base en estas especificaciones se detectar&aacute;n las responsabilidades que el puesto impondr&aacute; sobre quien lo ocupe y podr&aacute; elaborarse una lista detallada de actividades que deber&aacute;n realizar los aspirantes a cubrirlo.</p>
<p>De lo contrario, es probable que se solicite un perfil inadecuado, o que incluso se contrate a alguien que no ofrezca los resultados esperados. Si se omite este paso fundamental, el proceso de reclutamiento puede extenderse hasta obtener lo que realmente se busca.</p>
<p><strong>Descripci&oacute;n y especificaciones</strong></p>
<p>El proceso de an&aacute;lisis de puestos suele dividirse en la descripci&oacute;n y la evaluaci&oacute;n de las posiciones vacantes en una empresa. Tener esto en claro ayuda tanto al empleador para delimitar su elecci&oacute;n, como al candidato, para postularse s&oacute;lo al trabajo que puede desarrollar satisfactoriamente.</p>
<p>La descripci&oacute;n enumera las funciones espec&iacute;ficas que conforman el cargo: &iquest;Qui&eacute;n lo hace? Adem&aacute;s, &iquest;qu&eacute;, c&oacute;mo, cu&aacute;ndo y para qu&eacute; se hace? Su funci&oacute;n es determinar las obligaciones y responsabilidades inherentes al puesto. Las especificaciones del cargo se refieren a las aptitudes que debe tener el aspirante para ejercer su labor de manera apropiada. Influyen la experiencia, las caracter&iacute;sticas intelectuales y f&iacute;sicas y, en general, las cualidades que mostrar&aacute; en su desempe&ntilde;o.</p>
<p><strong>&iquest;Cu&aacute;ndo implementarlo?</strong></p>
<p>Hay ciertas situaciones en el interior de una empresa que demandan un an&aacute;lisis de puestos y que van m&aacute;s all&aacute; de las necesidades de contrataci&oacute;n. La creaci&oacute;n de una empresa lo vuelve imprescindible, ya sea cuando se establecen nuevas funciones o cuando se modifican significativamente las ya existentes.</p>
<p>Otras llamadas de atenci&oacute;n que demandan la implementaci&oacute;n de un an&aacute;lisis de puestos son la alta rotaci&oacute;n de personal, la falta de capacitaci&oacute;n, la baja productividad y el desconocimiento de las labores por parte del trabajador.</p>
<p>Algunos de los datos que se re&uacute;nen en un an&aacute;lisis de puestos son las actividades de trabajo, los procedimientos estipulados para cada funci&oacute;n, las m&aacute;quinas y herramientas utilizadas, las condiciones f&iacute;sicas del trabajo, las habilidades requeridas, el desempe&ntilde;o del trabajador, entre otras.</p>
<p>Considerando lo anterior, la direcci&oacute;n podr&aacute; mejorar las &aacute;reas que requieran actualizaci&oacute;n, determinar&aacute; la remuneraci&oacute;n justa y especificar&aacute; los principales requisitos del perfil laboral que solicita. Igualmente, el empleado conocer&aacute; mejor sus funciones, as&iacute; como las expectativas que la empresa tiene hacia &eacute;l.</p>
<p><strong>&iquest;C&oacute;mo realizarlo?</strong></p>
<p>La descripci&oacute;n y el an&aacute;lisis de puestos pueden realizarse por medio de la observaci&oacute;n directa, el cuestionario, la entrevista o una combinaci&oacute;n de dichas t&eacute;cnicas. Lo m&aacute;s recomendable es que sea supervisado por el jefe de departamento o por la persona que m&aacute;s relacionada est&eacute; con el puesto a examinar.</p>
<p>La observaci&oacute;n directa es una de las t&eacute;cnicas m&aacute;s utilizadas, y de preferencia debe realizarse mientras el ocupante del cargo est&aacute; en acci&oacute;n, de manera din&aacute;mica, y registrando todo lo que surja en torno a la funci&oacute;n que desempe&ntilde;a.</p>
<p>Por su parte, el cuestionario suele funcionar para los trabajos de car&aacute;cter burocr&aacute;tico. Es r&aacute;pido, econ&oacute;mico y se formula a manera de preguntas sencillas.</p>
<p>La entrevista suele ser la t&eacute;cnica m&aacute;s productiva para el an&aacute;lisis de puestos y, cuando es realizada de manera correcta, arroja datos interesantes y valiosos para el analista.</p>
<p>La elecci&oacute;n de la t&eacute;cnica a utilizar para el an&aacute;lisis de puestos depender&aacute; del tipo de trabajo examinado, ya que para algunos la observaci&oacute;n directa suele ser suficiente, pero para otros es necesario profundizar en las especificaciones del puesto por medio de entrevistas o cuestionarios.</p>
<p>Conocer los requerimientos de cada &aacute;rea a trav&eacute;s del an&aacute;lisis de puestos es una forma de aprovechar todos los recursos humanos y materiales disponibles para alcanzar el &eacute;xito y la productividad de la organizaci&oacute;n.</p>
<p>A partir de este concepto se derivan una serie de situaciones avaladas por la ley en la que el patr&oacute;n debe hacerse cargo del trabajador, por lo que es necesario que ambos est&eacute;n al tanto de cu&aacute;ndo y c&oacute;mo un accidente o enfermedad se considera producto del trabajo.</p>
<p>Con base en lo anterior, es necesario conocer los procedimientos que cada instituci&oacute;n de salud estipula para proveer sus servicios, as&iacute; como los mecanismos para que el trabajador pueda ser atendido eficazmente. Por esta raz&oacute;n, resulta de gran importancia que exista una comunicaci&oacute;n estrecha entre empleador y empleado la cual garantice un entorno &oacute;ptimo, asegurando el bienestar y la productividad de toda la organizaci&oacute;n.</p>
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
