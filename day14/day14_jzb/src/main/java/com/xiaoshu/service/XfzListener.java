package com.xiaoshu.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshu.entity.Teacher;

public class XfzListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage tm =(TextMessage) message;
		String sc;
		try {
			sc = tm.getText();
			Teacher teacher=JSONObject.parseObject(sc, Teacher.class);
			System.out.println("这是mq输出语句："+teacher);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
