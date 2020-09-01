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

import com.briup.crm.bean.CstActivity;
import com.briup.crm.service.IActivitiesService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/activities")
public class ActivitiesController {

	@Autowired
	private IActivitiesService activityService;
	
	@RequestMapping("/pageQuery/{curPage}/{atvCustId}")
	public String pageQuery( @PathVariable int curPage,@PathVariable Long atvCustId,HttpSession session,Model model) {
		PageInfo<CstActivity> activityInfo = activityService.pageQuery(atvCustId, curPage, 2);
		model.addAttribute("activityInfo", activityInfo);
		session.setAttribute("atvCustId", atvCustId);
		return "customer/activities";
	}
	
	@PostMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(CstActivity activity,HttpSession session) {
		Long atvCustId = (Long)session.getAttribute("atvCustId");
		activity.setAtvCustId(atvCustId);
//		activity.setAtvPlace(activity.getAtvPlace());
		activityService.saveOrUpdate(activity);
		
		return "保存成功";
		
	}
	@GetMapping("/deleteById")
	@ResponseBody
	public String deleteById(Long id) {
		String msg = "";
		try {
			activityService.deleteById(id);
			msg = "删除成功";
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}
	@GetMapping("/findById")
	@ResponseBody
	public CstActivity findById(long id) {
		CstActivity activity = activityService.findById(id);
		return activity;
	}
	
}
