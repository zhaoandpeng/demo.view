<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页</title>
<link rel="stylesheet" type="text/css"href="../../layui/css/layui.css">
<link rel="stylesheet" type="text/css"href="../../css/main.css">
<script type="text/javascript" src="../../layui/layui.js"></script>
</head>
<body class="index">
	<div class="flexbox">
	<div class="layui-container ">
		<form action="login" class="layui-form layui-form-pane" method="post">
			<div class="layui-row">
				<div class="layui-col-md3 layui-col-md-offset4">
					<div class="layui-form-item">
						<label class="layui-form-label"><i class="layui-icon layui-icon-username" style="font-size: 14px">&ensp;用户名</i></label>
						<div class="layui-input-block">
	      					<input type="text" name="username" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
	   					</div>
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-col-md3 layui-col-md-offset4">
					<div class="layui-form-item">
						<label class="layui-form-label"><i class="layui-icon layui-icon-password" style="font-size: 14px"></i>&ensp;密&ensp;&ensp;码</label>
						<div class="layui-input-block">
	      					<input type="password" name="password" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
	   					</div>
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-col-md3 layui-col-md-offset4">
					<div class="layui-form-item">
							<div id="slider" class="layui-div"></div>
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-col-md3 layui-col-md-offset4">
					<div class="layui-form-item">
						<div class="layui-row">
							<div class="layui-col-md9">
								<div class="layui-col-md4">
			      					<button class="layui-btn layui-btn-fluid" lay-submit>登录</button>
			      				</div>
	   						</div>
							<div class="layui-col-md3">
			      				<button class="layui-btn layui-btn-fluid" type="reset" >注册</button>
	   						</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	</div>
</body>
<script type="text/javascript">layui.config({base:'/layui.extend/'}).use('index', function(){var index = layui.index; index()})</script>
</html>