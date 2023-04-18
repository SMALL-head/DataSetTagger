package com.zyc.datasettagger.service.impl;

import com.zyc.datasettagger.mapper.SampleMapper;
import com.zyc.datasettagger.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 与样本相关的操作
 * @author zyc
 * @version 1.0
 */
@Service
public class SampleServiceImpl implements SampleService {
    SampleMapper sampleMapper;

    @Autowired
    public void setSampleMapper(SampleMapper sampleMapper) {
        this.sampleMapper = sampleMapper;
    }
}
