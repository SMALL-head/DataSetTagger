package com.zyc.datasettagger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zyc
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "pagination.config")
public class PageConfig {
    public int getLimitation() {
        return limitation;
    }

    public void setLimitation(int limitation) {
        this.limitation = limitation;
    }

    /**
     * 每个页中包含的item数量
     */
    private int limitation = 5;
}
