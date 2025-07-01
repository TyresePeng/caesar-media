package org.caesar.media.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @Description: json 配置
 * @Author: peng.guo
 * @Create: 2025-07-01 17:28
 * @Version 1.0
 **/
@Component
public class JsonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // 忽略 null 字段
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 禁止序列化空 Bean（否则会抛异常）
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 允许忽略 JSON 中在 Java 类中不存在的字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Java 8 日期时间模块（如 LocalDateTime）
        objectMapper.registerModule(new JavaTimeModule());

        // 避免 LocalDateTime 被序列化为 timestamp
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 设置统一的日期格式（如果你使用 Date 类型）
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 设置时区（建议统一设置为 GMT+8）
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return objectMapper;
    }

}
