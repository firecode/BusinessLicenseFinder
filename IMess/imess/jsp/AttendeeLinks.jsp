<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" %>
<%@ page import="IMess.Account" %>
<jsp:useBean id="account" class="IMess.Account" scope="session" />


<head>
<title>Attendee Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="/imess/html/style.css" />

</head>

<body>
<div class="header">
		<%@include file='../html/attendeeheader.html' %>
	</div>
<div class="sidebar">
	<%@include file='../html/attendeesidebar.html' %> 
	</div>

<div class="content">

<div style="text-align: center;"><big
style="color: rgb(51, 102, 255);">Welcome Attendee<br /><%out.println(account.getUserName());%>
</big> </div>


<p><font color="#008000" size="4"> <a href="/imess/IMessController?Form=READATTENDEEINFO">Read Your Personal Information</a>
</p>
<p><font color="#008000" size="4"> <a href="/imess/IMessController?Form=SHOWUPDATEATTENDEEINFO">Update Your Personal Information</a></p>
<p><font color="#008000" size="4"><a href="/imess/IMessController?Form=CONFLISTFORREGISTRATION">Create/Update Meeting Registration</a></p>
<p><font color="#008000" size="4"><a href="/imess/IMessController?Form=CONFLISTFORREADREG">Read Meeting Registration Information</a></p>
<!-- <p><font color="#008000" size="4"><a href="/imess/IMessController?Form=CONFLISTFORUPDATEREG">Update Meeting Registration Information</a></p> -->
<p><font color="#008000" size="4"><a href="/imess/IMessController?Form=DELETEMEETINGREGLIST">Delete Meeting Registration</a></p>
<br /><br /><br /><br />
</div>
</body>

</html>
