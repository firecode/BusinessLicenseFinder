<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
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
  <title>Attendee Update Meeting Registration Form</title>
</head>
<body text="#000000" bgcolor="#FFFFFF">

<div class="header">
		<%@include file='../html/attendeeheader.html' %>
	</div>
<div class="sidebar">
	<%@include file='../html/attendeesidebar.html' %> 
	</div>

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

<form action="/imess/IMessController" method="post" name="UPDATEREGISTRATION" id="UPDATEREGISTRATION"> 
  <input type="hidden" name="Form" value="UPDATEREGISTRATION">

<!-- <form name="READREGISTRATION" method="post"
 action="/imess/IMessController"> <input type="hidden" name="Form"
 value="READREGISTRATION"> -->

 
 <%session.getAttribute("account") ;
    if (account != null) {
 %> 

  <table style="text-align: left; width: 40%;" border="1" cellspacing="2" cellpadding="2">
    <tbody> 
    <tr> 
      <td style="vertical-align: top;"><b>First Name</b></td>
      <td style="vertical-align: top;"><% out.println(account.getFirstName()); %> 
      </td>
    </tr>
    <tr> 
      <td style="vertical-align: top;"><b>Last Name </b></td>
      <td style="vertical-align: top;"><% out.println(account.getLastName()); %> 
      </td>
    </tr>
    <tr> 
      <td style="vertical-align: top;"><b>IEEE No. </b></td>
      <td style="vertical-align: top;"> <% out.println(account.getIEEENum()); %> 
      </td>
    </tr>
    <tr> 
      <td style="vertical-align: top;"><b>Email </b></td>
      <td style="vertical-align: top;"><% out.println(account.getEMail()); %> 
      </td>
    </tr>
    <tr> 
      <td style="vertical-align: top;"><b>User Name </b></td>
      <td style="vertical-align: top;"><% out.println(account.getUserName()); %> 
      </td>
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
        </b></td>
      <td style="vertical-align: top;"><%out.println(mtFields.getHTMLforFieldType(mriFieldsVec,true)); %>
      </td>
    </tr>
    <%  i++;
        } ;
      %> 
  </tbody> 
  </table>

  
<hr />
  <table style="text-align: left; width: 60%;" border="1"
 cellspacing="2" cellpadding="2">
    <tbody> 
    <tr> 
      <td style="vertical-align: top;" width="24">&nbsp;</td>
      <td style="vertical-align: top;"><b>Day</b></td>
      <td style="vertical-align: top;"><b>Time </b>
      <td style="vertical-align: top;"><b>Event </b>
      <td style="vertical-align: top;"><b>Room </b>
      <td style="vertical-align: top;"><b>Convener </b></td>
    </tr>
	<%session.getAttribute("stFieldsVec");
	  session.getAttribute("sriFieldsVec");
        int j = 0;
	    while (stFieldsVec.size() > j){
          IMess.SessionTemplateFields stFields = (IMess.SessionTemplateFields) stFieldsVec.get(j);
      %> 

    <tr> 
      <td style="vertical-align: top;"><%out.println(stFields.getHTMLforFieldType(sriFieldsVec,true));  %>
      <td style="vertical-align: top;"><%out.println(stFields.getDateSpan());  %> </td>
      <td style="vertical-align: top;"><%out.println(stFields.getTimeSpan());  %> </td>
	  <td style="vertical-align: top;"><%out.println(stFields.getName());  %> </td>
      <td style="vertical-align: top;"><%out.println(stFields.getRoom());  %> </td>
	  <td style="vertical-align: top;"><%out.println(stFields.getConvener());  %> </td>
    </tr>
     <%  j++;
        } ;
     %> 
    </tbody> 
  </table>
  	<input type="submit" name="UpdateReg" value="Update">
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
<br /><br /><br />
<a href="/imess/html/index.html">Logout</a>
</body>
</html>