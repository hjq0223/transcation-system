/**
 * index加载ajax
 */
$(document).ready(function(){
	$.ajax({
		type : "GET",
		url : "./showitems",
		dataType:"json",
		contentType:"application/json;charset=UTF-8",
		success:function(r_data){
			//alert("success");
			var num = 0;
			$.each(r_data,function(index,value){
				var content = '<div class="wthree">'
					+'<div class="col-md-6 wthree-left wow fadeInDown"  data-wow-duration=".8s" data-wow-delay=".2s">'
					+'<div class="tch-img">'
					+'<img src=" ../reportimg/'+value.img+'" class="img-responsive" alt="" height = "100" width = "250">'
					+'</div>'
					+'</div>'
					+'<div class="col-md-6 wthree-right wow fadeInDown"  data-wow-duration=".8s" data-wow-delay=".2s">'
					+'<h3>'
					+value.name
					+'</h3>'
					+'<h6>BY '
					+'<a href="#">'
					+value.user
					+'</a>'
					+'</h6>'
					+'<p>'
					+value.des
					+'</p>'
					+'<div class="bht1">'
					+'<input type = "button" id = "'+value.id+'" class = "buttonbuy" value = "购买">'
					+'</a>'
					+'</div>'
					+'<div class="soci">'
					+'<ul>'
					+'<li class="hvr-rectangle-out">'
					+'<span>'
					+'数量：'
					+'</span>'
					+'</li>'
					+'<li class="hvr-rectangle-out">'
					+'<span>'
					+value.num
					+'</span>'
					+'</li>'
					+'</ul>'
					+'</div>'
					+'<div class="clearfix">'
					+'</div>'
					+'</div>'
					+'<div class="clearfix">'
					+'</div>'
					+'</div>';
				$(".w3ls").append(content);
				
			});
			$(":input[class = 'buttonbuy']").click(function(){
				if(confirm("确认购买？")){
					var id = this.id;
					var data = {'id':id};
					$.ajax({
						type : "POST",
						url : "./buyitem",
						data:JSON.stringify(data),
						dataType:'json',
						contentType : "application/json;charset=UTF-8",
						success:function(r_data){
							alert("购买成功!");
							window.location.href = "./Index";
						},
						error:function(){
							alert("购买失败！");
						},
						
					})
				}
				else{
					return false;
				}
			})
			$.ajax({
				type:"post",
				url: "getUsername",
				success: function(Data) {
					//alert(Data);
					//$("#loginuser").html("欢迎: "+Data.username);
					var obj = eval('('+Data+')')
					//alert(obj);
					//alert(obj.Username);
					if(obj.Username)
						$("#loginuser").html("欢迎: "+obj.Username);
				},
				error: function()  {
					alert("error");
				}
			});
		},
		error:function(){
			alert("failure");
		}
	})
})