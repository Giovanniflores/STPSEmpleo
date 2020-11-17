<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">AFORE al inicio de la vida laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
    <jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Una Administradora de Fondos para el Retiro (AFORE) es una empresa especializada en la administraci&oacute;n de las aportaciones de los trabajadores por medio de cuentas individuales. Las AFORES tienen personalidad jur&iacute;dica y patrimonio propio, y para su constituci&oacute;n y funcionamiento requieren de autorizaci&oacute;n de la Comisi&oacute;n Nacional del Sistema de Ahorro para el Retiro (CONSAR).
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
          <li><a href="<c:url value="/jsp/empleo/candidatos/alInicioVidaLaboral.jsp"/>">Al inicio de la vida laboral</a></li>
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
		
	        <p><strong>&iquest;Qu&eacute; es una AFORE?</strong></p>
<p>Una Administradora de Fondos para el Retiro (AFORE) es una empresa especializada en la administraci&oacute;n de las aportaciones de los trabajadores por medio de cuentas individuales. Las AFORES tienen personalidad jur&iacute;dica y patrimonio propio, y para su constituci&oacute;n y funcionamiento requieren de autorizaci&oacute;n de la Comisi&oacute;n Nacional del Sistema de Ahorro para el Retiro (CONSAR).</p>
<p><strong>&iquest;C&oacute;mo puedo inscribirme a una AFORE?</strong></p>
<p>Lo puedes hacer de dos formas:</p>
<p>a) A trav&eacute;s de un agente promotor que cuente con los conocimientos necesarios para informarte detalladamente sobre los beneficios y servicios que ofrece la AFORE. Tambi&eacute;n deber&aacute; informarte sobre las comisiones que te cobrar&aacute; por administrar tus recursos. Debe contar con un registro otorgado por la CONSAR para realizar sus funciones y no deber&aacute; recibir dinero alguno de tu parte por sus servicios.</p>
<p>b) Directamente en las oficinas de la AFORE elegida. Para registrarte, deber&aacute;s llenar y firmar una solicitud que deber&aacute; ir firmada por el agente promotor que la recibe. Una vez entregada, la administradora verificar&aacute; a la empresa operadora de la base de datos nacional del SAR para que la certifique. Si tu solicitud fue aceptada, la AFORE enviar&aacute; la constancia dentro de los 20 d&iacute;as h&aacute;biles posteriores a la firma de la solicitud de registro.</p>
<p><strong>&iquest;Qu&eacute; recibe la AFORE por administrar mis recursos de la cuenta individual?</strong></p>
<p>Las AFORES reciben comisiones que cobran directamente al trabajador. &Eacute;stas podr&aacute;n cobrarse con relaci&oacute;n al valor de los activos administrados en la cuenta o sobre el flujo de cuotas y aportaciones recibidas, o una combinaci&oacute;n de ambas, en forma de una cuota fija para determinados servicios. Las administradoras s&oacute;lo podr&aacute;n cobrar a los trabajadores con cuenta individual las comisiones con cargo a esas cuentas que establezcan de conformidad con las reglas de car&aacute;cter general que expida la Comisi&oacute;n.</p>
<p><strong>&iquest;Qu&eacute; tipo de comisiones puede cobrarme la AFORE a la cual estoy afiliado?</strong></p>
<p>Comisi&oacute;n por aportaci&oacute;n: implica que por el manejo del Fondo de Ahorro para el Retiro se cobrar&aacute; una comisi&oacute;n que recae sobre el salario bimestral del trabajador. Suponiendo que el trabajador gane un salario de 100 pesos al mes, la AFORE tiene derecho a cobrar un porcentaje bimestral de este salario.</p>
<p>Comisi&oacute;n por saldo anual: de las aportaciones anuales que tenga el trabajador dentro de su Fondo de Ahorro para el Retiro, est&aacute; autorizado cobrar un porcentaje. Al igual que una instituci&oacute;n financiera le cobra al cliente un porcentaje anual por el manejo de su chequera, la AFORE podr&aacute; cobrar este tipo de cargos administrativos.</p>
<p>Otras comisiones autorizadas: por consultas o servicios adicionales y extempor&aacute;neos, la CONSAR autoriza a las AFORES a cobrar un porcentaje o un cargo en efectivo a cada trabajador que solicite conocer su saldo, la impresi&oacute;n de su saldo, saber el total de sus aportaciones personales, expedici&oacute;n de estados de cuentas adicionales, reposici&oacute;n de documentaci&oacute;n de la cuenta individual a los trabajadores, pagos de retiros, traspasos y dep&oacute;sitos o retiros de las subcuentas de ahorro voluntarios.</p>
<p><strong>&iquest;Est&aacute; seguro mi dinero en el sistema de ahorro para el retiro? &iquest;No corro ning&uacute;n riesgo?</strong></p>
<p>S&iacute;, est&aacute; seguro, ya que si una AFORE llegara a quebrar, los recursos de los trabajadores afiliados a ella no peligran y se transferir&aacute;n a otra, sin ninguna p&eacute;rdida o riesgo para el trabajador.</p>
<p><strong>&iquest;C&oacute;mo puedo seleccionar una AFORE?</strong></p>
<p>La selecci&oacute;n es una decisi&oacute;n personal. A tu patr&oacute;n no le complica el pago de las aportaciones si optas por ir a una AFORE diferente a la del banco donde se ven&iacute;an haciendo las aportaciones del SAR, porque los bancos continuar&aacute;n actuando como entidades receptoras de aportaciones y en un solo dep&oacute;sito tu patr&oacute;n podr&aacute; entregar las aportaciones de sus trabajadores, aunque entre ellos haya afiliados a las 17 AFORES. Para seleccionarla, debes elegir con cuidado aquellas que m&aacute;s atiendan tus necesidades, aunque de momento no hay muchos elementos para diferenciarlas, excepto el nivel de servicios y el de comisiones cobradas. En general, se recomienda calcular cu&aacute;nto en pesos y centavos te costar&aacute; al a&ntilde;o cada AFORE, dado tu nivel de ingresos y el monto del fondo que ya tienes acumulado.</p>
<p><strong>&iquest;Qu&eacute; es la cuenta individual?</strong></p>
<p>Es aquella que se abrir&aacute; para cada asegurado (trabajador) en las administradoras de fondos para el retiro para que se depositen las cuotas obrero-patronales y estatales por concepto del Seguro de Retiro, Cesant&iacute;a en Edad Avanzada y Vejez, as&iacute; como los rendimientos.</p>
<p><strong>&iquest;C&oacute;mo se integra la cuenta individual de los trabajadores?</strong></p>
<p>La cuenta individual est&aacute; integrada por tres subcuentas: subcuenta de RCV (Retiro, Cesant&iacute;a, Vejez), subcuenta de vivienda y subcuenta de aportaciones voluntarias.</p>
<p><strong>&iquest;Qu&eacute; pasa si no hago el traspaso de mi saldo de la cuenta individual a la AFORE?</strong></p>
<p>Una vez que elijas una AFORE, los recursos acumulados en la subcuenta del SAR hasta esa fecha se traspasar&aacute;n a una cuenta individual que administrar&aacute; la AFORE. Para los trabajadores que no decidan por alguna del mercado, el Gobierno traspasar&aacute; autom&aacute;ticamente sus recursos a una cuenta concertadora que se abrir&aacute; para tal fin.</p>
<p><strong>&iquest;Cu&aacute;l es mi obligaci&oacute;n ante la AFORE que eleg&iacute;?</strong></p>
<p>Informarle de tus cambios de domicilio.</p>
<p><strong>&iquest;Puedo tener m&aacute;s de una AFORE?</strong></p>
<p>No.</p>
<p><strong>&iquest;C&oacute;mo sabr&eacute; cu&aacute;nto tengo acumulado en mi sistema de ahorro para el retiro?</strong></p>
<p>Por ley, la CONSAR establece como m&iacute;nimo que una vez al a&ntilde;o se le env&iacute;e al trabajador un estado de cuenta, que es un documento que contendr&aacute; su informaci&oacute;n personal, as&iacute; como todos los movimientos de aportaciones obreros, patronales, del Estado y las voluntarias, al igual que el detalle de los intereses generados por su ahorro, las comisiones cobradas, los retiros efectuados de sus aportaciones, el traspaso y la unificaci&oacute;n de cuentas y subcuentas.</p>
<p><strong>&iquest;Qu&eacute; es una unificaci&oacute;n de cuentas?</strong></p>
<p>Es el proceso por medio del cual el trabajador integra en una sola cuenta dentro de un mismo banco los saldos acumulados en diferentes cuentas por error u omisi&oacute;n en los datos administrativos del trabajador.</p>
<p><strong>Estoy prestando mis servicios a dos patrones, &iquest;qu&eacute; pasa con los recursos de mi cuenta individual?</strong></p>
<p>En el formato de solicitud de afiliaci&oacute;n se deber&aacute;n registrar los datos de las cuentas individuales del SAR por el patr&oacute;n, es decir, no se generar&aacute;n dos o m&aacute;s cuentas individuales de AFORE, sino que la que administre tu cuenta deber&aacute; conocer esta situaci&oacute;n y efectuar los procesos de unificaci&oacute;n que sean necesarios.</p>
<p><strong>&iquest;Ante qui&eacute;n se debe presentar una reclamaci&oacute;n contra una AFORE?</strong></p>
<p>Las reclamaciones que se tengan deber&aacute;n ser presentadas ante la CONDUSEF. Podr&aacute;n presentarse directamente, mediante el sindicato o a trav&eacute;s de cualquier otra organizaci&oacute;n representativa. La CONDUSEF impondr&aacute; multas y sanciones cuando no se cumpla con lo dispuesto en la ley y atender&aacute; a todo trabajador o patr&oacute;n en cualquier reclamaci&oacute;n o queja por irregularidades que pudieran cometer las AFORES.</p>
<p><strong>Todav&iacute;a no me he registrado a una AFORE, &iquest;qu&eacute; ha pasado con mis aportaciones al Seguro de Retiro, Cesant&iacute;a en Edad Avanzada y Vejez?</strong></p>
<p>La ley contempl&oacute; que para todos aquellos trabajadores que no eligieron AFORE, sus aportaciones, las de sus patrones, las que haga el gobierno federal y los recursos que hasta el momento tengan en su cuenta SAR 92-97, se depositar&iacute;an en una cuenta denominada Cuenta Concentradora hasta el 30 de junio del 2001. Esta cuenta la administrar&iacute;a el Banco de M&eacute;xico a nombre del Instituto Mexicano del Seguro Social. Por lo anterior, se tendr&iacute;a el derecho de registrarse en una AFORE o de permanecer en la cuenta concentradora hasta el 30 de junio del 2001. Una vez transcurrido este plazo, la CONSAR asignar&iacute;a las cuentas de los trabajadores que no hubieran elegido AFORE a determinadas administradoras, atendiendo la eficiencia de &eacute;stas, su situaci&oacute;n financiera, su ubicaci&oacute;n geogr&aacute;fica y los rendimientos de las sociedades de inversi&oacute;n que operen, buscando siempre proteger sus derechos. Para saber a qu&eacute; AFORE fuiste asignado, puedes comunicarte a la CONSAR o a la Asociaci&oacute;n Mexicana de AFORES.</p>
<p><strong>&iquest;Qu&eacute; es una SIEFORE?</strong></p>
<p>Es una sociedad de inversi&oacute;n donde se acumulan los ahorros de los trabajadores con la finalidad de invertirlos de manera segura y rentable.</p>
<p><strong>&iquest;Cu&aacute;l es la relaci&oacute;n entre las AFORES y las SIEFORES?</strong></p>
<p>El patrimonio y monto acumulado en el fondo de ahorro es propiedad exclusiva de los trabajadores. La AFORE puede, previa autorizaci&oacute;n del trabajador, invertir dichos recursos a trav&eacute;s de las SIEFORES de manera que se obtengan intereses y aumenten los recursos.</p>
<p><strong>&iquest;Qu&eacute; es y qu&eacute; hace la CONSAR?</strong></p>
<p>La CONSAR es un &oacute;rgano administrativo desconcentrado de la Secretar&iacute;a de Hacienda y Cr&eacute;dito P&uacute;blico cuya funci&oacute;n y tarea primordial reside en coordinar, regular, inspeccionar, vigilar y supervisar los Sistemas de Ahorro para el Retiro, propiamente a las AFORES. La CONSAR se encargar&aacute; de regular, vigilar e inspeccionar y sancionar su funcionamiento.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
