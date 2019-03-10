package com.hjq.Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hjq.po.LoginUser;
import com.hjq.po.UserItemList;
import com.hjq.service.UserItemListService;
import com.hjq.service.UserRegisterCustomService;

@Controller
public class CodeVerfoid {
	@Autowired
	UserRegisterCustomService ur;
	
	@Autowired
	UserItemListService useritem;
	
	Random random = new Random();
	@RequestMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/html/login.html").forward(request, response);
	}
	
	@RequestMapping("/code")
	public void createcode(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException {
		int width = 63;
		int height = 37;
		//禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		/*
		 * 生成缓冲区image
		 */
		BufferedImage image = new BufferedImage(width, height, 1);
		Graphics g = image.getGraphics();
		g.setColor(this.getrandcolor());
		g.setFont(new Font("Times New Roman",0,28));
		g.fillRect(0, 0, width, height);
		for(int i=0;i<40;i++){
	           g.setColor(this.getrandcolor());
	           int x = random.nextInt(width);
	           int y = random.nextInt(height);
	           int x1 = random.nextInt(12);
	           int y1 = random.nextInt(12);
	           g.drawLine(x, y, x + x1, y + y1);
	    }
		/*
		 * 生成code
		 */
		String strcode = "";
        for(int i=0;i<4;i++){
            String rand = String.valueOf(random.nextInt(10));
            strcode = strcode + rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand, 13*i+6, 28);
        }
        session.setAttribute("code",strcode);
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
        
		
	}
	public Color getrandcolor(){
		return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
	}
	
	@RequestMapping("/codeverifod")
	@ResponseBody
	public Map<String,String> codeverifod(@RequestBody List<Map<String,Object>>valuecode,HttpServletRequest request,HttpSession session) {
		String code = (String)request.getSession().getAttribute("code");
		String vcode = (String)valuecode.get(0).get("yzm");
		String username = (String)valuecode.get(0).get("username");
		String password = (String)valuecode.get(0).get("password");
		LoginUser loginuser = new LoginUser();
		loginuser.setUsername(username);
		loginuser.setPassword(password);
		LoginUser reuser = ur.loginverifod(loginuser);
		Map<String,String> map = new HashMap <>();
		if(vcode.equals(code) && reuser != null) {
			map.put("us",(String)reuser.getUsername());
			map.put("juge", "success");
			session.setAttribute("username",username);
			UserItemList ul = new UserItemList();
			ul.setUser(username);
			List<UserItemList> list = useritem.selectitemid(ul);
			List<Integer> itemlist = new ArrayList<>();
			for(UserItemList l:list) {
				itemlist.add(l.getItemnumber());	//获取用户购买商品id
			}
			session.setAttribute("itemlist", itemlist);
			System.out.println(session.getAttribute("itemlist"));
			map.put("re", "success:");
			//return "redirect:/Index";
		}
		else {
			map.put("re", "login failure!(username or password is not exists)");
			map.put("us", "sorry!");
		}
		return map;
	}
	
	@RequestMapping("/indexredirect")
	public String indexrediect(HttpServletRequest request,RedirectAttributes model1) {
		model1.addFlashAttribute("islogin", true);
		return "redirect:/Index";
		
	}
	
}
