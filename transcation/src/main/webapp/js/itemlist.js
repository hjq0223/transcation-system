/**
 * 已购商品列表ajax
 */
$(document).ready(function(){
	$.ajax({
		type : "GET",
		url:"./itemlistshow",
		dataType:"json",
		contentType:"application/json;charset=UTF-8",
		success:function(r_data){
		//	alert(typeof r_data);
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
					+'<div class="clearfix">'
					+'</div>'
					+'</div>'
					+'<div class="clearfix">'
					+'</div>'
					+'</div>';
				$(".w3ls").append(content);
			});
		},
		error:function(){
			alert("error");
		}
	});
})