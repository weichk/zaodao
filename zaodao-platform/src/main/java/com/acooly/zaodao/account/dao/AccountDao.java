package com.acooly.zaodao.account.dao;

import com.acooly.module.mybatis.EntityMybatisDao;
import com.acooly.zaodao.account.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户账户 Mybatis Dao
 */
public interface AccountDao extends EntityMybatisDao<Account> {

	@Select("select * from act_account where user_id = #{userId} and account_type = #{accountType} for update")
	Account findByUserIdAndAccountTypeAndLock(@Param("userId")String userId, @Param("accountType")String accountType);
	
	@Select("select * from act_account where account_no = #{accountNo} for update")
	Account findByAccountNoAndLock(@Param("accountNo")String accountNo);
	
	@Select("select * from act_account where account_no = #{accountNo}")
	Account findByAccountNo(@Param("accountNo")String accountNo);
	
	@Select("select * from act_account where user_id = #{userId} and status != #{notEqStatus}")
	List<Account> findByUserIdAndStatusNotEqStatus(@Param("userId")String userId, @Param("notEqStatus")String notEqStatus);

	@Select("select * from act_account where user_id = #{userId}")
	Account findByUserId(@Param("userId")String userId);

}
