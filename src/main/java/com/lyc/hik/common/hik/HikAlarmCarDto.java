package com.lyc.hik.common.hik;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 车辆布控接口Dto
 *
 * @author kisang
 * @date 2021年9月7日10:36:21
 */
@Data
public class HikAlarmCarDto {
    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号(必传)")
    private String plateNo;
    /**
     * 驾驶员名称
     */
    @ApiModelProperty(value = "驾驶员名称")
    private String driver;
    /**
     * 驾驶员电话
     */
    @ApiModelProperty(value = "驾驶员电话")
    private String driverPhone;
    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String remark;
    /**
     * 布控结束时间,ISO8601格式： yyyy-MM-ddTHH:mm:ss+当前时区，例如北京时间： 2018-07-26T15:00:00+08:00
     */
    @ApiModelProperty(value = "布控结束时间,ISO8601格式")
    private String endTime;
    /**
     * 区域IndexCode
     */
    @ApiModelProperty(value = "区域IndexCode")
    private String regionIndexCode;
    /**
     * 车牌号/卡号（查询时用）
     */
    @ApiModelProperty(value = "车牌号/卡号")
    private String searchKey;
    /**
     * 目标页码（查询时用）
     */
    @ApiModelProperty(value = "目标页码")
    private Integer pageNo;
    /**
     * 每页记录数（查询时用）
     */
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;

    /**
     * 查询开始时间（格式：yyyy-MM-ddTHH:mm:ss+当前时区）（查询时用）
     */
    @ApiModelProperty(value = "查询开始时间,ISO8601格式")
    private String startTime;

    /**
     * 布控车辆唯一标识集合(编号间用‘,’分隔)(删除时用)
     */
    @ApiModelProperty(value = "布控车辆唯一标识集合(编号间用‘,’分隔)")
    private String alarmSyscodes;
}
