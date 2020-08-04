package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.PcompanyMapper;
import com.xiaoshu.dao.PpersonMapper;
import com.xiaoshu.entity.Pcompany;
import com.xiaoshu.entity.Pperson;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class PersonService {

	@Autowired
	PpersonMapper pm;
	@Autowired
	PcompanyMapper cm;

	// 新增
	public void addPerson(Pperson t) throws Exception {
		pm.insert(t);
	};

	// 修改
	public void updatePerson(Pperson t) throws Exception {
		pm.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deletePerson(Integer id) throws Exception {
		pm.deleteByPrimaryKey(id);
	};

	public PageInfo<Pperson> findPersonPage(Pperson person, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Pperson> personList = pm.selectPerson(person);
		PageInfo<Pperson> pageInfo = new PageInfo<Pperson>(personList);
		return pageInfo;
	}

	public List<Pcompany> queryPcompany() {
		// TODO Auto-generated method stub
		return cm.selectAll();
	}


}
