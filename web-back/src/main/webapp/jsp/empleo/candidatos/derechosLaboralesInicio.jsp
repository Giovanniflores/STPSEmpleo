<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Derechos al inicio de la vida laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		 &iquest;Qu&eacute; son las condiciones de trabajo? Las condiciones de trabajo est&aacute;n basadas en el principio de igualdad sustantiva entre mujeres y hombres en ning&uacute;n caso podr&aacute;n ser inferiores a las fijadas en esta Ley y deber&aacute;n ser proporcionales a la importancia de los servicios e iguales para trabajos iguales, sin que puedan establecerse diferencias y/o exclusiones por motivo de origen &eacute;tnico o nacionalidad, sexo, g&eacute;nero, edad, discapacidad, condici&oacute;n social, condiciones de salud, religi&oacute;n, opiniones, preferencias sexuales, condiciones de embarazo responsabilidades familiares o estado civil, salvo las modalidades expresamente consignadas en esta Ley.
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
		
	        <p><strong>&iquest;Qu&eacute; son las condiciones de trabajo?</strong></p>
<p>Las condiciones de trabajo est&aacute;n basadas en el principio de igualdad sustantiva entre mujeres y hombres en ning&uacute;n caso podr&aacute;n ser inferiores a las fijadas en esta Ley y deber&aacute;n ser proporcionales a la importancia de los servicios e iguales para trabajos iguales, sin que puedan establecerse diferencias y/o exclusiones por motivo de origen &eacute;tnico o nacionalidad, sexo, g&eacute;nero, edad, discapacidad, condici&oacute;n social, condiciones de salud, religi&oacute;n, opiniones, preferencias sexuales, condiciones de embarazo responsabilidades familiares o estado civil, salvo las modalidades expresamente consignadas en esta Ley.</p>
<p><strong>&iquest;Qu&eacute; se&ntilde;ala la Ley Federal del Trabajo como Condiciones de Trabajo?</strong></p>
<p>La Ley Federal del Trabajo se&ntilde;ala como condiciones de trabajo la jornada laboral, d&iacute;as de descanso, vacaciones, salario, aguinaldo y participaci&oacute;n de utilidades, sin embargo, no son todas, ya que en la pr&aacute;ctica las autoridades laborales han establecido que tambi&eacute;n la categor&iacute;a del trabajador, las funciones que va a realizar y el lugar de adscripci&oacute;n son consideradas como tales. Las condiciones de trabajo deber&aacute;n ser establecidas en los contratos individuales, colectivos o ley de trabajo.</p>
<p><strong>Cuando empec&eacute; a trabajar para mi patr&oacute;n no firm&eacute; contrato de trabajo, &iquest;tengo derecho a las prestaciones que establece la Ley Federal del Trabajo?</strong></p>
<p>Por supuesto, ya que se presume la existencia del contrato y de la relaci&oacute;n de trabajo entre el que presta un trabajo personal y el que lo recibe.</p>
<p><strong>&iquest;Qu&eacute; se entiende por relaci&oacute;n de trabajo?</strong></p>
<p>Se entiende por relaci&oacute;n de trabajo, cualquiera que sea el acto que le d&eacute; origen, la prestaci&oacute;n de un trabajo personal subordinado a una persona, mediante el pago de un salario.</p>
<p><strong>&iquest;Qu&eacute; es el salario m&iacute;nimo y cu&aacute;l es su valor en la actualidad?</strong></p>
<p>Es la cantidad m&iacute;nima que debe recibir en efectivo el trabajador por los servicios prestados en una jornada de trabajo.</p>
<p>Es de dos tipos: los salarios m&iacute;nimos generales, que son los que se toman para todos los trabajadores en una o varias &aacute;reas geogr&aacute;ficas, con independencia de la rama industrial o del comercio en que laboren, de la profesi&oacute;n o trabajo especial que desempe&ntilde;en, y el cual se puede extender a una o m&aacute;s entidades federativas; y los salarios m&iacute;nimos profesionales que rigen para quienes prestan sus servicios en una rama determinada de la industria o del comercio y para profesiones, oficios o trabajos especiales, dentro de una o varias &aacute;reas geogr&aacute;ficas. Estos salarios son establecidos por la Comisi&oacute;n Nacional de los Salarios M&iacute;nimos, &oacute;rgano tripartito que se constituye con representantes de los trabajadores, de los patrones y del gobierno federal. En la actualidad est&aacute;n vigentes a partir del 1 de enero de 2015 dos tipos de salario m&iacute;nimo: en el &aacute;rea A, 70.10; &aacute;rea B, 66.45 .</p>
<p><strong>&iquest;Si percibo como salario por cuota diaria el salario m&iacute;nimo general, estoy obligado a pagar el impuesto sobre la renta?</strong></p>
<p>De acuerdo al Art&iacute;culo 93 de la Ley del Impuesto sobre la renta, NO se pagar&aacute; el impuesto sobre la renta por la obtenci&oacute;n de los siguientes ingresos:</p>
<p>Las prestaciones distintas del salario que reciban los trabajadores del salario m&iacute;nimo general para una o varias &aacute;reas geogr&aacute;ficas, calculadas sobre la base de dicho salario, cuando no excedan de los m&iacute;nimos se&ntilde;alados por la legislaci&oacute;n laboral, as&iacute; como las remuneraciones por concepto de tiempo extraordinario o de prestaci&oacute;n de servicios que se realice en los d&iacute;as de descanso sin disfrutar de otros en sustituci&oacute;n, hasta el l&iacute;mite establecido en la legislaci&oacute;n laboral, que perciban dichos trabajadores. Trat&aacute;ndose de los dem&aacute;s trabajadores, el 50% de las remuneraciones por concepto de tiempo extraordinario o de la prestaci&oacute;n de servicios que se realice en los d&iacute;as de descanso sin disfrutar de otros en sustituci&oacute;n, que no exceda el l&iacute;mite previsto en la legislaci&oacute;n laboral y sin que esta exenci&oacute;n exceda del equivalente de cinco veces el salario m&iacute;nimo general del &aacute;rea geogr&aacute;fica del trabajador por cada semana de servicios.</p>
<p><strong>&iquest;Qu&eacute; es una jornada de trabajo?</strong></p>
<p>Jornada de trabajo es el tiempo durante el cual el trabajador est&aacute; a disposici&oacute;n del patr&oacute;n para prestar su trabajo. El trabajador y el patr&oacute;n fijar&aacute;n la duraci&oacute;n de la jornada de trabajo, sin que pueda exceder los m&aacute;ximos legales.</p>
<p><strong>&iquest;Cu&aacute;l es la duraci&oacute;n m&aacute;xima de la jornada de trabajo?</strong></p>
<p>La duraci&oacute;n m&aacute;xima de la jornada ser&aacute;: ocho horas la diurna, siete la nocturna y siete horas y media la mixta.</p>
<p><strong>&iquest;Si se excede la jornada m&aacute;xima de trabajo a que tengo derecho?</strong></p>
<p>Si la jornada de trabajo se prolonga por circunstancias extraordinarias, sin exceder nunca de tres horas diarias ni de tres veces en una semana, se pagar&aacute;n con un ciento por ciento m&aacute;s del salario que corresponda a las horas de la jornada.</p>
<p><strong>&iquest;Qu&eacute; retribuci&oacute;n me debe cubrir el patr&oacute;n si la jornada extraordinaria excede de nueve horas?</strong></p>
<p>La prolongaci&oacute;n del tiempo extraordinario que exceda de nueve horas a la semana, obliga al patr&oacute;n a pagar al trabajador el tiempo excedente con un doscientos por ciento m&aacute;s del salario que corresponda a las horas de la jornada, sin perjuicio de las sanciones establecidas en la Ley Federal del Trabajo.</p>
<p><strong>&iquest;El descanso que me corresponde a la semana tiene que ser necesariamente el domingo?</strong></p>
<p>En los reglamentos de la Ley Federal del Trabajo se establece que se procurar&aacute; que el d&iacute;a de descanso semanal sea el domingo, sin embargo el d&iacute;a de descanso semanal puede ser cualquier d&iacute;a que se pacte en el contrato individual de trabajo o aquellos se&ntilde;alados en los Contratos Colectivos o Ley.</p>
<p><strong>&iquest;Estoy obligado a trabajar el d&iacute;a de mi descanso semanal?</strong></p>
<p>Los trabajadores no est&aacute;n obligados a prestar servicios en sus d&iacute;as de descanso. Si se quebranta esta disposici&oacute;n, el patr&oacute;n pagar&aacute; al trabajador, independientemente del salario que le corresponda por el descanso, un salario doble por el servicio prestado.</p>
<p><strong>&iquest;Cu&aacute;les son los d&iacute;as de descanso obligatorio que establece la Ley Federal del Trabajo?</strong></p>
<p>La Ley Federal del Trabajo se&ntilde;ala como descanso obligatorio siete d&iacute;as fijos al a&ntilde;o, en su Art&iacute;culo 74:</p>
<ul class="list-group-contenidos">
    <li>I. El 1o. de enero;</li>
    <li>II. El primer lunes de febrero en conmemoraci&oacute;n del 5 de febrero;</li>
    <li>III. El tercer lunes de marzo en conmemoraci&oacute;n del 21 de marzo;</li>
    <li>IV. El 1o. de mayo;</li>
    <li>V. El 16 de septiembre</li>
    <li>VI. El tercer lunes de noviembre en conmemoraci&oacute;n del 20 de noviembre;</li>
    <li>VII. El 1o. de diciembre de cada seis a&ntilde;os, cuando corresponda a la transmisi&oacute;n del Poder Ejecutivo Federal;</li>
    <li>VIII. El 25 de diciembre, y</li>
    <li>IX. El que determinen las leyes federales y locales electorales, en el caso de elecciones ordinarias, para efectuar la jornada electoral.</li>
