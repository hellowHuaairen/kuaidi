package com.wangzg.kuaidi.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 快递单号查询实体类
 */
@Data
@Builder
@TableName("kuaidi")
public class KuaiDi {
    public KuaiDi(){}
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

    @TableField("ID")
    private Integer id;

    /**
     * 收件人姓名
     */
    @TableField("USER_NAME")
    private String userName;
    /**
     * 收件人电话
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 快递单号
     */
    @TableField("KUAIDI_NO")
    private String kuaidiNo;

    /**
     * 快递公司名称（拼音）
     */
    @TableField("COMPANY")
    private String company;

    /**
     * 订单创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

}
