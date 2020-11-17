<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Art&iacute;culos de inter&eacute;s para ti</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Artículos de interés para ti
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="#">Herramientas del sitio</a></li>
          <li class="active">Artículos de interés para ti</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->
    

    <div class="row">
 
	  <!-- div contenido -->
      <div class="col-sm-12">
      
      <jsp:include page="/WEB-INF/template/redes.jsp"/>
      
      <div class="panelArticulos">
      
			<div class="page-header noMargin">
				<h2 class="titulosh2">Posgrado, ¿la opción para universitarios desempleados?</h2>
			</div>
			
			<div class="panel-descripcionArticulo">
	          <div class="panel-body">
	          	<p><b>Fuente:</b> <span>Expanción</span></p>
	          	<p><span title="Fecha" class="glyphicon glyphicon-calendar"></span> 14-oct-2015</p>
	            <p><b>Temas relacionados</b> <span class="badgeArticulo">Adultos mayores</span> <span class="badgeArticulo">Currículum</span> <span class="badgeArticulo">Jóvenes</span></p>
	          </div>
	        </div>
        
	        <div class="panel">
	          <div class="panel-body">
	          	<p>A unas semanas de que terminen los cursos en las universidades del país, una de las preguntas que más domina la mente de los estudiantes es “¿Y ahora qué hago?”</p>
				<p>Encontrar trabajo no es sencillo, y la volatilidad económica que se vive en México y el mundo lo complican aún más. Incluso empleadoras como OCCMundial y Manpower han advertido sobre una desaceleración en el empleo. Según datos de la Asociación Nacional de Universidades e Instituciones de Educación Superior (ANUIES), actualmente el 40 por ciento de los universitarios está desempleado.</p>
				<p>El escenario se vuelve más complejo cuando informes de las distintas bolsas de trabajo y de la consultora especializada en empleo, Hays, resaltan que las empresas están solicitando más habilidades que resultan difíciles de cubrir. Manpower, por ejemplo, asegura que de las 5 mil vacantes mensuales que maneja, tiene dificultad en llenar más del 20 por ciento.</p>
				<p>Alejandra Vera, gerente de Relaciones Públicas de OCCMundial, asegura en entrevista con AltoNivel.com.mx que su empresa realizó un estudio en el que el 75 por ciento de los encuestados señaló que para obtener un mejor empleo es importante continuar con su preparación académica después de finalizar la licenciatura.</p>
				<p>Pero cómo saber qué elegir, si un posgrado, un diplomado, una segunda licenciatura o alguna otra opción. Aquí te damos algunas recomendaciones: </p>
				<h3>Maestría</h3>
				<p>Alejandra Vera señala que para una maestría es importante identificar qué vas a requerir. En la mayoría de los casos son de dos a tres años de tiempo, y un título universitario previo, además de un presupuesto bastante elevado, casi del nivel de lo ejercido durante una licenciatura, y en algunos casos superior.</p>
				<p>Un posgrado es útil para quien quiere especializarse en un área determinada. Los temas se tratan de una forma más profunda, y llevan la investigación a la práctica. La gerente de OCC asegura que varios de los recién egresados que eligen una maestría lo hacen para tener una experiencia en otro país, donde amplíen además de sus conocimientos, su cultura y su visión.</p>
				<p>Un informe reciente del Consejo Nacional de Ciencia y Tecnología (Conacyt) apunta que más de 90 por ciento de los estudiantes mexicanos que reciben becas para una maestría o doctorado la cursa dentro del país, y aproximadamente 5 mil está en el extranjero. </p>
				<h3>Diplomado</h3>
				<p>Esta opción es una de las más atractivas para los recién egresados, pues no se requiere tanto tiempo ni presupuesto. Son programas que generalmente ocupan de 100 a 120 horas.</p>
				<p>La gerente de OCCMundial asegura que es importante que el recién egresado identifique el tiempo del que dispone para estudiar otra cosa, y agrega que una de las puertas que se ha abierto en los últimos años son los cursos en línea.</p>
				<p>Un diplomado es más corto que un posgrado y también sirve para especializarse y actualizarse en un área determinada. El estudiante no debe contar con un título universitario, pues no es un grado académico como tal. Son programas organizados en módulos en los que se juntan contenidos de distintas disciplinas. </p>
				
				<ol>
				    <li><strong>Armar un currículum vitae creativo</strong><br>
				    Aunque siempre debes tener en cuenta los consejos de los especialistas en cuanto a la información a incluir en tu hoja de vida, esto no quita que ésta no pueda presentarse de manera creativa o más atractiva. Una buena opción puede ser utilizar una infografía o algún otro formato innovador. Revisa 100 modelos de currículum vitae que propone el <a href="http://www.creadictos.com/100-creativos-disenos-hojas-vida/" target="_blank">Creadictos</a>.</li>
				    <li><strong>Usar la herramienta AdWords de Google</strong><br>
				    Otra alternativa interesante puede ser seguir los pasos de Alec Browstein, un creativo que después de buscar trabajo sin éxito decidió contratar la herramienta de AdWords. ¿Para qué? Seis dólares fue el monto que debió invertir para conocer los nombres de los directivos que le interesaba contactar, una acción que le permitió conseguir un gran trabajo, su propia página de Internet e incluso fama.</li>
				    <li><strong>Proponer algo que interese a la empresa</strong><br>
				    Nina Mufleh es otro buen ejemplo a seguir para conseguir una entrevista en esa empresa que tanto quieres trabajar. Era tal su admiración por la cultura interna de la firma Airbnb que dedicó mucho de su tiempo a hacer una investigación sobre posibles alternativas de expansión para la compañía. Una vez estuvo lista, decidió presentarla al CEO de la firma por medio de un tweet. Fue así como Nina consiguió trabajo en la empresa que admiraba.</li>
				</ol>
				
				<ol>
				    <li><strong>¿Cuántos años tienes?<br>
				    </strong><br>
				    Probablemente la pregunta ilegal más común que te han hecho. Aunque este dato debe estar incluido en tu CV, la persona que te entreviste no tiene porqué preguntarte cuántos años tienes.<br>
				    En EU, por ejemplo, existe la Age Discrimination Act (ADEA), ley que protege a las personas de más de 40 años de tales cuestionamientos en una entrevista de trabajo. Lo mismo ocurre en Reino Unido, en donde también es ilegal preguntar este dato.<br>
				    &nbsp;</li>
				    <li><strong>¿Estas casada?<br>
				    </strong><br>
				    Hay un término en el derecho laboral que se llama discriminación por embarazo, mismo que fue creado para evitar que los empleadores traten a las mamás o mujeres solicitantes de manera injusta.<br>
				    Esta ley prohíbe a los empleadores solicitar cualquier tipo de información respecto a los planes de familia que tenga el candidato.<br>
				    Si te hacen esta pregunta puedes responder que no te sientes cómoda o no te interesa hablar de tu vida privada en un entorno profesional.<br>
				    &nbsp;</li>
				    <li><strong>¿Cuál es tu orientación sexual?<br>
				    </strong><br>
				    Las leyes fundamentales de discriminación prohíben a los reclutadores hacer cualquier tipo de pregunta relacionada a la vida sexual del candidato. No tiene relevancia para el puesto de trabajo para el que compite.<br>
				    Cualquier director de Recursos Humanos que te pregunte sobre este tema está incurriendo en un delito claro.<br>
				    &nbsp;</li>
				</ol>
				
				
				<div class="form-group">
        			<input class="bntArticulos" name="Regresar" type="button" value="Regresar a temas de empleo" alt="">
        		</div>
				
	          </div>
	        </div>
	        
        </div>
        
      </div><!-- /div contenido -->
    </div>
    
	</jsp:body>
</t:publicTemplate>
