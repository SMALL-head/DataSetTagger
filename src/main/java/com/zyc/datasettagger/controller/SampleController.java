package com.zyc.datasettagger.controller;

import com.zyc.common.model.SampleModel;
import com.zyc.datasettagger.service.SampleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyc
 * @version 1.0
 */
@RestController
public class SampleController {
    SampleService sampleService;
    @PostMapping("/api/sample")
    public SampleModel addSample(@RequestBody SampleModel sample) {
        return null;
    }
}
