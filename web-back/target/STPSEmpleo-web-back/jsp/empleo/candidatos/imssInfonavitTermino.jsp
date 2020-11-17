<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">IMSS e Infonavit al t&eacute;rmino de la vida laboral</jsp:attribute>
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
          <li><a href="<c:url value="/jsp/empleo/candidatos/alTerminoVidaLaboral.jsp"/>">Al término de la vida laboral</a></li>
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
		
	        <p><strong>&iquest;Debo de agotar el recurso de inconformidad porque me pensionaron por riesgo de trabajo y no estoy conforme?</strong></p>
<p>No, porque genera un menoscabo a los derechos, cuya tutela jurisdiccional puede solicitarse ante la Junta Federal de Conciliaci&oacute;n y Arbitraje.</p>
<p>Fundamento legal: contradicci&oacute;n de tesis 35/2000 suscitada entre el Primer Tribunal Colegiado en Materia de Trabajo del Primer Circuito y el Primer Tribunal Colegiado en materia Penal y Civil del Cuarto Circuito.</p>
<p><strong>Tengo una pensi&oacute;n de Cesant&iacute;a en Edad Avanzada, &iquest;puedo solicitar mi pensi&oacute;n por vejez o por invalidez?</strong></p>
<p>No, la Ley del Seguro Social vigente se&ntilde;ala que el pensionado que se encuentre disfrutando de una pensi&oacute;n de Cesant&iacute;a en Edad Avanzada, no tendr&aacute; derecho a una posterior de vejez o de invalidez, ya que estas son incompatibles.</p>
<p><strong>&iquest;C&oacute;mo obtengo una pensi&oacute;n de Vejez?</strong></p>
<p>El asegurado debe reunir los siguientes requisitos: haber cumplido 65 a&ntilde;os; quedar privado de un trabajo remunerado y tener reconocido, por el Instituto Mexicano del Seguro Social (IMSS), un m&iacute;nimo de 500 cotizaciones semanales conforme a la ley anterior, o 1,250 semanas conforme a la ley vigente.</p>
<p>El derecho al disfrute de la pensi&oacute;n de vejez comenzar&aacute; a partir del d&iacute;a en que el asegurado cumpla todos los requisitos anteriores. No se tendr&aacute; derecho a disfrutar de una pensi&oacute;n de vejez cuando el asegurado tenga 65 a&ntilde;os y no re&uacute;na las 500 semanas, de acuerdo a la Ley del Seguro Social derogada; o 1,250 semanas, de acuerdo con la ley vigente. Por lo que podr&aacute; retirar el saldo de su cuenta individual, en una sola exhibici&oacute;n o seguir cotizando hasta cubrir las semanas necesarias para que opere su pensi&oacute;n.</p>
<p>En este caso, si el asegurado tiene cotizadas un m&iacute;nimo de 750 semanas, tendr&aacute; derecho a las prestaciones en especie del seguro de enfermedades y maternidad.</p>
<p><strong>&iquest;A partir de qu&eacute; fecha inicia el goce de la pensi&oacute;n de Cesant&iacute;a en Edad Avanzada y Vejez conforme a la nueva Ley del Seguro Social?</strong></p>
<p>Trat&aacute;ndose de la pensi&oacute;n de Cesant&iacute;a en Edad Avanzada, desde el d&iacute;a en que el asegurado cumpla con la edad (de 60 a 64 a&ntilde;os) y cotizaciones semanales requeridas (1,250), siempre que solicites el otorgamiento de dicha pensi&oacute;n y acredites haber quedado privado de trabajo; si no fue recibido en el instituto el aviso de baja.</p>
<p>Respecto a la pensi&oacute;n de Vejez, se otorgar&aacute; previa solicitud del asegurado y se le cubrir&aacute; a partir de la fecha en que haya dejado de trabajar, previo cumplimiento de la edad (65 a&ntilde;os) y cotizaciones (1,250).</p>
<p><strong>&iquest;Qu&eacute; opciones tengo para disfrutar de alguna pensi&oacute;n en el Seguro de Retiro, Cesant&iacute;a en Edad Avanzada y Vejez?</strong></p>
<p>Contratar con la instituci&oacute;n de seguros que elijas una renta vitalicia, la cual obtendr&aacute;s cuando la aseguradora, a cambio de recibir los recursos acumulados en la cuenta individual, se obliga a pagar peri&oacute;dicamente durante la vida del pensionado. O bien, retiros programados, que es la modalidad de obtener una pensi&oacute;n fraccionando el monto total de los recursos de la cuenta individual, para la cual se tomar&aacute; en cuenta la esperanza de vida de los pensionados, as&iacute; como los rendimientos previsibles de los saldos.</p>
<p><strong>&iquest;Qu&eacute; es pensi&oacute;n definitiva de Invalidez conforme a la ley vigente del IMSS?</strong></p>
<p>Es pensi&oacute;n definitiva la que corresponde al estado de invalidez que se estima de naturaleza permanente. Se requiere que al declararse &eacute;sta, el asegurado tenga acreditado el pago de 200  semanas de cotizaci&oacute;n. En el caso de que el dictamen respectivo determine el 75% o m&aacute;s de invalidez, s&oacute;lo se requerir&aacute; que tenga acreditadas 150 semanas de cotizaci&oacute;n.</p>
<p><strong>&iquest;Qu&eacute; requisitos se necesitan para obtener una pensi&oacute;n de Invalidez de acuerdo a la Ley del Seguro Social?</strong></p>
<p>Para los efectos de la ley, existe invalidez cuando el asegurado se encuentre imposibilitado para procurarse, mediante un trabajo igual, una remuneraci&oacute;n superior al 50% de su remuneraci&oacute;n habitual percibida durante el &uacute;ltimo a&ntilde;o de trabajo y que esa imposibilidad derive de una enfermedad o accidente no profesionales. La declaraci&oacute;n de invalidez deber&aacute; ser realizada por el Instituto Mexicano del Seguro Social.</p>
<p>No se tiene derecho a disfrutar de pensi&oacute;n de invalidez cuando el asegurado:&nbsp;</p>
<ol type="">
    <li>Por s&iacute; mismo, o de acuerdo con otra persona, se haya provocado intencionalmente la invalidez.</li>
    <li>Resulte responsable del delito intencional que origin&oacute; la invalidez.</li>
    <li>Padezca un estado de invalidez anterior a su afiliaci&oacute;n al r&eacute;gimen del Seguro Social.</li>
