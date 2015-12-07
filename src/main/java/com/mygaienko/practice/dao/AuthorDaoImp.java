package com.mygaienko.practice.dao;

import com.mygaienko.practice.dao.interfaces.AuthorDao;
import com.mygaienko.practice.model.Author;
import com.mygaienko.practice.model.Author_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by enda1n on 05.12.2015.
 */
@Repository
public class AuthorDaoImp implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author get(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> author = cq.from(Author.class);
        cq.where(cb.equal(author.get(Author_.id), id));

        return entityManager.createQuery(cq).getSingleResult();
    }
}
