package lk.ijse.dep.pos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@PropertySource("classpath:application.properties")
@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    Environment env;

    @Bean
    public static PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor(){
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource ds){
        LocalSessionFactoryBean lsbf=new LocalSessionFactoryBean();
        lsbf.setDataSource(ds);
        lsbf.setHibernateProperties(hibernateProperties());
        lsbf.setPackagesToScan("lk.ijse.dep.pos.entity");
        return lsbf;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClassName(env.getRequiredProperty("hibernate.connection.driver_class"));
        ds.setUsername(env.getRequiredProperty("hibernate.connection.username"));
        ds.setPassword(env.getRequiredProperty("hibernate.connection.password"));
        ds.setUrl("jdbc:mysql://localhost:3306/dep5?createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        return ds;
    }


    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql",true);
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.allow_refresh_detached_entity",false);
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sf){
        return new HibernateTransactionManager(sf);
    }

}
