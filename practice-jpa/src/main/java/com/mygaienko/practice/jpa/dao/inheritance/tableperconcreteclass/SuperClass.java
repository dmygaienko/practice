package com.mygaienko.practice.jpa.dao.inheritance.tableperconcreteclass;

import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass.B;
import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass.B_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by enda1n on 25.06.2017.
 */
@Repository
public class SuperClass {

    @PersistenceContext
    private EntityManager entityManager;

    public List<B> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<B> query = builder.createQuery(B.class);
        Root<B> productRoot = query.from(B.class);
        CriteriaQuery<B> allQuery = query.select(productRoot);

        return entityManager.createQuery(allQuery).getResultList();
    }

    public B get(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<B> query = builder.createQuery(B.class);
        Root<B> productRoot = query.from(B.class);
        query.where(builder.equal(productRoot.get(B_.id), id));

        return entityManager.createQuery(query).getSingleResult();
    }
}
