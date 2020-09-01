package com.briup.crm.dao.extend;

import java.util.List;

public interface CustomerExtendMapper {
	public List<String> selectRegion();
	
	public List<String> selectLevel();
	
	public List<Integer> selectCredit();
	
	public List<Integer> selectSatisfy();
}
