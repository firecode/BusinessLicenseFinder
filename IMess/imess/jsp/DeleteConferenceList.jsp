<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<%@ page language="java" %>
<%@ page import="java.util.Vector " %>
<%@ page import="IMess.MeetingTemplate" %>
<%@ page import="IMess.Account" %>

<jsp:useBean id="account" class="IMess.Account" scope="session" />
<jsp:useBean id="mTemplateVec" class="java.util.Vector" scope="session" />
<jsp:useBean id="dynamicForm" class="java.lang.String" scope="session" />

<HEAD>

  <TITLE>Meeting Template Creation Form</TITLE>
<script language="JavaScript">

function deleteAlert() {
var message = "Please confirm that it is OK to DELETE your selection";

    // CONFIRM IS BOOLEAN. THAT MEANS THAT
    // IT RETURNS TRUE IF 'OK' IS CLICKED
    // OTHERWISE IT RETURN FALSE
 var return_value = confirm(message);

    // TEST TO SEE IF TRUE|FALSE RETURNED
    if ( return_value == true )
        {
        return(true);
        }
    else
        {
        return(false);
        // YOUR 'CANCEL' SCRIPT GOES HERE
        }
}
  </script>

  <link rel="stylesheet" type="text/css" href="/imess/html/style.css" />

</HEAD>

<BODY>
    <%session.getAttribute("account");
      if (account!=null){
    %>
<div class="header">
<%session.getAttribute("dynamicForm");
 if (dynamicForm.equalsIgnoreCase("DELETEMEETINGREG")) {
%>

		<jsp:include page='../html/attendeeheader.html' />
<%} else {%>
 		<jsp:include page='../html/meetingorgheader.html' />  
<%}%> 		
	</div>
<div class="sidebar">
<%session.getAttribute("dynamicForm");
 if (dynamicForm.equalsIgnoreCase("DELETEMEETINGREG")) {
%>

		<jsp:include page='../html/attendeesidebar.html' />
<%} else {%>
 		<jsp:include page='../html/meetingorgsidebar.html' />  
<%}%> 		

	</div>

<div class="content">


<div style="text-align: center;"><big
style="color: rgb(51, 102, 255);"> Available Conference List for User<br>
<br> <%out.println(account.getUserName());%>

</big> </div>
   
    <%
    } else {%>
        <b><font size="5" color="#000066">Available Conference List</font></b></CENTER>
    <%}%>

<FORM name="DELETEMEETING" METHOD="POST" ACTION="/imess/IMessController" onSubmit="return deleteAlert()">
    <INPUT TYPE="hidden" NAME="Form" VALUE="<%=dynamicForm%>">


  <table width="90%" border="1" bordercolor="#000000" bgcolor="#ffffcc" >
    <tr> 
	  <td width="30%" height="29"> 
        <div align="left"><strong>Name</strong> </div>
      </td>
      <td width="23%" height="29"> 
        <p align="left"> 
        <b>Duration</b></td>
      <td width="19%" height="29"> 
        <p align="left"> 
        <strong>Location</strong></td>
      <td width="22%" height="29"> 
        <p align="left"> 
        <strong>Modified Date</strong></td>
    </tr>

   
    <%session.getAttribute("mTemplateVec");
      int i = 0;
	  String checkBoxFld = "mCheckBox";
	  while (mTemplateVec.size() > i){
          IMess.MeetingTemplate mt = (IMess.MeetingTemplate) mTemplateVec.get(i);
    %> 
    <tr> 
	  <td width="30%"> 
        <div align="left">
		  <input type="checkbox" name="<%=checkBoxFld + i %>" value="1">                
          <%out.println(mt.getName()); %>
        </div>
      </td>
      <td width="23%"> 
        <div align="center"> 
          <%out.println(mt.getDateSpan()); %>        
        </div>
      </td>
      <td width="19%"> 
        <div align="center"> 
          <%out.println(mt.getLocation()); %>        
        </div>
      </td>
      <td width="22%"> 
        <div align="center"> 
          <%out.println(mt.getModified()); %>        
        </div>
      </td>
    </tr>
    <%  i++;
        } ;
    %>    
  </table>

	<p>&nbsp;</p>

<!-- <CENTER>
    <input type="submit" value="Delete" name="Delete Meeting" >
</CENTER> -->
<%session.getAttribute("dynamicForm");
  if (dynamicForm != null)
  {
     if (dynamicForm.equalsIgnoreCase("DELETEMEETING"))
     {
%>
    <input type="submit" value="Delete Conference & All Registrations" name="DeleteAll">
&nbsp;&nbsp;
    <input type="submit" value="Delete Only Registrations" name="DeleteAllRegistration"></CENTER>
<%
     }
     else
     {
%>
    <input type="submit" value="Delete" name="Delete">
<%
     }
  }
%>
  </FORM>
</div>

</BODY>
</HTML>