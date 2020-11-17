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
          <td align="right" valign="top" background="https://www.empleo.gob.mx/images/fnd_body-email.jpg">
          	
          	<table width="100%" border="0" cellspacing="6" cellpadding="0">
              <tr>
                <td>
                	<img src="http://empleo.gob.mx/images/transp-email.gif" width="20" height="20" />
                	<p align="left"><font face="Arial, Helvetica, sans-serif" color="#222222" size="2"><b>Saludos <xsl:value-of select="destinatario/nombre" />.</b></font></p>
                	<p align="left"><font face="Arial, Helvetica, sans-serif" color="#222222" size="2"><b>Acceso para ingresar a su cuenta.</b></font></p>
                    <p align="left"><font face="Arial, Helvetica, sans-serif" color="#222222" size="2">El Portal del Empleo le notifica que para activar su cuenta en el sistema deberá ingresar a:</font></p>
		              <table style="text-align: left;" width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="padding:10px;background-color:#E9E9E9;border-left:1px solid #C1C1C1;border-right:1px solid #C1C1C1;border-top:1px solid #C1C1C1;border-bottom:1px solid #C1C1C1">
								<a>
									<xsl:attribute name="href">
			       						<xsl:value-of select="destinatario/encode"/>
			       					</xsl:attribute> 
			       					<xsl:value-of select="destinatario/encode"/> 
			       				</a> 
							</td>
						</tr>
					</table>
                  <p align="center"><img src="https://www.empleo.gob.mx/images/logo-empleo-email.jpg" alt="portal del empleo" width="248" height="40" /></p>
                  <p align="left"><font face="Arial, Helvetica, sans-serif" color="#222222" size="2">Si no puede acceder a la liga, copie  y péguela en su navegador.</font></p>
                  <p style="text-align: left;">
						Usuario:
						<xsl:value-of select="destinatario/login" />
						<br />
						Contraseña:
						<xsl:value-of select="destinatario/password" />
				  </p>
	                  <p align="center">
	                  	<font face="Arial, Helvetica, sans-serif" color="#5F7A18" size="3">
	                  		Considere este comunicado como la notificación oficial a su acceso al Portal del Empleo.
	                  	</font>
	                  </p>
	                  
	                  <p align="center">
	                  	<font face="Arial, Helvetica, sans-serif" color="#5F7A18" size="3">
	                  		No responder a este correo.
	                  	</font>
	                  </p>
	                  
	                   <p align="center">
	                  	<font face="Arial, Helvetica, sans-serif" color="#5F7A18" size="3">
	                  		Reciba un cordial saludo.
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
    <td height="53" valign="top"><img src="https://www.empleo.gob.mx/images/footer-email.jpg" width="600" height="314" /></td>
  </tr>
</table>
</body>
</html>
	

	</xsl:template>
</xsl:stylesheet>
