package lk.ijse.dep.pos;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@PropertySource("classpath:application.properties")
@ComponentScan("lk.ijse.dep.pos")
@Configuration
@Import(HibernateConfig.class)
public class AppConfig {



}
