package com.mygaienko.practice.jpa.dao;

import com.mygaienko.practice.jpa.model.Product;
import com.mygaienko.practice.jpa.model.Product_;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by dmygaenko on 15/01/2016.
 */
@Repository
public class ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        CriteriaQuery<Product> allQuery = query.select(productRoot);

        return entityManager.createQuery(allQuery).getResultList();
    }

    public Product get(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        query.where(builder.equal(productRoot.get(Product_.id), id));

        return entityManager.createQuery(query).getSingleResult();
    }

    public List<Product> getByIds(List<String> ids) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        query.where(productRoot.get(Product_.id).in(ids));

        return entityManager.createQuery(query).getResultList();
    }

    public Product get(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        query.where(builder.equal(productRoot.get(Product_.name), name));

        return entityManager.createQuery(query).getSingleResult();
    }

    public Product merge(Product product) {
        return entityManager.merge(product);
    }

    public void persist(Product product) {
        entityManager.persist(product);
    }

    public void remove(Product product) {
        entityManager.remove(entityManager.contains(product) ? product : entityManager.merge(product));
    }

    public Product pessimisticMerge(Product product) {
        Product productToMerge = entityManager.find(Product.class, product.getId(), LockModeType.PESSIMISTIC_WRITE);
        productToMerge.setCode(product.getCode());
        productToMerge.setName(product.getName());
        productToMerge.setVersion(product.getVersion());
        return entityManager.merge(productToMerge);
    }

    public void refresh(Product product) {
        entityManager.refresh(product);
    }

    public void flush() {
        entityManager.flush();
    }
}
