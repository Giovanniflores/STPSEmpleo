<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="${pageContext.request.requestURL}" />

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Admin Contenido | Portal del Empleo </title>
<meta property="og:title" content="Portal de Empleo: Admin Contenido">
<meta name="twitter:title" content="Portal de Empleo: Admin Contenido">
<meta property="og:description" content="Portal de Empleo: Admin Contenido">
<meta name="twitter:description" content="Portal de Empleo: Admin Contenido">
<meta name="description" content="Admin Contenido, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo">
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
    <link href="<%=request.getContextPath()%>/crm/css/datatables/tools/css/dataTables.tableTools.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

    <!--[if lt IE 9]>
    <script src="../assets/js/ie8-responsive-file-warning.js"></script>
    <![endif]-->

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
                    <a href="index.html" class="site_title"> <span>Portal de Empleo</span></a>
                </div>
                <div class="clearfix"></div>

                <!-- menu prile quick info -->
                <!-- /menu prile quick info -->

                <br />

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

                    <div class="menu_section">
                        <h3>Contenido</h3>
                        <ul class="nav side-menu">
                            <li><a><i class="fa fa-edit"></i> Articulos <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu" style="display: none">
                                    <li><li><a href="${crmArticulosForm.articulosUrl}" >Articulos de intéres para ti</a></li>
                                </ul>
                                <ul class="nav child_menu" style="display: none">
                                    <li><li><a href="${crmArticulosForm.etiquetaUrl}" >Etiquetas para los Articulos</a></li>
                                </ul>


                            </li>
                            <li><a><i class="fa fa-edit"></i> Banner <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu" style="display: none">
                                    <li><li><a href="${crmArticulosForm.bannerUrl}" >Crear Banner</a></li>
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
                        <h3>Articulos de su interes</h3>
                    </div>

                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">

                        </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>

                <div class="row">

                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Articulos</h2>
                                <ul class="nav navbar-right panel_toolbox">

                                    <li> <a href="crearArticulo.do"  class="btn btn-success btn-xs">Crear Artículo</a></li>

                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">

                                <table id="example" class="table table-striped responsive-utilities jambo_table">
                                    <thead>
                                    <tr>
                                        <th style="width: 1%">#</th>
                                        <th style="width: 50%">Nombre de articulo</th>
                                        <th style="width: 29%">Estatus</th>
                                        <th style="width: 20%">#Editar</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <logic:iterate id="crearArticulosForm" name="crmArticulosForm" property="articulosFormList">
                                        <tr>
                                            <td>#</td>
                                            <td>
                                                <a><bean:write name="crearArticulosForm"  property="titulo"></bean:write></a>
                                                <br />
                                                <small>Creado el <bean:write name="crearArticulosForm"  property="fecha"></bean:write></small>
                                            </td>

                                            <td>
                                                <logic:equal value="true" name="crearArticulosForm" property="activo">
                                                    <button type="button" class="btn btn-success btn-xs">Activo</button>
                                                </logic:equal>
                                                <logic:equal value="false" name="crearArticulosForm" property="activo">
                                                    <button type="button" class="btn btn-danger btn-xs">No Activo</button>
                                                </logic:equal>
                                                <logic:equal value="true" name="crearArticulosForm" property="indexable">
                                                    <button type="button" class="btn btn-success btn-xs">Indexado</button>
                                                </logic:equal>
                                                <logic:equal value="false" name="crearArticulosForm" property="indexable">
                                                    <button type="button" class="btn btn-danger btn-xs">No Indexado</button>
                                                </logic:equal>
                                                <logic:equal value="true" name="crearArticulosForm" property="mostrarEnHome">
                                                    <button type="button" class="btn btn-success btn-xs">Mostrar en Inicio</button>
                                                </logic:equal>
                                                <logic:equal value="false" name="crearArticulosForm" property="mostrarEnHome">
                                                    <button type="button" class="btn btn-danger btn-xs">No Mostrar en Inicio</button>
                                                </logic:equal>


                                            </td>
                                            <td>
                                                <a href="<bean:write name="crearArticulosForm"  property="editar"></bean:write>" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Editar</a>
                                                <a href="<bean:write name="crearArticulosForm"  property="borrar"></bean:write>" class="btn btn-danger btn-xs"><i class="fa fa-pencil"></i> Borrar</a>

                                            </td>
                                        </tr>

                                    </logic:iterate>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>

                    <br />
                    <br />
                    <br />

                </div>
            </div>
            <!-- footer content -->
            <footer>
                <div class="">

                </div>
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


<!-- Datatables -->
<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>

<script>
    $(document).ready(function () {
        $('input.tableflat').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });
    });

    var asInitVals = new Array();
    $(document).ready(function () {
        var oTable = $('#example').dataTable({
            "oLanguage": {
                "sProcessing":    "Procesando...",
                "sLengthMenu":    "Mostrar _MENU_ registros",
                "sZeroRecords":   "No se encontraron resultados",
                "sEmptyTable":    "Ningún dato disponible en esta tabla",
                "sInfo":          "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                "sInfoEmpty":     "Mostrando registros del 0 al 0 de un total de 0 registros",
                "sInfoFiltered":  "(filtrado de un total de _MAX_ registros)",
                "sInfoPostFix":   "",
                "sSearch":        "Buscar:",
                "sUrl":           "",
                "sInfoThousands":  ",",
                "sLoadingRecords": "Cargando...",
                "oPaginate": {
                    "sFirst":    "Primero",
                    "sLast":    "Último",
                    "sNext":    "Siguiente",
                    "sPrevious": "Anterior"
                },
                "oAria": {
                    "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                }
            },
            "aoColumnDefs": [
                {
                    'bSortable': false,
                    'aTargets': [0]
                } //disables sorting for column one
            ],
            'iDisplayLength': 10,
            "sPaginationType": "full_numbers",
            "dom": 'T<"clear">lfrtip'
        });
        $("tfoot input").keyup(function () {
            /* Filter on the column based on the index of this element's parent <th> */
            oTable.fnFilter(this.value, $("tfoot th").index($(this).parent()));
        });
        $("tfoot input").each(function (i) {
            asInitVals[i] = this.value;
        });
        $("tfoot input").focus(function () {
            if (this.className == "search_init") {
                this.className = "";
                this.value = "";
            }
        });
        $("tfoot input").blur(function (i) {
            if (this.value == "") {
                this.className = "search_init";
                this.value = asInitVals[$("tfoot input").index(this)];
            }
        });
    });


</script>
</body>

</html>