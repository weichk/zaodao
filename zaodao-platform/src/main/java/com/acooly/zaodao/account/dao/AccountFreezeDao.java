package com.acooly.zaodao.account.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.acooly.zaodao.account.entity.AccountFreeze;
import com.acooly.module.mybatis.EntityMybatisDao;

/**
 * 冻结账户表 Mybatis Dao
 */
public interface AccountFreezeDao extends EntityMybatisDao<AccountFreeze> {

	@Select("select * from act_account_freeze where account_no = #{accountNo} and freeze_type = #{freezeType}")
	AccountFreeze findByAccountNoAndFreezeType(@Param("accountNo")String accountNo, @Param("freezeType")String freezeType);
}
