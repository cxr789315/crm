package com.briup.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.extend.Contribution;
import com.briup.crm.dao.extend.CustomerExtendMapper;
import com.briup.crm.service.IContributionService;
import com.briup.crm.service.ICustomerService;

@Service
public class ContributionServiceImpl implements IContributionService{
	@Autowired
	private CustomerExtendMapper custExtendMapper;
	@Autowired
	private ICustomerService customerService;
	
	@Override
	public List<Contribution> findContribution() {
		//用于保存Contribution
		List<Contribution> conlist = new ArrayList<Contribution>();
		
		//获取每个区域对应的Contribution
			//获取每个区域
		List<String> regionlist = 
				custExtendMapper.selectRegion();
		for(String region:regionlist) {
			float y = customerService.getRegionPercent(region);
			Contribution con = new Contribution();
			con.setName(region);
			con.setY(y);
			conlist.add(con);
		}
		return conlist;
	}
	
	public List<Contribution> findContributionByRegion(String region){
		List<Contribution> conlist = new ArrayList<Contribution>();
		Contribution con = new Contribution();
		con.setName(region);
		float y = customerService.getRegionPercent(region);
		con.setY(y);
		conlist.add(con);
		return conlist;
	}

}
