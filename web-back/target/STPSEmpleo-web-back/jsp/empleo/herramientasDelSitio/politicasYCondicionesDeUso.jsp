<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Pol&iacute;ticas y condiciones de uso</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Las presentes Pol&iacute;ticas y Condiciones de Uso reglamentan el uso de los servicios que proporciona el sitio Web <strong>www.empleo.gob.mx</strong> (en adelante  <strong>Portal del Empleo</strong>) de la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social (en adelante <strong>STPS</strong>). Los servicios que proporciona el <strong>Portal del Empleo</strong> est&aacute;n sujetos al cumplimiento de las presentes Pol&iacute;ticas y Condiciones de Uso por parte de los usuarios.
	</jsp:attribute>
	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	           <li> <a href="<c:url value="/inicio.do"/>">Inicio</a></li>
<!-- 	          <li><a href="#">Inicio</a></li> -->
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li>
	          <li class="active">Políticas y Condiciones de Uso</li>
	        </ol>
	      </div>
	    </div>
	    <!-- /div ruta_navegacion -->
	    
	    <div class="row">
		  <!-- div menu -->
		  <jsp:include page="menu.jsp"/>
		  
		  <!-- div contenido -->
	      <div class="col-sm-8 col-sm-pull-4">
	      
	      	 <jsp:include page="/WEB-INF/template/redes.jsp"/>
	
	        <div class="panel">
	          <div class="panel-heading">
	            <h2 class="titulosh2">Políticas y Condiciones de Uso</h2>
	            <p>Consideraciones generales</p>
	          </div>
	          <style>
	          .condiciones_01 ol > li {border-bottom: 1px solid #dbe0e0; margin-bottom: 10px;}
	          </style>
	          <div class="panel-body condiciones_01">
	            				<ol>
								    <li>Las presentes Pol&iacute;ticas y Condiciones de Uso reglamentan el uso de los servicios que proporciona el sitio Web <strong>www.empleo.gob.mx</strong> (en adelante  <strong>Portal del Empleo</strong>) de la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social (en adelante <strong>STPS</strong>). Los servicios que proporciona el <strong>Portal del Empleo</strong> est&aacute;n sujetos al cumplimiento de las presentes Pol&iacute;ticas y Condiciones de Uso por parte de los usuarios.</li>
								    <li>Las empresas, candidatos de empleo y en general toda persona que ingrese al <strong>Portal del Empleo</strong> por cualquier raz&oacute;n, acepta en forma voluntaria y sin ninguna reserva estar de acuerdo con Pol&iacute;ticas y Condiciones de uso del <strong>Portal del Empleo</strong>, y en caso contrario, tienen derecho a no aceptar su uso y los servicios que el Portal proporciona.</li>
								    <li>La <strong>STPS</strong> no es responsable por el uso de la informaci&oacute;n que se registre en el <strong>Portal del Empleo</strong>, as&iacute; como de cualquier perjuicio originado al usuario por el uso de la misma. La <strong>STPS</strong> se&ntilde;alar&aacute; con claridad las fuentes de informaci&oacute;n y los criterios de confiabilidad que se hubieran tomado en cuenta para la incorporaci&oacute;n de la informaci&oacute;n sobre las ofertas de empleo, por lo que en ning&uacute;n caso ser&aacute; responsable por los juicios de valor o las interpretaciones que los usuarios realicen sobre la exactitud o validez de la informaci&oacute;n publicada, as&iacute; como por cualquier perjuicio o reclamo de cualquier otra &iacute;ndole que pudiera ser imputable al uso de la informaci&oacute;n.</li>
								    <li>La <strong>STPS</strong> no es responsable de la informaci&oacute;n que se muestra en otros sitios web a los que el usuario tiene acceso desde el <strong>Portal del Empleo</strong>. Con el objeto de facilitarle la navegaci&oacute;n y ampliar la informaci&oacute;n ofrecida en el <strong>Portal del Empleo</strong> se establecen enlaces a otras p&aacute;ginas Web. Dado que el <strong>Portal del Empleo</strong>, no es titular, ni responsable de las p&aacute;ginas Web enlazadas, le recomendamos examine la Pol&iacute;tica de uso de &eacute;stas cuando acceda a las mismas. El <strong>Portal del Empleo</strong>, en ning&uacute;n caso ser&aacute; responsable de los contenidos ni de cualquier otro aspecto derivado de las Webs de terceros.</li>
								    <li>La <strong>STPS</strong> se reserva el derecho de suspender el servicio a todos aquellos usuarios que no cumplan con estas Pol&iacute;ticas y Condiciones de Uso.</li>
								    <li>El <strong>Portal del Empleo</strong> se reserva el derecho de modificar y actualizar, cualquier contenido de configuraci&oacute;n o de dise&ntilde;o, cualquier servicio y las presentes Pol&iacute;ticas y Condiciones de Uso en cualquier momento y sin necesidad de previo aviso o notificaci&oacute;n a los usuarios, as&iacute; como a&ntilde;adir servicios nuevos, entendi&eacute;ndose que las mismas son aceptadas si se utilizan los servicios que ofrece el sitio.</li>
								    <li>La <strong>STPS</strong> no es responsable de la veracidad de la informaci&oacute;n publicada en las ofertas de empleo y los perfiles laborales de los candidatos de empleo, as&iacute; como de los resultados finales del contacto entre empresas y candidatos.</li>
								    <li>Queda absolutamente prohibido la utilizaci&oacute;n de los contenidos, logotipos y formatos de registro que utiliza el <strong>Portal del Empleo</strong>, salvo que la <strong>STPS</strong> lo autorice en forma expl&iacute;cita y por escrito.</li>
								    <li>El <strong>Portal del Empleo</strong> no se responsabiliza de la inactividad del sitio web como consecuencia de causas ajenas como cortes de energ&iacute;a el&eacute;ctrica, aver&iacute;a en el hosting, aver&iacute;as o cortes en las redes de comunicaci&oacute;n, y en general debido a cualquier aver&iacute;a o fallo en nuestro sistema inform&aacute;tico, ajenas a nuestra voluntad, comprometi&eacute;ndonos a hacer todo lo que sea posible para restablecer el servicio a la brevedad posible.</li>
								    <li>El usuario deber&aacute; comunicar al <strong>Portal del Empleo</strong> cualquier ilegalidad o anomal&iacute;a que observe, tras estudiarla procederemos en consecuencia con toda la celeridad posible.</li>
								    <li>Lea peri&oacute;dicamente las condiciones de uso, estas, pueden ser modificadas sin previo aviso.</li>
								    <h3>De la secci&oacute;n &ldquo;Candidatos&rdquo;</h3>
								    <li>Los servicios que proporciona el <strong>Portal del Empleo</strong> a los candidatos de empleo del pa&iacute;s son enteramente gratuitos.</li>
								    <li>Para la utilizaci&oacute;n de los servicios que ofrece el <strong>Portal del Empleo</strong>, es necesario que los candidatos se registren en el sitio. Deber&aacute; hacerlo a partir de su Clave &Uacute;nica de Registro Poblacional (CURP) la cu&aacute;l ser&aacute; validada en l&iacute;nea con la base de datos del Registro Nacional de Poblaci&oacute;n (RENAPO). Si no cuenta todav&iacute;a con su CURP, le pedimos se dirija a un M&oacute;dulo CURP para tramitarla. Consulta las oficinas y horarios de atenci&oacute;n haciendo <a href="http://www.gob.mx/tramites/ficha/obtencion-de-la-curp/SEGOB173" target="_blank">clic aqu&iacute;</a>.</li>
								    <li>El ingreso al <strong>Portal del Empleo</strong> ser&aacute; a trav&eacute;s de un nombre de usuario y contrase&ntilde;a los cuales quedar&aacute;n conformados de la siguiente manera:
								    <ul>
								        <li>Nombre de Usuario: Tienes dos alternativas para conformar el Nombre de usuario la primera es usar tu cuenta de correo electr&oacute;nico, si ya tienes una o puedes generarla. La segunda opci&oacute;n es que generes un nombre de usuario de entre 8 y 12 caracteres utilizando letras may&uacute;sculas, letras min&uacute;sculas y n&uacute;meros puedes hacer una combinaci&oacute;n.</li>
								        <li>Contrase&ntilde;a: Se te solicitar&aacute; cada vez que quieras tener acceso a tu Espacio y a tu Perfil Laboral, y debe ser una sola palabra de 8 a 12 caracteres como m&iacute;nimo que pueden ser letras may&uacute;sculas o min&uacute;sculas y/o n&uacute;meros.</li>
								    </ul>
								    </li>
								    <li>Si el resultado de la validaci&oacute;n de la CURP es satisfactorio, podr&aacute;s continuar con el llenado del formato de alta en el <strong>Portal del Empleo</strong>. En caso contrario te pedimos que acudas a un m&oacute;dulo de la CURP para regularizar tu situaci&oacute;n. Consulta las oficinas y horarios de atenci&oacute;n haciendo <a href="http://www.gob.mx/tramites/ficha/obtencion-de-la-curp/SEGOB173" target="_blank">clic aqu&iacute;</a>.</li>
								    <li>El registro de los candidatos s&oacute;lo se hace una vez y nunca se cancela, por lo que si ya estas registrado e intentas realizar otro registro el <strong>Portal del Empleo</strong> no lo permitir&aacute; por duplicidad de la CURP.</li>
								    <li>La cuenta de correo electr&oacute;nico, tiene el car&aacute;cter de nombre de usuario por lo cual se verifica su existencia en nuestra base de datos, si no cuenta con una cuenta de correo electr&oacute;nico propia le sugerimos generar una ya que es el medio por el cual recibir&aacute; ofertas laborales.</li>
								    <li>A partir de ese momento, el candidato de empleo ser&aacute; el &uacute;nico responsable de sus claves de ingreso al <strong>Portal del Empleo</strong> y el uso correcto que se le d&eacute; a las mismas para los fines que est&aacute;n especificados. Es importante que el usuario proteja sus claves, ya que si otras personas hacen un mal uso del <strong>Portal del Empleo</strong> usando la misma clave se corre el riesgo de perder acceso al sitio.</li>
								    <li>Es compromiso de los candidatos de empleo garantizar en todo lo posible la veracidad y autenticidad de sus datos personales y perfil laboral que ingresen al <strong>Portal del Empleo</strong> a trav&eacute;s del formato establecido para ello. Con fundamento en los art&iacute;culos 18 fracciones II y 20 fracciones I y VI de la Ley Federal de Transparencia y Acceso a la Informaci&oacute;n P&uacute;blica Gubernamental, se garantiza la seguridad de los datos personales y s&oacute;lo ser&aacute;n difundidos con el consentimiento de los candidatos.</li>
								    <li>Para mayor seguridad de los candidatos de empleo, &uacute;nicamente empresas que previamente se hayan registrado en el <strong>Portal del Empleo</strong>, podr&aacute;n acceder a sus datos personales y perfil laboral.</li>
								    <li>Los candidatos de empleo podr&aacute;n tener un manejo y control sobre sus datos personales una vez iniciada la sesi&oacute;n en el <strong>Portal del Empleo</strong>.</li>
								    <li>El <strong>Portal del Empleo</strong> te permite mantener confidenciales los datos de tu domicilio y tel&eacute;fono solo debes marcar la opci&oacute;n de confidencialidad correspondiente.</li>
								    <li>En caso de que los candidatos de empleo no cuenten con acceso a Internet en forma personal, podr&aacute;n acudir a las oficinas del Servicio Nacional de Empleo (SNE) de su entidad, donde podr&aacute;n hacer uso de los servicios que ofrece el <strong>Portal del Empleo</strong> sin costo alguno.</li>
								    <li>Es compromiso de todos los candidatos de empleo que hagan uso de los servicios que proporciona el <strong>Portal del Empleo</strong>, que la informaci&oacute;n que se obtenga del sitio se utilice &uacute;nicamente para los prop&oacute;sitos de b&uacute;squeda de empleo.</li>
								    <li>Si alg&uacute;n usuario del <strong>Portal del Empleo</strong> detecta que alguna de las ofertas consultadas no cumple con las presentes Pol&iacute;ticas y Condiciones de Uso, puede dar aviso a la <strong>STPS</strong> a trav&eacute;s de la opci&oacute;n de quejas que se encuentra en el Portal, proporcionando el nombre de la empresa y las ofertas de empleo que no cumplen con lo estipulado.</li>
								    <li>No es responsabilidad de la <strong>STPS</strong> cualquier conflicto laboral, personal, legal o de cualquier otra &iacute;ndole, que pudiera darse entre el candidato de empleo y las empresas que buscan personal a trav&eacute;s del <strong>Portal del Empleo</strong> y/o terceros.</li>
								    <li>La <strong>STPS</strong> no es responsable de la veracidad de la informaci&oacute;n sobre ofertas de empleo que se difunde, ni de los resultados del contacto entre los empleadores y los buscadores de trabajo. En tal sentido, la <strong>STPS</strong> no es responsable de los conflictos laborales, personales, legales y de cualquier otra &iacute;ndole que se susciten entre empleador y buscador de trabajo derivados del contacto que motive la publicaci&oacute;n de las ofertas de empleo en las bolsas de trabajo que colaboran en el Portal.</li>
								    <li>Es posible que algunas empresas publiquen simult&aacute;neamente sus ofertas de empleo tanto en el <strong>Portal del Empleo</strong> como en las otras bolsas de trabajo por Internet a las que se tienen acceso desde la opci&oacute;n &ldquo;Buscar en otras bolsas de trabajo por Internet&rdquo;. El candidato podr&aacute; postularse a la oferta de empleo de su inter&eacute;s por el canal que m&aacute;s le convenga.</li>
								    <li>El perfil laboral registrado en el <strong>Portal del Empleo</strong> tendr&aacute; una vigencia de un a&ntilde;o, posteriores a la &uacute;ltima fecha de actualizaci&oacute;n. En caso de cumplirse con este periodo de vigencia, el perfil del candidato ser&aacute; inactivado y ser&aacute; obligaci&oacute;n del candidato volver a reactivar su perfil laboral.</li>
								    <li>La <strong>STPS</strong> y el <strong>Portal del Empleo</strong> se reservan el derecho de dar de baja definitiva en cualquier momento y sin previo aviso, a los candidatos de empleo que no cumplan con las presentes Pol&iacute;ticas y Condiciones de Uso.</li>
								    <h3>De la secci&oacute;n &ldquo;Empresas&rdquo;</h3>
								    <li>Los servicios que proporciona el <strong>Portal del Empleo</strong> a las empresas del pa&iacute;s son enteramente gratuitos.</li>
								    <li>Para la utilizaci&oacute;n de los servicios que ofrece el <strong>Portal del Empleo</strong>, es necesario que las empresas se registren en el sitio.</li>
								    <li>Ser&aacute;n autorizadas todas aquellas empresas cuya informaci&oacute;n est&eacute; debidamente cumplimentada y que publiquen ofertas de trabajo debidamente sustentadas y reales y que cumplan con lo establecido en las presentes Pol&iacute;ticas y Condiciones de Uso.</li>
								    <li>Todas las empresas que se registren en el <strong>Portal del Empleo</strong> deben de proporcionar al menos un n&uacute;mero de tel&eacute;fono de contacto fijo, ya que ser&aacute; este el medio por el cual se validar&aacute; el registro de la empresa.</li>
								    <li>El <strong>Portal del Empleo</strong> requiere que al registrar su empresa se ingrese como dato obligatorio una cuenta de correo electr&oacute;nico, la cual tiene el car&aacute;cter de nombre de usuario por lo cual se verifica que sea &uacute;nica, si no cuenta con una cuenta de correo electr&oacute;nico propia le solicitamos generar una.&nbsp;<span style="font-size: 9pt; line-height: 107%; font-family: Tahoma, sans-serif; background: whitesmoke;">La contrase&ntilde;a debe ser de 8 a 12 caracteres como m&iacute;nimo y sin espacios, que pueden ser letras may&uacute;sculas o min&uacute;sculas y/o n&uacute;meros. No se admiten letras acentuadas ni caracteres especiales.</span></li>
								    <li>El <strong>Portal del Empleo</strong> requiere que se ingrese como dato obligatorio la fecha de alta constitutiva de la empresa con la &uacute;nica intenci&oacute;n de generar un identificador de empresa y garantizar la confiabilidad del registro.</li>
								    <li>Una vez concluido el registro de su empresa inmediatamente debe proceder a dar de alta una oferta de empleo, ambos registros deben pasar al proceso de validaci&oacute;n realizado por personal del centro de administraci&oacute;n del <strong>Portal del Empleo</strong>. Mientras se lleva a cabo la validaci&oacute;n puede seguir dando de alta ofertas de empleo. Una vez validada la informaci&oacute;n se le dar&aacute;n todos los permisos de uso en el <strong>Portal del Empleo</strong>.</li>
								    <li>El registro de la empresa s&oacute;lo se hace una vez y nunca se cancela, por lo que si ya est&aacute; registrado e intenta realizar otro registro el <strong>Portal del Empleo</strong> no lo permitir&aacute; por duplicidad en el identificador de la empresa.</li>
								    <li>A partir de ese momento, la empresa ser&aacute; la &uacute;nica responsable de su clave y el uso correcto que se le d&eacute; a la misma para los fines que est&aacute;n especificados. Es importante que el empleador proteja su clave, ya que si otras personas hacen mal uso del Portal usando la misma clave se corre el riesgo de perder acceso al sitio.</li>
								    <li>Para un mejor control de referencia y manejo de las ofertas de empleo, el <strong>Portal del Empleo</strong> asignar&aacute; un n&uacute;mero de folio a todas las ofertas de empleo que las empresas usuarias den de alta en el sitio.</li>
								    <li>Es obligaci&oacute;n de las empresas proporcionar en forma clara y veraz la informaci&oacute;n de las ofertas de empleo que ingresen al <strong>Portal del Empleo</strong> conforme al formato de registro correspondiente.</li>
								    <li>Las ofertas de empleo que se ofrezcan a trav&eacute;s del <strong>Portal del Empleo</strong> deben estar debidamente respaldadas por contratos legalmente v&aacute;lidos y vigentes. Est&aacute;n expresamente prohibidas en el Portal las ofertas de empleo que <strong>a)</strong> exijan invertir dinero a los candidatos; <strong>b)</strong> aquellas que correspondan a empresas &ldquo;pir&aacute;mide&rdquo; o negocios tipo multinivel y oportunidades de negocios; <strong>c)</strong> que impliquen la contrataci&oacute;n de menores sin la autorizaci&oacute;n legal; <strong>d)</strong> las que est&aacute;n relacionadas con el entretenimiento de adultos, ocio nocturno o contenidos pornogr&aacute;ficos; <strong>e)</strong> las que contraten personal solamente con pago por comisi&oacute;n o cambaceo; <strong>f)</strong> Trabajos que no paguen por lo menos el salario m&iacute;nimo diario;  <strong>g)</strong> Trabajos que requieran que el candidato, o empleado, vaya a un entrenamiento no pagado para ser contratado; <strong>h)</strong> contengan referencias obscenas, difamatorias, amenazantes, acosador, abusivas; <strong>i)</strong> Cualquier cosa que sea embarazosa u ofensiva para otra persona o entidad; <strong>j)</strong>  No debe publicar simult&aacute;neamente la misma oferta.</li>
								    <li>Asimismo, no est&aacute;n permitidas y no ser&aacute;n autorizadas en el <strong>Portal del Empleo</strong> las ofertas de empleo que pertenezcan a empresas con nombre irreal o gen&eacute;rico o que demeriten la calidad de la informaci&oacute;n que ofrece el sitio, ya sea porque est&aacute;n repetidas o porque contienen datos de contacto en los campos de funciones y actividades a realizar, observaciones, y/o conocimientos requeridos.</li>
								    <li>Tampoco est&aacute;n permitidas y no ser&aacute;n autorizadas los requerimientos de personal para servicios en casas-habitaci&oacute;n.</li>
								    <li>Las Agencias de Colocaci&oacute;n de Trabajadores y/o de Reclutamiento y Selecci&oacute;n de Recursos Humanos que hagan uso del <strong>Portal del Empleo</strong>, deber&aacute;n contar con el registro y autorizaci&oacute;n de funcionamiento otorgado por la <strong>STPS</strong>, conforme a lo estipulado en art&iacute;culo 4 del Reglamento de Agencia de Colocaci&oacute;n de Trabajadores publicado en el Diario Oficial de la Federaci&oacute;n el 3 de marzo de 2006.</li>
								    <li>En las ofertas de trabajo se debe incluir toda la informaci&oacute;n requerida de manera clara y concisa. Los oferentes de empleo que se registren en el sitio dan su cabal consentimiento para que el <strong>Portal del Empleo</strong> proporcione sus datos a los candidatos de empleo que est&eacute;n interesados en postularse para las ofertas que ofrezcan. Con fundamento en el art&iacute;culo 19 de la Ley Federal de Transparencia y Acceso a la Informaci&oacute;n P&uacute;blica Gubernamental, y en las fracciones II y III del Trig&eacute;simo Sexto de los Lineamientos Generales para la Clasificaci&oacute;n y Desclasificaci&oacute;n de la Informaci&oacute;n de las Dependencias y Entidades de la Administraci&oacute;n P&uacute;blica Federal, la informaci&oacute;n de las empresas registradas se considera clasificada como comercial reservada; salvo para los candidatos de empleo, registrados en el sitio, al momento de postular las citadas ofertas.</li>
								    <li>La <strong>STPS</strong> revisar&aacute; puntualmente la informaci&oacute;n y contenido de las ofertas de empleo que se inserten en el sitio, y en un plazo no mayor a 15 horas entre semana y 48 horas en fin de semana, publicar&aacute; en el <strong>Portal del Empleo</strong> las ofertas que cumplan con las presentes Pol&iacute;ticas y Condiciones de Uso.</li>
								    <li>Cuando la <strong>STPS</strong> identifique que se haya dado de alta una oferta de empleo no admitida o prohibida en el <strong>Portal del Empleo</strong> de acuerdo a las presentes Pol&iacute;ticas y Condiciones de Uso, proceder&aacute; a su baja en forma definitiva, reserv&aacute;ndose el derecho de no admitir la publicaci&oacute;n de m&aacute;s ofertas de empleo por parte de la empresa responsable de la misma. La <strong>STPS</strong> se reserva el derecho de dar de baja definitiva en cualquier momento y sin previo aviso, a las empresas que no cumplan con las presentes Pol&iacute;ticas y Condiciones de Uso.</li>
								    <li>No es responsabilidad de la <strong>STPS</strong> cualquier conflicto laboral, personal, legal o de otra &iacute;ndole, que pudiera darse entre el candidato de empleo y las empresas que buscan personal a trav&eacute;s del <strong>Portal del Empleo</strong> y/o terceros.</li>
								    <li>Las ofertas que se publiquen en el <strong>Portal del Empleo</strong> tendr&aacute;n una vigencia se&ntilde;alada por la empresa en el formato de alta de ofertas; cuando se cumpla con este periodo de vigencia, ser&aacute;n dadas de baja. Las empresas estar&aacute;n en posibilidades de volver a darlas de alta si as&iacute; lo requieren, o cambiar esta vigencia a trav&eacute;s de la opci&oacute;n de Espacio para Empresas.</li>
								    <li>La <strong>STPS</strong> no garantiza ni es responsable de que las empresas usuarias del <strong>Portal del Empleo</strong> encuentren en el sitio al personal necesario para cubrir sus ofertas.</li>
								    <li>Reglas para publicar una Oferta en el <strong>Portal del Empleo</strong>
								    <ul>
								        <li>El Puesto ofrecido debe ser acorde a la Ocupaci&oacute;n en la que lo est&aacute; clasificando, adem&aacute;s de ser congruente con la Escolaridad requerida y las actividades que va a realizar.</li>
								        <li>Se debe precisar con claridad los requerimientos de conocimientos y habilidades para desempe&ntilde;ar el puesto y las actividades a realizar en el mismo.</li>
								        <li>Nombre del puesto: deber&aacute; reflejar las actividades a realizar y se debe escribir de la siguiente manera:</li>
								        <li>Singular y que indique el g&eacute;nero, ejemplo: costurera, enfermera, &hellip;</li>
								        <li>Sin utilizar abreviaturas</li>
								        <li>Sin puntos al final del nombre</li>
								        <li>Usar di&eacute;resis cuando se requiera</li>
								        <li>En los puestos de ayudante general o de obrero general, especificar entre par&eacute;ntesis el &aacute;rea para la que se requiere, ejemplo: ayudante general (almac&eacute;n).</li>
								        <li>En los puestos de vendedor, especificar el tipo de mercanc&iacute;a a vender</li>
								        <li>En el caso de guardia de seguridad, no utilizar elemento de seguridad</li>
								        <li>No anotar observaciones en este campo es s&oacute;lo para el nombre del puesto</li>
								    </ul>
								    </li>
								    <h3>De la secci&oacute;n &ldquo;Servicio Nacional de Empleo&rdquo;</h3>
								    <li>Los Talleres para Buscadores de Empleo son una estrategia de informaci&oacute;n complementaria de las acciones de vinculaci&oacute;n laboral. En tal virtud, la <strong>STPS</strong> no garantiza que la participaci&oacute;n en los talleres d&eacute; como resultado la colocaci&oacute;n de los candidatos en un empleo ni su permanencia en &eacute;l.</li>
								    <li>No es responsabilidad de la <strong>STPS</strong> cualquier inconveniente provocado por la cancelaci&oacute;n, reprogramaci&oacute;n o cambio de sede de los talleres presenciales.</li>
								    <li>Las condiciones de acceso a las modalidades del Subprograma de Becas a la Capacitaci&oacute;n (B&eacute;cate), se regir&aacute;n por las <a href="${context}/download/sne/reglas-operacion-pae-2016.pdf" target="_blank">Reglas de Operaci&oacute;n del Programa de Apoyo al Empleo</a>.</li>
								    <li>Para ser registrado en cualesquiera de las modalidades del Subprograma de Becas a la Capacitaci&oacute;n (B&eacute;cate), los interesados deber&aacute;n acudir a la oficina del Servicio Nacional de Empleo en su Entidad y cumplir con los requisitos establecidos.</li>
								    <h3>Video curr&iacute;culum</h3>
								    <li>El <strong>Portal del Empleo</strong> revisar&aacute; el contenido de la informaci&oacute;n del video curr&iacute;culum para verificar que cumplen las condiciones de uso y de ser satisfactoria la revisi&oacute;n autorizara su publicaci&oacute;n.</li>
								    <li>Todos los datos aportados por el usuario, escritos o en video, han de ser propios y verdaderos. El usuario es responsable &uacute;nico de la informaci&oacute;n aportada y de su veracidad. Est&aacute; totalmente prohibida la suplantaci&oacute;n de personalidad de persona f&iacute;sica o jur&iacute;dica.</li>
								    <li>El usuario afirma que es titular en exclusiva de todos los derechos de propiedad intelectual del referido v&iacute;deo.</li>
								    <li>Es requisito indispensable por parte del candidato de empleo disponer de video curr&iacute;culum, alojado en un sitio de Internet especializado en videos proporcionar su URL (direcci&oacute;n web) para que el video pueda ser visto por las empresas que est&aacute;n interesadas en su perfil laboral.</li>
								    <li>La duraci&oacute;n del video curr&iacute;culum no podr&aacute; exceder de tres minutos.</li>
								    <li>El video curr&iacute;culum ha de tener unos m&iacute;nimos de calidad. En el caso de que no los cumpla, podr&aacute; ser rechazado.</li>
								    <li>En el video curr&iacute;culum no se podr&aacute; dar datos de contacto como tel&eacute;fono, direcci&oacute;n web o e-mail. A los datos de contacto solo podr&aacute;n acceder empresarios registrados en nuestra web bajo las condiciones de uso vigentes.</li>
								</ol>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
