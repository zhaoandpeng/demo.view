layui.define(['layer', 'element', 'jquery', 'table',  'iconPicker', 'form', 'slider'], function(exports){  
  
	
  var layer = layui.layer, element = layui.element, $ = layui.jquery,  table = layui.table,  form = layui.form,  iconPicker=layui.iconPicker, slider = layui.slider; 
  
  var tableIns = table.render({
	  	id:'mainData',
	    elem: '#demo',
	    skin:'row',
	    even:true,
	    toolbar:'#toolbar',
	    url: '/api/v1/sys/menu/index/view',
	    page: true ,
	    cols: [[ 
	    	{checkbox: true,fixed: 'left'},
	    	{field: 'menuName', title: '菜单名称', width:150, align: 'center'},
	    	{field: 'menuUrl', title: '菜单链接', width:400, align: 'center'},
	    	{field: 'menuIcon', title: '菜单图标', width:150, align: 'center'},
	    	{field: 'orderNo', title: '权重', width:150, align: 'center'},
	    	{field: 'creatorName', title: '创建人', width:150, align: 'center'},
	    	{field: 'createDate', title: '创建时间', width:150, align: 'center'}
	    ]],
	    done : function(){
	       $('th').css({'background-color': '#009688', 'color': '#fff','font-weight':'bold'})
	    }
  });
  
  iconPicker.render({
      elem: '#iconPicker_add',// 选择器，推荐使用input
      type: 'fontClass',// 数据类型：fontClass/unicode，推荐使用fontClass
//    search: true,// 是否开启搜索：true/false，默认true
      page: true,// 是否开启分页：true/false，默认true
      limit: 20// 每页显示数量，默认12
      /*click: function (data) {
          $('#add_form input[name="orderNo"]').val(data.icon);
      }*/
  });
  
  iconPicker.render({
	  elem: '#iconPicker_modify',// 选择器，推荐使用input
	  type: 'fontClass',// 数据类型：fontClass/unicode，推荐使用fontClass
//    search: true,// 是否开启搜索：true/false，默认true
	  page: true,// 是否开启分页：true/false，默认true
	  limit: 20// 每页显示数量，默认12
	  /*click: function (data) {
          $('#add_form input[name="orderNo"]').val(data.icon);
      }*/
  });
  
  slider.render({
	  elem: '#slideOrderNo_add',
	  value: 0, //初始值
	  max:100,
	  input: true, //输入框
	  theme: '#FF5722'
  });
  
  slider.render({
	  elem: '#slideOrderNo_modify',
	  value: 0, //初始值
	  max:100,
	  input: true, //输入框
	  theme: '#FF5722'
  });
  
  table.on('toolbar(operate)', function(obj){
	  
	  	var checkStatus = table.checkStatus(obj.config.id) ,data = checkStatus.data; 
	  	switch(obj.event){
	  			case 'add': add(); break;
	  			case 'modify': if(data.length === 0){ layer.msg('请选择一项数据'); } else if(data.length > 1){ layer.msg('只允许单条编辑')}else{modify(data)} break;
	  			case 'delete': if(data.length === 0){ layer.msg('请选择一行'); } else { del(data) } break;
	  	};
  });
  
  /*table.on('tool(operate)', function(obj){
	  
	  var row = obj.data  ,layEvent = obj.event;
	  
	  $.ajax({
		  
		  type: 'POST',
		  
		  url: '/api/v1/sys/menu/index/data',
		  
		  data: {'id':row.id},
		  
		  dataType: 'json',
		  
		  success: function(data){
			  
			  if(data.data.length>0){
				  
				  init_tree(data.data,row)
			  }
		  }
	  });
  });
  
  function init_tree(data, row){

	  var setting = {
			  view: {
				  showLine: true,
				  fontCss:{'color':'black','font-weight':'bold'},
				  selectedMulti: true 
			  },
			  check:{
				  chkStyle: "checkbox",
				  enable: true 
			  },
			  data: {
				  simpleData: {
					  enable:true,
					  idKey: "id",
					  pIdKey: "pId",
					  rootPId: null,
				  }
			  }
	  };

	  zTreeObj = layui.zTree.init($("#authorization_tree"), setting, data);

	  var authorization = layer.open({
		  type: 1, title:"权限树",
		  resize : false,
		  btn: [ '保存', '取消', ],
		  btnAlign: 'c',
		  area: ['350px', '600px'],
		  skin: 'demo-class',
		  content: $('#authorization_tree'),
		  yes:function(index,layero){
			 
			 var id = row.id, checkNode = zTreeObj.getCheckedNodes(true), menus = new Array(); //获取选中的CHECKBOX选项&&获取当前选中行的角色主键
			 
			 $.each(checkNode,function(index,value){
				 
				 if(value.id=="0"){return true;}
				 
				 menus.push(value.id);
			 })
				
			 $.ajax({
				  
				  type: 'POST',
				  
				  url: '/api/v1/sys/role/resources/update',
				  
				  data: {'roleId':id, 'menus':menus.join()},
				  
				  dataType: 'json',
				  
				  success: function(result){
					  
					  if(result.data.status){
						  
						  layer.msg('权限分配成功'); layer.close(authorization);
						  $('#authorization_tree').empty();
					  }else{
						  layer.msg(result.data.message);
					  }
				  }
			  });
			 
		  },
		  btn2: function(index, layero){
			  $('#authorization_tree').empty();
		  },
		  cancel: function(index, layero){ 
			  $('#authorization_tree').empty();
		  }   
	  });
  }*/
  
  exports('base_menu', function(){ }); 
  
  function add(){
	  
	  var add = layer.open({ 
		  type: 1, title:"新增菜单",
		  resize : false,
		  maxmin: true,
		  btn: [ '保存', '取消', ],
		  btnAlign: 'c',
		  area: ['650px', '480px'],
		  skin: 'demo-class',
		  content: $('#add_form'),
		  yes:function(index,layero){
			  
			  var orderNo = $(".demo-slider .layui-slider-input .layui-slider-input-txt .layui-input").val();
			 
			  $('#add_form input[name="orderNo"]').val(orderNo);
			  
			  $("#add_form_submit").trigger("click");
		  },
		  success:function(){
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/menu/index/view', dataType : "json",
				  success: function(result) { 
					  if(result.data.length>0){
						  $.each(result.data,function(index,value){
							  var option =new Option(value.menuName,value.id);
							  $('[name="pid"]').append(option);
							  form.render();
						  })
					  }
				  }
			  });
			  
			  $(".layui-slider-input").css('margin-top','15px')
		  }
	  });
	  
	  form.on('submit(addform)', function(data){
		  $.ajax({
			  type: 'POST',  url: '/api/v1/sys/menu/add_or_update', dataType : "json", data: data.field,
			  success: function(result) { 
				  if(result.data.status){
					  layer.msg('保存成功'); layer.close(add);  tableIns.reload({ page:{ curr: 1 }});
				  }else{
					  layer.msg('保存失败['+result.data.message+']');
				  }
			  }
		  });
		  return false;
	  });
  }
  
  function modify(data){
	  
	  form.val('modify_form', data[0]);
	 
	  var modify = layer.open({ 
		  type: 1, title:"修改菜单",
		  resize : false,
		  btn: [  '保存', '取消', ],
		  btnAlign: 'c',
		  area: ['650px', '480px'],
		  skin: 'demo-class',
		  content: $('#modify_form'),
		  yes:function(index,layero){
			  var roleName = $("#modify_form input[name='roleName']").val();
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/role/add_or_update', dataType : "json", data: {"roleName":roleName, "id":id},
				  success: function(result) { 
					  if(result.data.status){
						  layer.msg('修改成功'); layer.close(modify);  tableIns.reload({ page:{ curr: 1 }});
					  }else{
						  layer.msg(result.data.message);
					  }
				  }
			  });
		  },
		  success:function(){
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/menu/index/view', dataType : "json",
				  success: function(result) { 
					  if(result.data.length>0){
						  $.each(result.data,function(index,value){
							  var option =new Option(value.menuName,value.id);
							  $('[name="pid"]').append(option);
							  form.render();
						  })
					  }
				  }
			  });
			  
			  $(".layui-slider-input").css('margin-top','15px')
		  }
	  });
  }
  
  /*function del(data){
	  
	  var ids = new Array(); 
	  
	  $.each(data,function(index,value){
		  
		  ids.push(value.id);
	  })
	  $.ajax({
		  type: 'POST',  url: '/api/v1/sys/role/delete/'+ids.join(','), dataType : "json",
		  success: function(result) { 
			  if(result.data.status){
				  layer.msg('删除成功');  tableIns.reload({ page:{ curr: 1 }});
			  }else{
				  layer.msg(result.data.message);
			  }
		  }
	  });
	  
  }*/
}).extend({iconPicker: '../iconPicker/iconPicker'});



