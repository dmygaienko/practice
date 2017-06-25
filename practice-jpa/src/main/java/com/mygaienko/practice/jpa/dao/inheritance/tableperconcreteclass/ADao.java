package com.mygaienko.practice.jpa.dao.inheritance.tableperconcreteclass;

import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass.A;
import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass.A_;
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
public class ADao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<A> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<A> query = builder.createQuery(A.class);
        Root<A> root = query.from(A.class);
        CriteriaQuery<A> allQuery = query.select(root);

        return entityManager.createQuery(allQuery).getResultList();
    }

    public A get(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<A> query = builder.createQuery(A.class);
        Root<A> root = query.from(A.class);
        query.where(builder.equal(root.get(A_.id), id));

        return entityManager.createQuery(query).getSingleResult();
    }
}
