package com.lyc.hik.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.lyc.hik.common.hik.HikConst;
import com.lyc.hik.common.hik.HikPersonDto;
import com.lyc.hik.common.hik.HikRst;
import com.lyc.hik.common.utils.ArtemisUtils;
import com.lyc.hik.common.utils.PicUtils;
import com.lyc.hik.common.utils.UrlConst;
import com.lyc.hik.service.HikPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 人员信息应用服务实现层
 *
 * @author kisang
 * @date 2021年8月23日15:15:27
 */
@Service("hikPersonService")
public class HikPersonServiceImpl implements HikPersonService {

    private ArtemisUtils artemisUtils;

    @Autowired
    public void setArtemisUtils(ArtemisUtils artemisUtils) {
        this.artemisUtils = artemisUtils;
    }

    /**
     * 人员信息
     *
     * @param params 参数
     * @return 返回结果
     */
    @Override
    public HikRst add(HikPersonDto params) {
        if (params == null) {
            return HikRst.error("人员信息不能为空！");
        }
        if (StrUtil.isBlank(params.getPersonName()) || StrUtil.isBlank(params.getOrgIndexCode()) || StrUtil.isBlank(params.getFaceDataURL())) {
            return HikRst.error("姓名,组织编码,人脸图片不能为空！");
        }
        StringBuilder body = new StringBuilder();
        body.append("{");
        body.append("\"personName\": \"" + params.getPersonName() + "\",");
        body.append("\"gender\": \"1\",");
        body.append("\"orgIndexCode\": \"" + params.getOrgIndexCode() + "\",");
        if (StrUtil.isNotEmpty(params.getBirthday())) {
            body.append("\"birthday\": \"" + params.getBirthday() + "\",");
        }
        body.append("\"phoneNo\": \"" + params.getPhoneNo() + "\",");
        if (StrUtil.isNotEmpty(params.getBirthday())) {
            body.append("\"email\": \"" + params.getEmail() + "\",");
        }
        // 默认为身份证号
        body.append("\"certificateType\": \"111\",");
        body.append("\"certificateNo\": \"" + params.getCertificateNo() + "\",");
        if (StrUtil.isNotEmpty(params.getBirthday())) {
            body.append("\"jobNo\": \"" + params.getJobNo() + "\",");
        }
        body.append(" \"faces\": [");
        body.append("{");
        body.append("\"faceData\": \"" + PicUtils.netImgToBase64(params.getFaceDataURL()) + "\"");
        body.append("}");
        body.append("]");
        body.append("}");
        String rst = artemisUtils.doPostArtemis(UrlConst.HIK_PERSON_ADD, body.toString());
        return commonCall(rst);
    }

    /**
     * 更新人员信息
     *
     * @param params 参数
     * @return 返回结果
     */
    @Override
    public HikRst update(HikPersonDto params) {
        if (params == null || StrUtil.isBlank(params.getPersonId())) {
            return HikRst.error("人员信息不能为空！");
        }
        StringBuilder body = new StringBuilder();
        body.append("{");
        body.append("\"personId\": \"" + params.getPersonId() + "\",");
        body.append("\"personName\": \"" + params.getPersonName() + "\",");
        if (StrUtil.isNotBlank(params.getGender()) && NumberUtil.isNumber(params.getGender())) {
            body.append("\"gender\": " + Integer.valueOf(params.getGender()) + ",");
        } else {
            body.append("\"gender\": 0,");
        }
        body.append("\"orgIndexCode\": \"" + params.getOrgIndexCode() + "\",");
        body.append("\"birthday\": \"" + params.getBirthday() + "\",");
        body.append("\"phoneNo\": \"" + params.getPhoneNo() + "\",");
        body.append("\"email\": \"" + params.getEmail() + "\",");
        // 默认为身份证号
        body.append("\"certificateType\": \"111\",");
        body.append("\"certificateNo\": \"" + params.getCertificateNo() + "\",");
        body.append("\"jobNo\": \"" + params.getJobNo() + "\"");
        body.append("}");
        String rst = artemisUtils.doPostArtemis(UrlConst.HIK_PERSON_UPDATE, body.toString());
        return commonCall(rst);
    }

    /**
     * 批量删除人员
     * ids 逗号分隔
     *
     * @param ids 参数
     * @return 返回结果
     */
    @Override
    public HikRst delete(String ids) {
        if (StrUtil.isBlank(ids)) {
            return HikRst.error("人员信息不能为空！");
        }
        JSONObject paramJson = new JSONObject();
        paramJson.put("personIds", ids.split(","));
        String rst = artemisUtils.doPostArtemis(UrlConst.HIK_PERSON_DELETE, paramJson.toJSONString());
        return commonCall(rst);
    }

    private HikRst commonCall(String rstJson) {
        // 处理返回结果
        if (StrUtil.isNotEmpty(rstJson)) {
            JSONObject objRst = JSONObject.parseObject(rstJson);
            if (StrUtil.equals(objRst.getString(HikConst.KEY_CODE), HikConst.STR_ZERO)) {
                return HikRst.ok(objRst.get(HikConst.KEY_DATA));
            }
            return HikRst.error(objRst.getString(HikConst.KEY_MSG));
        }
        return HikRst.error("返回结果异常！");
    }
}
