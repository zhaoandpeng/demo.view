layui.define(['layer', 'sliderVerify', 'form'], function(exports){
  
	var sliderVerify = layui.sliderVerify;
	
	var slider = sliderVerify.render({
	
		elem: '#slider',
		
		onOk: function(){//当验证通过回调
			
			layer.msg("滑块验证通过");
		}
	})
  
	var form = layui.form;
	  
	form.on('submit(form-login)', function(data){
		
//	    layer.msg(JSON.stringify(data.field)); 校验用户名以及密码的合法性
	    
//		login(data)
	    
//		return false;
	});
	
}).extend({sliderVerify: 'modules/sliderVerify/sliderVerify'})