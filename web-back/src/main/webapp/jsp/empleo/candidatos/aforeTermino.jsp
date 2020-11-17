<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">AFORE al t&eacute;rmino de la vida laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">Cuando cumplas con los requisitos establecidos para los ramos de Cesant&iacute;a en Edad Avanzada y Vejez (a&ntilde;os de edad y cotizaciones semanales) a fin de poder disfrutar de una pensi&oacute;n.</jsp:attribute>
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
          <li><a href="<c:url value="/jsp/empleo/candidatos/alTerminoVidaLaboral.jsp"/>">Al término de la vida laboral</a></li>
          <li class="active">AFORE</li>
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
			<h2 class="titulosh2">AFORE</h2>
		
	        <p><strong>&iquest;Cu&aacute;ndo puedo disponer del saldo acumulado en mi cuenta individual?</strong></p>
<p>Cuando cumplas con los requisitos establecidos para los ramos de Cesant&iacute;a en Edad Avanzada y Vejez (a&ntilde;os de edad y cotizaciones semanales) a fin de poder disfrutar de una pensi&oacute;n. Para tal prop&oacute;sito podr&aacute;s optar por alguna de las siguientes alternativas:</p>
<ol type="a">
    <li>Contratar con la instituci&oacute;n de seguros de tu elecci&oacute;n una renta vitalicia que se actualizar&aacute;, anualmente, en el mes de febrero conforme al &Iacute;ndice Nacional de Precios al Consumidor (INPC).</li>
    <li>Mantener el saldo en tu cuenta individual con la Afore y efectuar, con cargo a &eacute;sta, retiros programados. En el momento que lo desees, podr&aacute;s optar por la alternativa anterior, en tanto el saldo de tu cuenta permita cubrir el monto constitutivo de la pensi&oacute;n.</li>
</ol>
<p><strong>&iquest;Cu&aacute;ndo puedo hacer retiros parciales de mi cuenta individual del Sistema de Ahorro para el Retiro (SAR)?</strong></p>
<p>Durante el tiempo en que el trabajador deje de estar sujeto a una relaci&oacute;n laboral, tendr&aacute; derecho a:  retirar parcialmente por situaci&oacute;n de desempleo los recursos de la Subcuenta de Retiro, Cesant&iacute;a en Edad Avanzada y Vejez, a partir del cuadrag&eacute;simo sexto d&iacute;a natural contado desde el d&iacute;a en que qued&oacute; desempleado, en los siguientes t&eacute;rminos:</p>
<ol type="a">
    <li>Si su cuenta individual tiene, al menos, tres a&ntilde;os de haber sido abierta y tiene un m&iacute;nimo de doce bimestres de cotizaci&oacute;n al instituto acreditados en dicha cuenta, podr&aacute; retirar en una exhibici&oacute;n la cantidad que resulte al equivalente a 30 d&iacute;as de su &uacute;ltimo salario base de cotizaci&oacute;n, con un l&iacute;mite de 10 veces el salario m&iacute;nimo mensual general que rija en el Distrito Federal.</li>
    <li>Si su cuenta individual tiene cinco a&ntilde;os o m&aacute;s de haber sido abierta, podr&aacute; retirar la cantidad que resulte menor entre 90 d&iacute;as de su propio salario base de cotizaci&oacute;n de las &uacute;ltimas 250 semanas o las que tuviere, o el 11.5% del saldo de la Subcuenta de Retiro, Cesant&iacute;a en Edad Avanzada y Vejez.<br />
    Las cantidades a las que se refiere este inciso se entregar&aacute;n en un m&aacute;ximo de seis mensualidades, la primera de las cuales podr&aacute; ser por un monto de 30 d&iacute;as de su &uacute;ltimo salario base de cotizaci&oacute;n a solicitud del trabajador, conforme a las reglas de car&aacute;cter general que al efecto expida la Comisi&oacute;n Nacional del Sistema de Ahorro para el Retiro. En caso de que el trabajador se reincorpore a laborar durante el plazo de entrega de los recursos, las mensualidades posteriores a su reincorporaci&oacute;n se suspender&aacute;n.<br />
    El trabajador que cumpla con los requisitos de antig&uuml;edad de la cuenta a que se refiere el primer p&aacute;rrafo de este inciso, podr&aacute; optar, en todo caso, por el beneficio se&ntilde;alado en el inciso:</li>
