package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.SchoolMapper;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentExample;
import com.xiaoshu.entity.StudentExample.Criteria;
import com.xiaoshu.entity.StudentVo;

@Service
public class StudentService {

	@Autowired
	StudentMapper sm;
	@Autowired
	SchoolMapper scm;

	// 新增
	public void addUser(Student t) throws Exception {
		sm.insert(t);
	};

	// 修改
	public void updateUser(Student t) throws Exception {
		sm.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		sm.deleteByPrimaryKey(id);
	};

	// 通过用户名判断是否存在，（新增时不能重名）
	public Student existUserWithUserName(String sname) throws Exception {
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andSnameEqualTo(sname);
		List<Student> userList = sm.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};

	public PageInfo<StudentVo> findUserPage(StudentVo studentVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StudentVo> studentList = sm.findAllStudent(studentVo);
		PageInfo<StudentVo> pageInfo = new PageInfo<StudentVo>(studentList);
		return pageInfo;
	}
	public List<StudentVo> exp(){
		return sm.findAllStudent(null);
	} 
	public List<School> findSchool(){
		return scm.selectAll();
	}
}