</ol>
<p><strong>Dej&eacute; de laborar hace un par de d&iacute;as, pero quiero continuar inscrito en el IMSS, &iquest;qu&eacute; debo hacer?</strong></p>
<p>El trabajador tiene el derecho a continuar, voluntariamente, en el r&eacute;gimen obligatorio en los seguros conjuntos de invalidez y vida. As&iacute; como en el retiro, Cesant&iacute;a en Edad Avanzada y Vejez, siempre y cuando al ser dado de baja o quedarse sin trabajo, cuente con un m&iacute;nimo de 52 cotizaciones semanales acreditadas en dicho r&eacute;gimen, debiendo quedar inscrito con el &uacute;ltimo salario o superior al que ten&iacute;a en el momento de la baja. El asegurado cubrir&aacute; las cuotas que le correspondan por mensualidades adelantadas.</p>
<p><strong>Me acabo de quedar sin trabajo, &iquest;qu&eacute; tiempo tengo para recibir la atenci&oacute;n m&eacute;dica del Instituto Mexicano del Seguro Social?</strong></p>
<p>El asegurado que quede privado de trabajo remunerado, pero que haya cubierto inmediatamente antes de tal privaci&oacute;n, un m&iacute;nimo de ocho cotizaciones semanales ininterrumpidas, conservar&aacute; durante las ocho semanas posteriores a la desocupaci&oacute;n el derecho a recibir exclusivamente la asistencia m&eacute;dica, maternidad, quir&uacute;rgica, farmac&eacute;utica y hospitalaria que sea necesaria. De este mismo derecho, disfrutar&aacute;n sus beneficiarios.</p>
<p><strong>Mi esposo acaba de morir, &iquest;qu&eacute; tengo que hacer para que me paguen sus prestaciones laborales?</strong></p>
<p>Debes hacer valer tus derechos por medio de un juicio laboral. Dicho juicio es regulado por la Ley Federal del Trabajo como un procedimiento especial, ya que se debe tramitar y resolver, en una sola audiencia, dada la naturaleza del conflicto planteado. En la pr&aacute;ctica laboral, se le denomina &quot;Designaci&oacute;n de Beneficiarios&quot;.</p>
<p><strong>Mi esposo acaba de morir, dej&oacute; de laborar hace dos a&ntilde;os, siempre estuvo asegurado por el Instituto Mexicano del Seguro Social y cotiz&oacute; 20 a&ntilde;os, &iquest;tengo derecho a la pensi&oacute;n de  Viudez y Orfandad para mis dos hijos?</strong></p>
<p>Tiene derecho a las pensiones, siempre y cuando se encuentre dentro de la conservaci&oacute;n de derechos, que equivale por un per&iacute;odo igual a la cuarta parte del tiempo cubierto por las cotizaciones semanales, contado a partir de la fecha de su baja.</p>
<p>Asimismo, para que tenga derecho a la pensi&oacute;n ya sea de Viudez o de Orfandad, al fallecer el asegurado debi&oacute; contar con el pago al instituto de un m&iacute;nimo de 150 cotizaciones semanales, o bien, que se encontrara disfrutando de una pensi&oacute;n de invalidez.</p>
<p>Cada uno de sus hijos tendr&aacute; derecho a recibir pensi&oacute;n de orfandad, siempre y cuando sean menores de 16 a&ntilde;os. Despu&eacute;s de alcanzar esta edad, se prorrogar&aacute; la pensi&oacute;n hasta la edad de 25 a&ntilde;os, si se encuentran estudiando en planteles del sistema educativo nacional.  El hu&eacute;rfano menor de 16 a&ntilde;os que desempe&ntilde;e un trabajo remunerado no tiene derecho a percibir esta pensi&oacute;n, salvo que no pueda mantenerse por su propio trabajo debido a una enfermedad cr&oacute;nica, defecto f&iacute;sico o ps&iacute;quico, en tanto no desaparezca la incapacidad que padece.</p>
<p><strong>Mi hermana acaba de morir y estaba pensionada por el IMSS por Invalidez, &iquest;tengo derecho a que se me pague la ayuda para gastos de funeral?</strong></p>
<p>Tendr&aacute;s derecho a que el IMSS te pague una ayuda, por concepto de gastos de funeral, consistente en dos meses del salario m&iacute;nimo general que rija en el Distrito Federal en la fecha del fallecimiento de tu hermana, debiendo presentar una copia del acta de defunci&oacute;n y la cuenta original de los gastos de funeral.</p>
<p>Esta prestaci&oacute;n, tambi&eacute;n le corresponde a los familiares del asegurado, siempre y cuando tenga reconocidas, cuando menos, 12 cotizaciones semanales en los nueve meses anteriores al fallecimiento.</p>
<p><strong>&iquest;Qu&eacute; pasa cuando mis certificados de incapacidad fueron expedidos por enfermedad general y me las pagan por dicho concepto, si en realidad dichos certificados son a consecuencia del accidente de trabajo que sufr&iacute;? &iquest;Qu&eacute; puedo hacer?</strong></p>
<p>Puedes solicitar al IMSS el pago correcto de tales incapacidades en forma administrativa, es decir, si cuentas con los elementos para demostrar tal circunstancia. En caso de que el Seguro Social no responda favorablemente, puedes acudir a la Procuradur&iacute;a Federal de la Defensa del Trabajo (Profedet) para demandar ante la Junta Federal de Conciliaci&oacute;n y Arbitraje el pago de las diferencias existentes (40%) entre lo que te pag&oacute; (60% sobre el salario) y lo que te debe de pagar (100% sobre el salario).</p>
<p><strong>&iquest;Qu&eacute; me corresponde al declararse una incapacidad Total Permanente?</strong></p>
<p>A una pensi&oacute;n por incapacidad Permanente Total, al declararse &eacute;sta, el asegurado deber&aacute; recibir, como pensi&oacute;n mensual definitiva, el equivalente al 70% del salario que cotizaba al momento de sufrir el riesgo, tomando en consideraci&oacute;n los incrementos que se han generado en el salario del trabajador hasta la fecha en que se determine la incapacidad que presenta.</p>
<p>Si se trata de una enfermedad de trabajo (profesional), esta pensi&oacute;n se calcular&aacute; con el promedio de las &uacute;ltimas 52 semanas de cotizaci&oacute;n o las que tuviere si su aseguramiento fuere por un tiempo menor.</p>
<p>Conforme a la ley vigente, el pensionado deber&aacute; contratar un seguro de sobrevivencia para el caso de su fallecimiento, con el cual se otorgue a sus beneficiarios las pensiones y dem&aacute;s prestaciones econ&oacute;micas a que tengan derecho. Por lo que la pensi&oacute;n, el seguro de sobrevivencia y las prestaciones econ&oacute;micas se otorgar&aacute;n por la instituci&oacute;n de seguros que elija el propio trabajador.</p>
<p><strong>La valuaci&oacute;n que me dio el IMSS por el riesgo de trabajo que presento rebasa el 100% de valuaci&oacute;n, &iquest;cu&aacute;nto me debe de pagar como pensi&oacute;n?</strong></p>
<p>Cuando se re&uacute;nan dos o m&aacute;s incapacidades, el Instituto Mexicano del Seguro Social no cubrir&aacute; al asegurado o sus beneficiarios una pensi&oacute;n mayor de la que hubiese correspondido a la incapacidad Permanente Total. Es decir, &uacute;nicamente el IMSS tiene la obligaci&oacute;n de pagar el 100%, que equivale al 70% del salario que estuviese cotizando el trabajador al momento en que se determine tal incapacidad.</p>
<p><strong>El IMSS me val&uacute;a en un 23% de incapacidad los padecimientos que presento a consecuencia del accidente de trabajo que sufr&iacute;; sin embargo, me dice que no me corresponde pensi&oacute;n, sino una indemnizaci&oacute;n global, &iquest;es correcto?</strong></p>
<p>Si la valuaci&oacute;n definitiva de la incapacidad es hasta 25%, el asegurado tendr&aacute; derecho a que se le pague, en sustituci&oacute;n de la pensi&oacute;n, una indemnizaci&oacute;n global equivalente a cinco anualidades de la pensi&oacute;n que le hubiese correspondido, es decir, que se debe calcular la pensi&oacute;n diaria con la valuaci&oacute;n que corresponde para que se determine la pensi&oacute;n anual y, por &uacute;ltimo, las cinco anualidades, e igualmente cuando la valuaci&oacute;n de la incapacidad sea entre el 25 y el 50%.</p>
<p><strong>He cotizado en el IMSS 33 a&ntilde;os, y desde hace aproximadamente 10 tengo diferentes padecimientos del orden de enfermedad general, &iquest;qu&eacute; puedo hacer?</strong></p>
<p>Deber&aacute;s solicitar, por escrito, una valoraci&oacute;n m&eacute;dica ante el IMSS para que te determinen el grado y porcentaje que presentas, y una vez que se haya dado respuesta a tu solicitud, y si est&aacute;s de acuerdo con &eacute;sta, deber&aacute;s volver a esta procuradur&iacute;a a fin de que se te canalice al &aacute;rea de medicina legal con la finalidad de que se determine el grado y porcentaje de la incapacidad. O bien, si presentas un estado de invalidez, podr&aacute;s ejercitar la acci&oacute;n correspondiente demandando al IMSS el otorgamiento y pago de la pensi&oacute;n correspondiente.</p>
<p><strong>&iquest;C&oacute;mo me dejan de pagar mis incapacidades por riesgo de trabajo?</strong></p>
<p>Tienes derecho a gozar del subsidio mientras dure tu incapacidad para trabajar, equivalente al 100% del salario en que estuvieras cotizando en el momento de ocurrir el riesgo de trabajo. Motivo por el cual, el Seguro Social deber&aacute; de expedirte tus certificados de incapacidad temporal, por riesgo de trabajo, y pagarlos por este mismo concepto. De no ser as&iacute;, tendr&aacute;s el derecho a demandar al IMSS el pago de las diferencias existentes (40%) entre lo que le pag&oacute; (60% sobre el salario) y lo que le debe de pagar (100% sobre el salario).</p>
<p><strong>&iquest;A cu&aacute;nto asciende el monto de mi pensi&oacute;n de Vejez?</strong></p>
<p>Conforme al r&eacute;gimen anterior de pensiones, el monto de la pensi&oacute;n se calcula sobre la base de una cuant&iacute;a b&aacute;sica, considerando el salario promedio de las &uacute;ltimas 200 semanas de cotizaci&oacute;n y los incrementos anuales, los cuales se determinan de acuerdo con el n&uacute;mero de cotizaciones semanales reconocidas al asegurado con posterioridad a las primeras 500 semanas de cotizaci&oacute;n. Fundamento legal: art&iacute;culo 167 de la ley derogada del Seguro Social.</p>
<p>De acuerdo con el nuevo r&eacute;gimen de pensiones, el asegurado que re&uacute;na los requisitos para obtener su pensi&oacute;n de Vejez podr&aacute; disponer de su cuenta individual con el objeto de disfrutar de su pensi&oacute;n. Para tal prop&oacute;sito, podr&aacute; optar por algunas de las siguientes alternativas: contratar una renta vitalicia con la compa&ntilde;&iacute;a de seguros de su elecci&oacute;n, la cual se actualizar&aacute; anualmente en el mes de febrero conforme al &Iacute;ndice Nacional de Precios al Consumidor (IOPC) y mantener el saldo de su cuenta individual en una administradora de fondos para el retiro (Afore) y efectuar retiros programados con cargo a &eacute;sta.</p>
<p><strong>En la actualidad, ya re&uacute;no los requisitos para una pensi&oacute;n de Cesant&iacute;a en Edad Avanzada, &iquest;con cu&aacute;l Ley del Seguro Social me conviene pensionarme?</strong></p>
<p>Para el caso de que hayas cotizado en t&eacute;rminos de la Ley del Seguro Social derogada y te pensiones durante la vigencia de la nueva ley, el Instituto Mexicano del Seguro Social estar&aacute; obligado, a tu solicitud, a calcular estimativamente el importe de tu pensi&oacute;n para cada uno de los reg&iacute;menes, para que puedas decidir lo que convenga a tus intereses. Fundamento legal: art&iacute;culo 4to. transitorio de la nueva Ley del Seguro Social.</p>
<p><strong>&iquest;A qu&eacute; tengo derecho cuando soy pensionado?</strong></p>
<p>A la pensi&oacute;n, prestaciones en especie del seguro de enfermedades y maternidad (atenci&oacute;n m&eacute;dico-quir&uacute;rgica, farmac&eacute;utica y hospitalaria para el pensionado y sus beneficiarios legales), asignaciones familiares y ayuda asistencial.</p>
<p><strong>Fui a tramitar mi pensi&oacute;n por Invalidez, pero me la negaron porque ya contaba con una pensi&oacute;n de Vejez, &iquest;esa acci&oacute;n es procedente?</strong></p>
<p>Es procedente que te hayan negado la pensi&oacute;n de Invalidez porque dichas pensiones son incompatibles, es decir, solamente se puede tener una de ellas.</p>
<p><strong>&iquest;Qu&eacute; es la pensi&oacute;n garantizada?</strong></p>
<p>Si la valuaci&oacute;n definitiva de la incapacidad es hasta 25%, el asegurado tendr&aacute; derecho a que se le pague, en sustituci&oacute;n de la pensi&oacute;n, una indemnizaci&oacute;n global equivalente a cinco anualidades de la pensi&oacute;n que le hubiese correspondido. Es decir, que se debe calcular su pensi&oacute;n diaria con la valuaci&oacute;n que le corresponde para que se determine la pensi&oacute;n anual y por &uacute;ltimo sus cinco anualidades.</p>
<p><strong>El IMSS me otorg&oacute; una pensi&oacute;n por Incapacidad Permanente Parcial del 30% de manera provisional pero no estoy de acuerdo, &iquest;qu&eacute; puedo hacer?</strong></p>
<p>Cuando se declare la incapacidad ya sea parcial o total, el Seguro Social le otorgar&aacute; al trabajador asegurado la pensi&oacute;n que le corresponda, con el car&aacute;cter de provisional, por un per&iacute;odo de adaptaci&oacute;n de dos a&ntilde;os. Durante ese per&iacute;odo, en cualquier momento, el instituto podr&aacute; ordenar y, por su parte, el trabajador pensionado tendr&aacute; el derecho a solicitar la revisi&oacute;n de la incapacidad que presenta con el fin de modificar la cuant&iacute;a de la pensi&oacute;n. Transcurrido el per&iacute;odo de adaptaci&oacute;n, la pensi&oacute;n se considerar&aacute; como definitiva.</p>
<p>Ambas leyes manejan la misma situaci&oacute;n, salvo que la ley derogada se&ntilde;ala que la pensi&oacute;n s&oacute;lo podr&aacute; revisarse una vez al a&ntilde;o, o en cualquier momento si existiesen pruebas de un cambio sustancial en las condiciones de la incapacidad. Si no est&aacute;s de acuerdo con la valuaci&oacute;n que se te otorg&oacute; por parte del Seguro Social, podr&aacute;s acudir a las instalaciones con la finalidad de verificar si es correcta tal valuaci&oacute;n.</p>
<p><strong>Fui a tramitar mi pensi&oacute;n por Invalidez, pero me la negaron porque me encuentro fuera de la conservaci&oacute;n de derechos, &iquest;qu&eacute; puedo hacer?</strong></p>
<p>Para tener derecho a una pensi&oacute;n por Invalidez, deber&aacute;s estar dentro de la conservaci&oacute;n de derechos, que ser&aacute; equivalente a un per&iacute;odo igual a la cuarta parte del tiempo cubierto por tus cotizaciones semanales, contados a partir de la fecha de tu baja. Pero si el asegurado deja de estar sujeto al r&eacute;gimen obligatorio y reingresa a &eacute;ste, se le reconocer&aacute; el tiempo cubierto por sus cotizaciones anteriores en la siguiente forma:</p>
<ol>
    <li>Si la interrupci&oacute;n en el pago de cotizaciones no fue mayor de tres a&ntilde;os, se le reconocer&aacute;n al momento de la reinscripci&oacute;n todas sus cotizaciones.</li>
    <li>Si la interrupci&oacute;n excede de tres a&ntilde;os, pero no de seis a&ntilde;os, se le reconocer&aacute;n todas sus cotizaciones anteriores.</li>
    <li>Cuando a partir de su ingreso haya cubierto 26 semanas de nuevas cotizaciones.</li>
    <li>Si el reingreso ocurre despu&eacute;s de 6 a&ntilde;os de interrupci&oacute;n, las cotizaciones anteriores cubiertas se le reconocer&aacute;n al momento en que re&uacute;na 52 semanas en su nuevo aseguramiento.</li>
