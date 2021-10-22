package com.lyc.hik.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyc.hik.entity.HikInfraredAlarmEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 海康服务-事件回调Mapper
 *
 * @author kisang
 */
@Mapper
public interface HikInfraredAlarmDao extends BaseMapper<HikInfraredAlarmEntity> {
}
