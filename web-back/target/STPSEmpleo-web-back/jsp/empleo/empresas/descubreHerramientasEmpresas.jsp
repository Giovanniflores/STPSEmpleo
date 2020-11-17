<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Descubre las herramientas que tenemos para ti</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Empresas</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Utiliza las herramientas que te ofrece el Servicio Nacional de Empleo (SNE) y cubre tus vacantes rápidamente. Te diremos el proceso para registrar ofertas de empleo, cómo aprovechar al máximo las herramientas de tu Espacio, cómo administrar la información de tus postulantes, etc.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/empresas/empresas.jsp"/>">Empresas</a></li>
          <li class="active">Descubre las herramientas que tenemos para ti</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp" />
      
      <div class="col-sm-8 col-sm-pull-4">
      
      <jsp:include page="/WEB-INF/template/redes.jsp" />
      	      
        <div class="panel panel-grey">
          <div class="panel-body">
          <div class="col-md-6">
            <img alt=""
								src="${context}/css/images/contenido/como_usar_descubre.png"
								class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Descubre las herramientas que tenemos para ti</h2>
            <p>Utiliza las herramientas que te ofrece el Servicio Nacional de Empleo (SNE) y cubre tus vacantes rápidamente. Te diremos el proceso para registrar ofertas de empleo, cómo aprovechar al máximo las herramientas de tu Espacio, cómo administrar la información de tus postulantes, etc.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <h3>&iexcl;Crea tu registro en 2 sencillos pasos!</h3>
<p>Para registrarte en el Portal del Empleo, s&oacute;lo tienes que ingresar al registro de empresas, leer los beneficios que te ofrece el Servicio Nacional de Empleo (SNE) y seleccionar la opci&oacute;n iniciar registro.</p>
<p>Cuando hayas ingresado, s&oacute;lo debes proporcionar la siguiente informaci&oacute;n:</p>
<p>
							<strong>Datos de acceso:</strong>
						</p>
<ul class="list-group-contenidos">
    <li>Correo electr&oacute;nico y una contrase&ntilde;a (entre 8 y 12 caracteres alfanum&eacute;ricos).</li>
</ul>
<p>
							<strong>Perfil de la empresa:</strong>
						</p>
<p>Es importante ingresar la informaci&oacute;n de tu empresa para generar un identificador y garantizar la confiabilidad del registro.  Los datos que deber&aacute;s ingresar son:</p>
<ul class="list-group-contenidos">
    <li>Datos de la empresa</li>
    <li>Domicilio de la empresa</li>
    <li>Datos de contacto</li>
</ul>
<p>Al finalizar tu registro, habr&aacute;s creado tu acceso al portal y podr&aacute;s imprimir o enviar tus claves de acceso.</p>
<p>Ahora, es necesario crear una oferta de empleo (m&aacute;s adelante, te indicaremos el proceso), la cual debe cumplir con las Pol&iacute;ticas y Condiciones de uso del Portal del Empleo. Una vez aprobada y publicada, tendr&aacute;s acceso a la base de datos de candidatos registrados.</p>
<h3>Mi Espacio</h3>
<p>Con tu registro, tendr&aacute;s acceso a un espacio virtual en el que contar&aacute;s con las mejores herramientas para facilitar tu b&uacute;squeda de candidatos.</p>
<p>Para ingresar a tu espacio, puedes hacerlo desde la parte superior derecha del portal, seleccionando Mi espacio (este acceso estar&aacute; visible durante toda la navegaci&oacute;n).</p>
<p>Dentro de Mi espacio, encontrar&aacute;s dos bloques principales:</p>
<p>
							<strong>1. Bloque para administrar los datos de tu empresa</strong>
						</p>
<p>Podr&aacute;s editar tus datos, adem&aacute;s de subir y/o cambiar el logotipo de tu empresa.</p>
<p>
							<strong>2. Bloque para ofertas de empleo y b&uacute;squeda de candidatos</strong>
						</p>
