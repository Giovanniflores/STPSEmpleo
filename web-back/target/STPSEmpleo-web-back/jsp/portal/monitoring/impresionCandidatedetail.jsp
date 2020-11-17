<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.stps.portal.core.candidate.vo.*" %>
<%@ page import="mx.gob.stps.portal.web.security.vo.UsuarioWebVO"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.List"%> 
<%@ page import="java.util.Date"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
String context = request.getContextPath() +"/";
UsuarioWebVO usuario = (UsuarioWebVO)session.getAttribute("USUARIO_APP");
InformacionGeneralVO detalle = (InformacionGeneralVO) request.getAttribute("detalle");
long idCandidato = ((Long) request.getAttribute("idCandidato")).longValue();
List<String> certificaciones = null;
List<HistoriaLaboralVO> lista = null;
List<GradoAcademicoVO> listaGrado = null;
if(detalle.getDatosCurriculum().getCertificaciones() != null && 
		detalle.getDatosCurriculum().getCertificaciones().size()>0){
	certificaciones = detalle.getDatosCurriculum().getCertificaciones();
}
if(detalle.getDatosCurriculum().getHistoriaLaboral() != null &&
		detalle.getDatosCurriculum().getHistoriaLaboral().size() > 0){
		lista = detalle.getDatosCurriculum().getHistoriaLaboral();
}
if(detalle.getDatosCurriculum().getGradoAcademico() != null &&
		detalle.getDatosCurriculum().getGradoAcademico().size()>0){
	listaGrado = detalle.getDatosCurriculum().getGradoAcademico();
}
%>
<html>
<head></head>
<body class="claro">
<style type="text/css">
	@import "css/sneWeb.css";
	@import "css/sneWeb.css";
	@import "dojotoolkit/dojo/resources/dojo.css";
		@import "dojotoolkit/dijit/themes/claro/claro.css";
		@import "css/estilos_formularios.css";
		@import "css/estilos_plantilla.css";
		.claro .dijitDialogTitleBar {
			background-color: #4F6710;
			color: #FFFFFF;
		    font-weight: bold;
		    text-decoration: none;
	    }
	
	.formApp.miEspacio > h2 {display:none;}
</style>
<div class="wrapper">
<table width="100%" class="tabla_cv" id="tablaCV1">
<tr>
	<td rowspan="2" style="vertical-align:middle !important;">
		<div class="foto_cv">
			<img src="<%=context%>imageAction.do?method=getFotoCandidatoRequest&<%="captcha?time="+ 
				Calendar.getInstance().getTimeInMillis()%>&ID_CANDIDATO=<%=idCandidato%>"
				alt="Foto Candidato" />
			</div>
	</td>
	<td style="vertical-align:middle !important;">
		<div class="titulo_cv">
				${detalle.datosCurriculum.tituloCV}
		</div>
		<div class="nombre_cv">
			${detalle.nombre}&nbsp;${detalle.apellido1}&nbsp;${detalle.apellido2}
		</div>
	</td>
	<td align="right" style="vertical-align: middle !important;">
		<img src="images/iconos/logo.png"/>
	</td>
</tr>
<tr>
	<td style="vertical-align: middle !important;">
		<%  
			if(detalle.getDatosCurriculum().getRedesSociales()!=null){
				if(detalle.getDatosCurriculum().getRedesSociales().getFacebook() != null 
					&& detalle.getDatosCurriculum().getRedesSociales().getFacebook().length()>0){
				%>
				<img src="images/iconos/icono_fb.png"/>&nbsp;&nbsp;&nbsp;<%=detalle.getDatosCurriculum().getRedesSociales().getFacebook() %><br>
				<%
				}
				if(detalle.getDatosCurriculum().getRedesSociales().getTwitter() != null 
						&& detalle.getDatosCurriculum().getRedesSociales().getTwitter().length()>0){
				%>
				<img src="images/iconos/icono_tw.png"/>&nbsp;<%=detalle.getDatosCurriculum().getRedesSociales().getTwitter() %><br>
				<%
				}
				if(detalle.getDatosCurriculum().getRedesSociales().getLinkedin() != null 
						&& detalle.getDatosCurriculum().getRedesSociales().getLinkedin().length()>0){
				%>
				<img src="images/iconos/icono_lin.png"/>&nbsp;<%=detalle.getDatosCurriculum().getRedesSociales().getLinkedin() %><br>
				<%
				}
			}		
			%>
	</td>
	<td style="vertical-align: top !important;">
		<div class="textoColor_cv">
			<c:if test="${detalle.confidencialidadCandidato == 1}">
				${detalle.nombreColonia},&nbsp;${detalle.nombreMunicipio},&nbsp;${detalle.codigoPostal}&nbsp;<img src="images/iconos/icono_localiza.png"/>				
				<br>
			</c:if>
			<c:if test="${detalle.contactoCorreo == 2}">
				${detalle.correoElectronico}&nbsp;<img src="images/iconos/icono_correo.png"/>
				<br>
			</c:if>
			<c:if test="${detalle.contactoTelefono == 2}">
				<c:forEach var="telefono" items="${detalle.telefonos}" varStatus="rowMeter">
					<samp>(${telefono.acceso}${telefono.clave}) ${telefono.telefono}
						<c:if test="${not empty telefono.extension}"> ext. ${telefono.extension}</c:if>
					</samp>						
				</c:forEach>&nbsp;<img src="images/iconos/icono_tel.png"/>
			</c:if>
		</div>
	</td>
