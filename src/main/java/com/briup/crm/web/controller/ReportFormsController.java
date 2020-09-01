package com.briup.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.extend.Contribution;
import com.briup.crm.service.IConstituteService;
import com.briup.crm.service.IContributionService;

@Controller
@RequestMapping("/reportForm")
public class ReportFormsController {
	@Autowired
	private IContributionService contributionService;
	
	@Autowired
	private IConstituteService constituteService;
	
	@RequestMapping("/toContribution")
	public String toContribution() {
		return "reportForms/customerContribution";
	}
	
	@RequestMapping("/getContribution")
	@ResponseBody
	public List<Contribution> getContribution(){
		List<Contribution> conlist = contributionService.findContribution();
		return conlist;
	}
	
	@GetMapping("/getContributionByRegion")
	@ResponseBody
	public List<Contribution> getContributionByRegion(String region){
		List<Contribution> conlist = 
				contributionService.findContributionByRegion(region);
		return conlist;
	}
	
	@RequestMapping("/toConstitute")
	public String toConstitute() {
		return "reportForms/customerConstitute";
	}
	
	
	@RequestMapping("/getConstitute")
	@ResponseBody
	public List<Contribution> getConstitute(int condition){
		List<Contribution> conslist = constituteService.findCustMarup(condition);
		return conslist;
	}
	
	
}
