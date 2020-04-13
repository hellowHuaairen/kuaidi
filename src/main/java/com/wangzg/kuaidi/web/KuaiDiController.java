package com.wangzg.kuaidi.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangzg.kuaidi.domain.KuaiDi;
import com.wangzg.kuaidi.service.KuaiDiService;
import com.wangzg.kuaidi.util.Message;
import com.wangzg.kuaidi.util.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
public class KuaiDiController {

    @Autowired
    private KuaiDiService kuaiDiService;


    @RequestMapping("/getList")
    public @ResponseBody
    PaginationResult getList(int offset, int limit, String userName, String phone) {
        Page<Object> page = PageHelper.offsetPage(offset, limit);
        List<KuaiDi> kuaiDiList = kuaiDiService.getList(userName, phone);
        return new PaginationResult(page.getTotal(), kuaiDiList);
    }

    @PostMapping("/addKuaiDi")
    public @ResponseBody
    Message addKuaiDi(String userName, String phone, String kuaiDiNo, String company) {
        return kuaiDiService.add(userName, phone, kuaiDiNo, company);
    }

    @PostMapping("/delKuaiDi")
    public @ResponseBody
    Message delKuaiDi(Integer id) {
        return kuaiDiService.removeById(id);
    }

    @PostMapping("/modifyKuaiDi")
    public @ResponseBody
    Message modifyKuaiDi(Integer id, String userName, String phone, String kuaiDiNo, String company) {
        return kuaiDiService.modify(id, userName, phone, kuaiDiNo, company);
    }

    @PostMapping("/import")
    public Message importData(MultipartFile multipartFile) throws Exception{
        kuaiDiService.importData(multipartFile);
        return  new Message(200);
    }
}
