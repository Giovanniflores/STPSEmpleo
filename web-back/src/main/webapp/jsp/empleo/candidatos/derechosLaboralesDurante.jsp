<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Derechos durante la vida laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Contenido pendiente
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
		
	        <p><strong>&iquest;Qu&eacute; puedo hacer si mi patr&oacute;n no respeta mis condiciones de trabajo?</strong></p>
<p>Puedes demandar a tu patr&oacute;n, ante la autoridad laboral competente, debido a que las condiciones de trabajo no pueden ser modificadas sin convenio previo, sin importar cu&aacute;les sean. Cuentas con el t&eacute;rmino de dos meses, contados a partir de la fecha en que se modificaron dichas condiciones, para hacer valer tu derecho ante la autoridad laboral.</p>
<p><strong>Si entrego recibo de honorarios a mi patr&oacute;n, &iquest;tengo derecho a las prestaciones que se&ntilde;ala la Ley Federal del Trabajo?</strong></p>
<p>Primeramente hay que saber qu&eacute; tipo de relaci&oacute;n existe entre t&uacute; y quien recibe el servicio para determinar si tienes derecho a las prestaciones de Ley. Si se firm&oacute; un contrato por honorarios, o contrato de prestaci&oacute;n de servicios, pero existe una subordinaci&oacute;n entre el prestador del servicio y el contratante, se entender&aacute; que existe una relaci&oacute;n de naturaleza laboral, por lo cual estar&aacute; protegida por las normas laborales, entendi&eacute;ndose con esto que s&iacute; tienes derechos a todas y cada una de las prestaciones que se&ntilde;ala la Ley Federal del Trabajo a favor de los trabajadores. Pero si no existe la subordinaci&oacute;n (es decir, no se registra tarjeta, no se reciben &oacute;rdenes de cu&aacute;ndo, d&oacute;nde y c&oacute;mo ejecutar el servicio), entonces se estar&iacute;a en presencia de un contrato de naturaleza civil.</p>
<p><strong>&iquest;Un trabajador de confianza puede tener derecho a las prestaciones que otorga el contrato colectivo de mi empresa?</strong></p>
<p>Las prestaciones que otorga el contrato colectivo de trabajo se extender&aacute;n a los trabajadores de confianza, salvo disposici&oacute;n en contrario consignada en el mismo contrato colectivo.</p>
<p><strong>Mi patr&oacute;n me acaba de cambiar mi horario de trabajo sin consultarme, &iquest;qu&eacute; puedo hacer?</strong></p>
<p>Puedes demandarlo ante la autoridad laboral competente, en virtud de que las condiciones de trabajo no pueden ser modificadas sin convenio previo. Si tu horario estaba convenido con antelaci&oacute;n, no se puede variar al arbitrio del patr&oacute;n. Cuentas con el t&eacute;rmino de dos meses, contados a partir de la fecha en que se modificaron tus condiciones de trabajo, para hacer valer tu derecho ante la autoridad laboral.</p>
<p><strong>Mi patr&oacute;n me efect&uacute;a un descuento por pensi&oacute;n alimenticia, &iquest;es v&aacute;lido?</strong></p>
<p>Si la pensi&oacute;n alimenticia se encuentra decretada por un Juez de los Familiar de conformidad con el C&oacute;digo Civil de tu estado, de acuerdo a la Ley Federal del Trabajo se&ntilde;ala que los descuentos en los salarios de los trabajadores est&aacute;n prohibidos, salvo en los casos y con los requisitos siguientes:</p>
<ol>
    <li class="no_line">Para el pago de pensiones alimenticias en favor de acreedores alimentarios, decretado por la autoridad competente</li>
