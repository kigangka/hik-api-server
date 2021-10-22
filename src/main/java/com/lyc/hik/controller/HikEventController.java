package com.lyc.hik.controller;

import com.lyc.hik.common.hik.HikEventDto;
import com.lyc.hik.common.hik.HikRst;
import com.lyc.hik.service.HikEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 海康平台-订阅事件
 *
 * @author lyc
 * @date 2021年3月2日16:06:41
 */
@RestController
@RequestMapping("hik/event")
@Slf4j
public class HikEventController {

    private HikEventService hikEventService;

    @Autowired
    public void setHikEventService(HikEventService hikEventService) {
        this.hikEventService = hikEventService;
    }

    /**
     * 按事件类型订阅事件
     *
     * @return 返回结果
     */
    @RequestMapping("/subscribe")
    public HikRst eventSubscribe(@RequestBody HikEventDto hikEventDto) {
        return hikEventService.eventSubscribe(hikEventDto);
    }

    /**
     * 取消订阅事件
     *
     * @return 返回结果
     */
    @RequestMapping("/unSubscribe")
    public HikRst eventUnSubscribe(String ids) {
        return hikEventService.eventUnSubscribe(ids);
    }

    /**
     * 查询订阅事件
     *
     * @param param 查看参数
     * @return 返回结果
     */
    @GetMapping("/viewSubscribe")
    public HikRst eventViewSubscribe(String param) {
        return hikEventService.eventViewSubscribe(param);
    }
}