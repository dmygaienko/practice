package com.mygaienko.practice.jpa.service;

import com.mygaienko.practice.jpa.Application;
import com.mygaienko.practice.jpa.config.TestDatabaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;

/**
 * Created by dmygaenko on 10/12/2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Import(TestDatabaseConfig.class)
@IntegrationTest
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService service = new TransactionService();

    @Autowired
    private SimpleService simpleService;

    @Test
    public void testRequiredMethod() throws Exception {
        when(simpleService.doService()).thenThrow(new RuntimeException());
        service.requiredMethod();

    }

    @Test
    @Transactional
    public void transactionalTestRequiredMethod() throws Exception {
  //      when(simpleService.doService()).thenThrow(new RuntimeException());
        service.requiredMethod();

    }

    @AfterTransaction
    public void afterTransaction() {
        System.out.println("\n\nAfter transaction\n\n");
    }

    @BeforeTransaction
    public void beforeTransaction() {
        System.out.println("\n\nBefore transaction\n\n");
    }

    @Rollback
    public void rollback() {
        System.out.println("\n\nRollback\n\n");
    }

}