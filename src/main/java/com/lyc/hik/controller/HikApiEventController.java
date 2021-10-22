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
 * 海康事件回调服务
 *
 * @author kisang
 * @date 2021年9月30日11:02:13
 */
@RestController
@RequestMapping("hik/api/event")
@Slf4j
public class HikApiEventController {

    private HikInfraredAlarmService hikInfraredAlarmService;

    @Autowired
    public void setHikInfraredAlarmService(HikInfraredAlarmService hikInfraredAlarmService) {
        this.hikInfraredAlarmService = hikInfraredAlarmService;
    }

    /**
     * 视频-温度报警事件
     *
     * @return 返回结果
     */
    @RequestMapping("/192517")
    public ResponseEntity<HttpStatus> temperatureAlarm(@RequestBody String body) {
        hikInfraredAlarmService.saveTemperatureAlarmData(body);
        log.info("海康订阅事件:温度报警" + "192517" + "报文信息:" + DateUtil.now() + ":" + body);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
