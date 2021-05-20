package com.mm.jpa;

import com.mm.jpa.jdbctojpa.Person;
import com.mm.jpa.jdbctojpa.PersonJdbcDAO;
import com.mm.jpa.jdbctojpa.PersonJpaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import java.util.Date;

/**
 * @author Mahesh More
 * <p>
 * Spring Boot application to implement JPA and Hibernate concepts.
 */
@SpringBootApplication
public class JpaHibernateApp extends SpringBootServletInitializer implements CommandLineRunner {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJdbcDAO dao;

    @Autowired
    PersonJpaRepo repo;

    /**
     * Main method to boot-up order processing app.
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JpaHibernateApp.class, args);

//        for (String bean:context.getBeanDefinitionNames()) {
//            System.out.println(bean);
//        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JpaHibernateApp.class);
    }

    @Override
    public void run(String... args) throws Exception {
        // JDBC Operations
//        logger.info("All Users: {}", dao.findAll());
//        logger.info("Single User: {}", dao.findById(101));
//        logger.info("Deleting User 102: number of rows deleted {}", dao.deleteById(102));
//        logger.info("Inserting User 104: {}", dao.insert(new Person(104,"Test","Patan", new Date())));
//        logger.info("Updating User 104:  {}", dao.update(new Person(104,"Updated Test","Patan", new Date())));

        //JPA Operations
        logger.info("Single User: {}", repo.findById(101));
        logger.info("Inserting User 106: {}", repo.insert(new Person(106,"Test","Patan", new Date())));
        logger.info("Updating User 106:  {}", repo.update(new Person(106,"Updated Test","Patan", new Date())));
        logger.info("Deleting User 106: ");
        repo.deleteById(106);
    }
}
