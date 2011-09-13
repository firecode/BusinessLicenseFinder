<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<%@ page language="java" %>
<%@ page import="java.util.Vector " %>
<%@ page import="IMess.MeetingTemplate" %>
<%@ page import="IMess.MeetingTemplateFields" %>
<%@ page import="IMess.SessionTemplateFields" %>
<%@ page import="IMess.Account" %>


<jsp:useBean id="account" class="IMess.Account" scope="session" />
<jsp:useBean id="mtFieldsVec" class="java.util.Vector" scope="session" />
<jsp:useBean id="stFieldsVec" class="java.util.Vector" scope="session" />
<jsp:useBean id="mTemplate" class="IMess.MeetingTemplate" scope="session" />

<HEAD>
  <TITLE>Meeting Template Creation Form</TITLE>
<link rel="stylesheet" type="text/css" href="/imess/html/style.css" />

</HEAD>

<BODY >
<div class="header">
		<%@include file='../html/meetingorgheader.html' %>
	</div>
<div class="sidebar">
	<%@include file='../html/meetingorgsidebar.html' %> 
	</div>

<div class="content">

<div style="text-align: center;"><big
style="color: rgb(51, 102, 255);">Conference Details for Meeting Organizer<br>
<br> <%out.println(account.getUserName());%>
</big> </div>

<FORM name="READMEETING" METHOD=POST ACTION="/imess/IMessController">
    <INPUT TYPE="hidden" NAME="Form" VALUE="READMEETING">
    <CENTER>
    <p> <Strong> </Strong></p>
	  			
</center>
   
    
  <%session.getAttribute("mTemplate");
	if(mTemplate != null){
 %> 
  <table width="94%" border="1" bordercolor="#000000" bgcolor="#ffffcc" >
    <tr> 
      <td width="27%"><font face="Arial, Helvetica, sans-serif"> <strong> 
        Name:</strong> <%out.println(mTemplate.getName()); %>
        </font>
      </td>
      <td width="30%"><font face="Arial, Helvetica, sans-serif"><strong>Duration: 
        </strong> <%out.println(mTemplate.getDateSpan()); %>
        </font>
      </td>
      <td width="22%"><font face="Arial, Helvetica, sans-serif"><strong>Location: 
        </strong> <%out.println(mTemplate.getLocation()); %>
        </font>
      </td>
      <td width="21%"><font face="Arial, Helvetica, sans-serif"><strong>Modified Date: 
		</strong> <%out.println(mTemplate.getModified()); %>
        </font></td>
    </tr>
  </table>

  <table width="94%" border="1" height="160" bordercolor="#000000" bgcolor="#ffffcc" >
    <tr>
      <td height="33">
        <div align="center"><strong>Header</strong></div>
      </td>
    </tr>
    <tr> 
      <td> 
        <div align="left">
          &nbsp;<%out.println(mTemplate.getHeader()); %></div>
      </td>
    </tr>
  </table>
  <table width="94%" border="1" height="160" bordercolor="#000000" bgcolor="#ffffcc" >
    <tr>
      <td height="28">
        <div align="center"><strong>Footer</strong></div>
      </td>
    </tr>
    <tr> 
      <td> 
        <div align="left">
          <%out.println(mTemplate.getTrailer()); %></textarea></div>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
<%; } %>

  <HR>
  <table width="73%" border="1" bordercolor="#000000" bgcolor="#ffffcc" >
    <tr> 
	<td width="26%"> 
        <div align="center"><strong>Display Order</strong> </div>
      </td>
      <td width="26%"> 
        <div align="center"><strong>Field name</strong> </div>
      </td>
      <td width="25%"> 
        <div align="center"><strong>Field value</strong></div>
      </td>
      <td width="49%"> 
        <div align="center"><strong>Field type</strong></div>
      </td>
    </tr>

   
    <%session.getAttribute("mtFieldsVec");
      int i = 0;
	while (mtFieldsVec.size() > i){
          IMess.MeetingTemplateFields mtFields = (IMess.MeetingTemplateFields) mtFieldsVec.get(i);
    %> 
    <tr> 
	<td width="26%"> 
        <div align="center"> 
          <%out.println(mtFields.getDisplayOrder()); %>
        </div>
      </td>

      <td width="26%"> 
        <div align="center"> 
          <%out.println(mtFields.getName()); %>        
        </div>
      </td>
      <td width="25%"> 
        <div align="center"> 
          <%out.println(mtFields.getValue()); %>        
        </div>
      </td>
      <td width="49%"> 
        <div align="center"> 
          <%out.println(mtFields.getType()); %>        
        </div>
      </td>
    </tr>
    <%  i++;
        } ;
    %>    
  </table>

  <HR>
  <table width="83%" border="1" bordercolor="#000000" bgcolor="#ffffcc" >
    <tr> 
	<td> 
        <div align="center"><strong>Display Order</strong></div>
      </td>
      <td> 
        <div align="center"><strong>Session name</strong></div>
      </td>
      <td> 
        <div align="center"><strong>Duration (Date)</strong></div>
      </td>
      <td> 
        <div align="center"><strong>Duration (Time)</strong></div>
      </td>
      <td> 
        <div align="center"><strong>Room</strong></div>
      </td>
      <td> 
        <div align="center"><strong>Convener</strong></div>
      </td>
    </tr>

    <%session.getAttribute("stFieldsVec");
      int j = 0;
	  while (stFieldsVec.size() > j){
          IMess.SessionTemplateFields stFields = (IMess.SessionTemplateFields) stFieldsVec.get(j);
    %> 

    <tr> 
      <td> 
        <div align="center">
          <%out.println(stFields.getDisplayOrder()); %>                
        </div>
      </td>
      <td> 
        <div align="center">
          <%out.println(stFields.getName()); %>        
        </div>
      </td>
      <td> 
        <div align="center">
          <%out.println(stFields.getDateSpan()); %>        
        </div>
      </td>
      <td> 
        <div align="center">
          <%out.println(stFields.getTimeSpan()); %>        
        </div>
      </td>
      <td> 
        <div align="center">
          <%out.println(stFields.getRoom()); %>        
        </div>
      </td>
      <td> 
        <div align="center">
          <%out.println(stFields.getConvener()); %>                
        </div>
      </td>
    </tr>
    <%  j++;
        } ;
    %>    
  </table>
  <p>
    &nbsp;</p>
  <HR>
  	<CENTER>
  	<input type="submit" name="CompletedReading" value="OK">
  <HR>
</CENTER>

</FORM>

</div>
</BODY>
</HTML>
