package com.gm.server.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hhj023
 * @Date: 2025/5/14
 * @Description: SpringDoc配置类
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "GM-IM 接口文档",
                version = "1.0.0"
        )
)
public class SpringDocConfig {

        /**
         * 默认分组（所有接口）
         */
        @Bean
        public GroupedOpenApi publicApi() {
                return GroupedOpenApi.builder()
                        .group("all")  // 分组名称
                        .packagesToScan("com.gm.server.controller")  // 扫描的包路径
                        .pathsToMatch("/**")  // 匹配的接口路径，匹配所有路径
                        .build();
        }

}
