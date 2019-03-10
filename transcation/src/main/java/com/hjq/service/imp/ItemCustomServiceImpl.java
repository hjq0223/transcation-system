package com.hjq.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjq.mapper.ItemCustomMapper;
import com.hjq.po.Item;
import com.hjq.service.ItemCustomService;

@Service
public class ItemCustomServiceImpl implements ItemCustomService {
	@Autowired
	private ItemCustomMapper itemCustomMapper; 
	
	@Override
	public int insertitem(Item item) {
		// TODO Auto-generated method stub
		return itemCustomMapper.insertitem(item);
	}
	@Override
	public List<Item> finditems(){
		return itemCustomMapper.finditems();
	}
	@Override
	public int buyitem(int id) {
		return itemCustomMapper.buyitem(id);
	}
	@Override
	public List<Item> selectuseritem(List<Integer> itemid) {
		// TODO Auto-generated method stub
		return itemCustomMapper.selectuseritem(itemid);
	}

}
