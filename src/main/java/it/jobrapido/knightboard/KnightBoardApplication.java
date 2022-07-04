package it.jobrapido.knightboard;

import it.jobrapido.knightboard.config.ApiPropertiesConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(ApiPropertiesConfig.class)
public class KnightBoardApplication {


    public static void main(String[] args) {
        new SpringApplicationBuilder(KnightBoardApplication.class)
                .bannerMode(Banner.Mode.OFF)
                //.logStartupInfo(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }


}
