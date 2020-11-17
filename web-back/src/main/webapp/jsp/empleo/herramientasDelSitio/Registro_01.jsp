<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">
      Registro de candidato
    </jsp:attribute>
    
<script src="${context}/js/jquery-scrolltofixed-min.js"></script>

<jsp:body>
<script type="text/javascript">
	function cambia(id){
		var imgOld=document.getElementById(id);
		var path = "${context}/css/images/";
		imgOld.src= path + id +"-act.png";
	}
</script>
<link href="${context}/css/bootstrap/registro.css" rel="stylesheet" type="text/css">
	<div>
		<div class="flow_1">Registro de Candidatos</div>
		<div class="sombra"></div>
	
		<p class="ins_1"><strong>Los datos marcados con <span>*</span> son obligatorios</strong></p>
		<div class="col-sm-3 col-sm-push-9 col-indicador">
		<div class="fuerza-perfil">
			
			<div class="indice-perfil col-sm-12">
				<h2>Fuerza de perfil</h2>
				<div class="indice">
					<div class="bt-datosIdentificacion-01">
				        <a nohref onClick="javascript:cambia('bt-datosIdentificacion');">
				        	<img id="bt-datosIdentificacion" src="${context}/css/images/bt-datosIdentificacion-01.png"
				        	onmouseover="this.src='${context}/css/images/bt-datosIdentificacion-hover.png'" />
				        </a>
				    </div>
					<div class="bt-datosContacto-01">
				        <a nohref onClick="javascript:cambia('bt-datosContacto');">
				        	<img id="bt-datosContacto" src="${context}/css/images/bt-datosContacto-01.png"
				        	onmouseover="this.src='${context}/css/images/bt-datosContacto-hover.png'" />
				        </a>
				    </div>
					<div class="bt-datosDomicilio-01">
				        <a nohref onClick="javascript:cambia('bt-datosDomicilio');">
				        	<img id="bt-datosDomicilio" src="${context}/css/images/bt-datosDomicilio-01.png"
				        	onmouseover="this.src='${context}/css/images/bt-datosDomicilio-hover.png'" />
				        </a>
				    </div>
					<div class="bt-datosExperiencia-01">
				        <a nohref onClick="javascript:cambia('bt-datosExperiencia');">
				        	<img id="bt-datosExperiencia" src="${context}/css/images/bt-datosExperiencia-01.png"
				        	onmouseover="this.src='${context}/css/images/bt-datosExperiencia-hover.png'" />
				        </a>
				    </div>
					<div class="bt-datosEscolaridad-01">
				        <a nohref onClick="javascript:cambia('bt-datosEscolaridad');">
				        	<img id="bt-datosEscolaridad" src="${context}/css/images/bt-datosEscolaridad-01.png"
				        	onmouseover="this.src='${context}/css/images/bt-datosEscolaridad-hover.png'" />
				        </a>
				    </div>

					
						<!-- Cambiar imagen del usuario-->
						<div class="circular" style="background: url(${context}/css/images/icon-persona.png)"></div>

				</div>
			</div>
			<div class="clearfix"></div>
			<div class="indice-candidato">
				<div class="ind-usuario">Candidato</div>
				<div class="indicador"></div>
				<div class="barra">
					<div class="crecimiento" style="width: 80%"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="programas-ind col-sm-12">
				<h2>Programas</h2>
				<div class="modulo-ind">
					<a href="#">Mecanismo de Movilidad Laboral (MML)</a>
				</div>
				<div class="modulo-ind">
					<a href="#">Sector agricola (MLI-SA)</a>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="programas-ind col-sm-12">
				<h2>Beneficios</h2>
				<div class="modulo-ind solicita-cita">
					<a href="#">Solicita una cita</a>
				</div>
				<div class="modulo-ind">
					<a href="#">Entrevista en línea</a>
				</div>
				<div class="modulo-ind">
					<a href="#">Oferta de acuerdo a mi perfil</a>
				</div>
				<div class="modulo-ind">
					<a href="#">Registrarme a un evento de Ferias de Empleo</a>
				</div>
				<div class="modulo-ind">
					<a href="#">Ver mi curriculum</a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		</div>
		
		<!-- div contenido -->
		<div class="col-sm-9 col-sm-pull-3">
			<div class="panel-body registrar">
			
				<!-- N U E V O -->
				
				<div class="pestana" data-toggle="collapse" data-target="#datos-id"><h2>Datos de identificación</h2></div>
				<div id="datos-id" class="collapse">
					<div class="break">
						<div class="col-sm-6">
							<div class="col-sm-5 row">
								<strong>¿Conoces tu CURP?</strong>
							</div>
							<div class="col-sm-3 row">
								<input type="radio">&nbsp;&nbsp;&nbsp;<strong>Si</strong>
							</div>
							<div class="col-sm-3 row">
								<input type="radio">&nbsp;&nbsp;&nbsp;<strong>No</strong>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="break">
						<div class="col-sm-4">
							<label for=""><span>*</span>CURP</label><br>
							<input id="" type="text" />
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="break">
						<div class="col-sm-4">
							<label for=""><span>*</span>Nombre(s)</label><br>
							<input id="" type="text" />
						</div>
						<div class="col-sm-4">
							<label for=""><span>*</span>Primer apellido</label><br>
							<input id="" type="text" />
						</div>
						<div class="col-sm-4">
							<label for="">Segundo apellido</label><br>
							<input id="" type="text" />
						</div>
						<div class="clearfix"></div>
					</div>
					
					<div class="break">
						<div class="col-sm-4">
							<div class="labeled"><span>*</span>Sexo</div>
							<div class="col-sm-5 row">
								<input type="radio">&nbsp;&nbsp;&nbsp;<strong>Hombre</strong>
							</div>
							<div class="col-sm-5">
								<input type="radio">&nbsp;&nbsp;&nbsp;<strong>Mujer</strong>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="labeled"><span>*</span>Fecha de nacimiento</div>
							<div class="col-sm-4 row">
								<select>
									<option>Día</option>
								</select>
							</div>
							<div class="col-sm-4">
								<select>
									<option>Mes</option>
								</select>
							</div>
							<div class="col-sm-4">
								<select>
									<option>Año</option>
								</select>
							</div>
						</div>	
						<div class="col-sm-4">
							<label for=""><span>*</span>Lugar de nacimiento</label><br>
							<select>
								<option></option>
							</select>
						</div>
						<div class="clearfix"></div>
					</div>

					<hr>
					<div class="break">
						<div class="col-sm-5 ins_2">Responde a la siguiente pregunta</div>
						<div class="clearfix"></div>
						<div class="captcha-element">
							<div class="captcha-group">
								<div class="col-sm-5 row">
									<label><strong><span>*</span>¿Qué número sigue después del 5?</strong></label>
								</div>
								<div class="col-sm-3 row">
									<input type="text">
								</div>
								<div class="col-sm-4 validacion">
									<a href="" class="bt-cambiar-pregunta">Cambiar pregunta</a>
									<a href="" class="bt-validar">Validar</a>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<div class="curp-resultado">
						<div class="col-sm-2 row tu-curp-r">
							<label><strong>Tu CURP es:</strong></label>
						</div>
						<div class="col-sm-4 row">
							<div class="tu-curp">SATD740918MDGLRR06</div>
						</div>
						<div class="clearfix"></div>
					</div>	
					<div class="break">
						<div class="break">
							<div class="labeled"><strong>¿Tiene alguna discapacidad?</strong></div>
						</div>
						<div class="col-sm-12 row">
							<ul class="group-discapacidad">
								<li class="col-sm-4"><input type="checkbox">&nbsp;<label>Auditiva</label></li>
								<li class="col-sm-4"><input type="checkbox">&nbsp;<label>Visual</label></li>
								<li class="col-sm-4"><input type="checkbox">&nbsp;<label>Comunicación (habla)</label></li>
								<li class="col-sm-4"><input type="checkbox">&nbsp;<label>Motriz</label></li>
								<li class="col-sm-4"><input type="checkbox">&nbsp;<label>Intelectual y/o Mental</label></li>
								<li class="col-sm-4"><input type="checkbox">&nbsp;<label>Otras enfermedades discapacitantes</label></li>
							</ul>
						</div>
						<div class="clearfix"></div>
					</div>			

				</div>
				<div class="pestana" data-toggle="collapse" data-target="#datos-contacto"><h2>Datos de contacto</h2></div>
				<div id="datos-contacto" class="collapse">
					<div class="break">
						<div class="col-sm-4">
							<label for=""><span>*</span>Correo electrónico</label><br>
							<input type="text">
						</div>
						<div class="col-sm-4">
							<label for=""><span>*</span>Confirmación de correo electrónico</label><br>
							<input type="text">
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="break">
						<div class="col-sm-4">
							<label for=""><span>*</span>Contraseña</label><br>
							<input type="text">
						</div>
						<div class="col-sm-4">
							<label for=""><span>*</span>Confirmación de contraseña</label><br>
							<input type="text">
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="break">
						<div class="col-sm-4">
							<div class="labeled"><span>*</span>Tipo de teléfono</div>
							<div class="col-sm-5 row">
								<input type="radio">&nbsp;&nbsp;&nbsp;<strong>Celular</strong>
							</div>
							<div class="col-sm-5">
								<input type="radio">&nbsp;&nbsp;&nbsp;<strong>Fijo</strong>
							</div>
						</div>
						<div class="col-sm-4 row">
							<div class="col-sm-6">
								<label for=""><span>*</span>Clave</label>
								<input type="text">
							</div>
							<div class="col-sm-6">
								<label for=""><span>*</span>Lada</label>
								<input type="text">
							</div>
						</div>
						<div class="col-sm-4 row">
							<div class="col-sm-6">
								<label for=""><span>*</span>Teléfono</label>
								<input type="text">
							</div>
							<div class="col-sm-6">
								<label for=""><span>*</span>Extensión</label>
								<input type="text">
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-sm-12 break">
						<a class="boton-01" href="#">Agregar otro teléfono</a>
					</div>
					<div class="clearfix"></div>
					<div class="break">
						<div class="col-sm-12">
							<div class="col-sm-4 row">
								<div class="labeled">
									<strong><span>*</span>Forma de contacto</strong>
								</div>
							</div>
							<div class="col-sm-3 row">
								<input type="checkbox">&nbsp;&nbsp;&nbsp;Correo electrónico
							</div>
							<div class="col-sm-3">
								<input type="checkbox">&nbsp;&nbsp;&nbsp;Teléfono
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="pestana" data-toggle="collapse" data-target="#datos-domicilio"><h2>Domicilio</h2></div>
				<div id="datos-domicilio" class="collapse">
					<div class="break">
						<div class="col-sm-4">
							<label for=""><span>*</span>Código postal</label><br>
							<input type="text" id="">
						</div>
						<div class="col-sm-4">
							<label for="">Entidad federativa</label><br>
							<select>
								<option>Ciudad de México</option>
							</select>
						</div>
						<div class="col-sm-4">
							<label for="">Municipio o delegación</label><br>
							<select>
								<option>Benito Juárez</option>
							</select>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="break">
						<div class="col-sm-4">
							<label for="">Colonia</label><br>
							<select>
								<option>Letrán Valle</option>
							</select>
						</div>
						<div class="col-sm-4">
							<label for="">Localidad</label><br>
							<select>
								<option>Letrán Valle</option>
							</select>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-sm-12 break">
						<a class="boton-01" href="#">Ampliar domicilio</a>
					</div>
					<div class="clearfix"></div>
					<div class="break ampliar">
						<div class="col-sm-4">
							<label for="">Calle</label><br>
							<input type="text" id="" value="Edzna">
						</div>
						<div class="col-sm-4">
							<label for="">Entre calle</label><br>
							<input type="text" id="" value="Pilares">
						</div>
						<div class="col-sm-4">
							<label for="">y calle</label><br>
							<input type="text" id="" value="Cuicuilco">
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="pestana" data-toggle="collapse" data-target="#datos-escolaridad"><h2>Escolaridad y otros conocimientos</h2></div>
				<div id="datos-escolaridad" class="collapse">
					<div class="break">
						<div class="col-sm-6">
							<div class="col-sm-6 row">
								<strong>¿Sabes leer y escribir?</strong>
							</div>
							<div class="col-sm-3 row">
								<input type="radio">&nbsp;&nbsp;&nbsp;<strong>Si</strong>
							</div>
							<div class="col-sm-3 row">
								<input type="radio">&nbsp;&nbsp;&nbsp;<strong>No</strong>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<fieldset><legend>Estudios</legend>
						<div class="break">
							<div class="col-sm-4">
								<label for=""><span>*</span>Escolaridad</label><br>
								<select>
									<option>Licenciatura</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label for=""><span>*</span>Carrera o especialidad</label><br>
								<select>
									<option>Computación</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label for=""><span>*</span>Situación académica</label><br>
								<select>
									<option>Titulado</option>
								</select>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-sm-12 break">
							<a href="#" class="boton-02">Ampliar mi perfil</a>
						</div>
						<div class="clearfix"></div>
						<hr>
						<div class="break">
							<div class="col-sm-8">
								<strong>¿Realizaste tu estudios con ayuda de PROSPERA Programa de Inclusión Social?</strong>
							</div>
							<div class="col-sm-2 row">
								<input type="radio">&nbsp;<label><strong>Si</strong></label>
							</div>
							<div class="col-sm-1 row">
								<input type="radio">&nbsp;<label><strong>No</strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="break">
							<div class="col-sm-4">
								<label for=""><span>*</span>Folio de Familia Prospera</label><br>
								<input type="text" id="" value="123456789">
							</div>
							<div class="col-sm-4">
								<label for="">Folio de integrante Prospera</label><br>
								<input type="text" id="" value="123456789">
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-sm-12 break">
							<a href="#" class="boton-01">Otros estudios</a>
						</div>
						<div class="clearfix"></div>
						<div class="break">
							<div class="col-sm-4">
								<label for="">Tipo de estudio</label><br>
								<select>
									<option>Curso</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label for="">Nombre del estudio</label><br>
								<input type="text" id="" value="PSP">
							</div>
							<div class="col-sm-4">
								<label for="">Institución</label><br>
								<input type="text" id="" value="Infotec">
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="break">
							<div class="col-sm-4">
								<div class="labeled">Fecha de ingreso</div>
								<div class="col-sm-4 row">
									<select>
										<option>Mes</option>
									</select>
								</div>
								<div class="col-sm-4">
									<select>
										<option>Año</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="labeled">Fecha de terminación</div>
								<div class="col-sm-4 row">
									<select>
										<option>Mes</option>
									</select>
								</div>
								<div class="col-sm-4">
									<select>
										<option>Año</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<label for="">Situación académica</label><br>
								<select>
									<option>Terminado</option>
								</select>
							</div>
							<div class="clearfix"></div>
						</div>
						<hr>
						<div class="col-sm-12 break">
							<a class="boton-01" href="#">Agregar otro estudio</a>
						</div>
						<div class="clearfix"></div>
					</fieldset>
					<fieldset>
						<legend>Idioma adicional al nativo</legend>
						<div class="break">
							<div class="col-sm-4">
								<label for=""><span>*</span>Idioma</label><br>
								<select>
									<option>Chino</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label for=""><span>*</span>Dominio del idioma</label><br>
								<select>
									<option>Avanzado</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label for=""><span>*</span>¿Cuentas con certificación?</label><br>
								<select>
									<option>Ninguno</option>
								</select>
							</div>
							<div class="clearfix"></div>
						</div>
						<hr>
						<div class="break">
							<div class="col-sm-4">
								<label for=""><span>*</span>Idioma</label><br>
								<select>
									<option>Chino</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label for=""><span>*</span>Dominio del idioma</label><br>
								<select>
									<option>Avanzado</option>
								</select>
							</div>
							<div class="col-sm-4">
								<label for=""><span>*</span>¿Cuentas con certificación?</label><br>
								<select>
									<option>Ninguno</option>
								</select>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="break">
							<div class="col-sm-4">
								<label for=""><span>*</span>Puntos</label><br>
								<input type="text">
							</div>
							<div class="col-sm-4">
								<div class="labeled"><span>*</span>Fecha de expedición</div>
								<div class="col-sm-4 row">
									<select>
										<option>Día</option>
									</select>
								</div>
								<div class="col-sm-4">
									<select>
										<option>Mes</option>
									</select>
								</div>
								<div class="col-sm-4">
									<select>
										<option>Año</option>
									</select>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
						<hr>
						<div class="col-sm-12 break">
							<a class="boton-01" href="#">Agregar idioma</a>
						</div>
						<div class="clearfix"></div>
					</fieldset>
					<fieldset>
						<legend>Conocimientos</legend>
						<div class="break">
							<div class="col-sm-4">
								<label for=""><span>*</span>Conocimiento</label><br>
								<input type="text">
							</div>
							<div class="col-sm-4">
								<label for=""><span>*</span>Experiencia</label><br>
								<select>
									<option>Avanzado</option>
								</select>
							</div>
							<!--  
							<div class="col-sm-4">
								<input type="checkbox"><label>Herramienta</label>
							</div>-->
							<div class="clearfix"></div>
						</div>
						<div class="break">
							<div class="col-sm-12">
								<label for=""><span>*</span>Describa su experiencia</label><br>
								<textarea></textarea>
							</div>
							<div class="clearfix"></div>
						</div>
						<hr>
						<div class="col-sm-12 break">
							<a class="boton-01" href="#">Agregar conocimiento</a>
						</div>
						<div class="clearfix"></div>
					</fieldset>
					<fieldset>
						<legend>Habilidades y aptitudes</legend>
						<div class="break">
							<div class="col-sm-12 break">
								<div class="labeled"><strong><span>*</span>Selecciona máximo 5 habilidades y aptitudes que te caracterizan</strong></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Autonomía</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Orientación a resultados</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Adaptación al cambio</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Razonamiento Lógico-Matemático</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Orientación al cliente</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Tolerancia a la presión</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Negociación</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Compromiso</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Mejora continua análisis y solución de problemas</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Pensamiento crítico</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Creatividad e innovación</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Planeación estratégica</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Trabajo en equipo, motivación, proactividad</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Calidad en el trabajo</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Comuniciación</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Liderazgo</label></div>
							</div>
							<div class="col-sm-6">
								<div class="col-sm-2 row"><input type="checkbox"></div>
								<div class="col-sm-10"><label>Aprendizaje constante</label></div>
							</div>
							<div class="clearfix"></div>
						</div>
					</fieldset>
				</div>
				<div class="pestana" data-toggle="collapse" data-target="#datos-experiencia"><h2>Experiencia y áreas de interés</h2></div>
				<div id="datos-experiencia" class="collapse">
					<div class="break">
						<div class="col-sm-6">
							<div class="col-sm-6 row">
								<strong>¿Tiene experiencia?</strong>
							</div>
							<div class="col-sm-3 row">
								<input type="radio">&nbsp;&nbsp;&nbsp;<strong>Si</strong>
							</div>
							<div class="col-sm-3 row">
								<input type="radio">&nbsp;&nbsp;&nbsp;<strong>No</strong>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<fieldset>
					<legend>Puesto desempeñado</legend>
					<div class="break">
						<div class="col-sm-4">
							<label for=""><span>*</span>Área</label><br>
							<select>
								<option>Administración</option>
							</select>
						</div>
						<div class="col-sm-4">
							<label for=""><span>*</span>Subárea</label><br>
							<select>
								<option>Administración general</option>
							</select>
						</div>
						<div class="col-sm-4">
							<label for=""><span>*</span>Jerarquia del puesto</label><br>
							<select>
								<option>Mandos</option>
							</select>
						</div>
						<div class="clearfix"></div>
						<div class="break">
							<div class="col-sm-4">
								<div class="labeled">Fecha de ingreso</div>
								<div class="col-sm-4 row">
									<select>
										<option>Mes</option>
									</select>
								</div>
								<div class="col-sm-4">
									<select>
										<option>Año</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="labeled">Fecha de terminación</div>
								<div class="col-sm-4 row">
									<select>
										<option>Mes</option>
									</select>
								</div>
								<div class="col-sm-4">
									<select>
										<option>Año</option>
									</select>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</fieldset>
				<hr>
				</div>
				<div class="break">
					<div class="col-sm-12 aviso-privacidad">
						<div class="col-sm-10 break"><strong>El usuario y contraseña son para ingresar en el Portal del Empleo y Ferias de Empleo</strong></div>
						<div class="break">
							<div class="col-sm-4">
								<label for=""><span>*</span>Contraseña</label><br>
								<input type="text">
							</div>
							<div class="col-sm-4">
								<label for=""><span>*</span>Confirmación de contraseña</label><br>
								<input type="text">
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="break">
					<div class="col-sm-12 aviso-privacidad">
						<div class="col-sm-10 row"><strong>¿Deseas que tus datos personales (CURP y domicilio) permanezcan confidenciales para el contacto inicial con las empresas?</strong></div>
						<div class="col-sm-1">
							<input type="radio">&nbsp;&nbsp;<strong>Si</strong>
						</div>
						<div class="col-sm-1">
							<input type="radio">&nbsp;&nbsp;<strong>No</strong>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="col-sm-12 close-form">
					<div style="float:right">
						<input class="boton-registrarse" type="button" value="Registrarse" />
						<input class="boton-cancelar" type="button" value="Cancelar" />
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-- /div contenido -->
		
	</div>
</jsp:body>
</t:publicTemplate>