</ol>
<p><strong>&iquest;Mi patr&oacute;n me puede sancionar por haber cometido alguna falta leve en mi trabajo?</strong></p>
<p>S&iacute;, siempre y cuando la falta se encuentre entre aquellas que se&ntilde;ala la Ley Federal del Trabajo en su art&iacute;culo 47, en el Contrato Colectivo de Trabajo aplicable al Centro de Trabajo, en el Reglamento Interior o Condiciones Generales de Trabajo, &eacute;sta tiene que constar por escrito.</p>
<p><strong>&iquest;Qu&eacute; t&eacute;rmino tengo para demandar mis vales de despensa?</strong></p>
<p>Un a&ntilde;o, contado a partir del d&iacute;a siguiente a la fecha en que el patr&oacute;n debe pagarlos.</p>
<p><strong>A mis dem&aacute;s compa&ntilde;eros les dan vales de despensa, a m&iacute; no y desempe&ntilde;amos la misma funci&oacute;n, &iquest;qu&eacute; puedo hacer?</strong></p>
<p>Lo primero que tienes que hacer es verificar cu&aacute;les son las condiciones en las que se otorgan dichos vales, y si las mismas han quedado establecidas en un contrato colectivo o reglamento interior. De ser as&iacute;, hay que verificar si cumples con tales requisitos. En caso de que no exista ninguna estipulaci&oacute;n al respecto, puedes demandar el otorgamiento de tales vales. Cuentas con el t&eacute;rmino de un a&ntilde;o para hacerlo, a partir del d&iacute;a siguiente en que se est&eacute;n pagando.</p>
<p><strong>En mi trabajo se desocup&oacute; una categor&iacute;a de mayor jerarqu&iacute;a, a la cual creo tener m&aacute;s derecho por mis conocimientos y mi antig&uuml;edad; sin embargo, se la otorgaron a mi compa&ntilde;ero de nuevo ingreso, &iquest;qu&eacute; puedo hacer?</strong></p>
<p>Podr&aacute;s demandar a tu patr&oacute;n el derecho de preferencia, a efecto de que se te otorgue el puesto correspondiente. Cuentas con dos meses a partir del desplazamiento de la convocatoria o de cuando se tiene conocimiento de &eacute;ste.</p>
<p><strong>&iquest;Pueden devengar intereses las deudas que tienen los trabajadores con sus patrones?</strong></p>
<p>No, en ning&uacute;n caso.</p>
<p><strong>&iquest;C&oacute;mo me deben pagar mis horas extra?</strong></p>
<p>Las horas de trabajo extraordinario se deben pagar con un 100% m&aacute;s del salario que corresponda a las horas de jornada ordinaria. El tiempo extraordinario que exceda de nueve horas a la semana se debe pagar con un 200% m&aacute;s, o sea, se pagar&aacute; un total del 300%.</p>
<p><strong>&iquest;Un trabajador de una l&iacute;nea de autotransporte tiene derecho a que se le pague tiempo extraordinario?</strong></p>
<p>Por criterio de las autoridades del trabajo, este gremio de trabajadores no tiene derecho al pago de tiempo extraordinario.</p>
<p><strong>Mi patr&oacute;n ha detenido el pago de mi salario. &iquest;Es correcto? &iquest;Qu&eacute; puedo hacer para solicitar el pago?</strong></p>
<p>No es correcto, ya que dentro de las obligaciones que les corresponden a los patrones, est&aacute; la de pagar a los trabajadores los salarios e indemnizaciones. En caso de no ser as&iacute;, deber&aacute;s solicitarlo por escrito o por conducto de la Inspecci&oacute;n en el Trabajo, para que en el supuesto de que no se te pague tu salario, tengas el derecho de rescindir tu relaci&oacute;n de trabajo sin ninguna responsabilidad.</p>
<p><strong>&iquest;Qu&eacute; t&eacute;rmino tengo para demandar mis salarios ya generados?</strong></p>
<p>Un a&ntilde;o, contado a partir del d&iacute;a siguiente a la fecha en que era obligaci&oacute;n del patr&oacute;n pagarlo.</p>
<p><strong>En mi contrato individual de trabajo, se fij&oacute; un salario superior al que me est&aacute; pagando realmente mi patr&oacute;n, &iquest;qu&eacute; puedo hacer?</strong></p>
<p>Puedes solicitar por escrito a tu patr&oacute;n el pago de las diferencias existentes entre la cantidad que te paga y la que te debe pagar, conforme al contrato de trabajo. En supuesto caso de que el patr&oacute;n se niegue, puedes demandar tales diferencias ante la autoridad laboral, cuentas con el t&eacute;rmino de un a&ntilde;o a partir de que se hace exigible dicho pago.</p>
<p><strong>&iquest;El salario puede ser sujeto de descuentos?</strong></p>
<p>Los descuentos en los salarios de los trabajadores est&aacute;n prohibidos, salvo en los siguientes casos: por el pago de deudas contra&iacute;das con el patr&oacute;n por anticipo de salario, pagos hechos con exceso al trabajador, errores, p&eacute;rdidas, aver&iacute;as o adquisici&oacute;n de art&iacute;culos producidos por la empresa o establecimiento.</p>
<p>La cantidad exigible, en ning&uacute;n caso podr&aacute; ser mayor del importe de los salarios de un mes, y el descuento ser&aacute; el que convenga el trabajador y el patr&oacute;n, sin que pueda ser mayor del 30% del excedente del salario m&iacute;nimo. Tambi&eacute;n, para el pago de la renta cuando las habitaciones se den en arrendamiento a los trabajadores por parte de su patr&oacute;n, el cual no podr&aacute; exceder del 15% del salario; para el pago de abonos para cubrir pr&eacute;stamos provenientes del Instituto del Fondo Nacional de la Vivienda para los Trabajadores (Infonavit) y ser&aacute;n aceptados libremente por el trabajador; para el pago de cuotas para la constituci&oacute;n y fomento de sociedades cooperativas y de caja de ahorro, siempre que los trabajadores manifiesten expresa y libremente su conformidad, y no deber&aacute;n ser mayores del 30% del excedente del salario m&iacute;nimo; para pagos de pensiones alimenticias a favor de la esposa, hijos, ascendientes y nietos decretados por la autoridad competente; para el pago de las cuotas sindicales ordinarias previstas en los estatutos de los sindicatos; y para el pago de abonos para cubrir cr&eacute;ditos garantizados por el Fondo de Fomento y Garant&iacute;a para el Consumo de los Trabajadores (Fonacot).</p>
<p><strong>&iquest;Cu&aacute;les son los descuentos que se pueden efectuar al salario m&iacute;nimo?</strong></p>
<p>Los &uacute;nicos descuentos que la Ley Laboral permite al salario m&iacute;nimo son: para el pago de pensi&oacute;n alimenticia; para cubrir la renta que le otorga el patr&oacute;n, el cual no podr&aacute; exceder del 10% del salario; para el pago de abonos; para cubrir pr&eacute;stamos al Infonavit, no pudiendo exceder &eacute;ste del 20% del salario; y para el pago de abonos para cubrir cr&eacute;ditos otorgados por el Fondo para la Adquisici&oacute;n de Bienes de Consumo Duradero, no excediendo del 10% del salario.</p>
<p><strong>&iquest;Qu&eacute; tiempo tiene mi patr&oacute;n para realizar descuentos a mi salario?</strong></p>
<p>Un mes, contado a partir de la fecha en que la deuda sea exigible.</p>
<p><strong>&iquest;Mi salario puede ser embargado?</strong></p>
<p>De acuerdo a la Ley Federal del Trabajo, los salarios de los trabajadores no podr&aacute;n ser embargados, salvo el caso de pensiones alimenticias decretadas por la autoridad competente en beneficio de la esposa, hijos, ascendientes y nietos.</p>
<p><strong>&iquest;Se puede pagar el salario en cheque?</strong></p>
<p>El salario en efectivo deber&aacute; pagarse, precisamente, en moneda de curso legal, no siendo permitido hacerlo en mercanc&iacute;as, vales, fichas o cualquier otro signo representativo con que se pretenda substituir la moneda. Con el previo consentimiento del trabajador, el pago del salario podr&aacute; efectuarse por medio de dep&oacute;sito en cuenta bancaria, tarjeta de d&eacute;bito, transferencias o cualquier otro medio electr&oacute;nico. Los gastos o costos que originen estos medios alternativos de pago ser&aacute;n cubiertos por el patr&oacute;n.</p>
<p><strong>&iquest;Los vales de despensa forman parte de mi salario?</strong></p>
<p>Del salario integrado s&iacute;, porque se trata de una prestaci&oacute;n en especie que otorga la empresa, el cual es el salario considerado para el pago de las indemnizaciones a las que tienen derecho los trabajadores, pero no para el pago de otras prestaciones.</p>
<p><strong>&iquest;En qu&eacute; momento tengo derecho a mis vacaciones?</strong></p>
<p>Los trabajadores que tengan m&aacute;s de un a&ntilde;o de servicios disfrutar&aacute;n de un per&iacute;odo anual de vacaciones pagadas, que en ning&uacute;n caso podr&aacute; ser inferior a seis d&iacute;as laborales. Las vacaciones se computar&aacute;n bas&aacute;ndose en la antig&uuml;edad del trabajador, es decir: por un a&ntilde;o de antig&uuml;edad, le corresponden 6 d&iacute;as; por 2 a&ntilde;os de antig&uuml;edad, 8 d&iacute;as; por 3 a&ntilde;os de antig&uuml;edad, 10 d&iacute;as; por 4 a&ntilde;os de antig&uuml;edad, 12 d&iacute;as; de 5 a 9 a&ntilde;os de antig&uuml;edad, 14 d&iacute;as; de 10 a 14 a&ntilde;os de antig&uuml;edad, 16 d&iacute;as; de 15 a 19 a&ntilde;os de antig&uuml;edad, 18 d&iacute;as; de 20 a 24 a&ntilde;os de antig&uuml;edad, 20 d&iacute;as; de 25 a 29 a&ntilde;os de antig&uuml;edad, 22 d&iacute;as, etc.</p>
<p><strong>&iquest;Cu&aacute;ndo se me deben de conceder las vacaciones a las que tengo derecho?</strong></p>
<p>Dentro de los seis meses siguientes al cumplimiento del a&ntilde;o de servicios, en caso contrario, el trabajador tendr&aacute; el derecho de solicitar el otorgamiento y pago de sus vacaciones ante la autoridad laboral competente, desde el d&iacute;a siguiente en que se cumpla el t&eacute;rmino se&ntilde;alado.</p>
<p><strong>&iquest;Cu&aacute;ndo me deben de pagar mis vacaciones?</strong></p>
<p>Deber&aacute;n ser pagadas el d&iacute;a inmediato anterior en que empiece a disfrutarlas el trabajador.</p>
<p><strong>Presto mis servicios al Gobierno Federal, &iquest;cu&aacute;ntos d&iacute;as de vacaciones me corresponde y a cu&aacute;nto equivale mi prima vacacional?</strong></p>
<p>Los trabajadores que tengan m&aacute;s de seis meses consecutivos de servicios disfrutar&aacute;n de dos per&iacute;odos anuales de vacaciones, de diez d&iacute;as laborales cada uno, en las fechas que se se&ntilde;alen al efecto, y percibir&aacute;n una prima adicional de un 30%, sobre el sueldo o salario que les corresponda durante dichos per&iacute;odos.</p>
<p><strong>&iquest;Cu&aacute;les son los d&iacute;as de descanso obligatorio en materia burocr&aacute;tica?</strong></p>
<p>El decreto publicado en el Diario Oficial del 2006 se&ntilde;ala como descanso obligatorio el 1&deg; de enero, el primer lunes de febrero en conmemoraci&oacute;n al 5&deg; de febrero, el tercer lunes de marzo en conmemoraci&oacute;n al 21 de marzo, 1&deg; de mayo, 5 de mayo, 16 de septiembre, el tercer lunes de noviembre en conmemoraci&oacute;n al 20 de noviembre, 1&deg; de diciembre de cada seis a&ntilde;os, cuando corresponda a la transmisi&oacute;n del Poder Ejecutivo Federal, 25 de diciembre y los que determinen las Leyes Federales y Locales Electorales. Con base en el art&iacute;culo 75 de la Ley Laboral, el pago que corresponde a los trabajadores que presten sus servicios en los descansos obligatorios, independientemente del salario que les corresponde por este descanso, ser&aacute; doble.</p>
<p><strong>&iquest;El jueves y viernes santos son d&iacute;as de descanso obligatorio?</strong></p>
<p>De acuerdo a la Ley Federal del Trabajo, no. Sin embargo, si en la empresa en que prestas tus servicios cuentan con contrato colectivo de trabajo, posiblemente s&iacute; se otorguen esos d&iacute;as a favor de los trabajadores, o bien, a cuenta de vacaciones.</p>
<p><strong>&iquest;Qu&eacute; es la prima dominical y cu&aacute;ndo procede su pago?</strong></p>
<p>Es una prima adicional que se les paga a aquellos trabajadores que prestan sus servicios en d&iacute;a domingo, equivalente al 25%, por lo menos, sobre el salario de los d&iacute;as ordinarios de trabajo y su pago procede, en la hip&oacute;tesis de que el trabajador normalmente labore en domingo y descanse otro d&iacute;a, es decir, que en su jornada de trabajo quede incluido el domingo, en los casos de excepci&oacute;n en que se labora el domingo y corresponda a su descaso semanal, se pagar&aacute; con un salario triple.</p>
<p><strong>&iquest;A cu&aacute;ntos d&iacute;as de aguinaldo tengo derecho de conformidad con la Ley Federal del Trabajo?</strong></p>
<p>Tienes derecho, como m&iacute;nimo, a 15 d&iacute;as de salario si trabajaste todo el a&ntilde;o o su parte proporcional si no laboraste ese periodo.</p>
<p><strong>&iquest;Cu&aacute;nto tiempo tengo para reclamar mi aguinaldo?</strong></p>
<p>Un a&ntilde;o contado a partir del 20 de diciembre.</p>
<p><strong>Los trabajadores contratados para labores de temporada, &iquest;tienen derecho a recibir aguinaldo?</strong></p>
<p>Este tipo de trabajadores tendr&aacute;n derecho, a la terminaci&oacute;n de la temporada, a la parte proporcional del aguinaldo en funci&oacute;n del tiempo laborado. Este criterio ha sido sustentado por la jurisprudencia de la Cuarta Sala de la Suprema Corte de Justicia de la Naci&oacute;n, relativa a la S&eacute;ptima &eacute;poca, volumen 51, quinta parte, p&aacute;gina 13, bajo el rubro &quot;Aguinaldo, Pago del&quot;, trat&aacute;ndose de labores de temporada.</p>
<p><strong>&iquest;Cu&aacute;ntos d&iacute;as de aguinaldo me corresponden, habiendo trabajado en un organismo descentralizado de car&aacute;cter federal?</strong></p>
<p>Tendr&aacute;s derecho a un aguinaldo anual, el cual deber&aacute; pagarse antes del 20 de diciembre y ser&aacute; equivalente a 40 d&iacute;as de salario, al menos. Los trabajadores que no hayan cumplido el a&ntilde;o de servicios, independientemente de que se encuentren laborando o no en la fecha de liquidaci&oacute;n del aguinaldo, tendr&aacute;n derecho a que se les pague la parte proporcional del mismo, conforme al tiempo que hubiera trabajado, cualquiera que &eacute;ste fuere.</p>
<p><strong>&iquest;El aguinaldo causa impuestos?</strong></p>
<p>La gratificaci&oacute;n anual, aguinaldo, que perciban los trabajadores durante un a&ntilde;o calendario no causar&aacute; impuesto hasta un monto equivalente al salario m&iacute;nimo general del &aacute;rea geogr&aacute;fica respectiva, elevado a 30 d&iacute;as cuando dicha gratificaci&oacute;n se otorgue de manera general. El aguinaldo que sea inferior al monto equivalente al salario m&iacute;nimo general, al &aacute;rea geogr&aacute;fica del trabajador elevado a 30 d&iacute;as, no pagar&aacute; el impuesto hasta el monto de la gratificaci&oacute;n otorgada, aun cuando se calcule sobre un salario superior al m&iacute;nimo.</p>
<p><strong>&iquest;Tengo derecho a la participaci&oacute;n de utilidades del ejercicio fiscal anterior?</strong></p>
<p>Primeramente, debes investigar el tipo de empresa donde est&aacute;s prestando tus servicios para verificar si est&aacute; exceptuada del pago de reparto de utilidades. Si la empresa o patr&oacute;n en su declaraci&oacute;n anual del Impuesto Sobre la Renta (ISR) declar&oacute; un ingreso anual no superior a $3 000,000.00, queda exceptuada de la obligaci&oacute;n de repartir utilidades, en caso contrario, s&iacute; existe la obligaci&oacute;n de repartir del ejercicio fiscal anterior. Para tal fin, de acuerdo al art&iacute;culo 125 de la Ley Federal del Trabajo, se deber&aacute; integrar una comisi&oacute;n mixta en la que participar&aacute;n representantes de los trabajadores y el patr&oacute;n, la cual formular&aacute; un proyecto que determine la participaci&oacute;n de cada trabajador, dependiendo del n&uacute;mero de d&iacute;as laborados y salarios devengados por todos y cada uno de ellos, as&iacute; como de la utilidad fiscal declarada.</p>
<p>En caso de que el patr&oacute;n te niegue el pago de las utilidades, la procuradur&iacute;a te ofrece sus servicios. Primeramente, para que se cite al patr&oacute;n y se trate de solucionar tu problema de manera conciliatoria, siendo necesario saber la cantidad l&iacute;quida que le corresponde por este concepto. El t&eacute;rmino para demandar ante la autoridad competente es de un a&ntilde;o, a partir de la fecha en que el patr&oacute;n empez&oacute; a pagar las utilidades, este pago deber&aacute; ser realizado dentro de los 60 d&iacute;as siguientes a la fecha en que present&oacute; su declaraci&oacute;n anual.</p>
<p><strong>&iquest;Cu&aacute;l es el salario que se considera como base del reparto de utilidades?</strong></p>
<p>Para los efectos del reparto de utilidades, el salario que se debe considerar es exclusivamente el salario tabulado o por cuota diaria, sin considerar otras prestaciones como tiempo extra, gratificaciones, primas o cualquier otro ingreso derivado de tu trabajo. En los casos de salario por unidad de obra o comisiones, o cuando la retribuci&oacute;n sea variable, se tomar&aacute; como salario diario el promedio de las percepciones obtenidas en el a&ntilde;o fiscal materia del reparto de utilidades. Si la percepci&oacute;n se integra con salario fijo y comisiones o destajo, se tomar&aacute; en cuenta &uacute;nicamente el salario fijo para determinar su participaci&oacute;n individual.</p>
<p><strong>&iquest;Qu&eacute; tiempo tiene el patr&oacute;n para pagarme mis utilidades?</strong></p>
<p>El art&iacute;culo 122 establece que el reparto de utilidades entre los trabajadores deber&aacute; efectuarse dentro de los 60 d&iacute;as, t&eacute;rmino que comienza a correr a partir de la fecha en que la empresa present&oacute; o debi&oacute; presentar la declaraci&oacute;n del ejercicio fiscal ante la Secretar&iacute;a de Hacienda y Cr&eacute;dito P&uacute;blico (SHCP), a&uacute;n y cuando est&eacute;n en tr&aacute;mite objeciones de los trabajadores.</p>
<p>El art&iacute;culo 58, fracci&oacute;n VIII de la Ley del Impuesto Sobre la Renta se&ntilde;ala la obligaci&oacute;n de las personas morales de presentar su declaraci&oacute;n de Impuesto Sobre la Renta, dentro de los tres meses siguientes al cierre de su ejercicio fiscal (31 de diciembre) y el art&iacute;culo 139 dispone que las personas f&iacute;sicas la presentar&aacute;n dentro de los meses de febrero a abril de cada a&ntilde;o.</p>
<p><strong>Soy un trabajador de confianza, &iquest;tengo derecho al reparto de utilidades?</strong></p>
<p>S&iacute;, pero en forma restringida, ya que el art&iacute;culo 127, fracci&oacute;n II de la Ley Federal del Trabajo, lo limita a un 20% del salario m&aacute;s alto del trabajador sindicalizado o, a falta de &eacute;ste, del trabajador de planta.</p>
<p><strong>&iquest;Los trabajadores eventuales tienen derecho a participar en las utilidades de la empresa?</strong></p>
<p>Estos trabajadores tendr&aacute;n derecho a participar en las utilidades de la empresa cuando hayan laborado un m&iacute;nimo de 60 d&iacute;as durante el a&ntilde;o, ya sea en forma continua o discontinua. Si un trabajador labora m&aacute;s de 60 d&iacute;as que abarquen dos ejercicios fiscales sin llegar a este n&uacute;mero en un solo ejercicio, no tendr&aacute; derecho a participar en las utilidades.</p>
<p><strong>&iquest;Cu&aacute;l es tiempo que tengo para solicitar el pago de las utilidades?</strong></p>
<p>Si eres trabajador activo, el plazo que tienes para cobrar la cantidad que te corresponde por concepto de utilidades es de un a&ntilde;o, contado a partir del d&iacute;a siguiente a aquel en que se hace exigible la obligaci&oacute;n, es decir, del 31 de mayo o 30 de junio si el patr&oacute;n es persona moral o f&iacute;sica, respectivamente. En el caso de un ex trabajador, el t&eacute;rmino con el que cuenta es de un a&ntilde;o a partir del d&iacute;a siguiente en que se deban pagar las utilidades.&nbsp;</p>
<p><strong>La empresa en la que presto mis servicios cambi&oacute; de raz&oacute;n social a principios del ejercicio fiscal anterior, motivo por el cual manifiesta que se encuentra exceptuado de repartir utilidades a sus trabajadores, &iquest;es cierto?</strong></p>
<p>No. Por criterio de las autoridades del trabajo, las empresas que se fusionen, traspasen o cambien de raz&oacute;n social, tienen la obligaci&oacute;n de repartir utilidades a sus trabajadores por no tratarse de empresa de nueva creaci&oacute;n, ya que iniciaron sus operaciones con anterioridad al cambio o modificaci&oacute;n de su nombre o raz&oacute;n social.</p>
<p><strong>&iquest;C&oacute;mo debe considerar mi patr&oacute;n para mi reparto de utilidades los per&iacute;odos pre y posnatales a los que tuve derecho en el ejercicio fiscal?</strong></p>
<p>Para efectos del pago de utilidades, estos per&iacute;odos se consideran como d&iacute;as trabajados, as&iacute; como el monto de los salarios percibidos.</p>
<p><strong>&iquest;A qu&eacute; prestaciones tengo derecho por mi estado de embarazo por parte de mi patr&oacute;n?</strong></p>
<p>Las madres trabajadoras tendr&aacute;n los siguientes derechos:</p>
<ol type="">
    <li>Durante el per&iacute;odo del embarazo, no realizar&aacute;n trabajos que exijan esfuerzos considerables y signifiquen un peligro para su salud en relaci&oacute;n con la gestaci&oacute;n, tales como levantar, tirar o empujar grandes pesos, que produzcan trepidaci&oacute;n, estar de pie durante largo tiempo o que act&uacute;en o puedan alterar su estado ps&iacute;quico y nervioso.</li>
    <br />
    <li>Disfrutar&aacute;n de un descanso de seis semanas anteriores y seis posteriores al parto. A solicitud expresa de la trabajadora, previa autorizaci&oacute;n escrita del m&eacute;dico de la instituci&oacute;n de seguridad social que le corresponda o, en su caso, del servicio de salud que otorgue el patr&oacute;n, tomando en cuenta la opini&oacute;n del patr&oacute;n y la naturaleza del trabajo que desempe&ntilde;e, se podr&aacute; transferir hasta cuatro de las seis semanas de descanso previas al parto para despu&eacute;s del mismo.  <br />
    <br />
    En caso de que los hijos hayan nacido con cualquier tipo de discapacidad o requieran atenci&oacute;n m&eacute;dica hospitalaria, el descanso podr&aacute; ser de hasta ocho semanas posteriores al parto, previa presentaci&oacute;n del certificado m&eacute;dico correspondiente. En caso de que se presente autorizaci&oacute;n de m&eacute;dicos particulares, &eacute;sta deber&aacute; contener el nombre y n&uacute;mero de c&eacute;dula profesional de quien los expida, la fecha y el estado m&eacute;dico de la trabajadora.</li>
    <br />
    <li>Bis. En caso de adopci&oacute;n de un infante disfrutar&aacute;n de un descanso de seis semanas con goce de sueldo, posteriores al d&iacute;a en que lo reciban.</li>
    <br />
    <li>Los per&iacute;odos de descanso a que se refiere la fracci&oacute;n anterior se prorrogar&aacute;n por el tiempo necesario en el caso de que se encuentren imposibilitadas para trabajar a causa del embarazo o del parto.</li>
    <br />
    <li>En el per&iacute;odo de lactancia hasta por el t&eacute;rmino m&aacute;ximo de seis meses, tendr&aacute;n dos reposos extraordinarios por d&iacute;a, de media hora cada uno, para alimentar a sus hijos, en lugar adecuado e higi&eacute;nico que designe la empresa, o bien, cuando esto no sea posible, previo acuerdo con el patr&oacute;n se reducir&aacute; en una hora su jornada de trabajo durante el per&iacute;odo se&ntilde;alado.</li>
    <br />
    <li>Durante los per&iacute;odos de descanso a que se refiere la fracci&oacute;n II, percibir&aacute;n su salario &iacute;ntegro. En los casos de pr&oacute;rroga mencionados en la fracci&oacute;n III, tendr&aacute;n derecho al 50% de su salario por un per&iacute;odo no mayor de sesenta d&iacute;as.</li>
    <br />
    <li>Al regresar al puesto que desempe&ntilde;aban, siempre que no haya transcurrido m&aacute;s de un a&ntilde;o de la fecha del parto; y</li>
    <br />
    <li class="no_line">A que se computen en su antig&uuml;edad los per&iacute;odos pre y postnatales.</li>
