package com.lyc.hik.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyc.hik.common.hik.HikEventConst;
import com.lyc.hik.common.hik.HikEventDto;
import com.lyc.hik.common.hik.HikEventInfo;
import com.lyc.hik.common.hik.HikRst;
import com.lyc.hik.common.utils.ArtemisUtils;
import com.lyc.hik.common.utils.UrlConst;
import com.lyc.hik.service.HikEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 海康视频应用服务层
 *
 * @author kisang
 * @date 2021年8月23日15:15:27
 */
@Service("hikEventService")
public class HikEventServiceImpl implements HikEventService {

    private ArtemisUtils artemisUtils;

    @Autowired
    public void setArtemisUtils(ArtemisUtils artemisUtils) {
        this.artemisUtils = artemisUtils;
    }

    /**
     * 按事件类型订阅事件
     *
     * @param hikEventDto 参数
     * @return 返回结果
     */
    @Override
    public HikRst eventSubscribe(HikEventDto hikEventDto) {
        if (hikEventDto == null) {
            return HikRst.error("参数有问题请确认！");
        }
        if (StrUtil.isEmpty(hikEventDto.getEventTypes()) || StrUtil.isEmpty(hikEventDto.getEventDest())) {
            return HikRst.error(("事件类型或事件接收地址不能为空!"));
        }
        // 组织报文格式
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"eventTypes\":[");
        jsonBuilder.append(hikEventDto.getEventTypes());
        jsonBuilder.append("],");
        jsonBuilder.append("\"eventDest\":\"");
        jsonBuilder.append(hikEventDto.getEventDest());
        jsonBuilder.append("\"");
        jsonBuilder.append(",");
        jsonBuilder.append("\"subType\":");
        if (hikEventDto.getSubType() != null) {
            jsonBuilder.append(hikEventDto.getSubType());
        } else {
            // 默认使用2
            jsonBuilder.append(2);
        }
        jsonBuilder.append(",");
        jsonBuilder.append("\"eventLvl\":[");
        if (StrUtil.isEmpty(hikEventDto.getEventLvl())) {
            // 默认使用1,2,3,4,5
            jsonBuilder.append("1,2,3,4,5");
        } else {
            jsonBuilder.append(hikEventDto.getEventLvl());
        }
        jsonBuilder.append("]");
        jsonBuilder.append("}");

        JSONObject jsonObject = JSON.parseObject(jsonBuilder.toString());
        return artemisUtils.commonCall(UrlConst.HIK_EVENT_SUBSCRIBE, jsonObject);
    }

    /**
     * 取消订阅事件
     *
     * @param ids 参数
     * @return 返回结果
     */
    @Override
    public HikRst eventUnSubscribe(String ids) {
        if (StrUtil.isEmpty(ids)) {
            return HikRst.error("参数有问题请确认！");
        }
        // 组织报文格式
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"eventTypes\":[");
        jsonBuilder.append(ids);
        jsonBuilder.append("]");
        jsonBuilder.append("}");
        JSONObject jsonObject = JSON.parseObject(jsonBuilder.toString());
        return artemisUtils.commonCall(UrlConst.HIK_EVENT_UNSUBSCRIBE, jsonObject);
    }

    /**
     * 查询订阅事件
     *
     * @param param 参数
     * @return 返回结果
     */
    @Override
    public HikRst eventViewSubscribe(String param) {
        String rst = artemisUtils.doPostArtemis(UrlConst.HIK_EVENT_VIEW, new JSONObject());
        List<HikEventInfo> rstList = new ArrayList<>();
        // 解析结果
        if (StrUtil.isNotEmpty(rst)) {
            JSONObject rstJSON = JSONObject.parseObject(rst);
            if (rstJSON.containsKey("code") && StrUtil.equals(rstJSON.getString("code"), "0")) {
                JSONObject dataJson = rstJSON.getJSONObject("data");
                if (dataJson.containsKey("detail")) {
                    JSONArray detailJson = dataJson.getJSONArray("detail");
                    HikEventInfo info;
                    if (detailJson != null) {
                        int len = detailJson.size();
                        for (int i = 0; i < len; i++) {
                            JSONObject infoObject = detailJson.getJSONObject(i);
                            JSONArray typeArray = infoObject.getJSONArray("eventTypes");
                            if (typeArray != null) {
                                int typeLen = typeArray.size();
                                for (int j = 0; j < typeLen; j++) {
                                    String typeId = typeArray.getString(j);
                                    String tmpEventDest = infoObject.getString("eventDest");
                                    String tmpEventTypeName = HikEventConst.EVENT_TYPE_MAP.get(typeId);
                                    if (StrUtil.isNotEmpty(param)) {
                                        boolean isSearch = StrUtil.equals(param, typeId) || (StrUtil.isNotEmpty(tmpEventDest) && tmpEventDest.indexOf(param) != -1) || (StrUtil.isNotEmpty(tmpEventTypeName) && tmpEventTypeName.indexOf(param) != -1);
                                        // 模糊查询
                                        if (isSearch) {
                                            info = new HikEventInfo();
                                            info.setEventDest(tmpEventDest);
                                            info.setEventType(Long.parseLong(typeId));
                                            info.setEventTypeName(tmpEventTypeName);
                                            rstList.add(info);
                                        }
                                    } else {
                                        info = new HikEventInfo();
                                        info.setEventDest(tmpEventDest);
                                        info.setEventType(Long.parseLong(typeId));
                                        info.setEventTypeName(tmpEventTypeName);
                                        rstList.add(info);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return HikRst.ok(rstList);
    }
}
