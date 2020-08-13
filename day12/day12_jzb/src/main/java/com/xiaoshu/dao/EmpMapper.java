package com.xiaoshu.dao;

import java.util.List;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Emp;
import com.xiaoshu.vo.EmpVo;

public interface EmpMapper extends BaseMapper<Emp> {

	

	List<EmpVo> findAll(Emp emp);

	List<EmpVo> echartsDevice();

	
}