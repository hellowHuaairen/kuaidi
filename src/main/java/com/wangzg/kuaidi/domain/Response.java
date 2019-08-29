package com.wangzg.kuaidi.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by wangzhiguo on 2019/8/29 0029.
 */
@Data
public class Response {
    private String message = "ok";
    private String state="0";
    private String status ="200";
    private List<ResponseData> dataList;
}
