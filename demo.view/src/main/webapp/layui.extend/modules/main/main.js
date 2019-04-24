layui.define(['layer', 'element', 'jquery'], function(exports){
  
	
  var layer = layui.layer, element = layui.element, $ = layui.jquery, data = null;
  
  function append_html(obj){
	  
	  return "<a href=\"javascript:;\" onclick=\"addTab('" + obj.menuName + "','" + obj.menuUrl + "')\" >" + obj.menuName + "</a>";
  }
  
  function checkLastItem(arr, i) {
		
	  return arr.length == (i + 1);
  }
  
  function addTab(a,b){
	  
	  
  }

  exports('main', function(){
	  
	  $.ajax({
		  
		  type: 'POST',  url: '/main/data', dataType : "json",
		 
		  success: function(result) { 
			  
			  var html = "";
			  
			  if(result.roleResources.length>0){
				  
				  var res = result.roleResources;
				  
				  for(var i = 0; i < res.length; i++) {
						
					  	var strli = "<li class=\"layui-nav-item lay-unselect \" >";
						
						if (res[i].menuUrl =='#'){
							
							strli = strli + "<a href=\"javascript:;\">" + res[i].menuName + "</a>";
						}else{
							
							strli = strli + append_html(res[i]);
						}
						if(res[i].pid == "0" && !checkLastItem(res, i) && res[i + 1].pid != "0") {
							
							strli = strli + "<dl class=\"layui-nav-child\" >";
							
							for(; !checkLastItem(res, i) && res[i + 1].pid != "0"; i++) {
								
								strli = strli + "<dd>"+append_html(res[i+1])+"</dd>";
							}
							
							strli = strli + "</dl>";
						}
						strli = strli + "</li>";
						
						html = html + strli;
				  }
				  
				  layui.jquery("#memus").html(html);
				  
				  layui.element.init();
			  }
		  }
	  });
  }); 

});