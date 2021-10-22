package com.lyc.hik.service;

import com.lyc.hik.common.hik.HikCardDto;
import com.lyc.hik.common.hik.HikRst;

/**
 * 人员开卡服务
 *
 * @author kisang
 * @date 2021年9月6日20:48:54
 */
public interface HikCardService {
    /**
     * 批量人员开卡
     * 该接口主要是应用于对多个人同时开卡的场景，输入卡片开始有效日期、卡片截止有效日期以及对应的人员、卡片关联列表，实现对多个人员同时开卡的功能，开卡成功后，可以到相应子系统开启卡片的权限，例如到门禁子系统开启人员门禁权限。
     *
     * @param params 参数
     * @return 返回结果
     */
    HikRst bindings(HikCardDto params);

    /**
     * 获取卡片列表
     * 查询卡片列表接口可以根据卡片号码、人员姓名、卡片状态、人员ID集合等查询条件来进行高级查询；若不指定查询条件，即全量获取所有的卡片信息。返回结果分页展示
     *
     * @param params 参数
     * @return 返回结果
     */
    HikRst cardList(HikCardDto params);

    /**
     * 退卡操作
     * 该接口主要是应用于对人员下卡片进行退卡，输入卡号以及所属人员id，实现卡片退卡的功能。退卡成功后，相应子系统的卡片权限清除，例如所属卡片在门禁子系统的门禁权限全部清除
     *
     * @param params 参数
     * @return 返回结果
     */
    HikRst deletion(HikCardDto params);

    /**
     * 获取单个卡片信息
     * 获取卡片列表接口可用来全量同步卡片信息，返回结果分页展示，不作权限过滤。 注：卡号为精确查找
     *
     * @param params 参数
     * @return 返回结果
     */
    HikRst cardInfo(HikCardDto params);

    /**
     * 生成卡片二维码
     * 用于生产卡片二维码，二维码默认有效期为24*60分钟，默认最大开锁次数4次.
     *
     * @param params 参数
     * @return 返回结果
     */
    HikRst barCode(HikCardDto params);
}
