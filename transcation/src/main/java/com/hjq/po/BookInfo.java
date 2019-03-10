package com.hjq.po;

import java.util.Date;

public class BookInfo {
	int id;
	String btitle;
	Date bpuDate;
	int bread;
	int bcomment;
	boolean idDelete;
	String bprice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public Date getBpuDate() {
		return bpuDate;
	}
	public void setBpuDate(Date bpuDate) {
		this.bpuDate = bpuDate;
	}
	public int getBread() {
		return bread;
	}
	public void setBread(int bread) {
		this.bread = bread;
	}
	public int getBcomment() {
		return bcomment;
	}
	public void setBcomment(int bcomment) {
		this.bcomment = bcomment;
	}
	public boolean isIdDelete() {
		return idDelete;
	}
	public void setIdDelete(boolean idDelete) {
		this.idDelete = idDelete;
	}
	public String getBprice() {
		return bprice;
	}
	public void setBprice(String bprice) {
		this.bprice = bprice;
	}
	

}
