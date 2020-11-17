<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />  
<title>Portal del Empleo :: Ofertas</title>

<link href="${PATH_CSS_SWB_APP}estilos_empleo.css" rel="stylesheet" type="text/css" />
<link href="${PATH_CSS_SWB_APP}estilos_mi_espacio.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">	
function doSubmit(action){
	if (action != null)
		document.OfertasRecientesForm.action=action;
	else
		document.OfertasRecientesForm.action="ofertasRecientes.do";		
		
	document.OfertasRecientesForm.submit();
}
</script>
</head>

<body>
<div id="contenido_principal">

<form name="OfertasRecientesForm" id="OfertasRecientesForm" method="post" action="">
  	<div id="ofertas">
  		<input type="hidden" name="method" id="method" value="ofertasRecientes"/>
  	
        <h3>Ofertas de empleo</h3>
        <a href="#" class="ofertas_recientes">Ofertas recientes</a> <a href="#" class="ofertas_destacadas">Ofertas destacadas </a>

        <table cellpadding="0" cellspacing="0">
        <tbody>
			<tr class="odd">
				<td colspan="3">La transacción no pudo ser completada, favor de intentarlo nuevamente después de unos instantes.</td>    	
    		</tr>
			<tr class="odd">
				<td colspan="3">
					<input type="button" name="btnReintentar" id="btnReintentar" value="Intentar nuevamente" onclick="javascript:doSubmit('${_urlpageinvoke}')" />
					<!--  <input type="button" name="btnReintentar" id="btnReintentar" value="Intentar nuevamente" onclick="javascript:doSubmit('"+${_urlpageinvoke}+"');" />-->
				</td>
    		</tr>
        </tbody>
        </table>
                
        <div class="borde_inferior"></div>
        <%--
     	<div id="bt_ofertas_mail"><span class="recibe_ofertas">Recibe ofertas</span><a href="#"> Por SMS&nbsp;<img src="images/ico_sms.png" alt="Mensajes de texto" /></a> <a href="#">Por correo electrónico&nbsp;<img src="images/ico_email.png" alt="e-mail" /></a></div>
     	--%>
     </div>
</form>

</div>

</body>
</html>