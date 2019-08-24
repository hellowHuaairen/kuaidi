package com.wangzg.kuaidi.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 快递单号查询实体类
 */
@Data
@Builder
public class KuaiDi {

    private Integer id;

    /**
     * 收件人姓名
     */
    private String userName;
    /**
     * 收件人电话
     */
    private String phone;

    /**
     * 快递单号
     */
    private String kuaidiNo;

}
