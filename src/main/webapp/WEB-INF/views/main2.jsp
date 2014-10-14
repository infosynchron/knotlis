<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Knot LIS Dashboard</title>
<link href="<c:url value="/resources/adminstrap/css/bootstrap.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/adminstrap/css/plugins/metisMenu/metisMenu.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/adminstrap/css/sb-admin-2.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/adminstrap/font-awesome-4.1.0/css/font-awesome.min.css" />"
	rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-static-top" role="navigation"
		style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">Knot LIS</a>
			<c:if test="${not empty user}">
				<div class="navbar-header">${user.userid}</div>
			</c:if>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right">
			<li class="divider"></li>

			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#"><i class="fa fa-user fa-fw"></i> User
							Profile</a></li>
					<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
					</li>
					<li class="divider"></li>
					<li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		<!-- /.navbar-top-links -->
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li><a id="navdashboard" href="#"><i
							class="fa fa-dashboard fa-fw"></i>Dashboard</a></li>
					<li class="divider"></li>
					<li><a id="navpatienttestentry" href="#"><i
							class="fa fa-bar-chart-o fa-fw"></i> Patient Test Entry</a></li>
					<li class="divider"></li>
					<li><a id="navpatienttestreport" href="#"><i
							class="fa fa-table fa-fw"></i> Patient Test Report</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side -->
	</nav>

	<!-- Page Content -->
	<div id="page-wrapper">
		<form class="form well" role="search" method="post" id="mainform"
			name="mainform" action="main">

			<div id="patiententry" class="container-fluid">
				<input type="text" name="pt_mrnsearchtxt" id="pt_mrnsearchtxt"
					class="span6" placeholder=".Enter MRN  #">

				<button type="submit" value="patientsearch" id="mode" name="mode"
					class="btn btn-primary  btn-circle btn-sm">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</div>


			<div id="dashboard" class="container-fluid">

				<h4 class="page-header">Dashboard</h4>
				<hr>
				<button type="submit" value="dashboard" id="mode" name="mode"
					class="btn btn-primary  btn-circle btn-sm">
					<span class="glyphicon glyphicon-search"></span>
				</button>

				<!-- /.col-lg-12 -->
			</div>

		</form>
	</div>

	<!-- /#page-wrapper -->


	<!-- jQuery Version 1.11.0 -->

	<script
		src="<c:url value="/resources/adminstrap/js/jquery-1.11.0.js" />"></script>
	<script
		src="<c:url value="/resources/adminstrap/js/bootstrap.min.js" />"></script>
	<script
		src="<c:url value="/resources/adminstrap/js/plugins/metisMenu/metisMenu.min.js" />"></script>
	<script src="<c:url value="/resources/adminstrap/js/sb-admin-2.js" />"></script>

	<script>

$(function() {
	
	
	
	$("#navpatienttestentry").click(function(){
		$('.container-fluid').hide();
		$('#page-wrapper').find('#patiententry').show();
	});
	
	$("#navdashboard").click(function(){
		$('.container-fluid').hide();
		$('#page-wrapper').find('#dashboard').show();
	});
	
	/* $(":submit").click(function(event){
		
		if($(this).prop("value") == "patientsearch"){
			alert($(this).prop("value"));
			$("#navpatienttestentry").click();
		}else{
		 $("#mainform").submit();
		}
		   if($(this).prop("id") == "patientsearch"){
		   alert($(this).prop("value"));
		   $("#mainform").submit();
		  }
		  else {
		   alert($(this).prop("name"));
		  } 
	}); */
	
	$("#mainform").submit(function(e){
		var isFormValid = true;
		if ($('#mode').val() == 'patientsearch'){
		        alert('submit intercepted');
		        e.preventDefault(e);
		        return false;
		}else if ($('#mode').val() == 'dashboard'){
		        alert('dashboard');
		        return true;
			}
		
		else{
			return true;
		}
    });

	
	
	
	$("#pt_mrnsearchtxt").keypress(function(event) {
		if (event.which == 13) {
	    	event.preventDefault();
	    	$("#mode").val("patientsearch");
	    	$("#mode").click();
	    }
	});
	
	
}); 

</script>
</body>

</html>