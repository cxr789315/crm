package com.briup.crm.service;

import java.util.List;

import com.briup.crm.bean.extend.Contribution;

public interface IConstituteService {
	public List<Contribution> findCustMarup(int condition);
}
