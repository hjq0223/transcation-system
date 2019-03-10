package com.hjq.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjq.mapper.UserItemListMapper;
import com.hjq.po.UserItemList;
import com.hjq.service.UserItemListService;

@Service
public class UserItemListServiceImpl implements UserItemListService {
	
	@Autowired
	UserItemListMapper useritemlist;
	
	@Override
	public int insertuseritem(UserItemList ul) {
		// TODO Auto-generated method stub
		return useritemlist.insertuseritem(ul);
	}

	@Override
	public List<UserItemList> selectitemid(UserItemList ul) {
		// TODO Auto-generated method stub
		return useritemlist.selectitemid(ul);
	}

}
