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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: hao.dai
 * @Date: 2019/10/10 17:35
 * @Description:
 */
@Configuration
@MapperScan(basePackages = "com.demo.mapper.db1", sqlSessionTemplateRef  = "db1MysqlSessionTemplate")
public class DB1Configuration {
    @Bean(name = "db1MysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.db1")
    @Primary
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db1MysqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("db1MysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper-db1/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "db1MysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("db1MysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "db1MysqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("db1MysqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
