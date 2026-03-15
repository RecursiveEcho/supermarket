package com.example.supermarket.pojo.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

/**
 * 实现功能【配置 Jackson 2 序列化 LocalDateTime 为指定格式】
 * <p>
 * 配置 Jackson 2 序列化 LocalDateTime 为指定格式，避免前端接收时的时区问题
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-15 11:39:27
 */
@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            // 配置 LocalDateTime 序列化格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");// LocalDateTime 格式
            builder.serializerByType(java.time.LocalDateTime.class, new LocalDateTimeSerializer(formatter));// LocalDateTime 序列化器

            // 配置其他日期时间类型
            builder.serializerByType(java.time.LocalDate.class, new com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            builder.serializerByType(java.time.LocalTime.class, new com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer(
                    DateTimeFormatter.ofPattern("HH:mm:ss")));

            // 禁用默认的时间戳格式
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }
}