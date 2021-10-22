package com.lyc.hik.service;

import com.lyc.hik.common.hik.HikPersonDto;
import com.lyc.hik.common.hik.HikRst;

/**
 * 人员信息对接
 * @author kisang
 */
public interface HikPersonService {
    /**
     * 获取监控点预览取流URLv2
     *
     * @param params 参数
     * @return 返回结果
     */
    HikRst add(HikPersonDto params);

    /**
     * 更新人员信息
     *
     * @param params 参数
     * @return 返回结果
     */
    HikRst update(HikPersonDto params);

    /**
     * 批量删除人员
     * ids 逗号分隔
     *
     * @param ids 参数
     * @return 返回结果
     */
    HikRst delete(String ids);
}
