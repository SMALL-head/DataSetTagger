package com.zyc.datasettagger;

import com.zyc.common.exception.EnumAcquireException;
import com.zyc.common.security.entity.User;
import com.zyc.datasettagger.mapper.DataSetMapper;
import com.zyc.datasettagger.config.security.mapper.UserMapper;
import com.zyc.utils.Convertor;
import com.zyc.utils.convertor.DataSetConvertor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DataSetTaggerApplicationTests {
    @Autowired
   UserMapper userMapper;

    @Autowired
    DataSetMapper dataSetMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        User p1 = userMapper.loadUserByUsername("p1");
        System.out.println(p1);
    }

    @Test
    public void testDataSetMapper() throws EnumAcquireException {
        log.info(DataSetConvertor.DataSetEntity2DataSetInfo(dataSetMapper.selectById(1) )+ "");
    }

}
