package com.wangzg.kuaidi.service.impl;

import com.wangzg.kuaidi.domain.KuaiDi;
import com.wangzg.kuaidi.mapper.KuaiDiMapper;
import com.wangzg.kuaidi.service.KuaiDiService;
import com.wangzg.kuaidi.util.Message;
import com.wangzg.kuaidi.util.MessageEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KuaiDiServiceImpl implements KuaiDiService {

//    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KuaiDiMapper kuaiDiMapper;


    @Override
    public List<KuaiDi> getList(String username, String phone) {

        username = StringUtils.defaultString(username);
        phone = StringUtils.defaultString(phone);

        KuaiDi kuaiDi = KuaiDi.builder()
                .userName(username.trim()).phone(phone.trim()).build();
        return kuaiDiMapper.selectList(kuaiDi);
    }

    @Override
    public Message<KuaiDi> add(String username, String phone, String kuaiDiNo) {

        Message msg;
        username = StringUtils.defaultString(username);
        phone = StringUtils.defaultString(phone);
        kuaiDiNo = StringUtils.defaultString(kuaiDiNo);

        KuaiDi kuaiDi = KuaiDi.builder()
                .userName(username.trim()).phone(phone.trim())
                .kuaidiNo(kuaiDiNo).build();

        int rowCount = kuaiDiMapper.insert(kuaiDi);
        if (rowCount == 0) {
            msg = new Message(MessageEnum.FAIL.getCode(), "新增失败！");
        } else {
            msg = new Message(MessageEnum.SUCCESS.getCode(), "新增成功！");
        }
        return msg;
    }

    @Override
    public Message<KuaiDi> modify(Integer id, String username, String phone, String kuaiDiNo) {

        Message msg;
        username = StringUtils.defaultString(username);
        phone = StringUtils.defaultString(phone);
        kuaiDiNo = StringUtils.defaultString(kuaiDiNo);

        KuaiDi kuaiDi = KuaiDi.builder()
                .id(id).userName(username.trim()).phone(phone.trim())
                .kuaidiNo(kuaiDiNo).build();

        int rowCount = kuaiDiMapper.update(kuaiDi);
        if (rowCount == 0) {
            msg = new Message(MessageEnum.FAIL.getCode(), "修改失败！");
        } else {
            msg = new Message(MessageEnum.SUCCESS.getCode(), "修改成功！");
        }

        return msg;
    }

    @Override
    public Message<KuaiDi> removeById(Integer id) {
        Message msg;
        int rowCount = kuaiDiMapper.deleteById(id);
        if (rowCount == 0) {
            msg = new Message(MessageEnum.FAIL.getCode(), "删除失败！");
        }else {
            msg = new Message(MessageEnum.SUCCESS.getCode(), "删除成功！");
        }
        return msg;
    }

}
