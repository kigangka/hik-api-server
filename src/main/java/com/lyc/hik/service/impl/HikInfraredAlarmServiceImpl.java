package com.lyc.hik.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.hik.dao.HikInfraredAlarmDao;
import com.lyc.hik.entity.HikInfraredAlarmEntity;
import com.lyc.hik.service.HikInfraredAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 海康红外视频事件
 *
 * @author kisang
 */
@Service
public class HikInfraredAlarmServiceImpl extends ServiceImpl<HikInfraredAlarmDao, HikInfraredAlarmEntity> implements HikInfraredAlarmService {

    HikInfraredAlarmDao hikInfraredAlarmDao;

    @Autowired
    public void setHikInfraredAlarmDao(HikInfraredAlarmDao hikInfraredAlarmDao) {
        this.hikInfraredAlarmDao = hikInfraredAlarmDao;
    }

    /**
     * 视频-温度报警事件
     *
     * @param jsonStr 参数
     */
    @Override
    @Async
    public void saveTemperatureAlarmData(String jsonStr) {
        Map<String, Object> bodyMap = JSON.parseObject(jsonStr);
        String objBody = Convert.toStr(bodyMap.get("params"));
        Map<String, Object> paramsMap = JSON.parseObject(objBody);
        //接收时间
        String sendTime = Convert.toStr(paramsMap.get("sendTime"));
        Date sendDate = DateUtil.parse(sendTime);
        Date resultTime = DateUtil.beginOfSecond(sendDate);
        String timestamp = String.valueOf(resultTime.getTime());
        Long cpTimeLong = Long.valueOf(timestamp) * 1000 * 1000;
        String receiveTime = DateUtil.formatDateTime(sendDate);
        if (CollectionUtil.isNotEmpty(bodyMap)) {
            //发送时间
            /*--------------------------------------------------------------------*/
            //获取events层
            Map<String, Object> eventsMap = getEventsMap(bodyMap);
            //事件发生时间
            String happenTime = Convert.toStr(eventsMap.get("happenTime"));
            Date beginDate = DateUtil.parse(happenTime);
            String beginTime = DateUtil.formatDateTime(beginDate);
            //事件唯一标识
            String eventId = Convert.toStr(eventsMap.get("eventId"));
            //事件状态
            Integer status = Convert.toInt(eventsMap.get("status"));
            //事件等级
            Integer eventLvl = Convert.toInt(eventsMap.get("eventLvl"));
            //事件名称
            String eventName = Convert.toStr(eventsMap.get("eventName"));
            /*--------------------------------------------------------------------*/
            //获取eventsDetails层
            JSONArray jsonArray = JSONObject.parseArray(Convert.toStr(eventsMap.get("eventDetails")));
            Map<String, Object> eventsDetailsMap = (Map<String, Object>) jsonArray.get(0);
            //srcName
            String srcName = Convert.toStr(eventsDetailsMap.get("srcName"));
            //srcIndex
            String srcIndex = Convert.toStr(eventsDetailsMap.get("srcIndex"));
            //eventType
            String eventType = Convert.toStr(eventsDetailsMap.get("eventType"));
            /*--------------------------------------------------------------------*/
            //获取data数据层
            String data = Convert.toStr(eventsDetailsMap.get("data"));
            Map<String, Object> dataMap = JSON.parseObject(data);
            //获取ip地址
            String ipAddress = Convert.toStr(dataMap.get("ipAddress"));
            //事件类型名
            String eventDescription = Convert.toStr(dataMap.get("eventDescription"));
            //获取分析结果层
            JSONArray thermometryArray = JSONObject.parseArray(Convert.toStr(dataMap.get("thermometry")));
            Map<String, Object> thermometryArrayMap = (Map<String, Object>) thermometryArray.get(0);
            //获取报警等级（0：预警 1：报警）
            String alarmLevel = Convert.toStr(thermometryArrayMap.get("alarmLevel"));
            //获取报警类型（0：最高温度 1：最低温度 2：平均温度 3：温差 4：温度突升 5：温度突降）
            Integer alarmType = Convert.toInt(thermometryArrayMap.get("alarmType"));
            //获取配置规则温差
            Double ruleTemperature = Convert.toDouble(thermometryArrayMap.get("ruleTemperature"));
            //获取当前温差
            Double curTemperature = Convert.toDouble(thermometryArrayMap.get("curTemperature"));
            //获取热成像图片
            String imageUrl = Convert.toStr(thermometryArrayMap.get("imageUrl"));
            //获取可见光图片
            String visiblePicUrl = Convert.toStr(thermometryArrayMap.get("visiblePicUrl"));
            //状态为瞬时0时，存pgsql
            //瞬时状态为0，存influxdb
            if (status == 0) {
                HikInfraredAlarmEntity hikInfraredAlarmEntity = new HikInfraredAlarmEntity();
                hikInfraredAlarmEntity.setEventId(eventId);
                hikInfraredAlarmEntity.setStatus(status);
                hikInfraredAlarmEntity.setAlarmType(alarmType);
                hikInfraredAlarmEntity.setEventLvl(eventLvl);
                hikInfraredAlarmEntity.setEventName(eventName);
                hikInfraredAlarmEntity.setSrcIndex(srcIndex);
                hikInfraredAlarmEntity.setEventDescription(eventDescription);
                hikInfraredAlarmEntity.setRuleTemperatureDiff(ruleTemperature);
                hikInfraredAlarmEntity.setCurTemperatureDiff(curTemperature);
                hikInfraredAlarmEntity.setImageUrl(imageUrl);
                hikInfraredAlarmEntity.setVisiblePicUrl(visiblePicUrl);
                hikInfraredAlarmEntity.setTime(DateUtil.date(sendDate).toTimestamp());
                hikInfraredAlarmEntity.setHappenTime(DateUtil.date(sendDate).toTimestamp());
                hikInfraredAlarmEntity.setReceiveTime(DateUtil.date(sendDate).toTimestamp());
                hikInfraredAlarmEntity.setClearAlarmStatus(0);
                hikInfraredAlarmDao.insert(hikInfraredAlarmEntity);
            }
        }
    }

    /**
     * 烟雾检测事件保存
     *
     * @param jsonStr 参数
     */
    @Override
    @Async("taskHKExecutor")
    public void saveSmokeDetection(String jsonStr) {
    }

    public Map<String, Object> getEventsMap(Map<String, Object> bodyMap) {
        String objBody = Convert.toStr(bodyMap.get("params"));
        Map<String, Object> paramsMap = JSON.parseObject(objBody);
        JSONArray jsonArray = JSONObject.parseArray(Convert.toStr(paramsMap.get("events")));
        return (Map<String, Object>) jsonArray.get(0);
    }
}
