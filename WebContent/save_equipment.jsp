<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<style type="text/css">
	span{
		font-size: 12px;
		color: red;
	}
</style>
<script type="text/javascript">

$(function(){
	
	$("#btn").click(function(){

		//每次验证之前将之前的span中提示的内容清楚掉
		$("#name_span").text("");
		$("#ipAddress_span").text("");
		$("#buyTime_span").text("");
		
		
		
		var name  = $("#name").val();
		//验证名称不能为空
		if("" == name || null == name){
			$("#name_span").text("设备名称不能为空！");
			return;
		}
		//验证名称长度
		if(name.length > 50){
			$("#name_span").text("设备名称长度不能超过50个字符！");
			return;
		}
		
		var ipAddress  = $("#ipAddress").val();
		//验证Ip地址不能为空
		if("" == ipAddress || null == ipAddress){
			$("#ipAddress_span").text("Ip地址不能为空！");
			return;
		}
		//验证名称长度
		if(ipAddress.length > 50){
			$("#ipAddress_span").text("Ip地址长度不能超过50个字符！");
			return;
		}
		
		
		var buyTime  = $("#buyTime").val();
		//验证Ip地址不能为空
		if("" == buyTime || null == buyTime){
			$("#buyTime_span").text("购买时间不能为空！");
			return;
		}
		
		$("#dform").submit();
		
	});
			
	
})
</script>
<title>增加设备</title>
</head>
<body>
	<form id="dform" action="equiServlet?method=saveEquipment" method="post">
		设备名称：<input type="text" name="name" id="name"><span id="name_span"></span><br>	
		I&nbsp;P&nbsp;地&nbsp;址&nbsp;：<input type="text" name="ipAddress" id="ipAddress"><span id="ipAddress_span"></span><br>	
		简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;介&nbsp;：<textarea name="introduce" rows="5" cols="20"></textarea><br>
		购买时间：<input type="date" name="buyTime" id="buyTime"><span id="buyTime_span"></span><br>	
		生产厂家：<input type="text" name="factory"><br>
		<!-- 此处应该是数据库获取的值,请求后台 -->
		设备类型：<select name="equipmentType.tid">
					<c:forEach items="${list }" var="etype">
						<option value="${etype.tid }">${etype.typeName}</option>
					</c:forEach>
			   </select><br>
		<input type="button" id="btn" value="保存">
	</form>
</body>
</html>