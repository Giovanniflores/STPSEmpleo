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
        	<p><strong style="font-size:14px;"><xsl:value-of select = "destinatario/nombreCandidato" /></strong></p><br />
        </td>
        <td width="38"></td>
        <td width="7"></td>
    </tr>
    <tr>
		<td style="padding:20px 20px 0; font-size: 13px; line-height: 22px; text-align: center;" colspan="3">
        	<p>El Servicio Nacional de Empleo (SNE) te notifica que ya estás inscrito en el Programa de Promoción y Colocación del Seguro de Desempleo (PPC-SD).</p><br/>
        	
			<p>Se adjunta el documento de <strong>Términos y Condiciones</strong> del PPC-SD en formato PDF.</p><br/>
			
			<p>Para seguir participando en el PPC-SD deberás cumplir con todos los requisitos del programa, como dar seguimiento a tus postulaciones por medio del Portal del Empleo.</p><br/>

    	</td>
    </tr>
   
    <tr>
    	<td style="padding:0 20px 20px;font-size: 12px; line-height: 22px; text-align: center;" colspan="3">
            
			<p><strong>Este comunicado es una notificación oficial del Servicio Nacional de Empleo (SNE) que se genera de manera automática, favor de no responder a este correo.
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

