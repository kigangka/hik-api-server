package com.lyc.hik.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyc.hik.common.hik.HikRst;
import com.lyc.hik.common.utils.PicUtils;
import com.lyc.hik.entity.SysUserEntity;
import com.lyc.hik.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试
 *
 * @author kisang
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    private SysUserService sysUserService;

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 添加人员信息
     */
    @PostMapping("add")
    public HikRst add(@RequestBody SysUserEntity params) {
        boolean rst = sysUserService.save(params);
        return rst ? HikRst.ok() : HikRst.error("保存失败！");
    }


    /**
     * 添加人员信息
     */
    @GetMapping("view")
    public HikRst view() {
        List<SysUserEntity> rst = sysUserService.list();
        return HikRst.ok(rst);
    }

    /**
     * 添加人员信息
     */
    @GetMapping("delete")
    public HikRst delete(Long id) {
        boolean rst = sysUserService.remove(new QueryWrapper<SysUserEntity>().eq("user_id", id));
        return rst ? HikRst.ok() : HikRst.error("保存失败！");
    }

    /**
     * 添加人员信息
     */
    @GetMapping("testpic")
    public HikRst testpic() {
        return HikRst.ok(PicUtils.localImgToBase64(""));
    }

}