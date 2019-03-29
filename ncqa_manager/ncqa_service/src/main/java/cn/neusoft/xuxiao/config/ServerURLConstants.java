package cn.neusoft.xuxiao.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 服务器端路径常量
 */
@Component
@Data
@PropertySource("classpath:application.yml")
public class ServerURLConstants {

    @Value("${outside_image_url}")
    private String outsideImageUrl;

    @Value("${inside_image_url}")
    private String insideImageUrl;
}
