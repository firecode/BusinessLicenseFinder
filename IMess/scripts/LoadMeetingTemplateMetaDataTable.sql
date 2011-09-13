delete from MeetingTemplateMetaData ;
insert into MeetingTemplateMetaData (displayOrder, name,value,type) VALUES (10,"Name of Spouse(if attending) (Joan)"," ","TEXT");
insert into MeetingTemplateMetaData (displayOrder, name,value,type) VALUES (20,"Section"," ","TEXT");
insert into MeetingTemplateMetaData (displayOrder, name,value,type) VALUES (30,"Position 1","Section Chair, PACE Chair, Awards Chair","MULTI-VALUE(Radio Button)");
insert into MeetingTemplateMetaData (displayOrder, name,value,type) VALUES (40,"Position 2","Section Chair, PACE Chair, Awards Chair","MULTI-VALUE(Radio Button)");
insert into MeetingTemplateMetaData (displayOrder, name,value,type) VALUES (50,"Position 3","Section Chair, PACE Chair, Awards Chair","MULTI-VALUE(Radio Button)");
insert into MeetingTemplateMetaData (displayOrder, name,value,type) VALUES (60,"Section Chair (or representing Section Chair)"," ","SINGLE-VALUE(Check Box)");
insert into MeetingTemplateMetaData (displayOrder, name,value,type) VALUES (70,"Hotel Registration Complete"," ","SINGLE-VALUE(Check Box)");
insert into MeetingTemplateMetaData (displayOrder, name,value,type) VALUES (80,"Conference Registration Complete"," ","SINGLE-VALUE(Check Box)");
insert into MeetingTemplateMetaData (displayOrder, name,value,type) VALUES (90,"Traveling by","Plane,Car","MULTI-VALUE(Radio Button)");
insert into MeetingTemplateMetaData (displayOrder, name,value,type) VALUES (100,"Arrival Date/Time (/Carrier/Flight if flying)"," ","TEXT");
insert into MeetingTemplateMetaData (displayOrder, name,value,type) VALUES (110,"Departure Date/Time (/Carrier/Flight if flying)"," ","TEXT");
insert into Account(userName,password,role,firstName,middleInitials,lastName,eMail,iEEENum) VALUES("root","root","Administrator","a","b","c","aa@aa.com",11111111);
   
select * from MeetingTemplateMetaData ;