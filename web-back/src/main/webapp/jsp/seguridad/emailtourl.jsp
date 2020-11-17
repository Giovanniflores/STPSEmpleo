<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.web.security.form.ActivacionForm" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
	.red2Font{
		color: #e3670b;
	}
	.black2Font{
		color: #4f4f4f;
	}
	.Font80{
		font-size: 80%;
	}
	.arialFont{
		font-family: font-family:Arial,Helvetica,sans-serif;
	}
</style>
<%String context = request.getContextPath() +"/";
ActivacionForm activacionForm = (ActivacionForm)session.getAttribute("activacionForm");	
%>

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
                	<p align="left" class="arialFont black2Font Font80" ><strong>&nbsp;&nbsp;&nbsp;Saludos ${activacionForm.confirmacion.nombre}.</strong></p>
                	<p align="left" class="arialFont black2Font Font80" ><strong>&nbsp;&nbsp;&nbsp;Acceso para ingresar a su cuenta.</strong></p>
                    <p align="left" class="arialFont black2Font Font80" >&nbsp;&nbsp;&nbsp;El portal del empleo le notifica que para activar su cuenta en el sistema deberá ingresar a:</p>
		              <table>
						<tr>
							<td style="padding:10px;background-color:#fff9d7;border-left:1px solid #e2c822;border-right:1px solid #e2c822;border-top:1px solid #e2c822;border-bottom:1px solid #e2c822">
								<a href="${activacionForm.confirmacion.urlConfirmacion}">
			       					${activacionForm.confirmacion.urlConfirmacion}
			       				</a>
							</td>
						</tr>
					</table>
                  <p align="center"><img src="http://empleo.gob.mx/images/logo-empleo-email.jpg" alt="portal del empleo" width="248" height="40" /></p>
                  <p align="left" class="arialFont black2Font Font80" >&nbsp;&nbsp;&nbsp;Si no puede acceder a la liga, copie  y péguela en su navegador.</p>
                  <p>
						&nbsp;&nbsp;&nbsp;Usuario:
						${activacionForm.confirmacion.correoElectronico}
						<br />
						&nbsp;&nbsp;&nbsp;Contraseña:
						${activacionForm.confirmacion.contrasena}
				  </p>
	                  <p align="center" class="arialFont black2Font Font80" >
	                  		Considere este comunicado como la notificación oficial a su acceso al Portal del Empleo.
	                  </p>
	                  
	                   <p align="center" class="arialFont black2Font Font80" >
	                  		Reciba un cordial saludo.
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