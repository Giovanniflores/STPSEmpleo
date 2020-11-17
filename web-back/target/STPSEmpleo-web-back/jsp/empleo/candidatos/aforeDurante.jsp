<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">AFORE durante la vida laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute> 
	<jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Si ya est&aacute;s afiliado a una Afore, puedes cambiar tu decisi&oacute;n despu&eacute;s de un a&ntilde;o de haberla seleccionado. En caso de que &eacute;sta suba sus comisiones, tambi&eacute;n podr&aacute;s cambiarte sin estar limitado al periodo anual. En caso de que baje sus comisiones quedar&aacute;s limitado al cambio anual.
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
		
	        <p><strong>&iquest;Puedo cambiar de afore? &iquest;Cada cu&aacute;nto?</strong></p>

<p>Si ya est&aacute;s afiliado a una Afore, puedes cambiar tu decisi&oacute;n despu&eacute;s de un a&ntilde;o de haberla seleccionado. En caso de que &eacute;sta suba sus comisiones, tambi&eacute;n podr&aacute;s cambiarte sin estar limitado al periodo anual. En caso de que baje sus comisiones quedar&aacute;s limitado al cambio anual.</p>
<p><strong>&iquest;Cu&aacute;l es el objeto de las Administradoras del Fondo para el Retiro?</strong></p>
<p>Las administradoras, tendr&aacute;n como objeto:</p>
<p><strong>I.</strong> Abrir, administrar y operar cuentas individuales de los trabajadores.</p>
<p>Trat&aacute;ndose de trabajadores afiliados, sus cuentas individuales se sujetar&aacute;n a las disposiciones de las leyes de seguridad social aplicables y sus reglamentos, as&iacute; como a las de este ordenamiento. Para el caso de las subcuentas de vivienda, las administradoras deber&aacute;n individualizar las aportaciones y rendimientos correspondientes con base en la informaci&oacute;n que les proporcionen los institutos de seguridad social. La canalizaci&oacute;n de los recursos de dichas subcuentas se har&aacute; en los t&eacute;rminos previstos por sus propias leyes;  Fracci&oacute;n reformada.</p>
<p><strong>I bis.</strong> Abrir, administrar y operar cuentas individuales, con sus respectivas subcuentas en las que se reciban recursos de los trabajadores inscritos en el Instituto de Seguridad y Servicios Sociales de los Trabajadores del Estado (ISSSTE), en los t&eacute;rminos previstos en el art&iacute;culo 74 bis de esta ley y conforme a las reglas de car&aacute;cter general que al efecto expida la comisi&oacute;n;</p>
<p><strong>I ter.</strong> Abrir, administrar y operar cuentas individuales, en las que se reciban recursos de los trabajadores no afiliados, o que no se encuentren inscritos en el ISSSTE, que as&iacute; lo deseen, destinados a la contrataci&oacute;n de rentas vitalicias, seguros de sobrevivencia o retiros programados en los t&eacute;rminos previstos en el art&iacute;culo 74 ter de esta ley y conforme a las reglas de car&aacute;cter general que al efecto expida la comisi&oacute;n;</p>
<p><strong>I qu&aacute;ter.</strong> Abrir, administrar y operar cuentas individuales en las que se reciban recursos de los trabajadores no afiliados de las dependencias o entidades p&uacute;blicas de car&aacute;cter estatal o municipal cuando proceda, en los t&eacute;rminos previstos en el art&iacute;culo 74 quinquies de esta ley y conforme a las reglas de car&aacute;cter general que al efecto expida la comisi&oacute;n;</p>
<p>Las Afores atender&aacute;n, exclusivamente, al inter&eacute;s de los trabajadores y asegurar&aacute;n que todas las operaciones que efect&uacute;en para la inversi&oacute;n de los recursos de dichos trabajadores se realicen con ese objetivo.</p>
<p><strong>&iquest;A qu&eacute; est&aacute; obligada la AFORE?</strong></p>
<ul class="list-group-contenidos">
    <li>Se encuentra obligada  a recibir las cuotas y aportaciones de seguridad social correspondientes a las cuentas individuales de conformidad con las leyes de seguridad social, as&iacute; como las aportaciones voluntarias y complementarias de retiro, y los dem&aacute;s recursos que en t&eacute;rminos de esta ley puedan ser recibidos en las cuentas individuales y administrar los recursos de los fondos de previsi&oacute;n social.</li>
    <li>Individualizar las cuotas y aportaciones destinadas a las cuentas individuales, as&iacute; como los rendimientos derivados de la inversi&oacute;n de las mismas.</li>
    <li>Enviar, por lo menos, tres veces al a&ntilde;o de forma cuatrimestral, al domicilio que indiquen los trabajadores, sus estados de cuenta y dem&aacute;s informaci&oacute;n sobre sus cuentas individuales conforme a lo dispuesto en el art&iacute;culo 37-A de esta ley. Asimismo, se deber&aacute;n establecer servicios de informaci&oacute;n, v&iacute;a internet, y atenci&oacute;n al p&uacute;blico personalizado.</li>
    <li>Prestar servicios de administraci&oacute;n a las sociedades de inversi&oacute;n.</li>
    <li>Prestar servicios de distribuci&oacute;n y recompra de acciones representativas del capital de las sociedades de inversi&oacute;n que administren.</li>
    <li>Operar y pagar, bajo las modalidades que la comisi&oacute;n autorice, los retiros programados.</li>
    <li>Pagar los retiros parciales con cargo a las cuentas individuales de los trabajadores en los t&eacute;rminos de las leyes de seguridad social.</li>
    <li>Entregar los recursos a las instituciones de seguros que el trabajador o sus beneficiarios hayan elegido, para la contrataci&oacute;n de rentas vitalicias o del seguro de sobrevivencia.</li>
    <li>Funcionar como entidades financieras autorizadas, en t&eacute;rminos de lo dispuesto por la Ley del Instituto de Seguridad y Servicios Sociales de los Trabajadores del Estado u otros ordenamientos; y</li>
    <li class="no_line">Los an&aacute;logos o conexos a los anteriores que sean autorizados por la Junta de Gobierno.</li>
