package it.jobrapido.knightboard.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties(prefix = "api")
@Data
@Primary
public class ApiPropertiesConfig {
    private String board;
    private String commands;
}
