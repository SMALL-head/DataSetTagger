package com.zyc.datasettagger.controller;

import com.zyc.common.constants.Constants;
import com.zyc.common.model.param.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author zyc
 * @version 1.0
 */
@RestController
@Slf4j
public class HelloController {
    @RequestMapping(value = "/hello", produces = Constants.JSON_CONTENT_TYPE_UTF8)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/api/user/hello", produces = Constants.JSON_CONTENT_TYPE_UTF8)
    public String taggerHello() {
        return "hello tagger: " + SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping(value = "/test", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void test(@RequestBody Hello hello) {
        log.warn("[test]-hello:{}", hello);
    }
}
