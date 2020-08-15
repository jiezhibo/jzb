package com.xiaoshu.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class XxListener implements MessageListener{

	@Autowired
	private RedisTemplate rt;
	@Override
	public void onMessage(Message message) {
		TextMessage Xx=(TextMessage) message;
		String zhi;
		try {
			zhi = Xx.getText();
			System.out.println("这是mq------------"+zhi);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
