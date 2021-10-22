package com.lyc.hik.controller;

import cn.hutool.core.util.ObjectUtil;
import com.lyc.hik.common.hik.HikAlarmCarDto;
import com.lyc.hik.common.hik.HikRst;
import com.lyc.hik.service.HikAlarmCarService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 海康车辆布控应用服务
 *
 * @author kisang
 * @date 2021年9月7日17:54:58
 */
@RestController
@RequestMapping("hik/alarmCar")
@Slf4j
public class HikAlarmCarController {

    private HikAlarmCarService hikAlarmCarService;

    @Autowired
    public void setHikAlarmCarService(HikAlarmCarService hikAlarmCarService) {
        this.hikAlarmCarService = hikAlarmCarService;
    }

    /**
     * 车辆布控
     */
    @PostMapping("addition")
    @ApiOperation(value = "车辆布控")
    public HikRst addition(@RequestBody HikAlarmCarDto params) {
        if (ObjectUtil.isNull(params)) {
            return HikRst.error("参数不能为空");
        }
        return hikAlarmCarService.addition(params);
    }

    /**
     * 车辆取消布控
     */
    @PostMapping("deletion")
    @ApiOperation(value = "车辆取消布控")
    public HikRst deletion(@RequestBody HikAlarmCarDto params) {
        if (ObjectUtil.isNull(params)) {
            return HikRst.error("参数不能为空");
        }
        return hikAlarmCarService.deletion(params);
    }

    /**
     * 查询布控车辆
     */
    @PostMapping("page")
    @ApiOperation(value = "车辆取消布控")
    public HikRst page(@RequestBody HikAlarmCarDto params) {
        if (ObjectUtil.isNull(params)) {
            return HikRst.error("参数不能为空");
        }
        return hikAlarmCarService.page(params);
    }
}