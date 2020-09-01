package com.briup.crm.service;

import java.util.List;

import com.briup.crm.bean.extend.Contribution;

public interface IContributionService {
	public List<Contribution> findContribution();
	public List<Contribution> findContributionByRegion(String region);
}