</ul>
<p><strong>&iquest;Si labor&eacute; en un d&iacute;a de descanso obligatorio a que tengo derecho?</strong></p>
<p>Con base en el Art&iacute;culo 75 de la Ley Laboral, el pago que corresponde a los trabajadores que presten sus servicios en los descansos obligatorios, independientemente del salario que les corresponde por este descanso, percibir&aacute; un salario doble. Los trabajadores no est&aacute;n obligados a prestar sus servicios en los descansos obligatorios, salvo que as&iacute; lo hayan pactado con su patr&oacute;n, por lo que el incumplimiento de esta obligaci&oacute;n pactada puede conducir a la rescisi&oacute;n de la relaci&oacute;n de trabajo, salvo causa justificada.</p>
<p><strong>&iquest;Qu&eacute; es la Indemnizaci&oacute;n Constitucional por despido injustificado?</strong></p>
<p>La indemnizaci&oacute;n constitucional es aquella que se cubre a los trabajadores  por despido injustificado, la cual se consta del importe de tres meses de salario, a raz&oacute;n del que corresponda a la fecha en que se realice el pago, si en el juicio correspondiente no comprueba el patr&oacute;n la causa de la rescisi&oacute;n, el trabajador tendr&aacute; derecho, adem&aacute;s, cualquiera que hubiese sido la acci&oacute;n intentada, a que se le paguen los salarios vencidos computados desde la fecha del despido hasta por un per&iacute;odo m&aacute;ximo de doce meses, si al t&eacute;rmino del plazo se&ntilde;alado en el p&aacute;rrafo anterior no ha concluido el procedimiento o no se ha dado cumplimiento al laudo, se pagar&aacute;n tambi&eacute;n al trabajador los intereses que se generen sobre el importe de quince meses de salario, a raz&oacute;n del dos por ciento mensual, capitalizable al momento del pago, asimismo se cubrir&aacute; el pago de las prestaciones devengadas y no cubiertas o aquellas que otorgue expresamente la ley o los contratos de trabajo.</p>
<p><strong>&iquest;Tengo derecho al pago del aguinaldo?</strong></p>
<p>Todos los trabajadores, sin excepci&oacute;n, tienen el derecho a recibir aguinaldo. No se necesita trabajar el a&ntilde;o completo ni estar laborando en la fecha del pago de esta prestaci&oacute;n. El trabajador tendr&aacute; derecho a que se le pague la parte proporcional del aguinaldo que corresponda al tiempo que labor&oacute; en el a&ntilde;o y ser&aacute; equivalente a, por lo menos, quince d&iacute;as de salario, mismo que deber&aacute; ser entregado antes del 20 de diciembre. Esta prestaci&oacute;n es una obligaci&oacute;n exigible jur&iacute;dicamente en caso de que no sea pagada. El salario que sirve de base para calcular el aguinaldo es el que ordinariamente se percibe por d&iacute;a laborado, y no con el salario integrado. El tiempo que dispone el trabajador para reclamar el pago de esta prestaci&oacute;n es de un a&ntilde;o, contado a partir del d&iacute;a siguiente a la fecha en que la obligaci&oacute;n se hace exigible.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
