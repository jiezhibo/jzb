package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Pperson;
import com.xiaoshu.entity.PpersonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PpersonMapper extends BaseMapper<Pperson> {
    List<Pperson> selectPerson(Pperson pperson);
}