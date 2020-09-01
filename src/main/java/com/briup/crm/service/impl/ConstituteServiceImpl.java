package com.briup.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstCustomer;
import com.briup.crm.bean.CstCustomerExample;
import com.briup.crm.bean.extend.Contribution;
import com.briup.crm.dao.CstCustomerMapper;
import com.briup.crm.dao.extend.CustomerExtendMapper;
import com.briup.crm.service.IConstituteService;
import com.briup.crm.service.IContributionService;

@Service
public class ConstituteServiceImpl implements IConstituteService{
	@Autowired
	private CustomerExtendMapper custExtendMapper;
	
	@Autowired
	private CstCustomerMapper custMapper;
	
	@Override
	public List<Contribution> findCustMarup(int condition) {
		//用于保存contribution
		List<Contribution> conlist = new ArrayList<Contribution>();
		//获取总人数
		int count = custMapper.selectByExample(new CstCustomerExample()).size();
		if(condition==0) {//按等级
			//获得所有的等级
			List<String> levlelist = custExtendMapper.selectLevel();
			for(String level:levlelist) {
				//按照客户等级查询客户
				CstCustomerExample example = new CstCustomerExample();
				example.createCriteria().andCustLevelLabelEqualTo(level);
				List<CstCustomer> custlist = custMapper.selectByExample(example);
				//获得每个等级对应的人数
				int size = custlist.size();
				Contribution con = new Contribution();
				con.setName(level);
				float y = (float)size/count;
				con.setY(y*100);
				conlist.add(con);
			}
		}
		if(condition==1) {//按信用度
			//获取不同的信用度
			List<Integer> creditlist = custExtendMapper.selectCredit();
			for(Integer credit:creditlist) {
				//根据不同信誉度查询customer
				CstCustomerExample example = new CstCustomerExample();
				example.createCriteria().andCustCreditEqualTo(credit);
				List<CstCustomer> custlist = custMapper.selectByExample(example);
				//获取每个信誉度的人数
				int size = custlist.size();
				float y = (float)size/count;
				Contribution con = new Contribution();
				con.setName(credit+"");
				con.setY(y*100);
				conlist.add(con);
			}
		}
		if(condition==2) {//按满意度
			//获取所有的满意度
			List<Integer> satisfylist = custExtendMapper.selectSatisfy();
			for(Integer satisfy:satisfylist) {
				//按照满意度查询Customer
				CstCustomerExample example = new CstCustomerExample();
				example.createCriteria().andCustSatisfyEqualTo(satisfy);
				List<CstCustomer> custlist = custMapper.selectByExample(example);
				int size = custlist.size();
				float y = (float)size/count;
				Contribution con = new Contribution();
				con.setName(satisfy+"");
				con.setY(y*100);
				conlist.add(con);
			}
		}
		
		return conlist;
	}

}
