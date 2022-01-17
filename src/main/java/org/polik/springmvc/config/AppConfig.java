package org.polik.springmvc.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(value = "org.polik.springmvc")
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/rpg?serverTimezone=UTC&characterEncoding=UTF-8");
            dataSource.setPassword("root");
            dataSource.setUser("root");

            return dataSource;
        } catch (PropertyVetoException ex) {
            throw new IllegalArgumentException("Driver class is incorrect!");
        }
    }

    @Bean
    public SessionFactory sessionFactory() {
        try {
            LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
            factoryBean.setDataSource(dataSource());
            factoryBean.setPackagesToScan("org.polik.springmvc.entity");

            Properties prop = new Properties();
            prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            prop.setProperty("hibernate.show_sql", "true");
            factoryBean.setHibernateProperties(prop);
            factoryBean.afterPropertiesSet();

            return factoryBean.getObject();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Something");
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());

        return transactionManager;
    }
}
