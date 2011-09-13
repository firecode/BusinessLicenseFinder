<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" %>
<%@ page import="IMess.Account" %>
<jsp:useBean id="account" class="IMess.Account" scope="session" />

<head>
<title>Conference Links for User:<%out.println(account.getUserName());%></title>
<link rel="stylesheet" type="text/css" href="/imess/html/style.css" />

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>

<body>
<div class="header">
		<%@include file='../html/meetingorgheader.html' %>
	</div>
<div class="sidebar">
	<%@include file='../html/meetingorgsidebar.html' %> 
	</div>

<div class="content">
  <div style="text-align: center;"><big
style="color: rgb(51, 102, 255);"> Welcome Meeting Organizer<br />
    <br />
     <%out.println(account.getUserName());%>

</big> </div></p>
Comments:- Here you can create a conference, read the list of conferences available, update a conference, delete a conference, and view conference reports.

<p><a href="/imess/IMessController?Form=CREATEMEETING">Create 
  Conference</a></p>
<p>  <a href="/imess/IMessController?Form=READMEETINGLIST">Read 
  Conference</a></p>
<p>  <a href="/imess/IMessController?Form=UPDATEMEETINGLIST">Update 
  Conference</a> </p>   
<p> <a href="/imess/IMessController?Form=DELETEMEETINGLIST">Delete 
  Conference</a> </p>
<p> <a href="/imess/IMessController?Form=VIEWCONFERENCEREPORTS">View 
  Conference Reports</a> </p>

</div>
</body>

</html>