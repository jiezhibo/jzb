package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Bumen;
import com.xiaoshu.entity.BumenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BumenMapper extends BaseMapper<Bumen> {

	Bumen findId(String maname);
	
	
}