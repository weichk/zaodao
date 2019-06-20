package com.acooly.zaodao.account.dao;

import com.acooly.module.mybatis.EntityMybatisDao;
import com.acooly.zaodao.account.entity.AccountChange;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户账户变动表 Mybatis Dao
 */
public interface AccountChangeDao extends EntityMybatisDao<AccountChange> {

	@Select("select * from act_account_change where order_no = #{orderNo}")
	List<AccountChange> findByOrderNo(@Param("orderNo")String orderNo);
}
