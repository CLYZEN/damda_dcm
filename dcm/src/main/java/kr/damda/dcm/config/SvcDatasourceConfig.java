package kr.damda.dcm.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@EnableJpaRepositories(
    basePackages = "kr.damda.dcm.svc.repository",
    entityManagerFactoryRef = "svcEntityManager",
    transactionManagerRef = "svcTransactionManager"
)
@Configuration
public class SvcDatasourceConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean svcEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(svcDataSource());
        em.setPackagesToScan(new String[] {"kr.damda.dcm.svc.entity"});
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.svc-datasource")
    public DataSource svcDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager svcTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(svcEntityManager().getObject());
        return transactionManager;
    }
}
