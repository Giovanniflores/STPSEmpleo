<xsl:stylesheet  version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
	

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>:: Nuevo portal del Empleo ::</title>
</head>

<body>
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="79"><img src="http://empleo.gob.mx/images/cabecera-email.jpg" width="600" height="79" /></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right" valign="top" background="http://empleo.gob.mx/images/fnd_body-email.jpg">
          	
          	<table width="100%" border="0" cellspacing="6" cellpadding="0">
              <tr>
                <td>
                	<img src="http://empleo.gob.mx/images/transp-email.gif" width="20" height="20" />
                	<p align="left"><font face="Arial, Helvetica, sans-serif" color="#222222" size="2"><b>Confirmación de registro </b></font></p>
                	<p align="left"><font face="Arial, Helvetica, sans-serif" color="#222222" size="2"><b>Estimado <xsl:value-of select="destinatario/nombre" />.</b></font></p>
                    <p align="left"><font face="Arial, Helvetica, sans-serif" color="#222222" size="2">El Servicio Nacional del Empleo (SNE) te notifica que tu registro en el Portal del Empleo ya se encuentra activo:</font></p>
	                <p align="center"><img src="http://empleo.gob.mx/images/logo-empleo-email.jpg" alt="portal del empleo" width="248" height="40" /></p>
	                <p style="text-align: center;">
	                  		<font face="Arial, Helvetica, sans-serif" color="#222222" size="2">
	                  			<b>Estos son los datos de tu registro:</b>
	                  		</font>
	                </p>
	                <p style="text-align: center;">
							Usuario:
							<xsl:value-of select="destinatario/login" />
							<br />
							Contraseña:
							<xsl:value-of select="destinatario/password" />
							<br />
							ID Portal del Empleo:
							<xsl:value-of select="destinatario/idPortalEmpleo" />						
					</p>
					<p align="center">
		                  	<font face="Arial, Helvetica, sans-serif" color="#222222" size="3">
		                  		Te recordamos que para hacer uso de los beneficios de tu registro,<br />
		                  		es necesario crear una oferta de empleo, dentro de los próximos 30 días,<br />
		                  		para evitar que el registro sea cancelado.
		                  	</font>
		             </p>
		             <p align="center">
		                  	<font face="Arial, Helvetica, sans-serif" color="#222222" size="3">
		                  		Si deseas crear una oferta de empleo, ingresa a www.empleo.gob.mx e inicia sesión.<br />
		                  		Si ya creaste una oferta, haz caso omiso de este mensaje.
		                  	</font>
		             </p>
		             <p align="center">
		                  	<font face="Arial, Helvetica, sans-serif" color="#5F7A18" size="3">
		                  		Este comunicado es una notificación oficial del Portal del Empleo que se genera de manera automática,<br />
		                  		favor de no responder a este correo.<br />
								Recibe un cordial saludo.
		                  	</font>
		             </p>               
                  </td>                  
              </tr>
            </table>
            </td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="53" valign="top"><img src="http://empleo.gob.mx/images/footer-email.jpg" width="600" height="314" /></td>
  </tr>
</table>
</body>
</html>
	

	</xsl:template>
</xsl:stylesheet>