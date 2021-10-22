package com.lyc.hik.common.hik;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 人员开卡信息
 *
 * @author kisang
 * @date 2021年9月8日21:14:12
 */
@Data
public class HikCardInfo {
    /*{
        "startDate": "2021-09-08",
            "endDate": "2021-10-30",
            "cardList": [
        {
            "cardNo": "320724198903076314",
                "personId": "f830107bcb7d40328faedabd1d6ca45f",
                "orgIndexCode": "",
                "cardType": 1
        }
    ]
    }*/

    /**
     * 卡口，默认使用身份证号(必传)(新增)
     */
    @ApiModelProperty(value = "卡口，默认使用身份证号(必传)")
    private String cardNo;

    /**
     * 人员唯一标示，调用添加人员信息时获取(必传)(新增)
     */
    @ApiModelProperty(value = "人员唯一标示，调用添加人员信息时获取(必传)")
    private String personId;

    /**
     * 人员所属组织(新增)
     */
    @ApiModelProperty(value = "人员所属组织")
    private String orgIndexCode;

    /**
     * 卡片类型,1：IC卡；2：CPU卡；3：远距离卡；4：MIFARE卡，默认1-IC卡(新增)
     */
    @ApiModelProperty(value = "卡片类型,1：IC卡；2：CPU卡；3：远距离卡；4：MIFARE卡，默认1-IC卡")
    private String cardType;
}
