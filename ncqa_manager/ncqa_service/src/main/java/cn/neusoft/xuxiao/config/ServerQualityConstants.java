package cn.neusoft.xuxiao.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 服务器性能指标常量
 */
@Component
@Data
@PropertySource("classpath:application.yml")
public class ServerQualityConstants {

    @Value("${async_thread_num}")
    private Integer asyncThreadNum;
}
