package com.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: hao.dai
 * @Date: 2019/10/10 17:35
 * @Description:
 */
@Configuration
@MapperScan(basePackages = "com.demo.mapper.db3", sqlSessionTemplateRef  = "db3MysqlSessionTemplate")
public class DB3Configuration {
    @Bean(name = "db3MysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.db3")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db3MysqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("db3MysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper-db3/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "db3MysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("db3MysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "db3MysqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("db3MysqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