<p>Este bloque contiene tres apartados: Administrar mis ofertas de empleo, Postulantes a mis ofertas de empleo, y Otras herramientas.</p>
<p>
							<strong>a) Administrar mis Ofertas de Empleo</strong>
						</p>
<p>
							<strong>I. Crear ofertas de empleo.</strong>
						</p>
<p>Selecciona d&oacute;nde quieres crear tu oferta de empleo: Portal del Empleo o Abriendo Espacios (personas con discapacidad y adultos mayores) y proporciona la siguiente informaci&oacute;n:</p>
<p>1. Informaci&oacute;n y ubicaci&oacute;n de la oferta de empleo.<br />
2. Requisitos de los candidatos.<br />
3. Datos de contacto.</p>
<p>Cuando ingreses los datos de la oferta de empleo, selecciona Guardar y habr&aacute;s creado una oferta de empleo; &eacute;sta ser&aacute; publicada una vez que haya concluido el proceso de validaci&oacute;n.</p>
<p>
							<strong>&iquest;Cu&aacute;nto tiempo tarda la validaci&oacute;n de la oferta de empleo?</strong>
						</p>
<p>Cada oferta de empleo pasa por un proceso de validaci&oacute;n (realizado por personal del centro de administraci&oacute;n del Portal del Empleo), con un plazo no mayor a 15 horas entre semana. Sin embargo, si creas tu oferta en fin de semana, puentes o d&iacute;as festivos, la validaci&oacute;n y publicaci&oacute;n se realizar&aacute; en un lapso de 48 horas.</p>
<p>En caso de que no se haya publicado en el tiempo establecido, puedes llamarnos al 01 800 8 41 2020, ll&aacute;manos de 8 am a 10 pm  los 365 d&iacute;as del a&ntilde;o y con gusto te atenderemos.</p>
<p>
							<strong>II. Mis ofertas de empleo. Cuando ingreses a esta opci&oacute;n, aparecer&aacute; un listado de las ofertas que hayas creado.</strong>
						</p>
<p>Cada oferta de empleo, contar&aacute; con las siguientes opciones:</p>
<table width="590" cellspacing="10" border="0">
    <tbody>
        <tr>
            <td width="176"><strong>Postulantes</strong></td>
            <td width="397">Aparecer&aacute; un listado con los candidatos que se hayan postulado a tu oferta de empleo, y los candidatos que hayas agregado a tus ofertas de empleo. Podr&aacute;s revisar su perfil laboral o eliminar a los que ya no desees.</td>
        </tr>
        <tr>
            <td><strong>Responder preguntas</strong></td>
            <td>Podr&aacute;s responder las preguntas que los postulantes enviaron, referente a tu oferta de empleo. S&oacute;lo escribe la respuesta en el recuadro y selecciona la opci&oacute;n Enviar.</td>
        </tr>
        <tr>
            <td><strong>Editar</strong></td>
            <td>Podr&aacute;s actualizar los datos de la oferta de empleo, cuando hayas finalizado; s&oacute;lo deber seleccionar la opci&oacute;n Guardar. La oferta se visualizar&aacute; hasta que sea validada.</td>
        </tr>
        <tr>
            <td><strong>Usar como plantilla</strong></td>
            <td>Podr&aacute;s recuperar los datos de alguna de tus ofertas de empleo. Modifica los datos que consideres necesarios, para no capturarlos de nuevo. Ten en cuenta que se generar&aacute; una nueva oferta de empleo y pasar&aacute; por el proceso de validaci&oacute;n.</td>
        </tr>
        <tr>
            <td><strong>Desactivar</strong></td>
            <td>Selecciona esta opci&oacute;n para que tu oferta no se muestre a los candidatos. Si seleccionas la opci&oacute;n Activar, la oferta volver&aacute; a ser visible.</td>
        </tr>
        <tr>
            <td><strong>Eliminar</strong></td>
            <td>Podr&aacute;s eliminar las ofertas de empleo que ya no necesites o las que ya est&eacute;n cubiertas, para ya no recibir postulaciones de candidatos interesados.</td>
        </tr>
        <tr>
            <td><strong>Candidatos para esta oferta</strong></td>
            <td>Esta opci&oacute;n es una b&uacute;squeda de candidatos que se realiza a trav&eacute;s de la herramienta de vinculaci&oacute;n llamada Match (m&aacute;s adelante te explicaremos su funci&oacute;n). Los resultados aparecer&aacute;n en forma de lista.</td>
        </tr>
    </tbody>
