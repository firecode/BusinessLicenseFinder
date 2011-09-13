<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<%@ page language="java" %>
<%@ page import="java.util.Vector" %>
<%@ page import="IMess.MeetingTemplate" %>
<%@ page import="IMess.MeetingTemplateFields" %>
<%@ page import="IMess.SessionTemplateFields" %>
<%@ page import="IMess.Account" %>


<jsp:useBean id="account" class="IMess.Account" scope="session" />
<jsp:useBean id="mtFieldsVec" class="java.util.Vector" scope="session" />
<jsp:useBean id="stFieldsVec" class="java.util.Vector" scope="session" />
<jsp:useBean id="mTemplate" class="IMess.MeetingTemplate" scope="session" />
<jsp:useBean id="errorMessageVec" class="java.util.Vector" scope="session" />

<HEAD>
  <TITLE>Meeting Template Creation Form</TITLE>
 <link rel="stylesheet" type="text/css" href="/imess/html/style.css" />

<script language="JavaScript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
  </script>
</HEAD>

<BODY>
<div class="header">
		<%@include file='../html/meetingorgheader.html' %>
	</div>
<div class="sidebar">
	<%@include file='../html/meetingorgsidebar.html' %> 
	</div>

<div class="content">
<div style="text-align: center;"><big
style="color: rgb(51, 102, 255);">Available Conference List for Meeting Organizer<br>
<br> <%out.println(account.getUserName());%>
</big> </div>
  
  <%session.getAttribute("errorMessageVec");
	if(!errorMessageVec.isEmpty()){
	  String errorMsg = (String)errorMessageVec.get(0);
 %> 
  <font face="Arial, Helvetica, sans-serif" color="#FF0000" size="3"><%="Error:" + errorMsg%></font><font face="Arial, Helvetica, sans-serif"><strong> 
  </strong></font><font color="#000066"><BR>
  </font> 
  <%}%>
  
<FORM name="MEETINGTEMPLATE" METHOD=POST ACTION="/imess/IMessController">
    <INPUT TYPE="hidden" NAME="Form" VALUE="MEETINGTEMPLATE">
    <CENTER>
    <p><%session.getAttribute("mTemplate");
	if(mTemplate != null){
 %> </p>
  </center>
   
    
  <table width="90%" border="1" bordercolor="#000000" bgcolor="#ffffcc" >
    <tr> 
      <td width="27%"><font face="Arial, Helvetica, sans-serif">Name:<strong><font size="1"> 
      (Robotics Conference 2004) </font> 
        <input type="text" name="mName" value="<%=mTemplate.getName() %>" size="34">
      </strong></font></td>
      <td width="21%"><font face="Arial, Helvetica, sans-serif">Conference Duration:<font size="1"> 
      (07/24/2004 - 07/29/2004)</font> &nbsp; &nbsp; &nbsp; &nbsp;
        <input type="text" name="mDuration" value="<%=mTemplate.getDateSpan() %>" size="34">
        </font></td>
</tr>
<tr>
      <td width="26%"><font face="Arial, Helvetica, sans-serif">Location:<font size="1"> 
      (Birmingham)</font> &nbsp; &nbsp; &nbsp; &nbsp;
        <input type="text" name="mLocation" value="<%=mTemplate.getLocation() %>" size="34">
        </font></td>
      <td width="24%"><font face="Arial, Helvetica, sans-serif">Modified Date:<font size="1"> 
      (07/21/2004)</font> <input type="text" name="mModified" value="<%=mTemplate.getModified() %>" size="34">
        </font></td>
    </tr>
  </table>

  
  <table width="90%" border="1" height="164" bordercolor="#000000" bgcolor="#ffffcc">
    <tr>
      <td height="33">
        <div align="center">Header</div>
      </td>
    </tr>
    <tr> 
      <td> 
        <div align="center">
          &nbsp;<textarea name="mHeader" 150="150" cols="70" rows="4" ><%=mTemplate.getHeader() %></textarea></div>
      </td>
    </tr>
  </table>
  <table width="90%" border="1" height="116" bordercolor="#000000" bgcolor="#ffffcc">
    <tr>
      <td>
        <div align="center">Footer</div>
      </td>
    </tr>
    <tr> 
      <td> 
        <div align="center"> 
          <textarea name="mTrailer" 150="150" cols="70" rows="4" ><%=mTemplate.getTrailer() %></textarea>
        </div>
      </td>
    </tr>
  </table>
  <p><%; } %> </p>
