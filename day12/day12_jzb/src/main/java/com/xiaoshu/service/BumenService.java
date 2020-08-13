package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.dao.BumenMapper;
import com.xiaoshu.entity.Bumen;

@Service
public class BumenService {
	@Autowired
	BumenMapper bumenMapper;
	

	public List<Bumen> findAll() {
		// TODO Auto-generated method stub
		return bumenMapper.selectAll();
	}


	public Bumen findID(String maname) {
		// TODO Auto-generated method stub
		return bumenMapper.findId(maname);
	}
	
}
