<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <h2>Cursos virtuales</h2>
<p>&lt;!--Ocupa este espacio desde aquí--&gt;</p>
        <p>A continuación les presento un formulario y una tabla como referencia para considerar una construcción uniforme en todos los diversos casos en que se ocupen estos elementos en común. Las siguientes hojas de estilo deber&aacute;n estar vinculadas para invocar el estilo necesario: <em>estilos_empleo.css, estilos_canal.css, estilos_tabla.css, estilos_genericos.css, estilos_mi_espacio.css</em></p>
        <p>He generado las siguientes clases <em>.un_quinto</em>, <em>.un_cuarto</em>, <em>.un_tercio</em>, <em>.un_medio</em> y <em>.entero</em> las cuales tienen las propiedades de las proporciones que su nombre indica y que pueden ser utilizadas en <em>parrafos</em>, <em>divs</em> y <em>spans</em>. Así mismo, al tener propiedades proporcionales se puede ocupar por ejemplo: un elemento con una clase <em>.un_medio</em>, dentro de otro elemento con una clase <em>.un_medio</em>, lo cuál daría como resultado <strong>la mitad de la mitad</strong></p>
        <p><strong>Por ejemplo:</strong></p>
        <code>
        <strong>&lt;div class=&quot;un_medio&quot;&gt;</strong><br />
