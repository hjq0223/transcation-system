package com.hjq.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hjq.po.Item;
import com.hjq.po.User;
import com.hjq.po.UserItemList;
import com.hjq.service.ItemCustomService;
import com.hjq.service.UserItemListService;

@Controller
public class Index {
	@Autowired
	ItemCustomService itemCustomService;
	@Autowired
	UserItemListService useritemlist;
	
	@RequestMapping("/Index")
	public void index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/html/index.html").forward(request, response);
		
	}
	@ResponseBody
	@RequestMapping("/getUsername")
	public User getUsername(HttpSession session) {
		User user = new User();
		user.setUsername((String)session.getAttribute("username"));
		return user;
	}
	
	@RequestMapping("/report")
	public void report(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/html/report.html").forward(request, response);
	}
	
	@ResponseBody
	@RequestMapping(value = "/reportajaxdata",method = RequestMethod.POST)
	public Map<String,String> reportdata(@RequestBody Item paramap,HttpSession session){
		System.out.println(paramap.getFile());
		paramap.setUser((String)session.getAttribute("username"));
		String[] str = paramap.getFile().split("\\\\");
		System.out.println(str[str.length-1]);
		paramap.setFile(str[str.length-1]);
		itemCustomService.insertitem(paramap);
		Map<String,String> map = new HashMap<>();
		map.put("juge", "yes");
		return map;
	
	}
	
	@ResponseBody
	@RequestMapping(value = "/reportajaximg",method = RequestMethod.POST)
	public Map<String,String> reportajax(HttpServletRequest request,HttpServletResponse response,HttpSession session,@RequestParam(value = "file") MultipartFile file) throws IllegalStateException, IOException {
		String picpath = "D:\\transcation\\img";
		System.out.println("real File:" + picpath);
		String filename = file.getOriginalFilename();
		System.out.println("filename:" + filename);
		File targetfile = new File(picpath,filename);
		if(!targetfile.exists()) {
			targetfile.mkdirs();
		}
		file.transferTo(targetfile);
		Map<String,String> res = new HashMap<>();
		res.put("filename", filename);
		return res;
	}
	@ResponseBody
	@RequestMapping(value = "/showitems", method = RequestMethod.GET)
	public List<Map<String,String>> showitems(HttpSession session) {
		List<Item> list = itemCustomService.finditems();
		List<Map<String,String>> relist = new ArrayList<>();
		for(Item item : list) {
			Map<String,String> map = new HashMap<>();
			map.put("name", item.getName());
			map.put("des", item.getDescription());
			map.put("num", String.valueOf(item.getNum()));
			map.put("user", item.getUser());
			map.put("id", String.valueOf(item.getId()));
			map.put("img", item.getFile());
			relist.add(map);
		}
		return relist;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/buyitem", method = RequestMethod.POST)
	public Map<String,String> buyitem(@RequestBody Map<String,Object> map,HttpSession session) {
		int id = Integer.parseInt((String) map.get("id"));
		itemCustomService.buyitem(id);
		String user = (String)session.getAttribute("username");
		UserItemList ul = new UserItemList();
		ul.setItemnumber(id);
		ul.setUser(user);
		useritemlist.insertuseritem(ul);
		List<Integer> itemlist = (List<Integer>) session.getAttribute("itemlist");
		itemlist.add(id);
		session.setAttribute("itemlist", itemlist);
		Map<String,String> m = new HashMap<>();
		m.put("juge", "success");
		return m;
	}
	
	@RequestMapping("/itemlist")
	public void itemlistshow(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/html/itemlist.html").forward(request, response);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/itemlistshow", method = RequestMethod.GET)
	public List<Map<String,String>> itemlist(HttpSession session){
		List<Integer> itemid = (List<Integer>) session.getAttribute("itemlist");
		for(Integer i :itemid) {
			System.out.println(i);
		}
		List<Item> itemlist= itemCustomService.selectuseritem(itemid);
		List<Map<String,String>> list = new ArrayList<>();
		for(Item item:itemlist) {
			System.out.println(item);
			Map<String,String> map = new HashMap<>();
			map.put("name", item.getName());
			map.put("des", item.getDescription());
			map.put("img", item.getFile());
			map.put("user", item.getUser());
			list.add(map);
		}
		return list;
	}
}