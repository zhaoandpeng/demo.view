<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>角色管理</title>
<link rel="stylesheet" type="text/css" href="../../../../layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="../../../../css/main.css">
<script type="text/javascript" src="../../../../layui/layui.js"></script>
</head>
<body class="layui-layout-body">
	
	<table class="layui-table" id="demo" lay-filter="operate" lay-data="{id: 'mainData'}"> </table>

	<div id="toolbar" style="display: none" >
		<button  class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon">&#xe608;</i>新增</button>
		<button  class="layui-btn layui-btn-sm" lay-event="delete"><i class="layui-icon">&#xe640;</i>删除</button>
		<button  class="layui-btn layui-btn-sm" lay-event="modify"><i class="layui-icon">&#xe642;</i>修改</button>
	</div>

	<div id="rowBar" class="layui-hide" >
		<button  class="layui-btn layui-btn-sm layui-btn-warm" lay-event="authorization"><i class="layui-icon">&#xe642;</i>权限分配</button>
	</div>
<script type="text/javascript">
	
	layui.config({base:'/layui.extend/modules/system/'}).use('base_role', function(){
	
		var base_role = layui.base_role; base_role()
	})
</script>
</body>

<form class="layui-form" id="add_form" lay-filter="addform" style="display: none;padding-top: 30px">
  <div class="layui-form-item" pane>
    <label class="layui-form-label">角色名称</label>
    <div class="layui-input-inline ">
      <input type="text" name="roleName" required  lay-verify="required" class="layui-input">
    </div>
  </div>
</form>

<form class="layui-form" id="modify_form" lay-filter="modifyform" style="display: none;padding-top: 30px">
  <div class="layui-form-item" pane>
    <label class="layui-form-label">角色名称</label>
    <div class="layui-input-inline ">
      <input type="text" name="roleName" required  lay-verify="required" class="layui-input">
    </div>
  </div>
</form>


</html>