</ol>
<p><strong>&iquest;Tengo derecho a alg&uacute;n tipo de pensi&oacute;n por parte del IMSS si tengo menos de 60 a&ntilde;os de edad y dej&eacute; de trabajar?</strong></p>
<p>Los asegurados que pertenezcan al r&eacute;gimen del seguro obligatorio conservar&aacute;n los derechos que tuvieran adquiridos para poder pensionarse por Invalidez, Vejez, Cesant&iacute;a en Edad Avanzada y muerte, por un per&iacute;odo igual a la cuarta parte del tiempo cubierto por sus cotizaciones semanales, contados a partir de la fecha en que fue dado de baja.</p>
<p>Si te encuentras dentro de la conservaci&oacute;n de derechos, para tener derecho a la pensi&oacute;n de Cesant&iacute;a en Edad Avanzada requieres tener 500 semanas de cotizaci&oacute;n, haber quedado privado de un trabajo remunerado, es decir, que haya causado baja del IMSS y haber cumplido 60 a&ntilde;os.  Una vez que se cumplan tales requisitos ser&aacute; necesario que te presentes a tu cl&iacute;nica de adscripci&oacute;n del IMSS para solicitar dicha pensi&oacute;n, junto con tu acta de nacimiento y hoja de baja.</p>
<p><strong>&iquest;A qu&eacute; prestaciones tiene derecho el asegurado que presenta un estado de invalidez conforme a la Ley del Seguro Social vigente?</strong></p>
<ol type="">
    <li>Pensi&oacute;n temporal.</li>
    <li>Pensi&oacute;n definitiva: cuando el trabajador tenga un saldo acumulado en su cuenta individual que sea mayor al necesario para integrar el monto constitutivo para contratar los seguros de renta vitalicia y de sobrevivencia, podr&aacute; el asegurado optar por:
    <ol type="a">
        <li>Retirar la suma excedente en una sola exhibici&oacute;n de su cuenta individual.</li>
        <li>Contratar una renta vitalicia por una cuant&iacute;a mayor.</li>
        <li>Aplicar el excedente a un pago de sobreprima para incrementar los beneficios del seguro de sobrevivencia.</li>
    </ol>
    <br />
    La renta vitalicia y el seguro de sobrevivencia se sujetar&aacute;n a lo dispuesto en el art&iacute;culo 159 fracci&oacute;n IV y VI de esta ley.</li>
    <li>Asistencia m&eacute;dica, en los t&eacute;rminos del cap&iacute;tulo IV de este t&iacute;tulo.</li>
    <li>Asignaciones familiares, de conformidad con lo establecido en la secci&oacute;n IV de este cap&iacute;tulo.</li>
    <li>Ayuda asistencial, en los t&eacute;rminos de la propia secci&oacute;n IV de este cap&iacute;tulo.</li>
