<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<%@ page language="java" %>
<%@ page import="java.util.Vector" %>
<%@ page import="IMess.MeetingTemplate" %>
<%@ page import="IMess.Account" %>

<jsp:useBean id="mTemplateVec" class="java.util.Vector" scope="session" />
<jsp:useBean id="dynamicForm" class="java.lang.String" scope="session" />
<jsp:useBean id="account" class="IMess.Account" scope="session" />

<HEAD>
  <TITLE>List of Available Conferences</TITLE>
<link rel="stylesheet" type="text/css" href="/imess/html/style.css" />

  </HEAD>

<BODY>
<div class="header">
<%session.getAttribute("dynamicForm");
 if ((dynamicForm.equalsIgnoreCase("CREATEREGISTRATION")) ||
     (dynamicForm.equalsIgnoreCase("READREGISTRATION"))) {
%>
		<jsp:include page='../html/attendeeheader.html' />
<%} else {%>
 		<jsp:include page='../html/meetingorgheader.html' />  
<%}%> 		
	</div>
<div class="sidebar">
<%session.getAttribute("dynamicForm");
 if ((dynamicForm.equalsIgnoreCase("CREATEREGISTRATION")) ||
     (dynamicForm.equalsIgnoreCase("READREGISTRATION"))) {
%>
		<jsp:include page='../html/attendeesidebar.html' />
<%} else {%>
 		<jsp:include page='../html/meetingorgsidebar.html' />  
<%}%> 	
	</div>

<div class="content">

    <%session.getAttribute("account");
      if (account!=null){
    %>
<div style="text-align: center;"><big
style="color: rgb(51, 102, 255);">Available Conference List for User<br>
<br> <%out.println(account.getUserName());%>
</big> </div>


    
    <%
    } else {%>
        <b><font size="5" color="#000066">Available Conference List</font></b></CENTER>
    <%}%>
<br />

<FORM name="READMEETING" METHOD=POST ACTION="/imess/IMessController">
    <INPUT TYPE="hidden" NAME="Form" VALUE="<%=dynamicForm%>">


  <table width="90%" border="1" bordercolor="#000000" bgcolor="#ffffcc" >
    <tr> 
	<td width="26%" height="29"> 
        <div align="center"><strong>Name</strong> </div>
      </td>
      <td width="26%" height="29"> 
        <p align="center"> 
        <b>Duration</b></td>
      <td width="25%" height="29"> 
        <p align="center"> 
        <strong>Location</strong></td>
      <td width="49%" height="29"> 
        <p align="center"> 
        <strong>Modified Date</strong></td>
    </tr>

   
    <%session.getAttribute("mTemplateVec");
      int i = 0;
      String urlStr = "/imess/IMessController?Form=" + dynamicForm + "?";
	  while (mTemplateVec.size() > i){
          IMess.MeetingTemplate mt = (IMess.MeetingTemplate) mTemplateVec.get(i);
    %> 
    <tr> 
	<td width="26%"> 
        <div align="center">
          <a href="<%=urlStr+mt.getMtNumber()%> ">                 
          <%out.println(mt.getName()); %>
        </div>
      </td>

      <td width="26%"> 
        <div align="center"> 
          <%out.println(mt.getDateSpan()); %>        
        </div>
      </td>
      <td width="25%"> 
        <div align="center"> 
          <%out.println(mt.getLocation()); %>        
        </div>
      </td>
      <td width="49%"> 
        <div align="center"> 
          <%out.println(mt.getModified()); %>        
        </div>
      </td>
    </tr>
    <%  i++;
        } ;
    %>    
  </table>

</FORM>


</div>
</BODY>
</HTML>