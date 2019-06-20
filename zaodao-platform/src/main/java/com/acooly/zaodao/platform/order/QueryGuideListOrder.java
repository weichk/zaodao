package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.core.utils.Money;
import lombok.Data;

import java.util.Date;

/** Created by xiyang on 2017/9/28. */
@Data
public class QueryGuideListOrder extends PageOrder {

  private Date startTime;

  private Date endTime;

  private Integer sex;

  private Integer hotGuide;

  private String realName;

  private String city;

  private String language;

  private Money priceMin = new Money(0);

  private Money priceMax = new Money(0);

  private String keywords;
}
