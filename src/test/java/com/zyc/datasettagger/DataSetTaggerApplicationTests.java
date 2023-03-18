package com.zyc.datasettagger;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DataSetTaggerApplicationTests {
    @Resource(name = "bCryptPasswordEncoder")
    PasswordEncoder bCryptPasswordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    void passwordEncoderTest() {
        System.out.println("{bcrypt}" + bCryptPasswordEncoder.encode("123"));
        System.out.println(bCryptPasswordEncoder.matches("$2a$10$OO7aJ5EtMPKW6ufuVO5ZfONe8JO0LZFLG1uO0r8roiXl5fO8Y9dpS", "$2a$10$nun10wPUnHWsX2W2GyeJTelA2LD86JfV1fjjHVQ0w2BRtZKjUlhjy"));
    }

    @Test
    void test() {
        Integer i = 128;
        Integer i2 = 128;
        System.out.println(i2 == i);
    }

}
