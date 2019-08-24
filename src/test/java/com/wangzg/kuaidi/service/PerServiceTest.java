package com.wangzg.kuaidi.service;

import com.wangzg.kuaidi.domain.Per;
import com.wangzg.kuaidi.util.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PerServiceTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PerService perService;

    @Test
    public void getPers() throws Exception {
        List<Per> pers = perService.getPers("", "");
        log.info("pers: {}", pers);
    }

    @Test
    public void addPer() throws Exception {
        Message<Per> message = perService.addPer("wch ", "25", "njfu");
        log.info("message: {}", message);
    }

    @Test
    public void modifyPer() throws Exception {
        Message<Per> message = perService.modifyPer(1, "wchtest", "21", "nifutest");
        log.info("message: {}", message);
    }

    @Test
    public void removePerById() throws Exception {
        Integer id = 5;
        Message<Per> message = perService.removePerById(id);
        log.info("message: {}", message);
    }

}