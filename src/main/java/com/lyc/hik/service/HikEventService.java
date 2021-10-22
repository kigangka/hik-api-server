package com.lyc.hik.service;

import com.lyc.hik.common.hik.HikEventDto;
import com.lyc.hik.common.hik.HikRst;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 海康视频应用服务层
 *
 * @author kisang
 * @date 2021年8月23日15:15:27
 */
public interface HikEventService {
    /**
     * 按事件类型订阅事件
     *
     * @param hikEventDto 参数
     * @return 返回结果
     */
    HikRst eventSubscribe(@RequestBody HikEventDto hikEventDto);

    /**
     * 取消订阅事件
     *
     * @param ids 参数
     * @return 返回结果
     */
    HikRst eventUnSubscribe(String ids);

    /**
     * 查询订阅事件
     *
     * @param param 参数
     * @return 返回结果
     */
    HikRst eventViewSubscribe(String param);
}