</table>
<p>
							<strong>III. Utilizar oferta como plantilla.</strong> En esta opci&oacute;n, podr&aacute;s recuperar tus ofertas de empleo para utilizarlas como plantilla en el registro de futuras ofertas. Con esto, ya no tendr&aacute;s que volver a capturar todos los datos sino modificar, &uacute;nicamente, la informaci&oacute;n que sea necesaria. Se generar&aacute; una nueva oferta de empleo y pasar&aacute; por el proceso de validaci&oacute;n.<br />
Para utilizar la oferta como plantilla, s&oacute;lo tienes que buscarla por n&uacute;mero de folio, t&iacute;tulo de la oferta o fecha de modificaci&oacute;n de la oferta.</p>
<p>
							<strong>IV. Reporte de ofertas de empleo.</strong> Selecciona esta opci&oacute;n para descargar un documento, en formato Excel, de tus ofertas de empleo. Para generarlo, elije una o las dos opciones siguientes y presiona la opci&oacute;n Buscar. Puedes generar un documento por cada rubro que selecciones:</p>
<ul class="list-group-contenidos">
    <li>Estatus de las ofertas (activas, canceladas, eliminadas, etc&eacute;tera).</li>
    <li>Fecha de alta de tus ofertas.</li>
</ul>
<p>
							<strong>b) Postulantes a mis ofertas de empleo</strong>
						</p>
<p>En este apartado, podr&aacute;s administrar la informaci&oacute;n de los candidatos que se postulen a tus ofertas.</p>
<p>
							<strong>I. Mis postulantes.</strong> Cuando ingreses, aparecer&aacute; un listado de tus postulantes, agrupados por cada una de tus ofertas de empleo. En cada postulante, aparecer&aacute; el grado de compatibilidad con la oferta de empleo que tiene relaci&oacute;n.</p>
<p>Para eliminar a los candidatos que no cumplan con los requisitos para tus ofertas de empleo, selecciona la casilla del nombre del candidato y posteriormente Eliminar.</p>
<p>En el detalle del candidato podr&aacute;s visualizar su perfil laboral adem&aacute;s de:</p>
<p>
							<strong>Descargar CV, en formato PDF:</strong> se descargar&aacute; el CV completo del candidato.</p>
<p>
							<strong>Agregar a tus candidatos seleccionados:</strong> podr&aacute;s relacionar al candidato a una de tus ofertas de empleo, seleccionando la casilla de la oferta y la opci&oacute;n enviar (esta opci&oacute;n, s&oacute;lo estar&aacute; disponible cuando realices una b&uacute;squeda espec&iacute;fica o por palabra clave).</p>
<p>
							<strong>Contactar al candidato:</strong> se le notificar&aacute; al candidato que est&aacute;s interesado en su perfil laboral (esta opci&oacute;n, s&oacute;lo estar&aacute; disponible para candidatos postulados a tus ofertas de empleo).</p>
<p>
							<strong>Programar una entrevista en l&iacute;nea:</strong> elige la fecha, el horario de la entrevista y env&iacute;a una invitaci&oacute;n al candidato (esta opci&oacute;n s&oacute;lo estar&aacute; disponible para candidatos postulados a tus ofertas de empleo y cuando realices una b&uacute;squeda a trav&eacute;s de Candidatos para esta oferta).</p>
