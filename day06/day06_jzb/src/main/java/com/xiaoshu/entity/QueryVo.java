package com.xiaoshu.entity;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

public class QueryVo extends Person {
	
	@Column(name = "express_name")
    private String expressName1;

    private String status;

    @Column(name = "create_time")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    
    public String getExpressName1() {
		return expressName1;
	}

	public void setExpressName1(String expressName1) {
		this.expressName1 = expressName1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	private static final long serialVersionUID = 1L;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date start;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date end;

}
