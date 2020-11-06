package kr.engsoft.test3.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource("classpath:application-aisc.properties")
@EnableJpaRepositories(
        entityManagerFactoryRef = "AiscEntityManagerFactory",
        transactionManagerRef = "AiscTransactionManager",
        basePackages = {"kr.engsoft.test3.aisc"} //리포지터리 패키지
)
public class AiscDataSource {

    private static final String POOL_NAME = "HIKARICP_AISC";

    @Autowired
    private Environment env;

    @Bean(name = "getAiscDataSource")
    public DataSource getAiscDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getProperty("spring.aisc.datasource.driver-class-name"));
        config.setUsername(env.getProperty("spring.aisc.datasource.username"));
        config.setPassword(env.getProperty("spring.aisc.datasource.password"));
        config.setJdbcUrl(env.getProperty("spring.aisc.datasource.url"));

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setPoolName(POOL_NAME);
        //config.setMaximumPoolSize(100); 커넥션 풀 갯수 지정 안하면 기본 갯수 (10)

        return new HikariDataSource(config);
    }

    @Bean(name = "AiscEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean AiscEntityManagerFactory() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        HashMap<String, Object> properties = new HashMap<>();

        properties.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty("spring.jpa.hibernate.ddl-auto"));
        System.out.println(env.getProperty("spring.jpa.hibernate.ddl-auto"));
        em.setDataSource(getAiscDataSource());
        em.setPersistenceUnitName("Aisc");
        em.setPackagesToScan("kr.engsoft.test3.aisc"); //엔티티 패키지
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(properties);
        return em;
    }


    @Bean(name = "AiscTransactionManager")
    public PlatformTransactionManager AiscTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(AiscEntityManagerFactory().getObject());
        return transactionManager;
    }
}