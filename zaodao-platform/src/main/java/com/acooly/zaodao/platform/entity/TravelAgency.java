/*
* zhike@yiji.com Inc.
* Copyright (c) 2018 All Rights Reserved.
* create by zhike
* date:2018-05-04
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * 旅行社 Entity
 *
 * @author zhike
 * Date: 2018-05-04 16:34:01
 */
@Entity
@Table(name = "zd_travel_agency")
public class TravelAgency extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户ID */
	@NotEmpty
	@Size(max=64)
    private String userId;

	/** 旅行社名称 */
	@NotEmpty
	@Size(max=128)
    private String agencyName;

	/** 许可证 */
	@NotEmpty
	@Size(max=128)
    private String licenseNo;

	/** 联系人名称 */
	@NotEmpty
	@Size(max=128)
    private String contactName;

	/** 联系人电话 */
	@NotEmpty
	@Size(max=128)
    private String contactPhone;



	


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getAgencyName(){
		return this.agencyName;
	}
	
	public void setAgencyName(String agencyName){
		this.agencyName = agencyName;
	}

	public String getLicenseNo(){
		return this.licenseNo;
	}
	
	public void setLicenseNo(String licenseNo){
		this.licenseNo = licenseNo;
	}

	public String getContactName(){
		return this.contactName;
	}
	
	public void setContactName(String contactName){
		this.contactName = contactName;
	}

	public String getContactPhone(){
		return this.contactPhone;
	}
	
	public void setContactPhone(String contactPhone){
		this.contactPhone = contactPhone;
	}



}
