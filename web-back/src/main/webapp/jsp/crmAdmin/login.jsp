<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="${pageContext.request.requestURL}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  
    <title> Admín Login | Portal del Empleo </title>
<meta property="og:title" content="Portal de Empleo: Admín | Login ">
<meta name="twitter:title" content="Portal de Empleo: Admín | Login ">
<meta property="og:description" content="Portal de Empleo: Admín | Login, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo. ">
<meta name="twitter:description" content="Portal de Empleo: Admín | Login, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo. ">
<meta name="description" content="Admín | Login, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo. ">
<meta property="og:url" content="${url}">
<meta property="og:site_name" content="Portal del Empleo">
<meta property="og:image" content="http://qa.empleo.gob.mx/css/images/contenido-compartir.jpg">
<meta property="og:image:type" content="image/jpeg">
<meta property="og:image:width" content="1200">
<meta property="og:image:height" content="627">
<meta property="fb:app_id" content="308925772806125" />
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@empleogob_mx">
<meta name="twitter:creator" content="@InfotecMexico">
<meta name="twitter:image:src" content="http://qa.empleo.gob.mx/css/images/compartir-contenido-tweetA.jpg">
<meta name="author" content="infotec">
    <!-- Bootstrap core CSS -->

    <link href="<%=request.getContextPath()%>/crm/css/bootstrap.min.css" rel="stylesheet">

    <link href="<%=request.getContextPath()%>/crm/fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/crm/css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="<%=request.getContextPath()%>/crm/css/custom.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/crm/css/icheck/flat/green.css" rel="stylesheet">


    <script src="<%=request.getContextPath()%>/crm/js/jquery.min.js"></script>

    <!--[if lt IE 9]>
    <script src="../assets/js/ie8-responsive-file-warning.js"></script>
    <![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body style="background:#F7F7F7;">

<div class="">
    <a class="hiddenanchor" id="toregister"></a>
    <a class="hiddenanchor" id="tologin"></a>

    <div id="wrapper">
        <div id="login" class="animate form">
            <section class="login_content">
                <html:messages id="errors">

                    <bean:write name="errors" />

                    <br />

                </html:messages>
                <form>
                    <h1>Ingresar</h1>

                    <html:form action="/crmAdmin/login" focus="usuario">
                        <div>
                            <html:text styleId="usuario" property="usuario" styleClass="form-control"/>
                        </div>
                        <div>

                            <html:password styleId="clave" styleClass="form-control" property="clave"/>
                        </div>
                        <div>
                            <html:submit styleClass="btn btn-default submit" value="Ingresar"/>
                            <a class="reset_pass" href="#">Perdido tu clave</a>
                        </div>

                    </html:form>

                    <div class="clearfix"></div>
                    <div class="separator">

                        <div class="clearfix"></div>
                        <br/>

                        <div>
                            <h1><i class="fa fa-paw" style="font-size: 26px;"></i> Gentelella Alela!</h1>

                            <p>©2015 Todo los derechos reservado ©SNE.</p>
                        </div>
                    </div>
                </form>
                <!-- form -->
            </section>
            <!-- content -->
        </div>
    </div>
</div>
</body>
<script>

    $(document).ready(function () {
        $("#clave").attr("placeholder", "Clave");
        $("#usuario").attr("placeholder", "Usuario");
    });
    window.onload = function () {
        document.crmLoginForm.action = "login.do";
    }
</script>

</html>
