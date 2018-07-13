<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎</title>
<style>
	.hello{width: 1000px;height: 300px;font-size: 101px; line-height: 300px;margin: 0 auto;color: #f0f;text-align: center;}
	.button{width: 1000px;height: 100px;margin: 0 auto;text-align: center;}
	a{width: 200px;height: 100px;font-size: 50px;line-height: 100px; margin: 0 auto;color: #ff0;display:inline-block;text-align: center;
	text-decoration: none;}
</style>
</head>
<body>
	<div class="hello">欢迎使用</div>
	<div class="button">
	<a href="http://localhost:8080/EquipmentManager/equiServlet?method=findEquipmentType">添加</a>
	<a href="http://localhost:8080/EquipmentManager/equiServlet?method=findList">修改</a>
	<a href="http://localhost:8080/EquipmentManager/equiServlet?method=findList">删除</a>
	<a href="http://localhost:8080/EquipmentManager/equiServlet?method=findList">列表</a>
	</div>
</body>
</html>