package com.mygaienko.practice.jpa.dao;

import com.mygaienko.practice.jpa.dao.interfaces.CountryDao;
import com.mygaienko.practice.jpa.model.Country;
import com.mygaienko.practice.jpa.model.CountryId;
import com.mygaienko.practice.jpa.model.Country_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by liuda on 12/6/2015.
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public Country get(CountryId id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> query = builder.createQuery(Country.class);
        Root<Country> author = query.from(Country.class);
        query.where(builder.equal(author.get(Country_.id), id));

        return  entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public Country getAndIncrement(CountryId id) {
        return entityManager.find(Country.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
    }

    @Override
    public void remove(Country country) {
        entityManager.remove(country);
    }

    @Override
    public List<Country> getCountries() {
        List<Country> countries = entityManager.createNamedQuery("getAllCountriesNamedQuery", Country.class).getResultList();
        return countries;
    }

    @Override
    public void merge(Country country) {
        entityManager.merge(country);
    }

    @Override
    public void persist(Country country) {
        entityManager.persist(country);
    }


}
