package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.ContentCategoryMapper;
import com.xiaoshu.dao.ContentMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Content;
import com.xiaoshu.entity.ContentCategory;
import com.xiaoshu.entity.ContentVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class ContentService {

	@Autowired
	ContentMapper cm;
	@Autowired
	ContentCategoryMapper tm;

	// 新增
	public void addUser(Content t) throws Exception {
		cm.insert(t);
	};

	// 修改
	public void updateUser(Content t) throws Exception {
		cm.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteUser(Integer id) throws Exception {
		cm.deleteByPrimaryKey(id);
	};
	//模糊查询
	public PageInfo<ContentVo> findUserPage(ContentVo contentVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ContentVo> userList = cm.findAllContent(contentVo);
		PageInfo<ContentVo> pageInfo = new PageInfo<ContentVo>(userList);
		return pageInfo;
	}
	//查询主表
	public List<ContentCategory> findAllContentCategory(){
		return tm.findAllCategory();
	}
	//统计
	public List<ContentVo> getTj(){
		return cm.getTj();
	}
}
