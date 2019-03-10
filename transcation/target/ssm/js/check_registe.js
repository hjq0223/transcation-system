$(document).ready(function(){
	$(".sucess").hide();
	$(".info").hide();
		/*var info = ["用户名错误","两次密码输入不一致","手机号输入格式错误","密码格式错误","邮箱输入错误"];
		//var ss = new Array(0,1,2,3,4);*/
		//email
	var verifod = [];
		function check_email(value){
			var reg=/^\w{3,}@\w+(\.\w+)+$/;
				if(value.length == 0 || !reg.test(value)){
					verifod.push(4);
					return false;
				}
				return true;
		}
		function check_repsd(value){
			if(value != $("#psd").val()){
				verifod.push(1);
					return false;
			}
			return true;
		}
		function check_mobilephone(value){
			var reg = /^(\+\d{2,3}\-)?\d{11}$/;
			if(!reg.test(value)){
			//alert("手机号输入格式错错误"")
				verifod.push(2);
					return false;
			}
				return true;
		}
		function check_user(value){
			if (value.length > 20 || value.length <6){
							//alert("用户名不合法，6-20");
				verifod.push(0);
				return false;			
			}
			else{
				return true;
			}
		}
		function check_psd(value){
			var reg = /^(\w)* $/;
			if(value.length > 20 ||value.length < 6){
			//alert("密码格式错误！);
				return false;
			}
			return true;
		}
		//检查用户名
		var show_f = function(){
			if(!check_user($("#user").val())){
				$(".info").eq(0).show();
				$(".success").eq(0).hide();
				return false;
			}
			else{
				$(".info").eq(0).hide();
				$(".sucess").eq(0).show();
				return true;
			}
		}
		//检查用户名、密码
		var show_s = function(){
			show_f();
			if(!check_psd($("#psd").val())){
				$(".info").eq(1).show();
				$(".sucess").eq(1).hide();
				return false;
			}
			else{
				$(".info").eq(1).hide();
				$(".sucess").eq(1).show();
				return true;
			}
		}
		//检查用户名、密码、密码确认
		var show_t = function(){
			show_s();
			if(!check_repsd($("#psd_check").val())){
				$(".info").eq(2).show();
				$(".sucess").eq(2).hide();
				return false;
			}
			else{
				$(".info").eq(2).hide();
				$(".sucess").eq(2).show();
				return true;
			}
		}
		//检查用户名、密码、密码确认、tel
		var show_fo = function(){
			show_t();
			if(!check_mobilephone($("#tel").val())){
				$(".info").eq(3).show();
				$(".sucess").eq(3).hide();
				return false;
			}
			else{
				$(".info").eq(3).hide();
				$(".sucess").eq(3).show();
				return true;
			}
		}
		//检查用户名、密码、密码确认、tel、email
		var show_fiv = function(){
			show_fo();
			if(!check_email($("#email").val())){
				$(".info").eq(4).show();
				$(".success").eq(4).hide();
				return false;
			}
			else if(show_fo()){
				$(".info").eq(4).hide();
				$(".sucess").eq(4).show();
				return true;
			}
		}
		function sub(){
			if(show_fiv()){
				var Data = {"juge": "ckj"};
				$.ajax({
					type: "POST",
					contentType:'application/json;charset=UTF-8',
					data: $("#submit_regist").serialize(),
					url: "./Register/verifod",
					dataType :'json',
					success: function(r_data){
					alert(r_data);
					},
					error: function(){
						alert("error!");
					}
				});
			}
				else{
					alert("输入不合法");
				}
			}
		
		$("#psd").click(show_f);
		$("#repsd").click(show_s);
		$("#tel").click(show_t);
		$("#email").click(show_fo);
		$("#sub").click(function(){
			//alert($("#submit_regist").serialize());
			if(show_fiv()){
				var Data = {"juge": "ckj"};
				$.ajax({
					type: "POST",
					contentType:'application/json;charset=UTF-8',
					data: JSON.stringify($("#submit_regist").serializeObject ()),
					url: "./Registerverifod",
					dataType :'json',
					success: function(r_data){ 
						alert(typeof(r_data));
						if(confirm("welcome"+r_data["username"]+"\n请登陆！")){
							window.location.href = "./login";
						}
					},
					error: function(){
						alert("注册失败！");
					}
				});
			} 
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