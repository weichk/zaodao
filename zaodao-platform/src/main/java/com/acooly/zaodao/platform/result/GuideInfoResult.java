package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.zaodao.platform.dto.TourGuideDto;
import com.acooly.zaodao.platform.entity.TourGuide;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/** Created by xiyang on 2017/9/22. */
@Getter
@Setter
public class GuideInfoResult extends ResultBase {
  /** 用户ID */
  private String userId;

  /**
   * 真实姓名
   */
  private String realName;

  /**
   * 导游用户头像
   */
  private String headImg;

  /**
   * 导游封面图
   */
  private String guideCoverImg;


  /** 带团时间 */
  private Integer tourTime = 0;

  /** 常驻城市 */
  private String permanentCity;

  /** 擅长路线 */
  @Size(max = 512)
  private String goodRoute;

  /** 自我介绍 */
  @Size(max = 512)
  private String introduceMyself;

  /** 价格 */
  private Long pricePerDay;

  /** 语种 */
  private String language;

  /** 星级 */
  private Integer starLevel;

  /** 是否为人气导游{1:是,0:否} */
  private Integer hotGuide;

  /** 年龄 */
  private Integer age;

  /** 文章数量  */
  private Integer articleNums;

  /**是否被关注 */
  private Integer focused;

  /**
   * 爱好
   */
  private String speciality;

  /**
   * 评论数量
   */
  private Integer reviewCount;

  /**
   * 关注人数
   */
  private Integer count;

  /**
   * 粉丝数
   */
  private Integer focusCount;

  /**
   * 导游电话号码
   */
  private String mobileNo;

  /**
   * 性别
   */
  private Integer sex;

  /**
   * 导游资格证
   */
  private String guideCertificateImg;

  /**
   * 是否开启商务接待{0-否,1-是}
   */
  private Integer isBusRecept;

  /**
   * 是否开启政务接待{0-否,1-是}
   */
  private Integer isGovRecept;
}
