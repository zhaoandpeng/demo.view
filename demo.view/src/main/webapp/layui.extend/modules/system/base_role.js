layui.define(['layer', 'element', 'jquery', 'table'], function(exports){
  
	
  var layer = layui.layer, element = layui.element, $ = layui.jquery,  table = layui.table;
  
  
  exports('base_role', function(){
	  
	  table.render({
		    elem: '#demo'
		    ,height: 312
		    ,url: '/api/v1/sys/role/index/data' //数据接口
		    ,page: true //开启分页
		    ,cols: [[ //表头
		       {field: 'roleName', title: '角色名称', width:150, fixed: 'left'}
		    ]]
	  });
	  
	 /* 
	  $.ajax({
		  
		  type: 'POST',  url: '/api/v1/sys/role/index/data', dataType : "json",
		 
		  success: function(result) { 
			  
			  
		  }
	  });*/
  }); 

});