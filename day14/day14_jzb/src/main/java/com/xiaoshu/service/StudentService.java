package com.xiaoshu.service;

import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.TeacherMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentVo;
import com.xiaoshu.entity.Teacher;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class StudentService {

	@Autowired
	StudentMapper sm;
	@Autowired
	TeacherMapper tm;
	@Autowired
	Destination zyssm;
	@Autowired
	JmsTemplate jt;


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

	
	//查询
	public PageInfo<StudentVo> findUserPage(StudentVo studentVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StudentVo> userList = sm.findAllStudent(studentVo);
		PageInfo<StudentVo> pageInfo = new PageInfo<StudentVo>(userList);
		return pageInfo;
	}
	//查询主表
	public List<Teacher> findTeacherAll(){
		return tm.selectAll();
	}
	//添加主表
	public void insertTeacherAll(final Teacher teacher){
		Integer id=tm.insertById(teacher);
		jt.send(zyssm, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				String json = JSONObject.toJSONString(teacher);
				return session.createTextMessage(json);
			}
		});
	}
	public List<StudentVo> getTj(){
		return sm.getTj();
	}
}
