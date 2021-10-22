package com.lyc.hik.service;

import com.lyc.hik.common.hik.HikCameraDto;
import com.lyc.hik.common.hik.HikRst;

/**
 * 海康视频应用服务层
 *
 * @author kisang
 * @date 2021年8月23日15:15:27
 */
public interface HikCameraService {

    /**
     * 获取监控点预览取流URLv2
     *
     * @param params 参数
     * @return 返回结果
     */
    HikRst previewURLs(HikCameraDto params);

    /**
     * 获取监控点回放取流URLv2
     *
     * @param params 参数
     * @return 返回结果
     */
    HikRst playbackURLs(HikCameraDto params);
}
