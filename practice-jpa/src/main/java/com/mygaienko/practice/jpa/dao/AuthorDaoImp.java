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
    public List<BeanA> getJoinOnNonPrimaryColumn(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BeanA> criteriaQuery = builder.createQuery(BeanA.class);

        Root<BeanA> root = criteriaQuery.from(BeanA.class);
        Join<BeanA, BeanB> beanAJoin = root.join(BeanA_.beanB);

        /*criteriaQuery.where(builder.equal(authorRoot.get(Author_.name), name),
                builder.equal(beanAJoin.get(BeanA_.name), beanAName),
                builder.equal(beanBJoin.get(BeanB_.code), beanBCode));*/

        return entityManager.createQuery(criteriaQuery).getResultList();
       // return null;
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

        Predicate subP = cb.equal(subRoot.get(BeanA_.name), name);
        subquery.where(subP);

        cq.select(root).where(
                cb.in(root.get(Author_.beanA)).value(subquery));

        return entityManager.createQuery(cq).getResultList();
    }


    @Override
    public List<Author> getByBeanAId(long l) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);
        cq.select(root);

        //Subquery<Long> subquery = cq.subquery(Long.class);
        Subquery<BeanA> subquery = cq.subquery(BeanA.class);
        Root<BeanA> subRoot = subquery.from(BeanA.class);
        subquery.select(subRoot);
        //subquery.select(subRoot.get(BeanA_.id));

        Predicate subP = cb.equal(subRoot.get(BeanA_.id), l);
        subquery.where(subP);

        cq.where(cb.in(root.get(Author_.beanA)).value(subquery));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Author> getSubQuery(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);
        cq.select(root);

        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Author> subRoot = subquery.from(Author.class);
        subquery.select(subRoot.get(Author_.id));

        Predicate subP = cb.equal(subRoot.get(Author_.name), name);
        subquery.where(subP);

        cq.where(cb.in(root.get(Author_.id)).value(subquery));

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
