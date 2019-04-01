package com.bubble.config.mongo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * train库
 *
 * @author wugang
 * date: 2019-04-01 18:33
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.train")
public class TrainMongoConfig extends AbstractMongoConfig {

    @Override
    public @Bean(name = "trainMongoTemplate")
    MongoTemplate getMongoTemplate(MongoMappingContext context) {
        //去除保存实体时，spring data mongodb 自动添加的_class字段
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory()), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new MongoTemplate(mongoDbFactory(), converter);
    }

}
