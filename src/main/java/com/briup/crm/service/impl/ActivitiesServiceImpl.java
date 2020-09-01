package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstActivity;
import com.briup.crm.bean.CstActivityExample;
import com.briup.crm.dao.CstActivityMapper;
import com.briup.crm.service.IActivitiesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ActivitiesServiceImpl implements IActivitiesService{

	@Autowired
	private CstActivityMapper mapper;
	

	@Override
	public PageInfo<CstActivity> pageQuery(long custId, int curPage, int size) {
		//设置当前页码,每页显示几条数据
		PageHelper.startPage(curPage, size);
		CstActivityExample example = new CstActivityExample();
		example.createCriteria().andAtvCustIdEqualTo(custId);
		//根据id查看最近交往记录
		List<CstActivity> list = mapper.selectByExample(example);
		//创建pageInfo对象,将查询的结果传递过来
		PageInfo<CstActivity> info = new PageInfo<>(list);
		return info;
	}


	@Override
	public void deleteById(long id) {
		mapper.deleteByPrimaryKey(id);
	}


	@Override
	public void saveOrUpdate(CstActivity activity) {
		if(activity.getAtvId()==null) {
			mapper.insertSelective(activity);
		}else {
			mapper.updateByPrimaryKeySelective(activity);
		}
	}


	@Override
	public CstActivity findById(long id) {
		CstActivity activity = mapper.selectByPrimaryKey(id);
		return activity;
	}




}
