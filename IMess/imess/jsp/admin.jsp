<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" %>
<%@ page import="IMess.Account" %>
<jsp:useBean id="account" class="IMess.Account" scope="session" />
<head>
  <title>Administrator Function Page</title>
<link rel="stylesheet" type="text/css" href="/imess/html/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>
<body>
<div class="header">
		<%@include file='../html/adminheader.html' %>
	</div>
<div class="sidebar">
	<%@include file='../html/adminsidebar.html' %> 
	</div>

<div class="content">
  <div style="text-align: center;"><big
style="color: rgb(51, 102, 255);">Welcome Administrator<br />
    <br />
     <%out.println(account.getUserName());%>
</big> </div>
  <p>Comments:-Here you can create power users (Administrator, Meeting Organizer, 
    and Session Organizer) with adminstrative privileges, Read information of 
    the power users created, Update the information, and Delete power users. </p>
  <p><a href="/imess/IMessController?Form=DISPLAYPOWERUSERSFORM">Create New Power Users</a></font>
</p>
<!--<p><font color="#008000" size="4"> <a href="/imess/IMessController?Form=READUSERS">Read Information of all Power Users</a> </p>
-->
<p> <a href="/imess/IMessController?Form=LISTUPDATEUSERS">Read,Update Information of Power Users </a></p>
<p><a href="/imess/IMessController?Form=LISTDELETEUSERS">Delete Power Users</a></p>
<br /><br /><br /><br />
</div>
</body>
</html>
