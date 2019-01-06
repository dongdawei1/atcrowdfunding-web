package com.xiangwei.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vaadin.data.util.filter.And;
import com.xiangwei.atcrowdfunding.bean.AJAXResult;
import com.xiangwei.atcrowdfunding.bean.User;
import com.xiangwei.atcrowdfunding.service.UserService;

@Controller   //����ҳ����ת
public class DispatcherController {
	

	@Autowired
	private UserService userService;
	
	//��½ע��ҳ
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	//ִ�е�½��ȡ���ύ����,���������ͱ��ύ��һ��
	@RequestMapping("/doLogin")
	public String doLogin(User user, Model model){
		//��ȡ��½��
	String loginacct = user.getLoginacct();
	
	String userpswd = user.getUserpswd();
	System.out.println("DispatcherController.doLogin() "+loginacct);	
		// �������ַ������մ���ı��뷽ʽת��Ϊԭʼ���ֽ�������
		//byte[] bs = loginacct.getBytes("ISO8859-1");
		
		// ��ԭʼ���ֽ������а�����ȷ�ı���ת��Ϊ��ȷ�����ּ��ɡ�
		//loginacct = new String(bs, "UTF-8");
		
		
		// 1) ��ȡ������
		// 1-1) HttpServletRequest
		// 1-2) �ڷ��������б������ӱ���Ӧ�Ĳ�����������ͬ
		// 1-3) ���ǽ������ݷ�װΪʵ�������
		
		// 2) ��ѯ�û���Ϣ
		User dbUser = userService.query4Login(user);
			
		// 3) �ж��û���Ϣ�Ƿ����
		if ( dbUser != null  && dbUser.getUserpswd().equals(userpswd)) {
			// ��½�ɹ�����ת����ҳ��
			return "main";
		} else {
			// ��½ʧ�ܣ���ת�ص���½ҳ�棬��ʾ������Ϣ
			String errorMsg = "��½�˺Ż����벻��ȷ������������";
			model.addAttribute("errorMsg", errorMsg);
			return "redirect:login";//�ض�������½ҳ
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/doAJAXLogin")
	public Object doAJAXLogin(User user, HttpSession session) {
		//��ȡ��½��
	String loginacct = user.getLoginacct();
	
	String userpswd = user.getUserpswd();
	
		AJAXResult result = new AJAXResult();
		System.out.println("DispatcherController.doLogin() "+loginacct);
		User dbUser = userService.query4Login(user);
		if ( dbUser != null  && dbUser.getUserpswd().equals(userpswd)) {
//			session.setAttribute("loginUser", dbUser);
//			
//			// ��ȡ�û�Ȩ����Ϣ
//			List<Permission> permissions = permissionService.queryPermissionsByUser(dbUser);
//			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
//			Permission root = null;
//			Set<String> uriSet = new HashSet<String>();
//			for ( Permission permission : permissions ) {
//				permissionMap.put(permission.getId(), permission);
//				if ( permission.getUrl() != null && !"".equals(permission.getUrl()) ) {
//					uriSet.add(session.getServletContext().getContextPath() + permission.getUrl());
//				}
//			}
//			session.setAttribute("authUriSet", uriSet);
//			for ( Permission permission : permissions ) {
//				Permission child = permission;
//				if ( child.getPid() == 0 ) {
//					root = permission;
//				} else {
//					Permission parent = permissionMap.get(child.getPid());
//					parent.getChildren().add(child);
//				}
//			}
//			session.setAttribute("rootPermission", root);
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		
		return result;
	}
	
	//��½�ɹ�����ҳ��
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
}
