package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.briup.crm.bean.SysUser;
import com.briup.crm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/login")
	public String login(@RequestParam("username")String name,String password,HttpSession session) {
		String url = "";
		try {
			SysUser user = 
					userService.login(name, password);
			session.setAttribute("user", user);
			System.out.println("roleId: "+user.getUsrId());
			url = "index";
		} catch (Exception e) {
			session.setAttribute("msg", e.getMessage());
			url = "login";
		}
		return url;
	}
	
	
}
