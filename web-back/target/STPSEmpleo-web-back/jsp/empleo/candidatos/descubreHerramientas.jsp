<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Herramientas del candidato</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		 ¡Qué tal amigo buscador de empleo! A continuación te detallamos los beneficios de tu registro y cómo usar las herramientas que tenemos para ti
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li class="active">Descubre las herramientas que tenemos para ti</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp"/>
      
      <div class="col-sm-8 col-sm-pull-4">
      
      <jsp:include page="/WEB-INF/template/redes.jsp"/>
      	      
        <div class="panel panel-grey">
          <div class="panel-body">
          <div class="col-md-6">
            <img alt="" src="${context}/css/images/contenido/como_usar_descubre.png" class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Descubre las herramientas que tenemos para ti</h2>
            <p>¡Qué tal amigo buscador de empleo! A continuación te detallamos los beneficios de tu registro y cómo usar las herramientas que tenemos para ti</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <h3>&iexcl;Reg&iacute;strate en 3 sencillos pasos!</h3>
			<p>Registrarte en el Portal del Empleo es muy f&aacute;cil, s&oacute;lo tienes que ingresar al registro de candidato, 
			leer los beneficios que te ofrece el Servicio Nacional de Empleo (SNE) y seleccionar la opci&oacute;n Iniciar registro.</p>
			<p>Cuando hayas ingresado, s&oacute;lo debes proporcionar la siguiente informaci&oacute;n:</p>
			<p><strong>Datos de identificaci&oacute;n:</strong></p>
			<ul class="list-group-contenidos">
			    <li>Clave &Uacute;nica de Registro de Poblaci&oacute;n (CURP).</li>
			</ul>
			<p><strong>Datos de acceso:</strong></p>
			<ul class="list-group-contenidos">
			    <li>Usuario o correo electr&oacute;nico y una contrase&ntilde;a (entre 8 y 12 caracteres alfanum&eacute;ricos).</li>
			</ul>
			<p><strong>Perfil laboral</strong></p>
			<p>Es importante que al llenar tu perfil laboral, proporciones s&oacute;lo informaci&oacute;n relevante y concisa, pero, 
			sin olvidar ning&uacute;n detalle. De esta manera, pondr&aacute;s &eacute;nfasis en tus cualidades y tendr&aacute;s mayores 
			oportunidades de que las empresas se interesen en tu perfil. Asimismo, no olvides seleccionar las habilidades y actitudes en 
			la captura de tu perfil.</p>
			<p>Los datos que deber&aacute;s ingresar son:</p>
			<ul class="list-group-contenidos">
			    <li>Datos personales.</li>
			    <li>Escolaridad y otros conocimientos.</li>
			    <li>Expectativa y experiencias laborales.</li>
			    <li>Situaci&oacute;n laboral.</li>
			</ul>
			<p>Al finalizar tu registro, habr&aacute;s creado tu acceso al portal y podr&aacute;s imprimir o enviar tus claves de acceso 
			a tu cuenta de correo electr&oacute;nico.</p>
			<h3>Mi Espacio</h3>
			<p>Una vez registrado, acceder&aacute;s a tu espacio virtual en el que contar&aacute;s con las mejores herramientas para facilitar 
			tu b&uacute;squeda de empleo. Para acceder a tu espacio, puedes hacerlo desde la parte superior derecha del portal, ingresando a 
			Mi espacio (este acceso estar&aacute; visible durante toda la navegaci&oacute;n).</p>
			<p>Dentro de Mi espacio, encontrar&aacute;s dos bloques principales:</p>
			<ol>
				<li><strong>Bloque para administrar los datos de tu perfil laboral</strong><br />
				En este bloque, podr&aacute;s modificar tu informaci&oacute;n laboral y cambiar la foto de tu perfil.<br />
				Es recomendable actualizar peri&oacute;dicamente tu informaci&oacute;n, ya que tu perfil laboral ser&aacute; tu carta de 
				presentaci&oacute;n ante las empresas.</li>
				<li><p><strong>Bloque de herramientas y b&uacute;squeda de empleo</strong><br />
				Este bloque contiene cinco apartados: Crear mi curr&iacute;culum, Encontrar ofertas de empleo, Mis ofertas de empleo, Programas 
				y Servicios.</p>
					<ol style="list-style-type: lower-alpha">
						<li><p><strong>Crear mi Curr&iacute;culum</strong></p>
							<p>En este apartado, utiliza las siguientes herramientas para personalizar tu CV.</p>
							<ol style="list-style-type: upper-roman">
								<li><p><strong>Elegir estilo de CV.</strong> Cuando completes tu perfil laboral, ingresa a esta opci&oacute;n para personalizar tu 
								CV con las diferentes plantillas que tenemos para ti. Elige un estilo cl&aacute;sico, elegante o moderno y selecciona Guardar.</p></li>
								<li><p><strong>Ver curr&iacute;culum.</strong> Ingresa esta opci&oacute;n para descargar tu CV, en formato PDF.</p></li>
								<li><p><strong>Subir video-curr&iacute;culum.</strong> Si deseas proyectar una mejor imagen ante las empresas, podr&aacute;s publicar 
								tu video-curr&iacute;culum, s&oacute;lo necesitas un dispositivo que capture video, alojar tu video en un sitio de internet especializado 
								en videos y proporcionar la URL del mismo.</p>
								<p>Cuando tengas listo tu video-curr&iacute;culum, ingresa a esta opci&oacute;n y sigue estos sencillos pasos:</p>
								<ol>
								    <li>Inserta la URL de tu video.</li>
								    <li>Escribe una descripci&oacute;n acerca del video.</li>
								    <li>Selecciona Guardar.</li>
								</ol></li>
							</ol>
							<hr/>
						</li>
						<li><p><strong>Encontrar Ofertas de Empleo</strong></p>
							<p>En este apartado, encuentra el empleo que m&aacute;s se adec&uacute;e a tus necesidades, utilizando las siguientes herramientas:</p>
							<ol style="list-style-type: upper-roman">
								<li><p><strong>Ofertas de acuerdo a mi perfil.</strong>  En esta opci&oacute;n, se encuentra la herramienta de vinculaci&oacute;n llamada Match. 
								Esta herramienta sirve para analizar, simult&aacute;neamente, el perfil del candidato y la oferta de empleo, ofreciendo de manera inmediata 
								el grado de compatibilidad entre tu perfil y una oferta de empleo.</p>
								<p>Selecciona esta opci&oacute;n para encontrar ofertas de empleo que busquen perfiles como el tuyo. Los resultados aparecer&aacute;n en 
								forma de lista y podr&aacute;s revisar cada una de las ofertas de empleo, postularte a las que sean de tu inter&eacute;s o agregarlas a tus 
								ofertas.</p></li>
								<li><p><strong>Buscar en otras bolsas de trabajo.</strong>  Como una alternativa para encontrar empleo, en esta opci&oacute;n, est&aacute;n 
								disponibles diversas bolsas de trabajo externas.</p>
								<p>Selecciona la bolsa de trabajo que sea de tu inter&eacute;s y en la opci&oacute;n Palabra clave, escribe el puesto u oficio que est&aacute;s 
								buscado; en la opci&oacute;n Entidad federativa, selecciona la entidad de tu inter&eacute;s; y en la opci&oacute;n Publicadas, selecciona la 
								fecha de antig&uuml;edad de la oferta de empleo; a continuaci&oacute;n, presiona la opci&oacute;n Buscar.</p>
								<p>Los resultados se mostrar&aacute;n en un listado y podr&aacute;s visualizar la oferta de empleo, seleccionando el t&iacute;tulo de la oferta 
								o Ver detalle (la oferta ser&aacute; abrir&aacute; en el sitio externo de la bolsa de trabajo que hayas elegido).</p></li>
							</ol>
						
						</li>
						<li><p><strong>Mis Ofertas de Empleo</strong></p>
						<p>En este apartado, podr&aacute;s dar seguimiento a tus postulaciones y administrar tus ofertas de empleo, con las siguientes herramientas:</p>
						<ol style="list-style-type: upper-roman">
							<li><p><strong>Mis postulaciones.</strong>   Ingresa a esta opci&oacute;n para visualizar el listado de las ofertas de empleo a las que te hayas 
							postulado. Cada postulaci&oacute;n tendr&aacute; el estatus de postulado. Una vez que consigas el empleo y notifiques tu contrataci&oacute;n, 
							el sistema cambiar&aacute; al estatus de contratado.</p>
							<p>Adem&aacute;s de administrar tus postulaciones, podr&aacute;s darles seguimiento a trav&eacute;s de la opci&oacute;n Dar seguimiento a esta 
							postulaci&oacute;n que aparece en la columna de dar seguimiento. Cuando ingreses, proporciona los datos que te solicitan.</p>
							<p>Si deseas eliminar alguna postulaci&oacute;n, selecciona la casilla de la oferta de empleo y presiona Eliminar.</p></li>
							<li><p><strong>Mis ofertas guardadas.</strong> En esta opci&oacute;n, se mostrar&aacute; un listado con las ofertas de empleo que hayas guardado 
							cuando realizaste tu b&uacute;squeda de empleo.</p></li>
							<li><p><strong>Empresas que me buscan.</strong> En esta opci&oacute;n, aparecer&aacute; un listado con las ofertas de empleo de las empresas 
							que se interesaron en tu perfil laboral.</p>
							<p>Podr&aacute;s revisar cada oferta de empleo y postularte a las que sean de tu inter&eacute;s o eliminar aqu&eacute;llas que no cumplan con 
							tus expectativas.</p></li>
						</ol>
						
						
						</li>
						<li><p><strong>Servicios</strong></p>
						<p>En este apartado, encontrar&aacute;s dos opciones que pueden complementar tu b&uacute;squeda de ofertas de empleo:</p>
						<ol>
							<li>Consultar mis entrevistas en l&iacute;nea.</li>
							<li>Solicitar una cita.</li>
						</ol>
						<hr/>
						<ol style="list-style-type: upper-roman">
							<li><p><strong>Consultar mis entrevistas en l&iacute;nea</strong></p>
							<p>Al postularte a una oferta de empleo, la empresa puede ver tu postulaci&oacute;n y solicitarte una entrevista en l&iacute;nea. Al ingresar 
							a esta opci&oacute;n puedes aceptarla, reprogramarla, rechazarla o cancelarla.</p></li>
							<li><p><strong>Solicitar una cita</strong></p>
							<p>Al ingresar a esta opci&oacute;n, podr&aacute;s agendar una cita para ser atendido en la red de oficinas del Servicio Nacional de Empleo (SNE). 
							Para solicitar una cita, selecciona Solicitar cita y proporciona los siguientes datos:</p>
							<ol>
								<li>Entidad federativa.</li>
								<li>Oficina del SNE de tu inter&eacute;s.</li>
								<li>Servicio de la cita.</li>
								<li>Fecha y horario de la cita.</li>
								<li>Datos personales.</li>
								<li>Datos de contacto.</li>
							</ol>
							<hr/>
							<p>En caso de que ya est&eacute;s registrado en el Portal del Empleo y tengas tu sesi&oacute;n activa, tus datos personales y de contacto se 
							cargar&aacute;n en autom&aacute;tico.</p>
							<p>Cuando completes todos los datos, selecciona Solicitar cita. Es importante que guardes el n&uacute;mero de cita o que imprimas el documento, 
							ya que te ser&aacute; solicitado el d&iacute;a que acudas a las oficinas del SNE.</p>
							<p>Para consultar o cancelar una cita:</p>
							<ol>
								<li>Ingresa a la opci&oacute;n Solicitar una cita y escribe el n&uacute;mero de tu cita.</li>
								<li>Selecciona consultar/cancelar cita.</li>
								<li>Aparecer&aacute;n los datos de tu cita y podr&aacute;s cancelarla o imprimir los datos.</li>
							</ol>
							<hr/>

							<p>Tambi&eacute;n puedes registrar una cita desde la p&aacute;gina principal del Portal del Empleo, en el m&oacute;dulo de Solicita una cita.  
							Ten en cuenta que, si deseas registrar dos citas, el sistema no lo permitir&aacute; por la duplicidad en la CURP. Si deseas registrar otra cita, 
							deber&aacute;s cancelar la anterior para registrar una nueva.</p></li>
						</ol>
 
						
						</li>
						<li><p><strong>Programas</strong></p>
						<p><strong>Registrarme a un evento de Ferias de Empleo</strong></p>
						<p>Las Ferias de Empleo son un medio de vinculaci&oacute;n gratuito, directo y &aacute;gil que ofrece en todo el pa&iacute;s la Secretar&iacute;a 
						del Trabajo y Previsi&oacute;n Social, a trav&eacute;s del Servicio Nacional de Empleo. En &eacute;stas, es posible interactuar y relacionarse 
						con representantes de empresas de diferentes sectores que requieren personal.</p>
						<p>Para registrarte a un evento, sigue estos sencillos pasos:</p>
						<ol>
							<li>Selecciona una entidad para mostrar los eventos pertenecientes a dicho estado.</li>
							<li>Elije el evento de tu inter&eacute;s y revisa los datos generales (horario, fecha, lugar, tipo de evento, etc&eacute;tera).</li>
							<li>En la parte inferior, selecciona Registrarme al evento.</li>
							<li>Una vez registrado, puedes imprimir tu comprobante. Si deseas hacerlo despu&eacute;s, ingresa de la misma forma y, en el detalle del evento, 
						selecciona Imprimir comprobante.</li>
						</ol>
						<hr/>

						<p>Con tu usuario y contrase&ntilde;a del Portal del Empleo, podr&aacute;s consultar el detalle de todas las ofertas de empleo en el portal de 
						<a href="http://ferias.empleo.gob.mx/content/common/home.jsf" target="_blank">Ferias de Empleo</a>.</p></li>
					</ol>
				</li>
			</ol>

			<h3>Encuentra Ofertas de Empleo</h3>
			<p>Usa nuestros dos tipos de buscadores, para postularte a las ofertas de empleo que sean de tu inter&eacute;s.</p>
			<p><strong>Buscador principal.</strong> En la parte superior de tu pantalla, en la opci&oacute;n &iquest;Qu&eacute; empleo buscas?, escribe 
			el puesto, carrera u oficio que est&aacute;s buscando; en la opci&oacute;n &iquest;D&oacute;nde?, selecciona la entidad de tu preferencia y 
			presiona la opci&oacute;n buscar.</p>
			<p><strong>Buscador espec&iacute;fico.</strong> Con este buscador, obtendr&aacute;s resultados m&aacute;s precisos. Ingresa a b&uacute;squeda 
			espec&iacute;fica y en cada opci&oacute;n, ingresa o selecciona los datos que te piden y presiona Buscar. El sistema te mostrar&aacute; 
			resultados seleccionando, desde una sola opci&oacute;n, hasta dos o m&aacute;s.</p>
			<p>Los resultados de ambos buscadores, aparecer&aacute;n en forma de lista y podr&aacute;s seleccionar la oferta de empleo que sea de tu 
			inter&eacute;s.</p>
			<p>Podr&aacute;s revisar el porcentaje de compatibilidad con tu perfil laboral, enviar una pregunta a la empresa, postularte o agregarla a 
			tus ofertas de empleo para revisarla m&aacute;s tarde.</p>
			<h3>Recibe Ofertas de Empleo en tu celular y correo electr&oacute;nico</h3>
			<p>Para recibir las ofertas de empleo m&aacute;s recientes, es importante que, en el llenado de tu perfil, indiques cu&aacute;l es el medio 
			por el que deseas recibir ofertas de empleo, ya sea en tu celular, correo electr&oacute;nico o ambos.</p>
			<p>Ten acceso a las mejores ofertas laborales y encuentra trabajo de una manera f&aacute;cil. &iexcl;Te invitamos a 
			<a href="<c:url value="/registro_candidato.do"/>">registrarte ahora</a>!</p>
			<p><strong>Todos nuestros servicios son gratuitos.</strong></p>
	        
	       </div>
	     </div>
       
      </div>

    </div>
	</jsp:body>
</t:publicTemplate>
