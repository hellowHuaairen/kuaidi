package com.wangzg.kuaidi.mapper;

import com.wangzg.kuaidi.domain.Per;
import org.junit.Assert;
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
public class PerMapperTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PerMapper perMapper;

    @Test
    public void selectPers() throws Exception {
        List<Per> pers = perMapper.selectPers(new Per());
        log.info("pers: {}", pers);
    }

    @Test
    public void insertPer() throws Exception {
        Per per = new Per();
        per.setName("wch");
        per.setAge(21);
        per.setAddress("njfu");
        int rowCount = perMapper.insertPer(per);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updatePer() throws Exception {
        Per per = new Per();
        per.setId(1);
        per.setName("wchtest");
        per.setAge(20);
        per.setAddress("你好");

        int rowCount = perMapper.updatePer(per);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void deletePerById() throws Exception {
        Integer id = 1;
        int rowCount = perMapper.deletePerById(id);
        Assert.assertEquals(1, rowCount);
    }

}