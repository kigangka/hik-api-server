package com.lyc.hik.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.hik.dao.SysUserDao;
import com.lyc.hik.entity.SysUserEntity;
import com.lyc.hik.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
}