<HR>
  <table width="80%" border="1" bordercolor="#000000" bgcolor="#ffffcc">
    <tr> 
	<td width="3%"> 
        &nbsp;</td>
	<td width="5%"> 
        <div align="center">Display </div>
		<div align="center">Order </div>
      </td>
      <td width="20%"> 
        <div align="center">Field name </div>
      </td>
      <td width="20%"> 
        <div align="center">Field value</div>
        <div align="center"><font size="1">(Field Value will be ignored if Field Type is TEXT or SINGLE-VALUE(Check Box))</font></div>
        <div align="center"><font size="1">(Ex:Section Chair, PACE Chair if Field 
          Type is Multi-Value)</font></div>
      </td>
      <td width="50%"> 
        <div align="center">Field type</div>
      </td>
    </tr>

   
    <%session.getAttribute("mtFieldsVec");
      int i = 0;
	  String checkBoxFld = "mfCheckBox" ;       
      String firstFld  = "mfDisplayOrder";
      String secondFld = "mfName";
      String thirdFld  = "mfValue";
      String fourthFld  = "mfType";
	while (mtFieldsVec.size() > i){
          IMess.MeetingTemplateFields mtFields = (IMess.MeetingTemplateFields) mtFieldsVec.get(i);
    %> 
    <tr> 
      <td width="3%"> 
          <input type="checkbox" name="<%=checkBoxFld + i %>" value="1" <%=mtFields.getDeleteRow().equals("1")?"checked":""%>></td>

      <td width="8%"> 
        <div align="center">
          <input type="text" name="<%=firstFld + i %>" value="<%=mtFields.getDisplayOrder()%>" size="9" >
        </div>
      </td>

      <td width="30%"> 
        <div align="center"> 
          <input type="text" name="<%=secondFld + i %>" value="<%=mtFields.getName()%>" size="35" >
        </div>
      </td>
      <td width="35%"> 
        <div align="center"> 
          <input type="text" name="<%=thirdFld + i %>" value="<%=mtFields.getValue() %>" size="31" >
        </div>
      </td>
      <td width="49%"> 
        <div align="center"> 
          <select name="<%=fourthFld + i %>"  size="1">
			<option selected><%=mtFields.getType() %></option>
            <option value="SINGLE-VALUE(Check Box)">SINGLE-VALUE(Check Box)</option>
            <option value="MULTI-VALUE(Radio Button)">MULTI-VALUE(Radio Button)</option>
            <option value="TEXT">TEXT</option>
          </select>
        </div>
      </td>
    </tr>
    <%  i++;
        } ;
    %>    
  </table>

  <p>
    <input type="submit" name="AddField" value="Add Field">
    <input type="submit" name="ClearField" value="Clear Fields"></p>
  <HR>
  <table width="90%" border="1" bordercolor="#000000" bgcolor="#ffffcc">
    <tr> 
	  <td width="2%"> 
        &nbsp;</td>
	  <td width="6%"> 
        <div align="center">Display </div>
		<div align="center">Order</div>
      </td>
      <td width="23%"> 
        <div align="center">Session Name</div>
      </td>
      <td width="21%"> 
        <div align="center">Session Date</div>
        <div align="center"><font size="1">(07/24/2004)</font></div>
      </td>
      <td width="22%"> 
        <div align="center">Session Time</div>
        <div align="center"><font size="1">(1100-1300)</font></div>
      </td>
      <td width="11%"> 
        <div align="center">Room</div>
        <div align="center"><font size="1">(BEC 355)</font></div>
      </td>
      <td width="19%"> 
        <div align="center">Convener</div>
      </td>
    </tr>

    <%session.getAttribute("stFieldsVec");
      int j = 0;
	  String field0  = "msCheckBox" ;
	  String field1  = "msDisplayOrder";
      String field2  = "msName";
      String field3  = "msDateSpan";
      String field4  = "msTimeSpan";
      String field5  = "msRoom";
      String field6  = "msConvener";


	while (stFieldsVec.size() > j){
          IMess.SessionTemplateFields stFields = (IMess.SessionTemplateFields) stFieldsVec.get(j);
    %> 

    <tr> 
      <td width="2%"> 
          <input type="checkbox" name="<%=field0 + j %>" value="1" <%=stFields.getDeleteRow().equals("1")?"checked":""%>></td>
      <td width="6%"> 
        <div align="center"> 
          <input type="text" name="<%=field1  + j %>" value="<%=stFields.getDisplayOrder()%>" size="9">
        </div>
      </td>
      <td width="23%"> 
        <div align="center">
          <input type="text" name="<%=field2  + j %>" value="<%=stFields.getName()%>" size="23">
        </div>
      </td>
      <td width="21%"> 
        <div align="center">
          <input type="text" name="<%=field3  + j %>" value="<%=stFields.getDateSpan()%>" size="26">
        </div>
      </td>
      <td width="22%"> 
        <div align="center">
          <input type="text" name="<%=field4  + j %>" value="<%=stFields.getTimeSpan()%>" size="25">
        </div>
      </td>
      <td width="11%"> 
        <div align="center">
          <input type="text" name="<%=field5  + j %>" value="<%=stFields.getRoom()%>" size="14">
        </div>
      </td>
      <td width="19%"> 
        <div align="center">
          <input type="text" name="<%=field6  + j %>" value="<%=stFields.getConvener()%>" size="22">
        </div>
      </td>
    </tr>
    <%  j++;
        } ;
    %>    
  </table>
  <p>
    <input type="submit" name="AddSession" value="Add Session">
    <input type="submit" name="ClearSession" value="Clear Sessions"></p>
  <HR>
  	<CENTER>
<INPUT TYPE="submit" value="Submit">
    <HR>
</CENTER>

</FORM>

</div>

</BODY>
</HTML>