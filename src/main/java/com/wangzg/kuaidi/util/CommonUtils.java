package com.wangzg.kuaidi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wangzg.kuaidi.domain.ResponseData;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by wangzhiguo on 2019/8/29 0029.
 */
public class CommonUtils {

    /**
     * 将json字符串转化为对象
     * @param jsonStr
     * @return
     */
    public static ResponseData convertJsonStr2Object(String jsonStr){
        ResponseData responseData = new ResponseData();
        if(StringUtils.isNotEmpty(jsonStr)){
            responseData = JSON.parseObject(jsonStr, new TypeReference<ResponseData>(){});
        }
        return responseData;
    }

    public static void main(String[] args) {
        String jsonStr = "{\n" +
                "    \"message\":\"ok\",\n" +
                "    \"state\":\"0\",\n" +
                "    \"status\":\"200\",\n" +
                "    \"condition\":\"F00\",\n" +
                "    \"ischeck\":\"0\",\n" +
                "    \"com\":\"yuantong\",\n" +
                "    \"nu\":\"V030344422\",\n" +
                "    \"data\":[\n" +
                "        {\n" +
                "            \"context\":\"上海分拨中心/装件入车扫描 \",\n" +
                "            \"time\":\"2012-08-28 16:33:19\",\n" +
                "            \"ftime\":\"2012-08-28 16:33:19\",\n" +
                "        },\n" +
                "        {\n" +
                "            \"context\":\"上海分拨中心/下车扫描 \",\n" +
                "            \"time\":\"2012-08-27 23:22:42\",\n" +
                "            \"ftime\":\"2012-08-27 23:22:42\",\n" +
                "        }]\n" +
                "}";

        System.out.println(CommonUtils.convertJsonStr2Object(jsonStr));
    }
}
