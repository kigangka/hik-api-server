package com.lyc.hik.controller;

import cn.hutool.core.util.ObjectUtil;
import com.lyc.hik.common.hik.HikCameraDto;
import com.lyc.hik.common.hik.HikRst;
import com.lyc.hik.service.HikCameraService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 海康视频应用服务
 *
 * @author kisang
 * @date 2021年8月23日15:15:27
 */
@RestController
@RequestMapping("hik/camera")
@Slf4j
public class HikCameraController {
    private HikCameraService hikCameraService;

    @Autowired
    public void setHikCameraService(HikCameraService hikCameraService) {
        this.hikCameraService = hikCameraService;
    }

    /**
     * 获取监控点预览取流URLv2
     */
    @PostMapping("previewURLs")
    @ApiOperation(value = "获取监控点预览取流URLv2")
    public HikRst previewURLs(@RequestBody HikCameraDto params) {
        if (ObjectUtil.isNull(params)) {
            return HikRst.error("参数不能为空");
        }
        return hikCameraService.previewURLs(params);
    }

    /**
     * 获取监控点回放取流URLv2
     */
    @PostMapping("playbackURLs")
    @ApiOperation(value = "获取监控点回放取流URLv2")
    public HikRst playbackURLs(@RequestBody HikCameraDto params) {
        if (ObjectUtil.isNull(params)) {
            return HikRst.error("参数不能为空");
        }
        return hikCameraService.playbackURLs(params);
    }
}
