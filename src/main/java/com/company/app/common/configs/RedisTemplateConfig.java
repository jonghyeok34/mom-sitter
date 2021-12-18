package com.company.app.common.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@EnableRedisRepositories
@Setter
public class RedisTemplateConfig {

    private String host;
    private Integer port;
    private String password;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(defaultRedisConfig());
    }

    // @Bean
    // @Primary
    // public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(RedisConfiguration defaultRedisConfig) {
    //     LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
    //             .build();
    //     return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
    // }

    @Bean
    public RedisConfiguration defaultRedisConfig() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setPassword(password);
        return config;
    }
}