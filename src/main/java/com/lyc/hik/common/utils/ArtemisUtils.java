package com.lyc.hik.common.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.lyc.hik.common.hik.HikConst;
import com.lyc.hik.common.hik.HikRst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 海康安防平台封装工具类
 *
 * @author kisang
 * @date 2021年1月13日 16:11:23
 */
@Slf4j
@Component
public class ArtemisUtils {
    // *--- 海康平台信息开始 ---*
    /**
     * artemis网关服务器ip端口
     */
    @Value("${ArtemisConfig.host}")
    private String host;
    /**
     * 秘钥appkey
     */
    @Value("${ArtemisConfig.app_key}")
    private String appKey;
    /**
     * 秘钥appSecret
     */
    @Value("${ArtemisConfig.app_secret}")
    private String appSecret;
    // *--- 海康平台信息结束 ---*

    // *--- 海康平台常量及接口开始 ---*
    /**
     * 设置OpenAPI接口的上下文
     */
    private static final String ARTEMIS_PATH = "/artemis";
    /**
     * 参数提交方式
     */
    private static final String CONTENT_TYPE = "application/json";
    // *--- 海康平台常量及接口结束 ---*

    /**
     * 执行海康平台接口请求（共通）
     *
     * @param url      请求地址
     * @param jsonBody 请求参数JSON格式
     * @return 结果
     */
    public String doPostArtemis(String url, JSONObject jsonBody) {
        // 设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
        ArtemisConfig.host = host;
        ArtemisConfig.appKey = appKey;
        ArtemisConfig.appSecret = appSecret;
        // 设置接口的URI地址
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                //根据现场环境部署确认是http还是https
                put("https://", ARTEMIS_PATH + url);
            }
        };
        // 调用接口,post请求application/json类型参数
        return ArtemisHttpUtil.doPostStringArtemis(path, jsonBody.toJSONString(), null, null, CONTENT_TYPE, null);
    }

    /**
     * 执行海康平台接口请求（共通）
     *
     * @param url     请求地址
     * @param jsonStr 请求参数，json字符串
     * @return 结果
     */
    public String doPostArtemis(String url, String jsonStr) {
        // 设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
        ArtemisConfig.host = host;
        ArtemisConfig.appKey = appKey;
        ArtemisConfig.appSecret = appSecret;
        // 设置接口的URI地址
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                //根据现场环境部署确认是http还是https
                put("https://", ARTEMIS_PATH + url);
            }
        };
        // 调用接口,post请求application/json类型参数
        return ArtemisHttpUtil.doPostStringArtemis(path, jsonStr, null, null, CONTENT_TYPE, null);
    }

    /**
     * 海康返回结果处理
     * @param url 请求地址
     * @param jsonBody 参数
     * @return 返回结果
     */
    public HikRst commonCall(String url, JSONObject jsonBody) {
        // 调用海康服务
        String rstJson = doPostArtemis(url, jsonBody);
        // 处理返回结果
        JSONObject objRst;
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
