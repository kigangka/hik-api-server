package com.lyc.hik.controller;

import cn.hutool.core.util.ObjectUtil;
import com.lyc.hik.common.hik.HikCardDto;
import com.lyc.hik.common.hik.HikRst;
import com.lyc.hik.service.HikCardService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 人员开卡应用服务
 *
 * @author kisang
 * @date 2021年9月9日15:42:11
 */
@RestController
@RequestMapping("hik/card")
@Slf4j
public class HikCardController {

    HikCardService hikCardService;

    @Autowired
    public void setHikCardService(HikCardService hikCardService) {
        this.hikCardService = hikCardService;
    }

    /**
     * 人员批量开卡
     */
    @PostMapping("bindings")
    @ApiOperation(value = "人员批量开卡")
    public HikRst bindings(@RequestBody HikCardDto params) {
        if (ObjectUtil.isNull(params)) {
            return HikRst.error("参数不能为空");
        }
        return hikCardService.bindings(params);
    }

    /**
     * 获取卡片列表
     */
    @PostMapping("cardList")
    @ApiOperation(value = "获取卡片列表")
    public HikRst cardList(@RequestBody HikCardDto params) {
        if (ObjectUtil.isNull(params)) {
            return HikRst.error("参数不能为空");
        }
        return hikCardService.cardList(params);
    }

    /**
     * 退卡操作
     */
    @PostMapping("deletion")
    @ApiOperation(value = "退卡操作")
    public HikRst deletion(@RequestBody HikCardDto params) {
        if (ObjectUtil.isNull(params)) {
            return HikRst.error("参数不能为空");
        }
        return hikCardService.deletion(params);
    }

    /**
     * 获取单个卡片信息
     */
    @PostMapping("cardInfo")
    @ApiOperation(value = "获取单个卡片信息")
    public HikRst cardInfo(@RequestBody HikCardDto params) {
        if (ObjectUtil.isNull(params)) {
            return HikRst.error("参数不能为空");
        }
        return hikCardService.cardInfo(params);
    }

    /**
     * 生成卡片二维码
     */
    @PostMapping("barCode")
    @ApiOperation(value = "退卡操作")
    public HikRst barCode(@RequestBody HikCardDto params) {
        if (ObjectUtil.isNull(params)) {
            return HikRst.error("参数不能为空");
        }
        return hikCardService.barCode(params);
    }
}
