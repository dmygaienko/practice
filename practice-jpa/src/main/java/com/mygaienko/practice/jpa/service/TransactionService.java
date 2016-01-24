package com.mygaienko.practice.jpa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dmygaenko on 10/12/2015.
 */
@Service
public class TransactionService {

    private SimpleService simpleService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewMethod() {
        simpleService.doService();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiredMethod() {
        simpleService.doService();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryMethod() {
        simpleService.doService();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nestedMethod() {
        simpleService.doService();
    }

    public void setSimpleService(SimpleService simpleService) {
        this.simpleService = simpleService;
    }
}