</tr>
</table>
<br>

<table width="100%" id="tableCV2">
	<tr>
		<td width="50%" style="vertical-align: top!important;">
			<table  width="100%">
			<%
			
			if(lista != null && lista.size()>0){
			%>
				<tr>
					<td colspan="3"><br><div class="lineaVertical"><br>
						<div class="subtituloGris">
						<img src="images/iconos/icono_exp.png" id="imgExp"/>&nbsp;Experiencia Laboral
						</div>
						<hr class="hr_cv">	</div>			
					</td>
				</tr>
				<%
					int i = 0;
					for(HistoriaLaboralVO historia:lista){
						SimpleDateFormat parseador = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat formateador = new SimpleDateFormat("MMM yyyy");
						try {
							Date date = parseador.parse(historia.getFechaIni());
							historia.setFechaIni(formateador.format(date).toUpperCase());
							date = parseador.parse(historia.getFechaFin());							
							historia.setFechaFin(formateador.format(date).toUpperCase());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						if (historia.getFechaIni()!=null || historia.getFechaFin()!=null){
						%>
							<tr>
								<td style="vertical-align:baseline;"><div class="texto14Top_cv"><%=historia.getFechaIni()%><br>-<br><%=historia.getFechaFin()%></div>				
								</td>
								<td style="vertical-align:baseline;background-image: url('images/iconos/lpunteada.png');background-repeat: repeat;">	
						<%
						}
						if(i==0){
						%>			
							<img src="images/iconos/cuadro.png" valign="top" vspace="0"/>
						<%	
						} else {
						%>				
							<img src="images/iconos/cuadro1.png" valign="top" vspace="10"/>
						<%
						}
						%>
							</td>
							<td><div class="lineaVertical">
							<%if (historia.getPuesto() != null && historia.getPuesto().trim().length()>0) {%>
								<div class="subTitulo"><%=historia.getPuesto()%></div>
							<%} if (historia.getEmpresa() != null && historia.getEmpresa().trim().length()>0) {%>
								<div class="textoBold_cv">Empresa:</div>
								<div class="texto_cv"><%=historia.getEmpresa()%></div>
							<%} if (historia.getSector() != null && historia.getSector().trim().length()>0) {%>
								<div class="textoBold_cv">Sector:</div>
								<div class="texto_cv"><%=historia.getSector()%></div>
							<%} if (historia.getFuncion() != null && historia.getFuncion().trim().length()>0) {%>
								<div class="textoBold_cv">Funciones:</div>
								<div class="texto_cv"><%=historia.getFuncion()%></div>
							<%} if (historia.getLogro() != null && historia.getLogro().trim().length()>0) {%>
								<div class="textoBold_cv">Logro principal:</div>
								<div class="texto_cv"><%=historia.getLogro()%></div>
							<%} if (historia.getJerarquia() != null && historia.getJerarquia().trim().length()>0) {%>
								<div class="textoBold_cv">Nivel de reporte:</div>
								<div class="texto_cv"><%=historia.getJerarquia()%></div>
							<%} %>
								</div>
							</td>
						</tr>
						<%
					i++;
					}	
				} 
			
				if(listaGrado != null && listaGrado.size()>0){
				%>
					<tr>
						<td colspan="3"><br><div class="lineaVertical">
							<div class="subtituloGris">
							<img src="images/iconos/icono_for.png" class="imgFormacion" />&nbsp;			
								Formaci&oacute;n Acad&eacute;mica
							</div>
							<hr class="hr_cv">	</div>			
						</td>
					</tr>
					<%
						for(GradoAcademicoVO grado:listaGrado){
					%>
					<tr>
						<td colspan="2"><div class="lineaVertical">
						<%
						if(grado.getInicio() > 0 || grado.getFin() > 0){
						%>		<textarea rows="3" cols="8" id="cuadroTD"><%=grado.getInicio()%>-<%=grado.getFin()%></textarea>
						<%} %>
							</div>
						</td>
						<td><div class="lineaVertical">
							<div class="textoColorLeft_cv">
								<%=(grado.getGradoAcademico()==null || 
									grado.getGradoAcademico().toLowerCase().equals("null")? "": grado.getGradoAcademico())%>
							</div><br>
							<div class="texto14_cv">
								<%=(grado.getCarrera()==null || 
									grado.getCarrera().toLowerCase().equals("null")? "": grado.getCarrera())%><br>
									
								<%=(grado.getSituacionAcademicaString()==null || 
									grado.getSituacionAcademicaString().toLowerCase().equals("null")? "": grado.getSituacionAcademicaString())%><br>
									
								<%=(grado.getEscuela()==null || 
									grado.getEscuela().toLowerCase().equals("null")? "": grado.getEscuela())%>
								
							</div></div>
						</td>
					</tr>
			<%	}
			} else {%>			
							
			<%	
			}
			if(lista == null || lista.size()==0 || 
					listaGrado==null || listaGrado.size()==0){
				if(certificaciones != null && certificaciones.size()>0) {%>
				<tr>
					<td><br>
						<div class="subtituloGris">
						<img src="images/iconos/icono_certif.png" width="20px" height="32px"/>&nbsp;			
							Certificaciones
						</div><hr class="hr_cv">
						<div class="texto_cv">
							<%
							for (String certificado:certificaciones){%>
								&ordm;&nbsp;<%=certificado%><br>
							<%
							}
							%>				
						</div>
					</td>
				</tr>
			<%
				}
			}
			if(lista == null || lista.size()==0){
			%>
			<tr>
				<td colspan="3" align="center" valign="middle"><div class="bordePunteado"><br>
					<div class="subtituloGrisCentro">
					<img src="images/iconos/icono_exp.png"/><br>			
						El candidato no cuenta con<br>Experiencia Laboral<br>&nbsp;
					</div></div>			
				</td>
			</tr>
			<%				
			}
			if(listaGrado == null && listaGrado.size()==0){
			%>
			<tr>
				<td colspan="3" align="center" valign="middle"><div class="bordePunteado"><br>
					<div class="subtituloGrisCentro">
					<img src="images/iconos/icono_for.png" class="imgFormacion" /><br>			
						El candidato no cuenta con<br>Formaci&oacute;n Acad&eacute;mica<br>&nbsp;
					</div></div>			
				</td>
			</tr>
			<%
			}
			%>
			</table>
		</td>
		<td style="vertical-align: top!important;">			
			<table  width="100%">
			<tr>
				<td id="sueldoTD">
					SUELDO PRETENDIDO: ${detalle.salarioPretendido} (NETO MENSUAL)
				</td>
			</tr>
			<%
			if(detalle.getDatosCurriculum().getObjetivoProfesional()!= null
					&& detalle.getDatosCurriculum().getObjetivoProfesional().length()>0){
			%>
				<tr><td><br>
					<div class="subtituloGris"><b>objetivo profesional</div><br>
					<div  class="texto10_cv">${detalle.datosCurriculum.objetivoProfesional}</div>
				</td></tr>
			<%
			}
			if (detalle.getDatosCurriculum().getResumenProfesional() != null 
					&& detalle.getDatosCurriculum().getResumenProfesional().length()>0){
			%>
				<tr>
					<td align="left"><br>
						<div class="subtituloGris">Resumen Profesional</div><br>
						<div  class="texto10_cv">${detalle.datosCurriculum.resumenProfesional}</div>
					</td>
				</tr>
			<%
			}
			
			if(detalle.getIdiomas()!=null && detalle.getIdiomas().size()>0
			&& detalle.getIdiomas().get(0) != null 
			&& detalle.getIdiomas().get(0).getIdioma().length()>0){
				
				boolean bIdioma = detalle.getIdiomas().get(0).getIdioma()!=null && detalle.getIdiomas().get(0).getIdioma().length()>0;
				boolean bHabla = detalle.getIdiomas().get(0).getIdDominioHabla() > 0;
				boolean bEscribir = detalle.getIdiomas().get(0).getIdDominioEscrito() > 0;
				boolean bLeer = detalle.getIdiomas().get(0).getIdDominioLectura()>0;
				boolean bComprender = detalle.getIdiomas().get(0).getIdDominioComprension()>0;
				boolean bDatos = bHabla && bEscribir && bLeer && bComprender;
				
				if(bIdioma && bDatos){
				%>
				<tr>
					<td><br>
						<div class="subtituloGris">
						<img src="images/iconos/icono_idiomas.png" class="imgIconito" />&nbsp;			
							Dominio de idiomas
						</div>
						<hr class="hr_cv">	
					</td>
				</tr>
				<%
				for(IdiomaVO idioma:detalle.getIdiomas()){
					bIdioma = idioma.getIdioma()!=null && idioma.getIdioma().length()>0;
					bHabla = idioma.getIdDominioHabla() > 0;
					bEscribir = idioma.getIdDominioEscrito() > 0;
					bLeer = idioma.getIdDominioLectura()>0;
					bComprender = idioma.getIdDominioComprension()>0;
					int suma = idioma.getIdDominioHabla()+idioma.getIdDominioEscrito()+
							idioma.getIdDominioLectura()+
							idioma.getIdDominioComprension();
					
					if(bIdioma && suma>0){
				%>
						<tr>
							<td>
								<table>
									<tr>
										<td><br><div class="alineaTxtIdioma"><%=idioma.getIdioma()%></div></td>
										<%if(bHabla){ %>
											<td class="alineaIdioma">
												<img src="images/iconos/barrita<%=idioma.getIdDominioHabla()%>.png"/>
												<hr>hablar
											</td>
										<%} else { %>
											<td></td>
										<%}
										if(bEscribir){ %>
											<td class="alineaIdioma">
												<img src="images/iconos/barrita<%=idioma.getIdDominioEscrito()%>.png"/>
												<hr>escribir
											</td>
										<%} else { %>
											<td></td>
										<%}
										if(bLeer){ %>	
											<td class="alineaIdioma">
												<img src="images/iconos/barrita<%=idioma.getIdDominioLectura()%>.png"/>
												<hr>leer
											</td>
										<%} else { %>
											<td></td>
										<%}									
										if(bComprender){ %>
											<td class="alineaIdioma">
												<img src="images/iconos/barrita<%=idioma.getIdDominioComprension()%>.png"/>
												<hr>comprender
											</td>
										<%} %>
									</tr>
									<%if(idioma.getCertificacion()!=null && idioma.getCertificacion().length()>0){ %>
									<tr>
									<td>
									<div  class="texto10_cv"><%=idioma.getCertificacion()%></div> 
									</td>
									</tr>
									<%} %>
								</table>
							</td>
						</tr>
				
				<%		}
					}
				}
			}
			if(certificaciones != null && certificaciones.size()>0 &&
					lista != null && lista.size()>0) {%>
			<tr>
				<td><br>
					<div class="subtituloGris">
					<img src="images/iconos/icono_certif.png" width="20px" height="32px"/>&nbsp;			
						Certificaciones
					</div><hr class="hr_cv">
					<div class="texto_cv">
						<%
						for (String certificado:certificaciones){%>
							&ordm;&nbsp;<%=certificado%><br>
						<%
						}
						%>				
					</div>
				</td>
			</tr>
			<%
			}
			%>
			<tr>
				<td><br>
					<div class="subtituloGris">
						<img src="images/iconos/icono_cono.png" width="20px" height="29px"/>&nbsp;			
							Conocimientos
					</div><hr class="hr_cv">
				</td>
			</tr>
			<%
			if (detalle.getConocimientos() != null && detalle.getConocimientos().size() > 0){
			%>
				<tr>
					<td><br>
						<div class="subtituloGris">
								&nbsp;Habilidades técnicas<br>	
						</div>
					</td>
				</tr>
				
				<tr id="trLimpio1">
					<td><table id="subTabla1">
						
						<%
						int habIndex = 1;
						int taman = detalle.getConocimientos().size();
						for (ConocimientoHabilidadVO vo:detalle.getConocimientos()){
							StringBuilder conocimiento = new StringBuilder();
							if(vo.getConocimientoHabilidad() != null && !vo.getConocimientoHabilidad().equals("null")
									&& vo.getConocimientoHabilidad().length()>0){
								conocimiento.append(vo.getConocimientoHabilidad());
							}
							if(vo.getExperiencia() != null && !vo.getExperiencia().equals("null")
									&& vo.getExperiencia().length()>0){
								conocimiento.append(", ").append(vo.getExperiencia());
							}
							if(vo.getDominio() != null && !vo.getDominio().equals("null")
									&& vo.getDominio().length()>0){
								conocimiento.append(", ").append(vo.getDominio());
							}
							String texto=conocimiento.toString();
							String alt = conocimiento.toString();
							if(conocimiento.toString().length() >20){
								texto = texto.substring(0,20)+"...";
							}
							if (taman == 1){						
						%>
								<tr>
									<td id="monito" rowspan="2">
										<img src="images/iconos/monito.png" style="max-width: 77px; max-height: 137px"/>
									</td>
									<td id="tdArriba" style="vertical-align: bottom !important;">
										<img src="images/iconos/arribaIzq.png"/>
									</td>
									<td id="tdLimpio1"></td>
								</tr>
								<tr>
									<td id="tdMedio2" style="vertical-align: top !important; align: left !important;">
										<img src="images/iconos/abajoDer.png"/>
									</td>
									<td class="texto_cv"><%=texto%></td>
								</tr>
						<%
							} else if (habIndex == 1){
								int rows = taman + 1;
								if (taman==2){
									rows = 4;
								}
						%>	
								<tr>
									<td id="monito" rowspan="<%=rows%>">
										<img src="images/iconos/monito.png" style="max-width: 77px; max-height: 137px"/>
									</td>
									<td id="tdArriba" style="vertical-align: bottom !important;">
										<img src="images/iconos/arribaIzq.png"/>
									</td>
									<td id="tdLimpio1"></td>
								</tr>
								<tr>
									<td id="tdMedio2">
										<img src="images/iconos/medio.png"/>
									</td>
									<td class="texto_cv"><%=texto%></td>
								</tr>
						<%
							} else if (taman == habIndex && taman>1){
						%>							
								<tr>
									<td id="tdMedio1">
										<img src="images/iconos/abajoDer.png" />
									</td>
									<td class="texto_cv"><%=texto%></td>
								</tr>
						<%
								if(taman==2){
						%>
								<tr>
									<td><br>
									</td>
									<td><br>
									</td>
								</tr>
						<%		}
							} else if (taman>1 && taman>habIndex){
						%>
								<tr>									
									<td id="ImgMedio1">
										<img src="images/iconos/medio.png" />
									</td>
									<td class="texto_cv"><%=texto%></td>
								</tr>
						<%
							}
							habIndex++;
						}
						%>
					</table></td>
				</tr>
			<%
			}
			
			if(detalle.getConocimientos() != null && detalle.getConocimientos().size()>0){
				
			%>
				<tr>
					<td><br>
						<div class="subtituloGris">
							&nbsp;Conocimientos complementarios
						</div><br>
						<%
							for(ConocimientoHabilidadVO vo:detalle.getConocimientos()){
								StringBuilder conocimiento = new StringBuilder();
								conocimiento.append(vo.getConocimientoHabilidad());
								if(vo.getExperiencia() != null && !vo.getExperiencia().equals("null")
										&& vo.getExperiencia().length()>0){
									conocimiento.append(", ").append(vo.getExperiencia());
								}
								if(vo.getDominio() != null && !vo.getDominio().equals("null")
										&& vo.getDominio().length()>0){
									conocimiento.append(", ").append(vo.getDominio());
								}							
						%>						
							<div class="texto_cv">&bull; <%=conocimiento.toString()%></div>
						<%
							}
						%>
					</td>
				</tr>
			
			<%
			}
			if(detalle.getDatosCurriculum().getComputacion()!=null && 
				detalle.getDatosCurriculum().getComputacion().length()>0){%>			
				<tr>
					<td><br>
						<div class="subtituloGris">
							&nbsp;Conocimientos de computaci&oacute;n
						</div><br>	
							<div class="texto_cv"><%=detalle.getDatosCurriculum().getComputacion()%></div>
					</td>
				</tr>
			<%} %>
			<%if(detalle.getDatosCurriculum().getSistemas()!=null && 
				detalle.getDatosCurriculum().getSistemas().length()>0){%>			
				<tr>
					<td><br>
						<div class="subtituloGris">
							&nbsp;Conocimientos de sistemas especializados
						</div><br>	
							<div class="texto_cv"><%=detalle.getDatosCurriculum().getSistemas()%></div>
					</td>
				</tr>
			<%
			} 
			%>
			</table>	
		</td>
	</tr>	
</table>
<br><br><br><br><br><br><br>
</div>
</body>
</html>


