/**
 * session验证验证码
 */
$(document).ready(function(){
	 $('#change').css('cursor','pointer').click(function () {
		 $('#yzmjpeg').attr('src',$('#yzmjpeg').attr('src')+'?1');
	 });
	$("#submit").click(function(){
		var Data = {"yzm":$("#yzm").val()};
		$.ajax({
			type:"POST",
			contentType:"application/json;charset=UTF-8",
			data:JSON.stringify($("#loginsub").serializeObject()),
			url:"./codeverifod",
			dataType:"json",
			success:function(r_data){
				if(r_data["juge"] ==="success" && confirm(r_data["re"]+r_data["us"]+"\n回到主页？")){
					window.location.href = "indexredirect";
				}
				
			},
			error:function(){
				alert("failure");
			},
			
		})
	});
	
	/*
	 * 自动将表单作为json
	 */
	$.fn.serializeObject = function() {
	    var list = [];
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (!o[this.name] && o[this.name]!='') {
	            o[this.name] = this.value || '';
	        } else {
	            list.push(o);
	            o={};
	            o[this.name] = this.value || '';
	        }
	    });
	    list.push(o);
	    return list;
	};
})