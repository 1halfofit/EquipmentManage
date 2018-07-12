<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/css.css" type="text/css" rel="stylesheet">
<title>列表</title>
<style type="text/css">
a {
	text-decoration: none;
	font-size: 30px;
	color: blue;
}
</style>
</head>
<body>
	<form action="equiServlet?method=findEquipmentListMH" method="post">
		设备名称:<input type="text" name="name" value="${equipment.name }">
		&nbsp;&nbsp; 生产厂家:<input type="text" name="factory"
			value="${equipment.factory }">&nbsp;&nbsp; <input
			type="submit" value="查询">
	</form>
	<table>
		<tr>
			<td>序号</td>
			<td>设备名称</td>
			<td>设备类型</td>
			<td>IP地址</td>
			<td>购买时间</td>
			<td>设备简介</td>
			<td>生产厂家</td>
			<td>操作</td>
		</tr>
		<!-- 循环显示后台传输过来的list集合 -->
		<c:forEach items="${list }" var="equi" varStatus="i">
			<tr>
				<td>${i.index+1 }</td>
				<td>${equi.name }</td>
				<td>${equi.equipmentType.typeName }</td>
				<td>${equi.ipAddress }</td>
				<td>${equi.buyTime }</td>
				<td>${equi.introduce }</td>
				<td>${equi.factory }</td>
				<td><input type="button" value="删除" onclick="del(${equi.eid })"><input
					type="button" value="修改" onclick="update(${equi.eid })"></td>
			</tr>
		</c:forEach>
	</table>
	<a href="index.jsp">主页</a>
	<a href="http://localhost:8080/EquipmentManage/equiServlet?method=findEquipmentType">添加</a>
	<script type="text/javascript">
		function del(eid) {
			if (confirm("你确定要删除么?")) {
				location = "equiServlet?method=deleteEquipment&eid=" + eid;
			}
		}
		function update(eid){
			if(confirm("你确定要修改么?")){
				location = "equiServlet?method=findEquipmentById&eid=" + eid;
			}
		}
	</script>

</body>
</html>