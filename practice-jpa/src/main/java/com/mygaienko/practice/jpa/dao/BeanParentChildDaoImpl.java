package com.mygaienko.practice.jpa.dao;

import com.mygaienko.practice.jpa.dao.interfaces.AuthorDao;
import com.mygaienko.practice.jpa.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by enda1n on 05.12.2015.
 */
@Repository
public class BeanParentChildDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<BeanParentChild> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BeanParentChild> cq = cb.createQuery(BeanParentChild.class);
        Root<BeanParentChild> parent = cq.from(BeanParentChild.class);

        return entityManager.createQuery(cq).getResultList();
    }

    public List<BeanParentChild> getAllEager() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BeanParentChild> cq = cb.createQuery(BeanParentChild.class);
        Root<BeanParentChild> parent = cq.from(BeanParentChild.class);

        parent.fetch(BeanParentChild_.children);

        return entityManager.createQuery(cq).getResultList();
    }

}
