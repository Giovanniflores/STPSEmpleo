<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Derechos laborales al t&eacute;rmino de la vida laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Me acaban de despedir, &iquest;a qu&eacute; tengo derecho? Tienes derecho a demandar, ante la junta ya sea Local o Federal de Conciliaci&oacute;n y Arbitraje, de acuerdo a la actividad que desarrolle la empresa, de manera optativa: la reinstalaci&oacute;n a su empleo en los mismos t&eacute;rminos y condiciones en que lo ven&iacute;a desempe&ntilde;ando. Es decir, en el mismo puesto o categor&iacute;a; horario y lugar de adscripci&oacute;n que ten&iacute;a hasta antes de ser despedido injustificadamente; el pago de los salarios vencidos, con los incrementos legales y contractuales  que se otorguen a los mismos durante el juicio laboral, desde la fecha del injustificado despido, hasta por un periodo m&aacute;ximo de 12 meses. Si al t&eacute;rmino de dicho periodo, a&uacute;n no ha concluido el procedimiento o no se ha dado cumplimiento al fallo respectivo, se le pagar&aacute; tambi&eacute;n el importe de los intereses que se generen sobre la base de 15 meses de salario, a raz&oacute;n del 2% mensual capitalizable, al momento del pago y hasta el cumplimiento del fallo, por precauci&oacute;n, el pago de la prima de antig&uuml;edad, a raz&oacute;n de 12 d&iacute;as de salario por cada a&ntilde;o de servicios prestados, el pago de vacaciones , prima vacacional, aguinaldo, legales o contractuales que le correspondan, en su caso, salarios devengados y no cubiertos, el reconocimiento de la antig&uuml;edad desde la fecha del despido hasta que se reinstale f&iacute;sica y materialmente y hasta que sea cumplimente el fallo, la continuaci&oacute;n en las aportaciones  al Sistema de Ahorro para el Retiro (SAR), Instituto Mexicano del Seguro Social (IMSS) e Instituto del Fondo Nacional de la Vivienda para los Trabajadores (Infonavit), a partir  de la fecha del despido y hasta que sea cumplimentado el fallo, los ascensos escalafonarios que se otorguen durante el juicio en el puesto que desempe&ntilde;aba el trabajador con las mejoras sal&aacute;riales legales que se generen.
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
          <li><a href="<c:url value="/jsp/empleo/candidatos/alTerminoVidaLaboral.jsp"/>">Al término de la vida laboral</a></li>
          <li class="active">Derechos laborales</li>
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
			<h2 class="titulosh2">Derechos laborales</h2>
		
	        <p><strong>Me acaban de despedir, &iquest;a qu&eacute; tengo derecho?</strong></h3>
