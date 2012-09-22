<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>System Requirement</title>
    
  </head>
  
  <body>
  	<jsp:include page="/header.jsp"></jsp:include>
	<div id="main">
		<div id="require">
   		 <h3>System Requirement</h3>
   		 <p>This system is developed in myeclipse 6.5 and oracle 11g.</p>
    	 <p>The version of jdk is 1.6.</p>
    	 <p>I just test in IE 6.0.</p>
	
	<h3>Before begining</h3>	
				<div class="note">
					<font color="red">At first,I develop this system in my local database. Now I migrate my database to
					the <a href="https://www.eeng.dcu.ie/db/servlet/litteral.servlets.dbbrowse.Query">temporary database.</a>
					and I have add some initial data to my table. The sql statements I provide in the <a href="sql.jsp">sql page</a>.  If tables are accidentally or maliciously deleted.
					You can recreate the necessary tables using these statements. 
					</font>
				</div>

	<h3>Table In My DataBase</h3>
    	 
	 <h4>Entity Table</h4>	
    	 <h5>Student Table</h5>
    	 <p>the fields in this table</p>
    	 	<ul>
    	 		<li><font color="red">stu_id</font> -- the primary key of student table</li>
    	 		<li><font color="red">stu_first_name</font> -- the first name of student</li>
    	 		<li><font color="red">stu_last_name</font> -- the last name of student</li>
    	 		<li><font color="red">stu_password</font> -- the password of student</li>
    	 		<li><font color="red">stu_sex</font> -- the gender of student</li>
    	 		<li><font color="red">stu_email</font> -- the email of student</li>
    	 		<li><font color="red">stu_programme_id</font> -- the programme id which student had paid</li>
    	 		<li><font color="red">stu_ispaid</font> -- have the student paid the programme </li>
    	 		<li><font color="red">stu_country</font> -- the country of student come from</li>
    	 		<li><font color="red">stu_birthday</font> -- the birthday of student</li>
    	 		<li><font color="red">stu_registe_time</font> -- when student registe</li>
    	 		<li><font color="red">stu_username</font> -- the username of student. this is used to login,it aslo is unique. </li>
    	 	</ul>
  	
	<h5>Module Table</h5>
	 <p>the fields in this table</p>
		<ul>
			<li><font color="red">module_id</font>  -- the primary key of student table</li>
			<li><font color="red">module_title</font> -- the name of module. it also is unique </li>
			<li><font color="red">module_desc</font> -- describe the module </li>
			<li><font color="red">module_teacher</font> -- who teach this module, a teacher teach a module,they ar one to one relation </li>
			<li><font color="red">module_start</font> -- when the module start </li>
			<li><font color="red">module_last</font> -- how long the module last </li>
		</ul>
	
	<h5>Programme Table</h5>
	 <p>the fields in this table</p>
		<ul>
			<li><font color="red">programme_id</font> -- the primary key of programme</li>
			<li><font color="red">programme_title</font> -- the title ofprogramme</li>
			<li><font color="red">programme_desc</font> -- decribe the programme</li>
			<li><font color="red">programme_cost</font> -- the tuition fees of programme</li>
		</ul>	

	<h4>Relation</h4>
		<ul>
			<li><h4><font color="darkgreen"><b>one to one</b></font></h4>
				<div id="one2One">	
					<ul>
						<li>A student only can choose one programme</li>
					</ul>
				</div>
			</li>
			
			<li><h4><font color="darkgreen"><b>one to many</b></font></h4>
				<div id="one2many">
					<ul>
						<li>
							a teacher can teach many module
						</li>
					</ul>
				</div>
			</li>

			<li><h4><font color="darkgreen"><b>many to many</b></font></h4>
				<div id="many2many">	
					<ul>
						<li>
							a programme can include many module and a module can be included in many programme.
						</li>
						<li>
							a student can select many module and a module can be selected with many student.
						</li>						
					</ul>
				</div>
			</li>
		</ul>
		<h4>The two relation entity table</h4>
		<p>Because there are two many to many relation. So we must define two relation table. They are:</p>
		
		<h5><font color="blue">Student-Module Associate Table</font></h5>
		 <p>the fields in this table</p>
			<ul>
				<li>module_id -- reference the module_id field at module table</li>
				<li>stu_id -- reference the stu_id field at student table</li>
			</ul>	
			<p>the two fields construct the primary key of this table</p>
		<h5><font color="blue">Programme-Module Associate Talbe</font></h5>
		 <p>the fields in this table</p>
		 	<ul>
		 		<li>module_id -- reference the module_id field at module table</li>
		 		<li>programme_id -- reference the programme_id field at programme table</li>
		 	</ul>
		 	<p>the two fields construct the primary key of this table</p>
	</div>
	</div>
  	<jsp:include page="/footer.jsp"></jsp:include>
  </body>
</html>
