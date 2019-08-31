package com.wangzg.kuaidi.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 快递单号查询实体类
 */
@Data
@Builder
public class KuaiDi {
    public KuaiDi(Integer id, String userName, String phone, String kuaidiNo, String company, Date createTime) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.kuaidiNo = kuaidiNo;
        this.company = company;
        this.createTime = createTime;
    }
    public KuaiDi(Integer id, String userName, String phone, String kuaidiNo, String company) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.kuaidiNo = kuaidiNo;
        this.company = company;
    }

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

    /**
     * 快递公司名称（拼音）
     */
    private String company;

    /**
     * 订单创建时间
     */
    private Date createTime;

}
