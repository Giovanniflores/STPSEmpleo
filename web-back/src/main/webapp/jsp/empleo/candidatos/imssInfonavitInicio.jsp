<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">IMSS e Infonavit al inicio de la vida laboral</jsp:attribute>
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
          <li><a href="<c:url value="/jsp/empleo/candidatos/alInicioVidaLaboral.jsp"/>">Al inicio de la vida laboral</a></li>
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
		
	        <p><strong>&iquest;Al ingresar a trabajar para una empresa, bajo qu&eacute; r&eacute;gimen del seguro social me tienen que dar de alta?</strong></p>
<p>Bajo el r&eacute;gimen obligatorio que marca la Ley del Seguro Social, ya que &eacute;ste se encuentra establecido para las personas que de conformidad con los art&iacute;culos 20 y 21 de la Ley Federal del Trabajo, presten, en forma permanente o eventual, a otras de car&aacute;cter f&iacute;sico o moral o unidades econ&oacute;micas sin personalidad jur&iacute;dica, un servicio remunerado, personal y subordinado, cualquiera que sea el acto que le d&eacute; origen y cualquiera que sea la personalidad jur&iacute;dica o la naturaleza econ&oacute;mica del patr&oacute;n aun cuando &eacute;ste, en virtud de alguna ley especial, est&eacute; exento del pago de contribuciones.</p>
<p><strong>&iquest;Qu&eacute; puedo hacer si el patr&oacute;n no me da de alta?</strong></p>
<p>Podr&aacute;s acudir al departamento de Auditoria Patrones del Instituto Mexicano del Seguro Social (IMSS) a presentar tu queja en contra del patr&oacute;n, debido a que la Ley del IMSS se&ntilde;ala que: &ldquo;Los trabajadores tienen el derecho de solicitar al Instituto su inscripci&oacute;n, comunicar las modificaciones de su salario y dem&aacute;s condiciones de trabajo y, en su caso, presentar la documentaci&oacute;n que acredite dicha relaci&oacute;n, demuestre el per&iacute;odo laborado y los salarios percibidos. Lo anterior no libera a los patrones del cumplimiento de sus obligaciones ni les exime de las sanciones y responsabilidades en que hubieran incurrido.</p>
<p><strong>Nunca he tenido Seguro Social porque soy comerciante, pero quiero obtener atenci&oacute;n m&eacute;dica por parte del IMSS, &iquest;qu&eacute; puedo hacer?</strong></p>
<p>De conformidad con la Ley del Seguro Social, voluntariamente podr&aacute;n ser sujetos de aseguramiento al r&eacute;gimen obligatorio, los trabajadores en industrias familiares y los independientes, como profesionales, comerciantes en peque&ntilde;o, artesanos y dem&aacute;s trabajadores no asalariados, mediante convenio con el Instituto se establecer&aacute;n las modalidades y fechas de incorporaci&oacute;n al r&eacute;gimen obligatorio, de los sujetos de aseguramiento.</p>
<p>En los convenios a que se refiere el art&iacute;culo anterior se establecer&aacute;:</p>
<ul class="list-group-contenidos">
    <li>I. La fecha de inicio de la prestaci&oacute;n de los servicios y los sujetos de aseguramiento que comprende;</li>
    <li>II. La vigencia;</li>
    <li>III. Las prestaciones que se otorgar&aacute;n;</li>
    <li>IV. Las cuotas a cargo de los asegurados y dem&aacute;s sujetos obligados;</li>
    <li>V. La contribuci&oacute;n a cargo del Gobierno Federal, cuando en su caso proceda;</li>
    <li>VI. Los procedimientos de inscripci&oacute;n y los de cobro de las cuotas, y</li>
    <li>VII. Las dem&aacute;s modalidades que se requieran conforme a la Ley del Seguro Social y sus reglamentos.</li>
