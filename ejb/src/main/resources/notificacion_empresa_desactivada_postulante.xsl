<!DOCTYPE xsl:stylesheet [
<!ENTITY aacute "á">
<!ENTITY NL "<xsl:text>&#xa;</xsl:text>">
]>


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
          			<br/>
                	<p align="left"><font face="Arial, Helvetica, sans-serif" color="#222222" size="2"><b>Notificación.</b></font></p>
                	<p align="center"><img src="http://empleo.gob.mx/images/logo-empleo-email.jpg" alt="portal del empleo" width="248" height="40" /></p>
                	<p align="left"><font face="Arial, Helvetica, sans-serif" color="#222222" size="2"><b>Saludos <xsl:value-of select="destinatario/nombreCandidato" />.</b></font></p>                	
                    <p align="left"><font face="Arial, Helvetica, sans-serif" color="#222222" size="2"><xsl:value-of select = "destinatario/mensaje" /></font></p>
                    
                    
                    <p><font face="Arial, Helvetica, sans-serif" color="#222222" size="2">
						  La empresa <xsl:value-of select="destinatario/nombre" />  
						  que publicó la oferta de empleo <xsl:value-of select="destinatario/tituloOfertaEmpleo" /> 
						  ha desactivado su cuenta en el Portal del Empleo, por lo tanto se le informa
						  que ya no será posible contactarlo.						 
						  <br />
				    </font></p>
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
    <td height="53" valign="top"><img src="http://empleo.gob.mx/images/footer-email.jpg" width="600" height="314" /></td>
  </tr>
</table>
</body>
</html>
	
	</xsl:template>
</xsl:stylesheet>