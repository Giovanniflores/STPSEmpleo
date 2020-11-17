<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.requestURL}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Crear Articulo de su interes | Portal del Empleo</title>
    <meta property="og:title" content="Portal del Empleo: Crear Articulo de su interes">
<meta name="twitter:title" content="Portal del Empleo: Crear Articulo de su interes">
<meta property="og:description" content="Portal del Empleo: Crear Articulo de su interes">
<meta name="twitter:description" content="Portal del Empleo: Crear Articulo de su interes">
<meta name="description" content="Crear Articulo de su interes, , Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo">
<meta property="og:url" content="${url}">
<meta property="og:site_name" content="Portal del Empleo">
<meta property="og:image" content="http://qa.empleo.gob.mxqa.empleo.gob.mx/css/images/contenido-compartir.jpg">
<meta property="og:image:type" content="image/jpeg">
<meta property="og:image:width" content="1200">
<meta property="og:image:height" content="627">
<meta property="fb:app_id" content="308925772806125" />
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@empleogob_mx">
<meta name="twitter:creator" content="@InfotecMexico">
<meta name="twitter:image:src" content="http:/qa.empleo.gob.mx/css/images/compartir-contenido-tweetA.jpg">
<meta name="author" content="infotec">
    <link href="<%=request.getContextPath()%>/crm/css/bootstrap.min.css" rel="stylesheet">

    <link href="<%=request.getContextPath()%>/crm/fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/crm/css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="<%=request.getContextPath()%>/crm/css/custom.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/crm/css/icheck/flat/green.css" rel="stylesheet">




    <script src="<%=request.getContextPath()%>/crm/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/crm/js/ckeditor/ckeditor.js"></script>

    <!--[if lt IE 9]>
    <script src="../assets/js/ie8-responsive-file-warning.js"></script>
    <![endif]-->
    <!-- select2 -->
    <link href="<%=request.getContextPath()%>/crm/css/select/select2.min.css" rel="stylesheet">
    <!-- daterangepicker -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/crm/js/moment.min2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/crm/js/datepicker/daterangepicker.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="nav-md">
