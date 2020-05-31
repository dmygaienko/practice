package com.mygaienko.practice.jpa.dao.inheritance.singletable;

import com.mygaienko.practice.jpa.model.inheritance.singletable.SingleSuperClass;
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
public class SingleSuperClassDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<SingleSuperClass> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SingleSuperClass> query = builder.createQuery(SingleSuperClass.class);
        Root<SingleSuperClass> root = query.from(SingleSuperClass.class);
        CriteriaQuery<SingleSuperClass> allQuery = query.select(root);

        return entityManager.createQuery(allQuery).getResultList();
    }
}
