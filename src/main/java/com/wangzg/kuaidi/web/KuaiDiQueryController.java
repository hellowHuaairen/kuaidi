package com.wangzg.kuaidi.web;



import com.wangzg.kuaidi.service.KuaiDiQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class KuaiDiQueryController {

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


}
