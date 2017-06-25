package com.mygaienko.practice.jpa.dao.inheritance.tableperconcreteclasswithunions;

import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclasswithunions.SSuperClass;
import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclasswithunions.SSuperClass_;
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
public class SSuperClassDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<SSuperClass> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SSuperClass> query = builder.createQuery(SSuperClass.class);
        Root<SSuperClass> productRoot = query.from(SSuperClass.class);
        CriteriaQuery<SSuperClass> allQuery = query.select(productRoot);

        return entityManager.createQuery(allQuery).getResultList();
    }

    public SSuperClass get(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SSuperClass> query = builder.createQuery(SSuperClass.class);
        Root<SSuperClass> productRoot = query.from(SSuperClass.class);
        query.where(builder.equal(productRoot.get(SSuperClass_.id), id));

        return entityManager.createQuery(query).getSingleResult();
    }
}