<p>
							<strong>Agregar a tu carpeta empresarial: </strong>podr&aacute;s agregar al candidato a la oferta de empleo con la que se relacion&oacute; (esta opci&oacute;n s&oacute;lo est&aacute; disponible cuando realizas una b&uacute;squeda a trav&eacute;s de Candidatos para esta oferta).</p>
<p>
							<strong>Otras herramientas</strong>
						</p>
<p>
							<strong>I. Entrevistas en l&iacute;nea.</strong> Aqu&iacute;, se almacenar&aacute;n las entrevistas en l&iacute;nea que tengas programadas. En la parte superior del listado, en Opciones, selecciona el &iacute;cono azul para reprogramar una entrevista y el &iacute;cono rojo para cancelarla.
						</p>
<p>
							<strong>II. Acerca del portal Abriendo Espacios.</strong> Podr&aacute;s conocer el alcance del programa Abriendo Espacios, crear una oferta para el portal, as&iacute; como ingresar a su sitio https://www.abriendoespacios.gob.mx/.
						</p>
<p>Para utilizar todas las herramientas que te ofrece Mi espacio, es necesario iniciar sesi&oacute;n y tener ofertas de empleo vigentes; de lo contrario, s&oacute;lo podr&aacute;s actualizar los datos de tu empresa.</p>
<h3>B&uacute;squeda de Candidatos</h3>
<p>Usa nuestros dos tipos de buscadores, dise&ntilde;ados para facilitar tus b&uacute;squedas de candidatos.</p>
<p>
							<strong>Buscador de candidatos.</strong> Utiliza este buscador, para encontrar el candidato que m&aacute;s se adec&uacute;e al perfil de tus ofertas de empleo.</p>
<p>En la parte superior de tu pantalla, en la opci&oacute;n &iquest;Qu&eacute; candidato buscas?, escribe la ocupaci&oacute;n u oficio que est&aacute;s buscando para tu oferta; en la opci&oacute;n &iquest;D&oacute;nde?, selecciona la entidad de tu preferencia y presiona la opci&oacute;n Buscar.</p>
<p>
							<strong>Buscador espec&iacute;fico.</strong> Con este buscador, obt&eacute;n resultados m&aacute;s precisos. Ingresa a b&uacute;squeda espec&iacute;fica y en cada opci&oacute;n, ingresa o selecciona los datos que te piden (puedes utilizar todas o algunas de las opciones de b&uacute;squeda que est&aacute;n disponibles) y presiona la opci&oacute;n buscar.</p>
<p>Los resultados de ambos buscadores aparecer&aacute;n en forma de lista, y podr&aacute;s seleccionar al candidato que sea de tu inter&eacute;s para revisar su perfil laboral.</p>
<p>
							<strong>B&uacute;squeda de candidatos por oferta.</strong> En esta opci&oacute;n, se encuentra la herramienta de vinculaci&oacute;n llamada Match. Esta herramienta sirve para analizar, simult&aacute;neamente, el perfil del candidato y la oferta de empleo, ofreciendo de manera inmediata el grado de compatibilidad entre el candidato y tu oferta de empleo.</p>
<p>Desde el listado de tus ofertas de empleo, selecciona la opci&oacute;n Candidatos para esta oferta y aparecer&aacute;n los candidatos que sean compatibles con tu oferta.</p>
<p>Ten en cuenta que para utilizar los buscadores, es necesario que inicies sesi&oacute;n y tengas una oferta de empleo vigente.</p>
<p>Utiliza las mejores herramientas de b&uacute;squeda de candidatos que tenemos para ti y cubre tus vacantes f&aacute;cilmente. &iexcl;Te invitamos a <a
								href="<c:url value="/registroEmpresa.do?method=init"/>">registrarte ahora</a>!</p>
<p>
							<a
								href="<c:url value="/registroEmpresa.do?method=init"/>"><br />
</a>
						</p>
<p>
							<strong>Todos nuestros servicios son gratuitos.</strong>
						</p>
	        
	       </div>
	     </div>
       
      </div>

    </div>
	</jsp:body>
</t:publicTemplate>
