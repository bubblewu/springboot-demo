package com.bubble.config.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import javax.annotation.PostConstruct;

/**
 * MongoDB多源配置抽象类
 *
 * @author wugang
 * date: 2019-04-01 18:25
 **/
public abstract class AbstractMongoConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMongoConfig.class);

    //定义相关连接数据库参数
    private String host, database, userName, password;
    private int port;

    @PostConstruct
    private void init() {
        if (mongoDbFactory().getDb(database).getName().equals(database)) {
            LOGGER.info("init the mongoDB configuration process successfully, host is {}, port is {}, dbName = {}, userName = {}", host, port, database, userName);
        } else {
            LOGGER.error("init the mongoDB configuration process error, host is {}, port is {}, dbName = {}, userName = {}", host, port, database, userName);
        }
    }

    /**
     * 创建MongoDbFactory，不同数据源继承该方法创建对应的MongoDbFactory。
     */
    public MongoDbFactory mongoDbFactory() {
        ServerAddress serverAddress = new ServerAddress(host, port);
        MongoCredential mongoCredential = MongoCredential.createCredential(userName, database, password.toCharArray());
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(100)
                .socketTimeout(30000)
                .connectTimeout(3000)
                .build();
        return new SimpleMongoDbFactory(new MongoClient(serverAddress, mongoCredential, options), database);
    }

    /**
     * 抽象方法，用于返回MongoTemplate
     */
    abstract public MongoTemplate getMongoTemplate(MongoMappingContext context);


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
