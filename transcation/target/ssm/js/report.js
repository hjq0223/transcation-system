/**
 * 发表五物品report
 */
$(document).ready(function(){
	$("#report").click(report);
	function report(){
		var imgpath = $("#file").val();
		if(imgpath == ""){
			alert("文件不能为空");
			return false;
		}
//		var imglastindex = imgpath.substr(imgpath.lastIndexOf('.')+1);
//		if (imglastindex != "png" &&imglastindex != "jpg" && imgindexlast != "gif"){
//			alert("必须是jpg/png/gif");
//			return false;
//		}
		var name = $("#name").val();
		var des = $("#description").val();
		var imgurl = $("#file").val();
		var num = $("#num").val();
		var Data ={"name":name,"description":des,"file":imgurl,"num":num};
		$.ajax({
			type : "POST",
			url :"./reportajaxdata",
			data: JSON.stringify(Data),
			//data: JSON.stringify(Data),
			dataType:"json",
			contentType:"application/json;charset=UTF-8",
			success:function(r_data){
				alert(r_data["juge"]);
				$.ajaxFileUpload({
					type : "POST",
					url :"./reportajaximg",
					//data: JSON.stringify(Data),
					dataType:"json",
					fileElementId:"file",
					success:function(){
						alert("123");
					},
					error:function(){
						window.location.href = "./Index";
						//alert("error");
					},
				
				});
			},
			error:function(){
				alert("error");
			},
		});
	};
})