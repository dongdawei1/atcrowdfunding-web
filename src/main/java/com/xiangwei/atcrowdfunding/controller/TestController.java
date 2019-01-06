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
@RequestMapping("/test")  //����test·���ľ͵��������
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
	
	
	@ResponseBody  //���صĲ���ҳ�棬��һ������ֻ��ת��Ϊ�ַ�����Ȼ��ӳ���jsp
	@RequestMapping("/json")
	public Object json() {
		Map map = new HashMap();
		map.put("username", "zhangsan");
		return map;
	}
	
}
