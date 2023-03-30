package com.zyc.datasettagger.controller;

import com.zyc.common.constants.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyc
 * @version 1.0
 */
@RestController
public class HelloController {
    @RequestMapping(value = "/hello", produces = Constants.JSON_CONTENT_TYPE_UTF8)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/api/user/hello", produces = Constants.JSON_CONTENT_TYPE_UTF8)
    public String taggerHello() {
        return "hello tagger: " + SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
