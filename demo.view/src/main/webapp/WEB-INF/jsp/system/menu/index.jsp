<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>菜单管理</title>
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

<script type="text/javascript">
	
	layui.config({base:'/layui.extend/modules/system/'}).use('base_menu', function(){
	
		var base_menu = layui.base_menu; base_menu()
	})
</script>
</body>

<form class="layui-form layui-form-pane" id="add_form" style="display: none;padding-top: 30px"><!-- lay-filter="addform"  -->
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">菜单名称</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="menuName" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">菜单链接</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="menuUrl" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">菜单图标</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="menuIcon" required  lay-verify="required" class="layui-input" id="iconPicker" lay-filter="iconPicker">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">父级菜单</label>
		    <div class="layui-input-block" style="width:300px;">
		      <select type="text" name="pid" required  lay-verify="required" class="layui-select" ></select>
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">权重</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="orderNo" required  lay-verify="required" class="layui-input layui-hide">
		      <div id="slideOrderNo" class="demo-slider" style="padding-top:15px;padding-left: 10px"></div>
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">是否启用</label>
		    <div class="layui-input-block" style="width:300px;">
		      <select type="text" name="isDisabled" required  lay-verify="required" class="layui-select"></select>
		    </div>
		  </div>
		  <button lay-submit lay-filter="addform" class="layui-hide">提交</button>
</form>

<!-- <form class="layui-form" id="modify_form" lay-filter="modifyform" style="display: none;padding-top: 30px">
  <div class="layui-form-item" pane>
    <label class="layui-form-label">角色名称</label>
    <div class="layui-input-inline ">
      <input type="text" name="roleName" required  lay-verify="required" class="layui-input">
    </div>
  </div>
</form> -->

</html>