<div class="container body">


    <div class="main_container">

        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">

                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Portal De Empleo Content Manager</span></a>
                </div>
                <div class="clearfix"></div>


                <br/>

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

                    <div class="menu_section">
                        <h3>Contenido</h3>
                        <ul class="nav side-menu">
                            <li><a><i class="fa fa-edit"></i> Articulos <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu" style="display: none">
                                    <li><li><a href="${crmCreateArticuloForm.articulosUrl}" >Articulos de intéres para ti</a></li>
                                </ul>
                                <ul class="nav child_menu" style="display: none">
                                    <li><li><a href="${crmCreateArticuloForm.etiquetaUrl}" >Etiquetas para los Articulos</a></li>
                                </ul>


                            </li>
                            <li><a><i class="fa fa-edit"></i> Banner <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu" style="display: none">
                                    <li><li><a href="${crmCreateArticuloForm.bannerUrl}" >Crear Banner</a></li>
                                </ul>
                            </li>

                        </ul>
                    </div>

                </div>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <div class="sidebar-footer hidden-small">
                    <a data-toggle="tooltip" data-placement="top" title="Logout">
                        <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                    </a>
                </div>
                <!-- /menu footer buttons -->
            </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">

            <div class="nav_menu">
                <nav class="" role="navigation">
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown"
                               aria-expanded="false">
                                Usuario
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
                                <li><a href="login.do"><i class="fa fa-sign-out pull-right"></i>Cerrar Sessión</a>
                                </li>
                            </ul>
                        </li>

                        <li role="presentation" class="dropdown">

                            <ul id="menu1" class="dropdown-menu list-unstyled msg_list animated fadeInDown" role="menu">
                                <li>
                                    <a>
                                            <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image"/>
                                    </span>
                                            <span>
                                        <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where...
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                            <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image"/>
                                    </span>
                                            <span>
                                        <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where...
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                            <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image"/>
                                    </span>
                                            <span>
                                        <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where...
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                            <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image"/>
                                    </span>
                                            <span>
                                        <span>John Smith</span>
                                            <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where...
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <div class="text-center">
                                        <a>
                                            <strong>See All Alerts</strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>

                    </ul>
                </nav>
            </div>

        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">

            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>${crmCreateArticuloForm.titulo}</h3>
                    </div>

                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">


                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>

                <div class="row">

                    <div class="col-md-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Datos del Artículo</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li> <html:link action="/crmAdmin/crearArticulo.do"  styleClass="btn btn-success btn-xs">Crear Artículo</html:link></li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>

                            <div class="x_content">
                                <html:messages id="errors">

                                <bean:write name="errors" />

                                <br />

                                </html:messages>

                                <br/>
                                <html:form styleClass="form-horizontal form-label-left" action="/crmAdmin/crearArticulo.do">
                                    <html:hidden property="id"></html:hidden>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Titulo</label>

                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <html:text  styleId="titulo" property="titulo" styleClass="form-control" ></html:text>

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Descripción </label>

                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <html:text styleId="Descripcion" property="descripcion" styleClass="form-control"></html:text>

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Palabras Claves:</label>
                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <html:select property="selectedEtiquestas"  styleId="selectedEtiquetas2" styleClass="select2_multiple form-control" multiple="multiple">
                                                    <html:optionsCollection property="etiquetas" value="id" label="etiquetaSelected"></html:optionsCollection>
                                            </html:select>

                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Fuente </label>

                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <html:text styleId="fuente" property="fuente" styleClass="form-control" ></html:text>

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Fecha de
                                            publicación</label>

                                        <div class="controls col-md-9 col-sm-9 col-xs-12">
                                            <div class="input-prepend input-group ">

                                                <html:text property="fecha"
                                                       styleId="fechaCreacion"  styleClass="date-picker form-control col-md-7 col-xs-12" ></html:text>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Mostrar en Home</label>

                                        <div class="controls col-md-9 col-sm-9 col-xs-12">
                                            <div class="input-prepend input-group">
                                                <html:checkbox styleId="mostrarEnHome" property="mostrarEnHome" ></html:checkbox>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Activo</label>

                                        <div class="controls col-md-9 col-sm-9 col-xs-12">
                                            <div class="input-prepend input-group">
                                                <html:checkbox styleId="activo" property="activo" ></html:checkbox>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Indexable</label>

                                        <div class="controls col-md-9 col-sm-9 col-xs-12">
                                            <div class="input-prepend input-group">
                                                <html:checkbox styleId="checkbox" property="indexable" ></html:checkbox>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Articulo</label>

                                        <div class="col-md-9 col-sm-9 col-xs-12">


                                                <textarea  name="editor1" id="editor1" rows="10" cols="80" >${crmCreateArticuloForm.articulo}</textarea>

                                        </div>

                                    </div>


                                    <div class="ln_solid"></div>
                                    <div class="form-group">
                                        <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                            <button type="submit" class="btn btn-primary">Cancelar</button>
                                            <button type="submit" class="btn btn-success">Guardar</button>
                                        </div>
                                    </div>


                                </html:form>
                            </div>
                        </div>
                    </div>


                </div>
            </div>

            <!-- footer content -->
            <footer>
                <div class="clearfix"></div>
            </footer>
            <!-- /footer content -->

        </div>
        <!-- /page content -->
    </div>

</div>

<div id="custom_notifications" class="custom-notifications dsp_none">
    <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
    </ul>
    <div class="clearfix"></div>
    <div id="notif-group" class="tabbed_notifications"></div>
</div>

<script src="<%=request.getContextPath()%>/crm/js/bootstrap.min.js"></script>

<!-- chart js -->
<script src="<%=request.getContextPath()%>/crm/js/chartjs/chart.min.js"></script>
<!-- bootstrap progress js -->
<script src="<%=request.getContextPath()%>/crm/js/progressbar/bootstrap-progressbar.min.js"></script>
<script src="<%=request.getContextPath()%>/crm/js/nicescroll/jquery.nicescroll.min.js"></script>
<!-- icheck -->
<script src="<%=request.getContextPath()%>/crm/js/icheck/icheck.min.js"></script>

<script src="<%=request.getContextPath()%>/crm/js/custom.js"></script>
<script>

</script>
<!-- select2 -->
<script src="<%=request.getContextPath()%>/crm/js/select/select2.full.js"></script>
<!-- select2 -->
<script>

    $(document).ready(function () {

        $("#titulo").attr("placeholder","Titulo");

        $(".select2_multiple").select2({
            maximumSelectionLength: 10,
            placeholder: "Maxima seleciona 10",
            allowClear: true
        });
        // Replace the <textarea id="editor1"> with a CKEditor
        // instance, using default configuration.
        CKEDITOR.replace("editor1");
        $("#fechaCreacion").attr("placeholder","dd/mm/yyyy");
        $("#fechaCreacion").daterangepicker({
            singleDatePicker: true
        }, function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });



    });
</script>
<script type="text/javascript">
    window.onload = function() {
        document.crmCreateArticuloForm.action = "crearArticulo.do";
    }

    function message(msg){
        alert(msg);
    }

</script>


<c:if test="${crmCreateArticuloForm.hasError}">
    <script>
        message('${crmCreateArticuloForm.errorMessage}');

    </script>
</c:if>
</body>
</html>
