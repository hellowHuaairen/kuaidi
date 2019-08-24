package com.wangzg.kuaidi.mapper;

import com.wangzg.kuaidi.domain.KuaiDi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface KuaiDiMapper {

    // 查询
    List<KuaiDi> selectList(KuaiDi kuaiDi);

    // 新增
    int insert(KuaiDi kuaiDi);

    // 修改
    int update(KuaiDi kuaiDi);

    // 删除
    int deleteById(@Param("id") Integer id);
}
