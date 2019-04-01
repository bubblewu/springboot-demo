//package com.bubble.config.mongo;
//
//import com.bubble.utils.convert.StringToLocalDateTimeConverter;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientOptions;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.core.env.Environment;
//import org.springframework.data.convert.CustomConversions;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
//import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.lang.NonNull;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 单源MongoDB配置
// *
// * @author wugang
// * date: 2019-04-01 18:17
// **/
//@Configuration
//@EnableMongoRepositories(basePackages = "com.bubble.dao")
//public class MongoConfig extends AbstractMongoConfiguration {
//    private static final Logger LOGGER = LoggerFactory.getLogger(MongoConfig.class);
//
//    @Value("#{'${mongodb.hosts}'.split(',')}")
//    private List<ServerAddress> seeds;
//    @Value("${mongodb.port}")
//    private int port;
//    @Value("${mongodb.database}")
//    private String database;
//    @Value("${mongodb.userName}")
//    private String userName;
//    @Value("${mongodb.password}")
//    private String password;
//
//    @Value("${mongodb.connectionsPerHost}")
//    private int connectionsPerHost;
//    @Value("${mongodb.threadsAllowedToBlockForConnectionMultiplier}")
//    private int threadsAllowedToBlockForConnectionMultiplier;
//    @Value("${mongodb.connectTimeout}")
//    private int connectTimeout;
//    @Value("${mongodb.maxWaitTime}")
//    private int maxWaitTime;
//    @Value("${mongodb.socketTimeout}")
//    private int socketTimeout;
//
//    private final Environment env;
//
//    @Autowired
//    public MongoConfig(Environment env) {
//        this.env = env;
//    }
//
//    @PostConstruct
//    private void init() {
//        if (mongoClient().getDatabase(database).getName().equals(database)) {
//            LOGGER.info("init the mongoDB configuration process successfully, host is {}, port is {}, dbName = {}, userName = {}", seeds.get(0), port, database, userName);
//        } else {
//            LOGGER.error("init the mongoDB configuration process error, host is {}, port is {}, dbName = {}, userName = {}", seeds.get(0), port, database, userName);
//        }
//    }
//
//    @Bean
//    public MongoClientOptions.Builder builder() {
//        return new MongoClientOptions.Builder()
//                .connectionsPerHost(connectionsPerHost)
//                .threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier)
//                .connectTimeout(connectTimeout)
//                .maxWaitTime(maxWaitTime);
////        Arrays.stream(env.getActiveProfiles())
////                .filter(p -> !p.equals("dev"))
////                .filter(p -> !p.equals("test")).map(p ->
////                builder.requiredReplicaSetName(replicationSetName)
////        );
//    }
//
//    //    @Profile(value = "dev")
//    @NonNull
//    @Bean
//    @Override
//    public MongoClient mongoClient() {
//        MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
//        return new MongoClient(seeds, credential, builder().build());
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, getDatabaseName());
////        mongoTemplate.setReadPreference(ReadPreference.secondaryPreferred());//Read/Write Splitting
//        ((MappingMongoConverter) mongoTemplate.getConverter()).setTypeMapper(new DefaultMongoTypeMapper(null));//removes _class
////        ((MappingMongoConverter) mongoTemplate.getConverter()).setCustomConversions(customConversions());
//        ((MappingMongoConverter) mongoTemplate.getConverter()).afterPropertiesSet();
//        return mongoTemplate;
//    }
//
//    @NonNull
//    @Bean
//    public CustomConversions customConversions() {
//        List<Converter<?, ?>> converterList = new ArrayList<>();
//        StringToLocalDateTimeConverter converter = new StringToLocalDateTimeConverter();
//        converterList.add(converter);
//        CustomConversions.StoreConversions storeConversions = CustomConversions.StoreConversions.NONE;
//        return new CustomConversions(storeConversions, converterList);
//    }
//
//    @NonNull
//    @Override
//    protected String getDatabaseName() {
//        return database;
//    }
//
//}