</ol>
<p><strong>Estoy pensionado por el IMSS y tengo un hijo de 17 a&ntilde;os de edad, &iquest;tiene derecho a las prestaciones en especie que otorga dicho instituto?</strong></p>
<p>S&iacute;, siempre y cuando no pueda mantenerse por su propio trabajo debido a una enfermedad cr&oacute;nica, defecto f&iacute;sico o ps&iacute;quico o por encontrarse estudiando en planteles del Sistema Educativo Nacional, hasta los 25 a&ntilde;os.</p>
<p><strong>Me qued&eacute; sin empleo, &iquest;puedo hacer un retiro parcial de mi Subcuenta de Retiro, Cesant&iacute;a en Edad Avanzada y Vejez?</strong></p>
<p>Durante el tiempo en que el trabajador deje de estar sujeto a una relaci&oacute;n laboral, tendr&aacute; derecho a:</p>
<ol>
    <li>Retirar parcialmente por situaci&oacute;n de desempleo los recursos de la Subcuenta de Retiro, Cesant&iacute;a en Edad Avanzada y Vejez, a partir del cuadrag&eacute;simo sexto d&iacute;a natural contado desde el d&iacute;a en que qued&oacute; desempleado, en los siguientes t&eacute;rminos:</li>
    <li>Si su cuenta individual tiene, al menos, tres a&ntilde;os de haber sido abierta y tiene un m&iacute;nimo de 12 bimestres de cotizaci&oacute;n al instituto acreditados en dicha cuenta, podr&aacute; retirar en una exhibici&oacute;n la cantidad que resulte al equivalente a 30 d&iacute;as de su &uacute;ltimo salario base de cotizaci&oacute;n, con un l&iacute;mite de 10 veces el salario m&iacute;nimo mensual general que rija en el Distrito Federal.</li>
    <li>Si su cuenta individual tiene cinco a&ntilde;os o m&aacute;s de haber sido abierta, podr&aacute; retirar la cantidad que resulte menor entre 90 d&iacute;as de su propio salario base de cotizaci&oacute;n de las &uacute;ltimas 250 semanas o las que tuviere, o el 11.5% del saldo de la Subcuenta de Retiro, Cesant&iacute;a en Edad Avanzada y Vejez.  <br />
    Las cantidades a que se refiere este inciso se entregar&aacute;n en un m&aacute;ximo de seis mensualidades, la primera de las cuales podr&aacute; ser por un monto de 30 d&iacute;as de su &uacute;ltimo salario base de cotizaci&oacute;n a solicitud del trabajador, conforme a las reglas de car&aacute;cter general que al efecto expida la Comisi&oacute;n Nacional del Sistema de Ahorro para el Retiro (Consar). En caso de que el trabajador se reincorpore a laborar, durante el plazo de entrega de los recursos, las mensualidades posteriores a su reincorporaci&oacute;n se suspender&aacute;n.  <br />
    El trabajador que cumpla con los requisitos de antig&uuml;edad de la cuenta a que se refiere el primer p&aacute;rrafo de este inciso, podr&aacute; optar, en todo caso, por el beneficio se&ntilde;alado en el inciso a).  <br />
    El derecho consignado en esta fracci&oacute;n, s&oacute;lo podr&aacute;n ejercerlo los trabajadores que acrediten con los estados de cuenta correspondientes, no haber efectuado retiros durante los cinco a&ntilde;os inmediatos anteriores a la fecha de la solicitud de retiro de recursos. El derecho consignado en esta fracci&oacute;n s&oacute;lo podr&aacute;n ejercerlo los trabajadores que acrediten con los estados de cuenta correspondientes, no haber efectuado retiros durante los cinco a&ntilde;os inmediatos anteriores a la fecha de la solicitud de retiro de recursos.</li>
