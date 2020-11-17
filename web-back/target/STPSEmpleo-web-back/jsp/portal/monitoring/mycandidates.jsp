<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils, mx.gob.stps.portal.core.candidate.vo.PerfilVO, java.util.List, java.util.Iterator, java.util.Date,  mx.gob.stps.portal.web.infra.utils.DateConverter, mx.gob.stps.portal.core.infra.utils.Constantes.*"%>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<!--EMPIEZA EXPAND COLLAPSE-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>

<script type="text/javascript">
<!--//--><![CDATA[//><!--
$(function() {
    $("a.expand").toggler();   
    $("#mis_ofertas").expandAll({trigger: ".expand", ref: ".candidato", localLinks: "p.top a"});
});
//--><!]]>
</script>
<!--TERMINA EXPAND COLLAPSE-->
<script>
    function callable(form, field) {
		selectCheck(form, form.allCheck.checked, field);
    }
	function selectCheck(form, obj, field) {
		var i=form.elements.length;
		for(var k=0;k<i;k++) {
			if(form.elements[k].name==field) {
				form.elements[k].checked=obj;
			}
		}
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div class="TabbedPanelsContent" id="mis_ofertas">
	<div class="candidato">
		<h2>Candidatos</h2>
        <div class="oferta_creada">
			<h3>Ingeniero en Sistemas</h3>
            <a href="#">Usar como plantilla</a>
            <a href="#">Búsqueda filtrada</a>
            <a href="#" class="resaltado">Editar</a><br />
            <div class="criterios">
				<a href="#">Ingeniero en Sistemas</a> | 
                <a href="#">Telecomunicaciones</a> | 
                <a href="#">Inglés avanzado</a> | 
                <a href="#">Tlalpan-Distrito Federal</a>
            </div>
            <a href="#" class="lupa">Buscar más</a>
				<img src="images/bottom_oferta.gif">
        </div>
		<div class="publicados">
			<h3>Mis Candidatos</h3>
			<table cellspacing="0" cellpadding="0" border="0">
				<tr class="temas">
					<td><input name="" type="checkbox" value="" />Candidato</td>
					<td>Carrera/Oficio</td>
					<td>Área laboral</td>
					<td>Edad</td>
					<td>Sueldo requerido</td>
					<td class="fin">Municipio/Entidad</td>
				</tr>
				<tr>
					<td class="checar"><input name="" type="checkbox" value="" />
						<div><strong>Ingeniero en sistemas</strong><br /></div>
					</td>
					<td>Ingeniero en Sistemas</td>
					<td>Telecom.</td>
					<td>30 años</td>
					<td>$15,000 a $20,000</td>
					<td>Tlalpan, D.F.</td>
				</tr>
				<tr class="detalles"><td colspan="5"><a title="Expand/Collapse" href="#" style="display: block;" class="expand">Ver detalles</a>
                      <div class="collapse"><p>Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra Este es un texto para muestra </p></div></td>
                </tr>
				<tr class="odd">
					<td class="checar"><input name="" type="checkbox" value="" />
						<div><strong>Ingeniero en sistemas</strong><br />
					<a href="#">Ver detalles</a>        </div></td>
					<td>Ingeniero en Sistemas</td>
					<td>Telecom.</td>
					<td>30 años</td>
					<td>$15,000 a $20,000</td>
					<td>Tlalpan, D.F.</td>
				</tr>
				<tr>
					<td class="checar"><input name="" type="checkbox" value="" />
						<div><strong>Ingeniero en sistemas</strong><br />
					<a href="#">Ver detalles</a></div></td>
					<td>Ingeniero en Sistemas</td>
					<td>Telecom.</td>
					<td>30 años</td>
					<td>$15,000 a $20,000</td>
					<td>Tlalpan, D.F.</td>
				</tr>
				<tr class="odd">
					<td class="checar"><input name="" type="checkbox" value="" />
						<div><strong>Ingeniero en sistemas</strong><br />
					<a href="#">Ver detalles</a>        </div></td>
					<td>Ingeniero en Sistemas</td>
					<td>Telecom.</td>
					<td>30 años</td>
					<td>$15,000 a $20,000</td>
					<td>Tlalpan, D.F.</td>
				</tr>
				<tr>
					<td class="checar"><input name="" type="checkbox" value="" />
						<div><strong>Ingeniero en sistemas</strong><br />
					<a href="#">Ver detalles</a></div></td>
					<td>Ingeniero en Sistemas</td>
					<td>Telecom.</td>
					<td>30 años</td>
					<td>$15,000 a $20,000</td>
					<td>Tlalpan, D.F.</td>
				</tr>
				<tr class="odd">
					<td class="checar"><input name="" type="checkbox" value="" />
						<div><strong>Ingeniero en sistemas</strong><br />
					<a href="#">Ver detalles</a>        </div></td>
					<td>Ingeniero en Sistemas</td>
					<td>Telecom.</td>
					<td>30 años</td>
					<td>$15,000 a $20,000</td>
					<td>Tlalpan, D.F.</td>
				</tr>
			</table>
			<input type="button" class="boton" value="Guardar seleccionados para futuras ofertas" />
			<input type="button" class="boton" value="Eliminar seleccionados" />
			<p class="paginador"><a href="#"><<</a> <a href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">>></a> de 25  <a href="#">Anterior</a> | <a href="#">Siguiente</a></p>
		</div>
	</div>
	<div class="candidato">                    
		<h3>Candidatos postulados</h3>
		<table cellspacing="0" cellpadding="0" border="0">
			<tr class="temas">
    			<td><input name="" type="checkbox" value="" />Candidato</td>
				<td>Carrera/Oficio</td>
				<td>Área laboral</td>
				<td>Edad</td>
				<td>Sueldo requerido</td>
				<td class="fin">Municipio/Entidad</td>
			</tr>
			<tr>
    			<td class="checar"><input name="" type="checkbox" value="" />
					<div><strong>Ingeniero en sistemas</strong><br />
				<a href="#">Ver detalles</a></div></td>
				<td>Ingeniero en Sistemas</td>
				<td>Telecom.</td>
				<td>30 años</td>
				<td>$15,000 a $20,000</td>
				<td>Tlalpan, D.F.</td>
			</tr>
			<tr class="odd">
    			<td class="checar"><input name="" type="checkbox" value="" />
					<div><strong>Ingeniero en sistemas</strong><br />
				<a href="#">Ver detalles</a>        </div></td>
				<td>Ingeniero en Sistemas</td>
				<td>Telecom.</td>
				<td>30 años</td>
				<td>$15,000 a $20,000</td>
				<td>Tlalpan, D.F.</td>
			</tr>
			<tr>
    			<td class="checar"><input name="" type="checkbox" value="" />
					<div><strong>Ingeniero en sistemas</strong><br />
				<a href="#">Ver detalles</a></div></td>
				<td>Ingeniero en Sistemas</td>
				<td>Telecom.</td>
				<td>30 años</td>
				<td>$15,000 a $20,000</td>
				<td>Tlalpan, D.F.</td>
			</tr>
			<tr class="odd">
    			<td class="checar"><input name="" type="checkbox" value="" />
					<div><strong>Ingeniero en sistemas</strong><br />
				<a href="#">Ver detalles</a>        </div></td>
				<td>Ingeniero en Sistemas</td>
				<td>Telecom.</td>
				<td>30 años</td>
				<td>$15,000 a $20,000</td>
				<td>Tlalpan, D.F.</td>
			</tr>
			<tr>
    			<td class="checar"><input name="" type="checkbox" value="" />
					<div><strong>Ingeniero en sistemas</strong><br />
				<a href="#">Ver detalles</a></div></td>
				<td>Ingeniero en Sistemas</td>
				<td>Telecom.</td>
				<td>30 años</td>
				<td>$15,000 a $20,000</td>
				<td>Tlalpan, D.F.</td>
			</tr>
			<tr class="odd">
    			<td class="checar"><input name="" type="checkbox" value="" />
					<div><strong>Ingeniero en sistemas</strong><br />
				<a href="#">Ver detalles</a>        </div></td>
				<td>Ingeniero en Sistemas</td>
				<td>Telecom.</td>
				<td>30 años</td>
				<td>$15,000 a $20,000</td>
				<td>Tlalpan, D.F.</td>
			</tr>
		</table>
		<input type="button" class="boton" value="Guardar seleccionados para futuras ofertas" />
		<input type="button" class="boton" value="Eliminar seleccionados" />
		<p class="paginador"><a href="#"><<</a> <a href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">>></a> de 25  <a href="#">Anterior</a> | <a href="#">Siguiente</a></p>
	</div>
</div>