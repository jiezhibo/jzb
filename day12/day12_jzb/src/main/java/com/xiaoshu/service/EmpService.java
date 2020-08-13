package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.EmpMapper;
import com.xiaoshu.entity.Emp;
import com.xiaoshu.vo.EmpVo;

@Service
public class EmpService {

	@Autowired
	EmpMapper empMapper;


	// 新增
	public void addEmp(Emp t) throws Exception {
		empMapper.insert(t);
	};

	// 修改
	public void updateEmp(Emp t) throws Exception {
		empMapper.updateByPrimaryKey(t);
	};

	// 删除
	public void deleteEmp(Integer eid) throws Exception {
		empMapper.deleteByPrimaryKey(eid);
	};


	public PageInfo<EmpVo> findAll(Emp emp, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<EmpVo> userList = empMapper.findAll(emp);
		PageInfo<EmpVo> pageInfo = new PageInfo<EmpVo>(userList);
		return pageInfo;
	}

	public List<EmpVo> find() {
		return empMapper.findAll(null);
	}

	public List<EmpVo> echartsDevice() {
		return empMapper.echartsDevice();
	}

 
}
