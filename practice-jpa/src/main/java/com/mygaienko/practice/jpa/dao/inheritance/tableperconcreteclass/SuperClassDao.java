package com.mygaienko.practice.jpa.dao.inheritance.tableperconcreteclass;

import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass.B;
import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass.B_;
import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass.SuperClass;
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
public class SuperClassDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<SuperClass> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SuperClass> query = builder.createQuery(SuperClass.class);
        Root<SuperClass> productRoot = query.from(SuperClass.class);
        CriteriaQuery<SuperClass> allQuery = query.select(productRoot);

        return entityManager.createQuery(allQuery).getResultList();
    }

}
