package com.wangzg.kuaidi.service;


import com.wangzg.kuaidi.domain.KuaiDi;
import com.wangzg.kuaidi.util.Message;

import java.util.List;

public interface KuaiDiService {

    // 查询
    List<KuaiDi> getList(String username, String phone);

    // 新增
    Message<KuaiDi> add(String username, String phone, String kuaiDiNo, String company);

    // 修改
    Message<KuaiDi> modify(Integer id, String username, String phone, String kuaiDiNo, String company);

    // 删除
    Message<KuaiDi> removeById(Integer id);
}
