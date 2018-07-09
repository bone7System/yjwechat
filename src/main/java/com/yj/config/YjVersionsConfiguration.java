package com.yj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by evkoh on 2015/11/25.
 */
@Component
@ConfigurationProperties(prefix = "yj.versions")
@PropertySource("classpath:versions.properties")
public class YjVersionsConfiguration {
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
