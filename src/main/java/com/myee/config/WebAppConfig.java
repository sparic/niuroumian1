package com.myee.config;

import com.myee.support.MappingFastJsonHttpMessageConverter;
import com.google.common.collect.Maps;
import com.myee.djinn.remoting.netty.NettyServerConfig;
import com.myee.djinn.rpc.bootstrap.ServerBootstrap;
import com.myee.djinn.server.operations.OperationsService;
import me.chanjar.weixin.mp.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.myee.domain.*.dao")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.myee.domain.*.service", "com.myee.web"})
@PropertySources({
        @PropertySource("classpath:/redis.properties"),
        @PropertySource("classpath:/djinn.properties")
})
@EnableWebMvc
@EnableRedisHttpSession
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    MappingFastJsonHttpMessageConverter jsonConverter() {
        MappingFastJsonHttpMessageConverter converter = new MappingFastJsonHttpMessageConverter();
        List<MediaType> mediaList = Arrays.asList(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaList);
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jsonConverter());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public JpaTransactionManager transactionManager(DataSource dataSource) throws SQLException {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    DataSource dataSource() {
        JndiDataSourceLookup lookup = new JndiDataSourceLookup();
        return lookup.getDataSource("java:comp/env/jdbc/freightDS");
    }

    @Bean
    CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1024000000L);
        return multipartResolver;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setGenerateDdl(false);
        adapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.myee.domain.*.model", "com.myee.domain.*.view");
//        factoryBean.setMappingResources("META-INF/adempiere.xml");
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }

    @Bean
    public WxMpService wxMpService(WxMpConfigStorage wxMpConfigStorage) {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
        return wxMpService;
    }

    @Bean
    @Scope("singleton")
    WxMpMessageRouter wxMpMessageRouter(WxMpService wxMpService) {
        WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
        return wxMpMessageRouter;
    }

    @Bean
    @Scope("singleton")
    public WxMpInMemoryConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId("wxe67244505b4041b6"); // 设置微信公众号的appid
        wxMpConfigStorage.setSecret("ae3b4cd8a550fab663c90ab16d548579"); // 设置微信公众号的app corpSecret
        wxMpConfigStorage.setToken("clever"); // 设置微信公众号的token
        wxMpConfigStorage.setAesKey("DKigNihAx9rcVHKJvc9D6xBnXCFHTUe4MnTBw01bLM7"); // 设置微信公众号的EncodingAESKey
        return wxMpConfigStorage;
    }

    @Bean
    JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(5000);
        config.setTestOnBorrow(true);
        return config;
    }

    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private int redisPort;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Scope("singleton")
    JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig poolConfig) {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(redisHost);
        connectionFactory.setPort(redisPort);
        connectionFactory.setUsePool(true);
        connectionFactory.setPoolConfig(poolConfig);
        return connectionFactory;
    }

    @Bean
    RedisTemplate redisTemplate(JedisConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean
    CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    @Value("${djinn.port}")
    private int djinnPort;
    @Autowired
    private OperationsService operationsService;

    @Bean
    @Scope("singleton")
    ServerBootstrap serverBootstrap() {
        final NettyServerConfig nettyServerConfig = new NettyServerConfig();
        nettyServerConfig.setListenPort(djinnPort);
        ServerBootstrap bootstrap = new ServerBootstrap(nettyServerConfig);
        bootstrap.initialize();
        try {
            bootstrap.export(operationsService, OperationsService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bootstrap.start();
        return bootstrap;
    }

}
