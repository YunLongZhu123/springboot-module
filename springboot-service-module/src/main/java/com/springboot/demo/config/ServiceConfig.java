package com.springboot.demo.config;


import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class ServiceConfig {

    @Bean
    public MapperFacade mapperFacade(){
        DefaultMapperFactory builder = new DefaultMapperFactory.Builder().mapNulls(false).build();
        return builder.getMapperFacade();
    }




}
