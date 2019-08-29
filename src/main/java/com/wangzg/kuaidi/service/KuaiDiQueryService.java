package com.wangzg.kuaidi.service;

import java.util.Map;

/**
 * Created by wangzhiguo on 2019/8/28 0028.
 */
public interface KuaiDiQueryService {

    /**
     * 实时查询快递单号
     * @param com			快递公司编码
     * @param num			快递单号
     * @param phone			手机号
     * @param from			出发地城市
     * @param to			目的地城市
     * @param resultv2		开通区域解析功能：0-关闭；1-开通
     * @return
     */
     String synQueryData(String com, String num, String phone, String from, String to, int resultv2);

    /**
     * 发送post请求
     */
     String post(Map<String, String> params);
}
