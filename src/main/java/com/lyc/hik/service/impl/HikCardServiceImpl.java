package com.lyc.hik.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.lyc.hik.common.hik.HikCardDto;
import com.lyc.hik.common.hik.HikConst;
import com.lyc.hik.common.hik.HikRst;
import com.lyc.hik.common.utils.ArtemisUtils;
import com.lyc.hik.common.utils.UrlConst;
import com.lyc.hik.service.HikCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 人员发卡服务实现层
 *
 * @author kisang
 * @date 2021年9月7日10:57:31
 */
@Service("hikCardService")
public class HikCardServiceImpl implements HikCardService {
    private ArtemisUtils artemisUtils;

    @Autowired
    public void setArtemisUtils(ArtemisUtils artemisUtils) {
        this.artemisUtils = artemisUtils;
    }

    /**
     * 人员批量卡口
     *
     * @param params 示例
     *               {
     *               "startDate": "2018-10-30", 必填
     *               "endDate": "2018-10-30", 必填
     *               "cardList": [ 必填
     *               {
     *               "cardNo": "100000001", 必填
     *               "personId": "370d303b-3294-428b-993b-07e6b3f09295", 必填
     *               "orgIndexCode": "root000000",
     *               "cardType": 1
     *               }
     *               ]
     *               }
     * @return 示例
     */
    @Override
    public HikRst bindings(HikCardDto params) {
        if (ObjectUtil.isEmpty(params.getCardList())) {
            return HikRst.error("参数未传！");
        }
        // 开始日期未传默认当天
        if (StrUtil.isEmpty(params.getStartDate())) {
            params.setStartDate(DateUtil.formatDate(new Date()));
        }
        // 结束日期未传2年有效
        if (StrUtil.isEmpty(params.getEndDate())) {
            params.setStartDate(DateUtil.formatDate(DateUtil.offset(new Date(), DateField.YEAR, HikConst.HIK_CARD_PERIOD)));
        }
        // 请求入参
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("startDate", params.getStartDate());
        jsonBody.put("endDate", params.getEndDate());
        jsonBody.put("cardList", params.getCardList());
        // 请求接口
        return artemisUtils.commonCall(UrlConst.HIK_CARD_BINDINGS, jsonBody);
    }

    /**
     * 获取卡片列表
     *
     * @param params 示例
     *               {
     *               "pageNo": 1, 必填
     *               "pageSize": 1000, 必填
     *               "personName": "张三",
     *               "cardNo": "1321424",
     *               "useStatus": 1,
     *               "personIds": "2334565",
     *               "orderBy": "cardNo",
     *               "orderType": "asc"
     *               }
     * @return 示例
     * {
     * "msg": "请求成功！",
     * "code": 200,
     * "data": {
     * "total": 1200,
     * "pageNo": 1,
     * "pageSize": 1,
     * "list": [
     * {
     * "orgName": "江苏省有限公司",
     * "endDate": "2037-12-31T23:59:59.000+08:00",
     * "cardType": 1,
     * "isBindFinger": 1,
     * "orgIndexCode": "aa3d81b1-0db6-457d-9ea8-dfed16ffa430",
     * "updateTime": "2021-09-08T20:55:29.916+08:00",
     * "cardNo": "11001001001100110011",
     * "useStatus": 1,
     * "personName": "person0",
     * "isBindFace": 1,
     * "createTime": "2021-09-08T20:55:29.916+08:00",
     * "cardId": "f3c9059c-5005-408b-a91a-33fed16eb162",
     * "personId": "fc79b31d5f3240a08218cc64bc896509",
     * "startDate": "2021-09-08T00:00:00.000+08:00"
     * }
     * ]
     * }
     * }
     */
    @Override
    public HikRst cardList(HikCardDto params) {
        // 请求入参
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", params.getPageNo() == null ? 1 : params.getPageNo());
        jsonBody.put("pageSize", params.getPageSize() == null ? 1000 : params.getPageSize());
        jsonBody.put("personName", params.getPersonName());
        jsonBody.put("cardNo", params.getCardNo());
        jsonBody.put("useStatus", params.getUseStatus());
        jsonBody.put("personIds", params.getPersonId());
        jsonBody.put("orderBy", params.getOrderBy());
        jsonBody.put("orderType", params.getOrderType());
        // 请求接口
        return artemisUtils.commonCall(UrlConst.HIK_CARD_CARDLIST, jsonBody);
    }

    /**
     * 退卡操作
     *
     * @param params 示例
     *               {
     *               "cardNumber": "100000002", 二者必填一个
     *               "personId": "370d303b-3294-428b-993b-07e6b3f09295"
     *               }
     * @return
     */
    @Override
    public HikRst deletion(HikCardDto params) {
        if (StrUtil.isEmpty(params.getCardNumber()) && StrUtil.isEmpty(params.getPersonId())) {
            return HikRst.error("卡口或人员唯一标示必须传！");
        }
        // 请求入参
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("cardNumber", params.getCardNumber());
        jsonBody.put("personId", params.getPersonId());
        // 请求接口
        return artemisUtils.commonCall(UrlConst.HIK_CARD_DELETION, jsonBody);
    }

    /**
     * 获取单个卡片信息
     *
     * @param params 示例
     *               {
     *               "cardNo": "12345678" 必传
     *               }
     * @return 示例
     * {
     * "msg": "请求成功！",
     * "code": 200,
     * "data": {
     * "personName": "xxx",
     * "endDate": "2022-09-10T23:59:59.000+08:00",
     * "lossDate": null,
     * "cardId": "ae0b9754-1165-11ec-bb88-4f856c67e116",
     * "unlossDate": null,
     * "personId": "f830107bcb7d40328faedabd1d6ca45f",
     * "cardNo": "412124198807051425",
     * "startDate": "2021-09-09T00:00:00.000+08:00",
     * "useStatus": 1
     * }
     * }
     */
    @Override
    public HikRst cardInfo(HikCardDto params) {
        if (StrUtil.isEmpty(params.getCardNo())) {
            return HikRst.error("卡号必须传!");
        }
        // 请求入参
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("cardNo", params.getCardNo());
        // 请求接口
        return artemisUtils.commonCall(UrlConst.HIK_CARD_CARDINFO, jsonBody);
    }

    /**
     * 生成卡片二维码
     * 用于生产卡片二维码，二维码默认有效期为24*60分钟，默认最大开锁次数4次.
     *
     * @param params 示例
     *               {
     *               "personId": "f6c70bfeebc14838854f2f14620ab5ee",  二者必填一个
     *               "cardNo": "1000000001",
     *               "certType": 111,
     *               "certNum": "330231232132132314481",
     *               "phone": "18969145145",
     *               "employeeId": "12345678",
     *               "duration": 1440,
     *               "maxLock": 4
     *               }
     * @return 示例
     */
    @Override
    public HikRst barCode(HikCardDto params) {
        if (StrUtil.isEmpty(params.getPersonId()) && StrUtil.isEmpty(params.getCardNo())) {
            return HikRst.error("卡号或人员唯一标示必须填一个");
        }
        // 请求入参
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("personId", params.getCardNo());
        jsonBody.put("cardNo", params.getCardNo());
        jsonBody.put("certType", params.getCardNo());
        jsonBody.put("certNum", params.getCardNo());
        jsonBody.put("phone", params.getCardNo());
        jsonBody.put("employeeId", params.getCardNo());
        jsonBody.put("duration", params.getCardNo());
        jsonBody.put("maxLock", params.getCardNo());
        // 请求接口
        return artemisUtils.commonCall(UrlConst.HIK_CARD_BARCODE, jsonBody);
    }
}
