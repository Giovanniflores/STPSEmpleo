<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Documento sin título</title>
</head>

<body>
<table width="585" style="width: 662px; margin: auto; border: 1px solid #75a201; font-family:Tahoma, Geneva, sans-serif;">
	<tr>
    	<td style="padding: 0 0 0 20px !important;" colspan="3">
		
    	</td>
    </tr>
    <tr>
    	<td style="padding-right:20px;padding-left:20px;" colspan="3">
        	<table width="615" cellspacing="0">
            	<tr>
    				<td style="padding-right:20px;padding-left:20px;" colspan="3">
        				<table width="615" cellspacing="0">
				            <tr>
				                <td height="79"><img src="http://empleo.gob.mx/images/cabecera-email.jpg" width="607" height="79" /></td>
				            </tr>
            			</table>
        			</td>
    			</tr>
            </table>
        </td>
    </tr>
    <tr>
    	<td width="524" style="padding:5px 20px; font-size: 12px; line-height: 14px;">
        	<p>Estimado, <strong style="font-size:14px;"><xsl:value-of select = "destinatario/nombreContacto" /></strong></p><br />
          	<strong style="font-size:14px;"><xsl:value-of select = "destinatario/nombre" /></strong><br />                 
        </td>
        <td width="38"></td>
        <td width="7"></td>
    </tr>
    <tr>
		<td style="padding:20px 20px 0; font-size: 13px; line-height: 22px; text-align: center;" colspan="3">
        	<p>El Servicio Nacional de Empleo (SNE) te notifica que el candidato <xsl:value-of select="destinatario/nombreCandidato" />, 
        	<xsl:if test="destinatario/inscritoPPC='inscritoPPC'"><xsl:text>inscrito en el Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD),</xsl:text></xsl:if>
        	 se ha postulado a la oferta de empleo de <xsl:value-of select="destinatario/tituloOfertaEmpleo" />.</p><br/>
        	
			<p><strong style="font-size:14px;">Para mayor detalle del candidato, entra en "Mi espacio" y consulta la opción "Mis postulantes", selecciona el nombre del candidato y obtendrás su perfil laboral.</strong></p><br/>
			
			<p><xsl:if test="destinatario/inscritoPPC='inscritoPPC'"><xsl:text>Es necesario notificar tu respuesta al candidato, para que él pueda dar seguimiento a su postulación.</xsl:text></xsl:if></p> 
    	</td>
    </tr>
   
    <tr>
    	<td style="padding:0 20px 20px;font-size: 12px; line-height: 22px; text-align: center;" colspan="3">
            
			<p><strong>Este comunicado es una notificación oficial del Servicio Nacional de Empleo (SNE) que se genera de manera automática, favor de no responder a este correo.
				</strong><br />
              </p>
			<p><strong>Esta notificación se emitió con copia para el candidato postulado a la oferta de empleo.
				</strong><br />
              </p>              
              <p><strong>Recibe un cordial saludo.</strong><br />
              </p>
		</td>
    </tr>
    <tr>
		<td colspan="3" style="padding-right:20px;padding-left:20px;">
        <table width="613">
        	<tr>
            	<td width="605" style="border-top: 1px solid #d3d3d3; text-align: center; font-size: 12px; padding-top: 19px; padding-bottom: 19px;">
                "Este programa es público, ajeno a cualquier partido político. <br />
                Queda prohibido el uso para fines distintos al desarrollo social."
                </td>
            </tr>
        </table>
    	</td>
    </tr>
</table>

</body>
</html>

	</xsl:template>
</xsl:stylesheet>

