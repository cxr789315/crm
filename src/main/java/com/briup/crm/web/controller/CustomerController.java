package com.briup.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstCustomer;
import com.briup.crm.service.ICustomerService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;

	@RequestMapping("/pageQuery/{curPage}")
	public String pageQuery(@PathVariable int curPage, Model model, String username, String region, String level) {
		System.out.println("username:" + username);
		System.out.println("region:" + region);
		System.out.println("level:" + level);
		PageInfo<CstCustomer> custinfo = customerService.pageQuery(curPage, 5, username, region, level);
		model.addAttribute("custinfo", custinfo);
		return "customer/customer";
	}

	
	@RequestMapping("/deleteById/{id}")
	@ResponseBody
	public String deleteById(@PathVariable long id) {
		String msg = "";
		try {
			customerService.deleteById(id);
			msg = "删除成功";
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	@PostMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(CstCustomer customer) {
		customerService.saveOrUpdate(customer);
		return "保存成功";
	}
	
	@RequestMapping("/findById/{id}")
	@ResponseBody
	public CstCustomer findById(@PathVariable long id) {
		CstCustomer customer = customerService.findById(id);
		return customer;
	}
}



















