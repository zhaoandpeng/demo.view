layui.define(['layer', 'element', 'jquery', 'table', 'form'], function(exports){
  
	
  var layer = layui.layer, element = layui.element, $ = layui.jquery,  table = layui.table, form = layui.form ;
  
  var roleName;
  
  table.render({
	    elem: '#demo',
	    skin:'row',
	    even:true,
	    height: 312,
	    toolbar:'#toolbar',
	    url: '/api/v1/sys/role/index/data',
	    page: true ,
	    cols: [[ 
	    	{checkbox: true,fixed: 'left'},
	    	{field: 'roleName', title: '角色名称', width:150, align: 'center'}
	    ]],
	    done : function(){
	        
	       $('th').css({'background-color': '#009688', 'color': '#fff','font-weight':'bold'})
	    }
  });
  
  table.on('toolbar(operate)', function(obj){
	  
	  	var checkStatus = table.checkStatus(obj.config.id) ,data = checkStatus.data; 
	  	switch(obj.event){
	  			case 'add': add(); break;
	  			case 'update': if(data.length === 0){ layer.msg('请选择一项数据'); } else if(data.length > 1){ layer.msg('只允许单条编辑')} break;
	  			case 'delete': if(data.length === 0){ layer.msg('请选择一行'); } else { layer.msg('删除'); } break;
	  	};
  });
  
  exports('base_role', function(){ }); 
  
  function add(){
	  
	  layer.open({ 
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
					  if(result.status){
						  layer.msg('保存成功');
					  }else{
						  layer.msg(result.message);
					  }
				  }
			  });
		  },
		  btn2:function(index, layero){
			   
		  }
	  });
  }
});