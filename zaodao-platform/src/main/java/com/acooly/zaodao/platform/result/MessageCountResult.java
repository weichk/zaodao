package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消息个数
 *
 * @author xiaohong
 * @create 2017-11-08 16:45
 **/
@Data
public class MessageCountResult extends ResultBase {
    /**
     * 消息个数
     */
    private Integer messageCount;
}
