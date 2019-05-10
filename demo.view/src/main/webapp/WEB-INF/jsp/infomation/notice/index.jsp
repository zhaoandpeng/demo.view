<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>通知公告</title>
<link rel="stylesheet" type="text/css" href="../../../../layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="../../../../css/main.css">
<script type="text/javascript" src="../../../../layui/layui.js"></script>
</head>
<body class="layui-layout-body">
	
	<table class="layui-table" id="demo" lay-filter="operate" lay-data="{id: 'mainData'}"> </table>
	
	<div id="div_item_code_tab" style="display: none">
		<table class="layui-table" id="tab_item_code" lay-filter="item_code" lay-data="{id: 'itemCodeData'}"> </table>
	</div>
	
	<div id="toolbar" style="display: none" >
		<button  class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon">&#xe608;</i>新增</button>
		<button  class="layui-btn layui-btn-sm" lay-event="delete"><i class="layui-icon">&#xe640;</i>删除</button>
		<button  class="layui-btn layui-btn-sm" lay-event="modify"><i class="layui-icon">&#xe642;</i>修改</button>
	</div>

<script type="text/javascript">
	
	layui.config({base:'/layui.extend/modules/information/'}).use('info_notice', function(){
	
		var base_dict = layui.base_dict; base_dict();
		
	})
</script>
</body>

<!-- <form class="layui-form layui-form-pane" id="add_form" style="display: none;padding-top: 30px">
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">上级选项码</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="PARENT_ITEM_CODE" required  lay-verify="required" value="0" class="layui-input"><button style="position: absolute;top: 0;right: 1px; cursor: pointer;" type="button" class="layui-btn btn_item_code" value="add">选择</button>
		      <input type="text" name="PID" required  value="0" lay-verify="required" class="layui-input layui-hide">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">选项码</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="ITEM_CODE" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">选项名称</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="ITEM_NAME" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">选项值</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="ITEM_VALUE"  class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">状态</label>
		    <div class="layui-input-block status" style="width:300px;">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">描述</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="REMARK"  class="layui-input"></input>
		    </div>
		  </div>
		  <button lay-submit lay-filter="addform" id="add_form_submit" class="layui-hide">提交</button>
</form>

<form class="layui-form layui-form-pane" id="modify_form" lay-filter="modify_form" style="display: none;padding-top: 30px">
		  <input type="text" name="ID" required  lay-verify="required" class="layui-input layui-hide">
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">上级选项码</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="PARENT_ITEM_CODE" required  lay-verify="required" value="0" class="layui-input"><button style="position: absolute;top: 0;right: 1px; cursor: pointer;" type="button" class="layui-btn btn_item_code" value="modify">选择</button>
		      <input type="text" name="PID" required  value="0" lay-verify="required" class="layui-input layui-hide">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">选项码</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="ITEM_CODE" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">选项名称</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="ITEM_NAME" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">选项值</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="ITEM_VALUE"  class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">状态</label>
		    <div class="layui-input-block status" style="width:300px;">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">描述</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="REMARK"  class="layui-input"></input>
		    </div>
		  </div>
		  <button lay-submit lay-filter="modifyform" id="modify_form_submit" class="layui-hide">提交</button>
</form> -->

</html>