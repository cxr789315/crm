 package com.briup.crm.service;

import com.briup.crm.bean.CstActivity;
import com.github.pagehelper.PageInfo;

public interface IActivitiesService {
	public PageInfo<CstActivity> pageQuery(long custId,int curPage,int size);
	
	public void deleteById(long id);
	
	public void saveOrUpdate(CstActivity activity);
	
	public CstActivity findById(long id);
	
	
}
