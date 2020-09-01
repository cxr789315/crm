package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SysUser;
import com.briup.crm.bean.SysUserExample;
import com.briup.crm.dao.SysUserMapper;
import com.briup.crm.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private SysUserMapper mapper;
	
	@Override
	public SysUser login(String name, String password) throws Exception {
		//判断用户名是否正确
		//根据name查询数据库,如果查询到一个user,说明用户名输入正确
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsrNameEqualTo(name);
		List<SysUser> list = mapper.selectByExample(example);
		if(list.size()>0) {
			SysUser user = list.get(0);
			//判断密码是否正确
			if(user.getUsrPassword().equals(password)) {
				return user;
			}else {
				throw new Exception("密码输入错误,请重新输入");
			}
		}else {
			throw new Exception("该用户名不存在,请重新输入");
		}
	}
	
}
