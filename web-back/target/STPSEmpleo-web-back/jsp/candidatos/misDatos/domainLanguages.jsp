<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<script type="text/javascript">
		dojo.require("dijit.form.FilteringSelect");
	</script>
						<c:forEach var="idioma" items="${ProgramaForm.idiomasAdicionales}" varStatus="status">
							<div id="idiomaExtra">
								<input type="hidden" dojoType="dijit.form.TextBox" id="idCandidatoIdiomaAdicional_${status.count}" name="idCandidatoIdiomaAdicional_${status.count}" value="${idioma.idCandidatoIdioma}"/>
								<input type="hidden" dojoType="dijit.form.TextBox" id="idIdiomaAdicional_${status.count}" name="idIdiomaAdicional_${status.count}" value="${idioma.idIdioma}"/>
								<input type="hidden" dojoType="dijit.form.TextBox" id="idCertificacionIdiomaAdd_${status.count}" name="idCertificacionIdiomaAdd_${status.count}" value="${idioma.idCertificacion}"/>
								<input type="hidden" dojoType="dijit.form.TextBox" id="idDominioIdiomaAdd_${status.count}" name="idDominioIdiomaAdd_${status.count}"  value="${idioma.idDominio}"/>
								<input type="hidden" dojoType="dijit.form.TextBox" id="idDominioHablaAdd${status.count}" name="idDominioHablaAdd${status.count}" value="${idioma.idDominioHabla}">
								<input type="hidden" dojoType="dijit.form.TextBox" id="idDominioEscritoAdd${status.count}" name="idDominioEscritoAdd${status.count}" value="${idioma.idDominioEscrito}">
								<input type="hidden" dojoType="dijit.form.TextBox" id="idDominioLecturaAdd${status.count}" name="idDominioLecturaAdd${status.count}" value="${idioma.idDominioLectura}">
								<div class="clearfix"></div>
								<div class="margin_top_30">
									<div class="grid1_3 fl">
										<c:if test="${idioma.idIdioma==1}">
											<label for="selectLanguage_${status.count}">Idioma adicional al nativo</label>
										</c:if>	
										<c:if test="${idioma.idIdioma>1}">
											<label for="selectLanguage_${status.count}"><span>*</span> Idioma adicional al nativo</label>
										</c:if>	
										<select required="true" missingMessage="Es necesario indicar el idioma adicional" store="idiomaStore"
											dojotype="dijit.form.FilteringSelect" id="selectLanguage_${status.count}" value="${idioma.idIdioma}"
												<c:if test="${idioma.idIdioma>1}">
													onchange="javascript:actualizaDominioAdd0('${status.count}'); actualizaValorAd('idIdiomaAdicional_${status.count}', this.value);"
												</c:if>	
											>
												<c:forEach var="regCatalogo" items="${ProgramaForm.catalogoIdiomas}">
													<c:choose>
														<c:when test="${regCatalogo.idCatalogoOpcion eq idioma.idIdioma}">
															<option value="${regCatalogo.idCatalogoOpcion}" selected >${regCatalogo.opcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${regCatalogo.idCatalogoOpcion}">${regCatalogo.opcion}</option>
														</c:otherwise>				
													</c:choose>
												</c:forEach>				
											</select>
										</div>
										<div class="grid1_3 fl">
											<c:if test="${idioma.idIdioma==1}">
												<label for="dominio_${status.count}">Dominio del idioma</label>
												<select required="false" store="dominioStore" dojotype="dijit.form.FilteringSelect" disabled="true" value=""
													missingMessage="Es necesario indicar el dominio requerido del idioma" id="dominio_${status.count}"
													onchange="javascript:actualizaValorAd('idDominioIdiomaAdd_${status.count}',this.value);actualizaDominioAdd('${status.count}',this.value);">		
													<c:forEach var="regCatalogoDom" items="${ProgramaForm.catalogoDominios}">
														<c:choose>
															<c:when test="${regCatalogoDom.idCatalogoOpcion eq idioma.idDominio}">
																<option value="${regCatalogoDom.idCatalogoOpcion}" selected >${regCatalogoDom.opcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${regCatalogoDom.idCatalogoOpcion}">${regCatalogoDom.opcion}</option>
															</c:otherwise>				
														</c:choose>
													</c:forEach>	
												</select>
											</c:if>
											<c:if test="${idioma.idIdioma>1}">
												<label for="dominio_${status.count}"><span>*</span>Dominio del idioma</label>
												<select 
													required="true"
													store="dominioStore" dojotype="dijit.form.FilteringSelect"
													missingMessage="Es necesario indicar el dominio requerido del idioma"
													id="dominio_${status.count}"
													value="${idioma.idDominio}"
													readonly="false"
													onchange="javascript:actualizaValorAd('idDominioIdiomaAdd_${status.count}',this.value);actualizaDominioAdd('${status.count}',this.value);">
													<!--  este parte es solo para cargar la lista de opciones el valor se pone arriba en value -->
													<c:forEach var="regCatalogoDom" items="${ProgramaForm.catalogoDominios}">
														<c:choose>
															<c:when test="${regCatalogoDom.idCatalogoOpcion eq idioma.idDominio}">
																<option value="${regCatalogoDom.idCatalogoOpcion}" selected >${regCatalogoDom.opcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${regCatalogoDom.idCatalogoOpcion}">${regCatalogoDom.opcion}</option>
															</c:otherwise>				
														</c:choose>
													</c:forEach>						
												</select>
											</c:if>
										</div>			
										<div class="grid1_3 fl">
											<c:if test="${idioma.idDominio > 1}">
													<label for="certification_${status.count}"><span>*</span>¿Cuentas con certificación?</label>
													<select 
														required="true"
														readOnly="false"
														dojotype="dijit.form.FilteringSelect"
														missingMessage="Es necesario indicar la certificacion requerida"
														value="${idioma.idCertificacion}"
														id="certification_${status.count}"					
														onchange="javascript:actualizaValorAd('idCertificacionIdiomaAdd_${status.count}',this.value);actualizarIdiomaAdd(${status.count},this.value)">					
														<c:if test="${status.count==1}">	
															<c:forEach var="regCatalogoCert" items="${ProgramaForm.idiomasMultiRegistro1}">
																<c:choose>
																	<c:when test="${regCatalogoCert.idCatalogoOpcion eq idioma.idCertificacion}">
																		<option value="${regCatalogoCert.idCatalogoOpcion}" selected >${regCatalogoCert.opcion}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${regCatalogoCert.idCatalogoOpcion}">${regCatalogoCert.opcion}</option>
																	</c:otherwise>				
																</c:choose>
															</c:forEach>						       
														</c:if>		
														<c:if test="${status.count==2}">	
															<c:forEach var="regCatalogoCert" items="${ProgramaForm.idiomasMultiRegistro2}">
																<c:choose>
																	<c:when test="${regCatalogoCert.idCatalogoOpcion eq idioma.idCertificacion}">
																		<option value="${regCatalogoCert.idCatalogoOpcion}" selected >${regCatalogoCert.opcion}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${regCatalogoCert.idCatalogoOpcion}">${regCatalogoCert.opcion}</option>
																	</c:otherwise>				
																</c:choose>
															</c:forEach>						       
														</c:if>		
														<c:if test="${status.count==3}">	
															<c:forEach var="regCatalogoCert" items="${ProgramaForm.idiomasMultiRegistro3}">
																<c:choose>
																	<c:when test="${regCatalogoCert.idCatalogoOpcion eq idioma.idCertificacion}">
																		<option value="${regCatalogoCert.idCatalogoOpcion}" selected >${regCatalogoCert.opcion}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${regCatalogoCert.idCatalogoOpcion}">${regCatalogoCert.opcion}</option>
																	</c:otherwise>				
																</c:choose>
															</c:forEach>						       
														</c:if>		
													</select>	
											</c:if>
											<c:if test="${idioma.idDominio <= 1}">
													<label for="certification_${status.count}">¿Cuentas con certificación?</label>
												<select 
													required="false"
													dojotype="dijit.form.FilteringSelect"
													missingMessage="Es necesario indicar la certificacion requerida"		       
													readOnly="true"
													id="certification_${status.count}"
													onchange="javascript:actualizaValorAd('idCertificacionIdiomaAdd_${status.count}',this.value);">
													
													<option value="0" selected="selected"></option>
												</select>
											</c:if>				
										</div>
										<div class="grid1_3 margin_top_10 fl">
											<label for="labelHabloDominio_${status.count}"><span>*</span> Hablo</label>
											<select id="habloDominio_${status.count}" required="true" value="${idioma.idDominioHabla}"
												<c:if test='${idioma.idDominio==3}'>disabled="disabled"</c:if>
												dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de habla del idioma">
												<c:forEach var="row" items="${competencia}">
													<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
												</c:forEach>
											</select>
										</div>
										<div class="grid1_3 margin_top_10 fl">
											<label for="labelEscriboDominio_${status.count}"><span>*</span> Escribo</label>
											<select id="escriboDominio_${status.count}" required="true" value="${idioma.idDominioEscrito}"
												<c:if test='${idioma.idDominio==3}'>disabled="disabled"</c:if>
												dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de escritura del idioma">
												<c:forEach var="row" items="${competencia}">
													<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
												</c:forEach>
											</select>
										</div>
										<div class="grid1_3 margin_top_10 fl">
											<label for="labelLeoDominio_${status.count}"><span>*</span> Leo</label>
											<select id="leoDominio_${status.count}" required="true" value="${idioma.idDominioLectura}"
												<c:if test='${idioma.idDominio==3}'>disabled="disabled"</c:if>
												dojotype="dijit.form.FilteringSelect" missingMessage="Es necesario indicar el nivel de lectura del idioma">
												<c:forEach var="row" items="${competencia}">
													<option value="${row.idCatalogoOpcion}">${row.opcion}</option>
												</c:forEach>
											</select>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="margin_top_10 ta_right" style="width: 918px">
										<input type="button" class="eliminar" onclick="javascript:eliminarIdioma('${idioma.idIdioma}','${idioma.idCertificacion}');" title="Eliminar idioma" value="Eliminar idioma" />
									</div>
									<div class="clearfix"></div>
								</div>
							</c:forEach>
							<c:if test="${ProgramaForm.totalIdiomasAdicionales<2}">
								<div class="clearfix"></div>
								<div id="idiomaBoton" class="margin_top_10 ta_right" style="width: 918px">
							 		<input type="button" id="btnAgregarIdioma" name="btnAgregarIdioma" class="agregar"
							 			   onclick="javascript:toggleIdioma();" title="Agregar idioma" value="Agregar idioma" />
							 	</div>
							</c:if>