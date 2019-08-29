package com.wangzg.kuaidi.web;



import com.wangzg.kuaidi.domain.KuaiDi;
import com.wangzg.kuaidi.domain.Response;
import com.wangzg.kuaidi.domain.ResponseData;
import com.wangzg.kuaidi.service.KuaiDiQueryService;
import com.wangzg.kuaidi.service.KuaiDiService;
import com.wangzg.kuaidi.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class KuaiDiQueryController {

    @Autowired
    private KuaiDiService kuaiDiService;
    @Autowired
    private KuaiDiQueryService kuaiDiQueryService;


//    @RequestMapping("/getKuaiDiInfo")
//    public String queryKuadiInfoByPhone(@RequestParam("com")String com, @RequestParam("no")String no, Model model) {
//		System.out.println("com:"+com + "no:"+ no);
//        String resultJson = kuaiDiQueryService.synQueryData(com, no,"", "", "", 0);
//		System.out.println("resultJson:"+resultJson);
//        model.addAttribute("name", "name123213");
//		model.addAttribute("resultJson", resultJson);
//        return "detail";
//    }

    /**
     * 返回json数据
     * @param com
     * @param no
     * @return
     */
    @GetMapping("/getKuaiDiInfoByJson")
    @ResponseBody
    public String queryKuadiInfoByJson(String com, String no) {
        return kuaiDiQueryService.synQueryData(com, no,"", "", "", 0);
    }

    @GetMapping("/getKuaiDiInfoByPhone")
    @ResponseBody
    public Response queryKuaidiByPhone(String phone){
        Response response = new Response();
        if(StringUtils.isNotEmpty(phone)){
            List<ResponseData> responseDataList = new ArrayList<>();
            // todo 1.通过手机号查询下面的所有订单号
            List<KuaiDi> kuaiDiList = kuaiDiService.getList("", phone);
            if(!CollectionUtils.isEmpty(kuaiDiList)){
                kuaiDiList.forEach(kuaiDi -> {
                    // todo 2.依次查出所有的订单号
                    String responseDataStr = kuaiDiQueryService.synQueryData(kuaiDi.getCompany(), kuaiDi.getKuaidiNo(),"", "", "", 0);
                    ResponseData responseData = CommonUtils.convertJsonStr2Object(responseDataStr);
                    responseDataList.add(responseData);
                });
            }
            //  todo 3.组装数据返回给前台
            response.setDataList(responseDataList);
        }
        return response;
    }


}
