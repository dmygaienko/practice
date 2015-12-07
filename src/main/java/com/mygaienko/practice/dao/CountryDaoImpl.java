package com.mygaienko.practice.dao;

import com.mygaienko.practice.dao.interfaces.CountryDao;
import com.mygaienko.practice.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by liuda on 12/6/2015.
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Country get(CountryId id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> author = cq.from(Country.class);
        cq.where(cb.equal(author.get(Country_.id), id));

        return  entityManager.createQuery(cq).getSingleResult();
    }
}
