<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="../../layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="../../css/main.css">
<script type="text/javascript" src="../../layui/layui.js"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
  		<div class="layui-header">
    		<div class="layui-logo">XXX管理系统</div>
   			<!-- 头部区域（可配合layui已有的水平导航） -->
		    <!-- <ul class="layui-nav layui-layout-left">
		      <li class="layui-nav-item"><a href="">控制台</a></li>
		      <li class="layui-nav-item"><a href="">商品管理</a></li>
		      <li class="layui-nav-item"><a href="">用户</a></li>
		      <li class="layui-nav-item">
		        <a href="javascript:;">其它系统</a>
		        <dl class="layui-nav-child">
		          <dd><a href="">邮件管理</a></dd>
		          <dd><a href="">消息管理</a></dd>
		          <dd><a href="">授权管理</a></dd>
		        </dl>
		      </li>
		    </ul> -->
		    <ul class="layui-nav layui-layout-right">
		      <li class="layui-nav-item">
		        <a href="javascript:;">
		          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">贤心
		        </a>
		        <dl class="layui-nav-child">
		          <dd><a href="">基本资料</a></dd>
		          <dd><a href="">安全设置</a></dd>
		        </dl>
		      </li>
		      <li class="layui-nav-item"><a href="">退出</a></li>
		    </ul>
  		</div>
  
	  	<div class="layui-side layui-bg-black">
	    	<div class="layui-side-scroll">
		      	<ul class="layui-nav layui-nav-tree"  id="memus"></ul>
	    	</div>
	  	</div>
  
		<div class="layui-body">
			<!-- 动态选项卡 -->
			<div class="layui-tab layui-tab-brief " lay-filter="demo_tab" lay-allowclose="true">
			  <ul class="layui-tab-title layui-bg-gray"></ul>
			  <div class="layui-tab-content"></div>
			</div>
		</div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script type="text/javascript">
	
	layui.config({base:'/layui.extend/modules/main/'}).use('main', function(){
	
		var main = layui.main; main()
	})
</script>
</body>
</html>