<p>Tienes derecho a demandar, ante la junta ya sea Local o Federal de Conciliaci&oacute;n y Arbitraje, de acuerdo a la actividad que desarrolle la empresa, de manera optativa: la reinstalaci&oacute;n a su empleo en los mismos t&eacute;rminos y condiciones en que lo ven&iacute;a desempe&ntilde;ando. Es decir, en el mismo puesto o categor&iacute;a; horario y lugar de adscripci&oacute;n que ten&iacute;a hasta antes de ser despedido injustificadamente; el pago de los salarios vencidos, con los incrementos legales y contractuales  que se otorguen a los mismos durante el juicio laboral, desde la fecha del injustificado despido, hasta por un periodo m&aacute;ximo de 12 meses. Si al t&eacute;rmino de dicho periodo, a&uacute;n no ha concluido el procedimiento o no se ha dado cumplimiento al fallo respectivo, se le pagar&aacute; tambi&eacute;n el importe de los intereses que se generen sobre la base de 15 meses de salario, a raz&oacute;n del 2% mensual capitalizable, al momento del pago y hasta el cumplimiento del fallo, por precauci&oacute;n, el pago de la prima de antig&uuml;edad, a raz&oacute;n de 12 d&iacute;as de salario por cada a&ntilde;o de servicios prestados, el pago de vacaciones , prima vacacional, aguinaldo, legales o contractuales que le correspondan, en su caso, salarios devengados y no cubiertos, el reconocimiento de la antig&uuml;edad desde la fecha del despido hasta que se reinstale f&iacute;sica y materialmente y hasta que sea cumplimente el fallo, la continuaci&oacute;n en las aportaciones  al Sistema de Ahorro para el Retiro (SAR), Instituto Mexicano del Seguro Social (IMSS) e Instituto del Fondo Nacional de la Vivienda para los Trabajadores (Infonavit), a partir  de la fecha del despido y hasta que sea cumplimentado el fallo, los ascensos escalafonarios que se otorguen durante el juicio en el puesto que desempe&ntilde;aba el trabajador con las mejoras sal&aacute;riales legales que se generen.</p>
<p>O bien la indemnizaci&oacute;n constitucional que se integra con el importe de tres meses de salario integrado, as&iacute; como la prima de antig&uuml;edad consistente en 12 d&iacute;as de salario, por cada a&ntilde;o de servicios prestados. Si su salario excede del doble del salario m&iacute;nimo, se tomar&aacute; como base &eacute;ste para el c&aacute;lculo de dicha prestaci&oacute;n, el pago de partes proporcionales y adeudadas de vacaciones, prima vacacional, aguinaldo, caja de ahorro, vales de despensa, salarios devengados y salarios vencidos desde la fecha del injustificado despido, hasta por un periodo m&aacute;ximo de 12 meses, si al t&eacute;rmino de dicho periodo a&uacute;n no ha concluido el procedimiento o no se ha dado cumplimiento al fallo respectivo, se le pagar&aacute; tambi&eacute;n el importe de los intereses que se generen sobre la base de 15 meses de salario, a raz&oacute;n del 2% mensual capitalizable al momento del pago lo anterior de conformidad con la Ley Federal del Trabajo y la informaci&oacute;n que se desprende de sus recibos de pago de salario.</p>
<p><strong>Mi patr&oacute;n me despidi&oacute; y me amenaza con boletinarme en caso de que lo demande, &iquest;es correcta la actitud de mi patr&oacute;n?</strong></h3>
<p>No, porque la legislaci&oacute;n de la materia determina que queda prohibido que los patrones boletinen a los trabajadores despedidos con el fin de que no sean contratados.</p>
<p><strong>Falt&eacute; cuatro veces a mi trabajo y mi patr&oacute;n me despidi&oacute;, &iquest;qu&eacute; puedo hacer?</strong></h3>
<p>De acuerdo a la Ley Federal del Trabajo, en su art&iacute;culo 47 fracci&oacute;n X, el patr&oacute;n tiene la facultad de rescindirte, sin responsabilidad para &eacute;l, en el momento en que tengas m&aacute;s de tres faltas de asistencia en 30 d&iacute;as, sin su permiso y sin causa justificada. Los 30 d&iacute;as a que se refiere la ley, deben ser d&iacute;as de calendario, no siendo necesario que los 30 d&iacute;as correspondan al mismo mes, pues muy bien pueden ser los dos &uacute;ltimos d&iacute;as de un mes y los dos primeros d&iacute;as del mes siguiente, para lo cual deber&aacute; darte un aviso de rescisi&oacute;n o presentar dicho aviso en la Junta de Conciliaci&oacute;n y Arbitraje, teniendo derecho al pago de tus partes proporcionales de: aguinaldo, vacaciones y prima vacacional y al pago de la prima de antig&uuml;edad, que consistir&aacute; en el pago de 12 d&iacute;as de salario por cada a&ntilde;o de servicios prestados, tomando como base para el pago de esta prestaci&oacute;n el doble del salario m&iacute;nimo, en el caso de que el trabajador perciba un salario superior a &eacute;ste. Fundamento legal art&iacute;culos 89, 76, 78, 162 de la Ley Federal del Trabajo.</p>
<p><strong>&iquest;Qu&eacute; es la Indemnizaci&oacute;n Constitucional por despido injustificado?</strong></h3>
<p>La Indemnizaci&oacute;n Constitucional s&oacute;lo implica el pago de tres meses de salario integrado, salarios ca&iacute;dos y dem&aacute;s prestaciones que hubiere devengado o que le otorgue expresamente la ley o los contratos de trabajo.</p>
<p><strong>&iquest;Cu&aacute;l es el tiempo que tengo para demandar a mi patr&oacute;n por un despido injustificado?</strong></h3>
<p>Cuentas con dos meses para demandar a tu patr&oacute;n por despido injustificado, a partir del d&iacute;a siguiente en que ocurri&oacute; el mismo.</p>
<p><strong>&iquest;A qu&eacute; tengo derecho si renuncio voluntariamente a mi trabajo?</strong></h3>
<p>Al pago de las partes proporcionales de: aguinaldo, vacaciones y prima vacacional y al pago de la prima de antig&uuml;edad, en el caso de contar con una antig&uuml;edad de quince a&ntilde;os de servicios, por lo menos, y consistir&aacute; en el pago de 12 d&iacute;as de salario por cada a&ntilde;o de servicios prestados, tomando como base el doble del salario m&iacute;nimo, si el trabajador percibe un salario superior a &eacute;ste.</p>
<p><strong>Renunci&eacute; a mi trabajo, &iquest;qu&eacute; me deben pagar en mi liquidaci&oacute;n?</strong></h3>
<p>El pago de las partes proporcionales de aguinaldo, vacaciones y prima vacacional. En el caso de que cuentes con una antig&uuml;edad de 15 a&ntilde;os de servicios, por lo menos, al pago de la prima de antig&uuml;edad, que consistir&aacute; en el pago de 12 d&iacute;as de salario por cada a&ntilde;o de servicios prestados, tomando como base para el pago de esta prestaci&oacute;n el doble del salario m&iacute;nimo, en el caso de que el trabajador perciba un salario superior a &eacute;ste. A las prestaciones generadas y no pagadas: fondo de ahorro, salarios devengados, etc., y a las prestaciones que se deriven del contrato de trabajo, si es que existe.</p>
<p><strong>Me retir&eacute; voluntariamente de mi trabajo, &iquest;cu&aacute;nto tiempo tengo para demandar el pago de mis prestaciones?</strong></h3>
<p>Un a&ntilde;o, contado a partir del d&iacute;a siguiente en que presentaste tu renuncia.</p>
<p><strong>Cuento con m&aacute;s de 15 a&ntilde;os de servicio, &iquest;a qu&eacute; prestaciones tengo derecho si renuncio voluntariamente a mi trabajo?</strong></h3>
<p>Por separaci&oacute;n voluntaria, el trabajador tendr&aacute; derecho a que el patr&oacute;n le pague la prima de antig&uuml;edad a raz&oacute;n de 12 d&iacute;as de salario por cada a&ntilde;o de servicios prestados, con un salario tope del doble del salario m&iacute;nimo general para el Distrito Federal y las partes proporcionales de aguinaldo, vacaciones y prima vacacional. De neg&aacute;rsele el pago, se deber&aacute; iniciar un juicio ante la autoridad laboral competente, y para ello, se cuenta con el t&eacute;rmino de un a&ntilde;o a partir del d&iacute;a siguiente de la separaci&oacute;n. Si pasa ese t&eacute;rmino y el trabajador no demanda, &eacute;ste perder&aacute; el derecho. En caso de la aplicaci&oacute;n de alg&uacute;n contrato colectivo de trabajo o contrato ley, se deber&aacute; demandar las prestaciones que se deriven de ese contrato.</p>
<p><strong>&iquest;Cu&aacute;ndo procede el pago de 20 d&iacute;as por cada a&ntilde;o de servicios prestados?</strong></h3>
<p>La indemnizaci&oacute;n de 20 d&iacute;as de salario por a&ntilde;o de servicios, procede &uacute;nicamente en los cinco supuestos que se&ntilde;ala la Legislaci&oacute;n laboral y que a continuaci&oacute;n se mencionan:</p>
<ol type="a">
    <li>Trat&aacute;ndose de los trabajadores de confianza, dom&eacute;sticos y eventuales, con una antig&uuml;edad menor de un a&ntilde;o y aquellos trabajadores que dada su estrecha relaci&oacute;n con el empleador, la autoridad considera que no ser&iacute;a posible el desarrollo normal de la relaci&oacute;n de trabajo, y el patr&oacute;n tenga la obligaci&oacute;n impuesta por la autoridad (Junta de Conciliaci&oacute;n y Arbitraje) de reinstalar al trabajador, en estos casos el patr&oacute;n queda eximido o liberado de dicha obligaci&oacute;n mediante el pago de una indemnizaci&oacute;n especial consistente, adem&aacute;s de los tres meses de salario y de los salarios vencidos, en el pago a los trabajadores mencionados cantidades adicionales, seg&uacute;n la duraci&oacute;n de la relaci&oacute;n laboral y 20 d&iacute;as por cada a&ntilde;o de servicios.</li>
    <li>Por rescisi&oacute;n de la relaci&oacute;n de trabajo por causa imputable al patr&oacute;n, es decir, cuando presenten motivos por los cuales un trabajador puede romper el v&iacute;nculo laboral sin que esto le genere consecuencias adversas, dichos motivos se encuentran expresamente se&ntilde;alados en la Ley Federal del Trabajo (art&iacute;culo 51). En estos casos, el trabajador podr&aacute; separarse y exigir al patr&oacute;n que lo indemnice con tres meses de salario, el pago de los salarios vencidos, m&aacute;s una cantidad adicional, seg&uacute;n la duraci&oacute;n de la relaci&oacute;n laboral y 20 d&iacute;as por cada a&ntilde;o de servicios.</li>
    <li>Cuando se presente una situaci&oacute;n de suspensi&oacute;n temporal de las relaciones colectivas de trabajo, con base en alguna de las causas previamente autorizadas de las enunciadas en el art&iacute;culo 427 de nuestra Ley Laboral, la autoridad determina, a su vez, el pago de una indemnizaci&oacute;n en favor de los trabajadores, la cual no puede exceder del importe de un mes de salario. Una vez que desaparezca la causa, si el patr&oacute;n se negara a reanudar los trabajos, los trabajadores podr&aacute;n exigir la indemnizaci&oacute;n especial, que como ya se se&ntilde;al&oacute;, contiene entre otros elementos, el pago de 20 d&iacute;as por a&ntilde;o de servicios.</li>
    <li>En los casos de reducci&oacute;n de personal, previa autorizaci&oacute;n de la Junta, por motivo de implantaci&oacute;n de maquinaria o procedimientos de trabajos nuevos, la indemnizaci&oacute;n que procede en este supuesto espec&iacute;fico para los trabajadores que resulten afectados con el reajuste, es de cuatro meses de salario, 20 d&iacute;as por a&ntilde;o de servicios prestados o la cantidad establecida en los contratos si fuese mayor, as&iacute; como la prima de antig&uuml;edad (art&iacute;culo 439).</li>
    <li>Cuando el patr&oacute;n se niega a someter sus diferencias al arbitraje o a acatar el fallo de la Junta, siempre y cuando en el conflicto planteado no se demande la Indemnizaci&oacute;n Constitucional o la reinstalaci&oacute;n, los trabajadores podr&aacute;n exigir la indemnizaci&oacute;n especial (art&iacute;culo 947 de la Ley Federal del Trabajo).</li>
