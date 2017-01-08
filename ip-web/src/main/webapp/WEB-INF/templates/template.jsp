<!DOCTYPE HTML>
<%@ include file="/WEB-INF/templates/includes.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
	
    <script src="../js/external/jquery-3.1.1.min.js"></script>
    <script src="../js/external/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../js/external/DataTables/datatables.min.css"/>
 
	<script src="../js/external/DataTables/datatables.min.js"></script>
</head>
<body>
	<div id="banner">
		<tiles:insertAttribute name="header" />
	</div>
	<div></div>
	<tiles:insertAttribute name="navigation_bar" />
	<div></div>
	<div id="page">
		<tiles:insertAttribute name="content" />
	</div>
	<div></div>
	<div id="footer_wrapper">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>