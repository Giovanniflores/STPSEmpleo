<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Art&iacute;culos de inter&eacute;s para ti</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		&iexcl;El Portal del Empleo te ofrece en forma gratuita el servicio de ofertas de empleo en tu correo electr&oacutenico!
	</jsp:attribute>
	<jsp:body>
	
<div id="cuerpo_int">
	<div id="contenido_principal" class="rssS">
	<div class="texto_principal">
	<h3>Consulta el Portal del Empleo en RSS</h3></br>
	<p><strong>&iexcl;El Portal del Empleo te ofrece en forma gratuita el servicio de ofertas de empleo en tu correo electr&oacutenico!</strong></p>
	<p><strong>&iexcl;Es muy f&aacute;cil!
	, solo sigue las siguientes indicaciones:</strong></p>
	<ol>
	<li><strong>Si a&uacuten no  est&aacute;s registrado</strong>
	<ul>
	<li>Cuando ingreses tu perfil laboral, acepta  la opci&oacuten de recibir ofertas de empleo por correo electr&oacutenico.</li>
	<li>En seguida selecciona el &aacute;rea laboral, el  nivel de escolaridad y la entidad federativa de las ofertas de empleo que deseas  recibir. </li>
	</ul>
	</li>
	<li><strong>Si ya est&aacute;s registrado y no seleccionaste esta opci&oacuten</strong>
	<ul>
	<li>Entra a tu capeta personal y selecciona la  opci&oacuten de &quot;modificar mi perfil&quot;.</li>
	<li>En seguida selecciona la opci&oacuten para  recibir ofertas de empleo por correo electr&oacutenico.</li>
	</ul>
	</li>
	<li><strong>En caso de  que quieras modificar los criterios de tu selecci&oacuten o quieras actualizar tu  correo electr&oacutenico</strong>
	<ul>
	<li>Entra a tu carpeta personal y selecciona la  opci&oacuten de &quot;modificar mi perfil&quot;.</li>
	<li>Modifica tus datos que desees actualizar  como tu correo electr&oacutenico o los criterios de selecci&oacuten para recibir ofertas de  empleo.</li>
	</ul>
	</li>	
	<li><strong>En caso de  que ya no desees seguir recibiendo este servicio</strong>
	<ul>
	<li>Entra a tu carpeta personal y selecciona la  opci&oacuten de &quot;modificar mi perfil&quot;.</li>
	<li>Inmediatamente despu&eacute;s selecciona la opci&oacuten  de que ya no deseas recibir ofertas de empleo por correo electr&oacutenico.</li>
	</ul>
	</li>
	</ol>
	<p>Si tienes dudas de c&oacutemo recibir ofertas de empleo por correo electr&oacutenico, comun&iacutecate con nosotros al </br><span class="textmayusorange">01 800 841 20 20</span> o haz <a href="/wb/BANEM/BANE_atencion_en_linea"><span class="ligastext2">click aqu&iacute</span></a> para recibir asesor&iacutea en l&iacutenea por uno de nuestros ejecutivos.</p>
	<p><span>Adem&aacute;s de este servicio, </span><span>el Portal del Empleo</span>
	<span> te ofrece en forma gratuita informaci&oacuten de ofertas de empleo de la entidad federativa donde resides en el instante mismo en que &eacute;sta informaci&oacuten se publica, por medio del novedoso formato<img width="32" height="15" alt="RSS" src="images/icon_rss.gif">.</br>
	</br>
	Para que puedas recibir esta informaci&oacuten necesitas de un software denominado feed (tambi&eacute;n llamado lector de noticias o agregador), el cual te permitir&aacute; actualizar los &iacutendices y contenidos de las ofertas de empleo de la entidad que deseas recibir.</br>
	</br>
	</br>
	<strong class="textmayusorange">Instrucciones:</strong></br>
	</br>
	</br>
	Para recibir ofertas de empleo por <img width="32" height="15" alt="RSS" src="images/icon_rss.gif">  debes seguir los siguientes pasos:</br>
	</br>
	<strong>A. Descarga e instala en tu computadora alg&uacuten lector o agregador  <img width="32" height="15" alt="RSS" src="images/icon_rss.gif">; los siguientes son dos de los m&aacute;s populares: </strong></span></p> 
	<ul class="bis">
	<li><a href="http://www.rssreader.com/download/rssreader.exe">RSS Reader</a></li>
	<li><a href="http://prdownloads.sourceforge.net/feedreader/FeedReader27-646Setup.exe?download">Feed reader</a></li>
	</ul>
	</div>	
	</div>
	<div id="columna_articulos" class="margin_top_50">
	<h2>Ofertas de Empleo en RSS en tu estado</h2>
	<ul class="rssS margin_top_20">
	<%
	PropertiesLoader properties = PropertiesLoader.getInstance();
	Constantes.ENTIDADES_FEDERATIVAS_RSS[] entidades = Constantes.ENTIDADES_FEDERATIVAS_RSS.values();
	String urlrss = properties.getProperty("rss.direccion");

	for(Constantes.ENTIDADES_FEDERATIVAS_RSS entidad: entidades){
		
	%>
		<li> 
		<a href="<%=urlrss + entidad.getAbreviatura()%>" target="_blank" > <%=entidad.getNombre()%></a>
		</li>
	<%
	}
	%>
	</ul>	
	</div>
</div>

	</jsp:body>
</t:publicTemplate>
