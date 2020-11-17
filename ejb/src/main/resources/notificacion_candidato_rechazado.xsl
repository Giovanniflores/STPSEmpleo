<xsl:stylesheet  version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Portal del Empleo :: Candidato Rechazado</title>
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

					<div id="registro_bienvenida" style="font-family: Tahoma, Geneva, sans-serif;margin: 0 40px 20px;padding: 20px 0;">
											
						<p class="registro_datos" style="background: #f9f9f9;border: 1px solid #b9b9b9;padding: 16px;width: 240px;-webkit-border-radius: 15px;-moz-border-radius: 15px;border-radius: 15px;font-size: 110% !important;letter-spacing: .05em;behavior: url(images/PIE.htc); position: relative;">
						<xsl:if test="destinatario/tipoPersona='moral'"><xsl:text>La empresa </xsl:text></xsl:if> 
						<xsl:value-of select="destinatario/nombre"/> te agradece el interés mostrado para postularte a la oferta de empleo <xsl:value-of select="destinatario/tituloOfertaEmpleo"/> y te comunica que ha decidido no continuar con el proceso de selección. 
                        Recuerda que puedes encontrar más ofertas en el Portal del Empleo.

						</p>
					</div>	

					<p align="center"><img src="http://empleo.gob.mx/images/logo-empleo-email.jpg" alt="portal del empleo" width="248" height="40" /></p>

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
    <td height="53" valign="top"><img src="http://empleo.gob.mx/images/footer-email.jpg" width="600" height="314" /></td>
  </tr>
</table>
</body>
</html>

	</xsl:template>
</xsl:stylesheet>
