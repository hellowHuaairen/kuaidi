package com.wangzg.kuaidi.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.wangzg.kuaidi.domain.KuaiDi;
import org.apache.commons.beanutils.BeanUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.apache.commons.beanutils.BeanUtils.populate;

/**
 * @author wangzg
 * @date 2020/4/13
 */
public class ExcelUtils {

    /**
     * 获取第一个sheet页的内容
     * @param inputStream
     * @return
     */
    public static List<KuaiDi> getKuaiDiList(InputStream inputStream)  {
        List<KuaiDi> kuaiDiList = new ArrayList<>();
        if(Objects.nonNull(inputStream)){
            ExcelReader excelReader = ExcelUtil.getReader(inputStream);
            excelReader.addHeaderAlias("快递单号","kuaidiNo");
            excelReader.addHeaderAlias("用户名","userName");
            excelReader.addHeaderAlias("电话","phone");
            excelReader.addHeaderAlias("快递公司","company");
            List<Map<String,Object>> rowList = excelReader.readAll();
            if(CollectionUtil.isNotEmpty(rowList)){
                rowList.stream().forEach(r->{
                    KuaiDi kuaiDi = new KuaiDi();
                    try {
                        populate(kuaiDi,r);
                        kuaiDi.setCreateTime(new Date());
                        kuaiDiList.add(kuaiDi);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        return kuaiDiList;
    }

    public static void main(String[] args) {
        List<KuaiDi> kuaiDiList = getKuaiDiList(ResourceUtil.getStream("D:\\快递.xls"));
        kuaiDiList.stream().forEach(System.out::println);
    }
}
