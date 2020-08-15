package com.xiaoshu.service;

import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.MajorMapper;
import com.xiaoshu.dao.StuMapper;
import com.xiaoshu.entity.Major;
import com.xiaoshu.entity.Stu;
import com.xiaoshu.entity.StuVo;


@Service
public class StuService {

	@Autowired
	StuMapper userMapper;

	@Autowired
	MajorMapper mm;
	@Autowired
	RedisTemplate rt;
	@Autowired
	JmsTemplate jt;
	@Autowired
	Destination dl;
	// 新增
	public void addUser(Stu t) throws Exception {
		userMapper.insert(t);
		rt.delete("hc");
	};

	// 修改
	public void updateUser(Stu t) throws Exception {
		userMapper.updateByPrimaryKeySelective(t);
		rt.delete("hc");
	};

	// 删除
	public void deleteUser(Integer sId) throws Exception {
		userMapper.deleteByPrimaryKey(sId);
		rt.delete("hc");
	};


	public PageInfo<StuVo> findUserPage(StuVo user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<StuVo> userList = userMapper.findAll(user);
		PageInfo<StuVo> pageInfo = new PageInfo<StuVo>(userList);
		return pageInfo;
	}



	public List<Major> findRole() {
		return mm.selectAll();
	}

	public List<StuVo> exp() {
		return userMapper.findAll(null);
	}

	public Major findMname(String mName) {
		Major major = new Major();
		major.setmName(mName);
		Major one = mm.selectOne(major);
		return one;
	}

	public List<StuVo> findEcharts() {
		return userMapper.findEacharts();
	}
	public void insertAllMajor(final Major major)throws Exception{
		mm.insert(major);
		rt.boundHashOps("hc").put(major.getmId()+"", major+"");
		jt.send(dl, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				String json = JSONObject.toJSONString(major);
				return session.createTextMessage(json);
			}
		});
		
	}
	}