<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" %>
<%@ page import="java.util.Vector" %>
<%@ page import="IMess.MeetingTemplate" %>
<%@ page import="IMess.MeetingTemplateFields" %>
<%@ page import="IMess.SessionTemplateFields" %>
<%@ page import="IMess.Account" %>
<jsp:useBean id="account" class="IMess.Account" scope="session" />
<jsp:useBean id="mTemplate" class="IMess.MeetingTemplate" scope="session" />
<jsp:useBean id="mtFieldsVec" class="java.util.Vector" scope="session" />
<jsp:useBean id="stFieldsVec" class="java.util.Vector" scope="session" />
<jsp:useBean id="mriFieldsVec" class="java.util.Vector" scope="session" />
<jsp:useBean id="sriFieldsVec" class="java.util.Vector" scope="session" />
<jsp:useBean id="errorMessageVec" class="java.util.Vector" scope="session" />

<head>
  <title>Attendee Meeting Registration Form</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="/imess/html/style.css" />

</head>
<body text="#000000" bgcolor="#FFFFFF">
<div class="header">
		<%@include file='../html/attendeeheader.html' %>
	</div>
<div class="sidebar">
	<%@include file='../html/attendeesidebar.html' %> 
	</div>

<div class="content">




<%session.getAttribute("mTemplate");
    if (mTemplate != null) {
%>
<h1><% out.println(mTemplate.getName()); %></h1>
    <p style="font-weight:bold"><% out.println("Location:" + mTemplate.getLocation()); %></p>
    <p style="font-weight:bold"><% out.println("Date(s):" + mTemplate.getDateSpan()); %></p>
    <p style="font-weight:bold"><% out.println(mTemplate.getHeader()); %></p>
<p>This form is only for the participant who are registered with the system to register for meetings</p>
  <%
    ;}
  %>
<hr />

 <%session.getAttribute("errorMessageVec");
	if(!errorMessageVec.isEmpty()){
	  String errorMsg = (String)errorMessageVec.get(0);
 %> 
  <font face="Arial, Helvetica, sans-serif" color="#FF0000" size="3"><%="Error:" + errorMsg%></font><font face="Arial, Helvetica, sans-serif"><strong> 
  </strong></font><font color="#000066"><BR>
  </font> 
  <%}%>

<form
 action="/imess/IMessController" method="post" name="CREATEREGISTRATION" id="CREATEREGISTRATION"> 
  <input type="hidden" name="Form"
 value="CREATEREGISTRATION" />

 
 <%session.getAttribute("account") ;
    if (account != null) {
 %> 

  <table style="text-align: left; width: 80%;" bgcolor="#ffffcc"  border="1" cellspacing="2" cellpadding="2">
    <tbody> 
    <tr> 
      <td style="vertical-align: top;"><b>First Name</b></td>
      <td style="vertical-align: top;"><% out.println(account.getFirstName()); %> &nbsp;</td>
    </tr>
    <tr> 
      <td style="vertical-align: top;"><b>Last Name </b></td>
      <td style="vertical-align: top;"><% out.println(account.getLastName()); %> &nbsp;</td>
    </tr>
    <tr> 
      <td style="vertical-align: top;"><b>IEEE No. </b></td>
      <td style="vertical-align: top;"> <% out.println(account.getIEEENum()); %> &nbsp;</td>
    </tr>
    <tr> 
      <td style="vertical-align: top;"><b>Email </b></td>
      <td style="vertical-align: top;"><% out.println(account.getEMail()); %> &nbsp;</td>
    </tr>
    <tr> 
      <td style="vertical-align: top;"><b>User Name </b></td>
      <td style="vertical-align: top;"><% out.println(account.getUserName()); %> &nbsp;</td>
    </tr>
    <%; } %> 
	<%session.getAttribute("mtFieldsVec");
	  session.getAttribute("mriFieldsVec"); 
        int i = 0;
	    while (mtFieldsVec.size() > i){
          IMess.MeetingTemplateFields mtFields = (IMess.MeetingTemplateFields) mtFieldsVec.get(i);
      %> 
    <tr> 
      <td style="vertical-align: top;"><b><%out.println(mtFields.getName());  %> 
        </b>&nbsp;</td>
      <td style="vertical-align: top;"><%out.println(mtFields.getHTMLforFieldType(mriFieldsVec,false));  %> &nbsp;</td>
    </tr>
    <%  i++;
        } ;
      %> 
  </tbody> 
  </table>

  
<hr />
  <table style="text-align: left; width: 80%;" bgcolor="#ffffcc"  border="1"
 cellspacing="2" cellpadding="2">
    <tbody> 
    <tr> 
      <td style="vertical-align: top;" width="24">&nbsp;</td>
      <td style="vertical-align: top;"><b>Day</b></td>
        <td style="vertical-align: top;"><b>Time </b> </td>
        <td style="vertical-align: top;"><b>Event </b> </td>
        <td style="vertical-align: top;"><b>Room </b> </td>
        <td style="vertical-align: top;"><b>Convener </b></td>
    </tr>
	<%session.getAttribute("stFieldsVec");
	  session.getAttribute("sriFieldsVec");
        int j = 0;
	    while (stFieldsVec.size() > j){
          IMess.SessionTemplateFields stFields = (IMess.SessionTemplateFields) stFieldsVec.get(j);
      %> 

    <tr> 
        <td style="vertical-align: top;">
          <%out.println(stFields.getHTMLforFieldType(sriFieldsVec,false));  %> &nbsp;</td>
        <td style="vertical-align: top;"><%out.println(stFields.getDateSpan());  %> &nbsp;</td>
      <td style="vertical-align: top;"><%out.println(stFields.getTimeSpan());  %> &nbsp;</td>
	  <td style="vertical-align: top;"><%out.println(stFields.getName());  %> &nbsp;</td>
      <td style="vertical-align: top;"><%out.println(stFields.getRoom());  %> &nbsp;</td>
	  <td style="vertical-align: top;"><%out.println(stFields.getConvener());  %> &nbsp;</td>
    </tr>
     <%  j++;
        } ;
     %> 
    </tbody> 
  </table>




<p>&nbsp;</p>
  <input name="submit" type="submit" value="Submit" />
  <input name="cancel" type="submit" value="Cancel" />
   </form>

<hr />
    
    <%session.getAttribute("mTemplate");
      if (mTemplate != null) {
    %>
      <p style="font-weight:bold"><% out.println(mTemplate.getTrailer()); %></p>
      <p style="font-weight:bold"><% out.println("Last Modified:" + mTemplate.getModified()); %></p>
    <%
    ;}
    %>
</div></body>
</html>