<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Preparaci&oacute;n de la entrevista</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Empresas</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		La entrevista laboral es la herramienta m&aacute;s utilizada en el proceso de reclutamiento de personal. Este primer encuentro con los postulantes a la oferta de trabajo es determinante para conocer su perfil y tener un proceso efectivo de selecci&oacute;n.
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
          <li class="active">Preparación de la entrevista</li>
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
			<h2 class="titulosh2">Preparación de la entrevista</h2>
		
	        <p>La entrevista laboral es la herramienta m&aacute;s utilizada en el proceso de reclutamiento de personal. Este primer encuentro con los postulantes a la oferta de trabajo es determinante para conocer su perfil y tener un proceso efectivo de selecci&oacute;n.</p>
<p>Durante la entrevista, tanto postulante como empleador persiguen objetivos. Por una parte, el entrevistador pretende encontrar al candidato id&oacute;neo para la posici&oacute;n y, por la otra, el entrevistado desea conseguir el puesto por el que compite. Es importante asegurarse que para el logro de tales objetivos se trabaje con informaci&oacute;n veraz y comprobable.</p>
<p><strong>Aspectos a considerar</strong></p>
<p>Llevar a cabo una entrevista requiere cierta preparaci&oacute;n para que los resultados obtenidos sean &uacute;tiles y brinden informaci&oacute;n de valor que facilite la elecci&oacute;n final.</p>
<p>La informaci&oacute;n proporcionada por el postulante debe ser cuidadosamente revisada y analizada debido a que de ella pueden surgir dudas sobre su experiencia laboral previa o alg&uacute;n aspecto acad&eacute;mico que no se especifique con claridad. En este sentido, la entrevista es la oportunidad id&oacute;nea para aclararlas. Para eso, es conveniente anotar las posibles preguntas acerca del entrevistado y cualquier aspecto que llame la atenci&oacute;n en su curr&iacute;culum. Esto, con la finalidad de tenerlo presente durante la entrevista.</p>
<p>El di&aacute;logo debe llevarse a cabo en un tiempo breve y es necesaria una interacci&oacute;n que rompa la tensi&oacute;n que suele haber en una entrevista, lo que se logra con un ritmo &aacute;gil, audaz y espont&aacute;neo por parte del entrevistador. Este debe ser quien dirija siempre la conversaci&oacute;n, por medio de preguntas abiertas que fomenten la explicaci&oacute;n y generen un buen ambiente en el momento de la charla.</p>
<p>La honestidad es un elemento imprescindible en una entrevista laboral. Por lo que el entrevistador deber&aacute; comunicar las necesidades y particularidades del puesto para no crear falsas expectativas. De igual forma, el postulante enfocar&aacute; sus esfuerzos en obtener la posici&oacute;n vacante, por lo que depender&aacute; de la habilidad del entrevistador determinar la veracidad de la informaci&oacute;n y el detectar al candidato m&aacute;s apto para cubrir el puesto.</p>
<p><strong>Ambiente sin tensi&oacute;n</strong></p>
<p>Es necesario tener en cuenta que el entorno en donde se realice la entrevista condicionar&aacute; los resultados del encuentro. Por esta raz&oacute;n, el di&aacute;logo debe llevarse a cabo en un lugar privado, en donde participen s&oacute;lo entrevistador y entrevistado.</p>
<p>Se recomienda iniciar la entrevista con preguntas sencillas que generen confianza en el candidato. Es posible comenzar indagando en las dificultades que tuvo para llegar al lugar de la cita, la distancia, entre otras. Esto suaviza el primer encuentro y reduce la tensi&oacute;n en ambas partes. De igual manera, se recomienda evitar aquellos cuestionamientos cuya respuesta sea s&oacute;lo &quot;S&iacute;&quot; o &quot;No&quot;, con la finalidad de obtener informaci&oacute;n completa.</p>
<p>Cuando el entrevistado relate alguna historia relacionada con su experiencia laboral no debe interrump&iacute;rsele, &uacute;nicamente cuando resulte necesario obtener informaci&oacute;n espec&iacute;fica sobre ese punto.</p>
<p>Si por alguna raz&oacute;n la entrevista se desv&iacute;a del tema de inter&eacute;s, pueden emplearse frases como &quot;antes de continuar&quot; o &quot;quisiera saber si&hellip;&quot;. Por medio de estas formulaciones, se evita perder el tiempo en informaci&oacute;n superficial y poco relevante para la empresa.</p>
<p>Respecto a la duraci&oacute;n de la entrevista, se recomienda que &eacute;sta no se prolongue m&aacute;s del periodo necesario, sobre todo cuando no hubo la empat&iacute;a necesaria entre el entrevistador y el candidato al puesto. En consecuencia, se simplificar&aacute; la sesi&oacute;n respetando los tiempos establecidos para ella.</p>
<p>Por &uacute;ltimo, es necesario tener en mente que, adem&aacute;s de ser una herramienta de selecci&oacute;n, la entrevista puede representar el inicio de la relaci&oacute;n laboral entre la empresa y el trabajador, por lo que es importante procurar las condiciones ideales para la presentaci&oacute;n y el conocimiento de ambos.</p>
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