</ol>
<p><strong>&iquest;Cu&aacute;nto tiempo deben durar los per&iacute;odos de lactancia a los que tengo derecho?</strong></p>
<p>En el per&iacute;odo de lactancia hasta por el t&eacute;rmino m&aacute;ximo de seis meses, tendr&aacute;n dos reposos extraordinarios por d&iacute;a, de media hora cada uno, para alimentar a sus hijos, en un lugar adecuado e higi&eacute;nico que designe la empresa, o bien, cuando esto no sea posible. Mediante un previo acuerdo con el patr&oacute;n, se reducir&aacute; en una hora su jornada de trabajo durante el per&iacute;odo se&ntilde;alado.</p>
<p><strong>&iquest;Durante cu&aacute;nto tiempo mi patr&oacute;n me debe de otorgar los dos reposos extraordinarios en el per&iacute;odo de lactancia?</strong></p>
<p>La Ley Federal del Trabajo, al se&ntilde;alar los dos descansos de media hora de la madre trabajadora para alimentar a sus hijos, hasta por el t&eacute;rmino m&aacute;ximo de seis meses.</p>
<p><strong>&iquest;Tengo derecho a que se me otorgue alg&uacute;n permiso por el nacimiento de mis hijos siendo hombre?</strong></p>
<p>S&iacute;, la Ley Federal del Trabajo se&ntilde;ala que son obligaciones de los patrones: otorgar el permiso de paternidad de cinco d&iacute;as laborables con goce de sueldo, a los hombres trabajadores, por el nacimiento de sus hijos y de igual manera en el caso de la adopci&oacute;n de un infante.</p>
<p><strong>&iquest;El patr&oacute;n tiene la obligaci&oacute;n de pagarme los primeros tres d&iacute;as de incapacidad por enfermedad general que no me pag&oacute; el Instituto Mexicano del Seguro Social (IMSS)?</strong></p>
<p>No, la Ley Federal del Trabajo en su art&iacute;culo 42 y la del Seguro Social, no establecen tal obligaci&oacute;n a cargo del patr&oacute;n, por lo cual no procede exigirle a &eacute;ste dicho pago.</p>
<p><strong>Estoy de incapacidad para laborar expedida por el Instituto Mexicano del Seguro Social por una enfermedad del orden general, &iquest;mi patr&oacute;n me puede despedir en el per&iacute;odo de incapacidad?</strong></p>
<p>No, porque se encuentra suspendida la relaci&oacute;n de trabajo, por lo que se detiene de manera provisional las obligaciones de prestar el servicio por parte del trabajador y de pagar el salario por parte del patr&oacute;n. La suspensi&oacute;n surte efectos desde que se produjo la incapacidad hasta que desaparezca la inhabilidad para el trabajo, la cual no puede exceder del t&eacute;rmino fijado por la Ley del Seguro Social para el tratamiento de enfermedad que no derive de un riesgo de trabajo, es decir, de 52 semanas, prorrogable hasta por otras 52 semanas.</p>
<p><strong>&iquest;Tengo derecho a que mi patr&oacute;n me otorgue d&iacute;as de descanso por haber contra&iacute;do matrimonio?</strong></p>
<p>La Ley Federal del Trabajo no regula al respecto; sin embargo, en algunos contratos colectivos de trabajo, reglamentos interiores de trabajo o condiciones generales de trabajo, se establece el derecho a determinados d&iacute;as.</p>
<p>La Ley del Seguro Social establece una prestaci&oacute;n denominada ayuda para gastos de matrimonio en la que el asegurado tiene derecho a retirar, de la cuota social aportada del Gobierno Federal en su cuenta individual, una cantidad equivalente a 30 d&iacute;as del salario m&iacute;nimo general que rija en el Distrito Federal, siempre y cuando re&uacute;na los siguientes requisitos: acreditar un m&iacute;nimo de 150 semanas de cotizaci&oacute;n en el Seguro de Retiro, Cesant&iacute;a en Edad Avanzada y Vejez, en la fecha de celebraci&oacute;n del matrimonio.</p>
<p>En caso de haber estado casado con anterioridad, deber&aacute; comprobar la muerte de la persona que se hab&iacute;a registrado ante el IMSS o exhibir el acta de divorcio, y que el c&oacute;nyuge no haya sido registrado con anterioridad en el instituto. Este derecho se ejercer&aacute; por una sola vez y el asegurado no tendr&aacute; derecho por posteriores matrimonios. El asegurado que deje de pertenecer al r&eacute;gimen obligatorio conservar&aacute; sus derechos a la ayuda de gastos de matrimonio, si lo contrae dentro de 90 d&iacute;as contados a partir de la fecha de su baja.</p>
<p><strong>El patr&oacute;n present&oacute; una declaraci&oacute;n de impuestos con la que no estoy de acuerdo, &iquest;qu&eacute; hago?</strong></p>
<p>De acuerdo al art&iacute;culo 121 y dem&aacute;s de la Ley Federal del Trabajo, los trabajadores podr&aacute;n formular objeciones en contra de la declaraci&oacute;n anual del Impuesto Sobre la Renta que su patr&oacute;n present&oacute; ante la Secretar&iacute;a de Hacienda y Cr&eacute;dito P&uacute;blico. Esto, a trav&eacute;s de un escrito en donde se establecer&aacute;n los renglones espec&iacute;ficos de ingresos y de deducciones con cuyo monto, aplicaci&oacute;n o tratamiento no est&eacute;n conformes, especificando las anomal&iacute;as.</p>
<p><strong>&iquest;Qu&eacute; es la prima quinquenal y cu&aacute;ndo procede su pago?</strong></p>
<p>Es una prima que se les paga a los trabajadores sujetos al r&eacute;gimen de la Ley Burocr&aacute;tica y su pago procede por cada cinco a&ntilde;os de servicios, efectivamente prestados, hasta llegar a 25, y su monto se determina en el presupuesto de egresos correspondiente.</p>
<p><strong>&iquest;La prima quinquenal y la prima de antig&uuml;edad son lo mismo?</strong></p>
<p>No, porque son de naturaleza distinta, de acuerdo al criterio sustentado por la Suprema Corte de Justicia de la Naci&oacute;n.</p>
<p><strong>&iquest;Mis derechos labores se ven perjudicados en el caso de que se traspase la empresa a la que presto mis servicios y cambie de raz&oacute;n social?</strong></p>
<p>El cambio de propietario de una empresa ya sea por venta, cesi&oacute;n, traspaso o fusi&oacute;n no puede afectar en nada los derechos y condiciones de trabajo de los empleados, tanto sindicalizados como de confianza, lo que significa que la relaci&oacute;n laboral se establece entre el trabajador y la fuente de trabajo, en este caso espec&iacute;fico.</p>
<p>De no ser as&iacute;, el nuevo patr&oacute;n desconocer&iacute;a, sin ninguna dificultad, las obligaciones contra&iacute;das por el anterior, argumentado que &eacute;l no las hab&iacute;a adquirido. Si los empleadores pudieran desligarse de sus obligaciones laborales vendiendo las empresas, el principio de estabilidad en el empleo se encontrar&iacute;a en un dilema. Siempre el patr&oacute;n sustituido ser&aacute; solidariamente responsable con el nuevo patr&oacute;n, por cada una de las obligaciones derivadas de las relaciones de laborales y de la normatividad laboral, nacidas antes de la fecha en que se hubiese dado aviso de la sustituci&oacute;n, hasta por seis meses. Vencido este t&eacute;rmino, el nuevo patr&oacute;n asumir&aacute; la totalidad de la responsabilidad de las obligaciones contra&iacute;das en esa fuente de trabajo.</p>
<p><strong>&iquest;Cu&aacute;ndo se da la sustituci&oacute;n patronal?</strong></p>
<p>La sustituci&oacute;n patronal nace cuando, por cualquier t&iacute;tulo de propiedad, se transmiten los derechos y bienes esenciales de la empresa, con el &aacute;nimo de continuar explot&aacute;ndola, aunque no sea en su totalidad y en cuyo caso el adquiriente asume la categor&iacute;a de nuevo patr&oacute;n, motivo por el cual deber&aacute; reconocer y responder de los derechos y obligaciones pasadas, presentes y futuras derivadas de las relaciones de trabajo.</p>
<p>El cambio de propietario de una empresa ya sea por venta, cesi&oacute;n, traspaso o fusi&oacute;n no puede afectar en nada los derechos y condiciones de trabajo de los empleados, tanto sindicalizados como de confianza, lo que significa que la relaci&oacute;n laboral se establece entre el trabajador y la fuente de trabajo, en este caso espec&iacute;fico. La sustituci&oacute;n patronal no s&oacute;lo se produce cuando se da la transmisi&oacute;n de una empresa en su totalidad, sino tambi&eacute;n cuando lo que cambia de patrimonio en uno de sus establecimientos, sucursales o agencias, que constituya una unidad t&eacute;cnica en s&iacute; misma y que va a funcionar como una nueva empresa. Siempre, el patr&oacute;n sustituido ser&aacute; solidariamente responsable con el nuevo patr&oacute;n por cada una de las obligaciones derivadas de las relaciones laborales y de la normatividad laboral, nacidas antes de la fecha en que se hubiese dado aviso de la sustituci&oacute;n, hasta por seis meses. Vencido este t&eacute;rmino, el nuevo patr&oacute;n asumir&aacute; la totalidad de la responsabilidad de las obligaciones contra&iacute;das en esa fuente de trabajo.</p>
<p>El t&eacute;rmino de seis meses a que se refiere el p&aacute;rrafo anterior, se contar&aacute; a partir de la fecha en que se hubiese dado aviso de la sustituci&oacute;n al sindicato o a los trabajadores.</p>
<p><strong>Acabo de ser despedido, &iquest;qu&eacute; documentos necesito llevar a sus instalaciones?</strong></p>
<p>Tu contrato individual, colectivo de trabajo o contrato ley, comprobantes de salario, aviso de inscripci&oacute;n al Instituto Mexicano del Seguro Social, identificaci&oacute;n oficial y cualquier documento relacionado con tu relaci&oacute;n de trabajo, as&iacute; como la fotocopia de la credencial de tres personas de tu confianza, no familiares y no personas que vivan en el mismo domicilio, y que puedan fungir como testigos en el juicio. Ello, depender&aacute; del caso particular de que se trate.</p>
<p><strong>&iquest;Qu&eacute; es la Indemnizaci&oacute;n Constitucional por despido injustificado?</strong></p>
<p>El trabajador podr&aacute; solicitar ante la Junta de Conciliaci&oacute;n y Arbitraje, a su elecci&oacute;n, que se le reinstale en el trabajo que desempe&ntilde;aba, o que se le indemnice con el importe de tres meses de salario, a raz&oacute;n del que corresponda a la fecha en que se realice el pago.</p>
<p>Si en el juicio correspondiente no comprueba el patr&oacute;n la causa de la rescisi&oacute;n, el trabajador tendr&aacute; derecho, adem&aacute;s, cualquiera que hubiese sido la acci&oacute;n intentada, a que se le paguen los salarios vencidos computados desde la fecha del despido hasta por un per&iacute;odo m&aacute;ximo de 12 meses, en t&eacute;rminos de lo preceptuado en la &uacute;ltima parte del p&aacute;rrafo anterior.</p>
<p>Si al t&eacute;rmino del plazo se&ntilde;alado en el p&aacute;rrafo anterior no ha concluido el procedimiento o no se ha dado cumplimiento al fallo, se pagar&aacute;n tambi&eacute;n al trabajador los intereses que se generen sobre el importe de 15 meses de salario, a raz&oacute;n del 2% mensual, capitalizable al momento del pago. Lo dispuesto en este p&aacute;rrafo no ser&aacute; aplicable para el pago de otro tipo de indemnizaciones o prestaciones.</p>
<p>En caso de muerte del trabajador, dejar&aacute;n de computarse los salarios vencidos como parte del conflicto, a partir de la fecha del fallecimiento.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
