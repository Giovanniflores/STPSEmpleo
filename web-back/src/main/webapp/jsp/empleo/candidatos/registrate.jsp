<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Reg&iacute;strate y haz que las empresas te vean</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute> 
	<jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Para tener acceso a los datos de contacto de las ofertas de empleo y poderte postular a ellas, es necesario estar registrado en el Portal del Empleo, de igual manera las empresas ubicaran tu perfil laboral.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li class="active">Regístrate y haz que las empresas te vean</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp" />
      
      <div class="col-sm-8 col-sm-pull-4">
      
      	 <jsp:include page="/WEB-INF/template/redes.jsp" />
      
        <div class="panel panel-grey">
          <div class="panel-body">
          <div class="col-md-6">
            <img alt=""
								src="${context}/css/images/contenido/registrate.png"
								class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Regístrate y haz que las empresas te vean</h2>
            <p>Para tener acceso a los datos de contacto de las ofertas de empleo y poderte postular a ellas, es necesario estar registrado en el Portal del Empleo, de igual manera las empresas ubicaran tu perfil laboral.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <p>El Portal del Empleo te ofrece la oportunidad de contar con un espacio personalizado para subir tu currículum y hacerlo visible para las empresas.</p>
	        <p>Para crear tu espacio y poder generar tu currículum, debes registrarte en el portal.</p>
	        <p>Tu registro te brinda beneficios como:</p>
	
	        <ul class="list-group-contenidos">
	          <li><strong>Buscar ofertas</strong> acordes con tu perfil laboral.</li>
	          <li><strong>Recibir ofertas</strong> de empleo en tu correo electrónico.</li>
	          <li><strong>Postularte</strong> por las ofertas de tu interés.</li>
	          <li><strong>Contactar</strong> con las empresas.</li>
	        </ul>
	        
	        <p>¡Ahora, buscar empleo es muy fácil!</p>
	        
	        <a href="<c:url value="/registro_candidato.do"/>">Regístrate ahora</a>
	       </div>
	     </div>
       
      </div>



    </div>
	</jsp:body>
</t:publicTemplate>
