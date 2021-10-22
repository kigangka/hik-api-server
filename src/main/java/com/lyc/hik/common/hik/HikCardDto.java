package com.lyc.hik.common.hik;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 人员开卡Dto
 *
 * @author kisang
 * @date 2021年9月8日21:14:12
 */
@Data
public class HikCardDto {
    /**
     * 开卡生效日期,时间格式：yyyy-MM-dd(必传)(新增)
     * (新增)
     */
    @ApiModelProperty(value = "开卡生效日期,时间格式：yyyy-MM-dd(必传)")
    private String startDate;

    /**
     * 卡片截止有效时间,时间格式：yyyy-MM-dd 不能大于2037年12月31日(必传)
     * (新增)
     */
    @ApiModelProperty(value = "卡片截止有效时间,时间格式：yyyy-MM-dd(必传)")
    private String endDate;

    /**
     * 人员信息列表
     * (新增)
     */
    @ApiModelProperty(value = "人员信息列表(必传)")
    private List<HikCardInfo> cardList;

    /**
     * 当前页，默认1
     * （查询）
     */
    @ApiModelProperty(value = "当前页（查询）（必传）")
    private Integer pageNo;

    /**
     * 每页数量，默认1000
     * （查询）
     */
    @ApiModelProperty(value = "每页数量（查询）（必传）")
    private Integer pageSize;

    /**
     * 人员姓名,模糊查询
     * （查询）
     */
    @ApiModelProperty(value = "人员姓名,模糊查询（查询）")
    private String personName;

    /**
     * 卡号,模糊查询
     * （查询）
     */
    @ApiModelProperty(value = "卡号,模糊查询（查询）")
    private String cardNo;

    /**
     * 使用状态标记，1：正常 2：挂失
     * （查询）
     */
    @ApiModelProperty(value = "使用状态标记，1：正常 2：挂失")
    private Integer useStatus;

    /**
     * 人员ID集，多个值使用英文逗号分隔，不超过1000个
     * （查询）
     */
    @ApiModelProperty(value = "人员ID集，多个值使用英文逗号分隔，不超过1000个")
    private String personIds;

    /**
     * 排序字段必须是查询条件
     * （查询）
     */
    @ApiModelProperty(value = "排序字段必须是查询条件（查询）")
    private String orderBy;

    /**
     * 降序：desc 升序：asc
     * （查询）
     */
    @ApiModelProperty(value = "降序：desc 升序：asc（查询）")
    private String orderType;

    /**
     * 卡号
     * （退卡）
     */
    @ApiModelProperty(value = "卡号（退卡）")
    private String cardNumber;

    /**
     * 人员唯一标示
     * （退卡）
     */
    @ApiModelProperty(value = "人员唯一标示（退卡）")
    private String personId;

    /**
     * 证件类型，当人员唯一标示是证件类型+证件号码时必填，111:身份证,414:护照,113:户口簿,335:驾驶证,131:工作证,133:学生证,990:其他
     * （二维码）
     */
    @ApiModelProperty(value = "证件类型（二维码）")
    private Integer certType;

    /**
     * 证件号码，当人员唯一标示是证件类型+证件号码时必填
     * （二维码）
     */
    @ApiModelProperty(value = "证件号码（二维码）")
    private String certNum;

    /**
     * 手机号码，当人员唯一标示是手机号码时必填
     * （二维码）
     */
    @ApiModelProperty(value = "手机号码（二维码）")
    private String phone;

    /**
     * 工号，当人员唯一标示是工号时必填
     */
    @ApiModelProperty(value = "工号（二维码）")
    private String employeeId;

    /**
     * 人员卡片二维码有效期时长 范围为[5~7*24*60]分钟，不传默认为24*60分钟
     */
    @ApiModelProperty(value = "人员卡片二维码有效期时长（二维码）")
    private Integer duration;

    /**
     * 最大开锁次数，其中针对同一台设备最大开锁次数不超过指定的次数，不传默认最大开锁次数为4次
     */
    @ApiModelProperty(value = "最大开锁次数（二维码）")
    private Integer maxLock;
}
