package com.mygaienko.practice.jpa.config;

import com.mygaienko.practice.jpa.dao.graph.PersonRepository;
import com.mygaienko.practice.jpa.model.graph.Person;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.InProcessServer;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * Created by enda1n on 23.01.2016.
 */
@Configuration
@EnableNeo4jRepositories(basePackageClasses = {PersonRepository.class})
@EnableTransactionManagement
@EntityScan(basePackageClasses = {Person.class})
public class MyNeo4jConfig extends Neo4jConfiguration {

    @Bean
    GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase("accessingdataneo4j.db");
    }

    @Bean
    public Neo4jServer neo4jServer() {
        //return new RemoteServer("http://localhost:7474");
        return new InProcessServer();
    }

    @Bean
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory("com.mygaienko.practice.jpa.model.graph");
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        return super.getSession();
    }

    @Bean(name = "myNeo4jTransactionManager")
    public PlatformTransactionManager neo4jTransactionManager() throws Exception {
        return this.transactionManager();
    }

  /*  @Autowired
    @Bean(name = "myNeo4jTransactionManager")
    public PlatformTransactionManager neo4jTransactionManager(EntityManagerFactory entityManagerFactory, GraphDatabaseService graphDatabaseService)
            throws Exception {
        return new ChainedTransactionManager(
                new JpaTransactionManager(entityManagerFactory),
                new JtaTransactionManagerFactoryBean(graphDatabaseService).getObject());
    }*/
}
