package com.copious.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

import javax.sql.DataSource;

/**
 * @author Mahesh More
 * <p>
 * Spring Boot application to implement Java8 basic features Assignment1 under copious training.
 */
@SpringBootApplication
@EnableCaching
public class AsssignmentApp extends SpringBootServletInitializer {
    /**
     * Main method to boot-up order processing app.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AsssignmentApp.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AsssignmentApp.class);
    }
}
