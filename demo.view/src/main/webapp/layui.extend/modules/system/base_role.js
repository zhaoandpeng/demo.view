layui.define(['layer', 'element', 'jquery', 'table', 'form'], function(exports){
  
	
  var layer = layui.layer, element = layui.element, $ = layui.jquery,  table = layui.table, form = layui.form ;
  
  var tableIns = table.render({
	  	id:'mainData',
	    elem: '#demo',
	    skin:'row',
	    even:true,
	    toolbar:'#toolbar',
	    url: '/api/v1/sys/role/index/data',
	    page: true ,
	    cols: [[ 
	    	{checkbox: true,fixed: 'left'},
	    	{field: 'roleName', title: '角色名称', width:150, align: 'center'},
	    	{fixed: 'right', title: '操作', width: 165, align:'center', toolbar: '#rowBar'}
	    ]],
	    done : function(){
	        
	       $('th').css({'background-color': '#009688', 'color': '#fff','font-weight':'bold'})
	    }
  });
  
  var checkRow = function(){
	  
	  var checkStatus = table.checkStatus('mainData'), data = checkStatus.data; return data;
  }
  
  table.on('toolbar(operate)', function(obj){
	  
	  	var checkStatus = table.checkStatus(obj.config.id) ,data = checkStatus.data; 
	  	switch(obj.event){
	  			case 'add': add(); break;
	  			case 'modify': if(data.length === 0){ layer.msg('请选择一项数据'); } else if(data.length > 1){ layer.msg('只允许单条编辑')}else{modify()} break;
	  			case 'delete': if(data.length === 0){ layer.msg('请选择一行'); } else { del(data) } break;
	  	};
  });
  
  table.on('tool(operate)', function(obj){
	  var row = obj.data  ,layEvent = obj.event;
	  
	  $.post('/', {}, function(str){
		  layer.open({
			  type: 1,
			  content: str //注意，如果str是object，那么需要字符拼接。
		  });
	  });
	  
	  
	  /*var authorization = layer.open({ 
		  type: 1, title:"权限树",
		  resize : false,
		  btn: ['保存', '取消', ],
		  btnAlign: 'c',
		  area: ['350px', '600px'],
		  skin: 'demo-class',
		  content: $('#add_form'),
		  yes:function(index,layero){
			  var roleName = $("input[name='roleName']").val();
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/role/add_or_update', dataType : "json", data: {"roleName":roleName},
				  success: function(result) { 
					  if(result.data.status){
						  layer.msg('保存成功'); layer.close(add);  tableIns.reload({ page:{ curr: 1 }});
					  }else{
						  layer.msg(result.data.message);
					  }
				  }
			  });
		  }
	  });*/
	  
	  
	  
  });
  
  exports('base_role', function(){ }); 
  
  function add(){
	  
	  var add = layer.open({ 
		  type: 1, title:"新增角色",
		  resize : false,
		  btn: ['保存', '取消', ],
		  btnAlign: 'c',
		  area: ['350px', '200px'],
		  skin: 'demo-class',
		  content: $('#add_form'),
		  yes:function(index,layero){
			  var roleName = $("input[name='roleName']").val();
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/role/add_or_update', dataType : "json", data: {"roleName":roleName},
				  success: function(result) { 
					  if(result.data.status){
						  layer.msg('保存成功'); layer.close(add);  tableIns.reload({ page:{ curr: 1 }});
					  }else{
						  layer.msg(result.data.message);
					  }
				  }
			  });
		  }
	  });
  }
  
  function modify(){
	  
	  var roleName = checkRow()[0].roleName;
	  
	  var id = checkRow()[0].id;
	  
	  $("#modify_form input[name='roleName']").val(roleName);
	 
	  var modify = layer.open({ 
		  type: 1, title:"修改角色",
		  resize : false,
		  btn: [  '保存', '取消', ],
		  btnAlign: 'c',
		  area: ['350px', '200px'],
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
		  }
	  });
  }
  
  function del(data){
	  
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
	  
  }
});