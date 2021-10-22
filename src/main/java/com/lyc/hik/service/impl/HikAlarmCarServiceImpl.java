package com.lyc.hik.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.lyc.hik.common.hik.HikAlarmCarDto;
import com.lyc.hik.common.hik.HikRst;
import com.lyc.hik.common.utils.ArtemisUtils;
import com.lyc.hik.common.utils.UrlConst;
import com.lyc.hik.service.HikAlarmCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 车辆布控服务实现层
 *
 * @author kisang
 * @date 2021年9月7日10:57:31
 */
@Service("hikAlarmCarService")
public class HikAlarmCarServiceImpl implements HikAlarmCarService {

    private ArtemisUtils artemisUtils;

    @Autowired
    public void setArtemisUtils(ArtemisUtils artemisUtils) {
        this.artemisUtils = artemisUtils;
    }

    /**
     * 车辆布控
     *
     * @param params 参数示例：
     *               {
     *               "plateNo": "浙A12345",
     *               "driver": "周三",
     *               "driverPhone": "18969188862",
     *               "remark": "test",
     *               "endTime": "2018-07-27T15:00:00+08:00",
     *               "regionIndexCode": "root00000"
     *               }
     * @return 返回结果示例:
     * {
     * "msg": "请求成功！",
     * "code": 200,
     * "data": {
     * "regionIndexCode": "root000000",
     * "alarmSyscode": "a19094f4f8ab4d7bb569090e3ae583e9",
     * "plateNo": "苏EV98L2",
     * "cardNo": ""
     * }
     * }
     */
    @Override
    public HikRst addition(HikAlarmCarDto params) {
        if (StrUtil.isEmpty(params.getPlateNo())) {
            return HikRst.error("车牌号必须传！");
        }
        // 请求入参
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("plateNo", params.getPlateNo());
        jsonBody.put("driver", params.getDriver());
        jsonBody.put("driverPhone", params.getDriverPhone());
        jsonBody.put("remark", params.getRemark());
        jsonBody.put("endTime", params.getEndTime());
        jsonBody.put("regionIndexCode", params.getRegionIndexCode());
        return artemisUtils.commonCall(UrlConst.HIK_ALARMCAR_ADDITION, jsonBody);
    }

    /**
     * 车辆取消布控
     *
     * @param params 布控车辆唯一标识，参数示例:
     *               {
     *               "alarmSyscodes": "06653f7f55fd451992a93d78f0e6ea3f,a19094f4f8ab4d7bb569090e3ae583e9"
     *               }
     * @return 示例
     * {
     * "msg": "请求成功！",
     * "code": 200,
     * "data": null
     * }
     */
    @Override
    public HikRst deletion(HikAlarmCarDto params) {
        if (StrUtil.isEmpty(params.getAlarmSyscodes())) {
            return HikRst.error("车辆唯一标识必须传！");
        }
        // 请求入参
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("alarmSyscodes", params.getAlarmSyscodes());
        return artemisUtils.commonCall(UrlConst.HIK_ALARMCAR_DELETION, jsonBody);
    }

    /**
     * 查询布控车辆
     *
     * @param params 参数示例：{
     *               "searchKey": "浙A12345",
     *               "pageNo": 1,
     *               "pageSize": 15,
     *               "startTime": "2018-07-26T15:00:00+08:00",
     *               "endTime": "2018-07-26T17:00:00+08:00",
     *               "regionIndexCode": "root00000"
     *               }
     * @return 示例：
     * {
     * "msg": "请求成功！",
     * "code": 200,
     * "data": {
     * "total": 2,
     * "pageNo": 1,
     * "pageSize": 15,
     * "list": [
     * {
     * "regionIndexCode": "root000000",
     * "alarmSyscode": "8e339047a784444586a98dcfdece8d5a",
     * "driver": "徐XX",
     * "plateNo": "苏A881SG",
     * "driverPhone": "13701456789",
     * "remark": "test",
     * "endTime": "2021-09-10T12:00:00+08:00",
     * "cardNo": null
     * },
     * {
     * "regionIndexCode": "root000000",
     * "alarmSyscode": "a19094f4f8ab4d7bb569090e3ae583e9",
     * "driver": "测试一下",
     * "plateNo": "苏EV98L2",
     * "driverPhone": "18652057788",
     * "remark": "",
     * "endTime": "2021-11-10T15:00:00+08:00",
     * "cardNo": ""
     * }
     * ]
     * }
     * }
     */
    @Override
    public HikRst page(HikAlarmCarDto params) {
        // 请求入参
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("searchKey", params.getSearchKey());
        jsonBody.put("pageNo", params.getPageNo() == null ? 1 : params.getPageNo());
        jsonBody.put("pageSize", params.getPageSize() == null ? 10 : params.getPageSize());
        jsonBody.put("startTime", params.getStartTime());
        jsonBody.put("endTime", params.getEndTime());
        jsonBody.put("regionIndexCode", params.getRegionIndexCode());
        return artemisUtils.commonCall(UrlConst.HIK_ALARMCAR_PAGE, jsonBody);
    }
}
