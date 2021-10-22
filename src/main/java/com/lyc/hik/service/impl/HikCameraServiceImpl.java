package com.lyc.hik.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.lyc.hik.common.hik.HikCameraDto;
import com.lyc.hik.common.hik.HikConst;
import com.lyc.hik.common.hik.HikRst;
import com.lyc.hik.common.utils.ArtemisUtils;
import com.lyc.hik.common.utils.UrlConst;
import com.lyc.hik.service.HikCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 海康视频应用服务实现层
 *
 * @author kisang
 * @date 2021年8月23日15:15:27
 */
@Service("hikCameraService")
public class HikCameraServiceImpl implements HikCameraService {

    private ArtemisUtils artemisUtils;

    @Autowired
    public void setArtemisUtils(ArtemisUtils artemisUtils) {
        this.artemisUtils = artemisUtils;
    }

    /**
     * 获取监控点预览取流URLv2
     *
     * @param params 参数
     * @return 返回结果
     */
    @Override
    public HikRst previewURLs(HikCameraDto params) {
        if (StrUtil.isEmpty(params.getCameraIndexCode())) {
            return HikRst.error("监测点编号必须传！");
        }
        // 请求入参
        JSONObject jsonBody = new JSONObject();
        jsonBody.put(HikConst.STR_CIC, params.getCameraIndexCode());
        jsonBody.put(HikConst.STR_STREAMTYPE, params.getStreamType() != null ? params.getStreamType() : HikConst.INTEGER_0);
        jsonBody.put(HikConst.STR_PROTOCOL, StrUtil.isNotEmpty(params.getProtocol()) ? params.getProtocol() : "ws");
        jsonBody.put(HikConst.STR_TRANSMODE, params.getTransmode() != null ? params.getTransmode() : HikConst.INTEGER_1);
        return commonCall(UrlConst.HIK_CAMERA_PREVIEWURLS, jsonBody);
    }

    /**
     * 获取监控点回放取流URLv2
     *
     * @param params 参数
     * @return 返回结果
     */
    @Override
    public HikRst playbackURLs(HikCameraDto params) {
        if (StrUtil.isEmpty(params.getCameraIndexCode())) {
            return HikRst.error("监测点编号必须传！");
        }
        if (StrUtil.isEmpty(params.getBeginTime())) {
            return HikRst.error("开始时间必须传！");
        }
        if (StrUtil.isEmpty(params.getEndTime())) {
            return HikRst.error("结束时间必须传！");
        }
        // 请求入参
        JSONObject jsonBody = new JSONObject();
        jsonBody.put(HikConst.STR_CIC, params.getCameraIndexCode());
        jsonBody.put(HikConst.STR_PROTOCOL, StrUtil.isNotEmpty(params.getProtocol()) ? params.getProtocol() : "ws");
        jsonBody.put(HikConst.STR_TRANSMODE, params.getTransmode() != null ? params.getTransmode() : HikConst.INTEGER_1);
        jsonBody.put(HikConst.STR_BEGINTIME, params.getBeginTime());
        jsonBody.put(HikConst.STR_ENDTIME, params.getEndTime());

        return commonCall(UrlConst.HIK_CAMERA_PLAYBACKURLS, jsonBody);
    }

    private HikRst commonCall(String url, JSONObject jsonBody) {
        // 调用海康服务
        String rstJson = artemisUtils.doPostArtemis(url, jsonBody);
        // 处理返回结果
        JSONObject objRst = null;
        if (StrUtil.isNotEmpty(rstJson)) {
            objRst = JSONObject.parseObject(rstJson);
            if (StrUtil.equals(objRst.getString(HikConst.KEY_CODE), HikConst.STR_ZERO)) {
                return HikRst.ok(objRst.get(HikConst.KEY_DATA));
            }
            return HikRst.error(objRst.getString(HikConst.KEY_MSG));
        }
        return HikRst.error("返回结果异常！");
    }
}
