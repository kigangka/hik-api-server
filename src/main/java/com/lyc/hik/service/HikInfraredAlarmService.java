package com.lyc.hik.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyc.hik.entity.HikInfraredAlarmEntity;

/**
 * 视频红外事件
 *
 * @author kisang
 * @date 2021年8月26日15:43:22
 */
public interface HikInfraredAlarmService extends IService<HikInfraredAlarmEntity> {

    /**
     * 视频-温度报警事件
     *
     * @param jsonStr 参数
     */
    void saveTemperatureAlarmData(String jsonStr);

    /**
     * 保存烟雾检测事件数据
     *
     * @param jsonStr 参数
     */
    void saveSmokeDetection(String jsonStr);
}
