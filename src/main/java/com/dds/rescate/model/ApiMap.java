package com.dds.rescate.model;

import java.util.List;

public class ApiMap {

	private int total;
	private String offset;
	private List<Hogar> hogares;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public List<Hogar> getHogares() {
		return hogares;
	}
	public void setHogares(List<Hogar> hogares) {
		this.hogares = hogares;
	}
	
}
