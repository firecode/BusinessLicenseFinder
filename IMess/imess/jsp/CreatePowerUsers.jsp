<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Enter Organizer Info</title>
<link rel="stylesheet" type="text/css" href="../html/style.css" />
<meta http-equiv="Content-Type"
content="text/html; charset=iso-8859-1" />
<script language="JavaScript" type="text/JavaScript">

function checkrequired(which) {
var pass=true;
if (document.images) {
for (i=0;i<which.length;i++) {
var tempobj=which.elements[i];
if (tempobj.name.substring(0,8)=="required") {
if (((tempobj.type=="text"||tempobj.type=="textarea")&&
tempobj.value=='')||(tempobj.type.toString().charAt(0)=="s"&&
tempobj.selectedIndex==0)) {
pass=false;
break;
}
}
}
}
if (!pass) {
shortFieldName=tempobj.name.substring(8,30).toUpperCase();
alert("Please make sure the "+shortFieldName+" field was properly completed.");
return false;
}
else
return true;
}
// End -->

function MM_findObj(n, d) { //v4.01
var p,i,x; if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_validateForm() { //v4.0
var i,p,q,nm,test,num,min,max,errors='',args=MM_validateForm.arguments;
for (i=0; i<(args.length-2); i+=3) { test=args[i+2]; val=MM_findObj(args[i]);
if (val) { nm=val.name; if ((val=val.value)!="") {
if (test.indexOf('isEmail')!=-1) { p=val.indexOf('@');
if (p<1 || p==(val.length-1)) errors+=''+nm+' must contain an e-mail address!\n';
} else if (test!='R') { num = parseFloat(val);
if (isNaN(val)) errors+=''+nm+' must contain a number!\n';
if (test.indexOf('inRange') != -1) { p=test.indexOf(':');
min=test.substring(8,p); max=test.substring(p+1);
if (num<min || max<num) errors+=''+nm+' must contain a number between '+min+' and '+max+'!\n';
} } } else if (test.charAt(0) == 'R') errors += 'Please enter your '+nm+'!\n'; }
}
x=document.CREATEUSERS
pass1=x.Password.value;
pass2=x.Password1.value;
if (pass1!=pass2)
{
errors+='Please enter the same password!\n';
}

 if (errors) alert(errors);
document.MM_returnValue = (errors == '');
}

function MM_goToURL() { //v3.0
var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}

//-->
</script>
<meta content="Microsoft FrontPage 5.0" name="GENERATOR" />
</head>
<body>
<div class="header">
<%@ include file="../html/adminheader.html" %></div>
<div class="sidebar">
<%@ include file="../html/adminsidebar.html" %>
 </div>
<div class="content">
  <div style="text-align: center;"><big style="color: rgb(51, 102, 255);">Create 
    Power Users<br />
    <br />
</big> </div>
<form action="/imess/IMessController" method="post" name="CREATEUSERS" id="CREATEUSERS">
    <input type="hidden" name="Form" value="CREATEUSERS" />

<table style="height: 269px; width: 380px;" border="0"  bgcolor='#ffffcc' cellspacing="2"
cellpadding="2">
<tbody>
<tr>
<td width="34%">First Name </td>
<td width="33%"><input name="FirstName" size="20" /></td>
</tr>
<tr>
<td width="34%">Middle Initials</td>
<td width="33%"><input name="MiddleInitials" size="20" /></td>
</tr>
<tr>
<td width="34%">Last Name</td>
<td width="33%"><input name="LastName" size="20" /></td>
</tr>
<tr>
<td height="26">E-Mail</td>
<td width="33%"> <input name="EMail" size="20" /></td>
</tr>
<tr>
<td width="34%">IEEE Number</td>
<td width="33%"><input name="IEEENo" size="20" /></td>
</tr>
<tr>
<td width="34%">Username</td>
<td width="33%"><input name="UserName" size="20" /></td>
</tr>
<tr>
<td width="34%">Password</td>
<td width="33%"> <input name="Password" size="20" type="password" /></td>
</tr>
<tr>
<td width="34%">Confirm Password</td>
<td width="33%"> <input name="Password1" size="20" type="password" /></td>
</tr>
<tr>
<td width="34%">Role</td>
<td width="33%">
<select size="1" name="role">
<option >Administrator</option>
<option selected="selected">Meeting Organizer</option>
<option>Session Organizer</option>
</select>
</td>
</tr>
</tbody>
</table>
<p> 
      <input
onclick="MM_validateForm('FirstName','','R','LastName','','R','IEEENo','','RinRange00000000:99999999','UserName','','R','EMail','','RisEmail','Password','','R');return document.MM_returnValue"
type="submit" value="Submit" />
       &nbsp;&nbsp;&nbsp; 
      <input type="reset"
value="Reset" />
       </p>
</form>
</div>
</body>
</html>
