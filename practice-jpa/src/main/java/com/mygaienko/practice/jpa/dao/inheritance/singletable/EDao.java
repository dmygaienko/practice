package com.mygaienko.practice.jpa.dao.inheritance.singletable;

import com.mygaienko.practice.jpa.model.inheritance.singletable.E;
import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass.A;
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
public class EDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<E> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = builder.createQuery(E.class);
        Root<E> root = query.from(E.class);
        CriteriaQuery<E> allQuery = query.select(root);

        return entityManager.createQuery(allQuery).getResultList();
    }
}
