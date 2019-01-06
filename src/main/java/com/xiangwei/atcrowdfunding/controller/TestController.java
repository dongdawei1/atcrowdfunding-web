package com.xiangwei.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangwei.atcrowdfunding.bean.User;
import com.xiangwei.atcrowdfunding.service.UserService;

@Controller
@RequestMapping("/test")  //请求test路径的就到这个类中
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/queryAll")
	public Object queryAll() {
		List<User> users = userService.queryAll();
		return users;
	}
	
	
	@ResponseBody  //返回的不是页面，是一个对象，只能转换为字符串，然后映射成jsp
	@RequestMapping("/json")
	public Object json() {
		Map map = new HashMap();
		map.put("username", "zhangsan");
		return map;
	}
	
}
