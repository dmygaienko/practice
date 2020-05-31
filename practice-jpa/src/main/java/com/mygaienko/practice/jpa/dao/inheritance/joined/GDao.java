package com.mygaienko.practice.jpa.dao.inheritance.joined;

import com.mygaienko.practice.jpa.model.inheritance.joined.G;
import com.mygaienko.practice.jpa.model.inheritance.singletable.E;
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
public class GDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<G> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<G> query = builder.createQuery(G.class);
        Root<G> root = query.from(G.class);
        CriteriaQuery<G> allQuery = query.select(root);

        return entityManager.createQuery(allQuery).getResultList();
    }
}