</ol>
<p><strong>Sufr&iacute; un accidente de trabajo y perd&iacute; mis dos piernas y una mano, la empresa no ha respondido por nada, &uacute;nicamente el IMSS me paga mi pensi&oacute;n, &iquest;qu&eacute; puedo hacer?</strong></p>
<p>Si ya se te paga tu pensi&oacute;n correspondiente por las secuelas del accidente que sufriste, no procede que tu patr&oacute;n o la empresa a la cual prestas tus servicios te cubra alg&uacute;n tipo de prestaci&oacute;n. Toda vez que si te encontrabas inscrito en el Instituto Mexicano del Seguro Social, al momento de ocurrir el riesgo, entonces dicho instituto ser&aacute; responsable de las secuelas del accidente. El patr&oacute;n tiene la obligaci&oacute;n de reubicarte en alg&uacute;n lugar compatible con la capacidad que te haya quedado.</p>
<p>En el supuesto caso de que ya no est&eacute; en condiciones de seguir laborando, entonces tu patr&oacute;n est&aacute; obligado a pagarte la parte proporcional de tus vacaciones, prima vacacional y aguinaldo.</p>
<p><strong>Tengo 56 a&ntilde;os de edad y 1,745 semanas cotizadas en el IMSS. Me dieron de baja en el a&ntilde;o de 1996, &iquest;qu&eacute; puedo hacer para que se me otorgue una pensi&oacute;n?</strong></p>
<p>Conforme al nuevo r&eacute;gimen de pensiones, podr&aacute;s pensionarte antes de cumplir los 60 a&ntilde;os de edad, siempre y cuando la pensi&oacute;n que se te calcule en el sistema de renta vitalicia sea superior en m&aacute;s del 30% de la pensi&oacute;n garantizada. Una vez cubierta la prima del seguro de sobrevivencia para sus beneficiarios, con base en este r&eacute;gimen, no se requiere como requisito indispensable la conservaci&oacute;n de derechos.  La renta vitalicia es el contrato por el cual, la aseguradora, a cambio de recibir los recursos acumulados en la cuenta individual, se obliga a pagar peri&oacute;dicamente una pensi&oacute;n durante la vida del pensionado.&nbsp;</p>
<p><strong>Desde hace alg&uacute;n tiempo, el IMSS me otorg&oacute; una pensi&oacute;n de incapacidad permanente por los padecimientos de bronquitis qu&iacute;mica, en un 20%; y por cortipatia bilateral por trauma ac&uacute;stico cr&oacute;nico, en un 31%; sumando un 51% de disminuci&oacute;n org&aacute;nica funcional. Aparte, tengo un cr&eacute;dito habitacional por parte del Infonavit.&nbsp;<br />
<br />
Desde el mes de abril de 2001 me qued&eacute; desempleado, &iquest;qu&eacute; tr&aacute;mites necesito hacer para que se libere mi cr&eacute;dito?</strong></p>
<p>Trat&aacute;ndose de los casos de incapacidad Parcial Permanente, cuando &eacute;sta sea del 50% o m&aacute;s, o de invalidez definitiva, en los t&eacute;rminos de la Ley del Seguro Social, se liberar&aacute; al trabajador acreditado del adeudo, los grav&aacute;menes o limitaciones de dominios a favor del instituto, siempre y cuando no sea sujeto de una nueva relaci&oacute;n de trabajo por un per&iacute;odo m&iacute;nimo de dos a&ntilde;os. Lapso durante el cual, gozar&aacute; de una pr&oacute;rroga, sin causa de intereses, para el pago de su cr&eacute;dito.</p>
<p>La existencia de cualquiera de estos supuestos deber&aacute; comprobarse ante el Infonavit, dentro del mes siguiente a la fecha en que se determinen. En tu caso, hasta el momento, no re&uacute;nes el per&iacute;odo m&iacute;nimo de desempleo para que proceda la liberaci&oacute;n de tu cr&eacute;dito. Debes tramitar la solicitud ante el Infonavit de la pr&oacute;rroga mencionada.</p>
<p><strong>&iquest;Qu&eacute; t&eacute;rmino tengo para solicitar mi fondo de ahorro de Infonavit?</strong></p>
<p>Los derechos de los trabajadores titulares de los dep&oacute;sitos constituidos en el Infonavit, es decir, del fondo de ahorro, prescriben en un plazo de cinco a&ntilde;os.</p>
<p><strong>Me retir&eacute; voluntariamente de mi trabajo y cuento con un cr&eacute;dito habitacional del Infonavit, &iquest;qu&eacute; debo hacer?</strong></p>
<p>Deber&aacute;s presentarte ante ese instituto y solicitar una pr&oacute;rroga, por 12 meses, sin causa de intereses en los pagos para cubrir el cr&eacute;dito. Deber&aacute;s hacerlo antes de que pase un mes, contado a partir de la fecha de la separaci&oacute;n, y la pr&oacute;rroga quedar&aacute; sin efectos si eres sujeto de una nueva relaci&oacute;n de trabajo.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
