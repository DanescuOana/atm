package com.tech.atm.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.tech.atm.model");
        bean.setMapperLocations(new Resource[] {
                new ClassPathResource("com/tech/atm/account/dao/sqlmap/account-sqlmap.xml"),
                new ClassPathResource("com/tech/atm/card/dao/sqlmap/card-sqlmap.xml")
        });
        return bean.getObject();
    }
}
