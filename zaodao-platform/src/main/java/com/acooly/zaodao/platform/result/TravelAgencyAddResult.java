package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.zaodao.platform.dto.TravelAgencyDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaohong
 * @create 2018-05-05 16:00
 **/
@Getter
@Setter
public class TravelAgencyAddResult extends ResultBase {
    /**
     * 旅行社
     */
    private TravelAgencyDto travelAgencyDto;
}
