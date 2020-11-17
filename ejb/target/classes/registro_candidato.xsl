<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Registro de Candidato</title>
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
                <td width="367" style="border-top: 2px solid #d3d3d3;border-bottom: 2px solid #d3d3d3;border-collapse: collapse;padding-top:20px;padding-bottom:20px;">
                    <img src="http://qa.empleo.gob.mx/images/logo-portal.png"/>
                    
                </td>
                <td width="144" style="border-top: 2px solid #d3d3d3;border-bottom: 2px solid #d3d3d3;border-collapse: collapse;">
                    <img src="http://qa.empleo.gob.mx/images/logo-STPS.png"/>
               </td>
                 <td width="96" style="border-top: 2px solid #d3d3d3;border-bottom: 2px solid #d3d3d3;border-collapse: collapse;">
                    <img src="http://qa.empleo.gob.mx/images/logo-SNE.png"/>
                </td>
            </tr>
            </table>
        </td>
    </tr>
    <tr>
    	<td width="524" style="padding:5px 20px; font-size: 12px; line-height: 14px;">
        	<strong style="font-size:14px;"><xsl:value-of select = "destinatario/nombreCandidato" /></strong><br />
        </td>
        <td width="38"></td>
        <td width="7"></td>
    </tr>
    <tr>
		<td style="padding:20px 20px 0; font-size: 13px; line-height: 22px; text-align: center;" colspan="3">
        	<p>El Servicio Nacional del Empleo (SNE) te notifica que tu registro en el Portal del Empleo ya se encuentra activo: 
        	</p>
        	</td>        	
    </tr>
    <tr>
    	<td width="524" style="padding:5px 20px; font-size: 12px; line-height: 14px;">
        	<strong style="font-size:14px;">Estos son los datos de tu registro:        	
        	</strong><br />
        	Usuario: <xsl:value-of select = "destinatario/login" /><br />
        	Contraseña: <xsl:value-of select = "destinatario/password" /><br />
        </td>
        <td width="38"></td>
        <td width="7"></td>
    </tr>    
    <tr>
    	<td style="padding:20px 20px 0; font-size: 13px; line-height: 22px; text-align: right;" colspan="3">
        	Fecha de alta  <xsl:value-of select = "destinatario/dia" />/<xsl:value-of select = "destinatario/mes" />/<xsl:value-of select = "destinatario/anio" /> 
        </td>
        <td width="38"></td>
        <td width="7"></td>
    </tr>
    <tr>
		<td style="padding:20px 20px 0; font-size: 13px; line-height: 22px; text-align: center;" colspan="3">
        	<p>Te recordamos que a partir de este momento, podrás hacer uso de  las 
        	<a href="www.empleo.gob.mx">herramientas y servicios</a> que ofrece el Portal del Empleo.
        	<br />
        	Si deseas postularte a una oferta de empleo, ingresa a <a href="www.empleo.gob.mx">www.empleo.gob.mx</a> e inicia sesión.
        	</p>
        	</td>        	
    </tr> 
    <tr>
    	<td colspan="3" style="text-align:center">
    		<br />
            <br />
            <br />
    	</td>
    </tr>          
   
    <tr>
    	<td style="padding:0 20px 20px;font-size: 11px; line-height: 22px; text-align: center;" colspan="3">
            
			<p>Este comunicado es una notificación oficial del Servicio Nacional de Empleo (SNE) que se genera de manera automática, favor de no responder a este correo.
				
              </p>
              <p><strong>Recibe un cordial saludo.</strong>
              </p><br/>
		</td>
    </tr>
    <tr>
		<td colspan="3" style="padding-right:20px;padding-left:20px;">
        <table width="613">
        	<tr>
            	<td width="605" style="border-top: 1px solid #d3d3d3; text-align: center; font-size: 10px; padding-top: 19px; padding-bottom: 19px;">
                Este programa es público, ajeno a cualquier partido político. <br />
                Queda prohibido el uso para fines distintos al desarrollo social. 
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

