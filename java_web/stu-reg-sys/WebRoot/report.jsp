<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>Short Report</title>

	</head>

	<body>
		<jsp:include page="/header.jsp"></jsp:include>
		<div id="main">
			<div id="report">
				<h1>
					Brief introduce to my system
				</h1>
				<p>Maybe you'd like to view some <a href="screenshot.jsp">screen shot</a> first.</p>
				<h2>
					What the system can do?
				</h2>

				<ul>
					<li>
						<h3>
							for student
						</h3>
					</li>
					<h4>
						--register
					</h4>
					<p>
						Students can register as a user. If a username already exists,the
						program will give a tip message to user and indicate a same
						username is existed.you can try another.The sign up proccess also
						check the other input parameter,include the email、username
						length、password length、birthday day format. The least length is 3.
					</p>
					<h4>
						--login
					</h4>
					<p>
						Students can login to select programme and view personal data.
						Only if student have logined and then he(she) can do these
						operation. As the same with sign up. the login poccess also
						validate the input parameter. when username doesn't existed or the
						password is uncorrect.The program will give correspond message to
						help user login success.
					</p>

					<h4>
						--logoff
					</h4>
					<p>
						Student leave this site.this operation just remove the user id in
						session.
					</p>

					<h4>
						--select programme
					</h4>
					<p>
						After login the student can choose programme and view avaiable
						modules in this programme.
					</p>

					<h4>
						--select module
					</h4>
					<p>
						After the select programme and pay for it. The student can view
						the avaiable modules at this programme. He(She) can select modules
						at this page.
					</p>
					
					<div class="note">We can find that the process for a student to select a programme is :
						<font color="red">sign up -> login -> pay for programme -> select module</font>
					</div>

					<li>
						<h3>
							for administrator
						</h3>
					</li>
					<p>Because I don't know facility how to manage the programme and module. So I just implement
						a demo. Just for you to test my program convenient. It doesn't contain authorithy. But it
						can hard code in my program easily. But I think this is not a good way.
					 </p>
					<h4>
						--<a href="admin.jsp">add teacher</a>
					</h4>
					the administrator can add teacher at this page when a teacher join
					us.

					<h4>
						--<a href="admin.jsp">add programme</a>
					</h4>
					<p>
						the administrator also can add a new programme at this page when a
						new collabrative programme come up.
					</p>

					<h4>
						--<a href="admin.jsp">add module</a>
					</h4>
					<p>
						the administrator can add a module into programme and assign a
						teacher to this module.That is said before add a module. there
						must be at least one programme and a teacher.
					</p>
				</ul>
				<div class="note">
					<font color="red">The three operation above can finish in
					<a href="admin.jsp">this page</a>.</font>
				</div>

				<h2>
					System design and implement
				</h2>

				<h3>
					Connect Manager class
				</h3>
				<p>
					This class only have two method. getConnection() and
					closeConnection(); These two method help the dao class to get
					connection and close connection. It perform as a resource manager
					role. If you want to change the database location and database name. you
					can go here and find the connect string and rewrite it.
				</p>

				<h3>
					System Design Pattern
				</h3>

				<p>
					I use the MVC design pattern to finish this Student Registration
					System.
				<ul>
					<li>
						<h4>
							The Model Tier
						</h4>
					</li>
					<p>
						There are four domain class in this system:
						module、programme、student、teacher.They correspond to the entity in
						system.These modules are the intermediary between viewer and
						database. In order to filling model class. I add data access
						object(DAO) tier in the EE557.WangFei.DAO package. for every
						table.I write a correspond dao class.
					</p>

					<li>
						<h4>
							The View Tier
						</h4>
					</li>
					<p>
						Presentation just use jsp page to show the data. May be we also
						can consider it as a template. Then we filling the domain class
						through dao classes. Then we can use request.setAttribute() to
						filling the jsp template. I think I should metion that I use the
						jsp
						<font color="red"><b>c.tld</b></font>( it is a jsp standard template library,
						you can get more details is its <a href="http://java.sun.com/products/jsp/jstl/">homepage</a>) 
						taglib to make my page show more logic and intelligent.
					</p>


					<li>
						<h4>
							The Control Tier
						</h4>
					</li>
					<p>
						This is performed using servlet. In order to let a servlet to
						respond many kinds of request. I add a requset parameter in every
						request. The name of paremeter is action. You can see every
						servlet contain a condional test.for example
						<span class="code">
							if(req.getParameter("action").equals("???")) { //do something
							here } </span>
						we also can check this according to the request url. For example:
						<b><a href="http://localhost:8080/stu-reg-sys/programme.do?action=details&id=35">
						http://localhost:8080/stu-reg-sys/programme.do?action=details&id=35</a></b>. We note 
						the request parameter have two parameters. one is action and the other is programme id.	
					</p>
				</ul>

				<h2>
					Appendix
				</h2>
				<ul>
					<li>
						<font color="darkgreen"><b>Requset Filter</b></font>
					</li>
					<p>
						before request submit. I write a filter to encode all request. And
						before response return to client,this filter also encode the
						response.
					</p>

					<li>
						<font color="darkgreen"><b>Junit</b></font>
					</li>
					<p>
						In order to ensure the correct. I write some unit test to test my
						dao class. Beacuse the dao is system fundation and it is likely to
						be bug in our system. So I just write some unit test to test the
						CRUD method of all dao classes. You can check them in test folder.
					</p>

					<li>
						<font color="darkgreen"><b>Log4j</b></font>
					</li>
					<p>
						In order to debug easily and track my program running. I use log4j
						to ouput the running log in console. You will see some <a href="screenshot.jsp">screen shot</a>
						in <a href="screenshot.jsp">this page</a>.
					</p>

				</ul>
				<h2>
					Some known bug
				</h2>

				<h3>
					Browser comptiable
				</h3>

				<p>
					I test this system is ie 6.0,I have no test in other browser.May be
					it will be not comptiable with other browser.
				</p>
			</div>
		</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	</body>
</html>
