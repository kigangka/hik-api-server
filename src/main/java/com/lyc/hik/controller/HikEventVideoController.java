package com.lyc.hik.controller;

import cn.hutool.core.date.DateUtil;
import com.lyc.hik.service.HikInfraredAlarmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 视频产生的事件
 *
 * @author kisang
 * @date 2021年8月26日15:24:54
 */
@RestController
@RequestMapping("hik/event")
@Slf4j
public class HikEventVideoController {

    private HikInfraredAlarmService hikInfraredAlarmService;

    @Autowired
    public void setHikInfraredAlarmService(HikInfraredAlarmService hikInfraredAlarmService) {
        this.hikInfraredAlarmService = hikInfraredAlarmService;
    }

    /**
     * 烟雾检测事件
     *
     * @param body 事件报文
     * @return 返回结果
     */
    @RequestMapping("/192513")
    public ResponseEntity<HttpStatus> smokeDetection(@RequestBody String body) {
        hikInfraredAlarmService.saveSmokeDetection(body);
        log.info(DateUtil.now() + ":" + body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
