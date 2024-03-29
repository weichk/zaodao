/*
 * www.yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * qiubo@yiji.com 2017-03-14 01:50 创建
 */
package com.acooly.zaodao.openapi;

import com.acooly.core.common.boot.component.ComponentInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/** @author qiubo@yiji.com */
public class OpendocIntegrationComponentInitializer implements ComponentInitializer {
  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    setPropertyIfMissing(
        "acooly.jpa.entityPackagesToScan.openapidoc1", "com.acooly.openapi.apidoc.**.domain");
      setPropertyIfMissing(
          "acooly.jpa.entityPackagesToScan.openapidoc2", "com.acooly.openapi.apidoc.**.entity");
  }
}
