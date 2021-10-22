package com.lyc.hik.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 高空球红外报警数据
 *
 * @author kisang
 * @date 2021年8月28日09:50:27
 */
@NoArgsConstructor
@Data
@TableName("hik_infrared_alarm")
public class HikInfraredAlarmEntity {

    /**
     * 报警时间
     */
    @TableId(type = IdType.INPUT)
    private Timestamp time;
    /**
     * 报警级别
     */
    private Integer alarmLevel;
    /**
     * 报警类型
     */
    private Integer alarmType;
    /**
     * 报警发生时间
     */
    private LocalDateTime beginTime;
    /**
     * 消警原因
     */
    private String clearAlarmReason;
    /**
     * 消警状态
     */
    private Integer clearAlarmStatus;
    /**
     * 消警时间
     */
    private String clearAlarmTime;
    /**
     * 当前温度
     */
    private Double curTemperatureDiff;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 事件描述
     */
    private String eventDescription;
    /**
     * 事件唯一ID
     */
    private String eventId;
    /**
     * 事件等级
     */
    private Integer eventLvl;
    /**
     * 事件名称
     */
    private String eventName;
    /**
     * 事件类别
     */
    private String eventType;
    /**
     * 火点最高温度
     */
    private Object fireMaxTemperature;
    /**
     * 发生时间
     */
    private Timestamp happenTime;
    /**
     * 红外光图片
     */
    private String imageUrl;
    /**
     * 摄像IP地址
     */
    private String ipAddress;
    /**
     * 是否推送
     */
    private Object isPush;
    /**
     * 接收时间
     */
    private Timestamp receiveTime;
    /**
     * 区域code
     */
    private String regionIndexCode;
    /**
     * 区域名称
     */
    private String regionName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 设定温度
     */
    private Double ruleTemperatureDiff;
    /**
     * 推送短信结果
     */
    private String smsResult;
    /**
     * 设备code
     */
    private String srcIndex;
    /**
     * 设备名称
     */
    private String srcName;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 目标距离
     */
    private Double targetDistance;
    /**
     * 附件唯一标识
     */
    private String unifiedCode;
    /**
     * 可见光图片
     */
    private String visiblePicUrl;
}
