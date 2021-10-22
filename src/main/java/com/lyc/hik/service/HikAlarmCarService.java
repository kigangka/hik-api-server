package com.lyc.hik.service;

import com.lyc.hik.common.hik.HikAlarmCarDto;
import com.lyc.hik.common.hik.HikRst;

/**
 * 车辆布控
 *
 * @author kisang
 * @date 2021年9月6日20:48:54
 */
public interface HikAlarmCarService {
    /**
     * 车辆布控
     *
     * @param params 参数
     * @return 返回结果
     */
    HikRst addition(HikAlarmCarDto params);

    /**
     * 车辆布控
     *
     * @param params 布控车辆唯一标识集合(编号间用‘,’分隔)
     * @return 返回结果
     */
    HikRst deletion(HikAlarmCarDto params);

    /**
     * 查询布控车辆
     *
     * @param params 参数
     * @return 返回结果
     */
    HikRst page(HikAlarmCarDto params);
}
