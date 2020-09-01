package com.briup.crm.service;

import com.briup.crm.bean.CstLinkman;
import com.github.pagehelper.PageInfo;

public interface ILinkmanService {
	
	public PageInfo<CstLinkman> pageQuery(long custId,int curPage,int size);
	public void deleteById(long id) throws Exception;
	public void saveOrUpdate(CstLinkman linkman);
	public CstLinkman findById(long id);
}
