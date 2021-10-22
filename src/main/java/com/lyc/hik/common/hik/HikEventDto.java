package com.lyc.hik.common.hik;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 海康开发者平台
 * 事件接口DTO
 *
 * @author lyc
 * @date 2021年3月2日16:22:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HikEventDto {
    /**
     * 事件类型,多个按逗号分隔
     */
    private String eventTypes;

    /**
     * 指定事件接收的地址
     */
    private String eventDest;

    /**
     * 订阅类型，0-订阅原始事件，1-联动事件，2-原始事件和联动事件，不填使用默认值0
     */
    private Integer subType;

    /**
     * 事件等级 0-未配置，1-低，2-中，3-高
     */
    private String eventLvl;

}