</ul>
<p><strong>&iquest;Sin ser asegurado existe alg&uacute;n seguro que solo me cubra enfermedades y maternidad en el IMSS?</strong></p>
<p>S&iacute;, el seguro para la familia en el cual podr&aacute;s recibir las prestaciones en especie del Seguro de Enfermedades y Maternidad, es decir, la asistencia m&eacute;dica, quir&uacute;rgica, farmac&eacute;utica y hospitalaria que sea necesaria desde el comienzo de la enfermedad y durante el plazo de 52 semanas para el mismo padecimiento, as&iacute; como asistencia obst&eacute;trica y ayuda en especie por seis meses para lactancia. Los sujetos amparados por este seguro de salud para la familia son: el asegurado, la esposa del asegurado, a falta de &eacute;sta, la mujer con quien haya hecho vida marital durante los cinco a&ntilde;os anteriores a la enfermedad o con la que haya procreado hijos, siempre que ambos permanezcan libres de matrimonio, el esposo de la asegurada o a falta de &eacute;ste el concubinario, siempre que dependa econ&oacute;micamente de la asegurada, los hijos menores de 16 a&ntilde;os del asegurado hasta la edad de 25 a&ntilde;os cuando realicen estudios en planteles del sistema educativo nacional, los hijos del asegurado cuando no puedan mantenerse por su propio trabajo debido a una enfermedad cr&oacute;nica o defecto f&iacute;sico y el padre y la madre del pensionado que vivan y dependan econ&oacute;micamente del asegurado. Este seguro podr&aacute; extenderse a los familiares que vivan con el asegurado y dependan econ&oacute;micamente de &eacute;ste. En dicha incorporaci&oacute;n deber&aacute;n de pagar anualmente una cuota equivalente al 22.4% de un salario m&iacute;nimo general diario para el Distrito Federal. Por cada familiar adicional se pagar&aacute; una cuota equivalente al 75% de la que corresponde a este seguro.</p>
<p><strong>&iquest;Por qu&eacute; mi patr&oacute;n me paga un salario inferior al que tengo registrado en el IMSS?</strong></p>
<p>Esto es porque en el IMSS se registra el salario base de cotizaci&oacute;n, el cual se integra con los pagos hechos en efectivo por cuota diaria y las gratificaciones, percepciones, alimentaci&oacute;n, habitaci&oacute;n, primas, comisiones, prestaciones en especie y cualquier otra cantidad o prestaci&oacute;n que se entregue al trabajador por sus servicios, siendo mayor este salario base de cotizaci&oacute;n al diario pagado por el patr&oacute;n, porque este &uacute;ltimo es el que percibes por cuota diaria &uacute;nicamente.</p>
<p><strong>Estoy de vacaciones y sufr&iacute; un accidente, &iquest;qu&eacute; pasa con mis vacaciones si tengo incapacidades expedidas por el IMSS?</strong></p>
<p>En primer t&eacute;rmino, las vacaciones no se suspenden sino que contin&uacute;an, y si al t&eacute;rmino de &eacute;stas sigues incapacitado para poder laborar, deber&aacute;s presentar a tu patr&oacute;n tu incapacidad al siguiente d&iacute;a en que termin&oacute; tu periodo vacacional para efectos de que no se consideren como falta los d&iacute;as de incapacidad.</p>
<p><strong>&iquest;Cu&aacute;les son las cuotas que debo pagar en los seguros de Invalidez y Vida, Retiro, Cesant&iacute;a en Edad Avanzada y Vejez, si decido inscribirme de manera voluntaria en el r&eacute;gimen obligatorio?</strong></p>
<p>Si se trata del seguro de Retiro, Cesant&iacute;a en Edad Avanzada o Vejez, el asegurado (ex trabajador) cubrir&aacute; por cuanto hace al ramo de retiro, la totalidad de la cuota (2% del salario base de cotizaci&oacute;n) y por lo que hace a los otros dos ramos (Cesant&iacute;a en Edad Avanzada o Vejez) cubrir&aacute; el importe de las cuotas obrero patronales (1.125% y el 3.150% sobre el salario base de cotizaci&oacute;n, que es la aportaci&oacute;n que le corresponde al trabajador y al patr&oacute;n, respectivamente), debiendo el Estado aportar la parte que le corresponda incluyendo la cuota social. En el Seguro de Invalidez y Vida, el asegurado cubrir&aacute; las cuotas obrero patronales (.625% y el 1.65% sobre el salario base de cotizaci&oacute;n, que es la aportaci&oacute;n que le corresponde al trabajador y al patr&oacute;n respectivamente) y el Estado la parte que le corresponda.</p>
<p><strong>&iquest;Por qu&eacute; debo contratar un seguro de sobrevivencia?</strong></p>
<p>Para proteger a tus beneficiarios y otorgarles la pensi&oacute;n, ayuda asistencial y dem&aacute;s prestaciones en dinero previstas en los seguros de Cesant&iacute;a en Edad Avanzada o Vejez, Invalidez y Vida y Riesgos de trabajo, mediante la renta (pensi&oacute;n) que se le asignar&aacute; despu&eacute;s de su fallecimiento.</p>
<p><strong>&iquest;Qu&eacute; tipo de cr&eacute;dito otorga el INFONAVIT?</strong></p>
<p>El Instituto del Fondo Nacional de la Vivienda para los Trabajadores (INFONAVIT) otorga cr&eacute;dito en tres l&iacute;neas diferentes para: a) la adquisici&oacute;n en propiedad de casa habitaci&oacute;n c&oacute;moda e higi&eacute;nica, b) la construcci&oacute;n, reparaci&oacute;n, ampliaci&oacute;n o mejoramiento de sus habitaciones y c) el pago de pasivos contra&iacute;dos por los conceptos anteriores.</p>
<p><strong>&iquest;Cu&aacute;l es el salario con el que me debe inscribir mi patr&oacute;n en el INFONAVIT?</strong></p>
<p>Los patrones inscribir&aacute;n a sus trabajadores con el salario que perciban al momento de su inscripci&oacute;n; el salario a que se refiere se integra con los pagos hechos en efectivo por cuota diaria, y las gratificaciones, percepciones, alimentaci&oacute;n, habitaci&oacute;n, primas, comisiones, prestaciones en especie y cualquier otra cantidad o prestaci&oacute;n que se entregue al trabajador por sus servicios.</p>
<p><strong>&iquest;Ante quien tengo que acudir si mi patr&oacute;n no me registra en el INFONAVIT?</strong></p>
<p>En el caso de que el patr&oacute;n no cumpla con la obligaci&oacute;n de inscribir al trabajador, o de enterar al Instituto las aportaciones y descuentos a los salarios, los trabajadores tienen derecho de acudir al INFONAVIT y proporcionarle los informes correspondientes; sin que ello releve al patr&oacute;n del cumplimiento de su obligaci&oacute;n y lo exima de las sanciones en que hubiere incurrido.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
