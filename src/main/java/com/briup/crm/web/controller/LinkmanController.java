package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstLinkman;
import com.briup.crm.service.ILinkmanService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/linkman")
public class LinkmanController {
	@Autowired
	private ILinkmanService linkmanService;
	
	@RequestMapping("/pageQuery/{page}/{custId}")
	public String pageQuery(@PathVariable("page") int curPage,@PathVariable long custId,HttpSession session,Model model) {
		PageInfo<CstLinkman> linkmanInfo = 
				linkmanService.pageQuery(custId, curPage, 2);
		model.addAttribute("linkmanInfo", linkmanInfo);
		session.setAttribute("custId", custId);
		return "customer/linkman";
	}
	
	
	@PostMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(CstLinkman linkman,HttpSession session) {
		Long custId = (Long) session.getAttribute("custId");
		linkman.setLkmCustId(custId);
		linkmanService.saveOrUpdate(linkman);
		String msg = "保存成功";
		return msg;
	}
	
	
	@GetMapping("/deleteById")
	@ResponseBody
	public String deleteById(long id) {
		String msg="";
		try {
			linkmanService.deleteById(id);
			msg="删除成功";
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}

	@GetMapping("/findById")
	@ResponseBody
	public CstLinkman findById(long id) {
		CstLinkman linkman = linkmanService.findById(id);
		return linkman;
	}
	
	
}










