package com.wangzg.kuaidi.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by wangzhiguo on 2019/8/29 0029.
 */
@Data
public class ResponseData {
    private String message;
    private String state;
    private String status;
    private String condition;
    private String ischeck;
    private String com;
    private String nu;
    private List<KuaidiInfo> data;
}
