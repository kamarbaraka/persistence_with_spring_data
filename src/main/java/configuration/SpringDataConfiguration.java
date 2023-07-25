package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * configuration class for the spring data jpa api.
 * @author kamar baraka*/


@EnableJpaRepositories("repositories")
public class SpringDataConfiguration {

    private DataSource dataSource;
    private JpaVendorAdapter vendorAdapter;

    /**
     * construct a datasource bean.
     * */
    @Bean
    public DataSource dataSource(){

        /*construct a datasource from DriverManagerDataSource*/
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        /*set the database driver*/
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        /*set the url*/
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/spring_data_jpa");

        /*set the username*/
        driverManagerDataSource.setUsername("root");

        /*set the password*/
        driverManagerDataSource.setPassword("Samsroot#mysql");

        this.dataSource = driverManagerDataSource;

        /*return the configured datasource*/
        return driverManagerDataSource;
    }

    /**
     * constructs jpa transaction manager.
     * @param factory entity manager factory.
     * @return jpa transaction manager.
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory factory){

        /*construct and return jpaTransaction manager from the factory*/
        return new JpaTransactionManager(factory);

    }

    /**
     * constructs a jpa vendor adaptor.
     * @return jpa vendor adapter.*/
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){

        /*construct JpaVendorAdapter implementation */
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        /*set the properties.
        * set the database*/
        jpaVendorAdapter.setDatabase(Database.MYSQL);

        /*allow logging of sql queries during execution*/
        jpaVendorAdapter.setShowSql(true);

        this.vendorAdapter = jpaVendorAdapter;

        /*return the configured jpa vendor adapter*/
        return jpaVendorAdapter;

    }

    /**
     * construct a local container entity factory bean, to produce entity manager factory.
     * @return the constructed local container entity factory bean*/
    @Bean
    public LocalContainerEntityManagerFactoryBean factoryBean(){

        /*construct local container entity manager factory*/
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        /*configure the data source for the factoryBean*/
        factoryBean.setDataSource(dataSource);

        /*construct properties for the factory bean*/
        Properties properties = new Properties();

        /*map(set) the value of property hibernate.dbm2dll to update, to update the database when the program runs*/
        properties.put("hibernate.hbm2dll.auto", "update");

        /*assign the properties to the bean factory*/
        factoryBean.setJpaProperties(properties);

        /*set the jpa vendor adapter*/
        factoryBean.setJpaVendorAdapter(vendorAdapter);

        /*set the packages to be scanned for persistent objects*/
        factoryBean.setPackagesToScan("repositories");

        /*return this factory bean*/
        return factoryBean;

    }
}
