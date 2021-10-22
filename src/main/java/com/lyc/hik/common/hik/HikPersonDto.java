package com.lyc.hik.common.hik;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 海康开发者平台信息
 *
 * @author lyc
 * @date 2021年3月2日16:22:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HikPersonDto {


    /**
     * （更新时必填）人员ID，可以指定人员personId，不允许与其他人员personId重复，包括已删除的人员。 为空时平台自动生成人员ID
     */
    private String personId;
    /**
     * （新增时必填）人员名称，1~32个字符；不能包含 ' / \ : * ? " < > | 这些特殊字符
     */
    private String personName;
    /**
     * 性别，1：男；2：女；0：未知
     */
    private String gender;
    /**
     * 所属组织标识，必须是已存在组织
     */
    private String orgIndexCode;
    /**
     * 出生日期，举例：1992-09-12
     */
    private String birthday;
    /**
     * 手机号，1-20位数字,
     */
    private String phoneNo;
    /**
     * 邮箱，举例：hic@163.com
     */
    private String email;
    /**
     * 证件类型 默认为111，身份证
     */
    private String certificateType;
    /**
     * 证件号码，默认未身份证
     */
    private String certificateNo;
    /**
     * 员工编号
     */
    private String jobNo;
    /**
     * 人脸照片URL
     */
    private String faceDataURL;
}
