package com.zyc.datasettagger;

import com.zyc.common.entity.DataSetEntity;
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
import java.util.List;
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
        List<TagEntity> tagByLimitation = tagMapper.getTagByLimitation("60e6ac4f-f859-4bf7-8bca-78eb670d6c9e", 0, 5);
        System.out.println(tagByLimitation);
    }

    @Test
    void test_dataset_search() {

    }

}
