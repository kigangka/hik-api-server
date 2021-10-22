package com.lyc.hik.controller;

import com.lyc.hik.common.hik.HikPersonDto;
import com.lyc.hik.common.hik.HikRst;
import com.lyc.hik.service.HikPersonService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 海康服务-人员信息接口
 *
 * @author kisang
 */
@RestController
@RequestMapping("hik/person")
@Slf4j
public class HikPersonController {

    private HikPersonService hikPersonService;

    @Autowired
    public void setHikPersonService(HikPersonService hikPersonService) {
        this.hikPersonService = hikPersonService;
    }

    /**
     * 添加人员信息
     */
    @PostMapping("add")
    @ApiOperation(value = "添加人员信息")
    public HikRst add(@RequestBody HikPersonDto params) {
        return hikPersonService.add(params);
    }

    /**
     * 更新人员信息
     */
    @PostMapping("update")
    @ApiOperation(value = "添加人员信息")
    public HikRst update(@RequestBody HikPersonDto params) {
        return hikPersonService.update(params);
    }

    /**
     * 批量删除人员
     * ids 逗号分隔
     */
    @PostMapping("delete")
    @ApiOperation(value = "添加人员信息")
    public HikRst delete(String ids) {
        return hikPersonService.delete(ids);
    }
}
