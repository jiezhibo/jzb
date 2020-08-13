package com.xiaoshu.vo;

import com.xiaoshu.entity.Emp;

public class EmpVo extends Emp{
	private String Bname;
	private Integer num;
	public EmpVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmpVo(String bname, Integer num) {
		super();
		Bname = bname;
		this.num = num;
	}
	public String getBname() {
		return Bname;
	}
	public void setBname(String bname) {
		Bname = bname;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "EmpVo [Bname=" + Bname + ", num=" + num + "]";
	}
	
	

	
}
