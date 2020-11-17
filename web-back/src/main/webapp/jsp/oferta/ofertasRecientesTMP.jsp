<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String context = request.getContextPath() +"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
<title>Portal del Empleo :: Ofertas</title> 

<link href="${PATH_CSS_SWB_APP}estilos_empleo.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function doSubmit(method){
	document.OfertasRecientesForm.method.value=method;
	document.OfertasRecientesForm.submit();		
}
</script>
  <script type="text/javascript">
  function redirectSWB(url){
	window.top.location = url;
  }
  </script>

<!-- Nuevo código analytics multidominio 01/06/2017 -->
<script> 
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    ga('create', 'UA-26166631-1', 'auto', {'allowLinker': true});
	ga('require', 'displayfeatures', 'linker');
	ga('linker:autoLink', ['publicaciones.empleo.gob.mx'] );
	ga('send', 'pageview');
</script>
 
</head>

<body>

<div id="contenido_principal">

<form name="OfertasRecientesForm" id="OfertasRecientesForm" method="post" action="<%=context %>portalEmpleoTmp.do">
	<div id="ofertas">
		<input type="hidden" name="method" id="method" value="ofertasRecientes"/>
		<input type="hidden" name="numRegistros" id="numRegistros" value="6"/>

    	<h3>Ofertas de empleo</h3>
	        <a href="javascript:doSubmit('ofertasRecientes');" class="ofertas_recientes" >Ofertas recientes</a>
	        <a href="<%=context %>ofertasRecientes.do?method=ofertasDestacadas" class="ofertas_destacadas" >Ofertas destacadas </a>
	        <a href="<%=context %>ofertasRecientes.do?method=ofertasCanada" class="ofertas_canada" >Ofertas en Canadá</a>		 
	        
        <table cellpadding="0" cellspacing="0">
        	<tbody>    
   			<c:forEach var="ofertaReciente" items="${OfertasRecientesForm.ofertas}" varStatus="rowCounter">
				<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>    	
    				<td>
    					<strong>
    						<c:choose>
	    						<c:when test="${OFERTA_RECIENTE == 3}">	
	    							<a href="/es_mx/empleo/Ofertas_Empleo?id_oferta_empleo=${ofertaReciente.idOfertaEmpleo}">${ofertaReciente.tituloOferta}</a>
	    						</c:when>	   
	    						<c:otherwise>	
	    							<a href="/es_mx/empleo/Ofertas_Empleo?id_oferta_empleo=${ofertaReciente.idOfertaEmpleo}">${ofertaReciente.tituloOferta}</a>
	    						</c:otherwise>		    						 						
    						</c:choose>	

    						
    					</strong>
    				</td>
        			<td>${ofertaReciente.ubicacion}</td>
        			<td>${ofertaReciente.vigencia}</td>
    			</tr>
    		</c:forEach>    		
    		</tbody>

        </table>         
        <div class="borde_inferior">        	
        	<div style="font-family:Arial, Helvetica, sans-serif; font-size:11px; color:#000000; text-decoration: none; font-weight:bold; position: relative; left: 30px;">
        	 		<c:if test="${OFERTA_RECIENTE == 1}">
						<a href="<%=context %>ofertasRecientes.do?method=totalOfertasRecientes">ver m&aacute;s</a>
					</c:if>
					<c:if test="${OFERTA_RECIENTE == 2}">					
						<a href="<%=context %>ofertasRecientes.do?method=totalOfertasDestacadas">ver m&aacute;s</a>			
					</c:if>	
					<c:if test="${OFERTA_RECIENTE == 3}">					
						<a href="<%=context %>ofertasRecientes.do?method=totalOfertasCanada">ver m&aacute;s</a>			
					</c:if>						
        	</div>          		
        </div>
        
        <%--
        <div id="bt_ofertas_mail">
        	<span class="recibe_ofertas">Recibe ofertas</span>
        	<a href="#"> Por SMS&nbsp;<img src="images/ico_sms.png" alt="Mensajes de texto" /></a>
        	<a href="#">Por correo electrónico&nbsp;<img src="images/ico_email.png" alt="e-mail" /></a>
        </div>
        --%>
	</div>
</form>
</div>

<c:if test="${not empty errmsg}">
<script>
	alert('${errmsg}');
	<%session.removeAttribute("errmsg");%>
</script>
</c:if>

</body>
</html>