</ol>
<ul class="list-group-contenidos">
    <li>El derecho consignado en esta fracci&oacute;n s&oacute;lo podr&aacute;n ejercerlo los trabajadores que acrediten con los estados de cuenta correspondientes, no haber efectuado retiros durante los cinco a&ntilde;os inmediatos anteriores a la fecha de la solicitud de retiro de recursos. El derecho consignado en esta fracci&oacute;n s&oacute;lo podr&aacute;n ejercerlo los trabajadores que acrediten con los estados de cuenta correspondientes, no haber efectuado retiros durante los cinco a&ntilde;os inmediatos anteriores a la fecha de la solicitud de retiro de recursos.</li>
</ul>
<p>Por gastos de matrimonio, como ayuda para gastos de matrimonio y proveniente de la cuota social aportada por el Gobierno Federal en tu cuenta individual, tienes derecho a retirar una cantidad equivalente a 30 d&iacute;as de salario m&iacute;nimo general vigente en el Distrito Federal, a la fecha de tu matrimonio. Para tramitar este retiro, debes acreditar ante el IMSS:</p>
<ol type="">
    <li>Tener un m&iacute;nimo de 150 semanas de cotizaci&oacute;n en el Seguro de Retiro, Cesant&iacute;a en Edad Avanzada y Vejez, a la fecha del matrimonio.</li>
    <li>En caso de haber estado casado con anterioridad, deber&aacute;s comprobar la muerte de la persona que se hab&iacute;a registrado como esposa (o) ante el Instituto Mexicano del Seguro Social (IMSS) o exhibir acta de divorcio.</li>
    <li class="no_line">Que tu c&oacute;nyuge no haya sido registrado con anterioridad en el IMSS, como esposa(o). Este retiro podr&aacute;s ejercitarlo por una sola vez y no tendr&aacute;s derecho a &eacute;l por posteriores matrimonios.</li>
</ol>
<p><strong>&iquest;En qu&eacute; casos procede la devoluci&oacute;n de las aportaciones generadas al Sistema de Ahorro para el Retiro (SAR) de 1992 al tercer bimestre de 1997?</strong></p>
<p>En el otorgamiento o negativa de la pensi&oacute;n de invalidez definitiva, Cesant&iacute;a en Edad Avanzada y Vejez (&uacute;nicamente lo que es SAR 92, la subcuenta de vivienda), o en caso de la muerte del trabajador. Tambi&eacute;n cuando el trabajador deje de estar sujeto a una relaci&oacute;n de trabajo y cuente con 50 o m&aacute;s a&ntilde;os de edad, tendr&aacute; derecho a que se le haga entrega del total de los dep&oacute;sitos que se hayan hecho a su favor en el Fondo Nacional de la Vivienda (antes del SAR).</p>
<p>Se devuelve, al igual, en los casos de Incapacidad Total Permanente, de Incapacidad Parcial Permanente, cuando &eacute;sta sea del 50% o m&aacute;s, jubilaci&oacute;n, se les entregar&aacute; el total de los dep&oacute;sitos Fondo Nacional de la Vivienda (antes del SAR) con una cantidad igual adicional. Podr&aacute;n obtener la devoluci&oacute;n de las aportaciones enteradas a su favor, siempre que no haya disfrutado de un cr&eacute;dito de Instituto del Fondo Nacional de la Vivienda para los Trabajadores (Infonavit).</p>
<p><strong>&iquest;En qu&eacute; casos procede que me devuelvan el Fondo de Ahorro de Infonavit y una cantidad adicional a los dep&oacute;sitos realizados?</strong></p>
<p>En los casos de Incapacidad Total Permanente, de Incapacidad Parcial Permanente, cuando &eacute;sta sea del 50% o m&aacute;s. Tambi&eacute;n en los de Invalidez Definitiva, en los t&eacute;rminos de la Ley del Seguro Social, de jubilaci&oacute;n o de muerte del trabajador, el Infonavit deber&aacute; entregar el total de los dep&oacute;sitos constituidos, al trabajador o a sus beneficiarios, con una cantidad adicional a dichos dep&oacute;sitos.</p>
<p><strong>&iquest;Cu&aacute;ndo no procede la cantidad adicional a los dep&oacute;sitos constituidos en el fondo de ahorro?</strong></p>
<p>Cuando el trabajador deje de estar sujeto a una relaci&oacute;n de trabajo y cuente con 50 o m&aacute;s a&ntilde;os de edad, tendr&aacute; derecho a que se le haga entrega del total de los dep&oacute;sitos que se hubieran hecho a su favor.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
