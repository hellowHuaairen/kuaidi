package com.wangzg.kuaidi.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangzg.kuaidi.domain.KuaiDi;
import com.wangzg.kuaidi.mapper.KuaiDiMapper;
import com.wangzg.kuaidi.service.KuaiDiService;
import com.wangzg.kuaidi.util.ExcelUtils;
import com.wangzg.kuaidi.util.Message;
import com.wangzg.kuaidi.util.MessageEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class KuaiDiServiceImpl extends ServiceImpl<KuaiDiMapper, KuaiDi> implements KuaiDiService {

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
    public Message<KuaiDi> add(String username, String phone, String kuaiDiNo, String company) {

        Message msg;

        KuaiDi kuaiDi = buildKuaiDiObject(username, phone, kuaiDiNo, company);
        int rowCount = kuaiDiMapper.insert(kuaiDi);
        if (rowCount == 0) {
            msg = new Message(MessageEnum.FAIL.getCode(), "新增失败！");
        } else {
            msg = new Message(MessageEnum.SUCCESS.getCode(), "新增成功！");
        }
        return msg;
    }

    /**
     * 构建KuaiDi对象
     * @param username
     * @param phone
     * @param kuaiDiNo
     * @param company
     * @return
     */
    private KuaiDi buildKuaiDiObject(String username, String phone, String kuaiDiNo, String company){
        username = StringUtils.defaultString(username);
        phone = StringUtils.defaultString(phone);
        kuaiDiNo = StringUtils.defaultString(kuaiDiNo);
        company = StringUtils.defaultString(company);
        KuaiDi kuaiDi = KuaiDi.builder()
                .userName(username.trim()).phone(phone.trim())
                .kuaidiNo(kuaiDiNo).company(company).createTime(new Date()).build();

        return kuaiDi;

    }
    @Override
    public Message<KuaiDi> modify(Integer id, String username, String phone, String kuaiDiNo, String company) {

        Message msg;

        KuaiDi kuaiDi = buildKuaiDiObject(username, phone, kuaiDiNo, company);
        kuaiDi.setId(id);

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

    @Override
    public void importData(MultipartFile multipartFile) throws Exception{
        List<KuaiDi> kuaiDiList = ExcelUtils.getKuaiDiList(multipartFile.getInputStream());
        if(CollectionUtil.isNotEmpty(kuaiDiList)){
            //批量插入
            this.saveBatch(kuaiDiList);
        }
    }

}
