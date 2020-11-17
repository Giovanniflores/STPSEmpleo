<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="ftp" value="${FTP_STATIC_CONTENT}" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Revista informativa del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
    <jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Es una publicaci&oacute;n que tiene la finalidad de divulgar entre las empresas e instituciones relacionadas con el empleo y la capacitaci&oacute;n, informaci&oacute;n sobre las caracter&iacute;sticas y el funcionamiento de los mercados laborales. Es tambi&eacute;n una herramienta de apoyo para una mejor toma de decisiones por parte de los actores que participan en la conformaci&oacute;n de una fuerza trabajadora, y as&iacute; dar transparencia al mercado laboral.
	</jsp:attribute>
    <jsp:attribute name="js">
    	<script src="${context}/js/jquery.rwdImageMaps.js"></script>
		<script type="text/javascript">
			$(document).ready(function(e) {
				$('img[usemap]').rwdImageMaps();
			});
			function MM_preloadImages() { //v3.0
			  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
			    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
			    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
			}
			function MM_swapImgRestore() { //v3.0
			  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
			}
			
			function MM_findObj(n, d) { //v4.01
			  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
			    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
			  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
			  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
			  if(!x && d.getElementById) x=d.getElementById(n); return x;
			}
			
			function MM_swapImage() { //v3.0
			  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
			   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
			}
		</script>
	</jsp:attribute>

	<jsp:body>
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
         <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasServiciosEmpleo.jsp"/>">Programas y servicios de empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/publicaciones.jsp"/>">Publicaciones</a></li>
          <li class="active">Revista informativa del SNE</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
		<div class="row">
		
		<jsp:include page="menu.jsp" />
		
			<div class="col-sm-8 col-sm-pull-4">
				 
			<jsp:include page="/WEB-INF/template/redes.jsp" />
			   
			<div class="clearfix"></div>
				<div class="panel">
					<div class="panel-body">
						<h2 class="titulosh2">Revista informativa del SNE</h2>
						<p>Es una publicaci&oacute;n que tiene la finalidad de divulgar entre las empresas e instituciones relacionadas con el empleo y la capacitaci&oacute;n, informaci&oacute;n sobre las caracter&iacute;sticas y el funcionamiento de los mercados laborales. Es tambi&eacute;n una herramienta de apoyo para una mejor toma de decisiones por parte de los actores que participan en la conformaci&oacute;n de una fuerza trabajadora, y as&iacute; dar transparencia al mercado laboral.</p>
						<p>Igualmente, por medio de esta revista informativa, los servicios de empleo dan a conocer los principales resultados de sus programas y, en general, de todas aquellas acciones que se hayan realizado en el campo del empleo y la capacitaci&oacute;n en la entidad durante el periodo correspondiente.</p>
						<p>La Revista Informativa del SNE se edita trimestralmente en todas las entidades federativas del pa&iacute;s a trav&eacute;s de las oficinas del organismo.</p>
						<p>Si deseas encontrar candidatos idóneos para cubrir tus vacantes de una forma m&aacute;s r&aacute;pida,<br />
						<a href="<c:url value="registroEmpresa.do?method=init"/>">Reg&iacute;strate</a> en el Portal.</p>
						
						<br><br/>
						<div class="page-header">
							<h3 class="titulosh2"></h3>
						</div>
					    
					    <a name="mapa"></a>
					    <img height="500" width="630" border="0" alt="" name="MapaOk" usemap="#MapaOkMap" id="MapaOk" src="${context}/css/images/mapaPeriodico/mapa.png" />
					    <map name="MapaOkMap" id="MapaOkMap">
							<area shape="poly" coords="9,33" href="#" />
							<area shape="poly" coords="9,34,53,34,53,40,63,45,57,53,54,55,51,59,52,68,55,71,53,83,54,92,55,109,60,119,68,127,74,136,80,142,84,150,89,157,86,159,73,159,64,158,61,156,64,148,61,142,50,129,40,122,31,111,29,95,21,76,18,69,16,59,12,45,9,32" target="_blank" alt="Baja California" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/bajaCalifornia.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/baja-california/index.html" />
							<area shape="poly" coords="45,160,52,162,61,157,70,159,86,159,91,163,95,171,99,182,104,182,109,189,112,199,114,209,119,219,125,230,126,239,125,243,128,248,132,251,135,248,140,253,145,261,148,265,148,273,141,277,139,278,132,264,122,253,104,239,97,230,98,212,96,203,90,196,79,191,76,188,70,187,64,184,60,180,54,173,48,165,45,159" target="_blank" alt="" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/bajaCaliforniaSur.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/baja-california-sur/index.html" />
							<area shape="poly" coords="55,69,66,76,72,77,80,81,86,86,87,93,92,109,98,128,106,142,115,155,124,165,132,165,132,173,140,183,147,188,153,194,159,200,173,191,175,189,173,178,169,166,165,156,172,158,175,152,173,139,173,133,177,125,174,105,170,86,172,81,127,80,114,72,98,65,83,57,67,49,63,46,58,51,54,56,51,60,54,68,54,67" target="_blank" alt="Sonora" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/sonora.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/sonora/index.html" />
							<area shape="poly" coords="175,186,183,190,185,196,188,204,188,208,197,212,199,217,200,230,204,236,207,242,210,247,211,251,217,249,223,254,226,263,228,270,231,278,234,278,232,284,234,288,232,290,227,288,217,277,202,257,193,250,191,244,182,243,180,233,172,224,164,218,156,216,154,208,158,202,176,186,165,196,176,186,171,190,173,189,176,187" target="_blank" alt="Sinaloa" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/sinaloa.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/sinaloa/index.html" />
							<area shape="poly" coords="172,81,180,80,181,70,204,71,214,75,219,80,229,88,233,97,237,100,247,111,249,127,259,133,269,140,278,143,275,153,267,167,265,171,272,196,265,196,259,196,254,202,249,201,237,199,231,198,224,193,220,195,217,201,214,205,212,213,207,219,202,220,195,210,191,208,187,197,186,189,179,187,174,184,170,168,166,157,172,158,174,152,174,144,174,134,176,125,175,112,172,89,172,80" target="_blank" alt="Chihuahua" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/chihuahua.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/chihuahua/index.html" />
							<area shape="poly" coords="272,196" href="#mapa" />
							<area shape="poly" coords="280,142,288,128,300,123,309,121,317,126,324,132,328,136,332,148,342,160,347,167,346,171,341,174,334,182,330,189,335,189,334,193,331,196,323,202,330,214,338,226,341,227,333,230,329,234,330,239,329,243,324,242,319,240,311,238,304,233,296,232,291,239,285,240,279,232,275,227,280,209,279,201,272,195,269,180,266,170,272,159,279,142" target="_blank" alt="Coahuila" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/coahuila.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/coahuila/index.html" />
							<area shape="poly" coords="204,220,213,216,215,207,221,195,226,194,232,200,245,201,255,201,260,199,270,196,276,200,279,205,279,214,278,222,276,231,282,236,288,241,295,237,296,244,296,249,280,250,270,255,268,261,267,265,265,270,262,275,259,287,256,295,251,292,249,289,244,289,242,287,244,283,237,279,231,280,227,272,227,270,224,259,219,252,213,249,212,249,206,240,203,232,201,228,200,220,204,220" target="_blank" alt="Durango" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/durango.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/durango/index.html" />
							<area shape="poly" coords="230,291,229,301,234,310,238,316,238,325,236,330,240,332,250,331,255,331,262,326,265,321,265,316,260,309,260,302,257,295,251,290,247,288,243,288,243,282,235,279,231,283,230,289,231,292" target="_blank" alt="" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/nayarit.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/nayarit/index.html" />
							<area shape="poly" coords="330,248,323,242,314,239,306,234,300,233,295,236,297,246,296,250,283,250,277,253,270,257,262,273,260,286,257,293,259,297,264,287,267,288,265,297,270,290,273,299,272,301,277,293,282,296,282,301,275,310,273,315,271,319,277,326,285,321,292,316,287,308,287,302,300,293,304,298,306,304,316,308,318,294,318,289,311,295,301,286,300,276,302,272,312,266,320,260,330,247" target="_blank" alt="Zacatecas" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/zacatecas.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/zacatecas/index.html" />
							<area shape="poly" coords="257,332,250,329,241,332,240,338,240,345,241,356,248,364,248,367,260,364,261,363,269,362,274,365,273,371,278,373,287,367,294,364,291,357,289,351,284,350,281,347,284,345,296,343,302,341,305,337,305,334,307,327,312,319,317,310,315,307,310,305,304,308,301,311,296,311,291,308,290,312,292,315,289,318,280,324,275,323,269,322,271,317,272,312,274,310,277,304,281,302,281,296,277,295,272,302,271,291,269,298,266,297,267,288,263,290,260,296,260,301,262,310,265,316,265,322,262,324,259,329,257,332" target="_blank" alt="Jalisco" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/jalisco.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/jalisco/index.html" />
							<area shape="poly" coords="287,301,292,299,297,296,301,293,303,295,304,300,307,305,308,307,305,308,301,310,300,310,298,311,294,310,293,309,290,311,289,310,288,307,288,303,286,300" target="_blank" alt="Aguascalientes" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/aguascalientes.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/aguascalientes/index.html" />
							<area shape="poly" coords="345,172,348,177,350,183,348,189,352,193,354,195,355,201,360,207,365,209,370,211,373,215,373,221,372,229,364,236,361,240,354,244,354,250,356,256,357,260,356,264,351,264,348,271,344,275,341,276,336,274,337,262,333,251,332,247,330,244,329,237,331,232,338,229,341,227,337,221,331,215,328,208,324,202,330,197,335,192,334,190,331,189,332,184,336,178,346,171" target="_blank" alt="Nuevo Le&oacute;n" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/nuevoLeon.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/nuevo-leon/index.html" />
							<area shape="poly" coords="250,367,255,371,261,375,266,378,271,376,274,373,274,369,274,364,268,362,264,363,257,366,255,366,250,367" target="_blank" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/colima.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/colima/index.html" />
							<area shape="poly" coords="347,174" href="#mapa" />
							<area shape="poly" coords="348,174,353,173,354,180,356,185,359,191,360,198,368,203,376,206,393,211,401,214,401,222,395,225,391,226,391,234,389,237,391,240,390,244,388,246,389,249,391,252,391,256,392,258,390,260,389,263,391,267,391,277,388,286,387,291,388,295,383,295,376,296,369,296,365,295,360,295,355,291,353,289,345,287,342,282,342,276,344,273,348,269,351,264,355,262,355,253,355,245,360,240,367,233,373,229,372,220,373,213,369,210,364,208,361,208,357,203,355,198,353,193,349,190,349,186,350,178,347,174" target="_self" alt="Tamaulipas" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/tamaulipas.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/tamaulipas/index.html" />
							<area shape="poly" coords="330,248,333,253,337,259,336,264,337,266,336,270,336,276,343,276,344,281,344,285,349,288,353,289,357,293,359,295,366,296,375,297,379,297,381,297,383,299,380,304,380,306,379,308,377,311,379,314,380,320,379,321,372,321,369,319,366,313,365,311,362,314,362,313,357,316,356,315,350,316,345,312,338,312,336,314,328,311,325,309,319,308,318,302,319,293,318,290,314,291,311,294,307,289,304,286,302,280,301,275,304,273,311,268,320,261,325,253,331,247" target="_blank" alt="San Luis Potos&iacute;" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/SanLuisPotosi.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/san-luis-potosi/index.html" />
							<area shape="poly" coords="318,307,327,310,335,315,340,313,347,312,349,315,354,317,355,320,351,321,348,324,348,328,344,329,336,329,337,335,340,342,345,345,342,348,334,352,328,351,328,348,324,348,321,350,318,346,318,341,313,344,310,345,308,344,305,341,303,340,306,335,308,329,311,321,316,313,318,305" target="_blank" alt="Guanajuato" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/guanajuato.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/guanajuato/index.html" />
							<area shape="poly" coords="268,378,273,386,277,388,283,389,289,392,296,394,301,395,303,391,308,390,309,383,312,381,316,383,323,383,330,384,334,386,336,385,336,381,339,377,342,371,343,365,346,355,348,347,347,345,336,350,329,349,327,348,323,349,320,347,318,342,315,344,311,345,305,341,302,342,291,344,284,347,285,350,290,352,291,358,293,362,295,363,288,367,276,373,267,378" target="_blank" alt="Michoac&aacute;n" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/michoacan.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/michoacan/index.html" />
							<area shape="poly" coords="300,396,307,395,315,401,327,411,343,416,353,420,358,423,373,426,379,428,381,431,386,428,392,425,391,417,387,409,384,398,380,394,372,387,369,383,367,382,365,384,362,380,358,376,353,380,348,383,345,384,342,384,340,376,338,379,337,382,336,386,331,386,328,385,319,383,314,382,310,383,309,387,305,390,300,396" target="_blank" alt="Guerrero" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/guerrero.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/guerrero/index.html" />
							<area shape="poly" coords="336,330,346,329,348,327,350,321,354,319,356,316,364,313,369,318,372,321,368,324,363,323,361,327,361,330,360,334,357,335,353,337,352,342,352,343,349,346,345,346,340,341,337,336,336,330" target="_blank" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/queretaro.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/queretaro/index.html" />
							<area shape="poly" coords="613,302,619,303,621,307,620,313,618,321,613,328,612,334,613,339,610,343,608,345,612,348,612,350,610,352,609,354,613,356,610,375,606,372,604,369,604,364,604,363,602,363,600,367,598,371,593,373,590,378,587,382,585,383,583,374,583,356,583,352,599,333,605,325,611,313,612,303" target="_blank" alt="Quintana Roo" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/quintanaRoo.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/quintana-roo/index.html" />
							<area shape="poly" coords="611,301" href="#mapa" />
							<area shape="poly" coords="610,301,601,299,595,301,588,302,583,306,570,309,563,311,557,315,553,317,553,326,557,329,560,329,562,329,582,353,586,348,588,345,595,339,602,328,608,320,613,309,611,301" target="_blank" alt="Yucat&aacute;n" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/yucatan.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/yucatan/index.html" />
							<area shape="poly" coords="551,324,552,334,552,339,550,344,549,344,548,354,546,356,537,365,532,366,537,369,536,373,531,377,528,376,526,373,518,375,517,380,523,382,524,387,526,389,528,391,532,384,540,386,545,386,545,391,554,391,564,389,582,385,583,379,583,364,582,352,574,343,561,329,552,322" target="_blank" alt="Campeche" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/campeche.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/campeche/index.html" />
							<area shape="poly" coords="521,464" href="#mapa" />
							<area shape="poly" coords="521,466,525,459,524,456,522,451,522,447,527,439,532,428,558,429,558,421,555,420,552,413,544,410,539,406,536,402,533,397,528,392,522,393,517,393,511,398,506,403,504,400,502,398,500,393,496,394,493,395,492,401,487,406,483,410,480,418,480,425,480,432,482,435,494,441,511,454,520,465" target="_blank" alt="Chiapas" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/chiapas.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/chiapas/index.html" />
							<area shape="poly" coords="518,376,510,377,506,385,499,385,489,387,481,391,478,393,487,398,487,400,489,403,492,400,495,395,500,394,506,401,513,398,516,394,523,392,527,392,533,398,536,403,543,405,544,394,545,386,533,385,531,389,527,391,525,387,521,381,517,376" target="_blank" alt="Tabasco" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/tabasco.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/tabasco/index.html" />
							<area shape="poly" coords="478,433,473,430,463,428,458,431,454,435,442,440,432,444,427,445,414,443,409,441,402,441,392,438,388,433,383,432,392,425,393,417,387,408,385,399,383,396,397,394,398,390,399,389,402,394,409,392,411,389,416,388,419,383,420,379,424,381,425,385,430,389,433,392,433,394,440,395,439,398,439,400,439,403,443,403,450,399,454,400,456,404,458,406,461,410,475,409,482,409,484,410,478,434" target="_blank" alt="Oaxaca" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/oaxaca.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/oaxaca/index.html" />
							<area shape="poly" coords="387,295,391,297,394,298,396,301,398,305,401,308,405,313,398,311,396,309,409,332,414,337,419,343,424,350,427,358,430,363,431,370,436,376,448,380,461,385,470,392,476,392,482,395,488,401,488,404,485,407,474,409,463,408,453,398,441,402,440,395,433,392,427,386,422,378,418,380,411,381,405,376,409,369,412,368,406,360,406,356,409,345,403,344,400,344,397,340,399,335,396,328,390,334,382,338,379,339,383,332,387,324,385,321,380,320,378,312,381,299,380,294,383,294,385,294" target="_blank" alt="Veracruz" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/veracruz.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/veracruz/index.html" />
							<area shape="poly" coords="351,343,359,346,360,350,361,353,368,349,369,350,370,354,378,352,380,357,378,360,385,355,388,342,388,344,389,334,383,339,381,337,383,330,386,326,386,323,381,320,371,320,367,323,364,324,361,327,360,331,356,336,354,336,349,344" target="_blank" alt="Hidalgo" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/hidalgo.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/hidalgo/index.html" />
							<area shape="poly" coords="386,356,391,356,394,359,397,361,399,363,393,367,388,369,382,364,378,359,384,355" target="_blank" alt="Tlaxcala" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/tlaxcala.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/tlaxcala/index.html" />
							<area shape="poly" coords="386,354,388,343,389,334,396,329,398,333,398,338,398,343,402,344,408,345,408,350,405,356,405,362,410,364,411,367,408,369,407,376,410,380,419,380,417,385,409,391,400,390,397,388,396,392,391,393,383,395,377,390,372,386,379,382,378,375,378,373,378,366,377,359,387,369,399,364,387,354" target="_blank" alt="Puebla" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/puebla.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/puebla/index.html" />
							<area shape="poly" coords="360,377,364,373,370,372,376,374,377,378,379,383,372,386,369,382,365,384,359,378" target="_blank" alt="Morelos" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/morelos.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/morelos/index.html" />
							<!--  <area shape="poly" coords="350,344,359,346,361,353,368,351,370,354,377,353,378,358,378,361,378,373,374,370,372,364,372,359,365,360,363,364,364,371,363,374,358,377,352,379,346,383,339,379,342,370,346,352,350,344" target="_blank" alt="Estado de M&eacute;xico" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/estadoMexico.png',1)" onmouseout="MM_swapImgRestore()" href="http://administrador.empleo.gob.mx/consultaPublicacion.do?method=consultaPublicacion&amp;idEntidad=15&amp;idTipoPublicacion=2" />-->
							<area shape="poly" coords="350,344,359,346,361,353,368,351,370,354,377,353,378,358,378,361,378,373,374,370,372,364,372,359,365,360,363,364,364,371,363,374,358,377,352,379,346,383,339,379,342,370,346,352,350,344" target="_blank" alt="Estado de M&eacute;xico" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/estadoMexico.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/estado-de-mexico/index.html" />
							
							<area shape="poly" coords="365,360,371,360,372,367,375,372,370,372,364,372,362,367,363,360" target="_blank" alt="Ciudad de México" onmouseover="MM_swapImage('MapaOk','','${context}/css/images/mapaPeriodico/df.png',1)" onmouseout="MM_swapImgRestore()" href="${ftp}/revistas/ciudad-de-mexico/index.html" />
						</map>
						<div class="page-header">
							<h3 class="titulosh2">Descarga la revista de tu estado</h3>
						</div>
						<a name="lista"></a>
						<ul class="list-group-contenidos pull-left-list">
						    <li><a href="${ftp}/revistas/aguascalientes/index.html" target="_blank">Aguascalientes</a></li>
						    <li><a href="${ftp}/revistas/baja-california/index.html" target="_blank">Baja California</a></li>
						    <li><a href="${ftp}/revistas/baja-california-sur/index.html" target="_blank">Baja California Sur</a></li>
						    <li><a href="${ftp}/revistas/campeche/index.html" target="_blank">Campeche</a></li>
						    <li><a href="${ftp}/revistas/chiapas/index.html" target="_blank">Chiapas</a></li>
						    <li><a href="${ftp}/revistas/chihuahua/index.html" target="_blank">Chihuahua</a></li>
						    <li><a href="${ftp}/revistas/coahuila/index.html" target="_blank">Coahuila</a></li>
						    <li><a href="${ftp}/revistas/colima/index.html" target="_blank">Colima</a></li>
						    <li><a href="${ftp}/revistas/ciudad-de-mexico/index.html" target="_blank">Ciudad de México</a></li>
						    <li><a href="${ftp}/revistas/durango/index.html" target="_blank">Durango</a></li>
						    <li><a href="${ftp}/revistas/guanajuato/index.html" target="_blank">Guanajuato</a></li>
						    <li><a href="${ftp}/revistas/guerrero/index.html" target="_blank">Guerrero</a></li>
						    <li><a href="${ftp}/revistas/hidalgo/index.html" target="_blank">Hidalgo</a></li>
						    <li><a href="${ftp}/revistas/jalisco/index.html" target="_blank">Jalisco</a></li>
						    <!--  <li><a href="http://administrador.empleo.gob.mx/consultaPublicacion.do?method=consultaPublicacion&amp;idEntidad=15&amp;idTipoPublicacion=2" target="_blank">Estado de M&eacute;xico</a></li>-->
						    <li><a href="${ftp}/revistas/estado-de-mexico/index.html" target="_blank">Estado de M&eacute;xico</a></li>
						    
						    <li><a href="${ftp}/revistas/michoacan/index.html" target="_blank">Michoac&aacute;n</a></li>
						    <li><a href="${ftp}/revistas/morelos/index.html" target="_blank">Morelos</a></li>
						    <li><a href="${ftp}/revistas/nayarit/index.html" target="_blank">Nayarit</a></li>
						    <li><a href="${ftp}/revistas/nuevo-leon/index.html" target="_blank">Nuevo Le&oacute;n</a></li>
						    <li><a href="${ftp}/revistas/oaxaca/index.html" target="_blank">Oaxaca</a></li>
						    <li><a href="${ftp}/revistas/puebla/index.html" target="_blank">Puebla</a></li>
						    <li><a href="${ftp}/revistas/queretaro/index.html" target="_blank">Quer&eacute;taro</a></li>
						    <li><a href="${ftp}/revistas/quintana-roo/index.html" target="_blank">Quintana Roo</a></li>
						    <li><a href="${ftp}/revistas/san-luis-potosi/index.html" target="_blank">San Luis Potos&iacute;</a></li>
						    <li><a href="${ftp}/revistas/sinaloa/index.html" target="_blank">Sinaloa</a></li>
						    <li><a href="${ftp}/revistas/sonora/index.html" target="_blank">Sonora</a></li>
						    <li><a href="${ftp}/revistas/tabasco/index.html" target="_blank">Tabasco</a></li>
						    <li><a href="${ftp}/revistas/tamaulipas/index.html" target="_blank">Tamaulipas</a></li>
						    <li><a href="${ftp}/revistas/tlaxcala/index.html" target="_blank">Tlaxcala</a></li>
						    <li><a href="${ftp}/revistas/veracruz/index.html" target="_blank">Veracruz</a></li>
						    <li><a href="${ftp}/revistas/yucatan/index.html" target="_blank">Yucat&aacute;n</a></li>
						    <li><a href="${ftp}/revistas/zacatecas/index.html" target="_blank">Zacatecas</a></li>
						</ul> 
					    	   
					</div> 
				</div> 
			</div>
		</div>
	</jsp:body>
</t:publicTemplate>