<strong>&lt;p class=&quot;un_tercio&quot;&gt;</strong><br />
&lt;strong&gt;Tipo de teléfono&lt;/strong&gt;&lt;br&gt;<br />
Fijo&amp;nbsp;&lt;input type=&quot;radio&quot; name=&quot;&quot; value=&quot;&quot;&gt;&amp;nbsp;&amp;nbsp;<br />
Celular&amp;nbsp;&lt;input type=&quot;radio&quot; name=&quot;&quot; value=&quot;&quot;&gt;<br />
<strong>&lt;/p&gt;</strong> <br />
<strong>&lt;p class=&quot;un_tercio&quot;&gt;</strong><br />
&lt;strong&gt;Lada*&lt;/strong&gt;&lt;br&gt;<br />
&lt;select name=&quot;&quot;&gt;<br />
&lt;option&gt;015&lt;/option&gt;<br />
&lt;option&gt;015&lt;/option&gt;<br />
&lt;/select&gt;<br />
<strong>&lt;/p&gt;</strong><br />
<strong>&lt;p class=&quot;un_tercio&quot;&gt;</strong><br />
&lt;strong&gt;Clave*&lt;/strong&gt;&lt;br&gt;<br />
&lt;select name=&quot;&quot;&gt;<br />
&lt;option&gt;55&lt;/option&gt;<br />
&lt;option&gt;55&lt;/option&gt;<br />
&lt;/select&gt;<br />
<strong>&lt;/p&gt;</strong><br />
<strong>&lt;/div&gt;</strong>
        </code>
        <p>&nbsp;</p>
        <h4><strong>Que da como resultado:</strong></h4>
        <div class="un_medio">
                        <p class="un_tercio">
                            <strong>Tipo de teléfono</strong><br>
                            <label>Fijo&nbsp;<input type="radio" name="" value=""></label>&nbsp;&nbsp;
                            <label>Celular&nbsp;<input type="radio" name="" value=""></label>
          </p>                            
                            <p class="un_tercio" style="text-align: right;">
                            	<strong><label for="selectLada">Lada</label>*</strong><br>
                                <select name="" id="selectLada">
                                    <option>015</option>
                                    <option>015</option>
                                </select>
                            </p>
                            <p class="un_tercio" style="text-align: center;">
                            	<strong><label for="selectClave">Clave</label>*</strong><br>
                                <select name="" id="selectClave">
                                    <option>55</option>
                                    <option>55</option>
                                </select>
                            </p>
		  </div>

		<h3>Formulario</h3>
       			 <div>
                   <div>
                        <p class="un_tercio"><strong><label for="checkExtranjero">Soy extranjero</label></strong>&nbsp;
                      <input type="checkbox" value="" name="" id="checkExtranjero"></p> 
                      <p class="dos_tercios"><strong><label for="selectSitLegal">situación legal para trabajar</label></strong>&nbsp;
                            <select id="selectSitLegal">
                                <option>Ciudadanía</option>
                                <option>Permiso temporal</option>
                                <option>permiso temporal</option>
                      </select></p>
                            <p class="un_medio">&nbsp;<br><strong><label for="curp">Si conoce su CURP, proporcionela para agilizar el registro</label></strong><br>
                      <input type="text" size="50" id="curp"></p>
                     <p class="un_medio">&nbsp;<br>
                     <a class="links" href="#">Consultar mi CURP (Clave Única de Registro de Población)&nbsp;<img title="Contenido externo" src="images/flecha_cont_externo.gif"></a></p>
                        <p class="un_tercio"><strong>Género*</strong><br>
                      <label>Masculino<input type="radio" value="" name=""></label>&nbsp;&nbsp;&nbsp;<label>Femenino<input type="radio" value="" name=""></label>
                      </p>
                      <p class="un_tercio"><strong><label for="selectMeses">Fecha de nacimiento</label>*</strong><br>
                        <select id="selectMeses">
                            <option>Septiembre</option>
                            <option>Octubre</option>
                            <option>Noviembre</option>
                        </select>&nbsp;&nbsp;
                        <select>
                            <option>22</option>
                            <option>23</option>
                            <option>24</option>
                        </select>&nbsp;&nbsp;
                        <select>
                            <option>1980</option>
                            <option>1981</option>
                            <option>1982</option>
                        </select>
                      </p>
                     <p class="un_tercio"><strong><label for="selectEntidad">Entidad de nacimiento</label></strong><br>
                        <select id="selectEntidad">
                            <option>Tlaxcala</option>
                            <option>Veracruz</option>
                            <option>Yucatán</option>
                        </select>
                     </p>
                    <div id="domicilio">
                    	<h3>Domicilio donde reside actualmente</h3>
                        <p class="un_tercio">
                            <label for="text1"><strong>Codigo postal*:</strong></label>
                            <br>
                            <input type="text" name="" id="" size=""></p>
                        <p class="un_tercio">
                            <label for="text1"><strong>Colonia*:</strong></label>
                            <br>
                            <input type="text" name="" id="" size=""></p>
                        <p class="un_tercio">
                            <label for="text1"><strong>Delegación/Municipio*:</strong></label>
                            <br>
                            <input type="text" name="" id="" size=""></p>
                        <p class="un_tercio">
                            <label for="text1"><strong>Ciudad*:</strong></label>
                            <br>
                            <input type="text" name="" id="" size=""></p>
                        <p class="un_tercio">
                            <label for="text1"><strong>Entidad Federativa*:</strong></label>
                            <br>
                            <input type="text" name="" id="" size=""></p>
                        <p class="entero">
                            <label for="text1"><strong>Entidad Federativa*:</strong></label>
                            <br>
                            <input type="text" name="" id="" size="100"><br>
                            Ej. Calle, número exterior, número interior, piso, departamento</p>
               </div>
               <div id="datos_contacto">
               	 <h3>Datos para contactarlo</h3>
                 <h4 class="entero">Teléfono</h4>
                 	<div class="un_medio">
                        <p class="un_tercio">
                            <strong>Tipo de teléfono</strong><br>
                            <label>Fijo&nbsp;<input type="radio" value="" name=""></label>&nbsp;&nbsp;
                            <label>Celular&nbsp;<input type="radio" value="" name=""></label>
                      </p>                            
                            <p style="text-align: right;" class="un_tercio">
                            	<strong><label for="selectLada2">Lada</label>*</strong><br>
                                <select name="" id="selectLada2">
                                    <option>015</option>
                                    <option>015</option>
                                </select>
                            </p>
                            <p style="text-align: center;" class="un_tercio">
                            	<strong><label for="selectClave2">Clave</label>*</strong><br>
                                <select name="" id="selectClave2">
                                    <option>55</option>
                                    <option>55</option>
                                </select>
                            </p>
       			 </div>
                            <div class="un_medio">
                            	<p class="un_medio">
                                    <label><strong>Telefono*</strong>
                                    <input type="text" size="" id="" name="">
                                    </label>
                                </p>
                            	<p class="un_medio">
                                    <label><strong>Extensión*</strong>
                                    <input type="text" size="" id="" name=""><br>
                                    </label>
                                    <a href="#" class="links">Agregar un teléfono</a>
                                </p>
                            </div>
                            <div class="division">
                                <p class="entero"><label><strong>Correo electrónico</strong><br>
                                <input type="text" size="50" id="" name=""></p>
                                </label>
                                
                                <p class="un_medio"><strong>Medio para contactarlo</strong><br>
                                <label>Teléfono<input type="checkbox" value="" name=""></label>&nbsp;&nbsp; <label>Correo electrónico <input type="checkbox" value="" name=""></label>&nbsp;&nbsp; <label> Domicilio <input type="checkbox" value="" name=""></label></p>
                                
                                <p class="un_medio"><strong><label for="selectHorario">Horario para contactarlo</label></strong><br>
                                <select id="selectHorario">
                                	<option>Por la mañana</option>
                                    <option>Por la tarde</option>
                                	<option>Por la noche</option>
                                    <option>fin de semana</option>
                                </select>
                                
                                </p><p class="entero">
                                <strong><label for="checkConfidencial">Deseo que los siguientes datos permanezcan confidenciales</label></strong><br>
                                CURP <input type="checkbox" value="" name="" id="checkConfidencial">&nbsp;&nbsp; <label for="telefonoCasa">Teléfono Casa</label> <input type="checkbox" value="" name="" id="telefonoCasa">&nbsp;&nbsp;  <label for="inputTelf">Teléfono</label> <input type="checkbox" value="" name="" id="inputTelf">&nbsp;&nbsp; <label for="inputCorreo">Correo electrónico</label> <input type="checkbox" value="" name="" id="inputCorreo">&nbsp;&nbsp; * <label for="inputDomicilio">Domicilio</label> <input type="checkbox" value="" name="" id="inputDomicilio"><br>
                                *Se publicará solo el municipio o delegación y el código postal.
                            </p></div>
                     </div>
               <div id="situacion_actual">
                     <h3>Situación laboral actual</h3>
                        <p class="un_tercio">
                        <strong>¿Trabajas Actualmente?</strong><br>
                        <label>Sí&nbsp;<input type="radio" value="" name=""></label>&nbsp;&nbsp; <label>No&nbsp;<input type="radio" value="" name=""></label>&nbsp;&nbsp;
                        </p>
                        <p class="un_tercio">
                        <strong><label for="selectMotivo">¿Por qué buscas trabajo?</label></strong><br>
                        <select id="selectMotivo">
                        	<option>Cambio de empleo</option>
                        </select>
                        </p>
                        <p class="un_tercio">
                        <strong><label for="selectMeses2">Empezaste a buscar empleo en</label>:</strong><br>
                        <select id="selectMeses2">
                        	<option>Enero</option>
                            <option>Febrero</option>
                            <option>Marzo</option>
                            <option>Abril</option>
                            <option>Mayo</option>
                            <option>Junio</option>
                            <option>Julio</option>
                            <option>Agosto</option>
                            <option>Septiembre</option>
                            <option>Octubre</option>
                            <option>Noviembre</option>
                            <option>Diciembre</option>
                        </select>&nbsp;&nbsp;
                        <select>
                        	<option>02</option>
                            <option>03</option>
                            <option>04</option>
                            <option>05</option>
                            <option>06</option>
                            <option>07</option>
                            <option>08</option>
                            <option>09</option>
                            <option>10</option>
                            <option>12</option>
                            <option>13</option>
                            <option>14</option>
                            <option>15</option>
                        </select>&nbsp;&nbsp;
                        <select>
                        	<option>2009</option>
                            <option>2010</option>
                            <option>2011</option>
                        </select>
                        </p>
                        <p class="un_tercio">
                        <strong><label for="selectEnteraste">¿Cómo te enteraste de este portal?</label></strong><br>
                        <select id="selectEnteraste"><option>Televisión</option></select>
                        </p>
                        <p class="dos_tercios">
                        <strong><label for="selectMedio">¿Qué otros medios has utilizado para buscar trabajo?</label></strong><br>
                        <select id="selectMedio"><option>Periódico</option></select>
                        </p>
                        <div class="division">
                            <p class="entero">
                            <strong>¿Cuéntas con algún tipo de discapacidad?</strong><br>
                            <label>Ninguna&nbsp;<input type="radio" value="" name=""></label>&nbsp;&nbsp; <label>Auditiva&nbsp;<input type="radio" value="" name=""></label>&nbsp;&nbsp; <label>Visual&nbsp;<input type="radio" value="" name=""></label>&nbsp;&nbsp; <label>Motriz&nbsp;<input type="radio" value="" name=""></label>&nbsp;&nbsp; <label>Intelectual&nbsp;<input type="radio" value="" name=""></label>
                            </p>
                            <p class="entero">
                            * Esta información no seá mostrada a menos que tu lo solicites, su uso solo será para fines internos y de vinculación.
                            </p>
                            <p class="entero">
                            <label for="checkConfDiscap">Deseo mostrar la información relacionada con mi discapacidad &nbsp;<input type="checkbox" value="" name="" id="checkConfDiscap"></label>
                            </p>
                        </div>
                 </div>
                 <p style="text-align: center;" class="entero">
                                        <input type="button" value="Guardar" name="" class="boton"></p>
                </div>
                
                  </div>
                  
                  <h3>Tabla</h3>
                  
                  
          <table cellspacing="0" cellpadding="0" border="0" width="100%">
	<tbody><tr class="temas">
    	<th>Oferta de Empleo</th>
        <th>Ubicación</th>
        <th>Empresa</th>
        <th>Sueldo</th>
        <th class="fin">Bolsa de trabajo</th>
    </tr>
	<tr>
    	<td class="checar"><input type="radio" value="" name="">
        <p><strong>Ingeniero en sistemas</strong><br>
      <a href="#">Ocultar detalles</a>        </p></td>
        <td><p>Tlalpan, Distrito Federal<br>
        <a href="#">Ver mapa</a></p></td>
        <td>Grupo Bimbo S.A.</td>
        <td>$10,000</td>
        <td><a class="btn_portal" href="#">Portal del empleo</a></td>
    </tr>
	<tr class="odd">
    	<td class="checar"><input type="radio" value="" name="">
        <p><strong>Ingeniero en sistemas</strong><br>
      <a href="#">Ocultar detalles</a>        </p></td>
        <td><p>Tlalpan, Distrito Federal<br>
        <a href="#">Ver mapa</a></p></td>
        <td>Grupo Bimbo S.A.</td>
        <td>$10,000</td>
        <td><a class="btn_portal" href="#">Portal del empleo</a></td>
    </tr>
	<tr>
    	<td class="checar"><input type="radio" value="" name="">
        <p><strong>Ingeniero en sistemas</strong><br>
      <a href="#">Ocultar detalles</a>        </p></td>
        <td><p>Tlalpan, Distrito Federal<br>
        <a href="#">Ver mapa</a></p></td>
        <td>Grupo Bimbo S.A.</td>
        <td>$10,000</td>
        <td><a class="btn_portal" href="#">Portal del empleo</a></td>
    </tr>
	<tr class="odd">
    	<td class="checar"><input type="radio" value="" name="">
        <p><strong>Ingeniero en sistemas</strong><br>
      <a href="#">Ocultar detalles</a>        </p></td>
        <td><p>Tlalpan, Distrito Federal<br>
        <a href="#">Ver mapa</a></p></td>
        <td>Grupo Bimbo S.A.</td>
        <td>$10,000</td>
        <td><a class="btn_portal" href="#">Portal del empleo</a></td>
    </tr>
	<tr>
    	<td class="checar"><input type="radio" value="" name="">
        <p><strong>Ingeniero en sistemas</strong><br>
      <a href="#">Ocultar detalles</a>        </p></td>
        <td><p>Tlalpan, Distrito Federal<br>
        <a href="#">Ver mapa</a></p></td>
        <td>Grupo Bimbo S.A.</td>
        <td>$10,000</td>
        <td><a class="btn_portal" href="#">Portal del empleo</a></td>
    </tr>
	<tr class="odd">
    	<td class="checar"><input type="radio" value="" name="">
        <p><strong>Ingeniero en sistemas</strong><br>
      <a href="#">Ocultar detalles</a>        </p></td>
        <td><p>Tlalpan, Distrito Federal<br>
        <a href="#">Ver mapa</a></p></td>
        <td>Grupo Bimbo S.A.</td>
        <td>$10,000</td>
        <td><a class="btn_portal" href="#">Portal del empleo</a></td>
    </tr>
	<tr>
    	<td class="checar"><input type="radio" value="" name="">
        <p><strong>Ingeniero en sistemas</strong><br>
      <a href="#">Ocultar detalles</a>        </p></td>
        <td><p>Tlalpan, Distrito Federal<br>
        <a href="#">Ver mapa</a></p></td>
        <td>Grupo Bimbo S.A.</td>
        <td>$10,000</td>
        <td><a class="btn_portal" href="#">Portal del empleo</a></td>
    </tr>
</tbody></table>

<p>&lt;!--Hasta aquí--&gt;</p>

