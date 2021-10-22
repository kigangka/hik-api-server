package com.lyc.hik.common.hik;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 海康开发者平台
 * 事件具体信息
 *
 * @author lyc
 * @date 2021年3月2日16:22:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HikEventInfo {
    /**
     * 订阅地址
     */
    private String eventDest;

    /**
     * 事件类型
     */
    private Long eventType;

    /**
     * 事件类型名称
     */
    private String eventTypeName;
}
