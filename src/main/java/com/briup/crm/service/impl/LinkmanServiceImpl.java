package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstLinkman;
import com.briup.crm.bean.CstLinkmanExample;
import com.briup.crm.dao.CstLinkmanMapper;
import com.briup.crm.service.ILinkmanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class LinkmanServiceImpl implements ILinkmanService{
	@Autowired
	private CstLinkmanMapper mapper;
	@Override
	public PageInfo<CstLinkman> pageQuery(long custId, int curPage, int size) {
		//设置当前页码,每页显示几条数据
		PageHelper.startPage(curPage,size);
		
		CstLinkmanExample example = new CstLinkmanExample();
		example.createCriteria().andLkmCustIdEqualTo(custId);
		//根据客户id查询对应联系人的信息
		List<CstLinkman> list = mapper.selectByExample(example );
		
		//创建pageInfo对象,将查询的结果传递过来
		PageInfo<CstLinkman> info = new PageInfo<CstLinkman>(list);
		
		return info;
		
	}
	@Override
	public void deleteById(long id) throws Exception {
		//根据id查询数据库,如果有对应的linkman说明id存在
		CstLinkman linkman = mapper.selectByPrimaryKey(id);
		if(linkman!=null) {
			mapper.deleteByPrimaryKey(id);
		}else {
			throw new Exception("该id不存在,请重新输入");
		}
	}
	@Override
	public void saveOrUpdate(CstLinkman linkman) {
		if(linkman.getLkmId()==null) {
			mapper.insertSelective(linkman);
		}else {
			mapper.updateByPrimaryKeySelective(linkman);
		}
	}
	@Override
	public CstLinkman findById(long id) {
		CstLinkman linkman = mapper.selectByPrimaryKey(id);
		return linkman;
	}

}
