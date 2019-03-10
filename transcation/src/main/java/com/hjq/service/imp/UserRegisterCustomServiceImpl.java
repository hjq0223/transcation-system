package com.hjq.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjq.mapper.UserRegisterCustomMapper;
import com.hjq.po.LoginUser;
import com.hjq.po.User;
import com.hjq.service.UserRegisterCustomService;

@Service
public class UserRegisterCustomServiceImpl implements UserRegisterCustomService {
	@Autowired
	private UserRegisterCustomMapper userRegisterCustomMapper;
	
	@Override
	public int UserRgist(User user) {
		return userRegisterCustomMapper.UserRegist(user);
	}
	@Override
	public LoginUser loginverifod(LoginUser lu) {
		return userRegisterCustomMapper.loginverifod(lu);	
	}
}
