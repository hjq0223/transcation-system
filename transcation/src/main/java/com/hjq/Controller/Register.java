package com.hjq.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjq.po.User;
import com.hjq.service.UserRegisterCustomService;

@Controller
public class Register {
	@Autowired
	UserRegisterCustomService UserRe;
	
	@RequestMapping(value = "/Registerverifod",method = RequestMethod.GET)
	public void Regist_get(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/html/register.html").forward(request, response);
	}
	
	//@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/Registerverifod",method = RequestMethod.POST)
	public Map<String,String> RegisterAjaxPost(@RequestBody List<User> paramap){
		for(User user:paramap) {
			System.out.println(user);
		}
		UserRe.UserRgist(paramap.get(0));
		String username = paramap.get(0).getUsername();
		Map<String,String> map = new HashMap<>();
		map.put("username", username);
		return map;
			//System.out.println(data);
		//String user = "Username";
//		Map<String, Object> UserInfo = paramap.get(0); 
//		User user = new User();
//		user.setId(1);
//		user.setUsername((String)UserInfo.get("Username"));
//		user.setPassword((String)UserInfo.get("Password"));
//		user.setPasswordCheck((String)UserInfo.get("PasswordCheck"));
//		user.setTel((String)UserInfo.get("Tel"));
//		user.setEmail((String)UserInfo.get("Email"));
//		UserRe.UserRgist(user);
		/*System.out.println(paramap.get(0).get("Username"));
		String u = (String) paramap.get(0).get("Username");
		Map<String,String> map= new HashMap<>();
		map.put("Username",u);*/
	}
}

