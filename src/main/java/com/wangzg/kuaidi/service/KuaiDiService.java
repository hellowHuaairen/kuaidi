package com.wangzg.kuaidi.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wangzg.kuaidi.domain.KuaiDi;
import com.wangzg.kuaidi.util.Message;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KuaiDiService extends IService<KuaiDi> {

    // 查询
    List<KuaiDi> getList(String username, String phone);

    // 新增
    Message<KuaiDi> add(String username, String phone, String kuaiDiNo, String company);

    // 修改
    Message<KuaiDi> modify(Integer id, String username, String phone, String kuaiDiNo, String company);

    // 删除
    Message<KuaiDi> removeById(Integer id);

    //导入文件内容
    void importData(MultipartFile multipartFile) throws Exception;
}
