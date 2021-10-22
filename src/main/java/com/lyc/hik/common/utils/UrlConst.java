package com.lyc.hik.common.utils;

/**
 * 海康对接地址常量数据
 *
 * @author kisang
 * @date 2021年8月25日16:19:14
 */
public class UrlConst {
    // 视频信息
    /**
     * 获取监控点预览取流URLv2
     */
    public static final String HIK_CAMERA_PREVIEWURLS = "/api/video/v2/cameras/previewURLs";

    /**
     * 获取监控点回放取流URLv2
     */
    public static final String HIK_CAMERA_PLAYBACKURLS = "/api/video/v2/cameras/playbackURLs";

    // 人员信息
    /**
     * 人员信息添加
     */
    public static final String HIK_PERSON_ADD = "/api/resource/v1/person/single/add";
    /**
     * 人员信息更新
     */
    public static final String HIK_PERSON_UPDATE = "/api/resource/v1/person/single/update";
    /**
     * 人员信息删除
     */
    public static final String HIK_PERSON_DELETE = "/api/resource/v1/person/batch/delete";

    // 事件信息

    /**
     * 按事件类型订阅事件
     */
    public static final String HIK_EVENT_SUBSCRIBE = "/api/eventService/v1/eventSubscriptionByEventTypes";
    /**
     * 按事件类型取消事件
     */
    public static final String HIK_EVENT_UNSUBSCRIBE = "/api/eventService/v1/eventUnSubscriptionByEventTypes";
    /**
     * 查询订阅的事件
     */
    public static final String HIK_EVENT_VIEW = "/api/eventService/v1/eventSubscriptionView";

    // 车辆及车库信息接口
    /**
     * 车辆布控
     */
    public static final String HIK_ALARMCAR_ADDITION = "/api/pms/v1/alarmCar/addition";
    /**
     * 车辆取消布控
     */
    public static final String HIK_ALARMCAR_DELETION = "/api/pms/v1/alarmCar/deletion";
    /**
     * 查询车辆布控
     */
    public static final String HIK_ALARMCAR_PAGE = "/api/pms/v1/alarmCar/page";

    // 人员开卡服务
    /**
     * 人员批量开卡
     */
    public static final String HIK_CARD_BINDINGS = "/api/cis/v1/card/bindings";
    /**
     * 获取卡片列表
     */
    public static final String HIK_CARD_CARDLIST = "/api/irds/v1/card/advance/cardList";
    /**
     * 退卡操作
     */
    public static final String HIK_CARD_DELETION = "/api/cis/v1/card/deletion";
    /**
     * 查询单个卡片
     */
    public static final String HIK_CARD_CARDINFO = "/api/irds/v1/card/cardInfo";
    /**
     * 生成卡片二维码
     */
    public static final String HIK_CARD_BARCODE = "/api/cis/v1/card/barCode";

}
