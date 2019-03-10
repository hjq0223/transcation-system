package com.hjq.mapper;

import java.util.List;

import com.hjq.po.Item;

public interface ItemCustomMapper {
	public int insertitem(Item item);
	public List<Item> finditems();
	public int buyitem(int id);
	public List<Item> selectuseritem(List<Integer> itemid); //获取已购商品列表
}
