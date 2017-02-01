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
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/styles.css" rel="stylesheet">
<link href="/css/font-awesome.min.css" rel="stylesheet">
<link href="/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
<link href="/css/sb-admin-2.min.css" rel="stylesheet">


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


<script src="/js/external/jquery-3.1.1.min.js"></script>
<script src="/js/external/bootstrap.min.js"></script>
<script src="/js/external/bootbox.min.js"></script>
<script src="/vendor/metisMenu/metisMenu.js"></script>
<script src="/js/external/sb-admin-2.min.js"></script>
<script src="/plugins/ckeditor/ckeditor.js"></script>
<script src="/js/internal/MyAppUtils.js"></script>
<link rel="stylesheet" type="text/css"
	href="/js/external/DataTables/datatables.min.css" />

<script src="/js/external/DataTables/datatables.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="navigation_bar" />
		<div class="container-fluid">
			<div class="row">
				<div class="alert alert-active alert-success center-block" id="success-alert" style="display:none;">
				    <button type="button" class="close" data-dismiss="alert">x</button>
				    <strong>Success! </strong>
				    Saved  successfully
				</div>
				<div class="alert alert-danger alert-active center-block" id="error-alert" style="display:none">
				    <button type="button" class="close" data-dismiss="alert">x</button>
				    <strong>Error! </strong>
				    Something went wrong
				</div>
				<div id="page">
					<tiles:insertAttribute name="content" />
				</div>
			</div>
		</div>
		<div id="footer_wrapper">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>