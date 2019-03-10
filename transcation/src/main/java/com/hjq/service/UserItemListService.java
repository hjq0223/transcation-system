package com.hjq.service;

import java.util.List;

import com.hjq.po.UserItemList;

public interface UserItemListService {
	public int insertuseritem(UserItemList ul);
	//查找登陆用户购买的商品写入session
	public List<UserItemList> selectitemid(UserItemList ul);
}
