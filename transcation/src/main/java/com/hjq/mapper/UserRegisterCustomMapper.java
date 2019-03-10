package com.hjq.mapper;

import com.hjq.po.LoginUser;
import com.hjq.po.User;

public interface UserRegisterCustomMapper {
	public int UserRegist(User user);
	public LoginUser loginverifod(LoginUser lu);
}
