package com.hjq.service;

import com.hjq.po.LoginUser;
import com.hjq.po.User;
public interface UserRegisterCustomService {
	public int UserRgist(User user);
	public LoginUser loginverifod(LoginUser lu);

}
