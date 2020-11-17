<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
function toggleIdioma(){	
		if(dijit.byId('idIdioma').get('value')==''){
			message("Ingrese primero un idioma principal");
		}else if(dijit.byId('idDominioIdioma').get('value')==''){
			message("Ingrese el dominio del idioma principal");
		}else{
			loadLanguageAdd();
			document.getElementById('idiomaAdicional').style.display = 'block';
			document.getElementById('bAgregarIdioma').style.display = 'none';
		}	
}
</script>

<c:forEach var="idioma" items="${ofertaEmpleoForm.idiomasAdicionales}" varStatus="status">
	<div id="idiomaExtra">
		<input type="hidden" id="idOfertaIdiomaAdicional_${status.count}"  name="idOfertaIdiomaAdicional_${status.count}"  value="${idioma.idRegistro}"/>
		<input type="hidden" id="idIdiomaAdicional_${status.count}" 	   name="idIdiomaAdicional_${status.count}"        value="${idioma.idIdioma}"/>
		<input type="hidden" id="idCertificacionIdiomaAdd_${status.count}" name="idCertificacionIdiomaAdd_${status.count}" value="${idioma.idCertificacion}"/>
		<input type="hidden" id="idDominioIdiomaAdd_${status.count}"       name="idDominioIdiomaAdd_${status.count}"       value="${idioma.idDominio}"/>
		<div class="clearfix"></div>
		<div class="margin_top_30">
			<div class="grid1_3 fl">
				<label for="selIdioma_${status.count}">Idioma Adicional al nativo</label>
				<select 
				        required="true" missingMessage="Es necesario indicar el idioma adicional"
				        id="selIdioma_${status.count}" 
				        store="idiomaStoreAdd" dojotype="dijit.form.FilteringSelect"
				        value="${idioma.idIdioma}"
				        onchange="javascript:actulizaCertificacionesAdd('${status.count}',this.value);actualizaValorAd('idIdiomaAdicional_${status.count}',this.value);">
						<c:forEach var="regCatalogo" items="${ofertaEmpleoForm.catalogoIdiomas}">
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
				<label for="selDom_${status.count}">Dominio del idioma</label>
				<c:if test="${idioma.idIdioma==1}">
					<select 
					        required="false"
					        store="dominioStore" dojotype="dijit.form.FilteringSelect"
					        missingMessage="Es necesario indicar el dominio requerido del idioma"
					        value=""
					        id="selDom_${status.count}"
					        disabled="true"
					        onchange="javascript:actualizaValorAd('idDominioIdiomaAdd_${status.count}',this.value);">
							<c:forEach var="regCatalogoDom" items="${ofertaEmpleoForm.catalogoDominios}">
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
				<c:if test="${idioma.idCertificacion!=2&&idioma.idIdioma!=1}">
					<select 
					        required="false"
					        store="dominioStore" dojotype="dijit.form.FilteringSelect"
					        missingMessage="Es necesario indicar el dominio requerido del idioma"
					        value="${idioma.idDominio}"
					        id="selDom_${status.count}"							        
					        disabled="true"
					        onchange="javascript:actualizaValorAd('idDominioIdiomaAdd_${status.count}',this.value);">
							<c:forEach var="regCatalogoDom" items="${ofertaEmpleoForm.catalogoDominios}">
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
				<c:if test="${idioma.idCertificacion==2&&idioma.idIdioma!=1}">
					<select 
					        required="false"
					        store="dominioStore" dojotype="dijit.form.FilteringSelect"
					        missingMessage="Es necesario indicar el dominio requerido del idioma"
					        value="${idioma.idDominio}"			
					        id="selDom_${status.count}"	       
					        onchange="javascript:actualizaValorAd('idDominioIdiomaAdd_${status.count}',this.value);">
							<c:forEach var="regCatalogoDom" items="${ofertaEmpleoForm.catalogoDominios}">
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
				<label for="selCertif_${status.count}">¿Cuentas con certificación?</label>
				<c:if test="${idioma.idIdioma==1}">
					<select 
					        required="false"
					         dojotype="dijit.form.FilteringSelect"
					        missingMessage="Es necesario indicar la certificacion requerida"
					        id="selCertif_${status.count}"
					        readOnly="true"
					        onchange="javascript:actualizaValorAd('idCertificacionIdiomaAdd_${status.count}',this.value);actualizaDominioAdd('${status.count}',this.value);">
								<option value="2" selected="selected">Ninguna</option>
					</select>					
				</c:if>
				<c:if test="${idioma.idIdioma!=1}">
					<select 
					        required="false"
					         dojotype="dijit.form.FilteringSelect"
					        missingMessage="Es necesario indicar la certificacion requerida"
					        id="selCertif_${status.count}"							        
					        value="${idioma.idCertificacion}"
					        readOnly="true"
					        onchange="javascript:actualizaValorAd('idCertificacionIdiomaAdd_${status.count}',this.value);actualizaDominioAdd('${status.count}',this.value);">
							<c:if test="${status.count==1}">	
								<c:forEach var="regCatalogoCert" items="${ofertaEmpleoForm.idiomasMultiRegistro1}">
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
								<c:forEach var="regCatalogoCert" items="${ofertaEmpleoForm.idiomasMultiRegistro2}">
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
								<c:forEach var="regCatalogoCert" items="${ofertaEmpleoForm.idiomasMultiRegistro3}">
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
			</div>
			<div class="clearfix"></div>	
		</div>	
		<div class="margin_top_10 ta_right" style="width: 918px">
			<input type="button" class="eliminar" onclick="javascript:eliminarIdioma('${idioma.idIdioma}','${idioma.idCertificacion}');" value="Eliminar" title="Eliminar idioma"/>	
		</div>
		<div class="clearfix"></div>
	</div>
</c:forEach>
		   
<c:if test="${ofertaEmpleoForm.totalIdiomasAdicionales<2}">
	<div class="clearfix"></div>
 	<div id="bAgregarIdioma" class="margin_top_10 ta_right" style="width: 918px">
 		<input type="button" class="agregar" onclick="toggleIdioma();" value="Agregar" title="Agregar idioma"/>
 	</div>
</c:if>
 
		

