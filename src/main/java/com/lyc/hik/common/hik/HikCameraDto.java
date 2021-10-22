package com.lyc.hik.common.hik;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 视频服务接口Dto
 *
 * @author kisang
 * @date 2021年8月23日15:23:26
 */
@Data
public class HikCameraDto {
    /**
     * 监控点唯一标识,多个监控点可以逗号分隔（必传）
     */
    @ApiModelProperty(value = "监控点唯一标识,多个监控点可以逗号分隔(必传)")
    private String cameraIndexCode;

    /**
     * 码流类型
     * 0:主码流，1:子码流，2:第三码流
     * 参数不填，默认为主码流
     */
    @ApiModelProperty(value = "码流类型(默认为0主码流)")
    private Integer streamType;

    /**
     * 取流协议
     * “hik”:HIK私有协议，rtsp”:RTSP协议，“rtmp”:RTMP协议，“hls”:HLS协议，ws”:Websocket协议
     * 参数不填，默认为HIK协议
     */
    @ApiModelProperty(value = "取流协议(默认为ws协议)")
    private String protocol;

    /**
     * 传输协议
     * 0:UDP,1:TCP
     * 参数不填，默认是TCP
     */
    @ApiModelProperty(value = "传输协议,默认是TCP")
    private Integer transmode;

    /**
     * 开始查询时间（回放参数）
     * （IOS8601格式：yyyy-MM-dd’T’HH:mm:ss.SSSXXX）
     * 例如北京时间：
     * 2017-06-14T00:00:00.000+08:00
     */
    @ApiModelProperty(value = "开始查询时间,IOS8601格式")
    private String beginTime;

    /**
     * 结束查询时间（回放参数） 开始时间和结束时间相差不超过三天；
     * （IOS8601格式：yyyy-MM-dd’T’HH:mm:ss.SSSXXX）
     * 例如北京时间：
     * 2017-06-14T00:00:00.000+08:00
     */
    @ApiModelProperty(value = "开始查询时间,IOS8601格式")
    private String endTime;

    /**
     * 分页查询id（回放参数），上一次查询返回的uuid，用于继续查询剩余片段，默认为空字符串。
     * 当存储类型为设备存储时，该字段生效，中心存储会一次性返回全部片段。
     */
    @ApiModelProperty(value = "分页查询id，上一次查询返回的uuid")
    private String uuid;
}
