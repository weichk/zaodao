package com.acooly.zaodao.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.acooly.zaodao.account.entity.AccountFreezeChange;
import com.acooly.module.mybatis.EntityMybatisDao;

/**
 * 冻结账户变动表 Mybatis Dao
 */
public interface AccountFreezeChangeDao extends EntityMybatisDao<AccountFreezeChange> {

	@Select("select * from act_account_freeze_change where order_no = #{orderNo} and account_no = #{accountNo} and freeze_type = #{freezeType}")
	List<AccountFreezeChange> findByOrgFreeze(@Param("orderNo")String orderNo,@Param("accountNo")String accountNo,@Param("freezeType")String freezeType);
}