</ul>
<p><strong>&iquest;C&oacute;mo se integra la cuenta individual del Sistema de Ahorro para el Retiro (SAR  de los trabajadores que se encuentran inscritos al IMSS?</strong></p>
<p>Los trabajadores afiliados tienen derecho a la apertura de su cuenta individual de conformidad con la Ley del Seguro Social, en la administradora de su elecci&oacute;n. Para abrir las cuentas individuales, se les asignar&aacute; una clave de identificaci&oacute;n por el Instituto Mexicano del Seguro Social.</p>
<p>Las cuentas individuales de los trabajadores afiliados se integrar&aacute;n por las siguientes subcuentas:</p>
<ol type="I">
    <li>Retiro, cesant&iacute;a en edad avanzada y vejez;</li>
    <li>Vivienda;</li>
    <li>Aportaciones Voluntarias, y</li>
    <li class="no_line">Aportaciones Complementarias de Retiro.</li>
    <p>Estas subcuentas se regir&aacute;n por la Ley de los Sistemas de Ahorro para el Retiro. Asimismo, la subcuenta referida en la fracci&oacute;n I se regir&aacute; por lo dispuesto en la Ley del Seguro Social y la prevista en la fracci&oacute;n II se regir&aacute; por lo dispuesto en la Ley del Instituto del Fondo Nacional de la Vivienda para los Trabajadores (Fonacot).</p>
    <p>Asimismo, los trabajadores afiliados podr&aacute;n solicitar, a su administradora, que se traspasen sus cuentas individuales que se hayan abierto conforme al r&eacute;gimen previsto en la Ley del Seguro Social de 1973.</p>
    <p>Las aportaciones complementarias de retiro s&oacute;lo podr&aacute;n retirarse cuando el trabajador afiliado tenga derecho a disponer de las aportaciones obligatorias, ya sea para complementar, cuando as&iacute; lo solicite el trabajador, los recursos destinados al pago de su pensi&oacute;n, o bien para recibirlas en una sola exhibici&oacute;n.</p>
    <p>Las administradoras estar&aacute;n obligadas a abrir la cuenta individual o a aceptar el traspaso de dicha cuenta, de aquellos trabajadores afiliados que cumpliendo con las disposiciones aplicables, soliciten su apertura de cuenta. En ning&uacute;n caso podr&aacute;n hacer discriminaci&oacute;n de trabajadores.</p>
    <p><strong>&iquest;C&oacute;mo se integra la cuenta individual del SAR de los trabajadores que se encuentran inscritos al ISSSTE?</strong></p>
    <p>Los trabajadores inscritos en el Instituto de Seguridad y Servicios Sociales de los Trabajadores del Estado, tendr&aacute;n derecho a la apertura de su cuenta individual en la administradora de su elecci&oacute;n. La administradora elegida tendr&aacute; a su cargo la administraci&oacute;n de la cuenta individual y, cuando el trabajador as&iacute; lo decida, la inversi&oacute;n de la totalidad de los recursos acumulados en la subcuenta de ahorro para el retiro y de las aportaciones voluntarias en las sociedades de inversi&oacute;n.</p>
    <p>Para abrir estas cuentas individuales o recibir el traspaso de las mismas, se asignar&aacute; a los trabajadores una clave de identificaci&oacute;n, de conformidad con las disposiciones de car&aacute;cter general que al efecto expida la comisi&oacute;n.</p>
    <p>Las cuentas individuales de los trabajadores a que se refiere este art&iacute;culo estar&aacute;n integradas por las siguientes subcuentas:</p>
    <ol type="I">
        <li>Subcuenta de ahorro para el retiro</li>
        <li>Subcuenta del fondo de la vivienda</li>
        <li class="no_line">Subcuenta de aportaciones voluntarias.</li>
    </ol>
    <p>Las subcuentas referidas en las fracciones I y II son las previstas en la Ley del Instituto de Seguridad y Servicios Sociales de los Trabajadores del Estado, por lo que se regir&aacute;n por lo dispuesto en dicha ley. La subcuenta referida en la fracci&oacute;n III se regir&aacute; por lo dispuesto en la presente ley.</p>
    <p><strong>&iquest;Puedo cambiar de Afore? &iquest;Cada cu&aacute;nto?</strong></p>
    <p>Los trabajadores tendr&aacute;n derecho a traspasar su cuenta individual de una administradora a otra una vez transcurrido un a&ntilde;o, contado a partir de que el trabajador se registr&oacute; o de la &uacute;ltima ocasi&oacute;n en que haya ejercitado su derecho al traspaso. Podr&aacute; hacerlo antes del a&ntilde;o, cuando traspase su cuenta individual a una administradora cuyas sociedades de inversi&oacute;n hubieren registrado un mayor Rendimiento Neto, en el per&iacute;odo de c&aacute;lculo inmediato anterior.</p>
    <p>Los trabajadores que ejerzan su derecho de traspasar su cuenta individual de una administradora a otra que haya registrado un Rendimiento Neto mayor, deber&aacute;n permanecer al menos 12 meses en la &uacute;ltima administradora elegida.</p>
</ol>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
