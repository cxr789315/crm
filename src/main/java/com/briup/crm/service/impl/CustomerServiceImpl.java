package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstCustomer;
import com.briup.crm.bean.CstCustomerExample;
import com.briup.crm.bean.CstCustomerExample.Criteria;
import com.briup.crm.dao.CstCustomerMapper;
import com.briup.crm.service.ICustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	private CstCustomerMapper customerMapper;
	
	@Override
	public PageInfo<CstCustomer> pageQuery(int curPage, int size,String username,String region,String level) {
		//设置当前的页面和每页显示几条数据
		PageHelper.startPage(curPage, size);
		
		//查询所有的customer的信息
		CstCustomerExample example = 
				new CstCustomerExample();
		Criteria criteria = example.createCriteria();
		if(username!=null&&!"".equals(username)) {
			criteria.andCustNameLike("%"+username+"%");
		}
		if(region!=null&&!"".equals(region)) {
			criteria.andCustRegionEqualTo(region);
		}
		if(level!=null&&!"".equals(level)) {
			criteria.andCustLevelLabelEqualTo(level);
		}
		
		List<CstCustomer> custlist = 
				customerMapper.selectByExample(example);
		
		//将查询出customer的信息传递给分页对象
		PageInfo<CstCustomer> custinfo =
				new PageInfo<CstCustomer>(custlist);
		return custinfo;
	}

	@Override
	public void deleteById(long id) throws Exception {
		//根据id查询数据库，如果有对应的数据，说明id存在，否则id不存在
		CstCustomer customer = 
				customerMapper.selectByPrimaryKey(id);
		if(customer!=null) {
			customerMapper.deleteByPrimaryKey(id);
		}else {
			throw new Exception("该客户id不存在，请重新输入");
		}
	}

	@Override
	public void saveOrUpdate(CstCustomer customer) {
		if(customer.getCustId()==null) {
			customerMapper.insertSelective(customer);
		}else {
			customerMapper.
			updateByPrimaryKeySelective(customer);
		}
	}

	@Override
	public CstCustomer findById(long id) {
		CstCustomer customer = 
				customerMapper.selectByPrimaryKey(id);
		return customer;
	}
	
	//获取总的消费额
		public float getTotal() {
			float total = 0;
			CstCustomerExample example = new CstCustomerExample();
			List<CstCustomer> custlist = customerMapper.selectByExample(example);
			for(CstCustomer cust:custlist) {
				total += cust.getCustTurnover();
			}
			return total;
			
		}
		
		
		//获取每个区域的消费额
		public float getRegionTotal(String region) {
			float regionTotal = 0;
			CstCustomerExample example = new CstCustomerExample();
			example.createCriteria().andCustRegionEqualTo(region);
			List<CstCustomer> custlist = customerMapper.selectByExample(example);
			for(CstCustomer cust:custlist) {
				regionTotal += cust.getCustTurnover();
			}
			return regionTotal;
		}
		
		
		
		//获取每个区域的百分比
		@Override
		public float getRegionPercent(String region) {
			float percent = getRegionTotal(region)/getTotal();
			return percent*100;
		}

	}
