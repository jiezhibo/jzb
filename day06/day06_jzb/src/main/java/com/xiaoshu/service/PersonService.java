package com.xiaoshu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.CompanyMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.entity.Company;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.QueryVo;

@Service
public class PersonService {
	
	@Autowired
	private PersonMapper pm;
	
	@Autowired
	private CompanyMapper cm;

	
	public PageInfo<QueryVo> findUserPage(QueryVo qv, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<QueryVo> userList = pm.findAll(qv);
		PageInfo<QueryVo> pageInfo = new PageInfo<QueryVo>(userList);
		return pageInfo;
	}
	
	// 新增
		public void addUser(Person p) throws Exception {
			pm.insert(p);
		};

		// 修改
		public void updateUser(Person p) throws Exception {
			pm.updateByPrimaryKeySelective(p);
		};

		// 删除
		public void deleteUser(Integer id) throws Exception {
			pm.deleteByPrimaryKey(id);
		}

		public List<Company> findAllCompany() {
			List<Company> li = new ArrayList<>();
			List<Company> sli = cm.selectAll();
			for (int i = 0; i < sli.size(); i++) {
				Company c = sli.get(i);
				if(c.getStatus().equals("1")){
					li.add(c);
				}
			}
			return li;
		}


		public void addCompany(Company c) {
			cm.insert(c);
		};
		
		public Company getCompanyByCname(String cname) {
			List<Company> list = cm.selectAll();
			for (int i = 0; i < list.size(); i++) {
				Company c = list.get(i);
				if(cname.equals(c.getExpressName1())){
					return c;
				}
			}
			return null;
		}
}
