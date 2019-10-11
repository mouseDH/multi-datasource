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
@MapperScan(basePackages = "com.demo.mapper.db2", sqlSessionTemplateRef  = "db2MysqlSessionTemplate")
public class DB2Configuration {
    @Bean(name = "db2MysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.db2")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2MysqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("db2MysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper-db2/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "db2MysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("db2MysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "db2MysqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("db2MysqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
