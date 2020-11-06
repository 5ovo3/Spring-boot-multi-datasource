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
@PropertySource("classpath:application-shelter.properties")
@EnableJpaRepositories(
        entityManagerFactoryRef = "ShelterEntityManagerFactory",
        transactionManagerRef = "ShelterTransactionManager",
        basePackages = {"kr.engsoft.test3.shelter"} //리포지터리 패키지
)
public class ShelterDataSource {

    private static final String POOL_NAME = "HIKARICP_Shelter";

    @Autowired
    private Environment env;

    @Bean(name = "getShelterDataSource")
    public DataSource getShelterDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getProperty("spring.shelter.datasource.driver-class-name"));
        config.setUsername(env.getProperty("spring.shelter.datasource.username"));
        config.setPassword(env.getProperty("spring.shelter.datasource.password"));
        config.setJdbcUrl(env.getProperty("spring.shelter.datasource.url"));

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setPoolName(POOL_NAME);
        //config.setMaximumPoolSize(100); 커넥션 풀 갯수 지정 안하면 기본 갯수 (10)

        return new HikariDataSource(config);
    }

    @Bean(name = "ShelterEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean ShelterEntityManagerFactory() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        HashMap<String, Object> properties = new HashMap<>();

        properties.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty("spring.jpa.hibernate.ddl-auto"));
        System.out.println(env.getProperty("spring.jpa.hibernate.ddl-auto"));
        em.setDataSource(getShelterDataSource());
        em.setPersistenceUnitName("Shelter");
        em.setPackagesToScan("kr.engsoft.test3.shelter"); //엔티티 패키지
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(properties);
        return em;
    }


    @Bean(name = "ShelterTransactionManager")
    public PlatformTransactionManager ShelterTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ShelterEntityManagerFactory().getObject());
        return transactionManager;
    }
}