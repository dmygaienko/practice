package com.mygaienko.practice.dao;

import com.mygaienko.practice.dao.interfaces.AuthorDao;
import com.mygaienko.practice.model.*;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

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

    @Override
    public Author getByBeanANameAndBeanBCode(String name, String beanAName, String beanBCode) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);

        Root<Author> authorRoot = criteriaQuery.from(Author.class);
        Join<Author, BeanA> beanAJoin = authorRoot.join(Author_.beanA);
        Join<Author, BeanB> beanBJoin = authorRoot.join(Author_.beanB);

        criteriaQuery.where(builder.equal(authorRoot.get(Author_.name), name),
                builder.equal(beanAJoin.get(BeanA_.name), beanAName),
                builder.equal(beanBJoin.get(BeanB_.code), beanBCode));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<Author> getByBeanANameLike(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);
        cq.select(root);

        Subquery<BeanA> subquery = cq.subquery(BeanA.class);
        Root<BeanA> subRoot = subquery.from(BeanA.class);
        subquery.select(subRoot);

        //Predicate subP = cb.like(subRoot.get(BeanA_.name), name);
        Predicate subP = cb.equal(subRoot.get(BeanA_.name), name);
        subquery.where(subP);

        //cq.where(cb.exists(subquery));
        cq.where(cb.in(subquery));

        //cq.multiselect(beanARoot.get(BeanA_.id));

        /*cq.select(authorRoot).where(
                cb.in(authorRoot.get(Author_.beanA)).value(beanASubquery));*/

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Author> getByBeanAId(long l) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);
        cq.select(root);

        Subquery<BeanA> subquery = cq.subquery(BeanA.class);
        Root<BeanA> subRoot = subquery.from(BeanA.class);
        subquery.select(subRoot);

        Predicate subP = cb.equal(subRoot.get(BeanA_.id), l);
        subquery.where(subP);

        cq.where(cb.in(subquery));

        return entityManager.createQuery(cq).getResultList();
    }


    @Override
    public List<Author> getByNameLike(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);

        Root<Author> authorRoot = criteriaQuery.from(Author.class);

        criteriaQuery.where(builder.like(authorRoot.get(Author_.name), name));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
