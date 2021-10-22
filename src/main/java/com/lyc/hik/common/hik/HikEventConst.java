package com.lyc.hik.common.hik;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author kisang
 * 海康平台常量
 */
public class HikEventConst {
    /**
     * 事件
     */
    public final static Map<String, String> EVENT_TYPE_MAP = new TreeMap<String, String>() {
        private static final long serialVersionUID = 1L;

        {
            put("771760130", "入场压线事件");
            put("771760131", "入场放行事件");
            put("771760133", "出场压线事件");
            put("771760134", "出场放行事件");
            put("1157632002", "园区卡口黑名单事件");
            put("1157632003", "园区卡口超速事件");
            put("1157632004", "园区卡口逆行事件");
            put("1157632005", "园区卡口违停事件");
            put("131329", "视频丢失事件");
            put("192513", "烟雾检测事件");
            put("192514", "烟火检测事件");
            put("192515", "火点检测事件");
            put("192518", "温差报警");
            put("192517", "温度报警");
            put("131614", "人脸抓拍事件");
            put("131659", "人脸比对事件");
            put("1644175361", "重点人员识别事件");
            put("1644171265", "陌生人识别事件");
            put("132865", "高频人员识别事件");
        }
    };
}