</ol>
<p><strong>&iquest;Cu&aacute;ndo procede el pago de la prima de antig&uuml;edad?</strong></h3>
<p>Cuando ocurra lo siguiente:</p>
<ol>
    <li>Por despido del trabajador, separaci&oacute;n justificada del trabajador y separaci&oacute;n voluntaria del trabajador, pero con la condici&oacute;n de que tenga, por lo menos, 15 a&ntilde;os de antig&uuml;edad en la empresa.</li>
    <li>Por muerte del trabajador o incapacidad del trabajador que le inhabilite para seguir prestando sus servicios.</li>
    <li>Terminaci&oacute;n colectiva de las relaciones de trabajo, fuerza mayor o caso fortuito que produzca el cierre definitivo de la empresa.</li>
</ol>
<p>Otras causas: incosteabilidad de la explotaci&oacute;n, agotamiento de la materia objeto de la industria extractiva, concurso o quiebra del patr&oacute;n, implantaci&oacute;n de maquinaria nueva o de nuevos m&eacute;todos de trabajo.</p>
<p>La prima de antig&uuml;edad consiste en 12 d&iacute;as de salario, por cada a&ntilde;o de servicios prestados, o la parte proporcional que corresponda. La base salarial que se debe utilizar es el salario en efectivo, por cuota diaria, y el tope m&aacute;ximo salarial ser&aacute; el equivalente a dos veces el salario m&iacute;nimo general del &aacute;rea geogr&aacute;fica correspondiente.</p>
<p><strong>Trabaj&eacute; en un organismo descentralizado del Gobierno Federal y me pagaban la prima quinquenal, a partir de este a&ntilde;o present&eacute; mi renuncia para poder jubilarme, &iquest;tengo derecho a la prima de antig&uuml;edad?</strong></h3>
<p>Claro que s&iacute;, porque la prima quinquenal y la prima de antig&uuml;edad son de naturaleza distinta, de acuerdo al criterio sustentado por la Suprema Corte de Justicia de la Naci&oacute;n (SCJN).</p>
<p><strong>Mi patr&oacute;n no me ha pagado los salarios que he generado desde la fecha de mi ingreso a la empresa, &iquest;qu&eacute; puedo hacer?</strong></h3>
<p>Debes solicitar dicho pago por escrito, y en caso de no obtener ninguna respuesta, puedes acudir a la procuradur&iacute;a que corresponda a efecto de demandar el pago de los salarios devengados.</p>
<p><strong>El IMSS me pension&oacute; por invalidez y ya no puedo trabajar, &iquest;a qu&eacute; tengo derecho por parte de mi patr&oacute;n?</strong></h3>
<p>Tienes derecho a que se te pague un mes de salario y 12 d&iacute;as por cada a&ntilde;o de servicios prestados, independientemente de las prestaciones que te correspondan, es decir, vacaciones, prima vacacional, aguinaldo y dem&aacute;s prestaciones generadas y no pagadas.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
