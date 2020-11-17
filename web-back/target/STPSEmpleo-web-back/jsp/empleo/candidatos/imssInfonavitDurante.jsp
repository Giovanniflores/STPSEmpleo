<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">IMSS e Infonavit durante la vida laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		&iquest;Al ingresar a trabajar para una empresa, bajo qu&eacute; r&eacute;gimen del seguro social me tienen que dar de alta?
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/asesoriaLaboral.jsp"/>">Asesoría laboral</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/preguntasFrecuentes.jsp"/>">Preguntas frecuentes</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/duranteVidaLaboral.jsp"/>">Durante de la vida laboral</a></li>
          <li class="active">IMSS e Infonavit</li>
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
			<h2 class="titulosh2">IMSS e Infonavit</h2>
		
	        <p>Asimismo, deber&aacute;s acudir a la instituci&oacute;n bancaria donde se abri&oacute; la cuenta individual para efectuar los tr&aacute;mites correspondientes y, en caso de negativa, el t&eacute;rmino con el que cuentas para demandar es de 10 a&ntilde;os contados a partir de la fecha en que se otorg&oacute; la pensi&oacute;n. De transcurrir esos t&eacute;rminos y no demandar, la acci&oacute;n prescribe.</p>
<p>De conformidad con el art&iacute;culo decimotercero transitorio de la Ley del Seguro Social vigente, si el trabajador asegurado se acoge de los beneficios de la ley anterior para el otorgamiento de su pensi&oacute;n, adem&aacute;s de los fondos de la subcuenta del seguro de retiro de la nueva cuenta individual que tendr&aacute; efectos a partir del 1&deg; de julio de 1997 y cuya primera aportaci&oacute;n corresponder&aacute; al cuarto bimestre de 1997, no as&iacute; a los fondos de la subcuenta de vivienda de la cuenta individual.</p>
<p><strong>&iquest;Qu&eacute; debo de hacer para obtener un cr&eacute;dito del Infonavit?</strong></p>
<p>&Uacute;nicamente los trabajadores inscritos al Infonavit tienen el derecho de obtener alg&uacute;n cr&eacute;dito. La asignaci&oacute;n de cr&eacute;ditos se hace por medio de un sistema de puntuaci&oacute;n; s&oacute;lo los trabajadores que alcancen la puntuaci&oacute;n m&iacute;nima, podr&aacute;n obtener un cr&eacute;dito.</p>
<p>Son cinco los factores que se toman en cuenta para determinar la puntuaci&oacute;n que corresponde a cada trabajador: salario, edad, saldo de la subcuenta de vivienda de la cuenta individual del Sistema de Ahorro para el Retiro, n&uacute;mero de aportaciones hechas al Infonavit a su favor, el n&uacute;mero de dependientes econ&oacute;micos y el ahorro voluntario que el trabajador haya acumulado con la modalidad de aportaciones adicionales.</p>
<p>Para calcular la puntuaci&oacute;n de cada trabajador, se deben localizar los puntos que le correspondan combinando su salario y edad en la tabla que forma parte de las propias reglas. Dicha tabla es restrictiva en cuanto al ingreso, pues excluye a los trabajadores que perciben m&aacute;s de 10 veces el salario m&iacute;nimo general en el Distrito Federal.</p>
<p>Los puntos que se pueden obtener, tomando en consideraci&oacute;n estos factores, van de 14 hasta 120. A la puntuaci&oacute;n que resulte de aplicar la tabla mencionada se sumar&aacute; un punto por cada salario m&iacute;nimo mensual que se tenga acumulado en el saldo de la subcuenta de vivienda de la cuenta individual del Sistema de Ahorro para el Retiro, asimismo se sumar&aacute;n un punto por cada aportaci&oacute;n bimestral al Infonavit.</p>
<p>Al puntaje obtenido, se agregar&aacute;n cinco puntos por cada dependiente econ&oacute;mico, pero s&oacute;lo hasta un m&aacute;ximo de dos dependientes, as&iacute; como dos puntos en caso de que exista ahorro voluntario por cada salario m&iacute;nimo mensual ahorrado. Considerando el salario del trabajador y el n&uacute;mero de a&ntilde;os en que se pagar&aacute; el cr&eacute;dito, que puede ser de uno a 30, se determinan las cantidades que puede prestar el instituto. El monto m&aacute;ximo de los cr&eacute;ditos es de 180 veces el salario m&iacute;nimo general mensual en el Distrito Federal. El precio de la vivienda objeto del cr&eacute;dito no podr&aacute; exceder de 300 veces dicho salario.</p>
<p><strong>&iquest;C&oacute;mo se determinan los intereses que se generan en el cr&eacute;dito habitacional que me otorg&oacute; el Infonavit?</strong></p>
<p>Los cr&eacute;ditos concedidos generan intereses sobre su saldo, a una tasa anual del 6%. Asimismo, el saldo del cr&eacute;dito se ajusta en la proporci&oacute;n en que se aumente el salario m&iacute;nimo general del Distrito Federal.</p>
<p>Fundamento legal: regla d&eacute;cima segunda de las reglas para el otorgamiento de cr&eacute;ditos a los trabajadores derechohabientes.</p>
<p><strong>Obtuve un cr&eacute;dito habitacional por parte del Infonavit, el cual ya est&aacute; pagado en su totalidad, &iquest;puedo obtener otro?</strong></p>
<p>El Infonavit asignar&aacute; el segundo cr&eacute;dito, con base en los planes de labores y de financiamiento aprobados por la Asamblea General, a todos los trabajadores que hayan liquidado su primer cr&eacute;dito Infonavit sin quebrantos o incumplimientos un a&ntilde;o antes de realizar la solicitud de cr&eacute;dito y que cuenten con, por lo menos, cinco a&ntilde;os de cotizaci&oacute;n continua.</p>
<p>B. Montos m&aacute;ximos de cr&eacute;dito y tasas de inter&eacute;s y factores de descuento.</p>
<p>La tasa de inter&eacute;s de los segundos cr&eacute;ditos ser&aacute; fijada, anualmente, como resultado de la subasta p&uacute;blica que se realice entre las entidades financieras participantes. Con base en esta tasa de inter&eacute;s, se determinar&aacute;n los montos m&aacute;ximos de cr&eacute;dito y los factores de descuento.</p>
<p>C. Requisitos adicionales.</p>
<p>El instituto otorgar&aacute; un segundo cr&eacute;dito siempre y cuando transfiera un porcentaje del cr&eacute;dito otorgado a una entidad financiera y el derechohabiente cumpla con los criterios de elegibilidad que el instituto convenga con &eacute;sta. Dichos criterios se deber&aacute;n dar a conocer a los derechohabientes en el sitio de internet del instituto y, en ning&uacute;n caso, ser&aacute;n m&aacute;s restrictivos que los que dichas entidades apliquen al otorgamiento de sus cr&eacute;ditos en cofinanciamiento con el instituto.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
