package com.zyc.datasettagger;

import com.zyc.common.entity.TagEntity;
import com.zyc.common.exception.EnumAcquireException;
import com.zyc.common.security.entity.User;
import com.zyc.datasettagger.mapper.DataSetMapper;
import com.zyc.datasettagger.config.security.mapper.UserMapper;
import com.zyc.datasettagger.mapper.TagMapper;
import com.zyc.utils.Convertor;
import com.zyc.utils.convertor.DataSetConvertor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
class DataSetTaggerApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    DataSetMapper dataSetMapper;

    @Autowired
    TagMapper tagMapper;


    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        User p1 = userMapper.loadUserByUsername("p1");
        System.out.println(p1);
    }

    @Test
    void test_string() {
        // String对象的split方法会保留空格，因此需要通过其他方式自行消除空格，比如我下面提供的Stream方法
        String sample = "123 : 223";
        String[] split = sample.split(":");
        String[] array = Arrays.stream(split).map(String::trim).toArray(String[]::new);
        System.out.println(Arrays.toString(array));
    }

    @Test
    void test_tagMapper() {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setTagId(UUID.randomUUID().toString());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        tagEntity.setTagTime(timestamp);
        tagEntity.setBeginPos("time : 1356s");
        tagEntity.setEndPos("time : 1358s");
        tagEntity.setTag("text : 一段文本标记");
        tagMapper.addTag(tagEntity);
    }

}
