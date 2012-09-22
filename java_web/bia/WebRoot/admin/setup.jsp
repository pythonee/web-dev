<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" >
<title>本站配置</title>
<!-- add your meta tags here -->

<link rel="stylesheet" type="text/css" href="css/main.css">
    <script type="text/javascript" src=""></script>
     
<link href="css/layout.css" rel="stylesheet" type="text/css" >
<!--[if lte IE 6]>
<script src="js/minmax.js" type="text/javascript"></script>
<![endif]-->

<!--[if lte IE 7]>
<link href="css/patches/patch.css" rel="stylesheet" type="text/css" >
<![endif]-->

    
</head>
<body>
  <div class="page_margins">
    <div id="border-top">
      <div id="edge-tl"></div>
      <div id="edge-tr"></div>
    </div>
    <div class="page">
      <jsp:include page="../header.jsp" />
      <div id="main">
        <div id="col1">
          <div id="col1_content" class="clearfix">
            <!-- add your content here -->
          
            <div id="menu"><jsp:include page="menu.jsp" /></div>
          </div>
        </div>
        <div id="col3">
          <div id="col3_content" class="clearfix">
            <!-- add your content here -->
       
           <div>
				----------------------------------------------------------------------
				<br>
				启动或停止爬虫：
			</div>
				<table id="setupTable1" border="1">
					<thead>
						<tr>
							<td>
								配置项
							</td>
							<td>
								<p align="center">设置</p>
							</td>
						</tr>
					</thead>
						<tr>
							<td>
								爬虫配置
							</td>
							<td>
								<c:if test="${ requestScope.started==true }">
								<input type="submit" value="启动爬虫" onclick="start()" disabled="disabled"/>			
								<input type="submit" value="停止爬虫" onclick="stop()"/>
								</c:if>
								<c:if test="${ requestScope.started==false }">
								<input type="submit" value="启动爬虫" onclick="start()"/>	    	
								<input type="submit" value="停止爬虫" onclick="stop()" disabled="disabled"/>
								</c:if>
							</td>
						</tr>
				</table>
			<div>
				----------------------------------------------------------------------
				<br>
				爬虫启动计划：
			</div>	
			<div>
				<table id="setupTable2" border="1">
					<thead>
						<tr>
							<td>
								<p align="center">每周启动计划</p>
							</td>
							<td>
								<p align="center">每天启动计划</p>
							</td>
						</tr>
					</thead>
						<tr>
							<td>
								请选择星期和小时：
								<select id="dayOfWeek" >
                                    <option  value ="1" > 星期日 </option >
                                    <option  value ="2" > 星期一 </option >
                                    <option  value ="3" > 星期二 </option >
                                    <option  value ="4" > 星期三 </option >
                                    <option  value ="5" > 星期四 </option >
                                    <option  value ="6" > 星期五 </option >
                                    <option  value ="7" > 星期六 </option >
                                </select>
                                <select id="hour1">
                                	<option value="1">1</option>
                                	<option value="2">2</option>
                                	<option value="3">3</option>
                                	<option value="4">4</option>
                                	<option value="5">5</option>
                                	<option value="6">6</option>
                                	<option value="7">7</option>
                                	<option value="8">8</option>
                                	<option value="9">9</option>
                                	<option value="10">10</option>
                                	<option value="11">11</option>
                                	<option value="12">12</option>
                                	<option value="13">13</option>
                                	<option value="14">14</option>
                                	<option value="15">15</option>
                                	<option value="16">16</option>
                                	<option value="17">17</option>
                                	<option value="18">18</option>
                                	<option value="19">19</option>
                                	<option value="20">20</option>
                                	<option value="21">21</option>
                                	<option value="22">22</option>
                                	<option value="23">23</option>
                                	<option value="0">0</option>
                                </select>
                                <br>
                                <input type="submit" value="提交" onclick="saveWeek()"/>
							</td>
							<td>
								请选择小时：
								<select id="hour2">
                                	<option value="1">1</option>
                                	<option value="2">2</option>
                                	<option value="3">3</option>
                                	<option value="4">4</option>
                                	<option value="5">5</option>
                                	<option value="6">6</option>
                                	<option value="7">7</option>
                                	<option value="8">8</option>
                                	<option value="9">9</option>
                                	<option value="10">10</option>
                                	<option value="11">11</option>
                                	<option value="12">12</option>
                                	<option value="13">13</option>
                                	<option value="14">14</option>
                                	<option value="15">15</option>
                                	<option value="16">16</option>
                                	<option value="17">17</option>
                                	<option value="18">18</option>
                                	<option value="19">19</option>
                                	<option value="20">20</option>
                                	<option value="21">21</option>
                                	<option value="22">22</option>
                                	<option value="23">23</option>
                                	<option value="0">0</option>
                                </select>
                                <br>
                                <input type="submit" value="提交" onclick="saveHour()"/>
							</td>
						</tr>
				</table>
			</div>	
          </div>
          <!-- IE Column Clearing -->
          <div id="ie_clearing"> &#160; </div>
        </div>
      </div>
      <!-- begin: #footer -->
       <div id="footer"><jsp:include page="../footer.jsp" /></div>
    </div>
    <div id="border-bottom">
      <div id="edge-bl"></div>
      <div id="edge-br"></div>
    </div>
  </div>
</body>
</html>
																																																																																																																																																						
