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
<link rel="stylesheet" type="text/css" href="/imess/html/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
  <TITLE>Conference Reports</TITLE>
  </HEAD>

<BODY>
<div class="header">
		<%@include file='../html/meetingorgheader.html' %>
	</div>
<div class="sidebar">
	<%@include file='../html/meetingorgsidebar.html' %> 
	</div>
    <%session.getAttribute("account");
      if (account!=null){
    %>
    <div class="content">
<div style="text-align: center;"><big
style="color: rgb(51, 102, 255);">View Conference Report<br>
<br> 
</big> </div>
 <b><font size="3" color="#800000"><u>Available Conference List</u></font></b></CENTER>

    
    <%
    } else {%>
        <b><font size="5" color="#000066">Available Conference List</font></b></CENTER>
    <%}%>
<br />

   
    <%session.getAttribute("mTemplateVec");
      int i = 0;
      String meetflag = "mfg";
	  while (mTemplateVec.size() > i){
          IMess.MeetingTemplate mt = (IMess.MeetingTemplate) mTemplateVec.get(i);
          String meeting = meetflag + mt.getName();
          String meeting1 = meeting.replace(' ','^');
          meeting1 = meeting1 + "?" + mt.getMtNumber();
    %> 
<p>
<a href="/imess/IMessController?Form=<%=meeting1%>">
<%out.println(mt.getName());%> </a></p>
    <%  i++;
        }
    %>    
  





<BR>

</div>
</BODY>
</HTML>