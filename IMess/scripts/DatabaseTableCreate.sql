mysql -u root -p
use mysql;
delete from user where user="";
create database ImessDB;
grant select on *.* to imessdbro@localhost identified by "imessdbro";
grant select,insert,update,delete on *.* to imessdbrw@localhost identified by "imessdbrw";
grant ALL on *.* to imessdbadm@localhost identified by "imessdbadm";
FLUSH PRIVILEGES;
quit


http://cepa.fnal.gov/DocDB/DocDB/doc/createdb.html
http://cepa.fnal.gov/DocDB/DocDB/sql/CreateDatabase.SQL