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
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Crear Banner | Portal del Empleo</title>
  <meta property="og:title" content="Portal del Empleo: Crear Banner">
<meta name="twitter:title" content="Portal del Empleo: Crear Banner">
<meta property="og:description" content="Portal del Empleo: Crear Banner">
<meta name="twitter:description" content="Portal del Empleo: Crear Banner">
<meta name="description" content="Crear Banner, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo">
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
                  <li><li><a href="${crmCreateBannerForm.bannerForm.articulosUrl}" >Articulos de intéres para ti</a></li>
                </ul>
                <ul class="nav child_menu" style="display: none">
                  <li><li><a href="${crmCreateBannerForm.bannerForm.etiquetaUrl}" >Etiquetas para los Articulos</a></li>
                </ul>

              </li>
                <li><a><i class="fa fa-edit"></i> Banner <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu" >
                        <li><li><a href="${crmCreateBannerForm.bannerUrl}" >Crear Banner</a></li>
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
                <h2>Banners</h2>
                <ul class="nav navbar-right panel_toolbox">

                </ul>
                <div class="clearfix"></div>
              </div>

              <div class="x_content">


                <br/>
                <html:form action="/crmAdmin/crearBanner.do"  method="post" enctype="multipart/form-data" >
                    <html:hidden property="bannerForm.id"></html:hidden>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Titulo</label>

                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <html:text  styleId="titulo" property="bannerForm.titulo" styleClass="form-control" ></html:text>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Descripción </label>

                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <html:text styleId="Descripcion" property="bannerForm.descripcion" styleClass="form-control"></html:text>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Indice </label>

                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <html:text styleId="Descripcion" property="bannerForm.indice" styleClass="form-control"></html:text>

                        </div>
                    </div>

                    <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Activo</label>

                    <div class="controls col-md-9 col-sm-9 col-xs-12">
                        <div class="input-prepend input-group">
                            <html:checkbox styleId="activo" property="bannerForm.activo" ></html:checkbox>
                        </div>
                    </div>
                </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Link</label>

                        <div class="col-md-9 col-sm-9 col-xs-12">
                                <html:text styleId="activo" property="bannerForm.link" styleClass="form-control"></html:text>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Nuevo ventana</label>

                        <div class="controls col-md-9 col-sm-9 col-xs-12">
                            <div class="input-prepend input-group">
                                <html:checkbox styleId="activo" property="bannerForm.nuevaVentana" ></html:checkbox>
                            </div>
                        </div>
                    </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Archivo:</label>
                    <div class="controls col-md-9 col-sm-9 col-xs-12">
                          <div class="input-prepend input-group">
                              <html:file property="bannerForm.file"></html:file>
                              <c:choose>
                                  <c:when test="${crmCreateBannerForm.bannerForm.id != null && crmCreateBannerForm.bannerForm.id != 0}">
                                      <img width="100px" src="<%=request.getContextPath()%>/ImageBanner?idBanner=${crmCreateBannerForm.bannerForm.id}"/>

                                  </c:when>

                              </c:choose>
                          </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Fecha ínicio publicación:</label>

                    <div class="controls col-md-9 col-sm-9 col-xs-12">
                        <div class="input-prepend input-group ">
                            <html:text property="bannerForm.fechaInicio"
                                     styleId="fechaInicio"  styleClass="date-picker form-control col-md-7 col-xs-12" ></html:text>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-md-3 col-sm-3 col-xs-12">Fecha fin publicación:</label>
                      <div class="controls col-md-9 col-sm-9 col-xs-12">
                          <div class="input-prepend input-group ">
                              <html:text property="bannerForm.fechaFin"
                                         styleId="fechaFin"  styleClass="date-picker form-control col-md-7 col-xs-12" ></html:text>
                          </div>
                      </div>
                </div>
                    <button type="submit" id="btn-guardar" name="btn-guardar" class="btn btn-success">Guardar</button>
                    <c:choose>
                    <c:when test="${crmCreateBannerForm.bannerForm.id != null && crmCreateBannerForm.bannerForm.id != 0}">
                    <button type="submit" id="btn-borrar" name="btn-borrar" class="btn btn-error">Borrar</button>
                    </c:when>
                    </c:choose>

                </html:form>

                <table id="example" class="table table-striped responsive-utilities jambo_table">
                  <thead>
                  <tr>
                      <th style="width: 10%">Indice</th>
                      <th style="width: 25%">Banner</th>
                      <th style="width: 25%">Activo</th>
                      <th style="width: 25%">Actiones</th>

                  </tr>
                  </thead>
                    <tbody>
                    <logic:iterate id="listBanner" name="crmCreateBannerForm" property="bannersForm">
                        <tr>
                            <td>
                                <bean:write name="listBanner"  property="indice"></bean:write>

                            </td>

                            <td>
                                <bean:write name="listBanner"  property="titulo"></bean:write>

                            </td>
                            <td>
                            <bean:write name="listBanner"  property="activo"></bean:write>
                            <br>
                            <bean:write name="listBanner"  property="fechaInicio"></bean:write>
                            <br>
                            <bean:write name="listBanner"  property="fechaFin"></bean:write>


                            </td>

                            <td>
                                <a href="<bean:write name="listBanner"  property="editar"></bean:write>" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Editar</a>
                                <a href="<bean:write name="listBanner"  property="borrar"></bean:write>" class="btn btn-danger btn-xs"><i class="fa fa-pencil"></i> Borrar</a>

                            </td>
                        </tr>

                    </logic:iterate>
                    </tbody>
                </table>
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

    $("#fechaInicio").attr("placeholder","dd/mm/yyyy");
    $("#fechaInicio").daterangepicker({
      singleDatePicker: true
    }, function (start, end, label) {
      console.log(start.toISOString(), end.toISOString(), label);
    });
    $("#fechaFin").attr("placeholder","dd/mm/yyyy");
    $("#fechaFin").daterangepicker({
      singleDatePicker: true
    }, function (start, end, label) {
      console.log(start.toISOString(), end.toISOString(), label);
    });




  });
</script>
<script type="text/javascript">
  window.onload = function() {
    document.crmCreateBannerForm.action = "crearBanner.do";
  }


  function message(msg){
      alert(msg);
  }

</script>


      <c:if test="${crmCreateBannerForm.hasError}">
      <script>
          message('${crmCreateBannerForm.errorMessage}');

      </script>
      </c:if>
</body>
</html>




